package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcms implements zzdvi<zzcmq> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzcdo> zzgde;

    public zzcms(zzdvv<Executor> zzdvvVar, zzdvv<zzcdo> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzgde = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcmq(this.zzfbt.get(), this.zzgde.get());
    }
}
