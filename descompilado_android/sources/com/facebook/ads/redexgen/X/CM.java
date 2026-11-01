package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class CM extends CN {

    @Nullable
    private ImageView.ScaleType B;

    public CM(Context context) {
        super(context);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = View.MeasureSpec.getSize(heightMeasureSpec);
        int size2 = View.MeasureSpec.getSize(widthMeasureSpec);
        if (View.MeasureSpec.getMode(widthMeasureSpec) == 1073741824 && View.MeasureSpec.getMode(heightMeasureSpec) == 1073741824) {
            int iMin = Math.min(size2, size);
            setMeasuredDimension(iMin, iMin);
        } else if (View.MeasureSpec.getMode(widthMeasureSpec) == 1073741824) {
            if (size > 0) {
                size2 = Math.min(size2, size);
            }
            setMeasuredDimension(size2, size2);
        } else if (View.MeasureSpec.getMode(heightMeasureSpec) == 1073741824) {
            if (size2 > 0) {
                size = Math.min(size2, size);
            }
            setMeasuredDimension(size, size);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        if (this.B != null) {
            super.setScaleType(this.B);
        }
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType mScaleType) {
        this.B = mScaleType;
    }
}
