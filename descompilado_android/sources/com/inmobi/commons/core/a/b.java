package com.inmobi.commons.core.a;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CrashConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends com.inmobi.commons.core.configs.a {
    private static final String m = "b";
    public String a = "https://crash-metrics.sdk.inmobi.com/trace";
    long b = 0;
    int c = 3;
    int d = 50;
    long e = 2592000;
    long f = 86400;
    boolean g = false;
    boolean h = false;
    JSONObject i;
    JSONObject j;
    a k;
    a l;

    @Override // com.inmobi.commons.core.configs.a
    public final String a() {
        return "crashReporting";
    }

    public b() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("retryInterval", 10L);
            jSONObject2.put("maxBatchSize", 1);
            jSONObject.put("wifi", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("retryInterval", 10L);
            jSONObject3.put("maxBatchSize", 1);
            jSONObject.put("others", jSONObject3);
            b(jSONObject);
            JSONObject jSONObject4 = new JSONObject();
            JSONObject jSONObject5 = new JSONObject();
            jSONObject5.put("enabled", false);
            jSONObject5.put("samplingFactor", 0);
            jSONObject4.put("catchEvent", jSONObject5);
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("enabled", false);
            jSONObject6.put("samplingFactor", 0);
            jSONObject4.put("crashEvent", jSONObject6);
            c(jSONObject4);
        } catch (JSONException unused) {
        }
    }

    @Override // com.inmobi.commons.core.configs.a
    public final boolean c() {
        if (this.a.trim().length() != 0 && (this.a.startsWith("http://") || this.a.startsWith("https://"))) {
            long j = this.f;
            long j2 = this.b;
            if (j >= j2) {
                long j3 = this.e;
                if (j <= j3 && j3 >= j2 && this.k.a() && this.l.a() && this.b >= 0 && this.c >= 0 && this.f > 0 && this.e > 0 && this.d > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void b(JSONObject jSONObject) throws JSONException {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(next);
            a aVar = new a();
            aVar.a = jSONObject2.getLong("retryInterval");
            aVar.b = jSONObject2.getInt("maxBatchSize");
            byte b = -1;
            int iHashCode = next.hashCode();
            if (iHashCode != -1068855134) {
                if (iHashCode != -1006804125) {
                    if (iHashCode == 3649301 && next.equals("wifi")) {
                        b = 0;
                    }
                } else if (next.equals("others")) {
                    b = 2;
                }
            } else if (next.equals("mobile")) {
                b = 1;
            }
            if (b == 0) {
                this.l = aVar;
            } else {
                this.k = aVar;
            }
        }
    }

    private void c(JSONObject jSONObject) throws JSONException {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            byte b = -1;
            int iHashCode = next.hashCode();
            if (iHashCode != -488533857) {
                if (iHashCode == 1411010355 && next.equals("crashEvent")) {
                    b = 1;
                }
            } else if (next.equals("catchEvent")) {
                b = 0;
            }
            if (b == 0) {
                this.j = jSONObject.getJSONObject(next);
            } else if (b == 1) {
                this.i = jSONObject.getJSONObject(next);
            }
        }
    }

    @Override // com.inmobi.commons.core.configs.a
    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        this.a = jSONObject.getString("url");
        this.b = jSONObject.getLong("processingInterval");
        this.c = jSONObject.getInt("maxRetryCount");
        this.d = jSONObject.getInt("maxEventsToPersist");
        this.e = jSONObject.getLong("eventTTL");
        this.f = jSONObject.getLong("txLatency");
        this.g = jSONObject.getBoolean("crashEnabled");
        this.h = jSONObject.getBoolean("catchEnabled");
        b(jSONObject.getJSONObject("networkType"));
        c(jSONObject.getJSONObject("telemetry"));
    }

    @Override // com.inmobi.commons.core.configs.a
    public final JSONObject b() throws JSONException {
        JSONObject jSONObjectB = super.b();
        new JSONObject();
        jSONObjectB.put("url", this.a);
        jSONObjectB.put("processingInterval", this.b);
        jSONObjectB.put("maxRetryCount", this.c);
        jSONObjectB.put("maxEventsToPersist", this.d);
        jSONObjectB.put("eventTTL", this.e);
        jSONObjectB.put("txLatency", this.f);
        jSONObjectB.put("crashEnabled", this.g);
        jSONObjectB.put("catchEnabled", this.h);
        JSONObject jSONObject = new JSONObject();
        a aVar = this.l;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retryInterval", aVar.a);
        jSONObject2.put("maxBatchSize", aVar.b);
        jSONObject.put("wifi", jSONObject2);
        a aVar2 = this.k;
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("retryInterval", aVar2.a);
        jSONObject3.put("maxBatchSize", aVar2.b);
        jSONObject.put("others", jSONObject3);
        jSONObjectB.put("networkType", jSONObject);
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("crashEvent", this.i);
        jSONObject4.put("catchEvent", this.j);
        jSONObjectB.put("telemetry", jSONObject4);
        return jSONObjectB;
    }

    @Override // com.inmobi.commons.core.configs.a
    public final com.inmobi.commons.core.configs.a d() {
        return new b();
    }

    /* JADX INFO: compiled from: CrashConfig.java */
    public final class a {
        long a;
        int b;

        public a() {
        }

        public final boolean a() {
            return this.b <= b.this.d && this.a > 0 && this.b > 0;
        }
    }
}
