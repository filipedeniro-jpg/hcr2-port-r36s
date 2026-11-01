package com.facebook.ads.redexgen.X;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class BF implements Runnable {
    private final WeakReference<C5I> B;

    public BF(C5I viewabilityChecker) {
        this.B = new WeakReference<>(viewabilityChecker);
    }

    public BF(WeakReference<C5I> weakReference) {
        this.B = weakReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C5I c5i = this.B.get();
        if (c5i != null) {
            c5i.F();
        }
    }
}
