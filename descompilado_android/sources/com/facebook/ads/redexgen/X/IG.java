package com.facebook.ads.redexgen.X;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class IG {
    public static final int C = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService B = Executors.newFixedThreadPool(C);
    private static volatile boolean D = true;
}
