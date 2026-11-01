package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class ay extends ImageView {
    protected TextView a;
    private com.chartboost.sdk.Libraries.h b;

    public ay(Context context) {
        super(context);
        this.b = null;
        this.a = null;
    }

    public void a(com.chartboost.sdk.Libraries.h hVar) {
        if (hVar == null || !hVar.c() || this.b == hVar) {
            return;
        }
        this.b = hVar;
        setImageDrawable(new BitmapDrawable(hVar.d()));
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.b = null;
        setImageDrawable(new BitmapDrawable(bitmap));
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas);
    }

    protected void a(Canvas canvas) {
        TextView textView = this.a;
        if (textView != null) {
            textView.layout(0, 0, canvas.getWidth(), canvas.getHeight());
            this.a.setEnabled(isEnabled());
            this.a.setSelected(isSelected());
            if (isFocused()) {
                this.a.requestFocus();
            } else {
                this.a.clearFocus();
            }
            this.a.setPressed(isPressed());
            this.a.draw(canvas);
        }
    }
}
