package com.applovin.impl.sdk;

/* JADX INFO: loaded from: classes.dex */
public class d implements m.a, s.a {
    private com.applovin.impl.sdk.utils.l a;
    private final Object b = new Object();
    private final h c;
    private final a d;
    private long e;

    public interface a {
        void onAdRefresh();
    }

    public d(h hVar, a aVar) {
        this.d = aVar;
        this.c = hVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        synchronized (this.b) {
            this.a = null;
            this.c.T().b(this);
            this.c.U().b(this);
        }
    }

    public void a(long j) {
        synchronized (this.b) {
            e();
            this.e = j;
            this.a = com.applovin.impl.sdk.utils.l.a(j, this.c, new Runnable() { // from class: com.applovin.impl.sdk.d.1
                @Override // java.lang.Runnable
                public void run() {
                    d.this.j();
                    d.this.d.onAdRefresh();
                }
            });
            this.c.T().a(this);
            this.c.U().a(this);
            if (((Boolean) this.c.a(com.applovin.impl.sdk.b.a.B)).booleanValue() && (this.c.U().b() || this.c.T().a())) {
                this.a.b();
            }
        }
    }

    public boolean a() {
        boolean z;
        synchronized (this.b) {
            z = this.a != null;
        }
        return z;
    }

    @Override // com.applovin.impl.sdk.s.a
    public void b() {
        if (((Boolean) this.c.a(com.applovin.impl.sdk.b.a.A)).booleanValue()) {
            f();
        }
    }

    @Override // com.applovin.impl.sdk.s.a
    public void c() {
        if (((Boolean) this.c.a(com.applovin.impl.sdk.b.a.A)).booleanValue()) {
            synchronized (this.b) {
                if (this.c.U().b()) {
                    this.c.w().a("AdRefreshManager", "Waiting for the full screen ad to be dismissed to resume the timer.");
                    return;
                }
                boolean z = false;
                if (this.a != null) {
                    long jD = this.e - d();
                    long jLongValue = ((Long) this.c.a(com.applovin.impl.sdk.b.a.z)).longValue();
                    if (jLongValue < 0 || jD <= jLongValue) {
                        this.a.c();
                    } else {
                        e();
                        z = true;
                    }
                }
                if (z) {
                    this.d.onAdRefresh();
                }
            }
        }
    }

    public long d() {
        long jA;
        synchronized (this.b) {
            jA = this.a != null ? this.a.a() : -1L;
        }
        return jA;
    }

    public void e() {
        synchronized (this.b) {
            if (this.a != null) {
                this.a.d();
                j();
            }
        }
    }

    public void f() {
        synchronized (this.b) {
            if (this.a != null) {
                this.a.b();
            }
        }
    }

    public void g() {
        synchronized (this.b) {
            if (this.a != null) {
                this.a.c();
            }
        }
    }

    @Override // com.applovin.impl.sdk.m.a
    public void h() {
        if (((Boolean) this.c.a(com.applovin.impl.sdk.b.a.B)).booleanValue()) {
            f();
        }
    }

    @Override // com.applovin.impl.sdk.m.a
    public void i() {
        if (((Boolean) this.c.a(com.applovin.impl.sdk.b.a.B)).booleanValue()) {
            synchronized (this.b) {
                if (this.c.T().a()) {
                    this.c.w().a("AdRefreshManager", "Waiting for the application to enter foreground to resume the timer.");
                } else {
                    if (this.a != null) {
                        this.a.c();
                    }
                }
            }
        }
    }
}
