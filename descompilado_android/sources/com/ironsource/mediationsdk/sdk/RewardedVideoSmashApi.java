package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

/* JADX INFO: loaded from: classes2.dex */
public interface RewardedVideoSmashApi {
    void fetchRewardedVideo();

    void initRewardedVideo(Activity activity, String str, String str2);

    boolean isRewardedVideoAvailable();

    void setRewardedVideoManagerListener(RewardedVideoManagerListener rewardedVideoManagerListener);

    void showRewardedVideo();
}
