package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8K, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C8K {
    private static final View.AccessibilityDelegate C;
    private static final C8H D;
    public final View.AccessibilityDelegate B = D.B(this);

    static {
        if (Build.VERSION.SDK_INT >= 16) {
            D = new C8H() { // from class: com.facebook.ads.redexgen.X.8I
                @Override // com.facebook.ads.redexgen.X.C8H
                public final C0385Ae A(View.AccessibilityDelegate delegate, View host) {
                    AccessibilityNodeProvider accessibilityNodeProvider = delegate.getAccessibilityNodeProvider(host);
                    if (accessibilityNodeProvider != null) {
                        return new C0385Ae(accessibilityNodeProvider);
                    }
                    return null;
                }

                @Override // com.facebook.ads.redexgen.X.C8H
                public final View.AccessibilityDelegate B(final C8K compat) {
                    return new View.AccessibilityDelegate() { // from class: com.facebook.ads.redexgen.X.8G
                        @Override // android.view.View.AccessibilityDelegate
                        public final boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                            return compat.A(host, event);
                        }

                        @Override // android.view.View.AccessibilityDelegate
                        public final AccessibilityNodeProvider getAccessibilityNodeProvider(View host) {
                            C0385Ae c0385AeB = compat.B(host);
                            if (c0385AeB != null) {
                                return (AccessibilityNodeProvider) c0385AeB.D();
                            }
                            return null;
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
                        public final boolean performAccessibilityAction(View host, int action, Bundle args) {
                            return compat.H(host, action, args);
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

                @Override // com.facebook.ads.redexgen.X.C8H
                public final boolean C(View.AccessibilityDelegate delegate, View host, int action, Bundle args) {
                    return delegate.performAccessibilityAction(host, action, args);
                }
            };
        } else {
            D = new C8H();
        }
        C = new View.AccessibilityDelegate();
    }

    public boolean A(View host, AccessibilityEvent event) {
        return C.dispatchPopulateAccessibilityEvent(host, event);
    }

    public C0385Ae B(View host) {
        return D.A(C, host);
    }

    public final View.AccessibilityDelegate C() {
        return this.B;
    }

    public void D(View host, AccessibilityEvent event) {
        C.onInitializeAccessibilityEvent(host, event);
    }

    public void E(View host, AT info) {
        C.onInitializeAccessibilityNodeInfo(host, info.B());
    }

    public final void F(View host, AccessibilityEvent event) {
        C.onPopulateAccessibilityEvent(host, event);
    }

    public boolean G(ViewGroup host, View child, AccessibilityEvent event) {
        return C.onRequestSendAccessibilityEvent(host, child, event);
    }

    public boolean H(View host, int action, Bundle args) {
        return D.C(C, host, action, args);
    }

    public final void I(View host, int eventType) {
        C.sendAccessibilityEvent(host, eventType);
    }

    public final void J(View host, AccessibilityEvent event) {
        C.sendAccessibilityEventUnchecked(host, event);
    }
}
