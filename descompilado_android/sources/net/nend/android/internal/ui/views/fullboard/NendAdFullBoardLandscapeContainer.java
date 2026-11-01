package net.nend.android.internal.ui.views.fullboard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import net.nend.android.internal.utilities.n;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdFullBoardLandscapeContainer extends FrameLayout {
    public NendAdFullBoardLandscapeContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        ImageView imageView = (ImageView) getChildAt(0);
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            super.onMeasure(i, i2);
            return;
        }
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        float fMin = Math.min(size / intrinsicWidth, size2 / intrinsicHeight);
        int i3 = (int) ((intrinsicWidth * fMin) + 0.5f);
        int i4 = (int) ((intrinsicHeight * fMin) + 0.5f);
        setMeasuredDimension(i3, i4);
        imageView.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(i4, 1073741824));
        View childAt = getChildAt(1);
        childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(getResources().getDimensionPixelSize(n.f(getContext(), "nend_full_board_ad_logo_size")) + childAt.getPaddingTop() + childAt.getPaddingBottom(), 1073741824));
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        View childAt = getChildAt(0);
        childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
        View childAt2 = getChildAt(1);
        childAt2.layout(0, (i4 - i2) - childAt2.getMeasuredHeight(), childAt2.getMeasuredWidth(), i4);
    }
}
