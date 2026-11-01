package com.applovin.impl.sdk.network;

import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.g;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {
    private String a;
    private String b;
    private Map<String, String> c;
    private Map<String, Object> d;
    private boolean e;
    private int f;

    public static class a {
        private String a;
        private String b;
        private Map<String, String> c;
        private Map<String, Object> d;
        private boolean e;

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a a(Map<String, String> map) {
            this.c = map;
            return this;
        }

        public a a(boolean z) {
            this.e = z;
            return this;
        }

        public e a() {
            return new e(this);
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public a b(Map<String, Object> map) {
            this.d = map;
            return this;
        }
    }

    private e(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = 0;
    }

    e(JSONObject jSONObject, h hVar) throws Exception {
        String string;
        Map<String, Object> mapB;
        String string2 = jSONObject.getString("targetUrl");
        int i = jSONObject.getInt("attemptNumber");
        Map<String, String> mapA = g.a(jSONObject, "parameters") ? g.a(jSONObject.getJSONObject("parameters")) : Collections.EMPTY_MAP;
        if (((Boolean) hVar.a(com.applovin.impl.sdk.b.b.dD)).booleanValue()) {
            string = g.a(jSONObject, "backupUrl", "", hVar);
            if (!g.a(jSONObject, "requestBody")) {
                mapB = Collections.EMPTY_MAP;
            }
            this.a = string2;
            this.b = string;
            this.c = mapA;
            this.d = mapB;
            this.e = jSONObject.optBoolean("isEncodingEnabled", false);
            this.f = i;
        }
        string = jSONObject.getString("backupUrl");
        mapB = g.b(jSONObject.getJSONObject("requestBody"));
        this.a = string2;
        this.b = string;
        this.c = mapA;
        this.d = mapB;
        this.e = jSONObject.optBoolean("isEncodingEnabled", false);
        this.f = i;
    }

    public static a j() {
        return new a();
    }

    String a() {
        return this.a;
    }

    String b() {
        return this.b;
    }

    Map<String, Object> c() {
        return this.d;
    }

    Map<String, String> d() {
        return this.c;
    }

    boolean e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        String str = this.a;
        if (str == null ? eVar.a != null : !str.equals(eVar.a)) {
            return false;
        }
        String str2 = this.b;
        if (str2 == null ? eVar.b != null : !str2.equals(eVar.b)) {
            return false;
        }
        Map<String, String> map = this.c;
        if (map == null ? eVar.c != null : !map.equals(eVar.c)) {
            return false;
        }
        Map<String, Object> map2 = this.d;
        if (map2 == null ? eVar.d == null : map2.equals(eVar.d)) {
            return this.f == eVar.f && this.e == eVar.e;
        }
        return false;
    }

    int f() {
        return this.f;
    }

    void g() {
        this.f++;
    }

    void h() {
        HashMap map = new HashMap();
        Map<String, String> map2 = this.c;
        if (map2 != null) {
            map.putAll(map2);
        }
        map.put("postback_ts", String.valueOf(System.currentTimeMillis()));
        this.c = map;
    }

    public int hashCode() {
        int i = this.f * 31;
        String str = this.a;
        int iHashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.b;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Map<String, String> map = this.c;
        int iHashCode3 = (iHashCode2 + (map != null ? map.hashCode() : 0)) * 31;
        Map<String, Object> map2 = this.d;
        return (iHashCode3 + (map2 != null ? map2.hashCode() : 0) + (this.e ? 1 : 0)) * 31;
    }

    JSONObject i() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("targetUrl", this.a);
        jSONObject.put("backupUrl", this.b);
        jSONObject.put("isEncodingEnabled", this.e);
        jSONObject.put("attemptNumber", this.f);
        Map<String, String> map = this.c;
        if (map != null) {
            jSONObject.put("parameters", new JSONObject(map));
        }
        Map<String, Object> map2 = this.d;
        if (map2 != null) {
            jSONObject.put("requestBody", new JSONObject(map2));
        }
        return jSONObject;
    }

    public String toString() {
        return "PostbackRequest{targetUrl='" + this.a + "', backupUrl='" + this.b + "', attemptNumber=" + this.f + ", isEncodingEnabled=" + this.e + '}';
    }
}
