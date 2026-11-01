package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcrt implements zzdvi<zzcro> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzasx> zzesq;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<ScheduledExecutorService> zzfom;

    public zzcrt(zzdvv<zzasx> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<ScheduledExecutorService> zzdvvVar3, zzdvv<Executor> zzdvvVar4) {
        this.zzesq = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzfom = zzdvvVar3;
        this.zzfbt = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcro(this.zzesq.get(), this.zzejn.get(), this.zzfom.get(), this.zzfbt.get());
    }
}
