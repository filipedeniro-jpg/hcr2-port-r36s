package com.facebook.ads.redexgen.X;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5e, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02555e implements InterfaceC02585h {
    private final int B;
    private final Drawable C;
    private TransitionDrawable D;
    private final Drawable E;
    private final View H;
    private TransitionDrawable I;
    private final Handler G = new Handler();
    private EnumC02595i F = EnumC02595i.REVERSE_ANIMATED;

    public C02555e(View targetView, int durationMs, Drawable startDrawable, Drawable endDrawable) {
        this.B = durationMs;
        this.H = targetView;
        this.E = startDrawable;
        this.C = endDrawable;
        this.I = new TransitionDrawable(new Drawable[]{startDrawable, endDrawable});
        this.I.setCrossFadeEnabled(true);
        this.D = new TransitionDrawable(new Drawable[]{endDrawable, startDrawable});
        this.D.setCrossFadeEnabled(true);
        J6.P(this.H, this.I);
    }

    private void F(boolean animate) {
        this.G.removeCallbacksAndMessages(null);
        if (animate) {
            this.F = EnumC02595i.REVERSE_ANIMATING;
            J6.P(this.H, this.D);
            this.D.startTransition(this.B);
            this.G.postDelayed(new Runnable() { // from class: com.facebook.ads.redexgen.X.5f
                @Override // java.lang.Runnable
                public final void run() {
                    this.B.F = EnumC02595i.REVERSE_ANIMATED;
                    J6.P(this.B.H, this.B.E);
                }
            }, this.B);
            return;
        }
        J6.P(this.H, this.E);
        this.F = EnumC02595i.REVERSE_ANIMATED;
    }

    private void G(boolean animate) {
        this.G.removeCallbacksAndMessages(null);
        if (animate) {
            this.F = EnumC02595i.ANIMATING;
            J6.P(this.H, this.I);
            this.I.startTransition(this.B);
            this.G.postDelayed(new Runnable() { // from class: com.facebook.ads.redexgen.X.5g
                @Override // java.lang.Runnable
                public final void run() {
                    this.B.F = EnumC02595i.ANIMATED;
                    J6.P(this.B.H, this.B.C);
                }
            }, this.B);
            return;
        }
        J6.P(this.H, this.C);
        this.F = EnumC02595i.ANIMATED;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final EnumC02595i JC() {
        return this.F;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final void XB(boolean showAnimation, boolean isReverse) {
        if (isReverse) {
            F(showAnimation);
        } else {
            G(showAnimation);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02585h
    public final void cancel() {
        this.G.removeCallbacksAndMessages(null);
        this.I.resetTransition();
        this.D.resetTransition();
        this.F = this.F == EnumC02595i.ANIMATING ? EnumC02595i.REVERSE_ANIMATED : EnumC02595i.ANIMATED;
    }
}
