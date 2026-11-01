package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdcr {
    public static Executor zzapf() {
        return zzdbx.INSTANCE;
    }

    public static zzdcs zza(ExecutorService executorService) {
        if (executorService instanceof zzdcs) {
            return (zzdcs) executorService;
        }
        return executorService instanceof ScheduledExecutorService ? new zzdcv((ScheduledExecutorService) executorService) : new zzdcw(executorService);
    }

    static Executor zza(Executor executor, zzdbf<?> zzdbfVar) {
        zzczx.checkNotNull(executor);
        zzczx.checkNotNull(zzdbfVar);
        return executor == zzdbx.INSTANCE ? executor : new zzdcu(executor, zzdbfVar);
    }
}
