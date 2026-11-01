package com.inmobi.rendering.mraid;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.my.target.aa;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: OrientationProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {
    private static String e = "g";
    public String b = "none";
    public String c = TtmlNode.RIGHT;
    public boolean a = true;
    public String d = null;

    public static g a(String str, g gVar) {
        g gVar2 = new g();
        gVar2.d = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            gVar2.b = jSONObject.optString(aa.d.bg, gVar.b);
            gVar2.a = jSONObject.optBoolean(aa.d.bf, gVar.a);
            gVar2.c = jSONObject.optString("direction", gVar.c);
            if (!gVar2.b.equals("portrait") && !gVar2.b.equals("landscape")) {
                gVar2.b = "none";
            }
            if (gVar2.c.equals(TtmlNode.LEFT) || gVar2.c.equals(TtmlNode.RIGHT)) {
                return gVar2;
            }
            gVar2.c = TtmlNode.RIGHT;
            return gVar2;
        } catch (JSONException unused) {
            return null;
        }
    }
}
