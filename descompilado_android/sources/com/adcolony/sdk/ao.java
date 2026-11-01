package com.adcolony.sdk;

import android.app.Activity;
import com.facebook.login.widget.ToolTipPopup;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ao implements Runnable {
    private long f;
    private long g;
    private long h;
    private long i;
    private long j;
    private long k;
    private long l;
    private long m;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private final long a = 30000;
    private final long b = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
    private final int c = 17;
    private final int d = DefaultLoadControl.DEFAULT_MIN_BUFFER_MS;
    private final int e = 1000;
    private boolean n = true;
    private boolean o = true;

    ao() {
    }

    public void a() {
        a.a("SessionInfo.stopped", new ah() { // from class: com.adcolony.sdk.ao.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                ao.this.u = true;
            }
        });
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!this.s) {
            this.i = System.currentTimeMillis();
            a.f();
            if (this.g >= 30000) {
                new aa.a().a("Ending session due to excessive suspend time: ").a(this.g).a(aa.d);
                break;
            }
            if (!this.n) {
                if (this.p && !this.o) {
                    this.p = false;
                    f();
                }
                if (!this.t && a.d() && a.c().isFinishing()) {
                    this.t = true;
                    this.k = 0L;
                }
                if (this.t) {
                    this.k += this.h;
                    if (this.k > DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) {
                        new aa.a().a("Ending session due to excessive time between an ").a("Activity finishing and an onResume() event.").a(aa.d);
                        break;
                    }
                }
                this.g += this.h;
            } else {
                if (this.p && this.o) {
                    this.p = false;
                    this.t = false;
                    g();
                }
                this.g = 0L;
            }
            this.h = 17L;
            a(this.h);
            this.j = System.currentTimeMillis() - this.i;
            long j = this.j;
            if (j > 0 && j < ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME) {
                this.f += j;
            }
            l lVarA = a.a();
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.m > 15000 && lVarA.B()) {
                lVarA.k().g();
                this.m = jCurrentTimeMillis;
            }
            if (a.d() && jCurrentTimeMillis - this.l > 1000) {
                this.l = jCurrentTimeMillis;
                String strC = lVarA.d.c();
                if (!strC.equals(lVarA.v())) {
                    lVarA.a(strC);
                    JSONObject jSONObjectA = y.a();
                    y.a(jSONObjectA, "network_type", lVarA.v());
                    new af("Network.on_status_change", 1, jSONObjectA).b();
                }
            }
        }
        new aa.a().a("AdColony session ending, releasing Activity reference.").a(aa.c);
        a.a().b(true);
        a.a((Activity) null);
        this.r = true;
        this.v = true;
        b();
        aw.a aVar = new aw.a(10.0d);
        while (!this.u && !aVar.b() && this.v) {
            a.f();
            a(100L);
        }
        new aa.a().a("SessionInfo.stopped message received, ending ADC.update_module() spam.").a(aa.d);
    }

    void a(boolean z) {
        if (this.q) {
            return;
        }
        if (this.r) {
            a.a().b(false);
            this.r = false;
        }
        this.f = 0L;
        this.g = 0L;
        this.q = true;
        this.n = true;
        this.u = false;
        new Thread(this).start();
        if (z) {
            JSONObject jSONObjectA = y.a();
            y.a(jSONObjectA, "id", aw.e());
            new af("SessionInfo.on_start", 1, jSONObjectA).b();
        }
        if (AdColony.a.isShutdown()) {
            AdColony.a = Executors.newSingleThreadExecutor();
        }
        ac.a();
    }

    void b() {
        this.q = false;
        this.n = false;
        if (ac.l != null) {
            ac.l.a();
        }
        ac.b();
        JSONObject jSONObjectA = y.a();
        double d = this.f;
        Double.isNaN(d);
        y.a(jSONObjectA, "session_length", d / 1000.0d);
        new af("SessionInfo.on_stop", 1, jSONObjectA).b();
        a.f();
        AdColony.a.shutdown();
        new aa.a().a("SESSION STOP").a(aa.d);
    }

    private void f() {
        b(false);
    }

    void b(boolean z) {
        ArrayList<ai> arrayListC = a.a().q().c();
        synchronized (arrayListC) {
            for (ai aiVar : arrayListC) {
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, "from_window_focus", z);
                new af("SessionInfo.on_pause", aiVar.a(), jSONObjectA).b();
            }
        }
        this.o = true;
        a.f();
    }

    private void g() {
        c(false);
    }

    void c(boolean z) {
        ArrayList<ai> arrayListC = a.a().q().c();
        synchronized (arrayListC) {
            for (ai aiVar : arrayListC) {
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, "from_window_focus", z);
                new af("SessionInfo.on_resume", aiVar.a(), jSONObjectA).b();
            }
        }
        ac.a();
        this.o = false;
    }

    void d(boolean z) {
        this.n = z;
    }

    void e(boolean z) {
        this.p = z;
    }

    void f(boolean z) {
        this.v = z;
    }

    boolean c() {
        return this.n;
    }

    boolean d() {
        return this.p;
    }

    boolean e() {
        return this.q;
    }

    private void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
        }
    }
}
