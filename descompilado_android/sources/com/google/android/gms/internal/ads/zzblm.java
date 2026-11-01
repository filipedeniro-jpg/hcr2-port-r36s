package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblm implements zzdvi<zzblj> {
    private final zzdvv<zzcbp> zzekf;
    private final zzdvv<zzato> zzeoo;
    private final zzdvv<zzawv> zzfcg;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<Context> zzfgb;

    private zzblm(zzdvv<Context> zzdvvVar, zzdvv<zzcvk> zzdvvVar2, zzdvv<zzawv> zzdvvVar3, zzdvv<zzato> zzdvvVar4, zzdvv<zzcbp> zzdvvVar5) {
        this.zzfgb = zzdvvVar;
        this.zzfds = zzdvvVar2;
        this.zzfcg = zzdvvVar3;
        this.zzeoo = zzdvvVar4;
        this.zzekf = zzdvvVar5;
    }

    public static zzblm zzc(zzdvv<Context> zzdvvVar, zzdvv<zzcvk> zzdvvVar2, zzdvv<zzawv> zzdvvVar3, zzdvv<zzato> zzdvvVar4, zzdvv<zzcbp> zzdvvVar5) {
        return new zzblm(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzblj(this.zzfgb.get(), this.zzfds.get(), this.zzfcg.get(), this.zzeoo.get(), this.zzekf.get());
    }
}
