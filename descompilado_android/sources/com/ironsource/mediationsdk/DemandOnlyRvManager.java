package com.ironsource.mediationsdk;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.model.RewardedVideoConfigurations;
import com.ironsource.mediationsdk.sdk.DemandOnlyRvManagerListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class DemandOnlyRvManager implements DemandOnlyRvManagerListener {
    private String mAppKey;
    private ConcurrentHashMap<String, DemandOnlyRvSmash> mSmashes = new ConcurrentHashMap<>();

    DemandOnlyRvManager(Activity activity, List<ProviderSettings> list, RewardedVideoConfigurations rewardedVideoConfigurations, String str, String str2) {
        this.mAppKey = str;
        for (ProviderSettings providerSettings : list) {
            if (providerSettings.getProviderTypeForReflection().equalsIgnoreCase(IronSourceConstants.SUPERSONIC_CONFIG_NAME) || providerSettings.getProviderTypeForReflection().equalsIgnoreCase(IronSourceConstants.IRONSOURCE_CONFIG_NAME)) {
                AbstractAdapter abstractAdapterLoadAdapter = loadAdapter(providerSettings.getProviderInstanceName());
                if (abstractAdapterLoadAdapter != null) {
                    this.mSmashes.put(providerSettings.getSubProviderId(), new DemandOnlyRvSmash(activity, str, str2, providerSettings, this, rewardedVideoConfigurations.getRewardedVideoAdaptersSmartLoadTimeout(), abstractAdapterLoadAdapter));
                }
            } else {
                logInternal("cannot load " + providerSettings.getProviderTypeForReflection());
            }
        }
    }

    public synchronized void onResume(Activity activity) {
        if (activity != null) {
            Iterator<DemandOnlyRvSmash> it = this.mSmashes.values().iterator();
            while (it.hasNext()) {
                it.next().onResume(activity);
            }
        }
    }

    public synchronized void onPause(Activity activity) {
        if (activity != null) {
            Iterator<DemandOnlyRvSmash> it = this.mSmashes.values().iterator();
            while (it.hasNext()) {
                it.next().onPause(activity);
            }
        }
    }

    public synchronized void setConsent(boolean z) {
        Iterator<DemandOnlyRvSmash> it = this.mSmashes.values().iterator();
        while (it.hasNext()) {
            it.next().setConsent(z);
        }
    }

    public synchronized void loadRewardedVideo(String str) {
        try {
            if (!this.mSmashes.containsKey(str)) {
                sendMediationEvent(1500, str);
                RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(str, ErrorBuilder.buildNonExistentInstanceError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            } else {
                DemandOnlyRvSmash demandOnlyRvSmash = this.mSmashes.get(str);
                sendProviderEvent(1001, demandOnlyRvSmash);
                demandOnlyRvSmash.loadRewardedVideo();
            }
        } catch (Exception e) {
            logInternal("loadRewardedVideo exception " + e.getMessage());
            RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(str, ErrorBuilder.buildLoadFailedError("loadRewardedVideo exception"));
        }
    }

    public synchronized void showRewardedVideo(String str) {
        if (!this.mSmashes.containsKey(str)) {
            sendMediationEvent(1500, str);
            RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdShowFailed(str, ErrorBuilder.buildNonExistentInstanceError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        } else {
            DemandOnlyRvSmash demandOnlyRvSmash = this.mSmashes.get(str);
            sendProviderEvent(IronSourceConstants.RV_INSTANCE_SHOW, demandOnlyRvSmash);
            demandOnlyRvSmash.showRewardedVideo();
        }
    }

    public synchronized boolean isRewardedVideoAvailable(String str) {
        if (!this.mSmashes.containsKey(str)) {
            sendMediationEvent(1500, str);
            return false;
        }
        DemandOnlyRvSmash demandOnlyRvSmash = this.mSmashes.get(str);
        if (demandOnlyRvSmash.isRewardedVideoAvailable()) {
            sendProviderEvent(IronSourceConstants.RV_INSTANCE_READY_TRUE, demandOnlyRvSmash);
            return true;
        }
        sendProviderEvent(IronSourceConstants.RV_INSTANCE_READY_FALSE, demandOnlyRvSmash);
        return false;
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyRvManagerListener
    public void onRewardedVideoLoadSuccess(DemandOnlyRvSmash demandOnlyRvSmash, long j) {
        logSmashCallback(demandOnlyRvSmash, "onRewardedVideoLoadSuccess");
        sendProviderEvent(1002, demandOnlyRvSmash, new Object[][]{new Object[]{"duration", Long.valueOf(j)}});
        RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoLoadSuccess(demandOnlyRvSmash.getSubProviderId());
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyRvManagerListener
    public void onRewardedVideoAdLoadFailed(IronSourceError ironSourceError, DemandOnlyRvSmash demandOnlyRvSmash, long j) {
        logSmashCallback(demandOnlyRvSmash, "onRewardedVideoAdLoadFailed error=" + ironSourceError);
        sendProviderEvent(IronSourceConstants.RV_INSTANCE_LOAD_FAILED, demandOnlyRvSmash, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}, new Object[]{"duration", Long.valueOf(j)}});
        RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(demandOnlyRvSmash.getSubProviderId(), ironSourceError);
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyRvManagerListener
    public void onRewardedVideoAdOpened(DemandOnlyRvSmash demandOnlyRvSmash) {
        logSmashCallback(demandOnlyRvSmash, "onRewardedVideoAdOpened");
        sendProviderEvent(1005, demandOnlyRvSmash);
        RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdOpened(demandOnlyRvSmash.getSubProviderId());
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyRvManagerListener
    public void onRewardedVideoAdClosed(DemandOnlyRvSmash demandOnlyRvSmash) {
        logSmashCallback(demandOnlyRvSmash, "onRewardedVideoAdClosed");
        sendProviderEvent(IronSourceConstants.RV_INSTANCE_CLOSED, demandOnlyRvSmash);
        RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdClosed(demandOnlyRvSmash.getSubProviderId());
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyRvManagerListener
    public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError, DemandOnlyRvSmash demandOnlyRvSmash) {
        logSmashCallback(demandOnlyRvSmash, "onRewardedVideoAdShowFailed error=" + ironSourceError);
        sendProviderEvent(IronSourceConstants.RV_INSTANCE_SHOW_FAILED, demandOnlyRvSmash, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}});
        RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdShowFailed(demandOnlyRvSmash.getSubProviderId(), ironSourceError);
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyRvManagerListener
    public void onRewardedVideoAdClicked(DemandOnlyRvSmash demandOnlyRvSmash) {
        logSmashCallback(demandOnlyRvSmash, "onRewardedVideoAdClicked");
        sendProviderEvent(1006, demandOnlyRvSmash);
        RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdClicked(demandOnlyRvSmash.getSubProviderId());
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyRvManagerListener
    public void onRewardedVideoAdVisible(DemandOnlyRvSmash demandOnlyRvSmash) {
        logSmashCallback(demandOnlyRvSmash, "onRewardedVideoAdVisible");
        sendProviderEvent(IronSourceConstants.RV_INSTANCE_VISIBLE, demandOnlyRvSmash);
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyRvManagerListener
    public void onRewardedVideoAdRewarded(DemandOnlyRvSmash demandOnlyRvSmash) {
        logSmashCallback(demandOnlyRvSmash, "onRewardedVideoAdRewarded");
        Map<String, Object> providerEventData = demandOnlyRvSmash.getProviderEventData();
        providerEventData.put(IronSourceConstants.EVENTS_TRANS_ID, IronSourceUtils.getTransId(Long.toString(new Date().getTime()) + this.mAppKey + demandOnlyRvSmash.getInstanceName()));
        if (!TextUtils.isEmpty(IronSourceObject.getInstance().getDynamicUserId())) {
            providerEventData.put(IronSourceConstants.EVENTS_DYNAMIC_USER_ID, IronSourceObject.getInstance().getDynamicUserId());
        }
        if (IronSourceObject.getInstance().getRvServerParams() != null) {
            for (String str : IronSourceObject.getInstance().getRvServerParams().keySet()) {
                providerEventData.put("custom_" + str, IronSourceObject.getInstance().getRvServerParams().get(str));
            }
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(1010, new JSONObject(providerEventData)));
        RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdRewarded(demandOnlyRvSmash.getSubProviderId());
    }

    private void sendProviderEvent(int i, DemandOnlyRvSmash demandOnlyRvSmash) {
        sendProviderEvent(i, demandOnlyRvSmash, (Object[][]) null);
    }

    private void sendProviderEvent(int i, DemandOnlyRvSmash demandOnlyRvSmash, Object[][] objArr) {
        Map<String, Object> providerEventData = demandOnlyRvSmash.getProviderEventData();
        if (objArr != null) {
            try {
                for (Object[] objArr2 : objArr) {
                    providerEventData.put(objArr2[0].toString(), objArr2[1]);
                }
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "RV sendProviderEvent " + Log.getStackTraceString(e), 3);
            }
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(i, new JSONObject(providerEventData)));
    }

    private void sendMediationEvent(int i, String str) {
        HashMap map = new HashMap();
        map.put("provider", "Mediation");
        map.put(IronSourceConstants.EVENTS_DEMAND_ONLY, 1);
        if (str == null) {
            str = "";
        }
        map.put("spId", str);
        RewardedVideoEventsManager.getInstance().log(new EventData(i, new JSONObject(map)));
    }

    private AbstractAdapter loadAdapter(String str) {
        try {
            Class<?> cls = Class.forName("com.ironsource.adapters.ironsource.IronSourceAdapter");
            return (AbstractAdapter) cls.getMethod(IronSourceConstants.START_ADAPTER, String.class).invoke(cls, str);
        } catch (Exception unused) {
            return null;
        }
    }

    private void logInternal(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "DemandOnlyRvManager " + str, 0);
    }

    private void logSmashCallback(DemandOnlyRvSmash demandOnlyRvSmash, String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "DemandOnlyRvManager " + demandOnlyRvSmash.getInstanceName() + " : " + str, 0);
    }
}
