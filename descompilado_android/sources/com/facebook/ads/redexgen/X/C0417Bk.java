package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Bk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0417Bk extends LinearLayout {
    private final ImageView B;
    private final TextView C;
    private final LinearLayout D;
    private static final int F = (int) (8.0f * J6.B);
    private static final int G = (int) (14.5d * ((double) J6.B));
    private static final int E = (int) (20.0f * J6.B);
    public static final LinearLayout.LayoutParams H = new LinearLayout.LayoutParams(-1, -2);

    public C0417Bk(Context context) {
        super(context);
        this.B = new ImageView(context);
        this.B.setColorFilter(-10459280);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(E, E);
        layoutParams.gravity = 16;
        this.B.setLayoutParams(layoutParams);
        this.D = new LinearLayout(context);
        this.D.setOrientation(1);
        this.D.setPadding(F * 2, 0, 0, 0);
        this.D.setLayoutParams(H);
        this.C = new TextView(context);
        J6.Q(this.C, true, 16);
        this.C.setTextColor(-14934495);
        this.D.addView(this.C, H);
        setOrientation(0);
        addView(this.B);
        addView(this.D);
    }

    public void setInfo(EnumC0606Is imageRes, String title, String subtitle) {
        this.B.setImageBitmap(C0605Ir.E(imageRes));
        this.C.setText(title);
        if (!TextUtils.isEmpty(subtitle)) {
            TextView textView = new TextView(getContext());
            J6.Q(textView, false, 14);
            textView.setTextColor(-10459280);
            textView.setText(subtitle);
            this.D.addView(textView, H);
            setPadding(0, F, 0, F);
            return;
        }
        setPadding(0, G, 0, G);
    }
}
