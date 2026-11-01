package com.fingersoft.ads_sdk.advertising.providers.Interstitials;

import android.app.Activity;
import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.InterstitialAd;
import com.fingersoft.ads_sdk.advertising.providers.AdProviders;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;

/* JADX INFO: loaded from: classes.dex */
public class AdProviderAmazonInterstitial extends InterstitialProvider implements AdListener {
    private String mApplicationKey;
    private InterstitialAd mInterstitialAd = null;
    private String mProviderName = AdProviders.AMAZON_INTERSTITIAL;

    @Override // com.amazon.device.ads.AdListener
    public void onAdCollapsed(Ad ad) {
    }

    @Override // com.amazon.device.ads.AdListener
    public void onAdExpanded(Ad ad) {
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void onPause() {
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void onResume(Activity activity) {
    }

    public AdProviderAmazonInterstitial(String str, InterstitialListener interstitialListener) {
        this.mApplicationKey = str;
        this.listener = interstitialListener;
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void initialise() {
        AdRegistration.setAppKey(this.mApplicationKey);
        AdUtils.log(getName() + " - Initialising");
        this.mInterstitialAd = new InterstitialAd(this.mInterstitialManager.getActivity());
        this.mInterstitialAd.setListener(this);
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void loadInterstitial() {
        this.mInterstitialAd.loadAd();
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public String getName() {
        return this.mProviderName;
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void show() {
        try {
            AdUtils.log(getName() + " - Showing interstitial");
            if (this.mInterstitialAd.showAd()) {
                if (this.listener != null) {
                    this.listener.onInterstitialShow();
                }
            } else if (this.listener != null) {
                this.listener.onInterstitialFailed();
            }
        } catch (Exception e) {
            AdUtils.log(getName() + " " + e.getMessage());
            this.listener.onInterstitialFailed();
        }
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void setName(String str) {
        this.mProviderName = str;
    }

    @Override // com.amazon.device.ads.AdListener
    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        this.mInterstitialManager.onInterstitialLoaded();
        this.listener.onInterstitialLoaded();
    }

    @Override // com.amazon.device.ads.AdListener
    public void onAdFailedToLoad(Ad ad, AdError adError) {
        AdUtils.log("onAdFailedToLoad(), providerName: " + this.mProviderName + ", errorCode: " + adError.getCode() + ", message " + adError.getMessage() + ", appkey: " + this.mApplicationKey);
        this.mInterstitialManager.onInterstitialViewFailed();
        this.listener.onInterstitialFailed();
    }

    @Override // com.amazon.device.ads.AdListener
    public void onAdDismissed(Ad ad) {
        this.mInterstitialManager.onInterstitialViewSuccess();
        if (this.listener != null) {
            this.listener.onInterstitialDismiss();
        }
    }
}
