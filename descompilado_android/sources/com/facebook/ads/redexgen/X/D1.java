package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class D1 extends AbstractC0466Dh {

    @Nullable
    private C0531Fu B;
    private final H2 C;
    private final C01491c D;
    private final C7H E;
    private final InterfaceC0528Fr F;

    @Nullable
    private AbstractC0390Aj G;
    private final AtomicBoolean H;
    private boolean I;
    private final AtomicBoolean J;
    private boolean K;
    private boolean L;
    private final C6A M;
    private final D4 N;
    private final GK O;
    private final GO P;
    private final GG Q;
    private final GE R;
    private final GC S;
    private final FO T;
    private boolean U;

    @Nullable
    private AbstractC03789x V;
    private final C0621Ji W;
    private final C02605j X;

    @Nullable
    private C7A Y;
    private final C0447Co Z;
    private final C5I a;
    private boolean b;
    private final C1B c;

    public D1(Context context, JV adEventManager, C01481b dataBundle, H2 adCacheManager, InterfaceC0525Fo listener) {
        super(context, adEventManager, listener, dataBundle);
        this.F = new DB(this);
        this.P = new DA(this);
        this.R = new D9(this);
        this.T = new D8(this);
        this.Q = new D7(this);
        this.O = new D6(this);
        this.S = new D5(this);
        this.W = new C0621Ji();
        this.J = new AtomicBoolean(false);
        this.H = new AtomicBoolean(false);
        this.N = new D3(this);
        this.K = false;
        this.U = false;
        this.I = false;
        this.L = false;
        this.b = false;
        this.Z = new C0447Co(getContext());
        this.Z.setFunnelLoggingHandler(super.F);
        this.Z.setVideoProgressReportIntervalMs(dataBundle.L());
        J6.U(this.Z);
        J6.O(this.Z, 0);
        this.D = dataBundle.A().get(0);
        this.C = adCacheManager;
        this.X = new C02605j(getContext());
        this.M = new C6A(context, super.F);
        this.Z.getEventBus().B(this.R, this.T, this.Q, this.O, this.P, this.S);
        setupPlugins(this.D);
        this.c = new D2(this);
        this.a = new C5I(this, 1, this.c);
        this.a.D(dataBundle.M());
        this.a.E(dataBundle.N());
        this.E = new C7G(getContext(), super.D, this.Z, super.C.C());
    }

    private void S(int orientation) {
        if (this.G == null) {
            return;
        }
        if (this.V == null || !this.V.B()) {
            this.V = A1.B(V(orientation), J6.C.heightPixels - this.G.getExactMediaHeightIfAvailable(), J6.C.widthPixels - this.G.getExactMediaWidthIfAvailable(), this.I);
        }
        DC dc = null;
        if (this.V != null) {
            dc = new DC(this);
        }
        A(this.G, this.V, dc, this.G.getExactMediaHeightIfAvailable(), J6.C.widthPixels - this.G.getExactMediaWidthIfAvailable(), this.G.B(), orientation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T() {
        if (this.Z.getParent() == null) {
            return;
        }
        C01491c c01491c = super.C.A().get(0);
        this.Z.setVolume(c01491c.C().I() ? 0.0f : 1.0f);
        if (!this.Z.F() && this.Z.m242M()) {
            this.Z.O(C7A.AUTO_STARTED);
        }
        if (c01491c.C().F() <= 0) {
            return;
        }
        postDelayed(new DD(this), JA.g(getContext()));
    }

    private void U(int orientation, @Nullable Bundle savedInstanceState) {
        AF afV = V(orientation);
        if (this.G != null) {
            this.G.F();
        }
        this.G = AU.B(afV, savedInstanceState, false);
    }

    private AF V(int orientation) {
        return new AG(getContext(), super.D, getAudienceNetworkListener(), super.C, this.Z, this.a, this.W).G(super.E.getToolbarHeight()).D(orientation).E(this.X).B(this.M).F(super.E).C(this.N).A();
    }

    private void W(@Nullable Bundle savedInstanceState, C01491c adInfo, int orientation) {
        if (this.G != null) {
            this.G.E(adInfo, super.C.C(), C0391Ak.B(adInfo), savedInstanceState, this.N);
        }
        X();
        S(orientation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X() {
        this.M.setVisibility(this.H.get() ? 0 : 8);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void bC(Intent intent, @Nullable Bundle savedInstanceState, C0531Fu activityImpl) {
        super.F(activityImpl);
        this.B = activityImpl;
        C01491c c01491c = super.C.A().get(0);
        int i = activityImpl.B().getResources().getConfiguration().orientation;
        U(i, savedInstanceState);
        this.Z.setVideoURI(this.C.K(this.D.C().H()));
        W(savedInstanceState, c01491c, i);
        if (this.B != null) {
            this.B.A(this.F);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void nE(boolean launchAdReporting) {
        if (this.V != null) {
            this.V.G();
        }
        if (this.K || this.Z.E() || this.Z.getParent() == null) {
            return;
        }
        this.Y = this.Z.getVideoStartReason();
        this.U = launchAdReporting;
        this.Z.H(false);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void oF(Bundle savedInstanceState) {
        if (this.G != null) {
            this.G.G(savedInstanceState);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, android.view.View
    public final void onConfigurationChanged(Configuration config) {
        Bundle bundle = new Bundle();
        oF(bundle);
        removeAllViews();
        J6.M(this.Z);
        J6.M(this.X);
        J6.M(this.M);
        if (this.V != null) {
            J6.M(this.V);
            this.I = this.V.B();
        }
        if (super.C.A() == null) {
            return;
        }
        U(config.orientation, bundle);
        W(bundle, super.C.A().get(0), config.orientation);
        super.onConfigurationChanged(config);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void onDestroy() {
        if (!this.K) {
            if (!this.J.get()) {
                this.Z.m243N();
            }
            if (super.C != null && !TextUtils.isEmpty(super.C.C())) {
                HashMap map = new HashMap();
                this.a.A(map);
                map.put("touch", MJ.C(this.W.m258B()));
                super.D.iC(super.C.C(), map);
            }
            if (this.G != null) {
                this.G.F();
            }
            this.Z.P();
            this.Z.B();
            this.Z.getEventBus().D(this.R, this.T, this.Q, this.O, this.P, this.S);
            this.K = true;
        }
        if (this.V != null) {
            this.V.F();
        }
        this.a.G();
        this.B = null;
        super.onDestroy();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent event) {
        this.W.F(event, this, this);
        return super.onInterceptTouchEvent(event);
    }

    private void setupPlugins(C01491c adInfo) {
        this.Z.K();
        this.Z.A(this.X);
        this.Z.A(this.M);
        if (!TextUtils.isEmpty(adInfo.C().B())) {
            AnonymousClass67 anonymousClass67 = new AnonymousClass67(getContext());
            this.Z.A(anonymousClass67);
            anonymousClass67.setImage(adInfo.C().B());
        }
        C02675q c02675q = new C02675q(getContext(), true, super.F);
        this.Z.A(c02675q);
        this.Z.A(new C6H(c02675q, C6I.FADE_OUT_ON_PLAY, true));
        this.Z.A(new C02735w(getContext()));
        this.Z.A(super.E);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void yE(boolean leaveAdReporting) {
        if (this.V != null) {
            this.V.H();
        }
        if (!this.K && this.Z.getParent() != null && !this.Z.F()) {
            if ((this.Z.getState() == C5K.PREPARED && this.Z.getVideoStartReason() == C7A.NOT_STARTED) || this.Z.getState() == C5K.PLAYBACK_COMPLETED || this.Y == null) {
                return;
            }
            if (this.U && !leaveAdReporting) {
                return;
            }
            this.Z.O(this.Y);
        }
    }
}
