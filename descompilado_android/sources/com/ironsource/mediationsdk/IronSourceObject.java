package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.config.ConfigValidationResult;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.events.SuperLooper;
import com.ironsource.mediationsdk.logger.ConsoleLogger;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.LogListener;
import com.ironsource.mediationsdk.logger.PublisherLogger;
import com.ironsource.mediationsdk.model.ApplicationEvents;
import com.ironsource.mediationsdk.model.BannerConfigurations;
import com.ironsource.mediationsdk.model.BannerPlacement;
import com.ironsource.mediationsdk.model.InterstitialConfigurations;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.model.OfferwallPlacement;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.model.RewardedVideoConfigurations;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.IronSourceInterface;
import com.ironsource.mediationsdk.sdk.ListenersWrapper;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.ironsource.mediationsdk.sdk.SegmentListener;
import com.ironsource.mediationsdk.server.HttpFunctions;
import com.ironsource.mediationsdk.server.ServerURL;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.GeneralPropertiesWorker;
import com.ironsource.mediationsdk.utils.IronSourceAES;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IronSourceObject implements IronSourceInterface, MediationInitializer.OnMediationInitializationListener {
    private static IronSourceObject sInstance;
    private Activity mActivity;
    private Set<IronSource.AD_UNIT> mAdUnitsToInitialize;
    private AtomicBoolean mAtomicIsFirstInit;
    private ArrayList<AbstractAdapter> mBannerAdaptersList;
    private BannerManager mBannerManager;
    private IronSourceBannerLayout mBnLayoutToLoad;
    private String mBnPlacementToLoad;
    private CopyOnWriteArraySet<String> mDemandOnlyIsLoadBeforeInitCompleted;
    private DemandOnlyIsManager mDemandOnlyIsManager;
    private CopyOnWriteArraySet<String> mDemandOnlyRvLoadBeforeInitCompleted;
    private DemandOnlyRvManager mDemandOnlyRvManager;
    private boolean mDidInitBanner;
    private boolean mDidInitInterstitial;
    private boolean mDidInitRewardedVideo;
    private AtomicBoolean mEventManagersInit;
    private int mInitCounter;
    private List<IronSource.AD_UNIT> mInitiatedAdUnits;
    private ArrayList<AbstractAdapter> mInterstitialAdaptersList;
    private InterstitialManager mInterstitialManager;
    private IronSourceSegment mIronSegment;
    private Boolean mIsBnLoadBeforeInitCompleted;
    private boolean mIsDemandOnlyIs;
    private boolean mIsDemandOnlyRv;
    private boolean mIsIsLoadBeforeInitCompleted;
    private boolean mIsIsProgrammatic;
    private boolean mIsRvProgrammatic;
    private ListenersWrapper mListenersWrapper;
    private IronSourceLoggerManager mLoggerManager;
    private AbstractAdapter mOfferwallAdapter;
    private OfferwallManager mOfferwallManager;
    private ProgIsManager mProgIsManager;
    private ProgRvManager mProgRvManager;
    private PublisherLogger mPublisherLogger;
    private Set<IronSource.AD_UNIT> mRequestedAdUnits;
    private ArrayList<AbstractAdapter> mRewardedVideoAdaptersList;
    private RewardedVideoManager mRewardedVideoManager;
    private String mSessionId;
    private final String TAG = getClass().getName();
    private final String IRONSOURCE_VERSION_STRING = "!SDK-VERSION-STRING!:com.ironsource:mediationsdk:\u200b6.9.0";
    private final Object mServerResponseLocker = new Object();
    private ServerResponseWrapper mCurrentServerResponse = null;
    private String mAppKey = null;
    private String mUserId = null;
    private Integer mUserAge = null;
    private String mUserGender = null;
    private String mSegment = null;
    private String mDynamicUserId = null;
    private Map<String, String> mRvServerParams = null;
    private String mMediationType = null;
    private boolean mInitSucceeded = false;
    private boolean mShouldSendGetInstanceEvent = true;
    private final String KEY_INIT_COUNTER = Constants.RequestParameters.SESSION_DEPTH;
    private Boolean mConsent = null;

    public interface IResponseListener {
        void onUnrecoverableError(String str);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialApi
    public void initInterstitial(Activity activity, String str, String str2) {
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public void initOfferwall(Activity activity, String str, String str2) {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoApi
    public void initRewardedVideo(Activity activity, String str, String str2) {
    }

    public static synchronized IronSourceObject getInstance() {
        if (sInstance == null) {
            sInstance = new IronSourceObject();
        }
        return sInstance;
    }

    private IronSourceObject() {
        this.mSessionId = null;
        initializeManagers();
        this.mEventManagersInit = new AtomicBoolean();
        this.mRewardedVideoAdaptersList = new ArrayList<>();
        this.mInterstitialAdaptersList = new ArrayList<>();
        this.mBannerAdaptersList = new ArrayList<>();
        this.mAdUnitsToInitialize = new HashSet();
        this.mRequestedAdUnits = new HashSet();
        this.mIsDemandOnlyIs = false;
        this.mIsDemandOnlyRv = false;
        this.mAtomicIsFirstInit = new AtomicBoolean(true);
        this.mInitCounter = 0;
        this.mDidInitRewardedVideo = false;
        this.mDidInitInterstitial = false;
        this.mDidInitBanner = false;
        this.mSessionId = UUID.randomUUID().toString();
        this.mIsBnLoadBeforeInitCompleted = false;
        this.mIsIsLoadBeforeInitCompleted = false;
        this.mBnPlacementToLoad = null;
        this.mProgRvManager = null;
        this.mProgIsManager = null;
        this.mIsRvProgrammatic = false;
        this.mIsIsProgrammatic = false;
        this.mDemandOnlyIsLoadBeforeInitCompleted = new CopyOnWriteArraySet<>();
        this.mDemandOnlyRvLoadBeforeInitCompleted = new CopyOnWriteArraySet<>();
        this.mDemandOnlyIsManager = null;
        this.mDemandOnlyRvManager = null;
        this.mBannerManager = null;
    }

    public void sendInitCompletedEvent(long j) {
        JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(this.mIsDemandOnlyRv || this.mIsDemandOnlyIs);
        try {
            mediationAdditionalData.put("duration", j);
            mediationAdditionalData.put(Constants.RequestParameters.SESSION_DEPTH, this.mInitCounter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(514, mediationAdditionalData));
    }

    public synchronized void init(Activity activity, String str, boolean z, IronSource.AD_UNIT... ad_unitArr) {
        if (this.mAtomicIsFirstInit != null && this.mAtomicIsFirstInit.compareAndSet(true, false)) {
            if (ad_unitArr == null || ad_unitArr.length == 0) {
                for (IronSource.AD_UNIT ad_unit : IronSource.AD_UNIT.values()) {
                    this.mAdUnitsToInitialize.add(ad_unit);
                }
                this.mDidInitRewardedVideo = true;
                this.mDidInitInterstitial = true;
                this.mDidInitBanner = true;
            } else {
                for (IronSource.AD_UNIT ad_unit2 : ad_unitArr) {
                    this.mAdUnitsToInitialize.add(ad_unit2);
                    this.mRequestedAdUnits.add(ad_unit2);
                    if (ad_unit2.equals(IronSource.AD_UNIT.INTERSTITIAL)) {
                        this.mDidInitInterstitial = true;
                    }
                    if (ad_unit2.equals(IronSource.AD_UNIT.BANNER)) {
                        this.mDidInitBanner = true;
                    }
                    if (ad_unit2.equals(IronSource.AD_UNIT.REWARDED_VIDEO)) {
                        this.mDidInitRewardedVideo = true;
                    }
                }
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init(appKey:" + str + ")", 1);
            if (activity == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Init Fail - provided activity is null", 2);
                return;
            }
            this.mActivity = activity;
            prepareEventManagers(activity);
            ConfigValidationResult configValidationResultValidateAppKey = validateAppKey(str);
            if (configValidationResultValidateAppKey.isValid()) {
                this.mAppKey = str;
                if (this.mShouldSendGetInstanceEvent) {
                    JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(z);
                    if (ad_unitArr != null) {
                        try {
                            for (IronSource.AD_UNIT ad_unit3 : ad_unitArr) {
                                mediationAdditionalData.put(ad_unit3.toString(), true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    int i = this.mInitCounter + 1;
                    this.mInitCounter = i;
                    mediationAdditionalData.put(Constants.RequestParameters.SESSION_DEPTH, i);
                    RewardedVideoEventsManager.getInstance().log(new EventData(14, mediationAdditionalData));
                    this.mShouldSendGetInstanceEvent = false;
                }
                if (this.mAdUnitsToInitialize.contains(IronSource.AD_UNIT.INTERSTITIAL)) {
                    MediationInitializer.getInstance().addMediationInitializationListener(this.mInterstitialManager);
                }
                MediationInitializer.getInstance().addMediationInitializationListener(this);
                MediationInitializer.getInstance().init(activity, str, this.mUserId, ad_unitArr);
            } else {
                MediationInitializer.getInstance().setInitStatusFailed();
                if (this.mAdUnitsToInitialize.contains(IronSource.AD_UNIT.REWARDED_VIDEO)) {
                    this.mListenersWrapper.onRewardedVideoAvailabilityChanged(false);
                }
                if (this.mAdUnitsToInitialize.contains(IronSource.AD_UNIT.OFFERWALL)) {
                    this.mListenersWrapper.onOfferwallAvailable(false, configValidationResultValidateAppKey.getIronSourceError());
                }
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, configValidationResultValidateAppKey.getIronSourceError().toString(), 1);
            }
        } else if (ad_unitArr != null) {
            attachAdUnits(z, ad_unitArr);
        } else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Multiple calls to init without ad units are not allowed", 3);
        }
    }

    public synchronized void initISDemandOnly(Activity activity, String str, IronSource.AD_UNIT... ad_unitArr) {
        ArrayList arrayList = new ArrayList();
        if (ad_unitArr == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Cannot initialized demand only mode: No ad units selected", 3);
            return;
        }
        if (ad_unitArr.length <= 0) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Cannot initialized demand only mode: No ad units selected", 3);
            return;
        }
        for (IronSource.AD_UNIT ad_unit : ad_unitArr) {
            if (ad_unit.equals(IronSource.AD_UNIT.BANNER) || ad_unit.equals(IronSource.AD_UNIT.OFFERWALL)) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ad_unit + " ad unit cannot be initialized in demand only mode", 3);
            } else {
                if (ad_unit.equals(IronSource.AD_UNIT.INTERSTITIAL)) {
                    if (this.mDidInitInterstitial) {
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ad_unit + " ad unit has already been initialized", 3);
                    } else {
                        this.mDidInitInterstitial = true;
                        this.mIsDemandOnlyIs = true;
                        if (!arrayList.contains(ad_unit)) {
                            arrayList.add(ad_unit);
                        }
                    }
                }
                if (ad_unit.equals(IronSource.AD_UNIT.REWARDED_VIDEO)) {
                    if (this.mDidInitRewardedVideo) {
                        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ad_unit + " ad unit has already been initialized", 3);
                    } else {
                        this.mDidInitRewardedVideo = true;
                        this.mIsDemandOnlyRv = true;
                        if (!arrayList.contains(ad_unit)) {
                            arrayList.add(ad_unit);
                        }
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            init(activity, str, true, (IronSource.AD_UNIT[]) arrayList.toArray(new IronSource.AD_UNIT[arrayList.size()]));
        }
    }

    private synchronized void attachAdUnits(boolean z, IronSource.AD_UNIT... ad_unitArr) {
        int i = 0;
        for (IronSource.AD_UNIT ad_unit : ad_unitArr) {
            if (ad_unit.equals(IronSource.AD_UNIT.INTERSTITIAL)) {
                this.mDidInitInterstitial = true;
            } else if (ad_unit.equals(IronSource.AD_UNIT.BANNER)) {
                this.mDidInitBanner = true;
            }
        }
        if (MediationInitializer.getInstance().getCurrentInitStatus() == MediationInitializer.EInitStatus.INIT_FAILED) {
            try {
                if (this.mListenersWrapper != null) {
                    int length = ad_unitArr.length;
                    while (i < length) {
                        IronSource.AD_UNIT ad_unit2 = ad_unitArr[i];
                        if (!this.mAdUnitsToInitialize.contains(ad_unit2)) {
                            notifyPublisherAboutInitFailed(ad_unit2, true);
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!this.mInitSucceeded) {
            JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(z);
            int length2 = ad_unitArr.length;
            boolean z2 = false;
            while (i < length2) {
                IronSource.AD_UNIT ad_unit3 = ad_unitArr[i];
                if (!this.mAdUnitsToInitialize.contains(ad_unit3)) {
                    this.mAdUnitsToInitialize.add(ad_unit3);
                    this.mRequestedAdUnits.add(ad_unit3);
                    try {
                        mediationAdditionalData.put(ad_unit3.toString(), true);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    z2 = true;
                } else {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ad_unit3 + " ad unit has started initializing.", 3);
                }
                i++;
            }
            if (z2) {
                try {
                    int i2 = this.mInitCounter + 1;
                    this.mInitCounter = i2;
                    mediationAdditionalData.put(Constants.RequestParameters.SESSION_DEPTH, i2);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                RewardedVideoEventsManager.getInstance().log(new EventData(14, mediationAdditionalData));
            }
        }
        if (this.mInitiatedAdUnits == null) {
            return;
        }
        JSONObject mediationAdditionalData2 = IronSourceUtils.getMediationAdditionalData(z);
        boolean z3 = false;
        for (IronSource.AD_UNIT ad_unit4 : ad_unitArr) {
            if (!this.mAdUnitsToInitialize.contains(ad_unit4)) {
                this.mAdUnitsToInitialize.add(ad_unit4);
                this.mRequestedAdUnits.add(ad_unit4);
                try {
                    mediationAdditionalData2.put(ad_unit4.toString(), true);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                if (this.mInitiatedAdUnits != null && this.mInitiatedAdUnits.contains(ad_unit4)) {
                    startAdUnit(ad_unit4);
                } else {
                    notifyPublisherAboutInitFailed(ad_unit4, false);
                }
                z3 = true;
            } else {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, ad_unit4 + " ad unit has already been initialized", 3);
            }
        }
        if (z3) {
            try {
                int i3 = this.mInitCounter + 1;
                this.mInitCounter = i3;
                mediationAdditionalData2.put(Constants.RequestParameters.SESSION_DEPTH, i3);
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            RewardedVideoEventsManager.getInstance().log(new EventData(14, mediationAdditionalData2));
        }
    }

    @Override // com.ironsource.mediationsdk.MediationInitializer.OnMediationInitializationListener
    public void onInitSuccess(List<IronSource.AD_UNIT> list, boolean z) {
        try {
            this.mInitiatedAdUnits = list;
            this.mInitSucceeded = true;
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "onInitSuccess()", 1);
            IronSourceUtils.sendAutomationLog("init success");
            if (z) {
                JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
                try {
                    mediationAdditionalData.put("revived", true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RewardedVideoEventsManager.getInstance().log(new EventData(114, mediationAdditionalData));
            }
            InterstitialEventsManager.getInstance().triggerEventsSend();
            RewardedVideoEventsManager.getInstance().triggerEventsSend();
            for (IronSource.AD_UNIT ad_unit : IronSource.AD_UNIT.values()) {
                if (this.mAdUnitsToInitialize.contains(ad_unit)) {
                    if (list.contains(ad_unit)) {
                        startAdUnit(ad_unit);
                    } else {
                        notifyPublisherAboutInitFailed(ad_unit, false);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void startAdUnit(IronSource.AD_UNIT ad_unit) {
        int i = AnonymousClass1.$SwitchMap$com$ironsource$mediationsdk$IronSource$AD_UNIT[ad_unit.ordinal()];
        if (i == 1) {
            startRewardedVideo();
            return;
        }
        if (i == 2) {
            startInterstitial();
        } else if (i == 3) {
            this.mOfferwallManager.initOfferwall(this.mActivity, getIronSourceAppKey(), getIronSourceUserId());
        } else {
            if (i != 4) {
                return;
            }
            startBanner();
        }
    }

    private void startProgrammaticRv() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Rewarded Video started in programmatic mode", 0);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getRewardedVideoProviderOrder().size(); i++) {
            String str = this.mCurrentServerResponse.getProviderOrder().getRewardedVideoProviderOrder().get(i);
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(str));
            }
        }
        if (arrayList.size() > 0) {
            this.mProgRvManager = new ProgRvManager(this.mActivity, arrayList, this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations(), getIronSourceAppKey(), getIronSourceUserId());
            Boolean bool = this.mConsent;
            if (bool != null) {
                this.mProgRvManager.setConsent(bool.booleanValue());
                return;
            }
            return;
        }
        notifyPublisherAboutInitFailed(IronSource.AD_UNIT.REWARDED_VIDEO, false);
    }

    private void startDemandOnlyRv() {
        synchronized (this.mDemandOnlyRvLoadBeforeInitCompleted) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Rewarded Video started in demand only mode", 0);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getRewardedVideoProviderOrder().size(); i++) {
                String str = this.mCurrentServerResponse.getProviderOrder().getRewardedVideoProviderOrder().get(i);
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(str));
                }
            }
            if (arrayList.size() > 0) {
                this.mDemandOnlyRvManager = new DemandOnlyRvManager(this.mActivity, arrayList, this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations(), getIronSourceAppKey(), getIronSourceUserId());
                if (this.mConsent != null) {
                    this.mDemandOnlyRvManager.setConsent(this.mConsent.booleanValue());
                }
                Iterator<String> it = this.mDemandOnlyRvLoadBeforeInitCompleted.iterator();
                while (it.hasNext()) {
                    this.mDemandOnlyRvManager.loadRewardedVideo(it.next());
                }
                this.mDemandOnlyRvLoadBeforeInitCompleted.clear();
            } else {
                notifyPublisherAboutInitFailed(IronSource.AD_UNIT.REWARDED_VIDEO, false);
            }
        }
    }

    private void startRewardedVideo() {
        ProviderSettings providerSettings;
        ProviderSettings providerSettings2;
        ProviderSettings providerSettings3;
        if (this.mIsDemandOnlyRv) {
            startDemandOnlyRv();
            return;
        }
        this.mIsRvProgrammatic = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoAuctionSettings().getIsProgrammatic();
        if (this.mIsRvProgrammatic) {
            startProgrammaticRv();
            return;
        }
        int rewardedVideoAdaptersSmartLoadTimeout = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoAdaptersSmartLoadTimeout();
        for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getRewardedVideoProviderOrder().size(); i++) {
            String str = this.mCurrentServerResponse.getProviderOrder().getRewardedVideoProviderOrder().get(i);
            if (!TextUtils.isEmpty(str) && (providerSettings3 = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(str)) != null) {
                RewardedVideoSmash rewardedVideoSmash = new RewardedVideoSmash(providerSettings3, rewardedVideoAdaptersSmartLoadTimeout);
                if (validateSmash(rewardedVideoSmash)) {
                    rewardedVideoSmash.setRewardedVideoManagerListener(this.mRewardedVideoManager);
                    rewardedVideoSmash.setProviderPriority(i + 1);
                    this.mRewardedVideoManager.addSmashToArray(rewardedVideoSmash);
                }
            }
        }
        if (this.mRewardedVideoManager.mSmashArray.size() > 0) {
            this.mRewardedVideoManager.setIsUltraEventsEnabled(this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().isUltraEventsEnabled());
            this.mRewardedVideoManager.setSmartLoadAmount(this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoAdaptersSmartLoadAmount());
            this.mRewardedVideoManager.setManualLoadInterval(this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations().getManualLoadIntervalInSeconds());
            String rVBackFillProvider = this.mCurrentServerResponse.getRVBackFillProvider();
            if (!TextUtils.isEmpty(rVBackFillProvider) && (providerSettings2 = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(rVBackFillProvider)) != null) {
                RewardedVideoSmash rewardedVideoSmash2 = new RewardedVideoSmash(providerSettings2, rewardedVideoAdaptersSmartLoadTimeout);
                if (validateSmash(rewardedVideoSmash2)) {
                    rewardedVideoSmash2.setRewardedVideoManagerListener(this.mRewardedVideoManager);
                    this.mRewardedVideoManager.setBackfillSmash(rewardedVideoSmash2);
                }
            }
            String rVPremiumProvider = this.mCurrentServerResponse.getRVPremiumProvider();
            if (!TextUtils.isEmpty(rVPremiumProvider) && (providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(rVPremiumProvider)) != null) {
                RewardedVideoSmash rewardedVideoSmash3 = new RewardedVideoSmash(providerSettings, rewardedVideoAdaptersSmartLoadTimeout);
                if (validateSmash(rewardedVideoSmash3)) {
                    rewardedVideoSmash3.setRewardedVideoManagerListener(this.mRewardedVideoManager);
                    this.mRewardedVideoManager.setPremiumSmash(rewardedVideoSmash3);
                }
            }
            this.mRewardedVideoManager.initRewardedVideo(this.mActivity, getIronSourceAppKey(), getIronSourceUserId());
            return;
        }
        notifyPublisherAboutInitFailed(IronSource.AD_UNIT.REWARDED_VIDEO, false);
    }

    private void startProgrammaticIs() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Interstitial started in programmatic mode", 0);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getInterstitialProviderOrder().size(); i++) {
            String str = this.mCurrentServerResponse.getProviderOrder().getInterstitialProviderOrder().get(i);
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(str));
            }
        }
        if (arrayList.size() > 0) {
            this.mProgIsManager = new ProgIsManager(this.mActivity, arrayList, this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations(), getIronSourceAppKey(), getIronSourceUserId(), this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getISDelayLoadFailure());
            Boolean bool = this.mConsent;
            if (bool != null) {
                this.mProgIsManager.setConsent(bool.booleanValue());
            }
            if (this.mIsIsLoadBeforeInitCompleted) {
                this.mIsIsLoadBeforeInitCompleted = false;
                this.mProgIsManager.loadInterstitial();
                return;
            }
            return;
        }
        notifyPublisherAboutInitFailed(IronSource.AD_UNIT.INTERSTITIAL, false);
    }

    private void startDemandOnlyIs() {
        synchronized (this.mDemandOnlyIsLoadBeforeInitCompleted) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "Interstitial started in demand only mode", 0);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getInterstitialProviderOrder().size(); i++) {
                String str = this.mCurrentServerResponse.getProviderOrder().getInterstitialProviderOrder().get(i);
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(str));
                }
            }
            if (arrayList.size() > 0) {
                this.mDemandOnlyIsManager = new DemandOnlyIsManager(this.mActivity, arrayList, this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations(), getIronSourceAppKey(), getIronSourceUserId());
                if (this.mConsent != null) {
                    this.mDemandOnlyIsManager.setConsent(this.mConsent.booleanValue());
                }
                Iterator<String> it = this.mDemandOnlyIsLoadBeforeInitCompleted.iterator();
                while (it.hasNext()) {
                    this.mDemandOnlyIsManager.loadInterstitial(it.next());
                }
                this.mDemandOnlyIsLoadBeforeInitCompleted.clear();
            } else {
                notifyPublisherAboutInitFailed(IronSource.AD_UNIT.INTERSTITIAL, false);
            }
        }
    }

    private void startInterstitial() {
        ProviderSettings providerSettings;
        if (this.mIsDemandOnlyIs) {
            startDemandOnlyIs();
            return;
        }
        this.mIsIsProgrammatic = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialAuctionSettings().getIsProgrammatic();
        if (this.mIsIsProgrammatic) {
            startProgrammaticIs();
            return;
        }
        int interstitialAdaptersSmartLoadTimeout = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialAdaptersSmartLoadTimeout();
        this.mInterstitialManager.setDelayLoadFailureNotificationInSeconds(this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getISDelayLoadFailure());
        for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getInterstitialProviderOrder().size(); i++) {
            String str = this.mCurrentServerResponse.getProviderOrder().getInterstitialProviderOrder().get(i);
            if (!TextUtils.isEmpty(str) && (providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(str)) != null) {
                InterstitialSmash interstitialSmash = new InterstitialSmash(providerSettings, interstitialAdaptersSmartLoadTimeout);
                if (validateSmash(interstitialSmash)) {
                    interstitialSmash.setInterstitialManagerListener(this.mInterstitialManager);
                    interstitialSmash.setProviderPriority(i + 1);
                    this.mInterstitialManager.addSmashToArray(interstitialSmash);
                }
            }
        }
        if (this.mInterstitialManager.mSmashArray.size() > 0) {
            this.mInterstitialManager.setSmartLoadAmount(this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations().getInterstitialAdaptersSmartLoadAmount());
            this.mInterstitialManager.initInterstitial(this.mActivity, getIronSourceAppKey(), getIronSourceUserId());
            if (this.mIsIsLoadBeforeInitCompleted) {
                this.mIsIsLoadBeforeInitCompleted = false;
                this.mInterstitialManager.loadInterstitial();
                return;
            }
            return;
        }
        notifyPublisherAboutInitFailed(IronSource.AD_UNIT.INTERSTITIAL, false);
    }

    private void startBanner() {
        ProviderSettings providerSettings;
        synchronized (this.mIsBnLoadBeforeInitCompleted) {
            long bannerAdaptersSmartLoadTimeout = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerAdaptersSmartLoadTimeout();
            int bannerRefreshInterval = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerRefreshInterval();
            int bannerDelayLoadFailure = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerDelayLoadFailure();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.mCurrentServerResponse.getProviderOrder().getBannerProviderOrder().size(); i++) {
                String str = this.mCurrentServerResponse.getProviderOrder().getBannerProviderOrder().get(i);
                if (!TextUtils.isEmpty(str) && (providerSettings = this.mCurrentServerResponse.getProviderSettingsHolder().getProviderSettings(str)) != null) {
                    arrayList.add(providerSettings);
                }
            }
            this.mBannerManager = new BannerManager(arrayList, this.mActivity, getIronSourceAppKey(), getIronSourceUserId(), bannerAdaptersSmartLoadTimeout, bannerRefreshInterval, bannerDelayLoadFailure);
            if (this.mIsBnLoadBeforeInitCompleted.booleanValue()) {
                this.mIsBnLoadBeforeInitCompleted = false;
                loadBanner(this.mBnLayoutToLoad, this.mBnPlacementToLoad);
                this.mBnLayoutToLoad = null;
                this.mBnPlacementToLoad = null;
            }
        }
    }

    private boolean validateSmash(AbstractSmash abstractSmash) {
        return abstractSmash.getMaxAdsPerIteration() >= 1 && abstractSmash.getMaxAdsPerSession() >= 1;
    }

    @Override // com.ironsource.mediationsdk.MediationInitializer.OnMediationInitializationListener
    public void onInitFailed(String str) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "onInitFailed(reason:" + str + ")", 1);
            if (this.mListenersWrapper != null) {
                Iterator<IronSource.AD_UNIT> it = this.mAdUnitsToInitialize.iterator();
                while (it.hasNext()) {
                    notifyPublisherAboutInitFailed(it.next(), true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.ironsource.mediationsdk.MediationInitializer.OnMediationInitializationListener
    public void onStillInProgressAfter15Secs() {
        synchronized (this.mIsBnLoadBeforeInitCompleted) {
            if (this.mIsBnLoadBeforeInitCompleted.booleanValue()) {
                this.mIsBnLoadBeforeInitCompleted = false;
                BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(this.mBnLayoutToLoad, new IronSourceError(IronSourceError.ERROR_BN_LOAD_WHILE_LONG_INITIATION, "init had failed"));
                this.mBnLayoutToLoad = null;
                this.mBnPlacementToLoad = null;
            }
        }
        if (this.mIsIsLoadBeforeInitCompleted) {
            this.mIsIsLoadBeforeInitCompleted = false;
            CallbackThrottler.getInstance().onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
        }
        synchronized (this.mDemandOnlyIsLoadBeforeInitCompleted) {
            Iterator<String> it = this.mDemandOnlyIsLoadBeforeInitCompleted.iterator();
            while (it.hasNext()) {
                ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(it.next(), ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
            }
            this.mDemandOnlyIsLoadBeforeInitCompleted.clear();
        }
        synchronized (this.mDemandOnlyRvLoadBeforeInitCompleted) {
            Iterator<String> it2 = this.mDemandOnlyRvLoadBeforeInitCompleted.iterator();
            while (it2.hasNext()) {
                RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(it2.next(), ErrorBuilder.buildInitFailedError("init() had failed", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            }
            this.mDemandOnlyRvLoadBeforeInitCompleted.clear();
        }
    }

    private void notifyPublisherAboutInitFailed(IronSource.AD_UNIT ad_unit, boolean z) {
        int i = AnonymousClass1.$SwitchMap$com$ironsource$mediationsdk$IronSource$AD_UNIT[ad_unit.ordinal()];
        if (i == 1) {
            if (this.mIsDemandOnlyRv) {
                Iterator<String> it = this.mDemandOnlyRvLoadBeforeInitCompleted.iterator();
                while (it.hasNext()) {
                    RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(it.next(), ErrorBuilder.buildInitFailedError("initISDemandOnly() had failed", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                }
                this.mDemandOnlyRvLoadBeforeInitCompleted.clear();
                return;
            }
            if (z || isRewardedVideoConfigurationsReady() || this.mRequestedAdUnits.contains(ad_unit)) {
                this.mListenersWrapper.onRewardedVideoAvailabilityChanged(false);
                return;
            }
            return;
        }
        if (i == 2) {
            if (this.mIsDemandOnlyIs) {
                Iterator<String> it2 = this.mDemandOnlyIsLoadBeforeInitCompleted.iterator();
                while (it2.hasNext()) {
                    ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(it2.next(), ErrorBuilder.buildInitFailedError("initISDemandOnly() had failed", "Interstitial"));
                }
                this.mDemandOnlyIsLoadBeforeInitCompleted.clear();
                return;
            }
            if (this.mIsIsLoadBeforeInitCompleted) {
                this.mIsIsLoadBeforeInitCompleted = false;
                CallbackThrottler.getInstance().onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                return;
            }
            return;
        }
        if (i == 3) {
            if (z || isOfferwallConfigurationsReady() || this.mRequestedAdUnits.contains(ad_unit)) {
                this.mListenersWrapper.onOfferwallAvailable(false);
                return;
            }
            return;
        }
        if (i != 4) {
            return;
        }
        synchronized (this.mIsBnLoadBeforeInitCompleted) {
            if (this.mIsBnLoadBeforeInitCompleted.booleanValue()) {
                this.mIsBnLoadBeforeInitCompleted = false;
                BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(this.mBnLayoutToLoad, new IronSourceError(IronSourceError.ERROR_BN_INIT_FAILED_AFTER_LOAD, "Init had failed"));
                this.mBnLayoutToLoad = null;
                this.mBnPlacementToLoad = null;
            }
        }
    }

    private void prepareEventManagers(Activity activity) {
        AtomicBoolean atomicBoolean = this.mEventManagersInit;
        if (atomicBoolean == null || !atomicBoolean.compareAndSet(false, true)) {
            return;
        }
        SuperLooper.getLooper().post(new GeneralPropertiesWorker(activity.getApplicationContext()));
        InterstitialEventsManager.getInstance().start(activity.getApplicationContext(), this.mIronSegment);
        RewardedVideoEventsManager.getInstance().start(activity.getApplicationContext(), this.mIronSegment);
    }

    synchronized void addToRVAdaptersList(AbstractAdapter abstractAdapter) {
        if (this.mRewardedVideoAdaptersList != null && abstractAdapter != null && !this.mRewardedVideoAdaptersList.contains(abstractAdapter)) {
            this.mRewardedVideoAdaptersList.add(abstractAdapter);
        }
    }

    synchronized void addToISAdaptersList(AbstractAdapter abstractAdapter) {
        if (this.mInterstitialAdaptersList != null && abstractAdapter != null && !this.mInterstitialAdaptersList.contains(abstractAdapter)) {
            this.mInterstitialAdaptersList.add(abstractAdapter);
        }
    }

    synchronized void addToBannerAdaptersList(AbstractAdapter abstractAdapter) {
        if (this.mBannerAdaptersList != null && abstractAdapter != null && !this.mBannerAdaptersList.contains(abstractAdapter)) {
            this.mBannerAdaptersList.add(abstractAdapter);
        }
    }

    synchronized void addOWAdapter(AbstractAdapter abstractAdapter) {
        this.mOfferwallAdapter = abstractAdapter;
    }

    synchronized AbstractAdapter getExistingAdapter(String str) {
        try {
            if (this.mRewardedVideoAdaptersList != null) {
                for (AbstractAdapter abstractAdapter : this.mRewardedVideoAdaptersList) {
                    if (abstractAdapter.getProviderName().equals(str)) {
                        return abstractAdapter;
                    }
                }
            }
            if (this.mInterstitialAdaptersList != null) {
                for (AbstractAdapter abstractAdapter2 : this.mInterstitialAdaptersList) {
                    if (abstractAdapter2.getProviderName().equals(str)) {
                        return abstractAdapter2;
                    }
                }
            }
            if (this.mBannerAdaptersList != null) {
                for (AbstractAdapter abstractAdapter3 : this.mBannerAdaptersList) {
                    if (abstractAdapter3.getProviderName().equals(str)) {
                        return abstractAdapter3;
                    }
                }
            }
            if (this.mOfferwallAdapter != null && this.mOfferwallAdapter.getProviderName().equals(str)) {
                return this.mOfferwallAdapter;
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "getExistingAdapter exception: " + e, 1);
        }
        return null;
    }

    private void initializeManagers() {
        this.mLoggerManager = IronSourceLoggerManager.getLogger(0);
        this.mPublisherLogger = new PublisherLogger(null, 1);
        this.mLoggerManager.addLogger(this.mPublisherLogger);
        this.mListenersWrapper = new ListenersWrapper();
        this.mRewardedVideoManager = new RewardedVideoManager();
        this.mRewardedVideoManager.setRewardedVideoListener(this.mListenersWrapper);
        this.mInterstitialManager = new InterstitialManager();
        this.mInterstitialManager.setInterstitialListener(this.mListenersWrapper);
        this.mInterstitialManager.setRewardedInterstitialListener(this.mListenersWrapper);
        this.mOfferwallManager = new OfferwallManager();
        this.mOfferwallManager.setInternalOfferwallListener(this.mListenersWrapper);
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void onResume(Activity activity) {
        try {
            this.mActivity = activity;
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "onResume()", 1);
            if (this.mRewardedVideoManager != null) {
                this.mRewardedVideoManager.onResume(activity);
            }
            if (this.mInterstitialManager != null) {
                this.mInterstitialManager.onResume(activity);
            }
            if (this.mBannerManager != null) {
                this.mBannerManager.onResume(activity);
            }
            if (this.mProgRvManager != null) {
                this.mProgRvManager.onResume(activity);
            }
            if (this.mProgIsManager != null) {
                this.mProgIsManager.onResume(activity);
            }
            if (this.mDemandOnlyIsManager != null) {
                this.mDemandOnlyIsManager.onResume(activity);
            }
            if (this.mDemandOnlyRvManager != null) {
                this.mDemandOnlyRvManager.onResume(activity);
            }
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "onResume()", th);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void onPause(Activity activity) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "onPause()", 1);
            if (this.mRewardedVideoManager != null) {
                this.mRewardedVideoManager.onPause(activity);
            }
            if (this.mInterstitialManager != null) {
                this.mInterstitialManager.onPause(activity);
            }
            if (this.mBannerManager != null) {
                this.mBannerManager.onPause(activity);
            }
            if (this.mProgRvManager != null) {
                this.mProgRvManager.onPause(activity);
            }
            if (this.mProgIsManager != null) {
                this.mProgIsManager.onPause(activity);
            }
            if (this.mDemandOnlyIsManager != null) {
                this.mDemandOnlyIsManager.onPause(activity);
            }
            if (this.mDemandOnlyRvManager != null) {
                this.mDemandOnlyRvManager.onPause(activity);
            }
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "onPause()", th);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public synchronized void setAge(int i) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":setAge(age:" + i + ")", 1);
            ConfigValidationResult configValidationResult = new ConfigValidationResult();
            validateAge(i, configValidationResult);
            if (configValidationResult.isValid()) {
                this.mUserAge = Integer.valueOf(i);
            } else {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, configValidationResult.getIronSourceError().toString(), 2);
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setAge(age:" + i + ")", e);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public synchronized void setGender(String str) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":setGender(gender:" + str + ")", 1);
            ConfigValidationResult configValidationResult = new ConfigValidationResult();
            validateGender(str, configValidationResult);
            if (configValidationResult.isValid()) {
                this.mUserGender = str;
            } else {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, configValidationResult.getIronSourceError().toString(), 2);
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setGender(gender:" + str + ")", e);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BaseApi
    public void setMediationSegment(String str) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":setMediationSegment(segment:" + str + ")", 1);
            ConfigValidationResult configValidationResult = new ConfigValidationResult();
            validateSegment(str, configValidationResult);
            if (configValidationResult.isValid()) {
                this.mSegment = str;
            } else {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, configValidationResult.getIronSourceError().toString(), 2);
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setMediationSegment(segment:" + str + ")", e);
        }
    }

    public void setSegment(IronSourceSegment ironSourceSegment) {
        if (MediationInitializer.getInstance().getCurrentInitStatus() == MediationInitializer.EInitStatus.INIT_IN_PROGRESS || MediationInitializer.getInstance().getCurrentInitStatus() == MediationInitializer.EInitStatus.INITIATED) {
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "Segments must be set prior to Init. Setting a segment after the init will be ignored", 0);
        } else {
            this.mIronSegment = ironSourceSegment;
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public boolean setDynamicUserId(String str) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":setDynamicUserId(dynamicUserId:" + str + ")", 1);
            ConfigValidationResult configValidationResult = new ConfigValidationResult();
            validateDynamicUserId(str, configValidationResult);
            if (configValidationResult.isValid()) {
                this.mDynamicUserId = str;
                return true;
            }
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, configValidationResult.getIronSourceError().toString(), 2);
            return false;
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setDynamicUserId(dynamicUserId:" + str + ")", e);
            return false;
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public void setAdaptersDebug(boolean z) {
        IronSourceLoggerManager.getLogger().setAdaptersDebug(z);
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public void setMediationType(String str) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, this.TAG + ":setMediationType(mediationType:" + str + ")", 1);
            if (validateLength(str, 1, 64) && validateAlphanumeric(str)) {
                this.mMediationType = str;
            } else {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, " mediationType value is invalid - should be alphanumeric and 1-64 chars in length", 1);
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setMediationType(mediationType:" + str + ")", e);
        }
    }

    public synchronized Integer getAge() {
        return this.mUserAge;
    }

    public synchronized String getGender() {
        return this.mUserGender;
    }

    synchronized String getMediationSegment() {
        return this.mSegment;
    }

    synchronized String getDynamicUserId() {
        return this.mDynamicUserId;
    }

    synchronized Map<String, String> getRvServerParams() {
        return this.mRvServerParams;
    }

    public synchronized String getMediationType() {
        return this.mMediationType;
    }

    public void showRewardedVideo() {
        Placement defaultRewardedVideoPlacement = getDefaultRewardedVideoPlacement();
        if (defaultRewardedVideoPlacement == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "showRewardedVideo error: empty default placement in response", 3);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(new IronSourceError(1021, "showRewardedVideo error: empty default placement in response"));
            return;
        }
        showRewardedVideo(defaultRewardedVideoPlacement.getPlacementName());
    }

    private Placement getRewardedVideoPlacement(String str) {
        RewardedVideoConfigurations rewardedVideoConfigurations = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations();
        if (rewardedVideoConfigurations != null) {
            return rewardedVideoConfigurations.getRewardedVideoPlacement(str);
        }
        return null;
    }

    private Placement getDefaultRewardedVideoPlacement() {
        RewardedVideoConfigurations rewardedVideoConfigurations = this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations();
        if (rewardedVideoConfigurations != null) {
            return rewardedVideoConfigurations.getDefaultRewardedVideoPlacement();
        }
        return null;
    }

    private void showProgrammaticRewardedVideo(String str) {
        Placement rewardedVideoPlacement = getRewardedVideoPlacement(str);
        if (rewardedVideoPlacement == null) {
            rewardedVideoPlacement = getDefaultRewardedVideoPlacement();
        }
        if (rewardedVideoPlacement == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "showProgrammaticRewardedVideo error: empty default placement in response", 3);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(new IronSourceError(1021, "showProgrammaticRewardedVideo error: empty default placement in response"));
            return;
        }
        this.mProgRvManager.showRewardedVideo(rewardedVideoPlacement);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoApi
    public void showRewardedVideo(String str) {
        String str2 = "showRewardedVideo(" + str + ")";
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, str2, 1);
        try {
            if (this.mIsDemandOnlyRv) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded Video was initialized in demand only mode. Use showISDemandOnlyRewardedVideo instead", 3);
                this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildInitFailedError("Rewarded Video was initialized in demand only mode. Use showISDemandOnlyRewardedVideo instead", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                return;
            }
            if (!isRewardedVideoConfigurationsReady()) {
                this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildInitFailedError("showRewardedVideo can't be called before the Rewarded Video ad unit initialization completed successfully", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                return;
            }
            if (this.mIsRvProgrammatic && this.mProgRvManager != null) {
                showProgrammaticRewardedVideo(str);
                return;
            }
            Placement placementToShowWithEvent = getPlacementToShowWithEvent(str);
            if (placementToShowWithEvent != null) {
                this.mRewardedVideoManager.setCurrentPlacement(placementToShowWithEvent);
                this.mRewardedVideoManager.showRewardedVideo(placementToShowWithEvent.getPlacementName());
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, str2, e);
            this.mListenersWrapper.onRewardedVideoAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, e.getMessage()));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoApi
    public boolean isRewardedVideoAvailable() {
        Throwable th;
        boolean zIsRewardedVideoAvailable;
        try {
            if (this.mIsDemandOnlyRv) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded Video was initialized in demand only mode. Use isISDemandOnlyRewardedVideoAvailable instead", 3);
                return false;
            }
            if (this.mIsRvProgrammatic) {
                zIsRewardedVideoAvailable = this.mProgRvManager != null && this.mProgRvManager.isRewardedVideoAvailable();
            } else {
                zIsRewardedVideoAvailable = this.mRewardedVideoManager.isRewardedVideoAvailable();
            }
            try {
                JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
                if (this.mIsRvProgrammatic) {
                    addToDictionary(mediationAdditionalData, new Object[][]{new Object[]{IronSourceConstants.EVENTS_PROGRAMMATIC, 1}});
                }
                RewardedVideoEventsManager.getInstance().log(new EventData(zIsRewardedVideoAvailable ? IronSourceConstants.RV_API_HAS_AVAILABILITY_TRUE : IronSourceConstants.RV_API_HAS_AVAILABILITY_FALSE, mediationAdditionalData));
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isRewardedVideoAvailable():" + zIsRewardedVideoAvailable, 1);
                return zIsRewardedVideoAvailable;
            } catch (Throwable th2) {
                th = th2;
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isRewardedVideoAvailable():" + zIsRewardedVideoAvailable, 1);
                this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "isRewardedVideoAvailable()", th);
                return false;
            }
        } catch (Throwable th3) {
            th = th3;
            zIsRewardedVideoAvailable = false;
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoApi
    public void setRewardedVideoListener(RewardedVideoListener rewardedVideoListener) {
        if (rewardedVideoListener == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setRewardedVideoListener(RVListener:null)", 1);
        } else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setRewardedVideoListener(RVListener)", 1);
        }
        this.mListenersWrapper.setRewardedVideoListener(rewardedVideoListener);
        RVListenerWrapper.getInstance().setListener(rewardedVideoListener);
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public void setRewardedVideoServerParameters(Map<String, String> map) {
        if (map != null) {
            try {
                if (map.size() == 0) {
                    return;
                }
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, this.TAG + ":setRewardedVideoServerParameters(params:" + map.toString() + ")", 1);
                this.mRvServerParams = new HashMap(map);
            } catch (Exception e) {
                this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, this.TAG + ":setRewardedVideoServerParameters(params:" + map.toString() + ")", e);
            }
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public void clearRewardedVideoServerParameters() {
        this.mRvServerParams = null;
    }

    public synchronized void loadDemandOnlyRewardedVideo(String str) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadISDemandOnlyRewardedVideo() instanceId=" + str, 1);
        try {
            if (!this.mDidInitRewardedVideo) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "initISDemandOnly() must be called before loadISDemandOnlyRewardedVideo()", 3);
                RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_INIT_FAILED, "initISDemandOnly() must be called before loadISDemandOnlyRewardedVideo()"));
                return;
            }
            if (!this.mIsDemandOnlyRv) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded video was initialized in mediation mode", 3);
                RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_INIT_FAILED, "Rewarded video was initialized in mediation mode"));
                return;
            }
            MediationInitializer.EInitStatus currentInitStatus = MediationInitializer.getInstance().getCurrentInitStatus();
            if (currentInitStatus == MediationInitializer.EInitStatus.INIT_FAILED) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(str, ErrorBuilder.buildInitFailedError("init() had failed", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                return;
            }
            if (currentInitStatus == MediationInitializer.EInitStatus.INIT_IN_PROGRESS) {
                if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                    RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(str, ErrorBuilder.buildInitFailedError("init() had failed", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
                } else {
                    this.mDemandOnlyRvLoadBeforeInitCompleted.add(str);
                }
                return;
            }
            synchronized (this.mDemandOnlyRvLoadBeforeInitCompleted) {
                if (this.mDemandOnlyRvManager == null) {
                    this.mDemandOnlyRvLoadBeforeInitCompleted.add(str);
                    return;
                }
                if (this.mCurrentServerResponse != null && this.mCurrentServerResponse.getConfigurations() != null && this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations() != null) {
                    this.mDemandOnlyRvManager.loadRewardedVideo(str);
                    return;
                }
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "No rewarded video configurations found", 3);
                RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(str, ErrorBuilder.buildInitFailedError("the server response does not contain rewarded video data", IronSourceConstants.REWARDED_VIDEO_AD_UNIT));
            }
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "loadISDemandOnlyRewardedVideo", th);
            RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdLoadFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, th.getMessage()));
        }
    }

    public synchronized void showDemandOnlyRewardedVideo(String str) {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "showISDemandOnlyRewardedVideo() instanceId=" + str, 1);
            try {
                if (!this.mIsDemandOnlyRv) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded video was initialized in mediation mode. Use showRewardedVideo instead", 3);
                    RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdShowFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_INIT_FAILED, "Rewarded video was initialized in mediation mode. Use showRewardedVideo instead"));
                } else if (this.mDemandOnlyRvManager == null) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Rewarded video was not initiated", 3);
                    RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdShowFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_INIT_FAILED, "Rewarded video was not initiated"));
                } else {
                    this.mDemandOnlyRvManager.showRewardedVideo(str);
                }
            } catch (Exception e) {
                this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "showISDemandOnlyRewardedVideo", e);
                RVDemandOnlyListenerWrapper.getInstance().onRewardedVideoAdShowFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, e.getMessage()));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized boolean isDemandOnlyRewardedVideoAvailable(String str) {
        return this.mDemandOnlyRvManager != null && this.mDemandOnlyRvManager.isRewardedVideoAvailable(str);
    }

    void setISDemandOnlyRewardedVideoListener(ISDemandOnlyRewardedVideoListener iSDemandOnlyRewardedVideoListener) {
        RVDemandOnlyListenerWrapper.getInstance().setListener(iSDemandOnlyRewardedVideoListener);
    }

    private boolean isRewardedVideoConfigurationsReady() {
        ServerResponseWrapper serverResponseWrapper = this.mCurrentServerResponse;
        return (serverResponseWrapper == null || serverResponseWrapper.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations() == null) ? false : true;
    }

    private Placement getPlacementToShowWithEvent(String str) {
        Placement rewardedVideoPlacement = getRewardedVideoPlacement(str);
        if (rewardedVideoPlacement == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
            rewardedVideoPlacement = getDefaultRewardedVideoPlacement();
            if (rewardedVideoPlacement == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Default placement was not found, please make sure you are using the right placements.", 3);
                return null;
            }
        }
        String cappingMessage = getCappingMessage(rewardedVideoPlacement.getPlacementName(), CappingManager.isPlacementCapped(this.mActivity, rewardedVideoPlacement));
        if (TextUtils.isEmpty(cappingMessage)) {
            return rewardedVideoPlacement;
        }
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, cappingMessage, 1);
        this.mListenersWrapper.onRewardedVideoAdShowFailed(ErrorBuilder.buildCappedPerPlacementError(cappingMessage));
        return null;
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialApi
    public void loadInterstitial() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadInterstitial()", 1);
        try {
            if (this.mIsDemandOnlyIs) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in demand only mode. Use loadISDemandOnlyInterstitial instead", 3);
                CallbackThrottler.getInstance().onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("Interstitial was initialized in demand only mode. Use loadISDemandOnlyInterstitial instead", "Interstitial"));
                return;
            }
            if (!this.mDidInitInterstitial) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() must be called before loadInterstitial()", 3);
                CallbackThrottler.getInstance().onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() must be called before loadInterstitial()", "Interstitial"));
                return;
            }
            MediationInitializer.EInitStatus currentInitStatus = MediationInitializer.getInstance().getCurrentInitStatus();
            if (currentInitStatus == MediationInitializer.EInitStatus.INIT_FAILED) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                CallbackThrottler.getInstance().onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                return;
            }
            if (currentInitStatus == MediationInitializer.EInitStatus.INIT_IN_PROGRESS) {
                if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                    CallbackThrottler.getInstance().onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                    return;
                } else {
                    this.mIsIsLoadBeforeInitCompleted = true;
                    return;
                }
            }
            if (this.mCurrentServerResponse != null && this.mCurrentServerResponse.getConfigurations() != null && this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations() != null) {
                if (this.mIsIsProgrammatic) {
                    this.mProgIsManager.loadInterstitial();
                    return;
                } else {
                    this.mInterstitialManager.loadInterstitial();
                    return;
                }
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "No interstitial configurations found", 3);
            CallbackThrottler.getInstance().onInterstitialAdLoadFailed(ErrorBuilder.buildInitFailedError("the server response does not contain interstitial data", "Interstitial"));
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "loadInterstitial()", th);
            CallbackThrottler.getInstance().onInterstitialAdLoadFailed(new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, th.getMessage()));
        }
    }

    public void showInterstitial() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "showInterstitial()", 1);
        try {
            if (this.mIsDemandOnlyIs) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in demand only mode. Use showISDemandOnlyInterstitial instead", 3);
                this.mListenersWrapper.onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, "Interstitial was initialized in demand only mode. Use showISDemandOnlyInterstitial instead"));
            } else {
                if (!isInterstitialConfigurationsReady()) {
                    this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildInitFailedError("showInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
                    return;
                }
                InterstitialPlacement defaultInterstitialPlacement = getDefaultInterstitialPlacement();
                if (defaultInterstitialPlacement != null) {
                    showInterstitial(defaultInterstitialPlacement.getPlacementName());
                } else {
                    this.mListenersWrapper.onInterstitialAdShowFailed(new IronSourceError(1020, "showInterstitial error: empty default placement in response"));
                }
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "showInterstitial()", e);
            this.mListenersWrapper.onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, e.getMessage()));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialApi
    public void showInterstitial(String str) {
        String str2 = "showInterstitial(" + str + ")";
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, str2, 1);
        try {
            if (this.mIsDemandOnlyIs) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in demand only mode. Use showISDemandOnlyInterstitial instead", 3);
                this.mListenersWrapper.onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, "Interstitial was initialized in demand only mode. Use showISDemandOnlyInterstitial instead"));
                return;
            }
            if (!isInterstitialConfigurationsReady()) {
                this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildInitFailedError("showInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
                return;
            }
            if (this.mIsIsProgrammatic) {
                showProgrammaticInterstitial(str);
                return;
            }
            InterstitialPlacement interstitialPlacementToShowWithEvent = getInterstitialPlacementToShowWithEvent(str);
            JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
            try {
                if (interstitialPlacementToShowWithEvent != null) {
                    mediationAdditionalData.put("placement", interstitialPlacementToShowWithEvent.getPlacementName());
                } else if (!TextUtils.isEmpty(str)) {
                    mediationAdditionalData.put("placement", str);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            InterstitialEventsManager.getInstance().log(new EventData(2100, mediationAdditionalData));
            if (interstitialPlacementToShowWithEvent != null) {
                this.mInterstitialManager.setCurrentPlacement(interstitialPlacementToShowWithEvent);
                this.mInterstitialManager.showInterstitial(interstitialPlacementToShowWithEvent.getPlacementName());
            }
        } catch (Exception e2) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, str2, e2);
            this.mListenersWrapper.onInterstitialAdShowFailed(new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, e2.getMessage()));
        }
    }

    private InterstitialPlacement getInterstitialPlacement(String str) {
        InterstitialConfigurations interstitialConfigurations = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations();
        if (interstitialConfigurations != null) {
            return interstitialConfigurations.getInterstitialPlacement(str);
        }
        return null;
    }

    private InterstitialPlacement getDefaultInterstitialPlacement() {
        InterstitialConfigurations interstitialConfigurations = this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations();
        if (interstitialConfigurations != null) {
            return interstitialConfigurations.getDefaultInterstitialPlacement();
        }
        return null;
    }

    private void showProgrammaticInterstitial(String str) {
        String placementName = null;
        try {
            InterstitialPlacement interstitialPlacement = getInterstitialPlacement(str);
            if (interstitialPlacement == null) {
                interstitialPlacement = getDefaultInterstitialPlacement();
            }
            if (interstitialPlacement != null) {
                placementName = interstitialPlacement.getPlacementName();
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "showProgrammaticInterstitial()", e);
        }
        this.mProgIsManager.showInterstitial(placementName);
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialApi
    public boolean isInterstitialReady() {
        Throwable th;
        boolean z;
        try {
            if (this.mIsDemandOnlyIs) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in demand only mode. Use isISDemandOnlyInterstitialReady instead", 3);
                return false;
            }
            z = !this.mIsIsProgrammatic ? this.mInterstitialManager == null || !this.mInterstitialManager.isInterstitialReady() : this.mProgIsManager == null || !this.mProgIsManager.isInterstitialReady();
            try {
                InterstitialEventsManager.getInstance().log(new EventData(z ? IronSourceConstants.IS_CHECK_READY_TRUE : IronSourceConstants.IS_CHECK_READY_FALSE, IronSourceUtils.getMediationAdditionalData(false, this.mIsIsProgrammatic)));
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isInterstitialReady():" + z, 1);
                return z;
            } catch (Throwable th2) {
                th = th2;
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "isInterstitialReady():" + z, 1);
                this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "isInterstitialReady()", th);
                return false;
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialApi
    public void setInterstitialListener(InterstitialListener interstitialListener) {
        if (interstitialListener == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setInterstitialListener(ISListener:null)", 1);
        } else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setInterstitialListener(ISListener)", 1);
        }
        this.mListenersWrapper.setInterstitialListener(interstitialListener);
        ISListenerWrapper.getInstance().setListener(interstitialListener);
        CallbackThrottler.getInstance().setInterstitialListener(interstitialListener);
    }

    public synchronized void loadDemandOnlyInterstitial(String str) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadISDemandOnlyInterstitial() instanceId=" + str, 1);
        try {
            if (!this.mDidInitInterstitial) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "initISDemandOnly() must be called before loadISDemandOnlyInterstitial()", 3);
                ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, "initISDemandOnly() must be called before loadISDemandOnlyInterstitial()"));
                return;
            }
            if (!this.mIsDemandOnlyIs) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in mediation mode. Use loadInterstitial instead", 3);
                ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, "Interstitial was initialized in mediation mode. Use loadInterstitial instead"));
                return;
            }
            MediationInitializer.EInitStatus currentInitStatus = MediationInitializer.getInstance().getCurrentInitStatus();
            if (currentInitStatus == MediationInitializer.EInitStatus.INIT_FAILED) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(str, ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                return;
            }
            if (currentInitStatus == MediationInitializer.EInitStatus.INIT_IN_PROGRESS) {
                if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                    ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(str, ErrorBuilder.buildInitFailedError("init() had failed", "Interstitial"));
                } else {
                    this.mDemandOnlyIsLoadBeforeInitCompleted.add(str);
                }
                return;
            }
            synchronized (this.mDemandOnlyIsLoadBeforeInitCompleted) {
                if (this.mDemandOnlyIsManager == null) {
                    this.mDemandOnlyIsLoadBeforeInitCompleted.add(str);
                    return;
                }
                if (this.mCurrentServerResponse != null && this.mCurrentServerResponse.getConfigurations() != null && this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations() != null) {
                    this.mDemandOnlyIsManager.loadInterstitial(str);
                    return;
                }
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "No interstitial configurations found", 3);
                ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(str, ErrorBuilder.buildInitFailedError("the server response does not contain interstitial data", "Interstitial"));
            }
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "loadDemandOnlyInterstitial", th);
            ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdLoadFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, th.getMessage()));
        }
    }

    public void showDemandOnlyInterstitial(String str) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "showISDemandOnlyInterstitial() instanceId=" + str, 1);
        try {
            if (!this.mIsDemandOnlyIs) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial was initialized in mediation mode. Use showInterstitial instead", 3);
            } else if (this.mDemandOnlyIsManager == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Interstitial video was not initiated", 3);
                ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdShowFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_INIT_FAILED, "Interstitial video was not initiated"));
            } else {
                this.mDemandOnlyIsManager.showInterstitial(str);
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "showISDemandOnlyInterstitial", e);
            ISDemandOnlyListenerWrapper.getInstance().onInterstitialAdShowFailed(str, ErrorBuilder.buildInitFailedError("showISDemandOnlyInterstitial can't be called before the Interstitial ad unit initialization completed successfully", "Interstitial"));
        }
    }

    public synchronized boolean isDemandOnlyInterstitialReady(String str) {
        return this.mDemandOnlyIsManager != null && this.mDemandOnlyIsManager.isInterstitialReady(str);
    }

    public void setISDemandOnlyInterstitialListener(ISDemandOnlyInterstitialListener iSDemandOnlyInterstitialListener) {
        ISDemandOnlyListenerWrapper.getInstance().setListener(iSDemandOnlyInterstitialListener);
    }

    private boolean isInterstitialConfigurationsReady() {
        ServerResponseWrapper serverResponseWrapper = this.mCurrentServerResponse;
        return (serverResponseWrapper == null || serverResponseWrapper.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations() == null) ? false : true;
    }

    private InterstitialPlacement getInterstitialPlacementToShowWithEvent(String str) {
        InterstitialPlacement interstitialPlacement = getInterstitialPlacement(str);
        if (interstitialPlacement == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
            interstitialPlacement = getDefaultInterstitialPlacement();
            if (interstitialPlacement == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Default placement was not found, please make sure you are using the right placements.", 3);
                return null;
            }
        }
        String cappingMessage = getCappingMessage(interstitialPlacement.getPlacementName(), getInterstitialCappingStatus(interstitialPlacement.getPlacementName()));
        if (TextUtils.isEmpty(cappingMessage)) {
            return interstitialPlacement;
        }
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, cappingMessage, 1);
        this.mListenersWrapper.setInterstitialPlacement(interstitialPlacement);
        this.mListenersWrapper.onInterstitialAdShowFailed(ErrorBuilder.buildCappedPerPlacementError(cappingMessage));
        return null;
    }

    private boolean isOfferwallConfigurationsReady() {
        ServerResponseWrapper serverResponseWrapper = this.mCurrentServerResponse;
        return (serverResponseWrapper == null || serverResponseWrapper.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations() == null) ? false : true;
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public void showOfferwall() {
        try {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "showOfferwall()", 1);
            if (!isOfferwallConfigurationsReady()) {
                this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", IronSourceConstants.OFFERWALL_AD_UNIT));
                return;
            }
            OfferwallPlacement defaultOfferwallPlacement = this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations().getDefaultOfferwallPlacement();
            if (defaultOfferwallPlacement != null) {
                showOfferwall(defaultOfferwallPlacement.getPlacementName());
            }
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "showOfferwall()", e);
            this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", IronSourceConstants.OFFERWALL_AD_UNIT));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public void showOfferwall(String str) {
        String str2 = "showOfferwall(" + str + ")";
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, str2, 1);
        try {
            if (!isOfferwallConfigurationsReady()) {
                this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", IronSourceConstants.OFFERWALL_AD_UNIT));
                return;
            }
            OfferwallPlacement offerwallPlacement = this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations().getOfferwallPlacement(str);
            if (offerwallPlacement == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Placement is not valid, please make sure you are using the right placements, using the default placement.", 3);
                offerwallPlacement = this.mCurrentServerResponse.getConfigurations().getOfferwallConfigurations().getDefaultOfferwallPlacement();
                if (offerwallPlacement == null) {
                    this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Default placement was not found, please make sure you are using the right placements.", 3);
                    return;
                }
            }
            this.mOfferwallManager.showOfferwall(offerwallPlacement.getPlacementName());
        } catch (Exception e) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, str2, e);
            this.mListenersWrapper.onOfferwallShowFailed(ErrorBuilder.buildInitFailedError("showOfferwall can't be called before the Offerwall ad unit initialization completed successfully", IronSourceConstants.OFFERWALL_AD_UNIT));
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public boolean isOfferwallAvailable() {
        try {
            if (this.mOfferwallManager != null) {
                return this.mOfferwallManager.isOfferwallAvailable();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public void getOfferwallCredits() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "getOfferwallCredits()", 1);
        try {
            this.mOfferwallManager.getOfferwallCredits();
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "getOfferwallCredits()", th);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallApi
    public void setOfferwallListener(OfferwallListener offerwallListener) {
        if (offerwallListener == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setOfferwallListener(OWListener:null)", 1);
        } else {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setOfferwallListener(OWListener)", 1);
        }
        this.mListenersWrapper.setOfferwallListener(offerwallListener);
    }

    @Override // com.ironsource.mediationsdk.logger.LoggingApi
    public void setLogListener(LogListener logListener) {
        if (logListener == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setLogListener(LogListener:null)", 1);
            return;
        }
        this.mPublisherLogger.setLogListener(logListener);
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "setLogListener(LogListener:" + logListener.getClass().getSimpleName() + ")", 1);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedInterstitialApi
    public void setRewardedInterstitialListener(RewardedInterstitialListener rewardedInterstitialListener) {
        this.mListenersWrapper.setRewardedInterstitialListener(rewardedInterstitialListener);
    }

    private boolean isBannerConfigurationsReady() {
        ServerResponseWrapper serverResponseWrapper = this.mCurrentServerResponse;
        return (serverResponseWrapper == null || serverResponseWrapper.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getBannerConfigurations() == null) ? false : true;
    }

    public IronSourceBannerLayout createBanner(Activity activity, ISBannerSize iSBannerSize) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "createBanner()", 1);
        if (activity == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "createBanner() : Activity cannot be null", 3);
            return null;
        }
        return new IronSourceBannerLayout(activity, iSBannerSize);
    }

    public void loadBanner(IronSourceBannerLayout ironSourceBannerLayout, String str) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadBanner(" + str + ")", 1);
        if (ironSourceBannerLayout == null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadBanner can't be called with a null parameter", 1);
            return;
        }
        if (!this.mDidInitBanner) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() must be called before loadBanner()", 3);
            return;
        }
        if (ironSourceBannerLayout.getSize().getDescription().equals("CUSTOM") && (ironSourceBannerLayout.getSize().getWidth() <= 0 || ironSourceBannerLayout.getSize().getHeight() <= 0)) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "loadBanner: Unsupported banner size. Height and width must be bigger than 0", 3);
            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(ironSourceBannerLayout, ErrorBuilder.unsupportedBannerSize(""));
            return;
        }
        MediationInitializer.EInitStatus currentInitStatus = MediationInitializer.getInstance().getCurrentInitStatus();
        if (currentInitStatus == MediationInitializer.EInitStatus.INIT_FAILED) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(ironSourceBannerLayout, new IronSourceError(600, "Init() had failed"));
            return;
        }
        if (currentInitStatus == MediationInitializer.EInitStatus.INIT_IN_PROGRESS) {
            if (MediationInitializer.getInstance().isInProgressMoreThan15Secs()) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "init() had failed", 3);
                BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(ironSourceBannerLayout, new IronSourceError(601, "Init had failed"));
                return;
            } else {
                this.mBnLayoutToLoad = ironSourceBannerLayout;
                this.mIsBnLoadBeforeInitCompleted = true;
                this.mBnPlacementToLoad = str;
                return;
            }
        }
        synchronized (this.mIsBnLoadBeforeInitCompleted) {
            if (this.mBannerManager == null) {
                this.mIsBnLoadBeforeInitCompleted = true;
                return;
            }
            ServerResponseWrapper serverResponseWrapper = this.mCurrentServerResponse;
            if (serverResponseWrapper == null || serverResponseWrapper.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getBannerConfigurations() == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "No banner configurations found", 3);
                BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(ironSourceBannerLayout, new IronSourceError(IronSourceError.ERROR_BN_LOAD_NO_CONFIG, "No banner configurations found"));
            } else {
                this.mBannerManager.loadBanner(ironSourceBannerLayout, getBannerPlacement(str));
            }
        }
    }

    public void loadBanner(IronSourceBannerLayout ironSourceBannerLayout) {
        loadBanner(ironSourceBannerLayout, "");
    }

    public void destroyBanner(IronSourceBannerLayout ironSourceBannerLayout) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "destroyBanner()", 1);
        try {
            if (this.mBannerManager != null) {
                this.mBannerManager.destroyBanner(ironSourceBannerLayout);
            }
        } catch (Throwable th) {
            this.mLoggerManager.logException(IronSourceLogger.IronSourceTag.API, "destroyBanner()", th);
        }
    }

    ServerResponseWrapper getServerResponse(Context context, String str, IResponseListener iResponseListener) {
        synchronized (this.mServerResponseLocker) {
            if (this.mCurrentServerResponse != null) {
                return new ServerResponseWrapper(this.mCurrentServerResponse);
            }
            ServerResponseWrapper serverResponseWrapperConnectAndGetServerResponse = connectAndGetServerResponse(context, str, iResponseListener);
            if (serverResponseWrapperConnectAndGetServerResponse == null || !serverResponseWrapperConnectAndGetServerResponse.isValidResponse()) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "Null or invalid response. Trying to get cached response", 0);
                serverResponseWrapperConnectAndGetServerResponse = getCachedResponse(context, str);
            }
            if (serverResponseWrapperConnectAndGetServerResponse != null) {
                this.mCurrentServerResponse = serverResponseWrapperConnectAndGetServerResponse;
                IronSourceUtils.saveLastResponse(context, serverResponseWrapperConnectAndGetServerResponse.toString());
                initializeSettingsFromServerResponse(this.mCurrentServerResponse, context);
            }
            InterstitialEventsManager.getInstance().setHasServerResponse(true);
            RewardedVideoEventsManager.getInstance().setHasServerResponse(true);
            return serverResponseWrapperConnectAndGetServerResponse;
        }
    }

    private ServerResponseWrapper getCachedResponse(Context context, String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(IronSourceUtils.getLastResponse(context));
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        String strOptString = jSONObject.optString(ServerResponseWrapper.APP_KEY_FIELD);
        String strOptString2 = jSONObject.optString(ServerResponseWrapper.USER_ID_FIELD);
        String strOptString3 = jSONObject.optString(ServerResponseWrapper.RESPONSE_FIELD);
        if (TextUtils.isEmpty(strOptString) || TextUtils.isEmpty(strOptString2) || TextUtils.isEmpty(strOptString3) || getIronSourceAppKey() == null || !strOptString.equals(getIronSourceAppKey()) || !strOptString2.equals(str)) {
            return null;
        }
        ServerResponseWrapper serverResponseWrapper = new ServerResponseWrapper(context, strOptString, strOptString2, strOptString3);
        IronSourceError ironSourceErrorBuildUsingCachedConfigurationError = ErrorBuilder.buildUsingCachedConfigurationError(strOptString, strOptString2);
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, ironSourceErrorBuildUsingCachedConfigurationError.toString(), 1);
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, ironSourceErrorBuildUsingCachedConfigurationError.toString() + ": " + serverResponseWrapper.toString(), 1);
        RewardedVideoEventsManager.getInstance().log(new EventData(IronSourceConstants.USING_CACHE_FOR_INIT_EVENT, IronSourceUtils.getMediationAdditionalData(false)));
        return serverResponseWrapper;
    }

    private ServerResponseWrapper connectAndGetServerResponse(Context context, String str, IResponseListener iResponseListener) {
        ServerResponseWrapper serverResponseWrapper;
        if (!IronSourceUtils.isNetworkConnected(context)) {
            return null;
        }
        try {
            String advertiserId = getAdvertiserId(context);
            if (TextUtils.isEmpty(advertiserId)) {
                advertiserId = DeviceStatus.getOrGenerateOnceUniqueIdentifier(context);
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "using custom identifier", 1);
            }
            String stringFromURL = HttpFunctions.getStringFromURL(ServerURL.getCPVProvidersURL(context, getIronSourceAppKey(), str, advertiserId, getMediationType(), this.mIronSegment != null ? this.mIronSegment.getSegmentData() : null), iResponseListener);
            if (stringFromURL == null) {
                return null;
            }
            if (IronSourceUtils.getSerr() == 1) {
                String strOptString = new JSONObject(stringFromURL).optString(ServerResponseWrapper.RESPONSE_FIELD, null);
                if (TextUtils.isEmpty(strOptString)) {
                    return null;
                }
                stringFromURL = IronSourceAES.decode(IronSourceUtils.KEY, strOptString);
            }
            serverResponseWrapper = new ServerResponseWrapper(context, getIronSourceAppKey(), str, stringFromURL);
            try {
                if (serverResponseWrapper.isValidResponse()) {
                    return serverResponseWrapper;
                }
                return null;
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
            serverResponseWrapper = null;
        }
        e.printStackTrace();
        return serverResponseWrapper;
    }

    private void initializeSettingsFromServerResponse(ServerResponseWrapper serverResponseWrapper, Context context) {
        initializeLoggerManager(serverResponseWrapper);
        initializeEventsSettings(serverResponseWrapper, context);
    }

    private void initializeEventsSettings(ServerResponseWrapper serverResponseWrapper, Context context) {
        boolean zIsEventsEnabled = isRewardedVideoConfigurationsReady() ? serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().isEventsEnabled() : false;
        boolean zIsEventsEnabled2 = isInterstitialConfigurationsReady() ? serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().isEventsEnabled() : false;
        boolean zIsEventsEnabled3 = isBannerConfigurationsReady() ? serverResponseWrapper.getConfigurations().getBannerConfigurations().getBannerEventsConfigurations().isEventsEnabled() : false;
        boolean zIsEventsEnabled4 = isOfferwallConfigurationsReady() ? serverResponseWrapper.getConfigurations().getOfferwallConfigurations().getOfferWallEventsConfigurations().isEventsEnabled() : false;
        if (zIsEventsEnabled) {
            RewardedVideoEventsManager.getInstance().setFormatterType(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getEventsType(), context);
            RewardedVideoEventsManager.getInstance().setEventsUrl(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getEventsURL(), context);
            RewardedVideoEventsManager.getInstance().setMaxNumberOfEvents(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getMaxNumberOfEvents());
            RewardedVideoEventsManager.getInstance().setMaxEventsPerBatch(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getMaxEventsPerBatch());
            RewardedVideoEventsManager.getInstance().setBackupThreshold(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getEventsBackupThreshold());
            RewardedVideoEventsManager.getInstance().setOptOutEvents(serverResponseWrapper.getConfigurations().getRewardedVideoConfigurations().getRewardedVideoEventsConfigurations().getOptOutEvents(), context);
            RewardedVideoEventsManager.getInstance().setServerSegmentData(serverResponseWrapper.getConfigurations().getApplicationConfigurations().getSegmetData());
        } else if (zIsEventsEnabled4) {
            RewardedVideoEventsManager.getInstance().setFormatterType(serverResponseWrapper.getConfigurations().getOfferwallConfigurations().getOfferWallEventsConfigurations().getEventsType(), context);
            RewardedVideoEventsManager.getInstance().setEventsUrl(serverResponseWrapper.getConfigurations().getOfferwallConfigurations().getOfferWallEventsConfigurations().getEventsURL(), context);
            RewardedVideoEventsManager.getInstance().setMaxNumberOfEvents(serverResponseWrapper.getConfigurations().getOfferwallConfigurations().getOfferWallEventsConfigurations().getMaxNumberOfEvents());
            RewardedVideoEventsManager.getInstance().setMaxEventsPerBatch(serverResponseWrapper.getConfigurations().getOfferwallConfigurations().getOfferWallEventsConfigurations().getMaxEventsPerBatch());
            RewardedVideoEventsManager.getInstance().setBackupThreshold(serverResponseWrapper.getConfigurations().getOfferwallConfigurations().getOfferWallEventsConfigurations().getEventsBackupThreshold());
            RewardedVideoEventsManager.getInstance().setOptOutEvents(serverResponseWrapper.getConfigurations().getOfferwallConfigurations().getOfferWallEventsConfigurations().getOptOutEvents(), context);
            RewardedVideoEventsManager.getInstance().setServerSegmentData(serverResponseWrapper.getConfigurations().getApplicationConfigurations().getSegmetData());
        } else {
            RewardedVideoEventsManager.getInstance().setIsEventsEnabled(false);
        }
        if (zIsEventsEnabled2) {
            InterstitialEventsManager.getInstance().setFormatterType(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getEventsType(), context);
            InterstitialEventsManager.getInstance().setEventsUrl(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getEventsURL(), context);
            InterstitialEventsManager.getInstance().setMaxNumberOfEvents(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getMaxNumberOfEvents());
            InterstitialEventsManager.getInstance().setMaxEventsPerBatch(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getMaxEventsPerBatch());
            InterstitialEventsManager.getInstance().setBackupThreshold(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getEventsBackupThreshold());
            InterstitialEventsManager.getInstance().setOptOutEvents(serverResponseWrapper.getConfigurations().getInterstitialConfigurations().getInterstitialEventsConfigurations().getOptOutEvents(), context);
            InterstitialEventsManager.getInstance().setServerSegmentData(serverResponseWrapper.getConfigurations().getApplicationConfigurations().getSegmetData());
            return;
        }
        if (zIsEventsEnabled3) {
            ApplicationEvents bannerEventsConfigurations = serverResponseWrapper.getConfigurations().getBannerConfigurations().getBannerEventsConfigurations();
            InterstitialEventsManager.getInstance().setFormatterType(bannerEventsConfigurations.getEventsType(), context);
            InterstitialEventsManager.getInstance().setEventsUrl(bannerEventsConfigurations.getEventsURL(), context);
            InterstitialEventsManager.getInstance().setMaxNumberOfEvents(bannerEventsConfigurations.getMaxNumberOfEvents());
            InterstitialEventsManager.getInstance().setMaxEventsPerBatch(bannerEventsConfigurations.getMaxEventsPerBatch());
            InterstitialEventsManager.getInstance().setBackupThreshold(bannerEventsConfigurations.getEventsBackupThreshold());
            InterstitialEventsManager.getInstance().setOptOutEvents(bannerEventsConfigurations.getOptOutEvents(), context);
            InterstitialEventsManager.getInstance().setServerSegmentData(serverResponseWrapper.getConfigurations().getApplicationConfigurations().getSegmetData());
            return;
        }
        InterstitialEventsManager.getInstance().setIsEventsEnabled(false);
    }

    private void initializeLoggerManager(ServerResponseWrapper serverResponseWrapper) {
        this.mPublisherLogger.setDebugLevel(serverResponseWrapper.getConfigurations().getApplicationConfigurations().getLoggerConfigurations().getPublisherLoggerLevel());
        this.mLoggerManager.setLoggerDebugLevel(ConsoleLogger.NAME, serverResponseWrapper.getConfigurations().getApplicationConfigurations().getLoggerConfigurations().getConsoleLoggerLevel());
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public void removeRewardedVideoListener() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "removeRewardedVideoListener()", 1);
        this.mListenersWrapper.setRewardedVideoListener(null);
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public void removeInterstitialListener() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "removeInterstitialListener()", 1);
        this.mListenersWrapper.setInterstitialListener(null);
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public void removeOfferwallListener() {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "removeOfferwallListener()", 1);
        this.mListenersWrapper.setOfferwallListener(null);
    }

    synchronized void setIronSourceUserId(String str) {
        this.mUserId = str;
    }

    public synchronized String getIronSourceAppKey() {
        return this.mAppKey;
    }

    public synchronized String getIronSourceUserId() {
        return this.mUserId;
    }

    private ConfigValidationResult validateAppKey(String str) {
        ConfigValidationResult configValidationResult = new ConfigValidationResult();
        if (str != null) {
            if (validateLength(str, 5, 10)) {
                if (!validateAlphanumeric(str)) {
                    configValidationResult.setInvalid(ErrorBuilder.buildInvalidCredentialsError(ServerResponseWrapper.APP_KEY_FIELD, str, "should contain only english characters and numbers"));
                }
            } else {
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidCredentialsError(ServerResponseWrapper.APP_KEY_FIELD, str, "length should be between 5-10 characters"));
            }
        } else {
            configValidationResult.setInvalid(new IronSourceError(IronSourceError.ERROR_CODE_INVALID_KEY_VALUE, "Init Fail - appKey is missing"));
        }
        return configValidationResult;
    }

    private void validateGender(String str, ConfigValidationResult configValidationResult) {
        if (str != null) {
            try {
                String strTrim = str.toLowerCase().trim();
                if ("male".equals(strTrim) || "female".equals(strTrim) || "unknown".equals(strTrim)) {
                    return;
                }
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("gender", IronSourceConstants.SUPERSONIC_CONFIG_NAME, "gender value should be one of male/female/unknown."));
            } catch (Exception unused) {
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("gender", IronSourceConstants.SUPERSONIC_CONFIG_NAME, "gender value should be one of male/female/unknown."));
            }
        }
    }

    private void validateAge(int i, ConfigValidationResult configValidationResult) {
        if (i < 5 || i > 120) {
            try {
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError(IronSourceSegment.AGE, IronSourceConstants.SUPERSONIC_CONFIG_NAME, "age value should be between 5-120"));
            } catch (NumberFormatException unused) {
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError(IronSourceSegment.AGE, IronSourceConstants.SUPERSONIC_CONFIG_NAME, "age value should be between 5-120"));
            }
        }
    }

    private void validateSegment(String str, ConfigValidationResult configValidationResult) {
        if (str != null) {
            try {
                if (str.length() > 64) {
                    configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("segment", IronSourceConstants.SUPERSONIC_CONFIG_NAME, "segment value should not exceed 64 characters."));
                }
            } catch (Exception unused) {
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError("segment", IronSourceConstants.SUPERSONIC_CONFIG_NAME, "segment value should not exceed 64 characters."));
            }
        }
    }

    private void validateDynamicUserId(String str, ConfigValidationResult configValidationResult) {
        if (validateLength(str, 1, 128)) {
            return;
        }
        configValidationResult.setInvalid(ErrorBuilder.buildInvalidKeyValueError(IronSourceConstants.EVENTS_DYNAMIC_USER_ID, IronSourceConstants.SUPERSONIC_CONFIG_NAME, "dynamicUserId is invalid, should be between 1-128 chars in length."));
    }

    private boolean validateLength(String str, int i, int i2) {
        return str != null && str.length() >= i && str.length() <= i2;
    }

    private boolean validateAlphanumeric(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^[a-zA-Z0-9]*$");
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public InterstitialPlacement getInterstitialPlacementInfo(String str) {
        try {
            InterstitialPlacement interstitialPlacement = getInterstitialPlacement(str);
            try {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "getPlacementInfo(placement: " + str + "):" + interstitialPlacement, 1);
                return interstitialPlacement;
            } catch (Exception unused) {
                return interstitialPlacement;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public Placement getRewardedVideoPlacementInfo(String str) {
        try {
            Placement rewardedVideoPlacement = getRewardedVideoPlacement(str);
            try {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "getPlacementInfo(placement: " + str + "):" + rewardedVideoPlacement, 1);
                return rewardedVideoPlacement;
            } catch (Exception unused) {
                return rewardedVideoPlacement;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public String getAdvertiserId(Context context) {
        try {
            String[] advertisingIdInfo = DeviceStatus.getAdvertisingIdInfo(context);
            if (advertisingIdInfo.length > 0 && advertisingIdInfo[0] != null) {
                return advertisingIdInfo[0];
            }
        } catch (Exception unused) {
        }
        return "";
    }

    @Override // com.ironsource.mediationsdk.sdk.IronSourceInterface
    public void shouldTrackNetworkState(Context context, boolean z) {
        RewardedVideoManager rewardedVideoManager = this.mRewardedVideoManager;
        if (rewardedVideoManager != null) {
            rewardedVideoManager.shouldTrackNetworkState(context, z);
        }
        InterstitialManager interstitialManager = this.mInterstitialManager;
        if (interstitialManager != null) {
            interstitialManager.shouldTrackNetworkState(context, z);
        }
    }

    boolean isInterstitialPlacementCapped(String str) {
        if (this.mIsDemandOnlyIs) {
            return false;
        }
        boolean z = getInterstitialCappingStatus(str) != CappingManager.ECappingStatus.NOT_CAPPED;
        if (z) {
            JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(this.mIsDemandOnlyIs, this.mIsIsProgrammatic);
            try {
                mediationAdditionalData.put("placement", str);
                if (this.mIsIsProgrammatic) {
                    mediationAdditionalData.put(IronSourceConstants.EVENTS_PROGRAMMATIC, 1);
                }
            } catch (Exception unused) {
            }
            InterstitialEventsManager.getInstance().log(new EventData(IronSourceConstants.IS_CHECK_CAPPED_TRUE, mediationAdditionalData));
        }
        return z;
    }

    boolean isRewardedVideoPlacementCapped(String str) {
        int i;
        CappingManager.ECappingStatus rewardedVideoCappingStatus = getRewardedVideoCappingStatus(str);
        boolean z = true;
        if (rewardedVideoCappingStatus == null || ((i = AnonymousClass1.$SwitchMap$com$ironsource$mediationsdk$utils$CappingManager$ECappingStatus[rewardedVideoCappingStatus.ordinal()]) != 1 && i != 2 && i != 3)) {
            z = false;
        }
        sendIsCappedEvent(z, str);
        return z;
    }

    /* JADX INFO: renamed from: com.ironsource.mediationsdk.IronSourceObject$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ironsource$mediationsdk$IronSource$AD_UNIT;
        static final /* synthetic */ int[] $SwitchMap$com$ironsource$mediationsdk$utils$CappingManager$ECappingStatus = new int[CappingManager.ECappingStatus.values().length];

        static {
            try {
                $SwitchMap$com$ironsource$mediationsdk$utils$CappingManager$ECappingStatus[CappingManager.ECappingStatus.CAPPED_PER_DELIVERY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ironsource$mediationsdk$utils$CappingManager$ECappingStatus[CappingManager.ECappingStatus.CAPPED_PER_COUNT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ironsource$mediationsdk$utils$CappingManager$ECappingStatus[CappingManager.ECappingStatus.CAPPED_PER_PACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ironsource$mediationsdk$utils$CappingManager$ECappingStatus[CappingManager.ECappingStatus.NOT_CAPPED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$ironsource$mediationsdk$IronSource$AD_UNIT = new int[IronSource.AD_UNIT.values().length];
            try {
                $SwitchMap$com$ironsource$mediationsdk$IronSource$AD_UNIT[IronSource.AD_UNIT.REWARDED_VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$ironsource$mediationsdk$IronSource$AD_UNIT[IronSource.AD_UNIT.INTERSTITIAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$ironsource$mediationsdk$IronSource$AD_UNIT[IronSource.AD_UNIT.OFFERWALL.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$ironsource$mediationsdk$IronSource$AD_UNIT[IronSource.AD_UNIT.BANNER.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    boolean isBannerPlacementCapped(String str) {
        ServerResponseWrapper serverResponseWrapper = this.mCurrentServerResponse;
        if (serverResponseWrapper == null || serverResponseWrapper.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getBannerConfigurations() == null) {
            return false;
        }
        BannerPlacement bannerPlacement = null;
        try {
            bannerPlacement = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getBannerPlacement(str);
            if (bannerPlacement == null && (bannerPlacement = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations().getDefaultBannerPlacement()) == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Banner default placement was not found", 3);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bannerPlacement == null) {
            return false;
        }
        return CappingManager.isBnPlacementCapped(this.mActivity, bannerPlacement.getPlacementName());
    }

    private CappingManager.ECappingStatus getInterstitialCappingStatus(String str) {
        ServerResponseWrapper serverResponseWrapper = this.mCurrentServerResponse;
        if (serverResponseWrapper == null || serverResponseWrapper.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getInterstitialConfigurations() == null) {
            return CappingManager.ECappingStatus.NOT_CAPPED;
        }
        InterstitialPlacement interstitialPlacement = null;
        try {
            interstitialPlacement = getInterstitialPlacement(str);
            if (interstitialPlacement == null && (interstitialPlacement = getDefaultInterstitialPlacement()) == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Default placement was not found", 3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (interstitialPlacement == null) {
            return CappingManager.ECappingStatus.NOT_CAPPED;
        }
        return CappingManager.isPlacementCapped(this.mActivity, interstitialPlacement);
    }

    private CappingManager.ECappingStatus getRewardedVideoCappingStatus(String str) {
        ServerResponseWrapper serverResponseWrapper = this.mCurrentServerResponse;
        if (serverResponseWrapper == null || serverResponseWrapper.getConfigurations() == null || this.mCurrentServerResponse.getConfigurations().getRewardedVideoConfigurations() == null) {
            return CappingManager.ECappingStatus.NOT_CAPPED;
        }
        Placement rewardedVideoPlacement = null;
        try {
            rewardedVideoPlacement = getRewardedVideoPlacement(str);
            if (rewardedVideoPlacement == null && (rewardedVideoPlacement = getDefaultRewardedVideoPlacement()) == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Default placement was not found", 3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rewardedVideoPlacement == null) {
            return CappingManager.ECappingStatus.NOT_CAPPED;
        }
        return CappingManager.isPlacementCapped(this.mActivity, rewardedVideoPlacement);
    }

    private void sendIsCappedEvent(boolean z, String str) {
        if (z) {
            JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(this.mIsDemandOnlyRv);
            if (str != null) {
                addToDictionary(mediationAdditionalData, new Object[][]{new Object[]{"placement", str}});
            }
            if (this.mIsRvProgrammatic) {
                addToDictionary(mediationAdditionalData, new Object[][]{new Object[]{IronSourceConstants.EVENTS_PROGRAMMATIC, 1}});
            }
            RewardedVideoEventsManager.getInstance().log(new EventData(IronSourceConstants.RV_API_IS_CAPPED_TRUE, mediationAdditionalData));
        }
    }

    String getCappingMessage(String str, CappingManager.ECappingStatus eCappingStatus) {
        if (eCappingStatus == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$ironsource$mediationsdk$utils$CappingManager$ECappingStatus[eCappingStatus.ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            return null;
        }
        return "placement " + str + " is capped";
    }

    ServerResponseWrapper getCurrentServerResponse() {
        return this.mCurrentServerResponse;
    }

    void setSegmentListener(SegmentListener segmentListener) {
        ListenersWrapper listenersWrapper = this.mListenersWrapper;
        if (listenersWrapper != null) {
            listenersWrapper.setSegmentListener(segmentListener);
            MediationInitializer.getInstance().setSegmentListener(this.mListenersWrapper);
        }
    }

    HashSet<String> getAllSettingsForProvider(String str, String str2) {
        ServerResponseWrapper serverResponseWrapper = this.mCurrentServerResponse;
        if (serverResponseWrapper == null) {
            return new HashSet<>();
        }
        return serverResponseWrapper.getProviderSettingsHolder().getProviderSettingsByReflectionName(str, str2);
    }

    private BannerPlacement getBannerPlacement(String str) {
        BannerConfigurations bannerConfigurations = this.mCurrentServerResponse.getConfigurations().getBannerConfigurations();
        if (bannerConfigurations == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return bannerConfigurations.getDefaultBannerPlacement();
        }
        BannerPlacement bannerPlacement = bannerConfigurations.getBannerPlacement(str);
        return bannerPlacement != null ? bannerPlacement : bannerConfigurations.getDefaultBannerPlacement();
    }

    public synchronized String getSessionId() {
        return this.mSessionId;
    }

    public void setConsent(boolean z) {
        this.mConsent = Boolean.valueOf(z);
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "setConsent : " + z, 1);
        RewardedVideoManager rewardedVideoManager = this.mRewardedVideoManager;
        if (rewardedVideoManager != null) {
            rewardedVideoManager.setConsent(z);
        }
        InterstitialManager interstitialManager = this.mInterstitialManager;
        if (interstitialManager != null) {
            interstitialManager.setConsent(z);
        }
        BannerManager bannerManager = this.mBannerManager;
        if (bannerManager != null) {
            bannerManager.setConsent(z);
        }
        if (this.mOfferwallAdapter != null) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_API, "Offerwall | setConsent(consent:" + z + ")", 1);
            this.mOfferwallAdapter.setConsent(z);
        }
        ProgRvManager progRvManager = this.mProgRvManager;
        if (progRvManager != null) {
            progRvManager.setConsent(z);
        }
        ProgIsManager progIsManager = this.mProgIsManager;
        if (progIsManager != null) {
            progIsManager.setConsent(z);
        }
        DemandOnlyIsManager demandOnlyIsManager = this.mDemandOnlyIsManager;
        if (demandOnlyIsManager != null) {
            demandOnlyIsManager.setConsent(z);
        }
        DemandOnlyRvManager demandOnlyRvManager = this.mDemandOnlyRvManager;
        if (demandOnlyRvManager != null) {
            demandOnlyRvManager.setConsent(z);
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(z ? 40 : 41, IronSourceUtils.getMediationAdditionalData(false)));
    }

    Boolean getConsent() {
        return this.mConsent;
    }

    private void addToDictionary(JSONObject jSONObject, Object[][] objArr) {
        if (objArr != null) {
            try {
                for (Object[] objArr2 : objArr) {
                    jSONObject.put(objArr2[0].toString(), objArr2[1]);
                }
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "IronSourceObject addToDictionary: " + Log.getStackTraceString(e), 3);
            }
        }
    }
}
