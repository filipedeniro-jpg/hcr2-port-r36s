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
public class DemandOnlySmash {
    protected JSONObject mAdUnitSettings;
    protected AbstractAdapter mAdapter;
    protected AdapterConfig mAdapterConfig;

    protected enum SMASH_STATE {
        NOT_LOADED,
        LOAD_IN_PROGRESS,
        LOADED,
        SHOW_IN_PROGRESS
    }

    public DemandOnlySmash(AdapterConfig adapterConfig, AbstractAdapter abstractAdapter) {
        this.mAdapterConfig = adapterConfig;
        this.mAdapter = abstractAdapter;
        this.mAdUnitSettings = adapterConfig.getAdUnitSetings();
    }

    public synchronized void onResume(Activity activity) {
        this.mAdapter.onResume(activity);
    }

    public synchronized void onPause(Activity activity) {
        this.mAdapter.onPause(activity);
    }

    public synchronized void setConsent(boolean z) {
        this.mAdapter.setConsent(z);
    }

    public synchronized String getInstanceName() {
        return this.mAdapterConfig.getProviderName();
    }

    public synchronized String getSubProviderId() {
        return this.mAdapterConfig.getSubProviderId();
    }

    public Map<String, Object> getProviderEventData() {
        HashMap map = new HashMap();
        try {
            map.put("providerAdapterVersion", this.mAdapter != null ? this.mAdapter.getVersion() : "");
            map.put("providerSDKVersion", this.mAdapter != null ? this.mAdapter.getCoreSDKVersion() : "");
            map.put("spId", this.mAdapterConfig.getSubProviderId());
            map.put("provider", this.mAdapterConfig.getAdSourceNameForEvents());
            map.put(IronSourceConstants.EVENTS_DEMAND_ONLY, 1);
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getProviderEventData " + getInstanceName() + ")", e);
        }
        return map;
    }
}
