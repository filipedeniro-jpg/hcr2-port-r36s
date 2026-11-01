package com.ironsource.mediationsdk;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.AdapterConfig;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ProgRvSmash extends ProgSmash implements RewardedVideoSmashListener {
    private Activity mActivity;
    private String mAppKey;
    private String mAuctionIdToLoad;
    private String mCurrentAuctionId;
    private Placement mCurrentPlacement;
    private boolean mIsShowCandidate;
    private ProgRvManagerListener mListener;
    private long mLoadStartTime;
    private int mLoadTimeoutSecs;
    private String mServerDataToLoad;
    private boolean mShouldLoadAfterClose;
    private boolean mShouldLoadAfterLoad;
    private SMASH_STATE mState;
    private Timer mTimer;
    private String mUserId;

    protected enum SMASH_STATE {
        NO_INIT,
        INIT_IN_PROGRESS,
        NOT_LOADED,
        LOAD_IN_PROGRESS,
        LOADED,
        SHOW_IN_PROGRESS
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoLoadFailed(IronSourceError ironSourceError) {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoLoadSuccess() {
    }

    public ProgRvSmash(Activity activity, String str, String str2, ProviderSettings providerSettings, ProgRvManagerListener progRvManagerListener, int i, AbstractAdapter abstractAdapter) {
        super(new AdapterConfig(providerSettings, providerSettings.getRewardedVideoSettings()), abstractAdapter);
        this.mState = SMASH_STATE.NO_INIT;
        this.mActivity = activity;
        this.mAppKey = str;
        this.mUserId = str2;
        this.mListener = progRvManagerListener;
        this.mTimer = null;
        this.mLoadTimeoutSecs = i;
        this.mAdapter.addRewardedVideoListener(this);
        this.mShouldLoadAfterClose = false;
        this.mShouldLoadAfterLoad = false;
        this.mIsShowCandidate = false;
        this.mCurrentPlacement = null;
        this.mServerDataToLoad = "";
        this.mCurrentAuctionId = "";
        this.mAuctionIdToLoad = "";
    }

    public synchronized boolean isReadyToBid() {
        return (this.mState == SMASH_STATE.NO_INIT || this.mState == SMASH_STATE.INIT_IN_PROGRESS) ? false : true;
    }

    public synchronized boolean isLoadingInProgress() {
        return this.mState == SMASH_STATE.INIT_IN_PROGRESS || this.mState == SMASH_STATE.LOAD_IN_PROGRESS;
    }

    public synchronized Map<String, Object> getBiddingData() {
        return isBidder() ? this.mAdapter.getRvBiddingData(this.mAdUnitSettings) : null;
    }

    public synchronized void initForBidding() {
        logInternal("initForBidding()");
        setState(SMASH_STATE.INIT_IN_PROGRESS);
        setCustomParams();
        this.mAdapter.initRvForBidding(this.mActivity, this.mAppKey, this.mUserId, this.mAdUnitSettings, this);
    }

    public synchronized void unloadVideo() {
        if (isBidder()) {
            this.mIsShowCandidate = false;
        }
    }

    public synchronized boolean isReadyToShow() {
        if (isBidder()) {
            return this.mIsShowCandidate && this.mState == SMASH_STATE.LOADED && this.mAdapter.isRewardedVideoAvailable(this.mAdUnitSettings);
        }
        return this.mAdapter.isRewardedVideoAvailable(this.mAdUnitSettings);
    }

    public synchronized void loadVideo(String str, String str2) {
        logInternal("loadVideo() auctionId: " + str2 + " state: " + this.mState);
        setIsLoadCandidate(false);
        this.mIsShowCandidate = true;
        if (this.mState == SMASH_STATE.LOAD_IN_PROGRESS) {
            this.mShouldLoadAfterLoad = true;
            this.mAuctionIdToLoad = str2;
            this.mServerDataToLoad = str;
            this.mListener.onLoadError(this, str2);
            return;
        }
        if (this.mState == SMASH_STATE.SHOW_IN_PROGRESS) {
            this.mShouldLoadAfterClose = true;
            this.mAuctionIdToLoad = str2;
            this.mServerDataToLoad = str;
            return;
        }
        this.mCurrentAuctionId = str2;
        startLoadTimer();
        this.mLoadStartTime = new Date().getTime();
        sendProviderEvent(1001);
        if (isBidder()) {
            setState(SMASH_STATE.LOAD_IN_PROGRESS);
            this.mAdapter.loadVideo(this.mAdUnitSettings, this, str);
        } else if (this.mState == SMASH_STATE.NO_INIT) {
            setCustomParams();
            setState(SMASH_STATE.LOAD_IN_PROGRESS);
            this.mAdapter.initRewardedVideo(this.mActivity, this.mAppKey, this.mUserId, this.mAdUnitSettings, this);
        } else {
            setState(SMASH_STATE.LOAD_IN_PROGRESS);
            this.mAdapter.fetchRewardedVideo(this.mAdUnitSettings);
        }
    }

    public synchronized void reportShowChance(boolean z) {
        Object[][] objArr = new Object[1][];
        Object[] objArr2 = new Object[2];
        objArr2[0] = "status";
        objArr2[1] = z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
        objArr[0] = objArr2;
        sendProviderEventWithPlacement(IronSourceConstants.RV_INSTANCE_SHOW_CHANCE, objArr);
    }

    public synchronized void showVideo(Placement placement) {
        logInternal("showVideo()");
        this.mCurrentPlacement = placement;
        setState(SMASH_STATE.SHOW_IN_PROGRESS);
        this.mAdapter.showRewardedVideo(this.mAdUnitSettings, this);
        sendProviderEventWithPlacement(IronSourceConstants.RV_INSTANCE_SHOW);
    }

    public synchronized void setCappedPerSession() {
        this.mAdapter.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION, "rewardedvideo");
        sendProviderEvent(IronSourceConstants.RV_CAP_SESSION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(SMASH_STATE smash_state) {
        logInternal("current state=" + this.mState + ", new state=" + smash_state);
        this.mState = smash_state;
    }

    private void setCustomParams() {
        try {
            Integer age = IronSourceObject.getInstance().getAge();
            if (age != null) {
                this.mAdapter.setAge(age.intValue());
            }
            String gender = IronSourceObject.getInstance().getGender();
            if (!TextUtils.isEmpty(gender)) {
                this.mAdapter.setGender(gender);
            }
            String mediationSegment = IronSourceObject.getInstance().getMediationSegment();
            if (!TextUtils.isEmpty(mediationSegment)) {
                this.mAdapter.setMediationSegment(mediationSegment);
            }
            String pluginType = ConfigFile.getConfigFile().getPluginType();
            if (!TextUtils.isEmpty(pluginType)) {
                this.mAdapter.setPluginData(pluginType, ConfigFile.getConfigFile().getPluginFrameworkVersion());
            }
            Boolean consent = IronSourceObject.getInstance().getConsent();
            if (consent != null) {
                logInternal("setConsent(" + consent + ")");
                this.mAdapter.setConsent(consent.booleanValue());
            }
        } catch (Exception e) {
            logInternal("setCustomParams() " + e.getMessage());
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoAvailabilityChanged(boolean z) {
        stopLoadTimer();
        logAdapterCallback("onRewardedVideoAvailabilityChanged available=" + z + " state=" + this.mState.name());
        if (this.mState != SMASH_STATE.LOAD_IN_PROGRESS) {
            sendProviderEvent(z ? IronSourceConstants.RV_INSTANCE_AVAILABILITY_TRUE : IronSourceConstants.RV_INSTANCE_AVAILABILITY_FALSE);
            return;
        }
        setState(z ? SMASH_STATE.LOADED : SMASH_STATE.NOT_LOADED);
        sendProviderEvent(z ? 1002 : IronSourceConstants.RV_INSTANCE_LOAD_FAILED, new Object[][]{new Object[]{"duration", Long.valueOf(new Date().getTime() - this.mLoadStartTime)}});
        if (!this.mShouldLoadAfterLoad) {
            if (z) {
                this.mListener.onLoadSuccess(this, this.mCurrentAuctionId);
            } else {
                this.mListener.onLoadError(this, this.mCurrentAuctionId);
            }
            return;
        }
        this.mShouldLoadAfterLoad = false;
        logInternal("onRewardedVideoAvailabilityChanged to " + z + "and mShouldLoadAfterLoad is true - calling loadVideo");
        loadVideo(this.mServerDataToLoad, this.mAuctionIdToLoad);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
        logAdapterCallback("onRewardedVideoAdShowFailed error=" + ironSourceError.getErrorMessage());
        sendProviderEventWithPlacement(IronSourceConstants.RV_INSTANCE_SHOW_FAILED, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}});
        if (this.mState != SMASH_STATE.SHOW_IN_PROGRESS) {
            return;
        }
        setState(SMASH_STATE.NOT_LOADED);
        this.mListener.onRewardedVideoAdShowFailed(ironSourceError, this);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoInitSuccess() {
        logAdapterCallback("onRewardedVideoInitSuccess");
        if (this.mState != SMASH_STATE.INIT_IN_PROGRESS) {
            return;
        }
        setState(SMASH_STATE.NOT_LOADED);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoInitFailed(IronSourceError ironSourceError) {
        logAdapterCallback("onRewardedVideoInitFailed error=" + ironSourceError.getErrorMessage());
        stopLoadTimer();
        sendProviderEvent(IronSourceConstants.RV_INSTANCE_LOAD_FAILED, new Object[][]{new Object[]{"errorCode", 1033}, new Object[]{"duration", Long.valueOf(new Date().getTime() - this.mLoadStartTime)}});
        if (this.mState != SMASH_STATE.INIT_IN_PROGRESS) {
            return;
        }
        setState(SMASH_STATE.NO_INIT);
        this.mListener.onLoadError(this, this.mCurrentAuctionId);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoAdOpened() {
        logAdapterCallback("onRewardedVideoAdOpened");
        this.mListener.onRewardedVideoAdOpened(this);
        sendProviderEventWithPlacement(1005);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoAdStarted() {
        logAdapterCallback("onRewardedVideoAdStarted");
        this.mListener.onRewardedVideoAdStarted(this);
        sendProviderEventWithPlacement(IronSourceConstants.RV_INSTANCE_STARTED);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoAdVisible() {
        logAdapterCallback("onRewardedVideoAdVisible");
        sendProviderEventWithPlacement(IronSourceConstants.RV_INSTANCE_VISIBLE);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoAdClosed() {
        logAdapterCallback("onRewardedVideoAdClosed");
        sendProviderEventWithPlacement(IronSourceConstants.RV_INSTANCE_CLOSED);
        if (this.mState != SMASH_STATE.SHOW_IN_PROGRESS) {
            return;
        }
        setState(SMASH_STATE.NOT_LOADED);
        this.mListener.onRewardedVideoAdClosed(this);
        if (this.mShouldLoadAfterClose) {
            logInternal("onRewardedVideoAdClosed and mShouldLoadAfterClose is true - calling loadVideo");
            this.mShouldLoadAfterClose = false;
            loadVideo(this.mServerDataToLoad, this.mAuctionIdToLoad);
            this.mServerDataToLoad = "";
            this.mAuctionIdToLoad = "";
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoAdEnded() {
        logAdapterCallback("onRewardedVideoAdEnded");
        this.mListener.onRewardedVideoAdEnded(this);
        sendProviderEventWithPlacement(IronSourceConstants.RV_INSTANCE_ENDED);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoAdRewarded() {
        logAdapterCallback("onRewardedVideoAdRewarded");
        this.mListener.onRewardedVideoAdRewarded(this, this.mCurrentPlacement);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Object[]{"placement", this.mCurrentPlacement.getPlacementName()});
        arrayList.add(new Object[]{IronSourceConstants.EVENTS_REWARD_NAME, this.mCurrentPlacement.getRewardName()});
        arrayList.add(new Object[]{IronSourceConstants.EVENTS_REWARD_AMOUNT, Integer.valueOf(this.mCurrentPlacement.getRewardAmount())});
        arrayList.add(new Object[]{IronSourceConstants.EVENTS_TRANS_ID, IronSourceUtils.getTransId(Long.toString(new Date().getTime()) + this.mAppKey + getInstanceName())});
        if (!TextUtils.isEmpty(IronSourceObject.getInstance().getDynamicUserId())) {
            arrayList.add(new Object[]{IronSourceConstants.EVENTS_DYNAMIC_USER_ID, IronSourceObject.getInstance().getDynamicUserId()});
        }
        if (IronSourceObject.getInstance().getRvServerParams() != null) {
            for (String str : IronSourceObject.getInstance().getRvServerParams().keySet()) {
                arrayList.add(new Object[]{"custom_" + str, IronSourceObject.getInstance().getRvServerParams().get(str)});
            }
        }
        sendProviderEvent(1010, (Object[][]) arrayList.toArray((Object[][]) Array.newInstance((Class<?>) Object.class, 2, arrayList.size())));
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public synchronized void onRewardedVideoAdClicked() {
        logAdapterCallback("onRewardedVideoAdClicked");
        this.mListener.onRewardedVideoAdClicked(this, this.mCurrentPlacement);
        sendProviderEventWithPlacement(1006);
    }

    private void stopLoadTimer() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }

    private void startLoadTimer() {
        stopLoadTimer();
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.ProgRvSmash.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                ProgRvSmash.this.logInternal("Rewarded Video - load instance time out");
                long time = new Date().getTime() - ProgRvSmash.this.mLoadStartTime;
                if (ProgRvSmash.this.mState == SMASH_STATE.LOAD_IN_PROGRESS) {
                    ProgRvSmash.this.sendProviderEvent(IronSourceConstants.RV_INSTANCE_LOAD_FAILED, new Object[][]{new Object[]{"errorCode", 1025}, new Object[]{"duration", Long.valueOf(time)}});
                    ProgRvSmash.this.setState(SMASH_STATE.NOT_LOADED);
                    ProgRvManagerListener progRvManagerListener = ProgRvSmash.this.mListener;
                    ProgRvSmash progRvSmash = ProgRvSmash.this;
                    progRvManagerListener.onLoadError(progRvSmash, progRvSmash.mCurrentAuctionId);
                    return;
                }
                if (ProgRvSmash.this.mState == SMASH_STATE.INIT_IN_PROGRESS) {
                    ProgRvSmash.this.sendProviderEvent(IronSourceConstants.RV_INSTANCE_LOAD_FAILED, new Object[][]{new Object[]{"errorCode", 1032}, new Object[]{"duration", Long.valueOf(time)}});
                    ProgRvSmash.this.setState(SMASH_STATE.NOT_LOADED);
                    ProgRvManagerListener progRvManagerListener2 = ProgRvSmash.this.mListener;
                    ProgRvSmash progRvSmash2 = ProgRvSmash.this;
                    progRvManagerListener2.onLoadError(progRvSmash2, progRvSmash2.mCurrentAuctionId);
                    return;
                }
                ProgRvSmash.this.sendProviderEvent(IronSourceConstants.RV_INSTANCE_AVAILABILITY_FALSE);
                ProgRvSmash.this.setState(SMASH_STATE.NOT_LOADED);
            }
        }, this.mLoadTimeoutSecs * 1000);
    }

    private void logAdapterCallback(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getInstanceName() + " smash: " + str, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logInternal(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, getInstanceName() + " smash: " + str, 0);
    }

    private void sendProviderEventWithPlacement(int i) {
        sendProviderEventWithPlacement(i, (Object[][]) null);
    }

    private void sendProviderEventWithPlacement(int i, Object[][] objArr) {
        sendProviderEvent(i, objArr, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendProviderEvent(int i) {
        sendProviderEvent(i, (Object[][]) null, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendProviderEvent(int i, Object[][] objArr) {
        sendProviderEvent(i, objArr, false);
    }

    private void sendProviderEvent(int i, Object[][] objArr, boolean z) {
        Placement placement;
        Map<String, Object> providerEventData = getProviderEventData();
        if (!TextUtils.isEmpty(this.mCurrentAuctionId)) {
            providerEventData.put(IronSourceConstants.EVENTS_AUCTION_ID, this.mCurrentAuctionId);
        }
        if (z && (placement = this.mCurrentPlacement) != null && !TextUtils.isEmpty(placement.getPlacementName())) {
            providerEventData.put("placement", this.mCurrentPlacement.getPlacementName());
        }
        if (objArr != null) {
            try {
                for (Object[] objArr2 : objArr) {
                    providerEventData.put(objArr2[0].toString(), objArr2[1]);
                }
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, getInstanceName() + " smash: RV sendMediationEvent " + Log.getStackTraceString(e), 3);
            }
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(i, new JSONObject(providerEventData)));
    }
}
