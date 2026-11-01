package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcid implements zzdvi<zzcie> {
    private final zzdvv<zzbds> zzfiv;
    private final zzdvv<zzblu.zza> zzfiw;
    private final zzdvv<zzbox> zzfix;
    private final zzdvv<zzbsn> zzfiy;

    public zzcid(zzdvv<zzbds> zzdvvVar, zzdvv<zzbsn> zzdvvVar2, zzdvv<zzblu.zza> zzdvvVar3, zzdvv<zzbox> zzdvvVar4) {
        this.zzfiv = zzdvvVar;
        this.zzfiy = zzdvvVar2;
        this.zzfiw = zzdvvVar3;
        this.zzfix = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcie(this.zzfiv.get(), this.zzfiy.get(), this.zzfiw.get(), this.zzfix.get());
    }
}
