package com.my.target;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: SliderLayoutManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class et extends LinearLayoutManager {
    private final int dL;
    private int dM;
    private int dN;

    et(Context context) {
        super(context, 0, false);
        this.dL = ck.x(context).l(16);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void measureChildWithMargins(View view, int i, int i2) {
        int i3;
        int i4;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int width = getWidth();
        int height = getHeight();
        if (height == 0 || width == 0 || this.dN == 0 || this.dM == 0) {
            return;
        }
        float f = width < height ? 0.125f : 0.05f;
        int paddingRight = getPaddingRight();
        int paddingLeft = getPaddingLeft();
        float f2 = width;
        float f3 = f2 / height;
        int i5 = this.dM;
        int i6 = this.dL;
        int i7 = this.dN;
        if (f3 > ((i6 * 2) + i5) / i7) {
            i3 = ((int) ((height * i5) / i7)) - (i6 * 2);
            i4 = ((int) (f2 - (((width + i3) / 2) + (f * f2)))) / 2;
        } else {
            i3 = (((int) ((1.0f - (f * 2.0f)) * f2)) - paddingRight) - paddingLeft;
            i4 = 0;
        }
        if (getItemViewType(view) == 1) {
            layoutParams.leftMargin = (int) ((((f2 - paddingLeft) - paddingRight) - i3) / 2.0f);
            layoutParams.rightMargin = i4;
        } else if (getItemViewType(view) == 2) {
            layoutParams.rightMargin = (int) ((((f2 - paddingLeft) - paddingRight) - i3) / 2.0f);
            layoutParams.leftMargin = i4;
        } else {
            layoutParams.leftMargin = i4;
            layoutParams.rightMargin = i4;
        }
        view.measure(getChildMeasureSpec(width, getWidthMode(), paddingLeft + paddingRight + layoutParams.leftMargin + layoutParams.rightMargin + i, i3, canScrollHorizontally()), getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin + i2, layoutParams.height, canScrollVertically()));
    }

    final void c(int i, int i2) {
        this.dM = i;
        this.dN = i2;
    }
}
