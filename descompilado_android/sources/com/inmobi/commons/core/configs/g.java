package com.inmobi.commons.core.configs;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: PkConfig.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g extends a {
    public String a = "010001";
    public String b = "E72409364B865B757E1D6B8DB73011BBB1D20C1A9F931ADD3C4C09E2794CE102F8AA7F2D50EB88F9880A576E6C7B0E95712CAE9416F7BACB798564627846E93B";
    private String d = "rsa";
    public String c = "1";

    @Override // com.inmobi.commons.core.configs.a
    public final String a() {
        return "pk";
    }

    @Override // com.inmobi.commons.core.configs.a
    public final void a(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        this.a = jSONObject.getString("e");
        this.b = jSONObject.getString("m");
        this.d = jSONObject.getString("alg");
        this.c = jSONObject.getString("ver");
    }

    @Override // com.inmobi.commons.core.configs.a
    public final JSONObject b() throws JSONException {
        JSONObject jSONObjectB = super.b();
        jSONObjectB.put("e", this.a);
        jSONObjectB.put("m", this.b);
        jSONObjectB.put("alg", this.d);
        jSONObjectB.put("ver", this.c);
        return jSONObjectB;
    }

    @Override // com.inmobi.commons.core.configs.a
    public final boolean c() {
        return (this.a.trim().length() == 0 || this.b.trim().length() == 0 || this.d.trim().length() == 0 || this.c.trim().length() == 0) ? false : true;
    }

    @Override // com.inmobi.commons.core.configs.a
    public final a d() {
        return new g();
    }
}
