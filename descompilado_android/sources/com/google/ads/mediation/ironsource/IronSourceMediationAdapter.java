package com.google.ads.mediation.ironsource;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.InitializationCompleteCallback;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.VersionInfo;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import com.ironsource.sdk.constants.Constants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class IronSourceMediationAdapter extends Adapter implements MediationRewardedAd {
    private String mInstanceID;
    private MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mMediationAdLoadCallback;
    private MediationRewardedAdCallback mMediationRewardedAdCallback;
    private INSTANCE_STATE mState = INSTANCE_STATE.START;
    private static AtomicBoolean mDidInitRewardedVideo = new AtomicBoolean(false);
    private static final List<IronSource.AD_UNIT> mAdUnitsToInit = new ArrayList(Collections.singletonList(IronSource.AD_UNIT.REWARDED_VIDEO));

    enum INSTANCE_STATE {
        START,
        CAN_LOAD,
        LOCKED
    }

    INSTANCE_STATE getInstanceState() {
        return this.mState;
    }

    void setInstanceState(INSTANCE_STATE instance_state) {
        this.mState = instance_state;
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public VersionInfo getSDKVersionInfo() {
        int i;
        int i2;
        int i3;
        String[] strArrSplit = IronSourceUtils.getSDKVersion().split("\\.");
        if (strArrSplit.length > 2) {
            i = Integer.parseInt(strArrSplit[0]);
            int i4 = Integer.parseInt(strArrSplit[1]);
            i3 = Integer.parseInt(strArrSplit[2]);
            i2 = i4;
        } else {
            if (strArrSplit.length == 2) {
                int i5 = Integer.parseInt(strArrSplit[0]);
                i2 = Integer.parseInt(strArrSplit[1]);
                i = i5;
            } else {
                i = strArrSplit.length == 1 ? Integer.parseInt(strArrSplit[0]) : 0;
                i2 = 0;
            }
            i3 = 0;
        }
        return new VersionInfo(i, i2, i3);
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public VersionInfo getVersionInfo() {
        String[] strArrSplit = BuildConfig.VERSION_NAME.split("\\.");
        return new VersionInfo(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), (Integer.parseInt(strArrSplit[2]) * 100) + Integer.parseInt(strArrSplit[3]));
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void initialize(Context context, InitializationCompleteCallback initializationCompleteCallback, List<MediationConfiguration> list) {
        if (!(context instanceof Activity)) {
            initializationCompleteCallback.onInitializationFailed("IronSource SDK requires an Activity context to initialize");
            return;
        }
        try {
            HashSet hashSet = new HashSet();
            Iterator<MediationConfiguration> it = list.iterator();
            while (it.hasNext()) {
                String string = it.next().getServerParameters().getString(ServerResponseWrapper.APP_KEY_FIELD);
                if (!TextUtils.isEmpty(string)) {
                    hashSet.add(string);
                }
            }
            String str = "";
            int size = hashSet.size();
            if (size > 0) {
                str = (String) hashSet.iterator().next();
                if (size > 1) {
                    Log.w(IronSourceAdapterUtils.TAG, String.format("Multiple '%s' entries found: %s. Using '%s' to initialize the IronSource SDK.", ServerResponseWrapper.APP_KEY_FIELD, hashSet.toString(), str));
                }
            }
            if (TextUtils.isEmpty(str)) {
                initializationCompleteCallback.onInitializationFailed("IronSource initialization Failed: Missing or Invalid App Key.");
                return;
            }
            if (!mDidInitRewardedVideo.getAndSet(true)) {
                IronSourceManager.getInstance().initIronSourceSDK((Activity) context, str, mAdUnitsToInit);
            }
            initializationCompleteCallback.onInitializationSucceeded();
        } catch (Exception e) {
            initializationCompleteCallback.onInitializationFailed("IronSource initialization failed, " + e.getMessage());
        }
    }

    @Override // com.google.android.gms.ads.mediation.Adapter
    public void loadRewardedAd(MediationRewardedAdConfiguration mediationRewardedAdConfiguration, MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback) {
        this.mMediationAdLoadCallback = mediationAdLoadCallback;
        Bundle serverParameters = mediationRewardedAdConfiguration.getServerParameters();
        if (serverParameters == null) {
            this.mMediationAdLoadCallback.onFailure("IronSource isn't initialized, server parameters are null");
            return;
        }
        this.mInstanceID = serverParameters.getString(Constants.CONVERT_INSTANCE_ID, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        if (!mDidInitRewardedVideo.getAndSet(true)) {
            try {
                Context context = mediationRewardedAdConfiguration.getContext();
                if (!(context instanceof Activity)) {
                    mediationAdLoadCallback.onFailure("IronSource SDK requires an Activity context to initialize");
                    return;
                }
                String string = serverParameters.getString(ServerResponseWrapper.APP_KEY_FIELD);
                if (TextUtils.isEmpty(string)) {
                    mediationAdLoadCallback.onFailure("IronSource initialization failed, make sure that 'appKey' server parameter is added");
                    return;
                }
                Log.d(IronSourceAdapterUtils.TAG, "IronSource Rewarded Video initialization called");
                IronSourceManager.getInstance().initIronSourceSDK((Activity) context, string, mAdUnitsToInit);
                Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource loadRewardedVideo called with instanceId: %s", this.mInstanceID));
                IronSourceManager.getInstance().loadRewardedVideo(this.mInstanceID, new WeakReference<>(this));
                return;
            } catch (Exception e) {
                Log.w(IronSourceAdapterUtils.TAG, "IronSource Initialization failed for Rewarded Video", e);
                this.mMediationAdLoadCallback.onFailure("IronSource initialization failed for Rewarded Video: " + e.getMessage());
                return;
            }
        }
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource loadRewardedVideo called with instanceId: %s", this.mInstanceID));
        IronSourceManager.getInstance().loadRewardedVideo(this.mInstanceID, new WeakReference<>(this));
    }

    @Override // com.google.android.gms.ads.mediation.MediationRewardedAd
    public void showAd(Context context) {
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource showAd called with instanceId: %s", this.mInstanceID));
        IronSourceManager.getInstance().showRewardedVideo(this.mInstanceID);
    }

    public void onRewardedVideoAdLoadSuccess(String str) {
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource load success for instanceId: %s", str));
        this.mMediationRewardedAdCallback = this.mMediationAdLoadCallback.onSuccess(this);
    }

    public void onRewardedVideoAdLoadFailed(String str, IronSourceError ironSourceError) {
        String str2 = String.format("IronSource Rewarded Video failed to load for instance %s with Error: %s", str, ironSourceError.getErrorMessage());
        Log.d(IronSourceAdapterUtils.TAG, str2);
        this.mMediationAdLoadCallback.onFailure(str2);
    }

    public void onRewardedVideoAdOpened(String str) {
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource Rewarded Video opened ad for instance %s", str));
        if (this.mMediationRewardedAdCallback != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceMediationAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceMediationAdapter.this.mMediationRewardedAdCallback.onAdOpened();
                    IronSourceMediationAdapter.this.mMediationRewardedAdCallback.onVideoStart();
                    IronSourceMediationAdapter.this.mMediationRewardedAdCallback.reportAdImpression();
                }
            });
        }
    }

    public void onRewardedVideoAdClosed(String str) {
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource Rewarded Video closed ad for instance %s", str));
        if (this.mMediationRewardedAdCallback != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceMediationAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceMediationAdapter.this.mMediationRewardedAdCallback.onAdClosed();
                }
            });
        }
    }

    public void onRewardedVideoAdRewarded(String str) {
        final IronSourceReward ironSourceReward = new IronSourceReward();
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource Rewarded Video received reward: %d %s, for instance %s", Integer.valueOf(ironSourceReward.getAmount()), ironSourceReward.getType(), str));
        if (this.mMediationRewardedAdCallback != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceMediationAdapter.3
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceMediationAdapter.this.mMediationRewardedAdCallback.onVideoComplete();
                    IronSourceMediationAdapter.this.mMediationRewardedAdCallback.onUserEarnedReward(ironSourceReward);
                }
            });
        }
    }

    public void onRewardedVideoAdShowFailed(String str, IronSourceError ironSourceError) {
        final String str2 = String.format("IronSource Rewarded Video failed to show for instance %s with Error: %s", str, ironSourceError.getErrorMessage());
        Log.w(IronSourceAdapterUtils.TAG, str2);
        if (this.mMediationRewardedAdCallback != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceMediationAdapter.4
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceMediationAdapter.this.mMediationRewardedAdCallback.onAdFailedToShow(str2);
                }
            });
        }
    }

    public void onRewardedVideoAdClicked(String str) {
        Log.d(IronSourceAdapterUtils.TAG, String.format("IronSource Rewarded Video clicked for instance %s", str));
        if (this.mMediationRewardedAdCallback != null) {
            IronSourceAdapterUtils.sendEventOnUIThread(new Runnable() { // from class: com.google.ads.mediation.ironsource.IronSourceMediationAdapter.5
                @Override // java.lang.Runnable
                public void run() {
                    IronSourceMediationAdapter.this.mMediationRewardedAdCallback.reportAdClicked();
                }
            });
        }
    }

    class IronSourceReward implements RewardItem {
        @Override // com.google.android.gms.ads.rewarded.RewardItem
        public int getAmount() {
            return 1;
        }

        @Override // com.google.android.gms.ads.rewarded.RewardItem
        public String getType() {
            return "";
        }

        IronSourceReward() {
        }
    }
}
