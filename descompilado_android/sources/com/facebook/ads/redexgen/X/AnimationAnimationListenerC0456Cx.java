package com.facebook.ads.redexgen.X;

import android.view.animation.Animation;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Cx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class AnimationAnimationListenerC0456Cx implements Animation.AnimationListener {
    public final /* synthetic */ AbstractC0466Dh B;

    public AnimationAnimationListenerC0456Cx(AbstractC0466Dh this$0) {
        this.B = this$0;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        J6.F(this.B.I);
        this.B.K = false;
        if (this.B.J == null) {
            return;
        }
        this.B.J.TD();
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
    }
}
