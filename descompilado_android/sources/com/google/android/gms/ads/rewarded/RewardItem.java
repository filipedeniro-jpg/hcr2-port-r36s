package com.google.android.gms.ads.rewarded;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public interface RewardItem {
    public static final RewardItem DEFAULT_REWARD = new zza();

    int getAmount();

    String getType();
}
