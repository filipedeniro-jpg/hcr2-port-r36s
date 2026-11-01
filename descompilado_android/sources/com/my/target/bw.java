package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: IconButton.java */
/* JADX INFO: loaded from: classes2.dex */
public class bw extends View {
    private static final int iK = 10;
    private final float density;

    @NonNull
    private final Paint iL;

    @NonNull
    private final ColorFilter iM;

    @Nullable
    private Bitmap iN;
    private int iO;
    private int iP;
    private final int padding;

    @NonNull
    private final Rect rect;

    public bw(@NonNull Context context) {
        super(context);
        this.iL = new Paint();
        this.iL.setFilterBitmap(true);
        this.density = context.getResources().getDisplayMetrics().density;
        this.padding = ck.a(10, context);
        this.rect = new Rect();
        this.iM = new LightingColorFilter(-3355444, 1);
    }

    public int getPadding() {
        return this.padding;
    }

    public void a(@Nullable Bitmap bitmap, boolean z) {
        this.iN = bitmap;
        Bitmap bitmap2 = this.iN;
        if (bitmap2 == null) {
            this.iP = 0;
            this.iO = 0;
        } else if (z) {
            float f = this.density > 1.0f ? 2.0f : 1.0f;
            this.iP = (int) ((this.iN.getHeight() / f) * this.density);
            this.iO = (int) ((this.iN.getWidth() / f) * this.density);
        } else {
            this.iO = bitmap2.getWidth();
            this.iP = this.iN.getHeight();
        }
        int i = this.iO;
        int i2 = this.padding;
        setMeasuredDimension(i + (i2 * 2), this.iP + (i2 * 2));
        requestLayout();
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.iL.setColorFilter(this.iM);
            invalidate();
            return true;
        }
        if (action != 1) {
            if (action != 3) {
                return super.onTouchEvent(motionEvent);
            }
        } else if (motionEvent.getX() >= 0.0f && motionEvent.getX() <= getMeasuredWidth() && motionEvent.getY() >= 0.0f && motionEvent.getY() <= getMeasuredHeight()) {
            performClick();
        }
        this.iL.setColorFilter(null);
        invalidate();
        return true;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = this.iN;
        if (bitmap != null) {
            Rect rect = this.rect;
            int i = this.padding;
            rect.left = i;
            rect.top = i;
            rect.right = this.iO + i;
            rect.bottom = this.iP + i;
            canvas.drawBitmap(bitmap, (Rect) null, rect, this.iL);
        }
    }
}
