package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FE extends GK {
    public final /* synthetic */ C0510Ez B;

    public FE(C0510Ez this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass76 event) {
        if (this.B.S == null || this.B.R == 0 || !this.B.O.isShown() || this.B.E) {
            return;
        }
        this.B.E(true);
        this.B.S.getEventBus().D(this.B.K, this.B.J);
    }
}
