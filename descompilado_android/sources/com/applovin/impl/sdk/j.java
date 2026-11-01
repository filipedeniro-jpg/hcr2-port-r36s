package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class j {
    private final String b;
    private final long d;
    private final String a = UUID.randomUUID().toString();
    private final Map<String, Object> c = new HashMap();

    public j(String str, Map<String, String> map, Map<String, Object> map2) {
        this.b = str;
        this.c.putAll(map);
        this.c.put("applovin_sdk_super_properties", map2);
        this.d = System.currentTimeMillis();
    }

    public String a() {
        return this.b;
    }

    public Map<String, Object> b() {
        return this.c;
    }

    public long c() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        j jVar = (j) obj;
        if (this.d != jVar.d) {
            return false;
        }
        String str = this.b;
        if (str == null ? jVar.b != null : !str.equals(jVar.b)) {
            return false;
        }
        Map<String, Object> map = this.c;
        if (map == null ? jVar.c != null : !map.equals(jVar.c)) {
            return false;
        }
        String str2 = this.a;
        if (str2 != null) {
            if (str2.equals(jVar.a)) {
                return true;
            }
        } else if (jVar.a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.b;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Map<String, Object> map = this.c;
        int iHashCode2 = (iHashCode + (map != null ? map.hashCode() : 0)) * 31;
        long j = this.d;
        int i = (iHashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        String str2 = this.a;
        return i + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "Event{name='" + this.b + "', id='" + this.a + "', creationTimestampMillis=" + this.d + ", parameters=" + this.c + '}';
    }
}
