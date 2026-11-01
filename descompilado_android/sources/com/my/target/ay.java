package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: compiled from: HttpVideoRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ay extends aw<String> {
    @NonNull
    public static ay ao() {
        return new ay();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v16, types: [T, java.lang.String] */
    @Override // com.my.target.aw
    @Nullable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public String c(@NonNull String str, @NonNull Context context) {
        HttpURLConnection httpURLConnection;
        cd cdVarT = cd.t(context);
        if (cdVarT != null) {
            this.dZ = cdVarT.N(str);
            if (this.dZ != 0) {
                this.ck = true;
                return (String) this.dZ;
            }
            try {
                g.a("send video request: " + str);
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setInstanceFollowRedirects(true);
                    httpURLConnection.setRequestProperty(i.z, "close");
                    httpURLConnection.connect();
                    this.responseCode = httpURLConnection.getResponseCode();
                    if (this.responseCode == 200) {
                        File fileB = cdVarT.b(httpURLConnection.getInputStream(), str);
                        if (fileB != null) {
                            this.dZ = fileB.getAbsolutePath();
                        } else {
                            this.dY = false;
                            this.o = "video request error: can't save video to disk cache";
                            g.a(this.o);
                        }
                    } else {
                        this.dY = false;
                        this.o = "video request error: response code " + this.responseCode;
                        g.a(this.o);
                    }
                } catch (Throwable th) {
                    th = th;
                    this.dY = false;
                    this.o = th.getMessage();
                    g.a("video request error: " + this.o);
                }
            } catch (Throwable th2) {
                th = th2;
                httpURLConnection = null;
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return (String) this.dZ;
        }
        g.a("unable to open disk cache and load/save video " + str);
        this.dY = false;
        return null;
    }
}
