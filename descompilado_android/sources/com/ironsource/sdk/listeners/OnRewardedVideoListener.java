package com.ironsource.sdk.listeners;

import com.ironsource.sdk.data.AdUnitsReady;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface OnRewardedVideoListener extends OnAdProductListener {
    void onRVAdClicked();

    void onRVAdClosed();

    void onRVAdCredited(int i);

    void onRVAdOpened();

    void onRVEventNotificationReceived(String str, JSONObject jSONObject);

    void onRVInitFail(String str);

    void onRVInitSuccess(AdUnitsReady adUnitsReady);

    void onRVNoMoreOffers();

    void onRVShowFail(String str);
}
