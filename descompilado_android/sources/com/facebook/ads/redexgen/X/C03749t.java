package com.facebook.ads.redexgen.X;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9t, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C03749t {
    public void A(ViewParent parent, View target) {
        if (parent instanceof InterfaceC03488t) {
            ((InterfaceC03488t) parent).onStopNestedScroll(target);
        }
    }

    public boolean B(ViewParent parent, View target, float velocityX, float velocityY, boolean consumed) {
        if (parent instanceof InterfaceC03488t) {
            return ((InterfaceC03488t) parent).onNestedFling(target, velocityX, velocityY, consumed);
        }
        return false;
    }

    public boolean C(ViewParent parent, View target, float velocityX, float velocityY) {
        if (parent instanceof InterfaceC03488t) {
            return ((InterfaceC03488t) parent).onNestedPreFling(target, velocityX, velocityY);
        }
        return false;
    }

    public void D(ViewParent parent, View target, int dx, int dy, int[] consumed) {
        if (parent instanceof InterfaceC03488t) {
            ((InterfaceC03488t) parent).onNestedPreScroll(target, dx, dy, consumed);
        }
    }

    public void E(ViewParent parent, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (parent instanceof InterfaceC03488t) {
            ((InterfaceC03488t) parent).onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        }
    }

    public void F(ViewParent parent, View child, View target, int nestedScrollAxes) {
        if (parent instanceof InterfaceC03488t) {
            ((InterfaceC03488t) parent).onNestedScrollAccepted(child, target, nestedScrollAxes);
        }
    }

    public boolean G(ViewParent parent, View child, View target, int nestedScrollAxes) {
        if (parent instanceof InterfaceC03488t) {
            return ((InterfaceC03488t) parent).onStartNestedScroll(child, target, nestedScrollAxes);
        }
        return false;
    }
}
