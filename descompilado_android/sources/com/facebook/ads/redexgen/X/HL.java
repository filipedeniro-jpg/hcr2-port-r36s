package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class HL extends C1B {
    public final /* synthetic */ FV B;

    public HL(FV this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.C1B
    public final void B() {
        if (!this.B.Q.D()) {
            this.B.Q.G();
            for (int i = 0; i < i; i++) {
                if (this.B.I.getChildAt(i) instanceof C03217s) {
                    C03217s c03217s = (C03217s) this.B.I.getChildAt(i);
                    c03217s.A(i);
                    c03217s.setViewability(true);
                }
            }
            if (!this.B.N) {
                this.B.J.E();
            }
        }
    }
}
