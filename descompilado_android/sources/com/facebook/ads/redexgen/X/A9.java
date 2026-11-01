package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class A9 {
    private static final A6 B;

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            B = new A8();
        } else if (Build.VERSION.SDK_INT >= 16) {
            B = new A7();
        } else {
            B = new A6();
        }
    }

    private A9() {
    }

    public static void B(AccessibilityEvent event, int changeTypes) {
        B.A(event, changeTypes);
    }

    public static int C(AccessibilityEvent event) {
        return B.B(event);
    }
}
