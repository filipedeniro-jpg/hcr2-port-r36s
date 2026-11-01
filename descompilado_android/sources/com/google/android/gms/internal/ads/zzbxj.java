package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxj implements zzdvi<zzbxh> {
    private final zzdvv<zzbup> zzenl;
    private final zzdvv<Context> zzfgb;
    private final zzdvv<zzbtx> zzfju;
    private final zzdvv<zzbtp> zzfox;

    private zzbxj(zzdvv<Context> zzdvvVar, zzdvv<zzbtx> zzdvvVar2, zzdvv<zzbup> zzdvvVar3, zzdvv<zzbtp> zzdvvVar4) {
        this.zzfgb = zzdvvVar;
        this.zzfju = zzdvvVar2;
        this.zzenl = zzdvvVar3;
        this.zzfox = zzdvvVar4;
    }

    public static zzbxj zzb(zzdvv<Context> zzdvvVar, zzdvv<zzbtx> zzdvvVar2, zzdvv<zzbup> zzdvvVar3, zzdvv<zzbtp> zzdvvVar4) {
        return new zzbxj(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbxh(this.zzfgb.get(), this.zzfju.get(), this.zzenl.get(), this.zzfox.get());
    }
}
