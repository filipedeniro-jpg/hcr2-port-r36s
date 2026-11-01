package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zziv implements zzix {
    private static final byte[] zzamp = new byte[4096];
    private final zznc zzamq;
    private final long zzamr;
    private long zzams;
    private byte[] zzamt = new byte[65536];
    private int zzamu;
    private int zzamv;

    public zziv(zznc zzncVar, long j, long j2) {
        this.zzamq = zzncVar;
        this.zzams = j;
        this.zzamr = j2;
    }

    @Override // com.google.android.gms.internal.ads.zzix
    public final int read(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        int iZzb = zzb(bArr, i, i2);
        if (iZzb == 0) {
            iZzb = zza(bArr, i, i2, 0, true);
        }
        zzaf(iZzb);
        return iZzb;
    }

    @Override // com.google.android.gms.internal.ads.zzix
    public final boolean zza(byte[] bArr, int i, int i2, boolean z) throws InterruptedException, IOException {
        int iZzb = zzb(bArr, i, i2);
        while (iZzb < i2 && iZzb != -1) {
            iZzb = zza(bArr, i, i2, iZzb, z);
        }
        zzaf(iZzb);
        return iZzb != -1;
    }

    @Override // com.google.android.gms.internal.ads.zzix
    public final void readFully(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        zza(bArr, i, i2, false);
    }

    @Override // com.google.android.gms.internal.ads.zzix
    public final int zzaa(int i) throws InterruptedException, IOException {
        int iZzad = zzad(i);
        if (iZzad == 0) {
            byte[] bArr = zzamp;
            iZzad = zza(bArr, 0, Math.min(i, bArr.length), 0, true);
        }
        zzaf(iZzad);
        return iZzad;
    }

    @Override // com.google.android.gms.internal.ads.zzix
    public final void zzab(int i) throws InterruptedException, IOException {
        int iZzad = zzad(i);
        while (iZzad < i && iZzad != -1) {
            byte[] bArr = zzamp;
            iZzad = zza(bArr, -iZzad, Math.min(i, bArr.length + iZzad), iZzad, false);
        }
        zzaf(iZzad);
    }

    @Override // com.google.android.gms.internal.ads.zzix
    public final void zza(byte[] bArr, int i, int i2) throws InterruptedException, IOException {
        if (zzd(i2, false)) {
            System.arraycopy(this.zzamt, this.zzamu - i2, bArr, i, i2);
        }
    }

    private final boolean zzd(int i, boolean z) throws InterruptedException, IOException {
        int i2 = this.zzamu + i;
        byte[] bArr = this.zzamt;
        if (i2 > bArr.length) {
            this.zzamt = Arrays.copyOf(this.zzamt, zzoh.zzd(bArr.length << 1, 65536 + i2, i2 + 524288));
        }
        int iMin = Math.min(this.zzamv - this.zzamu, i);
        while (iMin < i) {
            iMin = zza(this.zzamt, this.zzamu, i, iMin, false);
            if (iMin == -1) {
                return false;
            }
        }
        this.zzamu += i;
        this.zzamv = Math.max(this.zzamv, this.zzamu);
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzix
    public final void zzac(int i) throws InterruptedException, IOException {
        zzd(i, false);
    }

    @Override // com.google.android.gms.internal.ads.zzix
    public final void zzfz() {
        this.zzamu = 0;
    }

    @Override // com.google.android.gms.internal.ads.zzix
    public final long getPosition() {
        return this.zzams;
    }

    @Override // com.google.android.gms.internal.ads.zzix
    public final long getLength() {
        return this.zzamr;
    }

    private final int zzad(int i) {
        int iMin = Math.min(this.zzamv, i);
        zzae(iMin);
        return iMin;
    }

    private final int zzb(byte[] bArr, int i, int i2) {
        int i3 = this.zzamv;
        if (i3 == 0) {
            return 0;
        }
        int iMin = Math.min(i3, i2);
        System.arraycopy(this.zzamt, 0, bArr, i, iMin);
        zzae(iMin);
        return iMin;
    }

    private final void zzae(int i) {
        this.zzamv -= i;
        this.zzamu = 0;
        byte[] bArr = this.zzamt;
        int i2 = this.zzamv;
        if (i2 < bArr.length - 524288) {
            bArr = new byte[i2 + 65536];
        }
        System.arraycopy(this.zzamt, i, bArr, 0, this.zzamv);
        this.zzamt = bArr;
    }

    private final int zza(byte[] bArr, int i, int i2, int i3, boolean z) throws InterruptedException, IOException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int i4 = this.zzamq.read(bArr, i + i3, i2 - i3);
        if (i4 != -1) {
            return i3 + i4;
        }
        if (i3 == 0 && z) {
            return -1;
        }
        throw new EOFException();
    }

    private final void zzaf(int i) {
        if (i != -1) {
            this.zzams += (long) i;
        }
    }
}
