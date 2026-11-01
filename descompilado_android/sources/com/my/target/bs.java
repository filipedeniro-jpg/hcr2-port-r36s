package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: AdChoicesView.java */
/* JADX INFO: loaded from: classes2.dex */
public class bs extends by {
    private int ie;

    public bs(Context context) {
        super(context);
        this.ie = -1;
    }

    public bs(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.ie = -1;
    }

    public bs(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ie = -1;
    }

    public void setFixedHeight(int i) {
        this.ie = i;
    }

    @Override // com.my.target.by, android.widget.ImageView
    public void setImageBitmap(@Nullable Bitmap bitmap) {
        int paddingTop;
        int width;
        int i = this.ie;
        int height = 0;
        if (i >= 0) {
            paddingTop = (i - getPaddingTop()) - getPaddingBottom();
            if (paddingTop < 0) {
                paddingTop = 0;
            }
        } else {
            paddingTop = -1;
        }
        if (paddingTop >= 0) {
            if (bitmap != null) {
                width = bitmap.getWidth();
                height = bitmap.getHeight();
            } else {
                width = 0;
            }
            setMeasuredDimension(((int) (paddingTop * (height > 0 ? width / height : 0.0f))) + getPaddingLeft() + getPaddingRight(), this.ie);
        }
        super.setImageBitmap(bitmap);
    }

    @Override // com.my.target.by, android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.ie >= 0) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
        } else {
            super.onMeasure(i, i2);
        }
    }
}
