package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.api.AudienceNetworkActivityApi;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0531Fu implements AudienceNetworkActivityApi {
    private int B;
    private final AudienceNetworkActivity C;
    private final AudienceNetworkActivityApi D;
    private AbstractC0432Bz E;
    private String F;
    private String H;

    @Nullable
    private InterfaceC0578Hq I;
    private B8 J;
    private long K;
    private C0618Jf M;

    @Nullable
    private String N;

    @Nullable
    private C8N O;
    private RelativeLayout P;
    private int Q;
    private Intent R;
    private long S;
    private L2 T;
    private boolean U;
    private final List<InterfaceC0528Fr> G = new ArrayList();
    private int L = -1;

    public C0531Fu(AudienceNetworkActivity activity, AudienceNetworkActivityApi activityParentApi) {
        this.C = activity;
        this.D = activityParentApi;
    }

    public final void A(InterfaceC0528Fr interceptor) {
        this.G.add(interceptor);
    }

    public final AudienceNetworkActivity B() {
        return this.C;
    }

    public final Intent C() {
        return this.R;
    }

    public final void D(InterfaceC0528Fr interceptor) {
        this.G.remove(interceptor);
    }

    private void L(Intent intent) {
        if (JA.s(this.C) && this.T != L2.BROWSER && Build.VERSION.SDK_INT >= 18) {
            this.J = new B8();
            this.J.F(intent.getStringExtra(Constants.PLACEMENT_ID));
            this.J.D(this.C.getPackageName());
            long longExtra = intent.getLongExtra("requestTime", 0L);
            if (longExtra != 0) {
                this.J.G(longExtra);
            }
            TextView textView = new TextView(this.C);
            textView.setText("Debug");
            textView.setTextColor(-1);
            J6.O(textView, Color.argb(160, 0, 0, 0));
            textView.setPadding(5, 5, 5, 5);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12, -1);
            layoutParams.addRule(11, -1);
            textView.setLayoutParams(layoutParams);
            ViewOnLongClickListenerC0529Fs viewOnLongClickListenerC0529Fs = new ViewOnLongClickListenerC0529Fs(this);
            textView.setOnLongClickListener(viewOnLongClickListenerC0529Fs);
            this.P.setOnLongClickListener(viewOnLongClickListenerC0529Fs);
            this.P.getOverlay().add(this.J);
        }
    }

    private void M() {
        if (this.N != null) {
            this.O = C8O.C(this.C, this.N);
            if (this.O != null) {
                this.P.addView(this.O, new RelativeLayout.LayoutParams(-1, -1));
            }
        }
    }

    @Nullable
    private InterfaceC0578Hq N(Intent startIntent) {
        C0527Fq c0527Fq = new C0527Fq(this, startIntent, JX.E(this.C), null);
        if (this.T == null) {
            return null;
        }
        switch (this.T) {
            case FULL_SCREEN_VIDEO:
                return c0527Fq.O(this.P);
            case REWARDED_VIDEO:
                return c0527Fq.T();
            case REWARDED_PLAYABLE:
                return c0527Fq.U();
            case REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD:
                return c0527Fq.M();
            case BROWSER:
                return c0527Fq.L();
            case INTERSTITIAL_NATIVE_VIDEO:
                return c0527Fq.S();
            case INTERSTITIAL_NATIVE_IMAGE:
                return c0527Fq.Q();
            case INTERSTITIAL_NATIVE_CAROUSEL:
                return c0527Fq.P();
            case INTERSTITIAL_NATIVE_PLAYABLE:
                return c0527Fq.R();
            case DYNAMIC:
                return c0527Fq.N();
            default:
                return null;
        }
    }

    private boolean O() {
        return this.T == L2.REWARDED_VIDEO || this.T == L2.REWARDED_PLAYABLE || this.T == L2.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P(String clientToken, boolean isAdReportingFlowCompleted, @Nullable H6 listener) {
        if (this.I == null) {
            return;
        }
        if (this.E == null) {
            this.E = C0431By.B(this.C.getApplicationContext(), JX.E(this.C), clientToken, this.I, new C0526Fp(this));
            this.E.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        }
        this.E.I(isAdReportingFlowCompleted);
        this.E.setAdReportingFlowListener(listener);
        J6.M(this.E);
        J6.G(this.P);
        this.P.addView(this.E);
        this.E.F();
    }

    private void Q(Intent intent, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Bundle bundleC = MH.C(savedInstanceState);
            this.L = bundleC.getInt("predefinedOrientationKey", -1);
            this.F = bundleC.getString("uniqueId");
            this.T = (L2) bundleC.getSerializable("viewType");
            return;
        }
        this.L = intent.getIntExtra("predefinedOrientationKey", -1);
        this.F = intent.getStringExtra("uniqueId");
        this.T = (L2) intent.getSerializableExtra("viewType");
        this.Q = intent.getIntExtra("skipAfterSeconds", 0) * 1000;
    }

    private void R(Exception e) {
        finish();
        C0585Hx.D(this.C, "an_activity", C0584Hw.E, new C0583Hv(e));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S(String event) {
        if ("com.facebook.ads.adreporting.FINISH_AD_REPORTING_FLOW".equals(event)) {
            finish();
        } else {
            C02464v.C(this.C).B(new Intent(event + ":" + this.F));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(String event, It eventObj) {
        Intent intent = new Intent(event + ":" + this.F);
        intent.putExtra("event", eventObj);
        C02464v.C(this.C).B(intent);
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkActivityApi
    public final void finish() {
        if (this.C.isFinishing()) {
            return;
        }
        if (O()) {
            S(EnumC02926p.REWARDED_VIDEO_CLOSED.B());
        } else {
            S("com.facebook.ads.interstitial.dismissed");
        }
        this.D.finish();
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkActivityApi
    public final void onBackPressed() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.K += jCurrentTimeMillis - this.S;
            this.S = jCurrentTimeMillis;
            if (this.K > this.Q) {
                boolean z = false;
                Iterator<InterfaceC0528Fr> it = this.G.iterator();
                while (it.hasNext()) {
                    if (it.next().QC()) {
                        z = true;
                    }
                }
                if (!z) {
                    this.D.onBackPressed();
                }
            }
        } catch (Exception e) {
            R(e);
        }
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkActivityApi
    public final void onConfigurationChanged(Configuration newConfig) {
        try {
            if (this.I instanceof CO) {
                ((CO) this.I).onConfigurationChanged(newConfig);
            }
        } catch (Exception e) {
            R(e);
        }
        if (this.B != newConfig.orientation) {
            HashMap map = new HashMap();
            if (newConfig.orientation == 1) {
                map.put("to_orientation", "portrait");
            } else {
                map.put("to_orientation", "landscape");
            }
            this.M.A(EnumC0617Je.DEVICE_ROTATED, map);
            this.B = newConfig.orientation;
        }
        this.D.onConfigurationChanged(newConfig);
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkActivityApi
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        this.D.onCreate(savedInstanceState);
        try {
            MM.D();
            Intent intent = this.C.getIntent();
            this.R = MH.D(intent);
            this.C.requestWindowFeature(1);
            this.C.getWindow().setFlags(1024, 1024);
            this.P = new RelativeLayout(this.C);
            J6.O(this.P, 0);
            this.C.setContentView(this.P, new RelativeLayout.LayoutParams(-1, -1));
            Intent intent2 = this.R;
            Q(intent2, savedInstanceState);
            Intent intent3 = this.R;
            this.I = N(intent3);
            if (this.I == null) {
                Context contextB = HM.B();
                if (contextB != null) {
                    C0585Hx.D(contextB, "an_activity", C0584Hw.C, new C0583Hv("Unable to infer viewType from intent or savedInstanceState"));
                }
                S("com.facebook.ads.interstitial.error");
                finish();
                return;
            }
            InterfaceC0578Hq interfaceC0578Hq = this.I;
            Intent intent4 = this.R;
            interfaceC0578Hq.bC(intent4, savedInstanceState, this);
            S("com.facebook.ads.interstitial.displayed");
            this.S = System.currentTimeMillis();
            this.N = this.R.getStringExtra("mediationData");
            M();
            Intent intent5 = this.R;
            L(intent5);
            this.B = this.C.getResources().getConfiguration().orientation;
            if (savedInstanceState == null) {
                this.H = this.R.getStringExtra("clientToken");
            } else {
                this.H = savedInstanceState.getString("clientToken");
            }
            this.M = new C0618Jf(this.H, JX.E(this.C));
        } catch (Exception e) {
            R(e);
        }
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkActivityApi
    public final void onDestroy() {
        try {
            if (O()) {
                S(EnumC02926p.REWARDED_VIDEO_ACTIVITY_DESTROYED.B());
            } else {
                S("com.facebook.ads.interstitial.activity_destroyed");
            }
            if (this.P != null) {
                this.P.removeAllViews();
            }
            if (this.I != null) {
                this.I.onDestroy();
                this.I = null;
            }
            if (this.J != null && JA.s(this.C)) {
                this.J.B();
            }
            if (this.E != null) {
                this.E.A();
            }
        } catch (Exception e) {
            R(e);
        }
        this.D.onDestroy();
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkActivityApi
    public final void onPause() {
        try {
            this.K += System.currentTimeMillis() - this.S;
            if (this.I != null) {
                this.I.nE(false);
                if (!this.C.isFinishing()) {
                    this.M.A(EnumC0617Je.APP_MOVED_TO_BACKGROUND, null);
                    this.U = true;
                }
            }
        } catch (Exception e) {
            R(e);
        }
        this.D.onPause();
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkActivityApi
    public final void onResume() {
        this.D.onResume();
        try {
            this.S = System.currentTimeMillis();
            if (this.I != null) {
                this.I.yE(false);
                if (this.U) {
                    this.M.A(EnumC0617Je.APP_RETURNED_TO_FOREGROUND, null);
                }
            }
        } catch (Exception e) {
            R(e);
        }
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkActivityApi
    public final void onSaveInstanceState(Bundle savedInstanceState) {
        this.D.onSaveInstanceState(savedInstanceState);
        try {
            Bundle bundle = new Bundle();
            if (this.I != null) {
                this.I.oF(bundle);
            }
            bundle.putInt("predefinedOrientationKey", this.L);
            bundle.putString("uniqueId", this.F);
            bundle.putString("clientToken", this.H);
            bundle.putSerializable("viewType", this.T);
            MH.F(savedInstanceState, bundle);
        } catch (Exception e) {
            R(e);
        }
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkActivityApi
    public final void onStart() {
        this.D.onStart();
        try {
            if (this.L != -1) {
                C0580Hs.B(this.C, this.L);
            }
        } catch (Exception e) {
            R(e);
        }
    }

    @Override // com.facebook.ads.internal.api.AudienceNetworkActivityApi
    public final boolean onTouchEvent(MotionEvent me) {
        C0611Iy.D(null, me);
        return this.D.onTouchEvent(me);
    }
}
