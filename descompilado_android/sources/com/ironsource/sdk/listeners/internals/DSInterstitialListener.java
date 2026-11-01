package com.ironsource.sdk.listeners.internals;

/* JADX INFO: loaded from: classes2.dex */
public interface DSInterstitialListener extends DSAdProductListener {
    void onInterstitialAdRewarded(String str, int i);

    void onInterstitialLoadFailed(String str, String str2);

    void onInterstitialLoadSuccess(String str);

    void onInterstitialShowFailed(String str, String str2);

    void onInterstitialShowSuccess(String str);
}
