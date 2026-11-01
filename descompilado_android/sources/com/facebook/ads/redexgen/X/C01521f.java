package com.facebook.ads.redexgen.X;

import java.io.Serializable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1f, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01521f implements Serializable {
    private static final long serialVersionUID = 351643298236575729L;
    private final String B;
    private final String C;
    private final String D;
    private final String E;

    private C01521f(C01511e builder) {
        this.D = builder.D;
        this.B = builder.B;
        this.E = builder.E;
        this.C = builder.C;
    }

    public final String A() {
        return this.B;
    }

    public final String B() {
        return this.C;
    }

    public final String C() {
        return this.E;
    }
}
