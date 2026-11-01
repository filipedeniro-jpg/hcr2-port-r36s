package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjr {
    private static final long[] zzaug = {128, 64, 32, 16, 8, 4, 2, 1};
    private int length;
    private int state;
    private final byte[] zzana = new byte[8];

    public final void reset() {
        this.state = 0;
        this.length = 0;
    }

    public final long zza(zzix zzixVar, boolean z, boolean z2, int i) throws InterruptedException, IOException {
        if (this.state == 0) {
            if (!zzixVar.zza(this.zzana, 0, 1, z)) {
                return -1L;
            }
            this.length = zzam(this.zzana[0] & 255);
            if (this.length == -1) {
                throw new IllegalStateException("No valid varint length mask found");
            }
            this.state = 1;
        }
        int i2 = this.length;
        if (i2 > i) {
            this.state = 0;
            return -2L;
        }
        if (i2 != 1) {
            zzixVar.readFully(this.zzana, 1, i2 - 1);
        }
        this.state = 0;
        return zza(this.zzana, this.length, z2);
    }

    public final int zzgf() {
        return this.length;
    }

    public static int zzam(int i) {
        int i2 = 0;
        while (true) {
            long[] jArr = zzaug;
            if (i2 >= jArr.length) {
                return -1;
            }
            if ((jArr[i2] & ((long) i)) != 0) {
                return i2 + 1;
            }
            i2++;
        }
    }

    public static long zza(byte[] bArr, int i, boolean z) {
        long j = ((long) bArr[0]) & 255;
        if (z) {
            j &= zzaug[i - 1] ^ (-1);
        }
        for (int i2 = 1; i2 < i; i2++) {
            j = (j << 8) | (((long) bArr[i2]) & 255);
        }
        return j;
    }
}
