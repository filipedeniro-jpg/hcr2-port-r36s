package com.google.android.gms.internal.ads;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdcy<V> extends zzdcd<V> implements zzdcp<V>, ScheduledFuture<V> {
    private final ScheduledFuture<?> zzgrn;

    public zzdcy(zzdcp<V> zzdcpVar, ScheduledFuture<?> scheduledFuture) {
        super(zzdcpVar);
        this.zzgrn = scheduledFuture;
    }

    @Override // com.google.android.gms.internal.ads.zzdcb, java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        boolean zCancel = super.cancel(z);
        if (zCancel) {
            this.zzgrn.cancel(z);
        }
        return zCancel;
    }

    @Override // java.util.concurrent.Delayed
    public final long getDelay(TimeUnit timeUnit) {
        return this.zzgrn.getDelay(timeUnit);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Delayed delayed) {
        return this.zzgrn.compareTo(delayed);
    }
}
