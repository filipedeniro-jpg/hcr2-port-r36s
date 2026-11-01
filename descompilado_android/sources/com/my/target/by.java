package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;

/* JADX INFO: compiled from: SmartImageView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"AppCompatCustomView"})
public class by extends ImageView {

    @Nullable
    private Bitmap bitmap;
    private int iW;
    private int iX;
    private int maxHeight;
    private int maxWidth;

    public by(Context context) {
        super(context);
        bb();
    }

    public by(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        bb();
    }

    public by(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        bb();
    }

    public void setImageData(@Nullable ImageData imageData) {
        if (imageData == null) {
            this.iW = 0;
            this.iX = 0;
            setImageBitmap(null);
        } else {
            this.iW = imageData.getHeight();
            this.iX = imageData.getWidth();
            setImageBitmap(imageData.getBitmap());
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(@Nullable Bitmap bitmap) {
        this.bitmap = bitmap;
        super.setImageBitmap(bitmap);
    }

    public void b(@Nullable Bitmap bitmap, boolean z) {
        if (z) {
            setAlpha(0.0f);
            setImageBitmap(bitmap);
            animate().alpha(1.0f).setDuration(300L);
            return;
        }
        setImageBitmap(bitmap);
    }

    public void setPlaceholderHeight(int i) {
        this.iW = i;
    }

    public void setPlaceholderWidth(int i) {
        this.iX = i;
    }

    @Override // android.widget.ImageView
    public void setMaxWidth(int i) {
        this.maxWidth = i;
    }

    @Override // android.widget.ImageView
    public void setMaxHeight(int i) {
        this.maxHeight = i;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        int width;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int height = this.iW;
        if (height == 0 || (width = this.iX) == 0) {
            Bitmap bitmap = this.bitmap;
            if (bitmap != null) {
                width = bitmap.getWidth();
                height = this.bitmap.getHeight();
            } else {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
                return;
            }
        }
        if (width <= 0 || height <= 0) {
            super.onMeasure(i, i2);
            return;
        }
        float f = width;
        float f2 = height;
        float f3 = f / f2;
        int i3 = this.maxHeight;
        if (i3 > 0) {
            size2 = Math.min(i3, size2);
        }
        int i4 = this.maxWidth;
        if (i4 > 0) {
            size = Math.min(i4, size);
        }
        if (mode == 1073741824 && mode2 == 1073741824) {
            setMeasuredDimension(size, size2);
            return;
        }
        if (mode != 0 || mode2 != 0) {
            if (mode == 0) {
                width = (int) (size2 * f3);
            } else {
                if (mode2 == 0) {
                    height = (int) (size / f3);
                } else {
                    float f4 = size;
                    float f5 = f4 / f;
                    float f6 = size2;
                    if (Math.min(f5, f6 / f2) != f5 || f3 <= 0.0f) {
                        width = (int) (f6 * f3);
                    } else {
                        height = (int) (f4 / f3);
                    }
                }
                width = size;
            }
            height = size2;
        }
        setMeasuredDimension(width, height);
    }

    private void bb() {
        setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        setAdjustViewBounds(true);
    }
}
