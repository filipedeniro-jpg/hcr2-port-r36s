package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class CF extends C0447Co {
    public CF(Context context) {
        super(context);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    public final void onMeasure(int newWidthSpec, int heightMeasureSpec) {
        if (View.MeasureSpec.getMode(newWidthSpec) == 1073741824) {
            heightMeasureSpec = newWidthSpec;
        } else if (View.MeasureSpec.getMode(heightMeasureSpec) == 1073741824) {
            newWidthSpec = heightMeasureSpec;
        }
        super.onMeasure(newWidthSpec, heightMeasureSpec);
    }
}
