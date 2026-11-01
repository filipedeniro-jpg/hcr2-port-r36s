package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class D6 extends GK {
    public final /* synthetic */ D1 B;

    public D6(D1 this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass76 event) {
        this.B.J.set(true);
        if (this.B.getAudienceNetworkListener() != null) {
            this.B.getAudienceNetworkListener().bB("videoInterstitalEvent", event);
        }
        if (this.B.G != null) {
            this.B.G.H(event);
        }
    }
}
