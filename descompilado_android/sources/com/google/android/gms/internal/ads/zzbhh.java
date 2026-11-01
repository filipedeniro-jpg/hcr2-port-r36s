package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbhh implements zzpy {
    private final Clock zzbmd;
    private final ScheduledExecutorService zzfck;

    @Nullable
    @GuardedBy("this")
    private ScheduledFuture<?> zzfcl;

    @GuardedBy("this")
    private long zzfcm = -1;

    @GuardedBy("this")
    private long zzfcn = -1;

    @GuardedBy("this")
    private Runnable zzdrh = null;

    @GuardedBy("this")
    private boolean zzfco = false;

    public zzbhh(ScheduledExecutorService scheduledExecutorService, Clock clock) {
        this.zzfck = scheduledExecutorService;
        this.zzbmd = clock;
        com.google.android.gms.ads.internal.zzp.zzkb().zza(this);
    }

    @Override // com.google.android.gms.internal.ads.zzpy
    public final void zzo(boolean z) {
        if (z) {
            zzaej();
        } else {
            zzaei();
        }
    }

    public final synchronized void zza(int i, Runnable runnable) {
        this.zzdrh = runnable;
        long j = i;
        this.zzfcm = this.zzbmd.elapsedRealtime() + j;
        this.zzfcl = this.zzfck.schedule(runnable, j, TimeUnit.MILLISECONDS);
    }

    @VisibleForTesting
    private final synchronized void zzaei() {
        if (!this.zzfco) {
            if (this.zzfcl != null && !this.zzfcl.isDone()) {
                this.zzfcl.cancel(true);
                this.zzfcn = this.zzfcm - this.zzbmd.elapsedRealtime();
            } else {
                this.zzfcn = -1L;
            }
            this.zzfco = true;
        }
    }

    @VisibleForTesting
    private final synchronized void zzaej() {
        if (this.zzfco) {
            if (this.zzfcn > 0 && this.zzfcl != null && this.zzfcl.isCancelled()) {
                this.zzfcl = this.zzfck.schedule(this.zzdrh, this.zzfcn, TimeUnit.MILLISECONDS);
            }
            this.zzfco = false;
        }
    }
}
