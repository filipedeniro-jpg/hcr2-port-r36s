package com.google.ads.mediation.chartboost;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Keep;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Model.CBError;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class ChartboostAdapter extends ChartboostMediationAdapter implements MediationInterstitialAdapter {
    protected static final String TAG = "ChartboostAdapter";
    private boolean mIsLoading;
    private MediationInterstitialListener mMediationInterstitialListener;
    private ChartboostParams mChartboostParams = new ChartboostParams();
    private AbstractChartboostAdapterDelegate mChartboostInterstitialDelegate = new AbstractChartboostAdapterDelegate() { // from class: com.google.ads.mediation.chartboost.ChartboostAdapter.1
        @Override // com.google.ads.mediation.chartboost.AbstractChartboostAdapterDelegate
        public ChartboostParams getChartboostParams() {
            return ChartboostAdapter.this.mChartboostParams;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didInitialize() {
            super.didInitialize();
            ChartboostAdapter.this.mIsLoading = true;
            ChartboostSingleton.loadInterstitialAd(ChartboostAdapter.this.mChartboostInterstitialDelegate);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCacheInterstitial(String str) {
            super.didCacheInterstitial(str);
            if (ChartboostAdapter.this.mMediationInterstitialListener != null && ChartboostAdapter.this.mIsLoading && str.equals(ChartboostAdapter.this.mChartboostParams.getLocation())) {
                ChartboostAdapter.this.mIsLoading = false;
                ChartboostAdapter.this.mMediationInterstitialListener.onAdLoaded(ChartboostAdapter.this);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didFailToLoadInterstitial(String str, CBError.CBImpressionError cBImpressionError) {
            super.didFailToLoadInterstitial(str, cBImpressionError);
            if (ChartboostAdapter.this.mMediationInterstitialListener == null || !str.equals(ChartboostAdapter.this.mChartboostParams.getLocation())) {
                return;
            }
            if (ChartboostAdapter.this.mIsLoading) {
                ChartboostAdapter.this.mIsLoading = false;
                ChartboostAdapter.this.mMediationInterstitialListener.onAdFailedToLoad(ChartboostAdapter.this, ChartboostAdapterUtils.getAdRequestErrorType(cBImpressionError));
            } else if (cBImpressionError == CBError.CBImpressionError.INTERNET_UNAVAILABLE_AT_SHOW) {
                ChartboostAdapter.this.mMediationInterstitialListener.onAdOpened(ChartboostAdapter.this);
                ChartboostAdapter.this.mMediationInterstitialListener.onAdClosed(ChartboostAdapter.this);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDismissInterstitial(String str) {
            super.didDismissInterstitial(str);
            if (ChartboostAdapter.this.mMediationInterstitialListener != null) {
                ChartboostAdapter.this.mMediationInterstitialListener.onAdClosed(ChartboostAdapter.this);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didClickInterstitial(String str) {
            super.didClickInterstitial(str);
            if (ChartboostAdapter.this.mMediationInterstitialListener != null) {
                ChartboostAdapter.this.mMediationInterstitialListener.onAdClicked(ChartboostAdapter.this);
                ChartboostAdapter.this.mMediationInterstitialListener.onAdLeftApplication(ChartboostAdapter.this);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDisplayInterstitial(String str) {
            super.didDisplayInterstitial(str);
            if (ChartboostAdapter.this.mMediationInterstitialListener != null) {
                ChartboostAdapter.this.mMediationInterstitialListener.onAdOpened(ChartboostAdapter.this);
            }
        }
    };

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
        this.mMediationInterstitialListener = mediationInterstitialListener;
        this.mChartboostParams = ChartboostAdapterUtils.createChartboostParams(bundle, bundle2);
        if (!ChartboostAdapterUtils.isValidChartboostParams(this.mChartboostParams)) {
            MediationInterstitialListener mediationInterstitialListener2 = this.mMediationInterstitialListener;
            if (mediationInterstitialListener2 != null) {
                mediationInterstitialListener2.onAdFailedToLoad(this, 1);
                return;
            }
            return;
        }
        if (!ChartboostAdapterUtils.isValidContext(context)) {
            Log.w(TAG, "Failed to request ad from Chartboost: Internal Error.");
            this.mMediationInterstitialListener.onAdFailedToLoad(this, 1);
        } else {
            ChartboostSingleton.startChartboostInterstitial(context, this.mChartboostInterstitialDelegate);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        ChartboostSingleton.showInterstitialAd(this.mChartboostInterstitialDelegate);
    }

    public static final class ChartboostExtrasBundleBuilder {
        static final String KEY_FRAMEWORK = "framework";
        static final String KEY_FRAMEWORK_VERSION = "framework_version";
        private Chartboost.CBFramework cbFramework;
        private String cbFrameworkVersion;

        public ChartboostExtrasBundleBuilder setFramework(Chartboost.CBFramework cBFramework, String str) {
            this.cbFramework = cBFramework;
            this.cbFrameworkVersion = str;
            return this;
        }

        public Bundle build() {
            Bundle bundle = new Bundle();
            bundle.putSerializable(KEY_FRAMEWORK, this.cbFramework);
            bundle.putString(KEY_FRAMEWORK_VERSION, this.cbFrameworkVersion);
            return bundle;
        }
    }
}
