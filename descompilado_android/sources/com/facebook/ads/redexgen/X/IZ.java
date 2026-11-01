package com.facebook.ads.redexgen.X;

import com.facebook.ads.AudienceNetworkAds;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class IZ implements AudienceNetworkAds.InitResult {
    private final String B;
    private final boolean C;

    public IZ(boolean success, String message) {
        this.C = success;
        this.B = message;
    }

    @Override // com.facebook.ads.AudienceNetworkAds.InitResult
    public final String getMessage() {
        return this.B;
    }

    @Override // com.facebook.ads.AudienceNetworkAds.InitResult
    public final boolean isSuccess() {
        return this.C;
    }
}
