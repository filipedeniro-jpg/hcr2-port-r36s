package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9Y, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C9Y {
    public static final C9N B;

    static {
        if (Build.VERSION.SDK_INT >= 26) {
            B = new C9W() { // from class: com.facebook.ads.redexgen.X.9X
            };
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            B = new C9W();
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            B = new C9V();
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            B = new C9U();
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            B = new C9S();
            return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            B = new C9R();
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            B = new C9Q();
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            B = new C9P();
        } else if (Build.VERSION.SDK_INT >= 15) {
            B = new C9O();
        } else {
            B = new C9N();
        }
    }

    private C9Y() {
    }

    public static void B(@NonNull View view) {
        B.A(view);
    }

    public static void C(View v, InterfaceC03518w listener) {
        B.B(v, listener);
    }

    public static void D(View view, int mode) {
        B.C(view, mode);
    }

    public static void E(View view, float elevation) {
        B.E(view, elevation);
    }

    public static void F(View v, C8K delegate) {
        B.F(v, delegate);
    }

    public static void G(View view, Runnable action, long delayMillis) {
        B.G(view, action, delayMillis);
    }

    public static A5 H(View view, A5 insets) {
        return B.D(view, insets);
    }

    public static void I(View view, Runnable action) {
        B.H(view, action);
    }

    public static void J(View view) {
        B.I(view);
    }

    public static A5 K(View view, A5 insets) {
        return B.J(view, insets);
    }

    public static void L(View view, int offset) {
        B.K(view, offset);
    }

    public static void M(View view, int offset) {
        B.L(view, offset);
    }

    public static boolean N(View view) {
        return B.N(view);
    }

    public static boolean O(View v) {
        return B.O(v);
    }

    public static int P(View view) {
        return B.R(view);
    }

    public static int Q(View view) {
        return B.T(view);
    }

    public static int R(View view) {
        return B.U(view);
    }

    public static int S(View view) {
        return B.S(view);
    }

    public static float T(View view) {
        return B.Q(view);
    }

    public static Display U(@NonNull View view) {
        return B.P(view);
    }
}
