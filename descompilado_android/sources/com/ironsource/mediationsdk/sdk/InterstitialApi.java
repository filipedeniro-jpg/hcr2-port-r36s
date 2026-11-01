package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

/* JADX INFO: loaded from: classes2.dex */
public interface InterstitialApi {
    void initInterstitial(Activity activity, String str, String str2);

    boolean isInterstitialReady();

    void loadInterstitial();

    void setInterstitialListener(InterstitialListener interstitialListener);

    void showInterstitial(String str);
}
