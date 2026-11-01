package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdcv extends zzdcw implements zzdcs, ScheduledExecutorService {
    private final ScheduledExecutorService zzgrk;

    zzdcv(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        this.zzgrk = (ScheduledExecutorService) zzczx.checkNotNull(scheduledExecutorService);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzdcx zzdcxVar = new zzdcx(runnable);
        return new zzdcy(zzdcxVar, this.zzgrk.scheduleWithFixedDelay(zzdcxVar, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzdcx zzdcxVar = new zzdcx(runnable);
        return new zzdcy(zzdcxVar, this.zzgrk.scheduleAtFixedRate(zzdcxVar, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* synthetic */ ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        zzddd zzdddVarZze = zzddd.zze(callable);
        return new zzdcy(zzdddVarZze, this.zzgrk.schedule(zzdddVarZze, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* synthetic */ ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        zzddd zzdddVarZza = zzddd.zza(runnable, (Object) null);
        return new zzdcy(zzdddVarZza, this.zzgrk.schedule(zzdddVarZza, j, timeUnit));
    }
}
