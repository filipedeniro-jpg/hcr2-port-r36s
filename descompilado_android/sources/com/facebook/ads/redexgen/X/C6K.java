package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6K, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C6K extends AbstractC02976u {
    public final /* synthetic */ C6H B;

    public C6K(C6H this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(C02986v event) {
        if (this.B.K != null && event.A().getAction() == 0) {
            this.B.C.removeCallbacksAndMessages(null);
            this.B.N(new C6L(this));
        }
    }
}
