package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class EF extends ED {
    public EF(AbstractC0481Dw layoutManager) {
        super(layoutManager, null);
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int A(View view) {
        return this.B.e(view) + ((C0479Du) view.getLayoutParams()).bottomMargin;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int B(View view) {
        C0479Du c0479Du = (C0479Du) view.getLayoutParams();
        return this.B.h(view) + c0479Du.topMargin + c0479Du.bottomMargin;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int C(View view) {
        C0479Du c0479Du = (C0479Du) view.getLayoutParams();
        return this.B.i(view) + c0479Du.leftMargin + c0479Du.rightMargin;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int D(View view) {
        return this.B.k(view) - ((C0479Du) view.getLayoutParams()).topMargin;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int E() {
        return this.B.m();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int F() {
        return this.B.m() - this.B.s();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int G() {
        return this.B.s();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int H() {
        return this.B.n();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int J() {
        return this.B.v();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int K() {
        return (this.B.m() - this.B.v()) - this.B.s();
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int M(View view) {
        this.B.y(view, true, this.C);
        return this.C.bottom;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final int N(View view) {
        this.B.y(view, true, this.C);
        return this.C.top;
    }

    @Override // com.facebook.ads.redexgen.X.ED
    public final void O(int amount) {
        this.B.JA(amount);
    }
}
