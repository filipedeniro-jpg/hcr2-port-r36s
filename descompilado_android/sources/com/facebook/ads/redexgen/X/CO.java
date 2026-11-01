package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class CO extends RelativeLayout implements InterfaceC0578Hq, CP {
    public static final /* synthetic */ boolean n;
    private static final RelativeLayout.LayoutParams o;
    private static final int p;
    private static final int q;
    private static final int r;
    private static final int s;
    private static final int t;
    private static final int u;
    private final C01601n B;

    @Nullable
    private C6H C;

    @Nullable
    private AnonymousClass80 D;
    private final JV E;
    private WeakReference<C0531Fu> F;

    @Nullable
    private InterfaceC0525Fo G;
    private final InterfaceC0528Fr H;
    private boolean I;
    private final C1Y J;
    private final RelativeLayout K;

    @Nullable
    private Context L;

    @Nullable
    private Toast M;
    private C03157m N;
    private final C6Q O;
    private final C0618Jf P;
    private final AtomicBoolean Q;
    private boolean R;
    private final C6A S;
    private final GK T;
    private final GO U;
    private final GE V;
    private final GC W;
    private final FO X;
    private boolean Y;

    @Nullable
    private Integer Z;
    private final Handler a;

    @Nullable
    private C0510Ez b;
    private final C0621Ji c;

    @Nullable
    private C02675q d;
    private final C7G e;
    private boolean f;
    private final C02605j g;
    private CQ h;

    @Nullable
    private C7A i;

    @Nullable
    private C0447Co j;
    private final C5I k;
    private boolean l;
    private final C1B m;

    static {
        n = !CO.class.desiredAssertionStatus();
        q = (int) (12.0f * J6.B);
        r = (int) (18.0f * J6.B);
        u = (int) (64.0f * J6.B);
        t = (int) (16.0f * J6.B);
        p = (int) (72.0f * J6.B);
        s = (int) (28.0f * J6.B);
        o = new RelativeLayout.LayoutParams(-1, -1);
    }

    public CO(Context context, JV adEventManager, C0447Co videoView, InterfaceC0525Fo listener, C01601n adData) {
        super(context);
        this.H = new C0433Ca(this);
        this.T = new CZ(this);
        this.U = new CY(this);
        this.W = new CX(this);
        this.V = new CW(this);
        this.X = new CU(this);
        this.c = new C0621Ji();
        this.Q = new AtomicBoolean(false);
        this.a = new Handler();
        this.f = false;
        this.Y = false;
        this.I = false;
        this.R = false;
        this.l = false;
        this.L = context;
        this.G = listener;
        this.E = adEventManager;
        this.B = adData;
        this.P = new C0618Jf(this.B.A(), this.E);
        this.j = videoView;
        this.j.setFunnelLoggingHandler(this.P);
        this.J = this.B.J().B();
        this.K = new RelativeLayout(context);
        this.g = new C02605j(this.L);
        this.S = new C6A(this.L, this.P);
        this.O = new C6Q(this.B.K().B());
        this.m = new CT(this);
        this.k = new C5I(this, 1, this.m);
        this.k.D(250);
        this.e = new C7G(this.L, this.E, this.j, this.B.A());
        this.N = new C03157m(this.L, this.E, this.B, this.G, this.k, this.c);
        if (!n && this.j == null) {
            throw new AssertionError();
        }
        this.j.setVideoProgressReportIntervalMs(adData.F());
        if (JA.JB(this.L)) {
            J6.O(this.j, ViewCompat.MEASURED_STATE_MASK);
        }
        this.j.getEventBus().B(this.T, this.U, this.W, this.V, this.X);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void bC(Intent intent, Bundle savedInstanceState, C0531Fu activityImpl) {
        if (this.j == null || this.G == null) {
            return;
        }
        this.F = new WeakReference<>(activityImpl);
        k();
        activityImpl.A(this.H);
        this.j.setVideoURI(new H2(getContext()).K(this.B.K().H()));
        setUpContentLayoutForVideo(activityImpl.B().getResources().getConfiguration().orientation);
        addView(this.K, o);
        if (this.b != null) {
            J6.U(this.b);
            this.b.H(this.J, true);
            if (C01972y.Q(getContext(), true)) {
                this.b.D(this.B.P(), this.B.A(), this.B.M());
            }
            addView(this.b, new RelativeLayout.LayoutParams(-1, this.b.getToolbarHeight()));
        }
        this.G.WB(this, o);
        postDelayed(new CS(this), JA.g(getContext()));
    }

    private void c() {
        if (this.L == null) {
            return;
        }
        FrameLayout frameLayout = new FrameLayout(this.L);
        frameLayout.setLayoutParams(o);
        J6.O(frameLayout, -1509949440);
        this.K.addView(frameLayout, 0);
    }

    private void d() {
        C0531Fu c0531Fu = this.F.get();
        if (c0531Fu != null) {
            this.Z = Integer.valueOf(c0531Fu.B().getRequestedOrientation());
            C0580Hs.B(c0531Fu.B(), 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        return (this.D == null || this.D.getCTAButton().A()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(String errorMessage) {
        int currentPositionInMillis = 0;
        int duration = 0;
        if (this.j != null) {
            currentPositionInMillis = this.j.getCurrentPositionInMillis();
            duration = this.j.getDuration();
        }
        m();
        C0585Hx.D(this.L, "video", C0584Hw.tB, new C0583Hv(errorMessage));
        if (JA.z(this.L)) {
            g(new AnonymousClass76(currentPositionInMillis, duration));
        } else {
            if (this.G == null) {
                return;
            }
            this.G.aB(EnumC02926p.REWARDED_VIDEO_ERROR.B());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(AnonymousClass76 event) {
        this.N.C();
        h();
        j();
        if (this.G != null) {
            this.G.bB(EnumC02926p.REWARDED_VIDEO_COMPLETE.B(), event);
        }
    }

    public int getCurrentPosition() {
        if (this.j != null) {
            return this.j.getCurrentPositionInMillis();
        }
        return 0;
    }

    private int getToolbarHeight() {
        return this.b == null ? C0510Ez.a : this.b.getToolbarHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.M != null) {
            this.M.cancel();
        }
        if (this.I && e()) {
            this.D.C(this.B.N(), this.B.A());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.b == null) {
            return;
        }
        this.b.setCloseButtonStyle(F2.CROSS);
        this.b.E(true);
        this.b.J(0, true);
        this.b.setToolbarActionMessage("");
    }

    private void j() {
        this.f = true;
        c();
        J6.G(this.K);
        J6.O(this.K, ViewCompat.MEASURED_STATE_MASK);
        if (this.j != null) {
            this.j.K();
            this.j.setVisibility(4);
        }
        if (this.b == null) {
        }
        this.b.J(4, true);
        if (this.b.F()) {
            this.b.B();
            new Handler(Looper.getMainLooper()).postDelayed(new RunnableC0435Cc(this), 1000L);
        } else {
            i();
        }
        this.b.A();
        J6.N(this.j, this.S, this.g);
        Pair<EnumC03177o, View> pairA = this.N.A();
        switch (C0434Cb.B[((EnumC03177o) pairA.first).ordinal()]) {
            case 1:
                if (this.D != null) {
                    this.D.setVisibility(0);
                    this.D.A(true);
                }
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.setMargins(0, this.b.getToolbarHeight(), 0, 0);
                layoutParams.addRule(2, this.D.getId());
                this.K.addView((View) pairA.second, layoutParams);
                this.c.G();
                break;
            case 2:
                J6.N(this.D);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams2.setMargins(t, t, t, t);
                this.K.addView((View) pairA.second, layoutParams2);
                this.c.G();
                break;
            case 3:
                d();
                this.K.removeAllViews();
                J6.M(this.b);
                this.K.addView((View) pairA.second, o);
                ((AnonymousClass81) pairA.second).A();
                break;
        }
    }

    private void k() {
        int iF;
        if (this.j == null) {
            return;
        }
        this.j.K();
        this.j.A(new C02735w(this.L));
        this.j.A(this.S);
        this.j.A(this.g);
        this.j.A(this.O);
        this.d = new C02675q(this.L, true, this.P);
        View view = new View(this.L);
        view.setLayoutParams(o);
        J6.O(view, -1509949440);
        C6H mOverlayFadeablePlugin = new C6H(view, C6I.FADE_OUT_ON_PLAY, true);
        this.j.addView(view);
        this.j.A(mOverlayFadeablePlugin);
        C6H c6h = new C6H(this.d, C6I.FADE_OUT_ON_PLAY, true);
        this.j.A(this.d);
        this.j.A(c6h);
        this.D = new AnonymousClass80(this.L, p, this.J, this.E, this.G, this.N.B() == EnumC03177o.INFO, this.N.B() == EnumC03177o.INFO, this.k, this.c);
        this.D.setInfo(this.B.L(), this.B.N(), this.B.A(), this.B.P().B(), this);
        this.C = new C6H(this.D, C6I.FADE_OUT_ON_PLAY, true);
        C0447Co c0447Co = this.j;
        C6H fadeablePlugin = this.C;
        c0447Co.A(fadeablePlugin);
        F2 f2 = JA.WB(this.L) ? F2.ARROWS : F2.CROSS;
        if (this.L == null) {
            return;
        }
        if (JA.ZB(getContext())) {
            this.b = new EU(this.L, this.G, this.P, 1, this.B.M());
        } else {
            this.b = new C0510Ez(this.L, this.G, this.P, f2);
        }
        if (this.B.K().F() < 0) {
            iF = this.B.K().G();
        } else {
            iF = this.B.K().F();
        }
        this.b.I(this.B.P(), this.B.A(), iF, this.B.R(), true, this.B.K().E());
        int unskippable_seconds = this.B.K().F();
        if (unskippable_seconds < 0) {
            this.b.B();
            this.b.J(4, true);
        }
        if (this.N.B() != EnumC03177o.INFO) {
            this.b.A();
        }
        this.b.setToolbarListener(new CR(this));
        this.j.A(this.b);
    }

    private void l() {
        if (this.j != null) {
            if (this.M != null && this.M.getView().getWindowVisibility() == 0) {
                return;
            }
            if (this.M == null) {
                this.M = Toast.makeText(getContext(), this.B.N().A(), 1);
            }
            n((this.j.getDuration() - this.j.getCurrentPositionInMillis()) / 1000);
            this.M.show();
        }
    }

    private final void m() {
        if (this.j != null) {
            this.j.P();
            this.j.B();
        }
        if (this.k != null) {
            this.k.G();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(int remainingTimeInSec) {
        J6.L(this.M, this.B.N().A().replace("[secs]", String.valueOf(remainingTimeInSec)), 49, 0, u);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void nE(boolean launchAdReporting) {
        if (this.j == null || this.j.E()) {
            return;
        }
        this.i = this.j.getVideoStartReason();
        this.Y = launchAdReporting;
        this.j.H(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.S.setVisibility(this.Q.get() ? 0 : 8);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void oF(Bundle savedInstanceState) {
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration newConfig) {
        if (this.D != null) {
            this.D.D(newConfig.orientation);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void onDestroy() {
        m();
        if (this.j != null) {
            this.j.getEventBus().D(this.T, this.U, this.W, this.V, this.X);
        }
        if (!TextUtils.isEmpty(this.B.A())) {
            HashMap map = new HashMap();
            this.k.A(map);
            map.put("touch", MJ.C(this.c.m258B()));
            this.E.iC(this.B.A(), map);
        }
        if (this.b != null) {
            this.b.setToolbarListener(null);
        }
        if (this.Z != null && this.F.get() != null) {
            C0580Hs.B(this.F.get().B(), this.Z.intValue());
        }
        this.e.L();
        this.j = null;
        this.D = null;
        this.C = null;
        this.G = null;
        this.L = null;
        this.M = null;
        this.g.A();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent event) {
        this.c.F(event, this, this);
        return super.onInterceptTouchEvent(event);
    }

    @Override // com.facebook.ads.redexgen.X.CP
    public final void pD() {
        this.I = true;
        if (JA.UB(getContext()) && e()) {
            l();
        }
    }

    @VisibleForTesting
    public void setEndCardController(C03157m endCardController) {
        this.N = endCardController;
    }

    public void setListener(InterfaceC0525Fo listener) {
    }

    private void setUpContentLayoutForVideo(int orientation) {
        this.K.removeAllViews();
        this.K.addView(this.j, o);
        if (this.D != null) {
            J6.U(this.D);
            this.D.D(orientation);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(12);
            this.D.setPadding(t, t, t, t);
            this.K.addView(this.D, layoutParams);
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(s, s);
        layoutParams2.addRule(10);
        layoutParams2.addRule(11);
        layoutParams2.setMargins(q, q + getToolbarHeight(), q, r);
        this.K.addView(this.S, layoutParams2);
        o();
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(12);
        this.K.addView(this.g, layoutParams3);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void yE(boolean leaveAdReporting) {
        if (this.j == null || this.j.F() || this.j.getState() == C5K.PLAYBACK_COMPLETED || this.i == null) {
            return;
        }
        if (this.Y && !leaveAdReporting) {
            return;
        }
        this.j.O(this.i);
    }
}
