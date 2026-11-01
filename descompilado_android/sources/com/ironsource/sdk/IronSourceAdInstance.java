package com.ironsource.sdk;

import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.listeners.OnInterstitialListener;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceAdInstance {
    private OnInterstitialListener adListener;
    private Map<String, String> extraParams;
    private String id;
    private boolean inAppBidding;
    private boolean isInitialized = false;
    private String name;
    private boolean rewarded;

    IronSourceAdInstance(String str, String str2, boolean z, boolean z2, Map<String, String> map, OnInterstitialListener onInterstitialListener) {
        this.id = str;
        this.name = str2;
        this.rewarded = z;
        this.inAppBidding = z2;
        this.extraParams = map;
        this.adListener = onInterstitialListener;
    }

    public boolean isInitialized() {
        return this.isInitialized;
    }

    public void setInitialized(boolean z) {
        this.isInitialized = z;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isRewarded() {
        return this.rewarded;
    }

    public boolean isInAppBidding() {
        return this.inAppBidding;
    }

    public Map<String, String> getExtraParams() {
        return this.extraParams;
    }

    public final OnInterstitialListener getAdListener() {
        return this.adListener;
    }

    public Map<String, String> convertToMap() {
        HashMap map = new HashMap();
        map.put(Constants.CONVERT_INSTANCE_ID, this.id);
        map.put(Constants.CONVERT_INSTANCE_NAME, this.name);
        map.put(Constants.CONVERT_REWARDED, Boolean.toString(this.rewarded));
        map.put(Constants.CONVERT_IN_APP_BIDDING, Boolean.toString(this.inAppBidding));
        map.put(Constants.CONVERT_API_VERSION, String.valueOf(2));
        Map<String, String> map2 = this.extraParams;
        if (map2 != null) {
            map.putAll(map2);
        }
        return map;
    }
}
