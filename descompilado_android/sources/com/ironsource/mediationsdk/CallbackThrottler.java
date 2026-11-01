package com.ironsource.mediationsdk;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class CallbackThrottler {
    private static final String MEDIATION = "mediation";
    private static final CallbackThrottler instance = new CallbackThrottler();
    private int mISDelayLoadFailureNotificationInSeconds;
    private InterstitialListener mListener = null;
    private Map<String, Long> mLastInvoked = new HashMap();
    private Map<String, Boolean> mIsWaitingForInvocation = new HashMap();

    public static synchronized CallbackThrottler getInstance() {
        return instance;
    }

    private CallbackThrottler() {
    }

    public void setInterstitialListener(InterstitialListener interstitialListener) {
        this.mListener = interstitialListener;
    }

    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
        synchronized (this) {
            onInterstitialAdLoadFailedInternal(MEDIATION, ironSourceError);
        }
    }

    public void onInterstitialAdLoadFailed(String str, IronSourceError ironSourceError) {
        synchronized (this) {
            onInterstitialAdLoadFailedInternal(str, ironSourceError);
        }
    }

    public boolean hasPendingInvocation() {
        boolean zHasPendingInvocationInternal;
        synchronized (this) {
            zHasPendingInvocationInternal = hasPendingInvocationInternal(MEDIATION);
        }
        return zHasPendingInvocationInternal;
    }

    private boolean hasPendingInvocationInternal(String str) {
        if (!TextUtils.isEmpty(str) && this.mIsWaitingForInvocation.containsKey(str)) {
            return this.mIsWaitingForInvocation.get(str).booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeCallback(String str, IronSourceError ironSourceError) {
        this.mLastInvoked.put(str, Long.valueOf(System.currentTimeMillis()));
        InterstitialListener interstitialListener = this.mListener;
        if (interstitialListener != null) {
            interstitialListener.onInterstitialAdLoadFailed(ironSourceError);
            IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdLoadFailed(" + ironSourceError.toString() + ")", 1);
        }
    }

    private void onInterstitialAdLoadFailedInternal(final String str, final IronSourceError ironSourceError) {
        if (hasPendingInvocationInternal(str)) {
            return;
        }
        if (!this.mLastInvoked.containsKey(str)) {
            invokeCallback(str, ironSourceError);
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.mLastInvoked.get(str).longValue();
        if (jCurrentTimeMillis > this.mISDelayLoadFailureNotificationInSeconds * 1000) {
            invokeCallback(str, ironSourceError);
            return;
        }
        this.mIsWaitingForInvocation.put(str, true);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ironsource.mediationsdk.CallbackThrottler.1
            @Override // java.lang.Runnable
            public void run() {
                CallbackThrottler.this.invokeCallback(str, ironSourceError);
                CallbackThrottler.this.mIsWaitingForInvocation.put(str, false);
            }
        }, ((long) (this.mISDelayLoadFailureNotificationInSeconds * 1000)) - jCurrentTimeMillis);
    }

    public void setDelayLoadFailureNotificationInSeconds(int i) {
        this.mISDelayLoadFailureNotificationInSeconds = i;
    }
}
