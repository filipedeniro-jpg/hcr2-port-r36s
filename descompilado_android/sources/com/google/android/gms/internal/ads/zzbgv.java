package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgv implements zzdvi<zzbgu> {
    private final zzdvv<zzaim> zzfbr;
    private final zzdvv<zzbgs> zzfbs;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzbgl> zzfbu;
    private final zzdvv<Clock> zzfbv;

    private zzbgv(zzdvv<zzaim> zzdvvVar, zzdvv<zzbgs> zzdvvVar2, zzdvv<Executor> zzdvvVar3, zzdvv<zzbgl> zzdvvVar4, zzdvv<Clock> zzdvvVar5) {
        this.zzfbr = zzdvvVar;
        this.zzfbs = zzdvvVar2;
        this.zzfbt = zzdvvVar3;
        this.zzfbu = zzdvvVar4;
        this.zzfbv = zzdvvVar5;
    }

    public static zzbgv zza(zzdvv<zzaim> zzdvvVar, zzdvv<zzbgs> zzdvvVar2, zzdvv<Executor> zzdvvVar3, zzdvv<zzbgl> zzdvvVar4, zzdvv<Clock> zzdvvVar5) {
        return new zzbgv(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbgu(this.zzfbr.get(), this.zzfbs.get(), this.zzfbt.get(), this.zzfbu.get(), this.zzfbv.get());
    }
}
