package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzob {
    private byte[] data;
    private int zzbgm;
    private int zzbgn;
    private int zzbgo;

    public zzob() {
    }

    public zzob(byte[] bArr) {
        this(bArr, bArr.length);
    }

    private zzob(byte[] bArr, int i) {
        this.data = bArr;
        this.zzbgo = i;
    }

    public final int zzbg(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z = false;
        if (i == 0) {
            return 0;
        }
        int i6 = i / 8;
        int i7 = i;
        int i8 = 0;
        for (int i9 = 0; i9 < i6; i9++) {
            int i10 = this.zzbgn;
            if (i10 != 0) {
                byte[] bArr = this.data;
                int i11 = this.zzbgm;
                i5 = ((bArr[i11 + 1] & 255) >>> (8 - i10)) | ((bArr[i11] & 255) << i10);
            } else {
                i5 = this.data[this.zzbgm];
            }
            i7 -= 8;
            i8 |= (255 & i5) << i7;
            this.zzbgm++;
        }
        if (i7 > 0) {
            int i12 = this.zzbgn + i7;
            byte b = (byte) (255 >> (8 - i7));
            if (i12 > 8) {
                byte[] bArr2 = this.data;
                int i13 = this.zzbgm;
                i4 = (b & (((bArr2[i13 + 1] & 255) >> (16 - i12)) | ((bArr2[i13] & 255) << (i12 - 8)))) | i8;
                this.zzbgm = i13 + 1;
            } else {
                byte[] bArr3 = this.data;
                int i14 = this.zzbgm;
                i4 = (b & ((bArr3[i14] & 255) >> (8 - i12))) | i8;
                if (i12 == 8) {
                    this.zzbgm = i14 + 1;
                }
            }
            i8 = i4;
            this.zzbgn = i12 % 8;
        }
        int i15 = this.zzbgm;
        if (i15 >= 0 && (i2 = this.zzbgn) >= 0 && i2 < 8 && (i15 < (i3 = this.zzbgo) || (i15 == i3 && i2 == 0))) {
            z = true;
        }
        zznt.checkState(z);
        return i8;
    }
}
