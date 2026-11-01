package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ey, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0509Ey extends FO {
    public final /* synthetic */ EU B;

    public C0509Ey(EU this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(C03026z event) {
        if (this.B.P == null) {
            return;
        }
        float fMin = Math.min(this.B.O * 1000.0f, this.B.P.getDuration());
        int remainingSecondsForReward = this.B.getRemainingSecondsForReward();
        this.B.N(this.B.M.A(), remainingSecondsForReward);
        float currentPositionInMillis = this.B.P.getCurrentPositionInMillis() / fMin;
        float total = 100.0f * currentPositionInMillis;
        this.B.setProgress(total);
        if (currentPositionInMillis >= 1.0f) {
            this.B.L.setToolbarActionMode(this.B.K);
            if (remainingSecondsForReward > 0) {
                return;
            }
            this.B.P.getEventBus().D(this.B.H, this.B.G);
        }
    }
}
