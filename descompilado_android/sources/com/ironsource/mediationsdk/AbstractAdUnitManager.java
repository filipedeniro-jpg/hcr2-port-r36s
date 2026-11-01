package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.BaseApi;
import com.ironsource.mediationsdk.utils.DailyCappingManager;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
abstract class AbstractAdUnitManager implements BaseApi {
    Activity mActivity;
    String mAppKey;
    boolean mBackFillInitStarted;
    private AbstractSmash mBackfillSmash;
    Boolean mLastMediationAvailabilityState;
    private AbstractSmash mPremiumSmash;
    int mSmartLoadAmount;
    String mUserId;
    final String KEY_REASON = IronSourceConstants.EVENTS_ERROR_REASON;
    final String KEY_STATUS = "status";
    final String KEY_PLACEMENT = "placement";
    final String KEY_REWARD_NAME = IronSourceConstants.EVENTS_REWARD_NAME;
    final String KEY_REWARD_AMOUNT = IronSourceConstants.EVENTS_REWARD_AMOUNT;
    final String KEY_PROVIDER_PRIORITY = "providerPriority";
    boolean mShouldTrackNetworkState = false;
    boolean mCanShowPremium = true;
    final CopyOnWriteArrayList<AbstractSmash> mSmashArray = new CopyOnWriteArrayList<>();
    IronSourceLoggerManager mLoggerManager = IronSourceLoggerManager.getLogger();
    DailyCappingManager mDailyCappingManager = null;
    AtomicBoolean mDidImplementOnResume = new AtomicBoolean();
    AtomicBoolean mDidImplementOnPause = new AtomicBoolean();

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void setAge(int i) {
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void setGender(String str) {
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void setMediationSegment(String str) {
    }

    abstract void shouldTrackNetworkState(Context context, boolean z);

    AbstractAdUnitManager() {
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void onResume(Activity activity) {
        this.mDidImplementOnResume.set(true);
        if (activity != null) {
            this.mActivity = activity;
        }
        synchronized (this.mSmashArray) {
            if (this.mSmashArray != null) {
                Iterator<AbstractSmash> it = this.mSmashArray.iterator();
                while (it.hasNext()) {
                    it.next().onResume(activity);
                }
            }
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void onPause(Activity activity) {
        this.mDidImplementOnPause.set(true);
        synchronized (this.mSmashArray) {
            if (this.mSmashArray != null) {
                Iterator<AbstractSmash> it = this.mSmashArray.iterator();
                while (it.hasNext()) {
                    it.next().onPause(activity);
                }
            }
        }
    }

    void setSmartLoadAmount(int i) {
        this.mSmartLoadAmount = i;
    }

    void addSmashToArray(AbstractSmash abstractSmash) {
        this.mSmashArray.add(abstractSmash);
        DailyCappingManager dailyCappingManager = this.mDailyCappingManager;
        if (dailyCappingManager != null) {
            dailyCappingManager.addSmash(abstractSmash);
        }
    }

    void setBackfillSmash(AbstractSmash abstractSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, abstractSmash.getInstanceName() + " is set as backfill", 0);
        this.mBackfillSmash = abstractSmash;
    }

    void setPremiumSmash(AbstractSmash abstractSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, abstractSmash.getInstanceName() + " is set as premium", 0);
        this.mPremiumSmash = abstractSmash;
    }

    AbstractSmash getBackfillSmash() {
        return this.mBackfillSmash;
    }

    AbstractSmash getPremiumSmash() {
        return this.mPremiumSmash;
    }

    void setCustomParams(AbstractSmash abstractSmash) {
        try {
            Integer age = IronSourceObject.getInstance().getAge();
            if (age != null) {
                abstractSmash.setAge(age.intValue());
            }
            String gender = IronSourceObject.getInstance().getGender();
            if (!TextUtils.isEmpty(gender)) {
                abstractSmash.setGender(gender);
            }
            String mediationSegment = IronSourceObject.getInstance().getMediationSegment();
            if (!TextUtils.isEmpty(mediationSegment)) {
                abstractSmash.setMediationSegment(mediationSegment);
            }
            String pluginType = ConfigFile.getConfigFile().getPluginType();
            if (!TextUtils.isEmpty(pluginType)) {
                abstractSmash.setPluginData(pluginType, ConfigFile.getConfigFile().getPluginFrameworkVersion());
            }
            Boolean consent = IronSourceObject.getInstance().getConsent();
            if (consent != null) {
                abstractSmash.setConsent(consent.booleanValue());
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, ":setCustomParams():" + e.toString(), 3);
        }
    }

    synchronized boolean canShowPremium() {
        return this.mCanShowPremium;
    }

    synchronized void disablePremiumForCurrentSession() {
        this.mCanShowPremium = false;
    }

    synchronized AbstractAdapter getLoadedAdapterOrFetchByReflection(AbstractSmash abstractSmash) {
        AbstractAdapter existingAdapter;
        try {
            existingAdapter = IronSourceObject.getInstance().getExistingAdapter(abstractSmash.getName());
            if (existingAdapter == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "loading " + abstractSmash.getName() + " with reflection", 0);
                Class<?> cls = Class.forName("com.ironsource.adapters." + abstractSmash.getNameForReflection().toLowerCase() + "." + abstractSmash.getNameForReflection() + "Adapter");
                existingAdapter = (AbstractAdapter) cls.getMethod(IronSourceConstants.START_ADAPTER, String.class).invoke(cls, abstractSmash.getName());
            } else {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "using previously loaded " + abstractSmash.getName(), 0);
            }
        } catch (Exception unused) {
            return null;
        }
        return existingAdapter;
    }

    void setConsent(boolean z) {
        for (AbstractSmash abstractSmash : this.mSmashArray) {
            if (abstractSmash != null) {
                abstractSmash.setConsent(z);
            }
        }
    }

    void verifyOnPauseOnResume() {
        if (!this.mDidImplementOnPause.get()) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, "IronSource.onPause() wasn't overridden in your activity lifecycle!", 3);
        }
        if (this.mDidImplementOnResume.get()) {
            return;
        }
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, "IronSource.onResume() wasn't overridden in your activity lifecycle!", 3);
    }
}
