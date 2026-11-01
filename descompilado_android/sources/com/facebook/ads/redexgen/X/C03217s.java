package com.facebook.ads.redexgen.X;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7s, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03217s extends C0401Au {
    private ImageView B;
    private final C01601n C;
    private RelativeLayout D;
    private final JV E;

    @Nullable
    private EnumC01982z F;

    @Nullable
    private AnonymousClass31 G;
    private final C5I H;
    private final Map<String, String> I;
    private boolean J;
    private boolean K;
    private boolean L;
    private boolean M;
    private C02675q N;
    private ObjectAnimator O;
    private boolean P;
    private C0392Al Q;
    private final C0621Ji R;
    private static final int U = (int) (48.0f * J6.B);
    private static final int W = (int) (40.0f * J6.B);
    private static final int T = (int) (16.0f * J6.B);
    private static final int S = (int) (56.0f * J6.B);
    private static final int V = (int) (200.0f * J6.B);

    public C03217s(Context context, C01601n adDataBundle, JV adEventManager, C5I adViewabilityChecker, C0621Ji touchDataRecorder, InterfaceC0525Fo adListener) {
        super(context);
        this.I = new HashMap();
        this.J = false;
        this.C = adDataBundle;
        this.E = adEventManager;
        this.H = adViewabilityChecker;
        this.R = touchDataRecorder;
        this.B = new CN(context);
        this.N = new C02675q(context, true, null);
        this.N.setClickable(false);
        E();
        F(adDataBundle.A(), adListener);
        H(this.P);
    }

    public final void A(int adPosition) {
        this.I.put("ad_intro_position", String.valueOf(adPosition));
    }

    public final void B(AnonymousClass31 reason, EnumC01982z type) {
        this.J = true;
        this.G = reason;
        this.F = type;
        G(reason, type);
    }

    public final boolean C() {
        return this.J;
    }

    private View D(AnonymousClass31 reason, EnumC01982z type) {
        String strD;
        EnumC0606Is enumC0606Is;
        int i;
        if (type == EnumC01982z.REPORT) {
            strD = C01972y.E(getContext());
            enumC0606Is = EnumC0606Is.REPORT_AD;
            i = -552389;
        } else {
            strD = C01972y.D(getContext());
            enumC0606Is = EnumC0606Is.HIDE_AD;
            i = -13272859;
        }
        return new C3(getContext()).K(strD).J(C01972y.C(getContext())).E(reason.F()).H(false).B(enumC0606Is).C(i).L(false).G(false).I(false).A();
    }

    public final void D(boolean isPortrait) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.width = isPortrait ? -1 : 0;
        layoutParams.height = isPortrait ? 0 : -1;
    }

    private void E() {
        this.B.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(this.B, new RelativeLayout.LayoutParams(-1, -1));
        new B7(this.B).B().C(new C1D() { // from class: com.facebook.ads.redexgen.X.7u
            @Override // com.facebook.ads.redexgen.X.C1D
            public final void FE(boolean success) {
                this.B.L = success;
                this.B.I();
            }
        }).E(this.C.K().B());
    }

    public final void F() {
        if (this.O != null && !this.K) {
            if (Build.VERSION.SDK_INT >= 19) {
                this.O.pause();
            } else {
                this.O.cancel();
            }
        }
    }

    private void F(String clientToken, InterfaceC0525Fo adListener) {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setPadding(T, T, T, T);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(relativeLayout);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, -872415232});
        gradientDrawable.setCornerRadius(0.0f);
        J6.P(relativeLayout, gradientDrawable);
        RelativeLayout adDetailsContainer = new RelativeLayout(getContext());
        this.D = adDetailsContainer;
        J6.U(this.D);
        RelativeLayout.LayoutParams adDetailSubContainerParams = new RelativeLayout.LayoutParams(-2, -2);
        adDetailSubContainerParams.addRule(12);
        relativeLayout.addView(this.D, adDetailSubContainerParams);
        this.Q = new C0392Al(getContext(), clientToken, this.C.L().A(), adListener, this.C.M());
        this.Q.D(this.C.L().I(), true, 22, -1);
        this.Q.C(this.C.L().F(), false, 14, -1);
        this.Q.B(this.C.L().D(), false, 14, -1);
        this.Q.A(this.C.L().B(), false, 14, -1);
        this.D.addView(this.Q, new RelativeLayout.LayoutParams(-1, -2));
        CN cn = new CN(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(U, U);
        layoutParams.addRule(2, this.D.getId());
        cn.setLayoutParams(layoutParams);
        cn.setFullCircleCorners(this.C.L().A().equals(C1T.PAGE_POST));
        relativeLayout.addView(cn);
        new B7(cn).D(U, U).E(this.C.P().B());
    }

    public final void G() {
        if (this.O != null && !this.K) {
            if (Build.VERSION.SDK_INT >= 19) {
                this.O.resume();
            } else {
                this.O.start();
            }
        }
    }

    private void G(AnonymousClass31 reason, EnumC01982z type) {
        View adHiddenViewTextOnly;
        if (getWidth() >= V && getHeight() >= V) {
            adHiddenViewTextOnly = D(reason, type);
        } else {
            adHiddenViewTextOnly = getAdHiddenViewTextOnly();
        }
        J6.O(adHiddenViewTextOnly, -1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        adHiddenViewTextOnly.setLayoutParams(layoutParams);
        removeAllViews();
        addView(adHiddenViewTextOnly);
    }

    private void H(boolean playButtonsAtTop) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.Q.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(W, W);
        if (playButtonsAtTop) {
            layoutParams.rightMargin = 0;
            layoutParams2.topMargin = T;
            layoutParams2.rightMargin = T;
            layoutParams2.addRule(11);
            layoutParams2.addRule(10);
            addView(this.N, layoutParams2);
            return;
        }
        layoutParams.rightMargin = S;
        layoutParams2.addRule(15);
        layoutParams2.addRule(11);
        this.D.addView(this.N, layoutParams2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        if (this.M && this.L) {
            this.H.A(this.I);
            this.I.put("touch", MJ.C(this.R.m258B()));
            this.I.put("is_cyoa", Boolean.TRUE.toString());
            this.E.rC(this.C.A(), this.I);
        }
    }

    public final void I(boolean showRating) {
        this.Q.E(showRating);
    }

    public final void J(boolean isReverse, int animationDurationMS) {
        if (this.O != null) {
            this.O.cancel();
        }
        float f = isReverse ? 1.01f : 0.99f;
        float f2 = isReverse ? 0.99f : 1.01f;
        this.O = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("scaleX", f, f2), PropertyValuesHolder.ofFloat("scaleY", f, f2));
        this.O.setInterpolator(new C0396Ap());
        this.O.setDuration(animationDurationMS);
        this.O.setRepeatCount(-1);
        this.O.setRepeatMode(2);
        this.O.start();
        this.K = false;
    }

    public final void K() {
        if (this.O != null) {
            this.O.cancel();
        }
        this.K = true;
    }

    public C01601n getAdDataBundle() {
        return this.C;
    }

    private View getAdHiddenViewTextOnly() {
        TextView textView = new TextView(getContext());
        J6.Q(textView, true, 14);
        textView.setText(C01972y.C(getContext()));
        textView.setGravity(17);
        return textView;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && C()) {
            removeAllViews();
            G(this.G, this.F);
        }
    }

    public void setAdReportingFlowListener(H6 listener) {
        this.Q.setAdReportingFlowListener(listener);
    }

    public void setShouldPlayButtonOnTop(boolean shouldPlayButtonOnTop) {
        if (shouldPlayButtonOnTop != this.P) {
            this.P = shouldPlayButtonOnTop;
            J6.M(this.N);
            H(this.P);
        }
    }

    public void setViewability(boolean isViewable) {
        this.M = isViewable;
        I();
    }
}
