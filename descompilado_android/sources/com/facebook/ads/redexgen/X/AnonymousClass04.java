package com.facebook.ads.redexgen.X;

import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.04, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AnonymousClass04<T> implements Runnable {
    private final WeakReference<T> B;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.04 != com.facebook.ads.internal.util.common.WeakRunnable<T> */
    public AnonymousClass04(T t) {
        this.B = new WeakReference<>(t);
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.04 != com.facebook.ads.internal.util.common.WeakRunnable<T> */
    public final T A() {
        return this.B.get();
    }
}
