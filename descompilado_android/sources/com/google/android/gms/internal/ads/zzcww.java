package com.google.android.gms.internal.ads;

import androidx.annotation.VisibleForTesting;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcww<E, V> implements zzdcp<V> {
    private final E zzgkx;
    private final String zzgky;
    private final zzdcp<V> zzgkz;

    @VisibleForTesting(otherwise = 3)
    public zzcww(E e, String str, zzdcp<V> zzdcpVar) {
        this.zzgkx = e;
        this.zzgky = str;
        this.zzgkz = zzdcpVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcp
    public final void addListener(Runnable runnable, Executor executor) {
        this.zzgkz.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return this.zzgkz.cancel(z);
    }

    @Override // java.util.concurrent.Future
    public final V get() throws ExecutionException, InterruptedException {
        return this.zzgkz.get();
    }

    @Override // java.util.concurrent.Future
    public final V get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return this.zzgkz.get(j, timeUnit);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.zzgkz.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.zzgkz.isDone();
    }

    public final E zzanl() {
        return this.zzgkx;
    }

    public final String zzanm() {
        return this.zzgky;
    }

    public final String toString() {
        String str = this.zzgky;
        int iIdentityHashCode = System.identityHashCode(this);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
        sb.append(str);
        sb.append("@");
        sb.append(iIdentityHashCode);
        return sb.toString();
    }
}
