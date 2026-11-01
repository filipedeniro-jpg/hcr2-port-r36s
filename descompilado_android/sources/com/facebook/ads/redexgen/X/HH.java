package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class HH {
    public final String B;

    @Nullable
    public Integer C;
    public final String D;
    public final boolean E;
    public final String F;
    public final String G;

    public HH(String adFormat, String requestId, String creativeType, boolean duringAdLoading, String url) {
        this.B = adFormat;
        this.F = requestId;
        this.D = creativeType;
        this.E = duringAdLoading;
        this.G = url;
    }
}
