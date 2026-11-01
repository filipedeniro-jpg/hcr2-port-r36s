package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* JADX INFO: Add missing generic type declarations: [V] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzddg<V> extends zzdco<V> {
    private final Callable<V> zzgqt;
    private final /* synthetic */ zzddd zzgrs;

    zzddg(zzddd zzdddVar, Callable<V> callable) {
        this.zzgrs = zzdddVar;
        this.zzgqt = (Callable) zzczx.checkNotNull(callable);
    }

    @Override // com.google.android.gms.internal.ads.zzdco
    final boolean isDone() {
        return this.zzgrs.isDone();
    }

    @Override // com.google.android.gms.internal.ads.zzdco
    final V zzapb() throws Exception {
        return this.zzgqt.call();
    }

    @Override // com.google.android.gms.internal.ads.zzdco
    final void zzb(V v, Throwable th) {
        if (th == null) {
            this.zzgrs.set(v);
        } else {
            this.zzgrs.setException(th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdco
    final String zzapc() {
        return this.zzgqt.toString();
    }
}
