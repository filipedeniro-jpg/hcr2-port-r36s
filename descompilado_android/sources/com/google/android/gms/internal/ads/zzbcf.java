package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(11)
public final class zzbcf extends zzbce {
    public zzbcf(zzbbc zzbbcVar, zzrz zzrzVar, boolean z) {
        super(zzbbcVar, zzrzVar, z);
    }

    @Override // com.google.android.gms.internal.ads.zzbbf, android.webkit.WebViewClient
    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zza(webView, str, (Map<String, String>) null);
    }
}
