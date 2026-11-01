package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class g extends f {
    public g(List<NativeAdImpl> list, com.applovin.impl.sdk.h hVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdVideos", list, hVar, appLovinNativeAdLoadListener);
    }

    public g(List<NativeAdImpl> list, com.applovin.impl.sdk.h hVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdVideos", list, hVar, appLovinNativeAdPrecacheListener);
    }

    private boolean b(NativeAdImpl nativeAdImpl) {
        c("Unable to cache video resource " + nativeAdImpl.getSourceVideoUrl());
        a(nativeAdImpl, !com.applovin.impl.sdk.utils.f.a(e(), this.b) ? AppLovinErrorCodes.NO_NETWORK : AppLovinErrorCodes.UNABLE_TO_PRECACHE_VIDEO_RESOURCES);
        return false;
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.k;
    }

    @Override // com.applovin.impl.sdk.d.f
    protected void a(NativeAdImpl nativeAdImpl) {
        if (this.a != null) {
            this.a.onNativeAdVideoPreceached(nativeAdImpl);
        }
    }

    @Override // com.applovin.impl.sdk.d.f
    protected void a(NativeAdImpl nativeAdImpl, int i) {
        if (this.a != null) {
            this.a.onNativeAdVideoPrecachingFailed(nativeAdImpl, i);
        }
    }

    @Override // com.applovin.impl.sdk.d.f
    protected boolean a(NativeAdImpl nativeAdImpl, com.applovin.impl.sdk.l lVar) {
        if (!com.applovin.impl.sdk.utils.k.b(nativeAdImpl.getSourceVideoUrl())) {
            return true;
        }
        a("Beginning native ad video caching" + nativeAdImpl.getAdId());
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.bE)).booleanValue()) {
            String strA = a(nativeAdImpl.getSourceVideoUrl(), lVar, nativeAdImpl.getResourcePrefixes());
            if (strA == null) {
                return b(nativeAdImpl);
            }
            nativeAdImpl.setVideoUrl(strA);
        } else {
            a("Resource caching is disabled, skipping...");
        }
        return true;
    }

    @Override // com.applovin.impl.sdk.d.f, java.lang.Runnable
    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
