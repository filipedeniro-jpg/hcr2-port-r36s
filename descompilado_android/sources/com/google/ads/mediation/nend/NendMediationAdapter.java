package com.google.ads.mediation.nend;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.adcolony.sdk.AdColonyAppOptions;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.VersionInfo;
import java.util.List;
import net.nend.android.NendAdRewardItem;
import net.nend.android.NendAdRewardedListener;
import net.nend.android.NendAdRewardedVideo;
import net.nend.android.NendAdVideo;

/* JADX INFO: loaded from: classes.dex */
public class NendMediationAdapter extends Adapter implements MediationRewardedAd, NendAdRewardedListener {
    static final String KEY_API_KEY = "apiKey";
    static final String KEY_SPOT_ID = "spotId";
    static final String KEY_USER_ID = "key_user_id";
    static final String TAG = "NendMediationAdapter";
    private MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mAdLoadCallback;
    private MediationRewardedAdCallback mRewardedAdCallback;
    private NendAdRewardedVideo mRewardedVideo;

    @Override // net.nend.android.NendAdVideoListener
    public void onInformationClicked(NendAdVideo nendAdVideo) {
    }

    @Override // net.nend.android.NendAdVideoListener
    public void onStopped(NendAdVideo nendAdVideo) {
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public VersionInfo getVersionInfo() {
        String[] strArrSplit = BuildConfig.VERSION_NAME.split("\\.");
        return new VersionInfo(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), (Integer.parseInt(strArrSplit[2]) * 100) + Integer.parseInt(strArrSplit[3]));
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public VersionInfo getSDKVersionInfo() {
        String[] strArrSplit = net.nend.android.BuildConfig.VERSION_NAME.split("\\.");
        return new VersionInfo(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]));
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void initialize(Context context, InitializationCompleteCallback initializationCompleteCallback, List<MediationConfiguration> list) {
        initializationCompleteCallback.onInitializationSucceeded();
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void loadRewardedAd(MediationRewardedAdConfiguration mediationRewardedAdConfiguration, MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback) {
        Context context = mediationRewardedAdConfiguration.getContext();
        if (!(context instanceof Activity)) {
            Log.w(TAG, "Failed to request ad from Nend: Nend requires an Activity context to load an ad.");
            mediationAdLoadCallback.onFailure("Failed to request ad from Nend: Nend requires an Activity context to load an ad.");
            return;
        }
        Bundle serverParameters = mediationRewardedAdConfiguration.getServerParameters();
        Bundle mediationExtras = mediationRewardedAdConfiguration.getMediationExtras();
        String string = serverParameters.getString(KEY_API_KEY);
        if (TextUtils.isEmpty(string)) {
            Log.w(TAG, "Failed to request ad from Nend: Missing or Invalid API Key.");
            mediationAdLoadCallback.onFailure("Failed to request ad from Nend: Missing or Invalid API Key.");
            return;
        }
        int i = Integer.parseInt(serverParameters.getString(KEY_SPOT_ID, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        if (i <= 0) {
            Log.w(TAG, "Failed to request ad from Nend: Missing or invalid Spot ID");
            mediationAdLoadCallback.onFailure("Failed to request ad from Nend: Missing or invalid Spot ID");
            return;
        }
        this.mAdLoadCallback = mediationAdLoadCallback;
        this.mRewardedVideo = new NendAdRewardedVideo(context, i, string);
        this.mRewardedVideo.setAdListener(this);
        this.mRewardedVideo.setMediationName(AdColonyAppOptions.ADMOB);
        if (mediationExtras != null) {
            this.mRewardedVideo.setUserId(mediationExtras.getString(KEY_USER_ID, ""));
        }
        this.mRewardedVideo.loadAd();
    }

    @Override // com.google.android.gms.ads.mediation.MediationRewardedAd
    public void showAd(Context context) {
        if (this.mRewardedVideo.isLoaded()) {
            if (context instanceof Activity) {
                this.mRewardedVideo.showAd((Activity) context);
                return;
            }
            MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
            if (mediationRewardedAdCallback != null) {
                mediationRewardedAdCallback.onAdFailedToShow("Nend Ads require an Activity context to show ads.");
                return;
            }
            return;
        }
        MediationRewardedAdCallback mediationRewardedAdCallback2 = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback2 != null) {
            mediationRewardedAdCallback2.onAdFailedToShow("Ad not ready yet.");
        }
    }

    @Override // net.nend.android.NendAdVideoListener
    public void onLoaded(NendAdVideo nendAdVideo) {
        MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback = this.mAdLoadCallback;
        if (mediationAdLoadCallback != null) {
            this.mRewardedAdCallback = mediationAdLoadCallback.onSuccess(this);
        }
    }

    @Override // net.nend.android.NendAdVideoListener
    public void onFailedToLoad(NendAdVideo nendAdVideo, int i) {
        String str = "Failed to request ad from Nend, Error Code: " + ErrorUtil.convertErrorCodeFromNendVideoToAdMob(i);
        Log.w(TAG, str);
        MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback = this.mAdLoadCallback;
        if (mediationAdLoadCallback != null) {
            mediationAdLoadCallback.onFailure(str);
        }
        this.mRewardedVideo.releaseAd();
    }

    @Override // net.nend.android.NendAdRewardedListener
    public void onRewarded(NendAdVideo nendAdVideo, NendAdRewardItem nendAdRewardItem) {
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onUserEarnedReward(new NendMediationRewardItem(nendAdRewardItem));
        }
    }

    @Override // net.nend.android.NendAdVideoListener
    public void onFailedToPlay(NendAdVideo nendAdVideo) {
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onAdFailedToShow("Internal Error.");
        }
    }

    @Override // net.nend.android.NendAdVideoListener
    public void onShown(NendAdVideo nendAdVideo) {
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onAdOpened();
            this.mRewardedAdCallback.reportAdImpression();
        }
    }

    @Override // net.nend.android.NendAdVideoListener
    public void onClosed(NendAdVideo nendAdVideo) {
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onAdClosed();
        }
        this.mRewardedVideo.releaseAd();
    }

    @Override // net.nend.android.NendAdVideoListener
    public void onStarted(NendAdVideo nendAdVideo) {
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onVideoStart();
        }
    }

    @Override // net.nend.android.NendAdVideoListener
    public void onCompleted(NendAdVideo nendAdVideo) {
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.onVideoComplete();
        }
    }

    @Override // net.nend.android.NendAdVideoListener
    public void onAdClicked(NendAdVideo nendAdVideo) {
        MediationRewardedAdCallback mediationRewardedAdCallback = this.mRewardedAdCallback;
        if (mediationRewardedAdCallback != null) {
            mediationRewardedAdCallback.reportAdClicked();
        }
    }
}
