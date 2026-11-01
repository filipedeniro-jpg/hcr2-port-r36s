package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzod {
    private byte[] data;
    private int zzbgm;
    private int zzbgn = 0;
    private int zzbgo;

    public zzod(byte[] bArr, int i, int i2) {
        this.data = bArr;
        this.zzbgm = i;
        this.zzbgo = i2;
        zzip();
    }

    public final void zzbh(int i) {
        int i2 = this.zzbgm;
        this.zzbgm = (i / 8) + i2;
        this.zzbgn += i % 8;
        int i3 = this.zzbgn;
        if (i3 > 7) {
            this.zzbgm++;
            this.zzbgn = i3 - 8;
        }
        while (true) {
            i2++;
            if (i2 <= this.zzbgm) {
                if (zzbi(i2)) {
                    this.zzbgm++;
                    i2 += 2;
                }
            } else {
                zzip();
                return;
            }
        }
    }

    public final boolean zzil() {
        return zzbg(1) == 1;
    }

    public final int zzbg(int i) {
        int i2;
        int i3;
        if (i == 0) {
            return 0;
        }
        int i4 = i / 8;
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            int i7 = zzbi(this.zzbgm + 1) ? this.zzbgm + 2 : this.zzbgm + 1;
            int i8 = this.zzbgn;
            if (i8 != 0) {
                byte[] bArr = this.data;
                i3 = ((bArr[i7] & 255) >>> (8 - i8)) | ((bArr[this.zzbgm] & 255) << i8);
            } else {
                i3 = this.data[this.zzbgm];
            }
            i -= 8;
            i5 |= (255 & i3) << i;
            this.zzbgm = i7;
        }
        if (i > 0) {
            int i9 = this.zzbgn + i;
            byte b = (byte) (255 >> (8 - i));
            int i10 = zzbi(this.zzbgm + 1) ? this.zzbgm + 2 : this.zzbgm + 1;
            if (i9 > 8) {
                byte[] bArr2 = this.data;
                i2 = (b & (((255 & bArr2[i10]) >> (16 - i9)) | ((bArr2[this.zzbgm] & 255) << (i9 - 8)))) | i5;
                this.zzbgm = i10;
            } else {
                i2 = (b & ((255 & this.data[this.zzbgm]) >> (8 - i9))) | i5;
                if (i9 == 8) {
                    this.zzbgm = i10;
                }
            }
            i5 = i2;
            this.zzbgn = i9 % 8;
        }
        zzip();
        return i5;
    }

    public final int zzim() {
        return zzio();
    }

    public final int zzin() {
        int iZzio = zzio();
        return (iZzio % 2 == 0 ? -1 : 1) * ((iZzio + 1) / 2);
    }

    private final int zzio() {
        int i = 0;
        while (!zzil()) {
            i++;
        }
        return ((1 << i) - 1) + (i > 0 ? zzbg(i) : 0);
    }

    private final boolean zzbi(int i) {
        if (2 > i || i >= this.zzbgo) {
            return false;
        }
        byte[] bArr = this.data;
        return bArr[i] == 3 && bArr[i + (-2)] == 0 && bArr[i - 1] == 0;
    }

    private final void zzip() {
        int i;
        int i2;
        int i3 = this.zzbgm;
        zznt.checkState(i3 >= 0 && (i = this.zzbgn) >= 0 && i < 8 && (i3 < (i2 = this.zzbgo) || (i3 == i2 && i == 0)));
    }
}
