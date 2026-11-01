package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqp implements zzdvi<zzcqi> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzckx> zzejy;
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<String> zzfch;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<ScheduledExecutorService> zzfom;
    private final zzdvv<zzckv> zzfzr;

    private zzcqp(zzdvv<zzdcs> zzdvvVar, zzdvv<ScheduledExecutorService> zzdvvVar2, zzdvv<String> zzdvvVar3, zzdvv<zzckx> zzdvvVar4, zzdvv<Context> zzdvvVar5, zzdvv<zzcvk> zzdvvVar6, zzdvv<zzckv> zzdvvVar7) {
        this.zzfbt = zzdvvVar;
        this.zzfom = zzdvvVar2;
        this.zzfch = zzdvvVar3;
        this.zzejy = zzdvvVar4;
        this.zzejn = zzdvvVar5;
        this.zzfds = zzdvvVar6;
        this.zzfzr = zzdvvVar7;
    }

    public static zzcqp zza(zzdvv<zzdcs> zzdvvVar, zzdvv<ScheduledExecutorService> zzdvvVar2, zzdvv<String> zzdvvVar3, zzdvv<zzckx> zzdvvVar4, zzdvv<Context> zzdvvVar5, zzdvv<zzcvk> zzdvvVar6, zzdvv<zzckv> zzdvvVar7) {
        return new zzcqp(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5, zzdvvVar6, zzdvvVar7);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcqi(this.zzfbt.get(), this.zzfom.get(), this.zzfch.get(), this.zzejy.get(), this.zzejn.get(), this.zzfds.get(), this.zzfzr.get());
    }
}
