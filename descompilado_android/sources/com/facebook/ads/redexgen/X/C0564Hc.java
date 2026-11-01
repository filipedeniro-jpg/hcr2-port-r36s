package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.api.AdNativeComponentView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0564Hc extends AdNativeComponentView {
    private final KI B;
    private final TextView C;
    private final RelativeLayout D;
    private final CM E;
    private final RelativeLayout F;
    private final LinearLayout G;
    private final TextView H;
    private final TextView I;
    public static final int Q = ((int) J6.B) * 500;
    private static final int R = (int) (J6.B * 500.0f);
    private static final int M = (int) (J6.B * 4.0f);
    private static final int K = (int) (J6.B * 8.0f);
    private static final int P = (int) (J6.B * 8.0f);
    private static final int N = (int) (J6.B * 4.0f);
    private static final int L = (int) J6.B;
    private static final int O = (int) (J6.B * 4.0f);
    private static final int J = (int) (((double) J6.B) * 0.5d);

    public C0564Hc(Context context, KI attributes) {
        super(context);
        this.B = attributes;
        this.I = new TextView(context);
        this.H = new TextView(context);
        this.C = new TextView(context);
        this.F = new RelativeLayout(context);
        this.G = new LinearLayout(context);
        this.D = new RelativeLayout(context);
        this.E = new CM(context);
        K();
        O();
        G();
        L();
        B();
        M();
        E();
        L();
        J();
        N();
    }

    private void B() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.weight = 1.0f;
        layoutParams.gravity = 1;
        this.G.addView(this.D, layoutParams);
    }

    private void C() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, this.H.getId());
        J6.M(this.C);
        this.F.addView(this.C, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        this.F.setBackgroundDrawable(null);
        this.F.setPadding(P, P, P, P);
        J6.M(this.F);
        this.G.addView(this.F, layoutParams2);
    }

    private void D() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(K, M, K, M);
        J6.M(this.C);
        this.G.addView(this.C, layoutParams);
    }

    private void E() {
        this.D.addView(this.E, new LinearLayout.LayoutParams(-2, -1));
    }

    private void F() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(8, this.E.getId());
        layoutParams.addRule(5, this.E.getId());
        layoutParams.addRule(7, this.E.getId());
        this.F.setPadding(P, 0, P, 0);
        this.F.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{-872415232, 0}));
        J6.M(this.F);
        this.D.addView(this.F, layoutParams);
    }

    private void G() {
        addView(this.G, new LinearLayout.LayoutParams(-2, -2));
    }

    private void H() {
        this.I.setTextColor(-1);
        this.H.setTextColor(-1);
        this.C.setTextColor(this.B.D());
    }

    private void I() {
        this.E.setMaxWidth(R);
        this.I.setTextColor(this.B.E(0.2f));
        this.H.setTextColor(this.B.E(0.4f));
        this.C.setTextColor(this.B.D());
        ((LinearLayout.LayoutParams) this.D.getLayoutParams()).gravity = 1;
    }

    private void J() {
        this.C.setPadding(L, L, L, L);
        this.C.setTextSize(14.0f);
        J6.U(this.C);
    }

    private void K() {
        setLayoutParams(new RelativeLayout.LayoutParams(-2, -1));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.B.B());
        gradientDrawable.setCornerRadius(O);
        gradientDrawable.setStroke(1, this.B.C());
        setBackgroundDrawable(gradientDrawable);
        setPadding(J, J, J, J);
    }

    private void L() {
        J6.U(this.D);
    }

    private void M() {
        this.E.setScaleType(ImageView.ScaleType.FIT_XY);
        this.E.setRadius(new float[]{O, O, O, O, 0.0f, 0.0f, 0.0f, 0.0f});
        this.E.setAdjustViewBounds(true);
        MZ.C(this.E, MZ.INTERNAL_AD_MEDIA);
        J6.U(this.E);
    }

    private void N() {
        J6.U(this.F);
        this.I.setTextSize(14.0f);
        J6.U(this.I);
        this.I.setMaxLines(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, N);
        this.F.addView(this.I, layoutParams);
        this.H.setTextSize(12.0f);
        J6.U(this.H);
        this.H.setMaxLines(1);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(3, this.I.getId());
        layoutParams2.setMargins(0, 0, 0, N);
        this.F.addView(this.H, layoutParams2);
    }

    private void O() {
        this.G.setOrientation(1);
        J6.U(this.G);
    }

    @Override // com.facebook.ads.internal.api.AdNativeComponentView
    public View getAdContentsView() {
        return this.E;
    }

    public ImageView getImageCardView() {
        return this.E;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (View.MeasureSpec.getSize(heightMeasureSpec) >= ((int) J6.B) * JA.b(getContext()) || View.MeasureSpec.getMode(heightMeasureSpec) == 0) {
            C();
            I();
        } else {
            D();
            F();
            H();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setButtonText(String buttonText) {
        if (buttonText == null || buttonText.trim().isEmpty()) {
            this.C.setVisibility(8);
            return;
        }
        SpannableString spannableString = new SpannableString(buttonText);
        spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 0);
        this.C.setText(spannableString);
    }

    public void setSubtitle(String subtitleText) {
        if (subtitleText == null || subtitleText.trim().isEmpty()) {
            this.H.setVisibility(8);
        }
        this.H.setText(subtitleText);
    }

    public void setTitle(String titleText) {
        if (titleText == null || titleText.trim().isEmpty()) {
            this.I.setVisibility(8);
        }
        this.I.setText(titleText);
    }
}
