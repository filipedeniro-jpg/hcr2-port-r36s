package com.tonyodev.fetch;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.tonyodev.fetch.exception.DownloadInterruptedException;
import com.tonyodev.fetch.request.Header;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class FetchRunnable implements Runnable {
    private static final String ACTION_DONE = "com.tonyodev.fetch.action_done";
    private static final String EXTRA_ID = "com.tonyodev.fetch.extra_id";
    private final LocalBroadcastManager broadcastManager;
    private final Context context;
    private final DatabaseHelper databaseHelper;
    private long downloadedBytes;
    private final String filePath;
    private long fileSize;
    private final List<Header> headers;
    private HttpURLConnection httpURLConnection;
    private final long id;
    private BufferedInputStream input;
    private volatile boolean interrupted = false;
    private final boolean loggingEnabled;
    private final long onUpdateInterval;
    private RandomAccessFile output;
    private int progress;
    private final String url;

    private boolean isResponseOk(int i) {
        return i == 200 || i == 202 || i == 206;
    }

    @NonNull
    static IntentFilter getDoneFilter() {
        return new IntentFilter(ACTION_DONE);
    }

    FetchRunnable(@NonNull Context context, long j, @NonNull String str, @NonNull String str2, @NonNull List<Header> list, long j2, boolean z, long j3) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        if (str == null) {
            throw new NullPointerException("Url cannot be null");
        }
        if (str2 == null) {
            throw new NullPointerException("FilePath cannot be null");
        }
        if (list == null) {
            this.headers = new ArrayList();
        } else {
            this.headers = list;
        }
        this.id = j;
        this.url = str;
        this.filePath = str2;
        this.fileSize = j2;
        this.context = context.getApplicationContext();
        this.broadcastManager = LocalBroadcastManager.getInstance(this.context);
        this.databaseHelper = DatabaseHelper.getInstance(this.context);
        this.loggingEnabled = z;
        this.onUpdateInterval = j3;
        this.databaseHelper.setLoggingEnabled(z);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                setHttpConnectionPrefs();
                Utils.createFileOrThrow(this.filePath);
                this.downloadedBytes = Utils.getFileSize(this.filePath);
                this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
                this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
                this.httpURLConnection.setRequestProperty("Range", "bytes=" + this.downloadedBytes + "-");
                if (isInterrupted()) {
                    throw new DownloadInterruptedException("DIE", -118);
                }
                this.httpURLConnection.connect();
                int responseCode = this.httpURLConnection.getResponseCode();
                if (isResponseOk(responseCode)) {
                    if (isInterrupted()) {
                        throw new DownloadInterruptedException("DIE", -118);
                    }
                    if (this.fileSize < 1) {
                        setContentLength();
                        this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
                        this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
                    }
                    this.output = new RandomAccessFile(this.filePath, "rw");
                    if (responseCode == 206) {
                        this.output.seek(this.downloadedBytes);
                    } else {
                        this.output.seek(0L);
                    }
                    this.input = new BufferedInputStream(this.httpURLConnection.getInputStream());
                    writeToFileAndPost();
                    this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
                    if (isInterrupted()) {
                        throw new DownloadInterruptedException("DIE", -118);
                    }
                    if (this.downloadedBytes >= this.fileSize && !isInterrupted()) {
                        if (this.fileSize < 1) {
                            this.fileSize = Utils.getFileSize(this.filePath);
                            this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
                            this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
                        } else {
                            this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
                        }
                        if (this.databaseHelper.updateStatus(this.id, FetchConst.STATUS_DONE, -1)) {
                            Utils.sendEventUpdate(this.broadcastManager, this.id, FetchConst.STATUS_DONE, this.progress, this.downloadedBytes, this.fileSize, -1);
                        }
                    }
                    release();
                    broadcastDone();
                    return;
                }
                throw new IllegalStateException("SSRV:" + responseCode);
            } catch (Exception e) {
                if (this.loggingEnabled) {
                    e.printStackTrace();
                }
                int code = ErrorUtils.getCode(e.getMessage());
                if (canRetry(code)) {
                    if (this.databaseHelper.updateStatus(this.id, FetchConst.STATUS_QUEUED, -1)) {
                        Utils.sendEventUpdate(this.broadcastManager, this.id, FetchConst.STATUS_QUEUED, this.progress, this.downloadedBytes, this.fileSize, -1);
                    }
                } else if (this.databaseHelper.updateStatus(this.id, FetchConst.STATUS_ERROR, code)) {
                    Utils.sendEventUpdate(this.broadcastManager, this.id, FetchConst.STATUS_ERROR, this.progress, this.downloadedBytes, this.fileSize, code);
                }
            }
        } catch (Throwable th) {
            release();
            broadcastDone();
            throw th;
        }
    }

    private void setHttpConnectionPrefs() throws IOException {
        this.httpURLConnection = (HttpURLConnection) new URL(this.url).openConnection();
        this.httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
        this.httpURLConnection.setReadTimeout(20000);
        this.httpURLConnection.setConnectTimeout(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS);
        this.httpURLConnection.setUseCaches(false);
        this.httpURLConnection.setDefaultUseCaches(false);
        this.httpURLConnection.setInstanceFollowRedirects(true);
        this.httpURLConnection.setDoInput(true);
        for (Header header : this.headers) {
            this.httpURLConnection.addRequestProperty(header.getHeader(), header.getValue());
        }
    }

    private void setContentLength() {
        try {
            this.fileSize = this.downloadedBytes + Long.valueOf(this.httpURLConnection.getHeaderField(HttpRequest.HEADER_CONTENT_LENGTH)).longValue();
        } catch (Exception unused) {
            this.fileSize = -1L;
        }
    }

    private void writeToFileAndPost() throws IOException {
        byte[] bArr = new byte[1024];
        long jNanoTime = System.nanoTime();
        while (true) {
            int i = this.input.read(bArr, 0, 1024);
            if (i == -1 || isInterrupted()) {
                return;
            }
            this.output.write(bArr, 0, i);
            this.downloadedBytes += (long) i;
            if (Utils.hasIntervalElapsed(jNanoTime, System.nanoTime(), this.onUpdateInterval) && !isInterrupted()) {
                this.progress = Utils.getProgress(this.downloadedBytes, this.fileSize);
                Utils.sendEventUpdate(this.broadcastManager, this.id, FetchConst.STATUS_DOWNLOADING, this.progress, this.downloadedBytes, this.fileSize, -1);
                this.databaseHelper.updateFileBytes(this.id, this.downloadedBytes, this.fileSize);
                jNanoTime = System.nanoTime();
            }
        }
    }

    private boolean canRetry(int i) {
        return !Utils.isNetworkAvailable(this.context) || i == -118 || i == -104 || i == -103;
    }

    private void release() {
        try {
            if (this.input != null) {
                this.input.close();
            }
        } catch (IOException e) {
            if (this.loggingEnabled) {
                e.printStackTrace();
            }
        }
        try {
            if (this.output != null) {
                this.output.close();
            }
        } catch (IOException e2) {
            if (this.loggingEnabled) {
                e2.printStackTrace();
            }
        }
        HttpURLConnection httpURLConnection = this.httpURLConnection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private void broadcastDone() {
        Intent intent = new Intent(ACTION_DONE);
        intent.putExtra("com.tonyodev.fetch.extra_id", this.id);
        this.broadcastManager.sendBroadcast(intent);
    }

    private boolean isInterrupted() {
        return this.interrupted;
    }

    synchronized void interrupt() {
        this.interrupted = true;
    }

    synchronized long getId() {
        return this.id;
    }

    static long getIdFromIntent(Intent intent) {
        if (intent == null) {
            return -1L;
        }
        return intent.getLongExtra("com.tonyodev.fetch.extra_id", -1L);
    }
}
