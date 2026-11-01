package net.nend.android.internal.ui.views.formats;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes2.dex */
public class FontFitTextView extends TextView {
    private Paint.FontMetrics a;
    private int b;

    public FontFitTextView(Context context) {
        super(context);
        a();
    }

    public FontFitTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        setShadowLayer(8.0f, 1.0f, 1.0f, ViewCompat.MEASURED_STATE_MASK);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = i3 - i;
        if (this.b != i5) {
            a(b(getWidth()), this.b != 0);
            this.b = i5;
        }
    }

    private float b(int i) {
        Paint paint = new Paint();
        float textSize = getTextSize();
        paint.setTextSize(textSize);
        float fMeasureText = paint.measureText("１２３４５６７８");
        float f = i;
        float f2 = 2.0f;
        if (f > fMeasureText) {
            f2 = textSize;
            while (f > fMeasureText) {
                f2 += 1.0f;
                paint.setTextSize(f2);
                fMeasureText = paint.measureText("１２３４５６７８");
            }
        } else {
            while (f < fMeasureText) {
                if (2.0f < textSize) {
                    textSize -= 1.0f;
                    paint.setTextSize(textSize);
                    fMeasureText = paint.measureText("１２３４５６７８");
                }
            }
            f2 = textSize;
        }
        this.a = paint.getFontMetrics();
        return f2;
    }

    public void a(int i) {
        this.b = i;
        a(b(i), false);
    }

    private void a(float f, boolean z) {
        super.setTextSize(0, f);
        float f2 = (this.a.top * (-1.0f)) + this.a.bottom;
        if (z || getHeight() < f2) {
            setHeight((int) f2);
            postDelayed(new Runnable() { // from class: net.nend.android.internal.ui.views.formats.FontFitTextView.1
                @Override // java.lang.Runnable
                public void run() {
                    FontFitTextView.this.requestLayout();
                    FontFitTextView.this.invalidate();
                }
            }, 10L);
        }
    }
}
