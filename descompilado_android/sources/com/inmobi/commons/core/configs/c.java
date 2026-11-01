package com.inmobi.commons.core.configs;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ConfigDao.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {
    com.inmobi.commons.core.d.c a = com.inmobi.commons.core.d.c.b("config_store");

    public final void a(a aVar) {
        String strC = this.a.c(aVar.a() + "_config");
        if (strC == null) {
            return;
        }
        try {
            aVar.a(new JSONObject(strC));
        } catch (JSONException unused) {
        }
    }

    public final boolean a(String str) {
        com.inmobi.commons.core.d.c cVar = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_config");
        return cVar.c(sb.toString()) != null;
    }

    public final long b(String str) {
        return this.a.b(str + "_config_update_ts", 0L);
    }

    public final void a(String str, long j) {
        this.a.a(str + "_config_update_ts", j);
    }
}
