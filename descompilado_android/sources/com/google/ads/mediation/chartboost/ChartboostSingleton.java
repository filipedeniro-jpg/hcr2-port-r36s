package com.google.ads.mediation.chartboost;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ChartboostSingleton {
    private static ChartboostSingletonDelegate chartboostSingletonDelegate;
    private static boolean mIsChartboostInitialized;
    private static boolean mIsChartboostInitializing;
    private static HashMap<String, WeakReference<AbstractChartboostAdapterDelegate>> mInterstitialDelegates = new HashMap<>();
    private static HashMap<String, WeakReference<AbstractChartboostAdapterDelegate>> mRewardedDelegates = new HashMap<>();

    private static ChartboostSingletonDelegate getInstance() {
        if (chartboostSingletonDelegate == null) {
            chartboostSingletonDelegate = new ChartboostSingletonDelegate();
        }
        return chartboostSingletonDelegate;
    }

    private static void addInterstitialDelegate(String str, AbstractChartboostAdapterDelegate abstractChartboostAdapterDelegate) {
        if (TextUtils.isEmpty(str) || abstractChartboostAdapterDelegate == null) {
            return;
        }
        mInterstitialDelegates.put(str, new WeakReference<>(abstractChartboostAdapterDelegate));
    }

    private static void addRewardedDelegate(String str, AbstractChartboostAdapterDelegate abstractChartboostAdapterDelegate) {
        if (TextUtils.isEmpty(str) || abstractChartboostAdapterDelegate == null) {
            return;
        }
        mRewardedDelegates.put(str, new WeakReference<>(abstractChartboostAdapterDelegate));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WeakReference<AbstractChartboostAdapterDelegate> getInterstitialDelegate(String str) {
        if (TextUtils.isEmpty(str) || !mInterstitialDelegates.containsKey(str)) {
            return null;
        }
        return mInterstitialDelegates.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WeakReference<AbstractChartboostAdapterDelegate> getRewardedDelegate(String str) {
        if (TextUtils.isEmpty(str) || !mRewardedDelegates.containsKey(str)) {
            return null;
        }
        return mRewardedDelegates.get(str);
    }

    public static void startChartboostInterstitial(Context context, AbstractChartboostAdapterDelegate abstractChartboostAdapterDelegate) {
        String location = abstractChartboostAdapterDelegate.getChartboostParams().getLocation();
        WeakReference<AbstractChartboostAdapterDelegate> interstitialDelegate = getInterstitialDelegate(location);
        if (interstitialDelegate != null && interstitialDelegate.get() != null) {
            Log.w(ChartboostMediationAdapter.TAG, "An ad has already been requested for the location: " + location);
            abstractChartboostAdapterDelegate.didFailToLoadInterstitial(location, CBError.CBImpressionError.NO_AD_FOUND);
            return;
        }
        addInterstitialDelegate(location, abstractChartboostAdapterDelegate);
        startChartboost((Activity) context, abstractChartboostAdapterDelegate.getChartboostParams(), abstractChartboostAdapterDelegate);
    }

    public static void startChartboostRewardedVideo(Context context, AbstractChartboostAdapterDelegate abstractChartboostAdapterDelegate) {
        String location = abstractChartboostAdapterDelegate.getChartboostParams().getLocation();
        WeakReference<AbstractChartboostAdapterDelegate> rewardedDelegate = getRewardedDelegate(location);
        if (rewardedDelegate != null && rewardedDelegate.get() != null) {
            Log.w(ChartboostMediationAdapter.TAG, "An ad has already been requested for the location: " + location);
            abstractChartboostAdapterDelegate.didFailToLoadRewardedVideo(location, CBError.CBImpressionError.NO_AD_FOUND);
            return;
        }
        addRewardedDelegate(location, abstractChartboostAdapterDelegate);
        startChartboost((Activity) context, abstractChartboostAdapterDelegate.getChartboostParams(), abstractChartboostAdapterDelegate);
    }

    private static void startChartboost(Activity activity, ChartboostParams chartboostParams, AbstractChartboostAdapterDelegate abstractChartboostAdapterDelegate) {
        if (mIsChartboostInitializing) {
            return;
        }
        if (chartboostParams.getFramework() != null && !TextUtils.isEmpty(chartboostParams.getFrameworkVersion())) {
            Chartboost.setFramework(chartboostParams.getFramework(), chartboostParams.getFrameworkVersion());
        }
        if (!mIsChartboostInitialized) {
            mIsChartboostInitializing = true;
            Chartboost.startWithAppId(activity, chartboostParams.getAppId(), chartboostParams.getAppSignature());
            Chartboost.setMediation(Chartboost.CBMediation.CBMediationAdMob, BuildConfig.VERSION_NAME);
            Chartboost.setLoggingLevel(CBLogging.Level.INTEGRATION);
            Chartboost.setDelegate(getInstance());
            Chartboost.setAutoCacheAds(true);
            Chartboost.onCreate(activity);
            Chartboost.onStart(activity);
            Chartboost.onResume(activity);
            return;
        }
        abstractChartboostAdapterDelegate.didInitialize();
    }

    protected static void loadInterstitialAd(AbstractChartboostAdapterDelegate abstractChartboostAdapterDelegate) {
        String location = abstractChartboostAdapterDelegate.getChartboostParams().getLocation();
        if (Chartboost.hasInterstitial(location)) {
            abstractChartboostAdapterDelegate.didCacheInterstitial(location);
        } else {
            Chartboost.cacheInterstitial(location);
        }
    }

    protected static void showInterstitialAd(AbstractChartboostAdapterDelegate abstractChartboostAdapterDelegate) {
        Chartboost.showInterstitial(abstractChartboostAdapterDelegate.getChartboostParams().getLocation());
    }

    protected static void loadRewardedVideoAd(AbstractChartboostAdapterDelegate abstractChartboostAdapterDelegate) {
        String location = abstractChartboostAdapterDelegate.getChartboostParams().getLocation();
        if (Chartboost.hasRewardedVideo(location)) {
            abstractChartboostAdapterDelegate.didCacheRewardedVideo(location);
        } else {
            Chartboost.cacheRewardedVideo(location);
        }
    }

    protected static void showRewardedVideoAd(AbstractChartboostAdapterDelegate abstractChartboostAdapterDelegate) {
        Chartboost.showRewardedVideo(abstractChartboostAdapterDelegate.getChartboostParams().getLocation());
    }

    private static final class ChartboostSingletonDelegate extends ChartboostDelegate {
        private ChartboostSingletonDelegate() {
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didInitialize() {
            super.didInitialize();
            boolean unused = ChartboostSingleton.mIsChartboostInitializing = false;
            boolean unused2 = ChartboostSingleton.mIsChartboostInitialized = true;
            for (WeakReference weakReference : ChartboostSingleton.mInterstitialDelegates.values()) {
                if (weakReference.get() != null) {
                    ((AbstractChartboostAdapterDelegate) weakReference.get()).didInitialize();
                }
            }
            for (WeakReference weakReference2 : ChartboostSingleton.mRewardedDelegates.values()) {
                if (weakReference2.get() != null) {
                    ((AbstractChartboostAdapterDelegate) weakReference2.get()).didInitialize();
                }
            }
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCacheInterstitial(String str) {
            super.didCacheInterstitial(str);
            WeakReference interstitialDelegate = ChartboostSingleton.getInterstitialDelegate(str);
            if (interstitialDelegate == null || interstitialDelegate.get() == null) {
                return;
            }
            ((AbstractChartboostAdapterDelegate) interstitialDelegate.get()).didCacheInterstitial(str);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didFailToLoadInterstitial(String str, CBError.CBImpressionError cBImpressionError) {
            super.didFailToLoadInterstitial(str, cBImpressionError);
            WeakReference interstitialDelegate = ChartboostSingleton.getInterstitialDelegate(str);
            if (interstitialDelegate != null && interstitialDelegate.get() != null) {
                ((AbstractChartboostAdapterDelegate) interstitialDelegate.get()).didFailToLoadInterstitial(str, cBImpressionError);
            }
            ChartboostSingleton.mInterstitialDelegates.remove(str);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDisplayInterstitial(String str) {
            super.didDisplayInterstitial(str);
            WeakReference interstitialDelegate = ChartboostSingleton.getInterstitialDelegate(str);
            if (interstitialDelegate == null || interstitialDelegate.get() == null) {
                return;
            }
            ((AbstractChartboostAdapterDelegate) interstitialDelegate.get()).didDisplayInterstitial(str);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDismissInterstitial(String str) {
            super.didDismissInterstitial(str);
            WeakReference interstitialDelegate = ChartboostSingleton.getInterstitialDelegate(str);
            if (interstitialDelegate != null && interstitialDelegate.get() != null) {
                ((AbstractChartboostAdapterDelegate) interstitialDelegate.get()).didDismissInterstitial(str);
            }
            ChartboostSingleton.mInterstitialDelegates.remove(str);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didClickInterstitial(String str) {
            super.didClickInterstitial(str);
            WeakReference interstitialDelegate = ChartboostSingleton.getInterstitialDelegate(str);
            if (interstitialDelegate == null || interstitialDelegate.get() == null) {
                return;
            }
            ((AbstractChartboostAdapterDelegate) interstitialDelegate.get()).didClickInterstitial(str);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCacheRewardedVideo(String str) {
            super.didCacheRewardedVideo(str);
            WeakReference rewardedDelegate = ChartboostSingleton.getRewardedDelegate(str);
            if (rewardedDelegate == null || rewardedDelegate.get() == null) {
                return;
            }
            ((AbstractChartboostAdapterDelegate) rewardedDelegate.get()).didCacheRewardedVideo(str);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didFailToLoadRewardedVideo(String str, CBError.CBImpressionError cBImpressionError) {
            super.didFailToLoadRewardedVideo(str, cBImpressionError);
            WeakReference rewardedDelegate = ChartboostSingleton.getRewardedDelegate(str);
            if (rewardedDelegate != null && rewardedDelegate.get() != null) {
                ((AbstractChartboostAdapterDelegate) rewardedDelegate.get()).didFailToLoadRewardedVideo(str, cBImpressionError);
            }
            ChartboostSingleton.mRewardedDelegates.remove(str);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didClickRewardedVideo(String str) {
            super.didClickRewardedVideo(str);
            WeakReference rewardedDelegate = ChartboostSingleton.getRewardedDelegate(str);
            if (rewardedDelegate == null || rewardedDelegate.get() == null) {
                return;
            }
            ((AbstractChartboostAdapterDelegate) rewardedDelegate.get()).didClickRewardedVideo(str);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDisplayRewardedVideo(String str) {
            super.didDisplayRewardedVideo(str);
            WeakReference rewardedDelegate = ChartboostSingleton.getRewardedDelegate(str);
            if (rewardedDelegate == null || rewardedDelegate.get() == null) {
                return;
            }
            ((AbstractChartboostAdapterDelegate) rewardedDelegate.get()).didDisplayRewardedVideo(str);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didCompleteRewardedVideo(String str, int i) {
            super.didCompleteRewardedVideo(str, i);
            WeakReference rewardedDelegate = ChartboostSingleton.getRewardedDelegate(str);
            if (rewardedDelegate == null || rewardedDelegate.get() == null) {
                return;
            }
            ((AbstractChartboostAdapterDelegate) rewardedDelegate.get()).didCompleteRewardedVideo(str, i);
        }

        @Override // com.chartboost.sdk.ChartboostDelegate, com.chartboost.sdk.a
        public void didDismissRewardedVideo(String str) {
            super.didDismissRewardedVideo(str);
            WeakReference rewardedDelegate = ChartboostSingleton.getRewardedDelegate(str);
            if (rewardedDelegate != null && rewardedDelegate.get() != null) {
                ((AbstractChartboostAdapterDelegate) rewardedDelegate.get()).didDismissRewardedVideo(str);
            }
            ChartboostSingleton.mRewardedDelegates.remove(str);
        }
    }
}
