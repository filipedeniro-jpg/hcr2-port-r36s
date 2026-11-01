package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class L7 implements LA {
    private int B;

    @Nullable
    private String C;
    private final LA D;

    public L7(LA output) {
        this.D = output;
    }

    private void B() {
        if (this.C != null) {
            this.D.aF(this.C + ".. #" + this.B);
            this.C = null;
            this.B = 0;
        }
    }

    @Override // com.facebook.ads.redexgen.X.LA
    public final void aF(String line) {
        if (!L0.J(line)) {
            String strG = L0.G(line);
            if (strG.equals(this.C)) {
                this.B++;
                return;
            }
            B();
            this.C = strG;
            this.B = 1;
            return;
        }
        B();
        this.D.aF(line);
    }

    @Override // com.facebook.ads.redexgen.X.LA
    public final void flush() {
        B();
        this.D.flush();
    }
}
