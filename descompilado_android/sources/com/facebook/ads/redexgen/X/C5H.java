package com.facebook.ads.redexgen.X;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5H, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C5H {
    private C5G B;
    private float C;
    private Map<String, String> D;

    public C5H(C5G type) {
        this(type, 0.0f);
    }

    public C5H(C5G type, float percent) {
        this(type, percent, null);
    }

    public C5H(C5G type, float percent, Map<String, String> map) {
        this.B = type;
        this.C = percent;
        if (map != null) {
            this.D = map;
        } else {
            Map<String, String> windowParams = new HashMap<>();
            this.D = windowParams;
        }
    }

    public final float A() {
        return this.C;
    }

    public final int B() {
        return this.B.A();
    }

    public final Map<String, String> C() {
        return this.D;
    }

    public final boolean D() {
        return this.B == C5G.IS_VIEWABLE;
    }
}
