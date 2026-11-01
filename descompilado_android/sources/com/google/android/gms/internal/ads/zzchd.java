package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzchd implements zzdvi<zzcgy> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzawv> zzfcg;
    private final zzdvv<Executor> zzfdl;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzbyr> zzfnb;
    private final zzdvv<zzbry> zzfwz;

    public zzchd(zzdvv<Context> zzdvvVar, zzdvv<zzawv> zzdvvVar2, zzdvv<zzcvk> zzdvvVar3, zzdvv<Executor> zzdvvVar4, zzdvv<zzbry> zzdvvVar5, zzdvv<zzbyr> zzdvvVar6) {
        this.zzejn = zzdvvVar;
        this.zzfcg = zzdvvVar2;
        this.zzfds = zzdvvVar3;
        this.zzfdl = zzdvvVar4;
        this.zzfwz = zzdvvVar5;
        this.zzfnb = zzdvvVar6;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcgy(this.zzejn.get(), this.zzfcg.get(), this.zzfds.get(), this.zzfdl.get(), this.zzfwz.get(), this.zzfnb.get());
    }
}
