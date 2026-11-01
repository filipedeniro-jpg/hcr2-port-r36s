package com.fingersoft.hcr2.firebase;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.appevents.AppEventsConstants;
import com.fingersoft.hcr2.Log;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.jirbo.adcolony.AdColonyAdapter;
import com.jirbo.adcolony.AdColonyBundleBuilder;
import com.vungle.mediation.VungleAdapter;
import com.vungle.mediation.VungleExtrasBuilder;

/* JADX INFO: loaded from: classes.dex */
public class CFirebaseRewardedVideo implements RewardedVideoAdListener {
    private Activity mActivity;
    private FirebaseAdListener mListener;
    private String mUnitID;
    private RewardedVideoAd mVideoAd;
    private final int MAXIMUM_LOAD_FAIL_COUNT = 10;
    private int mLoadFailCount = 0;
    private final String mVungleVideoID = "REWARDE84354";
    private boolean mConsentGiven = false;
    private boolean mConsentUpdated = false;
    private boolean mVideoAdRunning = false;
    private boolean mForceLoad = false;
    private boolean mIsRewarded = false;

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public void onRewardedVideoAdLeftApplication() {
    }

    public CFirebaseRewardedVideo(Activity activity, String str, FirebaseAdListener firebaseAdListener) {
        this.mActivity = activity;
        this.mListener = firebaseAdListener;
        this.mVideoAd = MobileAds.getRewardedVideoAdInstance(activity);
        RewardedVideoAd rewardedVideoAd = this.mVideoAd;
        if (rewardedVideoAd != null) {
            rewardedVideoAd.setRewardedVideoAdListener(this);
        }
        this.mUnitID = str;
    }

    public boolean hasCampaigns() {
        RewardedVideoAd rewardedVideoAd = this.mVideoAd;
        if (rewardedVideoAd != null) {
            return rewardedVideoAd.isLoaded();
        }
        return false;
    }

    public void loadRewardedVideo() {
        Log.d("firebase,", "Load rewarded video ad");
        if (!this.mVideoAd.isLoaded() || this.mConsentUpdated || this.mForceLoad) {
            this.mConsentUpdated = false;
            this.mForceLoad = false;
            try {
                Bundle bundleBuild = new VungleExtrasBuilder(new String[]{"REWARDE84354"}).build();
                Bundle bundle = new Bundle();
                AdColonyBundleBuilder.setGdprRequired(true);
                if (this.mConsentGiven) {
                    AdColonyBundleBuilder.setGdprConsentString("1");
                    bundle.putString("npa", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                } else {
                    AdColonyBundleBuilder.setGdprConsentString(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    bundle.putString("npa", "1");
                }
                Log.d("Firebase,", "Loading vieooAd with gdpr consent: " + Boolean.toString(this.mConsentGiven) + ", with npa value: " + bundle.getString("npa"));
                this.mVideoAd.loadAd(this.mUnitID, new AdRequest.Builder().addNetworkExtrasBundle(AdColonyAdapter.class, AdColonyBundleBuilder.build()).addNetworkExtrasBundle(VungleAdapter.class, bundleBuild).addNetworkExtrasBundle(AdMobAdapter.class, bundle).build());
            } catch (Exception unused) {
            }
        }
    }

    public boolean showRewardedVideo() {
        Log.d("firebase,", "Show rewarded video ad, is ad running? " + this.mVideoAdRunning);
        if (this.mVideoAdRunning) {
            this.mVideoAdRunning = false;
            loadRewardedVideo();
            return false;
        }
        if (!this.mVideoAd.isLoaded()) {
            return false;
        }
        Log.d("firebase,", "Showing " + this.mVideoAd.getMediationAdapterClassName() + " video ad");
        this.mVideoAd.show();
        return true;
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public void onRewardedVideoAdLoaded() {
        this.mIsRewarded = false;
        this.mLoadFailCount = 0;
        FirebaseAdListener firebaseAdListener = this.mListener;
        if (firebaseAdListener != null) {
            firebaseAdListener.onVideoAdAvailable();
        }
        Log.d("firebase,", "Rewarded video ad loaded with consent: " + this.mConsentGiven + " adapter class: " + this.mVideoAd.getMediationAdapterClassName());
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public void onRewardedVideoAdOpened() {
        Log.d("firebase,", "Rewarded video ad opened");
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public void onRewardedVideoStarted() {
        this.mVideoAdRunning = true;
        Log.d("firebase,", "Rewarded video ad started");
        this.mListener.onVideoAdStarted(this.mVideoAd.getMediationAdapterClassName());
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public void onRewardedVideoCompleted() {
        Log.d("firebase,", "Rewarded video ad completed");
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public void onRewardedVideoAdClosed() {
        FirebaseAdListener firebaseAdListener;
        Log.d("firebase,", "Rewarded videoa ad closed");
        if (!this.mIsRewarded && (firebaseAdListener = this.mListener) != null) {
            firebaseAdListener.onVideoAdCancelled();
        }
        this.mVideoAdRunning = false;
        loadRewardedVideo();
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public void onRewarded(RewardItem rewardItem) {
        RewardedVideoAd rewardedVideoAd;
        Log.d("firebase,", "Rewarded videoa onRewarded");
        this.mIsRewarded = true;
        this.mVideoAdRunning = false;
        FirebaseAdListener firebaseAdListener = this.mListener;
        if (firebaseAdListener == null || (rewardedVideoAd = this.mVideoAd) == null) {
            return;
        }
        firebaseAdListener.onVideoAdViewed(rewardedVideoAd.getMediationAdapterClassName());
    }

    @Override // com.google.android.gms.ads.reward.RewardedVideoAdListener
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.d("firebase,", "Rewarded video onRewardedVideoAdFailedToLoad");
        if (this.mLoadFailCount < 10) {
            loadRewardedVideo();
            this.mLoadFailCount++;
        }
    }

    public void onResume() {
        Log.d("firebase,", "Video ad onResume(), is video running?" + this.mVideoAdRunning);
        this.mVideoAd.resume(this.mActivity);
        if (this.mVideoAd.getMediationAdapterClassName().equals("com.google.ads.mediation.facebook.FacebookAdapter")) {
            Log.d("firebase,", "Video ad Facebook(?), is video ad runnig: " + this.mVideoAdRunning);
            if (this.mVideoAdRunning) {
                this.mForceLoad = true;
                onRewardedVideoAdClosed();
            }
        }
    }

    public void onPause() {
        Log.d("firebase,", "Video ad onPause(), is video running " + this.mVideoAdRunning);
        this.mVideoAd.pause(this.mActivity);
    }

    public void onDestroy() {
        this.mVideoAd.destroy(this.mActivity);
    }

    public void setGDPRConsentStatus(boolean z, boolean z2) {
        this.mConsentGiven = z;
        this.mConsentUpdated = true;
        Log.d("firebase,", "Set gdpr video consent status");
        if (z2) {
            this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.hcr2.firebase.CFirebaseRewardedVideo.1
                @Override // java.lang.Runnable
                public void run() {
                    CFirebaseRewardedVideo.this.loadRewardedVideo();
                }
            });
        }
    }
}
