package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class ViewabilityJavascriptFetcherListener implements Configuration.ConfigurationListener {
    private static final String LOGTAG = "ViewabilityJavascriptFetcherListener";
    private final MobileAdsLogger logger;
    private ViewabilityJavascriptFetcher viewabilityJavascriptFetcher;

    ViewabilityJavascriptFetcherListener() {
        this(new ViewabilityJavascriptFetcher(), new MobileAdsLoggerFactory());
    }

    ViewabilityJavascriptFetcherListener(ViewabilityJavascriptFetcher viewabilityJavascriptFetcher, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.viewabilityJavascriptFetcher = viewabilityJavascriptFetcher;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    @Override // com.amazon.device.ads.Configuration.ConfigurationListener
    public void onConfigurationReady() {
        this.viewabilityJavascriptFetcher.fetchJavascript();
    }

    @Override // com.amazon.device.ads.Configuration.ConfigurationListener
    public void onConfigurationFailure() {
        this.logger.w("Configuration fetching failed so Viewability Javascript fetch will not proceed.");
    }
}
