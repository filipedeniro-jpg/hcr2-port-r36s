package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdcb<V> extends zzdad implements Future<V> {
    protected zzdcb() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzdad
    /* JADX INFO: renamed from: zzapd, reason: merged with bridge method [inline-methods] */
    public abstract Future<? extends V> zzaod();

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return zzaod().cancel(z);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return zzaod().isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return zzaod().isDone();
    }

    @Override // java.util.concurrent.Future
    public V get() throws ExecutionException, InterruptedException {
        return zzaod().get();
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return zzaod().get(j, timeUnit);
    }
}
