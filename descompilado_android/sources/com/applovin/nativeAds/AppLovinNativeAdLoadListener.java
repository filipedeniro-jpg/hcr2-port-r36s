package com.applovin.nativeAds;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface AppLovinNativeAdLoadListener {
    void onNativeAdsFailedToLoad(int i);

    void onNativeAdsLoaded(List<AppLovinNativeAd> list);
}
