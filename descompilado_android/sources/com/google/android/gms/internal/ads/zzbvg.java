package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbvg implements zzdvi<zzbux> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzbxn> zzemz;
    private final zzdvv<zzbuf> zzenf;
    private final zzdvv<zzbyr> zzfnb;
    private final zzdvv<zzbhd> zzfnc;

    public zzbvg(zzdvv<Context> zzdvvVar, zzdvv<zzbyr> zzdvvVar2, zzdvv<zzbxn> zzdvvVar3, zzdvv<zzbhd> zzdvvVar4, zzdvv<zzbuf> zzdvvVar5) {
        this.zzejn = zzdvvVar;
        this.zzfnb = zzdvvVar2;
        this.zzemz = zzdvvVar3;
        this.zzfnc = zzdvvVar4;
        this.zzenf = zzdvvVar5;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbux(this.zzejn.get(), this.zzfnb.get(), this.zzemz.get(), this.zzfnc.get(), this.zzenf.get());
    }
}
