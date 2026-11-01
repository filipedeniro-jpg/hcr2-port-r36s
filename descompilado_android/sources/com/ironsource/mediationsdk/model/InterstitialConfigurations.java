package com.ironsource.mediationsdk.model;

import com.ironsource.mediationsdk.utils.AuctionSettings;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class InterstitialConfigurations {
    private static final int DEFAULT_IS_PLACEMENT_ID = 0;
    private String mBackFillProviderName;
    private InterstitialPlacement mDefaultISPlacement;
    private int mISAdaptersSmartLoadAmount;
    private int mISAdaptersTimeOutInSeconds;
    private AuctionSettings mISAuctionSettings;
    private int mISDelayLoadFailureNotificationInSeconds;
    private ApplicationEvents mISEvents;
    private ArrayList<InterstitialPlacement> mISPlacements;
    private String mPremiumProviderName;

    public InterstitialConfigurations() {
        this.mISPlacements = new ArrayList<>();
        this.mISEvents = new ApplicationEvents();
    }

    public InterstitialConfigurations(int i, int i2, ApplicationEvents applicationEvents, AuctionSettings auctionSettings, int i3) {
        this.mISPlacements = new ArrayList<>();
        this.mISAdaptersSmartLoadAmount = i;
        this.mISAdaptersTimeOutInSeconds = i2;
        this.mISEvents = applicationEvents;
        this.mISAuctionSettings = auctionSettings;
        this.mISDelayLoadFailureNotificationInSeconds = i3;
    }

    public void addInterstitialPlacement(InterstitialPlacement interstitialPlacement) {
        if (interstitialPlacement != null) {
            this.mISPlacements.add(interstitialPlacement);
            if (this.mDefaultISPlacement == null) {
                this.mDefaultISPlacement = interstitialPlacement;
            } else if (interstitialPlacement.getPlacementId() == 0) {
                this.mDefaultISPlacement = interstitialPlacement;
            }
        }
    }

    public InterstitialPlacement getInterstitialPlacement(String str) {
        for (InterstitialPlacement interstitialPlacement : this.mISPlacements) {
            if (interstitialPlacement.getPlacementName().equals(str)) {
                return interstitialPlacement;
            }
        }
        return null;
    }

    public InterstitialPlacement getDefaultInterstitialPlacement() {
        for (InterstitialPlacement interstitialPlacement : this.mISPlacements) {
            if (interstitialPlacement.isDefault()) {
                return interstitialPlacement;
            }
        }
        return this.mDefaultISPlacement;
    }

    public int getInterstitialAdaptersSmartLoadAmount() {
        return this.mISAdaptersSmartLoadAmount;
    }

    public int getInterstitialAdaptersSmartLoadTimeout() {
        return this.mISAdaptersTimeOutInSeconds;
    }

    public ApplicationEvents getInterstitialEventsConfigurations() {
        return this.mISEvents;
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

    public AuctionSettings getInterstitialAuctionSettings() {
        return this.mISAuctionSettings;
    }

    public int getISDelayLoadFailure() {
        return this.mISDelayLoadFailureNotificationInSeconds;
    }
}
