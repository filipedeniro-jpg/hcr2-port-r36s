package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.widget.FrameLayout;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class GR extends C0533Fw {
    private InterfaceC0624Jl B;

    public final void D(NativeAdLayout mediumRectTemplateLayout, Context context, NativeAd nativeAd, KI attributes) {
        CN cn = new CN(context);
        MediaView mediaView = new MediaView(context);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, mediumRectTemplateLayout);
        attributes.J(adOptionsView, 28);
        KJ kjK = ((KF) nativeAd.getInternalNativeAd()).K();
        if (JA.N(context) || kjK == KJ.RECT_DYNAMIC) {
            this.B = new C03047b(context, nativeAd, attributes, kjK, cn, mediaView, adOptionsView);
        } else {
            this.B = new C0625Jm(context, (KF) nativeAd.getInternalNativeAd(), adOptionsView, mediaView, cn, kjK, attributes);
        }
        J6.O(mediumRectTemplateLayout, attributes.A());
        nativeAd.registerViewForInteraction(mediumRectTemplateLayout, mediaView, cn, this.B.getViewsForInteraction());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        mediumRectTemplateLayout.addView(this.B.getView(), layoutParams);
    }

    @Override // com.facebook.ads.redexgen.X.C0515Fe, com.facebook.ads.internal.api.AdComponentViewParentApi
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.B.unregisterView();
    }
}
