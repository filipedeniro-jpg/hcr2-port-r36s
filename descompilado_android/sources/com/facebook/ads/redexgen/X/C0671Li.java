package com.facebook.ads.redexgen.X;

import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Li, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0671Li {
    private final LY B;
    private final InterfaceC0663La F;
    private volatile Thread G;
    private volatile boolean I;
    private final Object J = new Object();
    private final Object H = new Object();
    private volatile int D = -1;
    private final AtomicInteger E = new AtomicInteger();
    private final AtomicReference<Throwable> C = new AtomicReference<>();

    public C0671Li(InterfaceC0663La source, LY cache) {
        this.F = (InterfaceC0663La) C0684Lv.C(source);
        this.B = (LY) C0684Lv.C(cache);
    }

    public void A(int percentsAvailable) {
    }

    public final int B(byte[] buffer, long offset, int length) throws C0666Ld {
        C0687Ly.B(buffer, offset, length);
        while (!this.B.RC() && this.B.available() < ((long) length) + offset && !this.I) {
            J();
            L();
            C();
        }
        int iGF = this.B.gF(buffer, offset, length);
        if (this.B.RC() && this.D != 100) {
            this.D = 100;
            A(100);
        }
        return iGF;
    }

    private void C() throws C0666Ld {
        int i = this.E.get();
        if (i >= 1) {
            this.E.set(0);
            String str = "Error reading source " + i + " times";
            Throwable th = this.C.get();
            if (th instanceof C0669Lg) {
                throw new C0669Lg(str);
            }
            if (th instanceof C0667Le) {
                throw new C0667Le(str);
            }
            throw new C0666Ld(str);
        }
    }

    private void D() {
        try {
            this.F.close();
        } catch (C0666Ld | IllegalArgumentException e) {
            H(new C0666Ld("Error closing source " + this.F, e));
        }
    }

    /* JADX INFO: renamed from: E, reason: collision with other method in class */
    public final void m260E() {
        synchronized (this.H) {
            Log.d("ProxyCache", "Shutdown proxy for " + this.F);
            try {
                this.I = true;
                if (this.G != null) {
                    this.G.interrupt();
                }
                this.B.close();
            } catch (C0666Ld e) {
                H(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    boolean E() {
        return Thread.currentThread().isInterrupted() || this.I;
    }

    private void F(long cacheAvailable, long sourceAvailable) {
        G(cacheAvailable, sourceAvailable);
        synchronized (this.J) {
            this.J.notifyAll();
        }
    }

    private final void G(long cacheAvailable, long sourceLength) {
        int i = (sourceLength > 0L ? 1 : (sourceLength == 0L ? 0 : -1)) == 0 ? 100 : (int) ((100 * cacheAvailable) / sourceLength);
        boolean z = i != this.D;
        if ((sourceLength >= 0) && z) {
            A(i);
        }
        this.D = i;
    }

    private final void H(Throwable e) {
        if (e instanceof C0683Lu) {
            Log.d("ProxyCache", "ProxyCache is interrupted");
        } else {
            Log.e("ProxyCache", "ProxyCache error", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        try {
            try {
                int offset = this.B.available();
                this.F.SF(offset);
                int length = this.F.length();
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = this.F.read(bArr);
                    if (i != -1) {
                        synchronized (this.H) {
                            if (!E()) {
                                this.B.YB(bArr, i);
                            } else {
                                D();
                                F(offset, length);
                                return;
                            }
                        }
                        offset += i;
                        F(offset, length);
                    } else {
                        K();
                        D();
                        F(offset, length);
                        return;
                    }
                }
            } catch (Throwable th) {
                this.C.set(th);
                this.E.incrementAndGet();
                H(th);
                D();
                F(0, -1);
            }
        } catch (Throwable th2) {
            D();
            F(0, -1);
            throw th2;
        }
    }

    private synchronized void J() {
        boolean z = (this.G == null || this.G.getState() == Thread.State.TERMINATED) ? false : true;
        if (!this.I && !this.B.RC() && !z) {
            this.G = new Thread(new RunnableC0686Lx(this), "Source reader for " + this.F);
            this.G.start();
        }
    }

    private void K() throws C0666Ld {
        synchronized (this.H) {
            if (!E() && this.B.available() == this.F.length()) {
                this.B.iB();
            }
        }
    }

    private void L() throws C0666Ld {
        synchronized (this.J) {
            try {
                this.J.wait(1000L);
            } catch (InterruptedException e) {
                throw new C0666Ld("Waiting source data is interrupted!", e);
            }
        }
    }
}
