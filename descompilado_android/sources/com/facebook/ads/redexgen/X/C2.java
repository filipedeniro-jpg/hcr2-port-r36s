package com.facebook.ads.redexgen.X;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C2 extends RelativeLayout {
    private final int B;
    private final int C;

    @Nullable
    private final C1 D;
    private final boolean E;
    private static final int I = (int) (J6.B * 16.0f);
    private static final int J = (int) (8.0f * J6.B);
    private static final int M = (int) (44.0f * J6.B);
    private static final int H = (int) (10.0f * J6.B);
    private static final int G = I - H;
    private static final int N = (int) (75.0f * J6.B);
    private static final int K = (int) (25.0f * J6.B);
    private static final int O = (int) (45.0f * J6.B);
    private static final int L = (int) (15.0f * J6.B);
    private static final int F = (int) (J6.B * 16.0f);

    private C2(C3 builder) {
        super(builder.B);
        this.D = builder.E;
        this.C = builder.M ? N : O;
        this.B = builder.M ? K : L;
        this.E = builder.G;
        setClickable(true);
        View viewE = E(builder);
        View viewD = D(builder);
        View footerView = getFooterView();
        J6.U(viewE);
        J6.U(viewD);
        J6.U(footerView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        layoutParams2.addRule(3, viewE.getId());
        layoutParams2.addRule(2, footerView.getId());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(12);
        layoutParams3.setMargins(I, 0, I, I);
        addView(viewE, layoutParams);
        addView(viewD, layoutParams2);
        addView(footerView, layoutParams3);
        footerView.setVisibility(builder.H ? 0 : 8);
    }

    public /* synthetic */ C2(C3 x0, C5 x1) {
        this(x0);
    }

    private View D(C3 builder) {
        ImageView imageView = new ImageView(getContext());
        imageView.setPadding(this.B, this.B, this.B, this.B);
        imageView.setImageBitmap(C0605Ir.E(builder.C));
        imageView.setColorFilter(-1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.C, this.C);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(builder.D);
        J6.P(imageView, gradientDrawable);
        layoutParams.gravity = 17;
        layoutParams.setMargins(I, 0, I, I);
        TextView textView = new TextView(getContext());
        J6.Q(textView, true, 20);
        textView.setTextColor(-14934495);
        textView.setText(builder.L);
        textView.setGravity(17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(I, 0, I, I);
        TextView textView2 = new TextView(getContext());
        J6.Q(textView2, false, 16);
        textView2.setTextColor(-10459280);
        textView2.setText(builder.K);
        textView2.setGravity(17);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(I, 0, I, I);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.addView(imageView, layoutParams);
        linearLayout.addView(textView, layoutParams2);
        linearLayout.addView(textView2, layoutParams3);
        if (builder.I) {
            C0430Bx c0430Bx = new C0430Bx(getContext());
            c0430Bx.setData(builder.F, EnumC0606Is.CHECKMARK);
            c0430Bx.setSelected(true);
            linearLayout.addView(c0430Bx, new LinearLayout.LayoutParams(-2, -2));
        }
        return linearLayout;
    }

    private View E(C3 builder) {
        LinearLayout headerView = new LinearLayout(getContext());
        headerView.setOrientation(0);
        if (builder.J) {
            ImageView imageView = new ImageView(getContext());
            imageView.setPadding(H, H, H, H);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setImageBitmap(C0605Ir.E(EnumC0606Is.CROSS));
            imageView.setOnClickListener(new C5(this));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(M, M);
            layoutParams.setMargins(G, G, G, G);
            headerView.addView(imageView, layoutParams);
        }
        return headerView;
    }

    private View getFooterView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(C0605Ir.E(EnumC0606Is.SETTINGS));
        imageView.setColorFilter(-13272859);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(F, F);
        layoutParams.gravity = 17;
        TextView textView = new TextView(getContext());
        J6.Q(textView, false, 16);
        textView.setTextColor(-13272859);
        textView.setPadding(J, J, J, J);
        textView.setText(C01972y.K(getContext()));
        LinearLayout.LayoutParams settingsIconParams = new LinearLayout.LayoutParams(-2, -2);
        settingsIconParams.gravity = 17;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setOnClickListener(new C4(this));
        linearLayout.addView(imageView, layoutParams);
        linearLayout.addView(textView, settingsIconParams);
        return linearLayout;
    }
}
