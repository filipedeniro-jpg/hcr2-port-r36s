package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;
import android.webkit.WebView;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzqd implements Runnable {
    final /* synthetic */ zzqb zzbph;
    private ValueCallback<String> zzbpv = new zzqc(this);
    final /* synthetic */ zzpv zzbpw;
    final /* synthetic */ WebView zzbpx;
    final /* synthetic */ boolean zzbpy;

    zzqd(zzqb zzqbVar, zzpv zzpvVar, WebView webView, boolean z) {
        this.zzbph = zzqbVar;
        this.zzbpw = zzpvVar;
        this.zzbpx = webView;
        this.zzbpy = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzbpx.getSettings().getJavaScriptEnabled()) {
            try {
                this.zzbpx.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzbpv);
            } catch (Throwable unused) {
                this.zzbpv.onReceiveValue("");
            }
        }
    }
}
