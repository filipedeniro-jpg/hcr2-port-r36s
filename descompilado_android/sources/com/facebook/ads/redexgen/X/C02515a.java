package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5a, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02515a implements InterfaceC02585h {
    private final int B;

    @Nullable
    private ValueAnimator C;
    private final int D;
    private final int E;
    private EnumC02595i F = EnumC02595i.REVERSE_ANIMATED;
    private final View G;

    public C02515a(View targetView, int durationMs, int expandHeight, int collapseHeight) {
        this.G = targetView;
        this.B = durationMs;
        this.E = expandHeight;
        this.D = collapseHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(boolean showAnimation) {
        if (showAnimation) {
            this.F = EnumC02595i.REVERSE_ANIMATING;
            this.C = J(this.E, this.D, this.G);
            this.C.addListener(new Animator.AnimatorListener() { // from class: com.facebook.ads.redexgen.X.5c
                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animation) {
                    this.B.I(false);
                    if (this.B.C != null) {
                        this.B.C.removeAllListeners();
                        this.B.C = null;
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animation) {
                    this.B.F = EnumC02595i.REVERSE_ANIMATED;
                    J6.F(this.B.G);
                    if (this.B.C != null) {
                        this.B.C.removeAllListeners();
                        this.B.C = null;
                    }
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
        ViewGroup.LayoutParams layoutParams = this.G.getLayoutParams();
        layoutParams.height = this.D;
        this.G.setLayoutParams(layoutParams);
        J6.F(this.G);
        this.F = EnumC02595i.REVERSE_ANIMATED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I(boolean showAnimation) {
        J6.S(this.G);
        if (showAnimation) {
            this.F = EnumC02595i.ANIMATING;
            this.C = J(this.D, this.E, this.G);
            this.C.addListener(new Animator.AnimatorListener() { // from class: com.facebook.ads.redexgen.X.5d
                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animation) {
                    this.B.H(false);
                }

                @Override // android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animation) {
                    this.B.C = null;
                    this.B.F = EnumC02595i.ANIMATED;
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
        ViewGroup.LayoutParams layoutParams = this.G.getLayoutParams();
        layoutParams.height = this.E;
        this.G.setLayoutParams(layoutParams);
        this.F = EnumC02595i.ANIMATED;
    }

    private ValueAnimator J(int startHeight, int endHeight, final View view) {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(startHeight, endHeight);
        valueAnimatorOfInt.setDuration(this.B);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.facebook.ads.redexgen.X.5b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator animation) {
                int iIntValue = ((Integer) animation.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = iIntValue;
                view.setLayoutParams(layoutParams);
            }
        });
        return valueAnimatorOfInt;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final EnumC02595i JC() {
        return this.F;
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
