package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpk implements zzdvi<zzcpi> {
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<zzcuz> zzfxo;

    private zzcpk(zzdvv<zzdcs> zzdvvVar, zzdvv<zzcuz> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzfxo = zzdvvVar2;
    }

    public static zzcpk zzap(zzdvv<zzdcs> zzdvvVar, zzdvv<zzcuz> zzdvvVar2) {
        return new zzcpk(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcpi(this.zzfbt.get(), this.zzfxo.get());
    }
}
