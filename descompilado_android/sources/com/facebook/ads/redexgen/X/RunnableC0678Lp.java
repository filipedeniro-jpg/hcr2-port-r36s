package com.facebook.ads.redexgen.X;

import java.util.concurrent.CountDownLatch;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class RunnableC0678Lp implements Runnable {
    public final /* synthetic */ C0679Lq B;
    private final CountDownLatch C;

    public RunnableC0678Lp(C0679Lq c0679Lq, CountDownLatch startSignal) {
        this.B = c0679Lq;
        this.C = startSignal;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.C.countDown();
        this.B.H();
    }
}
