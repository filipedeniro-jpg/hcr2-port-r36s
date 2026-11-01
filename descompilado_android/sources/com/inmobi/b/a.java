package com.inmobi.b;

import androidx.annotation.Nullable;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.ads.c;
import com.inmobi.commons.core.b.d;
import com.inmobi.commons.core.b.e;
import com.inmobi.commons.core.configs.b;
import com.my.target.ai;
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

/* JADX INFO: compiled from: TRCComponent.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements e, b.c {
    private static final String e = "a";
    private static volatile a g;
    private d i;
    private static final Object f = new Object();
    public static AtomicBoolean b = new AtomicBoolean(false);
    public c c = new c();
    public String d = this.c.b;
    private com.inmobi.commons.core.f.a h = new com.inmobi.commons.core.f.a();
    public ExecutorService a = Executors.newSingleThreadExecutor();

    static /* synthetic */ d c(a aVar) {
        aVar.i = null;
        return null;
    }

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
    }

    @Override // com.inmobi.commons.core.configs.b.c
    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.c = (c) aVar;
        this.d = this.c.b;
    }

    private void b(final String str) {
        this.a.execute(new Runnable() { // from class: com.inmobi.b.a.4
            @Override // java.lang.Runnable
            public final void run() {
                com.inmobi.commons.core.f.a unused = a.this.h;
                if (com.inmobi.commons.core.f.a.c(str)) {
                    a.a(a.this, str);
                }
            }
        });
    }

    @Override // com.inmobi.commons.core.b.e
    public final com.inmobi.commons.core.b.c a(String str) {
        List<com.inmobi.commons.core.f.b> listA;
        c.a aVarB = this.c.b(str);
        if (com.inmobi.commons.core.utilities.b.b.a() == 1) {
            listA = com.inmobi.commons.core.f.a.a(aVarB.g.b, str);
        } else {
            listA = com.inmobi.commons.core.f.a.a(aVarB.f.b, str);
        }
        if (!listA.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator<com.inmobi.commons.core.f.b> it = listA.iterator();
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
    private static String a(List<com.inmobi.commons.core.f.b> list) {
        try {
            HashMap map = new HashMap();
            map.putAll(com.inmobi.commons.core.utilities.b.b.a(false));
            map.put("im-accid", com.inmobi.commons.a.a.e());
            map.put("version", "2.0.0");
            map.put("component", "trc");
            map.put("adtype", list.get(0).j);
            map.put("mk-version", com.inmobi.commons.a.b.a());
            map.putAll(com.inmobi.commons.core.utilities.b.a.a().b);
            JSONObject jSONObject = new JSONObject(map);
            JSONArray jSONArray = new JSONArray();
            for (com.inmobi.commons.core.f.b bVar : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("event-id", bVar.b);
                jSONObject2.put("ad-markup-type", bVar.c);
                jSONObject2.put("event-name", bVar.d);
                jSONObject2.put("im-plid", bVar.e);
                jSONObject2.put("request-id", bVar.f);
                jSONObject2.put("event-type", bVar.g);
                jSONObject2.put("d-nettype-raw", bVar.h);
                jSONObject2.put("ts", bVar.i);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("extra-info", jSONArray);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    static /* synthetic */ void a(a aVar, com.inmobi.commons.core.f.b bVar) {
        c.a aVarB = aVar.c.b(bVar.j);
        aVar.h.b(aVarB.b, bVar.j);
        if ((aVar.h.a(bVar.j) + 1) - aVarB.c >= 0) {
            com.inmobi.commons.core.f.a.d(bVar.j);
        }
        com.inmobi.commons.core.f.a.a(bVar);
    }

    static /* synthetic */ void a(a aVar, String str) {
        if (b.get()) {
            return;
        }
        c.a aVarB = aVar.c.b(str);
        com.inmobi.commons.core.b.a aVar2 = new com.inmobi.commons.core.b.a(aVarB.a, aVarB.b, aVarB.d, aVarB.e, aVarB.g.b, aVarB.f.b, aVarB.g.a, aVarB.f.a);
        aVar2.e = aVar.d;
        aVar2.b = str;
        d dVar = aVar.i;
        if (dVar == null) {
            aVar.i = new d(aVar.h, aVar, aVar2);
        } else {
            dVar.a(aVar2);
        }
        aVar.i.a(str);
    }

    static /* synthetic */ void a(a aVar) {
        aVar.b(ai.a.cW);
        aVar.b("int");
        aVar.b(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
    }
}
