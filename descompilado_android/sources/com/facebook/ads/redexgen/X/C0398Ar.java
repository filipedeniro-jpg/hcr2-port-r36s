package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ar, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0398Ar extends LinearLayout {
    public C0398Ar(Context context, KF nativeAd, KI attributes) {
        super(context);
        float f = J6.B;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setVerticalGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(15.0f * f), Math.round(15.0f * f), Math.round(15.0f * f), Math.round(15.0f * f));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        String strL = nativeAd.L("headline");
        TextView textView = new TextView(getContext());
        textView.setText(TextUtils.isEmpty(strL) ? nativeAd.L("headline") : strL);
        attributes.I(textView);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setSingleLine(true);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(getContext());
        textView2.setText(nativeAd.getAdBodyText());
        attributes.G(textView2);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setMaxLines(2);
        linearLayout.addView(textView2);
    }
}
