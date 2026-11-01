package com.facebook.ads.redexgen.X;

import android.os.Handler;
import android.view.animation.Animation;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6l, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class AnimationAnimationListenerC02886l implements Animation.AnimationListener {
    public final /* synthetic */ C02856i B;

    public AnimationAnimationListenerC02886l(C02856i this$0) {
        this.B = this$0;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation arg0) {
        new Handler().postDelayed(new Runnable() { // from class: com.facebook.ads.redexgen.X.6m
            @Override // java.lang.Runnable
            public final void run() {
                if (this.B.B.B) {
                    this.B.B.K();
                }
            }
        }, 3000L);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation arg0) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation arg0) {
    }
}
