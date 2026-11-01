package com.applovin.mediation.adapters;

import android.app.Activity;
import android.text.TextUtils;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewDisplayErrorCode;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.adapter.MaxAdViewAdapter;
import com.applovin.mediation.adapter.MaxAdapter;
import com.applovin.mediation.adapter.MaxAdapterError;
import com.applovin.mediation.adapter.MaxInterstitialAdapter;
import com.applovin.mediation.adapter.MaxRewardedAdapter;
import com.applovin.mediation.adapter.MaxSignalProvider;
import com.applovin.mediation.adapter.listeners.MaxAdViewAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxInterstitialAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxRewardedAdapterListener;
import com.applovin.mediation.adapter.listeners.MaxSignalCollectionListener;
import com.applovin.mediation.adapter.parameters.MaxAdapterInitializationParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterResponseParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterSignalCollectionParameters;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AppLovinMediationAdapter extends MediationAdapterBase implements MaxAdViewAdapter, MaxInterstitialAdapter, MaxRewardedAdapter, MaxSignalProvider {
    private AppLovinAdView mLoadedAdView;
    private AppLovinAd mLoadedInterstitialAd;
    private AppLovinAd mLoadedRewardedAd;
    private MaxReward mPendingReward;

    /* JADX INFO: renamed from: com.applovin.mediation.adapters.AppLovinMediationAdapter$1, reason: invalid class name */
    class AnonymousClass1 implements AppLovinAdLoadListener {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ MaxAdViewAdapterListener val$listener;

        AnonymousClass1(Activity activity, MaxAdViewAdapterListener maxAdViewAdapterListener) {
            this.val$activity = activity;
            this.val$listener = maxAdViewAdapterListener;
        }

        @Override // com.applovin.sdk.AppLovinAdLoadListener
        public void adReceived(final AppLovinAd appLovinAd) {
            AppLovinSdkUtils.runOnUiThread(new Runnable() { // from class: com.applovin.mediation.adapters.AppLovinMediationAdapter.1.1
                @Override // java.lang.Runnable
                public void run() {
                    AppLovinMediationAdapter.this.mLoadedAdView = new AppLovinAdView(AppLovinMediationAdapter.this.getSdk().N(), appLovinAd.getSize(), AnonymousClass1.this.val$activity);
                    AppLovinMediationAdapter.this.mLoadedAdView.setAdDisplayListener(new AppLovinAdDisplayListener() { // from class: com.applovin.mediation.adapters.AppLovinMediationAdapter.1.1.1
                        @Override // com.applovin.sdk.AppLovinAdDisplayListener
                        public void adDisplayed(AppLovinAd appLovinAd2) {
                            AnonymousClass1.this.val$listener.onAdViewAdDisplayed();
                        }

                        @Override // com.applovin.sdk.AppLovinAdDisplayListener
                        public void adHidden(AppLovinAd appLovinAd2) {
                            AnonymousClass1.this.val$listener.onAdViewAdHidden();
                        }
                    });
                    AppLovinMediationAdapter.this.mLoadedAdView.setAdClickListener(new AppLovinAdClickListener() { // from class: com.applovin.mediation.adapters.AppLovinMediationAdapter.1.1.2
                        @Override // com.applovin.sdk.AppLovinAdClickListener
                        public void adClicked(AppLovinAd appLovinAd2) {
                            AnonymousClass1.this.val$listener.onAdViewAdClicked();
                        }
                    });
                    AppLovinMediationAdapter.this.mLoadedAdView.setAdViewEventListener(new AppLovinAdViewEventListener() { // from class: com.applovin.mediation.adapters.AppLovinMediationAdapter.1.1.3
                        @Override // com.applovin.adview.AppLovinAdViewEventListener
                        public void adClosedFullscreen(AppLovinAd appLovinAd2, AppLovinAdView appLovinAdView) {
                            AnonymousClass1.this.val$listener.onAdViewAdCollapsed();
                        }

                        @Override // com.applovin.adview.AppLovinAdViewEventListener
                        public void adFailedToDisplay(AppLovinAd appLovinAd2, AppLovinAdView appLovinAdView, AppLovinAdViewDisplayErrorCode appLovinAdViewDisplayErrorCode) {
                            AnonymousClass1.this.val$listener.onAdViewAdDisplayFailed(AppLovinMediationAdapter.toMaxError(appLovinAdViewDisplayErrorCode));
                        }

                        @Override // com.applovin.adview.AppLovinAdViewEventListener
                        public void adLeftApplication(AppLovinAd appLovinAd2, AppLovinAdView appLovinAdView) {
                        }

                        @Override // com.applovin.adview.AppLovinAdViewEventListener
                        public void adOpenedFullscreen(AppLovinAd appLovinAd2, AppLovinAdView appLovinAdView) {
                            AnonymousClass1.this.val$listener.onAdViewAdExpanded();
                        }
                    });
                    AnonymousClass1.this.val$listener.onAdViewAdLoaded(AppLovinMediationAdapter.this.mLoadedAdView);
                    AppLovinMediationAdapter.this.mLoadedAdView.renderAd(appLovinAd);
                }
            });
        }

        @Override // com.applovin.sdk.AppLovinAdLoadListener
        public void failedToReceiveAd(int i) {
            this.val$listener.onAdViewAdLoadFailed(AppLovinMediationAdapter.toMaxError(i));
        }
    }

    private class RewardListenerWrapper implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener {
        private boolean isFullyWatched;
        private final MaxRewardedAdapterListener listener;

        private RewardListenerWrapper(MaxRewardedAdapterListener maxRewardedAdapterListener) {
            this.isFullyWatched = false;
            this.listener = maxRewardedAdapterListener;
        }

        /* synthetic */ RewardListenerWrapper(AppLovinMediationAdapter appLovinMediationAdapter, MaxRewardedAdapterListener maxRewardedAdapterListener, AnonymousClass1 anonymousClass1) {
            this(maxRewardedAdapterListener);
        }

        @Override // com.applovin.sdk.AppLovinAdClickListener
        public void adClicked(AppLovinAd appLovinAd) {
            this.listener.onRewardedAdClicked();
        }

        @Override // com.applovin.sdk.AppLovinAdDisplayListener
        public void adDisplayed(AppLovinAd appLovinAd) {
            this.listener.onRewardedAdDisplayed();
        }

        @Override // com.applovin.sdk.AppLovinAdDisplayListener
        public void adHidden(AppLovinAd appLovinAd) {
            if (this.isFullyWatched && AppLovinMediationAdapter.this.mPendingReward != null) {
                this.listener.onUserRewarded(AppLovinMediationAdapter.this.mPendingReward);
                AppLovinMediationAdapter.this.mPendingReward = null;
            }
            this.listener.onRewardedAdHidden();
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
            AppLovinMediationAdapter.this.log("User declined to view rewarded ad");
            AppLovinMediationAdapter.this.mPendingReward = null;
            this.listener.onRewardedAdDisplayFailed(MaxAdapterError.REWARD_ERROR);
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map) {
            AppLovinMediationAdapter.this.log("User is over quota: " + map);
            AppLovinMediationAdapter.this.mPendingReward = null;
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map) {
            AppLovinMediationAdapter.this.log("Reward rejected: " + map);
            AppLovinMediationAdapter.this.mPendingReward = null;
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map) {
            String str = map.get("currency");
            String str2 = map.get(AppLovinEventParameters.REVENUE_AMOUNT);
            AppLovinMediationAdapter.this.log("Rewarded verified " + str2 + " " + str);
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                AppLovinMediationAdapter.this.mPendingReward = MaxReward.createDefault();
                return;
            }
            int i = 0;
            try {
                i = (int) Double.parseDouble(str2);
            } catch (NumberFormatException e) {
                AppLovinMediationAdapter.this.log("Failed to parse AppLovin reward amount: " + str2, e);
            }
            AppLovinMediationAdapter.this.mPendingReward = MaxReward.create(i, str);
        }

        @Override // com.applovin.sdk.AppLovinAdRewardListener
        public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
            AppLovinMediationAdapter.this.log("Reward validation request failed with code: " + i);
            AppLovinMediationAdapter.this.mPendingReward = null;
        }

        @Override // com.applovin.sdk.AppLovinAdVideoPlaybackListener
        public void videoPlaybackBegan(AppLovinAd appLovinAd) {
            this.listener.onRewardedAdVideoStarted();
        }

        @Override // com.applovin.sdk.AppLovinAdVideoPlaybackListener
        public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
            this.isFullyWatched = z;
            this.listener.onRewardedAdVideoCompleted();
        }
    }

    public AppLovinMediationAdapter(AppLovinSdk appLovinSdk) {
        super(appLovinSdk);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MaxAdapterError toMaxError(int i) {
        int i2 = 204;
        if (i == -103) {
            i2 = MaxAdapterError.ERROR_CODE_NO_CONNECTION;
        } else if (i != 204) {
            if (i == -1) {
                i2 = MaxAdapterError.ERROR_CODE_INTERNAL_ERROR;
            } else {
                i2 = i >= 500 ? MaxAdapterError.ERROR_CODE_SERVER_ERROR : MaxAdapterError.ERROR_CODE_UNSPECIFIED;
            }
        }
        return new MaxAdapterError(i2, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MaxAdapterError toMaxError(AppLovinAdViewDisplayErrorCode appLovinAdViewDisplayErrorCode) {
        return MaxAdapterError.UNSPECIFIED;
    }

    @Override // com.applovin.mediation.adapter.MaxSignalProvider
    public void collectSignal(MaxAdapterSignalCollectionParameters maxAdapterSignalCollectionParameters, Activity activity, MaxSignalCollectionListener maxSignalCollectionListener) {
        maxSignalCollectionListener.onSignalCollected(getSdk().p().getBidToken());
    }

    @Override // com.applovin.mediation.adapter.MaxAdapter
    public String getAdapterVersion() {
        return getSdkVersion();
    }

    @Override // com.applovin.mediation.adapter.MaxAdapter
    public String getSdkVersion() {
        return AppLovinSdk.VERSION;
    }

    @Override // com.applovin.mediation.adapter.MaxAdapter
    public void initialize(MaxAdapterInitializationParameters maxAdapterInitializationParameters, Activity activity, MaxAdapter.OnCompletionListener onCompletionListener) {
        getSdk().c("max");
        onCompletionListener.onCompletion();
    }

    @Override // com.applovin.mediation.adapter.MaxAdViewAdapter
    public void loadAdViewAd(MaxAdapterResponseParameters maxAdapterResponseParameters, MaxAdFormat maxAdFormat, Activity activity, MaxAdViewAdapterListener maxAdViewAdapterListener) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(activity, maxAdViewAdapterListener);
        if (!TextUtils.isEmpty(maxAdapterResponseParameters.getBidResponse())) {
            getSdk().p().loadNextAdForAdToken(maxAdapterResponseParameters.getBidResponse(), anonymousClass1);
            return;
        }
        if (!TextUtils.isEmpty(maxAdapterResponseParameters.getThirdPartyAdPlacementId())) {
            getSdk().p().loadNextAdForZoneId(maxAdapterResponseParameters.getThirdPartyAdPlacementId(), anonymousClass1);
            return;
        }
        AppLovinAdSize appLovinAdSize = null;
        if (maxAdFormat == MaxAdFormat.BANNER) {
            appLovinAdSize = AppLovinAdSize.BANNER;
        } else if (maxAdFormat == MaxAdFormat.MREC) {
            appLovinAdSize = AppLovinAdSize.MREC;
        } else if (maxAdFormat == MaxAdFormat.LEADER) {
            appLovinAdSize = AppLovinAdSize.LEADER;
        }
        if (appLovinAdSize != null) {
            getSdk().p().loadNextAd(appLovinAdSize, anonymousClass1);
            return;
        }
        log("Failed to load ad for format: " + maxAdFormat);
    }

    @Override // com.applovin.mediation.adapter.MaxInterstitialAdapter
    public void loadInterstitialAd(MaxAdapterResponseParameters maxAdapterResponseParameters, Activity activity, final MaxInterstitialAdapterListener maxInterstitialAdapterListener) {
        AppLovinAdLoadListener appLovinAdLoadListener = new AppLovinAdLoadListener() { // from class: com.applovin.mediation.adapters.AppLovinMediationAdapter.2
            @Override // com.applovin.sdk.AppLovinAdLoadListener
            public void adReceived(AppLovinAd appLovinAd) {
                AppLovinMediationAdapter.this.mLoadedInterstitialAd = appLovinAd;
                maxInterstitialAdapterListener.onInterstitialAdLoaded();
            }

            @Override // com.applovin.sdk.AppLovinAdLoadListener
            public void failedToReceiveAd(int i) {
                maxInterstitialAdapterListener.onInterstitialAdLoadFailed(AppLovinMediationAdapter.toMaxError(i));
            }
        };
        if (!TextUtils.isEmpty(maxAdapterResponseParameters.getBidResponse())) {
            getSdk().p().loadNextAdForAdToken(maxAdapterResponseParameters.getBidResponse(), appLovinAdLoadListener);
        } else if (TextUtils.isEmpty(maxAdapterResponseParameters.getThirdPartyAdPlacementId())) {
            getSdk().p().loadNextAd(AppLovinAdSize.INTERSTITIAL, appLovinAdLoadListener);
        } else {
            getSdk().p().loadNextAdForZoneId(maxAdapterResponseParameters.getThirdPartyAdPlacementId(), appLovinAdLoadListener);
        }
    }

    @Override // com.applovin.mediation.adapter.MaxRewardedAdapter
    public void loadRewardedAd(MaxAdapterResponseParameters maxAdapterResponseParameters, Activity activity, final MaxRewardedAdapterListener maxRewardedAdapterListener) {
        AppLovinAdLoadListener appLovinAdLoadListener = new AppLovinAdLoadListener() { // from class: com.applovin.mediation.adapters.AppLovinMediationAdapter.5
            @Override // com.applovin.sdk.AppLovinAdLoadListener
            public void adReceived(AppLovinAd appLovinAd) {
                AppLovinMediationAdapter.this.mLoadedRewardedAd = appLovinAd;
                maxRewardedAdapterListener.onRewardedAdLoaded();
            }

            @Override // com.applovin.sdk.AppLovinAdLoadListener
            public void failedToReceiveAd(int i) {
                maxRewardedAdapterListener.onRewardedAdLoadFailed(AppLovinMediationAdapter.toMaxError(i));
            }
        };
        if (!TextUtils.isEmpty(maxAdapterResponseParameters.getBidResponse())) {
            getSdk().p().loadNextAdForAdToken(maxAdapterResponseParameters.getBidResponse(), appLovinAdLoadListener);
        } else if (TextUtils.isEmpty(maxAdapterResponseParameters.getThirdPartyAdPlacementId())) {
            getSdk().p().loadNextAdForZoneId("inter_videoa", appLovinAdLoadListener);
        } else {
            getSdk().p().loadNextAdForZoneId(maxAdapterResponseParameters.getThirdPartyAdPlacementId(), appLovinAdLoadListener);
        }
    }

    @Override // com.applovin.mediation.adapter.MaxAdapter
    public void onDestroy() {
        this.mLoadedInterstitialAd = null;
        this.mLoadedRewardedAd = null;
        AppLovinAdView appLovinAdView = this.mLoadedAdView;
        if (appLovinAdView != null) {
            appLovinAdView.destroy();
            this.mLoadedAdView = null;
        }
    }

    @Override // com.applovin.mediation.adapter.MaxInterstitialAdapter
    public void showInterstitialAd(MaxAdapterResponseParameters maxAdapterResponseParameters, Activity activity, final MaxInterstitialAdapterListener maxInterstitialAdapterListener) {
        if (this.mLoadedInterstitialAd == null) {
            maxInterstitialAdapterListener.onInterstitialAdDisplayFailed(MaxAdapterError.AD_NOT_READY);
            return;
        }
        AppLovinInterstitialAdDialog appLovinInterstitialAdDialogCreate = AppLovinInterstitialAd.create(getSdk().N(), activity.getApplicationContext());
        appLovinInterstitialAdDialogCreate.setAdDisplayListener(new AppLovinAdDisplayListener() { // from class: com.applovin.mediation.adapters.AppLovinMediationAdapter.3
            @Override // com.applovin.sdk.AppLovinAdDisplayListener
            public void adDisplayed(AppLovinAd appLovinAd) {
                maxInterstitialAdapterListener.onInterstitialAdDisplayed();
            }

            @Override // com.applovin.sdk.AppLovinAdDisplayListener
            public void adHidden(AppLovinAd appLovinAd) {
                maxInterstitialAdapterListener.onInterstitialAdHidden();
            }
        });
        appLovinInterstitialAdDialogCreate.setAdClickListener(new AppLovinAdClickListener() { // from class: com.applovin.mediation.adapters.AppLovinMediationAdapter.4
            @Override // com.applovin.sdk.AppLovinAdClickListener
            public void adClicked(AppLovinAd appLovinAd) {
                maxInterstitialAdapterListener.onInterstitialAdClicked();
            }
        });
        appLovinInterstitialAdDialogCreate.showAndRender(this.mLoadedInterstitialAd);
    }

    @Override // com.applovin.mediation.adapter.MaxRewardedAdapter
    public void showRewardedAd(MaxAdapterResponseParameters maxAdapterResponseParameters, Activity activity, MaxRewardedAdapterListener maxRewardedAdapterListener) {
        if (this.mLoadedRewardedAd == null) {
            maxRewardedAdapterListener.onRewardedAdLoadFailed(MaxAdapterError.AD_NOT_READY);
            return;
        }
        AppLovinIncentivizedInterstitial appLovinIncentivizedInterstitialCreate = AppLovinIncentivizedInterstitial.create(getSdk().N());
        RewardListenerWrapper rewardListenerWrapper = new RewardListenerWrapper(this, maxRewardedAdapterListener, null);
        appLovinIncentivizedInterstitialCreate.show(this.mLoadedRewardedAd, activity, rewardListenerWrapper, rewardListenerWrapper, rewardListenerWrapper, rewardListenerWrapper);
    }
}
