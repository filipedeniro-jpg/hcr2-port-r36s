package com.google.android.gms.internal.ads;

import android.os.SystemClock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzof implements zznx {
    private boolean started;
    private zzgw zzade = zzgw.zzafx;
    private long zzbgp;
    private long zzbgq;

    public final void start() {
        if (this.started) {
            return;
        }
        this.zzbgq = SystemClock.elapsedRealtime();
        this.started = true;
    }

    public final void stop() {
        if (this.started) {
            zzef(zzfj());
            this.started = false;
        }
    }

    public final void zzef(long j) {
        this.zzbgp = j;
        if (this.started) {
            this.zzbgq = SystemClock.elapsedRealtime();
        }
    }

    public final void zza(zznx zznxVar) {
        zzef(zznxVar.zzfj());
        this.zzade = zznxVar.zzez();
    }

    @Override // com.google.android.gms.internal.ads.zznx
    public final long zzfj() {
        long jZzdo;
        long j = this.zzbgp;
        if (!this.started) {
            return j;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.zzbgq;
        if (this.zzade.zzafy == 1.0f) {
            jZzdo = zzgc.zzdj(jElapsedRealtime);
        } else {
            jZzdo = this.zzade.zzdo(jElapsedRealtime);
        }
        return j + jZzdo;
    }

    @Override // com.google.android.gms.internal.ads.zznx
    public final zzgw zzb(zzgw zzgwVar) {
        if (this.started) {
            zzef(zzfj());
        }
        this.zzade = zzgwVar;
        return zzgwVar;
    }

    @Override // com.google.android.gms.internal.ads.zznx
    public final zzgw zzez() {
        return this.zzade;
    }
}
