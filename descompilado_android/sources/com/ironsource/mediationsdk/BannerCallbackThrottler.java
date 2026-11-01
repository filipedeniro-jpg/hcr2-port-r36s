package com.ironsource.mediationsdk;

import android.os.Handler;
import android.os.Looper;
import com.ironsource.mediationsdk.logger.IronSourceError;

/* JADX INFO: loaded from: classes2.dex */
public class BannerCallbackThrottler {
    private static BannerCallbackThrottler sInstance;
    private int mBNDelayLoadFailureNotificationInSeconds;
    private long mLastInvoked = 0;
    private boolean mIsWaitingForInvocation = false;

    public static synchronized BannerCallbackThrottler getInstance() {
        if (sInstance == null) {
            sInstance = new BannerCallbackThrottler();
        }
        return sInstance;
    }

    private BannerCallbackThrottler() {
    }

    public void sendBannerAdLoadFailed(final IronSourceBannerLayout ironSourceBannerLayout, final IronSourceError ironSourceError) {
        synchronized (this) {
            if (this.mIsWaitingForInvocation) {
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mLastInvoked;
            if (jCurrentTimeMillis > this.mBNDelayLoadFailureNotificationInSeconds * 1000) {
                invokeCallback(ironSourceBannerLayout, ironSourceError);
                return;
            }
            this.mIsWaitingForInvocation = true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ironsource.mediationsdk.BannerCallbackThrottler.1
                @Override // java.lang.Runnable
                public void run() {
                    BannerCallbackThrottler.this.invokeCallback(ironSourceBannerLayout, ironSourceError);
                }
            }, ((long) (this.mBNDelayLoadFailureNotificationInSeconds * 1000)) - jCurrentTimeMillis);
        }
    }

    public boolean hasPendingInvocation() {
        boolean z;
        synchronized (this) {
            z = this.mIsWaitingForInvocation;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeCallback(IronSourceBannerLayout ironSourceBannerLayout, IronSourceError ironSourceError) {
        this.mLastInvoked = System.currentTimeMillis();
        this.mIsWaitingForInvocation = false;
        ironSourceBannerLayout.sendBannerAdLoadFailed(ironSourceError);
    }

    public void setDelayLoadFailureNotificationInSeconds(int i) {
        this.mBNDelayLoadFailureNotificationInSeconds = i;
    }
}
