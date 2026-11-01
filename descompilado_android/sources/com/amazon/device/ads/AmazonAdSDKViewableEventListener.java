package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class AmazonAdSDKViewableEventListener implements SDKEventListener {
    private static final String LOGTAG = "AmazonAdSDKViewableEventListener";
    private final MobileAdsLogger logger;

    public AmazonAdSDKViewableEventListener() {
        this(new MobileAdsLoggerFactory());
    }

    AmazonAdSDKViewableEventListener(MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    @Override // com.amazon.device.ads.SDKEventListener
    public void onSDKEvent(SDKEvent sDKEvent, AdControlAccessor adControlAccessor) {
        this.logger.d(sDKEvent.getEventType().toString());
        if (AnonymousClass1.$SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[sDKEvent.getEventType().ordinal()] != 1) {
            return;
        }
        handleViewableEvent(adControlAccessor, sDKEvent);
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.AmazonAdSDKViewableEventListener$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType = new int[SDKEvent.SDKEventType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEvent.SDKEventType.VIEWABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void handleViewableEvent(AdControlAccessor adControlAccessor, SDKEvent sDKEvent) {
        adControlAccessor.injectJavascript("viewableBridge.viewabilityChange('" + sDKEvent.getParameter(ViewabilityObserver.VIEWABLE_PARAMS_KEY) + "');");
    }
}
