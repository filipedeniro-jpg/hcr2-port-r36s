package com.facebook.ads.redexgen.X;

import com.ironsource.sdk.constants.Constants;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.15, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum AnonymousClass15 {
    AVAILABLE(Constants.ParametersKeys.AVAILABLE),
    TOO_SMALL("too_small"),
    NO_NATIVE_AD_LAYOUT("no_native_ad_layout");

    private final String B;

    AnonymousClass15(String value) {
        this.B = value;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.B;
    }
}
