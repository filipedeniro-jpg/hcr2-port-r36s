package com.my.target;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/* JADX INFO: compiled from: HttpStatRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public class ax extends aw<String> {
    private static final int ea = 10;
    private int ci;

    @NonNull
    public static ax an() {
        return new ax();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.my.target.aw
    @Nullable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public String c(@NonNull String str, @NonNull Context context) {
        this.ci = 0;
        g(str, context);
        return (String) this.dZ;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void g(@NonNull String str, @NonNull Context context) {
        ba baVarF;
        HttpURLConnection httpURLConnection;
        this.dZ = str;
        g.a("send stat request: " + str);
        String strA = null;
        try {
            baVarF = ba.f(context);
        } catch (Throwable th) {
            g.a("MyTargetCookieManager error");
            th.printStackTrace();
            baVarF = null;
        }
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                httpURLConnection.addRequestProperty("User-Agent", System.getProperty("http.agent"));
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setRequestProperty(i.z, "close");
                if (baVarF != null) {
                    baVarF.b(httpURLConnection);
                }
                this.responseCode = httpURLConnection.getResponseCode();
                InputStream inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (this.responseCode == 200 || this.responseCode == 204 || this.responseCode == 404 || this.responseCode == 403) {
                    if (baVarF != null) {
                        baVarF.a(httpURLConnection);
                    }
                } else if (this.responseCode == 302 || this.responseCode == 301 || this.responseCode == 303) {
                    this.ci++;
                    strA = a(httpURLConnection);
                }
            } catch (Throwable th2) {
                th = th2;
                this.dY = false;
                this.o = th.getMessage();
                g.a("stat request error: " + this.o);
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        if (strA != null) {
            g.a("redirected to: " + strA);
            g(strA, context);
        }
    }

    /* JADX WARN: Type inference failed for: r5v4, types: [T, java.lang.String] */
    @Nullable
    private String a(@NonNull HttpURLConnection httpURLConnection) {
        if (this.ci <= 10) {
            try {
                ?? string = httpURLConnection.getURL().toURI().resolve(new URI(httpURLConnection.getHeaderField("Location"))).toString();
                boolean z = true;
                if (Build.VERSION.SDK_INT >= 28 && !cl.U(string)) {
                    z = false;
                }
                if (!cl.S(string) && z) {
                    return string;
                }
                this.dZ = string;
            } catch (URISyntaxException unused) {
            }
        }
        return null;
    }
}
