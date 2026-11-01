package com.fingersoft.ads_sdk.advertising;

import android.app.Activity;
import android.os.CountDownTimer;
import android.os.Handler;
import com.fingersoft.ads_sdk.advertising.Comparators.InterstitialProviderComparator;
import com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class InterstitialManager {
    private static final long LOAD_TIMEOUT_MS = 20000;
    private AdManager mAdManager;
    private Activity mCurrentActivity;
    public InterstitialEventListener mInterstitialEventListener;
    private CountDownTimer mLoadTimeoutTimer;
    private int mCurrentAdInterstitialIdx = 0;
    private InterstitialProvider mCurrentInterstitialProvider = null;
    private List<InterstitialProvider> mInterstitialProviders = new ArrayList();
    private int mCurrentFailCount = 0;
    protected AdState mInterstitialState = AdState.willLoad;
    private List<String> mFailedProviders = new ArrayList();
    private String mPriorityString = "";

    public enum AdState {
        initialising,
        loading,
        loaded,
        showing,
        dismissed,
        willLoad,
        paused
    }

    public InterstitialManager(AdManager adManager, Activity activity) {
        this.mCurrentActivity = null;
        this.mCurrentActivity = activity;
        this.mAdManager = adManager;
    }

    public InterstitialManager(AdManager adManager, Activity activity, InterstitialEventListener interstitialEventListener) {
        this.mCurrentActivity = null;
        this.mCurrentActivity = activity;
        this.mInterstitialEventListener = interstitialEventListener;
        this.mAdManager = adManager;
    }

    public void addInterstitialProvider(InterstitialProvider interstitialProvider) {
        AdUtils.log("Adding interstitial provider " + interstitialProvider.getName());
        interstitialProvider.setManager(this);
        interstitialProvider.updateName(getProviderCountByID(interstitialProvider.getProviderID()));
        interstitialProvider.create(this.mAdManager);
        AdUtils.log("Initialised " + interstitialProvider.getName());
        this.mInterstitialProviders.add(interstitialProvider);
    }

    private int getProviderCountByID(int i) {
        List<InterstitialProvider> list = this.mInterstitialProviders;
        int i2 = 0;
        if (list == null) {
            return 0;
        }
        Iterator<InterstitialProvider> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getProviderID() == i) {
                i2++;
            }
        }
        return i2;
    }

    public void restartProviders() {
        this.mCurrentAdInterstitialIdx = 0;
    }

    public void setPriorities(String str) {
        this.mPriorityString = str;
    }

    public boolean isEnabled() {
        return this.mInterstitialProviders.size() > 0;
    }

    public void reorderInterstitials(String str) {
        AdUtils.log("New interstitial priorities set " + str);
        if (this.mInterstitialProviders.size() > 0) {
            this.mInterstitialProviders = new InterstitialProviderComparator(str).sort(this.mInterstitialProviders);
        }
    }

    public void onInterstitialViewSuccess() {
        InterstitialEventListener interstitialEventListener = this.mInterstitialEventListener;
        if (interstitialEventListener != null) {
            interstitialEventListener.onDismiss();
        }
        this.mInterstitialState = AdState.willLoad;
    }

    public InterstitialProvider getCurrentProvider() {
        return this.mCurrentInterstitialProvider;
    }

    public int getCurrentInterstitialIndex() {
        return this.mCurrentAdInterstitialIdx;
    }

    public int getInterstitialProviderCount() {
        List<InterstitialProvider> list = this.mInterstitialProviders;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void showInterstitial() {
        try {
            if (this.mCurrentInterstitialProvider == null) {
                if (this.mInterstitialEventListener != null) {
                    this.mInterstitialEventListener.failedToReceiveAd();
                    return;
                }
                return;
            }
            if (this.mInterstitialProviders == null) {
                if (this.mInterstitialEventListener != null) {
                    this.mInterstitialEventListener.failedToReceiveAd();
                    return;
                }
                return;
            }
            if (this.mInterstitialProviders.size() == 0) {
                AdUtils.log("FAILURE :: Load interstitial: No interstitial providers");
                if (this.mInterstitialEventListener != null) {
                    this.mInterstitialEventListener.failedToReceiveAd();
                    return;
                }
                return;
            }
            if (this.mInterstitialState != AdState.loaded) {
                AdUtils.log("Cannot show, state is currently: " + this.mInterstitialState);
                if (this.mInterstitialEventListener != null) {
                    this.mInterstitialEventListener.failedToReceiveAd();
                    return;
                }
                return;
            }
            AdUtils.log("Showing interstitial - " + this.mCurrentInterstitialProvider.getName());
            this.mCurrentInterstitialProvider.show();
            this.mCurrentInterstitialProvider.increaseShowCount();
            if (this.mInterstitialEventListener != null) {
                this.mInterstitialEventListener.onShow();
            }
        } catch (Exception e) {
            AdUtils.log("Error showing interstitial: " + e.getMessage());
        }
    }

    public void loadInterstitial() {
        List<InterstitialProvider> list = this.mInterstitialProviders;
        if (list == null) {
            return;
        }
        if (list.size() == 0) {
            AdUtils.log("FAILURE :: Load interstitial: No interstitial providers");
            return;
        }
        if (this.mFailedProviders.size() >= this.mInterstitialProviders.size()) {
            this.mFailedProviders.clear();
            AdUtils.log("All interstitial providers failed. Will not load this time. Reseting.");
            InterstitialEventListener interstitialEventListener = this.mInterstitialEventListener;
            if (interstitialEventListener != null) {
                interstitialEventListener.allProvidersFailed();
                return;
            }
            return;
        }
        loadInterstitial(0L);
    }

    private void loadInterstitial(long j) {
        AdUtils.log("Called load interstitial");
        try {
            if (this.mInterstitialProviders != null && this.mInterstitialProviders.size() != 0) {
                if (this.mInterstitialState != AdState.willLoad) {
                    AdUtils.log("Cannot load, adstate is: " + this.mInterstitialState);
                    return;
                }
                this.mInterstitialState = AdState.loading;
                if (this.mInterstitialProviders.size() < 1) {
                    return;
                }
                if (this.mCurrentInterstitialProvider != null && !this.mCurrentInterstitialProvider.canShow()) {
                    this.mCurrentInterstitialProvider.interstitialClose();
                    this.mCurrentAdInterstitialIdx++;
                    this.mCurrentInterstitialProvider = null;
                }
                if (this.mCurrentAdInterstitialIdx >= this.mInterstitialProviders.size()) {
                    this.mCurrentAdInterstitialIdx = 0;
                }
                if (this.mCurrentInterstitialProvider == null) {
                    this.mCurrentInterstitialProvider = this.mInterstitialProviders.get(this.mCurrentAdInterstitialIdx);
                }
                AdUtils.log("Loading interstitial - " + this.mCurrentInterstitialProvider.getName() + " current index is: " + this.mCurrentAdInterstitialIdx + ", delay is " + j);
                startLoadTimeoutTimer();
                new Handler().postDelayed(new Runnable() { // from class: com.fingersoft.ads_sdk.advertising.InterstitialManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (InterstitialManager.this.mInterstitialEventListener != null) {
                                InterstitialManager.this.mInterstitialEventListener.onLoad();
                            }
                            InterstitialManager.this.mCurrentInterstitialProvider.loadInterstitial();
                        } catch (Exception e) {
                            AdUtils.log("LoadInterstitial - Caught exception " + e);
                            InterstitialManager.this.onInterstitialViewFailed();
                        }
                    }
                }, j);
                return;
            }
            AdUtils.log("No interstitial providers, returning.");
        } catch (Exception e) {
            AdUtils.log("Error loading interstitial: " + e.getMessage());
            this.mInterstitialState = AdState.willLoad;
        }
    }

    public void onPause() {
        InterstitialProvider interstitialProvider = this.mCurrentInterstitialProvider;
        if (interstitialProvider != null) {
            interstitialProvider.onPause();
        }
    }

    public void onResume(Activity activity) {
        this.mCurrentActivity = activity;
        InterstitialProvider interstitialProvider = this.mCurrentInterstitialProvider;
        if (interstitialProvider != null) {
            interstitialProvider.onResume(activity);
        }
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [com.fingersoft.ads_sdk.advertising.InterstitialManager$2] */
    private void startLoadTimeoutTimer() {
        this.mLoadTimeoutTimer = new CountDownTimer(LOAD_TIMEOUT_MS, LOAD_TIMEOUT_MS) { // from class: com.fingersoft.ads_sdk.advertising.InterstitialManager.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                InterstitialManager.this.timeoutOccurredOnLoad();
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void timeoutOccurredOnLoad() {
        onInterstitialViewFailed();
    }

    private void cancelLoadTimeoutTimer() {
        CountDownTimer countDownTimer = this.mLoadTimeoutTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.mLoadTimeoutTimer = null;
        }
    }

    public void onInterstitialViewFailed() {
        try {
            cancelLoadTimeoutTimer();
            if (this.mInterstitialEventListener != null) {
                this.mInterstitialEventListener.failedToReceiveAd();
            }
            AdUtils.log("Failed to load interstitial");
            if (!this.mFailedProviders.contains(this.mCurrentInterstitialProvider.getName())) {
                this.mFailedProviders.add(this.mCurrentInterstitialProvider.getName());
            }
            this.mCurrentAdInterstitialIdx++;
            this.mCurrentInterstitialProvider = null;
            this.mInterstitialState = AdState.willLoad;
            loadInterstitial();
        } catch (Exception e) {
            AdUtils.log("Error getting interstitial: " + e.getMessage());
        }
    }

    public Activity getActivity() {
        return this.mCurrentActivity;
    }

    public void onInterstitialLoaded() {
        cancelLoadTimeoutTimer();
        InterstitialEventListener interstitialEventListener = this.mInterstitialEventListener;
        if (interstitialEventListener != null) {
            interstitialEventListener.receivedAd();
        }
        this.mInterstitialState = AdState.loaded;
    }

    public List<InterstitialProvider> getProviders() {
        return this.mInterstitialProviders;
    }
}
