package com.applovin.impl.sdk.ad;

import com.applovin.impl.sdk.n;
import com.applovin.impl.sdk.utils.k;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {
    private final com.applovin.impl.sdk.h a;
    private final n b;
    private volatile boolean e;
    private final Object d = new Object();
    private LinkedHashSet<d> c = c();

    public e(com.applovin.impl.sdk.h hVar) {
        this.a = hVar;
        this.b = hVar.w();
    }

    private LinkedHashSet<d> b(JSONArray jSONArray) {
        LinkedHashSet<d> linkedHashSet = new LinkedHashSet<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectA = com.applovin.impl.sdk.utils.g.a(jSONArray, i, (JSONObject) null, this.a);
            this.b.a("AdZoneManager", "Loading zone: " + com.applovin.impl.sdk.utils.g.a(jSONObjectA, this.a) + "...");
            linkedHashSet.add(d.a(com.applovin.impl.sdk.utils.g.a(jSONObjectA, "id", (String) null, this.a), jSONObjectA, this.a));
        }
        return linkedHashSet;
    }

    private LinkedHashSet<d> c() {
        LinkedHashSet<d> linkedHashSet = new LinkedHashSet<>();
        try {
            String str = (String) this.a.a(com.applovin.impl.sdk.b.d.n);
            if (k.b(str)) {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    linkedHashSet = b(jSONArray);
                } else {
                    this.b.a("AdZoneManager", "Unable to inflate json string: " + str);
                }
            }
        } catch (Throwable th) {
            this.b.b("AdZoneManager", "Encountered error retrieving persisted zones", th);
        } finally {
            if (!linkedHashSet.isEmpty()) {
                this.b.a("AdZoneManager", "Retrieved persisted zones: " + linkedHashSet);
                Iterator<d> it = linkedHashSet.iterator();
                while (it.hasNext()) {
                    it.next().a(this.a);
                }
            }
        }
        return linkedHashSet;
    }

    private void c(JSONArray jSONArray) {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.dU)).booleanValue()) {
            this.b.a("AdZoneManager", "Persisting zones...");
            this.a.a(com.applovin.impl.sdk.b.d.n, jSONArray.toString());
        }
    }

    public LinkedHashSet<d> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new LinkedHashSet<>();
        }
        LinkedHashSet<d> linkedHashSet = new LinkedHashSet<>(jSONArray.length());
        LinkedHashSet<d> linkedHashSetB = null;
        synchronized (this.d) {
            if (!this.e) {
                this.b.a("AdZoneManager", "Found " + jSONArray.length() + " zone(s)...");
                linkedHashSetB = b(jSONArray);
                linkedHashSet = new LinkedHashSet<>(linkedHashSetB);
                linkedHashSet.removeAll(this.c);
                this.c = linkedHashSetB;
                this.e = true;
            }
        }
        if (linkedHashSetB != null) {
            c(jSONArray);
            this.b.a("AdZoneManager", "Finished loading zones");
        }
        return linkedHashSet;
    }

    public boolean a() {
        return this.e;
    }

    public boolean a(d dVar) {
        boolean zContains;
        synchronized (this.d) {
            zContains = this.c.contains(dVar);
        }
        return zContains;
    }

    public LinkedHashSet<d> b() {
        LinkedHashSet<d> linkedHashSet;
        synchronized (this.d) {
            linkedHashSet = this.c;
        }
        return linkedHashSet;
    }
}
