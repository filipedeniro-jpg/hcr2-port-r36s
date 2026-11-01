package com.facebook.ads.redexgen.X;

import android.view.animation.Animation;
import android.widget.LinearLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Df, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class AnimationAnimationListenerC0464Df implements Animation.AnimationListener {
    public final /* synthetic */ C0462Dd B;
    public final /* synthetic */ LinearLayout C;

    public AnimationAnimationListenerC0464Df(C0462Dd this$0, LinearLayout linearLayout) {
        this.B = this$0;
        this.C = linearLayout;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        this.C.setVisibility(8);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
    }
}
