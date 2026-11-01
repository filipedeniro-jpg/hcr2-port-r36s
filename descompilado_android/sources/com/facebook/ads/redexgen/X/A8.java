package com.facebook.ads.redexgen.X;

import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: loaded from: assets/audience_network.dex */
@RequiresApi(19)
public class A8 extends A7 {
    @Override // com.facebook.ads.redexgen.X.A6
    public final void A(AccessibilityEvent event, int types) {
        event.setContentChangeTypes(types);
    }

    @Override // com.facebook.ads.redexgen.X.A6
    public final int B(AccessibilityEvent event) {
        return event.getContentChangeTypes();
    }
}
