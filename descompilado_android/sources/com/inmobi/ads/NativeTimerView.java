package com.inmobi.ads;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class NativeTimerView extends View {
    long a;
    long b;
    ValueAnimator c;
    private Bitmap d;
    private Canvas e;
    private RectF f;
    private RectF g;
    private Rect h;
    private Paint i;
    private Paint j;
    private Paint k;
    private Paint l;
    private Paint m;
    private float n;
    private b o;

    interface b {
        void a();
    }

    public NativeTimerView(Context context) {
        this(context, null);
    }

    public NativeTimerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NativeTimerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0L;
        this.i = new Paint();
        this.i.setAntiAlias(true);
        this.i.setColor(-723724);
        this.m = new Paint();
        this.m.setAntiAlias(true);
        this.m.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.m.setTextAlign(Paint.Align.CENTER);
        this.m.setAntiAlias(true);
        this.h = new Rect();
        this.j = new Paint();
        this.j.setAntiAlias(true);
        this.j.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setColor(0);
        this.k.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.l = new Paint();
        this.l.setStyle(Paint.Style.STROKE);
        this.l.setAntiAlias(true);
        this.l.setColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public void setTimerEventsListener(b bVar) {
        this.o = bVar;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3 || i2 != i4) {
            this.d = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            this.d.eraseColor(0);
            this.e = new Canvas(this.d);
        }
        super.onSizeChanged(i, i2, i3, i4);
        float fA = com.inmobi.commons.core.utilities.b.c.a((int) (getWidth() * 4.0f * 0.007f));
        float fA2 = com.inmobi.commons.core.utilities.b.c.a((int) (getWidth() * 14.0f * 0.007f));
        float fA3 = com.inmobi.commons.core.utilities.b.c.a((int) (getWidth() * 5.0f * 0.007f));
        float fA4 = com.inmobi.commons.core.utilities.b.c.a((int) (getWidth() * 1.5f * 0.007f));
        this.f = new RectF(fA3, fA3, getWidth() - fA3, getHeight() - fA3);
        this.g = new RectF(this.f.left + fA, this.f.top + fA, this.f.right - fA, this.f.bottom - fA);
        this.l.setStrokeWidth(fA4);
        this.m.setTextSize(fA2);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.e.drawColor(0, PorterDuff.Mode.CLEAR);
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        int iMin = Math.min(width, height);
        int iA = com.inmobi.commons.core.utilities.b.c.a((int) (getWidth() * 7.0f * 0.007f));
        float f = width;
        float f2 = height;
        canvas.drawCircle(f, f2, iMin, this.i);
        canvas.drawCircle(f, f2, iMin - iA, this.l);
        ValueAnimator valueAnimator = this.c;
        if (valueAnimator != null) {
            int currentPlayTime = (int) (this.a - (valueAnimator.getCurrentPlayTime() / 1000));
            if (((Float) this.c.getAnimatedValue()).floatValue() >= 1.0d) {
                currentPlayTime = 0;
            }
            Paint paint = this.m;
            Rect rect = this.h;
            String strValueOf = String.valueOf(currentPlayTime);
            paint.getTextBounds(strValueOf, 0, strValueOf.length(), rect);
            canvas.drawText(strValueOf, getWidth() / 2, (getHeight() / 2) + (((paint.descent() - paint.ascent()) / 2.0f) - paint.descent()), paint);
            if (((Float) this.c.getAnimatedValue()).floatValue() >= 1.0d) {
                b();
            }
        }
        float f3 = this.n;
        if (f3 > 0.0f) {
            this.e.drawArc(this.f, 270.0f, f3, true, this.j);
            this.e.drawOval(this.g, this.k);
        }
        canvas.drawBitmap(this.d, 0.0f, 0.0f, (Paint) null);
    }

    public void setTimerValue(long j) {
        this.a = j;
    }

    public static class a implements ValueAnimator.AnimatorUpdateListener {
        public WeakReference<NativeTimerView> a;

        public a(NativeTimerView nativeTimerView) {
            this.a = new WeakReference<>(nativeTimerView);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            NativeTimerView nativeTimerView = this.a.get();
            if (nativeTimerView == null) {
                return;
            }
            int visibility = nativeTimerView.getVisibility();
            if (visibility != 4 && visibility != 8) {
                nativeTimerView.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
            } else if (((Float) valueAnimator.getAnimatedValue()).floatValue() >= 1.0d) {
                nativeTimerView.b();
            }
        }
    }

    public final void a() {
        this.c = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.c.setDuration(TimeUnit.SECONDS.toMillis(this.a));
        this.c.setInterpolator(new LinearInterpolator());
        this.c.addUpdateListener(new a(this));
        this.c.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(float f) {
        this.n = f * 360.0f;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        b bVar = this.o;
        if (bVar != null) {
            bVar.a();
            this.c.cancel();
            this.c = null;
        }
    }
}
