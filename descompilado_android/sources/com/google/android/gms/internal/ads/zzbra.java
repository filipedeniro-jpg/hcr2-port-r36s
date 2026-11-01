package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import com.amazon.device.ads.WebRequest;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbra implements zzrb {
    private zzczn zzfhs;

    @Override // com.google.android.gms.internal.ads.zzrb
    public final void zza(Activity activity, WebView webView) {
        try {
            this.zzfhs = new zzczn(activity, webView);
        } catch (RuntimeException e) {
            String strValueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 46);
            sb.append(" Failed to initialize the internal ArWebView: ");
            sb.append(strValueOf);
            zzatm.zzes(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzrb
    public final void zze(String str, String str2) {
        if (this.zzfhs == null) {
            zzatm.zzes("ArWebView is not initialized.");
        } else {
            zzczn.getWebView().loadDataWithBaseURL(str, str2, WebRequest.CONTENT_TYPE_HTML, "UTF-8", null);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzrb
    public final WebView getWebView() {
        if (this.zzfhs == null) {
            return null;
        }
        return zzczn.getWebView();
    }

    @Override // com.google.android.gms.internal.ads.zzrb
    public final View getView() {
        return this.zzfhs;
    }

    @Override // com.google.android.gms.internal.ads.zzrb
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.zzfhs != null) {
            zzczn.onPageStarted(webView, str, bitmap);
        }
    }
}
