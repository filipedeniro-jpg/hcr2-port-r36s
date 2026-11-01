package com.applovin.impl.mediation.b;

import com.applovin.impl.mediation.h;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d extends a {
    private d(d dVar, h hVar) {
        super(dVar.t(), dVar.s(), hVar, dVar.b);
    }

    public d(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.h hVar) {
        super(jSONObject, jSONObject2, null, hVar);
    }

    @Override // com.applovin.impl.mediation.b.a
    public a a(h hVar) {
        return new d(this, hVar);
    }

    @Override // com.applovin.impl.mediation.b.e
    public String toString() {
        return "MediatedNativeAd{format=" + getFormat() + ", adUnitId=" + getAdUnitId() + ", isReady=" + isReady() + ", adapterClass='" + u() + "', adapterName='" + v() + "', isTesting=" + w() + ", isRefreshEnabled=" + A() + ", getAdRefreshMillis=" + B() + '}';
    }
}
