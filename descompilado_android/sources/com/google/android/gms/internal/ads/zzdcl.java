package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzdcl<V> implements zzdcp<V> {
    private static final Logger zzgpo = Logger.getLogger(zzdcl.class.getName());

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    /* JADX INFO: loaded from: classes2.dex */
    static final class zzb<V> extends zzdbf.zzj<V> {
        zzb(Throwable th) {
            setException(th);
        }
    }

    zzdcl() {
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public abstract V get() throws ExecutionException;

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdcp
    public void addListener(Runnable runnable, Executor executor) {
        zzczx.checkNotNull(runnable, "Runnable was null.");
        zzczx.checkNotNull(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = zzgpo;
            Level level = Level.SEVERE;
            String strValueOf = String.valueOf(runnable);
            String strValueOf2 = String.valueOf(executor);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 57 + String.valueOf(strValueOf2).length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(strValueOf);
            sb.append(" with executor ");
            sb.append(strValueOf2);
            logger.logp(level, "com.google.common.util.concurrent.ImmediateFuture", "addListener", sb.toString(), (Throwable) e);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    static class zza<V> extends zzdcl<V> {
        static final zza<Object> zzgrb = new zza<>(null);

        @NullableDecl
        private final V value;

        zza(@NullableDecl V v) {
            this.value = v;
        }

        @Override // com.google.android.gms.internal.ads.zzdcl, java.util.concurrent.Future
        public final V get() {
            return this.value;
        }

        public final String toString() {
            String string = super.toString();
            String strValueOf = String.valueOf(this.value);
            StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 27 + String.valueOf(strValueOf).length());
            sb.append(string);
            sb.append("[status=SUCCESS, result=[");
            sb.append(strValueOf);
            sb.append("]]");
            return sb.toString();
        }
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws ExecutionException {
        zzczx.checkNotNull(timeUnit);
        return get();
    }
}
