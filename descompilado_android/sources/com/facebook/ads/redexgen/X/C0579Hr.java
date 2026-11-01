package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0579Hr extends LinearLayout {
    private final ImageView B;
    private final ImageView C;
    private static final int F = (int) (50.0f * J6.B);
    private static final int D = (int) (10.0f * J6.B);
    private static final int E = (int) (20.0f * J6.B);
    private static final int G = (int) (4.0f * J6.B);

    public C0579Hr(Context context, int anLogoType) {
        super(context);
        setOrientation(0);
        setPadding(D, D, D, D);
        setBackgrounStyleToContainer(this);
        this.B = new ImageView(context);
        this.C = new ImageView(context);
        B(anLogoType);
    }

    private void B(int anLogoType) {
        setDataForImage(this.B, EnumC0606Is.AD_CHOICES_ICON);
        if (1 == anLogoType) {
            setDataForImage(this.C, EnumC0606Is.DEFAULT_INFO_ICON);
        } else {
            setDataForImage(this.C, EnumC0606Is.AN_INFO_ICON);
        }
        addView(this.C, new LinearLayout.LayoutParams(E, E));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(E, E);
        layoutParams.setMargins(G, 0, 0, 0);
        addView(this.B, layoutParams);
    }

    private static void setBackgrounStyleToContainer(LinearLayout adInfoAdChoiceButtonContainer) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(F);
        gradientDrawable.setColor(ViewCompat.MEASURED_STATE_MASK);
        J6.P(adInfoAdChoiceButtonContainer, gradientDrawable);
    }

    private static void setDataForImage(ImageView imageView, EnumC0606Is adChoicesIcon) {
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageBitmap(C0605Ir.E(adChoicesIcon));
        imageView.setColorFilter(-1);
    }
}
