package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ba, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0407Ba extends ViewGroup {
    private static final int C = (int) (8.0f * J6.B);
    private int B;

    public C0407Ba(Context context) {
        super(context);
        setMotionEventSplittingEnabled(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        int yPos = r - l;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (paddingLeft + measuredWidth > yPos) {
                paddingLeft = getPaddingLeft();
                paddingTop += this.B;
            }
            childAt.layout(paddingLeft, paddingTop, paddingLeft + measuredWidth, paddingTop + measuredHeight);
            paddingLeft += C + measuredWidth;
        }
    }

    @Override // android.view.View
    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = (View.MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()) - getPaddingRight();
        int size = View.MeasureSpec.getSize(heightMeasureSpec);
        int width = getPaddingTop();
        int i2 = size - width;
        int width2 = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int iMax = 0;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i2 - width2, Integer.MIN_VALUE);
        int lineHeight = getChildCount();
        int i3 = lineHeight > 0 ? 1 : 0;
        for (int i4 = 0; i4 < lineHeight; i4++) {
            View childAt = getChildAt(i4);
            int lineHeight2 = View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE);
            childAt.measure(lineHeight2, iMakeMeasureSpec);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int lineHeight3 = C;
            iMax = Math.max(iMax, measuredHeight + lineHeight3);
            int lineHeight4 = paddingLeft + measuredWidth;
            if (lineHeight4 > i) {
                i3++;
                paddingLeft = getPaddingLeft();
            }
            int lineHeight5 = C;
            paddingLeft += lineHeight5 + measuredWidth;
        }
        this.B = iMax;
        int i5 = this.B * i3;
        int lineHeight6 = C;
        setMeasuredDimension(i, i5 + lineHeight6);
    }
}
