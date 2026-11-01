package com.adcolony.sdk;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
class f {
    String a;
    private HashMap<Integer, Boolean> b = new HashMap<>();
    private aq c;
    private ae d;
    private int e;

    f(String str, int i) {
        this.a = str;
        this.e = i;
    }

    void a(af afVar) {
        if (this.c == null) {
            this.c = new aq(this.a, this.e);
            this.d = new ae(this.a, this.e);
        }
        int iC = y.c(afVar.c(), "id");
        if (y.d(afVar.c(), "use_sound_pool")) {
            this.b.put(Integer.valueOf(iC), true);
            this.c.a(afVar);
        } else {
            this.b.put(Integer.valueOf(iC), false);
            this.d.a(afVar);
        }
    }

    void a() {
        this.c.a().autoPause();
        this.d.a();
    }

    void b() {
        this.c.a().autoResume();
        this.d.b();
    }

    void b(af afVar) {
        if (this.b.get(Integer.valueOf(y.c(afVar.c(), "id"))).booleanValue()) {
            this.c.d(afVar);
        } else {
            this.d.b(afVar);
        }
    }

    void c(af afVar) {
        if (this.b.get(Integer.valueOf(y.c(afVar.c(), "id"))).booleanValue()) {
            this.c.c(afVar);
        } else {
            this.d.c(afVar);
        }
    }

    void d(af afVar) {
        if (this.b.get(Integer.valueOf(y.c(afVar.c(), "id"))).booleanValue()) {
            this.c.b(afVar);
        } else {
            this.d.d(afVar);
        }
    }

    void e(af afVar) {
        if (this.b.get(Integer.valueOf(y.c(afVar.c(), "id"))).booleanValue()) {
            this.c.e(afVar);
        } else {
            this.d.e(afVar);
        }
    }

    ae c() {
        return this.d;
    }

    aq d() {
        return this.c;
    }
}
