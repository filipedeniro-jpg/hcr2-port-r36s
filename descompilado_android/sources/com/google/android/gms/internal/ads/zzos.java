package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.WindowManager;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(16)
public final class zzos {
    private final zzov zzbim;
    private final boolean zzbin;
    private final long zzbio;
    private final long zzbip;
    private long zzbiq;
    private long zzbir;
    private long zzbis;
    private boolean zzbit;
    private long zzbiu;
    private long zzbiv;
    private long zzbiw;

    public zzos() {
        this(-1.0d);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public zzos(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this(windowManager.getDefaultDisplay() != null ? windowManager.getDefaultDisplay().getRefreshRate() : -1.0d);
    }

    private zzos(double d) {
        this.zzbin = d != -1.0d;
        if (this.zzbin) {
            this.zzbim = zzov.zzix();
            this.zzbio = (long) (1.0E9d / d);
            this.zzbip = (this.zzbio * 80) / 100;
        } else {
            this.zzbim = null;
            this.zzbio = -1L;
            this.zzbip = -1L;
        }
    }

    public final void enable() {
        this.zzbit = false;
        if (this.zzbin) {
            this.zzbim.zziy();
        }
    }

    public final void disable() {
        if (this.zzbin) {
            this.zzbim.zziz();
        }
    }

    public final long zzf(long j, long j2) {
        long j3;
        long j4;
        long j5;
        long j6 = 1000 * j;
        if (this.zzbit) {
            if (j != this.zzbiq) {
                this.zzbiw++;
                this.zzbir = this.zzbis;
            }
            long j7 = this.zzbiw;
            if (j7 >= 6) {
                j4 = this.zzbir + ((j6 - this.zzbiv) / j7);
                if (zzg(j4, j2)) {
                    this.zzbit = false;
                } else {
                    j3 = (this.zzbiu + j4) - this.zzbiv;
                }
            } else if (zzg(j6, j2)) {
                this.zzbit = false;
            }
            j3 = j2;
            j4 = j6;
        } else {
            j3 = j2;
            j4 = j6;
        }
        if (!this.zzbit) {
            this.zzbiv = j6;
            this.zzbiu = j2;
            this.zzbiw = 0L;
            this.zzbit = true;
        }
        this.zzbiq = j;
        this.zzbis = j4;
        zzov zzovVar = this.zzbim;
        if (zzovVar == null || zzovVar.zzbiy == 0) {
            return j3;
        }
        long j8 = this.zzbim.zzbiy;
        long j9 = this.zzbio;
        long j10 = j8 + (((j3 - j8) / j9) * j9);
        if (j3 <= j10) {
            j5 = j10 - j9;
        } else {
            j10 = j9 + j10;
            j5 = j10;
        }
        if (j10 - j3 >= j3 - j5) {
            j10 = j5;
        }
        return j10 - this.zzbip;
    }

    private final boolean zzg(long j, long j2) {
        return Math.abs((j2 - this.zzbiu) - (j - this.zzbiv)) > 20000000;
    }
}
