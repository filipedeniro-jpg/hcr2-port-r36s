package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class FV extends RelativeLayout implements InterfaceC0578Hq {
    private WeakReference<C0531Fu> B;
    private final C1W C;
    private final JV D;
    private final C5I E;
    private final C1B F;
    private final InterfaceC0525Fo G;
    private final InterfaceC0528Fr H;
    private final LinearLayout I;
    private final MS J;
    private final TextView K;

    @Nullable
    private final C0618Jf L;
    private boolean M;
    private boolean N;
    private final int O;
    private final C0397Aq P;
    private final C0621Ji Q;
    private static final RelativeLayout.LayoutParams S = new RelativeLayout.LayoutParams(-1, -1);
    private static final int R = (int) (16.0f * J6.B);
    private static final int U = (int) (56.0f * J6.B);
    private static final int T = (int) (230.0f * J6.B);

    public FV(Context context, C1W dataBundle, JV adEventManager, InterfaceC0525Fo audienceNetworkAdListener) {
        super(context);
        this.Q = new C0621Ji();
        this.H = new HN(this);
        this.C = dataBundle;
        this.D = adEventManager;
        this.O = this.C.J().A() / 1000;
        this.G = audienceNetworkAdListener;
        this.L = this.C.A() == null ? null : new C0618Jf(this.C.A(), this.D);
        this.F = new HL(this);
        this.E = new C5I(this, 1, this.F);
        this.E.D(250);
        this.P = new C0397Aq(context);
        J6.U(this.P);
        this.K = new TextView(getContext());
        J6.U(this.K);
        this.I = new LinearLayout(getContext());
        boolean z = getResources().getConfiguration().orientation == 1;
        J();
        K(z);
        I(z);
        J6.O(this, Color.parseColor(this.C.L()));
        this.J = new MS(this.O, new C0546Gj(this, this.C, this.O));
        this.E.F();
    }

    public final void A() {
        boolean allHidden = true;
        for (int i = 0; i < this.I.getChildCount(); i++) {
            C03217s c03217s = (C03217s) this.I.getChildAt(i);
            allHidden &= c03217s.C();
            c03217s.K();
        }
        if (allHidden && this.G != null) {
            this.G.aB(EnumC02926p.REWARDED_VIDEO_END_ACTIVITY.B());
        }
    }

    public final void B(boolean enable) {
        for (int i = 0; i < this.I.getChildCount(); i++) {
            if (enable) {
                ((C03217s) this.I.getChildAt(i)).F();
            } else {
                ((C03217s) this.I.getChildAt(i)).G();
            }
        }
    }

    private void I(boolean isPortrait) {
        this.I.setPadding(R / 2, R / 2, R / 2, R / 2);
        this.I.setOrientation(isPortrait ? 1 : 0);
        N(isPortrait, this.C.K());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, this.K.getId());
        addView(this.I, layoutParams);
    }

    private void J() {
        this.P.setProgress(0);
        this.P.A(false, Color.parseColor(this.C.M()), 14);
        this.P.setText(this.C.O().A(String.valueOf(this.O)));
        J6.O(this.P, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, U);
        layoutParams.addRule(10);
        addView(this.P, layoutParams);
    }

    private void K(boolean isPortrait) {
        this.K.setText(this.C.O().B());
        J6.Q(this.K, true, 32);
        this.K.setTextColor(Color.parseColor(this.C.N()));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(isPortrait ? T : -1, -2);
        layoutParams.setMargins(R, 0, R, R / 2);
        layoutParams.addRule(3, this.P.getId());
        addView(this.K, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L(C01601n selectedAdDataBundle) {
        if (this.N) {
            return;
        }
        this.N = true;
        this.J.m263D();
        if (this.E != null) {
            this.E.G();
        }
        View view = new View(getContext());
        view.setOnClickListener(new HK(this));
        addView(view, new RelativeLayout.LayoutParams(-1, -1));
        C0614Jb c0614Jb = new C0614Jb();
        for (int i = 0; i < i; i++) {
            C03217s c03217s = (C03217s) this.I.getChildAt(i);
            if (c03217s.getAdDataBundle() == selectedAdDataBundle) {
                c0614Jb.E(i);
            }
            c03217s.K();
        }
        M(selectedAdDataBundle.A(), c0614Jb);
        int i2 = this.C.F();
        selectedAdDataBundle.I(i2);
        selectedAdDataBundle.H(this.C.E());
        J6.K(this);
        this.G.bB(EnumC02926p.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD.B(), new H8(selectedAdDataBundle));
        if (this.B == null || this.B.get() == null) {
            return;
        }
        this.B.get().D(this.H);
    }

    private void M(String clientToken, C0614Jb adSelectionEvent) {
        adSelectionEvent.C((this.O - this.J.A()) * 1000);
        adSelectionEvent.G(this.O * 1000);
        adSelectionEvent.D(this.C.K().size());
        adSelectionEvent.F(this.J.B());
        adSelectionEvent.B(this.C.J().B());
        HashMap map = new HashMap();
        this.E.A(map);
        map.put("touch", MJ.C(this.Q.m258B()));
        map.put("ad_selection", MJ.C(adSelectionEvent.A()));
        map.put("is_cyoa", Boolean.TRUE.toString());
        this.D.fC(clientToken, map);
    }

    private void N(boolean isPortrait, List<C01601n> list) {
        this.I.setWeightSum(list.size());
        boolean z = list.size() == 2;
        boolean shouldStartAnimation = list.size() >= 3 && !isPortrait;
        int i = 0;
        Iterator<C01601n> it = list.iterator();
        while (shouldPlayButtonOnTop) {
            C03217s c03217s = new C03217s(getContext(), it.next(), this.D, this.E, this.Q, this.G);
            c03217s.setShouldPlayButtonOnTop(shouldStartAnimation);
            boolean shouldPlayButtonOnTop = this.C.J().D();
            c03217s.I(shouldPlayButtonOnTop);
            c03217s.setRadius(10);
            int index = isPortrait ? -1 : 0;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(index, isPortrait ? 0 : -1);
            int i2 = R / 2;
            int i3 = R / 2;
            int index2 = R / 2;
            layoutParams.setMargins(i2, i3, index2, R / 2);
            layoutParams.weight = 1.0f;
            H7 adItemClickListener = new H7(this, c03217s);
            c03217s.setOnTouchListener(adItemClickListener);
            c03217s.setOnClickListener(adItemClickListener);
            c03217s.setAdReportingFlowListener(new C0555Gt(this, c03217s));
            if (z) {
                c03217s.J(i % 2 != 0, this.C.J().C());
            }
            this.I.addView(c03217s, layoutParams);
            i++;
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void bC(Intent intent, Bundle savedInstanceState, C0531Fu activityImpl) {
        if (this.G == null) {
            return;
        }
        this.G.WB(this, S);
        activityImpl.A(this.H);
        this.B = new WeakReference<>(activityImpl);
    }

    public final C0621Ji getTouchDataRecorder() {
        return this.Q;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void nE(boolean launchAdReporting) {
        this.J.m263D();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void oF(Bundle savedInstanceState) {
        this.J.m263D();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration newConfig) {
        boolean z = newConfig.orientation == 1;
        ((RelativeLayout.LayoutParams) this.K.getLayoutParams()).width = z ? T : -1;
        this.I.setOrientation(z ? 1 : 0);
        boolean shouldPlayButtonOnTop = this.C.K().size() >= 3 && !z;
        for (int i = 0; i < this.I.getChildCount(); i++) {
            C03217s c03217s = (C03217s) this.I.getChildAt(i);
            c03217s.D(z);
            c03217s.setShouldPlayButtonOnTop(shouldPlayButtonOnTop);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void onDestroy() {
        this.J.m263D();
        if (this.E != null) {
            this.E.G();
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        this.Q.F(event, this, this);
        if (event.getAction() == 1) {
            HashMap map = new HashMap();
            this.E.A(map);
            map.put("touch", MJ.C(this.Q.m258B()));
            map.put("is_cyoa", Boolean.TRUE.toString());
            this.D.oC(this.C.K().get(0).A(), map);
        }
        return true;
    }

    public void setIsAdReportingLayoutVisible(boolean isVisible) {
        this.M = isVisible;
    }

    public void setListener(InterfaceC0525Fo listener) {
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void yE(boolean leaveAdReporting) {
        if (this.N) {
            return;
        }
        if (leaveAdReporting || !this.M) {
            this.J.E();
        }
    }
}
