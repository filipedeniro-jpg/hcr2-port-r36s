package com.applovin.mediation.ads;

import android.app.Activity;
import android.text.TextUtils;
import com.applovin.impl.mediation.ads.MaxRewardedImpl;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.sdk.AppLovinSdk;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MaxRewardedAd {
    private static final Map<String, MaxRewardedAd> a = new HashMap();
    private static final Object b = new Object();
    private final MaxRewardedImpl c;

    private MaxRewardedAd(String str, AppLovinSdk appLovinSdk) {
        this.c = new MaxRewardedImpl(str, appLovinSdk);
    }

    public static MaxRewardedAd getInstance(String str, Activity activity) {
        return getInstance(str, AppLovinSdk.getInstance(activity), activity);
    }

    public static MaxRewardedAd getInstance(String str, AppLovinSdk appLovinSdk, Activity activity) {
        if (str == null) {
            throw new IllegalArgumentException("No ad unit ID specified");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Empty ad unit ID specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        updateActivity(activity);
        synchronized (b) {
            MaxRewardedAd maxRewardedAd = a.get(str);
            if (maxRewardedAd != null) {
                return maxRewardedAd;
            }
            MaxRewardedAd maxRewardedAd2 = new MaxRewardedAd(str, appLovinSdk);
            a.put(str, maxRewardedAd2);
            return maxRewardedAd2;
        }
    }

    public static void updateActivity(Activity activity) {
        MaxRewardedImpl.updateActivity(activity);
    }

    public void destroy() {
        synchronized (b) {
            a.remove(this.c.getAdUnitId());
        }
        this.c.destroy();
    }

    public boolean isReady() {
        return this.c.isReady();
    }

    public void loadAd() {
        this.c.loadAd();
    }

    public void setExtrasParameter(String str, String str2) {
        this.c.setExtraParameter(str, str2);
    }

    public void setListener(MaxRewardedAdListener maxRewardedAdListener) {
        this.c.setListener(maxRewardedAdListener);
    }

    public void showAd() {
        showAd(null);
    }

    public void showAd(String str) {
        this.c.showAd(str);
    }

    public String toString() {
        return this.c.toString();
    }
}
