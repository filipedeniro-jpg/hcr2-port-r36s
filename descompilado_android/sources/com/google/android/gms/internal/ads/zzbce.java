package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import java.io.File;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(11)
public class zzbce extends zzbbf {
    public zzbce(zzbbc zzbbcVar, zzrz zzrzVar, boolean z) {
        super(zzbbcVar, zzrzVar, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    protected final WebResourceResponse zza(WebView webView, String str, @Nullable Map<String, String> map) {
        String str2;
        if (!(webView instanceof zzbbc)) {
            zzatm.zzeu("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        zzbbc zzbbcVar = (zzbbc) webView;
        if (this.zzeek != null) {
            this.zzeek.zza(str, map, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
            if (map == null) {
                map = Collections.emptyMap();
            }
            return super.zzd(str, map);
        }
        if (zzbbcVar.zzyy() != null) {
            zzbbcVar.zzyy().zzsm();
        }
        if (zzbbcVar.zzyw().zzaaq()) {
            str2 = (String) zzuo.zzoj().zzd(zzyt.zzcia);
        } else if (zzbbcVar.zzzd()) {
            str2 = (String) zzuo.zzoj().zzd(zzyt.zzchz);
        } else {
            str2 = (String) zzuo.zzoj().zzd(zzyt.zzchy);
        }
        com.google.android.gms.ads.internal.zzp.zzjy();
        return zzatv.zzd(zzbbcVar.getContext(), zzbbcVar.zzxp().zzbnh, str2);
    }
}
