package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import com.facebook.ads.AdError;
import com.facebook.ads.S2SRewardedVideoAdExtendedListener;
import com.ironsource.mediationsdk.utils.IronSourceConstants;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2n, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01862n extends C01832k implements S2SRewardedVideoAdExtendedListener {
    private final C01762d B;
    private final C01822j C;

    public C01862n(String adId, C2T messageListener, C01762d internalRewardedVideoAd, C01822j rewardedVideoAdModel) {
        super(adId, messageListener);
        this.B = internalRewardedVideoAd;
        this.C = rewardedVideoAdModel;
    }

    @Override // com.facebook.ads.AdListener
    public final void onAdClicked(com.facebook.ads.Ad ad) {
        super.C.cE(2104, super.B, null);
    }

    @Override // com.facebook.ads.AdListener
    public final void onAdLoaded(com.facebook.ads.Ad ad) {
        Bundle bundle = new Bundle();
        bundle.putLong("LONG_INVALIDATION_TIME_KEY", this.B.B());
        bundle.putInt("INT_RV_VIDEO_DURATION_KEY", this.C.K);
        super.C.cE(2100, super.B, bundle);
    }

    @Override // com.facebook.ads.AdListener
    public final void onError(com.facebook.ads.Ad ad, AdError error) {
        Bundle bundle = new Bundle();
        bundle.putString("STR_ERROR_MESSAGE_KEY", error.getErrorMessage());
        bundle.putInt("INT_ERROR_CODE_KEY", error.getErrorCode());
        super.C.cE(IronSourceConstants.IS_CHECK_CAPPED_TRUE, super.B, bundle);
    }

    @Override // com.facebook.ads.RewardedVideoAdListener, com.facebook.ads.AdListener
    public final void onLoggingImpression(com.facebook.ads.Ad ad) {
        super.C.cE(2105, super.B, null);
    }

    @Override // com.facebook.ads.S2SRewardedVideoAdListener
    public final void onRewardServerFailed() {
        super.C.cE(3002, super.B, null);
    }

    @Override // com.facebook.ads.S2SRewardedVideoAdListener
    public final void onRewardServerSuccess() {
        super.C.cE(3001, super.B, null);
    }

    @Override // com.facebook.ads.RewardedVideoAdExtendedListener
    public final void onRewardedVideoActivityDestroyed() {
        super.C.cE(2106, super.B, null);
    }

    @Override // com.facebook.ads.RewardedVideoAdListener
    public final void onRewardedVideoClosed() {
        super.C.cE(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, super.B, null);
    }

    @Override // com.facebook.ads.RewardedVideoAdListener
    public final void onRewardedVideoCompleted() {
        super.C.cE(3000, super.B, null);
    }
}
