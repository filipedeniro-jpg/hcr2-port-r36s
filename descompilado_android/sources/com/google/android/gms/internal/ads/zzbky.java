package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbky implements zzdvi<zzasw> {
    private final zzdvv<zzatj> zzeod;
    private final zzdvv<Clock> zzfbv;
    private final zzdvv<zzcvk> zzfds;

    private zzbky(zzdvv<Clock> zzdvvVar, zzdvv<zzatj> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3) {
        this.zzfbv = zzdvvVar;
        this.zzeod = zzdvvVar2;
        this.zzfds = zzdvvVar3;
    }

    public static zzbky zzh(zzdvv<Clock> zzdvvVar, zzdvv<zzatj> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3) {
        return new zzbky(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzasw) zzdvo.zza(this.zzeod.get().zza(this.zzfbv.get(), this.zzfds.get().zzgjt), "Cannot return null from a non-@Nullable @Provides method");
    }
}
