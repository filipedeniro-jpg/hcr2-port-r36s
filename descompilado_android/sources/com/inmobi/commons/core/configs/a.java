package com.inmobi.commons.core.configs;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Config.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public C0075a p = new C0075a();

    public abstract String a();

    public abstract boolean c();

    public abstract a d();

    public void a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("includeIds");
        for (int i = 0; i < jSONObject2.length(); i++) {
            this.p.a.put("O1", Boolean.valueOf(jSONObject2.getBoolean("O1")));
            this.p.a.put("UM5", Boolean.valueOf(jSONObject2.getBoolean("UM5")));
            this.p.a.put("GPID", Boolean.valueOf(jSONObject2.getBoolean("GPID")));
            this.p.a.put("SHA1_IMEI", Boolean.valueOf(jSONObject2.optBoolean("SHA1_IMEI", false)));
            this.p.a.put("MD5_IMEI", Boolean.valueOf(jSONObject2.optBoolean("MD5_IMEI", false)));
        }
    }

    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("O1", this.p.a.get("O1"));
        jSONObject2.put("UM5", this.p.a.get("UM5"));
        jSONObject2.put("GPID", this.p.a.get("GPID"));
        jSONObject2.put("SHA1_IMEI", this.p.a.get("SHA1_IMEI"));
        jSONObject2.put("MD5_IMEI", this.p.a.get("MD5_IMEI"));
        jSONObject.put("includeIds", jSONObject2);
        return jSONObject;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == getClass() && a().equals(((a) obj).a());
    }

    public int hashCode() {
        return a().hashCode();
    }

    /* JADX INFO: renamed from: com.inmobi.commons.core.configs.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Config.java */
    public static final class C0075a {
        public HashMap<String, Boolean> a = new HashMap<>();

        public C0075a() {
            this.a.put("O1", true);
            this.a.put("UM5", true);
            this.a.put("GPID", true);
            this.a.put("SHA1_IMEI", false);
            this.a.put("MD5_IMEI", false);
        }
    }
}
