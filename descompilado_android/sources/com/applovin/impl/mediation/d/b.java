package com.applovin.impl.mediation.d;

import com.applovin.impl.sdk.b.d;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.f;
import com.applovin.impl.sdk.utils.g;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends f {
    public static String a(h hVar) {
        return f.a((String) hVar.a(com.applovin.impl.sdk.b.a.a), "1.0/mediate", hVar);
    }

    public static void a(JSONObject jSONObject, h hVar) {
        if (g.a(jSONObject, "signal_providers")) {
            hVar.a(d.r, jSONObject.toString());
            hVar.w().b("MediationConnectionUtils", "Updated signal provider(s)");
        }
    }

    public static String b(h hVar) {
        return f.a((String) hVar.a(com.applovin.impl.sdk.b.a.b), "1.0/mediate", hVar);
    }

    public static void b(JSONObject jSONObject, h hVar) {
        if (g.a(jSONObject, "auto_init_adapters")) {
            hVar.a(d.s, jSONObject.toString());
            hVar.w().b("MediationConnectionUtils", "Updated auto-init adapter(s)");
        }
    }

    public static String c(h hVar) {
        return f.a((String) hVar.a(com.applovin.impl.sdk.b.a.a), "1.0/mediate_debug", hVar);
    }

    public static String d(h hVar) {
        return f.a((String) hVar.a(com.applovin.impl.sdk.b.a.b), "1.0/mediate_debug", hVar);
    }
}
