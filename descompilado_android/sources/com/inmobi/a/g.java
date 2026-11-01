package com.inmobi.a;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CarbWorker.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {
    private static final String a = "g";
    private p.a b;
    private boolean c = false;
    private a d = new a();
    private e e = new e();

    static /* synthetic */ boolean c(g gVar) {
        gVar.c = false;
        return false;
    }

    g() {
    }

    public final synchronized void a(p.a aVar) {
        this.b = aVar;
        if (!this.c) {
            long jB = this.d.a.b("carb_last_update_ts", 0L);
            if (jB == 0 || System.currentTimeMillis() - jB >= ((long) (this.b.d * 1000))) {
                this.c = true;
                new Thread(new Runnable() { // from class: com.inmobi.a.g.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        c cVarA = g.a(g.this);
                        if (!cVarA.a) {
                            g.this.d.a.a("carb_last_update_ts", System.currentTimeMillis());
                            if (!(cVarA.d == 0)) {
                                g.a(g.this, cVarA, g.a(cVarA.b));
                            }
                        }
                        g.c(g.this);
                    }
                }).start();
            }
        }
    }

    private static boolean a(String str) {
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return false;
        }
        try {
            contextB.getPackageManager().getPackageInfo(str, 256);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    static /* synthetic */ c a(g gVar) {
        b bVar = new b(gVar.b.b, gVar.b.e, gVar.b.f, o.a().d());
        bVar.v = gVar.b.h;
        bVar.r = gVar.b.g * 1000;
        bVar.s = gVar.b.g * 1000;
        return e.a(bVar);
    }

    static /* synthetic */ List a(List list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!a(((d) list.get(i)).a)) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    static /* synthetic */ void a(g gVar, c cVar, List list) {
        f fVar = new f(gVar.b.c, gVar.b.e, gVar.b.f, o.a().d(), list, cVar);
        fVar.r = gVar.b.g * 1000;
        fVar.s = gVar.b.g * 1000;
        e.a(fVar);
    }
}
