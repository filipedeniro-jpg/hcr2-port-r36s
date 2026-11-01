package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.TransitionSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Bs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0425Bs extends AbstractC0432Bz {
    private static final int C = (int) (8.0f * J6.B);
    private final RelativeLayout B;

    public C0425Bs(Context context, JV adEventManager, String clientToken, InterfaceC0578Hq audienceNetworkView, InterfaceC0525Fo listener) {
        super(context, adEventManager, clientToken, audienceNetworkView, listener);
        this.B = new RelativeLayout(getContext());
        addView(this.B, new RelativeLayout.LayoutParams(-1, -1));
        J6.O(this.B, -1728053248);
        this.B.setOnClickListener(new ViewOnClickListenerC0429Bw(this));
    }

    private static RelativeLayout.LayoutParams B(boolean fullScreen) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, fullScreen ? -1 : -2);
        layoutParams.addRule(12);
        return layoutParams;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void B(AnonymousClass31 reason, EnumC01982z type) {
        String strD;
        if (type == EnumC01982z.NONE) {
            return;
        }
        boolean z = type == EnumC01982z.REPORT;
        C3 c3D = new C3(getContext()).D(super.B);
        if (z) {
            strD = C01972y.E(getContext());
        } else {
            strD = C01972y.D(getContext());
        }
        C2 c2A = c3D.K(strD).J(C01972y.C(getContext())).E(reason.F()).B(z ? EnumC0606Is.REPORT_AD : EnumC0606Is.HIDE_AD).C(z ? -552389 : -13272859).F(this.C).A();
        J6.O(c2A, -1);
        J6.G(this);
        this.B.removeAllViews();
        this.B.addView(c2A, B(true));
    }

    private void C() {
        if (Build.VERSION.SDK_INT >= 21) {
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.setOrdering(0);
            transitionSet.addTransition(new ChangeBounds()).addTransition(new Explode());
            J6.I(this, transitionSet);
            return;
        }
        J6.G(this);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void D() {
        AnonymousClass31 anonymousClass31G = C01972y.G(getContext());
        C0417Bk reportAdView = new C0417Bk(getContext());
        reportAdView.setInfo(EnumC0606Is.HIDE_AD, C01972y.H(getContext()), C01972y.F(getContext()));
        reportAdView.setOnClickListener(new ViewOnClickListenerC0428Bv(this));
        AnonymousClass31 anonymousClass31M = C01972y.M(getContext());
        C0417Bk c0417Bk = new C0417Bk(getContext());
        c0417Bk.setInfo(EnumC0606Is.REPORT_AD, C01972y.N(getContext()), C01972y.L(getContext()));
        c0417Bk.setOnClickListener(new ViewOnClickListenerC0427Bu(this));
        C0417Bk c0417Bk2 = new C0417Bk(getContext());
        c0417Bk2.setInfo(EnumC0606Is.AD_CHOICES_ICON, C01972y.O(getContext()), "");
        c0417Bk2.setOnClickListener(new ViewOnClickListenerC0426Bt(this));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setClickable(true);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(C * 2, C, C * 2, C);
        J6.O(linearLayout, -1);
        if (!anonymousClass31G.B().isEmpty()) {
            linearLayout.addView(reportAdView, layoutParams);
        }
        if (!anonymousClass31M.B().isEmpty()) {
            linearLayout.addView(c0417Bk, layoutParams);
        }
        linearLayout.addView(c0417Bk2, layoutParams);
        C();
        this.B.removeAllViews();
        this.B.addView(linearLayout, B(false));
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void E(AnonymousClass31 reason, EnumC01982z type) {
        boolean isReportFlow = type == EnumC01982z.REPORT;
        C0414Bh c0414Bh = new C0414Bh(getContext(), reason, super.B, isReportFlow ? C01972y.N(getContext()) : C01972y.H(getContext()), isReportFlow ? EnumC0606Is.REPORT_AD : EnumC0606Is.HIDE_AD);
        c0414Bh.setClickable(true);
        J6.O(c0414Bh, -1);
        c0414Bh.setPadding(C * 2, C, C * 2, C);
        C();
        this.B.removeAllViews();
        this.B.addView(c0414Bh, B(false));
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final void G() {
        J6.J(this);
        this.B.removeAllViews();
        J6.M(this);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0432Bz
    public final boolean H() {
        return false;
    }
}
