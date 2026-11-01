package com.facebook.ads.redexgen.X;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Kh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0646Kh {
    private static Map<String, Long> C = new ConcurrentHashMap();
    private static Map<String, Long> B = new ConcurrentHashMap();
    private static Map<String, String> D = new ConcurrentHashMap();

    private C0646Kh() {
    }

    public static String B(C0647Ki c0647Ki) {
        C0642Kd.B("getLastResponse", "Using last ad response");
        return D.get(G(c0647Ki));
    }

    public static void C(long refreshThreshold, C0647Ki adEnvironmentData) {
        C.put(G(adEnvironmentData), Long.valueOf(refreshThreshold));
    }

    public static void D(String response, C0647Ki adEnvironmentData) {
        D.put(G(adEnvironmentData), response);
    }

    public static void E(C0647Ki adEnvironmentData) {
        B.put(G(adEnvironmentData), Long.valueOf(System.currentTimeMillis()));
    }

    public static boolean F(C0647Ki adEnvironmentData) {
        String strG = G(adEnvironmentData);
        if (!B.containsKey(strG)) {
            return false;
        }
        long jLongValue = B.get(strG).longValue();
        long jH = H(strG, adEnvironmentData.A());
        long refreshThreshold = System.currentTimeMillis();
        return refreshThreshold - jLongValue < jH;
    }

    private static String G(C0647Ki adEnvironmentData) {
        Locale locale = Locale.US;
        Object[] objArr = new Object[6];
        objArr[0] = adEnvironmentData.F();
        objArr[1] = adEnvironmentData.A();
        objArr[2] = adEnvironmentData.C();
        objArr[3] = Integer.valueOf(adEnvironmentData.B() == null ? 0 : adEnvironmentData.B().A());
        objArr[4] = Integer.valueOf(adEnvironmentData.B() != null ? adEnvironmentData.B().B() : 0);
        objArr[5] = Integer.valueOf(adEnvironmentData.E());
        return String.format(locale, "%s:%s:%s:%d:%d:%d", objArr);
    }

    private static long H(String key, KT adLayoutType) {
        if (C.containsKey(key)) {
            return C.get(key).longValue();
        }
        switch (adLayoutType) {
            case BANNER:
                return 15000L;
            case INTERSTITIAL:
            case NATIVE:
            default:
                return -1000L;
        }
    }
}
