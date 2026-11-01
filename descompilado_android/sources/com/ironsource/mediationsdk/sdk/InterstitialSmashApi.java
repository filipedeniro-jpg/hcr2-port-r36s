package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

/* JADX INFO: loaded from: classes2.dex */
public interface InterstitialSmashApi {
    void initInterstitial(Activity activity, String str, String str2);

    boolean isInterstitialReady();

    void loadInterstitial();

    void setInterstitialManagerListener(InterstitialManagerListener interstitialManagerListener);

    void showInterstitial();
}
