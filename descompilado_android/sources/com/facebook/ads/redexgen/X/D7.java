package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class D7 extends GG {
    public final /* synthetic */ D1 B;

    public D7(D1 this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass72 event) {
        if (this.B.getAudienceNetworkListener() != null) {
            this.B.getAudienceNetworkListener().bB("videoInterstitalEvent", event);
        }
        if (this.B.G != null) {
            this.B.G.J(event);
        }
    }
}
