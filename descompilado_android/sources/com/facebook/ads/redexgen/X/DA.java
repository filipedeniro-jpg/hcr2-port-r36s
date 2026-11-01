package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DA extends GO {
    public final /* synthetic */ D1 B;

    public DA(D1 this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass75 event) {
        if (this.B.getAudienceNetworkListener() != null) {
            this.B.getAudienceNetworkListener().bB("videoInterstitalEvent", event);
        }
        if (this.B.G != null) {
            this.B.G.I(event);
        }
        if (!this.B.K) {
            this.B.Z.P();
            this.B.Z.B();
            this.B.K = true;
        }
        if (this.B.B != null) {
            this.B.B.finish();
        }
    }
}
