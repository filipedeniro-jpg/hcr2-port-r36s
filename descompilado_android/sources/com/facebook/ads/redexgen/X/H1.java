package com.facebook.ads.redexgen.X;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class H1 implements Callable<Boolean> {
    public final /* synthetic */ H2 B;
    private final H0 C;

    private H1(H2 h2, H0 videoData) {
        this.B = h2;
        this.C = videoData;
    }

    public /* synthetic */ H1(H2 x0, H0 x1, RunnableC0557Gv x2) {
        this(x0, x1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Boolean call() throws Exception {
        return Boolean.valueOf(this.B.K.A(this.C));
    }
}
