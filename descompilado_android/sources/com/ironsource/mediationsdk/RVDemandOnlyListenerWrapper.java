package com.ironsource.mediationsdk;

import android.os.Handler;
import android.os.Looper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;

/* JADX INFO: loaded from: classes2.dex */
public class RVDemandOnlyListenerWrapper {
    private static final RVDemandOnlyListenerWrapper sInstance = new RVDemandOnlyListenerWrapper();
    private ISDemandOnlyRewardedVideoListener mListener = null;

    public static synchronized RVDemandOnlyListenerWrapper getInstance() {
        return sInstance;
    }

    private RVDemandOnlyListenerWrapper() {
    }

    public synchronized void setListener(ISDemandOnlyRewardedVideoListener iSDemandOnlyRewardedVideoListener) {
        this.mListener = iSDemandOnlyRewardedVideoListener;
    }

    public synchronized void onRewardedVideoLoadSuccess(final String str) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVDemandOnlyListenerWrapper.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVDemandOnlyListenerWrapper.this.mListener.onRewardedVideoAdLoadSuccess(str);
                        RVDemandOnlyListenerWrapper.this.log("onRewardedVideoAdLoadSuccess() instanceId=" + str);
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdLoadFailed(final String str, final IronSourceError ironSourceError) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVDemandOnlyListenerWrapper.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVDemandOnlyListenerWrapper.this.mListener.onRewardedVideoAdLoadFailed(str, ironSourceError);
                        RVDemandOnlyListenerWrapper.this.log("onRewardedVideoAdLoadFailed() instanceId=" + str + "error=" + ironSourceError.getErrorMessage());
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdOpened(final String str) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVDemandOnlyListenerWrapper.3
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVDemandOnlyListenerWrapper.this.mListener.onRewardedVideoAdOpened(str);
                        RVDemandOnlyListenerWrapper.this.log("onRewardedVideoAdOpened() instanceId=" + str);
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdClosed(final String str) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVDemandOnlyListenerWrapper.4
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVDemandOnlyListenerWrapper.this.mListener.onRewardedVideoAdClosed(str);
                        RVDemandOnlyListenerWrapper.this.log("onRewardedVideoAdClosed() instanceId=" + str);
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdShowFailed(final String str, final IronSourceError ironSourceError) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVDemandOnlyListenerWrapper.5
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVDemandOnlyListenerWrapper.this.mListener.onRewardedVideoAdShowFailed(str, ironSourceError);
                        RVDemandOnlyListenerWrapper.this.log("onRewardedVideoAdShowFailed() instanceId=" + str + "error=" + ironSourceError.getErrorMessage());
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdClicked(final String str) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVDemandOnlyListenerWrapper.6
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVDemandOnlyListenerWrapper.this.mListener.onRewardedVideoAdClicked(str);
                        RVDemandOnlyListenerWrapper.this.log("onRewardedVideoAdClicked() instanceId=" + str);
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdRewarded(final String str) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVDemandOnlyListenerWrapper.7
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVDemandOnlyListenerWrapper.this.mListener.onRewardedVideoAdRewarded(str);
                        RVDemandOnlyListenerWrapper.this.log("onRewardedVideoAdRewarded() instanceId=" + str);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, str, 1);
    }
}
