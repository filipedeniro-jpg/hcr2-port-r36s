package com.ironsource.sdk.listeners;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface OnOfferWallListener {
    void onGetOWCreditsFailed(String str);

    void onOWAdClosed();

    boolean onOWAdCredited(int i, int i2, boolean z);

    void onOWGeneric(String str, String str2);

    void onOWShowFail(String str);

    void onOWShowSuccess(String str);

    void onOfferwallEventNotificationReceived(String str, JSONObject jSONObject);

    void onOfferwallInitFail(String str);

    void onOfferwallInitSuccess();
}
