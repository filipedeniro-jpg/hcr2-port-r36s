package com.applovin.impl.sdk.c;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class h {
    private final com.applovin.impl.sdk.h a;
    private final Map<String, Long> b = new HashMap();

    public h(com.applovin.impl.sdk.h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = hVar;
    }

    private void e() {
        try {
            this.a.a(com.applovin.impl.sdk.b.d.h, c().toString());
        } catch (Throwable th) {
            this.a.w().b("GlobalStatsManager", "Unable to save stats", th);
        }
    }

    public long a(g gVar) {
        return a(gVar, 1L);
    }

    long a(g gVar, long j) {
        long jLongValue;
        synchronized (this.b) {
            Long l = this.b.get(gVar.a());
            if (l == null) {
                l = 0L;
            }
            jLongValue = l.longValue() + j;
            this.b.put(gVar.a(), Long.valueOf(jLongValue));
        }
        e();
        return jLongValue;
    }

    public void a() {
        synchronized (this.b) {
            this.b.clear();
        }
        e();
    }

    public long b(g gVar) {
        long jLongValue;
        synchronized (this.b) {
            Long l = this.b.get(gVar.a());
            if (l == null) {
                l = 0L;
            }
            jLongValue = l.longValue();
        }
        return jLongValue;
    }

    public void b() {
        synchronized (this.b) {
            Iterator<g> it = g.b().iterator();
            while (it.hasNext()) {
                this.b.remove(it.next().a());
            }
            e();
        }
    }

    public void b(g gVar, long j) {
        synchronized (this.b) {
            this.b.put(gVar.a(), Long.valueOf(j));
        }
        e();
    }

    public JSONObject c() throws JSONException {
        JSONObject jSONObject;
        synchronized (this.b) {
            jSONObject = new JSONObject();
            for (Map.Entry<String, Long> entry : this.b.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    public void c(g gVar) {
        synchronized (this.b) {
            this.b.remove(gVar.a());
        }
        e();
    }

    public void d() {
        try {
            JSONObject jSONObject = new JSONObject((String) this.a.b(com.applovin.impl.sdk.b.d.h, "{}"));
            synchronized (this.b) {
                try {
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        try {
                            String next = itKeys.next();
                            this.b.put(next, Long.valueOf(jSONObject.getLong(next)));
                        } catch (JSONException unused) {
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Throwable th2) {
            this.a.w().b("GlobalStatsManager", "Unable to load stats", th2);
        }
    }
}
