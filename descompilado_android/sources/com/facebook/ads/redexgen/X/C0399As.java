package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.widget.ImageView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.As, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0399As extends ImageView {
    private final int B;
    private final int C;
    private float D;
    private final Bitmap E;

    public C0399As(Context context, int backgroundColor, int fillInColor) {
        super(context);
        this.E = C0605Ir.E(EnumC0606Is.RATINGS);
        this.D = 1.0f;
        this.B = backgroundColor;
        this.C = fillInColor;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(2);
        paint.setColorFilter(new PorterDuffColorFilter(this.B, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(this.E, (Rect) null, new Rect(0, 0, getWidth(), getHeight()), paint);
        paint.setColorFilter(new PorterDuffColorFilter(this.C, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(this.E, new Rect(0, 0, (int) (this.D * this.E.getWidth()), this.E.getHeight()), new Rect(0, 0, (int) (this.D * getWidth()), getHeight()), paint);
    }

    public void setFillRatio(float ratio) {
        this.D = ratio;
        postInvalidate();
    }
}
