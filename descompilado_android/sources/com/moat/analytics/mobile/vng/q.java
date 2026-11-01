package com.moat.analytics.mobile.vng;

import com.google.android.exoplayer2.DefaultLoadControl;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
class q {
    q() {
    }

    static com.moat.analytics.mobile.vng.a.b.a<String> a(String str) {
        InputStream inputStream = null;
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS);
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() >= 400) {
                    return com.moat.analytics.mobile.vng.a.b.a.a();
                }
                InputStream inputStream2 = httpURLConnection.getInputStream();
                com.moat.analytics.mobile.vng.a.b.a<String> aVarA = com.moat.analytics.mobile.vng.a.b.a.a(a(inputStream2));
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException unused) {
                    }
                }
                return aVarA;
            } catch (IOException unused2) {
                com.moat.analytics.mobile.vng.a.b.a<String> aVarA2 = com.moat.analytics.mobile.vng.a.b.a.a();
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return aVarA2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    private static String a(InputStream inputStream) throws IOException {
        char[] cArr = new char[256];
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int i = 0;
        do {
            int i2 = inputStreamReader.read(cArr, 0, cArr.length);
            if (i2 <= 0) {
                break;
            }
            i += i2;
            sb.append(cArr, 0, i2);
        } while (i < 1024);
        return sb.toString();
    }
}
