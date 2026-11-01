package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.VideoAutoplayBehavior;
import com.facebook.ads.internal.api.NativeAdApi;
import com.facebook.ads.internal.api.NativeAdBaseApi;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class GS implements NativeAdApi {
    private final NativeAd B;
    private final NativeAdBaseApi C;

    public GS(NativeAd nativeAd, NativeAdBaseApi nativeAdBaseApi) {
        this.B = nativeAd;
        this.C = nativeAdBaseApi;
        ((KF) this.C).X(KW.NATIVE_UNKNOWN);
    }

    public GS(NativeAdBase other, NativeAd nativeAd, NativeAdBaseApi nativeAdBaseApi) {
        this.B = nativeAd;
        this.C = nativeAdBaseApi;
    }

    @Nullable
    public final List<NativeAd> A() {
        if (B().F() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (KF kf : B().F()) {
            arrayList.add(new NativeAd(kf.H(), kf));
        }
        return arrayList;
    }

    private KF B() {
        return (KF) this.C;
    }

    @Nullable
    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final String m252B() {
        return B().N();
    }

    @Nullable
    public final String C() {
        return B().O();
    }

    @Override // com.facebook.ads.internal.api.NativeAdApi
    public final NativeAd.AdCreativeType getAdCreativeType() {
        if (!TextUtils.isEmpty(B().O())) {
            return NativeAd.AdCreativeType.VIDEO;
        }
        if (B().F() != null && !B().F().isEmpty()) {
            return NativeAd.AdCreativeType.CAROUSEL;
        }
        if (B().getAdCoverImage() != null && !TextUtils.isEmpty(B().getAdCoverImage().getUrl())) {
            return NativeAd.AdCreativeType.IMAGE;
        }
        return NativeAd.AdCreativeType.UNKNOWN;
    }

    @Override // com.facebook.ads.internal.api.NativeAdApi
    public final VideoAutoplayBehavior getVideoAutoplayBehavior() {
        return KL.B(B().M());
    }

    @Override // com.facebook.ads.internal.api.NativeAdApi
    public final void registerViewForInteraction(View view, MediaView mediaView) {
        registerViewForInteraction(view, mediaView, (MediaView) null);
    }

    @Override // com.facebook.ads.internal.api.NativeAdApi
    public final void registerViewForInteraction(View view, MediaView mediaView, @Nullable ImageView iconView) {
        registerViewForInteraction(view, mediaView, iconView, (List<View>) null);
    }

    @Override // com.facebook.ads.internal.api.NativeAdApi
    public final void registerViewForInteraction(View view, MediaView mediaView, @Nullable ImageView iconView, @Nullable List<View> list) {
        if (iconView != null) {
            KF.a(B().getAdIcon(), iconView);
        }
        registerViewForInteraction(view, mediaView, (MediaView) null, list);
    }

    @Override // com.facebook.ads.internal.api.NativeAdApi
    public final void registerViewForInteraction(View view, MediaView mediaView, @Nullable MediaView adIconView) {
        registerViewForInteraction(view, mediaView, adIconView, (List<View>) null);
    }

    @Override // com.facebook.ads.internal.api.NativeAdApi
    public final void registerViewForInteraction(View view, MediaView mediaView, @Nullable MediaView adIconView, @Nullable List<View> list) {
        if (mediaView != null) {
            ((GB) mediaView.getMediaViewApi()).A(this.B);
        }
        if (adIconView != null) {
            ((GB) adIconView.getMediaViewApi()).B(this.C, false);
        }
        if (list != null) {
            B().V(view, mediaView, list);
        } else {
            B().U(view, mediaView);
        }
    }

    @Override // com.facebook.ads.internal.api.NativeAdApi
    public final void registerViewForInteraction(View view, MediaView mediaView, @Nullable List<View> list) {
        registerViewForInteraction(view, mediaView, (MediaView) null, list);
    }
}
