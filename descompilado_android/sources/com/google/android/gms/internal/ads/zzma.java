package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzma implements zzjf {
    private final zzna zzazs;
    private final int zzbce;
    private zzmd zzbci;
    private zzmd zzbcj;
    private zzgq zzbck;
    private boolean zzbcl;
    private zzgq zzbcm;
    private long zzbcn;
    private int zzbco;
    private zzmc zzbcp;
    private final zzly zzbcf = new zzly();
    private final zzmb zzbcg = new zzmb();
    private final zzoa zzanr = new zzoa(32);
    private final AtomicInteger zzbch = new AtomicInteger();

    public zzma(zzna zznaVar) {
        this.zzazs = zznaVar;
        this.zzbce = zznaVar.zzhx();
        int i = this.zzbce;
        this.zzbco = i;
        this.zzbci = new zzmd(0L, i);
        this.zzbcj = this.zzbci;
    }

    public final void zzj(boolean z) {
        int andSet = this.zzbch.getAndSet(z ? 0 : 2);
        zzhk();
        this.zzbcf.zzhl();
        if (andSet == 2) {
            this.zzbck = null;
        }
    }

    public final int zzhm() {
        return this.zzbcf.zzhm();
    }

    public final void disable() {
        if (this.zzbch.getAndSet(2) == 0) {
            zzhk();
        }
    }

    public final boolean zzhn() {
        return this.zzbcf.zzhn();
    }

    public final zzgq zzho() {
        return this.zzbcf.zzho();
    }

    public final long zzhe() {
        return this.zzbcf.zzhe();
    }

    public final void zzhq() {
        long jZzhp = this.zzbcf.zzhp();
        if (jZzhp != -1) {
            zzed(jZzhp);
        }
    }

    public final boolean zze(long j, boolean z) {
        long jZzd = this.zzbcf.zzd(j, z);
        if (jZzd == -1) {
            return false;
        }
        zzed(jZzd);
        return true;
    }

    public final int zza(zzgs zzgsVar, zzim zzimVar, boolean z, boolean z2, long j) {
        int unsignedShort;
        int iZza = this.zzbcf.zza(zzgsVar, zzimVar, z, z2, this.zzbck, this.zzbcg);
        if (iZza == -5) {
            this.zzbck = zzgsVar.zzafw;
            return -5;
        }
        if (iZza != -4) {
            if (iZza == -3) {
                return -3;
            }
            throw new IllegalStateException();
        }
        if (!zzimVar.zzfp()) {
            if (zzimVar.zzami < j) {
                zzimVar.zzv(Integer.MIN_VALUE);
            }
            if (zzimVar.zzfv()) {
                zzmb zzmbVar = this.zzbcg;
                long j2 = zzmbVar.zzaum;
                this.zzanr.reset(1);
                zza(j2, this.zzanr.data, 1);
                long j3 = j2 + 1;
                byte b = this.zzanr.data[0];
                boolean z3 = (b & 128) != 0;
                int i = b & 127;
                if (zzimVar.zzamh.iv == null) {
                    zzimVar.zzamh.iv = new byte[16];
                }
                zza(j3, zzimVar.zzamh.iv, i);
                long j4 = j3 + ((long) i);
                if (z3) {
                    this.zzanr.reset(2);
                    zza(j4, this.zzanr.data, 2);
                    j4 += 2;
                    unsignedShort = this.zzanr.readUnsignedShort();
                } else {
                    unsignedShort = 1;
                }
                int[] iArr = zzimVar.zzamh.numBytesOfClearData;
                if (iArr == null || iArr.length < unsignedShort) {
                    iArr = new int[unsignedShort];
                }
                int[] iArr2 = iArr;
                int[] iArr3 = zzimVar.zzamh.numBytesOfEncryptedData;
                if (iArr3 == null || iArr3.length < unsignedShort) {
                    iArr3 = new int[unsignedShort];
                }
                int[] iArr4 = iArr3;
                if (z3) {
                    int i2 = unsignedShort * 6;
                    this.zzanr.reset(i2);
                    zza(j4, this.zzanr.data, i2);
                    j4 += (long) i2;
                    this.zzanr.zzbd(0);
                    for (int i3 = 0; i3 < unsignedShort; i3++) {
                        iArr2[i3] = this.zzanr.readUnsignedShort();
                        iArr4[i3] = this.zzanr.zzij();
                    }
                } else {
                    iArr2[0] = 0;
                    iArr4[0] = zzmbVar.size - ((int) (j4 - zzmbVar.zzaum));
                }
                zzje zzjeVar = zzmbVar.zzapp;
                zzimVar.zzamh.set(unsignedShort, iArr2, iArr4, zzjeVar.zzamz, zzimVar.zzamh.iv, zzjeVar.zzamy);
                int i4 = (int) (j4 - zzmbVar.zzaum);
                zzmbVar.zzaum += (long) i4;
                zzmbVar.size -= i4;
            }
            zzimVar.zzy(this.zzbcg.size);
            long j5 = this.zzbcg.zzaum;
            ByteBuffer byteBuffer = zzimVar.zzcq;
            int i5 = this.zzbcg.size;
            zzed(j5);
            while (i5 > 0) {
                int i6 = (int) (j5 - this.zzbci.zzbcr);
                int iMin = Math.min(i5, this.zzbce - i6);
                zznb zznbVar = this.zzbci.zzbct;
                byteBuffer.put(zznbVar.data, zznbVar.zzay(i6), iMin);
                j5 += (long) iMin;
                i5 -= iMin;
                if (j5 == this.zzbci.zzaui) {
                    this.zzazs.zza(zznbVar);
                    this.zzbci = this.zzbci.zzht();
                }
            }
            zzed(this.zzbcg.zzbcq);
        }
        return -4;
    }

    private final void zza(long j, byte[] bArr, int i) {
        zzed(j);
        int i2 = 0;
        while (i2 < i) {
            int i3 = (int) (j - this.zzbci.zzbcr);
            int iMin = Math.min(i - i2, this.zzbce - i3);
            zznb zznbVar = this.zzbci.zzbct;
            System.arraycopy(zznbVar.data, zznbVar.zzay(i3), bArr, i2, iMin);
            j += (long) iMin;
            i2 += iMin;
            if (j == this.zzbci.zzaui) {
                this.zzazs.zza(zznbVar);
                this.zzbci = this.zzbci.zzht();
            }
        }
    }

    private final void zzed(long j) {
        while (j >= this.zzbci.zzaui) {
            this.zzazs.zza(this.zzbci.zzbct);
            this.zzbci = this.zzbci.zzht();
        }
    }

    public final void zza(zzmc zzmcVar) {
        this.zzbcp = zzmcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zze(zzgq zzgqVar) {
        zzgq zzgqVar2 = zzgqVar == null ? null : zzgqVar;
        boolean zZzg = this.zzbcf.zzg(zzgqVar2);
        this.zzbcm = zzgqVar;
        this.zzbcl = false;
        zzmc zzmcVar = this.zzbcp;
        if (zzmcVar == null || !zZzg) {
            return;
        }
        zzmcVar.zzf(zzgqVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final int zza(zzix zzixVar, int i, boolean z) throws InterruptedException, IOException {
        if (!zzhr()) {
            int iZzaa = zzixVar.zzaa(i);
            if (iZzaa != -1) {
                return iZzaa;
            }
            throw new EOFException();
        }
        try {
            int iZzat = zzat(i);
            zznb zznbVar = this.zzbcj.zzbct;
            int i2 = zzixVar.read(zznbVar.data, zznbVar.zzay(this.zzbco), iZzat);
            if (i2 == -1) {
                throw new EOFException();
            }
            this.zzbco += i2;
            this.zzbcn += (long) i2;
            zzhs();
            return i2;
        } catch (Throwable th) {
            zzhs();
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zza(zzoa zzoaVar, int i) {
        if (!zzhr()) {
            zzoaVar.zzbe(i);
            return;
        }
        while (i > 0) {
            int iZzat = zzat(i);
            zznb zznbVar = this.zzbcj.zzbct;
            zzoaVar.zze(zznbVar.data, zznbVar.zzay(this.zzbco), iZzat);
            this.zzbco += iZzat;
            this.zzbcn += (long) iZzat;
            i -= iZzat;
        }
        zzhs();
    }

    @Override // com.google.android.gms.internal.ads.zzjf
    public final void zza(long j, int i, int i2, int i3, zzje zzjeVar) {
        if (!zzhr()) {
            this.zzbcf.zzec(j);
            return;
        }
        try {
            this.zzbcf.zza(j, i, this.zzbcn - ((long) i2), i2, zzjeVar);
        } finally {
            zzhs();
        }
    }

    private final boolean zzhr() {
        return this.zzbch.compareAndSet(0, 1);
    }

    private final void zzhs() {
        if (this.zzbch.compareAndSet(1, 0)) {
            return;
        }
        zzhk();
    }

    private final void zzhk() {
        this.zzbcf.zzhk();
        zzmd zzmdVarZzht = this.zzbci;
        if (zzmdVarZzht.zzbcs) {
            boolean z = this.zzbcj.zzbcs;
            zznb[] zznbVarArr = new zznb[(z ? 1 : 0) + (((int) (this.zzbcj.zzbcr - zzmdVarZzht.zzbcr)) / this.zzbce)];
            for (int i = 0; i < zznbVarArr.length; i++) {
                zznbVarArr[i] = zzmdVarZzht.zzbct;
                zzmdVarZzht = zzmdVarZzht.zzht();
            }
            this.zzazs.zza(zznbVarArr);
        }
        this.zzbci = new zzmd(0L, this.zzbce);
        this.zzbcj = this.zzbci;
        this.zzbcn = 0L;
        this.zzbco = this.zzbce;
        this.zzazs.zzm();
    }

    private final int zzat(int i) {
        if (this.zzbco == this.zzbce) {
            this.zzbco = 0;
            if (this.zzbcj.zzbcs) {
                this.zzbcj = this.zzbcj.zzbcu;
            }
            zzmd zzmdVar = this.zzbcj;
            zznb zznbVarZzhw = this.zzazs.zzhw();
            zzmd zzmdVar2 = new zzmd(this.zzbcj.zzaui, this.zzbce);
            zzmdVar.zzbct = zznbVarZzhw;
            zzmdVar.zzbcu = zzmdVar2;
            zzmdVar.zzbcs = true;
        }
        return Math.min(i, this.zzbce - this.zzbco);
    }
}
