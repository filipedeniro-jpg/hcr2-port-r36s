package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class CK extends BS {
    public final /* synthetic */ CJ B;

    private CK(CJ cj) {
        this.B = cj;
    }

    @Override // com.facebook.ads.redexgen.X.BS
    public final int B(View child, int top, int dy) {
        int paddingTop = this.B.getPaddingTop();
        return Math.min(Math.max(top, paddingTop), this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.BS
    public final int E(View child) {
        return this.B.J;
    }

    @Override // com.facebook.ads.redexgen.X.BS
    public final void J(int state) {
        if (state == this.B.E) {
            return;
        }
        if (state == 0 && (this.B.E == 1 || this.B.E == 2)) {
            if (this.B.D == this.B.C) {
                this.B.M();
            } else if (this.B.D == this.B.J) {
                this.B.N();
            }
        }
        this.B.E = state;
    }

    @Override // com.facebook.ads.redexgen.X.BS
    public final void K(View changedView, int left, int top, int dx, int dy) {
        this.B.D = top;
    }

    @Override // com.facebook.ads.redexgen.X.BS
    public final void L(View releasedChild, float xvel, float yvel) {
        int i;
        if (this.B.D != this.B.C) {
            if (this.B.D == this.B.J) {
                this.B.F = true;
                return;
            }
            boolean z = false;
            if (yvel > 800.0d) {
                z = true;
            } else if (yvel >= -800.0d) {
                if (this.B.D <= this.B.J / 2) {
                    if (this.B.D < this.B.J / 2) {
                        z = false;
                    }
                } else {
                    z = true;
                }
            } else {
                z = false;
            }
            if (!z) {
                i = this.B.C;
            } else {
                i = this.B.J;
            }
            if (!this.B.B.D(0, i)) {
                return;
            }
            C9Y.J(this.B);
            return;
        }
        this.B.F = false;
    }

    @Override // com.facebook.ads.redexgen.X.BS
    public final boolean M(View view, int i) {
        return view == this.B.H;
    }
}
