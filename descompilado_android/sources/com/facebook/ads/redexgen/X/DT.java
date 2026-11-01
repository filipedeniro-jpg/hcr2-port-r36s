package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DT extends AnimatorListenerAdapter {
    public final /* synthetic */ C0459Da B;
    public final /* synthetic */ ViewPropertyAnimator C;
    public final /* synthetic */ int D;
    public final /* synthetic */ int E;
    public final /* synthetic */ AbstractC0502Er F;
    public final /* synthetic */ View G;

    public DT(C0459Da this$0, AbstractC0502Er abstractC0502Er, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
        this.B = this$0;
        this.F = abstractC0502Er;
        this.D = i;
        this.G = view;
        this.E = i2;
        this.C = viewPropertyAnimator;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        if (this.D != 0) {
            this.G.setTranslationX(0.0f);
        }
        if (this.E != 0) {
            this.G.setTranslationY(0.0f);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.C.setListener(null);
        this.B.e(this.F);
        this.B.F.remove(this.F);
        this.B.l();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.B.f(this.F);
    }
}
