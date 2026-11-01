package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.An, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0394An extends LinearLayout {
    private final TextView B;
    private final TextView C;
    private final TextView D;
    private static final float E = Resources.getSystem().getDisplayMetrics().density;
    private static final int G = (int) (6.0f * E);
    private static final int F = (int) (8.0f * E);

    public C0394An(Context context, C1Y colors, boolean textOverMedia, int titleFontSizeSp, int descriptionFontSizeSp, int itemSpacing) {
        super(context);
        setOrientation(1);
        this.D = new TextView(context);
        J6.Q(this.D, true, titleFontSizeSp);
        this.D.setTextColor(colors.C(textOverMedia));
        this.D.setEllipsize(TextUtils.TruncateAt.END);
        this.D.setLineSpacing(G, 1.0f);
        this.C = new TextView(context);
        this.C.setTextColor(colors.A(textOverMedia));
        this.B = new TextView(context);
        J6.Q(this.B, false, descriptionFontSizeSp);
        this.B.setTextColor(colors.B(textOverMedia));
        this.B.setEllipsize(TextUtils.TruncateAt.END);
        this.B.setLineSpacing(G, 1.0f);
        addView(this.D, new LinearLayout.LayoutParams(-1, -2));
        addView(this.C, new LinearLayout.LayoutParams(-1, -2));
        this.C.setVisibility(8);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, itemSpacing, 0, 0);
        addView(this.B, layoutParams);
    }

    public C0394An(Context context, C1Y colors, boolean textOverMedia, boolean useSmallerFonts, boolean useSmallerItemSpacing) {
        this(context, colors, textOverMedia, useSmallerFonts ? 18 : 22, useSmallerFonts ? 14 : 16, useSmallerItemSpacing ? F / 2 : F);
    }

    public final void A(String title, String description, @Nullable String sponsoredText, boolean isCarousel, boolean longerDescription) {
        int i = 2;
        int i2 = 1;
        boolean z = !TextUtils.isEmpty(title);
        boolean z2 = TextUtils.isEmpty(description) ? false : true;
        TextView textView = this.D;
        if (!z) {
            title = description;
        }
        textView.setText(title);
        if (sponsoredText != null) {
            this.C.setText(sponsoredText);
        }
        TextView textView2 = this.B;
        if (!z) {
            description = "";
        }
        textView2.setText(description);
        if (!z || !z2) {
            TextView textView3 = this.D;
            if (!isCarousel) {
                i = longerDescription ? 4 : 3;
            }
            textView3.setMaxLines(i);
            return;
        }
        this.D.setMaxLines(isCarousel ? 1 : 2);
        TextView textView4 = this.B;
        if (!isCarousel) {
            i2 = longerDescription ? 3 : 2;
        }
        textView4.setMaxLines(i2);
    }

    public final void B(boolean show, int gravity) {
        if (show) {
            this.C.setGravity(gravity);
            this.C.setVisibility(0);
        } else {
            this.C.setVisibility(8);
        }
    }

    public TextView getDescriptionTextView() {
        return this.B;
    }

    public TextView getTitleTextView() {
        return this.D;
    }

    public void setAlignment(int gravity) {
        this.D.setGravity(gravity);
        this.B.setGravity(gravity);
    }

    public void setDescriptionGravity(int gravity) {
        this.B.setGravity(gravity);
    }

    public void setTitleGravity(int gravity) {
        this.D.setGravity(gravity);
    }
}
