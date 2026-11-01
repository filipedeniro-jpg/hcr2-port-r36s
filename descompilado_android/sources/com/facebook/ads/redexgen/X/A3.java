package com.facebook.ads.redexgen.X;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class A3 extends AbstractC0390Aj {
    public static final /* synthetic */ boolean U;
    private static final int V;
    private static final int W;
    private static final int X;
    private static final int Y;
    private static final int Z;
    private static final int a;
    private static final int b;
    private static final int c;
    private static final int d;
    private final C01481b B;
    private C5Q C;
    private final C0403Aw D;
    private final C01491c E;
    private C6Z F;
    private final Handler G;
    private final int H;
    private C03157m I;
    private final AtomicBoolean J;

    @Nullable
    private C02515a K;
    private final C0618Jf L;

    @Nullable
    private C6F M;
    private final FrameLayout N;
    private boolean O;
    private final C0447Co P;

    @Nullable
    private C0510Ez Q;

    @Nullable
    private C5Q R;

    @Nullable
    private F1 S;
    private final C02605j T;

    static {
        U = !A3.class.desiredAssertionStatus();
        X = (int) (48.0f * J6.B);
        b = (int) (16.0f * J6.B);
        V = (int) (4.0f * J6.B);
        a = (int) (44.0f * J6.B);
        Y = (int) (8.0f * J6.B);
        Z = (int) (J6.B * 12.0f);
        d = (int) (J6.B * 12.0f);
        c = (int) (26.0f * J6.B);
        W = C5W.B(-1, 77);
    }

    public A3(AF params, C1Y colors) {
        super(params, colors, false);
        this.G = new Handler(Looper.getMainLooper());
        this.J = new AtomicBoolean(false);
        this.O = false;
        this.B = params.C();
        this.E = this.B.A().get(0);
        this.L = new C0618Jf(this.B.C(), getAdEventManager());
        this.Q = params.J();
        this.H = this.E.I() ? IronSourceConstants.RV_AUCTION_REQUEST : 0;
        S();
        this.P = O(params);
        this.D = M(params, colors);
        this.T = N(params);
        K();
        this.I = new C03157m(getContext(), params.A(), this.B, this.B.A().get(0), params.D(), params.M(), params.L());
        this.N = new FrameLayout(getContext());
        P();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final boolean A() {
        if (this.Q == null || !this.Q.G() || this.O) {
            return false;
        }
        T();
        return true;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final boolean B() {
        return true;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final void E(C01491c adInfo, String clientToken, double aspectRatio, @Nullable Bundle savedInstanceState, @Nullable D4 onAdShownListener) {
        Bundle bundle = savedInstanceState;
        super.E(adInfo, clientToken, aspectRatio, bundle, onAdShownListener);
        this.D.setInfo(adInfo.D(), adInfo.A(), clientToken, this.B.I().B(), null);
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!bundle.getBoolean("HAVE_STARTING_ANIMATIONS_PLAYED", false)) {
            U();
        }
        if (bundle.getBoolean("WAS_END_CARD_SHOWN", false)) {
            setupContentLayoutForEndCard(false);
        }
        if (bundle.getBoolean("LOSE_REWARD_SCREEN_SHOWN", false)) {
            T();
        }
        if (!bundle.getBoolean("WAS_END_CARD_SHOWN", false) && !bundle.getBoolean("LOSE_REWARD_SCREEN_SHOWN", false) && onAdShownListener != null) {
            onAdShownListener.TD();
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final void F() {
        super.F();
        Q();
        if (this.Q != null && this.S != null) {
            this.Q.setToolbarListener(this.S);
        }
        this.Q = null;
        this.S = null;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final void G(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("HAVE_STARTING_ANIMATIONS_PLAYED", true);
        savedInstanceState.putBoolean("WAS_END_CARD_SHOWN", this.J.get());
        savedInstanceState.putBoolean("LOSE_REWARD_SCREEN_SHOWN", this.O);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final void H(AnonymousClass76 event) {
        super.H(event);
        setupContentLayoutForEndCard(false);
    }

    private void K() {
        this.F = new C6Z(true);
        if (this.K != null) {
            this.F.A(this.K);
        }
        this.F.A(new C5U(this.D.getCTAButton(), 300, -1, -14934495));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(W);
        gradientDrawable.setCornerRadius(V);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(-1);
        gradientDrawable2.setCornerRadius(V);
        this.F.A(new C02555e(this.D.getCTAButton(), 300, gradientDrawable, gradientDrawable2));
        this.F.A(new C5X(this.D.getExpandableLayout(), IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED, false));
        this.F.E(IronSourceConstants.IS_AUCTION_FAILED);
        this.F.cC(this.P);
        this.P.A(new C6Q(this.E.C().B()));
        this.M = new C6F(getContext(), C6G.BOTTOM_HALF);
        this.P.A(this.M);
    }

    private int L(C0510Ez toolbar) {
        return toolbar == null ? C0510Ez.a : toolbar.getToolbarHeight();
    }

    private C0403Aw M(AF params, C1Y colors) {
        C0403Aw c0403Aw = new C0403Aw(params.B(), X, this.E.D().A() == C1T.PAGE_POST, colors, "com.facebook.ads.interstitial.clicked", params.A(), params.D(), params.M(), params.L());
        J6.U(c0403Aw);
        c0403Aw.D(params.H());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        this.C = new C5Q(c0403Aw, 400, 100, 0);
        addView(c0403Aw, layoutParams);
        return c0403Aw;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
    public final void M(C03026z event) {
        super.M(event);
        if (this.P.getDuration() - event.A() < 3000 && this.F.D()) {
            this.F.C();
        }
    }

    private C02605j N(AF params) {
        C02605j c02605jI = params.I();
        if (!U && c02605jI == null) {
            throw new AssertionError();
        }
        c02605jI.setPadding(d, d, d, d);
        c02605jI.B(-1, W);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, c);
        layoutParams.addRule(12);
        addView(c02605jI, layoutParams);
        return c02605jI;
    }

    private C0447Co O(AF params) {
        C0447Co c0447Co = (C0447Co) params.E();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(c0447Co, layoutParams);
        int iL = L(params.J());
        ImageView imageView = (ImageView) params.F();
        if (!U && imageView == null) {
            throw new AssertionError();
        }
        imageView.setPadding(Z, Z, Z, Z);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a, a);
        layoutParams2.setMargins(0, iL, Y, 0);
        layoutParams2.addRule(11);
        layoutParams2.addRule(10);
        c0447Co.addView(imageView, layoutParams2);
        return c0447Co;
    }

    private void P() {
        C0442Cj c0442Cj = new C0442Cj(getContext(), -1, ViewCompat.MEASURED_STATE_MASK, this.B.G().B(), this.B.G().C(), this.B.G().A(), C0605Ir.E(EnumC0606Is.REWARD_ICON));
        c0442Cj.C.setOnClickListener(new AD(this));
        c0442Cj.B.setOnClickListener(new AC(this));
        this.N.addView(c0442Cj, new RelativeLayout.LayoutParams(-1, -1));
    }

    private void Q() {
        this.F.B();
        this.P.J(this.F);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        this.O = false;
        if (this.Q != null) {
            J6.S(this.Q);
        }
        J6.V(this.P, 0);
        J6.M(this.N);
        if (!this.P.F()) {
            this.P.O(C7A.AUTO_STARTED);
        }
    }

    private void S() {
        if (this.Q == null) {
            return;
        }
        if (this.E.G()) {
            this.Q.setCloseButtonStyle(F2.ARROWS);
            this.Q.J(1, false);
        }
        if (this.E.G() || this.E.E()) {
            this.S = this.Q.getToolbarListener();
            this.Q.setToolbarListener(new AE(this));
        }
        this.Q.setLayoutParams(new RelativeLayout.LayoutParams(-1, this.Q.getToolbarHeight()));
        this.Q.A();
        this.R = new C5Q(this.Q, 400, -this.Q.getToolbarHeight(), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T() {
        if (this.Q != null) {
            J6.F(this.Q);
        }
        if (!this.P.F()) {
            this.P.I(false, false);
        }
        J6.V(this.P, 4);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        J6.P(this.N, this.P.getBackground());
        addView(this.N, layoutParams);
        this.O = true;
    }

    private void U() {
        this.G.postDelayed(new AA(this), this.H);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V() {
        if (this.R != null) {
            this.R.XB(true, false);
        }
        this.C.XB(true, false);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b2) {
        super.onLayout(changed, l, t, r, b2);
        if (changed && this.K == null) {
            this.K = new C02515a(this.D.getExpandableLayout(), 300, this.D.getExpandableLayout().getHeight(), 0);
            this.F.A(this.K);
            this.F.F();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupContentLayoutForEndCard(boolean skipButtonClicked) {
        if (!this.E.G()) {
        }
        this.J.set(true);
        if (this.Q != null && this.S != null) {
            J6.S(this.Q);
            this.Q.B();
            this.Q.J(4, true);
            this.Q.setToolbarActionMessage("");
            this.G.postDelayed(new AB(this), skipButtonClicked ? 1000L : 0L);
        }
        J6.M(this.T);
        J6.G(this);
        J6.M(this.P);
        J6.M(this.N);
        if (this.M != null && JA.K(getContext())) {
            J6.N(this.M.getGradientBackgroundView());
            addView(this.M.getGradientBackgroundView());
            J6.P(this, this.P.getBackground());
            this.D.bringToFront();
        }
        Pair<EnumC03177o, View> pairA = this.I.A();
        int iL = L(this.Q);
        switch (A4.B[((EnumC03177o) pairA.first).ordinal()]) {
            case 1:
                this.D.setVisibility(0);
                this.D.A(true);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.setMargins(0, iL, 0, 0);
                layoutParams.addRule(2, this.D.getId());
                addView((View) pairA.second, layoutParams);
                break;
            case 2:
                J6.N(this.D);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                int i = b;
                int toolbarHeight = b;
                layoutParams2.setMargins(i, toolbarHeight, b, b);
                addView((View) pairA.second, layoutParams2);
                break;
        }
    }
}
