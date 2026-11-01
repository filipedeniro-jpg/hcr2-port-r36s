package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class CH extends TextView {
    private float B;
    private float C;

    public CH(Context context) {
        super(context);
        this.C = 8.0f;
        super.setSingleLine();
        super.setMaxLines(1);
        this.B = getTextSize() / J6.B;
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int i = right - left;
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        for (float f = this.B; f >= this.C; f -= 0.5f) {
            super.setTextSize(f);
            measure(0, 0);
            if (getMeasuredWidth() <= i) {
                break;
            }
        }
        if (getMeasuredWidth() > i) {
            measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int maxLines) {
    }

    public void setMinTextSize(float mMinTextSize) {
        if (mMinTextSize <= this.B) {
            this.C = mMinTextSize;
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean singleLine) {
    }

    @Override // android.widget.TextView
    public void setTextSize(float textSize) {
        this.B = textSize;
        super.setTextSize(textSize);
    }
}
