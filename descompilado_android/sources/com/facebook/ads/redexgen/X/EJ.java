package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class EJ extends E8 {
    public final /* synthetic */ EI B;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EJ(EI ei, Context mContext) {
        super(mContext);
        this.B = ei;
    }

    @Override // com.facebook.ads.redexgen.X.E8
    public final int P(View view, int snapPreference) {
        AbstractC0481Dw abstractC0481DwB = B();
        if (!abstractC0481DwB.G()) {
            return 0;
        }
        C0479Du params = (C0479Du) view.getLayoutParams();
        int iO = O(abstractC0481DwB.g(view) - params.leftMargin, abstractC0481DwB.j(view) + params.rightMargin, abstractC0481DwB.t(), abstractC0481DwB.z() - abstractC0481DwB.u(), snapPreference);
        int end = this.B.H;
        return iO + end;
    }

    @Override // com.facebook.ads.redexgen.X.E8
    public final float Q(DisplayMetrics displayMetrics) {
        return this.B.E / displayMetrics.densityDpi;
    }

    @Override // com.facebook.ads.redexgen.X.E8
    public final PointF T(int targetPosition) {
        return this.B.jB(targetPosition);
    }

    @Override // com.facebook.ads.redexgen.X.E8
    public final int U() {
        return -1;
    }
}
