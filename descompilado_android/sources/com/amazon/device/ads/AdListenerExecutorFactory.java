package com.amazon.device.ads;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
class AdListenerExecutorFactory {
    private final AdListenerExecutorConstructor adListenerExecutorConstructor;
    private final MobileAdsLoggerFactory loggerFactory;

    public AdListenerExecutorFactory(MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this(mobileAdsLoggerFactory, new AdListenerExecutorConstructor());
    }

    public AdListenerExecutorFactory(MobileAdsLoggerFactory mobileAdsLoggerFactory, AdListenerExecutorConstructor adListenerExecutorConstructor) {
        this.loggerFactory = mobileAdsLoggerFactory;
        this.adListenerExecutorConstructor = adListenerExecutorConstructor;
    }

    protected MobileAdsLoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    public AdListenerExecutor createAdListenerExecutor(AdListener adListener) {
        return createAdListenerExecutor(adListener, this.loggerFactory);
    }

    public AdListenerExecutor createAdListenerExecutor(AdListener adListener, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        AdListenerExecutor adListenerExecutorCreateAdListenerExecutor = this.adListenerExecutorConstructor.createAdListenerExecutor(adListener, mobileAdsLoggerFactory);
        if (adListener instanceof ExtendedAdListener) {
            createAdResizedCommand(adListenerExecutorCreateAdListenerExecutor);
            createAdExpiredCommand(adListenerExecutorCreateAdListenerExecutor);
        }
        return adListenerExecutorCreateAdListenerExecutor;
    }

    private void createAdResizedCommand(final AdListenerExecutor adListenerExecutor) {
        adListenerExecutor.setOnAdResizedCommand(new OnAdResizedCommand() { // from class: com.amazon.device.ads.AdListenerExecutorFactory.1
            @Override // com.amazon.device.ads.OnAdResizedCommand
            public void onAdResized(final Ad ad, final Rect rect) {
                adListenerExecutor.execute(new Runnable() { // from class: com.amazon.device.ads.AdListenerExecutorFactory.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((ExtendedAdListener) adListenerExecutor.getAdListener()).onAdResized(ad, rect);
                    }
                });
            }
        });
    }

    private void createAdExpiredCommand(final AdListenerExecutor adListenerExecutor) {
        adListenerExecutor.setOnAdExpiredCommand(new OnAdExpiredCommand() { // from class: com.amazon.device.ads.AdListenerExecutorFactory.2
            @Override // com.amazon.device.ads.OnAdExpiredCommand
            public void onAdExpired(final Ad ad) {
                adListenerExecutor.execute(new Runnable() { // from class: com.amazon.device.ads.AdListenerExecutorFactory.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((ExtendedAdListener) adListenerExecutor.getAdListener()).onAdExpired(ad);
                    }
                });
            }
        });
    }

    static class AdListenerExecutorConstructor {
        AdListenerExecutorConstructor() {
        }

        public AdListenerExecutor createAdListenerExecutor(AdListener adListener, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
            return new AdListenerExecutor(adListener, mobileAdsLoggerFactory);
        }
    }
}
