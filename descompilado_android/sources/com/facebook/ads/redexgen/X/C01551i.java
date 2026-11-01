package com.facebook.ads.redexgen.X;

import java.io.Serializable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1i, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01551i implements Serializable {
    private static final long serialVersionUID = 351643298236575728L;
    private final String B;
    private final String C;
    private final String D;
    private final String E;

    private C01551i(C01541h builder) {
        this.D = builder.D;
        this.C = builder.C;
        this.B = builder.B;
        this.E = builder.E;
    }

    public final String A() {
        return this.B;
    }

    public final String B() {
        return this.C;
    }

    public final String C() {
        return this.D;
    }

    public final String D() {
        return this.E;
    }
}
