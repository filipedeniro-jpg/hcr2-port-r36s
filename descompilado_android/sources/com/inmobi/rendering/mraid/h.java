package com.inmobi.rendering.mraid;

import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ResizeProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public class h {
    private static final String g = "h";
    int b;
    int c;
    int d = 0;
    int e = 0;
    String a = Constants.ForceClosePosition.TOP_RIGHT;
    boolean f = true;

    public static h a(String str, h hVar) {
        h hVar2 = new h();
        try {
            JSONObject jSONObject = new JSONObject(str);
            hVar2.b = jSONObject.getInt("width");
            hVar2.c = jSONObject.getInt("height");
            hVar2.d = jSONObject.getInt(aa.d.bl);
            hVar2.e = jSONObject.getInt(aa.d.bm);
            if (hVar == null) {
                return hVar2;
            }
            hVar2.a = jSONObject.optString(aa.d.bj, hVar.a);
            hVar2.f = jSONObject.optBoolean(aa.d.bk, hVar.f);
            return hVar2;
        } catch (JSONException unused) {
            return null;
        }
    }

    public final String a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("width", this.b);
            jSONObject.put("height", this.c);
            jSONObject.put(aa.d.bj, this.a);
            jSONObject.put(aa.d.bl, this.d);
            jSONObject.put(aa.d.bm, this.e);
            jSONObject.put(aa.d.bk, this.f);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }
}
