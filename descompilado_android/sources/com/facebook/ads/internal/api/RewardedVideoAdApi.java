package com.facebook.ads.internal.api;

import androidx.annotation.Keep;
import androidx.annotation.UiThread;
import com.facebook.ads.Ad;
import com.facebook.ads.ExtraHints;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAdListener;

/* JADX INFO: loaded from: classes.dex */
@Keep
@UiThread
public interface RewardedVideoAdApi extends Ad {
    @Override // com.facebook.ads.Ad
    void destroy();

    @Override // com.facebook.ads.Ad
    String getPlacementId();

    int getVideoDuration();

    @Override // com.facebook.ads.Ad
    boolean isAdInvalidated();

    boolean isAdLoaded();

    @Override // com.facebook.ads.Ad
    void loadAd();

    void loadAd(boolean z);

    @Override // com.facebook.ads.Ad
    void loadAdFromBid(String str);

    void loadAdFromBid(String str, boolean z);

    void setAdListener(RewardedVideoAdListener rewardedVideoAdListener);

    @Override // com.facebook.ads.Ad
    void setExtraHints(ExtraHints extraHints);

    void setRewardData(RewardData rewardData);

    boolean show();

    boolean show(int i);
}
