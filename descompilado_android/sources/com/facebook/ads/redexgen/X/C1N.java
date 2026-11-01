package com.facebook.ads.redexgen.X;

import java.io.Serializable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1N, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C1N implements Serializable {
    private static final long serialVersionUID = 9136244113276723461L;
    private final C1Y B;
    private final C1Y C;

    public C1N(C1Y portraitColorInfo, C1Y landscapeColorInfo) {
        this.C = portraitColorInfo;
        this.B = landscapeColorInfo;
    }

    public final C1Y A() {
        return this.B;
    }

    public final C1Y B() {
        return this.C;
    }
}
