package com.facebook.ads;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.UiThread;
import com.facebook.ads.internal.api.InterstitialAdApi;
import com.facebook.ads.internal.dynamicloading.DynamicLoaderFactory;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes.dex */
@Keep
@UiThread
public class InterstitialAd implements Ad {
    private final InterstitialAdApi mInterstitialAdApi;

    public InterstitialAd(Context context, String str) {
        this.mInterstitialAdApi = DynamicLoaderFactory.makeLoader(context).createInterstitialAd(context, str, this);
    }

    public void setAdListener(InterstitialAdListener interstitialAdListener) {
        this.mInterstitialAdApi.setAdListener(interstitialAdListener);
    }

    public void setRewardedAdListener(RewardedAdListener rewardedAdListener) {
        this.mInterstitialAdApi.setRewardedAdListener(rewardedAdListener);
    }

    @Override // com.facebook.ads.Ad
    public void setExtraHints(ExtraHints extraHints) {
        this.mInterstitialAdApi.setExtraHints(extraHints);
    }

    @Override // com.facebook.ads.Ad
    public void loadAd() {
        this.mInterstitialAdApi.loadAd();
    }

    public void loadAd(EnumSet<CacheFlag> enumSet) {
        this.mInterstitialAdApi.loadAd(enumSet);
    }

    @Override // com.facebook.ads.Ad
    public void loadAdFromBid(String str) {
        this.mInterstitialAdApi.loadAdFromBid(str);
    }

    public void loadAdFromBid(EnumSet<CacheFlag> enumSet, String str) {
        this.mInterstitialAdApi.loadAdFromBid(enumSet, str);
    }

    @Override // com.facebook.ads.Ad
    public boolean isAdInvalidated() {
        return this.mInterstitialAdApi.isAdInvalidated();
    }

    @Override // com.facebook.ads.Ad
    public void destroy() {
        this.mInterstitialAdApi.destroy();
    }

    @Override // com.facebook.ads.Ad
    public String getPlacementId() {
        return this.mInterstitialAdApi.getPlacementId();
    }

    public boolean isAdLoaded() {
        return this.mInterstitialAdApi.isAdLoaded();
    }

    public boolean show() {
        return this.mInterstitialAdApi.show();
    }
}
