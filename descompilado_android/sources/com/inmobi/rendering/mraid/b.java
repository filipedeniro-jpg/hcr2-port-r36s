package com.inmobi.rendering.mraid;

import com.my.target.ab;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ExpandProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    private static final String d = "b";
    public boolean b;
    public String c;
    private int e = com.inmobi.commons.core.utilities.b.c.a().a;
    private int f = com.inmobi.commons.core.utilities.b.c.a().b;
    public boolean a = false;
    private boolean g = true;

    public b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", this.e);
            jSONObject.put("height", this.f);
            jSONObject.put(ab.by, this.a);
            jSONObject.put("isModal", this.g);
        } catch (JSONException e) {
            new StringBuilder("Exception in composing ExpandProperties: ").append(e.getMessage());
        }
        this.c = jSONObject.toString();
    }

    public static b a(String str) {
        b bVar = new b();
        bVar.c = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            bVar.g = true;
            if (jSONObject.has(ab.by)) {
                bVar.b = true;
            }
            bVar.a = jSONObject.optBoolean(ab.by, false);
        } catch (JSONException unused) {
        }
        return bVar;
    }
}
