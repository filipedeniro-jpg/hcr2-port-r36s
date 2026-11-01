package com.applovin.impl.sdk.a;

import com.applovin.impl.sdk.ad.g;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class e {
    private static e d;
    private final Map<g, String> a = new HashMap(1);
    private final Map<g, Map<String, String>> b = new HashMap(1);
    private final Object c = new Object();

    private e() {
    }

    public static synchronized e a() {
        if (d == null) {
            d = new e();
        }
        return d;
    }

    public Map<String, String> a(g gVar) {
        Map<String, String> mapRemove;
        synchronized (this.c) {
            mapRemove = this.b.remove(gVar);
        }
        return mapRemove;
    }

    public void a(g gVar, String str) {
        synchronized (this.c) {
            this.a.put(gVar, str);
        }
    }

    public void a(g gVar, Map<String, String> map) {
        synchronized (this.c) {
            this.b.put(gVar, map);
        }
    }

    public String b(g gVar) {
        String strRemove;
        synchronized (this.c) {
            strRemove = this.a.remove(gVar);
        }
        return strRemove;
    }
}
