package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Bx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0430Bx extends LinearLayout {
    private final ImageView B;
    private boolean C;
    private final TextView D;
    private static final int F = (int) (J6.B * 16.0f);
    private static final int G = (int) (J6.B * 12.0f);
    private static final int H = (int) (J6.B * 12.0f);
    private static final int E = (int) (J6.B * 16.0f);

    public C0430Bx(Context context) {
        super(context);
        this.C = false;
        setOrientation(0);
        setPadding(F, G, F, G);
        this.B = new ImageView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(E, E);
        layoutParams.gravity = 17;
        this.D = new TextView(getContext());
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        addView(this.B, layoutParams);
        addView(this.D, layoutParams2);
        B();
    }

    public final void A() {
        setSelected(!this.C);
    }

    private void B() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(this.C ? -13272859 : -1315344);
        gradientDrawable.setCornerRadius(50.0f);
        J6.P(this, gradientDrawable);
        J6.Q(this.D, false, 14);
        int i = this.C ? -1 : -10459280;
        this.D.setTextColor(i);
        this.B.setColorFilter(i);
    }

    public void setData(String title, @Nullable EnumC0606Is icon) {
        this.D.setText(title);
        if (icon != null) {
            this.B.setImageBitmap(C0605Ir.E(icon));
            this.B.setVisibility(0);
            this.D.setPadding(H, 0, 0, 0);
        } else {
            this.B.setVisibility(8);
            this.D.setPadding(0, 0, 0, 0);
        }
        B();
    }

    @Override // android.view.View
    public void setSelected(boolean selected) {
        this.C = selected;
        B();
    }
}
