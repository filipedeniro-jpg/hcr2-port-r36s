package com.amazon.device.ads;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
class AdListenerExecutor {
    private static final String LOGTAG = "AdListenerExecutor";
    private final AdListener adListener;
    private final MobileAdsLogger logger;
    private OnAdEventCommand onAdEventCommand;
    private OnAdExpiredCommand onAdExpiredCommand;
    private OnAdReceivedCommand onAdReceivedCommand;
    private OnAdResizedCommand onAdResizedCommand;
    private OnSpecialUrlClickedCommand onSpecialUrlClickedCommand;
    private final ThreadUtils.ThreadRunner threadRunner;

    public AdListenerExecutor(AdListener adListener, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this(adListener, ThreadUtils.getThreadRunner(), mobileAdsLoggerFactory);
    }

    AdListenerExecutor(AdListener adListener, ThreadUtils.ThreadRunner threadRunner, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.adListener = adListener;
        this.threadRunner = threadRunner;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    AdListener getAdListener() {
        return this.adListener;
    }

    public void onAdLoaded(final Ad ad, final AdProperties adProperties) {
        execute(new Runnable() { // from class: com.amazon.device.ads.AdListenerExecutor.1
            @Override // java.lang.Runnable
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdLoaded(ad, adProperties);
            }
        });
    }

    public void onAdFailedToLoad(final Ad ad, final AdError adError) {
        execute(new Runnable() { // from class: com.amazon.device.ads.AdListenerExecutor.2
            @Override // java.lang.Runnable
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdFailedToLoad(ad, adError);
            }
        });
    }

    public void onAdExpanded(final Ad ad) {
        execute(new Runnable() { // from class: com.amazon.device.ads.AdListenerExecutor.3
            @Override // java.lang.Runnable
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdExpanded(ad);
            }
        });
    }

    public void onAdCollapsed(final Ad ad) {
        execute(new Runnable() { // from class: com.amazon.device.ads.AdListenerExecutor.4
            @Override // java.lang.Runnable
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdCollapsed(ad);
            }
        });
    }

    public void onAdDismissed(final Ad ad) {
        execute(new Runnable() { // from class: com.amazon.device.ads.AdListenerExecutor.5
            @Override // java.lang.Runnable
            public void run() {
                AdListenerExecutor.this.getAdListener().onAdDismissed(ad);
            }
        });
    }

    public void onAdResized(Ad ad, Rect rect) {
        OnAdResizedCommand onAdResizedCommand = this.onAdResizedCommand;
        if (onAdResizedCommand == null) {
            this.logger.d("Ad listener called - Ad Resized.");
        } else {
            onAdResizedCommand.onAdResized(ad, rect);
        }
    }

    public void onAdExpired(Ad ad) {
        OnAdExpiredCommand onAdExpiredCommand = this.onAdExpiredCommand;
        if (onAdExpiredCommand == null) {
            this.logger.d("Ad listener called - Ad Expired.");
        } else {
            onAdExpiredCommand.onAdExpired(ad);
        }
    }

    public void onSpecialUrlClicked(Ad ad, String str) {
        OnSpecialUrlClickedCommand onSpecialUrlClickedCommand = this.onSpecialUrlClickedCommand;
        if (onSpecialUrlClickedCommand == null) {
            this.logger.d("Ad listener called - Special Url Clicked.");
        } else {
            onSpecialUrlClickedCommand.onSpecialUrlClicked(ad, str);
        }
    }

    public ActionCode onAdReceived(Ad ad, AdData adData) {
        OnAdReceivedCommand onAdReceivedCommand = this.onAdReceivedCommand;
        if (onAdReceivedCommand == null) {
            this.logger.d("Ad listener called - Ad Received.");
            return ActionCode.DISPLAY;
        }
        return onAdReceivedCommand.onAdReceived(ad, adData);
    }

    public void onAdEvent(AdEvent adEvent) {
        OnAdEventCommand onAdEventCommand = this.onAdEventCommand;
        if (onAdEventCommand == null) {
            this.logger.d("Ad listener called - Ad Event: " + adEvent);
            return;
        }
        onAdEventCommand.onAdEvent(adEvent);
    }

    protected void execute(Runnable runnable) {
        this.threadRunner.execute(runnable, ThreadUtils.ExecutionStyle.SCHEDULE, ThreadUtils.ExecutionThread.MAIN_THREAD);
    }

    public void setOnAdEventCommand(OnAdEventCommand onAdEventCommand) {
        this.onAdEventCommand = onAdEventCommand;
    }

    public void setOnAdResizedCommand(OnAdResizedCommand onAdResizedCommand) {
        this.onAdResizedCommand = onAdResizedCommand;
    }

    public void setOnAdExpiredCommand(OnAdExpiredCommand onAdExpiredCommand) {
        this.onAdExpiredCommand = onAdExpiredCommand;
    }

    public void setOnSpecialUrlClickedCommand(OnSpecialUrlClickedCommand onSpecialUrlClickedCommand) {
        this.onSpecialUrlClickedCommand = onSpecialUrlClickedCommand;
    }

    public void setOnAdReceivedCommand(OnAdReceivedCommand onAdReceivedCommand) {
        this.onAdReceivedCommand = onAdReceivedCommand;
    }
}
