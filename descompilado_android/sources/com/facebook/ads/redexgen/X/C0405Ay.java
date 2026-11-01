package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ay, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0405Ay extends LinearLayout {
    private int B;
    private CI C;

    public C0405Ay(Context context, KF nativeAd, KI attributes, AdOptionsView adOptionsView) {
        super(context);
        setOrientation(1);
        setVerticalGravity(16);
        this.C = new CI(getContext(), 2);
        this.C.setMinTextSize(14.0f);
        this.C.setText(nativeAd.L("headline"));
        attributes.I(this.C);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.C, layoutParams);
        linearLayout.addView(adOptionsView, new LinearLayout.LayoutParams(-2, -2));
        this.B = nativeAd.L("headline") != null ? Math.min(nativeAd.L("headline").length(), 21) : 21;
        addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        addView(AbstractC03037a.B(context, nativeAd, attributes));
    }

    public int getMinVisibleTitleCharacters() {
        return this.B;
    }

    public TextView getTitleTextView() {
        return this.C;
    }
}
