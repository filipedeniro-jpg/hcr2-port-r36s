package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbyc implements zzdvi<zzbxx> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzawv> zzfgk;
    private final zzdvv<zzdf> zzfjv;
    private final zzdvv<com.google.android.gms.ads.internal.zzb> zzfok;
    private final zzdvv<zzbbl> zzfpr;

    public zzbyc(zzdvv<Context> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<zzdf> zzdvvVar3, zzdvv<zzawv> zzdvvVar4, zzdvv<com.google.android.gms.ads.internal.zzb> zzdvvVar5, zzdvv<zzbbl> zzdvvVar6) {
        this.zzejn = zzdvvVar;
        this.zzfdl = zzdvvVar2;
        this.zzfjv = zzdvvVar3;
        this.zzfgk = zzdvvVar4;
        this.zzfok = zzdvvVar5;
        this.zzfpr = zzdvvVar6;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbxx(this.zzejn.get(), this.zzfdl.get(), this.zzfjv.get(), this.zzfgk.get(), this.zzfok.get(), this.zzfpr.get());
    }
}
