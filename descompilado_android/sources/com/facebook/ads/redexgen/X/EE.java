package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class EE extends ED {
    public EE(AbstractC0481Dw layoutManager) {
        super(layoutManager, null);
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int A(View view) {
        return this.B.j(view) + ((C0479Du) view.getLayoutParams()).rightMargin;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int B(View view) {
        C0479Du c0479Du = (C0479Du) view.getLayoutParams();
        return this.B.i(view) + c0479Du.leftMargin + c0479Du.rightMargin;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int C(View view) {
        C0479Du c0479Du = (C0479Du) view.getLayoutParams();
        return this.B.h(view) + c0479Du.topMargin + c0479Du.bottomMargin;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int D(View view) {
        return this.B.g(view) - ((C0479Du) view.getLayoutParams()).leftMargin;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int E() {
        return this.B.z();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int F() {
        return this.B.z() - this.B.u();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int G() {
        return this.B.u();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int H() {
        return this.B.AA();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int J() {
        return this.B.t();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int K() {
        return (this.B.z() - this.B.t()) - this.B.u();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int M(View view) {
        this.B.y(view, true, this.C);
        return this.C.right;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int N(View view) {
        this.B.y(view, true, this.C);
        return this.C.left;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final void O(int amount) {
        this.B.IA(amount);
    }
}
