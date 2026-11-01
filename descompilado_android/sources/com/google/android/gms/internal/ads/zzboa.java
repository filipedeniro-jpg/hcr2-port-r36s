package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Clock;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzboa extends zzbos<zzboe> {
    private final Clock zzbmd;

    @GuardedBy("this")
    private boolean zzfbw;
    private final ScheduledExecutorService zzfck;

    @GuardedBy("this")
    private long zzfcm;

    @GuardedBy("this")
    private long zzfcn;

    @Nullable
    @GuardedBy("this")
    private ScheduledFuture<?> zzfgr;

    public zzboa(ScheduledExecutorService scheduledExecutorService, Clock clock) {
        super(Collections.emptySet());
        this.zzfcm = -1L;
        this.zzfcn = -1L;
        this.zzfbw = false;
        this.zzfck = scheduledExecutorService;
        this.zzbmd = clock;
    }

    public final synchronized void onPause() {
        if (!this.zzfbw) {
            if (this.zzfgr != null && !this.zzfgr.isCancelled()) {
                this.zzfgr.cancel(true);
                this.zzfcn = this.zzfcm - this.zzbmd.elapsedRealtime();
            } else {
                this.zzfcn = -1L;
            }
            this.zzfbw = true;
        }
    }

    public final synchronized void onResume() {
        if (this.zzfbw) {
            if (this.zzfcn > 0 && this.zzfgr.isCancelled()) {
                zzex(this.zzfcn);
            }
            this.zzfbw = false;
        }
    }

    public final synchronized void zzafw() {
        this.zzfbw = false;
        zzex(0L);
    }

    public final synchronized void zzde(int i) {
        if (i <= 0) {
            return;
        }
        long millis = TimeUnit.SECONDS.toMillis(i);
        if (!this.zzfbw) {
            if (this.zzbmd.elapsedRealtime() > this.zzfcm || this.zzfcm - this.zzbmd.elapsedRealtime() > millis) {
                zzex(millis);
            }
        } else {
            if (this.zzfcn <= 0 || millis >= this.zzfcn) {
                millis = this.zzfcn;
            }
            this.zzfcn = millis;
        }
    }

    private final synchronized void zzex(long j) {
        if (this.zzfgr != null && !this.zzfgr.isDone()) {
            this.zzfgr.cancel(true);
        }
        this.zzfcm = this.zzbmd.elapsedRealtime() + j;
        this.zzfgr = this.zzfck.schedule(new zzbof(this), j, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzafx() {
        zza(zzbod.zzfgm);
    }
}
