package com.ironsource.mediationsdk;

import android.os.Handler;
import android.os.Looper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;

/* JADX INFO: loaded from: classes2.dex */
public class RVListenerWrapper {
    private static final RVListenerWrapper sInstance = new RVListenerWrapper();
    private RewardedVideoListener mListener = null;

    public static synchronized RVListenerWrapper getInstance() {
        return sInstance;
    }

    private RVListenerWrapper() {
    }

    public synchronized void setListener(RewardedVideoListener rewardedVideoListener) {
        this.mListener = rewardedVideoListener;
    }

    public synchronized void onRewardedVideoAdOpened() {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVListenerWrapper.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVListenerWrapper.this.mListener.onRewardedVideoAdOpened();
                        RVListenerWrapper.this.log("onRewardedVideoAdOpened()");
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdClosed() {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVListenerWrapper.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVListenerWrapper.this.mListener.onRewardedVideoAdClosed();
                        RVListenerWrapper.this.log("onRewardedVideoAdClosed()");
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAvailabilityChanged(final boolean z) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVListenerWrapper.3
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVListenerWrapper.this.mListener.onRewardedVideoAvailabilityChanged(z);
                        RVListenerWrapper.this.log("onRewardedVideoAvailabilityChanged() available=" + z);
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdStarted() {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVListenerWrapper.4
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVListenerWrapper.this.mListener.onRewardedVideoAdStarted();
                        RVListenerWrapper.this.log("onRewardedVideoAdStarted()");
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdEnded() {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVListenerWrapper.5
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVListenerWrapper.this.mListener.onRewardedVideoAdEnded();
                        RVListenerWrapper.this.log("onRewardedVideoAdEnded()");
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdRewarded(final Placement placement) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVListenerWrapper.6
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVListenerWrapper.this.mListener.onRewardedVideoAdRewarded(placement);
                        RVListenerWrapper.this.log("onRewardedVideoAdRewarded() placement=" + placement.getPlacementName());
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdShowFailed(final IronSourceError ironSourceError) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVListenerWrapper.7
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVListenerWrapper.this.mListener.onRewardedVideoAdShowFailed(ironSourceError);
                        RVListenerWrapper.this.log("onRewardedVideoAdShowFailed() error=" + ironSourceError.getErrorMessage());
                    }
                }
            });
        }
    }

    public synchronized void onRewardedVideoAdClicked(final Placement placement) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.RVListenerWrapper.8
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        RVListenerWrapper.this.mListener.onRewardedVideoAdClicked(placement);
                        RVListenerWrapper.this.log("onRewardedVideoAdClicked() placement=" + placement.getPlacementName());
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
