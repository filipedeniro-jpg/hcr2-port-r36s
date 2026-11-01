package net.hockeyapp.android.views;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class AttachmentListView extends ViewGroup {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int line_height;

    public AttachmentListView(Context context) {
        super(context);
    }

    public ArrayList<Uri> getAttachments() {
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            arrayList.add(((AttachmentView) getChildAt(i)).getAttachmentUri());
        }
        return arrayList;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int paddingBottom;
        int size = View.MeasureSpec.getSize(i);
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i4 = 0;
        int paddingLeft2 = paddingLeft;
        int iMax = 0;
        int i5 = 0;
        while (i4 < childCount) {
            View childAt = getChildAt(i4);
            AttachmentView attachmentView = (AttachmentView) childAt;
            int paddingTop2 = attachmentView.getPaddingTop() + attachmentView.getEffectiveMaxHeight();
            if (childAt.getVisibility() != 8) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                childAt.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(paddingTop2, Integer.MIN_VALUE));
                int measuredWidth = childAt.getMeasuredWidth();
                iMax = Math.max(iMax, childAt.getMeasuredHeight() + layoutParams.height);
                if (paddingLeft2 + measuredWidth > size) {
                    paddingLeft2 = getPaddingLeft();
                    paddingTop += iMax;
                }
                paddingLeft2 += measuredWidth + layoutParams.width;
            }
            i4++;
            i5 = paddingTop2;
        }
        this.line_height = iMax;
        if (View.MeasureSpec.getMode(i2) == 0) {
            i3 = paddingTop + iMax;
            paddingBottom = getPaddingBottom();
        } else {
            if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
                i3 = paddingTop + iMax;
                if (getPaddingBottom() + i3 < i5) {
                    paddingBottom = getPaddingBottom();
                }
            }
            setMeasuredDimension(size, i5);
        }
        i5 = i3 + paddingBottom;
        setMeasuredDimension(size, i5);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.LayoutParams(1, 1);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.LayoutParams;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                childAt.invalidate();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (paddingLeft + measuredWidth > i5) {
                    paddingLeft = getPaddingLeft();
                    paddingTop += this.line_height;
                }
                childAt.layout(paddingLeft, paddingTop, paddingLeft + measuredWidth, measuredHeight + paddingTop);
                paddingLeft += measuredWidth + layoutParams.width + ((AttachmentView) childAt).getGap();
            }
        }
    }
}
