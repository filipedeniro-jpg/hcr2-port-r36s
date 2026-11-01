package com.google.ads.mediation.mytarget;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.VersionInfo;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.my.target.ads.InterstitialAd;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MyTargetMediationAdapter extends Adapter implements MediationRewardedAd, InterstitialAd.InterstitialAdListener {
    static final String TAG = "MyTargetMediationAdapter";
    private MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mAdLoadCallback;
    private InterstitialAd mRewardedAd;
    private MediationRewardedAdCallback mRewardedAdCallback;

    @Override // com.google.android.gms.ads.mediation.Adapter
    public VersionInfo getVersionInfo() {
        String[] strArrSplit = BuildConfig.VERSION_NAME.split("\\.");
        return new VersionInfo(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), (Integer.parseInt(strArrSplit[2]) * 100) + Integer.parseInt(strArrSplit[3]));
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public VersionInfo getSDKVersionInfo() {
        String[] strArrSplit = "5.3.9".split("\\.");
        return new VersionInfo(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]));
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void initialize(Context context, InitializationCompleteCallback initializationCompleteCallback, List<MediationConfiguration> list) {
        initializationCompleteCallback.onInitializationSucceeded();
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void loadRewardedAd(MediationRewardedAdConfiguration mediationRewardedAdConfiguration, MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback) {
        Context context = mediationRewardedAdConfiguration.getContext();
        int iCheckAndGetSlotId = MyTargetTools.checkAndGetSlotId(context, mediationRewardedAdConfiguration.getServerParameters());
        Log.d(TAG, "Requesting rewarded mediation, slotID: " + iCheckAndGetSlotId);
        if (iCheckAndGetSlotId < 0) {
            mediationAdLoadCallback.onFailure("Failed to request ad from MyTarget: Internal Error.");
            return;
        }
        this.mAdLoadCallback = mediationAdLoadCallback;
        this.mRewardedAd = new InterstitialAd(iCheckAndGetSlotId, context);
        this.mRewardedAd.getCustomParams().setCustomParam("mediation", "1");
        this.mRewardedAd.setListener(this);
        this.mRewardedAd.load();
    }

    @Override // com.google.android.gms.ads.mediation.MediationRewardedAd
    public void showAd(Context context) {
        Log.d(TAG, "Show video");
        InterstitialAd interstitialAd = this.mRewardedAd;
        if (interstitialAd != null) {
            interstitialAd.show();
            return;
        }
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onAdFailedToShow("Rewarded Video is null.");
        }
    }

    @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
    public void onLoad(@NonNull InterstitialAd interstitialAd) {
        Log.d(TAG, "Ad loaded");
        MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback = this.mAdLoadCallback;
        if (mediationAdLoadCallback != null) {
            this.mRewardedAdCallback = mediationAdLoadCallback.onSuccess(this);
        }
    }

    @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
    public void onNoAd(@NonNull String str, @NonNull InterstitialAd interstitialAd) {
        String str2 = "Failed to load ad from MyTarget: " + str;
        Log.i(TAG, str2);
        MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback = this.mAdLoadCallback;
        if (mediationAdLoadCallback != null) {
            mediationAdLoadCallback.onFailure(str2);
        }
    }

    @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
    public void onClick(@NonNull InterstitialAd interstitialAd) {
        Log.d(TAG, "Ad clicked");
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.reportAdClicked();
        }
    }

    @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
    public void onDismiss(@NonNull InterstitialAd interstitialAd) {
        Log.d(TAG, "Ad dismissed");
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onAdClosed();
        }
    }

    @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
    public void onVideoCompleted(@NonNull InterstitialAd interstitialAd) {
        Log.d(TAG, "Video completed");
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onVideoComplete();
            this.mRewardedAdCallback.onUserEarnedReward(new MyTargetReward());
        }
    }

    @Override // com.my.target.ads.InterstitialAd.InterstitialAdListener
    public void onDisplay(@NonNull InterstitialAd interstitialAd) {
        Log.d(TAG, "Ad displayed");
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onAdOpened();
            this.mRewardedAdCallback.onVideoStart();
            this.mRewardedAdCallback.reportAdImpression();
        }
    }

    private static class MyTargetReward implements RewardItem {
        @Override // com.google.android.gms.ads.rewarded.RewardItem
        public int getAmount() {
            return 1;
        }

        @Override // com.google.android.gms.ads.rewarded.RewardItem
        public String getType() {
            return "";
        }

        private MyTargetReward() {
        }
    }
}
