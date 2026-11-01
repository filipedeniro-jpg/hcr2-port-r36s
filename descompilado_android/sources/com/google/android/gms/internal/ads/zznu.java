package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznu {
    private int size;
    private long[] zzbga;

    public zznu() {
        this(32);
    }

    private zznu(int i) {
        this.zzbga = new long[32];
    }

    public final void add(long j) {
        int i = this.size;
        long[] jArr = this.zzbga;
        if (i == jArr.length) {
            this.zzbga = Arrays.copyOf(jArr, i << 1);
        }
        long[] jArr2 = this.zzbga;
        int i2 = this.size;
        this.size = i2 + 1;
        jArr2[i2] = j;
    }

    public final long get(int i) {
        if (i < 0 || i >= this.size) {
            int i2 = this.size;
            StringBuilder sb = new StringBuilder(46);
            sb.append("Invalid index ");
            sb.append(i);
            sb.append(", size is ");
            sb.append(i2);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        return this.zzbga[i];
    }

    public final int size() {
        return this.size;
    }
}
