package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.api.NativeAdBaseApi;
import com.facebook.ads.internal.api.NativeBannerAdApi;
import com.facebook.ads.internal.protocol.AdErrorType;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0542Gf implements NativeBannerAdApi {
    private final KF B;

    public C0542Gf(NativeAdBaseApi nativeAdBaseApi) {
        this.B = (KF) nativeAdBaseApi;
        this.B.X(KW.NATIVE_BANNER);
    }

    private void B(ImageView iconView, NativeAdBaseApi nativeAdBaseApi) {
        C0539Gc c0539Gc = null;
        KF internalNativeAd = (KF) nativeAdBaseApi;
        internalNativeAd.d(iconView);
        AsyncTaskC0540Gd asyncTaskC0540Gd = new AsyncTaskC0540Gd(new C0539Gc(this, iconView, internalNativeAd), c0539Gc);
        KG kgC = internalNativeAd.getAdIcon();
        if (kgC != null) {
            asyncTaskC0540Gd.execute(new C0541Ge(kgC.getUrl(), internalNativeAd.I(), c0539Gc));
            return;
        }
        K4 k4D = internalNativeAd.D();
        if (k4D != null) {
            k4D.yD(KQ.B(AdErrorType.NATIVE_AD_IS_NOT_LOADED));
        }
        Log.e(AudienceNetworkAds.TAG, AdErrorType.NATIVE_AD_IS_NOT_LOADED.getDefaultErrorMessage());
    }

    @Override // com.facebook.ads.internal.api.NativeBannerAdApi
    public final void registerViewForInteraction(View view, ImageView iconView) {
        registerViewForInteraction(view, iconView, (List<View>) null);
    }

    @Override // com.facebook.ads.internal.api.NativeBannerAdApi
    public final void registerViewForInteraction(View view, ImageView iconView, @Nullable List<View> list) {
        if (iconView != null) {
            B(iconView, this.B);
        }
        if (list != null) {
            this.B.T(view, iconView, list);
        } else {
            this.B.S(view, iconView);
        }
    }

    @Override // com.facebook.ads.internal.api.NativeBannerAdApi
    public final void registerViewForInteraction(View view, MediaView iconView) {
        registerViewForInteraction(view, iconView, (List<View>) null);
    }

    @Override // com.facebook.ads.internal.api.NativeBannerAdApi
    public final void registerViewForInteraction(View view, MediaView iconView, @Nullable List<View> list) {
        if (iconView != null) {
            ((GB) iconView.getMediaViewApi()).B(this.B, true);
        }
        if (list != null) {
            this.B.V(view, iconView, list);
        } else {
            this.B.U(view, iconView);
        }
    }
}
