package com.inmobi.commons.core.configs;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ConfigNetworkRequest.java */
/* JADX INFO: loaded from: classes2.dex */
final class f extends com.inmobi.commons.core.network.c {
    private static final String d = "f";
    int a;
    int b;
    Map<String, a> c;

    f(Map<String, a> map, com.inmobi.commons.core.utilities.uid.d dVar, String str, int i, int i2, int i3) {
        this(map, dVar, str, i, i2, false, i3);
    }

    f(Map<String, a> map, com.inmobi.commons.core.utilities.uid.d dVar, String str, int i, int i2, boolean z, int i3) {
        super(HttpRequest.METHOD_POST, (str == null || str.trim().length() == 0) ? z ? "https://config.inmobi.cn/config-server/v1/config/secure.cfg" : "https://config.inmobi.com/config-server/v1/config/secure.cfg" : str, dVar, i3);
        this.c = map;
        this.a = i;
        this.b = i2;
    }

    @Override // com.inmobi.commons.core.network.c
    public final void a() {
        super.a();
        this.o.put(TtmlNode.TAG_P, c());
        this.o.put("im-accid", com.inmobi.commons.a.a.e());
    }

    private String c() {
        c cVar = new c();
        try {
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry<String, a> entry : this.c.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("n", entry.getKey());
                jSONObject.put("t", cVar.b(entry.getKey()));
                jSONArray.put(jSONObject);
            }
            return jSONArray.toString();
        } catch (JSONException unused) {
            return "";
        }
    }
}
