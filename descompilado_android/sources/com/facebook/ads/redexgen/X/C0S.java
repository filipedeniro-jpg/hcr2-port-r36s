package com.facebook.ads.redexgen.X;

import com.facebook.ads.internal.protocol.AdPlacementType;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0S, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public /* synthetic */ class C0S {
    public static final /* synthetic */ int[] B = new int[AdPlacementType.values().length];

    static {
        try {
            B[AdPlacementType.BANNER.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            B[AdPlacementType.INTERSTITIAL.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            B[AdPlacementType.NATIVE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            B[AdPlacementType.NATIVE_BANNER.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            B[AdPlacementType.INSTREAM.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            B[AdPlacementType.REWARDED_VIDEO.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
    }
}
