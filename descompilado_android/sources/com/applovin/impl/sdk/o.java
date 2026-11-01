package com.applovin.impl.sdk;

import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinAd;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class o extends p {
    o(h hVar) {
        super(hVar);
    }

    @Override // com.applovin.impl.sdk.p
    com.applovin.impl.sdk.ad.d a(com.applovin.impl.sdk.ad.j jVar) {
        return ((NativeAdImpl) jVar).getAdZone();
    }

    @Override // com.applovin.impl.sdk.p
    com.applovin.impl.sdk.d.a a(com.applovin.impl.sdk.ad.d dVar) {
        return new com.applovin.impl.sdk.d.n(null, 1, this.a, this);
    }

    public void a() {
        h(com.applovin.impl.sdk.ad.d.h(this.a));
    }

    @Override // com.applovin.impl.sdk.k
    public void a(com.applovin.impl.sdk.ad.d dVar, int i) {
    }

    @Override // com.applovin.impl.sdk.p
    void a(Object obj, com.applovin.impl.sdk.ad.d dVar, int i) {
        ((AppLovinNativeAdLoadListener) obj).onNativeAdsFailedToLoad(i);
    }

    @Override // com.applovin.impl.sdk.p
    void a(Object obj, com.applovin.impl.sdk.ad.j jVar) {
        ((AppLovinNativeAdLoadListener) obj).onNativeAdsLoaded(Arrays.asList((AppLovinNativeAd) jVar));
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ void a(LinkedHashSet linkedHashSet) {
        super.a((LinkedHashSet<com.applovin.impl.sdk.ad.d>) linkedHashSet);
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ boolean a(com.applovin.impl.sdk.ad.d dVar, Object obj) {
        return super.a(dVar, obj);
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void adReceived(AppLovinAd appLovinAd) {
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ void b(com.applovin.impl.sdk.ad.d dVar, int i) {
        super.b(dVar, i);
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ boolean b(com.applovin.impl.sdk.ad.d dVar) {
        return super.b(dVar);
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ com.applovin.impl.sdk.ad.j c(com.applovin.impl.sdk.ad.d dVar) {
        return super.c(dVar);
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ com.applovin.impl.sdk.ad.j d(com.applovin.impl.sdk.ad.d dVar) {
        return super.d(dVar);
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ com.applovin.impl.sdk.ad.j e(com.applovin.impl.sdk.ad.d dVar) {
        return super.e(dVar);
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ void f(com.applovin.impl.sdk.ad.d dVar) {
        super.f(dVar);
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void failedToReceiveAd(int i) {
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ boolean g(com.applovin.impl.sdk.ad.d dVar) {
        return super.g(dVar);
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ void h(com.applovin.impl.sdk.ad.d dVar) {
        super.h(dVar);
    }

    @Override // com.applovin.impl.sdk.p
    public /* bridge */ /* synthetic */ void i(com.applovin.impl.sdk.ad.d dVar) {
        super.i(dVar);
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
    public void onNativeAdsFailedToLoad(int i) {
        c(com.applovin.impl.sdk.ad.d.h(this.a), i);
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
    public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
        AppLovinNativeAd appLovinNativeAd = list.get(0);
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.bB)).booleanValue()) {
            this.a.q().precacheResources(appLovinNativeAd, new AppLovinNativeAdPrecacheListener() { // from class: com.applovin.impl.sdk.o.1
                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd2, int i) {
                    o oVar = o.this;
                    oVar.c(com.applovin.impl.sdk.ad.d.h(oVar.a), i);
                }

                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd2) {
                    if (com.applovin.impl.sdk.utils.k.b(appLovinNativeAd2.getVideoUrl())) {
                        return;
                    }
                    o.this.b((com.applovin.impl.sdk.ad.j) appLovinNativeAd2);
                }

                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd2, int i) {
                    o.this.b.c("NativeAdPreloadManager", "Video failed to cache during native ad preload. " + i);
                    o.this.b((com.applovin.impl.sdk.ad.j) appLovinNativeAd2);
                }

                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd2) {
                    o.this.b((com.applovin.impl.sdk.ad.j) appLovinNativeAd2);
                }
            });
        } else {
            b((com.applovin.impl.sdk.ad.j) appLovinNativeAd);
        }
    }
}
