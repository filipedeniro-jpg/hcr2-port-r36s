package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdnr {
    private static long zze(byte[] bArr, int i) {
        return ((long) (((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16))) & 4294967295L;
    }

    private static long zzg(byte[] bArr, int i, int i2) {
        return (zze(bArr, i) >> i2) & 67108863;
    }

    private static void zza(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) (255 & j);
            i2++;
            j >>= 8;
        }
    }

    static byte[] zzf(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        long jZzg = zzg(bArr, 0, 0) & 67108863;
        int i = 2;
        int i2 = 3;
        long jZzg2 = zzg(bArr, 3, 2) & 67108611;
        long jZzg3 = zzg(bArr, 6, 4) & 67092735;
        long jZzg4 = zzg(bArr, 9, 6) & 66076671;
        long jZzg5 = zzg(bArr, 12, 8) & 1048575;
        long j = jZzg2 * 5;
        long j2 = jZzg3 * 5;
        long j3 = jZzg4 * 5;
        long j4 = jZzg5 * 5;
        byte[] bArr3 = new byte[17];
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        int i3 = 0;
        while (i3 < bArr2.length) {
            int iMin = Math.min(16, bArr2.length - i3);
            System.arraycopy(bArr2, i3, bArr3, 0, iMin);
            bArr3[iMin] = 1;
            if (iMin != 16) {
                Arrays.fill(bArr3, iMin + 1, 17, (byte) 0);
            }
            long jZzg6 = j9 + zzg(bArr3, 0, 0);
            long jZzg7 = j5 + zzg(bArr3, i2, i);
            long jZzg8 = j6 + zzg(bArr3, 6, 4);
            long jZzg9 = j7 + zzg(bArr3, 9, 6);
            long jZzg10 = j8 + (zzg(bArr3, 12, 8) | ((long) (bArr3[16] << 24)));
            long j10 = (jZzg6 * jZzg) + (jZzg7 * j4) + (jZzg8 * j3) + (jZzg9 * j2) + (jZzg10 * j);
            long j11 = (jZzg6 * jZzg2) + (jZzg7 * jZzg) + (jZzg8 * j4) + (jZzg9 * j3) + (jZzg10 * j2);
            long j12 = (jZzg6 * jZzg3) + (jZzg7 * jZzg2) + (jZzg8 * jZzg) + (jZzg9 * j4) + (jZzg10 * j3);
            long j13 = (jZzg6 * jZzg4) + (jZzg7 * jZzg3) + (jZzg8 * jZzg2) + (jZzg9 * jZzg) + (jZzg10 * j4);
            long j14 = j11 + (j10 >> 26);
            long j15 = j12 + (j14 >> 26);
            long j16 = j13 + (j15 >> 26);
            long j17 = (jZzg6 * jZzg5) + (jZzg7 * jZzg4) + (jZzg8 * jZzg3) + (jZzg9 * jZzg2) + (jZzg10 * jZzg) + (j16 >> 26);
            long j18 = (j10 & 67108863) + ((j17 >> 26) * 5);
            j5 = (j14 & 67108863) + (j18 >> 26);
            i3 += 16;
            j6 = j15 & 67108863;
            j7 = j16 & 67108863;
            j8 = j17 & 67108863;
            j9 = j18 & 67108863;
            i = 2;
            i2 = 3;
        }
        long j19 = j6 + (j5 >> 26);
        long j20 = j19 & 67108863;
        long j21 = j7 + (j19 >> 26);
        long j22 = j21 & 67108863;
        long j23 = j8 + (j21 >> 26);
        long j24 = j23 & 67108863;
        long j25 = j9 + ((j23 >> 26) * 5);
        long j26 = j25 & 67108863;
        long j27 = (j5 & 67108863) + (j25 >> 26);
        long j28 = j26 + 5;
        long j29 = j28 & 67108863;
        long j30 = (j28 >> 26) + j27;
        long j31 = j20 + (j30 >> 26);
        long j32 = j22 + (j31 >> 26);
        long j33 = j32 & 67108863;
        long j34 = (j24 + (j32 >> 26)) - 67108864;
        long j35 = j34 >> 63;
        long j36 = j26 & j35;
        long j37 = j27 & j35;
        long j38 = j20 & j35;
        long j39 = j22 & j35;
        long j40 = j24 & j35;
        long j41 = j35 ^ (-1);
        long j42 = (j30 & 67108863 & j41) | j37;
        long j43 = (j31 & 67108863 & j41) | j38;
        long j44 = (j33 & j41) | j39;
        long j45 = (j34 & j41) | j40;
        long j46 = ((j42 << 26) | j36 | (j29 & j41)) & 4294967295L;
        long j47 = ((j42 >> 6) | (j43 << 20)) & 4294967295L;
        long j48 = ((j43 >> 12) | (j44 << 14)) & 4294967295L;
        long j49 = ((j44 >> 18) | (j45 << 8)) & 4294967295L;
        long jZze = j46 + zze(bArr, 16);
        long j50 = jZze & 4294967295L;
        long jZze2 = j47 + zze(bArr, 20) + (jZze >> 32);
        long j51 = jZze2 & 4294967295L;
        long jZze3 = j48 + zze(bArr, 24) + (jZze2 >> 32);
        long j52 = jZze3 & 4294967295L;
        long jZze4 = (j49 + zze(bArr, 28) + (jZze3 >> 32)) & 4294967295L;
        byte[] bArr4 = new byte[16];
        zza(bArr4, j50, 0);
        zza(bArr4, j51, 4);
        zza(bArr4, j52, 8);
        zza(bArr4, jZze4, 12);
        return bArr4;
    }
}
