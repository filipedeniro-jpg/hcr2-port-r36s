package com.applovin.impl.adview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public final class u extends f {
    private static final Paint d = new Paint(1);
    private static final Paint e = new Paint(1);
    private static final Paint f = new Paint(1);
    private float c;

    public u(com.applovin.impl.sdk.h hVar, Context context) {
        super(hVar, context);
        this.c = 1.0f;
        d.setColor(-1);
        e.setColor(ViewCompat.MEASURED_STATE_MASK);
        f.setColor(-1);
        f.setStyle(Paint.Style.STROKE);
    }

    @Override // com.applovin.impl.adview.f
    public void a(int i) {
        setViewScale(i / 30.0f);
    }

    protected float getCenter() {
        return getSize() / 2.0f;
    }

    protected float getCrossOffset() {
        return this.c * 10.0f;
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
        return this.c * 3.0f;
    }

    @Override // com.applovin.impl.adview.f
    public f.a getStyle() {
        return f.a.WhiteXOnOpaqueBlack;
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
        canvas.drawCircle(center, center, getInnerCircleRadius(), e);
        float crossOffset = getCrossOffset();
        float size = getSize() - crossOffset;
        f.setStrokeWidth(getStrokeWidth());
        canvas.drawLine(crossOffset, crossOffset, size, size, f);
        canvas.drawLine(crossOffset, size, size, crossOffset, f);
    }

    @Override // com.applovin.impl.adview.f
    public void setViewScale(float f2) {
        this.c = f2;
    }
}
