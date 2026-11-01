package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcei implements zzdvi<zzcdt> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzapb> zzfvd;
    private final zzdvv<zzbga> zzfve;
    private final zzdvv<zzaoy> zzfvf;

    private zzcei(zzdvv<Context> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<zzapb> zzdvvVar3, zzdvv<zzbga> zzdvvVar4, zzdvv<zzaoy> zzdvvVar5) {
        this.zzejn = zzdvvVar;
        this.zzfdl = zzdvvVar2;
        this.zzfvd = zzdvvVar3;
        this.zzfve = zzdvvVar4;
        this.zzfvf = zzdvvVar5;
    }

    public static zzcei zzg(zzdvv<Context> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<zzapb> zzdvvVar3, zzdvv<zzbga> zzdvvVar4, zzdvv<zzaoy> zzdvvVar5) {
        return new zzcei(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcdt(this.zzejn.get(), this.zzfdl.get(), this.zzfvd.get(), this.zzfve.get(), this.zzfvf.get());
    }
}
