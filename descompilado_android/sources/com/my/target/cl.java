package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.net.URLDecoder;

/* JADX INFO: compiled from: UrlResolver.java */
/* JADX INFO: loaded from: classes2.dex */
public class cl {
    private static final String[] kq = {"http://play.google.com", "https://play.google.com", "http://market.android.com", "https://market.android.com", "market://", "samsungapps://"};
    private static final String kr = "https";

    @Nullable
    private a ks;

    @NonNull
    private final String url;

    /* JADX INFO: compiled from: UrlResolver.java */
    public interface a {
        void L(@Nullable String str);
    }

    @NonNull
    public static String decode(@NonNull String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Throwable th) {
            g.a("Unable to decode url " + th.getMessage());
            return str;
        }
    }

    public static boolean S(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : kq) {
                if (str.startsWith(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean T(@NonNull String str) {
        return str.startsWith("samsungapps://");
    }

    public static boolean U(@Nullable String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(kr);
    }

    @NonNull
    public static cl V(@NonNull String str) {
        return new cl(str);
    }

    private cl(@NonNull String str) {
        this.url = str;
    }

    @NonNull
    public cl a(@Nullable a aVar) {
        this.ks = aVar;
        return this;
    }

    public void y(@NonNull Context context) {
        final Context applicationContext = context.getApplicationContext();
        h.b(new Runnable() { // from class: com.my.target.cl.1
            @Override // java.lang.Runnable
            public void run() {
                final String strF = ax.an().f(cl.this.url, applicationContext);
                if (cl.this.ks == null) {
                    return;
                }
                h.c(new Runnable() { // from class: com.my.target.cl.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (cl.this.ks != null) {
                            cl.this.ks.L(strF);
                            cl.this.ks = null;
                        }
                    }
                });
            }
        });
    }
}
