package com.facebook.ads.redexgen.X;

import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9C, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C9C implements C1D {
    public final WeakReference<C9K> B;

    private C9C(C9K cardLayout) {
        this.B = new WeakReference<>(cardLayout);
    }

    @Override // com.facebook.ads.redexgen.X.C1D
    public final void FE(boolean success) {
        C9K c9k = this.B.get();
        if (c9k == null) {
            return;
        }
        c9k.Q = success;
        c9k.G();
    }
}
