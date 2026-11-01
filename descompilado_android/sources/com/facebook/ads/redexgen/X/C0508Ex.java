package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ex, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0508Ex extends E8 {
    public final /* synthetic */ EA B;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0508Ex(EA this$0, Context context) {
        super(context);
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.E8, com.facebook.ads.redexgen.X.E7
    public final void K(View targetView, C0499Eo state, C0497Em action) {
        int[] iArrC = this.B.C(this.B.B.getLayoutManager(), targetView);
        int i = iArrC[0];
        int i2 = iArrC[1];
        int iR = R(Math.max(Math.abs(i), Math.abs(i2)));
        if (iR > 0) {
            action.D(i, i2, iR, ((E8) this).B);
        }
    }

    @Override // com.facebook.ads.redexgen.X.E8
    public final float Q(DisplayMetrics displayMetrics) {
        return 100.0f / displayMetrics.densityDpi;
    }
}
