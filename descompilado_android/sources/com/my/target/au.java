package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: compiled from: HttpImageRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class au extends aw<Bitmap> {
    private final boolean dW;

    @NonNull
    public static au ai() {
        return new au(false);
    }

    @NonNull
    public static au aj() {
        return new au(true);
    }

    private au(boolean z) {
        this.dW = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [T, android.graphics.Bitmap] */
    @Override // com.my.target.aw
    @Nullable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Bitmap c(@NonNull String str, @NonNull Context context) {
        cd cdVarT = cd.t(context);
        if (cdVarT != null) {
            if (this.dW) {
                if (cdVarT.O(str) != null) {
                    g.a("image request (caching only): image already cached");
                    this.ck = true;
                    return null;
                }
            } else {
                this.dZ = cdVarT.getBitmap(str);
                if (this.dZ != 0) {
                    this.ck = true;
                    return (Bitmap) this.dZ;
                }
            }
        } else {
            g.a("unable to open disk cache and get image " + str);
            if (this.dW) {
                this.dY = false;
                this.o = "image request (caching only) error: can't cache image";
                g.a(this.o);
                return null;
            }
        }
        a(cdVarT, str);
        return (Bitmap) this.dZ;
    }

    private void a(@Nullable cd cdVar, @NonNull String str) {
        HttpURLConnection httpURLConnection;
        try {
            g.a("send image request: " + str);
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setInstanceFollowRedirects(true);
                httpURLConnection.setRequestProperty(i.z, "close");
                httpURLConnection.connect();
                this.responseCode = httpURLConnection.getResponseCode();
                if (this.responseCode == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    if (cdVar != null) {
                        a(cdVar, inputStream, str);
                    } else {
                        a(inputStream);
                    }
                } else {
                    this.dY = false;
                    this.o = "image request error: response code " + this.responseCode;
                    g.a(this.o);
                }
            } catch (Throwable th) {
                th = th;
                this.dY = false;
                this.o = th.getMessage();
                g.a("image request error: " + this.o);
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = null;
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [T, android.graphics.Bitmap] */
    private void a(@NonNull cd cdVar, @NonNull InputStream inputStream, @NonNull String str) {
        File fileA = cdVar.a(inputStream, str);
        if (fileA != null) {
            if (this.dW) {
                return;
            }
            this.dZ = BitmapFactory.decodeFile(fileA.getAbsolutePath());
        } else {
            this.dY = false;
            this.o = "image request error: can't save image to disk cache";
            g.a(this.o);
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [T, android.graphics.Bitmap] */
    private void a(@NonNull InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        this.dZ = BitmapFactory.decodeStream(bufferedInputStream);
        try {
            bufferedInputStream.close();
        } catch (IOException e) {
            g.a(e.getMessage());
        }
    }
}
