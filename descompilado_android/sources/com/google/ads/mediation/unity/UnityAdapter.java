package com.google.ads.mediation.unity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Keep;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.unity3d.ads.UnityAds;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class UnityAdapter implements MediationRewardedVideoAdAdapter, MediationInterstitialAdapter, OnContextChangedListener {
    private static final String KEY_GAME_ID = "gameId";
    private static final String KEY_PLACEMENT_ID = "zoneId";
    public static final String TAG = "UnityAdapter";
    private WeakReference<Activity> mActivityWeakReference;
    private boolean mIsLoading;
    private boolean mIsRewardedVideoAdAdapterInitialized;
    private MediationInterstitialListener mMediationInterstitialListener;
    private MediationRewardedVideoAdListener mMediationRewardedVideoAdListener;
    private String mPlacementId;
    private UnityAdapterDelegate mUnityAdapterDelegate = new UnityAdapterDelegate() { // from class: com.google.ads.mediation.unity.UnityAdapter.1
        @Override // com.unity3d.ads.mediation.IUnityAdsExtendedListener
        public void onUnityAdsPlacementStateChanged(String str, UnityAds.PlacementState placementState, UnityAds.PlacementState placementState2) {
        }

        @Override // com.google.ads.mediation.unity.UnityAdapterDelegate
        public String getPlacementId() {
            return UnityAdapter.this.mPlacementId;
        }

        @Override // com.unity3d.ads.IUnityAdsListener
        public void onUnityAdsReady(String str) {
            if (UnityAdapter.this.mIsLoading) {
                if (UnityAdapter.this.mMediationInterstitialListener != null) {
                    UnityAdapter.this.mMediationInterstitialListener.onAdLoaded(UnityAdapter.this);
                    UnityAdapter.this.mIsLoading = false;
                } else if (UnityAdapter.this.mMediationRewardedVideoAdListener != null) {
                    UnityAdapter.this.mMediationRewardedVideoAdListener.onAdLoaded(UnityAdapter.this);
                    UnityAdapter.this.mIsLoading = false;
                }
            }
        }

        @Override // com.unity3d.ads.IUnityAdsListener
        public void onUnityAdsStart(String str) {
            if (UnityAdapter.this.mMediationRewardedVideoAdListener != null) {
                UnityAdapter.this.mMediationRewardedVideoAdListener.onVideoStarted(UnityAdapter.this);
            }
        }

        @Override // com.unity3d.ads.mediation.IUnityAdsExtendedListener
        public void onUnityAdsClick(String str) {
            if (UnityAdapter.this.mMediationInterstitialListener != null) {
                UnityAdapter.this.mMediationInterstitialListener.onAdClicked(UnityAdapter.this);
                UnityAdapter.this.mMediationInterstitialListener.onAdLeftApplication(UnityAdapter.this);
            } else if (UnityAdapter.this.mMediationRewardedVideoAdListener != null) {
                UnityAdapter.this.mMediationRewardedVideoAdListener.onAdClicked(UnityAdapter.this);
                UnityAdapter.this.mMediationRewardedVideoAdListener.onAdLeftApplication(UnityAdapter.this);
            }
        }

        @Override // com.unity3d.ads.IUnityAdsListener
        public void onUnityAdsFinish(String str, UnityAds.FinishState finishState) {
            if (UnityAdapter.this.mMediationInterstitialListener != null) {
                UnityAdapter.this.mMediationInterstitialListener.onAdClosed(UnityAdapter.this);
            } else if (UnityAdapter.this.mMediationRewardedVideoAdListener != null) {
                if (finishState == UnityAds.FinishState.COMPLETED) {
                    UnityAdapter.this.mMediationRewardedVideoAdListener.onVideoCompleted(UnityAdapter.this);
                    UnityAdapter.this.mMediationRewardedVideoAdListener.onRewarded(UnityAdapter.this, new UnityReward());
                }
                UnityAdapter.this.mMediationRewardedVideoAdListener.onAdClosed(UnityAdapter.this);
            }
        }

        @Override // com.unity3d.ads.IUnityAdsListener
        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String str) {
            if (UnityAdapter.this.mIsLoading) {
                if (UnityAdapter.this.mMediationInterstitialListener != null) {
                    UnityAdapter.this.mMediationInterstitialListener.onAdFailedToLoad(UnityAdapter.this, 3);
                    UnityAdapter.this.mIsLoading = false;
                } else if (UnityAdapter.this.mMediationRewardedVideoAdListener != null) {
                    UnityAdapter.this.mMediationRewardedVideoAdListener.onAdFailedToLoad(UnityAdapter.this, 3);
                    UnityAdapter.this.mIsLoading = false;
                }
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

    private static boolean isValidIds(String str, String str2) {
        String str3;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            str3 = TextUtils.isEmpty(str2) ? "Game ID and Placement ID" : "Game ID";
        } else {
            str3 = "Placement ID";
        }
        Log.w(TAG, str3 + " cannot be empty.");
        return false;
    }

    private static boolean isValidContext(Context context) {
        if (context == null) {
            Log.w(TAG, "Context cannot be null.");
            return false;
        }
        if (context instanceof Activity) {
            return true;
        }
        Log.w(TAG, "Context is not an Activity. Unity Ads requires an Activity context to load ads.");
        return false;
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.mMediationInterstitialListener = mediationInterstitialListener;
        String string = bundle.getString(KEY_GAME_ID);
        this.mPlacementId = bundle.getString(KEY_PLACEMENT_ID);
        if (!isValidIds(string, this.mPlacementId)) {
            MediationInterstitialListener mediationInterstitialListener2 = this.mMediationInterstitialListener;
            if (mediationInterstitialListener2 != null) {
                mediationInterstitialListener2.onAdFailedToLoad(this, 1);
                return;
            }
            return;
        }
        if (isValidContext(context)) {
            Activity activity = (Activity) context;
            if (!UnitySingleton.initializeUnityAds(this.mUnityAdapterDelegate, activity, string)) {
                MediationInterstitialListener mediationInterstitialListener3 = this.mMediationInterstitialListener;
                if (mediationInterstitialListener3 != null) {
                    mediationInterstitialListener3.onAdFailedToLoad(this, 1);
                    return;
                }
                return;
            }
            this.mActivityWeakReference = new WeakReference<>(activity);
            this.mIsLoading = true;
            UnitySingleton.loadAd(this.mUnityAdapterDelegate);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        this.mMediationInterstitialListener.onAdOpened(this);
        Activity activity = this.mActivityWeakReference.get();
        if (activity == null) {
            Log.w(TAG, "An activity context is required to show Unity Ads, please call RewardedVideoAd#resume(Context) in your Activity's onResume.");
            this.mMediationInterstitialListener.onAdClosed(this);
        } else {
            UnitySingleton.showAd(this.mUnityAdapterDelegate, activity);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        this.mMediationRewardedVideoAdListener = mediationRewardedVideoAdListener;
        String string = bundle.getString(KEY_GAME_ID);
        this.mPlacementId = bundle.getString(KEY_PLACEMENT_ID);
        if (!isValidIds(string, this.mPlacementId)) {
            MediationRewardedVideoAdListener mediationRewardedVideoAdListener2 = this.mMediationRewardedVideoAdListener;
            if (mediationRewardedVideoAdListener2 != null) {
                mediationRewardedVideoAdListener2.onInitializationFailed(this, 1);
                return;
            }
            return;
        }
        if (isValidContext(context)) {
            Activity activity = (Activity) context;
            if (!UnitySingleton.initializeUnityAds(this.mUnityAdapterDelegate, activity, string)) {
                MediationRewardedVideoAdListener mediationRewardedVideoAdListener3 = this.mMediationRewardedVideoAdListener;
                if (mediationRewardedVideoAdListener3 != null) {
                    mediationRewardedVideoAdListener3.onInitializationFailed(this, 1);
                    return;
                }
                return;
            }
            this.mActivityWeakReference = new WeakReference<>(activity);
            this.mIsRewardedVideoAdAdapterInitialized = true;
            this.mMediationRewardedVideoAdListener.onInitializationSucceeded(this);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        this.mIsLoading = true;
        this.mPlacementId = bundle.getString(KEY_PLACEMENT_ID);
        if (!isValidIds(bundle.getString(KEY_GAME_ID), this.mPlacementId)) {
            this.mMediationRewardedVideoAdListener.onAdFailedToLoad(this, 1);
        } else {
            UnitySingleton.loadAd(this.mUnityAdapterDelegate);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void showVideo() {
        this.mMediationRewardedVideoAdListener.onAdOpened(this);
        Activity activity = this.mActivityWeakReference.get();
        if (activity == null) {
            Log.w(TAG, "An activity context is required to show Unity Ads, please call RewardedVideoAd#resume(Context) in your Activity's onResume.");
            this.mMediationRewardedVideoAdListener.onAdClosed(this);
        } else {
            UnitySingleton.showAd(this.mUnityAdapterDelegate, activity);
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public boolean isInitialized() {
        return this.mIsRewardedVideoAdAdapterInitialized && UnityAds.isInitialized();
    }

    @Override // com.google.android.gms.ads.mediation.OnContextChangedListener
    public void onContextChanged(Context context) {
        if (!(context instanceof Activity)) {
            Log.w(TAG, "Context is not an Activity. Unity Ads requires an Activity context to show ads.");
        } else {
            this.mActivityWeakReference = new WeakReference<>((Activity) context);
        }
    }
}
