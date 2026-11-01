package com.fingersoft.hcr2.firebase;

/* JADX INFO: loaded from: classes.dex */
public interface FirebaseAdListener {
    void onAdCancelled();

    void onAdFailed();

    void onAdViewed();

    void onInterstitialAdFailed();

    void onInterstitialAdLoaded();

    void onInterstitialAdViewed(String str);

    void onInterstitialDismiss();

    void onInterstitialFailed();

    void onInterstitialInteract();

    void onInterstitialShow(String str);

    void onVideoAdAvailable();

    void onVideoAdCancelled();

    void onVideoAdFailed();

    void onVideoAdStarted(String str);

    void onVideoAdViewed(String str);
}
