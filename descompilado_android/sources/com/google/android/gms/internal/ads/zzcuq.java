package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcuq implements zzdvi<zzcup> {
    private final zzdvv<zzcvi> zzeyn;
    private final zzdvv<String> zzgie;
    private final zzdvv<zzcul> zzgif;
    private final zzdvv<zzcto> zzgig;

    public zzcuq(zzdvv<String> zzdvvVar, zzdvv<zzcul> zzdvvVar2, zzdvv<zzcto> zzdvvVar3, zzdvv<zzcvi> zzdvvVar4) {
        this.zzgie = zzdvvVar;
        this.zzgif = zzdvvVar2;
        this.zzgig = zzdvvVar3;
        this.zzeyn = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcup(this.zzgie.get(), this.zzgif.get(), this.zzgig.get(), this.zzeyn.get());
    }
}
