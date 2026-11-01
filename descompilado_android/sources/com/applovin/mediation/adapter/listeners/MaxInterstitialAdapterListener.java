package com.applovin.mediation.adapter.listeners;

import com.applovin.mediation.adapter.MaxAdapterError;

/* JADX INFO: loaded from: classes.dex */
public interface MaxInterstitialAdapterListener extends MaxAdapterListener {
    void onInterstitialAdClicked();

    void onInterstitialAdDisplayFailed(MaxAdapterError maxAdapterError);

    void onInterstitialAdDisplayed();

    void onInterstitialAdHidden();

    void onInterstitialAdLoadFailed(MaxAdapterError maxAdapterError);

    void onInterstitialAdLoaded();
}
