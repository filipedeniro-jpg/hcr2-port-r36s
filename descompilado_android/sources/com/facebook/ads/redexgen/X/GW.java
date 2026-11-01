package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.internal.api.NativeAdViewApi;

/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class GW implements NativeAdViewApi {
    private static View B(Context context, NativeAd nativeAd, NativeAdView.Type type, @Nullable NativeAdViewAttributes attributes) {
        if (attributes == null) {
            attributes = new NativeAdViewAttributes();
        }
        KI ki = (KI) attributes.getInternalAttributes();
        ((KF) nativeAd.getInternalNativeAd()).b(KJ.B(type.getEnumCode()));
        ((KF) nativeAd.getInternalNativeAd()).a(ki);
        GR gr = new GR();
        NativeAdLayout nativeAdLayout = new NativeAdLayout(context, gr);
        gr.D(nativeAdLayout, context, nativeAd, ki);
        nativeAdLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, (int) (J6.B * type.getHeight())));
        return nativeAdLayout;
    }

    private static View C(Context context, NativeAd nativeAd, @Nullable NativeAdViewAttributes attributes) {
        if (attributes == null) {
            attributes = new NativeAdViewAttributes();
        }
        KI ki = (KI) attributes.getInternalAttributes();
        ((KF) nativeAd.getInternalNativeAd()).b(KJ.RECT_DYNAMIC);
        ((KF) nativeAd.getInternalNativeAd()).a(ki);
        GR gr = new GR();
        NativeAdLayout nativeAdLayout = new NativeAdLayout(context, gr);
        gr.D(nativeAdLayout, context, nativeAd, ki);
        return nativeAdLayout;
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewApi
    public final View render(Context context, NativeAd nativeAd) {
        return render(context, nativeAd, (NativeAdViewAttributes) null);
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewApi
    public final View render(Context context, NativeAd nativeAd, NativeAdView.Type type) {
        return render(context, nativeAd, type, null);
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewApi
    @SuppressLint({"CatchGeneralException"})
    public final View render(Context context, NativeAd nativeAd, NativeAdView.Type type, @Nullable NativeAdViewAttributes attributes) {
        try {
            return B(context, nativeAd, type, attributes);
        } catch (Throwable th) {
            return C0441Ci.B(context, th);
        }
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewApi
    @SuppressLint({"CatchGeneralException"})
    public final View render(Context context, NativeAd nativeAd, @Nullable NativeAdViewAttributes attributes) {
        try {
            return C(context, nativeAd, attributes);
        } catch (Throwable th) {
            return C0441Ci.B(context, th);
        }
    }
}
