package com.facebook.ads.redexgen.X;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8h, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03368h {
    public final int[] A(View view, int widthSpec, int heightSpec) {
        C0479Du c0479Du = (C0479Du) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(widthSpec, view.getPaddingLeft() + view.getPaddingRight(), c0479Du.width), ViewGroup.getChildMeasureSpec(heightSpec, view.getPaddingTop() + view.getPaddingBottom(), c0479Du.height));
        return new int[]{view.getMeasuredWidth() + c0479Du.leftMargin + c0479Du.rightMargin, view.getMeasuredHeight() + c0479Du.bottomMargin + c0479Du.topMargin};
    }
}
