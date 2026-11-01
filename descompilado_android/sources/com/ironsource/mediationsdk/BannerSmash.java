package com.ironsource.mediationsdk;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.BannerManagerListener;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class BannerSmash implements BannerSmashListener {
    private AbstractAdapter mAdapter;
    private ProviderSettings mAdapterConfigs;
    private IronSourceBannerLayout mBannerLayout;
    private boolean mIsReadyToLoad;
    private BannerManagerListener mListener;
    private long mLoadTimeoutMilisecs;
    private int mProviderPriority;
    private BANNER_SMASH_STATE mState = BANNER_SMASH_STATE.NO_INIT;
    private Timer mTimer;

    protected enum BANNER_SMASH_STATE {
        NO_INIT,
        INIT_IN_PROGRESS,
        LOAD_IN_PROGRESS,
        LOADED,
        LOAD_FAILED,
        DESTROYED
    }

    BannerSmash(BannerManagerListener bannerManagerListener, ProviderSettings providerSettings, AbstractAdapter abstractAdapter, long j, int i) {
        this.mProviderPriority = i;
        this.mListener = bannerManagerListener;
        this.mAdapter = abstractAdapter;
        this.mAdapterConfigs = providerSettings;
        this.mLoadTimeoutMilisecs = j;
        this.mAdapter.addBannerListener(this);
    }

    public boolean isReadyToLoad() {
        return this.mIsReadyToLoad;
    }

    public void setReadyToLoad(boolean z) {
        this.mIsReadyToLoad = z;
    }

    public int getProviderPriority() {
        return this.mProviderPriority;
    }

    public String getName() {
        if (this.mAdapterConfigs.isMultipleInstances()) {
            return this.mAdapterConfigs.getProviderTypeForReflection();
        }
        return this.mAdapterConfigs.getProviderName();
    }

    public String getAdSourceNameForEvents() {
        if (!TextUtils.isEmpty(this.mAdapterConfigs.getAdSourceNameForEvents())) {
            return this.mAdapterConfigs.getAdSourceNameForEvents();
        }
        return getName();
    }

    public String getSubProviderId() {
        return this.mAdapterConfigs.getSubProviderId();
    }

    public AbstractAdapter getAdapter() {
        return this.mAdapter;
    }

    public void loadBanner(IronSourceBannerLayout ironSourceBannerLayout, Activity activity, String str, String str2) {
        log("loadBanner()");
        this.mIsReadyToLoad = false;
        if (ironSourceBannerLayout == null) {
            this.mListener.onBannerAdLoadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_LOAD_EMPTY_BANNER, "banner==null"), this, false);
            return;
        }
        if (this.mAdapter == null) {
            this.mListener.onBannerAdLoadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_LOAD_EMPTY_ADAPTER, "adapter==null"), this, false);
            return;
        }
        this.mBannerLayout = ironSourceBannerLayout;
        startLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.NO_INIT) {
            setState(BANNER_SMASH_STATE.INIT_IN_PROGRESS);
            setCustomParams();
            this.mAdapter.initBanners(activity, str, str2, this.mAdapterConfigs.getBannerSettings(), this);
        } else {
            setState(BANNER_SMASH_STATE.LOAD_IN_PROGRESS);
            this.mAdapter.loadBanner(ironSourceBannerLayout, this.mAdapterConfigs.getBannerSettings(), this);
        }
    }

    public void reloadBanner() {
        log("reloadBanner()");
        startLoadTimer();
        setState(BANNER_SMASH_STATE.LOADED);
        this.mAdapter.reloadBanner(this.mAdapterConfigs.getBannerSettings());
    }

    public void destroyBanner() {
        log("destroyBanner()");
        AbstractAdapter abstractAdapter = this.mAdapter;
        if (abstractAdapter == null) {
            log("destroyBanner() mAdapter == null");
        } else {
            abstractAdapter.destroyBanner(this.mAdapterConfigs.getBannerSettings());
            setState(BANNER_SMASH_STATE.DESTROYED);
        }
    }

    private void setCustomParams() {
        if (this.mAdapter == null) {
            return;
        }
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
                log("setConsent(" + consent + ")");
                this.mAdapter.setConsent(consent.booleanValue());
            }
        } catch (Exception e) {
            log(":setCustomParams():" + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(BANNER_SMASH_STATE banner_smash_state) {
        this.mState = banner_smash_state;
        log("state=" + banner_smash_state.name());
    }

    private void stopLoadTimer() {
        try {
            try {
                if (this.mTimer != null) {
                    this.mTimer.cancel();
                }
            } catch (Exception e) {
                logException("stopLoadTimer", e.getLocalizedMessage());
            }
        } finally {
            this.mTimer = null;
        }
    }

    private void startLoadTimer() {
        try {
            stopLoadTimer();
            this.mTimer = new Timer();
            this.mTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.BannerSmash.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    cancel();
                    if (BannerSmash.this.mState == BANNER_SMASH_STATE.INIT_IN_PROGRESS) {
                        BannerSmash.this.setState(BANNER_SMASH_STATE.NO_INIT);
                        BannerSmash.this.log("init timed out");
                        BannerSmash.this.mListener.onBannerAdLoadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_INIT_TIMEOUT, "Timed out"), BannerSmash.this, false);
                    } else if (BannerSmash.this.mState == BANNER_SMASH_STATE.LOAD_IN_PROGRESS) {
                        BannerSmash.this.setState(BANNER_SMASH_STATE.LOAD_FAILED);
                        BannerSmash.this.log("load timed out");
                        BannerSmash.this.mListener.onBannerAdLoadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_LOAD_TIMEOUT, "Timed out"), BannerSmash.this, false);
                    } else if (BannerSmash.this.mState == BANNER_SMASH_STATE.LOADED) {
                        BannerSmash.this.setState(BANNER_SMASH_STATE.LOAD_FAILED);
                        BannerSmash.this.log("reload timed out");
                        BannerSmash.this.mListener.onBannerAdReloadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_RELOAD_TIMEOUT, "Timed out"), BannerSmash.this, false);
                    }
                }
            }, this.mLoadTimeoutMilisecs);
        } catch (Exception e) {
            logException("startLoadTimer", e.getLocalizedMessage());
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerSmashListener
    public void onBannerInitSuccess() {
        stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.INIT_IN_PROGRESS) {
            startLoadTimer();
            setState(BANNER_SMASH_STATE.LOAD_IN_PROGRESS);
            this.mAdapter.loadBanner(this.mBannerLayout, this.mAdapterConfigs.getBannerSettings(), this);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerSmashListener
    public void onBannerInitFailed(IronSourceError ironSourceError) {
        stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.INIT_IN_PROGRESS) {
            this.mListener.onBannerAdLoadFailed(new IronSourceError(IronSourceError.ERROR_BN_INSTANCE_INIT_ERROR, "Banner init failed"), this, false);
            setState(BANNER_SMASH_STATE.NO_INIT);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerSmashListener
    public void onBannerAdLoaded(View view, FrameLayout.LayoutParams layoutParams) {
        log("onBannerAdLoaded()");
        stopLoadTimer();
        if (this.mState == BANNER_SMASH_STATE.LOAD_IN_PROGRESS) {
            setState(BANNER_SMASH_STATE.LOADED);
            this.mListener.onBannerAdLoaded(this, view, layoutParams);
        } else if (this.mState == BANNER_SMASH_STATE.LOADED) {
            this.mListener.onBannerAdReloaded(this);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerSmashListener
    public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
        log("onBannerAdLoadFailed()");
        stopLoadTimer();
        boolean z = ironSourceError.getErrorCode() == 606;
        if (this.mState == BANNER_SMASH_STATE.LOAD_IN_PROGRESS) {
            setState(BANNER_SMASH_STATE.LOAD_FAILED);
            this.mListener.onBannerAdLoadFailed(ironSourceError, this, z);
        } else if (this.mState == BANNER_SMASH_STATE.LOADED) {
            this.mListener.onBannerAdReloadFailed(ironSourceError, this, z);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerSmashListener
    public void onBannerAdClicked() {
        BannerManagerListener bannerManagerListener = this.mListener;
        if (bannerManagerListener != null) {
            bannerManagerListener.onBannerAdClicked(this);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerSmashListener
    public void onBannerAdScreenPresented() {
        BannerManagerListener bannerManagerListener = this.mListener;
        if (bannerManagerListener != null) {
            bannerManagerListener.onBannerAdScreenPresented(this);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerSmashListener
    public void onBannerAdScreenDismissed() {
        BannerManagerListener bannerManagerListener = this.mListener;
        if (bannerManagerListener != null) {
            bannerManagerListener.onBannerAdScreenDismissed(this);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerSmashListener
    public void onBannerAdLeftApplication() {
        BannerManagerListener bannerManagerListener = this.mListener;
        if (bannerManagerListener != null) {
            bannerManagerListener.onBannerAdLeftApplication(this);
        }
    }

    public void setConsent(boolean z) {
        if (this.mAdapter != null) {
            log("setConsent(" + z + ")");
            this.mAdapter.setConsent(z);
        }
    }

    public void onPause(Activity activity) {
        AbstractAdapter abstractAdapter = this.mAdapter;
        if (abstractAdapter != null) {
            abstractAdapter.onPause(activity);
        }
    }

    public void onResume(Activity activity) {
        AbstractAdapter abstractAdapter = this.mAdapter;
        if (abstractAdapter != null) {
            abstractAdapter.onResume(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_API, "BannerSmash " + getName() + " " + str, 1);
    }

    private void logException(String str, String str2) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, str + " Banner exception: " + getName() + " | " + str2, 3);
    }
}
