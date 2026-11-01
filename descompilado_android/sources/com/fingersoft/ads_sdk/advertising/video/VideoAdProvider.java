package com.fingersoft.ads_sdk.advertising.video;

import android.app.Activity;
import com.fingersoft.ads_sdk.advertising.AdManager;
import com.fingersoft.ads_sdk.advertising.json.ApplicationInfo;
import com.fingersoft.ads_sdk.advertising.json.IResultContainer;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class VideoAdProvider {
    public static final int VIDEO_AD_PLAY_VIA_MENU = 0;
    public static final int VIDEO_AD_PLAY_VIA_PROMOTION = 1;
    public static final int VIDEO_AD_PROVIDER_ADCOLONY = 1;
    public static final int VIDEO_AD_PROVIDER_CHARTBOOST = 2;
    public static final int VIDEO_AD_PROVIDER_NATIVEX = 3;
    public static final int VIDEO_AD_PROVIDER_SUPERSONIC = 4;
    public static final int VIDEO_AD_PROVIDER_UNITY = 5;
    public static final int VIDEO_AD_PROVIDER_VUNGLE = 6;
    private boolean mGDPRConsentStatus;
    private int mProviderID;
    private String mProviderName;
    protected IVideoAdListener mVideoAdListener;
    protected AdManager mAdManager = null;
    protected int mShowCount = 0;
    protected int mMaximumShowCount = 3;

    public abstract boolean hasCampaigns();

    public abstract void pause();

    public abstract boolean playVideo();

    public abstract void resume(Activity activity);

    public abstract void setGDPRConsentStatus(boolean z);

    public String getName() {
        return this.mProviderName;
    }

    public void setName(String str) {
        this.mProviderName = str;
    }

    public void updateName(int i) {
        if (i > 0) {
            setName(getName() + "" + (i + 1));
        }
    }

    protected void setProviderID(int i) {
        this.mProviderID = i;
    }

    public int getProviderID() {
        return this.mProviderID;
    }

    public int getShowCount() {
        return this.mShowCount;
    }

    public int getMaximumShowCount() {
        return this.mMaximumShowCount;
    }

    public boolean canShow() {
        IResultContainer container;
        ApplicationInfo applicationInfo;
        AdManager adManager = this.mAdManager;
        if (adManager != null && (container = adManager.getContainer()) != null && (applicationInfo = container.getApplicationInfo()) != null) {
            int providerShowLimit = applicationInfo.getProviderShowLimit(getName());
            if (providerShowLimit > 0) {
                setMaximumShowCount(providerShowLimit);
            } else {
                AdUtils.log("Show limit read failed");
            }
        }
        return getShowCount() < getMaximumShowCount();
    }

    public void setMaximumShowCount(int i) {
        this.mMaximumShowCount = i;
    }

    public void increaseShowCount() {
        this.mShowCount++;
    }

    public void resetShowCount() {
        this.mShowCount = 0;
    }

    public void setListener(IVideoAdListener iVideoAdListener) {
        this.mVideoAdListener = iVideoAdListener;
    }

    public void setAdManager(AdManager adManager) {
        this.mAdManager = adManager;
    }
}
