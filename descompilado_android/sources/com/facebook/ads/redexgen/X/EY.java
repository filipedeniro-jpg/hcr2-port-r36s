package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class EY implements EW {
    public final /* synthetic */ C0503Es B;

    public EY(C0503Es c0503Es) {
        this.B = c0503Es;
    }

    @Override // com.facebook.ads.redexgen.X.EW
    public final void VD(AbstractC0502Er item) {
        item.d(true);
        if (item.L != null && item.M == null) {
            item.L = null;
        }
        item.M = null;
        if (!item.S() && !this.B.u(item.B) && item.W()) {
            this.B.removeDetachedView(item.B, false);
        }
    }
}
