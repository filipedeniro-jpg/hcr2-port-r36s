package com.ironsource.sdk.agent;

import android.app.Activity;
import android.content.Context;
import android.content.MutableContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.sdk.ISAdSize;
import com.ironsource.sdk.ISNAdView.ISNAdView;
import com.ironsource.sdk.IronSourceAdInstance;
import com.ironsource.sdk.IronSourceNetworkAPI;
import com.ironsource.sdk.SSAPublisher;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.controller.BannerJSAdapter;
import com.ironsource.sdk.controller.CommandExecutor;
import com.ironsource.sdk.controller.DemandSourceManager;
import com.ironsource.sdk.controller.IronSourceWebView;
import com.ironsource.sdk.controller.MOATJSAdapter;
import com.ironsource.sdk.controller.PermissionsJSAdapter;
import com.ironsource.sdk.controller.TokenJSAdapter;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.data.SSASession;
import com.ironsource.sdk.listeners.OnBannerListener;
import com.ironsource.sdk.listeners.OnInterstitialListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.listeners.OnRewardedVideoListener;
import com.ironsource.sdk.listeners.internals.DSAdProductListener;
import com.ironsource.sdk.listeners.internals.DSBannerListener;
import com.ironsource.sdk.listeners.internals.DSInterstitialListener;
import com.ironsource.sdk.listeners.internals.DSRewardedVideoListener;
import com.ironsource.sdk.service.TokenService;
import com.ironsource.sdk.utils.DeviceProperties;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import com.ironsource.sdk.utils.IronSourceQaProperties;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class IronSourceAdsPublisherAgent implements SSAPublisher, DSRewardedVideoListener, DSInterstitialListener, DSAdProductListener, DSBannerListener, IronSourceNetworkAPI {
    private static final String TAG = "IronSourceAdsPublisherAgent";
    private static MutableContextWrapper mutableContextWrapper;
    private static IronSourceAdsPublisherAgent sInstance;
    private final String SUPERSONIC_ADS = IronSourceConstants.SUPERSONIC_CONFIG_NAME;
    private long adViewContainerCounter;
    private String mApplicationKey;
    private BannerJSAdapter mBannerJSAdapter;
    private CommandExecutor mCommandExecutor;
    private DemandSourceManager mDemandSourceManager;
    private TokenService mTokenService;
    private String mUserId;
    private SSASession session;
    private IronSourceWebView wvc;

    private IronSourceAdsPublisherAgent(Activity activity, int i) {
        initPublisherAgent(activity);
    }

    IronSourceAdsPublisherAgent(String str, String str2, Activity activity) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        initPublisherAgent(activity);
    }

    private void initPublisherAgent(Activity activity) {
        this.mTokenService = createToken(activity);
        this.mCommandExecutor = new CommandExecutor();
        IronSourceSharedPrefHelper.getSupersonicPrefHelper(activity);
        this.mDemandSourceManager = new DemandSourceManager();
        Logger.enableLogging(SDKUtils.getDebugMode());
        Logger.i(TAG, "C'tor");
        mutableContextWrapper = new MutableContextWrapper(activity);
        this.adViewContainerCounter = 0L;
        createWebView(activity);
        startSession(activity);
    }

    private TokenService createToken(Activity activity) {
        TokenService tokenService = new TokenService();
        tokenService.collectApplicationUserId(this.mUserId);
        tokenService.collectApplicationKey(this.mApplicationKey);
        tokenService.collectAdvertisingID(activity);
        tokenService.collectDataFromActivity(activity);
        tokenService.collectDataFromDevice(activity);
        tokenService.collectDataFromControllerConfig(SDKUtils.getControllerConfig());
        tokenService.collectDataFromExternalParams(SDKUtils.getInitSDKParams());
        collectQaParameters(tokenService);
        return tokenService;
    }

    private void collectQaParameters(TokenService tokenService) {
        if (IronSourceQaProperties.isInitialized()) {
            tokenService.collectDataFromExternalParams(IronSourceQaProperties.getInstance().getParameters());
        }
    }

    private void createWebView(final Activity activity) {
        activity.runOnUiThread(new Runnable() { // from class: com.ironsource.sdk.agent.IronSourceAdsPublisherAgent.1
            @Override // java.lang.Runnable
            public void run() {
                IronSourceAdsPublisherAgent.this.wvc = new IronSourceWebView(IronSourceAdsPublisherAgent.mutableContextWrapper, IronSourceAdsPublisherAgent.this.mDemandSourceManager);
                IronSourceAdsPublisherAgent.this.wvc.addTokenJSInterface(new TokenJSAdapter(IronSourceAdsPublisherAgent.this.mTokenService));
                IronSourceAdsPublisherAgent.this.wvc.addMoatJSInterface(new MOATJSAdapter(activity.getApplication()));
                IronSourceAdsPublisherAgent.this.wvc.addPermissionsJSInterface(new PermissionsJSAdapter(activity.getApplicationContext()));
                IronSourceAdsPublisherAgent.this.mBannerJSAdapter = new BannerJSAdapter();
                IronSourceAdsPublisherAgent.this.mBannerJSAdapter.setCommunicationWithController(IronSourceAdsPublisherAgent.this.wvc.getControllerDelegate());
                IronSourceAdsPublisherAgent.this.wvc.addBannerJSInterface(IronSourceAdsPublisherAgent.this.mBannerJSAdapter);
                IronSourceAdsPublisherAgent.this.wvc.registerConnectionReceiver(activity);
                IronSourceAdsPublisherAgent.this.wvc.setDebugMode(SDKUtils.getDebugMode());
                IronSourceAdsPublisherAgent.this.wvc.downloadController();
                IronSourceAdsPublisherAgent.this.mCommandExecutor.setReady();
                IronSourceAdsPublisherAgent.this.mCommandExecutor.purgeDelayedCommands();
            }
        });
    }

    public static IronSourceNetworkAPI createInstance(Activity activity, String str, String str2) {
        return getInstance(str, str2, activity);
    }

    public static synchronized IronSourceNetworkAPI getInstance(String str, String str2, Activity activity) {
        if (sInstance == null) {
            sInstance = new IronSourceAdsPublisherAgent(str, str2, activity);
        } else {
            mutableContextWrapper.setBaseContext(activity);
            sInstance.updateApplicationKey(str);
            sInstance.updateUserId(str2);
        }
        return sInstance;
    }

    private void updateApplicationKey(String str) {
        this.mTokenService.collectApplicationKey(str);
    }

    private void updateUserId(String str) {
        this.mTokenService.collectApplicationUserId(str);
    }

    public static synchronized IronSourceAdsPublisherAgent getInstance(Activity activity) throws Exception {
        return getInstance(activity, 0);
    }

    public static synchronized IronSourceAdsPublisherAgent getInstance(Activity activity, int i) throws Exception {
        Logger.i(TAG, "getInstance()");
        if (sInstance == null) {
            sInstance = new IronSourceAdsPublisherAgent(activity, i);
        } else {
            mutableContextWrapper.setBaseContext(activity);
        }
        return sInstance;
    }

    public IronSourceWebView getWebViewController() {
        return this.wvc;
    }

    private OnRewardedVideoListener getAdProductListenerAsRVListener(DemandSource demandSource) {
        if (demandSource == null) {
            return null;
        }
        return (OnRewardedVideoListener) demandSource.getListener();
    }

    private OnInterstitialListener getAdProductListenerAsISListener(DemandSource demandSource) {
        if (demandSource == null) {
            return null;
        }
        return (OnInterstitialListener) demandSource.getListener();
    }

    private OnBannerListener getAdProductListenerAsBNListener(DemandSource demandSource) {
        if (demandSource == null) {
            return null;
        }
        return (OnBannerListener) demandSource.getListener();
    }

    private void startSession(Context context) {
        this.session = new SSASession(context, SSASession.SessionType.launched);
    }

    public void resumeSession(Context context) {
        this.session = new SSASession(context, SSASession.SessionType.backFromBG);
    }

    private void endSession() {
        SSASession sSASession = this.session;
        if (sSASession != null) {
            sSASession.endSession();
            IronSourceSharedPrefHelper.getSupersonicPrefHelper().addSession(this.session);
            this.session = null;
        }
    }

    @Override // com.ironsource.sdk.SSAPublisher
    public void initRewardedVideo(String str, String str2, String str3, Map<String, String> map, OnRewardedVideoListener onRewardedVideoListener) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        this.wvc.initRewardedVideo(str, str2, this.mDemandSourceManager.createDemandSource(SSAEnums.ProductType.RewardedVideo, str3, map, onRewardedVideoListener), this);
    }

    @Override // com.ironsource.sdk.SSAPublisher
    public void showRewardedVideo(JSONObject jSONObject) {
        this.wvc.showRewardedVideo(jSONObject);
    }

    @Override // com.ironsource.sdk.SSAPublisher
    public void initOfferWall(String str, String str2, Map<String, String> map, OnOfferWallListener onOfferWallListener) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        this.wvc.initOfferWall(str, str2, map, onOfferWallListener);
    }

    @Override // com.ironsource.sdk.IronSourceNetworkAds
    public void initOfferWall(Map<String, String> map, OnOfferWallListener onOfferWallListener) {
        this.wvc.initOfferWall(this.mApplicationKey, this.mUserId, map, onOfferWallListener);
    }

    @Override // com.ironsource.sdk.SSAPublisher, com.ironsource.sdk.IronSourceNetworkAds
    public void showOfferWall(Map<String, String> map) {
        this.wvc.showOfferWall(map);
    }

    @Override // com.ironsource.sdk.SSAPublisher
    public void getOfferWallCredits(String str, String str2, OnOfferWallListener onOfferWallListener) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        this.wvc.getOfferWallCredits(str, str2, onOfferWallListener);
    }

    @Override // com.ironsource.sdk.IronSourceNetworkAds
    public void getOfferWallCredits(OnOfferWallListener onOfferWallListener) {
        this.wvc.getOfferWallCredits(this.mApplicationKey, this.mUserId, onOfferWallListener);
    }

    @Override // com.ironsource.sdk.SSAPublisher
    public void initInterstitial(String str, String str2, String str3, Map<String, String> map, OnInterstitialListener onInterstitialListener) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        this.wvc.initInterstitial(str, str2, this.mDemandSourceManager.createDemandSource(SSAEnums.ProductType.Interstitial, str3, map, onInterstitialListener), this);
    }

    @Override // com.ironsource.sdk.SSAPublisher
    public void loadInterstitial(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        String strOptString = jSONObject.optString("demandSourceName");
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        this.wvc.loadInterstitial(strOptString);
    }

    @Override // com.ironsource.sdk.SSAPublisher
    public boolean isInterstitialAdAvailable(String str) {
        return this.wvc.isInterstitialAdAvailable(str);
    }

    @Override // com.ironsource.sdk.SSAPublisher
    public void showInterstitial(JSONObject jSONObject) {
        this.wvc.showInterstitial(jSONObject);
    }

    @Override // com.ironsource.sdk.SSAPublisher
    public void initBanner(String str, String str2, String str3, Map<String, String> map, OnBannerListener onBannerListener) {
        this.mApplicationKey = str;
        this.mUserId = str2;
        this.wvc.initBanner(str, str2, this.mDemandSourceManager.createDemandSource(SSAEnums.ProductType.Banner, str3, map, onBannerListener), this);
    }

    @Override // com.ironsource.sdk.IronSourceNetworkAds
    public void initBanner(String str, Map<String, String> map, OnBannerListener onBannerListener) {
        this.wvc.initBanner(this.mApplicationKey, this.mUserId, this.mDemandSourceManager.createDemandSource(SSAEnums.ProductType.Banner, str, map, onBannerListener), this);
    }

    @Override // com.ironsource.sdk.SSAPublisher, com.ironsource.sdk.IronSourceNetworkAds
    public void loadBanner(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.wvc.loadBanner(jSONObject);
        }
    }

    @Override // com.ironsource.sdk.SSAPublisher, com.ironsource.sdk.IronSourceNetworkAPI
    public void onResume(Activity activity) {
        mutableContextWrapper.setBaseContext(activity);
        this.wvc.enterForeground();
        this.wvc.registerConnectionReceiver(activity);
        if (this.session == null) {
            resumeSession(activity);
        }
    }

    @Override // com.ironsource.sdk.SSAPublisher, com.ironsource.sdk.IronSourceNetworkAPI
    public void onPause(Activity activity) {
        try {
            this.wvc.enterBackground();
            this.wvc.unregisterConnectionReceiver(activity);
            endSession();
        } catch (Exception e) {
            e.printStackTrace();
            new IronSourceAsyncHttpRequestTask().execute(Constants.NATIVE_EXCEPTION_BASE_URL + e.getStackTrace()[0].getMethodName());
        }
    }

    @Override // com.ironsource.sdk.SSAPublisher, com.ironsource.sdk.IronSourceNetworkAPI
    public void release(Activity activity) {
        try {
            Logger.i(TAG, "release()");
            DeviceProperties.release();
            this.wvc.unregisterConnectionReceiver(activity);
            if (Looper.getMainLooper().equals(Looper.myLooper())) {
                this.wvc.destroy();
                this.wvc = null;
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.sdk.agent.IronSourceAdsPublisherAgent.2
                    @Override // java.lang.Runnable
                    public void run() {
                        IronSourceAdsPublisherAgent.this.wvc.destroy();
                        IronSourceAdsPublisherAgent.this.wvc = null;
                    }
                });
            }
        } catch (Exception unused) {
        }
        sInstance = null;
        endSession();
    }

    @Override // com.ironsource.sdk.listeners.internals.DSAdProductListener
    public void onAdProductInitSuccess(SSAEnums.ProductType productType, String str, AdUnitsReady adUnitsReady) {
        OnBannerListener adProductListenerAsBNListener;
        DemandSource demandSourceByName = getDemandSourceByName(productType, str);
        if (demandSourceByName != null) {
            demandSourceByName.setDemandSourceInitState(2);
            if (productType == SSAEnums.ProductType.RewardedVideo) {
                OnRewardedVideoListener adProductListenerAsRVListener = getAdProductListenerAsRVListener(demandSourceByName);
                if (adProductListenerAsRVListener != null) {
                    adProductListenerAsRVListener.onRVInitSuccess(adUnitsReady);
                    return;
                }
                return;
            }
            if (productType == SSAEnums.ProductType.Interstitial) {
                OnInterstitialListener adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName);
                if (adProductListenerAsISListener != null) {
                    adProductListenerAsISListener.onInterstitialInitSuccess();
                    return;
                }
                return;
            }
            if (productType != SSAEnums.ProductType.Banner || (adProductListenerAsBNListener = getAdProductListenerAsBNListener(demandSourceByName)) == null) {
                return;
            }
            adProductListenerAsBNListener.onBannerInitSuccess();
        }
    }

    @Override // com.ironsource.sdk.listeners.internals.DSAdProductListener
    public void onAdProductInitFailed(SSAEnums.ProductType productType, String str, String str2) {
        OnBannerListener adProductListenerAsBNListener;
        DemandSource demandSourceByName = getDemandSourceByName(productType, str);
        if (demandSourceByName != null) {
            demandSourceByName.setDemandSourceInitState(3);
            if (productType == SSAEnums.ProductType.RewardedVideo) {
                OnRewardedVideoListener adProductListenerAsRVListener = getAdProductListenerAsRVListener(demandSourceByName);
                if (adProductListenerAsRVListener != null) {
                    adProductListenerAsRVListener.onRVInitFail(str2);
                    return;
                }
                return;
            }
            if (productType == SSAEnums.ProductType.Interstitial) {
                OnInterstitialListener adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName);
                if (adProductListenerAsISListener != null) {
                    adProductListenerAsISListener.onInterstitialInitFailed(str2);
                    return;
                }
                return;
            }
            if (productType != SSAEnums.ProductType.Banner || (adProductListenerAsBNListener = getAdProductListenerAsBNListener(demandSourceByName)) == null) {
                return;
            }
            adProductListenerAsBNListener.onBannerInitFailed(str2);
        }
    }

    @Override // com.ironsource.sdk.listeners.internals.DSRewardedVideoListener
    public void onRVNoMoreOffers(String str) {
        OnRewardedVideoListener adProductListenerAsRVListener;
        DemandSource demandSourceByName = getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, str);
        if (demandSourceByName == null || (adProductListenerAsRVListener = getAdProductListenerAsRVListener(demandSourceByName)) == null) {
            return;
        }
        adProductListenerAsRVListener.onRVNoMoreOffers();
    }

    @Override // com.ironsource.sdk.listeners.internals.DSRewardedVideoListener
    public void onRVAdCredited(String str, int i) {
        OnRewardedVideoListener adProductListenerAsRVListener;
        DemandSource demandSourceByName = getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, str);
        if (demandSourceByName == null || (adProductListenerAsRVListener = getAdProductListenerAsRVListener(demandSourceByName)) == null) {
            return;
        }
        adProductListenerAsRVListener.onRVAdCredited(i);
    }

    @Override // com.ironsource.sdk.listeners.internals.DSAdProductListener
    public void onAdProductClose(SSAEnums.ProductType productType, String str) {
        OnInterstitialListener adProductListenerAsISListener;
        DemandSource demandSourceByName = getDemandSourceByName(productType, str);
        if (demandSourceByName != null) {
            if (productType == SSAEnums.ProductType.RewardedVideo) {
                OnRewardedVideoListener adProductListenerAsRVListener = getAdProductListenerAsRVListener(demandSourceByName);
                if (adProductListenerAsRVListener != null) {
                    adProductListenerAsRVListener.onRVAdClosed();
                    return;
                }
                return;
            }
            if (productType != SSAEnums.ProductType.Interstitial || (adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName)) == null) {
                return;
            }
            adProductListenerAsISListener.onInterstitialClose();
        }
    }

    @Override // com.ironsource.sdk.listeners.internals.DSRewardedVideoListener
    public void onRVShowFail(String str, String str2) {
        OnRewardedVideoListener adProductListenerAsRVListener;
        DemandSource demandSourceByName = getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, str);
        if (demandSourceByName == null || (adProductListenerAsRVListener = getAdProductListenerAsRVListener(demandSourceByName)) == null) {
            return;
        }
        adProductListenerAsRVListener.onRVShowFail(str2);
    }

    @Override // com.ironsource.sdk.listeners.internals.DSAdProductListener
    public void onAdProductClick(SSAEnums.ProductType productType, String str) {
        OnBannerListener adProductListenerAsBNListener;
        DemandSource demandSourceByName = getDemandSourceByName(productType, str);
        if (demandSourceByName != null) {
            if (productType == SSAEnums.ProductType.RewardedVideo) {
                OnRewardedVideoListener adProductListenerAsRVListener = getAdProductListenerAsRVListener(demandSourceByName);
                if (adProductListenerAsRVListener != null) {
                    adProductListenerAsRVListener.onRVAdClicked();
                    return;
                }
                return;
            }
            if (productType == SSAEnums.ProductType.Interstitial) {
                OnInterstitialListener adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName);
                if (adProductListenerAsISListener != null) {
                    adProductListenerAsISListener.onInterstitialClick();
                    return;
                }
                return;
            }
            if (productType != SSAEnums.ProductType.Banner || (adProductListenerAsBNListener = getAdProductListenerAsBNListener(demandSourceByName)) == null) {
                return;
            }
            adProductListenerAsBNListener.onBannerClick();
        }
    }

    @Override // com.ironsource.sdk.listeners.internals.DSAdProductListener
    public void onAdProductEventNotificationReceived(SSAEnums.ProductType productType, String str, String str2, JSONObject jSONObject) {
        OnRewardedVideoListener adProductListenerAsRVListener;
        DemandSource demandSourceByName = getDemandSourceByName(productType, str);
        if (demandSourceByName != null) {
            try {
                if (productType == SSAEnums.ProductType.Interstitial) {
                    OnInterstitialListener adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName);
                    if (adProductListenerAsISListener != null) {
                        jSONObject.put("demandSourceName", str);
                        adProductListenerAsISListener.onInterstitialEventNotificationReceived(str2, jSONObject);
                    }
                } else if (productType == SSAEnums.ProductType.RewardedVideo && (adProductListenerAsRVListener = getAdProductListenerAsRVListener(demandSourceByName)) != null) {
                    jSONObject.put("demandSourceName", str);
                    adProductListenerAsRVListener.onRVEventNotificationReceived(str2, jSONObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ironsource.sdk.listeners.internals.DSAdProductListener
    public void onAdProductOpen(SSAEnums.ProductType productType, String str) {
        OnRewardedVideoListener adProductListenerAsRVListener;
        DemandSource demandSourceByName = getDemandSourceByName(productType, str);
        if (demandSourceByName != null) {
            if (productType == SSAEnums.ProductType.Interstitial) {
                OnInterstitialListener adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName);
                if (adProductListenerAsISListener != null) {
                    adProductListenerAsISListener.onInterstitialOpen();
                    return;
                }
                return;
            }
            if (productType != SSAEnums.ProductType.RewardedVideo || (adProductListenerAsRVListener = getAdProductListenerAsRVListener(demandSourceByName)) == null) {
                return;
            }
            adProductListenerAsRVListener.onRVAdOpened();
        }
    }

    @Override // com.ironsource.sdk.listeners.internals.DSInterstitialListener
    public void onInterstitialLoadSuccess(String str) {
        OnInterstitialListener adProductListenerAsISListener;
        DemandSource demandSourceByName = getDemandSourceByName(SSAEnums.ProductType.Interstitial, str);
        if (demandSourceByName == null || (adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName)) == null) {
            return;
        }
        adProductListenerAsISListener.onInterstitialLoadSuccess();
    }

    @Override // com.ironsource.sdk.listeners.internals.DSInterstitialListener
    public void onInterstitialLoadFailed(String str, String str2) {
        OnInterstitialListener adProductListenerAsISListener;
        DemandSource demandSourceByName = getDemandSourceByName(SSAEnums.ProductType.Interstitial, str);
        if (demandSourceByName == null || (adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName)) == null) {
            return;
        }
        adProductListenerAsISListener.onInterstitialLoadFailed(str2);
    }

    @Override // com.ironsource.sdk.listeners.internals.DSInterstitialListener
    public void onInterstitialShowSuccess(String str) {
        OnInterstitialListener adProductListenerAsISListener;
        DemandSource demandSourceByName = getDemandSourceByName(SSAEnums.ProductType.Interstitial, str);
        if (demandSourceByName == null || (adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName)) == null) {
            return;
        }
        adProductListenerAsISListener.onInterstitialShowSuccess();
    }

    @Override // com.ironsource.sdk.listeners.internals.DSInterstitialListener
    public void onInterstitialShowFailed(String str, String str2) {
        OnInterstitialListener adProductListenerAsISListener;
        DemandSource demandSourceByName = getDemandSourceByName(SSAEnums.ProductType.Interstitial, str);
        if (demandSourceByName == null || (adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName)) == null) {
            return;
        }
        adProductListenerAsISListener.onInterstitialShowFailed(str2);
    }

    @Override // com.ironsource.sdk.listeners.internals.DSInterstitialListener
    public void onInterstitialAdRewarded(String str, int i) {
        DemandSource demandSourceByName = getDemandSourceByName(SSAEnums.ProductType.Interstitial, str);
        OnInterstitialListener adProductListenerAsISListener = getAdProductListenerAsISListener(demandSourceByName);
        if (demandSourceByName == null || adProductListenerAsISListener == null) {
            return;
        }
        adProductListenerAsISListener.onInterstitialAdRewarded(str, i);
    }

    private DemandSource getDemandSourceByName(SSAEnums.ProductType productType, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.mDemandSourceManager.getDemandSourceById(productType, str);
    }

    @Override // com.ironsource.sdk.SSAPublisher
    public void setMediationState(String str, String str2, int i) {
        SSAEnums.ProductType productType;
        DemandSource demandSourceById;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (productType = SDKUtils.getProductType(str)) == null || (demandSourceById = this.mDemandSourceManager.getDemandSourceById(productType, str2)) == null) {
            return;
        }
        demandSourceById.setMediationState(i);
    }

    @Override // com.ironsource.sdk.SSAPublisher, com.ironsource.sdk.IronSourceNetworkAPI
    public void updateConsentInfo(final JSONObject jSONObject) {
        updateConsentInToken(jSONObject);
        this.mCommandExecutor.executeCommand(new Runnable() { // from class: com.ironsource.sdk.agent.IronSourceAdsPublisherAgent.3
            @Override // java.lang.Runnable
            public void run() {
                IronSourceAdsPublisherAgent.this.wvc.updateConsentInfo(jSONObject);
            }
        });
    }

    private void updateConsentInToken(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has(Constants.RequestParameters.GDPR_CONSENT_STATUS)) {
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(Constants.RequestParameters.CONSENT, Boolean.valueOf(jSONObject.getString(Constants.RequestParameters.GDPR_CONSENT_STATUS)).booleanValue());
            this.mTokenService.updateData(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.ironsource.sdk.SSAPublisher, com.ironsource.sdk.IronSourceNetworkAds
    public ISNAdView createBanner(Activity activity, ISAdSize iSAdSize) {
        String str = "SupersonicAds_" + this.adViewContainerCounter;
        this.adViewContainerCounter++;
        ISNAdView iSNAdView = new ISNAdView(activity, str, iSAdSize);
        this.mBannerJSAdapter.setCommunicationWithAdView(iSNAdView);
        return iSNAdView;
    }

    @Override // com.ironsource.sdk.listeners.internals.DSBannerListener
    public void onBannerLoadSuccess(String str) {
        OnBannerListener adProductListenerAsBNListener;
        DemandSource demandSourceByName = getDemandSourceByName(SSAEnums.ProductType.Banner, str);
        if (demandSourceByName == null || (adProductListenerAsBNListener = getAdProductListenerAsBNListener(demandSourceByName)) == null) {
            return;
        }
        adProductListenerAsBNListener.onBannerLoadSuccess();
    }

    @Override // com.ironsource.sdk.listeners.internals.DSBannerListener
    public void onBannerLoadFail(String str, String str2) {
        OnBannerListener adProductListenerAsBNListener;
        DemandSource demandSourceByName = getDemandSourceByName(SSAEnums.ProductType.Banner, str);
        if (demandSourceByName == null || (adProductListenerAsBNListener = getAdProductListenerAsBNListener(demandSourceByName)) == null) {
            return;
        }
        adProductListenerAsBNListener.onBannerLoadFail(str2);
    }

    @Override // com.ironsource.sdk.IronSourceNetworkAds
    public void loadAd(IronSourceAdInstance ironSourceAdInstance, Map<String, String> map) {
        Logger.d(TAG, "loadAd " + ironSourceAdInstance.getId());
        if (ironSourceAdInstance.isInAppBidding()) {
            loadInAppBiddingAd(ironSourceAdInstance, map);
        } else {
            loadInstance(ironSourceAdInstance, map);
        }
    }

    private void loadInAppBiddingAd(IronSourceAdInstance ironSourceAdInstance, Map<String, String> map) {
        try {
            map = decodeADM(map);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.d(TAG, "loadInAppBiddingAd failed decoding ADM " + e.getMessage());
        }
        loadInstance(ironSourceAdInstance, map);
    }

    private void loadInstance(IronSourceAdInstance ironSourceAdInstance, Map<String, String> map) {
        if (ironSourceAdInstance.isInitialized()) {
            loadInitializedInstance(ironSourceAdInstance, map);
        } else {
            loadUninitializedInstance(ironSourceAdInstance, map);
        }
    }

    private Map<String, String> decodeADM(Map<String, String> map) {
        map.put(Constants.ParametersKeys.ADM, SDKUtils.decodeString(map.get(Constants.ParametersKeys.ADM)));
        return map;
    }

    private void loadInitializedInstance(final IronSourceAdInstance ironSourceAdInstance, final Map<String, String> map) {
        Logger.d(TAG, "loadOnInitializedInstance " + ironSourceAdInstance.getId());
        this.mCommandExecutor.executeCommand(new Runnable() { // from class: com.ironsource.sdk.agent.IronSourceAdsPublisherAgent.4
            @Override // java.lang.Runnable
            public void run() {
                DemandSource demandSourceById = IronSourceAdsPublisherAgent.this.mDemandSourceManager.getDemandSourceById(SSAEnums.ProductType.Interstitial, ironSourceAdInstance.getId());
                if (demandSourceById != null) {
                    IronSourceAdsPublisherAgent.this.wvc.loadInterstitial(demandSourceById, map);
                }
            }
        });
    }

    private void loadUninitializedInstance(final IronSourceAdInstance ironSourceAdInstance, final Map<String, String> map) {
        Logger.d(TAG, "loadOnNewInstance " + ironSourceAdInstance.getId());
        this.mCommandExecutor.executeCommand(new Runnable() { // from class: com.ironsource.sdk.agent.IronSourceAdsPublisherAgent.5
            @Override // java.lang.Runnable
            public void run() {
                DemandSource demandSourceCreateDemandSource = IronSourceAdsPublisherAgent.this.mDemandSourceManager.createDemandSource(SSAEnums.ProductType.Interstitial, ironSourceAdInstance);
                IronSourceAdsPublisherAgent.this.wvc.initInterstitial(IronSourceAdsPublisherAgent.this.mApplicationKey, IronSourceAdsPublisherAgent.this.mUserId, demandSourceCreateDemandSource, IronSourceAdsPublisherAgent.this);
                ironSourceAdInstance.setInitialized(true);
                IronSourceAdsPublisherAgent.this.wvc.loadInterstitial(demandSourceCreateDemandSource, map);
            }
        });
    }

    @Override // com.ironsource.sdk.IronSourceNetworkAds
    public void showAd(IronSourceAdInstance ironSourceAdInstance, final Map<String, String> map) {
        Logger.i(TAG, "showAd " + ironSourceAdInstance.getId());
        final DemandSource demandSourceById = this.mDemandSourceManager.getDemandSourceById(SSAEnums.ProductType.Interstitial, ironSourceAdInstance.getId());
        if (demandSourceById == null) {
            return;
        }
        this.mCommandExecutor.executeCommand(new Runnable() { // from class: com.ironsource.sdk.agent.IronSourceAdsPublisherAgent.6
            @Override // java.lang.Runnable
            public void run() {
                IronSourceAdsPublisherAgent.this.wvc.showInterstitial(demandSourceById, map);
            }
        });
    }

    @Override // com.ironsource.sdk.IronSourceNetworkAds
    public boolean isAdAvailable(IronSourceAdInstance ironSourceAdInstance) {
        if (this.wvc == null) {
            return false;
        }
        Logger.d(TAG, "isAdAvailable " + ironSourceAdInstance.getId());
        DemandSource demandSourceById = this.mDemandSourceManager.getDemandSourceById(SSAEnums.ProductType.Interstitial, ironSourceAdInstance.getId());
        if (demandSourceById == null) {
            return false;
        }
        return demandSourceById.getAvailabilityState();
    }

    @Override // com.ironsource.sdk.IronSourceNetworkAds
    public String getToken(Context context) {
        TokenService tokenService = this.mTokenService;
        if (tokenService == null) {
            return null;
        }
        tokenService.collectDataFromDevice(context);
        return this.mTokenService.getToken();
    }
}
