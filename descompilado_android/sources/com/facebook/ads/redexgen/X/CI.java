package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class CI extends TextView {
    private int B;
    private float C;
    private float D;

    public CI(Context context, int maxLines) {
        super(context);
        this.D = 8.0f;
        setMaxLines(maxLines);
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.setMaxLines(this.B + 1);
        super.setTextSize(this.C);
        int i = right - left;
        int i2 = bottom - top;
        measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(i2, 0));
        if (getMeasuredHeight() > i2) {
            float f = this.C;
            while (f > this.D) {
                f -= 0.5f;
                super.setTextSize(f);
                measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), 0);
                if (getMeasuredHeight() <= i2 && getLineCount() <= this.B) {
                    break;
                }
            }
        }
        super.setMaxLines(this.B);
        setMeasuredDimension(i, i2);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int maxLines) {
        this.B = maxLines;
        super.setMaxLines(maxLines);
    }

    public void setMinTextSize(float mMinTextSize) {
        this.D = mMinTextSize;
    }

    @Override // android.widget.TextView
    public void setTextSize(float textSize) {
        this.C = textSize;
        super.setTextSize(textSize);
    }
}
