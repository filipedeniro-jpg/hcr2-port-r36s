package com.facebook.ads.redexgen.X;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class A2 extends AbstractC0390Aj {
    private static final int C = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final C03569b B;

    public A2(AF params, boolean textInsideMedia, C1Y colors) {
        super(params, colors, textInsideMedia);
        this.B = new C03569b(params.B(), params.E());
        this.B.B(params.I(), params.F(), 10, getTitleDescContainer(), textInsideMedia);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        layoutParams.setMargins(AbstractC0390Aj.E, AbstractC0390Aj.E, AbstractC0390Aj.E, AbstractC0390Aj.E);
        getCtaButton().setLayoutParams(layoutParams);
        FrameLayout frameLayout = new FrameLayout(params.B());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(2, getCtaButton().getId());
        frameLayout.setLayoutParams(layoutParams2);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams3.gravity = 17;
        layoutParams3.setMargins(AbstractC0390Aj.E, 0, AbstractC0390Aj.E, 0);
        frameLayout.addView(this.B, layoutParams3);
        addView(frameLayout);
        addView(getCtaButton());
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final boolean B() {
        return false;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final boolean C() {
        return false;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final void E(C01491c adInfo, String clientToken, double aspectRatio, @Nullable Bundle savedInstanceState, @Nullable D4 onAdShownListener) {
        super.E(adInfo, clientToken, aspectRatio, savedInstanceState, onAdShownListener);
        if (aspectRatio > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.B.C((int) (((double) (C - (AbstractC0390Aj.E * 2))) / aspectRatio));
        }
        if (onAdShownListener != null) {
            onAdShownListener.TD();
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final boolean O() {
        return false;
    }
}
