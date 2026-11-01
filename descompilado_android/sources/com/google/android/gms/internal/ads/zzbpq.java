package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbpq implements zzdvi<zzcil> {
    private final zzdvv<Clock> zzfbv;
    private final zzbox zzfhi;

    private zzbpq(zzbox zzboxVar, zzdvv<Clock> zzdvvVar) {
        this.zzfhi = zzboxVar;
        this.zzfbv = zzdvvVar;
    }

    public static zzbpq zzb(zzbox zzboxVar, zzdvv<Clock> zzdvvVar) {
        return new zzbpq(zzboxVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcil) zzdvo.zza(this.zzfhi.zza(this.zzfbv.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
