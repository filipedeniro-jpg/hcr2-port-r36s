package com.vungle.mediation;

/* JADX INFO: loaded from: classes2.dex */
abstract class VungleListener {
    private boolean mIsWaitingInit = false;
    private String mWaitingForPlacement;

    void onAdAvailable() {
    }

    void onAdEnd(String str, boolean z, boolean z2) {
    }

    void onAdFail(String str) {
    }

    void onAdFailedToLoad() {
    }

    void onAdStart(String str) {
    }

    void onInitialized(boolean z) {
    }

    VungleListener() {
    }

    void waitForAd(String str) {
        this.mWaitingForPlacement = str;
    }

    String getWaitingForPlacement() {
        return this.mWaitingForPlacement;
    }

    public boolean isWaitingInit() {
        return this.mIsWaitingInit;
    }

    void setWaitingInit(boolean z) {
        this.mIsWaitingInit = z;
    }
}
