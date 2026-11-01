package com.inmobi.commons.core.configs;

import com.inmobi.commons.core.network.NetworkError;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class ConfigNetworkResponse {
    private static final String b = "com.inmobi.commons.core.configs.ConfigNetworkResponse";
    Map<String, ConfigResponse> a = new HashMap();
    private Map<String, a> c;
    private com.inmobi.commons.core.network.d d;
    private d e;
    private long f;

    ConfigNetworkResponse(Map<String, a> map, com.inmobi.commons.core.network.d dVar, long j) {
        this.c = map;
        this.d = dVar;
        this.f = j;
        c();
    }

    private void c() {
        if (!this.d.a()) {
            try {
                JSONObject jSONObject = new JSONObject(this.d.b());
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                    if (this.c.get(next) != null) {
                        this.a.put(next, new ConfigResponse(jSONObject2, this.c.get(next)));
                    }
                    jSONObject = jSONObject;
                }
                return;
            } catch (JSONException e) {
                this.e = new d(2, e.getLocalizedMessage());
                StringBuilder sb = new StringBuilder("Error code:");
                sb.append(this.e.a);
                sb.append(" Error message:");
                sb.append(this.e.b);
                try {
                    HashMap map = new HashMap();
                    map.put("name", a(this.c));
                    map.put("errorCode", "ParsingError");
                    map.put(IronSourceConstants.EVENTS_ERROR_REASON, e.getLocalizedMessage());
                    map.put("latency", Long.valueOf(this.f));
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("root", "InvalidConfig", map);
                    return;
                } catch (Exception e2) {
                    StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                    sb2.append(e2.getMessage());
                    sb2.append(")");
                    return;
                }
            }
        }
        for (Iterator<Map.Entry<String, a>> it = this.c.entrySet().iterator(); it.hasNext(); it = it) {
            Map.Entry<String, a> next2 = it.next();
            ConfigResponse configResponse = new ConfigResponse(null, next2.getValue());
            configResponse.c = new d(0, "Network error in fetching config.");
            this.a.put(next2.getKey(), configResponse);
        }
        this.e = new d(0, this.d.b.b);
        StringBuilder sb3 = new StringBuilder("Error code:");
        sb3.append(this.e.a);
        sb3.append(" Error message:");
        sb3.append(this.e.b);
        try {
            HashMap map2 = new HashMap();
            map2.put("name", a(this.c));
            map2.put("errorCode", String.valueOf(this.d.b.a.getValue()));
            map2.put(IronSourceConstants.EVENTS_ERROR_REASON, this.d.b.b);
            map2.put("latency", Long.valueOf(this.f));
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("root", "InvalidConfig", map2);
        } catch (Exception e3) {
            StringBuilder sb4 = new StringBuilder("Error in submitting telemetry event : (");
            sb4.append(e3.getMessage());
            sb4.append(")");
        }
    }

    public final boolean a() {
        com.inmobi.commons.core.network.d dVar = this.d;
        if (dVar != null && dVar.b != null) {
            if (this.d.b.a != NetworkError.ErrorCode.BAD_REQUEST) {
                int value = this.d.b.a.getValue();
                if (500 <= value && value < 600) {
                }
            }
            return true;
        }
        return false;
    }

    public static class ConfigResponse {
        ConfigResponseStatus a;
        a b;
        d c;

        public enum ConfigResponseStatus {
            SUCCESS(200),
            NOT_MODIFIED(304),
            PRODUCT_NOT_FOUND(404),
            INTERNAL_ERROR(500),
            UNKNOWN(-1);

            private int a;

            ConfigResponseStatus(int i) {
                this.a = i;
            }

            public final int getValue() {
                return this.a;
            }

            public static ConfigResponseStatus fromValue(int i) {
                for (ConfigResponseStatus configResponseStatus : values()) {
                    if (configResponseStatus.a == i) {
                        return configResponseStatus;
                    }
                }
                return UNKNOWN;
            }
        }

        public ConfigResponse(JSONObject jSONObject, a aVar) {
            this.b = aVar;
            if (jSONObject != null) {
                try {
                    this.a = ConfigResponseStatus.fromValue(jSONObject.getInt("status"));
                    if (this.a == ConfigResponseStatus.SUCCESS) {
                        this.b.a(jSONObject.getJSONObject("content"));
                        if (this.b.c()) {
                            return;
                        }
                        this.c = new d(2, "The received config has failed validation.");
                        String unused = ConfigNetworkResponse.b;
                        StringBuilder sb = new StringBuilder("Config type:");
                        sb.append(this.b.a());
                        sb.append(" Error code:");
                        sb.append(this.c.a);
                        sb.append(" Error message:");
                        sb.append(this.c.b);
                        return;
                    }
                    if (this.a == ConfigResponseStatus.NOT_MODIFIED) {
                        String unused2 = ConfigNetworkResponse.b;
                        StringBuilder sb2 = new StringBuilder("Config type:");
                        sb2.append(this.b.a());
                        sb2.append(" Config not modified");
                        return;
                    }
                    this.c = new d(1, this.a.toString());
                    String unused3 = ConfigNetworkResponse.b;
                    StringBuilder sb3 = new StringBuilder("Config type:");
                    sb3.append(this.b.a());
                    sb3.append(" Error code:");
                    sb3.append(this.c.a);
                    sb3.append(" Error message:");
                    sb3.append(this.c.b);
                } catch (JSONException e) {
                    this.c = new d(2, e.getLocalizedMessage());
                    String unused4 = ConfigNetworkResponse.b;
                    StringBuilder sb4 = new StringBuilder("Config type:");
                    sb4.append(this.b.a());
                    sb4.append(" Error code:");
                    sb4.append(this.c.a);
                    sb4.append(" Error message:");
                    sb4.append(this.c.b);
                }
            }
        }

        public final boolean a() {
            return this.c != null;
        }
    }

    private static String a(Map<String, a> map) {
        Iterator<String> it = map.keySet().iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + it.next() + ",";
        }
        return str.substring(0, str.length() - 1);
    }
}
