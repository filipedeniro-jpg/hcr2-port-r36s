package com.inmobi.a;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* JADX INFO: compiled from: CarbPostListNetworkRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends com.inmobi.commons.core.network.c {
    int a;
    int b;
    private c c;
    private List<d> d;

    f(String str, int i, int i2, com.inmobi.commons.core.utilities.uid.d dVar, List<d> list, c cVar) {
        super(HttpRequest.METHOD_POST, str, true, dVar);
        this.a = i;
        this.b = i2;
        this.d = list;
        this.c = cVar;
        this.o.put("req_id", this.c.c);
        this.o.put("i_till", Integer.toString(this.c.d));
        Map<String, String> map = this.o;
        JSONArray jSONArray = new JSONArray();
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            jSONArray.put(this.d.get(i3).b);
        }
        map.put("p_a_apps", jSONArray.toString());
    }
}
