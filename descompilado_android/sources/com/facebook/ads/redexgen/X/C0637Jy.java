package com.facebook.ads.redexgen.X;

import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0637Jy implements InterfaceC0636Jx {
    private WeakReference<KF> B;

    public C0637Jy(KF internalNativeAd) {
        this.B = new WeakReference<>(internalNativeAd);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0636Jx
    public final void oE(boolean success) {
        if (this.B.get() != null) {
            this.B.get().Q(success, false);
        }
    }
}
