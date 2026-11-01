package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.ValueAnimator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FF implements Animator.AnimatorListener {
    public final int B;
    public final int C;
    public boolean E;
    public final float G;
    public final float H;
    public final float I;
    public final float J;
    public final AbstractC0502Er K;
    public float L;
    public float M;
    private float N;
    public boolean F = false;
    public boolean D = false;
    private final ValueAnimator O = ValueAnimator.ofFloat(0.0f, 1.0f);

    public FF(AbstractC0502Er viewHolder, int animationType, int actionState, float startDx, float startDy, float targetX, float targetY) {
        this.B = actionState;
        this.C = animationType;
        this.K = viewHolder;
        this.G = startDx;
        this.H = startDy;
        this.I = targetX;
        this.J = targetY;
        this.O.addUpdateListener(new FN(this));
        this.O.setTarget(viewHolder.B);
        this.O.addListener(this);
        C(0.0f);
    }

    public final void A() {
        this.O.cancel();
    }

    public final void B(long duration) {
        this.O.setDuration(duration);
    }

    public final void C(float fraction) {
        this.N = fraction;
    }

    public final void D() {
        this.K.d(false);
        this.O.start();
    }

    public final void E() {
        if (this.G == this.I) {
            this.L = this.K.B.getTranslationX();
        } else {
            this.L = this.G + (this.N * (this.I - this.G));
        }
        if (this.H == this.J) {
            this.M = this.K.B.getTranslationY();
        } else {
            this.M = this.H + (this.N * (this.J - this.H));
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animation) {
        C(1.0f);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animation) {
        if (!this.D) {
            this.K.d(true);
        }
        this.D = true;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animation) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animation) {
    }
}
