package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import java.io.Serializable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1R, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C1R implements Serializable {
    private static final long serialVersionUID = -268645651038092386L;
    private final int B;
    private final String C;
    private final int D;
    private final boolean E;
    private final boolean F;

    @Nullable
    private final C01571k G;
    private final int H;
    private final int I;
    private final int J;
    private final String K;

    private C1R(C1Q builder) {
        this.K = builder.K;
        this.I = builder.I;
        this.J = builder.J;
        this.F = builder.F;
        this.E = builder.E;
        this.C = builder.C;
        this.D = builder.D;
        this.B = builder.B;
        this.G = builder.G;
        this.H = builder.H;
    }

    public final int A() {
        return this.B;
    }

    public final String B() {
        return this.C;
    }

    public final int C() {
        return this.D;
    }

    @Nullable
    public final C01571k D() {
        return this.G;
    }

    public final int E() {
        return this.H;
    }

    public final int F() {
        return this.I;
    }

    public final int G() {
        return this.J;
    }

    public final String H() {
        return this.K;
    }

    public final boolean I() {
        return this.E;
    }
}
