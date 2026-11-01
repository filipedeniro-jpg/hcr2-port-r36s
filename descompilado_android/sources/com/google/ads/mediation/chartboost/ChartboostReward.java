package com.google.ads.mediation.chartboost;

import com.google.android.gms.ads.rewarded.RewardItem;

/* JADX INFO: loaded from: classes.dex */
public class ChartboostReward implements RewardItem {
    private final int mAmount;

    @Override // com.google.android.gms.ads.rewarded.RewardItem
    public String getType() {
        return "";
    }

    public ChartboostReward(int i) {
        this.mAmount = i;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardItem
    public int getAmount() {
        return this.mAmount;
    }
}
