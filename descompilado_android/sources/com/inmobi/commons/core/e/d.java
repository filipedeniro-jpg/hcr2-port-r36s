package com.inmobi.commons.core.e;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: TelemetryConfig.java */
/* JADX INFO: loaded from: classes2.dex */
class d extends com.inmobi.commons.core.configs.a {
    private static final String j = "d";
    a h;
    a i;
    String b = "https://telemetry.sdk.inmobi.com/metrics";
    long c = 150;
    int d = 3;
    int e = 1000;
    long f = 259200;
    long g = 86400;
    c a = new c();

    @Override // com.inmobi.commons.core.configs.a
    public final String a() {
        return "telemetry";
    }

    public d() {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("retryInterval", 10L);
            jSONObject2.put("maxBatchSize", 25);
            jSONObject.put("wifi", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("retryInterval", 10L);
            jSONObject3.put("maxBatchSize", 25);
            jSONObject.put("others", jSONObject3);
            b(jSONObject);
        } catch (JSONException unused) {
        }
    }

    @Override // com.inmobi.commons.core.configs.a
    public final boolean c() {
        if (this.a != null && this.b.trim().length() != 0 && (this.b.startsWith("http://") || this.b.startsWith("https://"))) {
            long j2 = this.g;
            long j3 = this.c;
            if (j2 >= j3) {
                long j4 = this.f;
                if (j2 <= j4 && j4 >= j3 && this.h.a() && this.i.a() && this.c >= 0 && this.d >= 0 && this.g > 0 && this.f > 0 && this.e > 0) {
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
                this.i = aVar;
            } else {
                this.h = aVar;
            }
        }
    }

    @Override // com.inmobi.commons.core.configs.a
    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("base");
        this.a.b = jSONObject2.getBoolean("enabled");
        this.a.a = jSONObject2.getInt("samplingFactor");
        this.b = jSONObject.getString("telemetryUrl");
        this.c = jSONObject.getLong("processingInterval");
        this.d = jSONObject.getInt("maxRetryCount");
        this.e = jSONObject.getInt("maxEventsToPersist");
        this.f = jSONObject.getLong("eventTTL");
        this.g = jSONObject.getLong("txLatency");
        b(jSONObject.getJSONObject("networkType"));
    }

    @Override // com.inmobi.commons.core.configs.a
    public final JSONObject b() throws JSONException {
        JSONObject jSONObjectB = super.b();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enabled", this.a.b);
        jSONObject.put("samplingFactor", this.a.a);
        jSONObjectB.put("base", jSONObject);
        jSONObjectB.put("telemetryUrl", this.b);
        jSONObjectB.put("processingInterval", this.c);
        jSONObjectB.put("maxRetryCount", this.d);
        jSONObjectB.put("maxEventsToPersist", this.e);
        jSONObjectB.put("eventTTL", this.f);
        jSONObjectB.put("txLatency", this.g);
        JSONObject jSONObject2 = new JSONObject();
        a aVar = this.i;
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("retryInterval", aVar.a);
        jSONObject3.put("maxBatchSize", aVar.b);
        jSONObject2.put("wifi", jSONObject3);
        a aVar2 = this.h;
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("retryInterval", aVar2.a);
        jSONObject4.put("maxBatchSize", aVar2.b);
        jSONObject2.put("others", jSONObject4);
        jSONObjectB.put("networkType", jSONObject2);
        return jSONObjectB;
    }

    @Override // com.inmobi.commons.core.configs.a
    public final com.inmobi.commons.core.configs.a d() {
        return new d();
    }

    /* JADX INFO: compiled from: TelemetryConfig.java */
    public final class a {
        long a;
        int b;

        public a() {
        }

        public final boolean a() {
            return this.b <= d.this.e && this.a > 0 && this.b > 0;
        }
    }
}
