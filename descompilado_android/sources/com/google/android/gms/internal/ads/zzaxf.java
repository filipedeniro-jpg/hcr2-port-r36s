package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public class zzaxf<T> implements zzdcp<T> {
    private final zzdda<T> zzdwg = zzdda.zzapg();

    public final boolean set(@Nullable T t) {
        return zzal(this.zzdwg.set(t));
    }

    public final boolean setException(Throwable th) {
        return zzal(this.zzdwg.setException(th));
    }

    private static boolean zzal(boolean z) {
        if (!z) {
            com.google.android.gms.ads.internal.zzp.zzkc().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture");
        }
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzdcp
    public void addListener(Runnable runnable, Executor executor) {
        this.zzdwg.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return this.zzdwg.cancel(z);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.zzdwg.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.zzdwg.isDone();
    }

    @Override // java.util.concurrent.Future
    public T get() throws ExecutionException, InterruptedException {
        return this.zzdwg.get();
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return this.zzdwg.get(j, timeUnit);
    }
}
