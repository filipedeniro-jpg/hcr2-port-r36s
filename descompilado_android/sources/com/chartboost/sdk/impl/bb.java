package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class bb extends View {
    private Bitmap a;
    private Canvas b;

    protected abstract void a(Canvas canvas);

    public bb(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        a(context);
    }

    private void a(Context context) {
        try {
            getClass().getMethod("setLayerType", Integer.TYPE, Paint.class).invoke(this, 1, null);
        } catch (Exception unused) {
        }
    }

    private boolean b(Canvas canvas) {
        try {
            return ((Boolean) Canvas.class.getMethod("isHardwareAccelerated", new Class[0]).invoke(canvas, new Object[0])).booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        Canvas canvas2;
        boolean zB = b(canvas);
        if (zB) {
            Bitmap bitmap = this.a;
            if (bitmap == null || bitmap.getWidth() != canvas.getWidth() || this.a.getHeight() != canvas.getHeight()) {
                Bitmap bitmap2 = this.a;
                if (bitmap2 != null && !bitmap2.isRecycled()) {
                    this.a.recycle();
                }
                try {
                    this.a = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
                    this.b = new Canvas(this.a);
                } catch (Throwable unused) {
                    return;
                }
            }
            this.a.eraseColor(0);
            canvas2 = canvas;
            canvas = this.b;
        } else {
            canvas2 = null;
        }
        a(canvas);
        if (zB) {
            canvas2.drawBitmap(this.a, 0.0f, 0.0f, (Paint) null);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.a;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.a.recycle();
        }
        this.a = null;
    }
}
