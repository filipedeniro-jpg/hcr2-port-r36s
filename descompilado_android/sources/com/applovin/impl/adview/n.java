package com.applovin.impl.adview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public final class n extends f {
    private static final Paint d = new Paint(1);
    private static final Paint e = new Paint(1);
    private float c;

    public n(com.applovin.impl.sdk.h hVar, Context context) {
        super(hVar, context);
        this.c = 1.0f;
        d.setARGB(80, 0, 0, 0);
        e.setColor(-1);
        e.setStyle(Paint.Style.STROKE);
    }

    @Override // com.applovin.impl.adview.f
    public void a(int i) {
        setViewScale(i / 30.0f);
    }

    protected float getCenter() {
        return getSize() / 2.0f;
    }

    protected float getCrossOffset() {
        return this.c * 8.0f;
    }

    protected float getInnerCircleOffset() {
        return this.c * 2.0f;
    }

    protected float getInnerCircleRadius() {
        return getCenter() - getInnerCircleOffset();
    }

    protected float getSize() {
        return this.c * 30.0f;
    }

    protected float getStrokeWidth() {
        return this.c * 2.0f;
    }

    @Override // com.applovin.impl.adview.f
    public f.a getStyle() {
        return f.a.WhiteXOnTransparentGrey;
    }

    @Override // com.applovin.impl.adview.f
    public float getViewScale() {
        return this.c;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float center = getCenter();
        canvas.drawCircle(center, center, center, d);
        float crossOffset = getCrossOffset();
        float size = getSize() - crossOffset;
        e.setStrokeWidth(getStrokeWidth());
        canvas.drawLine(crossOffset, crossOffset, size, size, e);
        canvas.drawLine(crossOffset, size, size, crossOffset, e);
    }

    @Override // com.applovin.impl.adview.f
    public void setViewScale(float f) {
        this.c = f;
    }
}
