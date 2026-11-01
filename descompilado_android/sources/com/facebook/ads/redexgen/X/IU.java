package com.facebook.ads.redexgen.X;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class IU implements Callable<Void> {
    private final int B;
    private final int C;
    private final int D;
    private final int E;
    private final int[] F;
    private final int G;
    private final int H;

    public IU(int[] src, int w, int h, int radius, int totalCores, int coreIndex, int round) {
        this.F = src;
        this.H = w;
        this.C = h;
        this.D = radius;
        this.G = totalCores;
        this.B = coreIndex;
        this.E = round;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Void call() throws Exception {
        IS.C(this.F, this.H, this.C, this.D, this.G, this.B, this.E);
        return null;
    }
}
