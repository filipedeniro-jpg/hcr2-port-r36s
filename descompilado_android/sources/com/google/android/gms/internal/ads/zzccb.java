package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzccb implements zzdvi<zzcbp> {
    private final zzdvv<zzcgw> zzejj;
    private final zzdvv<zzcaz> zzeke;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzawv> zzfcg;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<ScheduledExecutorService> zzfom;
    private final zzdvv<Context> zzfsw;
    private final zzdvv<WeakReference<Context>> zzfsx;

    public zzccb(zzdvv<Executor> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<WeakReference<Context>> zzdvvVar3, zzdvv<Executor> zzdvvVar4, zzdvv<zzcgw> zzdvvVar5, zzdvv<ScheduledExecutorService> zzdvvVar6, zzdvv<zzcaz> zzdvvVar7, zzdvv<zzawv> zzdvvVar8) {
        this.zzfdl = zzdvvVar;
        this.zzfsw = zzdvvVar2;
        this.zzfsx = zzdvvVar3;
        this.zzfbt = zzdvvVar4;
        this.zzejj = zzdvvVar5;
        this.zzfom = zzdvvVar6;
        this.zzeke = zzdvvVar7;
        this.zzfcg = zzdvvVar8;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcbp(this.zzfdl.get(), this.zzfsw.get(), this.zzfsx.get(), this.zzfbt.get(), this.zzejj.get(), this.zzfom.get(), this.zzeke.get(), this.zzfcg.get());
    }
}
