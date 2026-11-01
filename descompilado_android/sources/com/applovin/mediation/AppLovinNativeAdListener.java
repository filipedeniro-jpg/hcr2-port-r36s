package com.applovin.mediation;

import android.content.Context;
import android.util.Log;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class AppLovinNativeAdListener implements AppLovinNativeAdLoadListener, AppLovinNativeAdPrecacheListener {
    private final AppLovinNativeAdapter mAdapter;
    private final WeakReference<Context> mContextWeakReference;
    private final MediationNativeListener mNativeListener;
    private final AppLovinSdk mSdk;

    @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
    public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
    public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd) {
    }

    AppLovinNativeAdListener(AppLovinNativeAdapter appLovinNativeAdapter, MediationNativeListener mediationNativeListener, AppLovinSdk appLovinSdk, Context context) {
        this.mAdapter = appLovinNativeAdapter;
        this.mNativeListener = mediationNativeListener;
        this.mSdk = appLovinSdk;
        this.mContextWeakReference = new WeakReference<>(context);
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
        if (list.size() > 0 && isValidNativeAd(list.get(0))) {
            this.mSdk.getNativeAdService().precacheResources(list.get(0), this);
        } else {
            Log.e(AppLovinNativeAdapter.TAG, "Ad from AppLovin doesn't have all assets required for the app install ad format");
            notifyAdFailure(3);
        }
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
    public void onNativeAdsFailedToLoad(int i) {
        Log.e(AppLovinNativeAdapter.TAG, "Native ad failed to load " + i);
        notifyAdFailure(AppLovinUtils.toAdMobErrorCode(i));
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
    public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd) {
        Context context = this.mContextWeakReference.get();
        if (context == null) {
            Log.e(AppLovinNativeAdapter.TAG, "Failed to create mapper. Context is null.");
            notifyAdFailure(0);
        } else {
            final AppLovinNativeAdMapper appLovinNativeAdMapper = new AppLovinNativeAdMapper(appLovinNativeAd, context);
            Log.d(AppLovinNativeAdapter.TAG, "Native ad loaded.");
            AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.mediation.AppLovinNativeAdListener.1
                @Override // java.lang.Runnable
                public void run() {
                    AppLovinNativeAdListener.this.mNativeListener.onAdLoaded(AppLovinNativeAdListener.this.mAdapter, appLovinNativeAdMapper);
                }
            });
        }
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
    public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd, int i) {
        Log.e(AppLovinNativeAdapter.TAG, "Native ad failed to pre cache images " + i);
        notifyAdFailure(AppLovinUtils.toAdMobErrorCode(i));
    }

    private void notifyAdFailure(final int i) {
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.mediation.AppLovinNativeAdListener.2
            @Override // java.lang.Runnable
            public void run() {
                AppLovinNativeAdListener.this.mNativeListener.onAdFailedToLoad(AppLovinNativeAdListener.this.mAdapter, i);
            }
        });
    }

    private static boolean isValidNativeAd(AppLovinNativeAd appLovinNativeAd) {
        return (appLovinNativeAd.getImageUrl() == null || appLovinNativeAd.getIconUrl() == null || appLovinNativeAd.getTitle() == null || appLovinNativeAd.getDescriptionText() == null || appLovinNativeAd.getCtaText() == null) ? false : true;
    }
}
