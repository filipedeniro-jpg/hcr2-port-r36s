package com.ironsource.mediationsdk.model;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class BannerConfigurations {
    private static final int DEFAULT_BN_PLACEMENT_ID = 0;
    private int mBNAdaptersSmartLoadAmount;
    private long mBNAdaptersTimeOutInMilliseconds;
    private int mBNDelayLoadFailureNotificationInSeconds;
    private ApplicationEvents mBNEvents;
    private ArrayList<BannerPlacement> mBNPlacements;
    private int mBNRefreshInterval;
    private BannerPlacement mDefaultBNPlacement;

    public BannerConfigurations() {
        this.mBNEvents = new ApplicationEvents();
        this.mBNPlacements = new ArrayList<>();
    }

    public BannerConfigurations(int i, long j, ApplicationEvents applicationEvents, int i2, int i3) {
        this.mBNPlacements = new ArrayList<>();
        this.mBNAdaptersSmartLoadAmount = i;
        this.mBNAdaptersTimeOutInMilliseconds = j;
        this.mBNEvents = applicationEvents;
        this.mBNRefreshInterval = i2;
        this.mBNDelayLoadFailureNotificationInSeconds = i3;
    }

    public int getBannerAdaptersSmartLoadAmount() {
        return this.mBNAdaptersSmartLoadAmount;
    }

    public long getBannerAdaptersSmartLoadTimeout() {
        return this.mBNAdaptersTimeOutInMilliseconds;
    }

    public ApplicationEvents getBannerEventsConfigurations() {
        return this.mBNEvents;
    }

    public void addBannerPlacement(BannerPlacement bannerPlacement) {
        if (bannerPlacement != null) {
            this.mBNPlacements.add(bannerPlacement);
            if (this.mDefaultBNPlacement == null) {
                this.mDefaultBNPlacement = bannerPlacement;
            } else if (bannerPlacement.getPlacementId() == 0) {
                this.mDefaultBNPlacement = bannerPlacement;
            }
        }
    }

    public BannerPlacement getBannerPlacement(String str) {
        for (BannerPlacement bannerPlacement : this.mBNPlacements) {
            if (bannerPlacement.getPlacementName().equals(str)) {
                return bannerPlacement;
            }
        }
        return null;
    }

    public BannerPlacement getDefaultBannerPlacement() {
        for (BannerPlacement bannerPlacement : this.mBNPlacements) {
            if (bannerPlacement.isDefault()) {
                return bannerPlacement;
            }
        }
        return this.mDefaultBNPlacement;
    }

    public int getBannerRefreshInterval() {
        return this.mBNRefreshInterval;
    }

    public int getBannerDelayLoadFailure() {
        return this.mBNDelayLoadFailureNotificationInSeconds;
    }
}
