package com.google.ads.mediation.unity;

import com.google.android.gms.ads.reward.RewardItem;

/* JADX INFO: loaded from: classes.dex */
public class UnityReward implements RewardItem {
    @Override // com.google.android.gms.ads.reward.RewardItem
    public int getAmount() {
        return 1;
    }

    @Override // com.google.android.gms.ads.reward.RewardItem
    public String getType() {
        return "";
    }
}
