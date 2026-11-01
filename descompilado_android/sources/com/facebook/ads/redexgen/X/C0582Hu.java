package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0582Hu {
    private static final String B = C0582Hu.class.getSimpleName();

    private C0582Hu() {
    }

    public static boolean B(Context context) {
        return C(context) && C0581Ht.C(context);
    }

    public static boolean C(Context context) {
        boolean zIsScreenOn = true;
        if (context == null) {
            Log.v(B, "Invalid context in screen interactive check, assuming interactive.");
        } else {
            try {
                PowerManager powerManager = (PowerManager) context.getSystemService("power");
                if (Build.VERSION.SDK_INT >= 20) {
                    zIsScreenOn = powerManager.isInteractive();
                } else {
                    zIsScreenOn = powerManager.isScreenOn();
                }
            } catch (Exception e) {
                Log.e(B, "Exception in screen interactive check, assuming interactive.", e);
                C0585Hx.D(context, "risky", C0584Hw.nB, new C0583Hv(e));
            }
        }
        return zIsScreenOn;
    }
}
