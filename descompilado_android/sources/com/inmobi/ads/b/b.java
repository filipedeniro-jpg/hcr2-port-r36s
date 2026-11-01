package com.inmobi.ads.b;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.inmobi.ads.f;
import com.inmobi.ads.r;
import com.inmobi.commons.core.configs.g;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: GMARequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    @NonNull
    public f a;

    @Nullable
    private List<com.inmobi.ads.a> b;

    b(@NonNull f fVar, @Nullable List<com.inmobi.ads.a> list) {
        this.a = fVar;
        this.b = list;
    }

    final byte[] a() throws JSONException, com.inmobi.ads.a.b {
        HashMap map = new HashMap();
        map.put("h-user-agent", com.inmobi.commons.a.a.f());
        JSONArray jSONArray = new JSONArray();
        if (this.b != null) {
            g gVar = new g();
            com.inmobi.commons.core.configs.b.a().a(gVar, (com.inmobi.commons.core.configs.b.c) null);
            for (com.inmobi.ads.a aVar : this.b) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("impressionId", aVar.g);
                r rVar = this.a.k;
                float f = aVar.k;
                String strA = com.inmobi.commons.core.utilities.a.b.a(String.valueOf(f), rVar.b, rVar.a, rVar.c, gVar.b, gVar.a);
                if (strA == null) {
                    strA = "";
                }
                jSONObject.put("bid", strA);
                JSONObject jSONObjectF = aVar.f();
                if (jSONObjectF == null) {
                    jSONObjectF = new JSONObject();
                }
                jSONObject.put("cachedAdData", jSONObjectF);
                jSONArray.put(jSONObject);
            }
        }
        map.put("cachedAdInfos", jSONArray.toString());
        this.a.c(map);
        this.a.a();
        if (this.a.x != 1) {
            throw new com.inmobi.ads.a.b();
        }
        return this.a.f().getBytes();
    }
}
