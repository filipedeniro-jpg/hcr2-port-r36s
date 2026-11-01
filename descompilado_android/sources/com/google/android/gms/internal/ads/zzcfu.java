package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcfu implements zzdvi<zzcfq> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzbyr> zzfnb;
    private final zzdvv<zzbho> zzfwz;

    public zzcfu(zzdvv<zzbho> zzdvvVar, zzdvv<Context> zzdvvVar2, zzdvv<Executor> zzdvvVar3, zzdvv<zzbyr> zzdvvVar4, zzdvv<zzcvk> zzdvvVar5) {
        this.zzfwz = zzdvvVar;
        this.zzejn = zzdvvVar2;
        this.zzfdl = zzdvvVar3;
        this.zzfnb = zzdvvVar4;
        this.zzfds = zzdvvVar5;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcfq(this.zzfwz.get(), this.zzejn.get(), this.zzfdl.get(), this.zzfnb.get(), this.zzfds.get());
    }
}
