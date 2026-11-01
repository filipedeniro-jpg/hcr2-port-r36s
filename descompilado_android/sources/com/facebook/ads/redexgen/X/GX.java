package com.facebook.ads.redexgen.X;

import com.facebook.ads.internal.api.NativeAdViewTypeApi;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class GX implements NativeAdViewTypeApi {
    private final KJ B;

    public GX(int enumCode) {
        this.B = KJ.B(enumCode);
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewTypeApi
    public final int getHeight() {
        return this.B.A();
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewTypeApi
    public final int getValue() {
        return this.B.B();
    }

    @Override // com.facebook.ads.internal.api.NativeAdViewTypeApi
    public final int getWidth() {
        return this.B.C();
    }
}
