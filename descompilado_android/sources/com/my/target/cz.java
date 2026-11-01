package com.my.target;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.View;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: VideoProgressWheelNative.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cz extends View {

    @NonNull
    private final Paint aC;

    @NonNull
    private final Paint aD;

    @NonNull
    private final Paint aE;

    @NonNull
    private RectF aF;
    private long aG;
    private float aH;
    private float aI;
    private float aJ;
    private boolean aK;
    private int aL;

    @NonNull
    private final ck uiUtils;

    public cz(@NonNull Context context) {
        super(context);
        this.aC = new Paint();
        this.aD = new Paint();
        this.aE = new Paint();
        this.aF = new RectF();
        this.aG = 0L;
        this.aH = 0.0f;
        this.aI = 0.0f;
        this.aJ = 230.0f;
        this.aK = false;
        this.uiUtils = ck.x(context);
    }

    public final void setDigit(int i) {
        this.aL = i;
    }

    public final void setProgress(float f) {
        if (this.aK) {
            this.aH = 0.0f;
            this.aK = false;
        }
        if (f > 1.0f) {
            f = 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        float f2 = this.aI;
        if (f == f2) {
            return;
        }
        if (this.aH == f2) {
            this.aG = SystemClock.uptimeMillis();
        }
        this.aI = Math.min(f * 360.0f, 360.0f);
        invalidate();
    }

    public final void setMax(float f) {
        if (f > 0.0f) {
            this.aJ = 360.0f / f;
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int iL = this.uiUtils.l(28) + getPaddingLeft() + getPaddingRight();
        int iL2 = this.uiUtils.l(28) + getPaddingTop() + getPaddingBottom();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            iL = size;
        } else if (mode == Integer.MIN_VALUE) {
            iL = Math.min(iL, size);
        }
        if (mode2 == 1073741824 || mode == 1073741824) {
            iL2 = size2;
        } else if (mode2 == Integer.MIN_VALUE) {
            iL2 = Math.min(iL2, size2);
        }
        setMeasuredDimension(iL, iL2);
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.aF = new RectF(getPaddingLeft() + this.uiUtils.l(1), getPaddingTop() + this.uiUtils.l(1), (i - getPaddingRight()) - this.uiUtils.l(1), (i2 - getPaddingBottom()) - this.uiUtils.l(1));
        this.aC.setColor(-1);
        this.aC.setAntiAlias(true);
        this.aC.setStyle(Paint.Style.STROKE);
        this.aC.setStrokeWidth(this.uiUtils.l(1));
        this.aD.setColor(-2013265920);
        this.aD.setAntiAlias(true);
        this.aD.setStyle(Paint.Style.FILL);
        this.aD.setStrokeWidth(this.uiUtils.l(4));
        invalidate();
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        boolean z;
        super.onDraw(canvas);
        canvas.drawOval(this.aF, this.aD);
        if (this.aH != this.aI) {
            this.aH = Math.min(this.aH + (((SystemClock.uptimeMillis() - this.aG) / 1000.0f) * this.aJ), this.aI);
            this.aG = SystemClock.uptimeMillis();
            z = true;
        } else {
            z = false;
        }
        canvas.drawArc(this.aF, -90.0f, isInEditMode() ? 360.0f : this.aH, false, this.aC);
        this.aE.setColor(-1);
        this.aE.setTextSize(this.uiUtils.l(12));
        this.aE.setTextAlign(Paint.Align.CENTER);
        this.aE.setAntiAlias(true);
        canvas.drawText(String.valueOf(this.aL), (int) this.aF.centerX(), (int) (this.aF.centerY() - ((this.aE.descent() + this.aE.ascent()) / 2.0f)), this.aE);
        if (z) {
            invalidate();
        }
    }

    @Override // android.view.View
    protected final void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            this.aG = SystemClock.uptimeMillis();
        }
    }
}
