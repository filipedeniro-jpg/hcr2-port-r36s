package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Eu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0505Eu extends C8K {
    public final C8K B = new C0504Et(this);
    public final C0503Es C;

    public C0505Eu(C0503Es recyclerView) {
        this.C = recyclerView;
    }

    @Override // com.facebook.ads.redexgen.X.C8K
    public final void D(View host, AccessibilityEvent event) {
        super.D(host, event);
        event.setClassName(C0503Es.class.getName());
        if ((host instanceof C0503Es) && !L()) {
            C0503Es c0503Es = (C0503Es) host;
            if (c0503Es.getLayoutManager() != null) {
                c0503Es.getLayoutManager().OA(event);
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.C8K
    public final void E(View host, AT info) {
        super.E(host, info);
        info.V(C0503Es.class.getName());
        if (!L() && this.C.getLayoutManager() != null) {
            this.C.getLayoutManager().PA(info);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C8K
    public final boolean H(View host, int action, Bundle args) {
        if (super.H(host, action, args)) {
            return true;
        }
        if (!L() && this.C.getLayoutManager() != null) {
            return this.C.getLayoutManager().fA(action, args);
        }
        return false;
    }

    public final C8K K() {
        return this.B;
    }

    public final boolean L() {
        return this.C.m248c();
    }
}
