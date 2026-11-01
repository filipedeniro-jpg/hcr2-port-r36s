package com.applovin.impl.sdk;

/* JADX INFO: loaded from: classes.dex */
public class b implements s.a {
    private final h a;
    private final a b;
    private com.applovin.impl.sdk.utils.l c;
    private final Object d = new Object();
    private long e;

    public interface a {
        void onAdExpired();
    }

    public b(h hVar, a aVar) {
        this.a = hVar;
        this.b = aVar;
    }

    private void d() {
        com.applovin.impl.sdk.utils.l lVar = this.c;
        if (lVar != null) {
            lVar.d();
            this.c = null;
        }
    }

    public void a() {
        synchronized (this.d) {
            d();
            this.a.T().b(this);
        }
    }

    public void a(long j) {
        synchronized (this.d) {
            a();
            this.e = System.currentTimeMillis() + j;
            this.a.T().a(this);
            this.c = com.applovin.impl.sdk.utils.l.a(j, this.a, new Runnable() { // from class: com.applovin.impl.sdk.b.1
                @Override // java.lang.Runnable
                public void run() {
                    b.this.a();
                    b.this.b.onAdExpired();
                }
            });
        }
    }

    @Override // com.applovin.impl.sdk.s.a
    public void b() {
        synchronized (this.d) {
            d();
        }
    }

    @Override // com.applovin.impl.sdk.s.a
    public void c() {
        boolean z;
        synchronized (this.d) {
            long jCurrentTimeMillis = this.e - System.currentTimeMillis();
            if (jCurrentTimeMillis <= 0) {
                a();
                z = true;
            } else {
                a(jCurrentTimeMillis);
                z = false;
            }
        }
        if (z) {
            this.b.onAdExpired();
        }
    }
}
