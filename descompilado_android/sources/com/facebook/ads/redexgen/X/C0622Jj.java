package com.facebook.ads.redexgen.X;

import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.Locale;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0622Jj {

    @VisibleForTesting
    public static KM B;

    private C0622Jj() {
    }

    public static long B() {
        return B != null ? B.currentTimeMillis() : System.currentTimeMillis();
    }

    public static String C(long startTimeMs) {
        return String.valueOf(G(startTimeMs));
    }

    @Deprecated
    public static String D(long milliseconds) {
        return E(milliseconds / 1000.0d);
    }

    public static String E(double decimalSeconds) {
        try {
            return String.format(Locale.US, "%.3f", Double.valueOf(decimalSeconds));
        } catch (Exception e) {
            Log.e(C0622Jj.class.getSimpleName(), "Can't format time.", e);
            return "1.234";
        }
    }

    public static String F(long milliseconds) {
        return Long.toString(milliseconds);
    }

    private static long G(long startTimeMs) {
        return System.currentTimeMillis() - startTimeMs;
    }
}
