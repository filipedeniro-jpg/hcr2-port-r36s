package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;

/* JADX INFO: loaded from: classes2.dex */
public interface BannerListener {
    void onBannerAdClicked();

    void onBannerAdLeftApplication();

    void onBannerAdLoadFailed(IronSourceError ironSourceError);

    void onBannerAdLoaded();

    void onBannerAdScreenDismissed();

    void onBannerAdScreenPresented();
}
