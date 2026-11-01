package com.applovin.sdk;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface AppLovinAdRewardListener {
    void userDeclinedToViewAd(AppLovinAd appLovinAd);

    void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map);

    void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map);

    void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map);

    void validationRequestFailed(AppLovinAd appLovinAd, int i);
}
