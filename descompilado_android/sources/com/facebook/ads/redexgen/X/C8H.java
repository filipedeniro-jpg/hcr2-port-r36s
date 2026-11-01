package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8H, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C8H {
    public C0385Ae A(View.AccessibilityDelegate delegate, View host) {
        return null;
    }

    public View.AccessibilityDelegate B(final C8K compat) {
        return new View.AccessibilityDelegate() { // from class: com.facebook.ads.redexgen.X.8J
            @Override // android.view.View.AccessibilityDelegate
            public final boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                return compat.A(host, event);
            }

            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                compat.D(host, event);
            }

            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                compat.E(host, AT.B(info));
            }

            @Override // android.view.View.AccessibilityDelegate
            public final void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                compat.F(host, event);
            }

            @Override // android.view.View.AccessibilityDelegate
            public final boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
                return compat.G(host, child, event);
            }

            @Override // android.view.View.AccessibilityDelegate
            public final void sendAccessibilityEvent(View host, int eventType) {
                compat.I(host, eventType);
            }

            @Override // android.view.View.AccessibilityDelegate
            public final void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
                compat.J(host, event);
            }
        };
    }

    public boolean C(View.AccessibilityDelegate delegate, View host, int action, Bundle args) {
        return false;
    }
}
