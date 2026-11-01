package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum LR {
    GET(true, false),
    POST(true, true);

    private boolean B;
    private boolean C;

    LR(boolean doInput, boolean doOutput) {
        this.B = doInput;
        this.C = doOutput;
    }

    public final boolean A() {
        return this.B;
    }

    public final boolean B() {
        return this.C;
    }

    public final String C() {
        return toString();
    }
}
