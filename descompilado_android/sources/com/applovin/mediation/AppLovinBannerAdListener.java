package com.applovin.mediation;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewDisplayErrorCode;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.gms.ads.mediation.MediationBannerListener;

/* JADX INFO: loaded from: classes.dex */
class AppLovinBannerAdListener implements AppLovinAdLoadListener, AppLovinAdDisplayListener, AppLovinAdClickListener, AppLovinAdViewEventListener {
    private final AppLovinAdView mAdView;
    private final ApplovinAdapter mAdapter;
    private final MediationBannerListener mMediationBannerListener;
    private final String mZoneId;

    AppLovinBannerAdListener(String str, AppLovinAdView appLovinAdView, ApplovinAdapter applovinAdapter, MediationBannerListener mediationBannerListener) {
        this.mAdapter = applovinAdapter;
        this.mMediationBannerListener = mediationBannerListener;
        this.mAdView = appLovinAdView;
        this.mZoneId = str;
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void adReceived(AppLovinAd appLovinAd) {
        ApplovinAdapter.log(3, "Banner did load ad: " + appLovinAd.getAdIdNumber() + " for zone: " + this.mZoneId);
        this.mAdView.renderAd(appLovinAd);
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.mediation.AppLovinBannerAdListener.1
            @Override // java.lang.Runnable
            public void run() {
                AppLovinBannerAdListener.this.mMediationBannerListener.onAdLoaded(AppLovinBannerAdListener.this.mAdapter);
            }
        });
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void failedToReceiveAd(final int i) {
        ApplovinAdapter.log(6, "Failed to load banner ad with error: " + i);
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.mediation.AppLovinBannerAdListener.2
            @Override // java.lang.Runnable
            public void run() {
                AppLovinBannerAdListener.this.mMediationBannerListener.onAdFailedToLoad(AppLovinBannerAdListener.this.mAdapter, AppLovinUtils.toAdMobErrorCode(i));
            }
        });
    }

    @Override // com.applovin.sdk.AppLovinAdDisplayListener
    public void adDisplayed(AppLovinAd appLovinAd) {
        ApplovinAdapter.log(3, "Banner displayed");
    }

    @Override // com.applovin.sdk.AppLovinAdDisplayListener
    public void adHidden(AppLovinAd appLovinAd) {
        ApplovinAdapter.log(3, "Banner dismissed");
    }

    @Override // com.applovin.sdk.AppLovinAdClickListener
    public void adClicked(AppLovinAd appLovinAd) {
        ApplovinAdapter.log(3, "Banner clicked");
        this.mMediationBannerListener.onAdClicked(this.mAdapter);
    }

    @Override // com.applovin.adview.AppLovinAdViewEventListener
    public void adOpenedFullscreen(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
        ApplovinAdapter.log(3, "Banner opened fullscreen");
        this.mMediationBannerListener.onAdOpened(this.mAdapter);
    }

    @Override // com.applovin.adview.AppLovinAdViewEventListener
    public void adClosedFullscreen(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
        ApplovinAdapter.log(3, "Banner closed fullscreen");
        this.mMediationBannerListener.onAdClosed(this.mAdapter);
    }

    @Override // com.applovin.adview.AppLovinAdViewEventListener
    public void adLeftApplication(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
        ApplovinAdapter.log(3, "Banner left application");
        this.mMediationBannerListener.onAdLeftApplication(this.mAdapter);
    }

    @Override // com.applovin.adview.AppLovinAdViewEventListener
    public void adFailedToDisplay(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinAdViewDisplayErrorCode appLovinAdViewDisplayErrorCode) {
        ApplovinAdapter.log(6, "Banner failed to display: " + appLovinAdViewDisplayErrorCode);
    }
}
