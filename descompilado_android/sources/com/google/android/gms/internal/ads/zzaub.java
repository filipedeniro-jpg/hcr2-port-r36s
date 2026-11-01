package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(16)
public class zzaub {
    public String getDefaultUserAgent(Context context) {
        return "";
    }

    public boolean isAttachedToWindow(View view) {
        return (view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true;
    }

    public boolean zza(Activity activity, Configuration configuration) {
        return false;
    }

    public void zzbb(Context context) {
    }

    public void zzg(Activity activity) {
    }

    public int zzvm() {
        return 5;
    }

    public int zzvp() {
        return 1;
    }

    public long zzvq() {
        return -1L;
    }

    public boolean zza(final Context context, final WebSettings webSettings) {
        zzavw.zza(context, new Callable(context, webSettings) { // from class: com.google.android.gms.internal.ads.zzaua
            private final Context zzdpm;
            private final WebSettings zzdsq;

            {
                this.zzdpm = context;
                this.zzdsq = webSettings;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                Context context2 = this.zzdpm;
                WebSettings webSettings2 = this.zzdsq;
                if (context2.getCacheDir() != null) {
                    webSettings2.setAppCachePath(context2.getCacheDir().getAbsolutePath());
                    webSettings2.setAppCacheMaxSize(0L);
                    webSettings2.setAppCacheEnabled(true);
                }
                webSettings2.setDatabasePath(context2.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
                webSettings2.setDatabaseEnabled(true);
                webSettings2.setDomStorageEnabled(true);
                webSettings2.setDisplayZoomControls(false);
                webSettings2.setBuiltInZoomControls(true);
                webSettings2.setSupportZoom(true);
                webSettings2.setAllowContentAccess(false);
                return true;
            }
        });
        webSettings.setAllowFileAccessFromFileURLs(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        return true;
    }

    public static boolean zza(zzbbc zzbbcVar) {
        if (zzbbcVar == null) {
            return false;
        }
        zzbbcVar.onPause();
        return true;
    }

    public static boolean zzb(zzbbc zzbbcVar) {
        if (zzbbcVar == null) {
            return false;
        }
        zzbbcVar.onResume();
        return true;
    }

    public zzbbf zza(zzbbc zzbbcVar, zzrz zzrzVar, boolean z) {
        return new zzbcf(zzbbcVar, zzrzVar, z);
    }

    public ViewGroup.LayoutParams zzvn() {
        return new ViewGroup.LayoutParams(-2, -2);
    }

    public Drawable zza(Context context, Bitmap bitmap, boolean z, float f) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public static boolean zzvo() {
        int iMyUid = Process.myUid();
        return iMyUid == 0 || iMyUid == 1000;
    }

    public CookieManager zzbc(Context context) {
        if (zzvo()) {
            return null;
        }
        try {
            CookieSyncManager.createInstance(context);
            return CookieManager.getInstance();
        } catch (Throwable th) {
            zzatm.zzc("Failed to obtain CookieManager.", th);
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    public zzso zza(Context context, TelephonyManager telephonyManager) {
        return zzso.ENUM_UNKNOWN;
    }

    public int zza(ContentResolver contentResolver) {
        return Settings.System.getInt(contentResolver, "wifi_on", 0);
    }

    public int zzb(ContentResolver contentResolver) {
        return Settings.System.getInt(contentResolver, "airplane_mode_on", 0);
    }

    public WebResourceResponse zza(String str, String str2, int i, String str3, Map<String, String> map, InputStream inputStream) {
        return new WebResourceResponse(str, str2, inputStream);
    }

    private zzaub() {
    }

    public static zzaub zzcp(int i) {
        if (i >= 28) {
            return new zzaui();
        }
        if (i >= 26) {
            return new zzauj();
        }
        if (i >= 24) {
            return new zzaug();
        }
        if (i >= 21) {
            return new zzauh();
        }
        if (i >= 19) {
            return new zzaue();
        }
        if (i >= 18) {
            return new zzauf();
        }
        if (i >= 17) {
            return new zzauc();
        }
        return new zzaub();
    }
}
