package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcxs implements zzdvi<zzcxn> {
    private final zzdvv<zzcxm> zzepl;
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<ScheduledExecutorService> zzfcy;

    private zzcxs(zzdvv<zzdcs> zzdvvVar, zzdvv<ScheduledExecutorService> zzdvvVar2, zzdvv<zzcxm> zzdvvVar3) {
        this.zzfbt = zzdvvVar;
        this.zzfcy = zzdvvVar2;
        this.zzepl = zzdvvVar3;
    }

    public static zzcxs zzs(zzdvv<zzdcs> zzdvvVar, zzdvv<ScheduledExecutorService> zzdvvVar2, zzdvv<zzcxm> zzdvvVar3) {
        return new zzcxs(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcxn(this.zzfbt.get(), this.zzfcy.get(), this.zzepl.get());
    }
}
