package com.ironsource.mediationsdk;

import android.app.Activity;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.AdapterConfig;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.DemandOnlyRvManagerListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class DemandOnlyRvSmash extends DemandOnlySmash implements RewardedVideoSmashListener {
    private DemandOnlyRvManagerListener mListener;
    private long mLoadStartTime;
    private int mLoadTimeoutSecs;
    private DemandOnlySmash.SMASH_STATE mState;
    private Timer mTimer;

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoAdEnded() {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoAdStarted() {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoAvailabilityChanged(boolean z) {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoInitFailed(IronSourceError ironSourceError) {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoInitSuccess() {
    }

    DemandOnlyRvSmash(Activity activity, String str, String str2, ProviderSettings providerSettings, DemandOnlyRvManagerListener demandOnlyRvManagerListener, int i, AbstractAdapter abstractAdapter) {
        super(new AdapterConfig(providerSettings, providerSettings.getInterstitialSettings()), abstractAdapter);
        this.mAdapterConfig = new AdapterConfig(providerSettings, providerSettings.getRewardedVideoSettings());
        this.mAdUnitSettings = this.mAdapterConfig.getAdUnitSetings();
        this.mAdapter = abstractAdapter;
        this.mListener = demandOnlyRvManagerListener;
        this.mTimer = null;
        this.mLoadTimeoutSecs = i;
        this.mState = DemandOnlySmash.SMASH_STATE.NOT_LOADED;
        this.mAdapter.initRvForDemandOnly(activity, str, str2, this.mAdUnitSettings, this);
    }

    public void loadRewardedVideo() {
        logInternal("loadRewardedVideo state=" + this.mState.name());
        if (this.mState == DemandOnlySmash.SMASH_STATE.NOT_LOADED || this.mState == DemandOnlySmash.SMASH_STATE.LOADED) {
            this.mState = DemandOnlySmash.SMASH_STATE.LOAD_IN_PROGRESS;
            startTimer();
            this.mLoadStartTime = new Date().getTime();
            this.mAdapter.loadVideoForDemandOnly(this.mAdUnitSettings, this);
            return;
        }
        if (this.mState == DemandOnlySmash.SMASH_STATE.LOAD_IN_PROGRESS) {
            this.mListener.onRewardedVideoAdLoadFailed(new IronSourceError(IronSourceError.ERROR_DO_RV_LOAD_ALREADY_IN_PROGRESS, "load already in progress"), this, 0L);
        } else {
            this.mListener.onRewardedVideoAdLoadFailed(new IronSourceError(IronSourceError.ERROR_DO_RV_LOAD_ALREADY_IN_PROGRESS, "cannot load because show is in progress"), this, 0L);
        }
    }

    public void showRewardedVideo() {
        logInternal("showRewardedVideo state=" + this.mState.name());
        if (this.mState == DemandOnlySmash.SMASH_STATE.LOADED) {
            this.mState = DemandOnlySmash.SMASH_STATE.SHOW_IN_PROGRESS;
            this.mAdapter.showRewardedVideo(this.mAdUnitSettings, this);
        } else {
            this.mListener.onRewardedVideoAdShowFailed(new IronSourceError(IronSourceError.ERROR_DO_RV_CALL_LOAD_BEFORE_SHOW, "load must be called before show"), this);
        }
    }

    public boolean isRewardedVideoAvailable() {
        return this.mAdapter.isRewardedVideoAvailable(this.mAdUnitSettings);
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
        this.mTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.DemandOnlyRvSmash.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                DemandOnlyRvSmash.this.logInternal("load timed out state=" + DemandOnlyRvSmash.this.mState.toString());
                if (DemandOnlyRvSmash.this.mState == DemandOnlySmash.SMASH_STATE.LOAD_IN_PROGRESS) {
                    DemandOnlyRvSmash.this.mState = DemandOnlySmash.SMASH_STATE.NOT_LOADED;
                    DemandOnlyRvSmash.this.mListener.onRewardedVideoAdLoadFailed(new IronSourceError(IronSourceError.ERROR_DO_RV_LOAD_TIMED_OUT, "load timed out"), DemandOnlyRvSmash.this, new Date().getTime() - DemandOnlyRvSmash.this.mLoadStartTime);
                }
            }
        }, this.mLoadTimeoutSecs * 1000);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoLoadSuccess() {
        logAdapterCallback("onRewardedVideoLoadSuccess state=" + this.mState.name());
        stopTimer();
        if (this.mState != DemandOnlySmash.SMASH_STATE.LOAD_IN_PROGRESS) {
            return;
        }
        this.mState = DemandOnlySmash.SMASH_STATE.LOADED;
        this.mListener.onRewardedVideoLoadSuccess(this, new Date().getTime() - this.mLoadStartTime);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoLoadFailed(IronSourceError ironSourceError) {
        logAdapterCallback("onRewardedVideoLoadFailed error=" + ironSourceError.getErrorMessage() + " state=" + this.mState.name());
        stopTimer();
        if (this.mState != DemandOnlySmash.SMASH_STATE.LOAD_IN_PROGRESS) {
            return;
        }
        this.mState = DemandOnlySmash.SMASH_STATE.NOT_LOADED;
        this.mListener.onRewardedVideoAdLoadFailed(ironSourceError, this, new Date().getTime() - this.mLoadStartTime);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoAdOpened() {
        logAdapterCallback("onRewardedVideoAdOpened");
        this.mListener.onRewardedVideoAdOpened(this);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoAdClosed() {
        this.mState = DemandOnlySmash.SMASH_STATE.NOT_LOADED;
        logAdapterCallback("onRewardedVideoAdClosed");
        this.mListener.onRewardedVideoAdClosed(this);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
        this.mState = DemandOnlySmash.SMASH_STATE.NOT_LOADED;
        logAdapterCallback("onRewardedVideoAdClosed error=" + ironSourceError);
        this.mListener.onRewardedVideoAdShowFailed(ironSourceError, this);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoAdClicked() {
        logAdapterCallback("onRewardedVideoAdClicked");
        this.mListener.onRewardedVideoAdClicked(this);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoAdVisible() {
        logAdapterCallback("onRewardedVideoAdVisible");
        this.mListener.onRewardedVideoAdVisible(this);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener
    public void onRewardedVideoAdRewarded() {
        logAdapterCallback("onRewardedVideoAdRewarded");
        this.mListener.onRewardedVideoAdRewarded(this);
    }

    private void logAdapterCallback(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "DemandOnlyRewardedVideoSmash " + this.mAdapterConfig.getProviderName() + " : " + str, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logInternal(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "DemandOnlyRewardedVideoSmash " + this.mAdapterConfig.getProviderName() + " : " + str, 0);
    }
}
