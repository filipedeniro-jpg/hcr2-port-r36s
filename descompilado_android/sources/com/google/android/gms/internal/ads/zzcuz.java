package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcuz {
    private final Clock zzbmd;
    private final Object lock = new Object();

    @GuardedBy("lock")
    private volatile int zzgio = zzcuy.zzgik;
    private volatile long zzdqr = 0;

    public zzcuz(Clock clock) {
        this.zzbmd = clock;
    }

    private final void zzq(int i, int i2) {
        zzams();
        long jCurrentTimeMillis = this.zzbmd.currentTimeMillis();
        synchronized (this.lock) {
            if (this.zzgio != i) {
                return;
            }
            this.zzgio = i2;
            if (this.zzgio == zzcuy.zzgim) {
                this.zzdqr = jCurrentTimeMillis;
            }
        }
    }

    private final void zzams() {
        long jCurrentTimeMillis = this.zzbmd.currentTimeMillis();
        synchronized (this.lock) {
            if (this.zzgio == zzcuy.zzgim) {
                if (this.zzdqr + ((Long) zzuo.zzoj().zzd(zzyt.zzcsc)).longValue() <= jCurrentTimeMillis) {
                    this.zzgio = zzcuy.zzgik;
                }
            }
        }
    }

    public final void zzbd(boolean z) {
        if (z) {
            zzq(zzcuy.zzgik, zzcuy.zzgil);
        } else {
            zzq(zzcuy.zzgil, zzcuy.zzgik);
        }
    }

    public final boolean zzamt() {
        boolean z;
        synchronized (this.lock) {
            zzams();
            z = this.zzgio == zzcuy.zzgil;
        }
        return z;
    }

    public final boolean zzamu() {
        boolean z;
        synchronized (this.lock) {
            zzams();
            z = this.zzgio == zzcuy.zzgim;
        }
        return z;
    }

    public final void zzuf() {
        zzq(zzcuy.zzgil, zzcuy.zzgim);
    }
}
