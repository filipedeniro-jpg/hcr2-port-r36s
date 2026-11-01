package com.facebook.ads.redexgen.X;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.HashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9a, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03559a extends FrameLayout {
    private final B0 B;
    private static final int C = (int) (J6.B * 21.0f);
    private static final int E = (int) (J6.B * 8.0f);
    private static final int D = (int) (J6.B * 3.0f);

    public C03559a(AF params, String ctaText, C1Y colorInfo, CP onCtaClickedListener) {
        super(params.B());
        LinearLayout linearLayout = new LinearLayout(params.B());
        addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        linearLayout.setClickable(false);
        ImageView imageView = new ImageView(params.B());
        imageView.setImageBitmap(C0605Ir.E(EnumC0606Is.BACK_ARROW));
        imageView.setRotation(90.0f);
        imageView.setClickable(false);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setShape(1);
        imageView.setBackgroundDrawable(gradientDrawable);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(C, C);
        layoutParams.bottomMargin = E;
        imageView.setPadding(D, D, D, D);
        layoutParams.gravity = 1;
        linearLayout.addView(imageView, layoutParams);
        TextView textView = new TextView(params.B());
        textView.setTextSize(14.0f);
        textView.setText(ctaText);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        textView.setTextColor(colorInfo.A(true));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 1;
        layoutParams2.bottomMargin = E;
        linearLayout.addView(textView, layoutParams2);
        textView.setClickable(false);
        this.B = new B0(params.B(), true, false, "com.facebook.ads.interstitial.clicked", null, params.A(), params.D(), params.M(), params.L());
        this.B.setCta(params.C().A().get(0).A(), params.C().C(), new HashMap(), onCtaClickedListener);
        this.B.setIsInAppBrowser(true);
        addView(this.B, new FrameLayout.LayoutParams(-1, -1));
    }

    @Override // android.view.View
    public final boolean performClick() {
        return this.B.performClick();
    }
}
