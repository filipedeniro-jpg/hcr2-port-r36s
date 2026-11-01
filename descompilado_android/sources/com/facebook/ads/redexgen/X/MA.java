package com.facebook.ads.redexgen.X;

import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MA extends M7 {
    private final long B;

    public MA(long maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.B = maxSize;
    }

    @Override // com.facebook.ads.redexgen.X.M7
    public final boolean A(File file, long totalSize, int totalCount) {
        return totalSize <= this.B;
    }

    @Override // com.facebook.ads.redexgen.X.M7, com.facebook.ads.redexgen.X.M0
    public final /* bridge */ /* synthetic */ void vF(File file) throws IOException {
        super.vF(file);
    }
}
