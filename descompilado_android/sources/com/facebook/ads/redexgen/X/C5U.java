package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5U, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C5U implements InterfaceC02585h {
    private final int B;

    @Nullable
    private ValueAnimator C;
    private final int D;
    private final int E;
    private EnumC02595i F = EnumC02595i.REVERSE_ANIMATED;
    private final View G;

    public C5U(View targetView, int durationMs, int startColor, int endColor) {
        this.B = durationMs;
        this.G = targetView;
        this.E = startColor;
        this.D = endColor;
    }

    private void G(final int startColor, final int endColor) {
        this.F = startColor == this.E ? EnumC02595i.ANIMATING : EnumC02595i.REVERSE_ANIMATING;
        this.C = ObjectAnimator.ofInt((TextView) this.G, "textColor", startColor, endColor);
        this.C.setEvaluator(new ArgbEvaluator());
        this.C.setDuration(this.B);
        this.C.addListener(new Animator.AnimatorListener() { // from class: com.facebook.ads.redexgen.X.5V
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animation) {
                this.B.H(endColor, startColor, false);
                if (this.B.C != null) {
                    this.B.C.removeAllListeners();
                    this.B.C = null;
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animation) {
                this.B.F = startColor == this.B.E ? EnumC02595i.ANIMATED : EnumC02595i.REVERSE_ANIMATED;
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(int startColor, int endColor, boolean animate) {
        if (animate) {
            G(startColor, endColor);
        } else {
            ((TextView) this.G).setTextColor(endColor);
            this.F = endColor == this.D ? EnumC02595i.ANIMATED : EnumC02595i.REVERSE_ANIMATED;
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final EnumC02595i JC() {
        return this.F;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final void XB(boolean showAnimation, boolean isReverse) {
        H(isReverse ? this.D : this.E, isReverse ? this.E : this.D, showAnimation);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final void cancel() {
        if (this.C != null) {
            this.C.cancel();
        }
    }
}
