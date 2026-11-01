package com.facebook.ads.redexgen.X;

import java.io.File;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0668Lf {
    public final File B;
    public final M0 C;
    public final M2 D;

    public C0668Lf(File cacheRoot, M2 fileNameGenerator, M0 diskUsage) {
        this.B = cacheRoot;
        this.D = fileNameGenerator;
        this.C = diskUsage;
    }

    public final File A(String url) {
        return new File(this.B, this.D.sB(url));
    }
}
