package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5Q, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C5Q implements InterfaceC02585h {
    private final int B;

    @Nullable
    private ValueAnimator C;
    private final int D;
    private final int E;
    private EnumC02595i F = EnumC02595i.REVERSE_ANIMATED;
    private final View G;

    public C5Q(View targetView, int durationMs, int startY, int finalY) {
        this.G = targetView;
        this.B = durationMs;
        this.E = startY;
        this.D = finalY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(boolean showAnimation) {
        if (showAnimation) {
            this.F = EnumC02595i.REVERSE_ANIMATING;
            this.C = K(this.G, this.D, this.E);
            this.C.addListener(new Animator.AnimatorListener() { // from class: com.facebook.ads.redexgen.X.5S
                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animation) {
                    this.B.I(false);
                    this.B.J();
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animation) {
                    this.B.F = EnumC02595i.REVERSE_ANIMATED;
                    J6.F(this.B.G);
                    this.B.J();
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationRepeat(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animation) {
                }
            });
            this.C.start();
            return;
        }
        this.G.setTranslationY(this.E);
        J6.F(this.G);
        this.F = EnumC02595i.REVERSE_ANIMATED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I(boolean showAnimation) {
        J6.S(this.G);
        if (showAnimation) {
            this.F = EnumC02595i.ANIMATING;
            this.C = K(this.G, this.E, this.D);
            this.C.addListener(new Animator.AnimatorListener() { // from class: com.facebook.ads.redexgen.X.5T
                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animation) {
                    this.B.H(false);
                    this.B.J();
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animation) {
                    this.B.C = null;
                    this.B.F = EnumC02595i.ANIMATED;
                    this.B.J();
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationRepeat(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animation) {
                }
            });
            this.C.start();
            return;
        }
        this.G.setTranslationY(this.D);
        this.F = EnumC02595i.ANIMATED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        if (this.C != null) {
            this.C.removeAllListeners();
            this.C = null;
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final EnumC02595i JC() {
        return this.F;
    }

    private ValueAnimator K(final View view, int startY, int finalY) {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(startY, finalY);
        valueAnimatorOfInt.setDuration(this.B);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.facebook.ads.redexgen.X.5R
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY(((Integer) animation.getAnimatedValue()).intValue());
            }
        });
        return valueAnimatorOfInt;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final void XB(boolean showAnimation, boolean isReverse) {
        if (isReverse) {
            H(showAnimation);
        } else {
            I(showAnimation);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final void cancel() {
        if (this.C != null) {
            this.C.cancel();
        }
    }
}
