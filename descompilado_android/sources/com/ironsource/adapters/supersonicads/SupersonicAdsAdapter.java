package com.ironsource.adapters.supersonicads;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.facebook.appevents.AppEventsConstants;
import com.ironsource.mediationsdk.AbstractAdapter;
import com.ironsource.mediationsdk.AbstractSmash;
import com.ironsource.mediationsdk.AdapterUtils;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IntegrationData;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.IronSourceObject;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.BannerSmashListener;
import com.ironsource.mediationsdk.sdk.InternalOfferwallListener;
import com.ironsource.mediationsdk.sdk.InterstitialSmashListener;
import com.ironsource.mediationsdk.sdk.OfferwallAdapterApi;
import com.ironsource.mediationsdk.sdk.RewardedVideoSmashListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.SessionDepthManager;
import com.ironsource.sdk.ISAdSize;
import com.ironsource.sdk.ISNAdView.ISNAdView;
import com.ironsource.sdk.SSAFactory;
import com.ironsource.sdk.SSAPublisher;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.listeners.OnBannerListener;
import com.ironsource.sdk.listeners.OnInterstitialListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.listeners.OnRewardedVideoListener;
import com.ironsource.sdk.utils.SDKUtils;
import com.tonyodev.fetch.FetchService;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class SupersonicAdsAdapter extends AbstractAdapter implements OfferwallAdapterApi, OnOfferWallListener, OnInterstitialListener, OnRewardedVideoListener, OnBannerListener {
    private static final String VERSION = "6.9.0";
    private static Handler mUIThreadHandler;
    private final String AD_VISIBLE_EVENT_NAME;
    private final String APPLICATION_PRIVATE_KEY;
    private final String APPLICATION_USER_AGE_GROUP;
    private final String APPLICATION_USER_GENDER;
    private final String CAMPAIGN_ID;
    private final String CLIENT_SIDE_CALLBACKS;
    private final String CUSTOM_PARAM_PREFIX;
    private final String CUSTOM_SEGMENT;
    private final String DYNAMIC_CONTROLLER_CONFIG;
    private final String DYNAMIC_CONTROLLER_DEBUG_MODE;
    private final String DYNAMIC_CONTROLLER_URL;
    private final String ITEM_COUNT;
    private final String ITEM_NAME;
    private final String ITEM_SIGNATURE;
    private final String LANGUAGE;
    private final String MAX_VIDEO_LENGTH;
    private final String OW_PLACEMENT_ID;
    private final String SDK_PLUGIN_TYPE;
    private final String SUPERSONIC_ADS;
    private final String TIMESTAMP;
    private boolean mConsent;
    private boolean mDidSetConsent;
    private boolean mDidSetInitParams;
    private boolean mIsRVAvailable;
    private ISNAdView mIsnAdView;
    private String mMediationSegment;
    private InternalOfferwallListener mOfferwallListener;
    private SSAPublisher mSSAPublisher;
    private String mUserAgeGroup;
    private String mUserGender;

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public String getVersion() {
        return "6.9.0";
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallAdapterApi
    public boolean isOfferwallAvailable() {
        return true;
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialAdRewarded(String str, int i) {
    }

    @Override // com.ironsource.sdk.listeners.OnOfferWallListener
    public void onOWGeneric(String str, String str2) {
    }

    public static SupersonicAdsAdapter startAdapter(String str) {
        return new SupersonicAdsAdapter(str);
    }

    private SupersonicAdsAdapter(String str) {
        super(str);
        this.TIMESTAMP = "timestamp";
        this.ITEM_SIGNATURE = "itemSignature";
        this.SDK_PLUGIN_TYPE = "SDKPluginType";
        this.OW_PLACEMENT_ID = Constants.PLACEMENT_ID;
        this.mIsRVAvailable = false;
        this.mDidSetInitParams = false;
        this.SUPERSONIC_ADS = IronSourceConstants.SUPERSONIC_CONFIG_NAME;
        this.DYNAMIC_CONTROLLER_URL = "controllerUrl";
        this.DYNAMIC_CONTROLLER_DEBUG_MODE = "debugMode";
        this.DYNAMIC_CONTROLLER_CONFIG = Constants.RequestParameters.CONTROLLER_CONFIG;
        this.APPLICATION_USER_GENDER = "applicationUserGender";
        this.APPLICATION_USER_AGE_GROUP = "applicationUserAgeGroup";
        this.LANGUAGE = "language";
        this.MAX_VIDEO_LENGTH = "maxVideoLength";
        this.CAMPAIGN_ID = Constants.RequestParameters.CAMPAIGN_ID;
        this.CUSTOM_PARAM_PREFIX = "custom_";
        this.CUSTOM_SEGMENT = "custom_Segment";
        this.ITEM_NAME = "itemName";
        this.ITEM_COUNT = "itemCount";
        this.APPLICATION_PRIVATE_KEY = "privateKey";
        this.CLIENT_SIDE_CALLBACKS = Constants.ParametersKeys.USE_CLIENT_SIDE_CALLBACKS;
        this.AD_VISIBLE_EVENT_NAME = "impressions";
    }

    public static IntegrationData getIntegrationData(Activity activity) {
        IntegrationData integrationData = new IntegrationData(IronSourceConstants.SUPERSONIC_CONFIG_NAME, "6.9.0");
        integrationData.activities = new String[]{"com.ironsource.sdk.controller.ControllerActivity", "com.ironsource.sdk.controller.InterstitialActivity", "com.ironsource.sdk.controller.OpenUrlActivity"};
        return integrationData;
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public String getCoreSDKVersion() {
        return SDKUtils.getSDKVersion();
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BaseApi
    public void onPause(Activity activity) {
        SSAPublisher sSAPublisher = this.mSSAPublisher;
        if (sSAPublisher != null) {
            sSAPublisher.onPause(activity);
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BaseApi
    public void onResume(Activity activity) {
        SSAPublisher sSAPublisher = this.mSSAPublisher;
        if (sSAPublisher != null) {
            sSAPublisher.onResume(activity);
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

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void initRewardedVideo(final Activity activity, final String str, final String str2, final JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
        if (!this.mDidSetInitParams) {
            setParamsBeforeInit(jSONObject);
        }
        activity.runOnUiThread(new Runnable() { // from class: com.ironsource.adapters.supersonicads.SupersonicAdsAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                    HashMap rewardedVideoExtraParams = SupersonicAdsAdapter.this.getRewardedVideoExtraParams(jSONObject);
                    if (SupersonicAdsAdapter.this.mDidSetConsent) {
                        SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                    }
                    SupersonicAdsAdapter.this.mSSAPublisher.initRewardedVideo(str, str2, SupersonicAdsAdapter.this.getProviderName(), rewardedVideoExtraParams, SupersonicAdsAdapter.this);
                } catch (Exception e) {
                    e.printStackTrace();
                    SupersonicAdsAdapter.this.onRVInitFail(Constants.JSMethods.INIT_REWARDED_VIDEO);
                }
            }
        });
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void fetchRewardedVideo(JSONObject jSONObject) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_API, getProviderName() + ": fetchRewardedVideo", 1);
        for (RewardedVideoSmashListener rewardedVideoSmashListener : this.mAllRewardedVideoSmashes) {
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(this.mIsRVAvailable);
            }
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public void showRewardedVideo(JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener) {
        this.mActiveRewardedVideoSmash = rewardedVideoSmashListener;
        if (this.mSSAPublisher != null) {
            int sessionDepth = SessionDepthManager.getInstance().getSessionDepth(1);
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
        this.mIsRVAvailable = false;
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdShowFailed(ErrorBuilder.buildNoAdsToShowError(IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
        }
        for (RewardedVideoSmashListener rewardedVideoSmashListener2 : this.mAllRewardedVideoSmashes) {
            if (rewardedVideoSmashListener2 != null) {
                rewardedVideoSmashListener2.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoAdapterApi
    public synchronized boolean isRewardedVideoAvailable(JSONObject jSONObject) {
        return this.mIsRVAvailable;
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public synchronized void preInitInterstitial(final Activity activity, final String str, final String str2, JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ": preInitInterstitial", 0);
        if (!this.mDidSetInitParams) {
            setParamsBeforeInit(jSONObject);
        }
        activity.runOnUiThread(new Runnable() { // from class: com.ironsource.adapters.supersonicads.SupersonicAdsAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (SupersonicAdsAdapter.this) {
                    try {
                        SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                        HashMap interstitialExtraParams = SupersonicAdsAdapter.this.getInterstitialExtraParams();
                        if (SupersonicAdsAdapter.this.mDidSetConsent) {
                            SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                        }
                        SupersonicAdsAdapter.this.mSSAPublisher.initInterstitial(str, str2, SupersonicAdsAdapter.this.getProviderName(), interstitialExtraParams, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                        SupersonicAdsAdapter.this.onInterstitialInitFailed(e.getMessage());
                    }
                }
            }
        });
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public synchronized void initInterstitial(final Activity activity, final String str, final String str2, JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ": initInterstitial", 0);
        if (!this.mDidSetInitParams) {
            setParamsBeforeInit(jSONObject);
        }
        activity.runOnUiThread(new Runnable() { // from class: com.ironsource.adapters.supersonicads.SupersonicAdsAdapter.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (SupersonicAdsAdapter.this) {
                    try {
                        SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                        HashMap interstitialExtraParams = SupersonicAdsAdapter.this.getInterstitialExtraParams();
                        if (SupersonicAdsAdapter.this.mDidSetConsent) {
                            SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                        }
                        SupersonicAdsAdapter.this.mSSAPublisher.initInterstitial(str, str2, SupersonicAdsAdapter.this.getProviderName(), interstitialExtraParams, SupersonicAdsAdapter.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                        SupersonicAdsAdapter.this.onInterstitialInitFailed(e.getMessage());
                    }
                }
            }
        });
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public void loadInterstitial(JSONObject jSONObject, InterstitialSmashListener interstitialSmashListener) {
        if (this.mSSAPublisher != null) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("demandSourceName", getProviderName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mSSAPublisher.loadInterstitial(jSONObject2);
            return;
        }
        log(IronSourceLogger.IronSourceTag.NATIVE, "Please call initInterstitial before calling loadInterstitial", 2);
        for (InterstitialSmashListener interstitialSmashListener2 : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener2 != null) {
                interstitialSmashListener2.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError("Load was called before Init"));
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
            this.mSSAPublisher.showInterstitial(jSONObject2);
            return;
        }
        log(IronSourceLogger.IronSourceTag.NATIVE, "Please call loadInterstitial before calling showInterstitial", 2);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(ErrorBuilder.buildNoAdsToShowError("Interstitial"));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialAdapterApi
    public boolean isInterstitialReady(JSONObject jSONObject) {
        SSAPublisher sSAPublisher = this.mSSAPublisher;
        return sSAPublisher != null && sSAPublisher.isInterstitialAdAvailable(getProviderName());
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallAdapterApi
    public void setInternalOfferwallListener(InternalOfferwallListener internalOfferwallListener) {
        this.mOfferwallListener = internalOfferwallListener;
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallAdapterApi
    public void initOfferwall(final Activity activity, final String str, final String str2, final JSONObject jSONObject) {
        if (!this.mDidSetInitParams) {
            setParamsBeforeInit(jSONObject);
        }
        activity.runOnUiThread(new Runnable() { // from class: com.ironsource.adapters.supersonicads.SupersonicAdsAdapter.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    HashMap offerwallExtraParams = SupersonicAdsAdapter.this.getOfferwallExtraParams(jSONObject);
                    SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                    if (SupersonicAdsAdapter.this.mDidSetConsent) {
                        SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                    }
                    SupersonicAdsAdapter.this.mSSAPublisher.initOfferWall(str, str2, offerwallExtraParams, SupersonicAdsAdapter.this);
                } catch (Exception e) {
                    e.printStackTrace();
                    IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.ADAPTER_API, SupersonicAdsAdapter.this.getProviderName() + ":initOfferwall(userId:" + str2 + ")", e);
                    SupersonicAdsAdapter.this.mOfferwallListener.onOfferwallAvailable(false, ErrorBuilder.buildInitFailedError("Adapter initialization failure - " + SupersonicAdsAdapter.this.getProviderName() + " - " + e.getMessage(), IronSourceConstants.OFFERWALL_AD_UNIT));
                }
            }
        });
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallAdapterApi
    public void getOfferwallCredits() {
        if (this.mSSAPublisher != null) {
            this.mSSAPublisher.getOfferWallCredits(IronSourceObject.getInstance().getIronSourceAppKey(), IronSourceObject.getInstance().getIronSourceUserId(), this);
            return;
        }
        log(IronSourceLogger.IronSourceTag.NATIVE, "Please call init before calling getOfferwallCredits", 2);
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallAdapterApi
    public void showOfferwall(String str, JSONObject jSONObject) {
        HashMap<String, String> offerwallExtraParams = getOfferwallExtraParams(jSONObject);
        if (offerwallExtraParams != null) {
            offerwallExtraParams.put(Constants.PLACEMENT_ID, str);
        }
        SSAPublisher sSAPublisher = this.mSSAPublisher;
        if (sSAPublisher != null) {
            sSAPublisher.showOfferWall(offerwallExtraParams);
        } else {
            log(IronSourceLogger.IronSourceTag.NATIVE, "Please call init before calling showOfferwall", 2);
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    public void initBanners(final Activity activity, final String str, final String str2, final JSONObject jSONObject, BannerSmashListener bannerSmashListener) {
        if (!this.mDidSetInitParams) {
            setParamsBeforeInit(jSONObject);
        }
        activity.runOnUiThread(new Runnable() { // from class: com.ironsource.adapters.supersonicads.SupersonicAdsAdapter.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    HashMap bannerExtraParams = SupersonicAdsAdapter.this.getBannerExtraParams(jSONObject);
                    SupersonicAdsAdapter.this.mSSAPublisher = SSAFactory.getPublisherInstance(activity);
                    if (SupersonicAdsAdapter.this.mDidSetConsent) {
                        SupersonicAdsAdapter.this.applyConsent(SupersonicAdsAdapter.this.mConsent);
                    }
                    SupersonicAdsAdapter.this.mSSAPublisher.initBanner(str, str2, SupersonicAdsAdapter.this.getProviderName(), bannerExtraParams, SupersonicAdsAdapter.this);
                } catch (Exception e) {
                    e.printStackTrace();
                    SupersonicAdsAdapter.this.onBannerInitFailed(e.getMessage());
                }
            }
        });
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BannerAdapterApi
    public void loadBanner(final IronSourceBannerLayout ironSourceBannerLayout, JSONObject jSONObject, BannerSmashListener bannerSmashListener) {
        try {
            if (this.mSSAPublisher == null) {
                log(IronSourceLogger.IronSourceTag.NATIVE, "Please call initBanner before calling loadBanner", 2);
                for (BannerSmashListener bannerSmashListener2 : this.mAllBannerSmashes) {
                    if (bannerSmashListener2 != null) {
                        bannerSmashListener2.onBannerAdLoadFailed(ErrorBuilder.buildLoadFailedError("Load was called before Init"));
                    }
                }
            }
            if (ironSourceBannerLayout == null) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "SupersonicAds loadBanner banner == null", 3);
                return;
            }
            this.mActiveBannerSmash = bannerSmashListener;
            if (this.mIsnAdView != null) {
                this.mIsnAdView.performCleanup();
                this.mIsnAdView = null;
            }
            final JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("demandSourceName", getProviderName());
            jSONObject2.put(Constants.ParametersKeys.PRODUCT_TYPE, SSAEnums.ProductType.Banner);
            if (mUIThreadHandler == null) {
                mUIThreadHandler = new Handler(Looper.getMainLooper());
            }
            mUIThreadHandler.post(new Runnable() { // from class: com.ironsource.adapters.supersonicads.SupersonicAdsAdapter.6
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        SupersonicAdsAdapter.this.mIsnAdView = SupersonicAdsAdapter.this.createBanner(ironSourceBannerLayout.getActivity(), ironSourceBannerLayout.getSize(), SupersonicAdsAdapter.this.mActiveBannerSmash);
                        if (SupersonicAdsAdapter.this.mIsnAdView != null) {
                            SupersonicAdsAdapter.this.mIsnAdView.loadAd(jSONObject2);
                        }
                    } catch (Exception e) {
                        IronSourceError ironSourceErrorBuildLoadFailedError = ErrorBuilder.buildLoadFailedError("Banner Load Fail, " + SupersonicAdsAdapter.this.getProviderName() + " - " + e.getMessage());
                        if (SupersonicAdsAdapter.this.mActiveBannerSmash != null) {
                            SupersonicAdsAdapter.this.mActiveBannerSmash.onBannerAdLoadFailed(ironSourceErrorBuildLoadFailedError);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BannerAdapterApi
    public void reloadBanner(JSONObject jSONObject) {
        try {
            if (this.mIsnAdView != null) {
                this.mIsnAdView.loadAd(jSONObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log(IronSourceLogger.IronSourceTag.NATIVE, getProviderName() + " reloadBanner Failed to reload banner ad", 2);
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter, com.ironsource.mediationsdk.sdk.BannerAdapterApi
    public void destroyBanner(JSONObject jSONObject) {
        ISNAdView iSNAdView = this.mIsnAdView;
        if (iSNAdView != null) {
            iSNAdView.performCleanup();
            this.mIsnAdView = null;
        }
    }

    private synchronized void setParamsBeforeInit(JSONObject jSONObject) {
        this.mDidSetInitParams = true;
        SDKUtils.setControllerUrl(jSONObject.optString("controllerUrl"));
        if (isAdaptersDebugEnabled()) {
            SDKUtils.setDebugMode(3);
        } else {
            SDKUtils.setDebugMode(jSONObject.optInt("debugMode", 0));
        }
        SDKUtils.setControllerConfig(jSONObject.optString(Constants.RequestParameters.CONTROLLER_CONFIG, ""));
    }

    private HashMap<String, String> getGenenralExtraParams() {
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
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> getRewardedVideoExtraParams(JSONObject jSONObject) {
        HashMap<String, String> genenralExtraParams = getGenenralExtraParams();
        String strOptString = jSONObject.optString("language");
        if (!TextUtils.isEmpty(strOptString)) {
            genenralExtraParams.put("language", strOptString);
        }
        String strOptString2 = jSONObject.optString("maxVideoLength");
        if (!TextUtils.isEmpty(strOptString2)) {
            genenralExtraParams.put("maxVideoLength", strOptString2);
        }
        String strOptString3 = jSONObject.optString(Constants.RequestParameters.CAMPAIGN_ID);
        if (!TextUtils.isEmpty(strOptString3)) {
            genenralExtraParams.put(Constants.RequestParameters.CAMPAIGN_ID, strOptString3);
        }
        if (!TextUtils.isEmpty(this.mMediationSegment)) {
            genenralExtraParams.put("custom_Segment", this.mMediationSegment);
        }
        addItemNameCountSignature(genenralExtraParams, jSONObject);
        Map<String, String> rewardedVideoCustomParams = SupersonicConfig.getConfigObj().getRewardedVideoCustomParams();
        if (rewardedVideoCustomParams != null && !rewardedVideoCustomParams.isEmpty()) {
            genenralExtraParams.putAll(rewardedVideoCustomParams);
        }
        return genenralExtraParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> getInterstitialExtraParams() {
        return getGenenralExtraParams();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> getOfferwallExtraParams(JSONObject jSONObject) {
        HashMap<String, String> genenralExtraParams = getGenenralExtraParams();
        String strOptString = jSONObject.optString("language");
        if (!TextUtils.isEmpty(strOptString)) {
            genenralExtraParams.put("language", strOptString);
        }
        genenralExtraParams.put(Constants.ParametersKeys.USE_CLIENT_SIDE_CALLBACKS, String.valueOf(SupersonicConfig.getConfigObj().getClientSideCallbacks()));
        Map<String, String> offerwallCustomParams = SupersonicConfig.getConfigObj().getOfferwallCustomParams();
        if (offerwallCustomParams != null && !offerwallCustomParams.isEmpty()) {
            genenralExtraParams.putAll(offerwallCustomParams);
        }
        addItemNameCountSignature(genenralExtraParams, jSONObject);
        return genenralExtraParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> getBannerExtraParams(JSONObject jSONObject) {
        return getGenenralExtraParams();
    }

    private void addItemNameCountSignature(HashMap<String, String> map, JSONObject jSONObject) {
        try {
            String strOptString = jSONObject.optString("itemName");
            int iOptInt = jSONObject.optInt("itemCount", -1);
            String strOptString2 = jSONObject.optString("privateKey");
            boolean z = true;
            if (TextUtils.isEmpty(strOptString)) {
                z = false;
            } else {
                map.put("itemName", strOptString);
            }
            if (TextUtils.isEmpty(strOptString2)) {
                z = false;
            }
            if (iOptInt == -1) {
                z = false;
            } else {
                map.put("itemCount", String.valueOf(iOptInt));
            }
            if (z) {
                int currentTimestamp = IronSourceUtils.getCurrentTimestamp();
                map.put("timestamp", String.valueOf(currentTimestamp));
                map.put("itemSignature", createItemSig(currentTimestamp, strOptString, iOptInt, strOptString2));
            }
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.ADAPTER_API, " addItemNameCountSignature", e);
        }
    }

    private String createItemSig(int i, String str, int i2, String str2) {
        return IronSourceUtils.getMD5(i + str + i2 + str2);
    }

    private String createMinimumOfferCommissionSig(double d, String str) {
        return IronSourceUtils.getMD5(d + str);
    }

    private String createUserCreationDateSig(String str, String str2, String str3) {
        return IronSourceUtils.getMD5(str + str2 + str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:20:0x0041  */
    /* JADX WARN: Code duplicated, block: B:44:0x0086 A[PHI: r7
  0x0086: PHI (r7v1 int) = (r7v0 int), (r7v3 int) binds: [B:22:0x0048, B:42:0x0083] A[DONT_GENERATE, DONT_INLINE]] */
    public ISNAdView createBanner(Activity activity, ISBannerSize iSBannerSize, BannerSmashListener bannerSmashListener) {
        byte b;
        String description = iSBannerSize.getDescription();
        switch (description) {
            case "RECTANGLE":
                b = 4;
                break;
            case "LARGE":
                b = 1;
                break;
            case "SMART":
                b = 2;
                break;
            case "BANNER":
                b = 0;
                break;
            case "CUSTOM":
                b = 3;
                break;
            default:
                b = -1;
                break;
        }
        int i = 90;
        int i2 = FetchService.ACTION_LOGGING;
        if (b == 0) {
            i = 50;
        } else if (b != 1) {
            if (b == 2) {
                boolean zIsLargeScreen = AdapterUtils.isLargeScreen(activity);
                if (zIsLargeScreen) {
                    i2 = 728;
                }
                if (!zIsLargeScreen) {
                    i = 50;
                }
            } else {
                if (b != 3) {
                    if (bannerSmashListener != null) {
                        bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.unsupportedBannerSize(IronSourceConstants.SUPERSONIC_CONFIG_NAME));
                    }
                    return null;
                }
                int width = iSBannerSize.getWidth();
                int height = iSBannerSize.getHeight();
                if (width < 320 || !(height == 50 || height == 90)) {
                    if (bannerSmashListener != null) {
                        bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.unsupportedBannerSize(IronSourceConstants.SUPERSONIC_CONFIG_NAME));
                    }
                    return null;
                }
                i = height;
                i2 = width;
            }
        }
        return this.mSSAPublisher.createBanner(activity, new ISAdSize(AdapterUtils.dpToPixels(activity, i2), AdapterUtils.dpToPixels(activity, i), description));
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVNoMoreOffers() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onRVNoMoreOffers ", 1);
        this.mIsRVAvailable = false;
        for (RewardedVideoSmashListener rewardedVideoSmashListener : this.mAllRewardedVideoSmashes) {
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVInitSuccess(AdUnitsReady adUnitsReady) {
        int i;
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onRVInitSuccess ", 1);
        try {
            i = Integer.parseInt(adUnitsReady.getNumOfAdUnits());
        } catch (NumberFormatException e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, ": onRVInitSuccess: parseInt()", e);
            i = 0;
        }
        boolean z = i > 0;
        this.mIsRVAvailable = z;
        for (RewardedVideoSmashListener rewardedVideoSmashListener : this.mAllRewardedVideoSmashes) {
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(z);
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVInitFail(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onRVInitFail ", 1);
        for (RewardedVideoSmashListener rewardedVideoSmashListener : this.mAllRewardedVideoSmashes) {
            if (rewardedVideoSmashListener != null) {
                rewardedVideoSmashListener.onRewardedVideoAvailabilityChanged(false);
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVAdClicked() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onRVAdClicked ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdClicked();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVEventNotificationReceived(String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onRVEventNotificationReceived: " + str + " extData: " + jSONObject.toString(), 1);
        }
        if (TextUtils.isEmpty(str) || !"impressions".equals(str) || this.mActiveRewardedVideoSmash == null) {
            return;
        }
        this.mActiveRewardedVideoSmash.onRewardedVideoAdVisible();
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVShowFail(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onRVShowFail ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_NO_ADS_TO_SHOW, str));
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVAdCredited(int i) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onRVAdCredited ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdRewarded();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onRVAdClosed ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdClosed();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnRewardedVideoListener
    public void onRVAdOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onRVAdOpened ", 1);
        if (this.mActiveRewardedVideoSmash != null) {
            this.mActiveRewardedVideoSmash.onRewardedVideoAdOpened();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialInitSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onInterstitialInitSuccess ", 1);
        for (InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitSuccess();
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialInitFailed(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onInterstitialInitFailed ", 1);
        for (InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialInitFailed(ErrorBuilder.buildInitFailedError(str, "Interstitial"));
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialLoadSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onInterstitialLoadSuccess ", 1);
        for (InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialAdReady();
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialLoadFailed(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onInterstitialAdLoadFailed ", 1);
        for (InterstitialSmashListener interstitialSmashListener : this.mAllInterstitialSmashes) {
            if (interstitialSmashListener != null) {
                interstitialSmashListener.onInterstitialAdLoadFailed(ErrorBuilder.buildLoadFailedError(str));
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialOpen() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onInterstitialAdOpened ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdOpened();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialClose() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onInterstitialAdClosed ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClosed();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialShowSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onInterstitialAdShowSucceeded ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowSucceeded();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialShowFailed(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onInterstitialAdShowFailed ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdShowFailed(ErrorBuilder.buildShowFailedError("Interstitial", str));
        }
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialClick() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onInterstitialAdClicked ", 1);
        if (this.mActiveInterstitialSmash != null) {
            this.mActiveInterstitialSmash.onInterstitialAdClicked();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnInterstitialListener
    public void onInterstitialEventNotificationReceived(String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onInterstitialEventNotificationReceived: " + str + " extData: " + jSONObject.toString(), 1);
            if (TextUtils.isEmpty(str) || !"impressions".equals(str) || this.mActiveInterstitialSmash == null) {
                return;
            }
            this.mActiveInterstitialSmash.onInterstitialAdVisible();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnOfferWallListener
    public void onOfferwallInitSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onOfferwallInitSuccess ", 1);
        InternalOfferwallListener internalOfferwallListener = this.mOfferwallListener;
        if (internalOfferwallListener != null) {
            internalOfferwallListener.onOfferwallAvailable(true);
        }
    }

    @Override // com.ironsource.sdk.listeners.OnOfferWallListener
    public void onOfferwallInitFail(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onOfferwallInitFail ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallAvailable(false, ErrorBuilder.buildGenericError(str));
        }
    }

    @Override // com.ironsource.sdk.listeners.OnOfferWallListener
    public void onOfferwallEventNotificationReceived(String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + " :onOfferwallEventNotificationReceived ", 1);
        }
    }

    @Override // com.ironsource.sdk.listeners.OnOfferWallListener
    public void onOWShowSuccess(String str) {
        if (TextUtils.isEmpty(str)) {
            log(IronSourceLogger.IronSourceTag.ADAPTER_API, getProviderName() + ":onOWShowSuccess()", 1);
        } else {
            log(IronSourceLogger.IronSourceTag.ADAPTER_API, getProviderName() + ":onOWShowSuccess(placementId:" + str + ")", 1);
        }
        InternalOfferwallListener internalOfferwallListener = this.mOfferwallListener;
        if (internalOfferwallListener != null) {
            internalOfferwallListener.onOfferwallOpened();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnOfferWallListener
    public void onOWShowFail(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onOWShowFail ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onOfferwallShowFailed(ErrorBuilder.buildGenericError(str));
        }
    }

    @Override // com.ironsource.sdk.listeners.OnOfferWallListener
    public boolean onOWAdCredited(int i, int i2, boolean z) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onOWAdCredited ", 1);
        InternalOfferwallListener internalOfferwallListener = this.mOfferwallListener;
        return internalOfferwallListener != null && internalOfferwallListener.onOfferwallAdCredited(i, i2, z);
    }

    @Override // com.ironsource.sdk.listeners.OnOfferWallListener
    public void onOWAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onOWAdClosed ", 1);
        InternalOfferwallListener internalOfferwallListener = this.mOfferwallListener;
        if (internalOfferwallListener != null) {
            internalOfferwallListener.onOfferwallClosed();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnOfferWallListener
    public void onGetOWCreditsFailed(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onGetOWCreditsFailed ", 1);
        if (this.mOfferwallListener != null) {
            this.mOfferwallListener.onGetOfferwallCreditsFailed(ErrorBuilder.buildGenericError(str));
        }
    }

    @Override // com.ironsource.sdk.listeners.OnBannerListener
    public void onBannerInitSuccess() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onBannerInitSuccess ", 1);
        for (BannerSmashListener bannerSmashListener : this.mAllBannerSmashes) {
            if (bannerSmashListener != null) {
                bannerSmashListener.onBannerInitSuccess();
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnBannerListener
    public void onBannerInitFailed(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onBannerInitFailed ", 1);
        for (BannerSmashListener bannerSmashListener : this.mAllBannerSmashes) {
            if (bannerSmashListener != null) {
                bannerSmashListener.onBannerInitFailed(ErrorBuilder.buildInitFailedError(str, IronSourceConstants.BANNER_AD_UNIT));
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnBannerListener
    public void onBannerClick() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onBannerAdClicked ", 1);
        if (this.mActiveBannerSmash != null) {
            this.mActiveBannerSmash.onBannerAdClicked();
        }
    }

    @Override // com.ironsource.sdk.listeners.OnBannerListener
    public void onBannerLoadSuccess() {
        ISNAdView iSNAdView;
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onBannerLoadSuccess ", 1);
        for (BannerSmashListener bannerSmashListener : this.mAllBannerSmashes) {
            if (bannerSmashListener != null && (iSNAdView = this.mIsnAdView) != null) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iSNAdView.getAdViewSize().getWidth(), this.mIsnAdView.getAdViewSize().getHeight());
                layoutParams.gravity = 17;
                bannerSmashListener.onBannerAdLoaded(this.mIsnAdView, layoutParams);
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.OnBannerListener
    public void onBannerLoadFail(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, getProviderName() + ": onBannerLoadFail", 1);
        for (BannerSmashListener bannerSmashListener : this.mAllBannerSmashes) {
            if (bannerSmashListener != null) {
                bannerSmashListener.onBannerAdLoadFailed(ErrorBuilder.buildInitFailedError(str, IronSourceConstants.BANNER_AD_UNIT));
            }
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    protected void addBannerListener(BannerSmashListener bannerSmashListener) {
        this.mAllBannerSmashes.add(bannerSmashListener);
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    protected void removeBannerListener(BannerSmashListener bannerSmashListener) {
        this.mAllBannerSmashes.remove(bannerSmashListener);
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    protected void setMediationState(AbstractSmash.MEDIATION_STATE mediation_state, String str) {
        if (this.mSSAPublisher != null) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, getProviderName() + ": setMediationState(" + str + " , " + getProviderName() + " , " + mediation_state.getValue() + ")", 1);
            this.mSSAPublisher.setMediationState(str, getProviderName(), mediation_state.getValue());
        }
    }

    @Override // com.ironsource.mediationsdk.AbstractAdapter
    protected void setConsent(boolean z) {
        this.mDidSetConsent = true;
        this.mConsent = z;
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
