package com.applovin.impl.sdk.d;

import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e extends f {
    public e(List<NativeAdImpl> list, com.applovin.impl.sdk.h hVar, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdImages", list, hVar, appLovinNativeAdLoadListener);
    }

    public e(List<NativeAdImpl> list, com.applovin.impl.sdk.h hVar, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdImages", list, hVar, appLovinNativeAdPrecacheListener);
    }

    private boolean b(NativeAdImpl nativeAdImpl) {
        c("Unable to cache image resource");
        a(nativeAdImpl, !com.applovin.impl.sdk.utils.f.a(e(), this.b) ? AppLovinErrorCodes.NO_NETWORK : AppLovinErrorCodes.UNABLE_TO_PRECACHE_IMAGE_RESOURCES);
        return false;
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.j;
    }

    @Override // com.applovin.impl.sdk.d.f
    protected void a(NativeAdImpl nativeAdImpl) {
        if (this.a != null) {
            this.a.onNativeAdImagesPrecached(nativeAdImpl);
        }
    }

    @Override // com.applovin.impl.sdk.d.f
    protected void a(NativeAdImpl nativeAdImpl, int i) {
        if (this.a != null) {
            this.a.onNativeAdImagePrecachingFailed(nativeAdImpl, i);
        }
    }

    @Override // com.applovin.impl.sdk.d.f
    protected boolean a(NativeAdImpl nativeAdImpl, com.applovin.impl.sdk.l lVar) {
        a("Beginning native ad image caching for #" + nativeAdImpl.getAdId());
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.bE)).booleanValue()) {
            a("Resource caching is disabled, skipping...");
            return true;
        }
        String strA = a(nativeAdImpl.getSourceIconUrl(), lVar, nativeAdImpl.getResourcePrefixes());
        if (strA == null) {
            return b(nativeAdImpl);
        }
        nativeAdImpl.setIconUrl(strA);
        String strA2 = a(nativeAdImpl.getSourceImageUrl(), lVar, nativeAdImpl.getResourcePrefixes());
        if (strA2 == null) {
            return b(nativeAdImpl);
        }
        nativeAdImpl.setImageUrl(strA2);
        return true;
    }

    @Override // com.applovin.impl.sdk.d.f, java.lang.Runnable
    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
