package com.facebook.ads.redexgen.X;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import java.util.HashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9M, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C9M extends LinearLayout {

    @Nullable
    private C03559a B;
    private final CN C;
    private final C0394An D;
    public static final int F = (int) (J6.B * 275.0f);
    private static final int I = (int) (J6.B * 56.0f);
    private static final int J = (int) (J6.B * 4.0f);
    private static final int H = (int) (J6.B * 8.0f);
    private static final int G = (int) (J6.B * 16.0f);
    private static final int E = (int) (J6.B * 20.0f);

    public C9M(AF params, C1Y colorInfo, CP ctaClickedListener) {
        super(params.B());
        setOrientation(1);
        setGravity(17);
        this.C = new CN(params.B());
        this.C.setFullCircleCorners(true);
        setupIconView(params);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(I, I);
        addView(this.C, layoutParams);
        layoutParams.bottomMargin = J;
        this.D = new C0394An(getContext(), colorInfo, true, true, false);
        J6.U(this.D);
        this.D.setTitleGravity(17);
        this.D.setDescriptionGravity(17);
        this.D.B(true, 17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(G, 0, G, J);
        addView(this.D, layoutParams2);
        J6.U(this.D);
        LinearLayout.LayoutParams titleDescLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        titleDescLayoutParams.topMargin = E;
        titleDescLayoutParams.bottomMargin = J;
        if (params.H() == 1) {
            this.B = new C03559a(params, params.C().A().get(0).A().B(), colorInfo, ctaClickedListener);
            addView(this.B, titleDescLayoutParams);
            return;
        }
        C1Y c1y = new C1Y();
        c1y.H(654311423);
        B0 b0 = new B0(params.B(), true, false, "com.facebook.ads.interstitial.clicked", c1y, params.A(), params.D(), params.M(), params.L());
        b0.setCta(params.C().A().get(0).A(), params.C().C(), new HashMap(), ctaClickedListener);
        b0.setIsInAppBrowser(true);
        b0.setPadding(H, J, H, J);
        b0.setTypeface(Typeface.defaultFromStyle(1));
        addView(b0, titleDescLayoutParams);
    }

    public final void A(String title, String description, String sponsoredText, boolean isCarousel, boolean longerDescription) {
        this.D.A(title, description, sponsoredText, isCarousel, longerDescription);
    }

    @Nullable
    public C03559a getSwipeUpCtaButton() {
        return this.B;
    }

    private void setupIconView(AF params) {
        B7 b7 = new B7(this.C);
        b7.D(I, I);
        b7.E(params.C().I().B());
    }
}
