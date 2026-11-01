package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxz implements zzdvi<zzbxv> {
    private final zzdvv<zzbnb> zzelz;
    private final zzdvv<zzbmf> zzemc;
    private final zzdvv<zzbnu> zzemu;
    private final zzdvv<zzbop> zzetq;
    private final zzdvv<zzbnp> zzetw;

    public zzbxz(zzdvv<zzbmf> zzdvvVar, zzdvv<zzbnb> zzdvvVar2, zzdvv<zzbnp> zzdvvVar3, zzdvv<zzbnu> zzdvvVar4, zzdvv<zzbop> zzdvvVar5) {
        this.zzemc = zzdvvVar;
        this.zzelz = zzdvvVar2;
        this.zzetw = zzdvvVar3;
        this.zzemu = zzdvvVar4;
        this.zzetq = zzdvvVar5;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbxv(this.zzemc.get(), this.zzelz.get(), this.zzetw.get(), this.zzemu.get(), this.zzetq.get());
    }
}
