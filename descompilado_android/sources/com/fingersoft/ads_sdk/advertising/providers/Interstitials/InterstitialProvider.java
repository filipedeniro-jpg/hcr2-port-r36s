package com.fingersoft.ads_sdk.advertising.providers.Interstitials;

import android.app.Activity;
import com.fingersoft.ads_sdk.advertising.AdManager;
import com.fingersoft.ads_sdk.advertising.InterstitialManager;
import com.fingersoft.ads_sdk.advertising.json.ApplicationInfo;
import com.fingersoft.ads_sdk.advertising.json.IResultContainer;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class InterstitialProvider {
    public static final int INTERSTITIAL_PROVIDER_ADMOB = 1;
    public static final int INTERSTITIAL_PROVIDER_AMAZON = 10;
    public static final int INTERSTITIAL_PROVIDER_CHARTBOOST = 2;
    public static final int INTERSTITIAL_PROVIDER_FACEBOOK = 3;
    public static final int INTERSTITIAL_PROVIDER_INMOBI = 9;
    public static final int INTERSTITIAL_PROVIDER_INNERACTIVE = 4;
    public static final int INTERSTITIAL_PROVIDER_LEADBOLT = 5;
    public static final int INTERSTITIAL_PROVIDER_MILLENNIAL = 6;
    public static final int INTERSTITIAL_PROVIDER_MOPUB = 7;
    public static final int INTERSTITIAL_PROVIDER_SMAATO = 8;
    private static final int INTERSTITIAL_SHOW_COUNT = 3;
    private int mProviderID;
    private int mShowCount = 0;
    private int mMaximumShowCount = 3;
    protected InterstitialManager mInterstitialManager = null;
    protected InterstitialListener listener = null;

    public abstract String getName();

    public abstract void initialise();

    public void interstitialClose() {
    }

    public void interstitialCreate(boolean z) {
    }

    public void interstitialPause() {
    }

    public void interstitialResume() {
    }

    public abstract void loadInterstitial();

    public abstract void onPause();

    public abstract void onResume(Activity activity);

    public abstract void setName(String str);

    public void show() {
    }

    public void increaseShowCount() {
        this.mShowCount++;
    }

    public boolean canShow() {
        return this.mShowCount <= this.mMaximumShowCount;
    }

    private void setShowLimit(int i) {
        AdUtils.log("Show limit changed to " + i + " for " + getName());
        this.mMaximumShowCount = i;
    }

    public void setProviderID(int i) {
        this.mProviderID = i;
    }

    public int getProviderID() {
        return this.mProviderID;
    }

    public void updateName(int i) {
        if (i > 0) {
            setName(getName() + "" + (i + 1));
        }
    }

    public void setManager(InterstitialManager interstitialManager) {
        this.mInterstitialManager = interstitialManager;
    }

    public void create(AdManager adManager) {
        ApplicationInfo applicationInfo;
        IResultContainer container = adManager.getContainer();
        if (container != null && (applicationInfo = container.getApplicationInfo()) != null) {
            int providerShowLimit = applicationInfo.getProviderShowLimit(getName());
            if (providerShowLimit > 0) {
                setShowLimit(providerShowLimit);
            } else {
                AdUtils.log("Show limit read failed");
            }
        }
        initialise();
    }
}
