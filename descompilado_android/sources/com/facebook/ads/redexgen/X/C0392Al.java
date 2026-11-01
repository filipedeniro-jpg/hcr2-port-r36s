package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Al, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0392Al extends LinearLayout {
    private final C1T B;
    private final InterfaceC0525Fo C;
    private LinearLayout D;

    @Nullable
    private H6 E;
    private TextView F;
    private TextView G;
    private final String H;
    private TextView I;
    private ImageView J;
    private TextView K;
    private final LinearLayout L;
    private TextView M;
    private ImageView N;
    private TextView O;
    private TextView P;
    private static final int T = (int) (14.0f * J6.B);
    private static final int U = (int) (J6.B * 8.0f);
    private static final int S = (int) (10.0f * J6.B);
    private static final int Q = (int) (J6.B * 8.0f);
    private static final int R = (int) (17.0f * J6.B);

    public C0392Al(Context context, String clientToken, C1T adCreativeType, InterfaceC0525Fo adListener, int anLogoType) {
        super(context);
        setOrientation(1);
        this.H = clientToken;
        this.B = adCreativeType;
        this.C = adListener;
        J();
        this.L = new LinearLayout(context);
        this.L.setOrientation(0);
        this.L.setGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = U / 2;
        addView(this.L, layoutParams);
        I(this.L);
        this.M = E();
        this.L.addView(this.M);
        H(this.L);
        this.I = E();
        this.L.addView(this.I);
        G(this.L);
        this.F = E();
        this.L.addView(this.F);
        F(this.L, anLogoType);
    }

    public final void A(String category, boolean isBold, int fontSizeSP, int textColor) {
        this.G.setText(category);
        this.G.setTextColor(textColor);
        J6.Q(this.G, isBold, fontSizeSP);
        this.G.setVisibility(TextUtils.isEmpty(category) ? 8 : 0);
        this.F.setVisibility(TextUtils.isEmpty(category) ? 8 : 0);
    }

    public final void B(String destinationTitle, boolean isBold, int fontSizeSP, int textColor) {
        this.K.setText(destinationTitle);
        this.K.setTextColor(textColor);
        J6.Q(this.K, isBold, fontSizeSP);
        this.J.setVisibility(TextUtils.isEmpty(destinationTitle) ? 8 : 0);
        this.K.setVisibility(TextUtils.isEmpty(destinationTitle) ? 8 : 0);
        this.I.setVisibility(TextUtils.isEmpty(destinationTitle) ? 8 : 0);
    }

    public final void C(String rating, boolean isBold, int fontSizeSP, int textColor) {
        this.O.setText(rating);
        this.O.setTextColor(textColor);
        J6.Q(this.O, isBold, fontSizeSP);
        this.N.setVisibility(TextUtils.isEmpty(rating) ? 8 : 0);
        this.O.setVisibility(TextUtils.isEmpty(rating) ? 8 : 0);
        this.M.setVisibility(TextUtils.isEmpty(rating) ? 8 : 0);
    }

    public final void D(String title, boolean isBold, int fontSizeSP, int textColor) {
        this.P.setText(title);
        this.P.setTextColor(textColor);
        J6.Q(this.P, isBold, fontSizeSP);
        this.P.setMaxLines(2);
        this.P.setEllipsize(TextUtils.TruncateAt.END);
    }

    private TextView E() {
        TextView textView = new TextView(getContext());
        textView.setText("·");
        textView.setTextColor(-1);
        J6.Q(textView, false, 14);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.leftMargin = U;
        layoutParams.rightMargin = U;
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public final void E(boolean showRating) {
        if (showRating) {
            if (!TextUtils.isEmpty(this.O.getText())) {
                this.N.setVisibility(0);
                this.O.setVisibility(0);
                this.M.setVisibility(0);
            }
            if (!TextUtils.isEmpty(this.G.getText())) {
                this.G.setVisibility(0);
                this.F.setVisibility(0);
            }
            this.J.setVisibility(8);
            this.K.setVisibility(8);
            this.I.setVisibility(8);
            return;
        }
        if (!TextUtils.isEmpty(this.K.getText())) {
            this.J.setVisibility(0);
            this.K.setVisibility(0);
            this.I.setVisibility(0);
        }
        this.N.setVisibility(8);
        this.O.setVisibility(8);
        this.M.setVisibility(8);
        this.G.setVisibility(8);
        this.F.setVisibility(8);
    }

    private void F(LinearLayout detailsContainer, int anLogoType) {
        this.D = new LinearLayout(getContext());
        this.D.setOrientation(0);
        this.D.setGravity(16);
        detailsContainer.addView(this.D, new LinearLayout.LayoutParams(-2, -1));
        ImageView imageView = new ImageView(getContext());
        if (1 == anLogoType) {
            imageView.setImageBitmap(C0605Ir.E(EnumC0606Is.AN_INFO_ICON));
        } else {
            imageView.setImageBitmap(C0605Ir.E(EnumC0606Is.DEFAULT_INFO_ICON));
        }
        imageView.setColorFilter(-1);
        this.D.addView(imageView, new LinearLayout.LayoutParams(T, T));
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setImageBitmap(C0605Ir.E(EnumC0606Is.AD_CHOICES_ICON));
        imageView2.setColorFilter(-1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(T, T);
        layoutParams.leftMargin = S;
        this.D.addView(imageView2, layoutParams);
        this.D.setOnClickListener(new ViewOnClickListenerC0393Am(this));
        J6.C(this, this.D, Q, R);
    }

    private void G(LinearLayout detailsContainer) {
        this.G = new TextView(getContext());
        this.G.setEllipsize(TextUtils.TruncateAt.END);
        this.G.setMaxLines(1);
        this.G.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        detailsContainer.addView(this.G);
    }

    private void H(LinearLayout detailsContainer) {
        this.K = new TextView(getContext());
        this.K.setEllipsize(TextUtils.TruncateAt.END);
        this.K.setMaxLines(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = U / 2;
        this.K.setLayoutParams(layoutParams);
        this.J = new ImageView(getContext());
        this.J.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.J.setColorFilter(-1);
        this.J.setImageBitmap(C0605Ir.E(this.B.equals(C1T.CONTEXTUAL_APP) ? EnumC0606Is.GOOGLE : EnumC0606Is.GLOBE));
        detailsContainer.addView(this.J, new LinearLayout.LayoutParams(T, T));
        detailsContainer.addView(this.K);
    }

    private void I(LinearLayout detailsContainer) {
        this.O = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = U / 2;
        this.O.setLayoutParams(layoutParams);
        this.N = new ImageView(getContext());
        this.N.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.N.setColorFilter(-1);
        this.N.setImageBitmap(C0605Ir.E(EnumC0606Is.RATINGS));
        detailsContainer.addView(this.N, new LinearLayout.LayoutParams(T, T));
        detailsContainer.addView(this.O);
    }

    private void J() {
        this.P = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = U;
        this.P.setLayoutParams(layoutParams);
        addView(this.P);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!changed) {
            return;
        }
        int size = View.MeasureSpec.getSize(0);
        this.L.measure(size, size);
        int measuredWidth = this.L.getMeasuredWidth();
        int i = measuredWidth - r;
        if (i > 0) {
            this.K.setMaxWidth(this.K.getWidth() - i);
            this.G.setMaxWidth(this.G.getWidth() - i);
        } else {
            this.K.setMaxWidth(measuredWidth);
            this.G.setMaxWidth(measuredWidth);
        }
    }

    public void setAdReportingFlowListener(H6 listener) {
        this.E = listener;
    }
}
