package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfl implements zzdvi<zzbfk> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzcgw> zzeju;
    private final zzdvv<zzcbp> zzekf;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<zzcfo<zzcvs, zzcgt>> zzfab;
    private final zzdvv<zzckx> zzfac;
    private final zzdvv<zzarv> zzfad;

    public zzbfl(zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<zzcgw> zzdvvVar3, zzdvv<zzcfo<zzcvs, zzcgt>> zzdvvVar4, zzdvv<zzckx> zzdvvVar5, zzdvv<zzcbp> zzdvvVar6, zzdvv<zzarv> zzdvvVar7) {
        this.zzejn = zzdvvVar;
        this.zzfaa = zzdvvVar2;
        this.zzeju = zzdvvVar3;
        this.zzfab = zzdvvVar4;
        this.zzfac = zzdvvVar5;
        this.zzekf = zzdvvVar6;
        this.zzfad = zzdvvVar7;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbfk(this.zzejn.get(), this.zzfaa.get(), this.zzeju.get(), this.zzfab.get(), this.zzfac.get(), this.zzekf.get(), this.zzfad.get());
    }
}
