package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FM extends FO {
    public final /* synthetic */ C0510Ez B;

    public FM(C0510Ez this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(C03026z event) {
        if (this.B.S != null && this.B.R != 0 && this.B.O.isShown()) {
            float currentPositionInMillis = this.B.S.getCurrentPositionInMillis() / Math.min(this.B.R * 1000.0f, this.B.S.getDuration());
            this.B.setProgress(100.0f * currentPositionInMillis);
            if (currentPositionInMillis < 1.0f) {
                return;
            }
            this.B.E(true);
            this.B.S.getEventBus().D(this.B.K, this.B.J);
        }
    }
}
