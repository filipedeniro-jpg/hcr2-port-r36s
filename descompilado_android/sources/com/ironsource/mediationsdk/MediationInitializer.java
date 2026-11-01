package com.ironsource.mediationsdk;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.environment.NetworkStateReceiver;
import com.ironsource.mediationsdk.config.ConfigValidationResult;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ServerSegmetData;
import com.ironsource.mediationsdk.sdk.GeneralProperties;
import com.ironsource.mediationsdk.sdk.SegmentListener;
import com.ironsource.mediationsdk.utils.ErrorBuilder;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
class MediationInitializer implements NetworkStateReceiver.NetworkStateReceiverListener {
    private static MediationInitializer sInstance;
    private Activity mActivity;
    private String mAppKey;
    private AtomicBoolean mAtomicShouldPerformInit;
    private CountDownTimer mCountDownTimer;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private long mInitStartTime;
    private boolean mIsInProgressMoreThan15Secs;
    private boolean mIsRevived;
    private NetworkStateReceiver mNetworkStateReceiver;
    private int mRetryAvailabilityLimit;
    private int mRetryCounter;
    private int mRetryDelay;
    private int mRetryGrowLimit;
    private int mRetryLimit;
    private SegmentListener mSegmentListener;
    private ServerResponseWrapper mServerResponseWrapper;
    private String mUserId;
    private String mUserIdType;
    private final String GENERAL_PROPERTIES_USER_ID = ServerResponseWrapper.USER_ID_FIELD;
    private final String GENERAL_PROPERTIES_APP_KEY = ServerResponseWrapper.APP_KEY_FIELD;
    private final String TAG = getClass().getSimpleName();
    private boolean mDidReportInitialAvailability = false;
    private boolean mListenForInit = false;
    private List<OnMediationInitializationListener> mOnMediationInitializationListeners = new ArrayList();
    private InitRunnable initRunnable = new InitRunnable() { // from class: com.ironsource.mediationsdk.MediationInitializer.1
        @Override // java.lang.Runnable
        public void run() {
            ServerSegmetData segmetData;
            try {
                IronSourceObject ironSourceObject = IronSourceObject.getInstance();
                if (MediationInitializer.this.validateUserId(MediationInitializer.this.mUserId).isValid()) {
                    MediationInitializer.this.mUserIdType = IronSourceConstants.TYPE_USER_GENERATED;
                } else {
                    MediationInitializer.this.mUserId = ironSourceObject.getAdvertiserId(MediationInitializer.this.mActivity);
                    if (!TextUtils.isEmpty(MediationInitializer.this.mUserId)) {
                        MediationInitializer.this.mUserIdType = IronSourceConstants.TYPE_GAID;
                    } else {
                        MediationInitializer.this.mUserId = DeviceStatus.getOrGenerateOnceUniqueIdentifier(MediationInitializer.this.mActivity);
                        if (!TextUtils.isEmpty(MediationInitializer.this.mUserId)) {
                            MediationInitializer.this.mUserIdType = IronSourceConstants.TYPE_UUID;
                        } else {
                            MediationInitializer.this.mUserId = "";
                        }
                    }
                    ironSourceObject.setIronSourceUserId(MediationInitializer.this.mUserId);
                }
                GeneralProperties.getProperties().putKey(GeneralProperties.USER_ID_TYPE, MediationInitializer.this.mUserIdType);
                if (!TextUtils.isEmpty(MediationInitializer.this.mUserId)) {
                    GeneralProperties.getProperties().putKey(ServerResponseWrapper.USER_ID_FIELD, MediationInitializer.this.mUserId);
                }
                if (!TextUtils.isEmpty(MediationInitializer.this.mAppKey)) {
                    GeneralProperties.getProperties().putKey(ServerResponseWrapper.APP_KEY_FIELD, MediationInitializer.this.mAppKey);
                }
                MediationInitializer.this.mInitStartTime = new Date().getTime();
                MediationInitializer.this.mServerResponseWrapper = ironSourceObject.getServerResponse(MediationInitializer.this.mActivity, MediationInitializer.this.mUserId, this.listener);
                if (MediationInitializer.this.mServerResponseWrapper != null) {
                    MediationInitializer.this.mHandler.removeCallbacks(this);
                    if (MediationInitializer.this.mServerResponseWrapper.isValidResponse()) {
                        MediationInitializer.this.setInitStatus(EInitStatus.INITIATED);
                        ironSourceObject.sendInitCompletedEvent(new Date().getTime() - MediationInitializer.this.mInitStartTime);
                        if (MediationInitializer.this.mServerResponseWrapper.getConfigurations().getApplicationConfigurations().getIntegration()) {
                            IntegrationHelper.validateIntegration(MediationInitializer.this.mActivity);
                        }
                        List<IronSource.AD_UNIT> initiatedAdUnits = MediationInitializer.this.mServerResponseWrapper.getInitiatedAdUnits();
                        Iterator it = MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                        while (it.hasNext()) {
                            ((OnMediationInitializationListener) it.next()).onInitSuccess(initiatedAdUnits, MediationInitializer.this.wasInitRevived());
                        }
                        if (MediationInitializer.this.mSegmentListener == null || (segmetData = MediationInitializer.this.mServerResponseWrapper.getConfigurations().getApplicationConfigurations().getSegmetData()) == null || TextUtils.isEmpty(segmetData.getSegmentName())) {
                            return;
                        }
                        MediationInitializer.this.mSegmentListener.onSegmentReceived(segmetData.getSegmentName());
                        return;
                    }
                    if (MediationInitializer.this.mDidReportInitialAvailability) {
                        return;
                    }
                    MediationInitializer.this.setInitStatus(EInitStatus.INIT_FAILED);
                    MediationInitializer.this.mDidReportInitialAvailability = true;
                    Iterator it2 = MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                    while (it2.hasNext()) {
                        ((OnMediationInitializationListener) it2.next()).onInitFailed(IronSourceConstants.FALSE_AVAILABILITY_REASON_SERVER_RESPONSE_IS_NOT_VALID);
                    }
                    return;
                }
                if (MediationInitializer.this.mRetryCounter == 3) {
                    MediationInitializer.this.mIsInProgressMoreThan15Secs = true;
                    Iterator it3 = MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                    while (it3.hasNext()) {
                        ((OnMediationInitializationListener) it3.next()).onStillInProgressAfter15Secs();
                    }
                }
                if (this.isRecoverable && MediationInitializer.this.mRetryCounter < MediationInitializer.this.mRetryLimit) {
                    MediationInitializer.this.mIsRevived = true;
                    MediationInitializer.this.mHandler.postDelayed(this, MediationInitializer.this.mRetryDelay * 1000);
                    if (MediationInitializer.this.mRetryCounter < MediationInitializer.this.mRetryGrowLimit) {
                        MediationInitializer.this.mRetryDelay *= 2;
                    }
                }
                if ((!this.isRecoverable || MediationInitializer.this.mRetryCounter == MediationInitializer.this.mRetryAvailabilityLimit) && !MediationInitializer.this.mDidReportInitialAvailability) {
                    MediationInitializer.this.mDidReportInitialAvailability = true;
                    if (TextUtils.isEmpty(this.reason)) {
                        this.reason = IronSourceConstants.FALSE_AVAILABILITY_REASON_NO_SERVER_RESPONSE;
                    }
                    Iterator it4 = MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                    while (it4.hasNext()) {
                        ((OnMediationInitializationListener) it4.next()).onInitFailed(this.reason);
                    }
                    MediationInitializer.this.setInitStatus(EInitStatus.INIT_FAILED);
                    IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "Mediation availability false reason: No server response", 1);
                }
                MediationInitializer.access$1308(MediationInitializer.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private EInitStatus mInitStatus = EInitStatus.NOT_INIT;

    enum EInitStatus {
        NOT_INIT,
        INIT_IN_PROGRESS,
        INIT_FAILED,
        INITIATED
    }

    interface OnMediationInitializationListener {
        void onInitFailed(String str);

        void onInitSuccess(List<IronSource.AD_UNIT> list, boolean z);

        void onStillInProgressAfter15Secs();
    }

    static /* synthetic */ int access$1308(MediationInitializer mediationInitializer) {
        int i = mediationInitializer.mRetryCounter;
        mediationInitializer.mRetryCounter = i + 1;
        return i;
    }

    abstract class InitRunnable implements Runnable {
        boolean isRecoverable = true;
        protected IronSourceObject.IResponseListener listener = new IronSourceObject.IResponseListener() { // from class: com.ironsource.mediationsdk.MediationInitializer.InitRunnable.1
            @Override // com.ironsource.mediationsdk.IronSourceObject.IResponseListener
            public void onUnrecoverableError(String str) {
                InitRunnable initRunnable = InitRunnable.this;
                initRunnable.isRecoverable = false;
                initRunnable.reason = str;
            }
        };
        String reason;

        InitRunnable() {
        }
    }

    public static synchronized MediationInitializer getInstance() {
        if (sInstance == null) {
            sInstance = new MediationInitializer();
        }
        return sInstance;
    }

    private MediationInitializer() {
        this.mHandlerThread = null;
        this.mHandlerThread = new HandlerThread("IronSourceInitiatorHandler");
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mRetryDelay = 1;
        this.mRetryCounter = 0;
        this.mRetryLimit = 62;
        this.mRetryGrowLimit = 12;
        this.mRetryAvailabilityLimit = 5;
        this.mAtomicShouldPerformInit = new AtomicBoolean(true);
        this.mIsRevived = false;
        this.mIsInProgressMoreThan15Secs = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setInitStatus(EInitStatus eInitStatus) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "setInitStatus(old status: " + this.mInitStatus + ", new status: " + eInitStatus + ")", 0);
        this.mInitStatus = eInitStatus;
    }

    public synchronized void init(Activity activity, String str, String str2, IronSource.AD_UNIT... ad_unitArr) {
        try {
            if (this.mAtomicShouldPerformInit != null && this.mAtomicShouldPerformInit.compareAndSet(true, false)) {
                setInitStatus(EInitStatus.INIT_IN_PROGRESS);
                this.mActivity = activity;
                this.mUserId = str2;
                this.mAppKey = str;
                if (IronSourceUtils.isNetworkConnected(activity)) {
                    this.mHandler.post(this.initRunnable);
                } else {
                    this.mListenForInit = true;
                    if (this.mNetworkStateReceiver == null) {
                        this.mNetworkStateReceiver = new NetworkStateReceiver(activity, this);
                    }
                    activity.getApplicationContext().registerReceiver(this.mNetworkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.MediationInitializer.2
                        /* JADX WARN: Type inference failed for: r7v0, types: [com.ironsource.mediationsdk.MediationInitializer$2$1] */
                        @Override // java.lang.Runnable
                        public void run() {
                            MediationInitializer.this.mCountDownTimer = new CountDownTimer(ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS, 15000L) { // from class: com.ironsource.mediationsdk.MediationInitializer.2.1
                                @Override // android.os.CountDownTimer
                                public void onTick(long j) {
                                    if (j <= 45000) {
                                        MediationInitializer.this.mIsInProgressMoreThan15Secs = true;
                                        Iterator it = MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                                        while (it.hasNext()) {
                                            ((OnMediationInitializationListener) it.next()).onStillInProgressAfter15Secs();
                                        }
                                    }
                                }

                                @Override // android.os.CountDownTimer
                                public void onFinish() {
                                    if (MediationInitializer.this.mDidReportInitialAvailability) {
                                        return;
                                    }
                                    MediationInitializer.this.mDidReportInitialAvailability = true;
                                    Iterator it = MediationInitializer.this.mOnMediationInitializationListeners.iterator();
                                    while (it.hasNext()) {
                                        ((OnMediationInitializationListener) it.next()).onInitFailed(IronSourceConstants.FALSE_AVAILABILITY_REASON_NO_INTERNET);
                                    }
                                    IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, "Mediation availability false reason: No internet connection", 1);
                                }
                            }.start();
                        }
                    });
                }
            } else {
                IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.API, this.TAG + ": Multiple calls to init are not allowed", 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.ironsource.environment.NetworkStateReceiver.NetworkStateReceiverListener
    public void onNetworkAvailabilityChanged(boolean z) {
        if (this.mListenForInit && z) {
            CountDownTimer countDownTimer = this.mCountDownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.mListenForInit = false;
            this.mIsRevived = true;
            this.mHandler.post(this.initRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean wasInitRevived() {
        return this.mIsRevived;
    }

    public synchronized EInitStatus getCurrentInitStatus() {
        return this.mInitStatus;
    }

    void setInitStatusFailed() {
        setInitStatus(EInitStatus.INIT_FAILED);
    }

    public synchronized boolean isInProgressMoreThan15Secs() {
        return this.mIsInProgressMoreThan15Secs;
    }

    public void addMediationInitializationListener(OnMediationInitializationListener onMediationInitializationListener) {
        if (onMediationInitializationListener == null) {
            return;
        }
        this.mOnMediationInitializationListeners.add(onMediationInitializationListener);
    }

    public void removeMediationInitializationListener(OnMediationInitializationListener onMediationInitializationListener) {
        if (onMediationInitializationListener == null || this.mOnMediationInitializationListeners.size() == 0) {
            return;
        }
        this.mOnMediationInitializationListeners.remove(onMediationInitializationListener);
    }

    void setSegmentListener(SegmentListener segmentListener) {
        this.mSegmentListener = segmentListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConfigValidationResult validateUserId(String str) {
        ConfigValidationResult configValidationResult = new ConfigValidationResult();
        if (str != null) {
            if (!validateLength(str, 1, 64)) {
                configValidationResult.setInvalid(ErrorBuilder.buildInvalidCredentialsError(ServerResponseWrapper.USER_ID_FIELD, str, null));
            }
        } else {
            configValidationResult.setInvalid(ErrorBuilder.buildInvalidCredentialsError(ServerResponseWrapper.USER_ID_FIELD, str, "it's missing"));
        }
        return configValidationResult;
    }

    private boolean validateLength(String str, int i, int i2) {
        return str != null && str.length() >= i && str.length() <= i2;
    }
}
