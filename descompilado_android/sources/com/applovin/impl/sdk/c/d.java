package com.applovin.impl.sdk.c;

import android.annotation.TargetApi;
import android.app.Activity;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.sdk.AppLovinAd;

/* JADX INFO: loaded from: classes.dex */
public class d {
    private final AppLovinAdBase a;
    private final long b;
    private final c c;
    private final h d;
    private final com.applovin.impl.sdk.h e;
    private final Object f = new Object();
    private long g;
    private long h;
    private long i;
    private long j;
    private boolean k;

    public d(AppLovinAd appLovinAd, com.applovin.impl.sdk.h hVar) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.c = hVar.S();
        this.d = hVar.G();
        this.e = hVar;
        if (!(appLovinAd instanceof AppLovinAdBase)) {
            this.a = null;
            this.b = 0L;
        } else {
            this.a = (AppLovinAdBase) appLovinAd;
            this.b = this.a.getCreatedAtMillis();
            this.c.a(b.a, this.a.getSource().ordinal(), this.a);
        }
    }

    public static void a(long j, AppLovinAdBase appLovinAdBase, com.applovin.impl.sdk.h hVar) {
        if (appLovinAdBase == null || hVar == null) {
            return;
        }
        hVar.S().a(b.b, j, appLovinAdBase);
    }

    public static void a(AppLovinAdBase appLovinAdBase, com.applovin.impl.sdk.h hVar) {
        if (appLovinAdBase == null || hVar == null) {
            return;
        }
        hVar.S().a(b.c, appLovinAdBase.getFetchLatencyMillis(), appLovinAdBase);
        hVar.S().a(b.d, appLovinAdBase.getFetchResponseSize(), appLovinAdBase);
    }

    private void a(b bVar) {
        synchronized (this.f) {
            if (this.g > 0) {
                this.c.a(bVar, System.currentTimeMillis() - this.g, this.a);
            }
        }
    }

    public static void a(e eVar, AppLovinAdBase appLovinAdBase, com.applovin.impl.sdk.h hVar) {
        if (appLovinAdBase == null || hVar == null || eVar == null) {
            return;
        }
        hVar.S().a(b.e, eVar.c(), appLovinAdBase);
        hVar.S().a(b.f, eVar.d(), appLovinAdBase);
        hVar.S().a(b.v, eVar.g(), appLovinAdBase);
        hVar.S().a(b.w, eVar.h(), appLovinAdBase);
        hVar.S().a(b.z, eVar.b() ? 1L : 0L, appLovinAdBase);
    }

    @TargetApi(24)
    public void a() {
        this.c.a(b.j, this.d.a(g.b), this.a);
        this.c.a(b.i, this.d.a(g.d), this.a);
        synchronized (this.f) {
            long j = 0;
            if (this.b > 0) {
                this.g = System.currentTimeMillis();
                this.c.a(b.h, this.g - this.e.C(), this.a);
                this.c.a(b.g, this.g - this.b, this.a);
                this.c.a(b.p, com.applovin.impl.sdk.utils.f.a(this.e.z(), this.e) ? 1L : 0L, this.a);
                Activity activityA = this.e.V().a();
                if (com.applovin.impl.sdk.utils.e.h() && activityA != null && activityA.isInMultiWindowMode()) {
                    j = 1;
                }
                this.c.a(b.A, j, this.a);
            }
        }
    }

    public void a(long j) {
        this.c.a(b.r, j, this.a);
    }

    public void b() {
        synchronized (this.f) {
            if (this.h < 1) {
                this.h = System.currentTimeMillis();
                if (this.g > 0) {
                    this.c.a(b.m, this.h - this.g, this.a);
                }
            }
        }
    }

    public void b(long j) {
        this.c.a(b.q, j, this.a);
    }

    public void c() {
        a(b.k);
    }

    public void c(long j) {
        this.c.a(b.s, j, this.a);
    }

    public void d() {
        a(b.n);
    }

    public void d(long j) {
        synchronized (this.f) {
            if (this.i < 1) {
                this.i = j;
                this.c.a(b.t, j, this.a);
            }
        }
    }

    public void e() {
        a(b.o);
    }

    public void e(long j) {
        synchronized (this.f) {
            if (!this.k) {
                this.k = true;
                this.c.a(b.x, j, this.a);
            }
        }
    }

    public void f() {
        a(b.l);
    }

    public void g() {
        this.c.a(b.u, 1L, this.a);
    }

    public void h() {
        synchronized (this.f) {
            if (this.j < 1) {
                this.j = System.currentTimeMillis();
                if (this.g > 0) {
                    this.c.a(b.y, this.j - this.g, this.a);
                }
            }
        }
    }
}
