package com.applovin.impl.sdk.c;

import android.text.TextUtils;
import com.applovin.impl.sdk.n;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class f {
    private final com.applovin.impl.sdk.h c;
    private final n d;
    private final Object b = new Object();
    private final List<a> a = new ArrayList();

    private static class a {
        private final Long a;
        private final String b;
        private final String c;
        private final String d;

        private a(String str, Throwable th) {
            this.b = str;
            this.a = Long.valueOf(System.currentTimeMillis());
            this.c = th != null ? th.getClass().getName() : null;
            this.d = th != null ? th.getMessage() : null;
        }

        private a(JSONObject jSONObject) throws JSONException {
            this.b = jSONObject.getString("ms");
            this.a = Long.valueOf(jSONObject.getLong("ts"));
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("ex");
            this.c = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.getString("nm") : null;
            this.d = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.getString("rn") : null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ms", this.b);
            jSONObject.put("ts", this.a);
            if (!TextUtils.isEmpty(this.c)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("nm", this.c);
                if (!TextUtils.isEmpty(this.d)) {
                    jSONObject2.put("rn", this.d);
                }
                jSONObject.put("ex", jSONObject2);
            }
            return jSONObject;
        }

        public String toString() {
            return "ErrorLog{timestampMillis=" + this.a + ",message='" + this.b + "',throwableName='" + this.c + "',throwableReason='" + this.d + "'}";
        }
    }

    public f(com.applovin.impl.sdk.h hVar) {
        this.c = hVar;
        this.d = hVar.w();
    }

    private void d() {
        JSONArray jSONArray = new JSONArray();
        synchronized (this.b) {
            for (a aVar : this.a) {
                try {
                    jSONArray.put(aVar.a());
                } catch (JSONException e) {
                    this.d.a("ErrorManager", false, "Failed to convert error log into json.", e);
                    this.a.remove(aVar);
                }
            }
        }
        this.c.a(com.applovin.impl.sdk.b.d.i, jSONArray.toString());
    }

    public JSONArray a() {
        JSONArray jSONArray;
        synchronized (this.b) {
            jSONArray = new JSONArray();
            Iterator<a> it = this.a.iterator();
            while (it.hasNext()) {
                try {
                    jSONArray.put(it.next().a());
                } catch (JSONException e) {
                    this.d.a("ErrorManager", false, "Failed to convert error log into json.", e);
                }
            }
        }
        return jSONArray;
    }

    public void a(String str, Throwable th) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.b) {
            if (this.a.size() >= ((Integer) this.c.a(com.applovin.impl.sdk.b.b.eI)).intValue()) {
                return;
            }
            this.a.add(new a(str, th));
            d();
        }
    }

    public void b() {
        String str = (String) this.c.b(com.applovin.impl.sdk.b.d.i, null);
        if (str != null) {
            synchronized (this.b) {
                try {
                    this.a.clear();
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            this.a.add(new a(jSONArray.getJSONObject(i)));
                        } catch (JSONException e) {
                            this.d.a("ErrorManager", false, "Failed to convert error json into a log.", e);
                        }
                    }
                } catch (JSONException e2) {
                    this.d.b("ErrorManager", "Unable to convert String to json.", e2);
                }
            }
        }
    }

    public void c() {
        synchronized (this.b) {
            this.a.clear();
            this.c.b(com.applovin.impl.sdk.b.d.i);
        }
    }
}
