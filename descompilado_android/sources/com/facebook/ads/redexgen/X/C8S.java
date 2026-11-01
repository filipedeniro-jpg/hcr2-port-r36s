package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8S, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C8S extends AbstractC0466Dh {
    private H2 B;
    private C5I C;
    private C03308b D;
    private String E;

    @Nullable
    private LinearLayout F;
    private List<C03298a> G;

    @Nullable
    private C0402Av H;

    @Nullable
    private C0562Ha I;
    private final C0621Ji J;
    private int K;
    private int L;
    private C1B M;
    private static final int N = (int) (48.0f * J6.B);
    private static final int O = (int) (J6.B * 8.0f);
    private static final int P = (int) (J6.B * 8.0f);
    private static final int R = (int) (56.0f * J6.B);
    private static final int Q = (int) (12.0f * J6.B);

    public C8S(Context context, JV adEventManager, H2 adCacheManager, InterfaceC0525Fo listener, C01481b dataBundle) {
        super(context, adEventManager, listener, dataBundle);
        this.J = new C0621Ji();
        this.B = adCacheManager;
    }

    private void K(C03308b carouselCardBehaviorHelper) {
        new EH().B(this.I);
        carouselCardBehaviorHelper.I(new C8Z() { // from class: com.facebook.ads.redexgen.X.8U
            @Override // com.facebook.ads.redexgen.X.C8Z
            public final void iF(int targetPosition) {
                if (this.B.H != null) {
                    this.B.H.A(targetPosition);
                }
            }
        });
        this.H = new C0402Av(getContext(), super.B.B(), this.G.size());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, P);
        layoutParams.setMargins(0, Q, 0, 0);
        this.H.setLayoutParams(layoutParams);
    }

    private void L(C01481b dataBundle) {
        this.E = dataBundle.C();
        this.K = dataBundle.M();
        this.L = dataBundle.N();
        List<C01491c> listA = dataBundle.A();
        this.G = new ArrayList(listA.size());
        for (int i = 0; i < i; i++) {
            C01491c c01491c = listA.get(i);
            List<C03298a> list = this.G;
            int i2 = listA.size();
            list.add(new C03298a(i, i2, c01491c));
        }
    }

    private final void M() {
        if (this.F != null) {
            this.F.removeAllViews();
            this.F = null;
        }
        if (this.I != null) {
            this.I.removeAllViews();
            this.I = null;
        }
        if (this.H != null) {
            this.H.removeAllViews();
            this.H = null;
        }
    }

    private final void N(final int orientation, @Nullable Bundle savedInstanceState) {
        final int iMin;
        final int i;
        final int i2;
        final C1Y c1yA;
        this.F = new LinearLayout(getContext());
        if (orientation == 1) {
            this.F.setGravity(17);
        } else {
            this.F.setGravity(48);
        }
        this.F.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.F.setOrientation(1);
        int i3 = J6.C.widthPixels;
        int i4 = J6.C.heightPixels;
        if (orientation == 1) {
            int height = O;
            int width = i3 - (height * 4);
            int height2 = i4 / 2;
            iMin = Math.min(width, height2);
            i = (i3 - iMin) / 8;
            i2 = i * 4;
        } else {
            int width2 = R;
            int childWidth = N;
            int width3 = width2 + childWidth;
            int childWidth2 = O;
            iMin = i4 - (width3 + (childWidth2 * 2));
            i = O;
            i2 = i * 2;
        }
        this.M = new C1B() { // from class: com.facebook.ads.redexgen.X.8V
            @Override // com.facebook.ads.redexgen.X.C1B
            public final void B() {
                HashMap map = new HashMap();
                if (!this.B.J.D()) {
                    this.B.J.G();
                    if (this.B.getAudienceNetworkListener() != null) {
                        this.B.getAudienceNetworkListener().aB("com.facebook.ads.interstitial.impression.logged");
                    }
                    if (!TextUtils.isEmpty(this.B.E)) {
                        this.B.C.A(map);
                        map.put("touch", MJ.C(this.B.J.m258B()));
                        this.B.C((Map<String, String>) map);
                        ((AbstractC0466Dh) ((AbstractC0466Dh) this.B)).D.lC(this.B.E, map);
                    }
                }
            }
        };
        this.C = new C5I(this, 1, this.M);
        C5I c5i = this.C;
        int childWidth3 = this.K;
        c5i.D(childWidth3);
        C5I c5i2 = this.C;
        int childWidth4 = this.L;
        c5i2.E(childWidth4);
        this.I = new C0562Ha(getContext());
        this.I.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        this.D = new C03308b(this.I, orientation, this.G, this.C, savedInstanceState);
        C0562Ha c0562Ha = this.I;
        final List<C03298a> list = this.G;
        final JV jv = super.D;
        final H2 h2 = this.B;
        final C5I c5i3 = this.C;
        final C0621Ji c0621Ji = this.J;
        final InterfaceC0525Fo audienceNetworkListener = getAudienceNetworkListener();
        if (orientation == 1) {
            c1yA = super.B.B();
        } else {
            c1yA = super.B.A();
        }
        final String str = this.E;
        final C03308b c03308b = this.D;
        c0562Ha.setAdapter(new C1G<C8P>(list, jv, h2, c5i3, c0621Ji, audienceNetworkListener, c1yA, str, iMin, i, i2, orientation, c03308b) { // from class: com.facebook.ads.redexgen.X.8Y
            private final H2 B;
            private final JV C;
            private final C03308b D;
            private final SparseBooleanArray E = new SparseBooleanArray();
            private List<C03298a> F;
            private int G;
            private String H;
            private final C1Y I;
            private int J;
            private int K;

            @Nullable
            private InterfaceC0525Fo L;
            private int M;
            private final C0621Ji N;
            private final C5I O;

            {
                this.C = jv;
                this.B = h2;
                this.O = c5i3;
                this.N = c0621Ji;
                this.L = audienceNetworkListener;
                this.F = list;
                this.G = iMin;
                this.I = c1yA;
                this.M = orientation;
                this.H = str;
                this.J = i2;
                this.K = i;
                this.D = c03308b;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.C1G
            /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
            public final void M(C8P holder, int position) {
                holder.h(this.F.get(position), this.C, this.B, this.N, this.H);
            }

            @Override // com.facebook.ads.redexgen.X.C1G
            public final int C() {
                return this.F.size();
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.C1G
            /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
            public final C8P N(ViewGroup parent, int viewType) {
                return new C8P(AnonymousClass97.B(new AG(parent.getContext(), this.C, this.L, null, null, this.O, this.N).A(), this.M, this.I, this.H, this.D), this.E, this.O, this.G, this.J, this.K, this.F.size());
            }
        });
        if (orientation == 1) {
            K(this.D);
        }
        this.F.addView(this.I);
        if (this.H != null) {
            this.F.addView(this.H);
        }
        B(this.F, false, orientation);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void bC(Intent intent, Bundle savedInstanceState, final C0531Fu activityImpl) {
        C01481b c01481b = (C01481b) intent.getSerializableExtra("ad_data_bundle");
        super.F(activityImpl);
        L(c01481b);
        N(activityImpl.B().getResources().getConfiguration().orientation, savedInstanceState);
        activityImpl.A(new InterfaceC0528Fr() { // from class: com.facebook.ads.redexgen.X.8T
            @Override // com.facebook.ads.redexgen.X.InterfaceC0528Fr
            public final boolean QC() {
                if (!this.B.E()) {
                    return this.B.H();
                }
                this.B.G(activityImpl);
                return true;
            }
        });
        int iF = super.C.A().get(0).C().F();
        if (iF > 0) {
            I(iF);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void nE(boolean launchAdReporting) {
        super.nE(launchAdReporting);
        if (this.D != null) {
            this.D.F();
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void oF(Bundle savedInstanceState) {
        if (this.D != null) {
            this.D.H(savedInstanceState);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, android.view.View
    public final void onConfigurationChanged(Configuration newConfig) {
        Bundle bundle = new Bundle();
        oF(bundle);
        M();
        N(newConfig.orientation, bundle);
        super.onConfigurationChanged(newConfig);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void onDestroy() {
        super.onDestroy();
        if (!TextUtils.isEmpty(this.E)) {
            HashMap map = new HashMap();
            this.C.A(map);
            map.put("touch", MJ.C(this.J.m258B()));
            super.D.iC(this.E, map);
        }
        M();
        this.C.G();
        this.C = null;
        this.M = null;
        this.G = null;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent event) {
        this.J.F(event, this, this);
        return super.onInterceptTouchEvent(event);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void yE(boolean leaveAdReporting) {
        super.yE(leaveAdReporting);
        this.D.G();
    }
}
