package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcoc implements zzdvi<zzcny> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<ScheduledExecutorService> zzfom;

    private zzcoc(zzdvv<Context> zzdvvVar, zzdvv<ScheduledExecutorService> zzdvvVar2, zzdvv<Executor> zzdvvVar3) {
        this.zzejn = zzdvvVar;
        this.zzfom = zzdvvVar2;
        this.zzfbt = zzdvvVar3;
    }

    public static zzcoc zzo(zzdvv<Context> zzdvvVar, zzdvv<ScheduledExecutorService> zzdvvVar2, zzdvv<Executor> zzdvvVar3) {
        return new zzcoc(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcny(this.zzejn.get(), this.zzfom.get(), this.zzfbt.get());
    }
}
