package com.ironsource.mediationsdk;

import android.os.Handler;
import android.os.Looper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

/* JADX INFO: loaded from: classes2.dex */
public class ISListenerWrapper {
    private static final ISListenerWrapper sInstance = new ISListenerWrapper();
    private InterstitialListener mListener = null;

    public static synchronized ISListenerWrapper getInstance() {
        return sInstance;
    }

    private ISListenerWrapper() {
    }

    public synchronized void setListener(InterstitialListener interstitialListener) {
        this.mListener = interstitialListener;
    }

    public synchronized InterstitialListener getListener() {
        return this.mListener;
    }

    public synchronized void onInterstitialAdReady() {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISListenerWrapper.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISListenerWrapper.this.mListener.onInterstitialAdReady();
                        ISListenerWrapper.this.log("onInterstitialAdReady()");
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdLoadFailed(final IronSourceError ironSourceError) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISListenerWrapper.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISListenerWrapper.this.mListener.onInterstitialAdLoadFailed(ironSourceError);
                        ISListenerWrapper.this.log("onInterstitialAdLoadFailed() error=" + ironSourceError.getErrorMessage());
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdOpened() {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISListenerWrapper.3
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISListenerWrapper.this.mListener.onInterstitialAdOpened();
                        ISListenerWrapper.this.log("onInterstitialAdOpened()");
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdClosed() {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISListenerWrapper.4
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISListenerWrapper.this.mListener.onInterstitialAdClosed();
                        ISListenerWrapper.this.log("onInterstitialAdClosed()");
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdShowSucceeded() {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISListenerWrapper.5
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISListenerWrapper.this.mListener.onInterstitialAdShowSucceeded();
                        ISListenerWrapper.this.log("onInterstitialAdShowSucceeded()");
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdShowFailed(final IronSourceError ironSourceError) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISListenerWrapper.6
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISListenerWrapper.this.mListener.onInterstitialAdShowFailed(ironSourceError);
                        ISListenerWrapper.this.log("onInterstitialAdShowFailed() error=" + ironSourceError.getErrorMessage());
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdClicked() {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISListenerWrapper.7
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISListenerWrapper.this.mListener.onInterstitialAdClicked();
                        ISListenerWrapper.this.log("onInterstitialAdClicked()");
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
