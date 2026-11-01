package com.facebook.ads.redexgen.X;

import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class CallableC0675Lm implements Callable<Boolean> {
    public final /* synthetic */ C0679Lq B;

    private CallableC0675Lm(C0679Lq c0679Lq) {
        this.B = c0679Lq;
    }

    public /* synthetic */ CallableC0675Lm(C0679Lq x0, C0673Lk x1) {
        this(x0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Boolean call() throws Exception {
        return Boolean.valueOf(this.B.Q());
    }
}
