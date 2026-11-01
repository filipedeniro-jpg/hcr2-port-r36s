package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.80, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass80 extends B5 {
    private static final int C = (int) (12.0f * J6.B);
    private static final int D = (int) (16.0f * J6.B);
    private final C0394An B;

    public AnonymousClass80(Context context, int iconSize, C1Y colors, JV adEventManager, InterfaceC0525Fo adListener, boolean useSmallerFonts, boolean hideIcon, C5I viewabilityChecker, C0621Ji touchDataRecorder) {
        super(context, iconSize, colors, EnumC02926p.REWARDED_VIDEO_AD_CLICK.B(), adEventManager, adListener, viewabilityChecker, touchDataRecorder);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iconSize, iconSize);
        layoutParams.addRule(15);
        layoutParams.addRule(9);
        layoutParams.setMargins(0, 0, C, 0);
        if (hideIcon) {
            this.E.setVisibility(8);
        }
        this.B = new C0394An(context, colors, true, useSmallerFonts, true);
        this.B.setAlignment(GravityCompat.START);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, this.E.getId());
        layoutParams2.addRule(15);
        B(JA.UB(context));
        super.B.addView(this.E, layoutParams);
        super.B.addView(this.B, layoutParams2);
        addView(super.B, new LinearLayout.LayoutParams(-2, -2));
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(0.0f);
        J6.P(this, gradientDrawable);
    }

    public final void D(int orientation) {
        J6.M(this.C);
        boolean z = orientation == 1;
        setOrientation(z ? 1 : 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(z ? -1 : 0, -2);
        layoutParams.weight = 1.0f;
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(z ? -1 : -2, -2);
        layoutParams2.setMargins(z ? 0 : D, z ? D : 0, 0, 0);
        layoutParams2.gravity = 80;
        super.B.setLayoutParams(layoutParams);
        addView(this.C, layoutParams2);
    }

    @Override // com.facebook.ads.redexgen.X.B5
    public void setInfo(C1V metadata, C1Z ctaData, String clientToken, String imageUrl, CP listener) {
        super.setInfo(metadata, ctaData, clientToken, imageUrl, listener);
        this.B.A(metadata.I(), metadata.H(), null, false, false);
        this.C.setActionEnabled(JA.UB(getContext()) ? false : true);
        if (TextUtils.isEmpty(ctaData.B())) {
            J6.F(this.C);
        }
    }
}
