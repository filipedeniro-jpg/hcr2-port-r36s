package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class D0 implements F1 {
    public final /* synthetic */ AbstractC0466Dh B;
    public final /* synthetic */ C0531Fu C;

    public D0(AbstractC0466Dh this$0, C0531Fu c0531Fu) {
        this.B = this$0;
        this.C = c0531Fu;
    }

    @Override // com.facebook.ads.redexgen.X.F1
    public final void jD() {
        this.B.F.A(EnumC0617Je.AD_CLOSE_CLICKED, null);
        if (this.B.E()) {
            this.B.G(this.C);
        } else {
            this.C.finish();
        }
    }
}
