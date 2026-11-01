package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzly {
    private int length;
    private int[] zzaml;
    private long[] zzamm;
    private long[] zzamo;
    private int[] zzawm;
    private int zzbbr = 1000;
    private int[] zzbbs;
    private zzje[] zzbbt;
    private zzgq[] zzbbu;
    private int zzbbv;
    private int zzbbw;
    private int zzbbx;
    private long zzbby;
    private long zzbbz;
    private boolean zzbca;
    private boolean zzbcb;
    private zzgq zzbcc;

    public zzly() {
        int i = this.zzbbr;
        this.zzbbs = new int[i];
        this.zzamm = new long[i];
        this.zzamo = new long[i];
        this.zzawm = new int[i];
        this.zzaml = new int[i];
        this.zzbbt = new zzje[i];
        this.zzbbu = new zzgq[i];
        this.zzbby = Long.MIN_VALUE;
        this.zzbbz = Long.MIN_VALUE;
        this.zzbcb = true;
        this.zzbca = true;
    }

    public final void zzhk() {
        this.zzbbv = 0;
        this.zzbbw = 0;
        this.zzbbx = 0;
        this.length = 0;
        this.zzbca = true;
    }

    public final void zzhl() {
        this.zzbby = Long.MIN_VALUE;
        this.zzbbz = Long.MIN_VALUE;
    }

    public final int zzhm() {
        return this.zzbbv + this.length;
    }

    public final synchronized boolean zzhn() {
        return this.length != 0;
    }

    public final synchronized zzgq zzho() {
        if (this.zzbcb) {
            return null;
        }
        return this.zzbcc;
    }

    public final synchronized long zzhe() {
        return Math.max(this.zzbby, this.zzbbz);
    }

    public final synchronized int zza(zzgs zzgsVar, zzim zzimVar, boolean z, boolean z2, zzgq zzgqVar, zzmb zzmbVar) {
        if (!zzhn()) {
            if (z2) {
                zzimVar.setFlags(4);
                return -4;
            }
            if (this.zzbcc == null || (!z && this.zzbcc == zzgqVar)) {
                return -3;
            }
            zzgsVar.zzafw = this.zzbcc;
            return -5;
        }
        if (!z && this.zzbbu[this.zzbbw] == zzgqVar) {
            if (zzimVar.zzcq == null) {
                return -3;
            }
            zzimVar.zzami = this.zzamo[this.zzbbw];
            zzimVar.setFlags(this.zzawm[this.zzbbw]);
            zzmbVar.size = this.zzaml[this.zzbbw];
            zzmbVar.zzaum = this.zzamm[this.zzbbw];
            zzmbVar.zzapp = this.zzbbt[this.zzbbw];
            this.zzbby = Math.max(this.zzbby, zzimVar.zzami);
            this.length--;
            this.zzbbw++;
            this.zzbbv++;
            if (this.zzbbw == this.zzbbr) {
                this.zzbbw = 0;
            }
            zzmbVar.zzbcq = this.length > 0 ? this.zzamm[this.zzbbw] : zzmbVar.zzaum + ((long) zzmbVar.size);
            return -4;
        }
        zzgsVar.zzafw = this.zzbbu[this.zzbbw];
        return -5;
    }

    public final synchronized long zzhp() {
        if (!zzhn()) {
            return -1L;
        }
        int i = ((this.zzbbw + this.length) - 1) % this.zzbbr;
        this.zzbbw = (this.zzbbw + this.length) % this.zzbbr;
        this.zzbbv += this.length;
        this.length = 0;
        return this.zzamm[i] + ((long) this.zzaml[i]);
    }

    public final synchronized long zzd(long j, boolean z) {
        if (zzhn() && j >= this.zzamo[this.zzbbw]) {
            if (j > this.zzbbz && !z) {
                return -1L;
            }
            int i = this.zzbbw;
            int i2 = -1;
            int i3 = 0;
            while (i != this.zzbbx && this.zzamo[i] <= j) {
                if ((this.zzawm[i] & 1) != 0) {
                    i2 = i3;
                }
                i = (i + 1) % this.zzbbr;
                i3++;
            }
            if (i2 == -1) {
                return -1L;
            }
            this.zzbbw = (this.zzbbw + i2) % this.zzbbr;
            this.zzbbv += i2;
            this.length -= i2;
            return this.zzamm[this.zzbbw];
        }
        return -1L;
    }

    public final synchronized boolean zzg(zzgq zzgqVar) {
        try {
            if (zzgqVar == null) {
                this.zzbcb = true;
                return false;
            }
            this.zzbcb = false;
            if (zzoh.zza(zzgqVar, this.zzbcc)) {
                return false;
            }
            this.zzbcc = zzgqVar;
            return true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void zza(long j, int i, long j2, int i2, zzje zzjeVar) {
        if (this.zzbca) {
            if ((i & 1) == 0) {
                return;
            } else {
                this.zzbca = false;
            }
        }
        zznt.checkState(!this.zzbcb);
        zzec(j);
        this.zzamo[this.zzbbx] = j;
        this.zzamm[this.zzbbx] = j2;
        this.zzaml[this.zzbbx] = i2;
        this.zzawm[this.zzbbx] = i;
        this.zzbbt[this.zzbbx] = zzjeVar;
        this.zzbbu[this.zzbbx] = this.zzbcc;
        this.zzbbs[this.zzbbx] = 0;
        this.length++;
        if (this.length == this.zzbbr) {
            int i3 = this.zzbbr + 1000;
            int[] iArr = new int[i3];
            long[] jArr = new long[i3];
            long[] jArr2 = new long[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            zzje[] zzjeVarArr = new zzje[i3];
            zzgq[] zzgqVarArr = new zzgq[i3];
            int i4 = this.zzbbr - this.zzbbw;
            System.arraycopy(this.zzamm, this.zzbbw, jArr, 0, i4);
            System.arraycopy(this.zzamo, this.zzbbw, jArr2, 0, i4);
            System.arraycopy(this.zzawm, this.zzbbw, iArr2, 0, i4);
            System.arraycopy(this.zzaml, this.zzbbw, iArr3, 0, i4);
            System.arraycopy(this.zzbbt, this.zzbbw, zzjeVarArr, 0, i4);
            System.arraycopy(this.zzbbu, this.zzbbw, zzgqVarArr, 0, i4);
            System.arraycopy(this.zzbbs, this.zzbbw, iArr, 0, i4);
            int i5 = this.zzbbw;
            System.arraycopy(this.zzamm, 0, jArr, i4, i5);
            System.arraycopy(this.zzamo, 0, jArr2, i4, i5);
            System.arraycopy(this.zzawm, 0, iArr2, i4, i5);
            System.arraycopy(this.zzaml, 0, iArr3, i4, i5);
            System.arraycopy(this.zzbbt, 0, zzjeVarArr, i4, i5);
            System.arraycopy(this.zzbbu, 0, zzgqVarArr, i4, i5);
            System.arraycopy(this.zzbbs, 0, iArr, i4, i5);
            this.zzamm = jArr;
            this.zzamo = jArr2;
            this.zzawm = iArr2;
            this.zzaml = iArr3;
            this.zzbbt = zzjeVarArr;
            this.zzbbu = zzgqVarArr;
            this.zzbbs = iArr;
            this.zzbbw = 0;
            this.zzbbx = this.zzbbr;
            this.length = this.zzbbr;
            this.zzbbr = i3;
            return;
        }
        this.zzbbx++;
        if (this.zzbbx == this.zzbbr) {
            this.zzbbx = 0;
        }
    }

    public final synchronized void zzec(long j) {
        this.zzbbz = Math.max(this.zzbbz, j);
    }
}
