package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DB implements InterfaceC0528Fr {
    public final /* synthetic */ D1 B;

    public DB(D1 this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0528Fr
    public final boolean QC() {
        if (this.B.H()) {
            return true;
        }
        boolean zA = false;
        if (this.B.V != null) {
            zA = this.B.V.A();
        }
        if (this.B.G != null) {
            zA = zA || this.B.G.A();
        }
        if (zA || !((AbstractC0466Dh) this.B).E.F()) {
            return true;
        }
        if (!this.B.D() || this.B.B == null) {
            return false;
        }
        this.B.G(this.B.B);
        return true;
    }
}
