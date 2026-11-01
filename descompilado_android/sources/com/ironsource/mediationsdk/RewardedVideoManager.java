package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.ironsource.environment.NetworkStateReceiver;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.ListenersWrapper;
import com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener;
import com.ironsource.mediationsdk.server.Server;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.utils.DailyCappingListener;
import com.ironsource.mediationsdk.utils.DailyCappingManager;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class RewardedVideoManager extends AbstractAdUnitManager implements RewardedVideoManagerListener, NetworkStateReceiver.NetworkStateReceiverListener, DailyCappingListener {
    private Placement mCurrentPlacement;
    private ListenersWrapper mListenersWrapper;
    private int mManualLoadInterval;
    private NetworkStateReceiver mNetworkStateReceiver;
    private final String TAG = getClass().getSimpleName();
    private Timer mTimer = null;
    private boolean mPauseSmartLoadDueToNetworkUnavailability = false;
    private boolean mIsUltraEventsEnabled = false;
    private boolean mShouldSendMediationLoadSuccessEvent = false;
    private long mLoadStartTime = new Date().getTime();
    private List<AbstractSmash.MEDIATION_STATE> mStatesToIgnore = Arrays.asList(AbstractSmash.MEDIATION_STATE.INIT_FAILED, AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION, AbstractSmash.MEDIATION_STATE.EXHAUSTED, AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);

    RewardedVideoManager() {
        this.mDailyCappingManager = new DailyCappingManager("rewarded_video", this);
    }

    public void setRewardedVideoListener(ListenersWrapper listenersWrapper) {
        this.mListenersWrapper = listenersWrapper;
    }

    public synchronized void initRewardedVideo(Activity activity, String str, String str2) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":initRewardedVideo(appKey: " + str + ", userId: " + str2 + ")", 1);
        this.mAppKey = str;
        this.mUserId = str2;
        this.mActivity = activity;
        this.mDailyCappingManager.setContext(this.mActivity);
        int i = 0;
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (this.mDailyCappingManager.shouldSendCapReleasedEvent(abstractSmash)) {
                logProviderEvent(IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED, abstractSmash, new Object[][]{new Object[]{"status", "false"}});
            }
            if (this.mDailyCappingManager.isCapped(abstractSmash)) {
                abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);
                i++;
            }
        }
        if (i == this.mSmashArray.size()) {
            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(false);
            return;
        }
        logMediationEvent(1000);
        this.mListenersWrapper.setRvPlacement(null);
        this.mShouldSendMediationLoadSuccessEvent = true;
        this.mLoadStartTime = new Date().getTime();
        for (int i2 = 0; i2 < this.mSmartLoadAmount && i2 < this.mSmashArray.size() && loadNextAdapter() != null; i2++) {
        }
    }

    public synchronized void showRewardedVideo(String str) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":showRewardedVideo(placementName: " + str + ")", 1);
        this.mListenersWrapper.setRvPlacement(str);
        logMediationEvent(IronSourceConstants.RV_API_SHOW_CALLED, new Object[][]{new Object[]{"placement", str}});
        if (!IronSourceUtils.isNetworkConnected(this.mActivity)) {
            this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoInternetConnectionShowFailError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            return;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.mSmashArray.size(); i3++) {
            AbstractSmash abstractSmash = this.mSmashArray.get(i3);
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "showRewardedVideo, iterating on: " + abstractSmash.getInstanceName() + ", Status: " + abstractSmash.getMediationState(), 0);
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                if (((RewardedVideoSmash) abstractSmash).isRewardedVideoAvailable()) {
                    showAdapter(abstractSmash, i3);
                    if (this.mCanShowPremium && !abstractSmash.equals(getPremiumSmash())) {
                        disablePremiumForCurrentSession();
                    }
                    if (abstractSmash.isCappedPerSession()) {
                        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION);
                        logProviderEvent(IronSourceConstants.RV_CAP_SESSION, abstractSmash, (Object[][]) null);
                        completeAdapterCap();
                    } else if (this.mDailyCappingManager.isCapped(abstractSmash)) {
                        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);
                        logProviderEvent(IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED, abstractSmash, new Object[][]{new Object[]{"status", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE}});
                        completeAdapterCap();
                    } else if (abstractSmash.isExhausted()) {
                        loadNextAdapter();
                        completeIterationRound();
                    }
                    return;
                }
                onRewardedVideoAvailabilityChanged(false, (RewardedVideoSmash) abstractSmash);
                Exception exc = new Exception("FailedToShowVideoException");
                this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.INTERNAL, abstractSmash.getInstanceName() + " Failed to show video", exc);
            } else if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY) {
                i++;
            } else if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                i2++;
            }
        }
        if (isBackFillAvailable()) {
            showAdapter(getBackfillSmash(), this.mSmashArray.size());
        } else if (i + i2 == this.mSmashArray.size()) {
            this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        }
    }

    public synchronized boolean isRewardedVideoAvailable() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":isRewardedVideoAvailable()", 1);
        if (this.mPauseSmartLoadDueToNetworkUnavailability) {
            return false;
        }
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.isMediationAvailable() && ((RewardedVideoSmash) abstractSmash).isRewardedVideoAvailable()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener
    public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError, RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdShowFailed(" + ironSourceError + ")", 1);
        logProviderEvent(IronSourceConstants.RV_INSTANCE_SHOW_FAILED, rewardedVideoSmash, new Object[][]{new Object[]{"placement", this.mCurrentPlacement.getPlacementName()}, new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}});
        sendMediationLoadEvents();
        this.mListenersWrapper.onRewardedVideoAdShowFailed(ironSourceError);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener
    public void onRewardedVideoAdOpened(RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdOpened()", 1);
        logProviderEvent(1005, rewardedVideoSmash, new Object[][]{new Object[]{"placement", this.mCurrentPlacement.getPlacementName()}});
        this.mListenersWrapper.onRewardedVideoAdOpened();
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener
    public void onRewardedVideoAdClosed(RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdClosed()", 1);
        verifyOnPauseOnResume();
        logProviderEvent(IronSourceConstants.RV_INSTANCE_CLOSED, rewardedVideoSmash, new Object[][]{new Object[]{"placement", this.mCurrentPlacement.getPlacementName()}});
        if (!rewardedVideoSmash.isCappedPerSession() && !this.mDailyCappingManager.isCapped(rewardedVideoSmash)) {
            logProviderEvent(1001, rewardedVideoSmash, (Object[][]) null);
        }
        sendMediationLoadEvents();
        this.mListenersWrapper.onRewardedVideoAdClosed();
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Fetch on ad closed, iterating on: " + abstractSmash.getInstanceName() + ", Status: " + abstractSmash.getMediationState(), 0);
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                try {
                    if (!abstractSmash.getInstanceName().equals(rewardedVideoSmash.getInstanceName())) {
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, abstractSmash.getInstanceName() + ":reload smash", 1);
                        ((RewardedVideoSmash) abstractSmash).fetchRewardedVideo();
                        logProviderEvent(1001, abstractSmash, (Object[][]) null);
                    }
                } catch (Throwable th) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, abstractSmash.getInstanceName() + " Failed to call fetchVideo(), " + th.getLocalizedMessage(), 1);
                }
            }
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener
    public synchronized void onRewardedVideoAvailabilityChanged(boolean z, RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ": onRewardedVideoAvailabilityChanged(available:" + z + ")", 1);
        if (this.mPauseSmartLoadDueToNetworkUnavailability) {
            return;
        }
        if (z && this.mShouldSendMediationLoadSuccessEvent) {
            this.mShouldSendMediationLoadSuccessEvent = false;
            logMediationEvent(1003, new Object[][]{new Object[]{"duration", Long.valueOf(new Date().getTime() - this.mLoadStartTime)}});
        }
        try {
            if (rewardedVideoSmash.equals(getBackfillSmash())) {
                if (shouldNotifyAvailabilityChanged(z)) {
                    this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
                }
                return;
            }
            if (rewardedVideoSmash.equals(getPremiumSmash())) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + " is a premium adapter, canShowPremium: " + canShowPremium(), 1);
                if (!canShowPremium()) {
                    rewardedVideoSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION);
                    if (shouldNotifyAvailabilityChanged(false)) {
                        this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
                    }
                    return;
                }
            }
            if (rewardedVideoSmash.isMediationAvailable() && !this.mDailyCappingManager.isCapped(rewardedVideoSmash)) {
                if (z) {
                    if (shouldNotifyAvailabilityChanged(true)) {
                        this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
                    }
                } else {
                    if (shouldNotifyAvailabilityChanged(false)) {
                        notifyAvailabilityChange();
                    }
                    loadNextAdapter();
                    completeIterationRound();
                }
            }
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onRewardedVideoAvailabilityChanged(available:" + z + ", provider:" + rewardedVideoSmash.getName() + ")", th);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener
    public void onRewardedVideoAdStarted(RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdStarted()", 1);
        logProviderEvent(IronSourceConstants.RV_INSTANCE_STARTED, rewardedVideoSmash, new Object[][]{new Object[]{"placement", this.mCurrentPlacement.getPlacementName()}});
        this.mListenersWrapper.onRewardedVideoAdStarted();
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener
    public void onRewardedVideoAdEnded(RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdEnded()", 1);
        logProviderEvent(IronSourceConstants.RV_INSTANCE_ENDED, rewardedVideoSmash, new Object[][]{new Object[]{"placement", this.mCurrentPlacement.getPlacementName()}});
        this.mListenersWrapper.onRewardedVideoAdEnded();
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener
    public void onRewardedVideoAdRewarded(RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdRewarded()", 1);
        if (this.mCurrentPlacement == null) {
            this.mCurrentPlacement = IronSourceObject.getInstance().getCurrentServerResponse().getConfigurations().getRewardedVideoConfigurations().getDefaultRewardedVideoPlacement();
        }
        JSONObject providerAdditionalData = IronSourceUtils.getProviderAdditionalData(rewardedVideoSmash);
        try {
            if (this.mCurrentPlacement != null) {
                providerAdditionalData.put("placement", this.mCurrentPlacement.getPlacementName());
                providerAdditionalData.put(IronSourceConstants.EVENTS_REWARD_NAME, this.mCurrentPlacement.getRewardName());
                providerAdditionalData.put(IronSourceConstants.EVENTS_REWARD_AMOUNT, this.mCurrentPlacement.getRewardAmount());
            } else {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "mCurrentPlacement is null", 3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        EventData eventData = new EventData(1010, providerAdditionalData);
        if (!TextUtils.isEmpty(this.mAppKey)) {
            eventData.addToAdditionalData(IronSourceConstants.EVENTS_TRANS_ID, IronSourceUtils.getTransId("" + Long.toString(eventData.getTimeStamp()) + this.mAppKey + rewardedVideoSmash.getName()));
            if (!TextUtils.isEmpty(IronSourceObject.getInstance().getDynamicUserId())) {
                eventData.addToAdditionalData(IronSourceConstants.EVENTS_DYNAMIC_USER_ID, IronSourceObject.getInstance().getDynamicUserId());
            }
            Map<String, String> rvServerParams = IronSourceObject.getInstance().getRvServerParams();
            if (rvServerParams != null) {
                for (String str : rvServerParams.keySet()) {
                    eventData.addToAdditionalData("custom_" + str, rvServerParams.get(str));
                }
            }
        }
        RewardedVideoEventsManager.getInstance().log(eventData);
        Placement placement = this.mCurrentPlacement;
        if (placement != null) {
            this.mListenersWrapper.onRewardedVideoAdRewarded(placement);
        } else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "mCurrentPlacement is null", 3);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener
    public void onRewardedVideoAdClicked(RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdClicked()", 1);
        if (this.mCurrentPlacement == null) {
            this.mCurrentPlacement = IronSourceObject.getInstance().getCurrentServerResponse().getConfigurations().getRewardedVideoConfigurations().getDefaultRewardedVideoPlacement();
        }
        Placement placement = this.mCurrentPlacement;
        if (placement == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "mCurrentPlacement is null", 3);
        } else {
            logProviderEvent(1006, rewardedVideoSmash, new Object[][]{new Object[]{"placement", placement.getPlacementName()}});
            this.mListenersWrapper.onRewardedVideoAdClicked(this.mCurrentPlacement);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoManagerListener
    public void onRewardedVideoAdVisible(RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, rewardedVideoSmash.getInstanceName() + ":onRewardedVideoAdVisible()", 1);
        Placement placement = this.mCurrentPlacement;
        if (placement != null) {
            logProviderEvent(IronSourceConstants.RV_INSTANCE_VISIBLE, rewardedVideoSmash, new Object[][]{new Object[]{"placement", placement.getPlacementName()}});
        } else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "mCurrentPlacement is null", 3);
        }
    }

    @Override // com.ironsource.environment.NetworkStateReceiver.NetworkStateReceiverListener
    public void onNetworkAvailabilityChanged(boolean z) {
        if (this.mShouldTrackNetworkState) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Network Availability Changed To: " + z, 0);
            if (shouldNotifyNetworkAvailabilityChanged(z)) {
                this.mPauseSmartLoadDueToNetworkUnavailability = !z;
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(z);
            }
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdUnitManager
    void shouldTrackNetworkState(Context context, boolean z) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, this.TAG + " Should Track Network State: " + z, 0);
        this.mShouldTrackNetworkState = z;
        if (this.mShouldTrackNetworkState) {
            if (this.mNetworkStateReceiver == null) {
                this.mNetworkStateReceiver = new NetworkStateReceiver(context, this);
            }
            context.getApplicationContext().registerReceiver(this.mNetworkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        } else if (this.mNetworkStateReceiver != null) {
            context.getApplicationContext().unregisterReceiver(this.mNetworkStateReceiver);
        }
    }

    private boolean shouldNotifyNetworkAvailabilityChanged(boolean z) {
        if (this.mLastMediationAvailabilityState == null) {
            return false;
        }
        if (z && !this.mLastMediationAvailabilityState.booleanValue() && hasAvailableSmash()) {
            this.mLastMediationAvailabilityState = true;
            return true;
        }
        if (z || !this.mLastMediationAvailabilityState.booleanValue()) {
            return false;
        }
        this.mLastMediationAvailabilityState = false;
        return true;
    }

    void setIsUltraEventsEnabled(boolean z) {
        this.mIsUltraEventsEnabled = z;
    }

    private void reportFalseImpressionsOnHigherPriority(int i, int i2) {
        for (int i3 = 0; i3 < i && i3 < this.mSmashArray.size(); i3++) {
            if (!this.mStatesToIgnore.contains(this.mSmashArray.get(i3).getMediationState())) {
                reportImpression(((RewardedVideoSmash) this.mSmashArray.get(i3)).getRequestUrl(), false, i2);
            }
        }
    }

    private synchronized void reportImpression(String str, boolean z, int i) {
        String str2 = "";
        try {
            str2 = ("" + str) + "&sdkVersion=" + IronSourceUtils.getSDKVersion();
            Server.callAsyncRequestURL(str2, z, i);
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.NETWORK, "reportImpression:(providerURL:" + str2 + ", hit:" + z + ")", th);
        }
    }

    void setCurrentPlacement(Placement placement) {
        this.mCurrentPlacement = placement;
        this.mListenersWrapper.setRvPlacement(placement.getPlacementName());
    }

    @Override // com.ironsource.mediationsdk.AbstractAdUnitManager
    protected synchronized void disablePremiumForCurrentSession() {
        super.disablePremiumForCurrentSession();
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.equals(getPremiumSmash())) {
                abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION);
                loadNextAdapter();
                break;
            }
        }
    }

    private synchronized AbstractAdapter startAdapter(RewardedVideoSmash rewardedVideoSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":startAdapter(" + rewardedVideoSmash.getInstanceName() + ")", 1);
        try {
            AbstractAdapter loadedAdapterOrFetchByReflection = getLoadedAdapterOrFetchByReflection(rewardedVideoSmash);
            if (loadedAdapterOrFetchByReflection == null) {
                return null;
            }
            IronSourceObject.getInstance().addToRVAdaptersList(loadedAdapterOrFetchByReflection);
            loadedAdapterOrFetchByReflection.setLogListener(this.mLoggerManager);
            rewardedVideoSmash.setAdapterForSmash(loadedAdapterOrFetchByReflection);
            rewardedVideoSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INITIATED);
            setCustomParams(rewardedVideoSmash);
            logProviderEvent(1001, rewardedVideoSmash, (Object[][]) null);
            rewardedVideoSmash.initRewardedVideo(this.mActivity, this.mAppKey, this.mUserId);
            return loadedAdapterOrFetchByReflection;
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":startAdapter(" + rewardedVideoSmash.getName() + ")", th);
            rewardedVideoSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INIT_FAILED);
            if (shouldNotifyAvailabilityChanged(false)) {
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ErrorBuilder.buildInitFailedError(rewardedVideoSmash.getName() + " initialization failed - please verify that required dependencies are in you build path.", IronSourceConstants.REWARDED_VIDEO_AD_UNIT).toString(), 2);
            return null;
        }
        throw th;
    }

    private AbstractAdapter loadNextAdapter() {
        AbstractAdapter abstractAdapterStartAdapter = null;
        int i = 0;
        for (int i2 = 0; i2 < this.mSmashArray.size() && abstractAdapterStartAdapter == null; i2++) {
            if (this.mSmashArray.get(i2).getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE || this.mSmashArray.get(i2).getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED) {
                i++;
                if (i >= this.mSmartLoadAmount) {
                    break;
                }
            } else if (this.mSmashArray.get(i2).getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_INITIATED && (abstractAdapterStartAdapter = startAdapter((RewardedVideoSmash) this.mSmashArray.get(i2))) == null) {
                this.mSmashArray.get(i2).setMediationState(AbstractSmash.MEDIATION_STATE.INIT_FAILED);
            }
        }
        return abstractAdapterStartAdapter;
    }

    private synchronized void showAdapter(AbstractSmash abstractSmash, int i) {
        CappingManager.incrementShowCounter(this.mActivity, this.mCurrentPlacement);
        if (CappingManager.isRvPlacementCapped(this.mActivity, this.mCurrentPlacement.getPlacementName())) {
            logMediationEvent(1400, new Object[][]{new Object[]{"placement", this.mCurrentPlacement.getPlacementName()}});
        }
        this.mDailyCappingManager.increaseShowCounter(abstractSmash);
        if (this.mCurrentPlacement != null) {
            if (this.mIsUltraEventsEnabled) {
                reportImpression(((RewardedVideoSmash) abstractSmash).getRequestUrl(), true, this.mCurrentPlacement.getPlacementId());
                reportFalseImpressionsOnHigherPriority(i, this.mCurrentPlacement.getPlacementId());
            }
            sendShowChanceEvents(abstractSmash, i, this.mCurrentPlacement.getPlacementName());
        } else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "mCurrentPlacement is null", 3);
        }
        logProviderEvent(IronSourceConstants.RV_INSTANCE_SHOW, abstractSmash, this.mCurrentPlacement != null ? new Object[][]{new Object[]{"placement", this.mCurrentPlacement.getPlacementName()}} : (Object[][]) null);
        ((RewardedVideoSmash) abstractSmash).showRewardedVideo();
    }

    void setManualLoadInterval(int i) {
        this.mManualLoadInterval = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleFetchTimer() {
        if (this.mManualLoadInterval <= 0) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "load interval is not set, ignoring", 1);
            return;
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
        }
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.RewardedVideoManager.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                cancel();
                RewardedVideoManager.this.loadRewardedVideo();
                RewardedVideoManager.this.scheduleFetchTimer();
            }
        }, this.mManualLoadInterval * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void loadRewardedVideo() {
        if (IronSourceUtils.isNetworkConnected(this.mActivity) && this.mLastMediationAvailabilityState != null) {
            if (!this.mLastMediationAvailabilityState.booleanValue()) {
                logMediationEvent(102);
                logMediationEvent(1000);
                this.mShouldSendMediationLoadSuccessEvent = true;
                for (AbstractSmash abstractSmash : this.mSmashArray) {
                    if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                        try {
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Fetch from timer: " + abstractSmash.getInstanceName() + ":reload smash", 1);
                            logProviderEvent(1001, abstractSmash, (Object[][]) null);
                            ((RewardedVideoSmash) abstractSmash).fetchRewardedVideo();
                        } catch (Throwable th) {
                            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, abstractSmash.getInstanceName() + " Failed to call fetchVideo(), " + th.getLocalizedMessage(), 1);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0054  */
    private synchronized boolean shouldNotifyAvailabilityChanged(boolean z) {
        boolean z2;
        z2 = true;
        if (this.mLastMediationAvailabilityState == null) {
            scheduleFetchTimer();
            if (z) {
                this.mLastMediationAvailabilityState = true;
            } else if (isBackFillAvailable() || !isAllAdaptersInactive()) {
                z2 = false;
            } else {
                this.mLastMediationAvailabilityState = false;
            }
        } else if (z && !this.mLastMediationAvailabilityState.booleanValue()) {
            this.mLastMediationAvailabilityState = true;
        } else if (z || !this.mLastMediationAvailabilityState.booleanValue() || hasAvailableSmash() || isBackFillAvailable()) {
            z2 = false;
        } else {
            this.mLastMediationAvailabilityState = false;
        }
        return z2;
    }

    private synchronized boolean isAllAdaptersInactive() {
        int i;
        i = 0;
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INIT_FAILED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.EXHAUSTED) {
                i++;
            }
        }
        return this.mSmashArray.size() == i;
    }

    private synchronized boolean isAvailableAdaptersToLoad() {
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INIT_PENDING || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.LOAD_PENDING) {
                return true;
            }
        }
        return false;
    }

    private synchronized boolean hasAvailableSmash() {
        boolean z;
        z = false;
        Iterator<AbstractSmash> it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            if (it.next().getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                z = true;
                break;
            }
        }
        return z;
    }

    private synchronized boolean isBackFillAvailable() {
        if (getBackfillSmash() == null) {
            return false;
        }
        return ((RewardedVideoSmash) getBackfillSmash()).isRewardedVideoAvailable();
    }

    private void sendShowChanceEvents(AbstractSmash abstractSmash, int i, String str) {
        logProviderEvent(IronSourceConstants.RV_INSTANCE_SHOW_CHANCE, abstractSmash, new Object[][]{new Object[]{"placement", str}, new Object[]{"status", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE}});
        for (int i2 = 0; i2 < this.mSmashArray.size() && i2 < i; i2++) {
            AbstractSmash abstractSmash2 = this.mSmashArray.get(i2);
            if (abstractSmash2.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                logProviderEvent(IronSourceConstants.RV_INSTANCE_SHOW_CHANCE, abstractSmash2, new Object[][]{new Object[]{"placement", str}, new Object[]{"status", "false"}});
            }
        }
    }

    private synchronized void notifyAvailabilityChange() {
        if (getBackfillSmash() != null && !this.mBackFillInitStarted) {
            this.mBackFillInitStarted = true;
            if (startAdapter((RewardedVideoSmash) getBackfillSmash()) == null) {
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
            }
        } else if (!isBackFillAvailable() || shouldNotifyAvailabilityChanged(true)) {
            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
        }
    }

    private synchronized void completeAdapterCap() {
        if (loadNextAdapter() != null) {
            return;
        }
        if (smashesCount(AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE, AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION, AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY) < this.mSmashArray.size()) {
            completeIterationRound();
        } else {
            if (shouldNotifyAvailabilityChanged(false)) {
                notifyAvailabilityChange();
            }
        }
    }

    private synchronized void completeIterationRound() {
        if (isIterationRoundComplete()) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Reset Iteration", 0);
            boolean z = false;
            for (AbstractSmash abstractSmash : this.mSmashArray) {
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.EXHAUSTED) {
                    abstractSmash.completeIteration();
                }
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                    z = true;
                }
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "End of Reset Iteration", 0);
            if (shouldNotifyAvailabilityChanged(z)) {
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(this.mLastMediationAvailabilityState.booleanValue());
            }
        }
    }

    private synchronized boolean isIterationRoundComplete() {
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_INITIATED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                return false;
            }
        }
        return true;
    }

    private void logMediationEvent(int i) {
        logMediationEvent(i, (Object[][]) null);
    }

    private void logMediationEvent(int i, Object[][] objArr) {
        JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        if (objArr != null) {
            try {
                for (Object[] objArr2 : objArr) {
                    mediationAdditionalData.put(objArr2[0].toString(), objArr2[1]);
                }
            } catch (Exception e) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "RewardedVideoManager logMediationEvent " + Log.getStackTraceString(e), 3);
            }
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(i, mediationAdditionalData));
    }

    private void logProviderEvent(int i, AbstractSmash abstractSmash, Object[][] objArr) {
        JSONObject providerAdditionalData = IronSourceUtils.getProviderAdditionalData(abstractSmash);
        if (objArr != null) {
            try {
                for (Object[] objArr2 : objArr) {
                    providerAdditionalData.put(objArr2[0].toString(), objArr2[1]);
                }
            } catch (Exception e) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "RewardedVideoManager logProviderEvent " + Log.getStackTraceString(e), 3);
            }
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(i, providerAdditionalData));
    }

    private int smashesCount(AbstractSmash.MEDIATION_STATE... mediation_stateArr) {
        int i;
        synchronized (this.mSmashArray) {
            i = 0;
            for (AbstractSmash abstractSmash : this.mSmashArray) {
                int i2 = i;
                for (AbstractSmash.MEDIATION_STATE mediation_state : mediation_stateArr) {
                    if (abstractSmash.getMediationState() == mediation_state) {
                        i2++;
                    }
                }
                i = i2;
            }
        }
        return i;
    }

    private void sendMediationLoadEvents() {
        if (isRewardedVideoAvailable()) {
            logMediationEvent(1000);
            logMediationEvent(1003, new Object[][]{new Object[]{"duration", 0}});
            this.mShouldSendMediationLoadSuccessEvent = false;
        } else if (isAvailableAdaptersToLoad()) {
            logMediationEvent(1000);
            this.mShouldSendMediationLoadSuccessEvent = true;
            this.mLoadStartTime = new Date().getTime();
        }
    }

    @Override // com.ironsource.mediationsdk.utils.DailyCappingListener
    public void onDailyCapReleased() {
        boolean z = false;
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY) {
                logProviderEvent(IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED, abstractSmash, new Object[][]{new Object[]{"status", "false"}});
                abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE);
                if (((RewardedVideoSmash) abstractSmash).isRewardedVideoAvailable() && abstractSmash.isMediationAvailable()) {
                    abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.AVAILABLE);
                    z = true;
                }
            }
        }
        if (z && shouldNotifyAvailabilityChanged(true)) {
            this.mListenersWrapper.onRewardedVideoAvailabilityChanged(true);
        }
    }
}
