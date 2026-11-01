package com.fingersoft.ads_sdk.advertising.providers.Interstitials;

/* JADX INFO: loaded from: classes.dex */
public interface InterstitialListener {
    void onInterstitialDismiss();

    void onInterstitialFailed();

    void onInterstitialInteract();

    void onInterstitialLoaded();

    void onInterstitialShow();
}
