package com.facebook.ads.redexgen.X;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class MC extends Thread implements Closeable {
    public final /* synthetic */ MB B;
    private boolean C = false;

    public MC(MB mb) {
        this.B = mb;
        start();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        synchronized (this) {
            this.C = true;
            this.B.E.A();
        }
        try {
            join();
        } catch (InterruptedException e) {
            throw new InterruptedIOException();
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x0044 */
    @Override // java.lang.Thread, java.lang.Runnable
    @android.annotation.SuppressLint({"CatchGeneralException"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r4 = this;
        L0:
            com.facebook.ads.redexgen.X.MB r0 = r4.B     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L5f
            com.facebook.ads.redexgen.X.MD r0 = com.facebook.ads.redexgen.X.MB.B(r0)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L5f
            r0.D()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L5f
            com.facebook.ads.redexgen.X.MB r2 = r4.B     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L5f
            monitor-enter(r2)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L5f
            monitor-enter(r4)     // Catch: java.lang.Throwable -> L44
            boolean r1 = r4.C     // Catch: java.lang.Throwable -> L41
            com.facebook.ads.redexgen.X.MB r0 = r4.B     // Catch: java.lang.Throwable -> L41
            com.facebook.ads.redexgen.X.MD r0 = com.facebook.ads.redexgen.X.MB.B(r0)     // Catch: java.lang.Throwable -> L41
            boolean r0 = r0.B()     // Catch: java.lang.Throwable -> L41
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L41
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L44
            if (r1 == 0) goto L21
        L1d:
            com.facebook.ads.redexgen.X.MB r2 = r4.B
            monitor-enter(r2)
            goto L2d
        L21:
            if (r0 == 0) goto L0
            com.facebook.ads.redexgen.X.MB r0 = r4.B     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L5f
            java.lang.Runnable r0 = com.facebook.ads.redexgen.X.MB.C(r0)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L5f
            r0.run()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L5f
            goto L1d
        L2d:
            com.facebook.ads.redexgen.X.MB r0 = r4.B     // Catch: java.lang.Throwable -> L3e
            com.facebook.ads.redexgen.X.MD r0 = com.facebook.ads.redexgen.X.MB.B(r0)     // Catch: java.lang.Throwable -> L3e
            r0.C()     // Catch: java.lang.Throwable -> L3e
            com.facebook.ads.redexgen.X.MB r1 = r4.B     // Catch: java.lang.Throwable -> L3e
            r0 = 0
            com.facebook.ads.redexgen.X.MB.D(r1, r0)     // Catch: java.lang.Throwable -> L3e
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3e
            goto L73
        L3e:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3e
            throw r0
        L41:
            r0 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L41
            throw r0     // Catch: java.lang.Throwable -> L44
        L44:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L44
            throw r0     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L5f
        L47:
            r3 = move-exception
            com.facebook.ads.redexgen.X.MB r2 = r4.B
            monitor-enter(r2)
            com.facebook.ads.redexgen.X.MB r0 = r4.B     // Catch: java.lang.Throwable -> L5c
            com.facebook.ads.redexgen.X.MD r0 = com.facebook.ads.redexgen.X.MB.B(r0)     // Catch: java.lang.Throwable -> L5c
            r0.C()     // Catch: java.lang.Throwable -> L5c
            com.facebook.ads.redexgen.X.MB r1 = r4.B     // Catch: java.lang.Throwable -> L5c
            r0 = 0
            com.facebook.ads.redexgen.X.MB.D(r1, r0)     // Catch: java.lang.Throwable -> L5c
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L5c
            throw r3
        L5c:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L5c
            throw r0
        L5f:
            r0 = move-exception
            com.facebook.ads.redexgen.X.MB r2 = r4.B
            monitor-enter(r2)
            com.facebook.ads.redexgen.X.MB r0 = r4.B     // Catch: java.lang.Throwable -> L74
            com.facebook.ads.redexgen.X.MD r0 = com.facebook.ads.redexgen.X.MB.B(r0)     // Catch: java.lang.Throwable -> L74
            r0.C()     // Catch: java.lang.Throwable -> L74
            com.facebook.ads.redexgen.X.MB r1 = r4.B     // Catch: java.lang.Throwable -> L74
            r0 = 0
            com.facebook.ads.redexgen.X.MB.D(r1, r0)     // Catch: java.lang.Throwable -> L74
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L74
        L73:
            return
        L74:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L74
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.redexgen.X.MC.run():void");
    }
}
