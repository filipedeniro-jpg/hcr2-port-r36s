package com.vungle.mediation;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import com.vungle.warren.AdConfig;

/* JADX INFO: loaded from: classes2.dex */
public class VungleAdapter implements MediationRewardedVideoAdAdapter {
    private static final String mId = "rewardBased";
    private AdConfig mAdConfig;
    private boolean mInitialized;
    private MediationRewardedVideoAdListener mMediationRewardedVideoAdListener;
    private String mPlacementForPlay;
    private final VungleListener mVungleListener = new VungleListener() { // from class: com.vungle.mediation.VungleAdapter.1
        @Override // com.vungle.mediation.VungleListener
        public void onAdEnd(String str, boolean z, boolean z2) {
            if (VungleAdapter.this.mMediationRewardedVideoAdListener != null) {
                if (z) {
                    VungleAdapter.this.mMediationRewardedVideoAdListener.onVideoCompleted(VungleAdapter.this);
                    MediationRewardedVideoAdListener mediationRewardedVideoAdListener = VungleAdapter.this.mMediationRewardedVideoAdListener;
                    VungleAdapter vungleAdapter = VungleAdapter.this;
                    mediationRewardedVideoAdListener.onRewarded(vungleAdapter, vungleAdapter.new VungleReward("vungle", 1));
                }
                if (z2) {
                    VungleAdapter.this.mMediationRewardedVideoAdListener.onAdClicked(VungleAdapter.this);
                    VungleAdapter.this.mMediationRewardedVideoAdListener.onAdLeftApplication(VungleAdapter.this);
                }
                VungleAdapter.this.mMediationRewardedVideoAdListener.onAdClosed(VungleAdapter.this);
            }
        }

        @Override // com.vungle.mediation.VungleListener
        public void onAdStart(String str) {
            if (VungleAdapter.this.mMediationRewardedVideoAdListener != null) {
                VungleAdapter.this.mMediationRewardedVideoAdListener.onAdOpened(VungleAdapter.this);
                VungleAdapter.this.mMediationRewardedVideoAdListener.onVideoStarted(VungleAdapter.this);
            }
        }

        @Override // com.vungle.mediation.VungleListener
        public void onAdAvailable() {
            if (VungleAdapter.this.mMediationRewardedVideoAdListener != null) {
                VungleAdapter.this.mMediationRewardedVideoAdListener.onAdLoaded(VungleAdapter.this);
            }
        }

        @Override // com.vungle.mediation.VungleListener
        void onAdFailedToLoad() {
            if (VungleAdapter.this.mMediationRewardedVideoAdListener != null) {
                VungleAdapter.this.mMediationRewardedVideoAdListener.onAdFailedToLoad(VungleAdapter.this, 3);
            }
        }

        @Override // com.vungle.mediation.VungleListener
        void onInitialized(boolean z) {
            if (VungleAdapter.this.mMediationRewardedVideoAdListener != null) {
                if (!z) {
                    VungleAdapter.this.mInitialized = false;
                    VungleAdapter.this.mMediationRewardedVideoAdListener.onInitializationFailed(VungleAdapter.this, 0);
                } else {
                    VungleAdapter.this.mInitialized = true;
                    VungleAdapter.this.mMediationRewardedVideoAdListener.onInitializationSucceeded(VungleAdapter.this);
                }
            }
        }

        @Override // com.vungle.mediation.VungleListener
        void onAdFail(String str) {
            if (!str.equals(VungleAdapter.this.mPlacementForPlay) || VungleAdapter.this.mMediationRewardedVideoAdListener == null) {
                return;
            }
            VungleAdapter.this.mMediationRewardedVideoAdListener.onAdClosed(VungleAdapter.this);
        }
    };
    private VungleManager mVungleManager;

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
    }

    private class VungleReward implements RewardItem {
        private final int mAmount;
        private final String mType;

        VungleReward(String str, int i) {
            this.mType = str;
            this.mAmount = i;
        }

        @Override // com.google.android.gms.ads.reward.RewardItem
        public int getAmount() {
            return this.mAmount;
        }

        @Override // com.google.android.gms.ads.reward.RewardItem
        public String getType() {
            return this.mType;
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        VungleManager vungleManager = this.mVungleManager;
        if (vungleManager != null) {
            vungleManager.removeListener(mId);
        }
        this.mInitialized = false;
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        try {
            AdapterParametersParser.Config config = AdapterParametersParser.parse(bundle2, bundle);
            this.mMediationRewardedVideoAdListener = mediationRewardedVideoAdListener;
            this.mVungleManager = VungleManager.getInstance(config.getAppId(), config.getAllPlacements());
            this.mVungleManager.addListener(mId, this.mVungleListener);
            if (this.mVungleManager.isInitialized()) {
                this.mInitialized = true;
                this.mMediationRewardedVideoAdListener.onInitializationSucceeded(this);
            } else {
                this.mVungleListener.setWaitingInit(true);
                this.mVungleManager.init(context);
            }
        } catch (IllegalArgumentException unused) {
            if (mediationRewardedVideoAdListener != null) {
                mediationRewardedVideoAdListener.onInitializationFailed(this, 1);
            }
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public boolean isInitialized() {
        return this.mInitialized;
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        this.mVungleManager.setIncentivizedFields(bundle2 != null ? bundle2.getString(ServerResponseWrapper.USER_ID_FIELD) : "", null, null, null, null);
        this.mAdConfig = VungleExtrasBuilder.adConfigWithNetworkExtras(bundle2);
        this.mPlacementForPlay = this.mVungleManager.findPlacement(bundle2, bundle);
        if (this.mVungleManager.isAdPlayable(this.mPlacementForPlay)) {
            MediationRewardedVideoAdListener mediationRewardedVideoAdListener = this.mMediationRewardedVideoAdListener;
            if (mediationRewardedVideoAdListener != null) {
                mediationRewardedVideoAdListener.onAdLoaded(this);
                return;
            }
            return;
        }
        this.mVungleListener.waitForAd(this.mPlacementForPlay);
        this.mVungleManager.loadAd(this.mPlacementForPlay);
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void showVideo() {
        this.mVungleManager.playAd(this.mPlacementForPlay, this.mAdConfig, mId);
    }
}
