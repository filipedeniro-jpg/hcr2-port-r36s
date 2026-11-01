package com.facebook.ads.redexgen.X;

import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ln, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class CallableC0676Ln implements Callable<Boolean> {
    public final /* synthetic */ C0679Lq B;
    private final String C;

    public CallableC0676Ln(C0679Lq c0679Lq, String url) {
        this.B = c0679Lq;
        this.C = url;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Boolean call() throws Exception {
        return Boolean.valueOf(this.B.S(this.C));
    }
}
