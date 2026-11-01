package com.ironsource.mediationsdk;

import android.app.Activity;
import android.text.TextUtils;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.OfferwallPlacement;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.InternalOfferwallApi;
import com.ironsource.mediationsdk.sdk.InternalOfferwallListener;
import com.ironsource.mediationsdk.sdk.OfferwallAdapterApi;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class OfferwallManager implements InternalOfferwallApi, InternalOfferwallListener {
    private Activity mActivity;
    private OfferwallAdapterApi mAdapter;
    private String mCurrentPlacementName;
    private InternalOfferwallListener mListenersWrapper;
    private ProviderSettings mProviderSettings;
    private ServerResponseWrapper mServerResponseWrapper;
    private final String TAG = getClass().getName();
    private AtomicBoolean mAtomicShouldPerformInit = new AtomicBoolean(true);
    private AtomicBoolean mIsOfferwallAvailable = new AtomicBoolean(false);
    private IronSourceLoggerManager mLoggerManager = IronSourceLoggerManager.getLogger();

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public void setOfferwallListener(OfferwallListener offerwallListener) {
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public void showOfferwall() {
    }

    OfferwallManager() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public synchronized void initOfferwall(Activity activity, String str, String str2) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":initOfferwall(appKey: " + str + ", userId: " + str2 + ")", 1);
        this.mActivity = activity;
        this.mServerResponseWrapper = IronSourceObject.getInstance().getCurrentServerResponse();
        if (this.mServerResponseWrapper == null) {
            reportInitFail(ErrorBuilder.buildInitFailedError("Please check configurations for Offerwall adapters", IronSourceConstants.OFFERWALL_AD_UNIT));
            return;
        }
        this.mProviderSettings = this.mServerResponseWrapper.getProviderSettingsHolder().getProviderSettings(IronSourceConstants.SUPERSONIC_CONFIG_NAME);
        if (this.mProviderSettings == null) {
            reportInitFail(ErrorBuilder.buildInitFailedError("Please check configurations for Offerwall adapters", IronSourceConstants.OFFERWALL_AD_UNIT));
            return;
        }
        AbstractAdapter abstractAdapterStartOfferwallAdapter = startOfferwallAdapter();
        if (abstractAdapterStartOfferwallAdapter == 0) {
            reportInitFail(ErrorBuilder.buildInitFailedError("Please check configurations for Offerwall adapters", IronSourceConstants.OFFERWALL_AD_UNIT));
            return;
        }
        setCustomParams(abstractAdapterStartOfferwallAdapter);
        abstractAdapterStartOfferwallAdapter.setLogListener(this.mLoggerManager);
        this.mAdapter = (OfferwallAdapterApi) abstractAdapterStartOfferwallAdapter;
        this.mAdapter.setInternalOfferwallListener(this);
        this.mAdapter.initOfferwall(activity, str, str2, this.mProviderSettings.getRewardedVideoSettings());
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public void showOfferwall(String str) {
        String str2 = "OWManager:showOfferwall(" + str + ")";
        try {
            if (!IronSourceUtils.isNetworkConnected(this.mActivity)) {
                this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildNoInternetConnectionShowFailError(IronSourceConstants.OFFERWALL_AD_UNIT));
                return;
            }
            this.mCurrentPlacementName = str;
            OfferwallPlacement offerwallPlacement = this.mServerResponseWrapper.getConfigurations().getOfferwallConfigurations().getOfferwallPlacement(str);
            if (offerwallPlacement == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
                offerwallPlacement = this.mServerResponseWrapper.getConfigurations().getOfferwallConfigurations().getDefaultOfferwallPlacement();
                if (offerwallPlacement == null) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Default placement was not found, please make sure you are using the right placements.", 3);
                    return;
                }
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, str2, 1);
            if (this.mIsOfferwallAvailable == null || !this.mIsOfferwallAvailable.get() || this.mAdapter == null) {
                return;
            }
            this.mAdapter.showOfferwall(String.valueOf(offerwallPlacement.getPlacementId()), this.mProviderSettings.getRewardedVideoSettings());
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.INTERNAL, str2, e);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public synchronized boolean isOfferwallAvailable() {
        return this.mIsOfferwallAvailable != null ? this.mIsOfferwallAvailable.get() : false;
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public void getOfferwallCredits() {
        OfferwallAdapterApi offerwallAdapterApi = this.mAdapter;
        if (offerwallAdapterApi != null) {
            offerwallAdapterApi.getOfferwallCredits();
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InternalOfferwallApi
    public void setInternalOfferwallListener(InternalOfferwallListener internalOfferwallListener) {
        this.mListenersWrapper = internalOfferwallListener;
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public void onOfferwallAvailable(boolean z) {
        onOfferwallAvailable(z, null);
    }

    @Override // com.ironsource.mediationsdk.sdk.InternalOfferwallListener
    public void onOfferwallAvailable(boolean z, IronSourceError ironSourceError) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onOfferwallAvailable(isAvailable: " + z + ")", 1);
        if (z) {
            this.mIsOfferwallAvailable.set(true);
            InternalOfferwallListener internalOfferwallListener = this.mListenersWrapper;
            if (internalOfferwallListener != null) {
                internalOfferwallListener.onOfferwallAvailable(true);
                return;
            }
            return;
        }
        reportInitFail(ironSourceError);
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public void onOfferwallOpened() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onOfferwallOpened()", 1);
        JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        try {
            if (!TextUtils.isEmpty(this.mCurrentPlacementName)) {
                mediationAdditionalData.put("placement", this.mCurrentPlacementName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(IronSourceConstants.OFFERWALL_OPENED, mediationAdditionalData));
        InternalOfferwallListener internalOfferwallListener = this.mListenersWrapper;
        if (internalOfferwallListener != null) {
            internalOfferwallListener.onOfferwallOpened();
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public void onOfferwallShowFailed(IronSourceError ironSourceError) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onOfferwallShowFailed(" + ironSourceError + ")", 1);
        InternalOfferwallListener internalOfferwallListener = this.mListenersWrapper;
        if (internalOfferwallListener != null) {
            internalOfferwallListener.onOfferwallShowFailed(ironSourceError);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public boolean onOfferwallAdCredited(int i, int i2, boolean z) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onOfferwallAdCredited()", 1);
        InternalOfferwallListener internalOfferwallListener = this.mListenersWrapper;
        if (internalOfferwallListener != null) {
            return internalOfferwallListener.onOfferwallAdCredited(i, i2, z);
        }
        return false;
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public void onGetOfferwallCreditsFailed(IronSourceError ironSourceError) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onGetOfferwallCreditsFailed(" + ironSourceError + ")", 1);
        InternalOfferwallListener internalOfferwallListener = this.mListenersWrapper;
        if (internalOfferwallListener != null) {
            internalOfferwallListener.onGetOfferwallCreditsFailed(ironSourceError);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public void onOfferwallClosed() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "onOfferwallClosed()", 1);
        InternalOfferwallListener internalOfferwallListener = this.mListenersWrapper;
        if (internalOfferwallListener != null) {
            internalOfferwallListener.onOfferwallClosed();
        }
    }

    private synchronized void reportInitFail(IronSourceError ironSourceError) {
        if (this.mIsOfferwallAvailable != null) {
            this.mIsOfferwallAvailable.set(false);
        }
        if (this.mAtomicShouldPerformInit != null) {
            this.mAtomicShouldPerformInit.set(true);
        }
        if (this.mListenersWrapper != null) {
            this.mListenersWrapper.onOfferwallAvailable(false, ironSourceError);
        }
    }

    private AbstractAdapter startOfferwallAdapter() {
        try {
            IronSourceObject ironSourceObject = IronSourceObject.getInstance();
            AbstractAdapter existingAdapter = ironSourceObject.getExistingAdapter(IronSourceConstants.SUPERSONIC_CONFIG_NAME);
            if (existingAdapter == null) {
                Class<?> cls = Class.forName("com.ironsource.adapters." + IronSourceConstants.SUPERSONIC_CONFIG_NAME.toLowerCase() + "." + IronSourceConstants.SUPERSONIC_CONFIG_NAME + "Adapter");
                existingAdapter = (AbstractAdapter) cls.getMethod(IronSourceConstants.START_ADAPTER, String.class).invoke(cls, IronSourceConstants.SUPERSONIC_CONFIG_NAME);
                if (existingAdapter == null) {
                    return null;
                }
            }
            ironSourceObject.addOWAdapter(existingAdapter);
            return existingAdapter;
        } catch (Throwable th) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "SupersonicAds initialization failed - please verify that required dependencies are in you build path.", 2);
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":startOfferwallAdapter", th);
            return null;
        }
    }

    private void setCustomParams(AbstractAdapter abstractAdapter) {
        try {
            Integer age = IronSourceObject.getInstance().getAge();
            if (age != null) {
                abstractAdapter.setAge(age.intValue());
            }
            String gender = IronSourceObject.getInstance().getGender();
            if (gender != null) {
                abstractAdapter.setGender(gender);
            }
            String mediationSegment = IronSourceObject.getInstance().getMediationSegment();
            if (mediationSegment != null) {
                abstractAdapter.setMediationSegment(mediationSegment);
            }
            Boolean consent = IronSourceObject.getInstance().getConsent();
            if (consent != null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, "Offerwall | setConsent(consent:" + consent + ")", 1);
                abstractAdapter.setConsent(consent.booleanValue());
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, ":setCustomParams():" + e.toString(), 3);
        }
    }
}
