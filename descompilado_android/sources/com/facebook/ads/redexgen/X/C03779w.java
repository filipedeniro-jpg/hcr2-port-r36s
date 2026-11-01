package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9w, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03779w {
    public static final C03749t B;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            B = new C03759u() { // from class: com.facebook.ads.redexgen.X.9v
                @Override // com.facebook.ads.redexgen.X.C03749t
                public final void A(ViewParent parent, View target) {
                    try {
                        parent.onStopNestedScroll(target);
                    } catch (AbstractMethodError e) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onStopNestedScroll", e);
                    }
                }

                @Override // com.facebook.ads.redexgen.X.C03749t
                public final boolean B(ViewParent parent, View target, float velocityX, float velocityY, boolean consumed) {
                    try {
                        return parent.onNestedFling(target, velocityX, velocityY, consumed);
                    } catch (AbstractMethodError e) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedFling", e);
                        return false;
                    }
                }

                @Override // com.facebook.ads.redexgen.X.C03749t
                public final boolean C(ViewParent parent, View target, float velocityX, float velocityY) {
                    try {
                        return parent.onNestedPreFling(target, velocityX, velocityY);
                    } catch (AbstractMethodError e) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedPreFling", e);
                        return false;
                    }
                }

                @Override // com.facebook.ads.redexgen.X.C03749t
                public final void D(ViewParent parent, View target, int dx, int dy, int[] consumed) {
                    try {
                        parent.onNestedPreScroll(target, dx, dy, consumed);
                    } catch (AbstractMethodError e) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedPreScroll", e);
                    }
                }

                @Override // com.facebook.ads.redexgen.X.C03749t
                public final void E(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
                    try {
                        parent.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
                    } catch (AbstractMethodError e) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedScroll", e);
                    }
                }

                @Override // com.facebook.ads.redexgen.X.C03749t
                public final void F(ViewParent parent, View child, View target, int nestedScrollAxes) {
                    try {
                        parent.onNestedScrollAccepted(child, target, nestedScrollAxes);
                    } catch (AbstractMethodError e) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedScrollAccepted", e);
                    }
                }

                @Override // com.facebook.ads.redexgen.X.C03749t
                public final boolean G(ViewParent parent, View child, View target, int nestedScrollAxes) {
                    try {
                        return parent.onStartNestedScroll(child, target, nestedScrollAxes);
                    } catch (AbstractMethodError e) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onStartNestedScroll", e);
                        return false;
                    }
                }
            };
        } else if (Build.VERSION.SDK_INT >= 19) {
            B = new C03759u();
        } else {
            B = new C03749t();
        }
    }

    private C03779w() {
    }

    public static void B(ViewParent parent, View target, int type) {
        if (parent instanceof InterfaceC03498u) {
            ((InterfaceC03498u) parent).onStopNestedScroll(target, type);
        } else {
            if (type != 0) {
                return;
            }
            B.A(parent, target);
        }
    }

    public static boolean C(ViewParent parent, View target, float velocityX, float velocityY, boolean consumed) {
        return B.B(parent, target, velocityX, velocityY, consumed);
    }

    public static boolean D(ViewParent parent, View target, float velocityX, float velocityY) {
        return B.C(parent, target, velocityX, velocityY);
    }

    public static boolean E(ViewParent parent, View child, View target, int nestedScrollAxes, int type) {
        if (parent instanceof InterfaceC03498u) {
            return ((InterfaceC03498u) parent).onStartNestedScroll(child, target, nestedScrollAxes, type);
        }
        if (type == 0) {
            return B.G(parent, child, target, nestedScrollAxes);
        }
        return false;
    }

    public static void F(ViewParent parent, View target, int dx, int dy, int[] consumed, int type) {
        if (parent instanceof InterfaceC03498u) {
            ((InterfaceC03498u) parent).onNestedPreScroll(target, dx, dy, consumed, type);
        } else {
            if (type != 0) {
                return;
            }
            B.D(parent, target, dx, dy, consumed);
        }
    }

    public static void G(ViewParent parent, View child, View target, int nestedScrollAxes, int type) {
        if (parent instanceof InterfaceC03498u) {
            ((InterfaceC03498u) parent).onNestedScrollAccepted(child, target, nestedScrollAxes, type);
        } else {
            if (type != 0) {
                return;
            }
            B.F(parent, child, target, nestedScrollAxes);
        }
    }

    public static void H(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (parent instanceof InterfaceC03498u) {
            ((InterfaceC03498u) parent).onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        } else {
            if (type != 0) {
                return;
            }
            B.E(parent, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        }
    }
}
