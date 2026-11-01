package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7q, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03197q {
    public static final int[] B = new int[0];
    public static final long[] C = new long[0];
    public static final Object[] D = new Object[0];

    private C03197q() {
    }

    public static int B(int[] array, int size, int value) {
        int midVal = 0;
        int i = size - 1;
        while (midVal <= i) {
            int i2 = (midVal + i) >>> 1;
            int i3 = array[i2];
            if (i3 < value) {
                midVal = i2 + 1;
            } else {
                if (i3 <= value) {
                    return i2;
                }
                i = i2 - 1;
            }
        }
        return midVal ^ (-1);
    }

    public static int C(long[] array, int size, long value) {
        int i = 0;
        int i2 = size - 1;
        while (i <= i2) {
            int i3 = (i + i2) >>> 1;
            long j = array[i3];
            if (j < value) {
                i = i3 + 1;
            } else {
                if (j <= value) {
                    return i3;
                }
                i2 = i3 - 1;
            }
        }
        return i ^ (-1);
    }

    public static boolean D(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int E(int need) {
        return F(need * 8) / 8;
    }

    private static int F(int need) {
        for (int i = 4; i < 32; i++) {
            if (need <= (1 << i) - 12) {
                int i2 = (1 << i) - 12;
                return i2;
            }
        }
        return need;
    }
}
