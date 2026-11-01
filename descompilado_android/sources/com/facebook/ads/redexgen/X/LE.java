package com.facebook.ads.redexgen.X;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class LE implements ThreadFactory {
    public final /* synthetic */ LD B;
    public final /* synthetic */ String C;
    private final AtomicInteger D = new AtomicInteger(1);

    public LE(LD this$0, String str) {
        this.B = this$0;
        this.C = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r) {
        return new Thread(r, String.format(Locale.US, "FAN:%s #%d", this.C, Integer.valueOf(this.D.getAndIncrement())));
    }
}
