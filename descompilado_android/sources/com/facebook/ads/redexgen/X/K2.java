package com.facebook.ads.redexgen.X;

import java.util.Iterator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class K2 extends C0P {
    public final /* synthetic */ KF B;

    public K2(KF this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void A() {
        if (this.B.D == null) {
            return;
        }
        this.B.D.MD();
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void B(C0O adAdapter) {
        if (this.B.J == null) {
            return;
        }
        this.B.J.N();
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void D(KQ error) {
        if (this.B.D == null) {
            return;
        }
        this.B.D.yD(error);
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void I() {
        throw new IllegalStateException("Native ads manager their own impressions.");
    }

    @Override // com.facebook.ads.redexgen.X.C0P
    public final void J(C01360p nativeAdapter) {
        this.B.r(nativeAdapter, true);
        if (this.B.D != null && nativeAdapter.J() != null) {
            K1 k1 = new K1(this);
            Iterator<KF> it = nativeAdapter.J().iterator();
            while (it.hasNext()) {
                it.next().u(k1);
            }
        }
    }
}
