package com.ironsource.mediationsdk;

import android.app.Activity;
import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.BaseApi;
import java.util.HashSet;
import java.util.Timer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractSmash implements BaseApi {
    public static final int MAX_ADS_PER_DAY_DEFAULT_VALUE = 99;
    String mAdSourceNameForEvents;
    AbstractAdapter mAdapter;
    ProviderSettings mAdapterConfigs;
    Timer mInitTimer;
    String mInstanceName;
    boolean mIsMultipleInstances;
    Timer mLoadTimer;
    int mMaxAdsPerDay;
    int mMaxAdsPerIteration;
    int mMaxAdsPerSession;
    String mNameForReflection;
    int mProviderPriority;
    String mSpId;
    final String MAX_ADS_PER_SESSION_KEY = "maxAdsPerSession";
    final String MAX_ADS_PER_ITERATION_KEY = "maxAdsPerIteration";
    final String MAX_ADS_PER_DAY_KEY = "maxAdsPerDay";
    int mIterationShowCounter = 0;
    int mSessionShowCounter = 0;
    MEDIATION_STATE mMediationState = MEDIATION_STATE.NOT_INITIATED;
    IronSourceLoggerManager mLoggerManager = IronSourceLoggerManager.getLogger();
    boolean mIsInForeground = true;

    abstract void completeIteration();

    protected abstract String getAdUnitString();

    abstract void startInitTimer();

    abstract void startLoadTimer();

    public enum MEDIATION_STATE {
        NOT_INITIATED(0),
        INIT_FAILED(1),
        INITIATED(2),
        AVAILABLE(3),
        NOT_AVAILABLE(4),
        EXHAUSTED(5),
        CAPPED_PER_SESSION(6),
        INIT_PENDING(7),
        LOAD_PENDING(8),
        CAPPED_PER_DAY(9);

        private int mValue;

        MEDIATION_STATE(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    AbstractSmash(ProviderSettings providerSettings) {
        this.mNameForReflection = providerSettings.getProviderTypeForReflection();
        this.mInstanceName = providerSettings.getProviderInstanceName();
        this.mIsMultipleInstances = providerSettings.isMultipleInstances();
        this.mAdapterConfigs = providerSettings;
        this.mSpId = providerSettings.getSubProviderId();
        this.mAdSourceNameForEvents = providerSettings.getAdSourceNameForEvents();
    }

    void setAdapterForSmash(AbstractAdapter abstractAdapter) {
        this.mAdapter = abstractAdapter;
    }

    boolean isExhausted() {
        return this.mIterationShowCounter >= this.mMaxAdsPerIteration;
    }

    boolean isCappedPerSession() {
        return this.mSessionShowCounter >= this.mMaxAdsPerSession;
    }

    boolean isCappedPerDay() {
        return this.mMediationState == MEDIATION_STATE.CAPPED_PER_DAY;
    }

    boolean isMediationAvailable() {
        return (isExhausted() || isCappedPerSession() || isCappedPerDay()) ? false : true;
    }

    void preShow() {
        this.mIterationShowCounter++;
        this.mSessionShowCounter++;
        if (isCappedPerSession()) {
            setMediationState(MEDIATION_STATE.CAPPED_PER_SESSION);
        } else if (isExhausted()) {
            setMediationState(MEDIATION_STATE.EXHAUSTED);
        }
    }

    void stopInitTimer() {
        try {
            try {
                if (this.mInitTimer != null) {
                    this.mInitTimer.cancel();
                }
            } catch (Exception e) {
                logException("stopInitTimer", e.getLocalizedMessage());
            }
        } finally {
            this.mInitTimer = null;
        }
    }

    void stopLoadTimer() {
        try {
            try {
                if (this.mLoadTimer != null) {
                    this.mLoadTimer.cancel();
                }
            } catch (Exception e) {
                logException("stopLoadTimer", e.getLocalizedMessage());
            }
        } finally {
            this.mLoadTimer = null;
        }
    }

    void setPluginData(String str, String str2) {
        AbstractAdapter abstractAdapter = this.mAdapter;
        if (abstractAdapter != null) {
            abstractAdapter.setPluginData(str, str2);
        }
    }

    MEDIATION_STATE getMediationState() {
        return this.mMediationState;
    }

    String getNameForReflection() {
        return this.mNameForReflection;
    }

    String getInstanceName() {
        return this.mInstanceName;
    }

    public String getName() {
        if (this.mIsMultipleInstances) {
            return this.mNameForReflection;
        }
        return this.mInstanceName;
    }

    public String getSubProviderId() {
        return this.mSpId;
    }

    public String getAdSourceNameForEvents() {
        if (!TextUtils.isEmpty(this.mAdSourceNameForEvents)) {
            return this.mAdSourceNameForEvents;
        }
        return getName();
    }

    int getMaxAdsPerSession() {
        return this.mMaxAdsPerSession;
    }

    int getMaxAdsPerIteration() {
        return this.mMaxAdsPerIteration;
    }

    public int getMaxAdsPerDay() {
        return this.mMaxAdsPerDay;
    }

    public AbstractAdapter getAdapter() {
        return this.mAdapter;
    }

    public int getProviderPriority() {
        return this.mProviderPriority;
    }

    synchronized void setMediationState(MEDIATION_STATE mediation_state) {
        if (this.mMediationState == mediation_state) {
            return;
        }
        this.mMediationState = mediation_state;
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Smart Loading - " + getInstanceName() + " state changed to " + mediation_state.toString(), 0);
        if (this.mAdapter != null && (mediation_state == MEDIATION_STATE.CAPPED_PER_SESSION || mediation_state == MEDIATION_STATE.CAPPED_PER_DAY)) {
            this.mAdapter.setMediationState(mediation_state, getAdUnitString());
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void onResume(Activity activity) {
        AbstractAdapter abstractAdapter = this.mAdapter;
        if (abstractAdapter != null) {
            abstractAdapter.onResume(activity);
        }
        this.mIsInForeground = true;
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void onPause(Activity activity) {
        AbstractAdapter abstractAdapter = this.mAdapter;
        if (abstractAdapter != null) {
            abstractAdapter.onPause(activity);
        }
        this.mIsInForeground = false;
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void setAge(int i) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, getName() + ":setAge(age:" + i + ")", 1);
            this.mAdapter.setAge(i);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void setGender(String str) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, getName() + ":setGender(gender:" + str + ")", 1);
            this.mAdapter.setGender(str);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void setMediationSegment(String str) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, getName() + ":setMediationSegment(segment:" + str + ")", 1);
            this.mAdapter.setMediationSegment(str);
        }
    }

    public HashSet<String> getAllSettingsForProvider(String str) {
        return IronSourceObject.getInstance().getAllSettingsForProvider(this.mNameForReflection, str);
    }

    void setProviderPriority(int i) {
        this.mProviderPriority = i;
    }

    void setConsent(boolean z) {
        if (this.mAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, getName() + " | " + getAdUnitString() + "| setConsent(consent:" + z + ")", 1);
            this.mAdapter.setConsent(z);
        }
    }

    void logException(String str, String str2) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, str + " exception: " + getInstanceName() + " | " + str2, 3);
    }
}
