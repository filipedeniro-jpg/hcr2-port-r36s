package com.google.ads.mediation.applovin;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.mediation.AppLovinIncentivizedAdListener;
import com.applovin.mediation.AppLovinUtils;
import com.applovin.mediation.ApplovinAdapter;
import com.applovin.mediation.BuildConfig;
import com.applovin.mediation.rtb.AppLovinRtbBannerRenderer;
import com.applovin.mediation.rtb.AppLovinRtbInterstitialRenderer;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationBannerAd;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdCallback;
import com.google.android.gms.ads.mediation.MediationInterstitialAdConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.VersionInfo;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;
import com.google.android.gms.ads.mediation.rtb.RtbSignalData;
import com.google.android.gms.ads.mediation.rtb.SignalCallbacks;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinMediationAdapter extends RtbAdapter implements MediationRewardedAd, AppLovinAdLoadListener {
    private static final String DEFAULT_ZONE = "";
    public static final HashMap<String, AppLovinIncentivizedInterstitial> INCENTIVIZED_ADS = new HashMap<>();
    private static final Object INCENTIVIZED_ADS_LOCK = new Object();
    private static final String TAG = "AppLovinMediationAdapter";
    private static WeakReference<Context> applicationContextRef = null;
    private static boolean isRtbAd = true;
    private AppLovinAd ad;
    private MediationRewardedAdConfiguration adConfiguration;
    private AppLovinIncentivizedInterstitial mIncentivizedInterstitial;
    private MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mMediationAdLoadCallback;
    private Bundle mNetworkExtras;
    private String mPlacement;
    private MediationRewardedAdCallback mRewardedAdCallback;
    private AppLovinSdk mSdk;
    private String mZoneId;

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void initialize(Context context, InitializationCompleteCallback initializationCompleteCallback, List<MediationConfiguration> list) {
        ApplovinAdapter.log(3, "Attempting to initialize SDK");
        Context applicationContext = context.getApplicationContext();
        applicationContextRef = new WeakReference<>(applicationContext);
        if (AppLovinUtils.androidManifestHasValidSdkKey(applicationContext)) {
            AppLovinSdk.getInstance(applicationContext).initializeSdk();
        }
        Iterator<MediationConfiguration> it = list.iterator();
        while (it.hasNext()) {
            AppLovinUtils.retrieveSdk(it.next().getServerParameters(), applicationContextRef.get()).initializeSdk();
        }
        initializationCompleteCallback.onInitializationSucceeded();
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public VersionInfo getVersionInfo() {
        String[] strArrSplit = BuildConfig.VERSION_NAME.split("\\.");
        return new VersionInfo(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), (Integer.parseInt(strArrSplit[2]) * 100) + Integer.parseInt(strArrSplit[3]));
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public VersionInfo getSDKVersionInfo() {
        String[] strArrSplit = AppLovinSdk.VERSION.split("\\.");
        return new VersionInfo(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]));
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void loadRewardedAd(MediationRewardedAdConfiguration mediationRewardedAdConfiguration, MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback) {
        this.adConfiguration = mediationRewardedAdConfiguration;
        if (mediationRewardedAdConfiguration.getBidResponse().equals("")) {
            isRtbAd = false;
        }
        if (!isRtbAd) {
            synchronized (INCENTIVIZED_ADS_LOCK) {
                Bundle serverParameters = this.adConfiguration.getServerParameters();
                this.mPlacement = AppLovinUtils.retrievePlacement(serverParameters);
                this.mZoneId = AppLovinUtils.retrieveZoneId(serverParameters);
                this.mSdk = AppLovinUtils.retrieveSdk(serverParameters, this.adConfiguration.getContext());
                this.mNetworkExtras = this.adConfiguration.getMediationExtras();
                this.mMediationAdLoadCallback = mediationAdLoadCallback;
                ApplovinAdapter.log(3, String.format("Requesting rewarded video for zone '%s' and placement '%s'.", this.mZoneId, this.mPlacement));
                if (INCENTIVIZED_ADS.containsKey(this.mZoneId)) {
                    this.mIncentivizedInterstitial = INCENTIVIZED_ADS.get(this.mZoneId);
                    this.mMediationAdLoadCallback.onFailure(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_FAILED);
                    ApplovinAdapter.log(6, "Cannot load multiple ads with the same Zone ID. Display one ad before attempting to load another.");
                } else {
                    if ("".equals(this.mZoneId)) {
                        this.mIncentivizedInterstitial = AppLovinIncentivizedInterstitial.create(this.mSdk);
                    } else {
                        this.mIncentivizedInterstitial = AppLovinIncentivizedInterstitial.create(this.mZoneId, this.mSdk);
                    }
                    INCENTIVIZED_ADS.put(this.mZoneId, this.mIncentivizedInterstitial);
                }
            }
            this.mIncentivizedInterstitial.preload(this);
            return;
        }
        this.mMediationAdLoadCallback = mediationAdLoadCallback;
        this.mNetworkExtras = this.adConfiguration.getMediationExtras();
        this.mSdk = AppLovinUtils.retrieveSdk(this.adConfiguration.getServerParameters(), this.adConfiguration.getContext());
        this.mIncentivizedInterstitial = AppLovinIncentivizedInterstitial.create(this.mSdk);
        this.mSdk.getAdService().loadNextAdForAdToken(this.adConfiguration.getBidResponse(), this);
    }

    @Override // com.google.android.gms.ads.mediation.MediationRewardedAd
    public void showAd(Context context) {
        this.mSdk.getSettings().setMuted(AppLovinUtils.shouldMuteAudio(this.mNetworkExtras));
        ApplovinAdapter.log(3, String.format("Showing rewarded video for zone '%s', placement '%s'", this.mZoneId, this.mPlacement));
        AppLovinIncentivizedAdListener appLovinIncentivizedAdListener = new AppLovinIncentivizedAdListener(this.adConfiguration, this.mRewardedAdCallback);
        if (!isRtbAd) {
            if (!this.mIncentivizedInterstitial.isAdReadyToDisplay()) {
                this.mRewardedAdCallback.onAdFailedToShow("Ad Failed to show");
                return;
            } else {
                this.mIncentivizedInterstitial.show(context, appLovinIncentivizedAdListener, appLovinIncentivizedAdListener, appLovinIncentivizedAdListener, appLovinIncentivizedAdListener);
                return;
            }
        }
        this.mIncentivizedInterstitial.show(this.ad, context, appLovinIncentivizedAdListener, appLovinIncentivizedAdListener, appLovinIncentivizedAdListener, appLovinIncentivizedAdListener);
    }

    @Override // com.google.android.gms.ads.mediation.rtb.RtbAdapter
    public void collectSignals(RtbSignalData rtbSignalData, SignalCallbacks signalCallbacks) {
        MediationConfiguration configuration = rtbSignalData.getConfiguration();
        if (configuration.getFormat() == AdFormat.NATIVE) {
            handleCollectSignalsFailure("Requested to collect signal for unsupported native ad format. Ignoring...", signalCallbacks);
            return;
        }
        if (rtbSignalData.getNetworkExtras() != null) {
            Log.i(TAG, "Extras for signal collection: " + rtbSignalData.getNetworkExtras());
        }
        String bidToken = AppLovinUtils.retrieveSdk(configuration.getServerParameters(), rtbSignalData.getContext()).getAdService().getBidToken();
        if (!TextUtils.isEmpty(bidToken)) {
            Log.i(TAG, "Generated bid token: " + bidToken);
            signalCallbacks.onSuccess(bidToken);
            return;
        }
        handleCollectSignalsFailure("Failed to generate bid token", signalCallbacks);
    }

    private void handleCollectSignalsFailure(String str, SignalCallbacks signalCallbacks) {
        Log.e(TAG, str);
        signalCallbacks.onFailure(str);
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void loadBannerAd(MediationBannerAdConfiguration mediationBannerAdConfiguration, MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback> mediationAdLoadCallback) {
        new AppLovinRtbBannerRenderer(mediationBannerAdConfiguration, mediationAdLoadCallback).loadAd();
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void loadInterstitialAd(MediationInterstitialAdConfiguration mediationInterstitialAdConfiguration, MediationAdLoadCallback<MediationInterstitialAd, MediationInterstitialAdCallback> mediationAdLoadCallback) {
        new AppLovinRtbInterstitialRenderer(mediationInterstitialAdConfiguration, mediationAdLoadCallback).loadAd();
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void adReceived(AppLovinAd appLovinAd) {
        this.ad = appLovinAd;
        Log.d("INFO", "Rewarded video did load ad: " + this.ad.getAdIdNumber());
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.google.ads.mediation.applovin.AppLovinMediationAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                AppLovinMediationAdapter appLovinMediationAdapter = AppLovinMediationAdapter.this;
                appLovinMediationAdapter.mRewardedAdCallback = (MediationRewardedAdCallback) appLovinMediationAdapter.mMediationAdLoadCallback.onSuccess(AppLovinMediationAdapter.this);
            }
        });
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void failedToReceiveAd(int i) {
        ApplovinAdapter.log(6, "Rewarded video failed to load with error: " + i);
        if (!isRtbAd) {
            INCENTIVIZED_ADS.remove(this.mZoneId);
        }
        AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.google.ads.mediation.applovin.AppLovinMediationAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                AppLovinMediationAdapter.this.mMediationAdLoadCallback.onFailure("Error");
            }
        });
    }
}
