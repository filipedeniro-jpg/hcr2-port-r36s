package com.facebook.ads;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.UiThread;
import com.facebook.ads.internal.api.RewardedVideoAdApi;
import com.facebook.ads.internal.dynamicloading.DynamicLoaderFactory;

/* JADX INFO: loaded from: classes.dex */
@Keep
@UiThread
public class RewardedVideoAd implements Ad {
    public static final int UNSET_VIDEO_DURATION = -1;
    private final RewardedVideoAdApi mRewardedVideoAdApi;

    public RewardedVideoAd(Context context, String str) {
        this.mRewardedVideoAdApi = DynamicLoaderFactory.makeLoader(context).createRewardedVideoAd(context, str, this);
    }

    @Override // com.facebook.ads.Ad
    public void setExtraHints(ExtraHints extraHints) {
        this.mRewardedVideoAdApi.setExtraHints(extraHints);
    }

    @Override // com.facebook.ads.Ad
    public void loadAd() {
        this.mRewardedVideoAdApi.loadAd();
    }

    public void loadAd(boolean z) {
        this.mRewardedVideoAdApi.loadAd(z);
    }

    @Override // com.facebook.ads.Ad
    public void loadAdFromBid(String str) {
        this.mRewardedVideoAdApi.loadAdFromBid(str);
    }

    public void loadAdFromBid(String str, boolean z) {
        this.mRewardedVideoAdApi.loadAdFromBid(str, z);
    }

    @Override // com.facebook.ads.Ad
    public boolean isAdInvalidated() {
        return this.mRewardedVideoAdApi.isAdInvalidated();
    }

    public boolean show() {
        return this.mRewardedVideoAdApi.show();
    }

    public boolean show(int i) {
        return this.mRewardedVideoAdApi.show(i);
    }

    public void setAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.mRewardedVideoAdApi.setAdListener(rewardedVideoAdListener);
    }

    @Override // com.facebook.ads.Ad
    public void destroy() {
        this.mRewardedVideoAdApi.destroy();
    }

    public boolean isAdLoaded() {
        return this.mRewardedVideoAdApi.isAdLoaded();
    }

    @Override // com.facebook.ads.Ad
    public String getPlacementId() {
        return this.mRewardedVideoAdApi.getPlacementId();
    }

    public void setRewardData(RewardData rewardData) {
        this.mRewardedVideoAdApi.setRewardData(rewardData);
    }

    public int getVideoDuration() {
        return this.mRewardedVideoAdApi.getVideoDuration();
    }
}
