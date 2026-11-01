package com.ironsource.sdk.listeners.internals;

import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.data.SSAEnums;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface DSAdProductListener {
    void onAdProductClick(SSAEnums.ProductType productType, String str);

    void onAdProductClose(SSAEnums.ProductType productType, String str);

    void onAdProductEventNotificationReceived(SSAEnums.ProductType productType, String str, String str2, JSONObject jSONObject);

    void onAdProductInitFailed(SSAEnums.ProductType productType, String str, String str2);

    void onAdProductInitSuccess(SSAEnums.ProductType productType, String str, AdUnitsReady adUnitsReady);

    void onAdProductOpen(SSAEnums.ProductType productType, String str);
}
