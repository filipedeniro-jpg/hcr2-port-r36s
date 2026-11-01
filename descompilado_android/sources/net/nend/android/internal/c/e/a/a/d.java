package net.nend.android.internal.c.e.a.a;

import com.amazon.device.ads.AdWebViewClient;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Sensor.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    private final JSONObject a;
    private final JSONObject b;
    private final JSONObject c;

    private d(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = this.a;
        if (jSONObject2 != null) {
            jSONObject.putOpt("attitude", jSONObject2);
        }
        JSONObject jSONObject3 = this.b;
        if (jSONObject3 != null) {
            jSONObject.putOpt("air", jSONObject3);
        }
        JSONObject jSONObject4 = this.c;
        if (jSONObject4 != null) {
            jSONObject.putOpt(AdWebViewClient.GEO, jSONObject4);
        }
        if (jSONObject.length() > 0) {
            return jSONObject;
        }
        return null;
    }

    /* JADX INFO: compiled from: Sensor.java */
    public static class a {
        private JSONObject a;
        private JSONObject b;
        private JSONObject c;

        public a a(JSONObject jSONObject) {
            this.a = jSONObject;
            return this;
        }

        public a b(JSONObject jSONObject) {
            this.b = jSONObject;
            return this;
        }

        public a c(JSONObject jSONObject) {
            this.c = jSONObject;
            return this;
        }

        public d a() {
            return new d(this);
        }
    }
}
