package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdlv extends zzdls {
    private static final int[] zzhax = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    private static final int[] zzhay = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    private int state;
    private int value;
    private final int[] zzhaz;

    public zzdlv(int i, byte[] bArr) {
        this.zzhav = bArr;
        this.zzhaz = (i & 8) == 0 ? zzhax : zzhay;
        this.state = 0;
        this.value = 0;
    }

    public final boolean zzb(byte[] bArr, int i, int i2, boolean z) {
        int i3 = this.state;
        if (i3 == 6) {
            return false;
        }
        byte[] bArr2 = this.zzhav;
        int[] iArr = this.zzhaz;
        int i4 = i3;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i2) {
            if (i4 == 0) {
                while (true) {
                    int i8 = i5 + 4;
                    if (i8 > i2) {
                        break;
                    }
                    i6 = iArr[bArr[i5 + 3] & 255] | (iArr[bArr[i5 + 1] & 255] << 12) | (iArr[bArr[i5] & 255] << 18) | (iArr[bArr[i5 + 2] & 255] << 6);
                    if (i6 < 0) {
                        break;
                    }
                    bArr2[i7 + 2] = (byte) i6;
                    bArr2[i7 + 1] = (byte) (i6 >> 8);
                    bArr2[i7] = (byte) (i6 >> 16);
                    i7 += 3;
                    i5 = i8;
                }
                if (i5 >= i2) {
                    break;
                }
            }
            i5++;
            int i9 = iArr[bArr[i5] & 255];
            if (i4 != 0) {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 3) {
                            if (i4 != 4) {
                                if (i4 == 5 && i9 != -1) {
                                    this.state = 6;
                                    return false;
                                }
                            } else if (i9 == -2) {
                                i4++;
                            } else if (i9 != -1) {
                                this.state = 6;
                                return false;
                            }
                        } else if (i9 >= 0) {
                            i6 = (i6 << 6) | i9;
                            bArr2[i7 + 2] = (byte) i6;
                            bArr2[i7 + 1] = (byte) (i6 >> 8);
                            bArr2[i7] = (byte) (i6 >> 16);
                            i7 += 3;
                            i4 = 0;
                        } else if (i9 == -2) {
                            bArr2[i7 + 1] = (byte) (i6 >> 2);
                            bArr2[i7] = (byte) (i6 >> 10);
                            i7 += 2;
                            i4 = 5;
                        } else if (i9 != -1) {
                            this.state = 6;
                            return false;
                        }
                    } else if (i9 >= 0) {
                        i6 = (i6 << 6) | i9;
                        i4++;
                    } else if (i9 == -2) {
                        bArr2[i7] = (byte) (i6 >> 4);
                        i7++;
                        i4 = 4;
                    } else if (i9 != -1) {
                        this.state = 6;
                        return false;
                    }
                } else if (i9 >= 0) {
                    i6 = (i6 << 6) | i9;
                    i4++;
                } else if (i9 != -1) {
                    this.state = 6;
                    return false;
                }
            } else if (i9 >= 0) {
                i4++;
                i6 = i9;
            } else if (i9 != -1) {
                this.state = 6;
                return false;
            }
        }
        if (i4 != 0) {
            if (i4 == 1) {
                this.state = 6;
                return false;
            }
            if (i4 == 2) {
                bArr2[i7] = (byte) (i6 >> 4);
                i7++;
            } else if (i4 == 3) {
                int i10 = i7 + 1;
                bArr2[i7] = (byte) (i6 >> 10);
                i7 = i10 + 1;
                bArr2[i10] = (byte) (i6 >> 2);
            } else if (i4 == 4) {
                this.state = 6;
                return false;
            }
        }
        this.state = i4;
        this.zzhaw = i7;
        return true;
    }
}
