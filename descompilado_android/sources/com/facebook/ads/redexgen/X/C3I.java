package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3I, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C3I {

    @InterfaceC01641r
    private String B;
    private String C;
    private int D;

    public C3I(int responseStatusCode, String responseMessage, @InterfaceC01641r String errorMessage) {
        this.D = responseStatusCode;
        this.C = responseMessage;
        this.B = errorMessage;
    }

    public final String A() {
        return this.B;
    }

    public final String B() {
        return this.C;
    }

    public final int C() {
        return this.D;
    }
}
