package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ax, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0404Ax extends LinearLayout {
    private View B;
    private TextView C;
    private LinearLayout D;
    private C0405Ay E;

    public C0404Ax(Context context, KF nativeAd, KI ki, View adIconView, AdOptionsView adOptionsView, boolean addDescription, int height) {
        super(context);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        setVerticalGravity(16);
        setOrientation(1);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(15.0f * displayMetrics.density), Math.round(15.0f * displayMetrics.density), Math.round(15.0f * displayMetrics.density), Math.round(15.0f * displayMetrics.density));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        this.D = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 0);
        this.D.setOrientation(0);
        this.D.setGravity(16);
        layoutParams2.weight = 3.0f;
        this.D.setLayoutParams(layoutParams2);
        linearLayout.addView(this.D);
        this.B = adIconView;
        int iB = B(addDescription, height);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(Math.round(iB * displayMetrics.density), Math.round(iB * displayMetrics.density));
        int iconSize = Math.round(15.0f * displayMetrics.density);
        layoutParams3.setMargins(0, 0, iconSize, 0);
        this.B.setLayoutParams(layoutParams3);
        this.D.addView(this.B);
        LinearLayout linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        this.D.addView(linearLayout2);
        this.E = new C0405Ay(getContext(), nativeAd, ki, adOptionsView);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -1);
        layoutParams4.setMargins(0, 0, Math.round(15.0f * displayMetrics.density), 0);
        layoutParams4.weight = 0.5f;
        this.E.setLayoutParams(layoutParams4);
        linearLayout2.addView(this.E);
        this.C = new TextView(getContext());
        this.C.setPadding(Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density), Math.round(6.0f * displayMetrics.density));
        this.C.setText(nativeAd.L("call_to_action"));
        this.C.setTextSize(14.0f);
        this.C.setMaxLines(2);
        this.C.setEllipsize(TextUtils.TruncateAt.END);
        this.C.setGravity(17);
        ki.F(this.C);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.weight = 0.25f;
        this.C.setLayoutParams(layoutParams5);
        if (!nativeAd.hasCallToAction()) {
            this.C.setVisibility(4);
        }
        linearLayout2.addView(this.C);
        if (addDescription) {
            CH ch = new CH(getContext());
            ch.setText(nativeAd.getAdBodyText());
            ki.G(ch);
            ch.setMinTextSize(13.0f);
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, 0);
            layoutParams6.weight = 1.0f;
            ch.setLayoutParams(layoutParams6);
            ch.setGravity(80);
            linearLayout.addView(ch);
        }
    }

    private int B(boolean addDescription, int height) {
        int i = height - 30;
        double totalWeight = (addDescription ? 1 : 0) + 3;
        double d = 3.0d / totalWeight;
        double totalWeight2 = i;
        return (int) (totalWeight2 * d);
    }

    public TextView getCallToActionView() {
        return this.C;
    }

    public View getIconView() {
        return this.B;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        TextView titleTextView = this.E.getTitleTextView();
        if (titleTextView.getLayout().getLineEnd(titleTextView.getLineCount() - 1) < this.E.getMinVisibleTitleCharacters()) {
            this.D.removeView(this.B);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
