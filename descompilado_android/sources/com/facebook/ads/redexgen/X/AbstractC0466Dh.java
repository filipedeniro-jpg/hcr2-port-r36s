package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC0466Dh extends RelativeLayout implements InterfaceC0578Hq {
    public C1N B;
    public final C01481b C;
    public final JV D;
    public final C0510Ez E;
    public final C0618Jf F;
    private final InterfaceC0525Fo G;
    private String H;

    @Nullable
    private C0462Dd I;

    @Nullable
    private D4 J;
    private boolean K;
    private final KX L;

    @Nullable
    private MS M;

    public AbstractC0466Dh(Context context, JV adEventManager, InterfaceC0525Fo listener, C01481b dataBundle) {
        super(context.getApplicationContext());
        this.K = false;
        this.D = adEventManager;
        this.G = listener;
        this.L = new KX(this);
        this.C = dataBundle;
        this.F = new C0618Jf(this.C.C(), this.D);
        if (JA.PB(context) && JA.ZB(context)) {
            this.E = new EU(getContext(), getAudienceNetworkListener(), this.F, 0, dataBundle.B());
        } else {
            this.E = new C0510Ez(getContext(), getAudienceNetworkListener(), this.F, F2.CROSS);
        }
    }

    public final void A(AbstractC0390Aj contentView, @Nullable AbstractC03789x browseView, @Nullable InterfaceC0468Dj dragListener, int browseViewScrollHeight, int browseViewWidth, boolean isFullscreen, int orientation) {
        B(contentView, isFullscreen, orientation);
        if (browseView != null) {
            this.E.setPageDetailsVisibility(4);
            this.L.C(EnumC0641Kc.DEFAULT);
            if (orientation == 1) {
                CJ cj = new CJ(getContext(), browseView, browseViewScrollHeight - this.E.getToolbarHeight(), 0);
                addView(cj);
                if (dragListener != null) {
                    cj.setDragListener(dragListener);
                    return;
                }
                return;
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(browseViewWidth, -1);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(J6.C.widthPixels - browseViewWidth, this.E.getToolbarHeight());
            layoutParams2.addRule(10);
            this.E.setLayoutParams(layoutParams2);
            layoutParams.addRule(11);
            contentView.addView(browseView, layoutParams);
        }
    }

    public final void B(View contentView, boolean isFullscreen, int orientation) {
        this.L.C(EnumC0641Kc.DEFAULT);
        d();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, isFullscreen ? 0 : this.E.getToolbarHeight(), 0, 0);
        addView(contentView, layoutParams);
        C1Y c1yC = c(orientation);
        b(c1yC, isFullscreen);
        J6.O(this, c1yC.D(isFullscreen));
        if (this.G != null) {
            e();
            if (isFullscreen && Build.VERSION.SDK_INT >= 19) {
                this.L.C(EnumC0641Kc.FULL_SCREEN);
            }
        }
    }

    public final void C(Map<String, String> map) {
        if (this.H == null) {
            return;
        }
        map.put("extra_hints", this.H);
    }

    public final boolean D() {
        return !this.C.A().isEmpty() && this.C.A().get(0).H();
    }

    public final boolean E() {
        return !this.C.A().isEmpty() && this.C.A().get(0).I();
    }

    public final void F(C0531Fu activityImpl) {
        int iF;
        this.L.A(activityImpl.B().getWindow());
        this.B = this.C.D();
        this.H = this.C.F();
        C01491c c01491c = null;
        if (this.C.A() != null) {
            C01491c adInfo = this.C.A().get(0);
            if (adInfo != null) {
                c01491c = this.C.A().get(0);
            }
        }
        C0510Ez c0510Ez = this.E;
        C01551i c01551iI = this.C.I();
        String strC = this.C.C();
        if (c01491c != null) {
            iF = c01491c.C().F();
        } else {
            iF = 0;
        }
        c0510Ez.I(c01551iI, strC, iF, this.C.K(), c01491c != null && c01491c.E(), c01491c != null ? c01491c.C().E() : 0);
        this.E.setToolbarListener(new D0(this, activityImpl));
        if (C01972y.Q(getContext(), true)) {
            this.E.D(this.C.I(), this.C.C(), this.C.B());
        }
    }

    public final void G(C0531Fu activityImpl) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(200L);
        alphaAnimation.setAnimationListener(new AnimationAnimationListenerC0457Cy(this, this, activityImpl));
        startAnimation(alphaAnimation);
    }

    public final boolean H() {
        return this.K;
    }

    public final void I(int unskippableSec) {
        this.M = new MS(unskippableSec, new C0454Cv(this, unskippableSec));
        this.M.E();
    }

    private void b(C1Y colorInfo, boolean isFullscreen) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.E.getToolbarHeight());
        layoutParams.addRule(10);
        this.E.H(colorInfo, isFullscreen);
        addView(this.E, layoutParams);
    }

    private C1Y c(int orientation) {
        return orientation == 1 ? this.B.B() : this.B.A();
    }

    private void d() {
        removeAllViews();
        J6.M(this);
    }

    private void e() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (E() && this.I == null) {
            this.K = true;
            this.I = new C0462Dd(getContext(), this.C);
            C0620Jh.C(this.I, this.F, EnumC0617Je.INTRO_CARD_SHOWN);
            this.G.VB(this, 0, layoutParams);
            this.G.VB(this.I, 1, layoutParams);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setStartOffset(1450L);
            alphaAnimation.setDuration(200L);
            alphaAnimation.setAnimationListener(new AnimationAnimationListenerC0456Cx(this));
            startAnimation(alphaAnimation);
            return;
        }
        this.G.VB(this, 0, layoutParams);
        if (this.J == null) {
            return;
        }
        this.J.TD();
    }

    public JV getAdEventManager() {
        return this.D;
    }

    public InterfaceC0525Fo getAudienceNetworkListener() {
        return this.G;
    }

    public void nE(boolean launchAdReporting) {
        if (this.M != null && this.M.C()) {
            this.M.m263D();
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration newConfig) {
        this.E.C();
        super.onConfigurationChanged(newConfig);
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserverOnGlobalLayoutListenerC0455Cw(this, viewTreeObserver));
    }

    public void onDestroy() {
        this.L.B();
        this.E.setToolbarListener(null);
        d();
    }

    public void setListener(InterfaceC0525Fo listener) {
    }

    public void setOnAdShownListener(D4 onAdShownListener) {
        this.J = onAdShownListener;
    }

    public void yE(boolean leaveAdReporting) {
        if (this.M != null && !this.M.B()) {
            this.M.E();
        }
    }
}
