package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: HttpLogRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public class av extends aw<Void> {

    @Nullable
    private String dX;

    @NonNull
    public static av ak() {
        return new av();
    }

    @Nullable
    public String getBody() {
        return this.dX;
    }

    @NonNull
    public av y(@Nullable String str) {
        this.dX = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:19:0x0096 A[PHI: r6
  0x0096: PHI (r6v6 java.net.HttpURLConnection) = (r6v5 java.net.HttpURLConnection), (r6v8 java.net.HttpURLConnection) binds: [B:18:0x0094, B:9:0x006c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:23:0x009d  */
    @Override // com.my.target.aw
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Void c(@NonNull String str, @NonNull Context context) throws Throwable {
        HttpURLConnection httpURLConnection;
        if (this.dX == null) {
            g.a("can't send log request: body is null");
            this.dY = false;
            return null;
        }
        g.a("send log request");
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                try {
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                    httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "text/html; charset=utf-8");
                    httpURLConnection.setRequestProperty(i.z, "close");
                    httpURLConnection.connect();
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName("UTF-8")));
                    bufferedWriter.write(this.dX);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    httpURLConnection.getInputStream().close();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th) {
                    th = th;
                    this.dY = false;
                    this.o = th.getMessage();
                    g.a("log request error: " + this.o);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
        }
        return null;
    }
}
