package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbtb implements zzadx<Object> {
    private WeakReference<zzbta> zzfjr;

    private zzbtb(zzbta zzbtaVar) {
        this.zzfjr = new WeakReference<>(zzbtaVar);
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final void zza(Object obj, Map<String, String> map) {
        zzbta zzbtaVar = this.zzfjr.get();
        if (zzbtaVar == null) {
            return;
        }
        zzbtaVar.zzfjd.onAdImpression();
    }
}
