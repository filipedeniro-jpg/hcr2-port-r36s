package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.widget.FrameLayout;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeBannerAd;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0534Fx extends C0533Fw {
    private InterfaceC0624Jl B;

    public final void D(Context context, NativeBannerAd nativeBannerAd, KI attributes, NativeAdLayout bannerTemplateLayout) {
        MediaView mediaView = new MediaView(bannerTemplateLayout.getContext());
        AdOptionsView adOptionsView = new AdOptionsView(bannerTemplateLayout.getContext(), nativeBannerAd, bannerTemplateLayout);
        attributes.J(adOptionsView, 20);
        KJ kjK = ((KF) nativeBannerAd.getInternalNativeAd()).K();
        KJ adType = KJ.HEIGHT_50;
        if (kjK == adType || JA.M(bannerTemplateLayout.getContext())) {
            this.B = new C03057c(context, nativeBannerAd, attributes, kjK, mediaView, adOptionsView);
        } else {
            this.B = new C0625Jm(context, (KF) nativeBannerAd.getInternalNativeAd(), adOptionsView, null, mediaView, kjK, attributes);
        }
        J6.O(bannerTemplateLayout, attributes.A());
        nativeBannerAd.registerViewForInteraction(bannerTemplateLayout, mediaView, this.B.getViewsForInteraction());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        bannerTemplateLayout.addView(this.B.getView(), layoutParams);
    }

    @Override // com.facebook.ads.redexgen.X.C0515Fe, com.facebook.ads.internal.api.AdComponentViewParentApi
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.B.unregisterView();
    }
}
