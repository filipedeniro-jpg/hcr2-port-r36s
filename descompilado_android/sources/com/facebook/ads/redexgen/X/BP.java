package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class BP {
    private final String I;
    private long C = -1;
    private long E = -1;
    private long F = -1;
    private long B = -1;
    private long G = -1;
    private long D = -1;
    private long H = -1;

    public BP(String url) {
        this.I = url;
    }

    public final BO A() {
        return new BO(this.I, this.C, this.E, this.F, this.B, this.G, this.D, this.H);
    }

    public final BP B(long domContentLoadedMs) {
        this.B = domContentLoadedMs;
        return this;
    }

    public final BP C(long handlerTimeMs) {
        this.C = handlerTimeMs;
        return this;
    }

    public final BP D(long loadFinishTime) {
        this.D = loadFinishTime;
        return this;
    }

    public final BP E(long loadStartMs) {
        this.E = loadStartMs;
        return this;
    }

    public final BP F(long responseEndMs) {
        this.F = responseEndMs;
        return this;
    }

    public final BP G(long scrollReadyMs) {
        this.G = scrollReadyMs;
        return this;
    }

    public final BP H(long sessionFinishTime) {
        this.H = sessionFinishTime;
        return this;
    }
}
