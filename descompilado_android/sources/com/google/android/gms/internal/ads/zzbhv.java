package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbhv implements zzdvi<zzboa> {
    private final zzdvv<Clock> zzfbv;
    private final zzdvv<ScheduledExecutorService> zzfcy;

    public zzbhv(zzdvv<ScheduledExecutorService> zzdvvVar, zzdvv<Clock> zzdvvVar2) {
        this.zzfcy = zzdvvVar;
        this.zzfbv = zzdvvVar2;
    }

    public static zzboa zza(ScheduledExecutorService scheduledExecutorService, Clock clock) {
        return (zzboa) zzdvo.zza(new zzboa(scheduledExecutorService, clock), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzfcy.get(), this.zzfbv.get());
    }
}
