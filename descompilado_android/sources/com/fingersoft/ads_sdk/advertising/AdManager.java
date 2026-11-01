package com.fingersoft.ads_sdk.advertising;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.RelativeLayout;
import com.appsflyer.share.Constants;
import com.fingersoft.ads_sdk.advertising.Comparators.AdProviderComparator;
import com.fingersoft.ads_sdk.advertising.analytics.IAnalyticsProvider;
import com.fingersoft.ads_sdk.advertising.json.IResultContainer;
import com.fingersoft.ads_sdk.advertising.network.AdNetworkListener;
import com.fingersoft.ads_sdk.advertising.network.AdNetworkMonitor;
import com.fingersoft.ads_sdk.advertising.network.NetworkChangeListener;
import com.fingersoft.ads_sdk.advertising.providers.AdProvider;
import com.fingersoft.ads_sdk.advertising.providers.Interstitials.AdProviderAmazonInterstitial;
import com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialListener;
import com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider;
import com.fingersoft.ads_sdk.advertising.providers.video.IronSourceVideoAds;
import com.fingersoft.ads_sdk.advertising.statistics.AdStatisticRecorder;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;
import com.fingersoft.ads_sdk.advertising.video.IVideoAdListener;
import com.fingersoft.ads_sdk.advertising.video.VideoAdProvider;
import com.fingersoft.ads_sdk.advertising.video.VideoManager;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class AdManager implements NetworkChangeListener {
    private static final int AD_PRIORITY_RELOAD_TIME_MS = 3600000;
    private static final int AD_PROVIDER_RESET_TIME_MS = 300000;
    private static final int AD_REFRESH_TIME_MS = 60000;
    private static final int ALL_PROVIDERS_FAILED_WAIT_TIME_MS = 900000;
    static String DEFAULT_BANNER_PRIORITY = "admob2,facebook,mopub,smaato,millennial,in_mobi,admob,inneractive,smaato2,in_mobi2,facebook2";
    static String DEFAULT_INTERSTITIAL_PRIORITY = "mopubInt,admobInt,chartboostInt,leadboltInt,facebookInt,millennialInt,smaatoInt,inneractiveInt,in_mobiInt,amazonInt";
    static String DEFAULT_VIDEO_PRIORITY = "adcolonyVid,chartboostVid,vungleVid,nativexVid,unityadsVid,supersonicVid";
    private static final int MSG_TYPE_AD_FREE_MODE = 9;
    private static final int MSG_TYPE_INITIALIZE_SDK = 10;
    private static final int MSG_TYPE_LOAD_INTERSTITIAL_AD = 11;
    private static final int MSG_TYPE_LOAD_INTERSTITIAL_AD_EVEN_IF_AD_FREE = 12;
    private static final int MSG_TYPE_NETWORK_OFF = 7;
    private static final int MSG_TYPE_NETWORK_ON = 8;
    private static final int MSG_TYPE_NOADS = 2;
    private static final int MSG_TYPE_PAUSE = 19;
    private static final int MSG_TYPE_PROVIDER_FAILED = 4;
    private static final int MSG_TYPE_PROVIDER_SUCCESS = 5;
    private static final int MSG_TYPE_RESUME = 20;
    private static final int MSG_TYPE_SHOWADS = 1;
    private static final int MSG_TYPE_SHOW_INTERSTITIAL_AD = 13;
    private static final int MSG_TYPE_SHOW_INTERSTITIAL_AD_EVEN_IF_AD_FREE = 14;
    private static final int MSG_TYPE_SHOW_NEXT_AD = 3;
    private static final int MSG_TYPE_SHOW_VIDEO_AD = 15;
    private static final int MSG_TYPE_SHOW_VIDEO_AD_EVEN_IF_AD_FREE = 16;
    private static final int MSG_TYPE_SPECIAL_AD_DISMISSED = 17;
    private static final int MSG_TYPE_SPECIAL_AD_TIMEOUT = 18;
    private static final int NUMBER_OF_MESSAGES = 21;
    private static final String PREFS_NAME = "ADMANAGER_SETTINGS";
    private static final int SPECIAL_AD_TIMEOUT = 60000;
    public static final String TAG = "ADS_SDK";
    private static final String mSdkVersion = "1.1";
    Activity mActivity;
    private RelativeLayout mAdTargetLayout;
    private String mAppVersionId;
    private int mCurrentVersionCode;
    private InterstitialManager mInterstitialManager;
    private int mLimitAdTrackingEnabled;
    private LinkListener mLinkListener;
    private AdNetworkListener mNetworkListener;
    private IFSAdSdkListener mSdkInitializedListener;
    private IVideoAdListener mUserVideoAdListener;
    private static long mStartupTimestamp = System.currentTimeMillis();
    private static int mMarketVariation = 0;
    private static Handler mHandler = null;
    private static int mStartupCount = 0;
    private static int mStartupCountWithCurrentVersion = 0;
    private static AdManagerState mAdManagerState = AdManagerState.NOT_INITIALIZED;
    private List<AdProvider> mAdProviders = new ArrayList();
    private AdProviderThread adProviderThread = null;
    private String mAdditionalRequestParams = "";
    private HashMap<String, String> mAdditionalReqParams = null;
    private String mAdvertisingId = null;
    ConnectivityManager mConnectivityManager = null;
    private ProviderLoader mProviderLoader = new ProviderLoader();
    private IResultContainer mContainer = null;
    private String mCurrentAdProviderName = "";
    private String mBaseAddress = "http://ads3.fingersoft.net:3000/";
    private boolean mIsStarted = false;
    private long mTimeCurrentAdShown = 0;
    private int mFailedProviderCountBeforeFirstSucceeded = 0;
    private String mFailedProvidersBeforeFirstSucceeded = "";
    private boolean mFirstBannerShown = false;
    private long mFirstBannerRequestTimestamp = 0;
    private String mLocale = null;
    private long mAdShowTime = 0;
    private long mAdHideTime = 0;
    private IAnalyticsProvider mTracker = null;
    boolean mHasNetworkConnection = true;
    private boolean mIsReady = false;
    private String mNetworkMcc = "";
    private String mTestAdProviderList = "";
    private String mSimMcc = "";
    private boolean TEST_MODE = false;
    private long mPauseTime = 0;
    private VideoManager mVideoAdManager = null;
    private int mXAlignment = 14;
    private int mYAlignment = 12;
    private boolean mVIPStatus = false;
    private boolean mWithoutAds = false;
    private InterstitialEventListener mInterstitialEventListener = new InterstitialEventListener() { // from class: com.fingersoft.ads_sdk.advertising.AdManager.1
        @Override // com.fingersoft.ads_sdk.advertising.InterstitialEventListener
        public void interactionWithAd() {
        }

        @Override // com.fingersoft.ads_sdk.advertising.InterstitialEventListener
        public void onLoad() {
        }

        @Override // com.fingersoft.ads_sdk.advertising.InterstitialEventListener
        public void onShow() {
        }

        @Override // com.fingersoft.ads_sdk.advertising.InterstitialEventListener
        public void receivedAd() {
        }

        @Override // com.fingersoft.ads_sdk.advertising.InterstitialEventListener
        public void failedToReceiveAd() {
            AdManager.mHandler.sendEmptyMessage(17);
        }

        @Override // com.fingersoft.ads_sdk.advertising.InterstitialEventListener
        public void onDismiss() {
            AdManager.mHandler.sendEmptyMessage(17);
        }

        @Override // com.fingersoft.ads_sdk.advertising.InterstitialEventListener
        public void allProvidersFailed() {
            AdManager.mHandler.sendEmptyMessage(17);
        }
    };
    private IVideoAdListener mVideoAdListener = new IVideoAdListener() { // from class: com.fingersoft.ads_sdk.advertising.AdManager.2
        @Override // com.fingersoft.ads_sdk.advertising.video.IVideoAdListener
        public void onAdFailed() {
            AdManager.mHandler.sendEmptyMessage(17);
            if (AdManager.this.mUserVideoAdListener == null) {
                return;
            }
            AdManager.this.mUserVideoAdListener.onAdFailed();
        }

        @Override // com.fingersoft.ads_sdk.advertising.video.IVideoAdListener
        public void onAdViewed() {
            AdManager.mHandler.sendEmptyMessage(17);
            if (AdManager.this.mUserVideoAdListener == null) {
                return;
            }
            AdManager.this.mUserVideoAdListener.onAdViewed();
        }

        @Override // com.fingersoft.ads_sdk.advertising.video.IVideoAdListener
        public void onAdCancelled() {
            AdManager.mHandler.sendEmptyMessage(17);
            if (AdManager.this.mUserVideoAdListener == null) {
                return;
            }
            AdManager.this.mUserVideoAdListener.onAdCancelled();
        }
    };
    private Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() { // from class: com.fingersoft.ads_sdk.advertising.AdManager.3
        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            AdUtils.log("UNCAUGHT EXCEPTION");
            AdUtils.log(th.getMessage());
        }
    };

    public enum AdManagerState {
        NOT_INITIALIZED,
        SHOW_ADS,
        NO_ADS,
        NETWORK_OFF_NO_ADS,
        NETWORK_OFF_WITH_ADS,
        SPECIAL_AD_RUNNING_NO_ADS,
        SPECIAL_AD_RUNNING_WITH_ADS,
        AD_FREE_NO_ADS,
        AD_FREE_CAN_SHOW_PROMOTION,
        PAUSED_NO_ADS,
        PAUSED_WITH_ADS
    }

    public boolean checkNetworkConnection() {
        return true;
    }

    static /* synthetic */ int access$1108(AdManager adManager) {
        int i = adManager.mFailedProviderCountBeforeFirstSucceeded;
        adManager.mFailedProviderCountBeforeFirstSucceeded = i + 1;
        return i;
    }

    public AdManager(Activity activity, RelativeLayout relativeLayout, LinkListener linkListener, IFSAdSdkListener iFSAdSdkListener) {
        this.mActivity = null;
        this.mAppVersionId = "NO_ID";
        this.mCurrentVersionCode = 0;
        this.mLinkListener = null;
        this.mInterstitialManager = null;
        this.mSdkInitializedListener = null;
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            this.mAppVersionId = packageInfo.versionCode + Constants.URL_PATH_DELIMITER + packageInfo.versionName;
            this.mCurrentVersionCode = packageInfo.versionCode;
            AdUtils.log("Current mCurrentVersionCode " + this.mAppVersionId);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        this.mActivity = activity;
        this.mAdTargetLayout = relativeLayout;
        this.mLinkListener = linkListener;
        this.mInterstitialManager = new InterstitialManager(this, this.mActivity, this.mInterstitialEventListener);
        this.mSdkInitializedListener = iFSAdSdkListener;
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public IResultContainer getContainer() {
        return this.mContainer;
    }

    public static int getPrefValueInt(Context context, String str, int i) {
        try {
            return context.getSharedPreferences(PREFS_NAME, 0).getInt(str, i);
        } catch (Exception unused) {
            return i;
        }
    }

    public static void setPrefValueInt(Context context, String str, int i) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFS_NAME, 0).edit();
            editorEdit.putInt(str, i);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }

    public static String getPrefValueString(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(PREFS_NAME, 0).getString(str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    public static void setPrefValueString(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor editorEdit = context.getApplicationContext().getSharedPreferences(PREFS_NAME, 0).edit();
            editorEdit.putString(str, str2);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }

    public AdManager withInterstitialProvider(InterstitialProvider interstitialProvider) {
        AdUtils.log("Adding interstitial support for the " + interstitialProvider.getName() + " network");
        this.mInterstitialManager.addInterstitialProvider(interstitialProvider);
        return this;
    }

    public AdManager withMarketVariation(int i) {
        mMarketVariation = i;
        return this;
    }

    public AdManager addInterstitialProviderAmazon(String str, InterstitialListener interstitialListener) {
        AdProviderAmazonInterstitial adProviderAmazonInterstitial = new AdProviderAmazonInterstitial(str, interstitialListener);
        adProviderAmazonInterstitial.setProviderID(10);
        withInterstitialProvider(adProviderAmazonInterstitial);
        return this;
    }

    public AdManager addVideoAdProviderSupersonic(String str, int i) {
        String strId;
        VideoManager videoManager = this.mVideoAdManager;
        if (videoManager != null) {
            if (!videoManager.checkIfProviderExists(4)) {
                if (AdApplicationSettings.hasValue(this.mActivity, "AdvertisingId")) {
                    Activity activity = this.mActivity;
                    strId = AdApplicationSettings.getValueString(activity, "AdvertisingId", UniqueID.id(activity));
                } else {
                    strId = UniqueID.id(this.mActivity);
                }
                withVideoAdProvider(new IronSourceVideoAds(getActivity(), str, strId));
            }
            this.mVideoAdManager.addVideoAdProviderToGroup(4, i);
        } else {
            AdUtils.log("ERROR: EnableVideoAds() should be called first!");
        }
        return this;
    }

    private void withVideoAdProvider(VideoAdProvider videoAdProvider) {
        VideoManager videoManager = this.mVideoAdManager;
        if (videoManager == null) {
            AdUtils.log("Video ads not enabled, call enableVideoAds() first!");
        } else {
            videoManager.addVideoAdProvider(videoAdProvider);
        }
    }

    public String getBaseAddress() {
        return this.mBaseAddress;
    }

    public String getNetworkMcc() {
        return this.mNetworkMcc;
    }

    public String getSimMcc() {
        return this.mSimMcc;
    }

    public String Locale() {
        return this.mLocale;
    }

    public String getPlatformName() {
        return MarketVariation.derivePlatformName(mMarketVariation);
    }

    public int getXAlignment() {
        return this.mXAlignment;
    }

    public int getYAlignment() {
        return this.mYAlignment;
    }

    public RelativeLayout getAdTarget() {
        return this.mAdTargetLayout;
    }

    public static AdManagerState getAdManagerState() {
        return mAdManagerState;
    }

    private void saveVideoPriorities(String str) {
        setPrefValueString(this.mActivity, "videoproviderpriority", str);
    }

    private String loadVideoPriorities() {
        return getPrefValueString(this.mActivity, "videoproviderpriority", DEFAULT_VIDEO_PRIORITY);
    }

    private void initialise(Activity activity, LinkListener linkListener) {
        AdUtils.log("Initialising AdManager with baseAddress: " + this.mBaseAddress);
        this.mActivity = activity;
        this.mLinkListener = linkListener;
        AdNetworkMonitor.isConnected = this.mHasNetworkConnection;
        AdNetworkMonitor.listener = this;
        this.mAdvertisingId = AdApplicationSettings.getValueString(this.mActivity, "AdvertisingId", null);
        this.mLimitAdTrackingEnabled = AdApplicationSettings.getValueInt(this.mActivity, "LimitedTracking", Integer.MIN_VALUE);
        getCountryCodes();
        AdStatisticRecorder.init(this, getPackageName());
        increaseStartupCounter();
    }

    public String getPackageName() {
        return this.mActivity.getPackageName();
    }

    public void manage() {
        this.mLocale = this.mActivity.getResources().getConfiguration().locale.getCountry().toUpperCase(Locale.US);
        initialise(this.mActivity, this.mLinkListener);
        if (this.adProviderThread == null) {
            try {
                this.adProviderThread = new AdProviderThread(this);
                this.adProviderThread.setUncaughtExceptionHandler(this.h);
                this.adProviderThread.start();
                AdUtils.log("AdProviderThread initialized");
            } catch (Exception e) {
                AdUtils.log(e.getMessage());
            }
        }
    }

    public boolean isPaused() {
        return mAdManagerState == AdManagerState.NO_ADS;
    }

    public void enableAdFreeMode() {
        mHandler.sendEmptyMessage(9);
    }

    public void stop() {
        AdUtils.log("AdManager: stopAdView");
        if (!this.mIsStarted) {
            AdUtils.log("AdManager not started so can't stop");
        } else if (hasAdProviders()) {
            onPause();
        }
    }

    public void onPause() {
        if (!this.mIsStarted) {
            AdUtils.log("AdManager not started so can't pause");
            return;
        }
        if (mAdManagerState == AdManagerState.SHOW_ADS) {
            AdUtils.log("Should show ads onResume");
        }
        VideoManager videoManager = this.mVideoAdManager;
        if (videoManager != null) {
            videoManager.pause();
        }
        InterstitialManager interstitialManager = this.mInterstitialManager;
        if (interstitialManager != null) {
            interstitialManager.onPause();
        }
        AdUtils.log("AdManager: pause");
        this.mPauseTime = System.currentTimeMillis();
        mHandler.sendEmptyMessage(19);
    }

    public void onResume(Activity activity) {
        AdUtils.log("AdManager: resume");
        this.mActivity = activity;
        if (hasAdProviders()) {
            if (!this.mIsStarted) {
                AdUtils.log("AdManager not started so can't resume");
                return;
            }
            AdStatisticRecorder.instance().sendStatistics();
        }
        increaseStartupCounter();
        VideoManager videoManager = this.mVideoAdManager;
        if (videoManager != null) {
            videoManager.resume(activity);
        }
        InterstitialManager interstitialManager = this.mInterstitialManager;
        if (interstitialManager != null) {
            interstitialManager.onResume(activity);
        }
        if (System.currentTimeMillis() - this.mPauseTime > 300000) {
            resetProviders();
        }
        mHandler.sendEmptyMessage(20);
    }

    private void resetProviders() {
        AdProviderThread adProviderThread = this.adProviderThread;
        if (adProviderThread != null) {
            adProviderThread.mCurrentAdProviderIdx = 0;
        }
        VideoManager videoManager = this.mVideoAdManager;
        if (videoManager != null) {
            videoManager.restartProviders();
        }
        InterstitialManager interstitialManager = this.mInterstitialManager;
        if (interstitialManager != null) {
            interstitialManager.restartProviders();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeSdk() {
        AdUtils.log("Using Fingersoft SDK Version 1.1");
        DeviceCapabilities.detectCapabilities(this.mActivity);
        DEFAULT_BANNER_PRIORITY = MarketVariation.getDefaultAdPriority(DEFAULT_BANNER_PRIORITY, this.mLocale, mMarketVariation);
        AdUtils.log("Default ad priority: " + DEFAULT_BANNER_PRIORITY);
        if (DEFAULT_BANNER_PRIORITY.length() == 0) {
            DEFAULT_BANNER_PRIORITY = "in_mobi,millennial,admob";
            AdUtils.log("Default ad priority was empty, setting to: " + DEFAULT_BANNER_PRIORITY);
        }
        String prefValueString = getPrefValueString(this.mActivity, "providerpriority", DEFAULT_BANNER_PRIORITY);
        if (this.TEST_MODE && this.mTestAdProviderList.length() > 0) {
            prefValueString = this.mTestAdProviderList;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Interstitials enabled: ");
        sb.append(this.mInterstitialManager.isEnabled() ? "Yes" : "No");
        AdUtils.log(sb.toString());
        if (this.mInterstitialManager.isEnabled()) {
            String strLoadInterstitialPriorities = loadInterstitialPriorities();
            if (strLoadInterstitialPriorities == null) {
                strLoadInterstitialPriorities = DEFAULT_INTERSTITIAL_PRIORITY;
            }
            this.mInterstitialManager.setPriorities(strLoadInterstitialPriorities);
            this.mInterstitialManager.reorderInterstitials(strLoadInterstitialPriorities);
            if (AdUtils.logEnabled) {
                StringBuilder sb2 = new StringBuilder("Interstitial list: ");
                Iterator<InterstitialProvider> it = this.mInterstitialManager.getProviders().iterator();
                while (it.hasNext()) {
                    sb2.append(it.next().getName() + " ");
                }
                AdUtils.log(sb2.toString());
            }
        }
        VideoManager videoManager = this.mVideoAdManager;
        if (videoManager != null && videoManager.isEnabled()) {
            String strLoadVideoPriorities = loadVideoPriorities();
            if (strLoadVideoPriorities == null) {
                strLoadVideoPriorities = DEFAULT_VIDEO_PRIORITY;
            }
            this.mVideoAdManager.reorderProvidersByPriority(strLoadVideoPriorities, 0);
        }
        AdUtils.log("AdStats: " + AdStatisticRecorder.instance().toString());
        AdStatisticRecorder.instance().clearCounters();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Ads enabled: ");
        sb3.append(hasAdProviders() ? "Yes" : "No");
        AdUtils.log(sb3.toString());
        if (!this.mWithoutAds) {
            AdUtils.log("Ads Enabled - loading....");
            this.mAdProviders = new AdProviderComparator(prefValueString).sort(this.mAdProviders);
            AdUtils.log("Calling mproviderloader");
            this.mProviderLoader.execute(this);
        } else {
            AdUtils.log("Ads disabled - continuing...");
        }
        if (checkNetworkConnection()) {
            return;
        }
        mHandler.sendEmptyMessage(7);
    }

    public AdManager inTestMode() {
        this.TEST_MODE = true;
        return this;
    }

    public void hideAds() {
        AdUtils.log("AdManager: hideAds");
        AdProviderThread adProviderThread = this.adProviderThread;
        if (adProviderThread != null) {
            adProviderThread.hideAdsInThread();
        } else {
            AdUtils.log("Adproviderthread is null, can't hideAds()");
        }
    }

    public void showAds() {
        AdUtils.log("AdManager: showAds");
        AdProviderThread adProviderThread = this.adProviderThread;
        if (adProviderThread != null) {
            adProviderThread.showAdsInThread();
        } else {
            AdUtils.log("Adproviderthread is null, can't showAds()");
        }
    }

    public boolean getVIPStatus() {
        return this.mVIPStatus;
    }

    public void setVIPStatus(boolean z) {
        this.mVIPStatus = z;
    }

    public void setGDPRConsentStatus(boolean z) {
        VideoManager videoManager = this.mVideoAdManager;
        if (videoManager == null || !videoManager.checkIfProviderExists(4)) {
            return;
        }
        Log.d(TAG, "Amazon gdpr sdk Consent setting... " + Boolean.toString(z));
        this.mVideoAdManager.setGDPRConsentStatus(4, z);
    }

    private String loadInterstitialPriorities() {
        return getPrefValueString(this.mActivity, "interstitialproviderpriority", DEFAULT_INTERSTITIAL_PRIORITY);
    }

    private void getCountryCodes() {
        String networkOperator;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.mActivity.getSystemService("phone");
            if (telephonyManager != null) {
                if (telephonyManager.getPhoneType() != 2 && (networkOperator = telephonyManager.getNetworkOperator()) != null && networkOperator.length() >= 3) {
                    this.mNetworkMcc = networkOperator.substring(0, 3);
                    AdUtils.log("Network MCC determined to be: " + this.mNetworkMcc);
                    this.mAdditionalRequestParams += "&networkMcc=" + this.mNetworkMcc;
                }
                String simOperator = telephonyManager.getSimOperator();
                if (simOperator == null || simOperator.length() < 3) {
                    return;
                }
                this.mSimMcc = simOperator.substring(0, 3);
                AdUtils.log("SIM MCC determined to be: " + this.mSimMcc);
                this.mAdditionalRequestParams += "&simMcc=" + this.mSimMcc;
            }
        } catch (Exception e) {
            AdUtils.log("Could not determine country network codes: " + e.getMessage());
        }
    }

    private void increaseStartupCounter() {
        AdUtils.log("Incrementing startup counter");
        mStartupCount = getPrefValueInt(this.mActivity, "startup_counter", 0);
        setPrefValueInt(this.mActivity, "startup_counter", mStartupCount + 1);
        int prefValueInt = getPrefValueInt(this.mActivity, "last_versioncode", -1);
        int i = this.mCurrentVersionCode;
        if (prefValueInt != i) {
            setPrefValueInt(this.mActivity, "last_versioncode", i);
            setPrefValueInt(this.mActivity, "startup_counter_currentversion", 1);
            mStartupCountWithCurrentVersion = 0;
            AdUtils.log("startupcounter is 0 versioncode " + this.mCurrentVersionCode);
            return;
        }
        mStartupCountWithCurrentVersion = getPrefValueInt(this.mActivity, "startup_counter_currentversion", 0);
        setPrefValueInt(this.mActivity, "startup_counter_currentversion", mStartupCountWithCurrentVersion + 1);
        AdUtils.log("current version startup is " + (mStartupCountWithCurrentVersion + 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasAdProviders() {
        return this.mAdProviders.size() > 0;
    }

    public void loadInterstitial() {
        mHandler.sendEmptyMessage(11);
    }

    public void showInterstitial() {
        mHandler.sendEmptyMessage(13);
    }

    public void loadInterstitialEvenIfAdfree() {
        mHandler.sendEmptyMessage(12);
    }

    public void showInterstitialEvenIfAdfree() {
        mHandler.sendEmptyMessage(14);
    }

    public boolean hasVideoCampaigns(int i) {
        VideoManager videoManager = this.mVideoAdManager;
        if (videoManager == null) {
            AdUtils.log("Video ads not enabled, call enableVideoAds() first!");
            return false;
        }
        return videoManager.hasCampaigns(i);
    }

    public void showVideoAd(int i) {
        if (this.mVideoAdManager == null) {
            AdUtils.log("Video ads not enabled, call enableVideoAds() first!");
            return;
        }
        Message message = new Message();
        message.what = 15;
        message.obj = new Integer(i);
        mHandler.sendMessage(message);
    }

    public void showVideoAdEvenIfAdFree(int i) {
        if (this.mVideoAdManager == null) {
            AdUtils.log("Video ads not enabled, call enableVideoAds() first!");
            return;
        }
        Message message = new Message();
        message.what = 16;
        message.obj = new Integer(i);
        mHandler.sendMessage(message);
    }

    public void trackPageView(String str) {
        try {
            if (this.mTracker != null) {
                this.mTracker.trackPageView(str);
            }
        } catch (Exception unused) {
        }
    }

    public void onAdViewSuccess(AdProvider adProvider) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 5;
        messageObtain.obj = adProvider.getName();
        mHandler.sendMessage(messageObtain);
        AdUtils.log("onAdViewSuccess " + adProvider.getName());
    }

    public void onAdViewFailed(AdProvider adProvider) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 4;
        messageObtain.obj = adProvider.getName();
        mHandler.sendMessage(messageObtain);
    }

    @Override // com.fingersoft.ads_sdk.advertising.network.NetworkChangeListener
    public void onConnectivityChanged(boolean z) {
        if (!z) {
            AdUtils.log("Network turned off");
            mHandler.sendEmptyMessage(7);
        } else {
            AdUtils.log("Network turned on");
            mHandler.sendEmptyMessage(8);
        }
        AdNetworkListener adNetworkListener = this.mNetworkListener;
        if (adNetworkListener != null) {
            adNetworkListener.onConnectivityChanged(z);
        }
    }

    public AdManager enableVideoAds(IVideoAdListener iVideoAdListener) {
        if (this.mVideoAdManager != null) {
            return this;
        }
        this.mVideoAdManager = new VideoManager(this, this.mVideoAdListener);
        this.mUserVideoAdListener = iVideoAdListener;
        return this;
    }

    public void updateFirstBannerShownTimeStamp() {
        if (this.mFirstBannerShown) {
            return;
        }
        this.mFirstBannerRequestTimestamp = System.currentTimeMillis();
    }

    @SuppressLint({"NewApi"})
    public class AdProviderThread extends Thread {
        AdManager mAdManager;
        List<String> mFailedProviders;
        int mCurrentAdProviderIdx = 0;
        AdProvider mCurrentProvider = null;
        private long mPreviousAdShowTime = 0;

        public AdProviderThread(AdManager adManager) {
            this.mAdManager = null;
            this.mFailedProviders = null;
            this.mAdManager = adManager;
            this.mFailedProviders = new ArrayList();
            if (this.mAdManager.hasAdProviders()) {
                return;
            }
            AdUtils.log("No ad providers so can't show ads");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            Handler unused = AdManager.mHandler = new AdIncomingHandler(this);
            AdManager.this.mIsReady = true;
            AdManager.mHandler.sendEmptyMessage(10);
            Looper.loop();
        }

        protected void initializeSdk() {
            this.mAdManager.initializeSdk();
        }

        public AdProvider getCurrentProvider() {
            return this.mCurrentProvider;
        }

        protected void networkOffShowAds() {
            if (AdManager.this.adProviderThread.mAdManager.checkNetworkConnection()) {
                AdUtils.log("NetworkOffShowAds");
                AdManager.this.adProviderThread.mFailedProviders.clear();
                AdManager.mHandler.sendEmptyMessage(3);
            }
        }

        protected void networkOffNoAds() {
            AdUtils.log("NetworkOffNoAds");
            if (AdManager.this.adProviderThread.mCurrentProvider != null) {
                AdManager.this.adProviderThread.mCurrentProvider.pause();
            }
        }

        protected void noAds() {
            AdUtils.log("SHOW_ADS - MSG_TYPE_NOADS");
            AdManager.this.adProviderThread.clearPendingMessages();
            if (AdManager.this.adProviderThread.mCurrentProvider != null) {
                AdUtils.log("Provider should pause");
                AdManager.this.adProviderThread.mCurrentProvider.pause();
            }
            AdManager.this.adProviderThread.mAdManager.mAdHideTime = System.currentTimeMillis();
        }

        protected void networkOff() {
            AdUtils.log("MSG_TYPE_NETWORK_OFF");
            AdProvider adProvider = this.mCurrentProvider;
            if (adProvider != null) {
                adProvider.close();
                this.mCurrentProvider = null;
            }
            this.mCurrentAdProviderIdx = 0;
        }

        protected void providerSuccess(String str) {
            AdProvider adProvider = this.mCurrentProvider;
            if (adProvider != null && adProvider.getName().equals(str)) {
                if (!AdManager.this.mFirstBannerShown) {
                    AdManager.this.mFirstBannerShown = true;
                    long jCurrentTimeMillis = System.currentTimeMillis() - AdManager.this.mFirstBannerRequestTimestamp;
                    StringBuilder sb = new StringBuilder();
                    sb.append(AdManager.this.mLocale);
                    sb.append(Constants.URL_PATH_DELIMITER);
                    sb.append(str);
                    sb.append(Constants.URL_PATH_DELIMITER);
                    sb.append(jCurrentTimeMillis);
                    sb.append(Constants.URL_PATH_DELIMITER);
                    sb.append(AdManager.this.mFailedProviderCountBeforeFirstSucceeded);
                    sb.append(AdManager.this.mFailedProviderCountBeforeFirstSucceeded > 0 ? Constants.URL_PATH_DELIMITER + AdManager.this.mFailedProvidersBeforeFirstSucceeded : "/none");
                    String string = sb.toString();
                    AdManager.this.trackPageView("firstbanner/" + string);
                    AdUtils.log("first banner firstbanner/" + string);
                }
                AdUtils.log("Ad show success. Clearing failed list");
                this.mCurrentProvider.show();
                this.mFailedProviders.clear();
                AdManager.mHandler.sendEmptyMessageDelayed(3, ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
                return;
            }
            AdUtils.log("Old adprovider success call detected, closing: " + str);
            for (AdProvider adProvider2 : AdManager.this.mAdProviders) {
                if (adProvider2.getName().equals(str)) {
                    adProvider2.close();
                }
            }
        }

        protected void showAds() {
            AdUtils.log("NO_ADS - MSG_TYPE_SHOWADS");
            AdManager.this.mAdShowTime = System.currentTimeMillis();
            AdUtils.log("Show ads in thread");
            if (AdManager.this.mAdProviders != null && !AdManager.this.mAdProviders.isEmpty()) {
                if (this.mCurrentAdProviderIdx >= AdManager.this.mAdProviders.size()) {
                    AdUtils.log("Resetting ad provider list to 0, was " + this.mCurrentAdProviderIdx + " size " + AdManager.this.mAdProviders.size());
                    this.mCurrentAdProviderIdx = 0;
                }
                if (this.mCurrentProvider != null) {
                    long j = AdManager.this.mAdShowTime - AdManager.this.mAdHideTime;
                    AdUtils.log("Time shown " + j);
                    AdManager adManager = AdManager.this;
                    adManager.mTimeCurrentAdShown = adManager.mTimeCurrentAdShown + j;
                    AdUtils.log("timeCurrentAdShown shown " + AdManager.this.mTimeCurrentAdShown);
                    long j2 = ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS - AdManager.this.mTimeCurrentAdShown;
                    if (j2 < 0) {
                        j2 = 0;
                    }
                    AdUtils.log("Time to next refresh: " + j2);
                    this.mCurrentProvider.resume();
                    clearPendingMessages();
                    AdManager.mHandler.sendEmptyMessageDelayed(3, j2);
                    return;
                }
                AdUtils.log("Current provider is null, requesting next provider");
                createProvider();
                clearPendingMessages();
                AdManager.mHandler.sendEmptyMessageDelayed(3, ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
                return;
            }
            AdUtils.log("Can't show ads since mAdProviders is empty");
        }

        protected void showNextAd() {
            AdUtils.log("SHOW_ADS - MSG_TYPE_SHOW_NEXT_AD");
            if (this.mPreviousAdShowTime > 0) {
                AdUtils.log("LAST SHOW " + (System.currentTimeMillis() - this.mPreviousAdShowTime));
            }
            this.mPreviousAdShowTime = System.currentTimeMillis();
            clearPendingMessages();
            AdManager.this.mTimeCurrentAdShown = 0L;
            AdProvider adProvider = this.mCurrentProvider;
            if (adProvider != null && adProvider.canRefresh() && !this.mFailedProviders.contains(this.mCurrentProvider.getName())) {
                if (!AdManager.this.checkNetworkConnection()) {
                    AdManager.mHandler.sendEmptyMessage(7);
                    return;
                }
                AdUtils.log("Refreshing current provider " + this.mCurrentProvider.getName());
                this.mCurrentProvider.incrementTimesShown();
                this.mCurrentProvider.refresh();
                AdManager.mHandler.sendEmptyMessageDelayed(3, ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
                AdUtils.log("Refreshing " + this.mCurrentProvider.getName());
                return;
            }
            AdProvider adProvider2 = this.mCurrentProvider;
            if (adProvider2 != null) {
                adProvider2.close();
                AdUtils.log("Closing current provider " + this.mCurrentProvider.getName());
            }
            AdUtils.log("Switching to next provider");
            this.mCurrentAdProviderIdx++;
            if (this.mCurrentAdProviderIdx >= AdManager.this.mAdProviders.size()) {
                AdUtils.log("Resetting ad provider list to 0, was " + this.mCurrentAdProviderIdx + " size " + AdManager.this.mAdProviders.size());
                this.mCurrentAdProviderIdx = 0;
            }
            createProvider();
        }

        protected void providerFailed(String str) {
            if (!AdManager.this.mFirstBannerShown) {
                AdManager.access$1108(AdManager.this);
                if (AdManager.this.mFailedProviderCountBeforeFirstSucceeded < 12) {
                    if (AdManager.this.mFailedProvidersBeforeFirstSucceeded.length() > 0) {
                        AdManager.this.mFailedProvidersBeforeFirstSucceeded = AdManager.this.mFailedProvidersBeforeFirstSucceeded + "," + str;
                    } else {
                        AdManager.this.mFailedProvidersBeforeFirstSucceeded = AdManager.this.mFailedProvidersBeforeFirstSucceeded + str;
                    }
                }
            }
            AdUtils.log("MSG_TYPE_PROVIDER_FAILED " + str + " - SHOW_ADS");
            clearPendingMessages();
            AdProvider adProvider = this.mCurrentProvider;
            if (adProvider == null || !adProvider.getName().equals(str)) {
                for (AdProvider adProvider2 : AdManager.this.mAdProviders) {
                    if (adProvider2.getName().equals(str)) {
                        AdUtils.log("Old provider failed, closing it " + str);
                        adProvider2.close();
                    }
                }
            } else {
                AdProvider adProvider3 = this.mCurrentProvider;
                if (adProvider3 != null) {
                    if (!this.mFailedProviders.contains(adProvider3.getName())) {
                        this.mFailedProviders.add(this.mCurrentProvider.getName());
                        AdUtils.log("Added failed provider " + this.mCurrentProvider.getName());
                    }
                    AdUtils.log("Closing failed provider " + this.mCurrentProvider.getName());
                    this.mCurrentProvider.hide();
                    this.mCurrentProvider.close();
                    this.mCurrentProvider = null;
                }
                AdManager.mHandler.sendEmptyMessage(3);
                AdUtils.log("SHould show next ad");
            }
            AdUtils.log("Failed providers " + this.mFailedProviders.size() + " / " + AdManager.this.mAdProviders.size());
            if (AdManager.this.checkNetworkConnection()) {
                if (this.mFailedProviders.size() >= AdManager.this.mAdProviders.size()) {
                    AdUtils.log("All providers failed, 15min cooldown");
                    this.mFailedProviders.clear();
                    clearPendingMessages();
                    AdManager.mHandler.sendEmptyMessageDelayed(3, 900000L);
                    return;
                }
                return;
            }
            AdUtils.log("Lost network");
            AdManager.mHandler.sendEmptyMessage(7);
        }

        protected void hideAdsInThread() {
            if (AdManager.mHandler != null) {
                AdManager.mHandler.sendEmptyMessage(2);
            } else {
                AdUtils.log("hideAdsInThread in thread not done, mHandler null");
            }
        }

        protected void showAdsInThread() {
            if (AdManager.mHandler != null) {
                AdManager.mHandler.sendEmptyMessage(1);
            } else {
                AdUtils.log("showAdsInThread in thread not done, mHandler null");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @SuppressLint({"NewApi"})
        public void adFreeMode() {
            AdUtils.log("-- ADFREE MODE --");
            AdManager.this.adProviderThread.clearPendingMessages();
            if (AdManager.this.adProviderThread.mCurrentProvider != null) {
                AdUtils.log("Provider should pause");
                AdManager.this.adProviderThread.mCurrentProvider.pause();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void loadInterstitialAd() {
            AdManager.this.getActivity().runOnUiThread(new Runnable() { // from class: com.fingersoft.ads_sdk.advertising.AdManager.AdProviderThread.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AdManager.this.mInterstitialManager != null) {
                        AdManager.this.mInterstitialManager.loadInterstitial();
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showInterstitialAd() {
            AdManager.this.getActivity().runOnUiThread(new Runnable() { // from class: com.fingersoft.ads_sdk.advertising.AdManager.AdProviderThread.2
                @Override // java.lang.Runnable
                public void run() {
                    if (AdManager.this.mInterstitialManager != null) {
                        AdManager.this.mInterstitialManager.showInterstitial();
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showVideoAd(int i) {
            if (AdManager.this.checkNetworkConnection()) {
                AdManager.this.mVideoAdManager.showVideoAd(i);
            }
        }

        private boolean canShowEula() {
            return (AdManager.getAdManagerState() == AdManagerState.NO_ADS || AdManager.getAdManagerState() == AdManagerState.AD_FREE_NO_ADS || AdManager.getAdManagerState() == AdManagerState.NETWORK_OFF_NO_ADS) ? false : true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPendingMessages() {
            for (int i = 0; i < 21; i++) {
                AdManager.mHandler.removeMessages(i);
            }
        }

        private void createProvider() {
            if (this.mCurrentAdProviderIdx >= AdManager.this.mAdProviders.size()) {
                AdUtils.log("Resetting ad provider list to 0");
                this.mCurrentAdProviderIdx = 0;
            }
            this.mCurrentProvider = (AdProvider) AdManager.this.mAdProviders.get(this.mCurrentAdProviderIdx);
            this.mCurrentProvider.create(AdManager.this.TEST_MODE);
            AdManager.this.mCurrentAdProviderName = this.mCurrentProvider.getName();
            AdUtils.log("AdManager provider thread initialized");
        }
    }

    private class ProviderLoader extends AsyncTask<AdManager, Void, Void> {
        AdManager mAdManager;
        String mProviderPriorities;

        private ProviderLoader() {
            this.mAdManager = null;
            this.mProviderPriorities = AdManager.DEFAULT_BANNER_PRIORITY;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(AdManager... adManagerArr) {
            try {
                this.mAdManager = adManagerArr[0];
            } catch (Exception e) {
                AdUtils.log("Exception in background: " + e.getMessage());
                this.mProviderPriorities = null;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            if (this.mProviderPriorities == null) {
                this.mProviderPriorities = AdManager.getPrefValueString(AdManager.this.mActivity, "providerpriority", AdManager.DEFAULT_BANNER_PRIORITY);
                AdManager.this.trackPageView("providerpriority/didnt_receive");
            }
        }
    }

    @SuppressLint({"NewApi"})
    static class AdIncomingHandler extends Handler {
        private final AdProviderThread mAdProviderThread;

        AdIncomingHandler(AdProviderThread adProviderThread) {
            this.mAdProviderThread = adProviderThread;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                AdUtils.log("Received " + message.what + " CurrentState: " + AdManager.mAdManagerState);
                switch (AdManager.mAdManagerState) {
                    case NOT_INITIALIZED:
                        if (message.what != 10) {
                            return;
                        }
                        AdUtils.log("Initializing sdk");
                        AdManagerState unused = AdManager.mAdManagerState = AdManagerState.NO_ADS;
                        this.mAdProviderThread.initializeSdk();
                        if (this.mAdProviderThread.mAdManager.mSdkInitializedListener != null) {
                            this.mAdProviderThread.mAdManager.mSdkInitializedListener.onFSAdSdkInitialized();
                            return;
                        }
                        return;
                    case SHOW_ADS:
                        switch (message.what) {
                            case 2:
                                AdManagerState unused2 = AdManager.mAdManagerState = AdManagerState.NO_ADS;
                                this.mAdProviderThread.noAds();
                                break;
                            case 3:
                                this.mAdProviderThread.showNextAd();
                                break;
                            case 4:
                                this.mAdProviderThread.providerFailed((String) message.obj);
                                break;
                            case 5:
                                this.mAdProviderThread.providerSuccess((String) message.obj);
                                break;
                            case 6:
                            case 8:
                            case 10:
                            case 17:
                            case 18:
                            default:
                                break;
                            case 7:
                                AdManagerState unused3 = AdManager.mAdManagerState = AdManagerState.NETWORK_OFF_WITH_ADS;
                                this.mAdProviderThread.clearPendingMessages();
                                this.mAdProviderThread.networkOff();
                                break;
                            case 9:
                                AdManager.setPrefValueInt(this.mAdProviderThread.mAdManager.getActivity(), "ADFREE_MODE", 1);
                                AdManagerState unused4 = AdManager.mAdManagerState = AdManagerState.AD_FREE_CAN_SHOW_PROMOTION;
                                this.mAdProviderThread.clearPendingMessages();
                                this.mAdProviderThread.adFreeMode();
                                break;
                            case 11:
                            case 12:
                                this.mAdProviderThread.loadInterstitialAd();
                                break;
                            case 13:
                            case 14:
                                this.mAdProviderThread.noAds();
                                this.mAdProviderThread.showInterstitialAd();
                                AdManagerState unused5 = AdManager.mAdManagerState = AdManagerState.SPECIAL_AD_RUNNING_WITH_ADS;
                                break;
                            case 15:
                            case 16:
                                int iIntValue = ((Integer) message.obj).intValue();
                                this.mAdProviderThread.noAds();
                                this.mAdProviderThread.showVideoAd(iIntValue);
                                AdManagerState unused6 = AdManager.mAdManagerState = AdManagerState.SPECIAL_AD_RUNNING_WITH_ADS;
                                break;
                            case 19:
                                AdManagerState unused7 = AdManager.mAdManagerState = AdManagerState.PAUSED_WITH_ADS;
                                this.mAdProviderThread.clearPendingMessages();
                                if (this.mAdProviderThread.mCurrentProvider != null) {
                                    AdUtils.log("Provider should pause");
                                    this.mAdProviderThread.mCurrentProvider.pause();
                                }
                                this.mAdProviderThread.mAdManager.mAdHideTime = System.currentTimeMillis();
                                break;
                        }
                    case NO_ADS:
                        int i = message.what;
                        if (i == 1) {
                            AdManagerState unused8 = AdManager.mAdManagerState = AdManagerState.SHOW_ADS;
                            this.mAdProviderThread.mAdManager.updateFirstBannerShownTimeStamp();
                            this.mAdProviderThread.showAds();
                            return;
                        }
                        if (i == 7) {
                            AdManagerState unused9 = AdManager.mAdManagerState = AdManagerState.NETWORK_OFF_NO_ADS;
                            this.mAdProviderThread.clearPendingMessages();
                            this.mAdProviderThread.networkOff();
                            return;
                        }
                        if (i == 9) {
                            AdManager.setPrefValueInt(this.mAdProviderThread.mAdManager.getActivity(), "ADFREE_MODE", 1);
                            AdManagerState unused10 = AdManager.mAdManagerState = AdManagerState.AD_FREE_NO_ADS;
                            this.mAdProviderThread.clearPendingMessages();
                            this.mAdProviderThread.adFreeMode();
                            return;
                        }
                        if (i == 19) {
                            AdManagerState unused11 = AdManager.mAdManagerState = AdManagerState.PAUSED_NO_ADS;
                            return;
                        }
                        switch (i) {
                            case 11:
                            case 12:
                                this.mAdProviderThread.loadInterstitialAd();
                                break;
                            case 13:
                            case 14:
                                this.mAdProviderThread.showInterstitialAd();
                                AdManagerState unused12 = AdManager.mAdManagerState = AdManagerState.SPECIAL_AD_RUNNING_NO_ADS;
                                break;
                            case 15:
                            case 16:
                                this.mAdProviderThread.showVideoAd(((Integer) message.obj).intValue());
                                AdManagerState unused13 = AdManager.mAdManagerState = AdManagerState.SPECIAL_AD_RUNNING_NO_ADS;
                                break;
                            default:
                                break;
                        }
                    case PAUSED_NO_ADS:
                        int i2 = message.what;
                        if (i2 == 1) {
                            AdManagerState unused14 = AdManager.mAdManagerState = AdManagerState.PAUSED_WITH_ADS;
                            return;
                        } else if (i2 == 3) {
                            AdManagerState unused15 = AdManager.mAdManagerState = AdManagerState.NO_ADS;
                            return;
                        } else {
                            if (i2 != 20) {
                                return;
                            }
                            AdManagerState unused16 = AdManager.mAdManagerState = AdManagerState.NO_ADS;
                            return;
                        }
                    case PAUSED_WITH_ADS:
                        int i3 = message.what;
                        if (i3 == 2) {
                            AdManagerState unused17 = AdManager.mAdManagerState = AdManagerState.PAUSED_NO_ADS;
                            return;
                        }
                        if (i3 != 20) {
                            return;
                        }
                        if (this.mAdProviderThread.mCurrentProvider != null) {
                            this.mAdProviderThread.mCurrentProvider.resume();
                        }
                        AdManagerState unused18 = AdManager.mAdManagerState = AdManagerState.SHOW_ADS;
                        this.mAdProviderThread.clearPendingMessages();
                        AdManager.mHandler.sendEmptyMessage(3);
                        return;
                    case NETWORK_OFF_NO_ADS:
                        int i4 = message.what;
                        if (i4 == 1) {
                            AdManagerState unused19 = AdManager.mAdManagerState = AdManagerState.NETWORK_OFF_WITH_ADS;
                            return;
                        }
                        if (i4 == 2) {
                            this.mAdProviderThread.networkOffNoAds();
                            return;
                        }
                        if (i4 == 8) {
                            AdManagerState unused20 = AdManager.mAdManagerState = AdManagerState.NO_ADS;
                            return;
                        } else {
                            if (i4 != 9) {
                                return;
                            }
                            AdManager.setPrefValueInt(this.mAdProviderThread.mAdManager.getActivity(), "ADFREE_MODE", 1);
                            AdManagerState unused21 = AdManager.mAdManagerState = AdManagerState.AD_FREE_NO_ADS;
                            this.mAdProviderThread.clearPendingMessages();
                            this.mAdProviderThread.adFreeMode();
                            return;
                        }
                    case NETWORK_OFF_WITH_ADS:
                        int i5 = message.what;
                        if (i5 == 2) {
                            AdManagerState unused22 = AdManager.mAdManagerState = AdManagerState.NETWORK_OFF_NO_ADS;
                            return;
                        }
                        if (i5 == 8) {
                            AdManagerState unused23 = AdManager.mAdManagerState = AdManagerState.SHOW_ADS;
                            this.mAdProviderThread.showAds();
                            return;
                        } else {
                            if (i5 != 9) {
                                return;
                            }
                            AdManager.setPrefValueInt(this.mAdProviderThread.mAdManager.getActivity(), "ADFREE_MODE", 1);
                            AdManagerState unused24 = AdManager.mAdManagerState = AdManagerState.AD_FREE_CAN_SHOW_PROMOTION;
                            this.mAdProviderThread.clearPendingMessages();
                            this.mAdProviderThread.adFreeMode();
                            return;
                        }
                    case SPECIAL_AD_RUNNING_NO_ADS:
                        int i6 = message.what;
                        if (i6 == 1) {
                            AdManagerState unused25 = AdManager.mAdManagerState = AdManagerState.SPECIAL_AD_RUNNING_WITH_ADS;
                            return;
                        }
                        if (i6 == 9) {
                            AdManager.setPrefValueInt(this.mAdProviderThread.mAdManager.getActivity(), "ADFREE_MODE", 1);
                            AdManagerState unused26 = AdManager.mAdManagerState = AdManagerState.AD_FREE_NO_ADS;
                            this.mAdProviderThread.clearPendingMessages();
                            this.mAdProviderThread.adFreeMode();
                            return;
                        }
                        if (i6 != 17) {
                            if (i6 != 18) {
                                return;
                            }
                            AdManagerState unused27 = AdManager.mAdManagerState = AdManagerState.NO_ADS;
                            return;
                        } else {
                            this.mAdProviderThread.clearPendingMessages();
                            AdManagerState unused28 = AdManager.mAdManagerState = AdManagerState.NO_ADS;
                            return;
                        }
                    case SPECIAL_AD_RUNNING_WITH_ADS:
                        int i7 = message.what;
                        if (i7 == 1) {
                            AdManagerState unused29 = AdManager.mAdManagerState = AdManagerState.SHOW_ADS;
                            this.mAdProviderThread.showAds();
                            return;
                        }
                        if (i7 == 2) {
                            AdManagerState unused30 = AdManager.mAdManagerState = AdManagerState.SPECIAL_AD_RUNNING_NO_ADS;
                            return;
                        }
                        if (i7 == 9) {
                            AdManager.setPrefValueInt(this.mAdProviderThread.mAdManager.getActivity(), "ADFREE_MODE", 1);
                            AdManagerState unused31 = AdManager.mAdManagerState = AdManagerState.AD_FREE_CAN_SHOW_PROMOTION;
                            this.mAdProviderThread.clearPendingMessages();
                            this.mAdProviderThread.adFreeMode();
                            return;
                        }
                        if (i7 != 17) {
                            if (i7 != 18) {
                                return;
                            }
                            AdManagerState unused32 = AdManager.mAdManagerState = AdManagerState.SHOW_ADS;
                            AdManager.mHandler.sendEmptyMessage(3);
                            return;
                        }
                        if (this.mAdProviderThread.mCurrentProvider != null) {
                            this.mAdProviderThread.mCurrentProvider.resume();
                        }
                        AdManagerState unused33 = AdManager.mAdManagerState = AdManagerState.SHOW_ADS;
                        this.mAdProviderThread.clearPendingMessages();
                        AdManager.mHandler.sendEmptyMessage(3);
                        return;
                    case AD_FREE_NO_ADS:
                        int i8 = message.what;
                        if (i8 == 1) {
                            AdManagerState unused34 = AdManager.mAdManagerState = AdManagerState.AD_FREE_CAN_SHOW_PROMOTION;
                            return;
                        }
                        if (i8 == 12) {
                            this.mAdProviderThread.loadInterstitialAd();
                            return;
                        }
                        if (i8 == 14) {
                            this.mAdProviderThread.showInterstitialAd();
                            return;
                        } else {
                            if (i8 != 16) {
                                return;
                            }
                            this.mAdProviderThread.showVideoAd(((Integer) message.obj).intValue());
                            return;
                        }
                    case AD_FREE_CAN_SHOW_PROMOTION:
                        int i9 = message.what;
                        if (i9 == 2) {
                            AdManagerState unused35 = AdManager.mAdManagerState = AdManagerState.AD_FREE_NO_ADS;
                        } else if (i9 == 12) {
                            this.mAdProviderThread.loadInterstitialAd();
                        } else if (i9 == 14) {
                            this.mAdProviderThread.showInterstitialAd();
                        } else if (i9 == 16) {
                            this.mAdProviderThread.showVideoAd(((Integer) message.obj).intValue());
                        }
                        break;
                }
                AdUtils.log("UNKNOWN STATE!");
            } catch (Exception e) {
                AdUtils.log(e.getMessage());
            }
        }
    }
}
