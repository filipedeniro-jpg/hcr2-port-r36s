package com.applovin.impl.mediation.ads;

import android.app.Activity;
import android.util.Base64;
import com.applovin.impl.mediation.ads.a.b;
import com.applovin.impl.sdk.b.a;
import com.applovin.impl.sdk.utils.h;
import com.applovin.impl.sdk.utils.n;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaxRewardedImpl extends b {
    private static WeakReference<Activity> a = new WeakReference<>(null);

    public MaxRewardedImpl(String str, AppLovinSdk appLovinSdk) {
        super(str, "MaxRewardedAd", n.a(appLovinSdk));
        this.logger.a(this.tag, "Created new MaxRewardedAd (" + this + ")");
    }

    public static void updateActivity(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        a = new WeakReference<>(activity);
    }

    @Override // com.applovin.impl.mediation.ads.a.b
    protected Activity getActivity() {
        return a.get();
    }

    public void loadAd() {
        try {
            this.logger.a(this.tag, "Loading ad for '" + this.adUnitId + "'...");
            if (!isReady()) {
                final Activity activity = a.get();
                if (activity != null) {
                    transitionToState(b.EnumC0017b.LOADING, new Runnable() { // from class: com.applovin.impl.mediation.ads.MaxRewardedImpl.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MaxRewardedImpl.this.sdk.a(activity).loadAd(MaxRewardedImpl.this.adUnitId, MaxAdFormat.REWARDED, MaxRewardedImpl.this.loadRequestBuilder.a(), activity, MaxRewardedImpl.this.listenerWrapper);
                        }
                    });
                    return;
                } else {
                    this.logger.e(this.tag, "Unable to load rewarded ad because Activity reference was null. Call MaxRewardedAd.updateActivity(...) before requesting more rewarded ads");
                    this.listenerWrapper.onAdLoadFailed(this.adUnitId, MaxErrorCodes.NO_ACTIVITY);
                    return;
                }
            }
            this.logger.a(this.tag, "An ad is already loaded for '" + this.adUnitId + "'");
            h.a(this.adListener, getLoadedAd(), this.sdk);
        } catch (Throwable th) {
            String strEncodeToString = Base64.encodeToString(th.toString().getBytes(Charset.defaultCharset()), 2);
            HashMap map = new HashMap(1);
            map.put("exception", strEncodeToString);
            this.sdk.r().trackEvent("max_rewarded_load_exception", map);
        }
    }

    public void showAd(final String str) {
        try {
            if (((Boolean) this.sdk.a(a.L)).booleanValue() && (this.sdk.U().a() || this.sdk.U().b())) {
                this.logger.e(this.tag, "Attempting to show ad when another fullscreen ad is already showing");
                h.a(this.adListener, getLoadedAd(), -23, this.sdk);
                return;
            }
            this.logger.a(this.tag, "Showing ad for '" + this.adUnitId + "'...");
            final Activity activity = a.get();
            if (activity != null) {
                transitionToState(b.EnumC0017b.SHOWING, new Runnable() { // from class: com.applovin.impl.mediation.ads.MaxRewardedImpl.2
                    @Override // java.lang.Runnable
                    public void run() {
                        MaxRewardedImpl.this.showFullscreenAd(str, activity);
                    }
                });
            } else {
                this.logger.e(this.tag, "Unable to show rewarded ad because Activity reference was null. Call MaxRewardedAd.updateActivity(...) before requesting more rewarded ads");
                this.listenerWrapper.onAdDisplayFailed(getLoadedAd(), MaxErrorCodes.NO_ACTIVITY);
            }
        } catch (Throwable th) {
            String strEncodeToString = Base64.encodeToString(th.toString().getBytes(Charset.defaultCharset()), 2);
            HashMap map = new HashMap(1);
            map.put("exception", strEncodeToString);
            this.sdk.r().trackEvent("max_rewarded_show_exception", map);
        }
    }

    public String toString() {
        return "MaxRewarded{adUnitId='" + this.adUnitId + "', adListener=" + this.adListener + ", isReady=" + isReady() + '}';
    }
}
