package com.ironsource.mediationsdk;

import android.app.Activity;
import android.util.Log;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.InterstitialConfigurations;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.DemandOnlyIsManagerListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.sdk.constants.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class DemandOnlyIsManager implements DemandOnlyIsManagerListener {
    private ConcurrentHashMap<String, DemandOnlyIsSmash> mSmashes = new ConcurrentHashMap<>();

    DemandOnlyIsManager(Activity activity, List<ProviderSettings> list, InterstitialConfigurations interstitialConfigurations, String str, String str2) {
        for (ProviderSettings providerSettings : list) {
            if (providerSettings.getProviderTypeForReflection().equalsIgnoreCase(IronSourceConstants.SUPERSONIC_CONFIG_NAME) || providerSettings.getProviderTypeForReflection().equalsIgnoreCase(IronSourceConstants.IRONSOURCE_CONFIG_NAME)) {
                AbstractAdapter abstractAdapterLoadAdapter = loadAdapter(providerSettings.getProviderInstanceName());
                if (abstractAdapterLoadAdapter != null) {
                    this.mSmashes.put(providerSettings.getSubProviderId(), new DemandOnlyIsSmash(activity, str, str2, providerSettings, this, interstitialConfigurations.getInterstitialAdaptersSmartLoadTimeout(), abstractAdapterLoadAdapter));
                }
            } else {
                logInternal("cannot load " + providerSettings.getProviderTypeForReflection());
            }
        }
    }

    public synchronized void onResume(Activity activity) {
        if (activity != null) {
            Iterator<DemandOnlyIsSmash> it = this.mSmashes.values().iterator();
            while (it.hasNext()) {
                it.next().onResume(activity);
            }
        }
    }

    public synchronized void onPause(Activity activity) {
        if (activity != null) {
            Iterator<DemandOnlyIsSmash> it = this.mSmashes.values().iterator();
            while (it.hasNext()) {
                it.next().onPause(activity);
            }
        }
    }

    public synchronized void setConsent(boolean z) {
        Iterator<DemandOnlyIsSmash> it = this.mSmashes.values().iterator();
        while (it.hasNext()) {
            it.next().setConsent(z);
        }
    }

    public synchronized void loadInterstitial(String str) {
        try {
            if (!this.mSmashes.containsKey(str)) {
                sendMediationEvent(2500, str);
                ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(str, ErrorBuilder.buildNonExistentInstanceError("Interstitial"));
            } else {
                DemandOnlyIsSmash demandOnlyIsSmash = this.mSmashes.get(str);
                sendProviderEvent(2002, demandOnlyIsSmash);
                demandOnlyIsSmash.loadInterstitial();
            }
        } catch (Exception e) {
            logInternal("loadInterstitial exception " + e.getMessage());
            ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(str, ErrorBuilder.buildLoadFailedError("loadInterstitial exception"));
        }
    }

    public synchronized void showInterstitial(String str) {
        if (!this.mSmashes.containsKey(str)) {
            sendMediationEvent(2500, str);
            ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdShowFailed(str, ErrorBuilder.buildNonExistentInstanceError("Interstitial"));
        } else {
            DemandOnlyIsSmash demandOnlyIsSmash = this.mSmashes.get(str);
            sendProviderEvent(IronSourceConstants.IS_INSTANCE_SHOW, demandOnlyIsSmash);
            demandOnlyIsSmash.showInterstitial();
        }
    }

    public synchronized boolean isInterstitialReady(String str) {
        if (!this.mSmashes.containsKey(str)) {
            sendMediationEvent(2500, str);
            return false;
        }
        DemandOnlyIsSmash demandOnlyIsSmash = this.mSmashes.get(str);
        if (demandOnlyIsSmash.isInterstitialReady()) {
            sendProviderEvent(IronSourceConstants.IS_INSTANCE_READY_TRUE, demandOnlyIsSmash);
            return true;
        }
        sendProviderEvent(IronSourceConstants.IS_INSTANCE_READY_FALSE, demandOnlyIsSmash);
        return false;
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyIsManagerListener
    public synchronized void onInterstitialAdReady(DemandOnlyIsSmash demandOnlyIsSmash, long j) {
        logSmashCallback(demandOnlyIsSmash, "onInterstitialAdReady");
        sendProviderEvent(2003, demandOnlyIsSmash, new Object[][]{new Object[]{"duration", Long.valueOf(j)}});
        ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdReady(demandOnlyIsSmash.getSubProviderId());
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyIsManagerListener
    public synchronized void onInterstitialAdLoadFailed(IronSourceError ironSourceError, DemandOnlyIsSmash demandOnlyIsSmash, long j) {
        logSmashCallback(demandOnlyIsSmash, "onInterstitialAdLoadFailed error=" + ironSourceError.toString());
        sendProviderEvent(IronSourceConstants.IS_INSTANCE_LOAD_FAILED, demandOnlyIsSmash, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}, new Object[]{"duration", Long.valueOf(j)}});
        ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(demandOnlyIsSmash.getSubProviderId(), ironSourceError);
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyIsManagerListener
    public synchronized void onInterstitialAdOpened(DemandOnlyIsSmash demandOnlyIsSmash) {
        logSmashCallback(demandOnlyIsSmash, "onInterstitialAdOpened");
        sendProviderEvent(IronSourceConstants.IS_INSTANCE_OPENED, demandOnlyIsSmash);
        ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdOpened(demandOnlyIsSmash.getSubProviderId());
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyIsManagerListener
    public synchronized void onInterstitialAdClosed(DemandOnlyIsSmash demandOnlyIsSmash) {
        logSmashCallback(demandOnlyIsSmash, "onInterstitialAdClosed");
        sendProviderEvent(IronSourceConstants.IS_INSTANCE_CLOSED, demandOnlyIsSmash);
        ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdClosed(demandOnlyIsSmash.getSubProviderId());
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyIsManagerListener
    public synchronized void onInterstitialAdShowFailed(IronSourceError ironSourceError, DemandOnlyIsSmash demandOnlyIsSmash) {
        logSmashCallback(demandOnlyIsSmash, "onInterstitialAdShowFailed error=" + ironSourceError.toString());
        sendProviderEvent(IronSourceConstants.IS_INSTANCE_SHOW_FAILED, demandOnlyIsSmash, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}});
        ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdShowFailed(demandOnlyIsSmash.getSubProviderId(), ironSourceError);
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyIsManagerListener
    public synchronized void onInterstitialAdClicked(DemandOnlyIsSmash demandOnlyIsSmash) {
        logSmashCallback(demandOnlyIsSmash, Constants.JSMethods.ON_INTERSTITIAL_AD_CLICKED);
        sendProviderEvent(2006, demandOnlyIsSmash);
        ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdClicked(demandOnlyIsSmash.getSubProviderId());
    }

    @Override // com.ironsource.mediationsdk.sdk.DemandOnlyIsManagerListener
    public synchronized void onInterstitialAdVisible(DemandOnlyIsSmash demandOnlyIsSmash) {
        sendProviderEvent(IronSourceConstants.IS_INSTANCE_VISIBLE, demandOnlyIsSmash);
        logSmashCallback(demandOnlyIsSmash, "onInterstitialAdVisible");
    }

    private void sendProviderEvent(int i, DemandOnlyIsSmash demandOnlyIsSmash) {
        sendProviderEvent(i, demandOnlyIsSmash, (Object[][]) null);
    }

    private void sendProviderEvent(int i, DemandOnlyIsSmash demandOnlyIsSmash, Object[][] objArr) {
        Map<String, Object> providerEventData = demandOnlyIsSmash.getProviderEventData();
        if (objArr != null) {
            try {
                for (Object[] objArr2 : objArr) {
                    providerEventData.put(objArr2[0].toString(), objArr2[1]);
                }
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "IS sendProviderEvent " + Log.getStackTraceString(e), 3);
            }
        }
        InterstitialEventsManager.getInstance().log(new EventData(i, new JSONObject(providerEventData)));
    }

    private void sendMediationEvent(int i, String str) {
        HashMap map = new HashMap();
        map.put("provider", "Mediation");
        map.put(IronSourceConstants.EVENTS_DEMAND_ONLY, 1);
        if (str == null) {
            str = "";
        }
        map.put("spId", str);
        InterstitialEventsManager.getInstance().log(new EventData(i, new JSONObject(map)));
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
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "DemandOnlyIsManager " + str, 0);
    }

    private void logSmashCallback(DemandOnlyIsSmash demandOnlyIsSmash, String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "DemandOnlyIsManager " + demandOnlyIsSmash.getInstanceName() + " : " + str, 0);
    }
}
