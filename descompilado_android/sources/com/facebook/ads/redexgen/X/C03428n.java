package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.api.AudienceNetworkActivityApi;
import com.facebook.ads.internal.api.BuildConfigApi;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8n, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03428n extends FrameLayout implements InterfaceC0578Hq {
    private final H2 B;
    private final C01481b C;
    private final JV D;

    @Nullable
    private AudienceNetworkActivityApi E;
    private final String F;
    private InterfaceC0528Fr G;
    private final IE H;
    private final C0462Dd I;
    private boolean J;
    private final InterfaceC0525Fo K;
    private final JSONObject L;
    private boolean M;
    private final C0621Ji N;

    public C03428n(final Context context, JV adEventManager, InterfaceC0525Fo listener, C01481b dataBundle) {
        super(context);
        this.G = new InterfaceC0528Fr() { // from class: com.facebook.ads.redexgen.X.95
            @Override // com.facebook.ads.redexgen.X.InterfaceC0528Fr
            public final boolean QC() {
                return this.B.M;
            }
        };
        this.N = new C0621Ji();
        this.M = true;
        this.J = false;
        this.D = adEventManager;
        this.K = listener;
        this.C = dataBundle;
        this.B = new H2(context);
        this.F = UUID.randomUUID().toString();
        this.L = C03388j.C(context, this.C.H(), this.B);
        this.I = new C0462Dd(context, this.C);
        this.H = new IE(context) { // from class: com.facebook.ads.redexgen.X.8v
            @Override // com.facebook.ads.redexgen.X.IE
            public final WebChromeClient A() {
                return new C03538y(this.B, null);
            }

            @Override // com.facebook.ads.redexgen.X.IE
            /* JADX INFO: renamed from: B */
            public final WebViewClient mo240B() {
                return new C03438o(this.B, null);
            }
        };
    }

    public final void A() {
        if (this.E == null) {
            return;
        }
        this.E.finish();
    }

    public final void B() {
        this.M = true;
    }

    public final void C() {
        this.M = false;
    }

    @SuppressLint({"CatchGeneralException"})
    public final void D() {
        C1Z c1zA = this.C.A().get(0).A();
        if (TextUtils.isEmpty(c1zA.C())) {
            return;
        }
        try {
            AnonymousClass09 adAction = C0A.C(getContext(), this.D, this.C.C(), Uri.parse(c1zA.C()), null, true, false);
            if (adAction != null) {
                adAction.A();
            }
            this.K.aB("com.facebook.ads.interstitial.clicked");
        } catch (Exception e) {
            C0585Hx.C(e);
        }
    }

    public final void E() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.facebook.ads.redexgen.X.94
            @Override // java.lang.Runnable
            public final void run() {
                this.B.K.YC(this.B.C.C(), true, null);
            }
        });
    }

    public final void F(List<String> list, final String callback) {
        final StringBuilder sb = new StringBuilder("{");
        try {
            for (String str : list) {
                if (this.L.has(str)) {
                    sb.append("\"").append(str).append("\"").append(":");
                    if (this.L.opt(str) instanceof JSONObject) {
                        sb.append(this.L.getJSONObject(str).toString());
                    } else if (this.L.opt(str) instanceof JSONArray) {
                        sb.append(this.L.getJSONArray(str).toString());
                    } else {
                        sb.append("\"").append(C03388j.B(this.L.getString(str))).append("\"");
                    }
                    sb.append(",");
                }
            }
            sb.replace(sb.length() - 1, sb.length(), "}");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.facebook.ads.redexgen.X.91
                @Override // java.lang.Runnable
                public final void run() {
                    this.B.J(String.format(Locale.US, callback + "('%s', '%s');", sb.toString(), this.B.F));
                }
            });
        } catch (JSONException e) {
            C0585Hx.D(getContext(), "dynamic_layout", C0584Hw.x, new C0583Hv(e.getMessage()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(21)
    public void J(String function) {
        if (this.J) {
            this.H.evaluateJavascript(function, null);
        }
    }

    @SuppressLint({"AddJavascriptInterface"})
    private final void K() {
        if (Build.VERSION.SDK_INT > 16) {
            final JV jv = this.D;
            final String str = this.F;
            final String strC = this.C.C();
            this.H.addJavascriptInterface(new Object(this, jv, str, strC) { // from class: com.facebook.ads.redexgen.X.8k
                private final WeakReference<JV> B;
                private final WeakReference<C03428n> C;
                private final String D;
                private final String E;

                {
                    this.C = new WeakReference<>(this);
                    this.B = new WeakReference<>(jv);
                    this.D = str;
                    this.E = strC;
                }

                private void B() {
                    C03428n c03428n = this.C.get();
                    if (c03428n == null) {
                        return;
                    }
                    c03428n.A();
                }

                private void C() {
                    C03428n c03428n = this.C.get();
                    if (c03428n == null) {
                        return;
                    }
                    c03428n.B();
                }

                private void D() {
                    C03428n c03428n = this.C.get();
                    if (c03428n == null) {
                        return;
                    }
                    c03428n.C();
                }

                private void E() {
                    C03428n c03428n = this.C.get();
                    if (c03428n == null) {
                        return;
                    }
                    c03428n.D();
                }

                private void F() {
                    C03428n c03428n = this.C.get();
                    if (c03428n == null) {
                        return;
                    }
                    c03428n.E();
                }

                private static void G(int subtypeCode, String errorMessage) {
                    Context contextB = HM.B();
                    if (contextB != null) {
                        C0585Hx.D(contextB, "dynamic_layout", subtypeCode, new C0583Hv(errorMessage));
                    }
                }

                private void H(JSONObject extraData) {
                    JV jv2 = this.B.get();
                    if (jv2 == null) {
                        return;
                    }
                    Iterator<String> itKeys = extraData.keys();
                    HashMap map = new HashMap();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        String key = extraData.optString(next);
                        map.put(next, key);
                    }
                    String key2 = this.E;
                    jv2.jC(key2, map);
                }

                private void I(JSONObject extraData) {
                    C03428n c03428n = this.C.get();
                    if (c03428n == null) {
                        return;
                    }
                    JSONArray jSONArrayOptJSONArray = extraData.optJSONArray("keys");
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        arrayList.add(jSONArrayOptJSONArray.optString(i));
                    }
                    c03428n.F(arrayList, extraData.optString("callback"));
                }

                @JavascriptInterface
                public void postMessage(String message) {
                    try {
                        JSONObject object = new JSONObject(message);
                        if (!this.D.equals(object.optString("authKey"))) {
                            G(C0584Hw.w, "Could not parse server message");
                        } else {
                            String strOptString = object.optString("extra_data");
                            switch (C03418m.B[EnumC03408l.B(object.optString("type")).ordinal()]) {
                                case 1:
                                    E();
                                    break;
                                case 2:
                                    F();
                                    break;
                                case 3:
                                    B();
                                    break;
                                case 4:
                                    D();
                                    break;
                                case 5:
                                    C();
                                    break;
                                case 6:
                                    I(new JSONObject(strOptString));
                                    break;
                                case 7:
                                    H(new JSONObject(strOptString));
                                    break;
                                case 8:
                                    if (BuildConfigApi.isDebug()) {
                                        Log.d("JavascriptBridge", strOptString);
                                    }
                                    break;
                            }
                        }
                    } catch (JSONException e) {
                        G(C0584Hw.y, "Error parsing JSON in postMessage " + e.getMessage());
                    }
                }
            }, "AndroidInterface");
        }
        this.H.loadUrl(this.B.I(this.C.E()));
    }

    private void L() {
        J6.F(this.H);
        addView(this.H, new FrameLayout.LayoutParams(-1, -1));
        addView(this.I, new FrameLayout.LayoutParams(-1, -1));
        this.K.WB(this, new RelativeLayout.LayoutParams(-1, -1));
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500L);
        alphaAnimation.setAnimationListener(new C03548z(this));
        startAnimation(alphaAnimation);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void bC(Intent intent, @Nullable Bundle savedInstanceState, C0531Fu activityImpl) {
        activityImpl.A(this.G);
        this.E = activityImpl;
        K();
        L();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void nE(boolean launchAdReporting) {
        if (launchAdReporting) {
            J(String.format(Locale.US, "adReportingStarted('%s');", this.F));
        } else {
            J(String.format(Locale.US, "onActivityPaused('%s');", this.F));
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void oF(Bundle savedInstanceState) {
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void onDestroy() {
        HashMap map = new HashMap();
        map.put("touch", MJ.C(this.N.m258B()));
        this.D.iC(this.C.C(), map);
        this.G = null;
        this.E = null;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouchEvent(MotionEvent event) {
        this.N.F(event, this, this);
        requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }

    public void setListener(InterfaceC0525Fo listener) {
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void yE(boolean leaveAdReporting) {
        if (leaveAdReporting) {
            J(String.format(Locale.US, "adReportingFinished('%s');", this.F));
        } else {
            J(String.format(Locale.US, "onActivityResumed('%s');", this.F));
        }
    }
}
