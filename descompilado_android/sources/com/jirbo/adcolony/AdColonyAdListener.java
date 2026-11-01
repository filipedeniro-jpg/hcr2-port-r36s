package com.jirbo.adcolony;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyRewardListener;
import com.adcolony.sdk.AdColonyZone;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;

/* JADX INFO: loaded from: classes2.dex */
class AdColonyAdListener extends AdColonyInterstitialListener implements AdColonyRewardListener {
    private AdColonyAdapter _adapter;
    private MediationInterstitialListener _mediationInterstitialListener;
    private MediationRewardedVideoAdListener _mediationRewardedVideoAdListener;
    private boolean _rewarded;
    private RequestState _state;

    enum RequestState {
        REQUESTED,
        FILLED,
        NOT_FILLED,
        CLOSED,
        EXPIRED,
        NONE
    }

    AdColonyAdListener(AdColonyAdapter adColonyAdapter, MediationInterstitialListener mediationInterstitialListener) {
        this._state = RequestState.NONE;
        this._mediationInterstitialListener = mediationInterstitialListener;
        this._adapter = adColonyAdapter;
    }

    AdColonyAdListener(AdColonyAdapter adColonyAdapter, MediationRewardedVideoAdListener mediationRewardedVideoAdListener) {
        this._state = RequestState.NONE;
        this._mediationRewardedVideoAdListener = mediationRewardedVideoAdListener;
        this._adapter = adColonyAdapter;
        this._rewarded = true;
    }

    @Override // com.adcolony.sdk.AdColonyInterstitialListener
    public void onRequestFilled(AdColonyInterstitial adColonyInterstitial) {
        if (this._adapter != null) {
            this._state = RequestState.FILLED;
            this._adapter.setAd(adColonyInterstitial);
            notifyAdLoaded();
        }
    }

    @Override // com.adcolony.sdk.AdColonyInterstitialListener
    public void onClicked(AdColonyInterstitial adColonyInterstitial) {
        AdColonyAdapter adColonyAdapter = this._adapter;
        if (adColonyAdapter != null) {
            adColonyAdapter.setAd(adColonyInterstitial);
            if (this._rewarded) {
                this._mediationRewardedVideoAdListener.onAdClicked(this._adapter);
            } else {
                this._mediationInterstitialListener.onAdClicked(this._adapter);
            }
        }
    }

    @Override // com.adcolony.sdk.AdColonyInterstitialListener
    public void onClosed(AdColonyInterstitial adColonyInterstitial) {
        if (this._adapter != null) {
            this._state = RequestState.CLOSED;
            this._adapter.setAd(adColonyInterstitial);
            if (this._rewarded) {
                this._mediationRewardedVideoAdListener.onAdClosed(this._adapter);
            } else {
                this._mediationInterstitialListener.onAdClosed(this._adapter);
            }
        }
    }

    @Override // com.adcolony.sdk.AdColonyInterstitialListener
    public void onExpiring(AdColonyInterstitial adColonyInterstitial) {
        if (this._adapter != null) {
            this._state = RequestState.EXPIRED;
            this._adapter.setAd(adColonyInterstitial);
            AdColony.requestInterstitial(adColonyInterstitial.getZoneID(), this);
        }
    }

    @Override // com.adcolony.sdk.AdColonyInterstitialListener
    public void onIAPEvent(AdColonyInterstitial adColonyInterstitial, String str, int i) {
        AdColonyAdapter adColonyAdapter = this._adapter;
        if (adColonyAdapter != null) {
            adColonyAdapter.setAd(adColonyInterstitial);
        }
    }

    @Override // com.adcolony.sdk.AdColonyInterstitialListener
    public void onLeftApplication(AdColonyInterstitial adColonyInterstitial) {
        AdColonyAdapter adColonyAdapter = this._adapter;
        if (adColonyAdapter != null) {
            adColonyAdapter.setAd(adColonyInterstitial);
            if (this._rewarded) {
                this._mediationRewardedVideoAdListener.onAdLeftApplication(this._adapter);
            } else {
                this._mediationInterstitialListener.onAdLeftApplication(this._adapter);
            }
        }
    }

    @Override // com.adcolony.sdk.AdColonyInterstitialListener
    public void onOpened(AdColonyInterstitial adColonyInterstitial) {
        AdColonyAdapter adColonyAdapter = this._adapter;
        if (adColonyAdapter != null) {
            adColonyAdapter.setAd(adColonyInterstitial);
            if (this._rewarded) {
                this._mediationRewardedVideoAdListener.onAdOpened(this._adapter);
                this._mediationRewardedVideoAdListener.onVideoStarted(this._adapter);
            } else {
                this._mediationInterstitialListener.onAdOpened(this._adapter);
            }
        }
    }

    @Override // com.adcolony.sdk.AdColonyInterstitialListener
    public void onRequestNotFilled(AdColonyZone adColonyZone) {
        if (this._adapter != null) {
            this._state = RequestState.NOT_FILLED;
            this._adapter.setAd(null);
            if (this._rewarded) {
                AdColony.removeRewardListener();
                this._mediationRewardedVideoAdListener.onAdFailedToLoad(this._adapter, 3);
            } else {
                this._mediationInterstitialListener.onAdFailedToLoad(this._adapter, 3);
            }
        }
    }

    @Override // com.adcolony.sdk.AdColonyRewardListener
    public void onReward(com.adcolony.sdk.AdColonyReward adColonyReward) {
        if (this._adapter == null || !adColonyReward.success()) {
            return;
        }
        this._mediationRewardedVideoAdListener.onRewarded(this._adapter, new AdColonyReward(adColonyReward.getRewardName(), adColonyReward.getRewardAmount()));
    }

    void destroy() {
        this._adapter = null;
        this._mediationInterstitialListener = null;
        this._mediationRewardedVideoAdListener = null;
    }

    void onRequest() {
        this._state = RequestState.REQUESTED;
    }

    RequestState getState() {
        return this._state;
    }

    void notifyAdLoaded() {
        if (this._rewarded) {
            this._mediationRewardedVideoAdListener.onAdLoaded(this._adapter);
        } else {
            this._mediationInterstitialListener.onAdLoaded(this._adapter);
        }
    }
}
