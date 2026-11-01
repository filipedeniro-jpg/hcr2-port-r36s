package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class AdUrlLoader {
    private static final String LOGTAG = "AdUrlLoader";
    private final AdControlAccessor adControlAccessor;
    private final AdWebViewClient adWebViewClient;
    private final DeviceInfo deviceInfo;
    private final MobileAdsLogger logger;
    private final ThreadUtils.ThreadRunner threadRunner;
    private final WebRequest.WebRequestFactory webRequestFactory;
    private final WebUtils2 webUtils;

    public AdUrlLoader(ThreadUtils.ThreadRunner threadRunner, AdWebViewClient adWebViewClient, WebRequest.WebRequestFactory webRequestFactory, AdControlAccessor adControlAccessor, WebUtils2 webUtils2, MobileAdsLoggerFactory mobileAdsLoggerFactory, DeviceInfo deviceInfo) {
        this.threadRunner = threadRunner;
        this.adWebViewClient = adWebViewClient;
        this.webRequestFactory = webRequestFactory;
        this.adControlAccessor = adControlAccessor;
        this.webUtils = webUtils2;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.deviceInfo = deviceInfo;
    }

    public void putUrlExecutorInAdWebViewClient(String str, AdWebViewClient.UrlExecutor urlExecutor) {
        this.adWebViewClient.putUrlExecutor(str, urlExecutor);
    }

    public void setAdWebViewClientListener(AdWebViewClient.AdWebViewClientListener adWebViewClientListener) {
        this.adWebViewClient.setListener(adWebViewClientListener);
    }

    public AdWebViewClient getAdWebViewClient() {
        return this.adWebViewClient;
    }

    public void loadUrl(final String str, final boolean z, final PreloadCallback preloadCallback) {
        String scheme = this.webUtils.getScheme(str);
        if (scheme.equals("http") || scheme.equals("https")) {
            this.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.AdUrlLoader.1
                @Override // java.lang.Runnable
                public void run() {
                    AdUrlLoader.this.loadUrlInThread(str, z, preloadCallback);
                }
            }, ThreadUtils.ExecutionStyle.RUN_ASAP, ThreadUtils.ExecutionThread.BACKGROUND_THREAD);
        } else {
            openUrl(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadUrlInThread(final String str, final boolean z, final PreloadCallback preloadCallback) {
        WebRequest.WebResponse webResponseMakeCall;
        WebRequest webRequestCreateWebRequest = this.webRequestFactory.createWebRequest();
        webRequestCreateWebRequest.setExternalLogTag(LOGTAG);
        webRequestCreateWebRequest.enableLogUrl(true);
        webRequestCreateWebRequest.setUrlString(str);
        webRequestCreateWebRequest.putHeader("User-Agent", this.deviceInfo.getUserAgentString());
        try {
            webResponseMakeCall = webRequestCreateWebRequest.makeCall();
        } catch (WebRequest.WebRequestException e) {
            this.logger.e("Could not load URL (%s) into AdContainer: %s", str, e.getMessage());
            webResponseMakeCall = null;
        }
        if (webResponseMakeCall != null) {
            final String asString = webResponseMakeCall.getResponseReader().readAsString();
            if (asString != null) {
                this.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.AdUrlLoader.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AdUrlLoader.this.adControlAccessor.loadHtml(str, asString, z, preloadCallback);
                    }
                }, ThreadUtils.ExecutionStyle.RUN_ASAP, ThreadUtils.ExecutionThread.MAIN_THREAD);
            } else {
                this.logger.e("Could not load URL (%s) into AdContainer.", str);
            }
        }
    }

    public void openUrl(String str) {
        this.adWebViewClient.openUrl(str);
    }
}
