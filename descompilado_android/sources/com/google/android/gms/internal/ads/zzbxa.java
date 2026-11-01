package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxa implements zzdvi<zzbwt> {
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzbhd> zzfnc;
    private final zzdvv<zzbqb> zzfou;

    public zzbxa(zzdvv<Executor> zzdvvVar, zzdvv<zzbhd> zzdvvVar2, zzdvv<zzbqb> zzdvvVar3) {
        this.zzfdl = zzdvvVar;
        this.zzfnc = zzdvvVar2;
        this.zzfou = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbwt(this.zzfdl.get(), this.zzfnc.get(), this.zzfou.get());
    }
}
