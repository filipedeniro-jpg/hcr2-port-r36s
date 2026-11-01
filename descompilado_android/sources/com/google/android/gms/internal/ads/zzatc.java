package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzatc {
    private final Object lock;
    private volatile int zzdqq;
    private volatile long zzdqr;

    private zzatc() {
        this.lock = new Object();
        this.zzdqq = zzatf.zzdrc;
        this.zzdqr = 0L;
    }

    public final void zzuf() {
        long jCurrentTimeMillis = com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis();
        synchronized (this.lock) {
            if (this.zzdqq == zzatf.zzdre) {
                if (this.zzdqr + ((Long) zzuo.zzoj().zzd(zzyt.zzcsc)).longValue() <= jCurrentTimeMillis) {
                    this.zzdqq = zzatf.zzdrc;
                }
            }
        }
        long jCurrentTimeMillis2 = com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis();
        synchronized (this.lock) {
            if (this.zzdqq != 2) {
                return;
            }
            this.zzdqq = 3;
            if (this.zzdqq == zzatf.zzdre) {
                this.zzdqr = jCurrentTimeMillis2;
            }
        }
    }

    /* synthetic */ zzatc(zzatd zzatdVar) {
        this();
    }
}
