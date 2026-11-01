package com.inmobi.ads;

import androidx.annotation.NonNull;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: VastNetworkClient.java */
/* JADX INFO: loaded from: classes2.dex */
final class bw {
    public static final Executor d;
    private static final String e = "bw";
    private static final int g = Runtime.getRuntime().availableProcessors();
    private static final int h = Math.max(2, Math.min(g - 1, 4));
    private static final int i = (g * 2) + 1;
    private static final ThreadFactory j = new ThreadFactory() { // from class: com.inmobi.ads.bw.1
        private final AtomicInteger a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "VastNetworkTask #" + this.a.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> k = new LinkedBlockingQueue(128);
    com.inmobi.commons.core.network.c a;
    WeakReference<bv> b;
    long c = 0;
    private final CountDownLatch f;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(h, i, 30L, TimeUnit.SECONDS, k, j);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        d = threadPoolExecutor;
    }

    public bw(bv bvVar, int i2, CountDownLatch countDownLatch) {
        this.a = new com.inmobi.commons.core.network.c(HttpRequest.METHOD_GET, bvVar.a);
        this.a.r = i2;
        this.b = new WeakReference<>(bvVar);
        this.f = countDownLatch;
    }

    final void a() {
        CountDownLatch countDownLatch = this.f;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public final void a(com.inmobi.commons.core.network.d dVar) {
        new StringBuilder("Vast Media Header Request fetch failed:").append(dVar.b.b);
        try {
            com.inmobi.a.n.a().a(this.a.g());
            com.inmobi.a.n.a().b(dVar.c());
        } catch (Exception e2) {
            new StringBuilder("Handling Vast Media Header Request fetch failed encountered an unexpected error: ").append(e2.getMessage());
        } finally {
            a();
        }
    }
}
