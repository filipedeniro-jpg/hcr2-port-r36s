package com.inmobi.a;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CarbGetListNetworkResponse.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    private static final String e = "c";
    String c;
    private com.inmobi.commons.core.network.d f;
    boolean a = true;
    int d = 0;
    List<d> b = new ArrayList();

    public c(com.inmobi.commons.core.network.d dVar) {
        this.f = dVar;
        a();
        if (this.a) {
            try {
                com.inmobi.commons.core.e.b.a().a(new com.inmobi.commons.core.e.f("signals", "InvalidCarbGetResponse"));
            } catch (Exception e2) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e2.getMessage());
                sb.append(")");
            }
        }
    }

    private void a() {
        if (!this.f.a()) {
            try {
                JSONObject jSONObject = new JSONObject(this.f.b());
                if (jSONObject.getBoolean("success")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    this.c = jSONObject2.getString("req_id");
                    JSONArray jSONArray = jSONObject2.getJSONArray("p_apps");
                    int i = 0;
                    while (i < jSONArray.length()) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                        String strOptString = jSONObject3.optString("bid", null);
                        String strOptString2 = jSONObject3.optString("inm_id", null);
                        if (strOptString != null && strOptString2 != null && strOptString2.trim().length() > 0) {
                            this.b.add(new d(strOptString, strOptString2));
                        }
                        i++;
                        this.d = i;
                    }
                } else {
                    new StringBuilder("Error response for Carb list received. Error code:").append(jSONObject.optInt("errorCode"));
                }
                this.a = false;
                return;
            } catch (JSONException unused) {
                return;
            }
        }
        new StringBuilder("Error response for Carb list received. Error code:").append(this.f.b.a);
    }
}
