package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcoo implements zzdvi<zzcon<zzcrh>> {
    private final zzdvv<zzcrg> zzeko;
    private final zzdvv<Clock> zzfbv;

    public zzcoo(zzdvv<zzcrg> zzdvvVar, zzdvv<Clock> zzdvvVar2) {
        this.zzeko = zzdvvVar;
        this.zzfbv = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcon) zzdvo.zza(new zzcon(this.zzeko.get(), ((Long) zzuo.zzoj().zzd(zzyt.zzclv)).longValue(), this.zzfbv.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
