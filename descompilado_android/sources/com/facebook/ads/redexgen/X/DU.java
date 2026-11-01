package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DU extends AnimatorListenerAdapter {
    public final /* synthetic */ C0459Da B;
    public final /* synthetic */ DW C;
    public final /* synthetic */ ViewPropertyAnimator D;
    public final /* synthetic */ View E;

    public DU(C0459Da this$0, DW dw, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.B = this$0;
        this.C = dw;
        this.D = viewPropertyAnimator;
        this.E = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.D.setListener(null);
        this.E.setAlpha(1.0f);
        this.E.setTranslationX(0.0f);
        this.E.setTranslationY(0.0f);
        this.B.c(this.C.E, true);
        this.B.D.remove(this.C.E);
        this.B.l();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.B.d(this.C.E, true);
    }
}
