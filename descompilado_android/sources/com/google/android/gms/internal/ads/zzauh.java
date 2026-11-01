package com.google.android.gms.internal.ads;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import java.io.InputStream;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(21)
public class zzauh extends zzaue {
    @Override // com.google.android.gms.internal.ads.zzaub
    public final int zzvp() {
        return R.style.Theme.Material.Dialog.Alert;
    }

    @Override // com.google.android.gms.internal.ads.zzaub
    public final CookieManager zzbc(Context context) {
        if (zzvo()) {
            return null;
        }
        try {
            return CookieManager.getInstance();
        } catch (Throwable th) {
            zzatm.zzc("Failed to obtain CookieManager.", th);
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaub
    public final zzbbf zza(zzbbc zzbbcVar, zzrz zzrzVar, boolean z) {
        return new zzbch(zzbbcVar, zzrzVar, z);
    }

    @Override // com.google.android.gms.internal.ads.zzaub
    public final WebResourceResponse zza(String str, String str2, int i, String str3, Map<String, String> map, InputStream inputStream) {
        return new WebResourceResponse(str, str2, i, str3, map, inputStream);
    }
}
