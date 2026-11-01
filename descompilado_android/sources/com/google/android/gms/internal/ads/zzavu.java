package com.google.android.gms.internal.ads;

import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzavu {
    private long zzdum;

    @GuardedBy("lock")
    private long zzdun = Long.MIN_VALUE;
    private final Object lock = new Object();

    public zzavu(long j) {
        this.zzdum = j;
    }

    public final boolean tryAcquire() {
        synchronized (this.lock) {
            long jElapsedRealtime = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime();
            if (this.zzdun + this.zzdum > jElapsedRealtime) {
                return false;
            }
            this.zzdun = jElapsedRealtime;
            return true;
        }
    }

    public final void zzev(long j) {
        synchronized (this.lock) {
            this.zzdum = j;
        }
    }
}
