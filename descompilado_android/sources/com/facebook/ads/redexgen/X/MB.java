package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MB implements Closeable {
    private boolean B;

    @Nullable
    private MC C;
    private final Runnable D;
    private final MD E;

    public MB(long nanoseconds, Runnable runnable) {
        this.E = new MD(nanoseconds);
        this.E.A();
        this.D = runnable;
        this.B = false;
    }

    public final MD A() {
        return this.E;
    }

    public final synchronized void B() {
        if (this.E.B() && !this.B) {
            C();
        }
    }

    public final synchronized void C() {
        if (this.C == null && !this.B) {
            this.C = new MC(this);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        MC mc;
        synchronized (this) {
            this.B = true;
            mc = this.C;
        }
        if (mc != null) {
            mc.close();
        }
    }
}
