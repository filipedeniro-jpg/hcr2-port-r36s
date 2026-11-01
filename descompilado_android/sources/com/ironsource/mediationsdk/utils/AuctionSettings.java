package com.ironsource.mediationsdk.utils;

/* JADX INFO: loaded from: classes2.dex */
public class AuctionSettings {
    private long mAuctionRetryInterval;
    private String mBlob;
    private boolean mIsAuctionOnShowStart;
    private boolean mIsProgrammatic;
    private long mMinTimeToWaitBeforeFirstAuction;
    private long mTimeToWaitBeforeAuction;
    private String mUrl;

    AuctionSettings() {
        this.mBlob = "";
        this.mUrl = "";
        this.mIsProgrammatic = false;
        this.mMinTimeToWaitBeforeFirstAuction = 0L;
        this.mAuctionRetryInterval = 0L;
        this.mTimeToWaitBeforeAuction = 0L;
        this.mIsAuctionOnShowStart = true;
    }

    AuctionSettings(String str, String str2, boolean z, long j, long j2, long j3, boolean z2) {
        this.mBlob = str;
        this.mUrl = str2;
        this.mIsProgrammatic = z;
        this.mMinTimeToWaitBeforeFirstAuction = j;
        this.mAuctionRetryInterval = j2;
        this.mTimeToWaitBeforeAuction = j3;
        this.mIsAuctionOnShowStart = z2;
    }

    public boolean getIsProgrammatic() {
        return this.mIsProgrammatic;
    }

    public String getBlob() {
        return this.mBlob;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public long getTimeToWaitBeforeFirstAuctionMs() {
        return this.mMinTimeToWaitBeforeFirstAuction;
    }

    public long getAuctionRetryInterval() {
        return this.mAuctionRetryInterval;
    }

    public long getTimeToWaitBeforeAuctionMs() {
        return this.mTimeToWaitBeforeAuction;
    }

    public boolean getIsAuctionOnShowStart() {
        return this.mIsAuctionOnShowStart;
    }
}
