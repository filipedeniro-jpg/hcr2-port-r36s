package com.amazon.device.ads;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
class AdCloser {
    private static final String LOGTAG = "AdCloser";
    private final AdController adController;
    private final AtomicBoolean isClosing;
    private final MobileAdsLogger logger;

    public AdCloser(AdController adController) {
        this(adController, new MobileAdsLoggerFactory());
    }

    AdCloser(AdController adController, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.isClosing = new AtomicBoolean(false);
        this.adController = adController;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0037  */
    /* JADX WARN: Code duplicated, block: B:15:0x0044  */
    /* JADX WARN: Code duplicated, block: B:17:0x0047  */
    public boolean closeAd() {
        boolean z;
        boolean z2;
        this.logger.d("Ad is attempting to close.");
        if (this.adController.getAdState().equals(AdState.READY_TO_LOAD)) {
            return false;
        }
        boolean z3 = true;
        if (this.isClosing.getAndSet(true)) {
            return false;
        }
        int iAdClosing = this.adController.getAdControlCallback().adClosing();
        if (iAdClosing != 0) {
            if (iAdClosing != 1) {
                z = false;
            } else {
                z = true;
                z2 = true;
            }
            if (z) {
                this.adController.fireSDKEvent(new SDKEvent(SDKEvent.SDKEventType.CLOSED));
            } else {
                z3 = false;
            }
            if (z2) {
                this.adController.resetToReady();
            }
            this.isClosing.set(false);
            return z3;
        }
        z = true;
        z2 = false;
        if (z) {
            this.adController.fireSDKEvent(new SDKEvent(SDKEvent.SDKEventType.CLOSED));
        } else {
            z3 = false;
        }
        if (z2) {
            this.adController.resetToReady();
        }
        this.isClosing.set(false);
        return z3;
    }
}
