package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.ironsource.sdk.constants.Constants;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgh implements zzge {
    private int repeatMode;
    private final zzgv[] zzacm;
    private final zzmw zzacn;
    private final zzmx zzaco;
    private final Handler zzacp;
    private final zzgj zzacq;
    private final CopyOnWriteArraySet<zzgd> zzacr;
    private final zzhb zzacs;
    private final zzhc zzact;
    private boolean zzacu;
    private boolean zzacv;
    private int zzacw;
    private int zzacx;
    private int zzacy;
    private boolean zzacz;
    private zzha zzada;
    private Object zzadb;
    private zzmi zzadc;
    private zzmx zzadd;
    private zzgw zzade;
    private zzgl zzadf;
    private int zzadg;
    private int zzadh;
    private long zzadi;

    @SuppressLint({"HandlerLeak"})
    public zzgh(zzgv[] zzgvVarArr, zzmw zzmwVar, zzgu zzguVar) {
        String str = zzoh.zzbgs;
        StringBuilder sb = new StringBuilder("Init ExoPlayerLib/2.4.2 [".length() + 1 + String.valueOf(str).length());
        sb.append("Init ExoPlayerLib/2.4.2 [");
        sb.append(str);
        sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        Log.i("ExoPlayerImpl", sb.toString());
        zznt.checkState(zzgvVarArr.length > 0);
        this.zzacm = (zzgv[]) zznt.checkNotNull(zzgvVarArr);
        this.zzacn = (zzmw) zznt.checkNotNull(zzmwVar);
        this.zzacv = false;
        this.repeatMode = 0;
        this.zzacw = 1;
        this.zzacr = new CopyOnWriteArraySet<>();
        this.zzaco = new zzmx(new zzmv[zzgvVarArr.length]);
        this.zzada = zzha.zzagd;
        this.zzacs = new zzhb();
        this.zzact = new zzhc();
        this.zzadc = zzmi.zzbda;
        this.zzadd = this.zzaco;
        this.zzade = zzgw.zzafx;
        this.zzacp = new zzgk(this, Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        this.zzadf = new zzgl(0, 0L);
        this.zzacq = new zzgj(zzgvVarArr, zzmwVar, zzguVar, this.zzacv, 0, this.zzacp, this.zzadf, this);
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final void zza(zzgd zzgdVar) {
        this.zzacr.add(zzgdVar);
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final void zzb(zzgd zzgdVar) {
        this.zzacr.remove(zzgdVar);
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final int getPlaybackState() {
        return this.zzacw;
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final void zza(zzls zzlsVar) {
        if (!this.zzada.isEmpty() || this.zzadb != null) {
            this.zzada = zzha.zzagd;
            this.zzadb = null;
            Iterator<zzgd> it = this.zzacr.iterator();
            while (it.hasNext()) {
                it.next().zza(this.zzada, this.zzadb);
            }
        }
        if (this.zzacu) {
            this.zzacu = false;
            this.zzadc = zzmi.zzbda;
            this.zzadd = this.zzaco;
            this.zzacn.zzd(null);
            Iterator<zzgd> it2 = this.zzacr.iterator();
            while (it2.hasNext()) {
                it2.next().zza(this.zzadc, this.zzadd);
            }
        }
        this.zzacy++;
        this.zzacq.zza(zzlsVar, true);
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final void zzf(boolean z) {
        if (this.zzacv != z) {
            this.zzacv = z;
            this.zzacq.zzf(z);
            Iterator<zzgd> it = this.zzacr.iterator();
            while (it.hasNext()) {
                it.next().zza(z, this.zzacw);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final boolean zzds() {
        return this.zzacv;
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final void seekTo(long j) {
        long jZzdj;
        int iZzdv = zzdv();
        if (iZzdv < 0 || (!this.zzada.isEmpty() && iZzdv >= this.zzada.zzem())) {
            throw new zzgr(this.zzada, iZzdv, j);
        }
        this.zzacx++;
        this.zzadg = iZzdv;
        if (!this.zzada.isEmpty()) {
            this.zzada.zza(iZzdv, this.zzacs, false);
            if (j == C.TIME_UNSET) {
                jZzdj = this.zzacs.zzagl;
            } else {
                jZzdj = zzgc.zzdj(j);
            }
            long j2 = this.zzacs.zzagn + jZzdj;
            long j3 = this.zzada.zza(0, this.zzact, false).zzagm;
            if (j3 != C.TIME_UNSET) {
                int i = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
            }
        }
        this.zzadh = 0;
        if (j == C.TIME_UNSET) {
            this.zzadi = 0L;
            this.zzacq.zza(this.zzada, iZzdv, C.TIME_UNSET);
            return;
        }
        this.zzadi = j;
        this.zzacq.zza(this.zzada, iZzdv, zzgc.zzdj(j));
        Iterator<zzgd> it = this.zzacr.iterator();
        while (it.hasNext()) {
            it.next().zzdr();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final void stop() {
        this.zzacq.stop();
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final void release() {
        this.zzacq.release();
        this.zzacp.removeCallbacksAndMessages(null);
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final void zza(zzgf... zzgfVarArr) {
        this.zzacq.zza(zzgfVarArr);
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final void zzb(zzgf... zzgfVarArr) {
        this.zzacq.zzb(zzgfVarArr);
    }

    private final int zzdv() {
        if (this.zzada.isEmpty() || this.zzacx > 0) {
            return this.zzadg;
        }
        this.zzada.zza(this.zzadf.zzaee, this.zzact, false);
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final long getDuration() {
        return this.zzada.isEmpty() ? C.TIME_UNSET : zzgc.zzdi(this.zzada.zza(zzdv(), this.zzacs, false).zzagm);
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final long zzdu() {
        if (this.zzada.isEmpty() || this.zzacx > 0) {
            return this.zzadi;
        }
        this.zzada.zza(this.zzadf.zzaee, this.zzact, false);
        return this.zzact.zzeo() + zzgc.zzdi(this.zzadf.zzaeg);
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final long getBufferedPosition() {
        if (this.zzada.isEmpty() || this.zzacx > 0) {
            return this.zzadi;
        }
        this.zzada.zza(this.zzadf.zzaee, this.zzact, false);
        return this.zzact.zzeo() + zzgc.zzdi(this.zzadf.zzaeh);
    }

    @Override // com.google.android.gms.internal.ads.zzge
    public final int zzdt() {
        return this.zzacm.length;
    }

    final void zza(Message message) {
        switch (message.what) {
            case 0:
                this.zzacy--;
                return;
            case 1:
                this.zzacw = message.arg1;
                Iterator<zzgd> it = this.zzacr.iterator();
                while (it.hasNext()) {
                    it.next().zza(this.zzacv, this.zzacw);
                }
                return;
            case 2:
                this.zzacz = message.arg1 != 0;
                Iterator<zzgd> it2 = this.zzacr.iterator();
                while (it2.hasNext()) {
                    it2.next().zze(this.zzacz);
                }
                return;
            case 3:
                if (this.zzacy == 0) {
                    zzmy zzmyVar = (zzmy) message.obj;
                    this.zzacu = true;
                    this.zzadc = zzmyVar.zzbec;
                    this.zzadd = zzmyVar.zzbed;
                    this.zzacn.zzd(zzmyVar.zzbee);
                    Iterator<zzgd> it3 = this.zzacr.iterator();
                    while (it3.hasNext()) {
                        it3.next().zza(this.zzadc, this.zzadd);
                    }
                    return;
                }
                return;
            case 4:
                int i = this.zzacx - 1;
                this.zzacx = i;
                if (i == 0) {
                    this.zzadf = (zzgl) message.obj;
                    if (message.arg1 != 0) {
                        Iterator<zzgd> it4 = this.zzacr.iterator();
                        while (it4.hasNext()) {
                            it4.next().zzdr();
                        }
                        return;
                    }
                    return;
                }
                return;
            case 5:
                if (this.zzacx == 0) {
                    this.zzadf = (zzgl) message.obj;
                    Iterator<zzgd> it5 = this.zzacr.iterator();
                    while (it5.hasNext()) {
                        it5.next().zzdr();
                    }
                    return;
                }
                return;
            case 6:
                zzgn zzgnVar = (zzgn) message.obj;
                this.zzacx -= zzgnVar.zzaet;
                if (this.zzacy == 0) {
                    this.zzada = zzgnVar.zzada;
                    this.zzadb = zzgnVar.zzadb;
                    this.zzadf = zzgnVar.zzadf;
                    Iterator<zzgd> it6 = this.zzacr.iterator();
                    while (it6.hasNext()) {
                        it6.next().zza(this.zzada, this.zzadb);
                    }
                    return;
                }
                return;
            case 7:
                zzgw zzgwVar = (zzgw) message.obj;
                if (this.zzade.equals(zzgwVar)) {
                    return;
                }
                this.zzade = zzgwVar;
                Iterator<zzgd> it7 = this.zzacr.iterator();
                while (it7.hasNext()) {
                    it7.next().zza(zzgwVar);
                }
                return;
            case 8:
                zzgb zzgbVar = (zzgb) message.obj;
                Iterator<zzgd> it8 = this.zzacr.iterator();
                while (it8.hasNext()) {
                    it8.next().zza(zzgbVar);
                }
                return;
            default:
                throw new IllegalStateException();
        }
    }
}
