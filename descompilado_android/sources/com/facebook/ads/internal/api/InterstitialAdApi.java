package com.facebook.ads.internal.api;

import androidx.annotation.Keep;
import androidx.annotation.UiThread;
import com.facebook.ads.Ad;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.ExtraHints;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.RewardedAdListener;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes.dex */
@Keep
@UiThread
public interface InterstitialAdApi extends Ad {
    boolean isAdLoaded();

    void loadAd(EnumSet<CacheFlag> enumSet);

    void loadAdFromBid(EnumSet<CacheFlag> enumSet, String str);

    void setAdListener(InterstitialAdListener interstitialAdListener);

    @Override // com.facebook.ads.Ad
    void setExtraHints(ExtraHints extraHints);

    void setRewardedAdListener(RewardedAdListener rewardedAdListener);

    boolean show();
}
