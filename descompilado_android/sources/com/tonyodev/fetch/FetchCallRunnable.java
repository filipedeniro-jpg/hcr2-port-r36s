package com.tonyodev.fetch;

import android.os.Handler;
import android.os.Looper;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.tonyodev.fetch.callback.FetchCall;
import com.tonyodev.fetch.exception.DownloadInterruptedException;
import com.tonyodev.fetch.request.Header;
import com.tonyodev.fetch.request.Request;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
final class FetchCallRunnable implements Runnable {
    private BufferedReader bufferedReader;
    private final Callback callback;
    private final FetchCall<String> fetchCall;
    private HttpURLConnection httpURLConnection;
    private InputStream input;
    private final Request request;
    private String response;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private volatile boolean interrupted = false;

    interface Callback {
        void onDone(Request request);
    }

    FetchCallRunnable(Request request, FetchCall<String> fetchCall, Callback callback) {
        if (request == null) {
            throw new NullPointerException("Request Cannot be null");
        }
        if (fetchCall == null) {
            throw new NullPointerException("FetchCall cannot be null");
        }
        if (callback == null) {
            throw new NullPointerException("Callback cannot be null");
        }
        this.request = request;
        this.fetchCall = fetchCall;
        this.callback = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                setHttpConnectionPrefs();
                this.httpURLConnection.connect();
                int responseCode = this.httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    if (isInterrupted()) {
                        throw new DownloadInterruptedException("DIE", -118);
                    }
                    this.input = this.httpURLConnection.getInputStream();
                    this.response = inputToString();
                    if (!isInterrupted()) {
                        this.handler.post(new Runnable() { // from class: com.tonyodev.fetch.FetchCallRunnable.1
                            @Override // java.lang.Runnable
                            public void run() {
                                FetchCallRunnable.this.fetchCall.onSuccess(FetchCallRunnable.this.response, FetchCallRunnable.this.request);
                            }
                        });
                    }
                    release();
                    this.callback.onDone(this.request);
                    return;
                }
                throw new IllegalStateException("SSRV:" + responseCode);
            } catch (Exception e) {
                e.printStackTrace();
                final int code = ErrorUtils.getCode(e.getMessage());
                if (!isInterrupted()) {
                    this.handler.post(new Runnable() { // from class: com.tonyodev.fetch.FetchCallRunnable.2
                        @Override // java.lang.Runnable
                        public void run() {
                            FetchCallRunnable.this.fetchCall.onError(code, FetchCallRunnable.this.request);
                        }
                    });
                }
            }
        } catch (Throwable th) {
            release();
            this.callback.onDone(this.request);
            throw th;
        }
    }

    private void setHttpConnectionPrefs() throws IOException {
        this.httpURLConnection = (HttpURLConnection) new URL(this.request.getUrl()).openConnection();
        this.httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
        this.httpURLConnection.setReadTimeout(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS);
        this.httpURLConnection.setConnectTimeout(10000);
        this.httpURLConnection.setUseCaches(true);
        this.httpURLConnection.setDefaultUseCaches(true);
        this.httpURLConnection.setInstanceFollowRedirects(true);
        this.httpURLConnection.setDoInput(true);
        for (Header header : this.request.getHeaders()) {
            this.httpURLConnection.addRequestProperty(header.getHeader(), header.getValue());
        }
    }

    private String inputToString() throws IOException {
        StringBuilder sb = new StringBuilder();
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.input));
        while (true) {
            String line = this.bufferedReader.readLine();
            if (line == null || isInterrupted()) {
                break;
            }
            sb.append(line);
        }
        if (isInterrupted()) {
            return null;
        }
        return sb.toString();
    }

    private void release() {
        try {
            if (this.input != null) {
                this.input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (this.bufferedReader != null) {
                this.bufferedReader.close();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        HttpURLConnection httpURLConnection = this.httpURLConnection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private boolean isInterrupted() {
        return this.interrupted;
    }

    synchronized void interrupt() {
        this.interrupted = true;
    }

    public Request getRequest() {
        return this.request;
    }
}
