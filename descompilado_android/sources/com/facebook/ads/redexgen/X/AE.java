package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class AE implements F1 {
    public final /* synthetic */ A3 B;

    public AE(A3 this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.F1
    public final void jD() {
        if (this.B.Q != null && this.B.Q.G() && !this.B.O) {
            this.B.T();
            return;
        }
        if (!this.B.E.G()) {
            if (this.B.S != null) {
                this.B.S.jD();
            }
        } else {
            new C0618Jf(this.B.B.C(), this.B.getAdEventManager()).A(EnumC0617Je.VIDEO_SKIP_CLICKED, null);
            this.B.setupContentLayoutForEndCard(true);
        }
    }
}
