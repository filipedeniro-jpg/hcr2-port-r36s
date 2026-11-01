package com.applovin.impl.mediation.b;

import com.applovin.impl.sdk.h;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class g extends e {
    public g(JSONObject jSONObject, JSONObject jSONObject2, h hVar) {
        super(jSONObject, jSONObject2, hVar);
    }

    int a() {
        return a("max_signal_length", 2048);
    }

    public boolean b() {
        return b("only_collect_signal_when_initialized", (Boolean) false);
    }

    @Override // com.applovin.impl.mediation.b.e
    public String toString() {
        return "SignalProviderSpec{specObject=" + t() + '}';
    }
}
