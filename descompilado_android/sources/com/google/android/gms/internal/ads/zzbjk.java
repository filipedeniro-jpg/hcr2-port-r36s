package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbjk implements zzdvi<zzbjb> {
    private final zzdvv<zzbjt> zzemy;
    private final zzdvv<Runnable> zzexf;
    private final zzdvv<zzack> zzfca;
    private final zzdvv<Executor> zzfdl;

    public zzbjk(zzdvv<zzbjt> zzdvvVar, zzdvv<zzack> zzdvvVar2, zzdvv<Runnable> zzdvvVar3, zzdvv<Executor> zzdvvVar4) {
        this.zzemy = zzdvvVar;
        this.zzfca = zzdvvVar2;
        this.zzexf = zzdvvVar3;
        this.zzfdl = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbjb(this.zzemy.get(), this.zzfca.get(), this.zzexf.get(), this.zzfdl.get());
    }
}
