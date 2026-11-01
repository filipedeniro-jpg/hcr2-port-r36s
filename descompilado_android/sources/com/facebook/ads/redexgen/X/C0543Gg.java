package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;
import com.facebook.ads.internal.api.NativeBannerAdViewApi;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0543Gg implements NativeBannerAdViewApi {
    private static View B(Context context, NativeBannerAd nativeBannerAd, NativeBannerAdView.Type type, @Nullable NativeAdViewAttributes attributes) {
        if (attributes == null) {
            attributes = new NativeAdViewAttributes();
        }
        ((KF) nativeBannerAd.getInternalNativeAd()).b(KJ.B(type.getEnumCode()));
        C0534Fx c0534Fx = new C0534Fx();
        NativeAdLayout nativeAdLayout = new NativeAdLayout(context, c0534Fx);
        c0534Fx.D(context, nativeBannerAd, (KI) attributes.getInternalAttributes(), nativeAdLayout);
        nativeAdLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, (int) (J6.B * type.getHeight())));
        return nativeAdLayout;
    }

    @Override // com.facebook.ads.internal.api.NativeBannerAdViewApi
    public final View render(Context context, NativeBannerAd nativeBannerAd, NativeBannerAdView.Type type) {
        return render(context, nativeBannerAd, type, null);
    }

    @Override // com.facebook.ads.internal.api.NativeBannerAdViewApi
    @SuppressLint({"CatchGeneralException"})
    public final View render(Context context, NativeBannerAd nativeBannerAd, NativeBannerAdView.Type type, @Nullable NativeAdViewAttributes attributes) {
        try {
            return B(context, nativeBannerAd, type, attributes);
        } catch (Throwable th) {
            return C0441Ci.B(context, th);
        }
    }
}
