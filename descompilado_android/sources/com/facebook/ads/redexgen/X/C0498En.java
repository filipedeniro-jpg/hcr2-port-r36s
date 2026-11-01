package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.En, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0498En extends GK {
    public final /* synthetic */ EU B;

    public C0498En(EU this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass76 event) {
        if (this.B.P != null && this.B.O != 0 && this.B.L.B.isShown() && this.B.K != 4) {
            this.B.L.setToolbarActionMode(this.B.K);
            this.B.P.getEventBus().D(this.B.H, this.B.G);
        }
    }
}
