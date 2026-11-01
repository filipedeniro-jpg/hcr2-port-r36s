package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;

/* JADX INFO: loaded from: classes2.dex */
public interface ISDemandOnlyRewardedVideoListener {
    void onRewardedVideoAdClicked(String str);

    void onRewardedVideoAdClosed(String str);

    void onRewardedVideoAdLoadFailed(String str, IronSourceError ironSourceError);

    void onRewardedVideoAdLoadSuccess(String str);

    void onRewardedVideoAdOpened(String str);

    void onRewardedVideoAdRewarded(String str);

    void onRewardedVideoAdShowFailed(String str, IronSourceError ironSourceError);
}
