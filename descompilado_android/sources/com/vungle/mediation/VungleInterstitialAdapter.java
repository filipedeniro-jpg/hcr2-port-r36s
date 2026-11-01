package com.vungle.mediation;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.vungle.warren.AdConfig;

/* JADX INFO: loaded from: classes2.dex */
public class VungleInterstitialAdapter implements MediationInterstitialAdapter {
    private static final String mId = "interstitial";
    private static int sCounter;
    private AdConfig mAdConfig;
    private String mAdapterId;
    private MediationInterstitialListener mMediationInterstitialListener;
    private String mPlacementForPlay;
    private final VungleListener mVungleListener = new VungleListener() { // from class: com.vungle.mediation.VungleInterstitialAdapter.1
        @Override // com.vungle.mediation.VungleListener
        public void onAdEnd(String str, boolean z, boolean z2) {
            if (VungleInterstitialAdapter.this.mMediationInterstitialListener != null) {
                if (z2) {
                    VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdClicked(VungleInterstitialAdapter.this);
                    VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdLeftApplication(VungleInterstitialAdapter.this);
                }
                VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdClosed(VungleInterstitialAdapter.this);
            }
        }

        @Override // com.vungle.mediation.VungleListener
        public void onAdStart(String str) {
            if (VungleInterstitialAdapter.this.mMediationInterstitialListener != null) {
                VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdOpened(VungleInterstitialAdapter.this);
            }
        }

        @Override // com.vungle.mediation.VungleListener
        public void onAdAvailable() {
            if (VungleInterstitialAdapter.this.mMediationInterstitialListener != null) {
                VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdLoaded(VungleInterstitialAdapter.this);
            }
        }

        @Override // com.vungle.mediation.VungleListener
        void onAdFailedToLoad() {
            if (VungleInterstitialAdapter.this.mMediationInterstitialListener != null) {
                VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdFailedToLoad(VungleInterstitialAdapter.this, 3);
            }
        }

        @Override // com.vungle.mediation.VungleListener
        void onInitialized(boolean z) {
            if (VungleInterstitialAdapter.this.mMediationInterstitialListener != null) {
                if (!z) {
                    VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdFailedToLoad(VungleInterstitialAdapter.this, 0);
                } else {
                    VungleInterstitialAdapter.this.loadAd();
                }
            }
        }

        @Override // com.vungle.mediation.VungleListener
        void onAdFail(String str) {
            if (!str.equals(VungleInterstitialAdapter.this.mPlacementForPlay) || VungleInterstitialAdapter.this.mMediationInterstitialListener == null) {
                return;
            }
            VungleInterstitialAdapter.this.mMediationInterstitialListener.onAdClosed(VungleInterstitialAdapter.this);
        }
    };
    private VungleManager mVungleManager;

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        try {
            AdapterParametersParser.Config config = AdapterParametersParser.parse(bundle2, bundle);
            this.mMediationInterstitialListener = mediationInterstitialListener;
            this.mVungleManager = VungleManager.getInstance(config.getAppId(), config.getAllPlacements());
            this.mPlacementForPlay = this.mVungleManager.findPlacement(bundle2, bundle);
            this.mAdConfig = VungleExtrasBuilder.adConfigWithNetworkExtras(bundle2);
            this.mAdapterId = "interstitial" + String.valueOf(sCounter);
            sCounter = sCounter + 1;
            this.mVungleManager.addListener(this.mAdapterId, this.mVungleListener);
            if (this.mVungleManager.isInitialized()) {
                loadAd();
            } else {
                this.mVungleListener.setWaitingInit(true);
                this.mVungleManager.init(context);
            }
        } catch (IllegalArgumentException unused) {
            if (mediationInterstitialListener != null) {
                mediationInterstitialListener.onAdFailedToLoad(this, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAd() {
        if (this.mVungleManager.isAdPlayable(this.mPlacementForPlay)) {
            MediationInterstitialListener mediationInterstitialListener = this.mMediationInterstitialListener;
            if (mediationInterstitialListener != null) {
                mediationInterstitialListener.onAdLoaded(this);
                return;
            }
            return;
        }
        this.mVungleListener.waitForAd(this.mPlacementForPlay);
        this.mVungleManager.loadAd(this.mPlacementForPlay);
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        VungleManager vungleManager = this.mVungleManager;
        if (vungleManager != null) {
            vungleManager.playAd(this.mPlacementForPlay, this.mAdConfig, this.mAdapterId);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        VungleManager vungleManager = this.mVungleManager;
        if (vungleManager != null) {
            vungleManager.removeListener(this.mAdapterId);
        }
    }
}
