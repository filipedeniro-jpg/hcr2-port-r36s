package com.applovin.mediation.rtb;

import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewDisplayErrorCode;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.mediation.AppLovinUtils;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationBannerAd;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;

/* JADX INFO: loaded from: classes.dex */
public final class AppLovinRtbBannerRenderer implements MediationBannerAd, AppLovinAdLoadListener, AppLovinAdDisplayListener, AppLovinAdClickListener, AppLovinAdViewEventListener {
    private static final String TAG = "AppLovinRtbBannerRenderer";
    private final MediationBannerAdConfiguration adConfiguration;
    private final AppLovinAdSize adSize;
    private AppLovinAdView adView;
    private final MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback> callback;
    private MediationBannerAdCallback mBannerAdCallback;
    private final AppLovinSdk sdk;

    public AppLovinRtbBannerRenderer(MediationBannerAdConfiguration mediationBannerAdConfiguration, MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback> mediationAdLoadCallback) {
        this.adConfiguration = mediationBannerAdConfiguration;
        this.callback = mediationAdLoadCallback;
        this.adSize = AppLovinUtils.appLovinAdSizeFromAdMobAdSize(mediationBannerAdConfiguration.getContext(), mediationBannerAdConfiguration.getAdSize());
        this.sdk = AppLovinUtils.retrieveSdk(mediationBannerAdConfiguration.getServerParameters(), mediationBannerAdConfiguration.getContext());
    }

    public void loadAd() {
        AppLovinAdSize appLovinAdSize = this.adSize;
        if (appLovinAdSize != null) {
            this.adView = new AppLovinAdView(this.sdk, appLovinAdSize, this.adConfiguration.getContext());
            this.adView.setAdDisplayListener(this);
            this.adView.setAdClickListener(this);
            this.adView.setAdViewEventListener(this);
            this.sdk.getAdService().loadNextAdForAdToken(this.adConfiguration.getBidResponse(), this);
            return;
        }
        this.callback.onFailure("Failed to request banner with unsupported size");
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAd
    @NonNull
    public View getView() {
        return this.adView;
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void adReceived(AppLovinAd appLovinAd) {
        Log.d(TAG, "Banner did load ad: " + appLovinAd.getAdIdNumber());
        this.mBannerAdCallback = this.callback.onSuccess(this);
        this.adView.renderAd(appLovinAd);
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void failedToReceiveAd(int i) {
        Log.e(TAG, "Failed to load banner ad with error: " + i);
        this.callback.onFailure(Integer.toString(AppLovinUtils.toAdMobErrorCode(i)));
    }

    @Override // com.applovin.sdk.AppLovinAdDisplayListener
    public void adDisplayed(AppLovinAd appLovinAd) {
        Log.d(TAG, "Banner displayed");
        this.mBannerAdCallback.reportAdImpression();
        this.mBannerAdCallback.onAdOpened();
    }

    @Override // com.applovin.sdk.AppLovinAdDisplayListener
    public void adHidden(AppLovinAd appLovinAd) {
        Log.d(TAG, "Banner hidden");
    }

    @Override // com.applovin.sdk.AppLovinAdClickListener
    public void adClicked(AppLovinAd appLovinAd) {
        Log.d(TAG, "Banner clicked");
        this.mBannerAdCallback.reportAdClicked();
    }

    @Override // com.applovin.adview.AppLovinAdViewEventListener
    public void adOpenedFullscreen(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
        Log.d(TAG, "Banner opened fullscreen");
        this.mBannerAdCallback.onAdOpened();
    }

    @Override // com.applovin.adview.AppLovinAdViewEventListener
    public void adClosedFullscreen(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
        Log.d(TAG, "Banner closed fullscreen");
        this.mBannerAdCallback.onAdClosed();
    }

    @Override // com.applovin.adview.AppLovinAdViewEventListener
    public void adLeftApplication(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
        Log.d(TAG, "Banner left application");
        this.mBannerAdCallback.onAdLeftApplication();
    }

    @Override // com.applovin.adview.AppLovinAdViewEventListener
    public void adFailedToDisplay(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinAdViewDisplayErrorCode appLovinAdViewDisplayErrorCode) {
        Log.e(TAG, "Banner failed to display: " + appLovinAdViewDisplayErrorCode);
    }
}
