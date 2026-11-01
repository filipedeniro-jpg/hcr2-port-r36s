package com.facebook.ads.redexgen.X;

import com.google.android.exoplayer2.C;
import java.util.Locale;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class MD {
    private long B;
    private long C;

    public MD(long nanoseconds) {
        if (nanoseconds < 0) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid number of nanoseconds for the timer: %d", Long.valueOf(nanoseconds)));
        }
        this.B = System.nanoTime() + nanoseconds;
        this.C = nanoseconds;
    }

    public final synchronized void A() {
        this.B = System.nanoTime();
        notifyAll();
    }

    public final synchronized boolean B() {
        return System.nanoTime() >= this.B;
    }

    public final synchronized void C() {
        this.B = System.nanoTime() + this.C;
    }

    public final synchronized void D() throws InterruptedException {
        while (!B()) {
            long jMin = Math.min(this.B - System.nanoTime(), 1L);
            wait(jMin / C.NANOS_PER_SECOND, (int) (jMin % C.NANOS_PER_SECOND));
        }
    }
}
