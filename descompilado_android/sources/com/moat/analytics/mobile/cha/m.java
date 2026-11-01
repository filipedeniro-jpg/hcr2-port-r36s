package com.moat.analytics.mobile.cha;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.moat.analytics.mobile.cha.base.functional.Optional;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
final class m {
    m() {
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static String m366(InputStream inputStream) throws IOException {
        char[] cArr = new char[256];
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        int i = 0;
        do {
            int i2 = inputStreamReader.read(cArr, 0, 256);
            if (i2 <= 0) {
                break;
            }
            i += i2;
            sb.append(cArr, 0, i2);
        } while (i < 1024);
        return sb.toString();
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static Optional<String> m367(String str) {
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
                    return Optional.empty();
                }
                InputStream inputStream2 = httpURLConnection.getInputStream();
                Optional<String> optionalOf = Optional.of(m366(inputStream2));
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException unused) {
                    }
                }
                return optionalOf;
            } catch (IOException unused2) {
                Optional<String> optionalEmpty = Optional.empty();
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return optionalEmpty;
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
}
