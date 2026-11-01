package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0f, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC01260f {
    public final boolean B;
    public final double C;
    public final double D;
    public final double E;

    public abstract void A(boolean z, boolean z2, C01912s c01912s);

    public AbstractC01260f(double viewableRatio, double startTimeSeconds, double viewableSeconds, boolean isContinuous) {
        this.D = viewableRatio;
        this.C = startTimeSeconds;
        this.E = viewableSeconds;
        this.B = isContinuous;
    }
}
