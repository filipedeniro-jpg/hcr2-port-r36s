package com.amazon.device.ads;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.Locale;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"InlinedApi"})
public class AdLayout extends FrameLayout implements Ad {
    private static final String CONTENT_DESCRIPTION_AD_LAYOUT = "adLayoutObject";
    public static final int DEFAULT_TIMEOUT = 20000;
    static final String LAYOUT_NOT_RUN_ERR_MSG = "Can't load an ad because the view size cannot be determined.";
    static final String LAYOUT_PARAMS_NULL_ERR_MSG = "Can't load an ad because layout parameters are blank. Use setLayoutParams() to specify dimensions for this AdLayout.";
    static final String LOADING_IN_PROGRESS_LOG_MSG = "Can't load an ad because an ad is currently loading. Please wait for the ad to finish loading and showing before loading another ad.";
    static final String LOADING_OR_LOADED_LOG_MSG = "Can't load an ad because an ad is currently loading or already loaded. Please wait for the ad to finish loading or showing before loading another ad.";
    private static final String LOGTAG = "AdLayout";
    private static ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(1);
    private View activityRootView;
    private AdController adController;
    private final AdControllerFactory adControllerFactory;
    private AdListenerExecutor adListenerExecutor;
    private final AdListenerExecutorFactory adListenerExecutorFactory;
    private final AdLoadStarter adLoadStarter;
    private final AdRegistrationExecutor adRegistration;
    private AdSize adSize;
    private AdTargetingOptions adTargetingOptions;
    private boolean attached;
    private boolean autoShow;
    private final Context context;
    private Destroyable currentDestroyable;
    private View currentView;
    private boolean hasRegisterBroadcastReciever;
    private boolean isDestroyed;
    private boolean isInForeground;
    private boolean isInitialized;
    private boolean isParentViewMissingAtLoadTime;
    private int lastVisibility;
    private final MobileAdsLogger logger;
    private final MobileAdsLoggerFactory loggerFactory;
    private AtomicBoolean needsToLoadAdOnLayout;
    private final AtomicBoolean previousAdExpired;
    private AdProperties properties;
    private BroadcastReceiver screenStateReceiver;
    private boolean shouldDisableWebViewHardwareAcceleration;

    static {
        threadPool.setKeepAliveTime(60L, TimeUnit.SECONDS);
    }

    public AdLayout(Context context) {
        this(context, AdSize.SIZE_AUTO);
    }

    public AdLayout(Context context, AdSize adSize) {
        this(context, adSize, new MobileAdsLoggerFactory(), new AdControllerFactory(), AdRegistration.getAmazonAdRegistrationExecutor(), new AdLoadStarter());
    }

    AdLayout(Context context, AdSize adSize, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        this(context, adSize, mobileAdsLoggerFactory, new AdListenerExecutorFactory(mobileAdsLoggerFactory), adControllerFactory, adRegistrationExecutor, adLoadStarter);
    }

    AdLayout(Context context, AdSize adSize, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdListenerExecutorFactory adListenerExecutorFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        super(context);
        this.hasRegisterBroadcastReciever = false;
        this.attached = false;
        this.lastVisibility = 8;
        this.needsToLoadAdOnLayout = new AtomicBoolean(false);
        this.isParentViewMissingAtLoadTime = false;
        this.activityRootView = null;
        this.adTargetingOptions = null;
        this.isDestroyed = false;
        this.isInitialized = false;
        this.autoShow = true;
        this.previousAdExpired = new AtomicBoolean(false);
        this.context = context;
        this.adSize = adSize;
        this.loggerFactory = mobileAdsLoggerFactory;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory;
        this.adControllerFactory = adControllerFactory;
        this.adRegistration = adRegistrationExecutor;
        this.adLoadStarter = adLoadStarter;
    }

    public AdLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, new MobileAdsLoggerFactory(), new AdControllerFactory(), AdRegistration.getAmazonAdRegistrationExecutor(), new AdLoadStarter());
    }

    AdLayout(Context context, AttributeSet attributeSet, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        this(context, attributeSet, mobileAdsLoggerFactory, new AdListenerExecutorFactory(mobileAdsLoggerFactory), adControllerFactory, adRegistrationExecutor, adLoadStarter);
    }

    AdLayout(Context context, AttributeSet attributeSet, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdListenerExecutorFactory adListenerExecutorFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        super(context, attributeSet);
        this.hasRegisterBroadcastReciever = false;
        this.attached = false;
        this.lastVisibility = 8;
        this.needsToLoadAdOnLayout = new AtomicBoolean(false);
        this.isParentViewMissingAtLoadTime = false;
        this.activityRootView = null;
        this.adTargetingOptions = null;
        this.isDestroyed = false;
        this.isInitialized = false;
        this.autoShow = true;
        this.previousAdExpired = new AtomicBoolean(false);
        this.context = context;
        this.adSize = determineAdSize(attributeSet);
        this.loggerFactory = mobileAdsLoggerFactory;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory;
        this.adControllerFactory = adControllerFactory;
        this.adRegistration = adRegistrationExecutor;
        this.adLoadStarter = adLoadStarter;
    }

    public AdLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, new MobileAdsLoggerFactory(), new AdControllerFactory(), AdRegistration.getAmazonAdRegistrationExecutor(), new AdLoadStarter());
    }

    AdLayout(Context context, AttributeSet attributeSet, int i, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        this(context, attributeSet, i, mobileAdsLoggerFactory, new AdListenerExecutorFactory(mobileAdsLoggerFactory), adControllerFactory, adRegistrationExecutor, adLoadStarter);
    }

    AdLayout(Context context, AttributeSet attributeSet, int i, MobileAdsLoggerFactory mobileAdsLoggerFactory, AdListenerExecutorFactory adListenerExecutorFactory, AdControllerFactory adControllerFactory, AdRegistrationExecutor adRegistrationExecutor, AdLoadStarter adLoadStarter) {
        super(context, attributeSet, i);
        this.hasRegisterBroadcastReciever = false;
        this.attached = false;
        this.lastVisibility = 8;
        this.needsToLoadAdOnLayout = new AtomicBoolean(false);
        this.isParentViewMissingAtLoadTime = false;
        this.activityRootView = null;
        this.adTargetingOptions = null;
        this.isDestroyed = false;
        this.isInitialized = false;
        this.autoShow = true;
        this.previousAdExpired = new AtomicBoolean(false);
        this.context = context;
        this.adSize = determineAdSize(attributeSet);
        this.loggerFactory = mobileAdsLoggerFactory;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adListenerExecutorFactory = adListenerExecutorFactory;
        this.adControllerFactory = adControllerFactory;
        this.adRegistration = adRegistrationExecutor;
        this.adLoadStarter = adLoadStarter;
    }

    @Override // android.view.View
    public void setX(float f) {
        super.setX(f);
        fireViewableEvent();
    }

    @Override // android.view.View
    public void setY(float f) {
        super.setY(f);
        fireViewableEvent();
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        super.setTranslationX(f);
        fireViewableEvent();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        super.setTranslationY(f);
        fireViewableEvent();
    }

    private void fireViewableEvent() {
        AdController adController = this.adController;
        if (adController != null) {
            adController.fireViewableEvent();
        }
    }

    private AdSize determineAdSize(AttributeSet attributeSet) {
        String attributeValue = getAttributeValue(attributeSet, "http://schemas.android.com/apk/lib/com.amazon.device.ads", "adSize");
        if (attributeValue == null) {
            attributeValue = getAttributeValue(attributeSet, "http://schemas.android.com/apk/res/" + this.context.getPackageName(), "adSize");
            if (attributeValue != null) {
                this.logger.forceLog(MobileAdsLogger.Level.WARN, "DEPRECATED - Please use the XML namespace \"http://schemas.android.com/apk/lib/com.amazon.device.ads\" for specifying AdLayout properties.", new Object[0]);
                if (attributeValue.toLowerCase(Locale.US).equals("custom")) {
                    this.logger.forceLog(MobileAdsLogger.Level.ERROR, "Using \"custom\" or \"CUSTOM\" for the \"adSize\" property is no longer supported. Please specifiy a size or remove the property to use Auto Ad Size.", new Object[0]);
                    throw new IllegalArgumentException("Using \"custom\" or \"CUSTOM\" for the \"adSize\" property is no longer supported. Please specifiy a size or remove the property to use Auto Ad Size.");
                }
            }
        }
        return parseAdSize(attributeValue);
    }

    MobileAdsLogger getLogger() {
        return this.logger;
    }

    boolean shouldDisableWebViewHardwareAcceleration() {
        return this.shouldDisableWebViewHardwareAcceleration;
    }

    void setShouldDisableWebViewHardwareAcceleration(boolean z) {
        this.shouldDisableWebViewHardwareAcceleration = z;
        AdController adController = this.adController;
        if (adController != null) {
            adController.requestDisableHardwareAcceleration(this.shouldDisableWebViewHardwareAcceleration);
        }
    }

    void initializeIfNecessary() {
        if (isInitialized()) {
            return;
        }
        long jNanoTime = System.nanoTime();
        this.logger.d("Initializing AdLayout.");
        this.adRegistration.initializeAds(this.context);
        setContentDescription(CONTENT_DESCRIPTION_AD_LAYOUT);
        if (isInEditMode()) {
            TextView textView = new TextView(this.context);
            textView.setText("AdLayout");
            textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            textView.setGravity(17);
            addView(textView);
            this.isInitialized = true;
            return;
        }
        this.isInForeground = getVisibility() == 0;
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        this.isInitialized = true;
        if (this.adListenerExecutor == null) {
            setListener(null);
        }
        initializeAdController();
        if (isWebViewDatabaseNull()) {
            this.logger.forceLog(MobileAdsLogger.Level.ERROR, "Disabling ads. Local cache file is inaccessible so ads will fail if we try to create a WebView. Details of this Android bug found at: http://code.google.com/p/android/issues/detail?id=10789", new Object[0]);
            this.isInitialized = false;
        } else {
            this.adController.getMetricsCollector().startMetricInMillisecondsFromNanoseconds(Metrics.MetricType.AD_LAYOUT_INITIALIZATION, jNanoTime);
            this.adController.getMetricsCollector().stopMetric(Metrics.MetricType.AD_LAYOUT_INITIALIZATION);
        }
    }

    private void initializeAdController() {
        if (this.adController == null) {
            AdSize adSize = this.adSize;
            if (adSize == null) {
                adSize = AdSize.SIZE_AUTO;
            }
            setAdController(createAdController(adSize, this.context));
            this.adController.requestDisableHardwareAcceleration(this.shouldDisableWebViewHardwareAcceleration);
        }
    }

    private void setAdController(AdController adController) {
        this.adController = adController;
        this.adController.setCallback(createAdControlCallback());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AdController getAdController() {
        initializeIfNecessary();
        if (this.adController == null) {
            initializeAdController();
        }
        return this.adController;
    }

    private static String getAttributeValue(AttributeSet attributeSet, String str, String str2) {
        return attributeSet.getAttributeValue(str, str2);
    }

    private static AdSize parseAdSize(String str) {
        int i;
        AdSize adSize = AdSize.SIZE_AUTO;
        if (str == null) {
            return adSize;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        if (lowerCase.equals("autonoscale")) {
            return AdSize.SIZE_AUTO_NO_SCALE;
        }
        if (lowerCase.equals("auto")) {
            return adSize;
        }
        String[] strArrSplit = lowerCase.split("x");
        int i2 = 0;
        if (strArrSplit.length == 2) {
            i = NumberUtils.parseInt(strArrSplit[0], 0);
            i2 = NumberUtils.parseInt(strArrSplit[1], 0);
        } else {
            i = 0;
        }
        return new AdSize(i, i2);
    }

    private AdController createAdController(AdSize adSize, Context context) {
        return this.adControllerFactory.buildAdController(context, adSize);
    }

    AdControlCallback createAdControlCallback() {
        return new AdLayoutAdControlCallback();
    }

    boolean isWebViewDatabaseNull() {
        return !getAdController().canShowViews();
    }

    AdData getAdData() {
        return getAdController().getAdData();
    }

    boolean isInitialized() {
        return this.isInitialized;
    }

    private void registerScreenStateBroadcastReceiver() {
        if (this.hasRegisterBroadcastReciever) {
            return;
        }
        this.hasRegisterBroadcastReciever = true;
        this.screenStateReceiver = new BroadcastReceiver() { // from class: com.amazon.device.ads.AdLayout.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.intent.action.SCREEN_OFF") && AdLayout.this.isInForeground) {
                    AdLayout.this.getAdController().closeAd();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.context.getApplicationContext().registerReceiver(this.screenStateReceiver, intentFilter);
    }

    private void unregisterScreenStateBroadcastReceiver() {
        if (this.hasRegisterBroadcastReciever) {
            this.hasRegisterBroadcastReciever = false;
            this.context.getApplicationContext().unregisterReceiver(this.screenStateReceiver);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        this.attached = true;
        registerScreenStateBroadcastReceiver();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        unregisterScreenStateBroadcastReceiver();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        if (!this.attached || this.lastVisibility == i) {
            return;
        }
        if (i != 0) {
            this.isInForeground = false;
            collapseAd();
            unregisterScreenStateBroadcastReceiver();
        } else if (i == 0) {
            this.isInForeground = true;
        }
    }

    private void collapseAd() {
        if (getAdController().getAdState().equals(AdState.EXPANDED)) {
            ThreadUtils.scheduleOnMainThread(new Runnable() { // from class: com.amazon.device.ads.AdLayout.2
                @Override // java.lang.Runnable
                public void run() {
                    if (AdLayout.this.getAdController().getAdState().equals(AdState.EXPANDED)) {
                        AdLayout.this.getAdController().closeAd();
                    }
                }
            });
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.isDestroyed) {
            return;
        }
        int i5 = i3 - i;
        int i6 = i4 - i2;
        super.onLayout(z, i, i2, i3, i4);
        if (isInEditMode()) {
            return;
        }
        getAdController().setWindowDimensions(i5, i6);
        if (getAndSetNeedsToLoadAdOnLayout(false)) {
            startAdLoadUponLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAdLoadUponLayout() {
        AdTargetingOptions adTargetingOptions = this.adTargetingOptions;
        this.adLoadStarter.loadAds(getAdController().getTimeout(), adTargetingOptions, new AdSlot(getAdController(), adTargetingOptions).setDeferredLoad(true));
        if (getAndResetIsPrepared()) {
            return;
        }
        onRequestError("Could not load ad on layout.");
    }

    @Override // com.amazon.device.ads.Ad
    public int getTimeout() {
        if (getAdController() == null) {
            return -1;
        }
        return getAdController().getTimeout();
    }

    @Override // com.amazon.device.ads.Ad
    public void setTimeout(int i) {
        AdController adController = getAdController();
        if (adController != null) {
            adController.setTimeout(i);
        }
    }

    public AdSize getAdSize() {
        AdController adController = getAdController();
        if (adController == null) {
            return null;
        }
        return adController.getAdSize();
    }

    void setMaxWidth(int i) {
        if (this.adController != null) {
            this.logger.w("The maximum width cannot be changed because this ad has already been initialized. Max width should be set immediately after construction of the Ad object.");
        } else {
            this.adSize = this.adSize.newMaxWidth(i);
        }
    }

    @Override // com.amazon.device.ads.Ad
    public boolean loadAd() {
        return loadAd(new AdTargetingOptions());
    }

    @Override // com.amazon.device.ads.Ad
    public boolean loadAd(AdTargetingOptions adTargetingOptions) {
        if (adTargetingOptions == null) {
            adTargetingOptions = new AdTargetingOptions();
        }
        this.adTargetingOptions = adTargetingOptions;
        if (getNeedsToLoadAdOnLayout()) {
            this.logger.e(LOADING_IN_PROGRESS_LOG_MSG);
            return false;
        }
        initializeIfNecessary();
        if (!isInitialized()) {
            this.logger.e("The ad could not be initialized properly.");
            return false;
        }
        if (!isReadyToLoad()) {
            int i = AnonymousClass4.$SwitchMap$com$amazon$device$ads$AdState[getAdController().getAdState().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    this.logger.e("An ad could not be loaded because the AdLayout has been destroyed.");
                } else if (i == 3) {
                    this.logger.e("An ad could not be loaded because another ad is currently expanded.");
                } else {
                    this.logger.e(LOADING_OR_LOADED_LOG_MSG);
                }
            } else {
                if (getAdController().isExpired()) {
                    getAdController().setAdState(AdState.READY_TO_LOAD);
                    getAdController().resetToReady();
                    return loadAd(adTargetingOptions);
                }
                this.logger.e("An ad could not be loaded because of an unknown issue with the web views.");
            }
            return false;
        }
        if (getAdController().getAdState().equals(AdState.SHOWING)) {
            getAdController().getMetricsCollector().stopMetric(Metrics.MetricType.AD_SHOW_DURATION);
        }
        this.previousAdExpired.set(false);
        this.adLoadStarter.loadAds(getAdController().getTimeout(), adTargetingOptions, new AdSlot(getAdController(), adTargetingOptions));
        if (getNeedsToLoadAdOnLayout()) {
            return true;
        }
        return getAndResetIsPrepared();
    }

    private boolean isReadyToLoad() {
        return AdState.READY_TO_LOAD.equals(getAdController().getAdState()) || AdState.SHOWING.equals(getAdController().getAdState());
    }

    private boolean isReadyToShow() {
        return getAdController().getAdState().equals(AdState.RENDERED);
    }

    public boolean isShowing() {
        return getAdController().getAdState().equals(AdState.SHOWING);
    }

    boolean prepareAd(boolean z) {
        if (z) {
            this.logger.d("Skipping ad layout preparation steps because the layout is already prepared.");
            return true;
        }
        if (!isReadyToLoad()) {
            return false;
        }
        if (getNeedsToLoadAdOnLayout()) {
            this.logger.e(LOADING_IN_PROGRESS_LOG_MSG);
            return false;
        }
        if (getAdSize().isAuto()) {
            this.logger.d("Ad size to be determined automatically.");
        }
        setIsParentViewMissingAtLoadTime();
        if (getAdSize().isAuto() && getAdController().areWindowDimensionsSet()) {
            return true;
        }
        if (isLayoutRequested() && getAdSize().isAuto() && !isParentViewMissingAtLoadTime()) {
            deferAdLoadToLayoutEvent();
            return false;
        }
        if (isParentViewMissingAtLoadTime()) {
            this.logger.d("The ad's parent view is missing at load time.");
            return loadAdWhenParentViewMissing();
        }
        setWindowDimensions();
        return true;
    }

    private boolean loadAdWhenParentViewMissing() {
        if (getLayoutParams() == null) {
            Metrics.getInstance().getMetricsCollector().incrementMetric(Metrics.MetricType.AD_FAILED_NULL_LAYOUT_PARAMS);
            onRequestError(LAYOUT_PARAMS_NULL_ERR_MSG);
            return false;
        }
        if (AndroidTargetUtils.isAtLeastAndroidAPI(11)) {
            setActivityRootView();
            if (isActivityRootViewNull()) {
                onRequestError("Ad load failed because root view could not be obtained from the activity.");
                return false;
            }
            if (isActivityRootViewLayoutRequested()) {
                this.logger.d("Activity root view layout is requested.");
                deferAdLoadToLayoutEvent();
                setOnLayoutChangeListenerForRoot();
                return false;
            }
            setFloatingWindowDimensions();
            return true;
        }
        setFloatingWindowDimensions();
        return true;
    }

    void adFailed(AdError adError) {
        getAdController().adFailed(adError);
    }

    void bypassAdRenderingProcess() {
        getAdController().setAdState(AdState.RENDERING);
        getAdController().adRendered("custom-render");
    }

    void adShown() {
        getAdController().adShown();
    }

    void setOnLayoutChangeListenerForRoot() {
        OnLayoutChangeListenerUtil.setOnLayoutChangeListenerForRoot(this);
    }

    private static class OnLayoutChangeListenerUtil {
        private OnLayoutChangeListenerUtil() {
        }

        @TargetApi(11)
        protected static void setOnLayoutChangeListenerForRoot(final AdLayout adLayout) {
            adLayout.activityRootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.amazon.device.ads.AdLayout.OnLayoutChangeListenerUtil.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    if (adLayout.getAndSetNeedsToLoadAdOnLayout(false)) {
                        adLayout.setFloatingWindowDimensions();
                        adLayout.startAdLoadUponLayout();
                        adLayout.activityRootView.removeOnLayoutChangeListener(this);
                    }
                }
            });
        }
    }

    void setFloatingWindowDimensions() {
        if (getLayoutParams().width == -1 || getLayoutParams().height == -1) {
            this.logger.d("The requested ad will scale based on the screen's dimensions because at least one AdLayout dimension is set to MATCH_PARENT but the AdLayout is currently missing a fixed-size parent view.");
        }
        setWindowDimensions();
    }

    private void setWindowDimensions() {
        int iResolveLayoutParam = resolveLayoutParam(true);
        int iResolveLayoutParam2 = resolveLayoutParam(false);
        if (iResolveLayoutParam > 0 || iResolveLayoutParam2 > 0) {
            getAdController().setWindowDimensions(iResolveLayoutParam, iResolveLayoutParam2);
        }
    }

    int resolveLayoutParam(boolean z) {
        int i = z ? getLayoutParams().width : getLayoutParams().height;
        if (i == -1) {
            if (isActivityRootViewNull()) {
                return getRawScreenDimension(z);
            }
            return getActivityRootViewDimension(z);
        }
        if (i == -2) {
            return 0;
        }
        return i;
    }

    int getRawScreenDimension(boolean z) {
        WindowManager windowManager = (WindowManager) this.context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return z ? displayMetrics.widthPixels : displayMetrics.heightPixels;
    }

    void deferAdLoadToLayoutEvent() {
        setNeedsToLoadAdOnLayout(true);
        scheduleTaskForCheckingIfLayoutHasRun();
    }

    void scheduleTaskForCheckingIfLayoutHasRun() {
        threadPool.schedule(new Runnable() { // from class: com.amazon.device.ads.AdLayout.3
            @Override // java.lang.Runnable
            public void run() {
                AdLayout.this.failLoadIfLayoutHasNotRun();
            }
        }, getTimeout(), TimeUnit.MILLISECONDS);
    }

    void failLoadIfLayoutHasNotRun() {
        if (getAndSetNeedsToLoadAdOnLayout(false)) {
            Metrics.getInstance().getMetricsCollector().incrementMetric(Metrics.MetricType.AD_FAILED_LAYOUT_NOT_RUN);
            onRequestError(LAYOUT_NOT_RUN_ERR_MSG);
        }
    }

    boolean getNeedsToLoadAdOnLayout() {
        return this.needsToLoadAdOnLayout.get();
    }

    void setNeedsToLoadAdOnLayout(boolean z) {
        this.needsToLoadAdOnLayout.set(z);
    }

    boolean getAndSetNeedsToLoadAdOnLayout(boolean z) {
        return this.needsToLoadAdOnLayout.getAndSet(z);
    }

    boolean getAndResetIsPrepared() {
        return getAdController().getAndResetIsPrepared();
    }

    private void onRequestError(String str) {
        getAdController().onRequestError(str);
    }

    boolean isParentViewMissingAtLoadTime() {
        return this.isParentViewMissingAtLoadTime;
    }

    void setIsParentViewMissingAtLoadTime() {
        this.isParentViewMissingAtLoadTime = getParent() == null;
    }

    void setIsParentViewMissingAtLoadTime(boolean z) {
        this.isParentViewMissingAtLoadTime = z;
    }

    void setActivityRootView() {
        Activity contextAsActivity = ContextUtils.getContextAsActivity(this.context);
        if (contextAsActivity == null) {
            this.logger.e("unable to set activity root view because the context did not contain an activity");
        } else {
            this.activityRootView = contextAsActivity.getWindow().getDecorView().findViewById(R.id.content).getRootView();
        }
    }

    boolean isActivityRootViewLayoutRequested() {
        return this.activityRootView.isLayoutRequested();
    }

    boolean isActivityRootViewNull() {
        return this.activityRootView == null;
    }

    int getActivityRootViewDimension(boolean z) {
        return z ? this.activityRootView.getWidth() : this.activityRootView.getHeight();
    }

    @Override // com.amazon.device.ads.Ad
    public void setListener(AdListener adListener) {
        if (adListener == null) {
            adListener = new DefaultAdListener(LOGTAG);
        }
        this.adListenerExecutor = this.adListenerExecutorFactory.createAdListenerExecutor(adListener);
    }

    AdListenerExecutor getAdListenerExecutor() {
        return this.adListenerExecutor;
    }

    @Override // com.amazon.device.ads.Ad
    public boolean isLoading() {
        if (getAdController() == null) {
            return false;
        }
        return getAdController().getAdState().equals(AdState.LOADING);
    }

    public boolean isAdLoading() {
        return isLoading();
    }

    public void destroy() {
        if (isInitialized()) {
            this.logger.d("Destroying the AdLayout");
            this.isDestroyed = true;
            unregisterScreenStateBroadcastReceiver();
            getAdController().destroy();
        }
    }

    public boolean showAd() {
        if (this.previousAdExpired.get()) {
            this.logger.w("This banner ad has expired. Please load another ad.");
            return false;
        }
        if (isReadyToShow()) {
            if (getAdController().isExpired()) {
                this.logger.w("This banner ad has expired. Please load another ad.");
                return false;
            }
            if (getAdController().startAdDrawing()) {
                if (!this.autoShow) {
                    getAdController().getMetricsCollector().stopMetric(Metrics.MetricType.AD_LOADED_TO_AD_SHOW_TIME);
                }
                getAdController().getMetricsCollector().startMetric(Metrics.MetricType.AD_SHOW_LATENCY);
                View view = this.currentView;
                if (view != null) {
                    removeView(view);
                }
                Destroyable destroyable = this.currentDestroyable;
                if (destroyable != null) {
                    destroyable.destroy();
                }
                this.currentView = getAdController().getView();
                this.currentDestroyable = getAdController().getDestroyable();
                addView(this.currentView, new FrameLayout.LayoutParams(-1, -1, 17));
                getAdController().getMetricsCollector().startMetric(Metrics.MetricType.AD_SHOW_DURATION);
                adShown();
                return true;
            }
            this.logger.w("Banner ad could not be shown.");
            return false;
        }
        if (isLoading()) {
            this.logger.w("The banner ad cannot be shown because it is still loading.");
        } else if (isShowing()) {
            this.logger.w("The banner ad cannot be shown because it is already showing.");
        } else if (isReadyToLoad()) {
            this.logger.w("The banner ad cannot be shown because it has not loaded successfully.");
        } else {
            this.logger.w("A banner ad is not ready to show.");
        }
        return false;
    }

    public void enableAutoShow() {
        this.autoShow = true;
    }

    public void disableAutoShow() {
        this.autoShow = false;
    }

    class AdLayoutAdControlCallback implements AdControlCallback {
        @Override // com.amazon.device.ads.AdControlCallback
        public void postAdRendered() {
        }

        AdLayoutAdControlCallback() {
        }

        @Override // com.amazon.device.ads.AdControlCallback
        public boolean isAdReady(boolean z) {
            return AdLayout.this.prepareAd(z);
        }

        @Override // com.amazon.device.ads.AdControlCallback
        public void onAdLoaded(AdProperties adProperties) {
            AdLayout.this.properties = adProperties;
            AdLayout.this.getAdController().render();
        }

        @Override // com.amazon.device.ads.AdControlCallback
        @SuppressLint({"InlinedApi"})
        public void onAdRendered() {
            if (!AdLayout.this.autoShow) {
                AdLayout.this.getAdController().getMetricsCollector().startMetric(Metrics.MetricType.AD_LOADED_TO_AD_SHOW_TIME);
                AdLayout.this.logger.d("Ad is ready to show. Please call showAd to display it.");
                AdListenerExecutor adListenerExecutor = AdLayout.this.getAdListenerExecutor();
                AdLayout adLayout = AdLayout.this;
                adListenerExecutor.onAdLoaded(adLayout, adLayout.properties);
                return;
            }
            if (AdLayout.this.showAd()) {
                AdListenerExecutor adListenerExecutor2 = AdLayout.this.getAdListenerExecutor();
                AdLayout adLayout2 = AdLayout.this;
                adListenerExecutor2.onAdLoaded(adLayout2, adLayout2.properties);
            }
        }

        @Override // com.amazon.device.ads.AdControlCallback
        public void onAdFailed(AdError adError) {
            if (AdError.ErrorCode.NETWORK_TIMEOUT.equals(adError.getCode())) {
                AdLayout.this.adController = null;
            }
            AdLayout.this.getAdListenerExecutor().onAdFailedToLoad(AdLayout.this, adError);
        }

        @Override // com.amazon.device.ads.AdControlCallback
        public void onAdEvent(AdEvent adEvent) {
            handleAdEvent(adEvent);
        }

        boolean handleAdEvent(AdEvent adEvent) {
            int i = AnonymousClass4.$SwitchMap$com$amazon$device$ads$AdEvent$AdEventType[adEvent.getAdEventType().ordinal()];
            if (i == 1) {
                AdLayout.this.getAdListenerExecutor().onAdExpanded(AdLayout.this);
                return true;
            }
            if (i == 2) {
                AdLayout.this.getAdListenerExecutor().onAdCollapsed(AdLayout.this);
                return true;
            }
            if (i != 3) {
                return false;
            }
            AdLayout.this.getAdListenerExecutor().onAdResized(AdLayout.this, (Rect) adEvent.getParameters().getParameter(AdEvent.POSITION_ON_SCREEN));
            return true;
        }

        @Override // com.amazon.device.ads.AdControlCallback
        public int adClosing() {
            return AdLayout.this.getAdController().getAdState().equals(AdState.EXPANDED) ? 0 : 2;
        }

        @Override // com.amazon.device.ads.AdControlCallback
        public void onAdExpired() {
            AdLayout.this.getAdController().getMetricsCollector().incrementMetric(Metrics.MetricType.AD_EXPIRED_BEFORE_SHOWING);
            AdLayout.this.previousAdExpired.set(true);
            AdLayout.this.adController = null;
            AdLayout.this.getAdListenerExecutor().onAdExpired(AdLayout.this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.AdLayout$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$AdEvent$AdEventType = new int[AdEvent.AdEventType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$AdState;

        static {
            try {
                $SwitchMap$com$amazon$device$ads$AdEvent$AdEventType[AdEvent.AdEventType.EXPANDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$AdEvent$AdEventType[AdEvent.AdEventType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$AdEvent$AdEventType[AdEvent.AdEventType.RESIZED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$device$ads$AdState = new int[AdState.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$AdState[AdState.INVALID.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$AdState[AdState.DESTROYED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$AdState[AdState.EXPANDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
