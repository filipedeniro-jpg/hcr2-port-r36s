package com.facebook.ads.redexgen.X;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AV extends AbstractC0390Aj {
    public AV(AF params, C1Y colors, boolean isInLandscape) {
        super(params, colors, true);
        RelativeLayout relativeLayout = new RelativeLayout(params.B());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
        gradientDrawable.setCornerRadius(0.0f);
        gradientDrawable.setGradientType(0);
        J6.P(relativeLayout, gradientDrawable);
        LinearLayout linearLayout = new LinearLayout(params.B());
        linearLayout.setOrientation(isInLandscape ? 0 : 1);
        linearLayout.setGravity(80);
        J6.U(linearLayout);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(AbstractC0390Aj.E, 0, AbstractC0390Aj.E, params.I() == null ? AbstractC0390Aj.E : AbstractC0390Aj.E / 2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(isInLandscape ? -2 : -1, -2);
        layoutParams3.setMargins(isInLandscape ? AbstractC0390Aj.E : 0, isInLandscape ? 0 : AbstractC0390Aj.E, 0, 0);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(isInLandscape ? 0 : -1, -2);
        layoutParams4.setMargins(0, 0, 0, 0);
        layoutParams4.weight = 1.0f;
        linearLayout.addView(getTitleDescContainer(), layoutParams4);
        linearLayout.addView(getCtaButton(), layoutParams3);
        relativeLayout.addView(linearLayout, layoutParams2);
        if (params.I() != null) {
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams5.setMargins(0, 0, 0, 0);
            layoutParams5.addRule(3, linearLayout.getId());
            relativeLayout.addView(params.I(), layoutParams5);
        }
        addView(params.E(), new RelativeLayout.LayoutParams(-1, -1));
        addView(relativeLayout, layoutParams);
        if (params.F() != null) {
            RelativeLayout.LayoutParams muteParams = new RelativeLayout.LayoutParams(AbstractC0390Aj.F, AbstractC0390Aj.F);
            muteParams.addRule(10);
            muteParams.addRule(11);
            muteParams.setMargins(AbstractC0390Aj.E, AbstractC0390Aj.E + params.K(), AbstractC0390Aj.E, AbstractC0390Aj.E);
            addView(params.F(), muteParams);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final boolean B() {
        return true;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final void E(C01491c adInfo, String clientToken, double aspectRatio, @Nullable Bundle savedInstanceState, @Nullable D4 onAdShownListener) {
        super.E(adInfo, clientToken, aspectRatio, savedInstanceState, onAdShownListener);
        if (onAdShownListener != null) {
            onAdShownListener.TD();
        }
    }
}
