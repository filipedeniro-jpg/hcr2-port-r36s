package com.facebook.ads.redexgen.X;

import com.facebook.ads.NativeAdBase;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum K0 {
    NONE(0, NativeAdBase.MediaCacheFlag.NONE),
    ALL(1, NativeAdBase.MediaCacheFlag.ALL);

    private final long B;
    private final NativeAdBase.MediaCacheFlag C;

    K0(long cacheFlagValue, NativeAdBase.MediaCacheFlag mediaCacheFlag) {
        this.B = cacheFlagValue;
        this.C = mediaCacheFlag;
    }

    public static K0 B(NativeAdBase.MediaCacheFlag cacheFlag) {
        for (K0 k0 : values()) {
            if (k0.C == cacheFlag) {
                return k0;
            }
        }
        return null;
    }
}
