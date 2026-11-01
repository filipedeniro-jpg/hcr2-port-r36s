package com.ironsource.adapters.ironsource;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.ironsource.mediationsdk.AbstractAdapter;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.ironsource.sdk.IronSourceAdInstance;
import com.ironsource.sdk.IronSourceAdInstanceBuilder;
import com.ironsource.sdk.IronSourceNetwork;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.listeners.OnInterstitialListener;
import com.ironsource.sdk.utils.SDKUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceAdapter extends AbstractAdapter {
    private static final int IS_LOAD_EXCEPTION = 1000;
    private static final int IS_SHOW_EXCEPTION = 1001;
    private static final int RV_LOAD_EXCEPTION = 1002;
    private static final int RV_SHOW_EXCEPTION = 1003;
    private static final String VERSION = "6.9.0";
    private final String ADM_KEY;
    private final String APPLICATION_USER_AGE_GROUP;
    private final String APPLICATION_USER_GENDER;
    private final String CUSTOM_SEGMENT;
    private final String DEMAND_SOURCE_NAME;
    private final String DYNAMIC_CONTROLLER_CONFIG;
    private final String DYNAMIC_CONTROLLER_DEBUG_MODE;
    private final String DYNAMIC_CONTROLLER_URL;
    private final String SDK_PLUGIN_TYPE;
    private Context mContext;
    private ConcurrentHashMap<String, IronSourceAdInstance> mDemandSourceToISAd;
    private ConcurrentHashMap<String, IronSourceAdInstance> mDemandSourceToRvAd;
    private boolean mDidInitSdk;
    private String mMediationSegment;
    private String mUserAgeGroup;
    private String mUserGender;

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public String getVersion() {
        return "6.9.0";
    }

    public static IronSourceAdapter startAdapter(String str) {
        return new IronSourceAdapter(str);
    }

    private IronSourceAdapter(String str) {
        super(str);
        this.DYNAMIC_CONTROLLER_URL = "controllerUrl";
        this.DYNAMIC_CONTROLLER_DEBUG_MODE = "debugMode";
        this.DYNAMIC_CONTROLLER_CONFIG = Constants.RequestParameters.CONTROLLER_CONFIG;
        this.APPLICATION_USER_GENDER = "applicationUserGender";
        this.APPLICATION_USER_AGE_GROUP = "applicationUserAgeGroup";
        this.SDK_PLUGIN_TYPE = "SDKPluginType";
        this.CUSTOM_SEGMENT = "custom_Segment";
        this.ADM_KEY = Constants.ParametersKeys.ADM;
        this.DEMAND_SOURCE_NAME = "demandSourceName";
        this.mDidInitSdk = false;
        log(str + ": new instance");
        this.mDemandSourceToISAd = new ConcurrentHashMap<>();
        this.mDemandSourceToRvAd = new ConcurrentHashMap<>();
        this.mUserAgeGroup = null;
        this.mUserGender = null;
        this.mMediationSegment = null;
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public String getCoreSDKVersion() {
        return SDKUtils.getSDKVersion();
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BaseApi
    public void onPause(Activity activity) {
        IronSourceNetwork.onPause(activity);
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BaseApi
    public void onResume(Activity activity) {
        IronSourceNetwork.onResume(activity);
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    protected void setConsent(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.RequestParameters.GDPR_CONSENT_STATUS, String.valueOf(z));
            IronSourceNetwork.updateConsentInfo(jSONObject);
        } catch (JSONException e) {
            logError("setConsent exception " + e.getMessage());
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BaseApi
    public void setAge(int i) {
        if (i >= 13 && i <= 17) {
            this.mUserAgeGroup = "1";
            return;
        }
        if (i >= 18 && i <= 20) {
            this.mUserAgeGroup = "2";
            return;
        }
        if (i >= 21 && i <= 24) {
            this.mUserAgeGroup = "3";
            return;
        }
        if (i >= 25 && i <= 34) {
            this.mUserAgeGroup = "4";
            return;
        }
        if (i >= 35 && i <= 44) {
            this.mUserAgeGroup = "5";
            return;
        }
        if (i >= 45 && i <= 54) {
            this.mUserAgeGroup = "6";
            return;
        }
        if (i >= 55 && i <= 64) {
            this.mUserAgeGroup = "7";
        } else if (i > 65 && i <= 120) {
            this.mUserAgeGroup = "8";
        } else {
            this.mUserAgeGroup = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BaseApi
    public void setGender(String str) {
        this.mUserGender = str;
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BaseApi
    public void setMediationSegment(String str) {
        this.mMediationSegment = str;
    }

    private HashMap<String, String> getInitParams() {
        HashMap<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(this.mUserAgeGroup)) {
            map.put("applicationUserAgeGroup", this.mUserAgeGroup);
        }
        if (!TextUtils.isEmpty(this.mUserGender)) {
            map.put("applicationUserGender", this.mUserGender);
        }
        String pluginType = getPluginType();
        if (!TextUtils.isEmpty(pluginType)) {
            map.put("SDKPluginType", pluginType);
        }
        if (!TextUtils.isEmpty(this.mMediationSegment)) {
            map.put("custom_Segment", this.mMediationSegment);
        }
        return map;
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public void preInitInterstitial(Activity activity, String str, String str2, JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        log(jSONObject, "preInitInterstitial");
        initSDK(activity, str, str2, jSONObject);
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public synchronized Map<String, Object> getIsBiddingData(JSONObject jSONObject) {
        HashMap map;
        map = new HashMap();
        String token = IronSourceNetwork.getToken(this.mContext);
        if (token != null) {
            map.put("token", token);
        } else {
            logError("IS bidding token is null");
            map.put("token", "");
        }
        return map;
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public void initInterstitialForBidding(Activity activity, String str, String str2, JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        log(jSONObject, "initInterstitialForBidding");
        initSDK(activity, str, str2, jSONObject);
        String demandSourceName = getDemandSourceName(jSONObject);
        this.mDemandSourceToISAd.put(demandSourceName, new IronSourceAdInstanceBuilder(demandSourceName, new IronSourceInterstitialListener(interstitialSmashListener, demandSourceName)).setInAppBidding().build());
        interstitialSmashListener.onInterstitialInitSuccess();
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public void initInterstitial(Activity activity, String str, String str2, JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        log(jSONObject, Constants.JSMethods.INIT_INTERSTITIAL);
        initSDK(activity, str, str2, jSONObject);
        String demandSourceName = getDemandSourceName(jSONObject);
        this.mDemandSourceToISAd.put(demandSourceName, new IronSourceAdInstanceBuilder(demandSourceName, new IronSourceInterstitialListener(interstitialSmashListener, demandSourceName)).build());
        interstitialSmashListener.onInterstitialInitSuccess();
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public void loadInterstitial(JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener, String str) {
        log(jSONObject, Constants.JSMethods.LOAD_INTERSTITIAL);
        try {
            HashMap map = new HashMap();
            map.put(Constants.ParametersKeys.ADM, str);
            IronSourceNetwork.loadAd(this.mDemandSourceToISAd.get(getDemandSourceName(jSONObject)), map);
        } catch (Exception e) {
            logError("loadInterstitial for bidding exception " + e.getMessage());
            interstitialSmashListener.onInterstitialAdLoadFailed(new IronSourceError(1000, e.getMessage()));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public void loadInterstitial(JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        log(jSONObject, Constants.JSMethods.LOAD_INTERSTITIAL);
        try {
            IronSourceNetwork.loadAd(this.mDemandSourceToISAd.get(getDemandSourceName(jSONObject)));
        } catch (Exception e) {
            logError("loadInterstitial exception " + e.getMessage());
            interstitialSmashListener.onInterstitialAdLoadFailed(new IronSourceError(1000, e.getMessage()));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public void showInterstitial(JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        log(jSONObject, Constants.JSMethods.SHOW_INTERSTITIAL);
        try {
            int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(2);
            HashMap map = new HashMap();
            map.put(Constants.RequestParameters.SESSION_DEPTH, String.valueOf(sessionDepth));
            IronSourceNetwork.showAd(this.mDemandSourceToISAd.get(getDemandSourceName(jSONObject)), map);
        } catch (Exception e) {
            logError("showInterstitial exception " + e.getMessage());
            interstitialSmashListener.onInterstitialAdShowFailed(new IronSourceError(1001, e.getMessage()));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public boolean isInterstitialReady(JSONObject jSONObject) {
        IronSourceAdInstance ironSourceAdInstance = this.mDemandSourceToISAd.get(getDemandSourceName(jSONObject));
        return ironSourceAdInstance != null && IronSourceNetwork.isAdAvailableForInstance(ironSourceAdInstance);
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public synchronized Map<String, Object> getRvBiddingData(JSONObject jSONObject) {
        HashMap map;
        map = new HashMap();
        String token = IronSourceNetwork.getToken(this.mContext);
        if (token != null) {
            map.put("token", token);
        } else {
            logError("RV bidding token is null");
            map.put("token", "");
        }
        return map;
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public void initRvForBidding(Activity activity, String str, String str2, JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
        log(jSONObject, "initRvForBidding");
        initSDK(activity, str, str2, jSONObject);
        String demandSourceName = getDemandSourceName(jSONObject);
        this.mDemandSourceToRvAd.put(demandSourceName, new IronSourceAdInstanceBuilder(demandSourceName, new IronSourceRewardedVideoListener(rewardedVideoSmashListener, demandSourceName)).setInAppBidding().setRewarded().build());
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public void initRvForDemandOnly(Activity activity, String str, String str2, JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
        log(jSONObject, "initRvForDemandOnly");
        initSDK(activity, str, str2, jSONObject);
        String demandSourceName = getDemandSourceName(jSONObject);
        this.mDemandSourceToRvAd.put(demandSourceName, new IronSourceAdInstanceBuilder(demandSourceName, new IronSourceRewardedVideoListener(rewardedVideoSmashListener, demandSourceName, true)).setRewarded().build());
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void initRewardedVideo(Activity activity, String str, String str2, JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
        log(jSONObject, Constants.JSMethods.INIT_REWARDED_VIDEO);
        initSDK(activity, str, str2, jSONObject);
        String demandSourceName = getDemandSourceName(jSONObject);
        this.mDemandSourceToRvAd.put(demandSourceName, new IronSourceAdInstanceBuilder(demandSourceName, new IronSourceRewardedVideoListener(rewardedVideoSmashListener, demandSourceName)).setRewarded().build());
        fetchRewardedVideo(jSONObject);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void fetchRewardedVideo(JSONObject jSONObject) {
        log(jSONObject, "fetchRewardedVideo");
        IronSourceAdInstance ironSourceAdInstance = this.mDemandSourceToRvAd.get(getDemandSourceName(jSONObject));
        if (ironSourceAdInstance == null) {
            logError("fetchRewardedVideo exception: null adInstance ");
            return;
        }
        try {
            IronSourceNetwork.loadAd(ironSourceAdInstance);
        } catch (Exception e) {
            logError("fetchRewardedVideo exception " + e.getMessage());
            OnInterstitialListener adListener = ironSourceAdInstance.getAdListener();
            if (adListener != null) {
                adListener.onInterstitialLoadFailed("fetch exception");
            }
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public void loadVideoForDemandOnly(JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
        log(jSONObject, "loadVideoForDemandOnly");
        try {
            IronSourceNetwork.loadAd(this.mDemandSourceToRvAd.get(getDemandSourceName(jSONObject)));
        } catch (Exception e) {
            logError("loadVideoForDemandOnly exception " + e.getMessage());
            rewardedVideoSmashListener.onRewardedVideoLoadFailed(new IronSourceError(1002, e.getMessage()));
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public void loadVideo(JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener, String str) {
        log(jSONObject, "loadVideo (RV in bidding mode)");
        try {
            IronSourceNetwork.loadAd(this.mDemandSourceToRvAd.get(getDemandSourceName(jSONObject)));
        } catch (Exception e) {
            logError("loadVideo exception " + e.getMessage());
            rewardedVideoSmashListener.onRewardedVideoLoadFailed(new IronSourceError(1002, e.getMessage()));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void showRewardedVideo(JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
        log(jSONObject, Constants.JSMethods.SHOW_REWARDED_VIDEO);
        try {
            IronSourceAdInstance ironSourceAdInstance = this.mDemandSourceToRvAd.get(getDemandSourceName(jSONObject));
            int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(1);
            HashMap map = new HashMap();
            map.put(Constants.RequestParameters.SESSION_DEPTH, String.valueOf(sessionDepth));
            IronSourceNetwork.showAd(ironSourceAdInstance, map);
        } catch (Exception e) {
            logError("showRewardedVideo exception " + e.getMessage());
            rewardedVideoSmashListener.onRewardedVideoAdShowFailed(new IronSourceError(1003, e.getMessage()));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public boolean isRewardedVideoAvailable(JSONObject jSONObject) {
        IronSourceAdInstance ironSourceAdInstance = this.mDemandSourceToRvAd.get(getDemandSourceName(jSONObject));
        return ironSourceAdInstance != null && IronSourceNetwork.isAdAvailableForInstance(ironSourceAdInstance);
    }

    private synchronized void initSDK(Activity activity, String str, String str2, JSONObject jSONObject) {
        if (!this.mDidInitSdk) {
            this.mDidInitSdk = true;
            if (isAdaptersDebugEnabled()) {
                SDKUtils.setDebugMode(3);
            } else {
                SDKUtils.setDebugMode(jSONObject.optInt("debugMode", 0));
            }
            SDKUtils.setControllerUrl(jSONObject.optString("controllerUrl"));
            SDKUtils.setControllerConfig(jSONObject.optString(Constants.RequestParameters.CONTROLLER_CONFIG, ""));
            this.mContext = activity.getApplicationContext();
            IronSourceNetwork.initSDK(activity, str, str2, getInitParams());
        }
    }

    private class IronSourceInterstitialListener implements OnInterstitialListener {
        private String mDemandSourceName;
        private InterstitialSmashListener mListener;

        IronSourceInterstitialListener(InterstitialSmashListener interstitialSmashListener, String str) {
            this.mDemandSourceName = str;
            this.mListener = interstitialSmashListener;
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialInitSuccess() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialInitSuccess");
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialInitFailed(String str) {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialInitFailed");
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialLoadSuccess() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialLoadSuccess");
            this.mListener.onInterstitialAdReady();
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialLoadFailed(String str) {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialLoadFailed " + str);
            this.mListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(str));
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialOpen() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialOpen");
            this.mListener.onInterstitialAdOpened();
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialAdRewarded(String str, int i) {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialAdRewarded demandSourceId=" + str + " amount=" + i);
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialClose() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialClose");
            this.mListener.onInterstitialAdClosed();
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialShowSuccess() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialShowSuccess");
            this.mListener.onInterstitialAdShowSucceeded();
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialShowFailed(String str) {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialShowFailed " + str);
            this.mListener.onInterstitialAdShowFailed(ErrorBuilder.buildShowFailedError("Interstitial", str));
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialClick() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialClick");
            this.mListener.onInterstitialAdClicked();
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialEventNotificationReceived(String str, JSONObject jSONObject) {
            IronSourceAdapter.this.log(this.mDemandSourceName + " interstitialListener onInterstitialEventNotificationReceived eventName=" + str);
            this.mListener.onInterstitialAdVisible();
        }
    }

    private class IronSourceRewardedVideoListener implements OnInterstitialListener {
        private String mDemandSourceName;
        boolean mIsRvDemandOnly;
        RewardedVideoSmashListener mListener;

        IronSourceRewardedVideoListener(RewardedVideoSmashListener rewardedVideoSmashListener, String str) {
            this.mDemandSourceName = str;
            this.mListener = rewardedVideoSmashListener;
            this.mIsRvDemandOnly = false;
        }

        IronSourceRewardedVideoListener(RewardedVideoSmashListener rewardedVideoSmashListener, String str, boolean z) {
            this.mDemandSourceName = str;
            this.mListener = rewardedVideoSmashListener;
            this.mIsRvDemandOnly = z;
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialInitSuccess() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " rewardedVideoListener onInterstitialInitSuccess");
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialInitFailed(String str) {
            IronSourceAdapter.this.log(this.mDemandSourceName + " rewardedVideoListener onInterstitialInitFailed");
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialLoadSuccess() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " rewardedVideoListener onInterstitialLoadSuccess");
            if (this.mIsRvDemandOnly) {
                this.mListener.onRewardedVideoLoadSuccess();
            } else {
                this.mListener.onRewardedVideoAvailabilityChanged(true);
            }
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialLoadFailed(String str) {
            IronSourceAdapter.this.log(this.mDemandSourceName + " rewardedVideoListener onInterstitialLoadFailed " + str);
            if (this.mIsRvDemandOnly) {
                this.mListener.onRewardedVideoLoadFailed(ErrorBuilder.buildLoadFailedError(str));
            } else {
                this.mListener.onRewardedVideoAvailabilityChanged(false);
            }
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialOpen() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " rewardedVideoListener onInterstitialOpen");
            this.mListener.onRewardedVideoAdOpened();
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialAdRewarded(String str, int i) {
            IronSourceAdapter.this.log(this.mDemandSourceName + " rewardedVideoListener onInterstitialAdRewarded demandSourceId=" + str + " amount=" + i);
            this.mListener.onRewardedVideoAdRewarded();
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialClose() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " rewardedVideoListener onInterstitialClose");
            this.mListener.onRewardedVideoAdClosed();
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialShowSuccess() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " rewardedVideoListener onInterstitialShowSuccess");
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialShowFailed(String str) {
            IronSourceAdapter.this.log("rewardedVideoListener onInterstitialShowSuccess " + str);
            this.mListener.onRewardedVideoAdShowFailed(ErrorBuilder.buildShowFailedError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT, str));
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialClick() {
            IronSourceAdapter.this.log(this.mDemandSourceName + " rewardedVideoListener onInterstitialClick");
            this.mListener.onRewardedVideoAdClicked();
        }

        @Override // com.ironsource.sdk.listeners.OnInterstitialListener
        public void onInterstitialEventNotificationReceived(String str, JSONObject jSONObject) {
            IronSourceAdapter.this.log(this.mDemandSourceName + " rewardedVideoListener onInterstitialEventNotificationReceived eventName=" + str);
            this.mListener.onRewardedVideoAdVisible();
        }
    }

    private String getDemandSourceName(JSONObject jSONObject) {
        if (!TextUtils.isEmpty(jSONObject.optString("demandSourceName"))) {
            return jSONObject.optString("demandSourceName");
        }
        return getProviderName();
    }

    private void logError(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "IronSourceAdapter: " + str, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "IronSourceAdapter: " + str, 0);
    }

    private void log(JSONObject jSONObject, String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "IronSourceAdapter " + getDemandSourceName(jSONObject) + ": " + str, 0);
    }
}
