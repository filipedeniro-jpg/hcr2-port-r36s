package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;

/* JADX INFO: loaded from: classes2.dex */
public interface InterstitialSmashListener {
    void onInterstitialAdClicked();

    void onInterstitialAdClosed();

    void onInterstitialAdLoadFailed(IronSourceError ironSourceError);

    void onInterstitialAdOpened();

    void onInterstitialAdReady();

    void onInterstitialAdShowFailed(IronSourceError ironSourceError);

    void onInterstitialAdShowSucceeded();

    void onInterstitialAdVisible();

    void onInterstitialInitFailed(IronSourceError ironSourceError);

    void onInterstitialInitSuccess();
}
