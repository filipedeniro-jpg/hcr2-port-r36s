package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Cd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@TargetApi(16)
public final class C0436Cd extends RelativeLayout implements InterfaceC0578Hq, DG {

    @Nullable
    private C0531Fu B;
    private final C1N C;
    private final C01601n D;
    private final JV E;
    private AnonymousClass17 F;

    @Nullable
    private InterfaceC0525Fo G;
    private final InterfaceC0528Fr H;
    private boolean I;

    @Nullable
    private Context J;
    private boolean K;
    private Executor L;
    private final C01571k M;
    private AnonymousClass81 N;
    private int O;

    public C0436Cd(Context context, JV adEventManager, InterfaceC0525Fo listener, C01601n adData) {
        super(context);
        this.L = LD.E;
        this.H = new C0440Ch(this);
        this.J = context;
        this.G = listener;
        this.E = adEventManager;
        this.D = adData;
        this.M = adData.K().D();
        this.C = adData.J();
    }

    @NonNull
    private B0 D(BB adWebView) {
        return new B0(this.J, true, false, EnumC02926p.REWARDED_VIDEO_AD_CLICK.B(), this.C.B(), this.E, this.G, adWebView.getViewabilityChecker(), adWebView.getTouchDataRecorder());
    }

    private void E(C0531Fu activityImpl) {
        AudienceNetworkActivity audienceNetworkActivityB = activityImpl.B();
        this.O = audienceNetworkActivityB.getRequestedOrientation();
        switch (C0438Cf.B[this.M.E().ordinal()]) {
            case 1:
                C0580Hs.B(audienceNetworkActivityB, 1);
                break;
            case 2:
                C0580Hs.B(audienceNetworkActivityB, 0);
                break;
            case 3:
                C0580Hs.B(audienceNetworkActivityB, -1);
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.facebook.ads.redexgen.X.17] */
    @NonNull
    private AnonymousClass17 F(final C5I adViewabilityChecker, final C0621Ji touchDataRecorder) {
        if (this.F != null) {
            return this.F;
        }
        final C0R impressionHelper = new C0439Cg(this);
        final Context context = getContext();
        final JV jv = this.E;
        this.F = new C0Q(context, jv, adViewabilityChecker, touchDataRecorder, impressionHelper) { // from class: com.facebook.ads.redexgen.X.17
            private C01601n B;
            private final JV C;
            private final C0621Ji D;

            {
                this.C = jv;
                this.D = touchDataRecorder;
            }

            @Override // com.facebook.ads.redexgen.X.C0Q
            public final void B(Map<String, String> map) {
                if (this.B != null && !TextUtils.isEmpty(this.B.A())) {
                    Map<String, String> extraData = this.D.m258B();
                    map.put("touch", MJ.C(extraData));
                    this.C.lC(this.B.A(), map);
                }
            }

            public final void C(C01601n adData) {
                this.B = adData;
            }
        };
        C(this.D);
        return this.F;
    }

    private void G() {
        String strA = this.D.O().A();
        if (this.J == null && TextUtils.isEmpty(strA)) {
            return;
        }
        I9 i9 = new I9(this.J, new HashMap());
        i9.A(new C0437Ce(new WeakReference(this.G), null));
        i9.executeOnExecutor(this.L, strA);
    }

    private void H() {
        if (this.G != null) {
            this.G.bB(EnumC02926p.REWARDED_VIDEO_COMPLETE.B(), new AnonymousClass76(0, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        if (this.G != null) {
            this.G.aB(EnumC02926p.REWARDED_VIDEO_IMPRESSION.B());
        }
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void IE(C5I adViewabilityChecker, C0621Ji touchDataRecorder) {
        J(adViewabilityChecker, touchDataRecorder);
    }

    private void J(C5I adViewabilityChecker, C0621Ji touchDataRecorder) {
        F(adViewabilityChecker, touchDataRecorder).A();
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void WE() {
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void YE() {
        if (this.G != null) {
            this.G.aB(EnumC02926p.REWARDED_VIDEO_ERROR.B());
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void bC(Intent intent, Bundle savedInstanceState, C0531Fu activityImpl) {
        if (this.G == null || this.J == null) {
            return;
        }
        this.B = activityImpl;
        this.B.A(this.H);
        E(activityImpl);
        AnonymousClass81 anonymousClass81 = new AnonymousClass81(this.J, C01581l.C(this.D), this.E, this.G, this, true, false);
        this.N = anonymousClass81;
        addView(anonymousClass81);
        this.G.WB(this, new RelativeLayout.LayoutParams(-1, -1));
        anonymousClass81.A();
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void iD() {
        if (this.G != null) {
            this.G.aB(EnumC02926p.REWARDED_VIDEO_END_ACTIVITY.B());
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void nE(boolean launchAdReporting) {
        this.N.C();
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void oD(boolean skipPlayStore, Map<String, String> map) {
        this.K = true;
        BB adWebView = this.N.getAdWebView();
        if (adWebView == null) {
            return;
        }
        B0 b0D = D(adWebView);
        b0D.setCta(this.D.N(), this.D.A(), map);
        b0D.setActionEnabled(skipPlayStore ? false : true);
        b0D.performClick();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void oF(Bundle savedInstanceState) {
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void onDestroy() {
        if (this.B != null) {
            this.B.D(this.H);
            C0580Hs.B(this.B.B(), this.O);
        }
        if (this.N != null) {
            BB adWebView = this.N.getAdWebView();
            if (adWebView != null && !TextUtils.isEmpty(this.D.A())) {
                HashMap map = new HashMap();
                adWebView.getViewabilityChecker().A(map);
                map.put("touch", MJ.C(adWebView.getTouchDataRecorder().m258B()));
                this.E.iC(this.D.A(), map);
            }
            this.N.B();
        }
        this.G = null;
        this.B = null;
        this.J = null;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (this.N.getAdWebView() == null) {
            return;
        }
        if (hasWindowFocus) {
            yE(false);
        } else {
            nE(false);
        }
    }

    @Override // com.facebook.ads.redexgen.X.DG
    public final void qE() {
        this.I = true;
        G();
        H();
        BB adWebView = this.N.getAdWebView();
        if (this.K && adWebView != null) {
            D(adWebView).B(this.D.N(), this.D.A(), new HashMap());
        }
    }

    public void setListener(InterfaceC0525Fo listener) {
        this.G = listener;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void yE(boolean leaveAdReporting) {
        this.N.D();
    }
}
