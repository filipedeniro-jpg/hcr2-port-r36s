package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class L1 implements LA {
    public final LA B;
    public final LB C;

    public L1(LA output, int beforeSize, int afterSize) {
        this.B = output;
        this.C = new LB(beforeSize, afterSize);
    }

    @Override // com.facebook.ads.redexgen.X.LA
    public final void aF(String line) {
        this.C.A(line);
        if (this.C.D() != null && L0.I(this.C)) {
            this.B.aF(this.C.D());
        }
    }

    @Override // com.facebook.ads.redexgen.X.LA
    public final void flush() {
        this.C.E();
        while (this.C.D() != null) {
            if (L0.I(this.C)) {
                this.B.aF(this.C.D());
            }
            this.C.E();
        }
    }
}
