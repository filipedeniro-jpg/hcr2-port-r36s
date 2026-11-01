package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.widget.LinearLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.At, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0400At extends LinearLayout {
    private static final int G = (int) (4.0f * J6.B);
    private final int B;
    private final int C;
    private int D;
    private final int E;
    private final C0399As[] F;

    public C0400At(Context context, int ratingIconSize, int maxRatingValue, int backgroundColor, int fillColor) {
        super(context);
        this.D = G;
        setOrientation(0);
        this.E = ratingIconSize;
        this.B = backgroundColor;
        this.C = fillColor;
        this.F = new C0399As[maxRatingValue];
        for (int i = 0; i < maxRatingValue; i++) {
            this.F[i] = B();
            addView(this.F[i]);
        }
        D();
    }

    private C0399As B() {
        C0399As c0399As = new C0399As(getContext(), this.B, this.C);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.E, this.E);
        layoutParams.gravity = 16;
        c0399As.setLayoutParams(layoutParams);
        return c0399As;
    }

    private void C(float ratingValue) {
        for (int i = 0; i < this.F.length; i++) {
            float fMin = Math.min(1.0f, ratingValue - i);
            if (fMin < 0.0f) {
                fMin = 0.0f;
            }
            this.F[i].setFillRatio(fMin);
        }
    }

    private void D() {
        int i = 0;
        while (i < this.F.length) {
            ((LinearLayout.LayoutParams) this.F[i].getLayoutParams()).leftMargin = i == 0 ? 0 : this.D;
            i++;
        }
        requestLayout();
    }

    public void setItemSpacing(int spacingPx) {
        this.D = spacingPx;
        D();
    }

    public void setRating(float ratingValue) {
        C(ratingValue);
    }
}
