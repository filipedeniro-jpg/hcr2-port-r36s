package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: TextViewWithAgeView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ea extends ViewGroup {
    private static final int aF = ck.br();
    private static final int aG = ck.br();

    @NonNull
    private final TextView aH;

    @NonNull
    private final bu aI;
    private final int aJ;
    private final int aK;

    public ea(@NonNull Context context) {
        super(context);
        ck ckVarX = ck.x(context);
        this.aH = new TextView(context);
        this.aI = new bu(context);
        this.aH.setId(aF);
        this.aI.setId(aG);
        this.aI.setLines(1);
        this.aH.setTextSize(2, 18.0f);
        this.aH.setEllipsize(TextUtils.TruncateAt.END);
        this.aH.setMaxLines(1);
        this.aH.setTextColor(-1);
        this.aJ = ckVarX.l(4);
        this.aK = ckVarX.l(2);
        ck.a(this.aH, "title_text");
        ck.a(this.aI, "age_bordering");
        addView(this.aH);
        addView(this.aI);
    }

    @NonNull
    public final TextView getLeftText() {
        return this.aH;
    }

    @NonNull
    public final bu getRightBorderedView() {
        return this.aI;
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.aI.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2 - (this.aK * 2), Integer.MIN_VALUE));
        int i3 = size / 2;
        if (this.aI.getMeasuredWidth() > i3) {
            this.aI.measure(View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2 - (this.aK * 2), Integer.MIN_VALUE));
        }
        this.aH.measure(View.MeasureSpec.makeMeasureSpec((size - this.aI.getMeasuredWidth()) - this.aJ, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2 - (this.aK * 2), Integer.MIN_VALUE));
        setMeasuredDimension(this.aH.getMeasuredWidth() + this.aI.getMeasuredWidth() + this.aJ, Math.max(this.aH.getMeasuredHeight(), this.aI.getMeasuredHeight()));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = this.aH.getMeasuredWidth();
        int measuredHeight = this.aH.getMeasuredHeight();
        int measuredWidth2 = this.aI.getMeasuredWidth();
        int measuredHeight2 = this.aI.getMeasuredHeight();
        int measuredHeight3 = getMeasuredHeight();
        int i5 = (measuredHeight3 - measuredHeight) / 2;
        int i6 = (measuredHeight3 - measuredHeight2) / 2;
        int i7 = this.aJ + measuredWidth;
        this.aH.layout(0, i5, measuredWidth, measuredHeight + i5);
        this.aI.layout(i7, i6, measuredWidth2 + i7, measuredHeight2 + i6);
    }
}
