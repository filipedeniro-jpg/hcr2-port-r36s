package com.ironsource.adapters.ris;

import android.app.Activity;
import android.text.TextUtils;
import com.ironsource.mediationsdk.AbstractAdapter;
import com.ironsource.mediationsdk.AbstractSmash;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.ironsource.sdk.SSAFactory;
import com.ironsource.sdk.SSAPublisher;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.listeners.OnRewardedVideoListener;
import com.ironsource.sdk.utils.SDKUtils;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RISAdapter extends AbstractAdapter implements OnRewardedVideoListener {
    private final String AD_VISIBLE_EVENT_NAME;
    private final String DYNAMIC_CONTROLLER_CONFIG;
    private final String DYNAMIC_CONTROLLER_DEBUG_MODE;
    private final String DYNAMIC_CONTROLLER_URL;
    private boolean hasAdAvailable;
    private boolean mConsent;
    private boolean mDidReportInitStatus;
    private boolean mDidSetConsent;
    private SSAPublisher mSSAPublisher;

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void fetchRewardedVideo(JSONObject jSONObject) {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void initRewardedVideo(Activity activity, String str, String str2, JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public boolean isRewardedVideoAvailable(JSONObject jSONObject) {
        return false;
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void showRewardedVideo(JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
    }

    public static RISAdapter startAdapter(String str) {
        return new RISAdapter(str);
    }

    private RISAdapter(String str) {
        super(str);
        this.hasAdAvailable = false;
        this.mDidReportInitStatus = false;
        this.DYNAMIC_CONTROLLER_URL = "controllerUrl";
        this.DYNAMIC_CONTROLLER_DEBUG_MODE = "debugMode";
        this.DYNAMIC_CONTROLLER_CONFIG = Constants.RequestParameters.CONTROLLER_CONFIG;
        this.AD_VISIBLE_EVENT_NAME = "impressions";
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public String getVersion() {
        return IronSourceUtils.getSDKVersion();
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public String getCoreSDKVersion() {
        return SDKUtils.getSDKVersion();
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BaseApi
    public void onResume(Activity activity) {
        SSAPublisher sSAPublisher = this.mSSAPublisher;
        if (sSAPublisher != null) {
            sSAPublisher.onResume(activity);
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BaseApi
    public void onPause(Activity activity) {
        SSAPublisher sSAPublisher = this.mSSAPublisher;
        if (sSAPublisher != null) {
            sSAPublisher.onPause(activity);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public void initInterstitial(final Activity activity, final String str, final String str2, JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        SDKUtils.setControllerUrl(jSONObject.optString("controllerUrl"));
        if (isAdaptersDebugEnabled()) {
            SDKUtils.setDebugMode(3);
        } else {
            SDKUtils.setDebugMode(jSONObject.optInt("debugMode", 0));
        }
        SDKUtils.setControllerConfig(jSONObject.optString(Constants.RequestParameters.CONTROLLER_CONFIG, ""));
        activity.runOnUiThread(new Runnable() { // from class: com.ironsource.adapters.ris.RISAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    RISAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                    if (RISAdapter.this.mDidSetConsent) {
                        RISAdapter.this.applyConsent(RISAdapter.this.mConsent);
                    }
                    SSAFactory.getPublisherInstance(activity).initRewardedVideo(str, str2, RISAdapter.this.getProviderName(), new HashMap(), RISAdapter.this);
                } catch (Exception e) {
                    RISAdapter.this.onRVInitFail(e.getMessage());
                }
            }
        });
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public void loadInterstitial(JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        if (this.hasAdAvailable) {
            for (InterstitialSmashListener interstitialSmashListener2 : this.mAllInterstitialSmashes) {
                if (interstitialSmashListener2 != null) {
                    interstitialSmashListener2.onInterstitialAdReady();
                }
            }
            return;
        }
        for (InterstitialSmashListener interstitialSmashListener3 : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener3 != null) {
                interstitialSmashListener3.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("No Ads to Load"));
            }
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public void showInterstitial(JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        this.mActiveInterstitialSmash = interstitialSmashListener;
        if (this.mSSAPublisher != null) {
            int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(2);
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("demandSourceName", getProviderName());
                jSONObject2.put(Constants.RequestParameters.SESSION_DEPTH, sessionDepth);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mSSAPublisher.showRewardedVideo(jSONObject2);
            return;
        }
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, "Please call init before calling showRewardedVideo"));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public boolean isInterstitialReady(JSONObject jSONObject) {
        return this.hasAdAvailable;
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVInitSuccess(AdUnitsReady adUnitsReady) {
        int i;
        log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ":onRVInitSuccess()", 1);
        try {
            i = Integer.parseInt(adUnitsReady.getNumOfAdUnits());
        } catch (NumberFormatException e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "onRVInitSuccess:parseInt()", e);
            i = 0;
        }
        this.hasAdAvailable = i > 0;
        if (this.mDidReportInitStatus) {
            return;
        }
        this.mDidReportInitStatus = true;
        for (InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitSuccess();
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVInitFail(String str) {
        log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ":onRVInitFail()", 1);
        this.hasAdAvailable = false;
        if (this.mDidReportInitStatus) {
            return;
        }
        this.mDidReportInitStatus = true;
        for (InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError(str, "Interstitial"));
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVNoMoreOffers() {
        log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ":onRVNoMoreOffers()", 1);
        if (this.mDidReportInitStatus) {
            return;
        }
        this.mDidReportInitStatus = true;
        for (InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitSuccess();
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVAdCredited(int i) {
        log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ":onRVAdCredited()", 1);
        if (this.mRewardedInterstitial != null) {
            this.mRewardedInterstitial.onInterstitialAdRewarded();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVAdClosed() {
        log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ":onRVAdClosed()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClosed();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVAdOpened() {
        log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ":onRVAdOpened()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowSucceeded();
            this.mActiveInterstitialSmash.onInterstitialAdOpened();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVShowFail(String str) {
        log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ":onRVShowFail()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, "Show Failed"));
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVAdClicked() {
        log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ":onRVAdClicked()", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClicked();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVEventNotificationReceived(String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + " :onRISEventNotificationReceived: " + str + " extData: " + jSONObject.toString(), 1);
            if (TextUtils.isEmpty(str) || !"impressions".equals(str) || this.mActiveInterstitialSmash == null) {
                return;
            }
            this.mActiveInterstitialSmash.onInterstitialAdVisible();
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    protected void setMediationState(AbstractSmash.MEDIATION_STATE mediation_state, String str) {
        if (this.mSSAPublisher != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + " :setMediationState(RIS:(rewardedvideo)):" + str + " , " + getProviderName() + " , " + mediation_state.getValue() + ")", 1);
            this.mSSAPublisher.setMediationState("rewardedvideo", getProviderName(), mediation_state.getValue());
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    protected void setConsent(boolean z) {
        this.mConsent = z;
        this.mDidSetConsent = true;
        applyConsent(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyConsent(boolean z) {
        if (this.mSSAPublisher == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.RequestParameters.GDPR_CONSENT_STATUS, String.valueOf(z));
            jSONObject.put("demandSourceName", getProviderName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.mSSAPublisher.updateConsentInfo(jSONObject);
    }
}
