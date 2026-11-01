package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzchy implements zzdvi<zzchv> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzbsr> zzfwz;

    public zzchy(zzdvv<Context> zzdvvVar, zzdvv<zzbsr> zzdvvVar2, zzdvv<Executor> zzdvvVar3) {
        this.zzejn = zzdvvVar;
        this.zzfwz = zzdvvVar2;
        this.zzfdl = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzchv(this.zzejn.get(), this.zzfwz.get(), this.zzfdl.get());
    }
}
