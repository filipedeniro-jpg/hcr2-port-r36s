package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgr implements zzdvi<zzcgn> {
    private final zzdvv<zzbit> zzewj;
    private final zzdvv<zzcft> zzewn;
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<ScheduledExecutorService> zzfom;
    private final zzdvv<zzbmj> zzfxq;

    public zzcgr(zzdvv<zzbit> zzdvvVar, zzdvv<zzcft> zzdvvVar2, zzdvv<zzbmj> zzdvvVar3, zzdvv<ScheduledExecutorService> zzdvvVar4, zzdvv<zzdcs> zzdvvVar5) {
        this.zzewj = zzdvvVar;
        this.zzewn = zzdvvVar2;
        this.zzfxq = zzdvvVar3;
        this.zzfom = zzdvvVar4;
        this.zzfbt = zzdvvVar5;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcgn(this.zzewj.get(), this.zzewn.get(), this.zzfxq.get(), this.zzfom.get(), this.zzfbt.get());
    }
}
