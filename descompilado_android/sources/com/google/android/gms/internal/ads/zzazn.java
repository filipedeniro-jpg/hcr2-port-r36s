package com.google.android.gms.internal.ads;

import com.google.ads.mediation.unity.BuildConfig;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzazn implements zzgu {
    private int zzbem;
    private final zzng zzeca;
    private long zzecb;
    private long zzecc;
    private long zzecd;
    private long zzece;
    private boolean zzecf;

    zzazn() {
        this(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS, BuildConfig.VERSION_CODE, 2500L, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    private zzazn(int i, int i2, long j, long j2) {
        this.zzeca = new zzng(true, 65536);
        this.zzecb = 15000000L;
        this.zzecc = 30000000L;
        this.zzecd = 2500000L;
        this.zzece = 5000000L;
    }

    @Override // com.google.android.gms.internal.ads.zzgu
    public final void zzei() {
        zzj(false);
    }

    @Override // com.google.android.gms.internal.ads.zzgu
    public final void zza(zzgv[] zzgvVarArr, zzmi zzmiVar, zzmx zzmxVar) {
        this.zzbem = 0;
        for (int i = 0; i < zzgvVarArr.length; i++) {
            if (zzmxVar.zzax(i) != null) {
                this.zzbem += zzoh.zzbk(zzgvVarArr[i].getTrackType());
            }
        }
        this.zzeca.zzaz(this.zzbem);
    }

    @Override // com.google.android.gms.internal.ads.zzgu
    public final void onStopped() {
        zzj(true);
    }

    @Override // com.google.android.gms.internal.ads.zzgu
    public final void zzej() {
        zzj(true);
    }

    @Override // com.google.android.gms.internal.ads.zzgu
    public final zzna zzek() {
        return this.zzeca;
    }

    @Override // com.google.android.gms.internal.ads.zzgu
    public final synchronized boolean zzc(long j, boolean z) {
        long j2;
        try {
            j2 = z ? this.zzece : this.zzecd;
        } catch (Throwable th) {
            throw th;
        }
        return j2 <= 0 || j >= j2;
    }

    @Override // com.google.android.gms.internal.ads.zzgu
    public final synchronized boolean zzdn(long j) {
        char c;
        boolean z = false;
        if (j > this.zzecc) {
            c = 0;
        } else {
            c = j < this.zzecb ? (char) 2 : (char) 1;
        }
        boolean z2 = this.zzeca.zzhz() >= this.zzbem;
        if (c == 2 || (c == 1 && this.zzecf && !z2)) {
            z = true;
        }
        this.zzecf = z;
        return this.zzecf;
    }

    public final synchronized void zzda(int i) {
        this.zzecb = ((long) i) * 1000;
    }

    public final synchronized void zzdb(int i) {
        this.zzecc = ((long) i) * 1000;
    }

    public final synchronized void zzcv(int i) {
        this.zzecd = ((long) i) * 1000;
    }

    public final synchronized void zzcw(int i) {
        this.zzece = ((long) i) * 1000;
    }

    @VisibleForTesting
    private final void zzj(boolean z) {
        this.zzbem = 0;
        this.zzecf = false;
        if (z) {
            this.zzeca.reset();
        }
    }
}
