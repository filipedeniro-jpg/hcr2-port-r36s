package com.jirbo.adcolony;

import android.content.Context;
import android.os.Bundle;
import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;

/* JADX INFO: loaded from: classes2.dex */
public class AdColonyAdapter implements MediationInterstitialAdapter, MediationRewardedVideoAdAdapter {
    private AdColonyInterstitial _adColonyInterstitial;
    private AdColonyAdListener _adColonyInterstitialListener;
    private AdColonyAdListener _adColonyRewardedInterstitialListener;
    private MediationRewardedVideoAdListener _mediationRewardedVideoAdListener;

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
        AdColonyManager.getInstance().onDestroy();
        AdColonyInterstitial adColonyInterstitial = this._adColonyInterstitial;
        if (adColonyInterstitial != null) {
            adColonyInterstitial.cancel();
            this._adColonyInterstitial.destroy();
        }
        AdColonyAdListener adColonyAdListener = this._adColonyInterstitialListener;
        if (adColonyAdListener != null) {
            adColonyAdListener.destroy();
        }
        AdColonyAdListener adColonyAdListener2 = this._adColonyRewardedInterstitialListener;
        if (adColonyAdListener2 != null) {
            adColonyAdListener2.destroy();
            AdColony.removeRewardListener();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this._adColonyInterstitialListener = new AdColonyAdListener(this, mediationInterstitialListener);
        boolean zConfigureAdColony = AdColonyManager.getInstance().configureAdColony(context, bundle, mediationAdRequest, bundle2);
        if (zConfigureAdColony) {
            String zoneFromRequest = AdColonyManager.getInstance().getZoneFromRequest(AdColonyManager.getInstance().parseZoneList(bundle), bundle2);
            if (zoneFromRequest != null) {
                AdColony.requestInterstitial(zoneFromRequest, this._adColonyInterstitialListener);
            } else {
                zConfigureAdColony = false;
            }
        }
        if (zConfigureAdColony) {
            return;
        }
        mediationInterstitialListener.onAdFailedToLoad(this, 1);
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        showAdColonyInterstitial();
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        this._mediationRewardedVideoAdListener = mediationRewardedVideoAdListener;
        this._adColonyRewardedInterstitialListener = new AdColonyAdListener(this, mediationRewardedVideoAdListener);
        if (AdColonyManager.getInstance().configureAdColony(context, bundle, mediationAdRequest, bundle2)) {
            AdColonyManager.getInstance().rewardedAdsConfigured = true;
            this._mediationRewardedVideoAdListener.onInitializationSucceeded(this);
        } else {
            this._mediationRewardedVideoAdListener.onInitializationFailed(this, 1);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        boolean z;
        boolean z2;
        String zoneFromRequest = AdColonyManager.getInstance().getZoneFromRequest(AdColonyManager.getInstance().parseZoneList(bundle), bundle2);
        if (this._adColonyRewardedInterstitialListener.getState() == AdColonyAdListener.RequestState.FILLED) {
            this._adColonyRewardedInterstitialListener.notifyAdLoaded();
            return;
        }
        if (this._adColonyRewardedInterstitialListener.getState() != AdColonyAdListener.RequestState.REQUESTED) {
            boolean zConfigureAdColony = AdColonyManager.getInstance().configureAdColony(null, bundle, mediationAdRequest, bundle2);
            if (bundle2 != null) {
                z2 = bundle2.getBoolean("show_pre_popup", false);
                z = bundle2.getBoolean("show_post_popup", false);
            } else {
                z = false;
                z2 = false;
            }
            if (this._adColonyRewardedInterstitialListener == null) {
                this._adColonyRewardedInterstitialListener = new AdColonyAdListener(this, this._mediationRewardedVideoAdListener);
            }
            if (AdColony.getRewardListener() == null) {
                AdColony.setRewardListener(this._adColonyRewardedInterstitialListener);
            }
            if (zoneFromRequest != null) {
                AdColonyAdOptions adColonyAdOptionsEnableResultsDialog = new AdColonyAdOptions().enableConfirmationDialog(z2).enableResultsDialog(z);
                this._adColonyRewardedInterstitialListener.onRequest();
                AdColony.requestInterstitial(zoneFromRequest, this._adColonyRewardedInterstitialListener, adColonyAdOptionsEnableResultsDialog);
            } else {
                zConfigureAdColony = false;
            }
            if (zConfigureAdColony) {
                return;
            }
            this._mediationRewardedVideoAdListener.onAdFailedToLoad(this, 1);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void showVideo() {
        showAdColonyInterstitial();
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public boolean isInitialized() {
        return AdColonyManager.getInstance().rewardedAdsConfigured;
    }

    private void showAdColonyInterstitial() {
        AdColonyInterstitial adColonyInterstitial = this._adColonyInterstitial;
        if (adColonyInterstitial != null) {
            adColonyInterstitial.show();
        }
    }

    void setAd(AdColonyInterstitial adColonyInterstitial) {
        this._adColonyInterstitial = adColonyInterstitial;
    }
}
