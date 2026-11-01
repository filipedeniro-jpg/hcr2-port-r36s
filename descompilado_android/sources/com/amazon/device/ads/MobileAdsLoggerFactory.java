package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class MobileAdsLoggerFactory {
    MobileAdsLoggerFactory() {
    }

    public MobileAdsLogger createMobileAdsLogger(String str) {
        return new MobileAdsLogger(new LogcatLogger()).withLogTag(str);
    }
}
