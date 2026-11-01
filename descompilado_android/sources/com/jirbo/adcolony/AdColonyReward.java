package com.jirbo.adcolony;

import com.google.android.gms.ads.reward.RewardItem;

/* JADX INFO: loaded from: classes2.dex */
public class AdColonyReward implements RewardItem {
    private int _amount;
    private String _name;

    public AdColonyReward(String str, int i) {
        this._name = str;
        this._amount = i;
    }

    @Override // com.google.android.gms.ads.reward.RewardItem
    public String getType() {
        return this._name;
    }

    @Override // com.google.android.gms.ads.reward.RewardItem
    public int getAmount() {
        return this._amount;
    }
}
