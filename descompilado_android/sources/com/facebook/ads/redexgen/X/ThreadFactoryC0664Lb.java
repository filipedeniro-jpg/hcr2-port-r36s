package com.facebook.ads.redexgen.X;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class ThreadFactoryC0664Lb implements ThreadFactory {
    public final AtomicLong B = new AtomicLong();
    private int C = Thread.currentThread().getPriority();

    private final String B() {
        return String.format(Locale.US, "com.facebook.ads thread-%d %tF %<tT", Long.valueOf(this.B.incrementAndGet()), Long.valueOf(System.currentTimeMillis()));
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r) {
        Thread thread = new Thread(null, r, B(), 0L);
        thread.setPriority(this.C);
        return thread;
    }
}
