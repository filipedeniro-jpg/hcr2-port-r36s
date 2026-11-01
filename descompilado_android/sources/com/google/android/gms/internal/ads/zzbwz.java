package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbwz implements zzdvi<zzbwo> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzbyr> zzfnb;

    public zzbwz(zzdvv<Context> zzdvvVar, zzdvv<zzcvk> zzdvvVar2, zzdvv<Executor> zzdvvVar3, zzdvv<zzbyr> zzdvvVar4) {
        this.zzejn = zzdvvVar;
        this.zzfds = zzdvvVar2;
        this.zzfdl = zzdvvVar3;
        this.zzfnb = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbwo(this.zzejn.get(), this.zzfds.get(), this.zzfdl.get(), this.zzfnb.get());
    }
}
