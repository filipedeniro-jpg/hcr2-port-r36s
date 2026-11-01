package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtw implements zzdvi<zzbtp> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzbub> zzelm;
    private final zzdvv<zzbjt> zzemy;
    private final zzdvv<zzbuf> zzenf;
    private final zzdvv<zzbtv> zzenk;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzbui> zzfdk;
    private final zzdvv<zzars> zzfes;
    private final zzdvv<zzbtx> zzfju;
    private final zzdvv<zzdf> zzfjv;
    private final zzdvv<zzbup> zzfko;
    private final zzdvv<zzbxf> zzfkp;
    private final zzdvv<zzbxd> zzfkq;
    private final zzdvv<zzbxm> zzfkr;
    private final zzdvv<zzbxc> zzfks;
    private final zzdvv<zzbxh> zzfkt;

    private zzbtw(zzdvv<zzbjt> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<zzbtx> zzdvvVar3, zzdvv<zzbuf> zzdvvVar4, zzdvv<zzbup> zzdvvVar5, zzdvv<zzbub> zzdvvVar6, zzdvv<zzbui> zzdvvVar7, zzdvv<zzbxf> zzdvvVar8, zzdvv<zzbxd> zzdvvVar9, zzdvv<zzbxm> zzdvvVar10, zzdvv<zzbxc> zzdvvVar11, zzdvv<zzbxh> zzdvvVar12, zzdvv<zzars> zzdvvVar13, zzdvv<zzdf> zzdvvVar14, zzdvv<zzawv> zzdvvVar15, zzdvv<Context> zzdvvVar16, zzdvv<zzbtv> zzdvvVar17) {
        this.zzemy = zzdvvVar;
        this.zzfbt = zzdvvVar2;
        this.zzfju = zzdvvVar3;
        this.zzenf = zzdvvVar4;
        this.zzfko = zzdvvVar5;
        this.zzelm = zzdvvVar6;
        this.zzfdk = zzdvvVar7;
        this.zzfkp = zzdvvVar8;
        this.zzfkq = zzdvvVar9;
        this.zzfkr = zzdvvVar10;
        this.zzfks = zzdvvVar11;
        this.zzfkt = zzdvvVar12;
        this.zzfes = zzdvvVar13;
        this.zzfjv = zzdvvVar14;
        this.zzfaa = zzdvvVar15;
        this.zzejn = zzdvvVar16;
        this.zzenk = zzdvvVar17;
    }

    public static zzbtw zza(zzdvv<zzbjt> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<zzbtx> zzdvvVar3, zzdvv<zzbuf> zzdvvVar4, zzdvv<zzbup> zzdvvVar5, zzdvv<zzbub> zzdvvVar6, zzdvv<zzbui> zzdvvVar7, zzdvv<zzbxf> zzdvvVar8, zzdvv<zzbxd> zzdvvVar9, zzdvv<zzbxm> zzdvvVar10, zzdvv<zzbxc> zzdvvVar11, zzdvv<zzbxh> zzdvvVar12, zzdvv<zzars> zzdvvVar13, zzdvv<zzdf> zzdvvVar14, zzdvv<zzawv> zzdvvVar15, zzdvv<Context> zzdvvVar16, zzdvv<zzbtv> zzdvvVar17) {
        return new zzbtw(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5, zzdvvVar6, zzdvvVar7, zzdvvVar8, zzdvvVar9, zzdvvVar10, zzdvvVar11, zzdvvVar12, zzdvvVar13, zzdvvVar14, zzdvvVar15, zzdvvVar16, zzdvvVar17);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbtp(this.zzemy.get(), this.zzfbt.get(), this.zzfju.get(), this.zzenf.get(), this.zzfko.get(), this.zzelm.get(), this.zzfdk.get(), zzdvj.zzao(this.zzfkp), zzdvj.zzao(this.zzfkq), zzdvj.zzao(this.zzfkr), zzdvj.zzao(this.zzfks), zzdvj.zzao(this.zzfkt), this.zzfes.get(), this.zzfjv.get(), this.zzfaa.get(), this.zzejn.get(), this.zzenk.get());
    }
}
