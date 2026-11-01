package com.facebook.ads.redexgen.X;

import android.os.Build;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3k, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02093k<T> {
    private final int B;
    private final T C;
    private final int D;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3k != com.facebook.botdetection.signals.model.BundledSignalMetadata<T> */
    public C02093k(T bundledSignalMetadataKey, int addedApiLevel, int deprecatedApiLevel) {
        this.C = bundledSignalMetadataKey;
        this.B = addedApiLevel;
        this.D = deprecatedApiLevel;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3k != com.facebook.botdetection.signals.model.BundledSignalMetadata<T> */
    public final T A() {
        return this.C;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3k != com.facebook.botdetection.signals.model.BundledSignalMetadata<T> */
    public final boolean B() {
        return Build.VERSION.SDK_INT >= this.B && Build.VERSION.SDK_INT < this.D;
    }
}
