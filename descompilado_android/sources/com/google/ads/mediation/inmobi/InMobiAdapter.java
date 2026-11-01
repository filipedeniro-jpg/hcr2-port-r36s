package com.google.ads.mediation.inmobi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.InMobiNative;
import com.inmobi.ads.listeners.BannerAdEventListener;
import com.inmobi.ads.listeners.InterstitialAdEventListener;
import com.inmobi.ads.listeners.NativeAdEventListener;
import com.inmobi.sdk.InMobiSdk;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class InMobiAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationRewardedVideoAdAdapter, MediationNativeAdapter {
    private static final String TAG = "InMobiAdapter";
    private static Boolean sDisableHardwareFlag = false;
    private static Boolean sIsAppInitialized = false;
    private InMobiInterstitial mAdInterstitial;
    private InMobiInterstitial mAdRewarded;
    private MediationBannerListener mBannerListener;
    private MediationInterstitialListener mInterstitialListener;
    private boolean mIsRewardedVideoAdAdapterInitialized;
    private MediationNativeListener mNativeListener;
    private NativeMediationAdRequest mNativeMedAdReq;
    private MediationRewardedVideoAdListener mRewardedVideoAdListener;
    private FrameLayout mWrappedAdView;
    private String mKey = "";
    private String mValue = "";
    private Boolean mIsOnlyUrl = false;

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onDestroy() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onPause() {
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public void onResume() {
    }

    public static Boolean IsAppInitialized() {
        return sIsAppInitialized;
    }

    private void isTaggedForChildDirectedTreatment(MediationAdRequest mediationAdRequest, HashMap<String, String> map) {
        if (mediationAdRequest.taggedForChildDirectedTreatment() == 1) {
            map.put("coppa", "1");
        } else {
            map.put("coppa", AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getAdRequestErrorCode(InMobiAdRequestStatus.StatusCode statusCode) {
        switch (statusCode) {
            case INTERNAL_ERROR:
                return 0;
            case AD_ACTIVE:
            case REQUEST_INVALID:
            case REQUEST_PENDING:
            case EARLY_REFRESH_REQUEST:
            case MISSING_REQUIRED_DEPENDENCIES:
                return 1;
            case REQUEST_TIMED_OUT:
            case NETWORK_UNREACHABLE:
                return 2;
            default:
                return 3;
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        InMobiBanner inMobiBanner;
        if (!sIsAppInitialized.booleanValue() && bundle != null) {
            Log.d(TAG, bundle.getString("accountid"));
            Log.d(TAG, bundle.getString("placementid"));
            InMobiSdk.init(context, bundle.getString("accountid"), InMobiConsent.getConsentObj());
            sIsAppInitialized = true;
        }
        this.mBannerListener = mediationBannerListener;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context));
        if (bundle != null) {
            if (context instanceof Activity) {
                inMobiBanner = new InMobiBanner((Activity) context, Long.parseLong(bundle.getString("placementid")));
            } else {
                inMobiBanner = new InMobiBanner(context, Long.parseLong(bundle.getString("placementid")));
            }
            inMobiBanner.setEnableAutoRefresh(false);
            inMobiBanner.setAnimationType(InMobiBanner.AnimationType.ANIMATION_OFF);
            if (mediationAdRequest.getKeywords() != null) {
                inMobiBanner.setKeywords(TextUtils.join(", ", mediationAdRequest.getKeywords()));
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("tp", "c_admob");
            isTaggedForChildDirectedTreatment(mediationAdRequest, map);
            inMobiBanner.setExtras(map);
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            inMobiBanner.setListener(new BannerAdEventListener() { // from class: com.google.ads.mediation.inmobi.InMobiAdapter.1
                @Override // com.inmobi.ads.listeners.BannerAdEventListener
                public void onUserLeftApplication(InMobiBanner inMobiBanner2) {
                    Log.d(InMobiAdapter.TAG, "onUserLeftApplication");
                    InMobiAdapter.this.mBannerListener.onAdLeftApplication(InMobiAdapter.this);
                }

                @Override // com.inmobi.ads.listeners.BannerAdEventListener
                public void onRewardsUnlocked(InMobiBanner inMobiBanner2, Map<Object, Object> map2) {
                    Log.d(InMobiAdapter.TAG, "InMobi Banner onRewardsUnlocked.");
                    if (map2 != null) {
                        Iterator<Object> it = map2.keySet().iterator();
                        while (it.hasNext()) {
                            String string = it.next().toString();
                            Log.d("Rewards: ", string + ":" + map2.get(string).toString());
                        }
                    }
                }

                @Override // com.inmobi.ads.listeners.BannerAdEventListener
                public void onAdLoadSucceeded(InMobiBanner inMobiBanner2) {
                    System.out.println("onLoadSucceeded");
                    Log.d(InMobiAdapter.TAG, "onAdLoadSucceeded");
                    InMobiAdapter.this.mBannerListener.onAdLoaded(InMobiAdapter.this);
                }

                @Override // com.inmobi.ads.listeners.BannerAdEventListener
                public void onAdLoadFailed(InMobiBanner inMobiBanner2, InMobiAdRequestStatus inMobiAdRequestStatus) {
                    InMobiAdapter.this.mBannerListener.onAdFailedToLoad(InMobiAdapter.this, InMobiAdapter.getAdRequestErrorCode(inMobiAdRequestStatus.getStatusCode()));
                    Log.d(InMobiAdapter.TAG, "onAdLoadFailed: " + inMobiAdRequestStatus.getMessage());
                }

                @Override // com.inmobi.ads.listeners.BannerAdEventListener
                public void onAdDisplayed(InMobiBanner inMobiBanner2) {
                    Log.d(InMobiAdapter.TAG, "onAdDismissed");
                    InMobiAdapter.this.mBannerListener.onAdOpened(InMobiAdapter.this);
                }

                @Override // com.inmobi.ads.listeners.BannerAdEventListener
                public void onAdDismissed(InMobiBanner inMobiBanner2) {
                    Log.d(InMobiAdapter.TAG, "onAdDismissed");
                    InMobiAdapter.this.mBannerListener.onAdClosed(InMobiAdapter.this);
                }

                @Override // com.inmobi.ads.listeners.BannerAdEventListener
                public void onAdClicked(InMobiBanner inMobiBanner2, Map<Object, Object> map2) {
                    Log.d("onBannerClicked", "onBannerClick called");
                    InMobiAdapter.this.mBannerListener.onAdClicked(InMobiAdapter.this);
                }
            });
            if (sDisableHardwareFlag.booleanValue()) {
                inMobiBanner.disableHardwareAcceleration();
            }
            this.mWrappedAdView = new FrameLayout(context);
            this.mWrappedAdView.setLayoutParams(layoutParams);
            inMobiBanner.setLayoutParams(new LinearLayout.LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context)));
            this.mWrappedAdView.addView(inMobiBanner);
            InMobiAdapterUtils.buildAdRequest(mediationAdRequest, bundle2);
            inMobiBanner.load();
            return;
        }
        mediationBannerListener.onAdFailedToLoad(this, 1);
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.mWrappedAdView;
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        if (!sIsAppInitialized.booleanValue()) {
            InMobiSdk.init(context, bundle.getString("accountid"), InMobiConsent.getConsentObj());
            sIsAppInitialized = true;
        }
        this.mInterstitialListener = mediationInterstitialListener;
        this.mAdInterstitial = new InMobiInterstitial(context, Long.parseLong(bundle.getString("placementid")), new InterstitialAdEventListener() { // from class: com.google.ads.mediation.inmobi.InMobiAdapter.2
            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onUserLeftApplication");
                InMobiAdapter.this.mInterstitialListener.onAdLeftApplication(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onRewardsUnlocked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d(InMobiAdapter.TAG, "InMobi Interstitial onRewardsUnlocked.");
                if (map != null) {
                    Iterator<Object> it = map.keySet().iterator();
                    while (it.hasNext()) {
                        String string = it.next().toString();
                        Log.d("Rewards: ", string + ":" + map.get(string).toString());
                    }
                }
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "Ad Display failed.");
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "Ad Will Display.");
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdLoadSucceeded");
                InMobiAdapter.this.mInterstitialListener.onAdLoaded(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                InMobiAdapter.this.mInterstitialListener.onAdFailedToLoad(InMobiAdapter.this, InMobiAdapter.getAdRequestErrorCode(inMobiAdRequestStatus.getStatusCode()));
                Log.d(InMobiAdapter.TAG, "onAdLoadFailed: " + inMobiAdRequestStatus.getMessage());
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "InMobi Ad server responded with an Ad.");
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdDisplayed");
                InMobiAdapter.this.mInterstitialListener.onAdOpened(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdDismissed");
                InMobiAdapter.this.mInterstitialListener.onAdClosed(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdClicked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d(InMobiAdapter.TAG, "InterstitialClicked");
                InMobiAdapter.this.mInterstitialListener.onAdClicked(InMobiAdapter.this);
            }
        });
        if (mediationAdRequest.getKeywords() != null) {
            this.mAdInterstitial.setKeywords(TextUtils.join(", ", mediationAdRequest.getKeywords()));
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("tp", "c_admob");
        isTaggedForChildDirectedTreatment(mediationAdRequest, map);
        this.mAdInterstitial.setExtras(map);
        if (sDisableHardwareFlag.booleanValue()) {
            this.mAdInterstitial.disableHardwareAcceleration();
        }
        InMobiAdapterUtils.buildAdRequest(mediationAdRequest, bundle2);
        this.mAdInterstitial.load();
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        if (this.mAdInterstitial.isReady()) {
            Log.d(TAG, "Ad is ready to show");
            this.mAdInterstitial.show();
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        Log.d(TAG, "initialize called from InMobiAdapter.");
        this.mRewardedVideoAdListener = mediationRewardedVideoAdListener;
        String string = bundle.getString("accountid");
        if (!sIsAppInitialized.booleanValue()) {
            InMobiSdk.init(context, string, InMobiConsent.getConsentObj());
            sIsAppInitialized = true;
        }
        this.mAdRewarded = new InMobiInterstitial(context, Long.parseLong(bundle.getString("placementid")), new InterstitialAdEventListener() { // from class: com.google.ads.mediation.inmobi.InMobiAdapter.3
            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onRewardsUnlocked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d(InMobiAdapter.TAG, "InMobi RewardedVideo onRewardsUnlocked.");
                if (map != null) {
                    Iterator<Object> it = map.keySet().iterator();
                    while (it.hasNext()) {
                        InMobiAdapter.this.mKey = it.next().toString();
                        InMobiAdapter inMobiAdapter = InMobiAdapter.this;
                        inMobiAdapter.mValue = map.get(inMobiAdapter.mKey).toString();
                        Log.d("Rewards: ", InMobiAdapter.this.mKey + ":" + InMobiAdapter.this.mValue);
                    }
                }
                InMobiAdapter.this.mRewardedVideoAdListener.onVideoCompleted(InMobiAdapter.this);
                InMobiAdapter.this.mRewardedVideoAdListener.onRewarded(InMobiAdapter.this, new RewardItem() { // from class: com.google.ads.mediation.inmobi.InMobiAdapter.3.1
                    @Override // com.google.android.gms.ads.reward.RewardItem
                    public String getType() {
                        return InMobiAdapter.this.mKey;
                    }

                    @Override // com.google.android.gms.ads.reward.RewardItem
                    public int getAmount() {
                        if (InMobiAdapter.this.mValue != null && !"".equalsIgnoreCase(InMobiAdapter.this.mValue)) {
                            try {
                                return Integer.parseInt(InMobiAdapter.this.mValue);
                            } catch (NumberFormatException e) {
                                Log.e(InMobiAdapter.TAG, "Reward value should be of type integer:" + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                        return 0;
                    }
                });
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "Ad Display failed.");
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "Ad Will Display.");
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdDisplayed");
                InMobiAdapter.this.mRewardedVideoAdListener.onAdOpened(InMobiAdapter.this);
                InMobiAdapter.this.mRewardedVideoAdListener.onVideoStarted(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdDismissed");
                InMobiAdapter.this.mRewardedVideoAdListener.onAdClosed(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdClicked(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d(InMobiAdapter.TAG, "onInterstitialClicked called");
                InMobiAdapter.this.mRewardedVideoAdListener.onAdClicked(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onAdLoadSucceeded");
                InMobiAdapter.this.mRewardedVideoAdListener.onAdLoaded(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                InMobiAdapter.this.mRewardedVideoAdListener.onAdFailedToLoad(InMobiAdapter.this, InMobiAdapter.getAdRequestErrorCode(inMobiAdRequestStatus.getStatusCode()));
                Log.d(InMobiAdapter.TAG, "onAdLoadFailed: " + inMobiAdRequestStatus.getMessage());
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "InMobi Ad server responded with an Ad.");
            }

            @Override // com.inmobi.ads.listeners.InterstitialAdEventListener
            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
                Log.d(InMobiAdapter.TAG, "onUserLeftApplication");
                InMobiAdapter.this.mRewardedVideoAdListener.onAdLeftApplication(InMobiAdapter.this);
            }
        });
        this.mIsRewardedVideoAdAdapterInitialized = true;
        this.mRewardedVideoAdListener.onInitializationSucceeded(this);
        if (mediationAdRequest.getKeywords() != null) {
            Log.d(TAG, "keyword is present:" + mediationAdRequest.getKeywords().toString());
            this.mAdRewarded.setKeywords(TextUtils.join(", ", mediationAdRequest.getKeywords()));
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("tp", "c_admob");
        isTaggedForChildDirectedTreatment(mediationAdRequest, map);
        this.mAdRewarded.setExtras(map);
        if (sDisableHardwareFlag.booleanValue()) {
            this.mAdRewarded.disableHardwareAcceleration();
        }
        InMobiAdapterUtils.buildAdRequest(mediationAdRequest, bundle2);
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        InMobiInterstitial inMobiInterstitial = this.mAdRewarded;
        if (inMobiInterstitial != null) {
            inMobiInterstitial.load();
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public void showVideo() {
        if (this.mAdRewarded.isReady()) {
            this.mAdRewarded.show();
        }
    }

    @Override // com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter
    public boolean isInitialized() {
        return this.mIsRewardedVideoAdAdapterInitialized && sIsAppInitialized.booleanValue();
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void requestNativeAd(final Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        this.mNativeMedAdReq = nativeMediationAdRequest;
        if (!sIsAppInitialized.booleanValue() && bundle != null) {
            InMobiSdk.init(context, bundle.getString("accountid"), InMobiConsent.getConsentObj());
            sIsAppInitialized = true;
        }
        this.mNativeListener = mediationNativeListener;
        if (!Boolean.valueOf(nativeMediationAdRequest.isAppInstallAdRequested() && nativeMediationAdRequest.isContentAdRequested()).booleanValue()) {
            this.mNativeListener.onAdFailedToLoad(this, 1);
            return;
        }
        InMobiNative inMobiNative = new InMobiNative(context, Long.parseLong(bundle.getString("placementid")), new NativeAdEventListener() { // from class: com.google.ads.mediation.inmobi.InMobiAdapter.4
            @Override // com.inmobi.ads.listeners.NativeAdEventListener
            public void onAdFullScreenWillDisplay(InMobiNative inMobiNative2) {
            }

            @Override // com.inmobi.ads.listeners.NativeAdEventListener
            public void onAdStatusChanged(@NonNull InMobiNative inMobiNative2) {
            }

            @Override // com.inmobi.ads.listeners.NativeAdEventListener
            public void onAdLoadSucceeded(InMobiNative inMobiNative2) {
                System.out.println(" [ InMobi Native Ad ] : onAdLoadSucceeded ");
                Log.d(InMobiAdapter.TAG, "onAdLoadSucceeded");
                if (inMobiNative2 == null) {
                    return;
                }
                NativeAdOptions nativeAdOptions = InMobiAdapter.this.mNativeMedAdReq.getNativeAdOptions();
                if (nativeAdOptions != null) {
                    InMobiAdapter.this.mIsOnlyUrl = Boolean.valueOf(nativeAdOptions.shouldReturnUrlsForImageAssets());
                }
                InMobiAdapter inMobiAdapter = InMobiAdapter.this;
                new InMobiAppInstallNativeAdMapper(inMobiAdapter, inMobiNative2, inMobiAdapter.mIsOnlyUrl, InMobiAdapter.this.mNativeListener).mapAppInstallAd(context);
            }

            @Override // com.inmobi.ads.listeners.NativeAdEventListener
            public void onAdLoadFailed(InMobiNative inMobiNative2, InMobiAdRequestStatus inMobiAdRequestStatus) {
                InMobiAdapter.this.mNativeListener.onAdFailedToLoad(InMobiAdapter.this, InMobiAdapter.getAdRequestErrorCode(inMobiAdRequestStatus.getStatusCode()));
                Log.d(InMobiAdapter.TAG, "onAdLoadFailed: " + inMobiAdRequestStatus.getMessage());
            }

            @Override // com.inmobi.ads.listeners.NativeAdEventListener
            public void onAdFullScreenDismissed(InMobiNative inMobiNative2) {
                Log.d(InMobiAdapter.TAG, "onAdDismissed");
                InMobiAdapter.this.mNativeListener.onAdClosed(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.NativeAdEventListener
            public void onAdFullScreenDisplayed(InMobiNative inMobiNative2) {
                InMobiAdapter.this.mNativeListener.onAdOpened(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.NativeAdEventListener
            public void onUserWillLeaveApplication(InMobiNative inMobiNative2) {
                Log.d("InMobiAdapter", "onUserLeftApplication");
                InMobiAdapter.this.mNativeListener.onAdLeftApplication(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.NativeAdEventListener
            public void onAdImpressed(@NonNull InMobiNative inMobiNative2) {
                Log.d(InMobiAdapter.TAG, "InMobi impression recorded successfully");
                InMobiAdapter.this.mNativeListener.onAdImpression(InMobiAdapter.this);
            }

            @Override // com.inmobi.ads.listeners.NativeAdEventListener
            public void onAdClicked(@NonNull InMobiNative inMobiNative2) {
                InMobiAdapter.this.mNativeListener.onAdClicked(InMobiAdapter.this);
            }
        });
        Set<String> keywords = nativeMediationAdRequest.getKeywords();
        if (keywords != null) {
            inMobiNative.setKeywords(TextUtils.join(", ", keywords));
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("tp", "c_admob");
        isTaggedForChildDirectedTreatment(nativeMediationAdRequest, map);
        inMobiNative.setExtras(map);
        InMobiAdapterUtils.buildAdRequest(nativeMediationAdRequest, bundle2);
        inMobiNative.load();
    }
}
