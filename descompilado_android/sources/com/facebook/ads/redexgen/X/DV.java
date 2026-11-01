package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DV extends AnimatorListenerAdapter {
    public final /* synthetic */ C0459Da B;
    public final /* synthetic */ DW C;
    public final /* synthetic */ View D;
    public final /* synthetic */ ViewPropertyAnimator E;

    public DV(C0459Da this$0, DW dw, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.B = this$0;
        this.C = dw;
        this.E = viewPropertyAnimator;
        this.D = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.E.setListener(null);
        this.D.setAlpha(1.0f);
        this.D.setTranslationX(0.0f);
        this.D.setTranslationY(0.0f);
        this.B.c(this.C.D, false);
        this.B.D.remove(this.C.D);
        this.B.l();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.B.d(this.C.D, false);
    }
}
