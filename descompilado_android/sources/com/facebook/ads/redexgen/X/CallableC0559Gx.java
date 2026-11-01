package com.facebook.ads.redexgen.X;

import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class CallableC0559Gx implements Callable<Boolean> {
    public final /* synthetic */ H2 B;
    private final H0 C;

    public CallableC0559Gx(H2 h2, H0 cacheFileData) {
        this.B = h2;
        this.C = cacheFileData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Boolean call() throws Exception {
        return Boolean.valueOf(this.B.B.A(this.C));
    }
}
