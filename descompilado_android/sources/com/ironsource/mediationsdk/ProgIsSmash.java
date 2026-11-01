package com.ironsource.mediationsdk;

import android.app.Activity;
import android.text.TextUtils;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.AdapterConfig;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class ProgIsSmash extends ProgSmash implements InterstitialSmashListener {
    private Activity mActivity;
    private String mAppKey;
    private ProgIsManagerListener mListener;
    private long mLoadStartTime;
    private int mLoadTimeoutSecs;
    private SMASH_STATE mState;
    private Timer mTimer;
    private String mUserId;

    protected enum SMASH_STATE {
        NO_INIT,
        INIT_IN_PROGRESS,
        INIT_SUCCESS,
        LOAD_IN_PROGRESS,
        LOADED,
        LOAD_FAILED
    }

    public ProgIsSmash(Activity activity, String str, String str2, ProviderSettings providerSettings, ProgIsManagerListener progIsManagerListener, int i, AbstractAdapter abstractAdapter) {
        super(new AdapterConfig(providerSettings, providerSettings.getInterstitialSettings()), abstractAdapter);
        this.mState = SMASH_STATE.NO_INIT;
        this.mActivity = activity;
        this.mAppKey = str;
        this.mUserId = str2;
        this.mListener = progIsManagerListener;
        this.mTimer = null;
        this.mLoadTimeoutSecs = i;
        this.mAdapter.addInterstitialListener(this);
    }

    public synchronized Map<String, Object> getBiddingData() {
        return isBidder() ? this.mAdapter.getIsBiddingData(this.mAdUnitSettings) : null;
    }

    public boolean isReadyToBid() {
        return this.mState == SMASH_STATE.INIT_SUCCESS || this.mState == SMASH_STATE.LOADED || this.mState == SMASH_STATE.LOAD_FAILED;
    }

    public boolean isLoadingInProgress() {
        return this.mState == SMASH_STATE.INIT_IN_PROGRESS || this.mState == SMASH_STATE.LOAD_IN_PROGRESS;
    }

    public synchronized void initForBidding() {
        logInternal("initForBidding()");
        setState(SMASH_STATE.INIT_IN_PROGRESS);
        setCustomParams();
        this.mAdapter.initInterstitialForBidding(this.mActivity, this.mAppKey, this.mUserId, this.mAdUnitSettings, this);
    }

    public synchronized void preInitInterstitial() {
        setCustomParams();
        this.mAdapter.preInitInterstitial(this.mActivity, this.mAppKey, this.mUserId, this.mAdUnitSettings, this);
    }

    public synchronized void loadInterstitial(String str) {
        this.mLoadStartTime = new Date().getTime();
        logInternal(Constants.JSMethods.LOAD_INTERSTITIAL);
        setIsLoadCandidate(false);
        if (isBidder()) {
            startTimer();
            setState(SMASH_STATE.LOAD_IN_PROGRESS);
            this.mAdapter.loadInterstitial(this.mAdUnitSettings, this, str);
        } else {
            if (this.mState == SMASH_STATE.NO_INIT) {
                startTimer();
                setState(SMASH_STATE.INIT_IN_PROGRESS);
                setCustomParams();
                this.mAdapter.initInterstitial(this.mActivity, this.mAppKey, this.mUserId, this.mAdUnitSettings, this);
                return;
            }
            startTimer();
            setState(SMASH_STATE.LOAD_IN_PROGRESS);
            this.mAdapter.loadInterstitial(this.mAdUnitSettings, this);
        }
    }

    public synchronized void showInterstitial() {
        this.mAdapter.showInterstitial(this.mAdUnitSettings, this);
    }

    public synchronized void setCappedPerSession() {
        this.mAdapter.setMediationState(AbstractSmash.MEDIATION_STATE.CAPPED_PER_SESSION, aa.e.bo);
    }

    public synchronized boolean isReadyToShow() {
        return this.mAdapter.isInterstitialReady(this.mAdUnitSettings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(SMASH_STATE smash_state) {
        logInternal("state=" + smash_state);
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

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialInitSuccess() {
        synchronized (this) {
            logAdapterCallback("onInterstitialInitSuccess state=" + this.mState.name());
            if (this.mState != SMASH_STATE.INIT_IN_PROGRESS) {
                return;
            }
            this.mListener.onInterstitialInitSuccess(this);
            stopTimer();
            if (isBidder()) {
                setState(SMASH_STATE.INIT_SUCCESS);
            } else {
                setState(SMASH_STATE.LOAD_IN_PROGRESS);
                startTimer();
                this.mAdapter.loadInterstitial(this.mAdUnitSettings, this);
            }
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialInitFailed(IronSourceError ironSourceError) {
        synchronized (this) {
            logAdapterCallback("onInterstitialInitFailed error" + ironSourceError.getErrorMessage() + " state=" + this.mState.name());
            if (this.mState != SMASH_STATE.INIT_IN_PROGRESS) {
                return;
            }
            this.mListener.onInterstitialInitFailed(ironSourceError, this);
            stopTimer();
            setState(SMASH_STATE.NO_INIT);
            if (!isBidder()) {
                this.mListener.onInterstitialAdLoadFailed(ironSourceError, this, new Date().getTime() - this.mLoadStartTime);
            }
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialAdReady() {
        synchronized (this) {
            logAdapterCallback("onInterstitialAdReady state=" + this.mState.name());
            stopTimer();
            if (this.mState != SMASH_STATE.LOAD_IN_PROGRESS) {
                return;
            }
            setState(SMASH_STATE.LOADED);
            this.mListener.onInterstitialAdReady(this, new Date().getTime() - this.mLoadStartTime);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
        synchronized (this) {
            logAdapterCallback("onInterstitialAdLoadFailed error=" + ironSourceError.getErrorMessage() + " state=" + this.mState.name());
            stopTimer();
            if (this.mState != SMASH_STATE.LOAD_IN_PROGRESS) {
                return;
            }
            setState(SMASH_STATE.LOAD_FAILED);
            this.mListener.onInterstitialAdLoadFailed(ironSourceError, this, new Date().getTime() - this.mLoadStartTime);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialAdOpened() {
        synchronized (this) {
            logAdapterCallback("onInterstitialAdOpened");
            this.mListener.onInterstitialAdOpened(this);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialAdClosed() {
        synchronized (this) {
            logAdapterCallback("onInterstitialAdClosed");
            this.mListener.onInterstitialAdClosed(this);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialAdShowSucceeded() {
        synchronized (this) {
            logAdapterCallback("onInterstitialAdShowSucceeded");
            this.mListener.onInterstitialAdShowSucceeded(this);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
        synchronized (this) {
            logAdapterCallback("onInterstitialAdShowFailed error=" + ironSourceError.getErrorMessage());
            this.mListener.onInterstitialAdShowFailed(ironSourceError, this);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialAdClicked() {
        synchronized (this) {
            logAdapterCallback(Constants.JSMethods.ON_INTERSTITIAL_AD_CLICKED);
            this.mListener.onInterstitialAdClicked(this);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialAdVisible() {
        synchronized (this) {
            logAdapterCallback("onInterstitialAdVisible");
            this.mListener.onInterstitialAdVisible(this);
        }
    }

    private void stopTimer() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }

    private void startTimer() {
        logInternal("start timer");
        stopTimer();
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.ProgIsSmash.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                ProgIsSmash.this.logInternal("timed out state=" + ProgIsSmash.this.mState.name() + " isBidder=" + ProgIsSmash.this.isBidder());
                if (ProgIsSmash.this.mState != SMASH_STATE.INIT_IN_PROGRESS || !ProgIsSmash.this.isBidder()) {
                    ProgIsSmash.this.setState(SMASH_STATE.LOAD_FAILED);
                    ProgIsSmash.this.mListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("timed out"), ProgIsSmash.this, new Date().getTime() - ProgIsSmash.this.mLoadStartTime);
                    return;
                }
                ProgIsSmash.this.setState(SMASH_STATE.NO_INIT);
            }
        }, this.mLoadTimeoutSecs * 1000);
    }

    private void logAdapterCallback(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "ProgIsSmash " + getInstanceName() + " : " + str, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logInternal(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "ProgIsSmash " + getInstanceName() + " : " + str, 0);
    }
}
