package com.amazon.device.ads;

import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
class MRAIDAdSDKEventListener implements SDKEventListener {
    private static final String LOGTAG = "MRAIDAdSDKEventListener";
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private MRAIDAdSDKBridge mraidAdSDKBridge;

    MRAIDAdSDKEventListener(MRAIDAdSDKBridge mRAIDAdSDKBridge) {
        this.mraidAdSDKBridge = mRAIDAdSDKBridge;
    }

    @Override // com.amazon.device.ads.SDKEventListener
    public void onSDKEvent(SDKEvent sDKEvent, AdControlAccessor adControlAccessor) {
        this.logger.d(sDKEvent.getEventType().toString());
        switch (sDKEvent.getEventType()) {
            case PLACED:
                handleDefaultEvent(adControlAccessor);
                handleReadyEvent(adControlAccessor);
                break;
            case VISIBLE:
                handleShowingEvent(adControlAccessor);
                handleDefaultEvent(adControlAccessor);
                handleReadyEvent(adControlAccessor);
                break;
            case CLOSED:
                handleClosedEvent(adControlAccessor);
                break;
            case RESIZED:
                this.mraidAdSDKBridge.reportSizeChangeEvent();
                break;
            case HIDDEN:
            case DESTROYED:
                adControlAccessor.injectJavascript("mraidBridge.stateChange('hidden');");
                break;
            case BRIDGE_ADDED:
                handleBridgeAddedEvent(sDKEvent, adControlAccessor);
                break;
            case VIEWABLE:
                handleViewableEvent(sDKEvent, adControlAccessor);
                break;
        }
    }

    private void handleViewableEvent(SDKEvent sDKEvent, AdControlAccessor adControlAccessor) {
        adControlAccessor.injectJavascript("mraidBridge.viewableChange(" + sDKEvent.getParameter(ViewabilityObserver.IS_VIEWABLE_KEY) + ");");
    }

    private void handleBridgeAddedEvent(SDKEvent sDKEvent, AdControlAccessor adControlAccessor) {
        String parameter = sDKEvent.getParameter(SDKEvent.BRIDGE_NAME);
        if (parameter == null || !parameter.equals(this.mraidAdSDKBridge.getName())) {
            return;
        }
        int i = AnonymousClass2.$SwitchMap$com$amazon$device$ads$AdState[adControlAccessor.getAdState().ordinal()];
        if (i == 1 || i == 2) {
            handleShowingEvent(adControlAccessor);
            handleDefaultEvent(adControlAccessor);
            handleReadyEvent(adControlAccessor);
        } else if (i == 3 && !adControlAccessor.isModal()) {
            handleDefaultEvent(adControlAccessor);
            handleReadyEvent(adControlAccessor);
        }
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.MRAIDAdSDKEventListener$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$AdState = new int[AdState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$ads$AdState[AdState.EXPANDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$AdState[AdState.SHOWING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$AdState[AdState.RENDERED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType = new int[SDKEvent.SDKEventType.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEvent.SDKEventType.RENDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEvent.SDKEventType.PLACED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEvent.SDKEventType.VISIBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEvent.SDKEventType.CLOSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEvent.SDKEventType.RESIZED.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEvent.SDKEventType.HIDDEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEvent.SDKEventType.DESTROYED.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEvent.SDKEventType.BRIDGE_ADDED.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEvent.SDKEventType.VIEWABLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private void handleReadyEvent(AdControlAccessor adControlAccessor) {
        adControlAccessor.injectJavascript("mraidBridge.ready();");
    }

    private void handleShowingEvent(final AdControlAccessor adControlAccessor) {
        adControlAccessor.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.device.ads.MRAIDAdSDKEventListener.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Position currentPosition = adControlAccessor.getCurrentPosition();
                if (currentPosition != null) {
                    adControlAccessor.removeOnGlobalLayoutListener(this);
                    MRAIDAdSDKEventListener.this.mraidAdSDKBridge.updateDefaultPosition(currentPosition.getSize().getWidth(), currentPosition.getSize().getHeight(), currentPosition.getX(), currentPosition.getY());
                    MRAIDAdSDKEventListener.this.mraidAdSDKBridge.orientationPropertyChange();
                }
            }
        });
    }

    private void handleDefaultEvent(AdControlAccessor adControlAccessor) {
        adControlAccessor.injectJavascript("mraidBridge.stateChange('default');");
    }

    private void handleClosedEvent(AdControlAccessor adControlAccessor) {
        if (adControlAccessor.getAdState().equals(AdState.EXPANDED)) {
            this.mraidAdSDKBridge.collapseExpandedAd(adControlAccessor);
        } else if (adControlAccessor.getAdState().equals(AdState.SHOWING)) {
            adControlAccessor.injectJavascript("mraidBridge.stateChange('hidden');");
            adControlAccessor.injectJavascript("mraidBridge.viewableChange('false');");
        }
    }
}
