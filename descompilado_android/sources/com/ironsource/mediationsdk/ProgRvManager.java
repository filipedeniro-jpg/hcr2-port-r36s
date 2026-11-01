package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.model.RewardedVideoConfigurations;
import com.ironsource.mediationsdk.utils.AuctionSettings;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.SessionCappingManager;
import com.ironsource.mediationsdk.utils.SessionDepthManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class ProgRvManager implements ProgRvManagerListener, RvAuctionTriggerCallback {
    private Context mAppContext;
    private AuctionHandler mAuctionHandler;
    private long mAuctionStartTime;
    private String mCurrentAuctionId;
    private String mCurrentPlacement;
    private boolean mIsShowingVideo;
    private long mLastChangedAvailabilityTime;
    private Boolean mLastReportedAvailabilityState;
    private int mMaxSmashesToLoad;
    private RvAuctionTrigger mRvAuctionTrigger;
    private SessionCappingManager mSessionCappingManager;
    private final ConcurrentHashMap<String, ProgRvSmash> mSmashes;
    private RV_MEDIATION_STATE mState;
    private CopyOnWriteArrayList<ProgRvSmash> mWaterfall;
    private ConcurrentHashMap<String, AuctionResponseItem> mWaterfallServerData;

    private enum RV_MEDIATION_STATE {
        RV_STATE_INITIATING,
        RV_STATE_AUCTION_IN_PROGRESS,
        RV_STATE_NOT_LOADED,
        RV_STATE_LOADING_SMASHES,
        RV_STATE_READY_TO_SHOW
    }

    public ProgRvManager(Activity activity, List<ProviderSettings> list, RewardedVideoConfigurations rewardedVideoConfigurations, String str, String str2) {
        setState(RV_MEDIATION_STATE.RV_STATE_INITIATING);
        this.mAppContext = activity.getApplicationContext();
        this.mLastReportedAvailabilityState = null;
        this.mMaxSmashesToLoad = rewardedVideoConfigurations.getRewardedVideoAdaptersSmartLoadAmount();
        this.mCurrentPlacement = "";
        AuctionSettings rewardedVideoAuctionSettings = rewardedVideoConfigurations.getRewardedVideoAuctionSettings();
        this.mIsShowingVideo = false;
        this.mWaterfall = new CopyOnWriteArrayList<>();
        this.mWaterfallServerData = new ConcurrentHashMap<>();
        this.mLastChangedAvailabilityTime = new Date().getTime();
        this.mAuctionHandler = new AuctionHandler(this.mAppContext, "rewardedVideo", rewardedVideoAuctionSettings.getBlob(), rewardedVideoAuctionSettings.getUrl());
        this.mRvAuctionTrigger = new RvAuctionTrigger(rewardedVideoAuctionSettings, this);
        this.mSmashes = new ConcurrentHashMap<>();
        for (ProviderSettings providerSettings : list) {
            AbstractAdapter abstractAdapterLoadAdapter = ProgUtils.loadAdapter(providerSettings);
            if (abstractAdapterLoadAdapter != null && AdaptersCompatibilityHandler.getInstance().isAdapterVersionRVCompatible(abstractAdapterLoadAdapter)) {
                IronSourceObject.getInstance().addToRVAdaptersList(abstractAdapterLoadAdapter);
                ProgRvSmash progRvSmash = new ProgRvSmash(activity, str, str2, providerSettings, this, rewardedVideoConfigurations.getRewardedVideoAdaptersSmartLoadTimeout(), abstractAdapterLoadAdapter);
                this.mSmashes.put(progRvSmash.getInstanceName(), progRvSmash);
            }
        }
        this.mSessionCappingManager = new SessionCappingManager(new ArrayList(this.mSmashes.values()));
        for (ProgRvSmash progRvSmash2 : this.mSmashes.values()) {
            if (progRvSmash2.isBidder()) {
                progRvSmash2.initForBidding();
            }
        }
        new Timer().schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.ProgRvManager.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                ProgRvManager.this.makeAuction();
            }
        }, rewardedVideoAuctionSettings.getTimeToWaitBeforeFirstAuctionMs());
    }

    public synchronized void showRewardedVideo(Placement placement) {
        try {
            if (placement == null) {
                logInternal("showRewardedVideo error: empty default placement in response");
                RVListenerWrapper.getInstance().onRewardedVideoAdShowFailed(new IronSourceError(1021, "showRewardedVideo error: empty default placement in response"));
                sendMediationEvent(IronSourceConstants.RV_CALLBACK_SHOW_FAILED, new Object[][]{new Object[]{"errorCode", 1021}}, false, true);
                return;
            }
            this.mCurrentPlacement = placement.getPlacementName();
            logApi("showRewardedVideo() placement=" + this.mCurrentPlacement);
            sendMediationEventWithPlacement(IronSourceConstants.RV_API_SHOW_CALLED);
            if (this.mIsShowingVideo) {
                String str = "showRewardedVideo error: can't show ad while an ad is already showing. State: " + this.mState;
                logInternal(str);
                RVListenerWrapper.getInstance().onRewardedVideoAdShowFailed(new IronSourceError(1022, str));
                sendMediationEventWithPlacement(IronSourceConstants.RV_CALLBACK_SHOW_FAILED, new Object[][]{new Object[]{"errorCode", 1022}});
                return;
            }
            if (this.mState != RV_MEDIATION_STATE.RV_STATE_READY_TO_SHOW) {
                logInternal("showRewardedVideo error: show called while no ads are available");
                RVListenerWrapper.getInstance().onRewardedVideoAdShowFailed(new IronSourceError(IronSourceError.ERROR_RV_SHOW_CALLED_WRONG_STATE, "showRewardedVideo error: show called while no ads are available"));
                sendMediationEventWithPlacement(IronSourceConstants.RV_CALLBACK_SHOW_FAILED, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_RV_SHOW_CALLED_WRONG_STATE)}});
                return;
            }
            if (CappingManager.isRvPlacementCapped(this.mAppContext, this.mCurrentPlacement)) {
                String str2 = "placement " + this.mCurrentPlacement + " is capped";
                logInternal(str2);
                RVListenerWrapper.getInstance().onRewardedVideoAdShowFailed(new IronSourceError(IronSourceError.ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT, str2));
                sendMediationEventWithPlacement(IronSourceConstants.RV_CALLBACK_SHOW_FAILED, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_REACHED_CAP_LIMIT_PER_PLACEMENT)}});
                return;
            }
            synchronized (this.mSmashes) {
                for (ProgRvSmash progRvSmash : this.mWaterfall) {
                    if (progRvSmash.isReadyToShow()) {
                        this.mIsShowingVideo = true;
                        progRvSmash.reportShowChance(true);
                        showVideo(progRvSmash, placement);
                        setState(RV_MEDIATION_STATE.RV_STATE_NOT_LOADED);
                        this.mRvAuctionTrigger.showStart();
                        return;
                    }
                    progRvSmash.reportShowChance(false);
                }
                logApi("showRewardedVideo(): No ads to show ");
                RVListenerWrapper.getInstance().onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                sendMediationEventWithPlacement(IronSourceConstants.RV_CALLBACK_SHOW_FAILED, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW)}});
                this.mRvAuctionTrigger.showError();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private void showVideo(ProgRvSmash progRvSmash, Placement placement) {
        logInternal("showVideo()");
        this.mSessionCappingManager.increaseShowCounter(progRvSmash);
        if (this.mSessionCappingManager.isCapped(progRvSmash)) {
            progRvSmash.setCappedPerSession();
            logInternal(progRvSmash.getInstanceName() + " rewarded video is now session capped");
        }
        CappingManager.incrementRvShowCounter(this.mAppContext, placement.getPlacementName());
        if (CappingManager.isRvPlacementCapped(this.mAppContext, placement.getPlacementName())) {
            sendMediationEventWithPlacement(1400);
        }
        progRvSmash.showVideo(placement);
    }

    public synchronized boolean isRewardedVideoAvailable() {
        return isRewardedVideoAvailableInternal();
    }

    private boolean isRewardedVideoAvailableInternal() {
        if (this.mState != RV_MEDIATION_STATE.RV_STATE_READY_TO_SHOW || this.mIsShowingVideo) {
            return false;
        }
        synchronized (this.mSmashes) {
            Iterator<ProgRvSmash> it = this.mWaterfall.iterator();
            while (it.hasNext()) {
                if (it.next().isReadyToShow()) {
                    return true;
                }
            }
            return false;
        }
    }

    public void onResume(Activity activity) {
        synchronized (this.mSmashes) {
            Iterator<ProgRvSmash> it = this.mSmashes.values().iterator();
            while (it.hasNext()) {
                it.next().onResume(activity);
            }
        }
    }

    public void onPause(Activity activity) {
        synchronized (this.mSmashes) {
            Iterator<ProgRvSmash> it = this.mSmashes.values().iterator();
            while (it.hasNext()) {
                it.next().onPause(activity);
            }
        }
    }

    public void setConsent(boolean z) {
        synchronized (this.mSmashes) {
            Iterator<ProgRvSmash> it = this.mSmashes.values().iterator();
            while (it.hasNext()) {
                it.next().setConsent(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeAuction() {
        logInternal("makeAuction()");
        setState(RV_MEDIATION_STATE.RV_STATE_AUCTION_IN_PROGRESS);
        this.mCurrentAuctionId = "";
        this.mAuctionStartTime = new Date().getTime();
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        synchronized (this.mSmashes) {
            for (ProgRvSmash progRvSmash : this.mSmashes.values()) {
                progRvSmash.unloadVideo();
                if (!this.mSessionCappingManager.isCapped(progRvSmash)) {
                    if (progRvSmash.isBidder() && progRvSmash.isReadyToBid()) {
                        Map<String, Object> biddingData = progRvSmash.getBiddingData();
                        if (biddingData != null) {
                            map.put(progRvSmash.getInstanceName(), biddingData);
                            sb.append("2" + progRvSmash.getInstanceName() + ",");
                        }
                    } else if (!progRvSmash.isBidder()) {
                        arrayList.add(progRvSmash.getInstanceName());
                        sb.append("1" + progRvSmash.getInstanceName() + ",");
                    }
                }
            }
        }
        if (map.keySet().size() == 0 && arrayList.size() == 0) {
            logInternal("makeAuction() failed - request waterfall is empty");
            reportAvailabilityIfNeeded(false);
            setState(RV_MEDIATION_STATE.RV_STATE_NOT_LOADED);
            this.mRvAuctionTrigger.loadError();
            return;
        }
        logInternal("makeAuction() - request waterfall is: " + ((Object) sb));
        if (sb.length() > 256) {
            sb.setLength(256);
        } else {
            sb.deleteCharAt(sb.length() - 1);
        }
        sendMediationEventWithoutAuctionId(1000);
        sendMediationEventWithoutAuctionId(IronSourceConstants.RV_AUCTION_REQUEST);
        sendMediationEventWithoutAuctionId(IronSourceConstants.RV_AUCTION_REQUEST_WATERFALL, new Object[][]{new Object[]{IronSourceConstants.EVENTS_EXT1, sb.toString()}});
        this.mAuctionHandler.executeAuction(map, arrayList, SessionDepthManager.getInstance().getSessionDepth(1), new AuctionHandlerCallback() { // from class: com.ironsource.mediationsdk.ProgRvManager.2
            @Override // com.ironsource.mediationsdk.AuctionHandlerCallback
            public void callback(boolean z, List<AuctionResponseItem> list, String str, int i, String str2, long j) {
                if (z) {
                    ProgRvManager.this.logInternal("makeAuction(): success");
                    ProgRvManager.this.mCurrentAuctionId = str;
                    ProgRvManager.this.sendMediationEvent(IronSourceConstants.RV_AUCTION_SUCCESS, new Object[][]{new Object[]{"duration", Long.valueOf(j)}});
                    ProgRvManager.this.updateWaterfall(list);
                    ProgRvManager.this.loadSmashes();
                    return;
                }
                ProgRvManager.this.logInternal("makeAuction(): failed");
                if (TextUtils.isEmpty(str2)) {
                    ProgRvManager.this.sendMediationEventWithoutAuctionId(IronSourceConstants.RV_AUCTION_FAILED, new Object[][]{new Object[]{"errorCode", Integer.valueOf(i)}, new Object[]{"duration", Long.valueOf(j)}});
                } else {
                    ProgRvManager.this.sendMediationEventWithoutAuctionId(IronSourceConstants.RV_AUCTION_FAILED, new Object[][]{new Object[]{"errorCode", Integer.valueOf(i)}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, str2.substring(0, Math.min(str2.length(), 39))}, new Object[]{"duration", Long.valueOf(j)}});
                }
                ProgRvManager.this.reportAvailabilityIfNeeded(false);
                ProgRvManager.this.setState(RV_MEDIATION_STATE.RV_STATE_NOT_LOADED);
                ProgRvManager.this.mRvAuctionTrigger.loadError();
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
                ProgRvSmash progRvSmash = this.mSmashes.get(auctionResponseItem.getInstanceName());
                if (progRvSmash != null) {
                    progRvSmash.setIsLoadCandidate(true);
                    this.mWaterfall.add(progRvSmash);
                    this.mWaterfallServerData.put(progRvSmash.getInstanceName(), auctionResponseItem);
                }
            }
            if (sb.length() > 256) {
                sb.setLength(256);
            } else {
                sb.deleteCharAt(sb.length() - 1);
            }
            sendMediationEvent(IronSourceConstants.RV_AUCTION_RESPONSE_WATERFALL, new Object[][]{new Object[]{IronSourceConstants.EVENTS_EXT1, sb.toString()}});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSmashes() {
        synchronized (this.mSmashes) {
            setState(RV_MEDIATION_STATE.RV_STATE_LOADING_SMASHES);
            int i = 0;
            for (int i2 = 0; i2 < this.mWaterfall.size() && i < this.mMaxSmashesToLoad; i2++) {
                ProgRvSmash progRvSmash = this.mWaterfall.get(i2);
                if (progRvSmash.getIsLoadCandidate()) {
                    progRvSmash.loadVideo(this.mWaterfallServerData.get(progRvSmash.getInstanceName()).getServerData(), this.mCurrentAuctionId);
                    i++;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(RV_MEDIATION_STATE rv_mediation_state) {
        logInternal("current state=" + this.mState + ", new state=" + rv_mediation_state);
        this.mState = rv_mediation_state;
    }

    @Override // com.ironsource.mediationsdk.ProgRvManagerListener
    public synchronized void onLoadSuccess(ProgRvSmash progRvSmash, String str) {
        logSmashCallback(progRvSmash, "onLoadSuccess ");
        if (this.mState != RV_MEDIATION_STATE.RV_STATE_LOADING_SMASHES && this.mState != RV_MEDIATION_STATE.RV_STATE_READY_TO_SHOW) {
            logErrorInternal("onLoadSuccess was invoked at the wrong manager state: " + this.mState);
            return;
        }
        if (!str.equalsIgnoreCase(this.mCurrentAuctionId)) {
            logInternal("onLoadSuccess was invoked with auctionId: " + str + " and the current id is " + this.mCurrentAuctionId);
            return;
        }
        reportAvailabilityIfNeeded(true);
        if (this.mState != RV_MEDIATION_STATE.RV_STATE_READY_TO_SHOW) {
            setState(RV_MEDIATION_STATE.RV_STATE_READY_TO_SHOW);
            sendMediationEvent(1003, new Object[][]{new Object[]{"duration", Long.valueOf(new Date().getTime() - this.mAuctionStartTime)}});
        }
    }

    @Override // com.ironsource.mediationsdk.ProgRvManagerListener
    public synchronized void onLoadError(ProgRvSmash progRvSmash, String str) {
        try {
            logSmashCallback(progRvSmash, "onLoadError ");
            if (this.mState != RV_MEDIATION_STATE.RV_STATE_LOADING_SMASHES && this.mState != RV_MEDIATION_STATE.RV_STATE_READY_TO_SHOW) {
                logErrorInternal("onLoadError was invoked at the wrong manager state: " + this.mState);
                return;
            }
            if (!str.equalsIgnoreCase(this.mCurrentAuctionId)) {
                logInternal("onLoadError was invoked with auctionId:" + str + " and the current id is " + this.mCurrentAuctionId);
                return;
            }
            synchronized (this.mSmashes) {
                boolean z = false;
                boolean z2 = false;
                for (ProgRvSmash progRvSmash2 : this.mWaterfall) {
                    if (progRvSmash2.getIsLoadCandidate()) {
                        if (this.mWaterfallServerData.get(progRvSmash2.getInstanceName()) != null) {
                            progRvSmash2.loadVideo(this.mWaterfallServerData.get(progRvSmash2.getInstanceName()).getServerData(), this.mCurrentAuctionId);
                            return;
                        }
                    } else if (progRvSmash2.isLoadingInProgress()) {
                        z2 = true;
                    } else if (progRvSmash2.isReadyToShow()) {
                        z = true;
                    }
                }
                if (!z && !z2) {
                    logInternal("onLoadError(): No other available smashes");
                    reportAvailabilityIfNeeded(false);
                    setState(RV_MEDIATION_STATE.RV_STATE_NOT_LOADED);
                    this.mRvAuctionTrigger.loadError();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.ironsource.mediationsdk.ProgRvManagerListener
    public void onRewardedVideoAdOpened(ProgRvSmash progRvSmash) {
        synchronized (this) {
            logSmashCallback(progRvSmash, "onRewardedVideoAdOpened");
            RVListenerWrapper.getInstance().onRewardedVideoAdOpened();
            this.mAuctionHandler.reportImpression(this.mWaterfallServerData.get(progRvSmash.getInstanceName()));
        }
    }

    @Override // com.ironsource.mediationsdk.ProgRvManagerListener
    public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError, ProgRvSmash progRvSmash) {
        synchronized (this) {
            logSmashCallback(progRvSmash, "onRewardedVideoAdShowFailed error=" + ironSourceError.getErrorMessage());
            sendMediationEventWithPlacement(IronSourceConstants.RV_CALLBACK_SHOW_FAILED, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}});
            RVListenerWrapper.getInstance().onRewardedVideoAdShowFailed(ironSourceError);
            this.mIsShowingVideo = false;
            if (this.mState != RV_MEDIATION_STATE.RV_STATE_READY_TO_SHOW) {
                reportAvailabilityIfNeeded(false);
            }
            this.mRvAuctionTrigger.showError();
        }
    }

    @Override // com.ironsource.mediationsdk.ProgRvManagerListener
    public void onRewardedVideoAdClosed(ProgRvSmash progRvSmash) {
        synchronized (this) {
            logSmashCallback(progRvSmash, "onRewardedVideoAdClosed, mediation state: " + this.mState.name());
            RVListenerWrapper.getInstance().onRewardedVideoAdClosed();
            this.mIsShowingVideo = false;
            if (this.mState != RV_MEDIATION_STATE.RV_STATE_READY_TO_SHOW) {
                reportAvailabilityIfNeeded(false);
            }
            this.mRvAuctionTrigger.showEnd();
        }
    }

    @Override // com.ironsource.mediationsdk.ProgRvManagerListener
    public void onRewardedVideoAdStarted(ProgRvSmash progRvSmash) {
        synchronized (this) {
            logSmashCallback(progRvSmash, "onRewardedVideoAdStarted");
            RVListenerWrapper.getInstance().onRewardedVideoAdStarted();
        }
    }

    @Override // com.ironsource.mediationsdk.ProgRvManagerListener
    public void onRewardedVideoAdEnded(ProgRvSmash progRvSmash) {
        synchronized (this) {
            logSmashCallback(progRvSmash, "onRewardedVideoAdEnded");
            RVListenerWrapper.getInstance().onRewardedVideoAdEnded();
        }
    }

    @Override // com.ironsource.mediationsdk.ProgRvManagerListener
    public void onRewardedVideoAdRewarded(ProgRvSmash progRvSmash, Placement placement) {
        synchronized (this) {
            logSmashCallback(progRvSmash, "onRewardedVideoAdRewarded");
            RVListenerWrapper.getInstance().onRewardedVideoAdRewarded(placement);
        }
    }

    @Override // com.ironsource.mediationsdk.ProgRvManagerListener
    public void onRewardedVideoAdClicked(ProgRvSmash progRvSmash, Placement placement) {
        synchronized (this) {
            logSmashCallback(progRvSmash, "onRewardedVideoAdClicked");
            RVListenerWrapper.getInstance().onRewardedVideoAdClicked(placement);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportAvailabilityIfNeeded(boolean z) {
        Boolean bool = this.mLastReportedAvailabilityState;
        if (bool == null || bool.booleanValue() != z) {
            this.mLastReportedAvailabilityState = Boolean.valueOf(z);
            long time = new Date().getTime() - this.mLastChangedAvailabilityTime;
            this.mLastChangedAvailabilityTime = new Date().getTime();
            if (z) {
                sendMediationEvent(IronSourceConstants.RV_CALLBACK_AVAILABILITY_TRUE, new Object[][]{new Object[]{"duration", Long.valueOf(time)}});
            } else {
                sendMediationEvent(IronSourceConstants.RV_CALLBACK_AVAILABILITY_FALSE, new Object[][]{new Object[]{"duration", Long.valueOf(time)}});
            }
            RVListenerWrapper.getInstance().onRewardedVideoAvailabilityChanged(z);
        }
    }

    private void logSmashCallback(ProgRvSmash progRvSmash, String str) {
        String str2 = progRvSmash.getInstanceName() + " : " + str;
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "ProgRvManager: " + str2, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logInternal(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "ProgRvManager: " + str, 0);
    }

    private void logErrorInternal(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "ProgRvManager: " + str, 3);
    }

    private void logApi(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, str, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMediationEventWithoutAuctionId(int i, Object[][] objArr) {
        sendMediationEvent(i, objArr, false, false);
    }

    private void sendMediationEventWithoutAuctionId(int i) {
        sendMediationEvent(i, (Object[][]) null, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMediationEvent(int i, Object[][] objArr) {
        sendMediationEvent(i, objArr, false, true);
    }

    private void sendMediationEventWithPlacement(int i) {
        sendMediationEvent(i, (Object[][]) null, true, true);
    }

    private void sendMediationEventWithPlacement(int i, Object[][] objArr) {
        sendMediationEvent(i, objArr, true, true);
    }

    private void sendMediationEvent(int i, Object[][] objArr, boolean z, boolean z2) {
        HashMap map = new HashMap();
        map.put("provider", "Mediation");
        map.put(IronSourceConstants.EVENTS_PROGRAMMATIC, 1);
        if (z2 && !TextUtils.isEmpty(this.mCurrentAuctionId)) {
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
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "ProgRvManager: RV sendMediationEvent " + Log.getStackTraceString(e), 3);
            }
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(i, new JSONObject(map)));
    }

    @Override // com.ironsource.mediationsdk.RvAuctionTriggerCallback
    public synchronized void onAuctionTriggered() {
        logInternal("onAuctionTriggered: auction was triggered in " + this.mState + " state");
        makeAuction();
    }
}
