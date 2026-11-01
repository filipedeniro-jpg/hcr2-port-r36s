package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.widget.ImageView;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class CN extends ImageView {
    private final Path B;
    private boolean C;
    private float[] D;
    private final RectF E;
    private static final int G = (int) (8.0f * J6.B);
    private static final float[] F = {G, G, G, G, G, G, G, G};

    public CN(Context context) {
        super(context);
        this.D = F;
        this.C = false;
        this.B = new Path();
        this.E = new RectF();
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
    }

    private float[] getRadiiForCircularImage() {
        int iMin = Math.min(getWidth(), getHeight()) / 2;
        return new float[]{iMin, iMin, iMin, iMin, iMin, iMin, iMin, iMin};
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDraw(Canvas canvas) {
        float[] radiiForCircularImage;
        this.E.set(0.0f, 0.0f, getWidth(), getHeight());
        this.B.reset();
        if (this.C) {
            radiiForCircularImage = getRadiiForCircularImage();
        } else {
            radiiForCircularImage = this.D;
        }
        this.B.addRoundRect(this.E, radiiForCircularImage, Path.Direction.CW);
        canvas.clipPath(this.B);
        super.onDraw(canvas);
    }

    public void setFullCircleCorners(boolean hasCompletelyRoundCorners) {
        this.C = hasCompletelyRoundCorners;
    }

    public void setRadius(int radiusDp) {
        int i = (int) (radiusDp * J6.B);
        this.D = new float[]{i, i, i, i, i, i, i, i};
    }

    public void setRadius(float[] radii) {
        this.D = radii;
    }
}
