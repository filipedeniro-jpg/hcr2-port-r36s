package com.ironsource.mediationsdk;

import android.app.Activity;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.AdapterConfig;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.DemandOnlyIsManagerListener;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.sdk.constants.Constants;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class DemandOnlyIsSmash extends DemandOnlySmash implements InterstitialSmashListener {
    private DemandOnlyIsManagerListener mListener;
    private long mLoadStartTime;
    private int mLoadTimeoutSecs;
    private DemandOnlySmash.SMASH_STATE mState;
    private Timer mTimer;

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialInitFailed(IronSourceError ironSourceError) {
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public void onInterstitialInitSuccess() {
    }

    public DemandOnlyIsSmash(Activity activity, String str, String str2, ProviderSettings providerSettings, DemandOnlyIsManagerListener demandOnlyIsManagerListener, int i, AbstractAdapter abstractAdapter) {
        super(new AdapterConfig(providerSettings, providerSettings.getInterstitialSettings()), abstractAdapter);
        this.mListener = demandOnlyIsManagerListener;
        this.mTimer = null;
        this.mLoadTimeoutSecs = i;
        this.mState = DemandOnlySmash.SMASH_STATE.NOT_LOADED;
        this.mAdapter.initInterstitial(activity, str, str2, this.mAdUnitSettings, this);
    }

    public synchronized void loadInterstitial() {
        logInternal("loadInterstitial state=" + this.mState.name());
        if (this.mState == DemandOnlySmash.SMASH_STATE.NOT_LOADED || this.mState == DemandOnlySmash.SMASH_STATE.LOADED) {
            this.mState = DemandOnlySmash.SMASH_STATE.LOAD_IN_PROGRESS;
            startTimer();
            this.mLoadStartTime = new Date().getTime();
            this.mAdapter.loadInterstitial(this.mAdUnitSettings, this);
        } else if (this.mState == DemandOnlySmash.SMASH_STATE.LOAD_IN_PROGRESS) {
            this.mListener.onInterstitialAdLoadFailed(new IronSourceError(IronSourceError.ERROR_DO_IS_LOAD_ALREADY_IN_PROGRESS, "load already in progress"), this, 0L);
        } else {
            this.mListener.onInterstitialAdLoadFailed(new IronSourceError(IronSourceError.ERROR_DO_IS_LOAD_ALREADY_IN_PROGRESS, "cannot load because show is in progress"), this, 0L);
        }
    }

    public synchronized void showInterstitial() {
        logInternal("showInterstitial state=" + this.mState.name());
        if (this.mState == DemandOnlySmash.SMASH_STATE.LOADED) {
            this.mState = DemandOnlySmash.SMASH_STATE.SHOW_IN_PROGRESS;
            this.mAdapter.showInterstitial(this.mAdUnitSettings, this);
        } else {
            this.mListener.onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_DO_IS_CALL_LOAD_BEFORE_SHOW, "load must be called before show"), this);
        }
    }

    public synchronized boolean isInterstitialReady() {
        return this.mAdapter.isInterstitialReady(this.mAdUnitSettings);
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
        this.mTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.DemandOnlyIsSmash.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                DemandOnlyIsSmash.this.logInternal("load timed out state=" + DemandOnlyIsSmash.this.mState.toString());
                if (DemandOnlyIsSmash.this.mState == DemandOnlySmash.SMASH_STATE.LOAD_IN_PROGRESS) {
                    DemandOnlyIsSmash.this.mState = DemandOnlySmash.SMASH_STATE.NOT_LOADED;
                    DemandOnlyIsSmash.this.mListener.onInterstitialAdLoadFailed(new IronSourceError(IronSourceError.ERROR_DO_IS_LOAD_TIMED_OUT, "load timed out"), DemandOnlyIsSmash.this, new Date().getTime() - DemandOnlyIsSmash.this.mLoadStartTime);
                }
            }
        }, this.mLoadTimeoutSecs * 1000);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public synchronized void onInterstitialAdReady() {
        logAdapterCallback("onInterstitialAdReady state=" + this.mState.name());
        stopTimer();
        if (this.mState != DemandOnlySmash.SMASH_STATE.LOAD_IN_PROGRESS) {
            return;
        }
        this.mState = DemandOnlySmash.SMASH_STATE.LOADED;
        this.mListener.onInterstitialAdReady(this, new Date().getTime() - this.mLoadStartTime);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public synchronized void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
        logAdapterCallback("onInterstitialAdLoadFailed error=" + ironSourceError.getErrorMessage() + " state=" + this.mState.name());
        stopTimer();
        if (this.mState != DemandOnlySmash.SMASH_STATE.LOAD_IN_PROGRESS) {
            return;
        }
        this.mState = DemandOnlySmash.SMASH_STATE.NOT_LOADED;
        this.mListener.onInterstitialAdLoadFailed(ironSourceError, this, new Date().getTime() - this.mLoadStartTime);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public synchronized void onInterstitialAdOpened() {
        logAdapterCallback("onInterstitialAdOpened");
        this.mListener.onInterstitialAdOpened(this);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public synchronized void onInterstitialAdClosed() {
        this.mState = DemandOnlySmash.SMASH_STATE.NOT_LOADED;
        logAdapterCallback("onInterstitialAdClosed");
        this.mListener.onInterstitialAdClosed(this);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public synchronized void onInterstitialAdShowSucceeded() {
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public synchronized void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
        this.mState = DemandOnlySmash.SMASH_STATE.NOT_LOADED;
        logAdapterCallback("onInterstitialAdShowFailed error=" + ironSourceError.getErrorMessage());
        this.mListener.onInterstitialAdShowFailed(ironSourceError, this);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public synchronized void onInterstitialAdClicked() {
        logAdapterCallback(Constants.JSMethods.ON_INTERSTITIAL_AD_CLICKED);
        this.mListener.onInterstitialAdClicked(this);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialSmashListener
    public synchronized void onInterstitialAdVisible() {
        logAdapterCallback("onInterstitialAdVisible");
        this.mListener.onInterstitialAdVisible(this);
    }

    private void logAdapterCallback(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "DemandOnlyInterstitialSmash " + this.mAdapterConfig.getProviderName() + " : " + str, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logInternal(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "DemandOnlyInterstitialSmash " + this.mAdapterConfig.getProviderName() + " : " + str, 0);
    }
}
