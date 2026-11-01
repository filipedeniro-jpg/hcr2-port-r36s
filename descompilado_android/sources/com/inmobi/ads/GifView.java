package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class GifView extends View implements t.a {
    private t a;
    private float b;
    private float c;
    private float d;
    private int e;
    private int f;
    private boolean g;

    public GifView(Context context) {
        this(context, null);
    }

    public GifView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = true;
        setLayerType(1, null);
    }

    public GifView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = true;
        setLayerType(1, null);
    }

    public void setPaused(boolean z) {
        this.a.a(z);
    }

    public void setGif(t tVar) {
        this.a = tVar;
        t tVar2 = this.a;
        if (tVar2 != null) {
            tVar2.a(this);
            this.a.a();
        }
        requestLayout();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size;
        int size2;
        t tVar = this.a;
        if (tVar != null) {
            int iB = tVar.b();
            int iC = this.a.c();
            this.d = 1.0f / Math.max((View.MeasureSpec.getMode(i) == 0 || iB <= (size2 = View.MeasureSpec.getSize(i))) ? 1.0f : iB / size2, (View.MeasureSpec.getMode(i2) == 0 || iC <= (size = View.MeasureSpec.getSize(i2))) ? 1.0f : iC / size);
            float f = this.d;
            this.e = (int) (iB * f);
            this.f = (int) (iC * f);
            setMeasuredDimension(this.e, this.f);
            return;
        }
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.b = (getWidth() - this.e) / 2.0f;
        this.c = (getHeight() - this.f) / 2.0f;
        this.g = getVisibility() == 0;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        t tVar = this.a;
        if (tVar != null) {
            if (tVar.d()) {
                this.a.e();
                a(canvas);
                b();
                return;
            }
            a(canvas);
        }
    }

    private void a(Canvas canvas) {
        canvas.save();
        float f = this.d;
        canvas.scale(f, f);
        t tVar = this.a;
        float f2 = this.b;
        float f3 = this.d;
        tVar.a(canvas, f2 / f3, this.c / f3);
        canvas.restore();
    }

    private void b() {
        if (this.g) {
            if (Build.VERSION.SDK_INT >= 16) {
                postInvalidateOnAnimation();
            } else {
                invalidate();
            }
        }
    }

    @Override // android.view.View
    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        this.g = i == 1;
        b();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        this.g = i == 0;
        b();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.g = i == 0;
        b();
    }

    @Override // com.inmobi.ads.t.a
    public final void a() {
        invalidate();
    }
}
