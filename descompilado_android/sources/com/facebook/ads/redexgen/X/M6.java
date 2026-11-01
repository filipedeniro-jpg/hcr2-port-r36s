package com.facebook.ads.redexgen.X;

import java.io.File;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class M6 implements Callable<Void> {
    public final /* synthetic */ M7 B;
    private final File C;

    public M6(M7 m7, File file) {
        this.B = m7;
        this.C = file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Void call() throws Exception {
        this.B.D(this.C);
        return null;
    }
}
