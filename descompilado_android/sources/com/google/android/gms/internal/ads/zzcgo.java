package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgo implements zzdvi<zzcgj> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzbry> zzfwz;
    private final zzdvv<zzcuz> zzfxo;

    public zzcgo(zzdvv<Context> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<zzbry> zzdvvVar3, zzdvv<zzcuz> zzdvvVar4) {
        this.zzejn = zzdvvVar;
        this.zzfdl = zzdvvVar2;
        this.zzfwz = zzdvvVar3;
        this.zzfxo = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcgj(this.zzejn.get(), this.zzfdl.get(), this.zzfwz.get(), this.zzfxo.get());
    }
}
