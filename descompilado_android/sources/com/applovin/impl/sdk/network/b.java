package com.applovin.impl.sdk.network;

import com.applovin.impl.sdk.h;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b<T> {
    private String a;
    private Map<String, String> b;
    private String c;
    private final JSONObject d;
    private String e;
    private final T f;
    private final boolean g;
    private final int h;
    private int i;
    private final int j;
    private final int k;
    private final boolean l;

    public static class a<T> {
        String a;
        String b;
        String c;
        JSONObject e;
        T f;
        int i;
        int j;
        boolean k;
        boolean g = true;
        int h = 1;
        Map<String, String> d = new HashMap();

        public a(h hVar) {
            this.i = ((Integer) hVar.a(com.applovin.impl.sdk.b.b.dF)).intValue();
            this.j = ((Integer) hVar.a(com.applovin.impl.sdk.b.b.dE)).intValue();
            this.k = ((Boolean) hVar.a(com.applovin.impl.sdk.b.b.eV)).booleanValue();
        }

        public a<T> a(int i) {
            this.h = i;
            return this;
        }

        public a<T> a(T t) {
            this.f = t;
            return this;
        }

        public a<T> a(String str) {
            this.b = str;
            return this;
        }

        public a<T> a(Map<String, String> map) {
            this.d = map;
            return this;
        }

        public a<T> a(JSONObject jSONObject) {
            this.e = jSONObject;
            return this;
        }

        public a<T> a(boolean z) {
            this.k = z;
            return this;
        }

        public b<T> a() {
            return new b<>(this);
        }

        public a<T> b(int i) {
            this.i = i;
            return this;
        }

        public a<T> b(String str) {
            this.a = str;
            return this;
        }

        public a<T> c(int i) {
            this.j = i;
            return this;
        }

        public a<T> c(String str) {
            this.c = str;
            return this;
        }
    }

    protected b(a<T> aVar) {
        this.a = aVar.b;
        this.b = aVar.d;
        this.c = aVar.a;
        this.d = aVar.e;
        this.e = aVar.c;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.h;
        this.j = aVar.i;
        this.k = aVar.j;
        this.l = aVar.k;
    }

    public static <T> a<T> a(h hVar) {
        return new a<>(hVar);
    }

    public String a() {
        return this.a;
    }

    public void a(int i) {
        this.i = i;
    }

    public void a(String str) {
        this.a = str;
    }

    public Map<String, String> b() {
        return this.b;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public JSONObject d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        String str = this.a;
        if (str == null ? bVar.a != null : !str.equals(bVar.a)) {
            return false;
        }
        Map<String, String> map = this.b;
        if (map == null ? bVar.b != null : !map.equals(bVar.b)) {
            return false;
        }
        String str2 = this.e;
        if (str2 == null ? bVar.e != null : !str2.equals(bVar.e)) {
            return false;
        }
        String str3 = this.c;
        if (str3 == null ? bVar.c != null : !str3.equals(bVar.c)) {
            return false;
        }
        JSONObject jSONObject = this.d;
        if (jSONObject == null ? bVar.d != null : !jSONObject.equals(bVar.d)) {
            return false;
        }
        T t = this.f;
        if (t == null ? bVar.f == null : t.equals(bVar.f)) {
            return this.g == bVar.g && this.h == bVar.h && this.i == bVar.i && this.j == bVar.j && this.k == bVar.k && this.l == bVar.l;
        }
        return false;
    }

    public T f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public int h() {
        return this.h - this.i;
    }

    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        String str = this.a;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.e;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.c;
        int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        T t = this.f;
        int iHashCode5 = ((((((((((((iHashCode4 + (t != null ? t.hashCode() : 0)) * 31) + (this.g ? 1 : 0)) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31) + this.k) * 31) + (this.l ? 1 : 0);
        Map<String, String> map = this.b;
        if (map != null) {
            iHashCode5 = (iHashCode5 * 31) + map.hashCode();
        }
        JSONObject jSONObject = this.d;
        if (jSONObject == null) {
            return iHashCode5;
        }
        char[] charArray = jSONObject.toString().toCharArray();
        Arrays.sort(charArray);
        return (iHashCode5 * 31) + new String(charArray).hashCode();
    }

    public int i() {
        return this.i;
    }

    public int j() {
        return this.j;
    }

    public int k() {
        return this.k;
    }

    public boolean l() {
        return this.l;
    }

    public String toString() {
        return "HttpRequest {endpoint=" + this.a + ", backupEndpoint=" + this.e + ", httpMethod=" + this.c + ", body=" + this.d + ", emptyResponse=" + this.f + ", requiresResponse=" + this.g + ", initialRetryAttempts=" + this.h + ", retryAttemptsLeft=" + this.i + ", timeoutMillis=" + this.j + ", retryDelayMillis=" + this.k + ", encodingEnabled=" + this.l + '}';
    }
}
