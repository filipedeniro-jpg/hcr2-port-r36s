package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.rewarded.RewardedAdCallback;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaqx extends zzaqk {
    private final RewardedAdCallback zzdns;

    public zzaqx(RewardedAdCallback rewardedAdCallback) {
        this.zzdns = rewardedAdCallback;
    }

    @Override // com.google.android.gms.internal.ads.zzaql
    public final void onRewardedAdOpened() {
        RewardedAdCallback rewardedAdCallback = this.zzdns;
        if (rewardedAdCallback != null) {
            rewardedAdCallback.onRewardedAdOpened();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaql
    public final void onRewardedAdClosed() {
        RewardedAdCallback rewardedAdCallback = this.zzdns;
        if (rewardedAdCallback != null) {
            rewardedAdCallback.onRewardedAdClosed();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaql
    public final void zza(zzaqf zzaqfVar) {
        RewardedAdCallback rewardedAdCallback = this.zzdns;
        if (rewardedAdCallback != null) {
            rewardedAdCallback.onUserEarnedReward(new zzaqu(zzaqfVar));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaql
    public final void onRewardedAdFailedToShow(int i) {
        RewardedAdCallback rewardedAdCallback = this.zzdns;
        if (rewardedAdCallback != null) {
            rewardedAdCallback.onRewardedAdFailedToShow(i);
        }
    }
}
