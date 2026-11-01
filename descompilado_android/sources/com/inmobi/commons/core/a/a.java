package com.inmobi.commons.core.a;

import androidx.annotation.Nullable;
import com.inmobi.commons.core.e.f;
import com.my.target.aa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CrashComponent.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements com.inmobi.commons.core.b.e, com.inmobi.commons.core.configs.b.c {
    private static final String e = "a";
    private static volatile a g;
    public ExecutorService a;
    public b c;
    public String d;
    private c h;
    private com.inmobi.commons.core.b.d i;
    private static final Object f = new Object();
    public static AtomicBoolean b = new AtomicBoolean(false);

    public static a a() {
        a aVar = g;
        if (aVar == null) {
            synchronized (f) {
                aVar = g;
                if (aVar == null) {
                    aVar = new a();
                    g = aVar;
                }
            }
        }
        return aVar;
    }

    private a() {
        Thread.setDefaultUncaughtExceptionHandler(new e(Thread.getDefaultUncaughtExceptionHandler()));
        this.c = new b();
        com.inmobi.commons.core.e.b.a().a("crashReporting", this.c.i);
        com.inmobi.commons.core.e.b.a().a("catchReporting", this.c.j);
        this.d = this.c.a;
        this.h = new c();
        this.a = Executors.newSingleThreadExecutor();
    }

    @Override // com.inmobi.commons.core.configs.b.c
    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.c = (b) aVar;
        this.d = this.c.a;
        com.inmobi.commons.core.e.b.a().a("crashReporting", this.c.i);
        com.inmobi.commons.core.e.b.a().a("catchReporting", this.c.j);
    }

    public final void a(final com.inmobi.commons.core.e.a aVar) {
        if (this.c.h) {
            com.inmobi.commons.core.e.b.a().a(new f("catchReporting", "CatchEventOccurred"));
            this.a.execute(new Runnable() { // from class: com.inmobi.commons.core.a.a.1
                @Override // java.lang.Runnable
                public final void run() {
                    a.this.a((d) aVar);
                    a.a(a.this);
                }
            });
        }
    }

    public final void a(d dVar) {
        if (!(dVar instanceof com.inmobi.commons.core.e.a)) {
            if (!this.c.g) {
                return;
            }
            com.inmobi.commons.core.e.b.a().b(new f("crashReporting", "CrashEventOccurred"));
        }
        this.h.b(this.c.e, aa.f.bq);
        if ((this.h.a(aa.f.bq) + 1) - this.c.d >= 0) {
            c.a();
        }
        c.a(dVar);
    }

    @Override // com.inmobi.commons.core.b.e
    public final com.inmobi.commons.core.b.c a(String str) {
        List<d> listA;
        if (com.inmobi.commons.core.utilities.b.b.a() == 1) {
            listA = c.a(this.c.l.b);
        } else {
            listA = c.a(this.c.k.b);
        }
        if (!listA.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator<d> it = listA.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(it.next().a));
            }
            String strA = a(listA);
            if (strA != null) {
                return new com.inmobi.commons.core.b.c(arrayList, strA, false);
            }
        }
        return null;
    }

    @Nullable
    private static String a(List<d> list) {
        try {
            HashMap map = new HashMap();
            map.putAll(com.inmobi.commons.core.utilities.b.b.a(false));
            map.put("im-accid", com.inmobi.commons.a.a.e());
            map.put("version", "2.0.0");
            map.put("component", "crash");
            map.put("mk-version", com.inmobi.commons.a.b.a());
            map.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
            JSONObject jSONObject = new JSONObject(map);
            JSONArray jSONArray = new JSONArray();
            for (d dVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("eventId", dVar.b);
                jSONObject2.put("eventType", dVar.c);
                if (!dVar.a().trim().isEmpty()) {
                    jSONObject2.put("crash_report", dVar.a());
                }
                jSONObject2.put("ts", dVar.e);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("crash", jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    static /* synthetic */ void a(a aVar) {
        if (b.get()) {
            return;
        }
        b bVar = aVar.c;
        com.inmobi.commons.core.b.a aVar2 = new com.inmobi.commons.core.b.a(bVar.c, bVar.e, bVar.b, bVar.f, bVar.l.b, bVar.k.b, bVar.l.a, bVar.k.a);
        aVar2.e = aVar.d;
        aVar2.b = aa.f.bq;
        com.inmobi.commons.core.b.d dVar = aVar.i;
        if (dVar == null) {
            aVar.i = new com.inmobi.commons.core.b.d(aVar.h, aVar, aVar2);
        } else {
            dVar.a(aVar2);
        }
        aVar.i.a(aa.f.bq);
    }

    static /* synthetic */ void b(a aVar) {
        aVar.a.execute(new Runnable() { // from class: com.inmobi.commons.core.a.a.3
            @Override // java.lang.Runnable
            public final void run() {
                if (a.this.h.a(aa.f.bq) > 0) {
                    a.a(a.this);
                }
            }
        });
    }
}
