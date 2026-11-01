package com.applovin.impl.sdk.c;

import android.text.TextUtils;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.impl.sdk.d.q;
import com.applovin.impl.sdk.d.w;
import com.applovin.impl.sdk.n;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private final com.applovin.impl.sdk.h a;
    private final n b;
    private final Object c = new Object();
    private final b d = new b();

    private static class a {
        private final String a;
        private final String b;
        private final String c;
        private final long d;
        private final Map<String, Long> e;

        private a(String str, String str2, String str3) {
            this.e = new HashMap();
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = System.currentTimeMillis();
        }

        private JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pk", this.a);
            jSONObject.put("ts", this.d);
            if (!TextUtils.isEmpty(this.b)) {
                jSONObject.put("sk1", this.b);
            }
            if (!TextUtils.isEmpty(this.c)) {
                jSONObject.put("sk2", this.c);
            }
            for (Map.Entry<String, Long> entry : this.e.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
            return jSONObject;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String b() throws JSONException, OutOfMemoryError {
            return a().toString();
        }

        void a(String str, long j) {
            this.e.put(str, Long.valueOf(j));
        }

        public String toString() {
            return "AdEventStats{pk='" + this.a + "', size=" + this.e.size() + '}';
        }
    }

    private class b extends LinkedHashMap<String, a> {
        private b() {
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, a> entry) {
            return size() > ((Integer) c.this.a.a(com.applovin.impl.sdk.b.b.eE)).intValue();
        }
    }

    public c(com.applovin.impl.sdk.h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = hVar;
        this.b = hVar.w();
    }

    private a a(AppLovinAdBase appLovinAdBase) {
        a aVar;
        synchronized (this.c) {
            String primaryKey = appLovinAdBase.getPrimaryKey();
            aVar = this.d.get(primaryKey);
            if (aVar == null) {
                aVar = new a(primaryKey, appLovinAdBase.getSecondaryKey1(), appLovinAdBase.getSecondaryKey2());
                this.d.put(primaryKey, aVar);
            }
        }
        return aVar;
    }

    private void a(JSONObject jSONObject) {
        w<JSONObject> wVar = new w<JSONObject>(com.applovin.impl.sdk.network.b.a(this.a).a(c()).c(d()).a(com.applovin.impl.sdk.utils.f.e(this.a)).b(HttpRequest.METHOD_POST).a(jSONObject).b(((Integer) this.a.a(com.applovin.impl.sdk.b.b.eC)).intValue()).a(((Integer) this.a.a(com.applovin.impl.sdk.b.b.eD)).intValue()).a(), this.a) { // from class: com.applovin.impl.sdk.c.c.1
            @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
            public void a(int i) {
                c.this.b.d("AdEventStatsManager", "Failed to submitted ad stats: " + i);
            }

            @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
            public void a(JSONObject jSONObject2, int i) {
                c.this.b.a("AdEventStatsManager", "Ad stats submitted: " + i);
            }
        };
        wVar.a(com.applovin.impl.sdk.b.b.aG);
        wVar.b(com.applovin.impl.sdk.b.b.aH);
        this.a.F().a(wVar, q.a.BACKGROUND);
    }

    private String c() {
        return com.applovin.impl.sdk.utils.f.a("2.0/s", this.a);
    }

    private String d() {
        return com.applovin.impl.sdk.utils.f.b("2.0/s", this.a);
    }

    private void e() {
        HashSet hashSet;
        synchronized (this.c) {
            try {
                hashSet = new HashSet(this.d.size());
                for (a aVar : this.d.values()) {
                    try {
                        try {
                            String strB = aVar.b();
                            if (strB != null) {
                                hashSet.add(strB);
                            }
                        } catch (OutOfMemoryError e) {
                            this.b.b("AdEventStatsManager", "Failed to serialize " + aVar + " due to OOM error", e);
                            b();
                        }
                    } catch (JSONException e2) {
                        this.b.b("AdEventStatsManager", "Failed to serialize " + aVar, e2);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.a.a(com.applovin.impl.sdk.b.d.o, hashSet);
    }

    public void a() {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eB)).booleanValue()) {
            Set<String> set = (Set) this.a.b(com.applovin.impl.sdk.b.d.o, new HashSet(0));
            this.a.b(com.applovin.impl.sdk.b.d.o);
            if (set == null || set.isEmpty()) {
                this.b.a("AdEventStatsManager", "No serialized ad events found");
                return;
            }
            this.b.a("AdEventStatsManager", "De-serializing " + set.size() + " stat ad events");
            JSONArray jSONArray = new JSONArray();
            for (String str : set) {
                try {
                    jSONArray.put(new JSONObject(str));
                } catch (JSONException e) {
                    this.b.b("AdEventStatsManager", "Failed to parse: " + str, e);
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("stats", jSONArray);
                a(jSONObject);
            } catch (JSONException e2) {
                this.b.b("AdEventStatsManager", "Failed to create stats to submit", e2);
            }
        }
    }

    public void a(com.applovin.impl.sdk.c.b bVar, long j, AppLovinAdBase appLovinAdBase) {
        if (appLovinAdBase == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        if (bVar == null) {
            throw new IllegalArgumentException("No key specified");
        }
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eB)).booleanValue()) {
            synchronized (this.c) {
                a(appLovinAdBase).a(((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eF)).booleanValue() ? bVar.b() : bVar.a(), j);
            }
            e();
        }
    }

    public void b() {
        synchronized (this.c) {
            this.b.a("AdEventStatsManager", "Clearing ad stats...");
            this.d.clear();
        }
    }
}
