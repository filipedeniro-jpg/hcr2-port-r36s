package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzchi implements zzdvi<zzchg> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzawv> zzfcg;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzbry> zzfwz;

    public zzchi(zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<zzbry> zzdvvVar3, zzdvv<Executor> zzdvvVar4) {
        this.zzejn = zzdvvVar;
        this.zzfcg = zzdvvVar2;
        this.zzfwz = zzdvvVar3;
        this.zzfdl = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzchg(this.zzejn.get(), this.zzfcg.get(), this.zzfwz.get(), this.zzfdl.get());
    }
}
