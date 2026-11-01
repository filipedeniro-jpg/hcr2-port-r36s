package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5X, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C5X implements InterfaceC02585h {
    private final int B;

    @Nullable
    private ViewPropertyAnimator C;
    private final boolean D;
    private EnumC02595i E = EnumC02595i.REVERSE_ANIMATED;
    private final View F;

    public C5X(View targetView, int durationMs, boolean changeVisibility) {
        this.B = durationMs;
        this.F = targetView;
        this.D = changeVisibility;
    }

    private void G(boolean showAnimation) {
        this.E = EnumC02595i.ANIMATING;
        if (this.D) {
            J6.S(this.F);
        }
        if (showAnimation) {
            this.C = this.F.animate().alpha(1.0f).setDuration(this.B).setListener(new AnimatorListenerAdapter() { // from class: com.facebook.ads.redexgen.X.5Z
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animation) {
                    if (this.B.D) {
                        J6.F(this.B.F);
                    }
                    this.B.F.setAlpha(0.0f);
                    this.B.E = EnumC02595i.REVERSE_ANIMATED;
                    if (this.B.C != null) {
                        this.B.C.setListener(null);
                        this.B.C = null;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animation) {
                    this.B.E = EnumC02595i.ANIMATED;
                    if (this.B.C != null) {
                        this.B.C.setListener(null);
                        this.B.C = null;
                    }
                }
            });
        } else {
            this.F.setAlpha(1.0f);
            this.E = EnumC02595i.ANIMATED;
        }
    }

    private void H(boolean showAnimation) {
        this.E = EnumC02595i.REVERSE_ANIMATING;
        if (showAnimation) {
            this.C = this.F.animate().alpha(0.0f).setDuration(this.B).setListener(new AnimatorListenerAdapter() { // from class: com.facebook.ads.redexgen.X.5Y
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animation) {
                    if (this.B.D) {
                        J6.S(this.B.F);
                    }
                    this.B.F.setAlpha(1.0f);
                    this.B.E = EnumC02595i.ANIMATED;
                    if (this.B.C != null) {
                        this.B.C.setListener(null);
                        this.B.C = null;
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animation) {
                    if (this.B.D) {
                        J6.F(this.B.F);
                    }
                    this.B.E = EnumC02595i.REVERSE_ANIMATED;
                    if (this.B.C != null) {
                        this.B.C.setListener(null);
                        this.B.C = null;
                    }
                }
            });
        } else {
            this.F.setAlpha(0.0f);
            this.E = EnumC02595i.REVERSE_ANIMATED;
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final EnumC02595i JC() {
        return this.E;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final void XB(boolean showAnimation, boolean isReverse) {
        if (isReverse) {
            H(showAnimation);
        } else {
            G(showAnimation);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final void cancel() {
        this.F.clearAnimation();
        if (this.C != null) {
            this.C.cancel();
        }
    }
}
