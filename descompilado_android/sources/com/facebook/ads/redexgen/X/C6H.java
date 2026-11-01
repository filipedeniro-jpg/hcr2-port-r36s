package com.facebook.ads.redexgen.X;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6H, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@TargetApi(12)
public final class C6H implements F0 {
    private boolean B;
    private final Handler C;
    private View D;

    @Nullable
    private C6I E;
    private final GK F;
    private final GG G;
    private final GE H;
    private final AbstractC02976u I;
    private final boolean J;

    @Nullable
    private C0447Co K;
    private final boolean L;

    public C6H(View fadeableView, C6I initialState) {
        this(fadeableView, initialState, false);
    }

    public C6H(View fadeableView, C6I initialState, boolean playAnimationOnPlayOut) {
        this(fadeableView, initialState, playAnimationOnPlayOut, false);
    }

    public C6H(View fadeableView, @Nullable C6I initialState, boolean playAnimationOnPlayOut, boolean visibleWhenPaused) {
        this.G = new GG() { // from class: com.facebook.ads.redexgen.X.6P
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass72 event) {
                this.B.M(1, 0);
            }
        };
        this.H = new GE() { // from class: com.facebook.ads.redexgen.X.6O
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass71 event) {
                if (this.B.B) {
                    if (this.B.E != C6I.FADE_OUT_ON_PLAY && !this.B.J) {
                        this.B.M(0, 8);
                    } else {
                        this.B.E = null;
                        this.B.O();
                    }
                }
            }
        };
        this.F = new GK() { // from class: com.facebook.ads.redexgen.X.6N
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass76 event) {
                if (this.B.E != C6I.INVSIBLE) {
                    this.B.D.setAlpha(1.0f);
                    this.B.D.setVisibility(0);
                }
            }
        };
        this.I = new C6K(this);
        this.B = true;
        this.C = new Handler();
        this.J = playAnimationOnPlayOut;
        this.L = visibleWhenPaused;
        P(fadeableView, initialState);
    }

    public final void A() {
        this.B = false;
        N(null);
    }

    public final boolean B() {
        return this.B;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(int newAlpha, int newVisibility) {
        this.C.removeCallbacksAndMessages(null);
        this.D.clearAnimation();
        this.D.setAlpha(newAlpha);
        this.D.setVisibility(newVisibility);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N(AnimatorListenerAdapter listener) {
        this.D.setVisibility(0);
        this.D.animate().alpha(1.0f).setDuration(500L).setListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O() {
        this.D.animate().alpha(0.0f).setDuration(500L).setListener(new AnimatorListenerAdapter() { // from class: com.facebook.ads.redexgen.X.6J
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animation) {
                this.B.D.setVisibility(8);
            }
        });
    }

    private final void P(View fadeableView, C6I initialState) {
        this.E = initialState;
        this.D = fadeableView;
        this.D.clearAnimation();
        if (initialState == C6I.INVSIBLE) {
            this.D.setAlpha(0.0f);
            this.D.setVisibility(8);
        } else {
            this.D.setAlpha(1.0f);
            this.D.setVisibility(0);
        }
    }

    @Override // com.facebook.ads.redexgen.X.F0
    public final void cC(C0447Co videoView) {
        this.K = videoView;
        videoView.getEventBus().B(this.G, this.H, this.I, this.F);
    }

    @Override // com.facebook.ads.redexgen.X.F0
    public final void wF(C0447Co videoView) {
        M(1, 0);
        videoView.getEventBus().D(this.F, this.I, this.H, this.G);
        this.K = null;
    }
}
