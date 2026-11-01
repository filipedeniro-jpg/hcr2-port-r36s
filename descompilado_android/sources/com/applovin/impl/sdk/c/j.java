package com.applovin.impl.sdk.c;

import android.text.TextUtils;
import com.applovin.impl.sdk.n;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class j {
    private final com.applovin.impl.sdk.h a;
    private final n b;
    private final Object c = new Object();
    private final Map<String, b> d = new HashMap();

    private static class a {
        private static final Set<String> h = new HashSet(7);
        static final String a = a("tk");
        static final String b = a("tc");
        static final String c = a("ec");
        static final String d = a("dm");
        static final String e = a("dv");
        static final String f = a("dh");
        static final String g = a("dl");

        private static String a(String str) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("No key name specified");
            }
            if (!h.contains(str)) {
                h.add(str);
                return str;
            }
            throw new IllegalArgumentException("Key has already been used: " + str);
        }
    }

    private static class b {
        private final String a;
        private int b;
        private int c;
        private double d;
        private double e;
        private Long f;
        private Long g;

        b(String str) {
            this.b = 0;
            this.c = 0;
            this.d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            this.e = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            this.f = null;
            this.g = null;
            this.a = str;
        }

        b(JSONObject jSONObject) throws JSONException {
            this.b = 0;
            this.c = 0;
            this.d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            this.e = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            this.f = null;
            this.g = null;
            this.a = jSONObject.getString(a.a);
            this.b = jSONObject.getInt(a.b);
            this.c = jSONObject.getInt(a.c);
            this.d = jSONObject.getDouble(a.d);
            this.e = jSONObject.getDouble(a.e);
            this.f = Long.valueOf(jSONObject.optLong(a.f));
            this.g = Long.valueOf(jSONObject.optLong(a.g));
        }

        String a() {
            return this.a;
        }

        void a(long j) {
            int i = this.b;
            double d = this.d;
            double d2 = this.e;
            this.b = i + 1;
            double d3 = i;
            Double.isNaN(d3);
            double d4 = j;
            Double.isNaN(d4);
            int i2 = this.b;
            double d5 = i2;
            Double.isNaN(d5);
            this.d = ((d * d3) + d4) / d5;
            double d6 = i2;
            Double.isNaN(d3);
            Double.isNaN(d6);
            double d7 = d3 / d6;
            Double.isNaN(d4);
            double dPow = Math.pow(d - d4, 2.0d);
            double d8 = this.b;
            Double.isNaN(d8);
            this.e = d7 * (d2 + (dPow / d8));
            Long l = this.f;
            if (l == null || j > l.longValue()) {
                this.f = Long.valueOf(j);
            }
            Long l2 = this.g;
            if (l2 == null || j < l2.longValue()) {
                this.g = Long.valueOf(j);
            }
        }

        void b() {
            this.c++;
        }

        JSONObject c() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(a.a, this.a);
            jSONObject.put(a.b, this.b);
            jSONObject.put(a.c, this.c);
            jSONObject.put(a.d, this.d);
            jSONObject.put(a.e, this.e);
            jSONObject.put(a.f, this.f);
            jSONObject.put(a.g, this.g);
            return jSONObject;
        }

        public String toString() {
            try {
                return "TaskStats{n='" + this.a + "', stats=" + c().toString() + '}';
            } catch (JSONException unused) {
                return "TaskStats{n='" + this.a + "', count=" + this.b + '}';
            }
        }
    }

    public j(com.applovin.impl.sdk.h hVar) {
        this.a = hVar;
        this.b = hVar.w();
        c();
    }

    private b b(i iVar) {
        b bVar;
        synchronized (this.c) {
            String strA = iVar.a();
            bVar = this.d.get(strA);
            if (bVar == null) {
                bVar = new b(strA);
                this.d.put(strA, bVar);
            }
        }
        return bVar;
    }

    private void c() {
        Set set = (Set) this.a.a(com.applovin.impl.sdk.b.d.j);
        if (set != null) {
            synchronized (this.c) {
                try {
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        b bVar = new b(new JSONObject((String) it.next()));
                        this.d.put(bVar.a(), bVar);
                    }
                } catch (JSONException e) {
                    this.b.b("TaskStatsManager", "Failed to convert stats json.", e);
                }
            }
        }
    }

    private void d() {
        HashSet hashSet;
        synchronized (this.c) {
            hashSet = new HashSet(this.d.size());
            for (b bVar : this.d.values()) {
                try {
                    hashSet.add(bVar.c().toString());
                } catch (JSONException e) {
                    this.b.b("TaskStatsManager", "Failed to serialize " + bVar, e);
                }
            }
        }
        this.a.a(com.applovin.impl.sdk.b.d.j, hashSet);
    }

    public JSONArray a() {
        JSONArray jSONArray;
        synchronized (this.c) {
            jSONArray = new JSONArray();
            for (b bVar : this.d.values()) {
                try {
                    jSONArray.put(bVar.c());
                } catch (JSONException e) {
                    this.b.b("TaskStatsManager", "Failed to serialize " + bVar, e);
                }
            }
        }
        return jSONArray;
    }

    public void a(i iVar) {
        a(iVar, false, 0L);
    }

    public void a(i iVar, long j) {
        if (iVar == null) {
            throw new IllegalArgumentException("No key specified");
        }
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eG)).booleanValue()) {
            synchronized (this.c) {
                b(iVar).a(j);
                d();
            }
        }
    }

    public void a(i iVar, boolean z, long j) {
        if (iVar == null) {
            throw new IllegalArgumentException("No key specified");
        }
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eG)).booleanValue()) {
            synchronized (this.c) {
                b bVarB = b(iVar);
                bVarB.b();
                if (z) {
                    bVarB.a(j);
                }
                d();
            }
        }
    }

    public void b() {
        synchronized (this.c) {
            this.d.clear();
            this.a.b(com.applovin.impl.sdk.b.d.j);
        }
    }
}
