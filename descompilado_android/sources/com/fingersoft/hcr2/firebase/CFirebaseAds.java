package com.fingersoft.hcr2.firebase;

import android.app.Activity;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.chartboost.sdk.Chartboost;
import com.fingersoft.hcr2.AppActivity;
import com.fingersoft.hcr2.Log;
import com.google.ads.mediation.inmobi.InMobiConsent;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.inmobi.sdk.InMobiSdk;
import com.ironsource.mediationsdk.IronSource;
import com.my.target.common.MyTargetPrivacy;
import com.unity3d.ads.metadata.MetaData;
import com.vungle.mediation.VungleConsent;
import com.vungle.warren.Vungle;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CFirebaseAds {
    private final long SEMAPHORE_TIMEOUT_MS = 3000;
    private Activity mActivity;
    private FirebaseAdListener mAdListener;
    private CFirebaseInterstitial mFirebaseInterstitial;
    private CFirebaseRewardedVideo mFirebaseRewardedVideo;
    private boolean mHasCampaignsReturnValue;
    private Semaphore mHasCampaignsSemaphore;
    private String mInterstitialBackfillID;
    private String mInterstitialID;
    private boolean mShowVideoAdReturnValue;
    private Semaphore mShowVideoAdSemaphore;
    private String mVideoUnitID;

    public CFirebaseAds(Activity activity, String str, String str2, String str3, FirebaseAdListener firebaseAdListener) {
        this.mActivity = activity;
        this.mVideoUnitID = str;
        this.mInterstitialID = str2;
        this.mInterstitialBackfillID = str3;
        this.mAdListener = firebaseAdListener;
        setGDPRConsentStatus(false, false);
        Log.d("Firebase,", "Admob video id: " + this.mVideoUnitID);
        Log.d("Firebase,", "Admob interstitial id: " + this.mInterstitialID);
        MobileAds.initialize(this.mActivity, new OnInitializationCompleteListener() { // from class: com.fingersoft.hcr2.firebase.CFirebaseAds.1
            @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d("Firebase,", "Initialization Status: " + initializationStatus.getAdapterStatusMap());
                CFirebaseAds cFirebaseAds = CFirebaseAds.this;
                cFirebaseAds.mFirebaseRewardedVideo = new CFirebaseRewardedVideo(cFirebaseAds.mActivity, CFirebaseAds.this.mVideoUnitID, CFirebaseAds.this.mAdListener);
                CFirebaseAds cFirebaseAds2 = CFirebaseAds.this;
                cFirebaseAds2.mFirebaseInterstitial = new CFirebaseInterstitial(cFirebaseAds2.mActivity, CFirebaseAds.this.mInterstitialID, CFirebaseAds.this.mInterstitialBackfillID, CFirebaseAds.this.mAdListener);
                CFirebaseAds.this.mHasCampaignsSemaphore = new Semaphore(0);
                CFirebaseAds.this.mShowVideoAdSemaphore = new Semaphore(0);
                try {
                    Map<String, AdapterStatus> adapterStatusMap = initializationStatus.getAdapterStatusMap();
                    for (String str4 : adapterStatusMap.keySet()) {
                        AdapterStatus adapterStatus = adapterStatusMap.get(str4);
                        if (adapterStatus != null) {
                            Log.d("Firebase,", "Adapter name: " + str4 + "  Description: " + adapterStatus.getDescription() + "  Latency: " + adapterStatus.getLatency());
                        }
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    public void loadInterstitial() {
        if (this.mFirebaseInterstitial != null) {
            this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.hcr2.firebase.CFirebaseAds.2
                @Override // java.lang.Runnable
                public void run() {
                    CFirebaseAds.this.mFirebaseInterstitial.loadInterstitial();
                }
            });
        }
    }

    public void loadVideoAd() {
        if (this.mFirebaseRewardedVideo != null) {
            this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.hcr2.firebase.CFirebaseAds.3
                @Override // java.lang.Runnable
                public void run() {
                    CFirebaseAds.this.mFirebaseRewardedVideo.loadRewardedVideo();
                }
            });
        }
    }

    public void showInterstitial() {
        if (this.mFirebaseInterstitial == null) {
            AppActivity.getInstance().onInterstitialFailed();
        } else {
            this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.hcr2.firebase.CFirebaseAds.4
                @Override // java.lang.Runnable
                public void run() {
                    CFirebaseAds.this.mFirebaseInterstitial.show();
                }
            });
        }
    }

    public boolean hasVideoCampaigns() {
        if (this.mFirebaseRewardedVideo == null) {
            return false;
        }
        this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.hcr2.firebase.CFirebaseAds.5
            @Override // java.lang.Runnable
            public void run() {
                CFirebaseAds cFirebaseAds = CFirebaseAds.this;
                cFirebaseAds.mHasCampaignsReturnValue = cFirebaseAds.mFirebaseRewardedVideo.hasCampaigns();
                CFirebaseAds.this.mHasCampaignsSemaphore.release();
            }
        });
        try {
            if (this.mHasCampaignsSemaphore.tryAcquire(3000L, TimeUnit.MILLISECONDS)) {
                return this.mHasCampaignsReturnValue;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean showVideoAd() {
        if (this.mFirebaseRewardedVideo == null) {
            return false;
        }
        this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.hcr2.firebase.CFirebaseAds.6
            @Override // java.lang.Runnable
            public void run() {
                CFirebaseAds cFirebaseAds = CFirebaseAds.this;
                cFirebaseAds.mShowVideoAdReturnValue = cFirebaseAds.mFirebaseRewardedVideo.showRewardedVideo();
                CFirebaseAds.this.mShowVideoAdSemaphore.release();
            }
        });
        try {
            if (this.mShowVideoAdSemaphore.tryAcquire(3000L, TimeUnit.MILLISECONDS)) {
                return this.mShowVideoAdReturnValue;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public void onStart() {
        Chartboost.onStart(this.mActivity);
    }

    public void onResume() {
        Chartboost.onResume(this.mActivity);
        IronSource.onResume(this.mActivity);
        CFirebaseRewardedVideo cFirebaseRewardedVideo = this.mFirebaseRewardedVideo;
        if (cFirebaseRewardedVideo != null) {
            cFirebaseRewardedVideo.onResume();
        }
        CFirebaseInterstitial cFirebaseInterstitial = this.mFirebaseInterstitial;
        if (cFirebaseInterstitial != null) {
            cFirebaseInterstitial.onResume(this.mActivity);
        }
    }

    public void onPause() {
        Chartboost.onPause(this.mActivity);
        IronSource.onPause(this.mActivity);
        CFirebaseRewardedVideo cFirebaseRewardedVideo = this.mFirebaseRewardedVideo;
        if (cFirebaseRewardedVideo != null) {
            cFirebaseRewardedVideo.onPause();
        }
        CFirebaseInterstitial cFirebaseInterstitial = this.mFirebaseInterstitial;
        if (cFirebaseInterstitial != null) {
            cFirebaseInterstitial.onPause();
        }
    }

    public void onStop() {
        Chartboost.onStop(this.mActivity);
    }

    public void onDestroy() {
        Chartboost.onDestroy(this.mActivity);
        CFirebaseRewardedVideo cFirebaseRewardedVideo = this.mFirebaseRewardedVideo;
        if (cFirebaseRewardedVideo != null) {
            cFirebaseRewardedVideo.onDestroy();
        }
    }

    public void setGDPRConsentStatus(boolean z, boolean z2) {
        Log.d("firebase,", "Setting gdpr consent status...");
        try {
            IronSource.setConsent(z);
            MetaData metaData = new MetaData(this.mActivity);
            metaData.set("gdpr.consent", Boolean.valueOf(z));
            metaData.commit();
            AppLovinPrivacySettings.setHasUserConsent(z, this.mActivity);
            if (z) {
                Chartboost.setPIDataUseConsent(this.mActivity, Chartboost.CBPIDataUseConsent.YES_BEHAVIORAL);
            } else {
                Chartboost.setPIDataUseConsent(this.mActivity, Chartboost.CBPIDataUseConsent.NO_BEHAVIORAL);
            }
            if (z) {
                VungleConsent.updateConsentStatus(Vungle.Consent.OPTED_IN, "1.0.0");
            } else {
                VungleConsent.updateConsentStatus(Vungle.Consent.OPTED_OUT, "1.0.0");
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, z);
                jSONObject.put("gdpr", "1");
            } catch (Exception unused) {
            }
            InMobiConsent.updateGDPRConsent(jSONObject);
            MyTargetPrivacy.setUserConsent(z);
            if (this.mFirebaseRewardedVideo != null) {
                this.mFirebaseRewardedVideo.setGDPRConsentStatus(z, z2);
            }
            if (this.mFirebaseInterstitial != null) {
                this.mFirebaseInterstitial.setGDPRConsentStatus(z, z2);
            }
        } catch (Exception unused2) {
        }
    }
}
