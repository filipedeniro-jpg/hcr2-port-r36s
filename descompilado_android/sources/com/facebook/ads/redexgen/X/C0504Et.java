package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Et, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0504Et extends C8K {
    public final C0505Eu B;

    public C0504Et(C0505Eu recyclerViewDelegate) {
        this.B = recyclerViewDelegate;
    }

    @Override // com.facebook.ads.redexgen.X.C8K
    public final void E(View host, AT info) {
        super.E(host, info);
        if (!this.B.L() && this.B.C.getLayoutManager() != null) {
            this.B.C.getLayoutManager().QA(host, info);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C8K
    public final boolean H(View host, int action, Bundle args) {
        if (super.H(host, action, args)) {
            return true;
        }
        if (!this.B.L() && this.B.C.getLayoutManager() != null) {
            return this.B.C.getLayoutManager().gA(host, action, args);
        }
        return false;
    }
}
