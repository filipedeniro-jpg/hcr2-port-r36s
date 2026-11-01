package com.ironsource.mediationsdk.utils;

/* JADX INFO: loaded from: classes2.dex */
public class SessionDepthManager {
    public static final int BANNER = 3;
    public static final int INTERSTITIAL = 2;
    public static final int NONE = -1;
    public static final int OFFERWALL = 0;
    public static final int REWARDEDVIDEO = 1;
    private static SessionDepthManager mInstance;
    private int mRewardedVideoDepth = 1;
    private int mInterstitialDepth = 1;
    private int mOfferwallDepth = 1;
    private int mBannerDepth = 1;

    public static synchronized SessionDepthManager getInstance() {
        if (mInstance == null) {
            mInstance = new SessionDepthManager();
        }
        return mInstance;
    }

    public synchronized void increaseSessionDepth(int i) {
        try {
            if (i == 0) {
                this.mOfferwallDepth++;
            } else if (i == 1) {
                this.mRewardedVideoDepth++;
            } else if (i == 2) {
                this.mInterstitialDepth++;
            } else if (i == 3) {
                this.mBannerDepth++;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized int getSessionDepth(int i) {
        try {
            if (i == 0) {
                return this.mOfferwallDepth;
            }
            if (i == 1) {
                return this.mRewardedVideoDepth;
            }
            if (i == 2) {
                return this.mInterstitialDepth;
            }
            if (i != 3) {
                return -1;
            }
            return this.mBannerDepth;
        } catch (Throwable th) {
            throw th;
        }
    }
}
