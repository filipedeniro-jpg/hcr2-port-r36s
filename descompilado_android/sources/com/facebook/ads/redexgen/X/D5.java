package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class D5 extends GC {
    public final /* synthetic */ D1 B;

    public D5(D1 this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass70 event) {
        this.B.L = true;
        if (!this.B.K) {
            this.B.H.set(this.B.Z.D());
            this.B.X();
        }
        if (this.B.getAudienceNetworkListener() != null) {
            this.B.getAudienceNetworkListener().bB("videoInterstitalEvent", event);
        }
        if (this.B.G != null) {
            this.B.G.L(event);
        }
        if (!this.B.H() && this.B.Z.isShown()) {
            this.B.T();
        } else {
            this.B.setOnAdShownListener(this.B.N);
        }
    }
}
