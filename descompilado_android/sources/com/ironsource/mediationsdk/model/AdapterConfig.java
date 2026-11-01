package com.ironsource.mediationsdk.model;

import com.ironsource.mediationsdk.utils.IronSourceConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AdapterConfig {
    private JSONObject mAdUnitSettings;
    private boolean mIsBidder;
    private int mMaxAdsPerSession;
    private ProviderSettings mProviderSettings;

    public AdapterConfig(ProviderSettings providerSettings, JSONObject jSONObject) {
        this.mProviderSettings = providerSettings;
        this.mAdUnitSettings = jSONObject;
        this.mIsBidder = jSONObject.optInt(IronSourceConstants.EVENTS_INSTANCE_TYPE) == 2;
        this.mMaxAdsPerSession = jSONObject.optInt("maxAdsPerSession", 99);
    }

    public JSONObject getAdUnitSetings() {
        return this.mAdUnitSettings;
    }

    public boolean isBidder() {
        return this.mIsBidder;
    }

    public int getMaxAdsPerSession() {
        return this.mMaxAdsPerSession;
    }

    public String getProviderName() {
        return this.mProviderSettings.getProviderName();
    }

    public String getSubProviderId() {
        return this.mProviderSettings.getSubProviderId();
    }

    public String getAdSourceNameForEvents() {
        return this.mProviderSettings.getAdSourceNameForEvents();
    }

    public String getProviderNameForReflection() {
        return this.mProviderSettings.getProviderTypeForReflection();
    }
}
