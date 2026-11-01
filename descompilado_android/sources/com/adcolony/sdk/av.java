package com.adcolony.sdk;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class av {
    an a;
    boolean i;
    boolean j;
    ad b = new ad().b();
    float[] c = new float[16];
    ad d = new ad().b();
    ad e = new ad().b();
    ad f = new ad().b();
    ArrayList<ad> g = new ArrayList<>();
    ArrayList<ad> h = new ArrayList<>();
    boolean k = true;

    av(an anVar) {
        this.a = anVar;
    }

    void a() {
        this.a.d();
        this.e.b();
        this.i = true;
    }

    void b() {
        a();
        while (d()) {
        }
    }

    ad c() {
        int size = this.h.size();
        if (size == 0) {
            return new ad();
        }
        return this.h.remove(size - 1);
    }

    boolean d() {
        int size = this.g.size();
        if (size == 0) {
            return false;
        }
        this.a.d();
        this.j = true;
        this.h.add(this.g.remove(size - 1));
        this.e.b();
        return true;
    }

    void e() {
        this.a.d();
        this.g.add(c().b(this.e));
        this.e.b();
        this.j = true;
        this.i = true;
    }

    void a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        double d10;
        double d11;
        boolean z;
        this.a.d();
        double d12 = d3 / d8;
        double d13 = d4 / d9;
        double d14 = 1.0d;
        boolean z2 = true;
        if (d12 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d10 = d12;
            d11 = 1.0d;
            z = false;
        } else {
            d10 = -d12;
            d11 = -1.0d;
            z = true;
        }
        if (d13 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            z2 = false;
        } else {
            d13 = -d13;
            d14 = -1.0d;
        }
        double d15 = d5 * d3;
        double d16 = d6 * d4;
        if (z || z2) {
            d15 -= d3 / 2.0d;
            d16 -= d4 / 2.0d;
            b((-d3) / 2.0d, (-d4) / 2.0d);
        }
        double dCos = Math.cos(d7);
        double dSin = Math.sin(d7);
        double d17 = d15 * d10;
        double d18 = (d - (d17 * dCos)) + (dSin * d13 * d16);
        double d19 = dCos * d13;
        this.e.a(dCos * d10 * d11, d10 * dSin * d11, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, (-dSin) * d13 * d14, d19 * d14, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, d18, (d2 - (d17 * dSin)) - (d19 * d16), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d);
    }

    void a(double d) {
        this.a.d();
        this.e.b(d);
    }

    void b(double d) {
        this.a.d();
        this.e.a(d);
    }

    void a(double d, double d2) {
        this.a.d();
        this.e.a(d, d2, 1.0d);
    }

    void a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16) {
        this.a.d();
        this.e.b(d, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16);
        this.i = true;
    }

    void a(ad adVar) {
        this.a.d();
        this.d.b(adVar);
        this.k = true;
    }

    void f() {
        b();
    }

    void b(double d, double d2) {
        this.e.b(d, d2, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    void g() {
        if (this.j || this.k) {
            this.k = false;
            if (this.j) {
                this.j = false;
                this.f.b();
                for (int size = this.g.size() - 1; size >= 0; size--) {
                    this.f.a(this.g.get(size));
                }
            }
            this.b.b();
            this.b.a(this.e);
            this.b.a(this.f);
            this.b.a(this.d);
            this.b.a(this.c);
        }
    }
}
