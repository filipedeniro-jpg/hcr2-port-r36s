package com.fingersoft.ads_sdk.advertising.providers.video;

import android.app.Activity;
import android.util.Log;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;
import com.fingersoft.ads_sdk.advertising.video.VideoAdProvider;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.LogListener;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;

/* JADX INFO: loaded from: classes.dex */
public class IronSourceVideoAds extends VideoAdProvider implements RewardedVideoListener {
    private static final String TAG = "supersonicVid";
    private String country;
    private Activity mActivity;
    private String mApplicationKey;
    private String mUserID;
    private boolean mHasCampaigns = false;
    private boolean mIsInitialized = true;
    private boolean mGDPRConsentStatus = false;
    private boolean mVideoAdCredited = false;

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdClicked(Placement placement) {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdEnded() {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdOpened() {
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdStarted() {
    }

    public IronSourceVideoAds(Activity activity, String str, String str2) {
        this.mApplicationKey = "2e4cac71";
        this.mUserID = "";
        this.mActivity = null;
        setName("supersonicVid");
        setProviderID(4);
        this.mActivity = activity;
        this.mApplicationKey = str;
        this.mUserID = str2;
        initialize();
    }

    private void initialize() {
        try {
            IronSource.setConsent(this.mGDPRConsentStatus);
            IronSource.setUserId(this.mUserID);
            IronSource.init(this.mActivity, this.mApplicationKey);
            IronSource.setRewardedVideoListener(this);
            IronSource.setLogListener(new LogListener() { // from class: com.fingersoft.ads_sdk.advertising.providers.video.IronSourceVideoAds.1
                @Override // com.ironsource.mediationsdk.logger.LogListener
                public void onLog(IronSourceLogger.IronSourceTag ironSourceTag, String str, int i) {
                    AdUtils.log("IRONSOURCE: " + ironSourceTag + " : " + str);
                }
            });
            IntegrationHelper.validateIntegration(this.mActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.fingersoft.ads_sdk.advertising.video.VideoAdProvider
    public boolean hasCampaigns() {
        return this.mHasCampaigns;
    }

    @Override // com.fingersoft.ads_sdk.advertising.video.VideoAdProvider
    public boolean playVideo() {
        if (!this.mIsInitialized) {
            AdUtils.log("Supersonic : video ads not initialized!");
            return false;
        }
        if (!hasCampaigns()) {
            AdUtils.log("Supersonic : No video ads available");
            return false;
        }
        this.mVideoAdCredited = false;
        IronSource.showRewardedVideo();
        return true;
    }

    @Override // com.fingersoft.ads_sdk.advertising.video.VideoAdProvider
    public void pause() {
        IronSource.onPause(this.mActivity);
    }

    @Override // com.fingersoft.ads_sdk.advertising.video.VideoAdProvider
    public void resume(Activity activity) {
        this.mActivity = activity;
        IronSource.onResume(this.mActivity);
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdClosed() {
        if (this.mVideoAdCredited || this.mVideoAdListener == null) {
            return;
        }
        this.mVideoAdListener.onAdCancelled();
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdRewarded(Placement placement) {
        if (this.mVideoAdListener != null) {
            this.mVideoAdCredited = true;
            this.mVideoAdListener.onAdViewed();
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
        if (this.mVideoAdListener != null) {
            this.mVideoAdListener.onAdFailed();
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.RewardedVideoListener
    public void onRewardedVideoAvailabilityChanged(boolean z) {
        AdUtils.log("Supersonic : no more offers");
        this.mHasCampaigns = z;
    }

    @Override // com.fingersoft.ads_sdk.advertising.video.VideoAdProvider
    public void setGDPRConsentStatus(boolean z) {
        Log.d("amazon fsadsk", "Set GDPR consent status for IRONSource videos" + Boolean.toString(z));
        this.mGDPRConsentStatus = z;
        IronSource.setConsent(z);
    }
}
