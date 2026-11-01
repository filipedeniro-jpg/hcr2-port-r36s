package com.google.ads.mediation.nend;

import com.google.android.gms.ads.rewarded.RewardItem;
import net.nend.android.NendAdRewardItem;

/* JADX INFO: loaded from: classes.dex */
class NendMediationRewardItem implements RewardItem {
    private final int mRewardAmount;
    private final String mRewardType;

    NendMediationRewardItem(NendAdRewardItem nendAdRewardItem) {
        this.mRewardType = nendAdRewardItem.getCurrencyName();
        this.mRewardAmount = nendAdRewardItem.getCurrencyAmount();
    }

    @Override // com.google.android.gms.ads.rewarded.RewardItem
    public String getType() {
        return this.mRewardType;
    }

    @Override // com.google.android.gms.ads.rewarded.RewardItem
    public int getAmount() {
        return this.mRewardAmount;
    }
}
