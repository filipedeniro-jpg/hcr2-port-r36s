package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: HttpAdRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class at extends aw<String> {
    @NonNull
    public static at ah() {
        return new at();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16, types: [T, java.lang.String] */
    @Override // com.my.target.aw
    @Nullable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public String c(@NonNull String str, @NonNull Context context) {
        Throwable th;
        HttpURLConnection httpURLConnection;
        try {
            g.a("send ad request: " + str);
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setRequestProperty(i.z, "close");
                httpURLConnection.connect();
                this.responseCode = httpURLConnection.getResponseCode();
                if (this.responseCode == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), Charset.forName("UTF-8")));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    }
                    bufferedReader.close();
                    this.dZ = sb.toString();
                } else if (this.responseCode != 204) {
                    this.dY = false;
                    this.o = "ad request error: response code " + this.responseCode;
                    g.a(this.o);
                }
            } catch (Throwable th2) {
                th = th2;
                this.dY = false;
                this.o = th.getMessage();
                g.a("ad request error: " + this.o);
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        return (String) this.dZ;
    }
}
