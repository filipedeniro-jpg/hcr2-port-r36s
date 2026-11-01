package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Cj, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0442Cj extends RelativeLayout {
    private static final int G = (int) (40.0f * J6.B);
    private static final int H = (int) (16.0f * J6.B);
    private static final int I = (int) (24.0f * J6.B);
    public final Button B;
    public final Button C;
    private final LinearLayout D;
    private final ImageView E;
    private final TextView F;

    public C0442Cj(Context context, int primaryColor, int secondaryColor, String message, String positiveBtnText, String negativeBtnText, Bitmap icon) {
        super(context);
        this.F = new TextView(context);
        this.C = new Button(context);
        this.B = new Button(context);
        this.E = new ImageView(context);
        this.D = new LinearLayout(context);
        this.F.setText(message);
        this.F.setTextColor(primaryColor);
        J6.Q(this.F, true, 10);
        this.F.setTextSize(J6.B(10));
        this.E.setImageBitmap(icon);
        this.E.setColorFilter(primaryColor);
        this.C.setText(positiveBtnText);
        this.B.setText(negativeBtnText);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(primaryColor);
        gradientDrawable.setCornerRadius(H);
        J6.P(this.C, gradientDrawable);
        this.C.setTextColor(secondaryColor);
        this.C.setAllCaps(false);
        this.C.setTextSize(J6.B(8));
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(primaryColor);
        gradientDrawable2.setAlpha(20);
        gradientDrawable2.setCornerRadius(H);
        this.B.setTextColor(primaryColor);
        this.B.setAllCaps(false);
        this.B.setTextSize(J6.B(8));
        J6.P(this.B, gradientDrawable2);
        B();
        setGravity(17);
    }

    private void B() {
        setPadding(I, I, I, I);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        this.D.setOrientation(1);
        this.D.setGravity(14);
        addView(this.D, layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(G, G);
        layoutParams2.setMargins(0, 0, 0, H);
        layoutParams2.gravity = 1;
        this.D.addView(this.E, layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(0, 0, 0, H);
        this.F.setGravity(17);
        this.D.addView(this.F, layoutParams3);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams4.setMargins(0, 0, 0, H);
        this.D.addView(this.C, layoutParams4);
        this.D.addView(this.B, layoutParams4);
    }
}
