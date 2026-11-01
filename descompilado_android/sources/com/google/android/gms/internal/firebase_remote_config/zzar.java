package com.google.android.gms.internal.firebase_remote_config;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: classes2.dex */
final class zzar extends zzaj {
    private final HttpURLConnection zzct;

    zzar(HttpURLConnection httpURLConnection) {
        this.zzct = httpURLConnection;
        httpURLConnection.setInstanceFollowRedirects(false);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaj
    public final void addHeader(String str, String str2) {
        this.zzct.addRequestProperty(str, str2);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaj
    public final void zza(int i, int i2) {
        this.zzct.setReadTimeout(i2);
        this.zzct.setConnectTimeout(i);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzaj
    public final zzai zzaj() throws IOException {
        HttpURLConnection httpURLConnection = this.zzct;
        if (zzai() != null) {
            String contentType = getContentType();
            if (contentType != null) {
                addHeader(HttpRequest.HEADER_CONTENT_TYPE, contentType);
            }
            String contentEncoding = getContentEncoding();
            if (contentEncoding != null) {
                addHeader(HttpRequest.HEADER_CONTENT_ENCODING, contentEncoding);
            }
            long contentLength = getContentLength();
            if (contentLength >= 0) {
                httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_LENGTH, Long.toString(contentLength));
            }
            String requestMethod = httpURLConnection.getRequestMethod();
            if (HttpRequest.METHOD_POST.equals(requestMethod) || HttpRequest.METHOD_PUT.equals(requestMethod)) {
                httpURLConnection.setDoOutput(true);
                if (contentLength >= 0 && contentLength <= 2147483647L) {
                    httpURLConnection.setFixedLengthStreamingMode((int) contentLength);
                } else {
                    httpURLConnection.setChunkedStreamingMode(0);
                }
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    zzai().writeTo(outputStream);
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        throw e;
                    }
                } catch (Throwable th) {
                    try {
                        outputStream.close();
                    } catch (IOException unused) {
                    }
                    throw th;
                }
            } else {
                Object[] objArr = {requestMethod};
                if (!(contentLength == 0)) {
                    throw new IllegalArgumentException(zzdy.zza("%s with non-zero content length is not supported", objArr));
                }
            }
        }
        try {
            httpURLConnection.connect();
            return new zzaq(httpURLConnection);
        } catch (Throwable th2) {
            httpURLConnection.disconnect();
            throw th2;
        }
    }
}
