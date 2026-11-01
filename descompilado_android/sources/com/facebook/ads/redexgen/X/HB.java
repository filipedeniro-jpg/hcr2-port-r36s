package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class HB implements HA {
    @Override // com.facebook.ads.redexgen.X.HA
    public final boolean BG(String url) {
        return HJ.E(url);
    }

    @Override // com.facebook.ads.redexgen.X.HA
    public final void cB(String url, boolean loading, int eventCode, @Nullable String failureReason, @Nullable Integer creativeSize, @Nullable Long loadTime) {
        HI.E(url, loading, eventCode, failureReason, creativeSize, loadTime);
    }

    @Override // com.facebook.ads.redexgen.X.HA
    public final void dB(String url, boolean loading) {
        HI.F(url, true, loading);
    }

    @Override // com.facebook.ads.redexgen.X.HA
    public final void eB(String url, boolean loading) {
        HI.F(url, false, loading);
    }
}
