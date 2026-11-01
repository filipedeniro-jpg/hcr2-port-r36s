package com.my.target;

import android.content.Context;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: CarouselLayoutManager.java */
/* JADX INFO: loaded from: classes2.dex */
final class en extends LinearLayoutManager {
    private final int aK;
    private int cT;

    @Nullable
    private a cU;
    private int cV;
    private int cW;
    private int cX;
    private int cY;

    /* JADX INFO: compiled from: CarouselLayoutManager.java */
    public interface a {
        void onLayoutCompleted();
    }

    en(Context context) {
        super(context, 0, false);
        this.aK = ck.x(context).l(4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void measureChildWithMargins(View view, int i, int i2) {
        int height = getHeight();
        int width = getWidth();
        if (height != this.cY || width != this.cX || this.cV <= 0 || this.cW <= 0) {
            view.measure(View.MeasureSpec.makeMeasureSpec(getWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getHeight(), Integer.MIN_VALUE));
            float width2 = getWidth() / view.getMeasuredWidth();
            if (width2 > 1.0f) {
                double d = width;
                double dFloor = Math.floor(width2) + 0.5d;
                Double.isNaN(d);
                this.cV = (int) (d / dFloor);
            } else {
                this.cV = (int) (width / 1.5f);
            }
            this.cW = height;
            this.cX = width;
            this.cY = height;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (view != getChildAt(0)) {
            layoutParams.leftMargin = ck.a(this.cT / 2, view.getContext());
        }
        if (view != getChildAt(getChildCount())) {
            layoutParams.rightMargin = ck.a(this.cT / 2, view.getContext());
        }
        int childMeasureSpec = getChildMeasureSpec(width, getWidthMode(), 0, this.cV, canScrollHorizontally());
        int heightMode = getHeightMode();
        int i3 = this.aK;
        view.measure(childMeasureSpec, getChildMeasureSpec(height, heightMode, i3, height - (i3 * 2), canScrollVertically()));
    }

    public final void a(@Nullable a aVar) {
        this.cU = aVar;
    }

    public final void f(int i) {
        this.cT = i;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        a aVar = this.cU;
        if (aVar != null) {
            aVar.onLayoutCompleted();
        }
    }
}
