package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzdcf extends zzdcm {
    public static <V> zzdcp<V> zzah(@NullableDecl V v) {
        return v == null ? zzdcl.zza.zzgrb : new zzdcl.zza(v);
    }

    public static <V> zzdcp<V> zzi(Throwable th) {
        zzczx.checkNotNull(th);
        return new zzdcl.zzb(th);
    }

    public static <V, X extends Throwable> zzdcp<V> zzb(zzdcp<? extends V> zzdcpVar, Class<X> cls, zzdbq<? super X, ? extends V> zzdbqVar, Executor executor) {
        return zzdbd.zza(zzdcpVar, cls, zzdbqVar, executor);
    }

    public static <V> zzdcp<V> zza(zzdcp<V> zzdcpVar, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        return zzdcpVar.isDone() ? zzdcpVar : zzdcz.zzb(zzdcpVar, j, timeUnit, scheduledExecutorService);
    }

    public static <I, O> zzdcp<O> zzb(zzdcp<I> zzdcpVar, zzdbq<? super I, ? extends O> zzdbqVar, Executor executor) {
        return zzdbh.zza(zzdcpVar, zzdbqVar, executor);
    }

    public static <I, O> zzdcp<O> zzb(zzdcp<I> zzdcpVar, zzczs<? super I, ? extends O> zzczsVar, Executor executor) {
        return zzdbh.zza(zzdcpVar, zzczsVar, executor);
    }

    public static <V> zzdcp<List<V>> zzg(Iterable<? extends zzdcp<? extends V>> iterable) {
        return new zzdbr(zzdak.zzf(iterable), true);
    }

    @SafeVarargs
    public static <V> zzdck<V> zza(zzdcp<? extends V>... zzdcpVarArr) {
        return new zzdck<>(false, zzdak.zzb(zzdcpVarArr), null);
    }

    public static <V> zzdck<V> zzh(Iterable<? extends zzdcp<? extends V>> iterable) {
        return new zzdck<>(false, zzdak.zzf(iterable), null);
    }

    public static <V> void zza(zzdcp<V> zzdcpVar, zzdcg<? super V> zzdcgVar, Executor executor) {
        zzczx.checkNotNull(zzdcgVar);
        zzdcpVar.addListener(new zzdch(zzdcpVar, zzdcgVar), executor);
    }

    public static <V> V zzb(Future<V> future) throws ExecutionException {
        if (!future.isDone()) {
            throw new IllegalStateException(zzczz.zzb("Future was expected to be done: %s", future));
        }
        return (V) zzddi.zza(future);
    }

    public static <V> V zzc(Future<V> future) {
        zzczx.checkNotNull(future);
        try {
            return (V) zzddi.zza(future);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof Error) {
                throw new zzdca((Error) cause);
            }
            throw new zzddf(cause);
        }
    }
}
