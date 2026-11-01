package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
public class x extends bb {
    private Paint a;
    private Paint b;
    private Path c;
    private RectF d;
    private RectF e;
    private int f;
    private float g;
    private float h;

    public x(Context context) {
        super(context);
        this.f = 0;
        a(context);
    }

    private void a(Context context) {
        float f = context.getResources().getDisplayMetrics().density;
        this.g = 4.5f * f;
        this.a = new Paint();
        this.a.setColor(-1);
        this.a.setStyle(Paint.Style.STROKE);
        this.a.setStrokeWidth(f * 1.0f);
        this.a.setAntiAlias(true);
        this.b = new Paint();
        this.b.setColor(-855638017);
        this.b.setStyle(Paint.Style.FILL);
        this.b.setAntiAlias(true);
        this.c = new Path();
        this.e = new RectF();
        this.d = new RectF();
    }

    @Override // com.chartboost.sdk.impl.bb
    protected void a(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.d.set(0.0f, 0.0f, getWidth(), getHeight());
        float fMin = Math.min(1, Math.round(f * 0.5f));
        this.d.inset(fMin, fMin);
        this.c.reset();
        Path path = this.c;
        RectF rectF = this.d;
        float f2 = this.g;
        path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
        canvas.save();
        canvas.clipPath(this.c);
        canvas.drawColor(this.f);
        this.e.set(this.d);
        RectF rectF2 = this.e;
        rectF2.right = ((rectF2.right - this.e.left) * this.h) + this.e.left;
        canvas.drawRect(this.e, this.b);
        canvas.restore();
        RectF rectF3 = this.d;
        float f3 = this.g;
        canvas.drawRoundRect(rectF3, f3, f3, this.a);
    }

    public void a(int i) {
        this.f = i;
        invalidate();
    }

    public void b(int i) {
        this.a.setColor(i);
        invalidate();
    }

    public void c(int i) {
        this.b.setColor(i);
        invalidate();
    }

    public void a(float f) {
        this.h = f;
        if (getVisibility() != 8) {
            invalidate();
        }
    }

    public void b(float f) {
        this.g = f;
    }
}
