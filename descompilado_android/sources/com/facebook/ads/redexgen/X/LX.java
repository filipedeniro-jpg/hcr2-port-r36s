package com.facebook.ads.redexgen.X;

import android.util.Log;
import com.facebook.ads.internal.api.BuildConfigApi;
import java.util.UUID;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class LX {
    private static final String B = LX.class.getSimpleName();
    private static volatile boolean C = false;
    private static String D;
    private static double E;
    private static double F;

    private LX() {
    }

    public static String B() {
        if (BuildConfigApi.isDebug() && !C) {
            Log.w(B, "getSessionId called without initialization.");
        }
        return D;
    }

    public static double C() {
        if (BuildConfigApi.isDebug() && !C) {
            Log.w(B, "getSessionRandom called without initialization.");
        }
        return E;
    }

    public static double D() {
        if (BuildConfigApi.isDebug() && !C) {
            Log.w(B, "getSessionTime called without initialization.");
        }
        return F;
    }

    public static void E() {
        if (!C) {
            synchronized (B) {
                if (!C) {
                    C = true;
                    F = System.currentTimeMillis() / 1000.0d;
                    D = UUID.randomUUID().toString();
                    E = Math.random();
                    F();
                }
            }
        }
    }

    private static void F() {
        C0642Kd.B("reportInitCompleted", "Session data initialized");
    }
}
