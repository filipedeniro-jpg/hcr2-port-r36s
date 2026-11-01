package com.ironsource.mediationsdk;

import android.os.Handler;
import android.os.Looper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;

/* JADX INFO: loaded from: classes2.dex */
public class ISDemandOnlyListenerWrapper {
    private static final ISDemandOnlyListenerWrapper sInstance = new ISDemandOnlyListenerWrapper();
    private ISDemandOnlyInterstitialListener mListener = null;

    public static synchronized ISDemandOnlyListenerWrapper getInstance() {
        return sInstance;
    }

    private ISDemandOnlyListenerWrapper() {
    }

    public synchronized void setListener(ISDemandOnlyInterstitialListener iSDemandOnlyInterstitialListener) {
        this.mListener = iSDemandOnlyInterstitialListener;
    }

    public synchronized ISDemandOnlyInterstitialListener getListener() {
        return this.mListener;
    }

    public synchronized void onInterstitialAdReady(final String str) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISDemandOnlyListenerWrapper.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISDemandOnlyListenerWrapper.this.mListener.onInterstitialAdReady(str);
                        ISDemandOnlyListenerWrapper.this.log("onInterstitialAdReady() instanceId=" + str);
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdLoadFailed(final String str, final IronSourceError ironSourceError) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISDemandOnlyListenerWrapper.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISDemandOnlyListenerWrapper.this.mListener.onInterstitialAdLoadFailed(str, ironSourceError);
                        ISDemandOnlyListenerWrapper.this.log("onInterstitialAdLoadFailed() instanceId=" + str + " error=" + ironSourceError.getErrorMessage());
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdOpened(final String str) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISDemandOnlyListenerWrapper.3
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISDemandOnlyListenerWrapper.this.mListener.onInterstitialAdOpened(str);
                        ISDemandOnlyListenerWrapper.this.log("onInterstitialAdOpened() instanceId=" + str);
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdClosed(final String str) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISDemandOnlyListenerWrapper.4
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISDemandOnlyListenerWrapper.this.mListener.onInterstitialAdClosed(str);
                        ISDemandOnlyListenerWrapper.this.log("onInterstitialAdClosed() instanceId=" + str);
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdShowFailed(final String str, final IronSourceError ironSourceError) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISDemandOnlyListenerWrapper.5
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISDemandOnlyListenerWrapper.this.mListener.onInterstitialAdShowFailed(str, ironSourceError);
                        ISDemandOnlyListenerWrapper.this.log("onInterstitialAdShowFailed() instanceId=" + str + " error=" + ironSourceError.getErrorMessage());
                    }
                }
            });
        }
    }

    public synchronized void onInterstitialAdClicked(final String str) {
        if (this.mListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ironsource.mediationsdk.ISDemandOnlyListenerWrapper.6
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (this) {
                        ISDemandOnlyListenerWrapper.this.mListener.onInterstitialAdClicked(str);
                        ISDemandOnlyListenerWrapper.this.log("onInterstitialAdClicked() instanceId=" + str);
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
