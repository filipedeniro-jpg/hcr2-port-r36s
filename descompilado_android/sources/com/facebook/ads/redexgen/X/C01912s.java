package com.facebook.ads.redexgen.X;

import java.io.Serializable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2s, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01912s implements Serializable {
    private C01902r B;
    private C01902r C;

    public C01912s() {
        this(0.5d, 0.5d);
    }

    public C01912s(double viewableThreshold) {
        this(viewableThreshold, 0.5d);
    }

    private C01912s(double viewableThreshold, double audibleThreshold) {
        this.B = new C01902r(viewableThreshold);
        this.C = new C01902r(audibleThreshold);
        E();
    }

    public final C01902r A() {
        return this.B;
    }

    public final C01902r B() {
        return this.C;
    }

    public final void C(double seconds, double viewableRatio) {
        this.B.G(seconds, viewableRatio);
    }

    public final void D(double seconds, double mVolume) {
        this.C.G(seconds, mVolume);
    }

    public final void E() {
        this.B.H();
        this.C.H();
    }

    public final void F() {
        this.B.I();
        this.C.I();
    }
}
