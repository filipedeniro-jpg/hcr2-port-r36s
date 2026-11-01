package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.widget.Button;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5o, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02655o extends Button {
    private final Paint B;
    private boolean C;
    private final Path D;
    private final Path E;
    private final Path F;

    public C02655o(Context context) {
        this(context, false);
    }

    public C02655o(Context context, final boolean darkBackground) {
        super(context);
        this.C = false;
        this.D = new Path();
        this.E = new Path();
        this.F = new Path();
        this.B = new Paint() { // from class: com.facebook.ads.redexgen.X.5p
            {
                setStyle(Paint.Style.FILL_AND_STROKE);
                setStrokeCap(Paint.Cap.ROUND);
                setStrokeWidth(3.0f);
                setAntiAlias(true);
                setColor(darkBackground ? -1 : -10066330);
            }
        };
        setClickable(true);
        J6.O(this, 0);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        if (canvas.isHardwareAccelerated() && Build.VERSION.SDK_INT < 17) {
            setLayerType(1, null);
        }
        float fMax = Math.max(canvas.getWidth(), canvas.getHeight()) / 100.0f;
        if (this.C) {
            this.F.rewind();
            this.F.moveTo(26.5f * fMax, 15.5f * fMax);
            this.F.lineTo(26.5f * fMax, 84.5f * fMax);
            this.F.lineTo(90.0f * fMax, 50.0f * fMax);
            this.F.lineTo(26.5f * fMax, 15.5f * fMax);
            this.F.close();
            canvas.drawPath(this.F, this.B);
        } else {
            this.D.rewind();
            this.D.moveTo(29.0f * fMax, 21.0f * fMax);
            this.D.lineTo(29.0f * fMax, 79.0f * fMax);
            this.D.lineTo(45.0f * fMax, 79.0f * fMax);
            this.D.lineTo(45.0f * fMax, 21.0f * fMax);
            this.D.lineTo(29.0f * fMax, 21.0f * fMax);
            this.D.close();
            this.E.rewind();
            this.E.moveTo(55.0f * fMax, 21.0f * fMax);
            this.E.lineTo(55.0f * fMax, 79.0f * fMax);
            this.E.lineTo(71.0f * fMax, 79.0f * fMax);
            this.E.lineTo(71.0f * fMax, 21.0f * fMax);
            this.E.lineTo(55.0f * fMax, 21.0f * fMax);
            this.E.close();
            canvas.drawPath(this.D, this.B);
            canvas.drawPath(this.E, this.B);
        }
        super.onDraw(canvas);
    }

    public void setChecked(boolean mChecked) {
        this.C = mChecked;
        refreshDrawableState();
        invalidate();
    }
}
