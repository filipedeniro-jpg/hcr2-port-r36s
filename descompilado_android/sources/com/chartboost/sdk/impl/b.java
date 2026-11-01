package com.chartboost.sdk.impl;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static int[] a(char[] cArr, int[] iArr, boolean z) {
        int i = (cArr[0] << 16) + cArr[1];
        int i2 = (cArr[2] << 16) + cArr[3];
        if (!z) {
            a(iArr);
        }
        int i3 = i2;
        int i4 = i;
        int i5 = 0;
        while (i5 < 16) {
            int i6 = i4 ^ iArr[i5];
            a aVar = a.b;
            int i7 = i3 ^ (((aVar.a[0][i6 >>> 24] + aVar.a[1][(i6 >>> 16) & 255]) ^ aVar.a[2][(i6 >>> 8) & 255]) + aVar.a[3][i6 & 255]);
            i5++;
            i3 = i6;
            i4 = i7;
        }
        int i8 = iArr[16] ^ i4;
        int i9 = iArr[17] ^ i3;
        int[] iArr2 = {i9, i8};
        cArr[0] = i9 >>> 16;
        cArr[1] = (char) i9;
        cArr[2] = i8 >>> 16;
        cArr[3] = (char) i8;
        if (!z) {
            a(iArr);
        }
        return iArr2;
    }

    private static void a(int[] iArr) {
        for (int i = 0; i < iArr.length / 2; i++) {
            int i2 = iArr[i];
            iArr[i] = iArr[(iArr.length - i) - 1];
            iArr[(iArr.length - i) - 1] = i2;
        }
    }
}
