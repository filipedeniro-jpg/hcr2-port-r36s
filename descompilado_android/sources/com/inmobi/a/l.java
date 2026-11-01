package com.inmobi.a;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: IceWifiSample.java */
/* JADX INFO: loaded from: classes2.dex */
public class l {
    private static final String d = "l";
    com.inmobi.a.b.a a;
    List<com.inmobi.a.b.a> b;
    Map<String, String> c;
    private long e = Calendar.getInstance().getTimeInMillis();

    l() {
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", this.e);
            if (this.c != null && !this.c.isEmpty()) {
                for (Map.Entry<String, String> entry : this.c.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
            if (this.a != null) {
                jSONObject.put("c-ap", this.a.a());
            }
            JSONArray jSONArray = new JSONArray();
            if (this.b != null) {
                for (int i = 0; i < this.b.size(); i++) {
                    jSONArray.put(this.b.get(i).a());
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("v-ap", jSONArray);
                }
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
