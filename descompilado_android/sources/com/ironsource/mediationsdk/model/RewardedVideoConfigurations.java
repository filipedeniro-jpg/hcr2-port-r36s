package com.ironsource.mediationsdk.model;

import com.ironsource.mediationsdk.utils.AuctionSettings;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class RewardedVideoConfigurations {
    private static final int DEFAULT_RV_PLACEMENT_ID = 0;
    private String mBackFillProviderName;
    private Placement mDefaultRVPlacement;
    private int mManualLoadIntervalInSeconds;
    private String mPremiumProviderName;
    private int mRVAdaptersSmartLoadAmount;
    private int mRVAdaptersTimeOutInSeconds;
    private AuctionSettings mRVAuctionSettings;
    private ApplicationEvents mRVEvents;
    private ArrayList<Placement> mRVPlacements;

    public RewardedVideoConfigurations() {
        this.mRVPlacements = new ArrayList<>();
        this.mRVEvents = new ApplicationEvents();
    }

    public RewardedVideoConfigurations(int i, int i2, int i3, ApplicationEvents applicationEvents, AuctionSettings auctionSettings) {
        this.mRVPlacements = new ArrayList<>();
        this.mRVAdaptersSmartLoadAmount = i;
        this.mRVAdaptersTimeOutInSeconds = i2;
        this.mManualLoadIntervalInSeconds = i3;
        this.mRVEvents = applicationEvents;
        this.mRVAuctionSettings = auctionSettings;
    }

    public int getRewardedVideoAdaptersSmartLoadTimeout() {
        return this.mRVAdaptersTimeOutInSeconds;
    }

    public void addRewardedVideoPlacement(Placement placement) {
        if (placement != null) {
            this.mRVPlacements.add(placement);
            if (this.mDefaultRVPlacement == null) {
                this.mDefaultRVPlacement = placement;
            } else if (placement.getPlacementId() == 0) {
                this.mDefaultRVPlacement = placement;
            }
        }
    }

    public Placement getRewardedVideoPlacement(String str) {
        for (Placement placement : this.mRVPlacements) {
            if (placement.getPlacementName().equals(str)) {
                return placement;
            }
        }
        return null;
    }

    public Placement getDefaultRewardedVideoPlacement() {
        for (Placement placement : this.mRVPlacements) {
            if (placement.isDefault()) {
                return placement;
            }
        }
        return this.mDefaultRVPlacement;
    }

    public int getRewardedVideoAdaptersSmartLoadAmount() {
        return this.mRVAdaptersSmartLoadAmount;
    }

    public int getManualLoadIntervalInSeconds() {
        return this.mManualLoadIntervalInSeconds;
    }

    public ApplicationEvents getRewardedVideoEventsConfigurations() {
        return this.mRVEvents;
    }

    public String getBackFillProviderName() {
        return this.mBackFillProviderName;
    }

    public void setBackFillProviderName(String str) {
        this.mBackFillProviderName = str;
    }

    public String getPremiumProviderName() {
        return this.mPremiumProviderName;
    }

    public void setPremiumProviderName(String str) {
        this.mPremiumProviderName = str;
    }

    public AuctionSettings getRewardedVideoAuctionSettings() {
        return this.mRVAuctionSettings;
    }
}
