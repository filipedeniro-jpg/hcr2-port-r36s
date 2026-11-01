package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Av, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0402Av extends LinearLayout {
    private final int B;
    private final int C;
    private final int D;
    private int E;
    private List<GradientDrawable> F;

    public C0402Av(Context context, C1Y colors, int dotsCount) {
        super(context);
        this.E = -1;
        setOrientation(0);
        setGravity(17);
        float f = J6.B;
        int i = (int) (8.0f * f);
        int i2 = (int) (6.0f * f);
        this.B = (int) (1.0f * f);
        this.D = colors.A(false);
        this.C = C5W.B(this.D, 128);
        this.F = new ArrayList();
        for (int i3 = 0; i3 < dotsCount; i3++) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setSize(i, i);
            gradientDrawable.setStroke(this.B, 0);
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, i2, 0);
            layoutParams.gravity = 17;
            imageView.setLayoutParams(layoutParams);
            imageView.setImageDrawable(gradientDrawable);
            this.F.add(gradientDrawable);
            addView(imageView);
        }
        A(0);
    }

    public final void A(int currentPosition) {
        int i;
        int i2;
        if (this.E == currentPosition) {
            return;
        }
        this.E = currentPosition;
        for (int i3 = 0; i3 < i; i3++) {
            if (i3 == currentPosition) {
                i = this.D;
                i2 = this.D;
            } else {
                i = this.C;
                i2 = 0;
            }
            GradientDrawable gradientDrawable = this.F.get(i3);
            int i4 = this.B;
            gradientDrawable.setStroke(i4, i2);
            this.F.get(i3).setColor(i);
            this.F.get(i3).invalidateSelf();
        }
    }
}
