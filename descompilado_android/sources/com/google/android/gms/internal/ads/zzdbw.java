package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: Add missing generic type declarations: [V] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdbw<V> extends zzdbv<V> {
    private final /* synthetic */ zzdbt zzgqs;
    private final Callable<V> zzgqt;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdbw(zzdbt zzdbtVar, Callable<V> callable, Executor executor) {
        super(zzdbtVar, executor);
        this.zzgqs = zzdbtVar;
        this.zzgqt = (Callable) zzczx.checkNotNull(callable);
    }

    @Override // com.google.android.gms.internal.ads.zzdco
    final V zzapb() throws Exception {
        this.zzgqr = false;
        return this.zzgqt.call();
    }

    @Override // com.google.android.gms.internal.ads.zzdbv
    final void setValue(V v) {
        this.zzgqs.set(v);
    }

    @Override // com.google.android.gms.internal.ads.zzdco
    final String zzapc() {
        return this.zzgqt.toString();
    }
}
