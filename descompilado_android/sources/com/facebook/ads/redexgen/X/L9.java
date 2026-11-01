package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class L9 implements LA {
    public final LA B;
    public final LA C;
    public int D;

    public L9(LA initialBypass, int bypassCount, LA output) {
        this.B = initialBypass;
        this.D = bypassCount;
        this.C = output;
    }

    @Override // com.facebook.ads.redexgen.X.LA
    public final void aF(String line) {
        if (this.D > 0) {
            this.B.aF(line);
            this.B.flush();
            this.D--;
            return;
        }
        this.C.aF(line);
    }

    @Override // com.facebook.ads.redexgen.X.LA
    public final void flush() {
        this.C.flush();
    }
}
