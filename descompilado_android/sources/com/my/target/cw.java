package com.my.target;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: CardRecyclerLayoutManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cw extends LinearLayoutManager {

    /* JADX INFO: compiled from: CardRecyclerLayoutManager.java */
    public interface a {
        void onLayoutCompleted();
    }

    public cw(@NonNull Context context) {
        super(context, 0, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void measureChildWithMargins(View view, int i, int i2) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        view.measure(LinearLayoutManager.getChildMeasureSpec((int) (getWidth() * 0.75f), getWidthMode(), getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin + i, layoutParams.width, canScrollHorizontally()), LinearLayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin + i2, layoutParams.height, canScrollVertically()));
    }
}
