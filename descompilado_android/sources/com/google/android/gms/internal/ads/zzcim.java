package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcim implements zzdvi<zzcij> {
    private final zzdvv<zzbds> zzfiv;
    private final zzdvv<zzblu.zza> zzfiw;
    private final zzdvv<zzbox> zzfix;

    public zzcim(zzdvv<zzbds> zzdvvVar, zzdvv<zzblu.zza> zzdvvVar2, zzdvv<zzbox> zzdvvVar3) {
        this.zzfiv = zzdvvVar;
        this.zzfiw = zzdvvVar2;
        this.zzfix = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcij(this.zzfiv.get(), this.zzfiw.get(), this.zzfix.get());
    }
}
