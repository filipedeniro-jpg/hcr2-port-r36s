package com.vungle.warren.network;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import com.tonyodev.fetch.Fetch;
import com.tonyodev.fetch.listener.FetchListener;
import com.tonyodev.fetch.request.Request;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.utility.FileUtility;
import com.vungle.warren.utility.UnzipUtility;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class FetchDownloader implements Downloader, FetchListener {
    private static final String DOWNLOADS_FOLDER = "downloads_vungle";
    private static final String TAG = "FetchDownloader";
    private Context context;
    private int downloadCount = 0;
    protected Fetch fetch;
    protected HashMap<Long, Pair<Downloader.Listener, File>> operations;

    @Override // com.vungle.warren.network.Downloader
    public void pause() {
    }

    @Override // com.vungle.warren.network.Downloader
    public void resume() {
    }

    public FetchDownloader(Context context) {
        this.fetch = Fetch.getInstance(context);
        this.fetch.addFetchListener(this);
        this.fetch.removeAll();
        this.operations = new HashMap<>();
        this.context = context;
        new Fetch.Settings(context).enableLogging(true).apply();
    }

    @Override // com.vungle.warren.network.Downloader
    public boolean download(@NonNull String str, @NonNull File file, @NonNull Downloader.Listener listener) throws IllegalStateException, IOException, IllegalArgumentException {
        if (file.exists()) {
            try {
                if (!file.delete()) {
                    throw new IOException("Failed to delete file at " + file.getAbsolutePath());
                }
            } catch (SecurityException e) {
                throw new IOException(e);
            }
        }
        if (!this.fetch.isValid()) {
            Context context = this.context;
            if (context != null) {
                this.fetch = Fetch.getInstance(context);
                this.fetch.addFetchListener(this);
            } else {
                throw new IllegalStateException("Context is null, application is no longer running");
            }
        }
        String path = downloadFolder().getPath();
        StringBuilder sb = new StringBuilder();
        sb.append(file.getName());
        sb.append(" (");
        int i = this.downloadCount;
        this.downloadCount = i + 1;
        sb.append(i);
        sb.append(")");
        this.operations.put(Long.valueOf(this.fetch.enqueue(new Request(str, path, sb.toString()))), new Pair<>(listener, file));
        return true;
    }

    private File downloadFolder() {
        return new File(this.context.getCacheDir().getPath() + File.separator + DOWNLOADS_FOLDER);
    }

    @Override // com.tonyodev.fetch.listener.FetchListener
    public void onUpdate(long j, int i, int i2, long j2, long j3, int i3) throws Throwable {
        Log.d("FetchDownloader", String.format(Locale.ENGLISH, "%s: %d%% completed, %d/%d , error: %d", Long.valueOf(j), Integer.valueOf(i2), Long.valueOf(j2), Long.valueOf(j3), Integer.valueOf(i3)));
        if (this.operations.containsKey(Long.valueOf(j))) {
            Downloader.Listener listener = (Downloader.Listener) this.operations.get(Long.valueOf(j)).first;
            File file = (File) this.operations.get(Long.valueOf(j)).second;
            if (listener == null || file == null) {
                return;
            }
            listener.onProgress(i2);
            if (i3 != -1) {
                Log.e(TAG, "error: " + i3);
                listener.onError(new IOException("Error downloading !!!"));
            }
            if (i2 == 100) {
                File downloadedFile = this.fetch.getDownloadedFile(j);
                if (downloadedFile == null) {
                    listener.onError(new IOException("Downloaded file not found!"));
                    return;
                }
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                if (downloadedFile.renameTo(file)) {
                    if (file.getName().equals("postroll")) {
                        try {
                            UnzipUtility.unzip(file.getPath(), new File(file.getParent() + File.separator + Advertisement.POSTROLL_UNZIP).getPath());
                        } catch (IOException e) {
                            Log.e(TAG, "Error on unzipping assets", e);
                        }
                        try {
                            FileUtility.delete(file);
                        } catch (IOException e2) {
                            Log.e(TAG, "Error on deleting zip assets archive", e2);
                        }
                    }
                    listener.onComplete(file.getParentFile());
                    this.operations.remove(Long.valueOf(j));
                } else {
                    listener.onError(new IOException("Error processing file to destination directory!"));
                }
                if (this.operations.isEmpty()) {
                    this.fetch.release();
                }
            }
        }
    }
}
