package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class ViewabilityJavascriptFetcher {
    private static final String CDN_URL = "https://dwxjayoxbnyrr.cloudfront.net/amazon-ads.viewablejs";
    private static final String LOGTAG = "ViewabilityJavascriptFetcher";
    private static ViewabilityJavascriptFetcher instance = new ViewabilityJavascriptFetcher();
    private final Configuration configuration;
    private int currentJSVersion;
    private final DebugProperties debugProperties;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final Metrics metrics;
    private final PermissionChecker permissionChecker;
    private final Settings settings;
    private final ThreadUtils.ThreadRunner threadRunner;
    private final WebRequest.WebRequestFactory webRequestFactory;

    protected ViewabilityJavascriptFetcher() {
        this(new MobileAdsLoggerFactory(), new PermissionChecker(), DebugProperties.getInstance(), Settings.getInstance(), new WebRequest.WebRequestFactory(), Metrics.getInstance(), ThreadUtils.getThreadRunner(), MobileAdsInfoStore.getInstance(), Configuration.getInstance());
    }

    ViewabilityJavascriptFetcher(MobileAdsLoggerFactory mobileAdsLoggerFactory, PermissionChecker permissionChecker, DebugProperties debugProperties, Settings settings, WebRequest.WebRequestFactory webRequestFactory, Metrics metrics, ThreadUtils.ThreadRunner threadRunner, MobileAdsInfoStore mobileAdsInfoStore, Configuration configuration) {
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.permissionChecker = permissionChecker;
        this.debugProperties = debugProperties;
        this.settings = settings;
        this.webRequestFactory = webRequestFactory;
        this.metrics = metrics;
        this.threadRunner = threadRunner;
        this.infoStore = mobileAdsInfoStore;
        this.configuration = configuration;
    }

    private boolean shouldFetch() {
        this.currentJSVersion = this.configuration.getInt(Configuration.ConfigOption.VIEWABLE_JS_VERSION_CONFIG);
        return this.settings.getInt("viewableJSVersionStored", -1) < this.currentJSVersion || StringUtils.isNullOrEmpty(this.settings.getString("viewableJSSettingsNameAmazonAdSDK", null));
    }

    public void fetchJavascript() {
        if (shouldFetch()) {
            beginFetch();
        }
    }

    protected void beginFetch() {
        this.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.ViewabilityJavascriptFetcher.1
            @Override // java.lang.Runnable
            public void run() {
                ViewabilityJavascriptFetcher.this.fetchJavascriptFromURLOnBackgroundThread();
            }
        }, ThreadUtils.ExecutionStyle.SCHEDULE, ThreadUtils.ExecutionThread.BACKGROUND_THREAD);
    }

    public void fetchJavascriptFromURLOnBackgroundThread() {
        this.logger.d("In ViewabilityJavascriptFetcher background thread");
        if (!this.permissionChecker.hasInternetPermission(this.infoStore.getApplicationContext())) {
            this.logger.e("Network task cannot commence because the INTERNET permission is missing from the app's manifest.");
            onFetchFailure();
            return;
        }
        WebRequest webRequestCreateWebRequest = createWebRequest();
        if (webRequestCreateWebRequest == null) {
            onFetchFailure();
            return;
        }
        try {
            this.settings.putString("viewableJSSettingsNameAmazonAdSDK", webRequestCreateWebRequest.makeCall().getResponseReader().readAsString());
            this.settings.putInt("viewableJSVersionStored", this.currentJSVersion);
            this.logger.d("Viewability Javascript fetched and saved");
        } catch (WebRequest.WebRequestException unused) {
            onFetchFailure();
        }
    }

    protected WebRequest createWebRequest() {
        WebRequest webRequestCreateWebRequest = this.webRequestFactory.createWebRequest();
        webRequestCreateWebRequest.setExternalLogTag(LOGTAG);
        webRequestCreateWebRequest.enableLog(true);
        webRequestCreateWebRequest.setUrlString(this.configuration.getStringWithDefault(Configuration.ConfigOption.VIEWABLE_JAVASCRIPT_URL, CDN_URL));
        webRequestCreateWebRequest.setMetricsCollector(this.metrics.getMetricsCollector());
        webRequestCreateWebRequest.setServiceCallLatencyMetric(Metrics.MetricType.CDN_JAVASCRIPT_DOWLOAD_LATENCY);
        webRequestCreateWebRequest.setUseSecure(this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_AAX_CONFIG_USE_SECURE, true).booleanValue());
        return webRequestCreateWebRequest;
    }

    private void onFetchFailure() {
        this.metrics.getMetricsCollector().incrementMetric(Metrics.MetricType.CDN_JAVASCRIPT_DOWNLOAD_FAILED);
        this.logger.w("Viewability Javascript fetch failed");
    }

    public static final ViewabilityJavascriptFetcher getInstance() {
        return instance;
    }
}
