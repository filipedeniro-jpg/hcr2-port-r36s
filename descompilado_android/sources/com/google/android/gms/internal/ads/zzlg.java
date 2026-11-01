package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzlg implements zziw, zzlq, zzmc, zznm<zzln> {
    private final Uri uri;
    private final Handler zzacp;
    private boolean zzads;
    private boolean zzaeo;
    private long zzagm;
    private final zznc zzamq;
    private final int zzazp;
    private final zzlr zzazq;
    private final zzlv zzazr;
    private final zzna zzazs;
    private final String zzazt;
    private final long zzazu;
    private final zzlm zzazw;
    private zzlt zzbab;
    private zzjd zzbac;
    private boolean zzbad;
    private boolean zzbae;
    private boolean zzbaf;
    private int zzbag;
    private zzmi zzbah;
    private boolean[] zzbai;
    private boolean[] zzbaj;
    private boolean zzbak;
    private long zzbal;
    private int zzban;
    private boolean zzbao;
    private final zznn zzazv = new zznn("Loader:ExtractorMediaPeriod");
    private final zznv zzazx = new zznv();
    private final Runnable zzazy = new zzlj(this);
    private final Runnable zzazz = new zzli(this);
    private final Handler handler = new Handler();
    private long zzbam = C.TIME_UNSET;
    private final SparseArray<zzma> zzbaa = new SparseArray<>();
    private long zzcb = -1;

    public zzlg(Uri uri, zznc zzncVar, zziu[] zziuVarArr, int i, Handler handler, zzlr zzlrVar, zzlv zzlvVar, zzna zznaVar, String str, int i2) {
        this.uri = uri;
        this.zzamq = zzncVar;
        this.zzazp = i;
        this.zzacp = handler;
        this.zzazq = zzlrVar;
        this.zzazr = zzlvVar;
        this.zzazs = zznaVar;
        this.zzazt = str;
        this.zzazu = i2;
        this.zzazw = new zzlm(zziuVarArr, this);
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final void zzdy(long j) {
    }

    public final void release() {
        this.zzazv.zza(new zzll(this, this.zzazw));
        this.handler.removeCallbacksAndMessages(null);
        this.zzads = true;
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final void zza(zzlt zzltVar, long j) {
        this.zzbab = zzltVar;
        this.zzazx.open();
        startLoading();
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final void zzgw() throws IOException {
        this.zzazv.zzbb(Integer.MIN_VALUE);
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final zzmi zzgx() {
        return this.zzbah;
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final long zza(zzmv[] zzmvVarArr, boolean[] zArr, zzmf[] zzmfVarArr, boolean[] zArr2, long j) {
        zznt.checkState(this.zzaeo);
        for (int i = 0; i < zzmvVarArr.length; i++) {
            if (zzmfVarArr[i] != null && (zzmvVarArr[i] == null || !zArr[i])) {
                int i2 = ((zzlp) zzmfVarArr[i]).track;
                zznt.checkState(this.zzbai[i2]);
                this.zzbag--;
                this.zzbai[i2] = false;
                this.zzbaa.valueAt(i2).disable();
                zzmfVarArr[i] = null;
            }
        }
        boolean z = false;
        for (int i3 = 0; i3 < zzmvVarArr.length; i3++) {
            if (zzmfVarArr[i3] == null && zzmvVarArr[i3] != null) {
                zzmv zzmvVar = zzmvVarArr[i3];
                zznt.checkState(zzmvVar.length() == 1);
                zznt.checkState(zzmvVar.zzaw(0) == 0);
                int iZza = this.zzbah.zza(zzmvVar.zzhu());
                zznt.checkState(!this.zzbai[iZza]);
                this.zzbag++;
                this.zzbai[iZza] = true;
                zzmfVarArr[i3] = new zzlp(this, iZza);
                zArr2[i3] = true;
                z = true;
            }
        }
        if (!this.zzbae) {
            int size = this.zzbaa.size();
            for (int i4 = 0; i4 < size; i4++) {
                if (!this.zzbai[i4]) {
                    this.zzbaa.valueAt(i4).disable();
                }
            }
        }
        if (this.zzbag == 0) {
            this.zzbaf = false;
            if (this.zzazv.isLoading()) {
                this.zzazv.zzic();
            }
        } else if (!this.zzbae ? j != 0 : z) {
            j = zzea(j);
            for (int i5 = 0; i5 < zzmfVarArr.length; i5++) {
                if (zzmfVarArr[i5] != null) {
                    zArr2[i5] = true;
                }
            }
        }
        this.zzbae = true;
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzlq, com.google.android.gms.internal.ads.zzme
    public final boolean zzdz(long j) {
        if (this.zzbao) {
            return false;
        }
        if (this.zzaeo && this.zzbag == 0) {
            return false;
        }
        boolean zOpen = this.zzazx.open();
        if (this.zzazv.isLoading()) {
            return zOpen;
        }
        startLoading();
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzlq, com.google.android.gms.internal.ads.zzme
    public final long zzgy() {
        if (this.zzbag == 0) {
            return Long.MIN_VALUE;
        }
        return zzha();
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final long zzgz() {
        if (!this.zzbaf) {
            return C.TIME_UNSET;
        }
        this.zzbaf = false;
        return this.zzbal;
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final long zzha() {
        long jZzhe;
        if (this.zzbao) {
            return Long.MIN_VALUE;
        }
        if (zzhf()) {
            return this.zzbam;
        }
        if (this.zzbak) {
            jZzhe = Long.MAX_VALUE;
            int size = this.zzbaa.size();
            for (int i = 0; i < size; i++) {
                if (this.zzbaj[i]) {
                    jZzhe = Math.min(jZzhe, this.zzbaa.valueAt(i).zzhe());
                }
            }
        } else {
            jZzhe = zzhe();
        }
        return jZzhe == Long.MIN_VALUE ? this.zzbal : jZzhe;
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final long zzea(long j) {
        if (!this.zzbac.zzfy()) {
            j = 0;
        }
        this.zzbal = j;
        int size = this.zzbaa.size();
        boolean zZze = !zzhf();
        for (int i = 0; zZze && i < size; i++) {
            if (this.zzbai[i]) {
                zZze = this.zzbaa.valueAt(i).zze(j, false);
            }
        }
        if (!zZze) {
            this.zzbam = j;
            this.zzbao = false;
            if (this.zzazv.isLoading()) {
                this.zzazv.zzic();
            } else {
                for (int i2 = 0; i2 < size; i2++) {
                    this.zzbaa.valueAt(i2).zzj(this.zzbai[i2]);
                }
            }
        }
        this.zzbaf = false;
        return j;
    }

    final boolean zzas(int i) {
        if (this.zzbao) {
            return true;
        }
        return !zzhf() && this.zzbaa.valueAt(i).zzhn();
    }

    final void zzhb() throws IOException {
        this.zzazv.zzbb(Integer.MIN_VALUE);
    }

    final int zza(int i, zzgs zzgsVar, zzim zzimVar, boolean z) {
        if (this.zzbaf || zzhf()) {
            return -3;
        }
        return this.zzbaa.valueAt(i).zza(zzgsVar, zzimVar, z, this.zzbao, this.zzbal);
    }

    final void zzd(int i, long j) {
        zzma zzmaVarValueAt = this.zzbaa.valueAt(i);
        if (this.zzbao && j > zzmaVarValueAt.zzhe()) {
            zzmaVarValueAt.zzhq();
        } else {
            zzmaVarValueAt.zze(j, true);
        }
    }

    @Override // com.google.android.gms.internal.ads.zziw
    public final zzjf zzb(int i, int i2) {
        zzma zzmaVar = this.zzbaa.get(i);
        if (zzmaVar != null) {
            return zzmaVar;
        }
        zzma zzmaVar2 = new zzma(this.zzazs);
        zzmaVar2.zza(this);
        this.zzbaa.put(i, zzmaVar2);
        return zzmaVar2;
    }

    @Override // com.google.android.gms.internal.ads.zziw
    public final void zzga() {
        this.zzbad = true;
        this.handler.post(this.zzazy);
    }

    @Override // com.google.android.gms.internal.ads.zziw
    public final void zza(zzjd zzjdVar) {
        this.zzbac = zzjdVar;
        this.handler.post(this.zzazy);
    }

    @Override // com.google.android.gms.internal.ads.zzmc
    public final void zzf(zzgq zzgqVar) {
        this.handler.post(this.zzazy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzhc() {
        if (this.zzads || this.zzaeo || this.zzbac == null || !this.zzbad) {
            return;
        }
        int size = this.zzbaa.size();
        for (int i = 0; i < size; i++) {
            if (this.zzbaa.valueAt(i).zzho() == null) {
                return;
            }
        }
        this.zzazx.zzid();
        zzmj[] zzmjVarArr = new zzmj[size];
        this.zzbaj = new boolean[size];
        this.zzbai = new boolean[size];
        this.zzagm = this.zzbac.getDurationUs();
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 < size) {
                zzgq zzgqVarZzho = this.zzbaa.valueAt(i2).zzho();
                zzmjVarArr[i2] = new zzmj(zzgqVarZzho);
                String str = zzgqVarZzho.zzafb;
                if (!zznw.zzbd(str) && !zznw.zzbc(str)) {
                    z = false;
                }
                this.zzbaj[i2] = z;
                this.zzbak = z | this.zzbak;
                i2++;
            } else {
                this.zzbah = new zzmi(zzmjVarArr);
                this.zzaeo = true;
                this.zzazr.zzb(new zzmg(this.zzagm, this.zzbac.zzfy()), null);
                this.zzbab.zza((zzlq) this);
                return;
            }
        }
    }

    private final void zza(zzln zzlnVar) {
        if (this.zzcb == -1) {
            this.zzcb = zzlnVar.zzcb;
        }
    }

    private final void startLoading() {
        zzjd zzjdVar;
        zzln zzlnVar = new zzln(this, this.uri, this.zzamq, this.zzazw, this.zzazx);
        if (this.zzaeo) {
            zznt.checkState(zzhf());
            long j = this.zzagm;
            if (j != C.TIME_UNSET && this.zzbam >= j) {
                this.zzbao = true;
                this.zzbam = C.TIME_UNSET;
                return;
            } else {
                zzlnVar.zze(this.zzbac.zzdt(this.zzbam), this.zzbam);
                this.zzbam = C.TIME_UNSET;
            }
        }
        this.zzban = zzhd();
        int i = this.zzazp;
        if (i == -1) {
            i = (this.zzaeo && this.zzcb == -1 && ((zzjdVar = this.zzbac) == null || zzjdVar.getDurationUs() == C.TIME_UNSET)) ? 6 : 3;
        }
        this.zzazv.zza(zzlnVar, this, i);
    }

    private final int zzhd() {
        int size = this.zzbaa.size();
        int iZzhm = 0;
        for (int i = 0; i < size; i++) {
            iZzhm += this.zzbaa.valueAt(i).zzhm();
        }
        return iZzhm;
    }

    private final long zzhe() {
        int size = this.zzbaa.size();
        long jMax = Long.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            jMax = Math.max(jMax, this.zzbaa.valueAt(i).zzhe());
        }
        return jMax;
    }

    private final boolean zzhf() {
        return this.zzbam != C.TIME_UNSET;
    }

    @Override // com.google.android.gms.internal.ads.zznm
    public final /* synthetic */ int zza(zzno zznoVar, long j, long j2, IOException iOException) {
        zzjd zzjdVar;
        zzln zzlnVar = (zzln) zznoVar;
        zza(zzlnVar);
        Handler handler = this.zzacp;
        if (handler != null && this.zzazq != null) {
            handler.post(new zzlk(this, iOException));
        }
        if (iOException instanceof zzml) {
            return 3;
        }
        boolean z = zzhd() > this.zzban;
        if (this.zzcb == -1 && ((zzjdVar = this.zzbac) == null || zzjdVar.getDurationUs() == C.TIME_UNSET)) {
            this.zzbal = 0L;
            this.zzbaf = this.zzaeo;
            int size = this.zzbaa.size();
            for (int i = 0; i < size; i++) {
                this.zzbaa.valueAt(i).zzj(!this.zzaeo || this.zzbai[i]);
            }
            zzlnVar.zze(0L, 0L);
        }
        this.zzban = zzhd();
        return z ? 1 : 0;
    }

    @Override // com.google.android.gms.internal.ads.zznm
    public final /* synthetic */ void zza(zzno zznoVar, long j, long j2, boolean z) {
        zza((zzln) zznoVar);
        if (z || this.zzbag <= 0) {
            return;
        }
        int size = this.zzbaa.size();
        for (int i = 0; i < size; i++) {
            this.zzbaa.valueAt(i).zzj(this.zzbai[i]);
        }
        this.zzbab.zza(this);
    }

    @Override // com.google.android.gms.internal.ads.zznm
    public final /* synthetic */ void zza(zzno zznoVar, long j, long j2) {
        zza((zzln) zznoVar);
        this.zzbao = true;
        if (this.zzagm == C.TIME_UNSET) {
            long jZzhe = zzhe();
            this.zzagm = jZzhe == Long.MIN_VALUE ? 0L : jZzhe + 10000;
            this.zzazr.zzb(new zzmg(this.zzagm, this.zzbac.zzfy()), null);
        }
        this.zzbab.zza(this);
    }
}
