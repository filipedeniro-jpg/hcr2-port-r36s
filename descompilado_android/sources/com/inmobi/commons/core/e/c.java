package com.inmobi.commons.core.e;

import com.ironsource.eventsmodule.DataBaseEventsStorage;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: TelemetryComponentConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    int a;
    boolean b;
    private String c;
    private Map<String, a> d;

    /* JADX INFO: compiled from: TelemetryComponentConfig.java */
    static final class a {
        String a;
        int b;
    }

    public c() {
        this.a = 0;
        this.c = "telemetry";
        this.b = false;
        this.d = new HashMap();
    }

    public c(String str, JSONObject jSONObject, c cVar) {
        String str2;
        this.a = 0;
        this.c = "telemetry";
        this.b = false;
        this.d = new HashMap();
        if (jSONObject == null) {
            a(str);
            return;
        }
        if (str != null) {
            try {
                str2 = str.trim().length() == 0 ? cVar.c : str;
            } catch (JSONException unused) {
                a(str);
                return;
            }
        }
        this.c = str2;
        this.b = !jSONObject.has("enabled") || jSONObject.getBoolean("enabled");
        this.a = jSONObject.has("samplingFactor") ? jSONObject.getInt("samplingFactor") : cVar.a;
        this.d = new HashMap();
        if (jSONObject.has(DataBaseEventsStorage.EventEntry.TABLE_NAME)) {
            JSONArray jSONArray = jSONObject.getJSONArray(DataBaseEventsStorage.EventEntry.TABLE_NAME);
            for (int i = 0; i < jSONArray.length(); i++) {
                a aVar = new a();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                aVar.a = jSONObject2.getString("type");
                aVar.b = jSONObject2.has("samplingFactor") ? jSONObject2.getInt("samplingFactor") : this.a;
                this.d.put(aVar.a, aVar);
            }
        }
    }

    private void a(String str) {
        this.b = true;
        this.c = str;
    }
}
