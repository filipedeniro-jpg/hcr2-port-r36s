package com.integralads.avid.library.inmobi.utils;

/* JADX INFO: loaded from: classes2.dex */
public class AvidTimestamp {
    private static double NANOSEC_PER_MILLISEC = 1000000.0d;

    public static double getCurrentTime() {
        double dNanoTime = System.nanoTime();
        double d = NANOSEC_PER_MILLISEC;
        Double.isNaN(dNanoTime);
        return dNanoTime / d;
    }
}
