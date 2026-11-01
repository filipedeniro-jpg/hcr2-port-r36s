package com.chartboost.sdk;

import com.chartboost.sdk.Model.CBError;

/* JADX INFO: loaded from: classes.dex */
public abstract class ChartboostDelegate implements a {
    @Override // com.chartboost.sdk.a
    public void didCacheInPlay(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didCacheInterstitial(String str) {
    }

    public void didCacheMoreApps(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didCacheRewardedVideo(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didClickInterstitial(String str) {
    }

    public void didClickMoreApps(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didClickRewardedVideo(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didCloseInterstitial(String str) {
    }

    public void didCloseMoreApps(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didCloseRewardedVideo(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didCompleteRewardedVideo(String str, int i) {
    }

    @Override // com.chartboost.sdk.a
    public void didDismissInterstitial(String str) {
    }

    public void didDismissMoreApps(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didDismissRewardedVideo(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didDisplayInterstitial(String str) {
    }

    public void didDisplayMoreApps(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didDisplayRewardedVideo(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void didFailToLoadInPlay(String str, CBError.CBImpressionError cBImpressionError) {
    }

    @Override // com.chartboost.sdk.a
    public void didFailToLoadInterstitial(String str, CBError.CBImpressionError cBImpressionError) {
    }

    @Override // com.chartboost.sdk.a
    public void didFailToLoadMoreApps(String str, CBError.CBImpressionError cBImpressionError) {
    }

    @Override // com.chartboost.sdk.a
    public void didFailToLoadRewardedVideo(String str, CBError.CBImpressionError cBImpressionError) {
    }

    @Override // com.chartboost.sdk.a
    public void didFailToRecordClick(String str, CBError.CBClickError cBClickError) {
    }

    @Override // com.chartboost.sdk.a
    public void didInitialize() {
    }

    @Override // com.chartboost.sdk.a
    public boolean shouldDisplayInterstitial(String str) {
        return true;
    }

    public boolean shouldDisplayMoreApps(String str) {
        return true;
    }

    @Override // com.chartboost.sdk.a
    public boolean shouldDisplayRewardedVideo(String str) {
        return true;
    }

    @Override // com.chartboost.sdk.a
    public boolean shouldRequestInterstitial(String str) {
        return true;
    }

    public boolean shouldRequestMoreApps(String str) {
        return true;
    }

    @Override // com.chartboost.sdk.a
    public void willDisplayInterstitial(String str) {
    }

    @Override // com.chartboost.sdk.a
    public void willDisplayVideo(String str) {
    }
}
