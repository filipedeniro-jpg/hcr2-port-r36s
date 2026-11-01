package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class NumberUtils {
    public static final long convertToMillisecondsFromSeconds(long j) {
        return j * 1000;
    }

    private NumberUtils() {
    }

    public static int parseInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static final long convertToMillisecondsFromNanoseconds(long j) {
        return j / 1000000;
    }
}
