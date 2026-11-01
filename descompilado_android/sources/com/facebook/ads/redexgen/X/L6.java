package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class L6 implements LA {
    public final LA B;
    public int C;

    public L6(LA output, int remaining) {
        this.B = output;
        this.C = remaining;
    }

    @Override // com.facebook.ads.redexgen.X.LA
    public final void aF(String line) {
        if (this.C > 0) {
            this.B.aF(line);
            this.C--;
        }
    }

    @Override // com.facebook.ads.redexgen.X.LA
    public final void flush() {
        this.B.flush();
    }
}
