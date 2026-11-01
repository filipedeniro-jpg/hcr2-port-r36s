package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class GF extends GE {
    public final /* synthetic */ GQ B;

    public GF(GQ this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass71 event) {
        if (this.B.B != null) {
            ((KF) this.B.B.getInternalNativeAd()).Q(true, true);
        }
        this.B.F.onPlayed();
    }
}
