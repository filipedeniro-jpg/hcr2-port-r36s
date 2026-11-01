package com.facebook.ads.redexgen.X;

import android.os.Handler;
import android.view.animation.Animation;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Cy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class AnimationAnimationListenerC0457Cy implements Animation.AnimationListener {
    public final /* synthetic */ AbstractC0466Dh B;
    public final /* synthetic */ C0531Fu C;
    public final /* synthetic */ AbstractC0466Dh D;

    public AnimationAnimationListenerC0457Cy(AbstractC0466Dh this$0, AbstractC0466Dh abstractC0466Dh, C0531Fu c0531Fu) {
        this.B = this$0;
        this.D = abstractC0466Dh;
        this.C = c0531Fu;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        this.B.K = false;
        J6.F(this.D);
        new Handler().postDelayed(new RunnableC0458Cz(this), 200L);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
    }
}
