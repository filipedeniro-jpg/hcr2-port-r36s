package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebView;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbdj {

    @VisibleForTesting
    @GuardedBy("InvokeJavascriptWorkaround.class")
    private static Boolean zzehv;

    private zzbdj() {
    }

    @TargetApi(19)
    private static boolean zzb(WebView webView) {
        boolean zBooleanValue;
        synchronized (zzbdj.class) {
            if (zzehv == null) {
                try {
                    webView.evaluateJavascript("(function(){})()", null);
                    zzehv = true;
                } catch (IllegalStateException unused) {
                    zzehv = false;
                }
                zBooleanValue = zzehv.booleanValue();
            } else {
                zBooleanValue = zzehv.booleanValue();
            }
            throw th;
        }
        return zBooleanValue;
    }

    @TargetApi(19)
    static void zza(WebView webView, String str) {
        if (PlatformVersion.isAtLeastKitKat() && zzb(webView)) {
            webView.evaluateJavascript(str, null);
        } else {
            String strValueOf = String.valueOf(str);
            webView.loadUrl(strValueOf.length() != 0 ? "javascript:".concat(strValueOf) : new String("javascript:"));
        }
    }
}
