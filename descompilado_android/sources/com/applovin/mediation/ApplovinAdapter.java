package com.applovin.mediation;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.ads.mediation.applovin.AppLovinMediationAdapter;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public class ApplovinAdapter extends AppLovinMediationAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, OnContextChangedListener, MediationRewardedAd {
    private static final int BANNER_HEIGHT_OFFSET_TOLERANCE = 10;
    private static final int BANNER_STANDARD_HEIGHT = 50;
    private static final HashMap<String, Queue<AppLovinAd>> INTERSTITIAL_AD_QUEUES = new HashMap<>();
    private static final Object INTERSTITIAL_AD_QUEUES_LOCK = new Object();
    private static final boolean LOGGING_ENABLED = true;
    private AppLovinAdView mAdView;
    private Context mContext;
    private MediationInterstitialListener mMediationInterstitialListener;
    private Bundle mNetworkExtras;
    private String mPlacement;
    private AppLovinSdk mSdk;
    private String mZoneId;

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.mSdk = AppLovinUtils.retrieveSdk(bundle, context);
        this.mContext = context;
        this.mNetworkExtras = bundle2;
        this.mMediationInterstitialListener = mediationInterstitialListener;
        this.mPlacement = AppLovinUtils.retrievePlacement(bundle);
        this.mZoneId = AppLovinUtils.retrieveZoneId(bundle);
        log(3, "Requesting interstitial for zone: " + this.mZoneId + " and placement: " + this.mPlacement);
        AppLovinAdLoadListener appLovinAdLoadListener = new AppLovinAdLoadListener() { // from class: com.applovin.mediation.ApplovinAdapter.1
            @Override // com.applovin.sdk.AppLovinAdLoadListener
            public void adReceived(AppLovinAd appLovinAd) {
                ApplovinAdapter.log(3, "Interstitial did load ad: " + appLovinAd.getAdIdNumber() + " for zone: " + ApplovinAdapter.this.mZoneId + " and placement: " + ApplovinAdapter.this.mPlacement);
                synchronized (ApplovinAdapter.INTERSTITIAL_AD_QUEUES_LOCK) {
                    Queue linkedList = (Queue) ApplovinAdapter.INTERSTITIAL_AD_QUEUES.get(ApplovinAdapter.this.mZoneId);
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                        ApplovinAdapter.INTERSTITIAL_AD_QUEUES.put(ApplovinAdapter.this.mZoneId, linkedList);
                    }
                    linkedList.offer(appLovinAd);
                    AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.mediation.ApplovinAdapter.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ApplovinAdapter.this.mMediationInterstitialListener.onAdLoaded(ApplovinAdapter.this);
                        }
                    });
                }
            }

            @Override // com.applovin.sdk.AppLovinAdLoadListener
            public void failedToReceiveAd(final int i) {
                ApplovinAdapter.log(6, "Interstitial failed to load with error: " + i);
                AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.mediation.ApplovinAdapter.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ApplovinAdapter.this.mMediationInterstitialListener.onAdFailedToLoad(ApplovinAdapter.this, AppLovinUtils.toAdMobErrorCode(i));
                    }
                });
            }
        };
        synchronized (INTERSTITIAL_AD_QUEUES_LOCK) {
            Queue<AppLovinAd> queue = INTERSTITIAL_AD_QUEUES.get(this.mZoneId);
            if (queue == null || (queue != null && queue.isEmpty())) {
                if (!TextUtils.isEmpty(this.mZoneId)) {
                    this.mSdk.getAdService().loadNextAdForZoneId(this.mZoneId, appLovinAdLoadListener);
                } else {
                    this.mSdk.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, appLovinAdLoadListener);
                }
            } else {
                log(3, "Enqueued interstitial found. Finishing load...");
                AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.mediation.ApplovinAdapter.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ApplovinAdapter.this.mMediationInterstitialListener.onAdLoaded(ApplovinAdapter.this);
                    }
                });
            }
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        synchronized (INTERSTITIAL_AD_QUEUES_LOCK) {
            this.mSdk.getSettings().setMuted(AppLovinUtils.shouldMuteAudio(this.mNetworkExtras));
            Queue<AppLovinAd> queue = INTERSTITIAL_AD_QUEUES.get(this.mZoneId);
            AppLovinAd appLovinAdPoll = queue != null ? queue.poll() : null;
            AppLovinInterstitialAdDialog appLovinInterstitialAdDialogCreate = AppLovinInterstitialAd.create(this.mSdk, this.mContext);
            AppLovinInterstitialAdListener appLovinInterstitialAdListener = new AppLovinInterstitialAdListener(this, this.mMediationInterstitialListener);
            appLovinInterstitialAdDialogCreate.setAdDisplayListener(appLovinInterstitialAdListener);
            appLovinInterstitialAdDialogCreate.setAdClickListener(appLovinInterstitialAdListener);
            appLovinInterstitialAdDialogCreate.setAdVideoPlaybackListener(appLovinInterstitialAdListener);
            if (appLovinAdPoll != null) {
                log(3, "Showing interstitial for zone: " + this.mZoneId + " placement: " + this.mPlacement);
                appLovinInterstitialAdDialogCreate.showAndRender(appLovinAdPoll, this.mPlacement);
            } else {
                log(3, "Attempting to show interstitial before one was loaded");
                if (TextUtils.isEmpty(this.mZoneId) && appLovinInterstitialAdDialogCreate.isAdReadyToDisplay()) {
                    log(3, "Showing interstitial preloaded by SDK");
                    appLovinInterstitialAdDialogCreate.show(this.mPlacement);
                } else {
                    this.mMediationInterstitialListener.onAdOpened(this);
                    this.mMediationInterstitialListener.onAdClosed(this);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(Context context, final MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.mSdk = AppLovinUtils.retrieveSdk(bundle, context);
        this.mPlacement = AppLovinUtils.retrievePlacement(bundle);
        this.mZoneId = AppLovinUtils.retrieveZoneId(bundle);
        log(3, "Requesting banner of size " + adSize + " for zone: " + this.mZoneId + " and placement: " + this.mPlacement);
        AppLovinAdSize appLovinAdSizeAppLovinAdSizeFromAdMobAdSize = AppLovinUtils.appLovinAdSizeFromAdMobAdSize(context, adSize);
        if (appLovinAdSizeAppLovinAdSizeFromAdMobAdSize != null) {
            this.mAdView = new AppLovinAdView(this.mSdk, appLovinAdSizeAppLovinAdSizeFromAdMobAdSize, context);
            AppLovinBannerAdListener appLovinBannerAdListener = new AppLovinBannerAdListener(this.mZoneId, this.mAdView, this, mediationBannerListener);
            this.mAdView.setAdDisplayListener(appLovinBannerAdListener);
            this.mAdView.setAdClickListener(appLovinBannerAdListener);
            this.mAdView.setAdViewEventListener(appLovinBannerAdListener);
            if (!TextUtils.isEmpty(this.mZoneId)) {
                this.mSdk.getAdService().loadNextAdForZoneId(this.mZoneId, appLovinBannerAdListener);
                return;
            } else {
                this.mSdk.getAdService().loadNextAd(appLovinAdSizeAppLovinAdSizeFromAdMobAdSize, appLovinBannerAdListener);
                return;
            }
        }
        log(6, "Failed to request banner with unsupported size");
        if (mediationBannerListener != null) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.mediation.ApplovinAdapter.3
                @Override // java.lang.Runnable
                public void run() {
                    mediationBannerListener.onAdFailedToLoad(ApplovinAdapter.this, 1);
                }
            });
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.mAdView;
    }

    @Override // com.google.android.gms.ads.mediation.OnContextChangedListener
    public void onContextChanged(Context context) {
        if (context != null) {
            log(3, "Context changed: " + context);
            this.mContext = context;
        }
    }

    public static void log(int i, String str) {
        Log.println(i, "AppLovinAdapter", str);
    }
}
