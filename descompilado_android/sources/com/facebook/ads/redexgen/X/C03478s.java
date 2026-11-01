package com.facebook.ads.redexgen.X;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8s, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03478s {
    private boolean B;
    private ViewParent C;
    private ViewParent D;
    private int[] E;
    private final View F;

    public C03478s(@NonNull View view) {
        this.F = view;
    }

    public final boolean A(float velocityX, float velocityY, boolean consumed) {
        ViewParent viewParentB;
        if (!I() || (viewParentB = B(0)) == null) {
            return false;
        }
        return C03779w.C(viewParentB, this.F, velocityX, velocityY, consumed);
    }

    private ViewParent B(int type) {
        switch (type) {
            case 0:
                return this.D;
            case 1:
                return this.C;
            default:
                return null;
        }
    }

    public final boolean B(float velocityX, float velocityY) {
        ViewParent viewParentB;
        if (!I() || (viewParentB = B(0)) == null) {
            return false;
        }
        return C03779w.D(viewParentB, this.F, velocityX, velocityY);
    }

    private void C(int type, ViewParent p) {
        switch (type) {
            case 0:
                this.D = p;
                break;
            case 1:
                this.C = p;
                break;
        }
    }

    public final boolean C(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return D(dx, dy, consumed, offsetInWindow, 0);
    }

    public final boolean D(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        ViewParent viewParentB;
        int[] iArr = consumed;
        boolean z = true;
        if (!I() || (viewParentB = B(type)) == null) {
            return false;
        }
        if (dx != 0 || dy != 0) {
            int i = 0;
            int i2 = 0;
            if (offsetInWindow != null) {
                this.F.getLocationInWindow(offsetInWindow);
                i = offsetInWindow[0];
                i2 = offsetInWindow[1];
            }
            if (iArr == null) {
                if (this.E == null) {
                    this.E = new int[2];
                }
                iArr = this.E;
            }
            iArr[0] = 0;
            iArr[1] = 0;
            C03779w.F(viewParentB, this.F, dx, dy, iArr, type);
            if (offsetInWindow != null) {
                this.F.getLocationInWindow(offsetInWindow);
                offsetInWindow[0] = offsetInWindow[0] - i;
                offsetInWindow[1] = offsetInWindow[1] - i2;
            }
            if (iArr[0] == 0 && iArr[1] == 0) {
                z = false;
            }
            return z;
        }
        if (offsetInWindow == null) {
            return false;
        }
        offsetInWindow[0] = 0;
        offsetInWindow[1] = 0;
        return false;
    }

    public final boolean E(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return F(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, 0);
    }

    public final boolean F(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        ViewParent viewParentB;
        if (!I() || (viewParentB = B(type)) == null) {
            return false;
        }
        if (dxConsumed != 0 || dyConsumed != 0 || dxUnconsumed != 0 || dyUnconsumed != 0) {
            int i = 0;
            int i2 = 0;
            if (offsetInWindow != null) {
                this.F.getLocationInWindow(offsetInWindow);
                i = offsetInWindow[0];
                i2 = offsetInWindow[1];
            }
            C03779w.H(viewParentB, this.F, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
            if (offsetInWindow != null) {
                this.F.getLocationInWindow(offsetInWindow);
                int startX = offsetInWindow[0];
                offsetInWindow[0] = startX - i;
                int startX2 = offsetInWindow[1];
                offsetInWindow[1] = startX2 - i2;
            }
            return true;
        }
        if (offsetInWindow != null) {
            offsetInWindow[0] = 0;
            offsetInWindow[1] = 0;
        }
        return false;
    }

    public final boolean G() {
        return H(0);
    }

    public final boolean H(int type) {
        return B(type) != null;
    }

    public final boolean I() {
        return this.B;
    }

    public final void J(boolean enabled) {
        if (this.B) {
            C9Y.B(this.F);
        }
        this.B = enabled;
    }

    public final boolean K(int axes) {
        return L(axes, 0);
    }

    public final boolean L(int axes, int type) {
        if (H(type)) {
            return true;
        }
        if (I()) {
            View view = this.F;
            for (ViewParent parent = this.F.getParent(); parent != null; parent = parent.getParent()) {
                if (C03779w.E(parent, view, this.F, axes, type)) {
                    C(type, parent);
                    C03779w.G(parent, view, this.F, axes, type);
                    return true;
                }
                if (parent instanceof View) {
                    view = (View) parent;
                }
            }
        }
        return false;
    }

    public final void M() {
        N(0);
    }

    public final void N(int type) {
        ViewParent viewParentB = B(type);
        if (viewParentB != null) {
            C03779w.B(viewParentB, this.F, type);
            C(type, null);
        }
    }
}
