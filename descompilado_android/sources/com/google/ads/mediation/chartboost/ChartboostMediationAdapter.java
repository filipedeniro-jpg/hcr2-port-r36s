package com.google.ads.mediation.chartboost;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Model.CBError;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.VersionInfo;
import com.vungle.warren.model.Cookie;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChartboostMediationAdapter extends Adapter implements MediationRewardedAd {
    static final String TAG = "ChartboostMediationAdapter";
    private MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mAdLoadCallback;
    private ChartboostParams mChartboostParams = new ChartboostParams();
    private AbstractChartboostAdapterDelegate mChartboostRewardedVideoDelegate = new AbstractChartboostAdapterDelegate() { // from class: com.google.ads.mediation.chartboost.ChartboostMediationAdapter.1
        @Override // com.google.ads.mediation.chartboost.AbstractChartboostAdapterDelegate
        public ChartboostParams getChartboostParams() {
            return ChartboostMediationAdapter.this.mChartboostParams;
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didInitialize() {
            super.didInitialize();
            if (ChartboostMediationAdapter.this.mInitializationCallback != null) {
                ChartboostMediationAdapter.this.mInitializationCallback.onInitializationSucceeded();
            }
            if (ChartboostMediationAdapter.this.mAdLoadCallback != null) {
                ChartboostMediationAdapter.this.mIsLoading = true;
                ChartboostSingleton.loadRewardedVideoAd(ChartboostMediationAdapter.this.mChartboostRewardedVideoDelegate);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCacheRewardedVideo(String str) {
            super.didCacheRewardedVideo(str);
            if (ChartboostMediationAdapter.this.mAdLoadCallback != null && ChartboostMediationAdapter.this.mIsLoading && str.equals(ChartboostMediationAdapter.this.mChartboostParams.getLocation())) {
                ChartboostMediationAdapter.this.mIsLoading = false;
                ChartboostMediationAdapter chartboostMediationAdapter = ChartboostMediationAdapter.this;
                chartboostMediationAdapter.mRewardedAdCallback = (MediationRewardedAdCallback) chartboostMediationAdapter.mAdLoadCallback.onSuccess(ChartboostMediationAdapter.this);
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didFailToLoadRewardedVideo(String str, CBError.CBImpressionError cBImpressionError) {
            super.didFailToLoadRewardedVideo(str, cBImpressionError);
            if (ChartboostMediationAdapter.this.mAdLoadCallback == null || !str.equals(ChartboostMediationAdapter.this.mChartboostParams.getLocation())) {
                return;
            }
            if (ChartboostMediationAdapter.this.mIsLoading) {
                String str2 = "Failed to load ad from Chartboost: " + cBImpressionError.toString();
                Log.w(ChartboostMediationAdapter.TAG, str2);
                ChartboostMediationAdapter.this.mAdLoadCallback.onFailure(str2);
                ChartboostMediationAdapter.this.mIsLoading = false;
                return;
            }
            if (cBImpressionError != CBError.CBImpressionError.INTERNET_UNAVAILABLE_AT_SHOW || ChartboostMediationAdapter.this.mRewardedAdCallback == null) {
                return;
            }
            ChartboostMediationAdapter.this.mRewardedAdCallback.onAdFailedToShow("No network connection is available.");
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDismissRewardedVideo(String str) {
            super.didDismissRewardedVideo(str);
            if (ChartboostMediationAdapter.this.mRewardedAdCallback != null) {
                ChartboostMediationAdapter.this.mRewardedAdCallback.onAdClosed();
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didClickRewardedVideo(String str) {
            super.didClickRewardedVideo(str);
            if (ChartboostMediationAdapter.this.mRewardedAdCallback != null) {
                ChartboostMediationAdapter.this.mRewardedAdCallback.reportAdClicked();
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCompleteRewardedVideo(String str, int i) {
            super.didCompleteRewardedVideo(str, i);
            if (ChartboostMediationAdapter.this.mRewardedAdCallback != null) {
                ChartboostMediationAdapter.this.mRewardedAdCallback.onVideoComplete();
                ChartboostMediationAdapter.this.mRewardedAdCallback.onUserEarnedReward(new ChartboostReward(i));
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDisplayRewardedVideo(String str) {
            super.didDisplayRewardedVideo(str);
            if (ChartboostMediationAdapter.this.mRewardedAdCallback != null) {
                ChartboostMediationAdapter.this.mRewardedAdCallback.onAdOpened();
                ChartboostMediationAdapter.this.mRewardedAdCallback.onVideoStart();
                ChartboostMediationAdapter.this.mRewardedAdCallback.reportAdImpression();
            }
        }
    };
    private InitializationCompleteCallback mInitializationCallback;
    private boolean mIsLoading;
    private MediationRewardedAdCallback mRewardedAdCallback;

    @Override // com.google.android.gms.ads.mediation.Adapter
    public VersionInfo getVersionInfo() {
        String[] strArrSplit = BuildConfig.VERSION_NAME.split("\\.");
        return new VersionInfo(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), (Integer.parseInt(strArrSplit[2]) * 100) + Integer.parseInt(strArrSplit[3]));
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public VersionInfo getSDKVersionInfo() {
        String[] strArrSplit = Chartboost.getSDKVersion().split("\\.");
        return new VersionInfo(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]));
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void initialize(Context context, InitializationCompleteCallback initializationCompleteCallback, List<MediationConfiguration> list) {
        if (!(context instanceof Activity)) {
            initializationCompleteCallback.onInitializationFailed("Chartboost SDK requires an Activity context to initialize");
            return;
        }
        HashMap map = new HashMap();
        Iterator<MediationConfiguration> it = list.iterator();
        while (it.hasNext()) {
            Bundle serverParameters = it.next().getServerParameters();
            String string = serverParameters.getString(Cookie.APP_ID);
            if (!TextUtils.isEmpty(string)) {
                map.put(string, serverParameters);
            }
        }
        int size = map.size();
        if (size > 0) {
            String str = (String) map.keySet().iterator().next();
            Bundle bundle = (Bundle) map.get(str);
            if (size > 1) {
                Log.w(TAG, String.format("Multiple '%s' entries found: %s. Using '%s' to initialize the Chartboost SDK", Cookie.APP_ID, map.keySet(), str));
            }
            this.mInitializationCallback = initializationCompleteCallback;
            this.mChartboostParams = ChartboostAdapterUtils.createChartboostParams(bundle, null);
            if (!ChartboostAdapterUtils.isValidChartboostParams(this.mChartboostParams)) {
                initializationCompleteCallback.onInitializationFailed("Initialization Failed: Invalid server parameters.");
                return;
            } else {
                ChartboostSingleton.startChartboostRewardedVideo(context, this.mChartboostRewardedVideoDelegate);
                return;
            }
        }
        initializationCompleteCallback.onInitializationFailed("Initialization failed:Missing or invalid App ID.");
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void loadRewardedAd(MediationRewardedAdConfiguration mediationRewardedAdConfiguration, MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback) {
        this.mAdLoadCallback = mediationAdLoadCallback;
        Bundle serverParameters = mediationRewardedAdConfiguration.getServerParameters();
        Bundle mediationExtras = mediationRewardedAdConfiguration.getMediationExtras();
        Context context = mediationRewardedAdConfiguration.getContext();
        if (!(context instanceof Activity)) {
            Log.w(TAG, "Failed to request ad from Chartboost: Chartboost SDK requires an Activity context to initialize.");
            mediationAdLoadCallback.onFailure("Failed to request ad from Chartboost: Chartboost SDK requires an Activity context to initialize.");
            return;
        }
        this.mChartboostParams = ChartboostAdapterUtils.createChartboostParams(serverParameters, mediationExtras);
        if (!ChartboostAdapterUtils.isValidChartboostParams(this.mChartboostParams)) {
            Log.w(TAG, "Failed to request ad from Chartboost: Invalid server parameters.");
            mediationAdLoadCallback.onFailure("Failed to request ad from Chartboost: Invalid server parameters.");
        } else if (!ChartboostAdapterUtils.isValidContext(context)) {
            Log.w(TAG, "Failed to request ad from Chartboost: Internal Error.");
            mediationAdLoadCallback.onFailure("Failed to request ad from Chartboost: Internal Error.");
        } else {
            ChartboostSingleton.startChartboostRewardedVideo(context, this.mChartboostRewardedVideoDelegate);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationRewardedAd
    public void showAd(Context context) {
        ChartboostSingleton.showRewardedVideoAd(this.mChartboostRewardedVideoDelegate);
    }
}
