package com.facebook.ads.redexgen.X;

import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class CallableC0561Gz implements Callable<Boolean> {
    public final /* synthetic */ H2 B;
    private final C0560Gy C;

    public CallableC0561Gz(H2 h2, C0560Gy imageData) {
        this.B = h2;
        this.C = imageData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Boolean call() throws Exception {
        return Boolean.valueOf(this.B.E.C(this.C) != null);
    }
}
