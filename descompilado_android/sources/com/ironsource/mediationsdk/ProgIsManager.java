package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.InterstitialConfigurations;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.utils.AuctionSettings;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.SessionCappingManager;
import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class ProgIsManager implements ProgIsManagerListener {
    private Context mAppContext;
    private AuctionHandler mAuctionHandler;
    private String mCurrentAuctionId;
    private String mCurrentPlacement;
    private long mInitMangerTime;
    private long mLoadStartTime;
    private int mMaxSmashesToLoad;
    private SessionCappingManager mSessionCappingManager;
    private final ConcurrentHashMap<String, ProgIsSmash> mSmashes;
    private MEDIATION_STATE mState;
    private long mTimeToWaitBeforeFirstAction;
    private CopyOnWriteArrayList<ProgIsSmash> mWaterfall;
    private ConcurrentHashMap<String, AuctionResponseItem> mWaterfallServerData;

    enum MEDIATION_STATE {
        STATE_NOT_INITIALIZED,
        STATE_READY_TO_LOAD,
        STATE_AUCTION,
        STATE_LOADING_SMASHES,
        STATE_READY_TO_SHOW,
        STATE_SHOWING
    }

    public ProgIsManager(Activity activity, List<ProviderSettings> list, InterstitialConfigurations interstitialConfigurations, String str, String str2, int i) {
        setState(MEDIATION_STATE.STATE_NOT_INITIALIZED);
        this.mSmashes = new ConcurrentHashMap<>();
        this.mWaterfall = new CopyOnWriteArrayList<>();
        this.mWaterfallServerData = new ConcurrentHashMap<>();
        this.mCurrentPlacement = "";
        this.mCurrentAuctionId = "";
        this.mAppContext = activity.getApplicationContext();
        this.mMaxSmashesToLoad = interstitialConfigurations.getInterstitialAdaptersSmartLoadAmount();
        CallbackThrottler.getInstance().setDelayLoadFailureNotificationInSeconds(i);
        AuctionSettings interstitialAuctionSettings = interstitialConfigurations.getInterstitialAuctionSettings();
        this.mTimeToWaitBeforeFirstAction = interstitialAuctionSettings.getTimeToWaitBeforeFirstAuctionMs();
        this.mAuctionHandler = new AuctionHandler(this.mAppContext, aa.e.bo, interstitialAuctionSettings.getBlob(), interstitialAuctionSettings.getUrl());
        HashSet hashSet = new HashSet();
        for (ProviderSettings providerSettings : list) {
            AbstractAdapter abstractAdapterLoadAdapter = ProgUtils.loadAdapter(providerSettings);
            if (abstractAdapterLoadAdapter != null && AdaptersCompatibilityHandler.getInstance().isAdapterVersionISCompatible(abstractAdapterLoadAdapter)) {
                IronSourceObject.getInstance().addToISAdaptersList(abstractAdapterLoadAdapter);
                ProgIsSmash progIsSmash = new ProgIsSmash(activity, str, str2, providerSettings, this, interstitialConfigurations.getInterstitialAdaptersSmartLoadTimeout(), abstractAdapterLoadAdapter);
                this.mSmashes.put(progIsSmash.getInstanceName(), progIsSmash);
                hashSet.add(progIsSmash.getNameForReflection());
            }
        }
        this.mSessionCappingManager = new SessionCappingManager(new ArrayList(this.mSmashes.values()));
        for (ProgIsSmash progIsSmash2 : this.mSmashes.values()) {
            if (progIsSmash2.isBidder()) {
                progIsSmash2.initForBidding();
            } else if (hashSet.contains(progIsSmash2.getNameForReflection())) {
                hashSet.remove(progIsSmash2.getNameForReflection());
                progIsSmash2.preInitInterstitial();
            }
        }
        this.mInitMangerTime = new Date().getTime();
        setState(MEDIATION_STATE.STATE_READY_TO_LOAD);
    }

    public void onResume(Activity activity) {
        synchronized (this.mSmashes) {
            Iterator<ProgIsSmash> it = this.mSmashes.values().iterator();
            while (it.hasNext()) {
                it.next().onResume(activity);
            }
        }
    }

    public void onPause(Activity activity) {
        synchronized (this.mSmashes) {
            Iterator<ProgIsSmash> it = this.mSmashes.values().iterator();
            while (it.hasNext()) {
                it.next().onPause(activity);
            }
        }
    }

    public void setConsent(boolean z) {
        synchronized (this.mSmashes) {
            Iterator<ProgIsSmash> it = this.mSmashes.values().iterator();
            while (it.hasNext()) {
                it.next().setConsent(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeAuction() {
        setState(MEDIATION_STATE.STATE_AUCTION);
        this.mCurrentAuctionId = "";
        StringBuilder sb = new StringBuilder();
        long time = this.mTimeToWaitBeforeFirstAction - (new Date().getTime() - this.mInitMangerTime);
        if (time > 0) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ironsource.mediationsdk.ProgIsManager.1
                @Override // java.lang.Runnable
                public void run() {
                    ProgIsManager.this.makeAuction();
                }
            }, time);
            return;
        }
        sendMediationEvent(2000, (Object[][]) null);
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        synchronized (this.mSmashes) {
            for (ProgIsSmash progIsSmash : this.mSmashes.values()) {
                if (!this.mSessionCappingManager.isCapped(progIsSmash)) {
                    if (progIsSmash.isBidder() && progIsSmash.isReadyToBid()) {
                        Map<String, Object> biddingData = progIsSmash.getBiddingData();
                        if (biddingData != null) {
                            map.put(progIsSmash.getInstanceName(), biddingData);
                            sb.append("2" + progIsSmash.getInstanceName() + ",");
                        }
                    } else if (!progIsSmash.isBidder()) {
                        arrayList.add(progIsSmash.getInstanceName());
                        sb.append("1" + progIsSmash.getInstanceName() + ",");
                    }
                }
            }
        }
        if (map.size() == 0 && arrayList.size() == 0) {
            sendMediationEvent(IronSourceConstants.IS_AUCTION_FAILED, new Object[][]{new Object[]{"errorCode", 1005}, new Object[]{"duration", 0}});
            CallbackThrottler.getInstance().onInterstitialAdLoadFailed(new IronSourceError(1005, "No candidates available for auctioning"));
            sendMediationEvent(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", 1005}});
            setState(MEDIATION_STATE.STATE_READY_TO_LOAD);
            return;
        }
        if (sb.length() > 256) {
            sb.setLength(256);
        } else {
            sb.deleteCharAt(sb.length() - 1);
        }
        sendMediationEvent(IronSourceConstants.IS_AUCTION_REQUEST_WATERFALL, new Object[][]{new Object[]{IronSourceConstants.EVENTS_EXT1, sb.toString()}});
        this.mAuctionHandler.executeAuction(map, arrayList, SessionDepthManager.getInstance().getSessionDepth(2), new AuctionHandlerCallback() { // from class: com.ironsource.mediationsdk.ProgIsManager.2
            @Override // com.ironsource.mediationsdk.AuctionHandlerCallback
            public void callback(boolean z, List<AuctionResponseItem> list, String str, int i, String str2, long j) {
                if (z) {
                    ProgIsManager.this.mCurrentAuctionId = str;
                    ProgIsManager.this.sendMediationEvent(IronSourceConstants.IS_AUCTION_SUCCESS, new Object[][]{new Object[]{"duration", Long.valueOf(j)}});
                    ProgIsManager.this.updateWaterfall(list);
                    ProgIsManager.this.loadSmashes();
                    return;
                }
                if (TextUtils.isEmpty(str2)) {
                    ProgIsManager.this.sendMediationEvent(IronSourceConstants.IS_AUCTION_FAILED, new Object[][]{new Object[]{"errorCode", Integer.valueOf(i)}, new Object[]{"duration", Long.valueOf(j)}});
                    CallbackThrottler.getInstance().onInterstitialAdLoadFailed(new IronSourceError(i, "Auction failed"));
                    ProgIsManager.this.sendMediationEvent(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(i)}});
                } else {
                    String strSubstring = str2.substring(0, Math.min(str2.length(), 39));
                    ProgIsManager.this.sendMediationEvent(IronSourceConstants.IS_AUCTION_FAILED, new Object[][]{new Object[]{"errorCode", Integer.valueOf(i)}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, strSubstring}, new Object[]{"duration", Long.valueOf(j)}});
                    CallbackThrottler.getInstance().onInterstitialAdLoadFailed(new IronSourceError(i, str2));
                    ProgIsManager.this.sendMediationEvent(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(i)}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, strSubstring}});
                }
                ProgIsManager.this.setState(MEDIATION_STATE.STATE_READY_TO_LOAD);
            }
        });
    }

    private String getAsString(AuctionResponseItem auctionResponseItem) {
        return (TextUtils.isEmpty(auctionResponseItem.getServerData()) ? "1" : "2") + auctionResponseItem.getInstanceName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWaterfall(List<AuctionResponseItem> list) {
        synchronized (this.mSmashes) {
            this.mWaterfall.clear();
            this.mWaterfallServerData.clear();
            StringBuilder sb = new StringBuilder();
            for (AuctionResponseItem auctionResponseItem : list) {
                sb.append(getAsString(auctionResponseItem) + ",");
                ProgIsSmash progIsSmash = this.mSmashes.get(auctionResponseItem.getInstanceName());
                if (progIsSmash != null) {
                    progIsSmash.setIsLoadCandidate(true);
                    this.mWaterfall.add(progIsSmash);
                    this.mWaterfallServerData.put(progIsSmash.getInstanceName(), auctionResponseItem);
                }
            }
            if (sb.length() > 256) {
                sb.setLength(256);
            } else {
                sb.deleteCharAt(sb.length() - 1);
            }
            sendMediationEvent(IronSourceConstants.IS_AUCTION_RESPONSE_WATERFALL, new Object[][]{new Object[]{IronSourceConstants.EVENTS_EXT1, sb.toString()}});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSmashes() {
        synchronized (this.mSmashes) {
            setState(MEDIATION_STATE.STATE_LOADING_SMASHES);
            for (int i = 0; i < Math.min(this.mMaxSmashesToLoad, this.mWaterfall.size()); i++) {
                ProgIsSmash progIsSmash = this.mWaterfall.get(i);
                String serverData = this.mWaterfallServerData.get(progIsSmash.getInstanceName()).getServerData();
                sendProviderEvent(2002, progIsSmash);
                progIsSmash.loadInterstitial(serverData);
            }
        }
    }

    public synchronized void loadInterstitial() {
        if (this.mState == MEDIATION_STATE.STATE_SHOWING) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "loadInterstitial() cannot be invoked while showing", 3);
            return;
        }
        if ((this.mState != MEDIATION_STATE.STATE_READY_TO_LOAD && this.mState != MEDIATION_STATE.STATE_READY_TO_SHOW) || CallbackThrottler.getInstance().hasPendingInvocation()) {
            logInternal("loadInterstitial() already in progress");
            return;
        }
        this.mCurrentAuctionId = "";
        this.mCurrentPlacement = "";
        sendMediationEvent(2001);
        this.mLoadStartTime = new Date().getTime();
        makeAuction();
    }

    public synchronized void showInterstitial(String str) {
        if (this.mState != MEDIATION_STATE.STATE_READY_TO_SHOW) {
            logInternal("showInterstitial() error state=" + this.mState.toString());
            ISListenerWrapper.getInstance().onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, "No ads to show"));
            return;
        }
        if (str == null) {
            logInternal("showInterstitial error: empty default placement in response");
            ISListenerWrapper.getInstance().onInterstitialAdShowFailed(new IronSourceError(1020, "showInterstitial error: empty default placement in response"));
            sendMediationEvent(IronSourceConstants.IS_CALLBACK_AD_SHOW_ERROR, new Object[][]{new Object[]{"errorCode", 1020}});
            return;
        }
        this.mCurrentPlacement = str;
        sendMediationEventWithPlacement(2100);
        if (CappingManager.isInterstitialPlacementCapped(this.mAppContext, this.mCurrentPlacement)) {
            String str2 = "placement " + this.mCurrentPlacement + " is capped";
            logInternal(str2);
            ISListenerWrapper.getInstance().onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT, str2));
            sendMediationEventWithPlacement(IronSourceConstants.IS_CALLBACK_AD_SHOW_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT)}});
            return;
        }
        synchronized (this.mSmashes) {
            for (ProgIsSmash progIsSmash : this.mWaterfall) {
                if (progIsSmash.isReadyToShow()) {
                    showInterstitial(progIsSmash, this.mCurrentPlacement);
                    return;
                }
                logInternal("showInterstitial " + progIsSmash.getInstanceName() + " isReadyToShow() == false");
            }
            ISListenerWrapper.getInstance().onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
            sendMediationEventWithPlacement(IronSourceConstants.IS_CALLBACK_AD_SHOW_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW)}});
        }
    }

    private void showInterstitial(ProgIsSmash progIsSmash, String str) {
        setState(MEDIATION_STATE.STATE_SHOWING);
        progIsSmash.showInterstitial();
        sendProviderEventWithPlacement(IronSourceConstants.IS_INSTANCE_SHOW, progIsSmash);
        this.mSessionCappingManager.increaseShowCounter(progIsSmash);
        if (this.mSessionCappingManager.isCapped(progIsSmash)) {
            progIsSmash.setCappedPerSession();
            sendProviderEvent(IronSourceConstants.IS_CAP_SESSION, progIsSmash);
            logInternal(progIsSmash.getInstanceName() + " was session capped");
        }
        CappingManager.incrementIsShowCounter(this.mAppContext, str);
        if (CappingManager.isInterstitialPlacementCapped(this.mAppContext, str)) {
            sendMediationEventWithPlacement(IronSourceConstants.IS_CAP_PLACEMENT);
        }
    }

    public synchronized boolean isInterstitialReady() {
        if (IronSourceUtils.isNetworkConnected(this.mAppContext) && this.mState == MEDIATION_STATE.STATE_READY_TO_SHOW) {
            synchronized (this.mSmashes) {
                Iterator<ProgIsSmash> it = this.mWaterfall.iterator();
                while (it.hasNext()) {
                    if (it.next().isReadyToShow()) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    @Override // com.ironsource.mediationsdk.ProgIsManagerListener
    public void onInterstitialAdReady(ProgIsSmash progIsSmash, long j) {
        synchronized (this) {
            logSmashCallback(progIsSmash, "onInterstitialAdReady");
            sendProviderEvent(2003, progIsSmash, new Object[][]{new Object[]{"duration", Long.valueOf(j)}});
            if (this.mState == MEDIATION_STATE.STATE_LOADING_SMASHES) {
                setState(MEDIATION_STATE.STATE_READY_TO_SHOW);
                ISListenerWrapper.getInstance().onInterstitialAdReady();
                sendMediationEvent(2004, new Object[][]{new Object[]{"duration", Long.valueOf(new Date().getTime() - this.mLoadStartTime)}});
            }
        }
    }

    @Override // com.ironsource.mediationsdk.ProgIsManagerListener
    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError, ProgIsSmash progIsSmash, long j) {
        synchronized (this) {
            logSmashCallback(progIsSmash, "onInterstitialAdLoadFailed error=" + ironSourceError.getErrorMessage() + " state=" + this.mState.name());
            sendProviderEvent(IronSourceConstants.IS_INSTANCE_LOAD_FAILED, progIsSmash, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}, new Object[]{"duration", Long.valueOf(j)}});
            if (this.mState == MEDIATION_STATE.STATE_LOADING_SMASHES || this.mState == MEDIATION_STATE.STATE_READY_TO_SHOW) {
                synchronized (this.mSmashes) {
                    boolean z = false;
                    for (ProgIsSmash progIsSmash2 : this.mWaterfall) {
                        if (progIsSmash2.getIsLoadCandidate()) {
                            String serverData = this.mWaterfallServerData.get(progIsSmash2.getInstanceName()).getServerData();
                            sendProviderEvent(2002, progIsSmash2);
                            progIsSmash2.loadInterstitial(serverData);
                            return;
                        } else if (progIsSmash2.isLoadingInProgress()) {
                            z = true;
                        }
                    }
                    if (this.mState == MEDIATION_STATE.STATE_LOADING_SMASHES && !z) {
                        CallbackThrottler.getInstance().onInterstitialAdLoadFailed(new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, "No ads to show"));
                        sendMediationEvent(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW)}});
                        setState(MEDIATION_STATE.STATE_READY_TO_LOAD);
                    }
                }
            }
        }
    }

    @Override // com.ironsource.mediationsdk.ProgIsManagerListener
    public void onInterstitialAdOpened(ProgIsSmash progIsSmash) {
        synchronized (this) {
            logSmashCallback(progIsSmash, "onInterstitialAdOpened");
            ISListenerWrapper.getInstance().onInterstitialAdOpened();
            sendProviderEventWithPlacement(IronSourceConstants.IS_INSTANCE_OPENED, progIsSmash);
            if (this.mWaterfallServerData.containsKey(progIsSmash.getInstanceName())) {
                this.mAuctionHandler.reportImpression(this.mWaterfallServerData.get(progIsSmash.getInstanceName()));
            }
        }
    }

    @Override // com.ironsource.mediationsdk.ProgIsManagerListener
    public void onInterstitialAdClosed(ProgIsSmash progIsSmash) {
        synchronized (this) {
            logSmashCallback(progIsSmash, "onInterstitialAdClosed");
            sendProviderEventWithPlacement(IronSourceConstants.IS_INSTANCE_CLOSED, progIsSmash);
            ISListenerWrapper.getInstance().onInterstitialAdClosed();
            setState(MEDIATION_STATE.STATE_READY_TO_LOAD);
        }
    }

    @Override // com.ironsource.mediationsdk.ProgIsManagerListener
    public void onInterstitialAdShowSucceeded(ProgIsSmash progIsSmash) {
        synchronized (this) {
            logSmashCallback(progIsSmash, "onInterstitialAdShowSucceeded");
            ISListenerWrapper.getInstance().onInterstitialAdShowSucceeded();
            sendProviderEventWithPlacement(IronSourceConstants.IS_INSTANCE_SHOW_SUCCESS, progIsSmash);
        }
    }

    @Override // com.ironsource.mediationsdk.ProgIsManagerListener
    public void onInterstitialAdShowFailed(IronSourceError ironSourceError, ProgIsSmash progIsSmash) {
        synchronized (this) {
            logSmashCallback(progIsSmash, "onInterstitialAdShowFailed error=" + ironSourceError.getErrorMessage());
            ISListenerWrapper.getInstance().onInterstitialAdShowFailed(ironSourceError);
            sendProviderEventWithPlacement(IronSourceConstants.IS_INSTANCE_SHOW_FAILED, progIsSmash, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}});
            setState(MEDIATION_STATE.STATE_READY_TO_LOAD);
        }
    }

    @Override // com.ironsource.mediationsdk.ProgIsManagerListener
    public void onInterstitialAdClicked(ProgIsSmash progIsSmash) {
        synchronized (this) {
            logSmashCallback(progIsSmash, Constants.JSMethods.ON_INTERSTITIAL_AD_CLICKED);
            ISListenerWrapper.getInstance().onInterstitialAdClicked();
            sendProviderEventWithPlacement(2006, progIsSmash);
        }
    }

    @Override // com.ironsource.mediationsdk.ProgIsManagerListener
    public void onInterstitialAdVisible(ProgIsSmash progIsSmash) {
        synchronized (this) {
            logSmashCallback(progIsSmash, "onInterstitialAdVisible");
        }
    }

    @Override // com.ironsource.mediationsdk.ProgIsManagerListener
    public void onInterstitialInitFailed(IronSourceError ironSourceError, ProgIsSmash progIsSmash) {
        synchronized (this) {
            sendProviderEvent(IronSourceConstants.IS_INSTANCE_INIT_FAILED, progIsSmash, new Object[][]{new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}});
        }
    }

    @Override // com.ironsource.mediationsdk.ProgIsManagerListener
    public void onInterstitialInitSuccess(ProgIsSmash progIsSmash) {
        synchronized (this) {
            sendProviderEvent(IronSourceConstants.IS_INSTANCE_INIT_SUCCESS, progIsSmash);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(MEDIATION_STATE mediation_state) {
        this.mState = mediation_state;
        logInternal("state=" + mediation_state);
    }

    private void logSmashCallback(ProgIsSmash progIsSmash, String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "ProgIsManager " + progIsSmash.getInstanceName() + " : " + str, 0);
    }

    private void logInternal(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "ProgIsManager " + str, 0);
    }

    private void sendMediationEvent(int i) {
        sendMediationEvent(i, (Object[][]) null, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMediationEvent(int i, Object[][] objArr) {
        sendMediationEvent(i, objArr, false);
    }

    private void sendMediationEventWithPlacement(int i, Object[][] objArr) {
        sendMediationEvent(i, objArr, true);
    }

    private void sendMediationEventWithPlacement(int i) {
        sendMediationEvent(i, (Object[][]) null, true);
    }

    private void sendMediationEvent(int i, Object[][] objArr, boolean z) {
        HashMap map = new HashMap();
        map.put("provider", "Mediation");
        map.put(IronSourceConstants.EVENTS_PROGRAMMATIC, 1);
        if (!TextUtils.isEmpty(this.mCurrentAuctionId)) {
            map.put(IronSourceConstants.EVENTS_AUCTION_ID, this.mCurrentAuctionId);
        }
        if (z && !TextUtils.isEmpty(this.mCurrentPlacement)) {
            map.put("placement", this.mCurrentPlacement);
        }
        if (objArr != null) {
            try {
                for (Object[] objArr2 : objArr) {
                    map.put(objArr2[0].toString(), objArr2[1]);
                }
            } catch (Exception e) {
                logInternal("sendMediationEvent " + e.getMessage());
            }
        }
        InterstitialEventsManager.getInstance().log(new EventData(i, new JSONObject(map)));
    }

    private void sendProviderEvent(int i, ProgIsSmash progIsSmash) {
        sendProviderEvent(i, progIsSmash, (Object[][]) null, false);
    }

    private void sendProviderEvent(int i, ProgIsSmash progIsSmash, Object[][] objArr) {
        sendProviderEvent(i, progIsSmash, objArr, false);
    }

    private void sendProviderEventWithPlacement(int i, ProgIsSmash progIsSmash, Object[][] objArr) {
        sendProviderEvent(i, progIsSmash, objArr, true);
    }

    private void sendProviderEventWithPlacement(int i, ProgIsSmash progIsSmash) {
        sendProviderEvent(i, progIsSmash, (Object[][]) null, true);
    }

    private void sendProviderEvent(int i, ProgIsSmash progIsSmash, Object[][] objArr, boolean z) {
        Map<String, Object> providerEventData = progIsSmash.getProviderEventData();
        if (!TextUtils.isEmpty(this.mCurrentAuctionId)) {
            providerEventData.put(IronSourceConstants.EVENTS_AUCTION_ID, this.mCurrentAuctionId);
        }
        if (z && !TextUtils.isEmpty(this.mCurrentPlacement)) {
            providerEventData.put("placement", this.mCurrentPlacement);
        }
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
}
