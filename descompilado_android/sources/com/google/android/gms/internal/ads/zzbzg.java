package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbzg implements zzdvi<zzbyt> {
    private final zzdvv<zzbgu> zzelr;
    private final zzdvv<zzbnb> zzelz;
    private final zzdvv<zzbmf> zzemc;
    private final zzdvv<zzbnu> zzemu;
    private final zzdvv<zzbqb> zzend;
    private final zzdvv<zzbop> zzetq;
    private final zzdvv<zzbnp> zzetw;
    private final zzdvv<zzbol> zzevm;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzaro> zzffz;
    private final zzdvv<zzdf> zzfjv;
    private final zzdvv<com.google.android.gms.ads.internal.zza> zzfqi;
    private final zzdvv<zzbmv> zzfqj;

    private zzbzg(zzdvv<zzbmf> zzdvvVar, zzdvv<zzbnb> zzdvvVar2, zzdvv<zzbnp> zzdvvVar3, zzdvv<zzbnu> zzdvvVar4, zzdvv<zzbop> zzdvvVar5, zzdvv<Executor> zzdvvVar6, zzdvv<zzbqb> zzdvvVar7, zzdvv<zzbgu> zzdvvVar8, zzdvv<com.google.android.gms.ads.internal.zza> zzdvvVar9, zzdvv<zzbmv> zzdvvVar10, zzdvv<zzaro> zzdvvVar11, zzdvv<zzdf> zzdvvVar12, zzdvv<zzbol> zzdvvVar13) {
        this.zzemc = zzdvvVar;
        this.zzelz = zzdvvVar2;
        this.zzetw = zzdvvVar3;
        this.zzemu = zzdvvVar4;
        this.zzetq = zzdvvVar5;
        this.zzfbt = zzdvvVar6;
        this.zzend = zzdvvVar7;
        this.zzelr = zzdvvVar8;
        this.zzfqi = zzdvvVar9;
        this.zzfqj = zzdvvVar10;
        this.zzffz = zzdvvVar11;
        this.zzfjv = zzdvvVar12;
        this.zzevm = zzdvvVar13;
    }

    public static zzbzg zza(zzdvv<zzbmf> zzdvvVar, zzdvv<zzbnb> zzdvvVar2, zzdvv<zzbnp> zzdvvVar3, zzdvv<zzbnu> zzdvvVar4, zzdvv<zzbop> zzdvvVar5, zzdvv<Executor> zzdvvVar6, zzdvv<zzbqb> zzdvvVar7, zzdvv<zzbgu> zzdvvVar8, zzdvv<com.google.android.gms.ads.internal.zza> zzdvvVar9, zzdvv<zzbmv> zzdvvVar10, zzdvv<zzaro> zzdvvVar11, zzdvv<zzdf> zzdvvVar12, zzdvv<zzbol> zzdvvVar13) {
        return new zzbzg(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5, zzdvvVar6, zzdvvVar7, zzdvvVar8, zzdvvVar9, zzdvvVar10, zzdvvVar11, zzdvvVar12, zzdvvVar13);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbyt(this.zzemc.get(), this.zzelz.get(), this.zzetw.get(), this.zzemu.get(), this.zzetq.get(), this.zzfbt.get(), this.zzend.get(), this.zzelr.get(), this.zzfqi.get(), this.zzfqj.get(), this.zzffz.get(), this.zzfjv.get(), this.zzevm.get());
    }
}
