package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class LT extends Exception {
    private static final long serialVersionUID = -2413629666163901633L;
    private LU B;

    public LT(Exception e, LU httpResponse) {
        super(e);
        this.B = httpResponse;
    }

    public final LU A() {
        return this.B;
    }
}
