package com.facebook.ads.redexgen.X;

import com.ironsource.sdk.constants.Constants;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0574Hm {
    private Map<String, String> B;
    private String C;
    private double D;
    private double E = System.currentTimeMillis() / 1000.0d;

    public C0574Hm(double mSessionTime, String mSessionId, Map<String, String> map) {
        this.D = mSessionTime;
        this.C = mSessionId;
        this.B = map;
    }

    public final Map<String, String> A() {
        return this.B;
    }

    public final String B() {
        return this.C;
    }

    public final double C() {
        return this.D;
    }

    public final double D() {
        return this.E;
    }

    public final String E() {
        return Constants.RequestParameters.DEBUG;
    }
}
