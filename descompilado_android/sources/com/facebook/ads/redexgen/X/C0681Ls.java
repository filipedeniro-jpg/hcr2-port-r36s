package com.facebook.ads.redexgen.X;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ls, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0681Ls {
    private final C0668Lf C;
    private volatile C0672Lj E;
    private final InterfaceC0665Lc F;
    private final String G;
    private final AtomicInteger B = new AtomicInteger(0);
    private final List<InterfaceC0665Lc> D = new CopyOnWriteArrayList();

    public C0681Ls(String url, C0668Lf config) {
        this.G = (String) C0684Lv.C(url);
        this.C = (C0668Lf) C0684Lv.C(config);
        this.F = new HandlerC0680Lr(url, this.D);
    }

    public final int A() {
        return this.B.get();
    }

    private synchronized void B() {
        if (this.B.decrementAndGet() <= 0) {
            this.E.E();
            this.E = null;
        }
    }

    public final void B(C0670Lh request, Socket socket) throws C0666Ld, IOException {
        D();
        try {
            this.B.incrementAndGet();
            this.E.F(request, socket);
        } finally {
            B();
        }
    }

    private C0672Lj C() throws C0666Ld {
        C0672Lj c0672Lj = new C0672Lj(new C0682Lt(this.G), new M1(this.C.A(this.G), this.C.C));
        c0672Lj.G(this.F);
        return c0672Lj;
    }

    /* JADX INFO: renamed from: C, reason: collision with other method in class */
    public final void m261C() {
        this.D.clear();
        if (this.E != null) {
            this.E.G(null);
            this.E.E();
            this.E = null;
        }
        this.B.set(0);
    }

    private synchronized void D() throws C0666Ld {
        this.E = this.E == null ? C() : this.E;
    }
}
