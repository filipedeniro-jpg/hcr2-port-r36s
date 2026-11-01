package com.facebook.ads.redexgen.X;

import android.os.Handler;
import android.view.animation.Animation;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FZ implements Animation.AnimationListener {
    public final /* synthetic */ C0513Fc B;

    public FZ(C0513Fc this$0) {
        this.B = this$0;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation arg0) {
        new Handler().postDelayed(new FY(this), 3000L);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation arg0) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation arg0) {
    }
}
