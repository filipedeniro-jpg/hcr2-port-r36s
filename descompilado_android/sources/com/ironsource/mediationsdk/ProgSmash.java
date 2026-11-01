package com.ironsource.mediationsdk;

import android.app.Activity;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.AdapterConfig;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ProgSmash {
    protected JSONObject mAdUnitSettings;
    protected AbstractAdapter mAdapter;
    protected AdapterConfig mAdapterConfig;
    private boolean mIsLoadCandidate;

    ProgSmash(AdapterConfig adapterConfig, AbstractAdapter abstractAdapter) {
        this.mAdapterConfig = adapterConfig;
        this.mAdapter = abstractAdapter;
        this.mAdUnitSettings = adapterConfig.getAdUnitSetings();
    }

    public boolean isBidder() {
        return this.mAdapterConfig.isBidder();
    }

    public int getMaxAdsPerSession() {
        return this.mAdapterConfig.getMaxAdsPerSession();
    }

    public String getInstanceName() {
        return this.mAdapterConfig.getProviderName();
    }

    public String getNameForReflection() {
        return this.mAdapterConfig.getProviderNameForReflection();
    }

    public void setIsLoadCandidate(boolean z) {
        this.mIsLoadCandidate = z;
    }

    public boolean getIsLoadCandidate() {
        return this.mIsLoadCandidate;
    }

    public void onResume(Activity activity) {
        this.mAdapter.onResume(activity);
    }

    public void onPause(Activity activity) {
        this.mAdapter.onPause(activity);
    }

    public void setConsent(boolean z) {
        this.mAdapter.setConsent(z);
    }

    public Map<String, Object> getProviderEventData() {
        HashMap map = new HashMap();
        try {
            map.put("providerAdapterVersion", this.mAdapter != null ? this.mAdapter.getVersion() : "");
            map.put("providerSDKVersion", this.mAdapter != null ? this.mAdapter.getCoreSDKVersion() : "");
            map.put("spId", this.mAdapterConfig.getSubProviderId());
            map.put("provider", this.mAdapterConfig.getAdSourceNameForEvents());
            map.put(IronSourceConstants.EVENTS_INSTANCE_TYPE, Integer.valueOf(isBidder() ? 2 : 1));
            map.put(IronSourceConstants.EVENTS_PROGRAMMATIC, 1);
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getProviderEventData " + getInstanceName() + ")", e);
        }
        return map;
    }
}
