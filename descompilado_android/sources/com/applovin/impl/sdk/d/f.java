package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class f extends a {
    protected final AppLovinNativeAdPrecacheListener a;
    private final List<NativeAdImpl> c;
    private final AppLovinNativeAdLoadListener d;
    private int e;

    f(String str, List<NativeAdImpl> list, com.applovin.impl.sdk.h hVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super(str, hVar);
        this.c = list;
        this.d = appLovinNativeAdLoadListener;
        this.a = null;
    }

    f(String str, List<NativeAdImpl> list, com.applovin.impl.sdk.h hVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super(str, hVar);
        if (list == null) {
            throw new IllegalArgumentException("Native ads cannot be null");
        }
        this.c = list;
        this.d = null;
        this.a = appLovinNativeAdPrecacheListener;
    }

    private void a(int i) {
        AppLovinNativeAdLoadListener appLovinNativeAdLoadListener = this.d;
        if (appLovinNativeAdLoadListener != null) {
            appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
        }
    }

    private void a(List<AppLovinNativeAd> list) {
        AppLovinNativeAdLoadListener appLovinNativeAdLoadListener = this.d;
        if (appLovinNativeAdLoadListener != null) {
            appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
        }
    }

    protected String a(String str, com.applovin.impl.sdk.l lVar, List<String> list) {
        if (!com.applovin.impl.sdk.utils.k.b(str)) {
            a("Asked to cache file with null/empty URL, nothing to do.");
            return null;
        }
        if (!com.applovin.impl.sdk.utils.n.a(str, list)) {
            a("Domain is not whitelisted, skipping precache for URL " + str);
            return null;
        }
        try {
            String strA = lVar.a(e(), str, null, list, true, true, null);
            if (strA != null) {
                return strA;
            }
            c("Unable to cache icon resource " + str);
            return null;
        } catch (Exception e) {
            a("Unable to cache icon resource " + str, e);
            return null;
        }
    }

    protected abstract void a(NativeAdImpl nativeAdImpl);

    protected abstract void a(NativeAdImpl nativeAdImpl, int i);

    protected abstract boolean a(NativeAdImpl nativeAdImpl, com.applovin.impl.sdk.l lVar);

    @Override // java.lang.Runnable
    public void run() {
        for (NativeAdImpl nativeAdImpl : this.c) {
            a("Beginning resource caching phase...");
            if (a(nativeAdImpl, this.b.Q())) {
                this.e++;
                a(nativeAdImpl);
            } else {
                d("Unable to cache resources");
            }
        }
        try {
            if (this.e != this.c.size() && ((Boolean) this.b.a(com.applovin.impl.sdk.b.b.dw)).booleanValue()) {
                d("Mismatch between successful populations and requested size");
                a(-6);
            } else {
                List<AppLovinNativeAd> list = this.c;
                a(list);
            }
        } catch (Throwable th) {
            c().w().c(d(), "Encountered exception while notifying publisher code", th);
        }
    }
}
