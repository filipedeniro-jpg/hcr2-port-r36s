package com.facebook.ads.redexgen.X;

import java.io.Serializable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1q, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01631q implements Serializable {
    private static final long serialVersionUID = 42;
    private final String B;
    private final String C;

    private C01631q(C01621p builder) {
        this.B = builder.B;
        this.C = builder.C;
    }

    public final String A() {
        return this.B;
    }

    public final String B() {
        return this.C;
    }
}
