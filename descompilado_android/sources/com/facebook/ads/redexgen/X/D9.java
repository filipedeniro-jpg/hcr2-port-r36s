package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class D9 extends GE {
    public final /* synthetic */ D1 B;

    public D9(D1 this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass71 event) {
        if (!this.B.b) {
            this.B.a.F();
            this.B.b = true;
        }
        if (this.B.getAudienceNetworkListener() != null) {
            this.B.getAudienceNetworkListener().bB("videoInterstitalEvent", event);
        }
        if (this.B.G != null) {
            this.B.G.K(event);
        }
    }
}
