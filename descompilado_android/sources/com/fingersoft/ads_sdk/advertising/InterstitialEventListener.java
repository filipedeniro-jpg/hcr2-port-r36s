package com.fingersoft.ads_sdk.advertising;

/* JADX INFO: loaded from: classes.dex */
public interface InterstitialEventListener {
    void allProvidersFailed();

    void failedToReceiveAd();

    void interactionWithAd();

    void onDismiss();

    void onLoad();

    void onShow();

    void receivedAd();
}
