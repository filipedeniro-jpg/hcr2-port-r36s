package com.inmobi.a;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: IceSample.java */
/* JADX INFO: loaded from: classes2.dex */
public class k {
    private static final String d = "k";
    Map<String, Object> a;
    com.inmobi.commons.core.utilities.b.h b;
    List<l> c;
    private long e = Calendar.getInstance().getTimeInMillis();

    k() {
    }

    public final JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", this.e);
            if (this.a != null && !this.a.isEmpty()) {
                jSONObject.put("l", new JSONObject(this.a));
            }
            if (this.b != null) {
                jSONObject.put("s", this.b.b());
            }
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.c.size(); i++) {
                jSONArray.put(this.c.get(i).a());
            }
            if (jSONArray.length() > 0) {
                jSONObject.put(com.my.target.i.WIDTH, jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
