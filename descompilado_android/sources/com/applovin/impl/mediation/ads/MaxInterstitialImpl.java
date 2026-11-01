package com.applovin.impl.mediation.ads;

import android.app.Activity;
import android.util.Base64;
import com.applovin.impl.mediation.ads.a.b;
import com.applovin.impl.sdk.b.a;
import com.applovin.impl.sdk.utils.h;
import com.applovin.impl.sdk.utils.n;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.sdk.AppLovinSdk;
import java.nio.charset.Charset;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaxInterstitialImpl extends b {
    private final Activity a;

    public MaxInterstitialImpl(String str, AppLovinSdk appLovinSdk, Activity activity) {
        super(str, "MaxInterstitialAd", n.a(appLovinSdk));
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        this.a = activity;
        this.logger.a(this.tag, "Created new MaxInterstitialAd (" + this + ")");
    }

    @Override // com.applovin.impl.mediation.ads.a.b
    protected Activity getActivity() {
        return this.a;
    }

    public void loadAd() {
        try {
            this.logger.a(this.tag, "Loading ad for '" + this.adUnitId + "'...");
            if (!isReady()) {
                transitionToState(b.EnumC0017b.LOADING, new Runnable() { // from class: com.applovin.impl.mediation.ads.MaxInterstitialImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MaxInterstitialImpl.this.sdk.a(MaxInterstitialImpl.this.a).loadAd(MaxInterstitialImpl.this.adUnitId, MaxAdFormat.INTERSTITIAL, MaxInterstitialImpl.this.loadRequestBuilder.a(), MaxInterstitialImpl.this.a, MaxInterstitialImpl.this.listenerWrapper);
                    }
                });
                return;
            }
            this.logger.a(this.tag, "An ad is already loaded for '" + this.adUnitId + "'");
            h.a(this.adListener, getLoadedAd(), this.sdk);
        } catch (Throwable th) {
            String strEncodeToString = Base64.encodeToString(th.toString().getBytes(Charset.defaultCharset()), 2);
            HashMap map = new HashMap(1);
            map.put("exception", strEncodeToString);
            this.sdk.r().trackEvent("max_inter_load_exception", map);
        }
    }

    public void showAd(final String str) {
        try {
            if (!((Boolean) this.sdk.a(a.L)).booleanValue() || (!this.sdk.U().a() && !this.sdk.U().b())) {
                transitionToState(b.EnumC0017b.SHOWING, new Runnable() { // from class: com.applovin.impl.mediation.ads.MaxInterstitialImpl.2
                    @Override // java.lang.Runnable
                    public void run() {
                        MaxInterstitialImpl maxInterstitialImpl = MaxInterstitialImpl.this;
                        maxInterstitialImpl.showFullscreenAd(str, maxInterstitialImpl.a);
                    }
                });
            } else {
                this.logger.e(this.tag, "Attempting to show ad when another fullscreen ad is already showing");
                h.a(this.adListener, getLoadedAd(), -23, this.sdk);
            }
        } catch (Throwable th) {
            String strEncodeToString = Base64.encodeToString(th.toString().getBytes(Charset.defaultCharset()), 2);
            HashMap map = new HashMap(1);
            map.put("exception", strEncodeToString);
            this.sdk.r().trackEvent("max_inter_show_exception", map);
        }
    }

    public String toString() {
        return "MaxInterstitial{adUnitId='" + this.adUnitId + "', adListener=" + this.adListener + ", isReady=" + isReady() + '}';
    }
}
