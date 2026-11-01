package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class GU extends C03739s {
    private int B;

    public GU(Context context) {
        super(context);
        this.B = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(int height) {
        this.B = height;
    }

    @Override // com.facebook.ads.redexgen.X.C03739s, android.view.View
    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = this.B;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            childAt.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
            int measuredHeight = childAt.getMeasuredHeight();
            if (measuredHeight > i) {
                i = measuredHeight;
            }
        }
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(i, 1073741824));
    }
}
