package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Eb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0486Eb implements EZ {
    public final /* synthetic */ AbstractC0481Dw B;

    public C0486Eb(AbstractC0481Dw this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.EZ
    public final int FC() {
        return this.B.m() - this.B.s();
    }

    @Override // com.facebook.ads.redexgen.X.EZ
    public final int GC() {
        return this.B.v();
    }

    @Override // com.facebook.ads.redexgen.X.EZ
    public final View vB(int index) {
        return this.B.a(index);
    }

    @Override // com.facebook.ads.redexgen.X.EZ
    public final int xB(View view) {
        return this.B.e(view) + ((C0479Du) view.getLayoutParams()).bottomMargin;
    }

    @Override // com.facebook.ads.redexgen.X.EZ
    public final int yB(View view) {
        return this.B.k(view) - ((C0479Du) view.getLayoutParams()).topMargin;
    }
}
