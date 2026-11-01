package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcjr implements zzdvi<zzcjo> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzbym> zzfwz;

    public zzcjr(zzdvv<Context> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<zzbym> zzdvvVar3) {
        this.zzejn = zzdvvVar;
        this.zzfdl = zzdvvVar2;
        this.zzfwz = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcjo(this.zzejn.get(), this.zzfdl.get(), this.zzfwz.get());
    }
}
