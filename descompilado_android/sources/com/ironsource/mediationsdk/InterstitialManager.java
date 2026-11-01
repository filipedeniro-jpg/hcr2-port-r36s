package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.sdk.InterstitialManagerListener;
import com.ironsource.mediationsdk.sdk.ListenersWrapper;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialApi;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialManagerListener;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.utils.DailyCappingListener;
import com.ironsource.mediationsdk.utils.DailyCappingManager;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.my.target.aa;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class InterstitialManager extends AbstractAdUnitManager implements InterstitialManagerListener, MediationInitializer.OnMediationInitializationListener, RewardedInterstitialManagerListener, RewardedInterstitialApi, DailyCappingListener {
    private InterstitialPlacement mCurrentPlacement;
    private boolean mDidFinishToInitInterstitial;
    private ListenersWrapper mInterstitialListenersWrapper;
    private boolean mIsCurrentlyShowing;
    private long mLoadStartTime;
    private RewardedInterstitialListener mRewardedInterstitialListenerWrapper;
    private final String TAG = getClass().getName();
    private CopyOnWriteArraySet<String> mInstancesToLoad = new CopyOnWriteArraySet<>();
    private Map<String, InterstitialSmash> mInstanceIdToSmashMap = new ConcurrentHashMap();
    private CallbackThrottler mCallbackThrottler = CallbackThrottler.getInstance();
    private boolean mShouldSendAdReadyEvent = false;
    private boolean mIsLoadInterstitialInProgress = false;
    private boolean mDidCallLoadInterstitial = false;

    @Override // com.ironsource.mediationsdk.MediationInitializer.OnMediationInitializationListener
    public void onInitSuccess(List<IronSource.AD_UNIT> list, boolean z) {
    }

    InterstitialManager() {
        this.mDailyCappingManager = new DailyCappingManager(aa.e.bo, this);
        this.mIsCurrentlyShowing = false;
    }

    public void setInterstitialListener(ListenersWrapper listenersWrapper) {
        this.mInterstitialListenersWrapper = listenersWrapper;
        this.mCallbackThrottler.setInterstitialListener(listenersWrapper);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedInterstitialApi
    public void setRewardedInterstitialListener(RewardedInterstitialListener rewardedInterstitialListener) {
        this.mRewardedInterstitialListenerWrapper = rewardedInterstitialListener;
    }

    public synchronized void initInterstitial(Activity activity, String str, String str2) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":initInterstitial(appKey: " + str + ", userId: " + str2 + ")", 1);
        this.mAppKey = str;
        this.mUserId = str2;
        this.mActivity = activity;
        this.mDailyCappingManager.setContext(this.mActivity);
        int i = 0;
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (this.mDailyCappingManager.shouldSendCapReleasedEvent(abstractSmash)) {
                logProviderEvent(250, abstractSmash, new Object[][]{new Object[]{"status", "false"}});
            }
            if (this.mDailyCappingManager.isCapped(abstractSmash)) {
                abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);
                i++;
            }
        }
        if (i == this.mSmashArray.size()) {
            this.mDidFinishToInitInterstitial = true;
        }
        for (int i2 = 0; i2 < this.mSmartLoadAmount && startNextAdapter() != null; i2++) {
        }
    }

    public synchronized void loadInterstitial() {
        try {
            if (this.mIsCurrentlyShowing) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadInterstitial() cannot be invoked while showing", 3);
                return;
            }
            this.mCurrentPlacement = null;
            this.mInterstitialListenersWrapper.setInterstitialPlacement(null);
            if (!this.mIsLoadInterstitialInProgress && !this.mCallbackThrottler.hasPendingInvocation()) {
                MediationInitializer.EInitStatus currentInitStatus = MediationInitializer.getInstance().getCurrentInitStatus();
                if (currentInitStatus == MediationInitializer.EInitStatus.NOT_INIT) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() must be called before loadInterstitial()", 3);
                    return;
                }
                if (currentInitStatus == MediationInitializer.EInitStatus.INIT_IN_PROGRESS) {
                    if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                        this.mCallbackThrottler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                    } else {
                        this.mLoadStartTime = new Date().getTime();
                        logMediationEvent(2001, (Object[][]) null);
                        this.mDidCallLoadInterstitial = true;
                        this.mShouldSendAdReadyEvent = true;
                    }
                    return;
                }
                if (currentInitStatus == MediationInitializer.EInitStatus.INIT_FAILED) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                    this.mCallbackThrottler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                    return;
                }
                if (this.mSmashArray.size() == 0) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "the server response does not contain interstitial data", 3);
                    this.mCallbackThrottler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("the server response does not contain interstitial data", "Interstitial"));
                    return;
                }
                this.mLoadStartTime = new Date().getTime();
                logMediationEvent(2001, (Object[][]) null);
                this.mShouldSendAdReadyEvent = true;
                changeStateToInitiated();
                if (smashesCount(AbstractSmash.MEDIATION_STATE.INITIATED) == 0) {
                    if (!this.mDidFinishToInitInterstitial) {
                        this.mDidCallLoadInterstitial = true;
                        return;
                    }
                    IronSourceError ironSourceErrorBuildGenericError = ErrorBuilder.buildGenericError("no ads to load");
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ironSourceErrorBuildGenericError.getErrorMessage(), 1);
                    this.mCallbackThrottler.onInterstitialAdLoadFailed(ironSourceErrorBuildGenericError);
                    logMediationEvent(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceErrorBuildGenericError.getErrorCode())}});
                    this.mShouldSendAdReadyEvent = false;
                    return;
                }
                this.mDidCallLoadInterstitial = true;
                this.mIsLoadInterstitialInProgress = true;
                int i = 0;
                for (AbstractSmash abstractSmash : this.mSmashArray) {
                    if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED) {
                        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.LOAD_PENDING);
                        loadAdapterAndSendEvent((InterstitialSmash) abstractSmash);
                        i++;
                        if (i >= this.mSmartLoadAmount) {
                            return;
                        }
                    }
                }
                return;
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Load Interstitial is already in progress", 3);
        } catch (Exception e) {
            e.printStackTrace();
            IronSourceError ironSourceErrorBuildLoadFailedError = ErrorBuilder.buildLoadFailedError("loadInterstitial exception " + e.getMessage());
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ironSourceErrorBuildLoadFailedError.getErrorMessage(), 3);
            this.mCallbackThrottler.onInterstitialAdLoadFailed(ironSourceErrorBuildLoadFailedError);
            if (this.mShouldSendAdReadyEvent) {
                this.mShouldSendAdReadyEvent = false;
                logMediationEvent(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceErrorBuildLoadFailedError.getErrorCode())}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, e.getMessage().substring(0, Math.min(e.getMessage().length(), 39))}});
            }
        }
    }

    public void showInterstitial(String str) {
        if (this.mIsCurrentlyShowing) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "showInterstitial() cannot be invoked while showing", 3);
            return;
        }
        if (this.mShouldTrackNetworkState && this.mActivity != null && !IronSourceUtils.isNetworkConnected(this.mActivity)) {
            this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildNoInternetConnectionShowFailError("Interstitial"));
            return;
        }
        if (!this.mDidCallLoadInterstitial) {
            this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildShowFailedError("Interstitial", "showInterstitial failed - You need to load interstitial before showing it"));
            return;
        }
        for (int i = 0; i < this.mSmashArray.size(); i++) {
            AbstractSmash abstractSmash = this.mSmashArray.get(i);
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                CappingManager.incrementShowCounter(this.mActivity, this.mCurrentPlacement);
                if (CappingManager.isPlacementCapped(this.mActivity, this.mCurrentPlacement) != CappingManager.ECappingStatus.NOT_CAPPED) {
                    logMediationEventWithPlacement(IronSourceConstants.IS_CAP_PLACEMENT, (Object[][]) null);
                }
                logProviderEventWithPlacement(IronSourceConstants.IS_INSTANCE_SHOW, abstractSmash, (Object[][]) null);
                this.mIsCurrentlyShowing = true;
                ((InterstitialSmash) abstractSmash).showInterstitial();
                if (abstractSmash.isCappedPerSession()) {
                    logProviderEvent(IronSourceConstants.IS_CAP_SESSION, abstractSmash);
                }
                this.mDailyCappingManager.increaseShowCounter(abstractSmash);
                if (this.mDailyCappingManager.isCapped(abstractSmash)) {
                    abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY);
                    logProviderEvent(250, abstractSmash, new Object[][]{new Object[]{"status", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE}});
                }
                this.mDidCallLoadInterstitial = false;
                if (abstractSmash.isMediationAvailable()) {
                    return;
                }
                startNextAdapter();
                return;
            }
        }
        this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildShowFailedError("Interstitial", "showInterstitial failed - No adapters ready to show"));
    }

    public synchronized boolean isInterstitialReady() {
        if (this.mShouldTrackNetworkState && this.mActivity != null && !IronSourceUtils.isNetworkConnected(this.mActivity)) {
            return false;
        }
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE && ((InterstitialSmash) abstractSmash).isInterstitialReady()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialManagerListener
    public synchronized void onInterstitialInitSuccess(InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + " :onInterstitialInitSuccess()", 1);
        logProviderEvent(IronSourceConstants.IS_INSTANCE_INIT_SUCCESS, interstitialSmash);
        this.mDidFinishToInitInterstitial = true;
        if (this.mDidCallLoadInterstitial && smashesCount(AbstractSmash.MEDIATION_STATE.AVAILABLE, AbstractSmash.MEDIATION_STATE.LOAD_PENDING) < this.mSmartLoadAmount) {
            interstitialSmash.setMediationState(AbstractSmash.MEDIATION_STATE.LOAD_PENDING);
            loadAdapterAndSendEvent(interstitialSmash);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialManagerListener
    public synchronized void onInterstitialInitFailed(IronSourceError ironSourceError, InterstitialSmash interstitialSmash) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialInitFailed(" + ironSourceError + ")", 1);
            logProviderEvent(IronSourceConstants.IS_INSTANCE_INIT_FAILED, interstitialSmash, new Object[][]{new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}});
            if (smashesCount(AbstractSmash.MEDIATION_STATE.INIT_FAILED) >= this.mSmashArray.size()) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, "Smart Loading - initialization failed - no adapters are initiated and no more left to init, error: " + ironSourceError.getErrorMessage(), 2);
                if (this.mDidCallLoadInterstitial) {
                    this.mCallbackThrottler.onInterstitialAdLoadFailed(ErrorBuilder.buildGenericError("no ads to show"));
                    logMediationEvent(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_CODE_GENERIC)}});
                    this.mShouldSendAdReadyEvent = false;
                }
                this.mDidFinishToInitInterstitial = true;
            } else {
                if (startNextAdapter() == null && this.mDidCallLoadInterstitial && smashesCount(AbstractSmash.MEDIATION_STATE.INIT_FAILED, AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE, AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION, AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY, AbstractSmash.MEDIATION_STATE.EXHAUSTED) >= this.mSmashArray.size()) {
                    this.mCallbackThrottler.onInterstitialAdLoadFailed(new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, "No ads to show"));
                    logMediationEvent(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW)}});
                    this.mShouldSendAdReadyEvent = false;
                }
                completeIterationRound();
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onInterstitialInitFailed(error:" + ironSourceError + ", provider:" + interstitialSmash.getName() + ")", e);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialManagerListener
    public synchronized void onInterstitialAdReady(InterstitialSmash interstitialSmash, long j) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdReady()", 1);
        logProviderEvent(2003, interstitialSmash, new Object[][]{new Object[]{"duration", Long.valueOf(j)}});
        long time = new Date().getTime() - this.mLoadStartTime;
        interstitialSmash.setMediationState(AbstractSmash.MEDIATION_STATE.AVAILABLE);
        this.mIsLoadInterstitialInProgress = false;
        if (this.mShouldSendAdReadyEvent) {
            this.mShouldSendAdReadyEvent = false;
            this.mInterstitialListenersWrapper.onInterstitialAdReady();
            logMediationEvent(2004, new Object[][]{new Object[]{"duration", Long.valueOf(time)}});
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialManagerListener
    public synchronized void onInterstitialAdLoadFailed(IronSourceError ironSourceError, InterstitialSmash interstitialSmash, long j) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdLoadFailed(" + ironSourceError + ")", 1);
        logProviderEvent(IronSourceConstants.IS_INSTANCE_LOAD_FAILED, interstitialSmash, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39))}, new Object[]{"duration", Long.valueOf(j)}});
        interstitialSmash.setMediationState(AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE);
        int iSmashesCount = smashesCount(AbstractSmash.MEDIATION_STATE.AVAILABLE, AbstractSmash.MEDIATION_STATE.LOAD_PENDING);
        if (iSmashesCount >= this.mSmartLoadAmount) {
            return;
        }
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED) {
                abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.LOAD_PENDING);
                loadAdapterAndSendEvent((InterstitialSmash) abstractSmash);
                return;
            }
        }
        if (startNextAdapter() != null) {
            return;
        }
        if (this.mDidCallLoadInterstitial && iSmashesCount + smashesCount(AbstractSmash.MEDIATION_STATE.INIT_PENDING) == 0) {
            completeIterationRound();
            this.mIsLoadInterstitialInProgress = false;
            this.mCallbackThrottler.onInterstitialAdLoadFailed(new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, "No ads to show"));
            logMediationEvent(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW)}});
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialManagerListener
    public void onInterstitialAdOpened(InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdOpened()", 1);
        logProviderEventWithPlacement(IronSourceConstants.IS_INSTANCE_OPENED, interstitialSmash, (Object[][]) null);
        this.mInterstitialListenersWrapper.onInterstitialAdOpened();
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialManagerListener
    public void onInterstitialAdClosed(InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdClosed()", 1);
        verifyOnPauseOnResume();
        logProviderEventWithPlacement(IronSourceConstants.IS_INSTANCE_CLOSED, interstitialSmash, (Object[][]) null);
        this.mInterstitialListenersWrapper.onInterstitialAdClosed();
        this.mIsCurrentlyShowing = false;
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialManagerListener
    public void onInterstitialAdShowSucceeded(InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdShowSucceeded()", 1);
        logProviderEventWithPlacement(IronSourceConstants.IS_INSTANCE_SHOW_SUCCESS, interstitialSmash, (Object[][]) null);
        boolean z = false;
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                completeAdapterShow(abstractSmash);
                z = true;
            }
        }
        if (!z && (interstitialSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION || interstitialSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.EXHAUSTED || interstitialSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY)) {
            completeIterationRound();
        }
        changeStateToInitiated();
        this.mInterstitialListenersWrapper.onInterstitialAdShowSucceeded();
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialManagerListener
    public void onInterstitialAdShowFailed(IronSourceError ironSourceError, InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdShowFailed(" + ironSourceError + ")", 1);
        logProviderEventWithPlacement(IronSourceConstants.IS_INSTANCE_SHOW_FAILED, interstitialSmash, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}});
        completeAdapterShow(interstitialSmash);
        Iterator<AbstractSmash> it = this.mSmashArray.iterator();
        while (it.hasNext()) {
            if (it.next().getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                this.mDidCallLoadInterstitial = true;
                showInterstitial(this.mCurrentPlacement.getPlacementName());
                return;
            }
        }
        this.mInterstitialListenersWrapper.onInterstitialAdShowFailed(ironSourceError);
        this.mIsCurrentlyShowing = false;
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialManagerListener
    public void onInterstitialAdClicked(InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdClicked()", 1);
        logProviderEventWithPlacement(2006, interstitialSmash, (Object[][]) null);
        this.mInterstitialListenersWrapper.onInterstitialAdClicked();
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialManagerListener
    public void onInterstitialAdVisible(InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, interstitialSmash.getInstanceName() + ":onInterstitialAdVisible()", 1);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedInterstitialManagerListener
    public void onInterstitialAdRewarded(InterstitialSmash interstitialSmash) {
        logProviderEvent(IronSourceConstants.INTERSTITIAL_AD_REWARDED, interstitialSmash, (Object[][]) null);
        RewardedInterstitialListener rewardedInterstitialListener = this.mRewardedInterstitialListenerWrapper;
        if (rewardedInterstitialListener != null) {
            rewardedInterstitialListener.onInterstitialAdRewarded();
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdUnitManager
    void shouldTrackNetworkState(Context context, boolean z) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, this.TAG + " Should Track Network State: " + z, 0);
        this.mShouldTrackNetworkState = z;
    }

    @Override // com.ironsource.mediationsdk.MediationInitializer.OnMediationInitializationListener
    public void onInitFailed(String str) {
        if (this.mDidCallLoadInterstitial) {
            this.mCallbackThrottler.onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
            this.mDidCallLoadInterstitial = false;
            this.mIsLoadInterstitialInProgress = false;
        }
    }

    @Override // com.ironsource.mediationsdk.MediationInitializer.OnMediationInitializationListener
    public void onStillInProgressAfter15Secs() {
        if (this.mDidCallLoadInterstitial) {
            IronSourceError ironSourceErrorBuildInitFailedError = ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial");
            this.mCallbackThrottler.onInterstitialAdLoadFailed(ironSourceErrorBuildInitFailedError);
            this.mDidCallLoadInterstitial = false;
            this.mIsLoadInterstitialInProgress = false;
            if (this.mShouldSendAdReadyEvent) {
                logMediationEvent(IronSourceConstants.IS_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceErrorBuildInitFailedError.getErrorCode())}});
                this.mShouldSendAdReadyEvent = false;
            }
        }
    }

    private boolean isIterationRoundComplete() {
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_INITIATED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INIT_PENDING || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.LOAD_PENDING || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE) {
                return false;
            }
        }
        return true;
    }

    private void completeIterationRound() {
        if (isIterationRoundComplete()) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Reset Iteration", 0);
            for (AbstractSmash abstractSmash : this.mSmashArray) {
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.EXHAUSTED) {
                    abstractSmash.completeIteration();
                }
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "End of Reset Iteration", 0);
        }
    }

    private void completeAdapterShow(AbstractSmash abstractSmash) {
        if (!abstractSmash.isMediationAvailable()) {
            startNextAdapter();
            completeIterationRound();
        } else {
            abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INITIATED);
        }
    }

    private AbstractAdapter startNextAdapter() {
        AbstractAdapter abstractAdapterStartAdapter = null;
        int i = 0;
        for (int i2 = 0; i2 < this.mSmashArray.size() && abstractAdapterStartAdapter == null; i2++) {
            if (this.mSmashArray.get(i2).getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE || this.mSmashArray.get(i2).getMediationState() == AbstractSmash.MEDIATION_STATE.INITIATED || this.mSmashArray.get(i2).getMediationState() == AbstractSmash.MEDIATION_STATE.INIT_PENDING || this.mSmashArray.get(i2).getMediationState() == AbstractSmash.MEDIATION_STATE.LOAD_PENDING) {
                i++;
                if (i >= this.mSmartLoadAmount) {
                    break;
                }
            } else if (this.mSmashArray.get(i2).getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_INITIATED && (abstractAdapterStartAdapter = startAdapter((InterstitialSmash) this.mSmashArray.get(i2))) == null) {
                this.mSmashArray.get(i2).setMediationState(AbstractSmash.MEDIATION_STATE.INIT_FAILED);
            }
        }
        return abstractAdapterStartAdapter;
    }

    private synchronized AbstractAdapter startAdapter(InterstitialSmash interstitialSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":startAdapter(" + interstitialSmash.getName() + ")", 1);
        try {
            AbstractAdapter loadedAdapterOrFetchByReflection = getLoadedAdapterOrFetchByReflection(interstitialSmash);
            if (loadedAdapterOrFetchByReflection == null) {
                return null;
            }
            IronSourceObject.getInstance().addToISAdaptersList(loadedAdapterOrFetchByReflection);
            loadedAdapterOrFetchByReflection.setLogListener(this.mLoggerManager);
            interstitialSmash.setAdapterForSmash(loadedAdapterOrFetchByReflection);
            interstitialSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INIT_PENDING);
            if (this.mRewardedInterstitialListenerWrapper != null) {
                interstitialSmash.setRewardedInterstitialManagerListener(this);
            }
            setCustomParams(interstitialSmash);
            interstitialSmash.initInterstitial(this.mActivity, this.mAppKey, this.mUserId);
            return loadedAdapterOrFetchByReflection;
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":startAdapter(" + interstitialSmash.getName() + ")", th);
            interstitialSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INIT_FAILED);
            StringBuilder sb = new StringBuilder();
            sb.append(interstitialSmash.getName());
            sb.append(" initialization failed - please verify that required dependencies are in you build path.");
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ErrorBuilder.buildInitFailedError(sb.toString(), "Interstitial").toString(), 2);
            return null;
        }
        throw th;
    }

    void setCurrentPlacement(InterstitialPlacement interstitialPlacement) {
        this.mCurrentPlacement = interstitialPlacement;
        this.mInterstitialListenersWrapper.setInterstitialPlacement(interstitialPlacement);
    }

    private synchronized void loadAdapterAndSendEvent(InterstitialSmash interstitialSmash) {
        logProviderEvent(2002, interstitialSmash, (Object[][]) null);
        interstitialSmash.loadInterstitial();
    }

    private synchronized void changeStateToInitiatedForInstanceId(String str) {
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getSubProviderId().equals(str) && (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.LOAD_PENDING || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE)) {
                abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INITIATED);
                break;
            }
        }
    }

    private synchronized void changeStateToInitiated() {
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.AVAILABLE || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.LOAD_PENDING || abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.NOT_AVAILABLE) {
                abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INITIATED);
            }
        }
    }

    private void logMediationEvent(int i) {
        logMediationEvent(i, (Object[][]) null);
    }

    private void logMediationEvent(int i, Object[][] objArr) {
        logMediationEvent(i, objArr, false);
    }

    private void logMediationEventWithPlacement(int i, Object[][] objArr) {
        logMediationEvent(i, objArr, true);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0027 A[Catch: Exception -> 0x0023, TryCatch #0 {Exception -> 0x0023, blocks: (B:4:0x0007, B:6:0x000b, B:8:0x0017, B:12:0x0027, B:14:0x002b), top: B:19:0x0007 }] */
    /* JADX WARN: Code duplicated, block: B:14:0x002b A[Catch: Exception -> 0x0023, TRY_LEAVE, TryCatch #0 {Exception -> 0x0023, blocks: (B:4:0x0007, B:6:0x000b, B:8:0x0017, B:12:0x0027, B:14:0x002b), top: B:19:0x0007 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:12:0x0027, please report this as an issue */
    private void logMediationEvent(int i, Object[][] objArr, boolean z) {
        int i2;
        JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        if (z) {
            try {
                if (this.mCurrentPlacement != null && !TextUtils.isEmpty(this.mCurrentPlacement.getPlacementName())) {
                    mediationAdditionalData.put("placement", this.mCurrentPlacement.getPlacementName());
                }
                if (objArr != null) {
                    for (Object[] objArr2 : objArr) {
                        mediationAdditionalData.put(objArr2[0].toString(), objArr2[1]);
                    }
                }
            } catch (Exception e) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "InterstitialManager logMediationEvent " + Log.getStackTraceString(e), 3);
            }
        } else if (objArr != null) {
            while (i2 < r9) {
                mediationAdditionalData.put(objArr2[0].toString(), objArr2[1]);
            }
        }
        InterstitialEventsManager.getInstance().log(new EventData(i, mediationAdditionalData));
    }

    private void logProviderEvent(int i, AbstractSmash abstractSmash) {
        logProviderEvent(i, abstractSmash, (Object[][]) null);
    }

    private void logProviderEvent(int i, AbstractSmash abstractSmash, Object[][] objArr) {
        logProviderEvent(i, abstractSmash, objArr, false);
    }

    private void logProviderEventWithPlacement(int i, AbstractSmash abstractSmash, Object[][] objArr) {
        logProviderEvent(i, abstractSmash, objArr, true);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[Catch: Exception -> 0x0022, TryCatch #0 {Exception -> 0x0022, blocks: (B:4:0x0006, B:6:0x000a, B:8:0x0016, B:12:0x0026, B:14:0x002b), top: B:19:0x0006 }] */
    /* JADX WARN: Code duplicated, block: B:14:0x002b A[Catch: Exception -> 0x0022, TRY_LEAVE, TryCatch #0 {Exception -> 0x0022, blocks: (B:4:0x0006, B:6:0x000a, B:8:0x0016, B:12:0x0026, B:14:0x002b), top: B:19:0x0006 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:12:0x0026, please report this as an issue */
    private void logProviderEvent(int i, AbstractSmash abstractSmash, Object[][] objArr, boolean z) {
        int i2;
        JSONObject providerAdditionalData = IronSourceUtils.getProviderAdditionalData(abstractSmash);
        if (z) {
            try {
                if (this.mCurrentPlacement != null && !TextUtils.isEmpty(this.mCurrentPlacement.getPlacementName())) {
                    providerAdditionalData.put("placement", this.mCurrentPlacement.getPlacementName());
                }
                if (objArr != null) {
                    for (Object[] objArr2 : objArr) {
                        providerAdditionalData.put(objArr2[0].toString(), objArr2[1]);
                    }
                }
            } catch (Exception e) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "InterstitialManager logProviderEvent " + Log.getStackTraceString(e), 3);
            }
        } else if (objArr != null) {
            while (i2 < r9) {
                providerAdditionalData.put(objArr2[0].toString(), objArr2[1]);
            }
        }
        InterstitialEventsManager.getInstance().log(new EventData(i, providerAdditionalData));
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

    @Override // com.ironsource.mediationsdk.utils.DailyCappingListener
    public void onDailyCapReleased() {
        if (this.mSmashArray != null) {
            for (AbstractSmash abstractSmash : this.mSmashArray) {
                if (abstractSmash.getMediationState() == AbstractSmash.MEDIATION_STATE.CAPPED_PER_DAY) {
                    logProviderEvent(250, abstractSmash, new Object[][]{new Object[]{"status", "false"}});
                    if (abstractSmash.isCappedPerSession()) {
                        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION);
                    } else if (abstractSmash.isExhausted()) {
                        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.EXHAUSTED);
                    } else {
                        abstractSmash.setMediationState(AbstractSmash.MEDIATION_STATE.INITIATED);
                    }
                }
            }
        }
    }

    public void setDelayLoadFailureNotificationInSeconds(int i) {
        this.mCallbackThrottler.setDelayLoadFailureNotificationInSeconds(i);
    }
}
