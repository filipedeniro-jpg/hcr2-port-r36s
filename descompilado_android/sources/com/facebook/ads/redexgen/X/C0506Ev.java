package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ev, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0506Ev {
    private C0506Ev() {
    }

    public static int B(C0499Eo state, ED orientation, View startChild, View endChild, AbstractC0481Dw lm, boolean smoothScrollbarEnabled) {
        if (lm.b() == 0 || state.B() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        if (!smoothScrollbarEnabled) {
            return Math.abs(lm.w(startChild) - lm.w(endChild)) + 1;
        }
        int extend = orientation.A(endChild) - orientation.D(startChild);
        return Math.min(orientation.K(), extend);
    }

    public static int C(C0499Eo state, ED orientation, View startChild, View endChild, AbstractC0481Dw lm, boolean smoothScrollbarEnabled, boolean reverseLayout) {
        int iMax;
        if (lm.b() == 0 || state.B() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        int iMin = Math.min(lm.w(startChild), lm.w(endChild));
        int iMax2 = Math.max(lm.w(startChild), lm.w(endChild));
        if (reverseLayout) {
            iMax = Math.max(0, (state.B() - iMax2) - 1);
        } else {
            iMax = Math.max(0, iMin);
        }
        if (!smoothScrollbarEnabled) {
            return iMax;
        }
        return Math.round((iMax * (Math.abs(orientation.A(endChild) - orientation.D(startChild)) / (Math.abs(lm.w(startChild) - lm.w(endChild)) + 1))) + (orientation.J() - orientation.D(startChild)));
    }

    public static int D(C0499Eo state, ED orientation, View startChild, View endChild, AbstractC0481Dw lm, boolean smoothScrollbarEnabled) {
        if (lm.b() == 0 || state.B() == 0 || startChild == null || endChild == null) {
            return 0;
        }
        if (!smoothScrollbarEnabled) {
            return state.B();
        }
        return (int) (((orientation.A(endChild) - orientation.D(startChild)) / (Math.abs(lm.w(startChild) - lm.w(endChild)) + 1)) * state.B());
    }
}
