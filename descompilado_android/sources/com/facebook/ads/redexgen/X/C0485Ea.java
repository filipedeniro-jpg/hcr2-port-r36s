package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ea, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0485Ea implements EZ {
    public final /* synthetic */ AbstractC0481Dw B;

    public C0485Ea(AbstractC0481Dw this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.EZ
    public final int FC() {
        return this.B.z() - this.B.u();
    }

    @Override // com.facebook.ads.redexgen.X.EZ
    public final int GC() {
        return this.B.t();
    }

    @Override // com.facebook.ads.redexgen.X.EZ
    public final View vB(int index) {
        return this.B.a(index);
    }

    @Override // com.facebook.ads.redexgen.X.EZ
    public final int xB(View view) {
        return this.B.j(view) + ((C0479Du) view.getLayoutParams()).rightMargin;
    }

    @Override // com.facebook.ads.redexgen.X.EZ
    public final int yB(View view) {
        return this.B.g(view) - ((C0479Du) view.getLayoutParams()).leftMargin;
    }
}
