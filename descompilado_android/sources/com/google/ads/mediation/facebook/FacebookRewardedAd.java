package com.google.ads.mediation.facebook;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class FacebookRewardedAd implements MediationRewardedAd, RewardedVideoAdListener {
    private MediationRewardedAdConfiguration adConfiguration;
    private boolean isRtbAd = false;
    private MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mMediationAdLoadCallback;
    private MediationRewardedAdCallback mRewardedAdCallback;
    private RewardedVideoAd rewardedAd;

    public FacebookRewardedAd(MediationRewardedAdConfiguration mediationRewardedAdConfiguration, MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback) {
        this.adConfiguration = mediationRewardedAdConfiguration;
        this.mMediationAdLoadCallback = mediationAdLoadCallback;
    }

    public void render() {
        final Context context = this.adConfiguration.getContext();
        Bundle serverParameters = this.adConfiguration.getServerParameters();
        if (!FacebookMediationAdapter.isValidRequestParameters(context, serverParameters)) {
            this.mMediationAdLoadCallback.onFailure("Invalid request");
            return;
        }
        if (!this.adConfiguration.getBidResponse().equals("")) {
            this.isRtbAd = true;
        }
        if (this.isRtbAd) {
            String placementID = FacebookMediationAdapter.getPlacementID(serverParameters);
            if (placementID == null || placementID.isEmpty()) {
                this.mMediationAdLoadCallback.onFailure("FacebookRtbRewardedAd received a null or empty placement ID.");
                return;
            }
            String bidResponse = this.adConfiguration.getBidResponse();
            if (bidResponse.isEmpty()) {
                this.mMediationAdLoadCallback.onFailure("FacebookRtbRewardedAd failed to decode bidresponse as UTF-8.");
                return;
            }
            this.rewardedAd = new RewardedVideoAd(context, placementID);
            this.rewardedAd.setAdListener(this);
            this.rewardedAd.loadAdFromBid(bidResponse);
            return;
        }
        final String placementID2 = FacebookMediationAdapter.getPlacementID(serverParameters);
        FacebookInitializer.getInstance().initialize(context, placementID2, new FacebookInitializer.Listener() { // from class: com.google.ads.mediation.facebook.FacebookRewardedAd.1
            @Override // com.google.ads.mediation.facebook.FacebookInitializer.Listener
            public void onInitializeSuccess() {
                FacebookRewardedAd.this.createAndLoadRewardedVideo(context, placementID2);
            }

            @Override // com.google.ads.mediation.facebook.FacebookInitializer.Listener
            public void onInitializeError(String str) {
                String str2 = "Failed to load ad from Facebook: " + str;
                Log.w(FacebookMediationAdapter.TAG, str2);
                if (FacebookRewardedAd.this.mMediationAdLoadCallback != null) {
                    FacebookRewardedAd.this.mMediationAdLoadCallback.onFailure(str2);
                }
            }
        });
    }

    @Override // com.google.android.gms.ads.mediation.MediationRewardedAd
    public void showAd(Context context) {
        if (this.rewardedAd.isAdLoaded()) {
            this.rewardedAd.show();
            MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
            if (mediationRewardedAdCallback != null) {
                mediationRewardedAdCallback.onVideoStart();
                this.mRewardedAdCallback.onAdOpened();
                return;
            }
            return;
        }
        MediationRewardedAdCallback mediationRewardedAdCallback2 = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback2 != null) {
            mediationRewardedAdCallback2.onAdFailedToShow("No ads to show");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAndLoadRewardedVideo(Context context, String str) {
        this.rewardedAd = new RewardedVideoAd(context, str);
        this.rewardedAd.setAdListener(this);
        this.rewardedAd.loadAd(true);
    }

    @Override // com.facebook.ads.RewardedVideoAdListener
    public void onRewardedVideoCompleted() {
        this.mRewardedAdCallback.onVideoComplete();
        this.mRewardedAdCallback.onUserEarnedReward(new FacebookReward());
    }

    @Override // com.facebook.ads.AdListener
    public void onError(Ad ad, AdError adError) {
        String errorMessage = adError.getErrorMessage();
        if (!TextUtils.isEmpty(errorMessage)) {
            Log.w(FacebookMediationAdapter.TAG, "Failed to load ad from Facebook: " + errorMessage);
        }
        MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback = this.mMediationAdLoadCallback;
        if (mediationAdLoadCallback != null) {
            mediationAdLoadCallback.onFailure(errorMessage);
        }
        this.rewardedAd.destroy();
    }

    @Override // com.facebook.ads.AdListener
    public void onAdLoaded(Ad ad) {
        MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback = this.mMediationAdLoadCallback;
        if (mediationAdLoadCallback != null) {
            this.mRewardedAdCallback = mediationAdLoadCallback.onSuccess(this);
        }
    }

    @Override // com.facebook.ads.AdListener
    public void onAdClicked(Ad ad) {
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback == null || this.isRtbAd) {
            return;
        }
        mediationRewardedAdCallback.reportAdClicked();
    }

    @Override // com.facebook.ads.RewardedVideoAdListener, com.facebook.ads.AdListener
    public void onLoggingImpression(Ad ad) {
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback == null || this.isRtbAd) {
            return;
        }
        mediationRewardedAdCallback.reportAdImpression();
    }

    @Override // com.facebook.ads.RewardedVideoAdListener
    public void onRewardedVideoClosed() {
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onAdClosed();
        }
        this.rewardedAd.destroy();
    }
}
