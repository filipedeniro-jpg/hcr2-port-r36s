package com.facebook.ads.redexgen.X;

import java.net.Socket;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class RunnableC0677Lo implements Runnable {
    public final /* synthetic */ C0679Lq B;
    private final Socket C;

    public RunnableC0677Lo(C0679Lq c0679Lq, Socket socket) {
        this.B = c0679Lq;
        this.C = socket;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.B.R(this.C);
    }
}
