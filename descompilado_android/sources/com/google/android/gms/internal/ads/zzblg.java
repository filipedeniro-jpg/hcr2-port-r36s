package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblg implements zzdvi<zzbjt> {
    private final zzdvv<zzbmv> zzelu;
    private final zzdvv<zzbnl> zzemq;
    private final zzdvv<zzcvj> zzfba;
    private final zzdvv<zzcvb> zzfem;
    private final zzdvv<String> zzffx;

    private zzblg(zzdvv<zzcvj> zzdvvVar, zzdvv<zzcvb> zzdvvVar2, zzdvv<zzbmv> zzdvvVar3, zzdvv<zzbnl> zzdvvVar4, zzdvv<String> zzdvvVar5) {
        this.zzfba = zzdvvVar;
        this.zzfem = zzdvvVar2;
        this.zzelu = zzdvvVar3;
        this.zzemq = zzdvvVar4;
        this.zzffx = zzdvvVar5;
    }

    public static zzblg zzb(zzdvv<zzcvj> zzdvvVar, zzdvv<zzcvb> zzdvvVar2, zzdvv<zzbmv> zzdvvVar3, zzdvv<zzbnl> zzdvvVar4, zzdvv<String> zzdvvVar5) {
        return new zzblg(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbjt(this.zzfba.get(), this.zzfem.get(), this.zzelu.get(), this.zzemq.get(), this.zzffx.get());
    }
}
