package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.view.WindowInsets;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class A5 {
    private final Object B;

    private A5(Object insets) {
        this.B = insets;
    }

    public final int A() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.B).getSystemWindowInsetBottom();
        }
        return 0;
    }

    public final int B() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.B).getSystemWindowInsetLeft();
        }
        return 0;
    }

    public static A5 B(Object insets) {
        if (insets == null) {
            return null;
        }
        return new A5(insets);
    }

    public final int C() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.B).getSystemWindowInsetRight();
        }
        return 0;
    }

    public static Object C(A5 insets) {
        if (insets == null) {
            return null;
        }
        return insets.B;
    }

    public final int D() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.B).getSystemWindowInsetTop();
        }
        return 0;
    }

    public final boolean E() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.B).isConsumed();
        }
        return false;
    }

    public final A5 F(int left, int top, int right, int bottom) {
        if (Build.VERSION.SDK_INT >= 20) {
            return new A5(((WindowInsets) this.B).replaceSystemWindowInsets(left, top, right, bottom));
        }
        return null;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        A5 a5 = (A5) o;
        if (this.B == null) {
            return a5.B == null;
        }
        return this.B.equals(a5.B);
    }

    public final int hashCode() {
        if (this.B == null) {
            return 0;
        }
        return this.B.hashCode();
    }
}
