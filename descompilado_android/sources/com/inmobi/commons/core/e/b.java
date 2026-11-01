package com.inmobi.commons.core.e;

import androidx.annotation.Nullable;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.my.target.aa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: TelemetryComponent.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements com.inmobi.commons.core.b.e, com.inmobi.commons.core.configs.b.c {
    private static final String c = "b";
    private static volatile b e;
    private static Map<String, c> f;
    public ExecutorService a;
    private d g;
    private e h;
    private String i;
    private com.inmobi.commons.core.b.d j;
    private static final Object d = new Object();
    public static AtomicBoolean b = new AtomicBoolean(false);

    static /* synthetic */ com.inmobi.commons.core.b.d c(b bVar) {
        bVar.j = null;
        return null;
    }

    public static b a() {
        b bVar = e;
        if (bVar == null) {
            synchronized (d) {
                bVar = e;
                if (bVar == null) {
                    bVar = new b();
                    e = bVar;
                }
            }
        }
        return bVar;
    }

    public final void b() {
        b.set(false);
        com.inmobi.commons.core.configs.b.a().a(this.g, this);
        a("telemetry", this.g.a);
        this.i = this.g.b;
        this.a.execute(new Runnable() { // from class: com.inmobi.commons.core.e.b.1
            @Override // java.lang.Runnable
            public final void run() {
                b.a(b.this);
            }
        });
    }

    private b() {
        f = new HashMap();
        this.g = new d();
        a("telemetry", this.g.a);
        this.i = this.g.b;
        this.h = new e();
        this.a = Executors.newSingleThreadExecutor();
    }

    public final void a(String str, JSONObject jSONObject) {
        a(str, new c(str, jSONObject, this.g.a));
    }

    private void a(String str, c cVar) {
        if (str == null || str.trim().equals("")) {
            return;
        }
        if (cVar != null) {
            f.put(str, cVar);
        } else {
            f.put(str, new c(str, null, this.g.a));
        }
    }

    @Nullable
    private static c c(f fVar) {
        a();
        String str = fVar.d;
        if (str == null || str.trim().equals("")) {
            return null;
        }
        return f.get(str);
    }

    public static void a(String str, String str2, Map<String, Object> map) {
        try {
            f fVar = new f(str, str2);
            if (map != null && !map.isEmpty()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                    fVar.f = jSONObject.toString();
                } catch (JSONException e2) {
                    StringBuilder sb = new StringBuilder("Error forming JSON payload for ");
                    sb.append(str2);
                    sb.append(" Error: ");
                    sb.append(e2);
                }
            }
            a().a(fVar);
        } catch (Exception e3) {
            StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
            sb2.append(e3.getMessage());
            sb2.append(")");
        }
    }

    public final void a(final f fVar) {
        c cVarC = c(fVar);
        if (cVarC == null || !cVarC.b || !this.g.a.b) {
            StringBuilder sb = new StringBuilder("Telemetry service is not enabled or registered for component: ");
            sb.append(fVar.d);
            sb.append("|| type = ");
            sb.append(fVar.c);
            sb.append(" Config :");
            sb.append(cVarC);
            return;
        }
        this.a.execute(new Runnable() { // from class: com.inmobi.commons.core.e.b.3
            @Override // java.lang.Runnable
            public final void run() {
                b.this.b(fVar);
                b.d(b.this);
            }
        });
    }

    public final void b(f fVar) {
        c cVarC = c(fVar);
        if (cVarC == null || !cVarC.b || !this.g.a.b) {
            StringBuilder sb = new StringBuilder("Telemetry service is not enabled or registered for component: ");
            sb.append(fVar.d);
            sb.append("|| type = ");
            sb.append(fVar.c);
            sb.append(" Config :");
            sb.append(cVarC);
            return;
        }
        this.h.b(this.g.f, aa.f.bq);
        if ((this.h.a(aa.f.bq) + 1) - this.g.e >= 0) {
            e.a();
        }
        e.a(fVar);
    }

    @Override // com.inmobi.commons.core.b.e
    public final com.inmobi.commons.core.b.c a(String str) {
        List<f> listA;
        if (com.inmobi.commons.core.utilities.b.b.a() == 1) {
            listA = e.a(this.g.i.b);
        } else {
            listA = e.a(this.g.h.b);
        }
        if (!listA.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator<f> it = listA.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(it.next().a));
            }
            String strA = a(listA);
            if (strA != null) {
                return new com.inmobi.commons.core.b.c(arrayList, strA, true);
            }
        }
        return null;
    }

    @Nullable
    private static String a(List<f> list) {
        try {
            HashMap map = new HashMap();
            map.putAll(com.inmobi.commons.core.utilities.b.b.a(false));
            map.put("im-accid", com.inmobi.commons.a.a.e());
            map.put("version", "2.0.0");
            map.put("component", "telemetry");
            map.put("mk-version", com.inmobi.commons.a.b.a());
            map.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
            JSONObject jSONObject = new JSONObject(map);
            JSONArray jSONArray = new JSONArray();
            for (f fVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("eventId", fVar.b);
                jSONObject2.put("eventType", fVar.c);
                if (!fVar.a().trim().isEmpty()) {
                    jSONObject2.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, fVar.a());
                }
                jSONObject2.put("componentType", fVar.d);
                jSONObject2.put("ts", fVar.e);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("telemetry", jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.inmobi.commons.core.configs.b.c
    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.g = (d) aVar;
        this.i = this.g.b;
    }

    static /* synthetic */ void a(b bVar) {
        bVar.a.execute(new Runnable() { // from class: com.inmobi.commons.core.e.b.4
            @Override // java.lang.Runnable
            public final void run() {
                if (b.this.h.a(aa.f.bq) > 0) {
                    b.d(b.this);
                }
            }
        });
    }

    static /* synthetic */ void d(b bVar) {
        if (b.get()) {
            return;
        }
        d dVar = bVar.g;
        com.inmobi.commons.core.b.a aVar = new com.inmobi.commons.core.b.a(dVar.d, dVar.f, dVar.c, dVar.g, dVar.i.b, dVar.h.b, dVar.i.a, dVar.h.a);
        aVar.e = bVar.i;
        aVar.b = aa.f.bq;
        com.inmobi.commons.core.b.d dVar2 = bVar.j;
        if (dVar2 == null) {
            bVar.j = new com.inmobi.commons.core.b.d(bVar.h, bVar, aVar);
        } else {
            dVar2.a(aVar);
        }
        bVar.j.a(aa.f.bq);
    }
}
