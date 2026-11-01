package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzckl<AdT> implements zzdvi<zzckh<AdT>> {
    private final zzdvv<zzdcs> zzfdl;
    private final zzdvv<zzcxn> zzfdw;
    private final zzdvv<zzzn> zzgah;
    private final zzdvv<zzckm<AdT>> zzgam;

    public zzckl(zzdvv<zzcxn> zzdvvVar, zzdvv<zzdcs> zzdvvVar2, zzdvv<zzzn> zzdvvVar3, zzdvv<zzckm<AdT>> zzdvvVar4) {
        this.zzfdw = zzdvvVar;
        this.zzfdl = zzdvvVar2;
        this.zzgah = zzdvvVar3;
        this.zzgam = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzckh(this.zzfdw.get(), this.zzfdl.get(), this.zzgah.get(), this.zzgam.get());
    }
}
