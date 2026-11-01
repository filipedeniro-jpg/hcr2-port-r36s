package com.my.target;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.View;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: VideoProgressWheelInterstitial.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eb extends View {

    @NonNull
    private final ck aC;

    @NonNull
    private final Paint aL;

    @NonNull
    private final Paint aM;

    @NonNull
    private final Paint aN;

    @NonNull
    private RectF aO;
    private long aP;
    private float aQ;
    private float aR;
    private float aS;
    private boolean aT;
    private int aU;

    public eb(@NonNull Context context) {
        super(context);
        this.aL = new Paint();
        this.aM = new Paint();
        this.aN = new Paint();
        this.aO = new RectF();
        this.aP = 0L;
        this.aQ = 0.0f;
        this.aR = 0.0f;
        this.aS = 230.0f;
        this.aT = false;
        this.aC = ck.x(context);
    }

    public final void setDigit(int i) {
        this.aU = i;
    }

    public final void setProgress(float f) {
        if (this.aT) {
            this.aQ = 0.0f;
            this.aT = false;
        }
        if (f > 1.0f) {
            f = 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        float f2 = this.aR;
        if (f == f2) {
            return;
        }
        if (this.aQ == f2) {
            this.aP = SystemClock.uptimeMillis();
        }
        this.aR = Math.min(f * 360.0f, 360.0f);
        invalidate();
    }

    public final void setMax(float f) {
        if (f > 0.0f) {
            this.aS = 360.0f / f;
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int iL = this.aC.l(28) + getPaddingLeft() + getPaddingRight();
        int iL2 = this.aC.l(28) + getPaddingTop() + getPaddingBottom();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            iL = Math.min(iL, size);
        } else if (mode == 1073741824) {
            iL = size;
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
        this.aO = new RectF(getPaddingLeft() + this.aC.l(1), getPaddingTop() + this.aC.l(1), (i - getPaddingRight()) - this.aC.l(1), (i2 - getPaddingBottom()) - this.aC.l(1));
        this.aL.setColor(-1);
        this.aL.setAntiAlias(true);
        this.aL.setStyle(Paint.Style.STROKE);
        this.aL.setStrokeWidth(this.aC.l(1));
        this.aM.setColor(-2013265920);
        this.aM.setAntiAlias(true);
        this.aM.setStyle(Paint.Style.FILL);
        this.aM.setStrokeWidth(this.aC.l(4));
        invalidate();
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        boolean z;
        super.onDraw(canvas);
        canvas.drawOval(this.aO, this.aM);
        if (this.aQ != this.aR) {
            this.aQ = Math.min(this.aQ + (((SystemClock.uptimeMillis() - this.aP) / 1000.0f) * this.aS), this.aR);
            this.aP = SystemClock.uptimeMillis();
            z = true;
        } else {
            z = false;
        }
        canvas.drawArc(this.aO, -90.0f, isInEditMode() ? 360.0f : this.aQ, false, this.aL);
        this.aN.setColor(-1);
        this.aN.setTextSize(this.aC.l(12));
        this.aN.setTextAlign(Paint.Align.CENTER);
        this.aN.setAntiAlias(true);
        canvas.drawText(String.valueOf(this.aU), (int) this.aO.centerX(), (int) (this.aO.centerY() - ((this.aN.descent() + this.aN.ascent()) / 2.0f)), this.aN);
        if (z) {
            invalidate();
        }
    }

    @Override // android.view.View
    protected final void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            this.aP = SystemClock.uptimeMillis();
        }
    }
}
