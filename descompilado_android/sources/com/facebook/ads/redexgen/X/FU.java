package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class FU extends View {
    private Paint B;
    private Paint C;
    private int D;
    private boolean E;
    private Paint F;

    public FU(Context context) {
        this(context, 60, true);
    }

    private FU(Context context, int mButtonSize, boolean mShowGraphic) {
        super(context);
        this.D = mButtonSize;
        this.E = mShowGraphic;
        if (mShowGraphic) {
            this.C = new Paint();
            this.C.setColor(-3355444);
            this.C.setStyle(Paint.Style.STROKE);
            this.C.setStrokeWidth(3.0f);
            this.C.setAntiAlias(true);
            this.B = new Paint();
            this.B.setColor(-1287371708);
            this.B.setStyle(Paint.Style.FILL);
            this.B.setAntiAlias(true);
            this.F = new Paint();
            this.F.setColor(-1);
            this.F.setStyle(Paint.Style.STROKE);
            this.F.setStrokeWidth(6.0f);
            this.F.setAntiAlias(true);
        }
    }

    public RelativeLayout.LayoutParams getDefaultLayoutParams() {
        float f = J6.B;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (this.D * f), (int) (this.D * f));
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        return layoutParams;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        if (this.E) {
            if (canvas.isHardwareAccelerated() && Build.VERSION.SDK_INT < 17) {
                setLayerType(1, null);
            }
            int outerRadius = Math.min(canvas.getWidth(), canvas.getHeight());
            int i = outerRadius / 2;
            int i2 = outerRadius / 2;
            int centerY = i * 2;
            int i3 = centerY / 3;
            canvas.drawCircle(i, i2, i3, this.C);
            int centerY2 = i3 - 2;
            canvas.drawCircle(i, i2, centerY2, this.B);
            int i4 = outerRadius / 3;
            int centerX = outerRadius / 3;
            int innerRadius = i4 * 2;
            float f = innerRadius;
            int innerRadius2 = centerX * 2;
            canvas.drawLine(i4, centerX, f, innerRadius2, this.F);
            int innerRadius3 = i4 * 2;
            int innerRadius4 = centerX * 2;
            canvas.drawLine(innerRadius3, centerX, i4, innerRadius4, this.F);
        }
        super.onDraw(canvas);
    }
}
