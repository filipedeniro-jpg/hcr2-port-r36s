package com.facebook.ads.redexgen.X;

import java.util.Iterator;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.23, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass23 {
    private AnonymousClass23() {
    }

    public static void B(H2 adCacheManager, C01601n adDataBundle) {
        adCacheManager.C(new C0560Gy(adDataBundle.P().B(), C03348f.E, C03348f.E, adDataBundle.Q(), "rewarded_video"));
        String videoUrlToCache = adDataBundle.K().H();
        adCacheManager.E(new H0(videoUrlToCache, adDataBundle.Q(), "rewarded_video"));
        adCacheManager.C(new C0560Gy(adDataBundle.K().B(), C01701x.B(adDataBundle.K()), C01701x.C(adDataBundle.K()), adDataBundle.Q(), "rewarded_video"));
        Iterator<String> it = adDataBundle.O().B().iterator();
        while (it.hasNext()) {
            adCacheManager.C(new C0560Gy(it.next(), -1, -1, adDataBundle.Q(), "rewarded_video"));
        }
    }
}
