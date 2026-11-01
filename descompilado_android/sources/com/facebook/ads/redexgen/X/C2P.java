package com.facebook.ads.redexgen.X;

import com.facebook.ads.AdSettings;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2P, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public /* synthetic */ class C2P {
    public static final /* synthetic */ int[] B = new int[AdSettings.IntegrationErrorMode.values().length];

    static {
        try {
            B[AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            B[AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CALLBACK_MODE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
