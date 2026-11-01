package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class CE extends LinearLayout {
    public final C0406Az B;
    public int C;
    private final FrameLayout D;
    private final ImageView E;
    private final TextView F;
    private String G;
    private static final int J = (int) (50.0f * J6.B);
    private static final int H = (int) (10.0f * J6.B);
    private static final int I = (int) (J6.B * 20.0f);
    private static final int L = (int) (6.0f * J6.B);
    private static final int K = (int) (J6.B * 20.0f);

    public CE(Context context, int actionMode) {
        super(context);
        this.E = new ImageView(context);
        this.B = new C0406Az(context);
        this.B.setProgress(0.0f);
        this.F = new TextView(context);
        setOrientation(0);
        setPadding(H, H, H, H);
        this.D = new FrameLayout(context);
        this.C = actionMode;
        B();
    }

    public final boolean A() {
        return (this.C == 2 || this.C == 4) ? false : true;
    }

    private void B() {
        setToolbarActionMode(this.C);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        setGravity(17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(I, I);
        J6.Q(this.F, true, 16);
        this.F.setTextColor(-1);
        this.F.setVisibility(8);
        this.D.addView(this.E, layoutParams2);
        this.D.addView(this.B, layoutParams2);
        addView(this.D, layoutParams);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 1;
        layoutParams3.setMargins(L, 0, 0, 0);
        addView(this.F, layoutParams3);
        C(this, false);
    }

    private static void C(View view, boolean hasMessage) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(hasMessage ? J : K);
        gradientDrawable.setColor(ViewCompat.MEASURED_STATE_MASK);
        J6.P(view, gradientDrawable);
    }

    public void setActionClickListener(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }

    public void setColors(int accentColor) {
        this.B.A(C5W.B(accentColor, 77), accentColor);
        this.E.setColorFilter(accentColor);
    }

    public void setInitialUnskippableSeconds(int unskippableSeconds) {
        if (unskippableSeconds > 0) {
            this.B.setFillUp(true);
            setToolbarActionMode(2);
        }
    }

    public void setProgress(float progress) {
        this.B.setProgressWithAnimation(progress);
    }

    public void setSkipMessage(String message) {
        this.G = message;
    }

    public void setToolbarActionMode(int actionMode) {
        this.C = actionMode;
        EnumC0606Is enumC0606Is = EnumC0606Is.CROSS;
        this.B.setVisibility(actionMode == 2 ? 0 : 8);
        this.E.setVisibility(actionMode == 2 ? 8 : 0);
        setVisibility(0);
        switch (actionMode) {
            case 0:
                enumC0606Is = EnumC0606Is.CROSS;
                break;
            case 1:
                enumC0606Is = EnumC0606Is.SKIP_ARROW;
                if (TextUtils.isEmpty(this.F.getText())) {
                    setToolbarMessage(this.G);
                }
                break;
            case 3:
                enumC0606Is = EnumC0606Is.MINIMIZE_ARROW;
                break;
            case 4:
                this.E.setVisibility(8);
                setVisibility(8);
                break;
        }
        this.E.setImageBitmap(C0605Ir.E(enumC0606Is));
    }

    public void setToolbarMessage(String message) {
        this.F.setText(message);
        C(this, !TextUtils.isEmpty(message));
        this.F.setVisibility(TextUtils.isEmpty(message) ? 8 : 0);
    }
}
