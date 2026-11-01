package com.adcolony.sdk;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class af {
    private String a;
    private JSONObject b;

    af(JSONObject jSONObject) {
        try {
            this.b = jSONObject;
            this.a = jSONObject.getString("m_type");
        } catch (JSONException e) {
            new aa.a().a("JSON Error in ADCMessage constructor: ").a(e.toString()).a(aa.h);
        }
    }

    af(String str, int i) {
        try {
            this.a = str;
            this.b = new JSONObject();
            this.b.put("m_target", i);
        } catch (JSONException e) {
            new aa.a().a("JSON Error in ADCMessage constructor: ").a(e.toString()).a(aa.h);
        }
    }

    af(String str, int i, String str2) {
        try {
            this.a = str;
            this.b = y.a(str2);
            this.b.put("m_target", i);
        } catch (JSONException e) {
            new aa.a().a("JSON Error in ADCMessage constructor: ").a(e.toString()).a(aa.h);
        }
    }

    af(String str, int i, JSONObject jSONObject) {
        try {
            this.a = str;
            this.b = jSONObject == null ? new JSONObject() : jSONObject;
            this.b.put("m_target", i);
        } catch (JSONException e) {
            new aa.a().a("JSON Error in ADCMessage constructor: ").a(e.toString()).a(aa.h);
        }
    }

    af a() {
        return a((JSONObject) null);
    }

    af a(String str) {
        return a(y.a(str));
    }

    af a(JSONObject jSONObject) {
        try {
            af afVar = new af("reply", this.b.getInt("m_origin"), jSONObject);
            afVar.b.put("m_id", this.b.getInt("m_id"));
            return afVar;
        } catch (JSONException e) {
            new aa.a().a("JSON error in ADCMessage's createReply(): ").a(e.toString()).a(aa.h);
            return new af("JSONException", 0);
        }
    }

    void b() {
        a.a(this.a, this.b);
    }

    JSONObject c() {
        return this.b;
    }

    void b(JSONObject jSONObject) {
        this.b = jSONObject;
    }

    String d() {
        return this.a;
    }

    void b(String str) {
        this.a = str;
    }
}
