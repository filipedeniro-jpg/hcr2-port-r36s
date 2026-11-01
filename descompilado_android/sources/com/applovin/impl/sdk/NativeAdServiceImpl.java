package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.impl.sdk.ad.NativeAdImpl;
import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.nativeAds.AppLovinNativeAdService;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NativeAdServiceImpl implements AppLovinNativeAdService {
    private final h a;
    private final n b;

    NativeAdServiceImpl(h hVar) {
        this.a = hVar;
        this.b = hVar.w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppLovinNativeAd appLovinNativeAd, final AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        if (appLovinNativeAd.isVideoPrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
        } else {
            this.a.F().a(new com.applovin.impl.sdk.d.g((List<NativeAdImpl>) Arrays.asList((NativeAdImpl) appLovinNativeAd), this.a, new AppLovinNativeAdPrecacheListener() { // from class: com.applovin.impl.sdk.NativeAdServiceImpl.3
                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd2, int i) {
                }

                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd2) {
                }

                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd2, int i) {
                    NativeAdServiceImpl.this.a(appLovinNativeAdPrecacheListener, appLovinNativeAd2, i, true);
                }

                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd2) {
                    NativeAdServiceImpl.this.a(appLovinNativeAdPrecacheListener, appLovinNativeAd2, true);
                }
            }), com.applovin.impl.sdk.d.q.a.CACHING_OTHER);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, int i) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
            } catch (Exception e) {
                this.b.c("NativeAdService", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, List<AppLovinNativeAd> list) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
            } catch (Exception e) {
                this.b.c("NativeAdService", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, int i, boolean z) {
        if (appLovinNativeAdPrecacheListener != null) {
            try {
                if (z) {
                    appLovinNativeAdPrecacheListener.onNativeAdVideoPrecachingFailed(appLovinNativeAd, i);
                } else {
                    appLovinNativeAdPrecacheListener.onNativeAdImagePrecachingFailed(appLovinNativeAd, i);
                }
            } catch (Exception e) {
                this.b.c("NativeAdService", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, boolean z) {
        if (appLovinNativeAdPrecacheListener != null) {
            try {
                if (z) {
                    appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
                } else {
                    appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
                }
            } catch (Exception e) {
                this.b.c("NativeAdService", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    private void a(String str, int i, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.F().a(new com.applovin.impl.sdk.d.n(str, i, this.a, new AppLovinNativeAdLoadListener() { // from class: com.applovin.impl.sdk.NativeAdServiceImpl.1
            @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
            public void onNativeAdsFailedToLoad(int i2) {
                NativeAdServiceImpl.this.a(appLovinNativeAdLoadListener, i2);
            }

            @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
            public void onNativeAdsLoaded(List<AppLovinNativeAd> list) {
                NativeAdServiceImpl.this.a(list, appLovinNativeAdLoadListener);
            }
        }), com.applovin.impl.sdk.d.q.a.MAIN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<AppLovinNativeAd> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        int iIntValue = ((Integer) this.a.a(com.applovin.impl.sdk.b.b.dx)).intValue();
        if (iIntValue <= 0) {
            if (appLovinNativeAdLoadListener != null) {
                appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
                return;
            }
            return;
        }
        List<AppLovinNativeAd> list2 = list;
        int size = list2.size();
        if (size == 0) {
            if (appLovinNativeAdLoadListener != null) {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
            }
        } else {
            int iMin = Math.min(iIntValue, size);
            final List<AppLovinNativeAd> listSubList = list2.subList(0, iMin);
            final List<AppLovinNativeAd> listSubList2 = list2.subList(iMin, size);
            b(listSubList, new AppLovinNativeAdLoadListener() { // from class: com.applovin.impl.sdk.NativeAdServiceImpl.4
                @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
                public void onNativeAdsFailedToLoad(int i) {
                    AppLovinNativeAdLoadListener appLovinNativeAdLoadListener2 = appLovinNativeAdLoadListener;
                    if (appLovinNativeAdLoadListener2 != null) {
                        appLovinNativeAdLoadListener2.onNativeAdsFailedToLoad(i);
                    }
                }

                @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
                public void onNativeAdsLoaded(List<AppLovinNativeAd> list3) {
                    NativeAdServiceImpl.this.c(listSubList, new AppLovinNativeAdLoadListener() { // from class: com.applovin.impl.sdk.NativeAdServiceImpl.4.1
                        @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
                        public void onNativeAdsFailedToLoad(int i) {
                            if (appLovinNativeAdLoadListener != null) {
                                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
                            }
                        }

                        @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
                        public void onNativeAdsLoaded(List<AppLovinNativeAd> list4) {
                            if (appLovinNativeAdLoadListener != null) {
                                ArrayList arrayList = new ArrayList();
                                arrayList.addAll(listSubList);
                                arrayList.addAll(listSubList2);
                                appLovinNativeAdLoadListener.onNativeAdsLoaded(arrayList);
                            }
                        }
                    });
                }
            });
        }
    }

    private void b(List<NativeAdImpl> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.F().a(new com.applovin.impl.sdk.d.e(list, this.a, new AppLovinNativeAdLoadListener() { // from class: com.applovin.impl.sdk.NativeAdServiceImpl.5
            @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
            public void onNativeAdsFailedToLoad(int i) {
                AppLovinNativeAdLoadListener appLovinNativeAdLoadListener2 = appLovinNativeAdLoadListener;
                if (appLovinNativeAdLoadListener2 != null) {
                    appLovinNativeAdLoadListener2.onNativeAdsFailedToLoad(i);
                }
            }

            @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
            public void onNativeAdsLoaded(List<AppLovinNativeAd> list2) {
                AppLovinNativeAdLoadListener appLovinNativeAdLoadListener2 = appLovinNativeAdLoadListener;
                if (appLovinNativeAdLoadListener2 != null) {
                    appLovinNativeAdLoadListener2.onNativeAdsLoaded(list2);
                }
            }
        }), com.applovin.impl.sdk.d.q.a.CACHING_OTHER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<NativeAdImpl> list, final AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.F().a(new com.applovin.impl.sdk.d.g(list, this.a, new AppLovinNativeAdLoadListener() { // from class: com.applovin.impl.sdk.NativeAdServiceImpl.6
            @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
            public void onNativeAdsFailedToLoad(int i) {
                NativeAdServiceImpl.this.a(appLovinNativeAdLoadListener, i);
            }

            @Override // com.applovin.nativeAds.AppLovinNativeAdLoadListener
            public void onNativeAdsLoaded(List<AppLovinNativeAd> list2) {
                NativeAdServiceImpl.this.a(appLovinNativeAdLoadListener, list2);
            }
        }), com.applovin.impl.sdk.d.q.a.CACHING_OTHER);
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdService
    public boolean hasPreloadedAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.e("NativeAdService", "Unable to check if ad is preloaded - invalid zone id.");
            return false;
        }
        return this.a.P().g(com.applovin.impl.sdk.ad.d.a(str, this.a));
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdService
    public void loadNativeAds(int i, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        loadNativeAds(i, null, appLovinNativeAdLoadListener);
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdService
    public void loadNativeAds(int i, String str, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        if (i <= 0) {
            this.b.e("NativeAdService", "Requested invalid number of native ads: " + i);
            return;
        }
        this.a.a();
        if (i != 1) {
            a(str, i, appLovinNativeAdLoadListener);
            return;
        }
        com.applovin.impl.sdk.ad.d dVarB = com.applovin.impl.sdk.ad.d.b(str, this.a);
        AppLovinNativeAd appLovinNativeAd = (AppLovinNativeAd) this.a.P().e(dVarB);
        if (appLovinNativeAd != null) {
            a(appLovinNativeAdLoadListener, Arrays.asList(appLovinNativeAd));
        } else {
            a(str, 1, appLovinNativeAdLoadListener);
        }
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.aY)).booleanValue()) {
            this.a.P().i(dVarB);
        }
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdService
    public void loadNextAd(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        loadNativeAds(1, appLovinNativeAdLoadListener);
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdService
    public void precacheResources(AppLovinNativeAd appLovinNativeAd, final AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        this.a.a();
        if (!appLovinNativeAd.isImagePrecached()) {
            this.a.F().a(new com.applovin.impl.sdk.d.e((List<NativeAdImpl>) Arrays.asList((NativeAdImpl) appLovinNativeAd), this.a, new AppLovinNativeAdPrecacheListener() { // from class: com.applovin.impl.sdk.NativeAdServiceImpl.2
                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdImagePrecachingFailed(AppLovinNativeAd appLovinNativeAd2, int i) {
                    NativeAdServiceImpl.this.a(appLovinNativeAdPrecacheListener, appLovinNativeAd2, i, false);
                }

                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdImagesPrecached(AppLovinNativeAd appLovinNativeAd2) {
                    NativeAdServiceImpl.this.a(appLovinNativeAdPrecacheListener, appLovinNativeAd2, false);
                    NativeAdServiceImpl.this.a(appLovinNativeAd2, appLovinNativeAdPrecacheListener);
                }

                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdVideoPrecachingFailed(AppLovinNativeAd appLovinNativeAd2, int i) {
                }

                @Override // com.applovin.nativeAds.AppLovinNativeAdPrecacheListener
                public void onNativeAdVideoPreceached(AppLovinNativeAd appLovinNativeAd2) {
                }
            }), com.applovin.impl.sdk.d.q.a.CACHING_OTHER);
        } else {
            appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
            a(appLovinNativeAd, appLovinNativeAdPrecacheListener);
        }
    }

    @Override // com.applovin.nativeAds.AppLovinNativeAdService
    public void preloadAdForZoneId(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.e("NativeAdService", "Unable to preload zone for invalid zone id.");
            return;
        }
        this.a.a();
        com.applovin.impl.sdk.ad.d dVarA = com.applovin.impl.sdk.ad.d.a(str, this.a);
        this.a.O().h(dVarA);
        this.a.O().i(dVarA);
    }

    public void preloadAds(com.applovin.impl.sdk.ad.d dVar) {
        this.a.O().h(dVar);
        int iG = dVar.g();
        if (iG == 0 && this.a.O().b(dVar)) {
            iG = 1;
        }
        this.a.O().b(dVar, iG);
    }

    public String toString() {
        return "NativeAdServiceImpl{}";
    }
}
