package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: assets/audience_network.dex */
@TargetApi(19)
public final class BR extends ProgressBar {
    private static final int D = Color.argb(26, 0, 0, 0);
    private static final int E = Color.rgb(88, 144, 255);
    private Paint B;
    private Rect C;

    public BR(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        B();
    }

    private void B() {
        setIndeterminate(false);
        setMax(100);
        setProgressDrawable(C());
        this.C = new Rect();
        this.B = new Paint();
        this.B.setStyle(Paint.Style.FILL);
        this.B.setColor(D);
    }

    private Drawable C() {
        return new LayerDrawable(new Drawable[]{new ColorDrawable(0), new ClipDrawable(new ColorDrawable(E), 3, 1)});
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final synchronized void onDraw(Canvas canvas) {
        canvas.drawRect(this.C, this.B);
        super.onDraw(canvas);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.C.set(0, 0, getMeasuredWidth(), 2);
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int progress) {
        super.setProgress(progress == 100 ? 0 : Math.max(progress, 5));
    }
}
