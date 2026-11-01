package com.google.ads.mediation.ironsource;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import com.ironsource.sdk.constants.Constants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class IronSourceAdapter implements MediationInterstitialAdapter {
    private String mInstanceID;
    private MediationInterstitialListener mInterstitialListener;
    private static AtomicBoolean mDidInitInterstitial = new AtomicBoolean(false);
    private static final List<IronSource.AD_UNIT> mAdUnitsToInit = new ArrayList(Collections.singletonList(IronSource.AD_UNIT.INTERSTITIAL));

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
        this.mInterstitialListener = mediationInterstitialListener;
        if (!(context instanceof Activity)) {
            Log.e(IronSourceAdapterUtils.TAG, "IronSource SDK requires an Activity context to initialize");
            onISAdFailedToLoad(1);
            return;
        }
        String string = bundle.getString(ServerResponseWrapper.APP_KEY_FIELD);
        if (TextUtils.isEmpty(string)) {
            Log.e(IronSourceAdapterUtils.TAG, String.format("IronSource initialization failed,make sure that the '%s' server parameter is added", ServerResponseWrapper.APP_KEY_FIELD));
            onISAdFailedToLoad(1);
            return;
        }
        this.mInstanceID = bundle.getString(Constants.CONVERT_INSTANCE_ID, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        if (!mDidInitInterstitial.getAndSet(true)) {
            try {
                Log.d(IronSourceAdapterUtils.TAG, String.format("Init IronSource interstitial ad for instance: %s", this.mInstanceID));
                IronSourceManager.getInstance().initIronSourceSDK((Activity) context, string, mAdUnitsToInit);
                Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource load Interstitial called with instanceId: %s", this.mInstanceID));
                IronSourceManager.getInstance().loadInterstitial(this.mInstanceID, new WeakReference<>(this));
                return;
            } catch (Exception e) {
                Log.e(IronSourceAdapterUtils.TAG, String.format("IronSource initialization failed with Error: %s", e.getMessage()));
                onISAdFailedToLoad(0);
                return;
            }
        }
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource loadRewardedVideo called with instanceId: %s", this.mInstanceID));
        IronSourceManager.getInstance().loadInterstitial(this.mInstanceID, new WeakReference<>(this));
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource showInterstitial for instance: %s", this.mInstanceID));
        IronSourceManager.getInstance().showInterstitial(this.mInstanceID);
    }

    private void onISAdFailedToLoad(final int i) {
        Log.e(IronSourceAdapterUtils.TAG, String.format("IronSource Interstitial failed to load for instance %s, Error: %d", this.mInstanceID, Integer.valueOf(i)));
        if (this.mInterstitialListener != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceAdapter.this.mInterstitialListener.onAdFailedToLoad(IronSourceAdapter.this, i);
                }
            });
        }
    }

    public void onInterstitialAdReady(String str) {
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource Interstitial loaded successfully for instance %s ", str));
        if (this.mInterstitialListener != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceAdapter.this.mInterstitialListener.onAdLoaded(IronSourceAdapter.this);
                }
            });
        }
    }

    public void onInterstitialAdLoadFailed(String str, IronSourceError ironSourceError) {
        Log.e(IronSourceAdapterUtils.TAG, String.format("IronSource Interstitial failed to load for instance %s  with Error: %s", str, ironSourceError.getErrorMessage()));
        if (this.mInstanceID.equals(str)) {
            onISAdFailedToLoad(0);
        }
    }

    public void onInterstitialAdOpened(String str) {
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource Interstitial opened ad for instance %s", str));
        if (this.mInterstitialListener != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceAdapter.3
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceAdapter.this.mInterstitialListener.onAdOpened(IronSourceAdapter.this);
                }
            });
        }
    }

    public void onInterstitialAdClosed(String str) {
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource Interstitial closed ad for instance %s", str));
        if (this.mInterstitialListener != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceAdapter.4
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceAdapter.this.mInterstitialListener.onAdClosed(IronSourceAdapter.this);
                }
            });
        }
    }

    public void onInterstitialAdShowFailed(String str, IronSourceError ironSourceError) {
        Log.e(IronSourceAdapterUtils.TAG, String.format("IronSource Interstitial failed to show for instance %s with Error: %s", str, ironSourceError.getErrorMessage()));
        if (this.mInterstitialListener != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceAdapter.5
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceAdapter.this.mInterstitialListener.onAdOpened(IronSourceAdapter.this);
                    IronSourceAdapter.this.mInterstitialListener.onAdClosed(IronSourceAdapter.this);
                }
            });
        }
    }

    public void onInterstitialAdClicked(String str) {
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource Interstitial ad clicked for instance %s", str));
        if (this.mInterstitialListener != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceAdapter.6
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceAdapter.this.mInterstitialListener.onAdClicked(IronSourceAdapter.this);
                    IronSourceAdapter.this.mInterstitialListener.onAdLeftApplication(IronSourceAdapter.this);
                }
            });
        }
    }
}
