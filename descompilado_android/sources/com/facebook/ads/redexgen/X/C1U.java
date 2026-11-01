package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1U, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C1U {
    private C1T B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;

    public final C1U A(String adCreativeType) {
        this.B = C1T.B(adCreativeType);
        return this;
    }

    public final C1V B() {
        return new C1V(this);
    }

    public final C1U C(String category) {
        this.C = category;
        return this;
    }

    public final C1U D(String description) {
        this.D = description;
        return this;
    }

    public final C1U E(String destinationTitle) {
        this.E = destinationTitle;
        return this;
    }

    public final C1U F(String ratingCount) {
        this.F = ratingCount;
        return this;
    }

    public final C1U G(String ratingValue) {
        this.G = ratingValue;
        return this;
    }

    public final C1U H(String socialContext) {
        this.H = socialContext;
        return this;
    }

    public final C1U I(String subtitle) {
        this.I = subtitle;
        return this;
    }

    public final C1U J(String title) {
        this.J = title;
        return this;
    }
}
