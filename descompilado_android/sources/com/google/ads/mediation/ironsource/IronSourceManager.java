package com.google.ads.mediation.ironsource;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
class IronSourceManager implements ISDemandOnlyRewardedVideoListener, ISDemandOnlyInterstitialListener {
    private static final IronSourceManager instance = new IronSourceManager();
    private ConcurrentHashMap<String, WeakReference<IronSourceMediationAdapter>> availableInstances = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, WeakReference<IronSourceAdapter>> availableInterstitialInstances = new ConcurrentHashMap<>();

    static IronSourceManager getInstance() {
        return instance;
    }

    private IronSourceManager() {
        IronSource.setISDemandOnlyRewardedVideoListener(this);
        IronSource.setISDemandOnlyInterstitialListener(this);
    }

    void initIronSourceSDK(Activity activity, String str, List<IronSource.AD_UNIT> list) {
        IronSource.setMediationType("AdMob310");
        if (list.size() > 0) {
            IronSource.initISDemandOnly(activity, str, (IronSource.AD_UNIT[]) list.toArray(new IronSource.AD_UNIT[list.size()]));
        }
    }

    void loadInterstitial(String str, @NonNull WeakReference<IronSourceAdapter> weakReference) {
        if (TextUtils.isEmpty(str) || weakReference == null) {
            log("loadInterstitial- instanceId / weakAdapter is null");
            return;
        }
        IronSourceAdapter ironSourceAdapter = weakReference.get();
        if (ironSourceAdapter == null) {
            log("loadInterstitial - ironSourceAdapter is null");
        } else if (canLoadInterstitialInstance(str)) {
            registerISInterstitialAdapter(str, weakReference);
            IronSource.loadISDemandOnlyInterstitial(str);
        } else {
            ironSourceAdapter.onInterstitialAdLoadFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, "interstitial instance already exists, couldn't load another one at the same time!"));
        }
    }

    void loadRewardedVideo(String str, @NonNull WeakReference<IronSourceMediationAdapter> weakReference) {
        if (str == null || weakReference == null) {
            log("loadRewardedVideo - instanceId / weakAdapter is null");
            return;
        }
        IronSourceMediationAdapter ironSourceMediationAdapter = weakReference.get();
        if (ironSourceMediationAdapter == null) {
            log("loadRewardedVideo - ironSourceMediationAdapter is null");
        } else {
            if (canLoadRewardedVideoInstance(str)) {
                changeInstanceState(ironSourceMediationAdapter, IronSourceMediationAdapter.INSTANCE_STATE.LOCKED);
                registerISRewardedVideoAdapter(str, weakReference);
                IronSource.loadISDemandOnlyRewardedVideo(str);
                return;
            }
            ironSourceMediationAdapter.onRewardedVideoAdLoadFailed(str, new IronSourceError(IronSourceError.ERROR_CODE_GENERIC, "instance already exists, couldn't load another one in the same time!"));
        }
    }

    private boolean canLoadInterstitialInstance(String str) {
        return !isIsInterstitialAdapterRegistered(str) || isRegisteredInterstitialAdapterCanLoad(str);
    }

    private boolean canLoadRewardedVideoInstance(String str) {
        return !isISRewardedVideoAdapterRegistered(str) || isRegisteredRewardedVideoAdapterCanLoad(str);
    }

    private boolean isRegisteredInterstitialAdapterCanLoad(String str) {
        WeakReference<IronSourceAdapter> weakReference = this.availableInterstitialInstances.get(str);
        return weakReference == null || weakReference.get() == null;
    }

    private boolean isRegisteredRewardedVideoAdapterCanLoad(String str) {
        IronSourceMediationAdapter ironSourceMediationAdapter;
        WeakReference<IronSourceMediationAdapter> weakReference = this.availableInstances.get(str);
        return weakReference == null || (ironSourceMediationAdapter = weakReference.get()) == null || ironSourceMediationAdapter.getInstanceState().equals(IronSourceMediationAdapter.INSTANCE_STATE.CAN_LOAD);
    }

    void showRewardedVideo(String str) {
        IronSource.showISDemandOnlyRewardedVideo(str);
    }

    void showInterstitial(String str) {
        IronSource.showISDemandOnlyInterstitial(str);
    }

    private void registerISInterstitialAdapter(@NonNull String str, @NonNull WeakReference<IronSourceAdapter> weakReference) {
        if (weakReference == null) {
            log("registerISInterstitialAdapter - weakAdapter is null");
        } else if (weakReference.get() == null) {
            log("registerISInterstitialAdapter - ironSourceMediationAdapter is null");
        } else {
            this.availableInterstitialInstances.put(str, weakReference);
        }
    }

    private void registerISRewardedVideoAdapter(@NonNull String str, @NonNull WeakReference<IronSourceMediationAdapter> weakReference) {
        if (weakReference == null) {
            log("registerISRewardedVideoAdapter - weakAdapter is null");
        } else if (weakReference.get() == null) {
            log("registerISRewardedVideoAdapter - ironSourceMediationAdapter is null");
        } else {
            this.availableInstances.put(str, weakReference);
        }
    }

    private boolean isIsInterstitialAdapterRegistered(@NonNull String str) {
        WeakReference<IronSourceAdapter> weakReference = this.availableInterstitialInstances.get(str);
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    private boolean isISRewardedVideoAdapterRegistered(@NonNull String str) {
        WeakReference<IronSourceMediationAdapter> weakReference = this.availableInstances.get(str);
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener
    public void onRewardedVideoAdLoadSuccess(String str) {
        IronSourceMediationAdapter ironSourceMediationAdapter;
        log(String.format("IronSourceManager got RV Load success for instance %s", str));
        WeakReference<IronSourceMediationAdapter> weakReference = this.availableInstances.get(str);
        if (weakReference == null || (ironSourceMediationAdapter = weakReference.get()) == null) {
            return;
        }
        ironSourceMediationAdapter.onRewardedVideoAdLoadSuccess(str);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener
    public void onRewardedVideoAdLoadFailed(String str, IronSourceError ironSourceError) {
        IronSourceMediationAdapter ironSourceMediationAdapter;
        log(String.format("IronSourceManager got RV Load failed for instance %s", str));
        WeakReference<IronSourceMediationAdapter> weakReference = this.availableInstances.get(str);
        if (weakReference == null || (ironSourceMediationAdapter = weakReference.get()) == null) {
            return;
        }
        changeInstanceState(ironSourceMediationAdapter, IronSourceMediationAdapter.INSTANCE_STATE.CAN_LOAD);
        ironSourceMediationAdapter.onRewardedVideoAdLoadFailed(str, ironSourceError);
    }

    private void changeInstanceState(IronSourceMediationAdapter ironSourceMediationAdapter, IronSourceMediationAdapter.INSTANCE_STATE instance_state) {
        if (ironSourceMediationAdapter == null) {
            log("changeInstanceState - IronSourceMediationAdapter is null");
        } else {
            log(String.format("IronSourceManager change state to %s", instance_state));
            ironSourceMediationAdapter.setInstanceState(instance_state);
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener
    public void onRewardedVideoAdOpened(String str) {
        IronSourceMediationAdapter ironSourceMediationAdapter;
        log(String.format("IronSourceManager got RV ad opened for instance %s", str));
        WeakReference<IronSourceMediationAdapter> weakReference = this.availableInstances.get(str);
        if (weakReference == null || (ironSourceMediationAdapter = weakReference.get()) == null) {
            return;
        }
        ironSourceMediationAdapter.onRewardedVideoAdOpened(str);
    }

    private void log(String str) {
        Log.d(IronSourceAdapterUtils.TAG, str);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener
    public void onRewardedVideoAdClosed(String str) {
        IronSourceMediationAdapter ironSourceMediationAdapter;
        log(String.format("IronSourceManager got RV ad closed for instance %s", str));
        WeakReference<IronSourceMediationAdapter> weakReference = this.availableInstances.get(str);
        if (weakReference == null || (ironSourceMediationAdapter = weakReference.get()) == null) {
            return;
        }
        changeInstanceState(ironSourceMediationAdapter, IronSourceMediationAdapter.INSTANCE_STATE.CAN_LOAD);
        ironSourceMediationAdapter.onRewardedVideoAdClosed(str);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener
    public void onRewardedVideoAdShowFailed(String str, IronSourceError ironSourceError) {
        IronSourceMediationAdapter ironSourceMediationAdapter;
        log(String.format("IronSourceManager got RV show failed for instance %s", str));
        WeakReference<IronSourceMediationAdapter> weakReference = this.availableInstances.get(str);
        if (weakReference == null || (ironSourceMediationAdapter = weakReference.get()) == null) {
            return;
        }
        changeInstanceState(ironSourceMediationAdapter, IronSourceMediationAdapter.INSTANCE_STATE.CAN_LOAD);
        ironSourceMediationAdapter.onRewardedVideoAdShowFailed(str, ironSourceError);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener
    public void onRewardedVideoAdClicked(String str) {
        IronSourceMediationAdapter ironSourceMediationAdapter;
        log(String.format("IronSourceManager got RV ad clicked for instance %s", str));
        WeakReference<IronSourceMediationAdapter> weakReference = this.availableInstances.get(str);
        if (weakReference == null || (ironSourceMediationAdapter = weakReference.get()) == null) {
            return;
        }
        ironSourceMediationAdapter.onRewardedVideoAdClicked(str);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener
    public void onRewardedVideoAdRewarded(String str) {
        IronSourceMediationAdapter ironSourceMediationAdapter;
        log(String.format("IronSourceManager got RV ad rewarded for instance %s", str));
        WeakReference<IronSourceMediationAdapter> weakReference = this.availableInstances.get(str);
        if (weakReference == null || (ironSourceMediationAdapter = weakReference.get()) == null) {
            return;
        }
        ironSourceMediationAdapter.onRewardedVideoAdRewarded(str);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener
    public void onInterstitialAdReady(String str) {
        IronSourceAdapter ironSourceAdapter;
        log(String.format("IronSourceManager got interstitial Load success for instance %s", str));
        WeakReference<IronSourceAdapter> weakReference = this.availableInterstitialInstances.get(str);
        if (weakReference == null || (ironSourceAdapter = weakReference.get()) == null) {
            return;
        }
        ironSourceAdapter.onInterstitialAdReady(str);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener
    public void onInterstitialAdLoadFailed(String str, IronSourceError ironSourceError) {
        IronSourceAdapter ironSourceAdapter;
        log(String.format("IronSourceManager got interstitial Load failed for instance %s", str));
        WeakReference<IronSourceAdapter> weakReference = this.availableInterstitialInstances.get(str);
        if (weakReference == null || (ironSourceAdapter = weakReference.get()) == null) {
            return;
        }
        ironSourceAdapter.onInterstitialAdLoadFailed(str, ironSourceError);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener
    public void onInterstitialAdOpened(String str) {
        IronSourceAdapter ironSourceAdapter;
        log(String.format("IronSourceManager got interstitial ad opened for instance %s", str));
        WeakReference<IronSourceAdapter> weakReference = this.availableInterstitialInstances.get(str);
        if (weakReference == null || (ironSourceAdapter = weakReference.get()) == null) {
            return;
        }
        ironSourceAdapter.onInterstitialAdOpened(str);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener
    public void onInterstitialAdClosed(String str) {
        IronSourceAdapter ironSourceAdapter;
        log(String.format("IronSourceManager got interstitial ad closed for instance %s", str));
        WeakReference<IronSourceAdapter> weakReference = this.availableInterstitialInstances.get(str);
        if (weakReference == null || (ironSourceAdapter = weakReference.get()) == null) {
            return;
        }
        ironSourceAdapter.onInterstitialAdClosed(str);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener
    public void onInterstitialAdShowFailed(String str, IronSourceError ironSourceError) {
        IronSourceAdapter ironSourceAdapter;
        log(String.format("IronSourceManager got interstitial show failed for instance %s", str));
        WeakReference<IronSourceAdapter> weakReference = this.availableInterstitialInstances.get(str);
        if (weakReference == null || (ironSourceAdapter = weakReference.get()) == null) {
            return;
        }
        ironSourceAdapter.onInterstitialAdShowFailed(str, ironSourceError);
    }

    @Override // com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener
    public void onInterstitialAdClicked(String str) {
        IronSourceAdapter ironSourceAdapter;
        log(String.format("IronSourceManager got interstitial ad clicked for instance %s", str));
        WeakReference<IronSourceAdapter> weakReference = this.availableInterstitialInstances.get(str);
        if (weakReference == null || (ironSourceAdapter = weakReference.get()) == null) {
            return;
        }
        ironSourceAdapter.onInterstitialAdClicked(str);
    }
}
