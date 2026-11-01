package com.ironsource.mediationsdk.sdk;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.events.RewardedVideoEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ListenersWrapper implements RewardedVideoListener, InterstitialListener, InternalOfferwallListener, RewardedInterstitialListener, SegmentListener {
    private InterstitialListener mInterstitialListener;
    private long mLastChangedAvailabilityTime;
    private OfferwallListener mOfferwallListener;
    private RewardedInterstitialListener mRewardedInterstitialListener;
    private RewardedVideoListener mRewardedVideoListener;
    private SegmentListener mSegementListener;
    private InterstitialPlacement mInterstitialPlacement = null;
    private String mRvPlacementName = null;
    private CallbackHandlerThread mCallbackHandlerThread = new CallbackHandlerThread();

    public ListenersWrapper() {
        this.mCallbackHandlerThread.start();
        this.mLastChangedAvailabilityTime = new Date().getTime();
    }

    public void setInterstitialPlacement(InterstitialPlacement interstitialPlacement) {
        this.mInterstitialPlacement = interstitialPlacement;
    }

    public void setRvPlacement(String str) {
        this.mRvPlacementName = str;
    }

    private boolean canSendCallback(Object obj) {
        return (obj == null || this.mCallbackHandlerThread == null) ? false : true;
    }

    private void sendCallback(Runnable runnable) {
        Handler callbackHandler;
        CallbackHandlerThread callbackHandlerThread = this.mCallbackHandlerThread;
        if (callbackHandlerThread == null || (callbackHandler = callbackHandlerThread.getCallbackHandler()) == null) {
            return;
        }
        callbackHandler.post(runnable);
    }

    public void setRewardedVideoListener(RewardedVideoListener rewardedVideoListener) {
        this.mRewardedVideoListener = rewardedVideoListener;
    }

    public void setInterstitialListener(InterstitialListener interstitialListener) {
        this.mInterstitialListener = interstitialListener;
    }

    public void setOfferwallListener(OfferwallListener offerwallListener) {
        this.mOfferwallListener = offerwallListener;
    }

    public void setRewardedInterstitialListener(RewardedInterstitialListener rewardedInterstitialListener) {
        this.mRewardedInterstitialListener = rewardedInterstitialListener;
    }

    public void setSegmentListener(SegmentListener segmentListener) {
        this.mSegementListener = segmentListener;
    }

    @Override // com.ironsource.mediationsdk.sdk.SegmentListener
    public void onSegmentReceived(final String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onSegmentReceived(" + str + ")", 1);
        if (canSendCallback(this.mSegementListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.1
                @Override // java.lang.Runnable
                public void run() {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    ListenersWrapper.this.mSegementListener.onSegmentReceived(str);
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdOpened()", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.2
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdOpened();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdClosed()", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.3
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdClosed();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAvailabilityChanged(final boolean z) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAvailabilityChanged(available:" + z + ")", 1);
        long time = new Date().getTime() - this.mLastChangedAvailabilityTime;
        this.mLastChangedAvailabilityTime = new Date().getTime();
        JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        try {
            mediationAdditionalData.put("duration", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(z ? IronSourceConstants.RV_CALLBACK_AVAILABILITY_TRUE : IronSourceConstants.RV_CALLBACK_AVAILABILITY_FALSE, mediationAdditionalData));
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.4
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAvailabilityChanged(z);
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdStarted() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdStarted()", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.5
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdStarted();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdEnded() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdEnded()", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.6
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdEnded();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdRewarded(final Placement placement) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdRewarded(" + placement.toString() + ")", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.7
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdRewarded(placement);
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdClicked(final Placement placement) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdClicked(" + placement.getPlacementName() + ")", 1);
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.8
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdClicked(placement);
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdShowFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onRewardedVideoAdShowFailed(" + ironSourceError.toString() + ")", 1);
        JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        try {
            String strSubstring = ironSourceError.getErrorMessage().substring(0, Math.min(ironSourceError.getErrorMessage().length(), 39));
            mediationAdditionalData.put("errorCode", ironSourceError.getErrorCode());
            mediationAdditionalData.put(IronSourceConstants.EVENTS_ERROR_REASON, strSubstring);
            if (!TextUtils.isEmpty(this.mRvPlacementName)) {
                mediationAdditionalData.put("placement", this.mRvPlacementName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(IronSourceConstants.RV_CALLBACK_SHOW_FAILED, mediationAdditionalData));
        if (canSendCallback(this.mRewardedVideoListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.9
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mRewardedVideoListener.onRewardedVideoAdShowFailed(ironSourceError);
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialListener
    public void onInterstitialAdReady() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdReady()", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.10
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdReady();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialListener
    public void onInterstitialAdLoadFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdLoadFailed(" + ironSourceError + ")", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.11
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdLoadFailed(ironSourceError);
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialListener
    public void onInterstitialAdOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdOpened()", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.12
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdOpened();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialListener
    public void onInterstitialAdShowSucceeded() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdShowSucceeded()", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.13
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdShowSucceeded();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialListener
    public void onInterstitialAdShowFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdShowFailed(" + ironSourceError + ")", 1);
        JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        try {
            mediationAdditionalData.put("errorCode", ironSourceError.getErrorCode());
            if (this.mInterstitialPlacement != null && !TextUtils.isEmpty(this.mInterstitialPlacement.getPlacementName())) {
                mediationAdditionalData.put("placement", this.mInterstitialPlacement.getPlacementName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        InterstitialEventsManager.getInstance().log(new EventData(IronSourceConstants.IS_CALLBACK_AD_SHOW_ERROR, mediationAdditionalData));
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.14
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdShowFailed(ironSourceError);
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialListener
    public void onInterstitialAdClicked() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdClicked()", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.15
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdClicked();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.InterstitialListener
    public void onInterstitialAdClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdClosed()", 1);
        if (canSendCallback(this.mInterstitialListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.16
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mInterstitialListener.onInterstitialAdClosed();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedInterstitialListener
    public void onInterstitialAdRewarded() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onInterstitialAdRewarded()", 1);
        if (canSendCallback(this.mRewardedInterstitialListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.17
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mRewardedInterstitialListener.onInterstitialAdRewarded();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public void onOfferwallOpened() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onOfferwallOpened()", 1);
        if (canSendCallback(this.mOfferwallListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.18
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallOpened();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public void onOfferwallShowFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onOfferwallShowFailed(" + ironSourceError + ")", 1);
        if (canSendCallback(this.mOfferwallListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.19
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallShowFailed(ironSourceError);
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public boolean onOfferwallAdCredited(int i, int i2, boolean z) {
        OfferwallListener offerwallListener = this.mOfferwallListener;
        boolean zOnOfferwallAdCredited = offerwallListener != null ? offerwallListener.onOfferwallAdCredited(i, i2, z) : false;
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onOfferwallAdCredited(credits:" + i + ", totalCredits:" + i2 + ", totalCreditsFlag:" + z + "):" + zOnOfferwallAdCredited, 1);
        return zOnOfferwallAdCredited;
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public void onGetOfferwallCreditsFailed(final IronSourceError ironSourceError) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onGetOfferwallCreditsFailed(" + ironSourceError + ")", 1);
        if (canSendCallback(this.mOfferwallListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.20
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onGetOfferwallCreditsFailed(ironSourceError);
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public void onOfferwallClosed() {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, "onOfferwallClosed()", 1);
        if (canSendCallback(this.mOfferwallListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.21
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallClosed();
                }
            });
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.OfferwallListener
    public void onOfferwallAvailable(boolean z) {
        onOfferwallAvailable(z, null);
    }

    @Override // com.ironsource.mediationsdk.sdk.InternalOfferwallListener
    public void onOfferwallAvailable(final boolean z, IronSourceError ironSourceError) {
        String str = "onOfferwallAvailable(isAvailable: " + z + ")";
        if (ironSourceError != null) {
            str = str + ", error: " + ironSourceError.getErrorMessage();
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.CALLBACK, str, 1);
        JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        try {
            mediationAdditionalData.put("status", String.valueOf(z));
            if (ironSourceError != null) {
                mediationAdditionalData.put("errorCode", ironSourceError.getErrorCode());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RewardedVideoEventsManager.getInstance().log(new EventData(IronSourceConstants.OFFERWALL_AVAILABLE, mediationAdditionalData));
        if (canSendCallback(this.mOfferwallListener)) {
            sendCallback(new Runnable() { // from class: com.ironsource.mediationsdk.sdk.ListenersWrapper.22
                @Override // java.lang.Runnable
                public void run() {
                    ListenersWrapper.this.mOfferwallListener.onOfferwallAvailable(z);
                }
            });
        }
    }

    private class CallbackHandlerThread extends Thread {
        private Handler mCallbackHandler;

        private CallbackHandlerThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            this.mCallbackHandler = new Handler();
            Looper.loop();
        }

        public Handler getCallbackHandler() {
            return this.mCallbackHandler;
        }
    }
}
