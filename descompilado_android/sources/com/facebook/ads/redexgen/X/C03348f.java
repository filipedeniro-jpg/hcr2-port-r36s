package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8f, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03348f extends LinearLayout {
    public static final int E = (int) (J6.B * 32.0f);
    private static final int F = (int) (J6.B * 8.0f);
    private CN B;
    private TextView C;
    private TextView D;

    public C03348f(Context context) {
        super(context);
        B(context);
    }

    public final void A(int pageNameColor, int sponsoredTextColor) {
        this.C.setTextColor(pageNameColor);
        this.D.setTextColor(sponsoredTextColor);
    }

    private final void B(Context context) {
        setGravity(16);
        this.B = new CN(context);
        this.B.setFullCircleCorners(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(E, E);
        layoutParams.setMargins(0, 0, F, 0);
        addView(this.B, layoutParams);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        this.C = new TextView(context);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        J6.Q(this.C, true, 16);
        this.C.setEllipsize(TextUtils.TruncateAt.END);
        this.C.setSingleLine(true);
        this.D = new TextView(context);
        J6.Q(this.D, false, 14);
        linearLayout.addView(this.C);
        linearLayout.addView(this.D);
        addView(linearLayout, layoutParams2);
    }

    public void setPageDetails(C01551i pageDetails) {
        B7 b7 = new B7(this.B);
        b7.D(E, E);
        b7.E(pageDetails.B());
        this.C.setText(pageDetails.C());
        this.D.setText(pageDetails.D());
    }
}
