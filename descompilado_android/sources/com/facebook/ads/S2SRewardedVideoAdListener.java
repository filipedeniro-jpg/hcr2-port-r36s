package com.facebook.ads;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public interface S2SRewardedVideoAdListener extends RewardedVideoAdListener {
    void onRewardServerFailed();

    void onRewardServerSuccess();
}
