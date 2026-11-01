package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Af, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0386Af extends AccessibilityNodeProvider {
    public final /* synthetic */ AX B;

    public C0386Af(AX ax) {
        this.B = ax;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
        return (AccessibilityNodeInfo) this.B.kB(virtualViewId);
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String text, int virtualViewId) {
        return this.B.pB(text, virtualViewId);
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final boolean performAction(int virtualViewId, int action, Bundle arguments) {
        return this.B.XF(virtualViewId, action, arguments);
    }
}
