package com.applovin.impl.mediation.b;

import android.content.Context;
import android.os.Bundle;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.k;
import com.applovin.sdk.AppLovinPrivacySettings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {
    private final JSONObject a;
    protected final h b;
    private final JSONObject c;
    private final Object d = new Object();
    private final Object e = new Object();
    private volatile String f;

    public e(JSONObject jSONObject, JSONObject jSONObject2, h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        if (jSONObject2 == null) {
            throw new IllegalArgumentException("No full response specified");
        }
        if (jSONObject == null) {
            throw new IllegalArgumentException("No spec object specified");
        }
        this.b = hVar;
        this.a = jSONObject2;
        this.c = jSONObject;
    }

    private List<String> a(List<String> list, Map<String, String> map) {
        this.b.w().a("MediationAdapterSpec", "Replacing postback macros for postbacks: " + list);
        Map<String, String> mapA = a();
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            for (String str : mapA.keySet()) {
                next = next.replace(str, h(mapA.get(str)));
            }
            for (String str2 : map.keySet()) {
                next = next.replace(str2, map.get(str2));
            }
            arrayList.add(next);
        }
        this.b.w().a("MediationAdapterSpec", "Finished replacing macros for postbacks: " + arrayList);
        return arrayList;
    }

    private Map<String, String> a() {
        try {
            return com.applovin.impl.sdk.utils.g.a(new JSONObject((String) this.b.a(com.applovin.impl.sdk.b.a.i)));
        } catch (JSONException unused) {
            return Collections.EMPTY_MAP;
        }
    }

    private List<String> f(String str) {
        try {
            return com.applovin.impl.sdk.utils.g.b(a(str, new JSONArray()));
        } catch (JSONException unused) {
            return Collections.EMPTY_LIST;
        }
    }

    private List<String> g(String str) {
        try {
            return com.applovin.impl.sdk.utils.g.b(b(str, new JSONArray()));
        } catch (JSONException unused) {
            return Collections.EMPTY_LIST;
        }
    }

    private String h(String str) {
        String strB = b(str, "");
        return k.b(strB) ? strB : a(str, "");
    }

    public boolean A() {
        return B() >= 0;
    }

    public long B() {
        long jB = b("ad_refresh_ms", -1L);
        return jB >= 0 ? jB : a("ad_refresh_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.a.w)).longValue());
    }

    public long C() {
        long jB = b("fullscreen_display_delay_ms", -1L);
        return jB >= 0 ? jB : ((Long) this.b.a(com.applovin.impl.sdk.b.a.E)).longValue();
    }

    public long D() {
        long jB = b("init_completion_delay_ms", -1L);
        return jB >= 0 ? jB : ((Long) this.b.a(com.applovin.impl.sdk.b.a.q)).longValue();
    }

    public String E() {
        return this.f;
    }

    public boolean F() {
        return b("destroy_on_load_failure") ? b("destroy_on_load_failure", (Boolean) false) : a("destroy_on_load_failure", (Boolean) this.b.a(com.applovin.impl.sdk.b.a.R));
    }

    protected float a(String str, float f) {
        float fA;
        synchronized (this.d) {
            fA = com.applovin.impl.sdk.utils.g.a(this.c, str, f, this.b);
        }
        return fA;
    }

    protected int a(String str, int i) {
        int iB;
        synchronized (this.d) {
            iB = com.applovin.impl.sdk.utils.g.b(this.c, str, i, this.b);
        }
        return iB;
    }

    protected long a(String str, long j) {
        long jA;
        synchronized (this.e) {
            jA = com.applovin.impl.sdk.utils.g.a(this.a, str, j, this.b);
        }
        return jA;
    }

    protected String a(String str, String str2) {
        String strA;
        synchronized (this.e) {
            strA = com.applovin.impl.sdk.utils.g.a(this.a, str, str2, this.b);
        }
        return strA;
    }

    public List<String> a(String str, Map<String, String> map) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        if (b(str)) {
            return a(g(str), map);
        }
        return null;
    }

    protected JSONArray a(String str, JSONArray jSONArray) {
        JSONArray jSONArrayA;
        synchronized (this.e) {
            jSONArrayA = com.applovin.impl.sdk.utils.g.a(this.a, str, jSONArray, this.b);
        }
        return jSONArrayA;
    }

    protected JSONObject a(String str, JSONObject jSONObject) {
        JSONObject jSONObjectA;
        synchronized (this.d) {
            jSONObjectA = com.applovin.impl.sdk.utils.g.a(this.c, str, jSONObject, this.b);
        }
        return jSONObjectA;
    }

    public boolean a(Context context) {
        return b("huc") ? b("huc", (Boolean) false) : a("huc", Boolean.valueOf(AppLovinPrivacySettings.hasUserConsent(context)));
    }

    protected boolean a(String str) {
        boolean zHas;
        synchronized (this.e) {
            zHas = this.a.has(str);
        }
        return zHas;
    }

    protected boolean a(String str, Boolean bool) {
        boolean zBooleanValue;
        synchronized (this.e) {
            zBooleanValue = com.applovin.impl.sdk.utils.g.a(this.a, str, bool, this.b).booleanValue();
        }
        return zBooleanValue;
    }

    protected long b(String str, long j) {
        long jA;
        synchronized (this.d) {
            jA = com.applovin.impl.sdk.utils.g.a(this.c, str, j, this.b);
        }
        return jA;
    }

    protected String b(String str, String str2) {
        String strA;
        synchronized (this.d) {
            strA = com.applovin.impl.sdk.utils.g.a(this.c, str, str2, this.b);
        }
        return strA;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x002e  */
    public List<String> b(String str, Map<String, String> map) {
        List<String> listG;
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        boolean zA = a(str);
        boolean zB = b(str);
        if (!zA && !zB) {
            return null;
        }
        if (zB && zA) {
            if (e(str)) {
                listG = f(str);
            } else {
                List<String> listG2 = g(str);
                listG = f(str);
                listG.addAll(listG2);
            }
        } else if (zB) {
            listG = g(str);
        } else {
            listG = f(str);
        }
        return a(listG, map);
    }

    protected JSONArray b(String str, JSONArray jSONArray) {
        JSONArray jSONArrayA;
        synchronized (this.d) {
            jSONArrayA = com.applovin.impl.sdk.utils.g.a(this.c, str, jSONArray, this.b);
        }
        return jSONArrayA;
    }

    public boolean b(Context context) {
        return b("aru") ? b("aru", (Boolean) false) : a("aru", Boolean.valueOf(AppLovinPrivacySettings.isAgeRestrictedUser(context)));
    }

    protected boolean b(String str) {
        boolean zHas;
        synchronized (this.d) {
            zHas = this.c.has(str);
        }
        return zHas;
    }

    protected boolean b(String str, Boolean bool) {
        boolean zBooleanValue;
        synchronized (this.d) {
            zBooleanValue = com.applovin.impl.sdk.utils.g.a(this.c, str, bool, this.b).booleanValue();
        }
        return zBooleanValue;
    }

    protected Object c(String str) {
        Object objOpt;
        synchronized (this.d) {
            objOpt = this.c.opt(str);
        }
        return objOpt;
    }

    protected void c(String str, long j) {
        synchronized (this.d) {
            com.applovin.impl.sdk.utils.g.b(this.c, str, j, this.b);
        }
    }

    public void d(String str) {
        this.f = str;
    }

    public boolean e(String str) {
        return a("fire_in_succession_" + str, (Boolean) true);
    }

    protected JSONObject s() {
        JSONObject jSONObject;
        synchronized (this.e) {
            jSONObject = this.a;
        }
        return jSONObject;
    }

    protected JSONObject t() {
        JSONObject jSONObject;
        synchronized (this.d) {
            jSONObject = this.c;
        }
        return jSONObject;
    }

    public String toString() {
        return "MediationAdapterSpec{adapterClass='" + u() + "', adapterName='" + v() + "', isTesting=" + w() + ", isRefreshEnabled=" + A() + ", getAdRefreshMillis=" + B() + '}';
    }

    public String u() {
        return b("class", (String) null);
    }

    public String v() {
        return b("name", (String) null);
    }

    public boolean w() {
        return b("is_testing") ? b("is_testing", (Boolean) false) : a("is_testing", (Boolean) this.b.a(com.applovin.impl.sdk.b.a.F));
    }

    public boolean x() {
        return b("run_on_ui_thread", (Boolean) this.b.a(com.applovin.impl.sdk.b.a.s));
    }

    public Bundle y() {
        JSONObject jSONObjectA;
        return (!(c("server_parameters") instanceof JSONObject) || (jSONObjectA = a("server_parameters", (JSONObject) null)) == null) ? Bundle.EMPTY : com.applovin.impl.sdk.utils.g.c(jSONObjectA);
    }

    public long z() {
        return b("adapter_timeout_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.a.t)).longValue());
    }
}
