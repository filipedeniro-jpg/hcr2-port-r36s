package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DR extends AnimatorListenerAdapter {
    public final /* synthetic */ C0459Da B;
    public final /* synthetic */ ViewPropertyAnimator C;
    public final /* synthetic */ AbstractC0502Er D;
    public final /* synthetic */ View E;

    public DR(C0459Da this$0, AbstractC0502Er abstractC0502Er, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.B = this$0;
        this.D = abstractC0502Er;
        this.C = viewPropertyAnimator;
        this.E = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.C.setListener(null);
        this.E.setAlpha(1.0f);
        this.B.g(this.D);
        this.B.H.remove(this.D);
        this.B.l();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.B.h(this.D);
    }
}
