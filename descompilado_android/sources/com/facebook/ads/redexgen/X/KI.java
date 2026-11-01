package com.facebook.ads.redexgen.X;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.internal.api.NativeAdViewAttributesApi;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class KI implements NativeAdViewAttributesApi {
    private static final int J = (int) (J6.B * 4.0f);
    private static final int I = (int) (J6.B * 1.0f);
    private Typeface H = Typeface.create(Typeface.SANS_SERIF, 0);
    private int B = -1;
    private int F = -14868183;
    private int G = -10393744;
    private int C = -1;
    private int E = -12420889;
    private int D = this.E;

    public final int A() {
        return this.B;
    }

    public final int B() {
        return this.C;
    }

    public final int C() {
        return this.D;
    }

    public final int D() {
        return this.E;
    }

    public final int E(float blendRatio) {
        return C5W.C((this.C & ViewCompat.MEASURED_STATE_MASK) | ((this.C ^ (-1)) & ViewCompat.MEASURED_SIZE_MASK), this.C, blendRatio);
    }

    public final void F(TextView ctaButton) {
        ctaButton.setTextSize(2, 14.0f);
        ctaButton.setTypeface(this.H, 1);
        ctaButton.setGravity(17);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.C);
        gradientDrawable.setCornerRadius(J);
        gradientDrawable.setStroke(I, this.D);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(this.E);
        gradientDrawable2.setCornerRadius(J);
        gradientDrawable2.setStroke(I, this.D);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, gradientDrawable2);
        stateListDrawable.addState(new int[0], gradientDrawable);
        J6.P(ctaButton, stateListDrawable);
        ctaButton.setTextColor(new ColorStateList(new int[][]{new int[]{R.attr.state_pressed}, new int[0]}, new int[]{this.C, this.E}));
    }

    public final void G(TextView description) {
        description.setTextColor(this.G);
        description.setTextSize(14.0f);
        description.setTypeface(this.H);
        description.setEllipsize(TextUtils.TruncateAt.END);
    }

    public final void H(TextView headline) {
        headline.setTextColor(this.F);
        headline.setTextSize(16.0f);
        headline.setTypeface(this.H, 1);
        headline.setEllipsize(TextUtils.TruncateAt.END);
    }

    public final void I(TextView title) {
        title.setTextColor(this.F);
        title.setTextSize(16.0f);
        title.setTypeface(this.H, 1);
        title.setEllipsize(TextUtils.TruncateAt.END);
    }

    public final void J(AdOptionsView adOptionsView, int iconSizeDp) {
        adOptionsView.setIconColor(this.F);
        adOptionsView.setIconSizeDp(iconSizeDp);
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewAttributesApi
    public final void setBackgroundColor(int backgroundColor) {
        this.B = backgroundColor;
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewAttributesApi
    public final void setCTABackgroundColor(int ctaBackgroundColor) {
        this.C = ctaBackgroundColor;
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewAttributesApi
    public final void setCTABorderColor(int ctaBorderColor) {
        this.D = ctaBorderColor;
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewAttributesApi
    public final void setCTATextColor(int ctaTextColor) {
        this.E = ctaTextColor;
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewAttributesApi
    public final void setPrimaryTextColor(int primaryTextColor) {
        this.F = primaryTextColor;
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewAttributesApi
    public final void setSecondaryTextColor(int secondaryTextColor) {
        this.G = secondaryTextColor;
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewAttributesApi
    public final void setTypeface(Typeface typeface) {
        this.H = typeface;
    }
}
