package com.facebook.ads.redexgen.X;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FN implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ FF B;

    public FN(FF this$0) {
        this.B = this$0;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator animation) {
        this.B.C(animation.getAnimatedFraction());
    }
}
