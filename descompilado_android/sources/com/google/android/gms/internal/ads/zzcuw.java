package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcuw implements zzdvi<zzcuv> {
    private final zzdvv<zzcvi> zzeyn;
    private final zzdvv<zzcul> zzgif;
    private final zzdvv<zzcto> zzgig;

    public zzcuw(zzdvv<zzcul> zzdvvVar, zzdvv<zzcto> zzdvvVar2, zzdvv<zzcvi> zzdvvVar3) {
        this.zzgif = zzdvvVar;
        this.zzgig = zzdvvVar2;
        this.zzeyn = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcuv(this.zzgif.get(), this.zzgig.get(), this.zzeyn.get());
    }
}
