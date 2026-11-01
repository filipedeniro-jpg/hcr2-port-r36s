package com.integralads.avid.library.adcolony.utils;

/* JADX INFO: loaded from: classes2.dex */
public class AvidTimestamp {
    private static double a = 1000000.0d;

    public static double getCurrentTime() {
        double dNanoTime = System.nanoTime();
        double d = a;
        Double.isNaN(dNanoTime);
        return dNanoTime / d;
    }
}
