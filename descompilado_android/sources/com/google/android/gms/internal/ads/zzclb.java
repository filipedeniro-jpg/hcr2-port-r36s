package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclb implements zzdvi<zzckx> {
    private final zzdvv<Executor> zzfbt;

    public zzclb(zzdvv<Executor> zzdvvVar) {
        this.zzfbt = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzckx(this.zzfbt.get());
    }
}
