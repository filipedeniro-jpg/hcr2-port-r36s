package com.facebook.ads.redexgen.X;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Keep;
import android.view.View;
import android.view.animation.LinearInterpolator;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Az, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0406Az extends View {
    private final Paint B;
    private boolean C;
    private final Paint D;
    private float E;
    private final RectF F;
    private final float G;

    public C0406Az(Context context) {
        super(context);
        this.G = 3.0f * Resources.getSystem().getDisplayMetrics().density;
        this.E = 0.0f;
        this.C = true;
        this.F = new RectF();
        this.B = new Paint(1);
        this.B.setStyle(Paint.Style.STROKE);
        this.B.setStrokeWidth(this.G);
        this.D = new Paint(1);
        this.D.setStyle(Paint.Style.STROKE);
        this.D.setStrokeWidth(this.G);
    }

    public final void A(int backgroungColor, int foregroundColor) {
        this.B.setColor(backgroungColor);
        this.D.setColor(foregroundColor);
    }

    @Keep
    public float getProgress() {
        return this.E;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.F, 0.0f, 360.0f, false, this.B);
        canvas.drawArc(this.F, -90.0f, this.C ? 360.0f * (this.E / 100.0f) : 360.0f * ((100.0f - this.E) / 100.0f), false, this.D);
    }

    @Override // android.view.View
    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int iMin = Math.min(getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec), getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec));
        setMeasuredDimension(iMin, iMin);
        this.F.set((this.G / 2.0f) + 0.0f + getPaddingLeft(), (this.G / 2.0f) + 0.0f + getPaddingTop(), (iMin - (this.G / 2.0f)) - getPaddingRight(), (iMin - (this.G / 2.0f)) - getPaddingBottom());
    }

    public void setFillUp(boolean fillUp) {
        this.C = fillUp;
    }

    @Keep
    public void setProgress(float progress) {
        this.E = Math.min(progress, 100.0f);
        postInvalidate();
    }

    public void setProgressWithAnimation(float progress) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "progress", progress);
        objectAnimatorOfFloat.setDuration(400L);
        objectAnimatorOfFloat.setInterpolator(new LinearInterpolator());
        objectAnimatorOfFloat.start();
    }
}
