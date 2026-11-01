package com.facebook.ads.redexgen.X;

import android.graphics.Canvas;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FS extends FR {
    private float B(C0503Es recyclerView, View itemView) {
        int i = recyclerView.getChildCount();
        float max = 0.0f;
        for (int i2 = 0; i2 < i; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (childAt != itemView) {
                float fT = C9Y.T(childAt);
                if (fT > max) {
                    max = fT;
                }
            }
        }
        return max;
    }

    @Override // com.facebook.ads.redexgen.X.FR, com.facebook.ads.redexgen.X.FQ
    public final void fB(View view) {
        Object tag = view.getTag(AnonymousClass34.item_touch_helper_previous_elevation);
        if (tag != null && (tag instanceof Float)) {
            C9Y.E(view, ((Float) tag).floatValue());
        }
        view.setTag(AnonymousClass34.item_touch_helper_previous_elevation, null);
        super.fB(view);
    }

    @Override // com.facebook.ads.redexgen.X.FR, com.facebook.ads.redexgen.X.FQ
    public final void uD(Canvas c, C0503Es recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (isCurrentlyActive && view.getTag(AnonymousClass34.item_touch_helper_previous_elevation) == null) {
            Float fValueOf = Float.valueOf(C9Y.T(view));
            C9Y.E(view, 1.0f + B(recyclerView, view));
            view.setTag(AnonymousClass34.item_touch_helper_previous_elevation, fValueOf);
        }
        super.uD(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive);
    }
}
