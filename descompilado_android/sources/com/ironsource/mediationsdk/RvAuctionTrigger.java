package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.utils.AuctionSettings;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class RvAuctionTrigger {
    private AuctionSettings mAuctionSettings;
    private RvAuctionTriggerCallback mListener;
    private Timer mTimer = null;

    public RvAuctionTrigger(AuctionSettings auctionSettings, RvAuctionTriggerCallback rvAuctionTriggerCallback) {
        this.mAuctionSettings = auctionSettings;
        this.mListener = rvAuctionTriggerCallback;
    }

    public synchronized void showStart() {
        if (this.mAuctionSettings.getIsAuctionOnShowStart()) {
            stopTimer();
            this.mTimer = new Timer();
            this.mTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.RvAuctionTrigger.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    RvAuctionTrigger.this.mListener.onAuctionTriggered();
                }
            }, this.mAuctionSettings.getTimeToWaitBeforeAuctionMs());
        }
    }

    public synchronized void showEnd() {
        if (!this.mAuctionSettings.getIsAuctionOnShowStart()) {
            stopTimer();
            this.mTimer = new Timer();
            this.mTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.RvAuctionTrigger.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    RvAuctionTrigger.this.mListener.onAuctionTriggered();
                }
            }, this.mAuctionSettings.getTimeToWaitBeforeAuctionMs());
        }
    }

    public synchronized void showError() {
        stopTimer();
        this.mListener.onAuctionTriggered();
    }

    public synchronized void loadError() {
        stopTimer();
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.RvAuctionTrigger.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                RvAuctionTrigger.this.mListener.onAuctionTriggered();
            }
        }, this.mAuctionSettings.getAuctionRetryInterval());
    }

    private void stopTimer() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }
}
