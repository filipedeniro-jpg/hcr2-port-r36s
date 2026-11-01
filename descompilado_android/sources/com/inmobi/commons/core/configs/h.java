package com.inmobi.commons.core.configs;

import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: RootConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h extends com.inmobi.commons.core.configs.a {
    private static final Object i = new Object();
    int a = 3;
    int b = 60;
    int c = 3;
    public int d = -1;
    public boolean g = false;
    private List<a> h = new ArrayList();
    b e = new b();
    JSONObject f = new JSONObject();

    /* JADX INFO: compiled from: RootConfig.java */
    public static final class b {
        String a = "7.2.1";
        String b = "https://www.inmobi.com/products/sdk/#downloads";
    }

    @Override // com.inmobi.commons.core.configs.a
    public final String a() {
        return "root";
    }

    @Override // com.inmobi.commons.core.configs.a
    public final JSONObject b() throws JSONException {
        JSONObject jSONObjectB = super.b();
        JSONArray jSONArray = new JSONArray();
        jSONObjectB.put("maxRetries", this.a);
        jSONObjectB.put("retryInterval", this.b);
        jSONObjectB.put("waitTime", this.c);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", this.e.a);
        jSONObject.put("url", this.e.b);
        jSONObjectB.put("latestSdkInfo", jSONObject);
        synchronized (i) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                a aVar = this.h.get(i2);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", aVar.a);
                jSONObject2.put("expiry", aVar.b);
                jSONObject2.put(Constants.RequestParameters.PROTOCOL, aVar.c);
                jSONObject2.put("url", aVar.d);
                if ("root".equals(aVar.a)) {
                    jSONObject2.put("fallbackUrl", aVar.e);
                }
                jSONArray.put(jSONObject2);
            }
        }
        jSONObjectB.put("components", jSONArray);
        jSONObjectB.put("monetizationDisabled", this.g);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("transmitRequest", this.d == 1);
        jSONObjectB.put("gdpr", jSONObject3);
        return jSONObjectB;
    }

    @Override // com.inmobi.commons.core.configs.a
    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        this.a = jSONObject.getInt("maxRetries");
        this.b = jSONObject.getInt("retryInterval");
        this.c = jSONObject.getInt("waitTime");
        JSONObject jSONObject2 = jSONObject.getJSONObject("latestSdkInfo");
        this.e.a = jSONObject2.getString("version");
        this.e.b = jSONObject2.getString("url");
        JSONArray jSONArray = jSONObject.getJSONArray("components");
        synchronized (i) {
            this.h.clear();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                a aVar = new a();
                aVar.a = jSONObject3.getString("type");
                aVar.b = jSONObject3.getLong("expiry");
                aVar.c = jSONObject3.getString(Constants.RequestParameters.PROTOCOL);
                aVar.d = jSONObject3.getString("url");
                if ("root".equals(aVar.a)) {
                    aVar.e = jSONObject3.getString("fallbackUrl");
                }
                this.h.add(aVar);
            }
        }
        this.g = jSONObject.getBoolean("monetizationDisabled");
        this.d = jSONObject.getJSONObject("gdpr").getBoolean("transmitRequest") ? 1 : 0;
    }

    @Override // com.inmobi.commons.core.configs.a
    public final boolean c() {
        if (this.h == null || this.a < 0 || this.b < 0 || this.c < 0 || this.e.a.trim().length() == 0 || (!this.e.b.startsWith("http://") && !this.e.b.startsWith("https://"))) {
            return false;
        }
        synchronized (i) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                a aVar = this.h.get(i2);
                if (aVar.a.trim().length() == 0) {
                    return false;
                }
                if (Long.valueOf(aVar.b).longValue() >= 0 && Long.valueOf(aVar.b).longValue() <= 864000) {
                    if (aVar.c.trim().length() == 0) {
                        return false;
                    }
                    if (c(aVar.d)) {
                        return false;
                    }
                    if ("root".equals(aVar.a) && c(aVar.e)) {
                        return false;
                    }
                }
                return false;
            }
            return this.d != -1;
        }
    }

    private static boolean c(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return (str.startsWith("http://") || str.startsWith("https://")) ? false : true;
    }

    @Override // com.inmobi.commons.core.configs.a
    public final com.inmobi.commons.core.configs.a d() {
        return new h();
    }

    public final long a(String str) {
        synchronized (i) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                a aVar = this.h.get(i2);
                if (str.equals(aVar.a)) {
                    return aVar.b;
                }
            }
            return 86400L;
        }
    }

    public final String b(String str) {
        synchronized (i) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                a aVar = this.h.get(i2);
                if (str.equals(aVar.a)) {
                    return aVar.d;
                }
            }
            return "";
        }
    }

    public final String e() {
        synchronized (i) {
            for (a aVar : this.h) {
                if ("root".equals(aVar.a)) {
                    return aVar.e;
                }
            }
            return "https://config.inmobi.cn/config-server/v1/config/secure.cfg";
        }
    }

    /* JADX INFO: compiled from: RootConfig.java */
    static final class a {
        String a;
        long b;
        String c;
        String d;
        String e = "https://config.inmobi.cn/config-server/v1/config/secure.cfg";

        a() {
        }
    }
}
