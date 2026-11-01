package com.ironsource.sdk.ISNAdView;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface ISNAdViewDelegate {
    void sendErrorMessageToController(String str, String str2);

    void sendMessageToController(String str, JSONObject jSONObject);
}
