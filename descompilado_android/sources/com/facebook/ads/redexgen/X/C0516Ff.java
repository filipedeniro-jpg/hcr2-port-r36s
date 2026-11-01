package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.internal.api.AdComponentViewApi;
import com.facebook.ads.internal.api.AdOptionsViewApi;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ff, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class C0516Ff extends C0515Fe implements AdOptionsViewApi {
    private static final int E = (int) (J6.B * 23.0f);
    private static final int F = (int) (J6.B * 4.0f);
    private final ImageView B;
    private final AdOptionsView C;
    private final ImageView D;

    public C0516Ff(Context context, NativeAdBase nativeAdBase, @Nullable NativeAdLayout nativeAdLayout, AdOptionsView.Orientation orientation, int iconSizeDp, AdOptionsView adOptionsView) {
        this.C = adOptionsView;
        LinearLayout linearLayout = new LinearLayout(context);
        this.C.addView(linearLayout);
        linearLayout.setOrientation(orientation == AdOptionsView.Orientation.HORIZONTAL ? 0 : 1);
        this.D = B(EnumC0606Is.DEFAULT_INFO_ICON);
        this.B = B(EnumC0606Is.AD_CHOICES_ICON);
        linearLayout.addView(this.D);
        linearLayout.addView(this.B);
        setIconSizeDp(iconSizeDp);
        setIconColor(-10459280);
        ((KF) nativeAdBase.getInternalNativeAd()).Z(nativeAdLayout);
        ((KF) nativeAdBase.getInternalNativeAd()).W(this);
        C01360p c01360pE = ((KF) nativeAdBase.getInternalNativeAd()).E();
        if (c01360pE != null && c01360pE.W() && !c01360pE.A()) {
            linearLayout.setVisibility(8);
        } else {
            linearLayout.setOnClickListener(new ViewOnClickListenerC0514Fd(this, nativeAdBase));
            MZ.C(this.C, MZ.INTERNAL_AD_OPTIONS_VIEW);
        }
    }

    public C0516Ff(Context context, NativeAdBase nativeAdBase, @Nullable NativeAdLayout nativeAdLayout, AdOptionsView adOptionsView) {
        this(context, nativeAdBase, nativeAdLayout, AdOptionsView.Orientation.HORIZONTAL, 23, adOptionsView);
    }

    private ImageView B(EnumC0606Is iconRes) {
        ImageView imageView = new ImageView(this.C.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(F, F, F, F);
        imageView.setImageBitmap(C0605Ir.E(iconRes));
        return imageView;
    }

    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final void m251B(EnumC0606Is iconRes) {
        this.D.setImageBitmap(C0605Ir.E(iconRes));
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewApiProvider
    public final AdComponentViewApi getAdComponentViewApi() {
        return this;
    }

    @Override // com.facebook.ads.internal.api.AdOptionsViewApi
    public final void setIconColor(int color) {
        this.D.setColorFilter(color);
        this.B.setColorFilter(color);
    }

    @Override // com.facebook.ads.internal.api.AdOptionsViewApi
    public final void setIconSizeDp(int iconSizeDp) {
        int iMax = Math.max(E, (int) (J6.B * iconSizeDp));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(iMax, iMax);
        this.D.setLayoutParams(layoutParams);
        this.B.setLayoutParams(layoutParams);
    }

    @Override // com.facebook.ads.redexgen.X.C0515Fe, com.facebook.ads.internal.api.AdComponentViewParentApi
    public final void setLayoutParams(ViewGroup.LayoutParams params) {
        params.width = -2;
        params.height = -2;
        super.setLayoutParams(params);
    }

    @Override // com.facebook.ads.internal.api.AdOptionsViewApi
    public final void setSingleIcon(boolean singleIcon) {
        J6.V(this.D, singleIcon ? 8 : 0);
    }
}
