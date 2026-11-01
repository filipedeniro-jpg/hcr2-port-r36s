package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.view.GravityCompat;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class EU extends C0510Ez {
    private static final int Q = (int) (16.0f * J6.B);
    private static final int R = (int) (72.0f * J6.B);
    private final C0579Hr B;
    private final InterfaceC0525Fo C;
    private final C0618Jf D;
    private int E;
    private boolean F;
    private final GK G;
    private final FO H;
    private int I;
    private boolean J;
    private int K;
    private final CE L;
    private C01631q M;

    @Nullable
    private F1 N;
    private int O;

    @Nullable
    private C0447Co P;

    public EU(Context context, InterfaceC0525Fo listener, C0618Jf funnelLoggingHandler, int toolbarActionMode, int anLogoType) {
        super(context, listener, funnelLoggingHandler);
        this.H = new C0509Ey(this);
        this.G = new C0498En(this);
        this.J = false;
        this.L = new CE(context, toolbarActionMode);
        this.B = new C0579Hr(context, anLogoType);
        this.K = toolbarActionMode;
        this.D = funnelLoggingHandler;
        this.C = listener;
        FrameLayout frameLayout = new FrameLayout(context);
        addView(frameLayout, new LinearLayout.LayoutParams(-1, -1));
        setPadding(Q, Q, Q, Q);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -1);
        layoutParams.gravity = GravityCompat.START;
        J6.V(this.L, 8);
        frameLayout.addView(this.L, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -1);
        layoutParams2.gravity = GravityCompat.END;
        frameLayout.addView(this.B, layoutParams2);
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final void A() {
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final void B() {
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final void C() {
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final void D(C01551i pageDetails, String clientToken, int anLogoType) {
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final void E(boolean closeButtonEnabled) {
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final boolean F() {
        return this.L.A();
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final boolean G() {
        return this.L.C != 2 && this.F && this.I > 0;
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final void H(C1Y colors, boolean fullScreenEnabled) {
        this.L.setColors(colors.A(true));
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final void I(C01551i pageDetails, String clientToken, int unskippableSeconds, C01631q toolbarDetails, boolean isRewardedAd, int secondsForReward) {
        this.L.setInitialUnskippableSeconds(unskippableSeconds);
        this.L.setSkipMessage(toolbarDetails.B());
        this.O = unskippableSeconds;
        this.I = secondsForReward;
        this.E = secondsForReward;
        this.M = toolbarDetails;
        this.F = isRewardedAd;
        String strA = toolbarDetails.A();
        if (!isRewardedAd) {
            secondsForReward = 0;
        }
        N(strA, secondsForReward);
        this.B.setOnClickListener(M(clientToken));
        J6.V(this.L, 0);
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final void J(int actionMode, boolean forceAction) {
        this.K = actionMode;
        if (this.L.C != 2 || forceAction) {
            this.L.setToolbarActionMode(actionMode);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final void K(boolean shouldShow) {
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public final void L() {
    }

    private View.OnClickListener M(String clientToken) {
        return new ViewOnClickListenerC0496El(this, clientToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N(String message, int secondsForReward) {
        if (secondsForReward > 0) {
            this.L.setToolbarMessage(message.replace("[secs]", String.valueOf(secondsForReward)));
            return;
        }
        this.L.setToolbarMessage("");
        J(this.K, false);
        if (this.J) {
            return;
        }
        this.C.aB("com.facebook.ads.interstitial.reward");
        this.J = true;
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez, com.facebook.ads.redexgen.X.F0
    public final void cC(C0447Co videoView) {
        this.P = videoView;
        this.P.getEventBus().B(this.H, this.G);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRemainingSecondsForReward() {
        if (this.P == null || !this.F) {
            return 0;
        }
        int iMin = (int) ((Math.min(this.E * 1000.0f, this.P.getDuration()) - this.P.getCurrentPositionInMillis()) / 1000.0f);
        this.I = iMin;
        return iMin;
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public int getToolbarHeight() {
        return R;
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    @Nullable
    public F1 getToolbarListener() {
        return this.N;
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public void setCloseButtonStyle(F2 closeButtonStyle) {
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public void setPageDetailsVisibility(int visibility) {
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public void setProgress(float percentage) {
        this.L.setProgress(percentage);
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public void setShowPageDetails(boolean showPageDetails) {
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public void setToolbarActionMessage(String message) {
        this.L.setToolbarMessage(message);
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez
    public void setToolbarListener(F1 toolbarListener) {
        this.N = toolbarListener;
        this.L.setActionClickListener(new ViewOnClickListenerC0487Ec(this, toolbarListener));
    }

    @Override // com.facebook.ads.redexgen.X.C0510Ez, com.facebook.ads.redexgen.X.F0
    public final void wF(C0447Co videoView) {
        if (this.P != null) {
            this.P.getEventBus().D(this.H, this.G);
            this.P = null;
        }
    }
}
