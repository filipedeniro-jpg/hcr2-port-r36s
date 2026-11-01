package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appsflyer.BuildConfig;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.81, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass81 extends RelativeLayout implements BG, MR {
    private final C1N B;
    private final C01581l C;
    private final JV D;
    private final C0510Ez E;
    private WeakReference<BB> F;
    private C0V G;
    private RelativeLayout H;
    private B1 I;
    private Toast J;
    private boolean K;
    private final boolean L;
    private final C0618Jf M;
    private final MS N;
    private final AtomicBoolean O;
    private final MS P;
    private C8C Q;
    private final C01571k R;

    @Nullable
    private DG S;
    private static final int X = (int) (64.0f * J6.B);
    private static final RelativeLayout.LayoutParams U = new RelativeLayout.LayoutParams(-1, -1);
    private static final int V = (int) (16.0f * J6.B);
    private static final int Y = (int) (12.0f * J6.B);
    private static final int T = (int) (10.0f * J6.B);
    private static final float W = (int) (4.0f * J6.B);

    public AnonymousClass81(Context context, C01581l adData, JV adEventManager, InterfaceC0525Fo audienceNetworkAdListener, DG playableAdsListener, boolean enableTimer, boolean forceCta) {
        super(context);
        this.O = new AtomicBoolean();
        this.K = false;
        this.C = adData;
        this.R = adData.B().D();
        this.B = adData.A();
        this.D = adEventManager;
        this.S = playableAdsListener;
        this.M = new C0618Jf(this.C.E(), this.D);
        this.E = new C0510Ez(context, audienceNetworkAdListener, this.M, F2.CROSS);
        this.L = forceCta;
        this.N = new MS(enableTimer ? this.R.H() : 0, this);
        this.P = new MS(this.R.K() ? 2 : 0, new MR() { // from class: com.facebook.ads.redexgen.X.8B
            @Override // com.facebook.ads.redexgen.X.MR
            public final void mD() {
                this.B.Q();
            }

            @Override // com.facebook.ads.redexgen.X.MR
            public final void uE(int curSec) {
            }
        });
        P();
    }

    public final void A() {
        if (this.R.K()) {
            this.P.E();
        } else {
            removeAllViews();
            Q();
        }
    }

    public final void B() {
        this.P.m263D();
        this.N.m263D();
        this.E.setToolbarListener(null);
        BB bb = this.F != null ? this.F.get() : null;
        if (bb != null) {
            bb.removeJavascriptInterface("FbPlayableAd");
        }
        this.S = null;
        this.J = null;
    }

    public final void C() {
        this.P.m263D();
        this.N.m263D();
    }

    public final void D() {
        if (!this.P.B()) {
            this.P.E();
        } else {
            if (this.N.C()) {
                return;
            }
            this.N.E();
        }
    }

    private BB L() {
        this.G = new C0W() { // from class: com.facebook.ads.redexgen.X.88
            @Override // com.facebook.ads.redexgen.X.C0W, com.facebook.ads.redexgen.X.C0V
            public final void BE(int errorCode, @Nullable String description) {
                this.B.K = true;
                if (this.B.F.get() != null) {
                    ((BB) this.B.F.get()).setVisibility(4);
                }
                if (this.B.S != null) {
                    this.B.S.YE();
                }
            }

            @Override // com.facebook.ads.redexgen.X.C0W, com.facebook.ads.redexgen.X.C0V
            public final void HE() {
                if (this.B.O.compareAndSet(false, true) && this.B.F.get() != null && this.B.S != null) {
                    BB bb = (BB) this.B.F.get();
                    this.B.S.IE(bb.getViewabilityChecker(), bb.getTouchDataRecorder());
                    this.B.N.E();
                }
            }
        };
        BB bb = new BB(getContext(), new WeakReference(this.G), 10);
        bb.setLogMultipleImpressions(false);
        bb.setWaitForAssetsToLoad(true);
        bb.setCheckAssetsByJavascriptBridge(false);
        bb.setWebViewTimeoutInMillis(this.R.I());
        bb.setRequestId(this.C.I());
        WebSettings settings = bb.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
        }
        this.F = new WeakReference<>(bb);
        return bb;
    }

    private B1 M() {
        C1Y c1yB = this.B.B();
        c1yB.H(452984831);
        B1 b1 = new B1(getContext(), true, false, c1yB);
        b1.setText(this.C.F().B());
        J6.U(b1);
        b1.setOnClickListener(new View.OnClickListener(this) { // from class: com.facebook.ads.redexgen.X.84
            public final WeakReference<AnonymousClass81> B;

            {
                this.B = new WeakReference<>(this);
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View v) {
                if (this.B.get() != null) {
                    this.B.get().O(false);
                }
            }
        });
        b1.setTextSize(14.0f);
        b1.setIncludeFontPadding(false);
        b1.setPadding(T, T, T, T);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        b1.setLayoutParams(layoutParams);
        if (!this.L) {
            b1.setVisibility(8);
        }
        return b1;
    }

    private C0394An N(B1 fbButton) {
        C0394An c0394An = new C0394An(getContext(), this.C.A().B(), true, 16, 14, 0);
        J6.U(c0394An);
        c0394An.A(this.C.C().I(), this.C.C().H(), null, false, true);
        TextView descriptionTextView = c0394An.getDescriptionTextView();
        descriptionTextView.setAlpha(0.8f);
        descriptionTextView.setMaxLines(1);
        descriptionTextView.setEllipsize(TextUtils.TruncateAt.END);
        TextView titleTextView = c0394An.getTitleTextView();
        titleTextView.setMaxLines(1);
        titleTextView.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(0, fbButton.getId());
        layoutParams.setMargins(0, 0, V, 0);
        c0394An.setLayoutParams(layoutParams);
        return c0394An;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(boolean isWebViewCTA) {
        boolean z = (this.L || this.N.B()) ? false : true;
        if (this.S != null) {
            HashMap map = new HashMap();
            if (isWebViewCTA) {
                map.put("click_origin", "webview_click");
            } else {
                map.put("click_origin", "native_click");
            }
            this.S.oD(z, map);
        }
        if (z) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.facebook.ads.redexgen.X.85
                @Override // java.lang.Runnable
                public final void run() {
                    this.B.U();
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.facebook.ads.redexgen.X.8C] */
    private void P() {
        this.E.H(this.B.B(), true);
        this.E.setShowPageDetails(false);
        this.E.I(this.C.H(), this.C.E(), this.R.H(), this.C.K(), this.C.L(), this.C.J());
        this.E.setToolbarListener(new F1() { // from class: com.facebook.ads.redexgen.X.8A
            @Override // com.facebook.ads.redexgen.X.F1
            public final void jD() {
                this.B.M.A(EnumC0617Je.AD_CLOSE_CLICKED, null);
                if (this.B.S != null) {
                    this.B.S.iD();
                }
            }
        });
        if (C01972y.Q(getContext(), true)) {
            this.E.D(this.C.H(), this.C.E(), this.C.D());
        }
        J6.U(this.E);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.E.setLayoutParams(layoutParams);
        final Context context = getContext();
        final C01581l c01581l = this.C;
        this.Q = new RelativeLayout(context, c01581l) { // from class: com.facebook.ads.redexgen.X.8C
            private final C01581l B;
            private CN C;
            private LinearLayout D;
            private C0394An E;
            private static final int H = (int) (J6.B * 16.0f);
            private static final int I = (int) (J6.B * 16.0f);
            public static final int G = (int) (72.0f * J6.B);
            private static final RelativeLayout.LayoutParams F = new RelativeLayout.LayoutParams(-1, -1);

            {
                this.B = c01581l;
                B();
                C();
            }

            private void B() {
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setGravity(17);
                linearLayout.setOrientation(1);
                this.C = new CN(getContext());
                J6.O(this.C, 0);
                this.C.setRadius(50);
                new B7(this.C).B().E(this.B.H().B());
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(G, G);
                this.E = new C0394An(getContext(), this.B.A().B(), true, false, true);
                this.E.A(this.B.C().I(), this.B.C().H(), null, false, true);
                this.E.getDescriptionTextView().setAlpha(0.8f);
                this.E.setAlignment(17);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams3.setMargins(0, I, 0, I / 2);
                this.D = new LinearLayout(getContext());
                this.D.setGravity(17);
                this.D.setPadding(I, I / 2, I, I / 2);
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams4.setMargins(0, I / 2, 0, 0);
                C01571k c01571kD = this.B.B().D();
                TextView textView = new TextView(getContext());
                textView.setTextColor(-1);
                J6.Q(textView, false, 16);
                textView.setText(c01571kD.G());
                LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
                ImageView imageView = new ImageView(getContext());
                new B7(imageView).B().C(new C8L(imageView)).E(c01571kD.C());
                LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(H, H);
                layoutParams6.setMargins(0, 0, I / 2, 0);
                this.D.addView(imageView, layoutParams6);
                this.D.addView(textView, layoutParams5);
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(100.0f);
                gradientDrawable.setColor(469762047);
                J6.P(this.D, gradientDrawable);
                linearLayout.addView(this.C, layoutParams2);
                linearLayout.addView(this.E, layoutParams3);
                linearLayout.addView(this.D, layoutParams4);
                J6.O(this, -14473425);
                addView(linearLayout, F);
            }

            private void C() {
                D(this.C, IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED);
                D(this.E, BuildConfig.VERSION_CODE);
                D(this.D, FacebookRequestErrorClassification.EC_INVALID_TOKEN);
            }

            private void D(View view, int transitionY) {
                view.setTranslationY(transitionY);
                view.setScaleY(0.75f);
                view.setScaleX(0.75f);
                view.animate().translationYBy(-transitionY).scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator(new DecelerateInterpolator(2.0f));
            }
        };
        C0620Jh.C(this.Q, this.M, EnumC0617Je.INTRO_CARD_SHOWN);
        setLayoutParams(U);
        J6.O(this, this.B.B().D(true));
        addView(this.Q, U);
        J6.O(this, -14473425);
        setLayoutParams(U);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() {
        C8B c8b = null;
        if (this.S != null) {
            this.S.WE();
        }
        this.H = new RelativeLayout(getContext());
        J6.U(this.H);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(V, Y, V, Y);
        layoutParams.addRule(12);
        this.H.setLayoutParams(layoutParams);
        this.I = M();
        C0394An c0394AnN = N(this.I);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.I.getLayoutParams();
        layoutParams2.addRule(6, c0394AnN.getId());
        layoutParams2.addRule(8, c0394AnN.getId());
        BB bbL = L();
        bbL.loadUrl(getMarkupUrl());
        bbL.setOnTouchListener(new AnonymousClass83(bbL, this.D, this.C));
        if (Build.VERSION.SDK_INT > 16) {
            bbL.addJavascriptInterface(new AnonymousClass82(this), "FbPlayableAd");
        }
        bbL.setCornerRadius(W);
        J6.O(this, -14473425);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams3.setMargins(V, 0, V, 0);
        layoutParams3.addRule(3, this.E.getId());
        layoutParams3.addRule(2, this.H.getId());
        bbL.setLayoutParams(layoutParams3);
        bbL.setVisibility(4);
        bbL.setOnAssetsLoadedListener(this);
        this.H.addView(c0394AnN);
        this.H.addView(this.I);
        addView(this.E);
        addView(bbL);
        addView(this.H);
        this.E.setVisibility(4);
        bbL.setVisibility(4);
        bbL.setTranslationY(50.0f);
        this.H.setVisibility(4);
        this.H.setTranslationY(200.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        this.M.A(EnumC0617Je.PLAYABLE_CTA_CLICKED, null);
    }

    private void S() {
        if (!this.L) {
            J6.H(this, 500);
            this.I.setVisibility(0);
        }
    }

    private void T() {
        BB adWebView = getAdWebView();
        if (adWebView == null) {
            return;
        }
        J6.G(this);
        adWebView.setVisibility(0);
        J6.V(this.Q, 8);
        this.E.setVisibility(0);
        this.H.setVisibility(0);
        adWebView.animate().setStartDelay(100L).setDuration(300L).translationYBy(-50.0f);
        this.H.animate().setStartDelay(100L).setDuration(300L).translationYBy(-200.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U() {
        if (this.J != null && this.J.getView().getWindowVisibility() == 0) {
            return;
        }
        this.J = Toast.makeText(getContext(), this.R.B(), 1);
        V(this.N.A());
        this.J.show();
    }

    private void V(int secs) {
        J6.L(this.J, this.R.B().replace("[secs]", String.valueOf(secs)), 49, 0, X);
    }

    @Override // com.facebook.ads.redexgen.X.BG
    public final void XD() {
        if (!this.K && this.F.get() != null) {
            T();
        }
    }

    @Nullable
    public BB getAdWebView() {
        if (this.F != null) {
            return this.F.get();
        }
        return null;
    }

    private String getMarkupUrl() {
        if (!TextUtils.isEmpty(this.R.A())) {
            return this.R.A();
        }
        return this.R.D();
    }

    @Override // com.facebook.ads.redexgen.X.MR
    public final void mD() {
        if (this.S != null) {
            this.S.qE();
        }
        this.E.E(true);
        S();
    }

    @Override // com.facebook.ads.redexgen.X.MR
    public final void uE(int curSec) {
        this.E.setProgress(100.0f * (1.0f - (curSec / this.R.H())));
        V(curSec);
    }
}
