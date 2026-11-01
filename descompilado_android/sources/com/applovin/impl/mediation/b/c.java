package com.applovin.impl.mediation.b;

import com.applovin.impl.mediation.h;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c extends a {
    private c(c cVar, h hVar) {
        super(cVar.t(), cVar.s(), hVar, cVar.b);
    }

    public c(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.h hVar) {
        super(jSONObject, jSONObject2, null, hVar);
    }

    @Override // com.applovin.impl.mediation.b.a
    public a a(h hVar) {
        return new c(this, hVar);
    }

    public long i() {
        long jB = b("ad_expiration_ms", -1L);
        return jB >= 0 ? jB : a("ad_expiration_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.a.M)).longValue());
    }

    public long j() {
        long jB = b("ad_hidden_timeout_ms", -1L);
        return jB >= 0 ? jB : a("ad_hidden_timeout_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.a.N)).longValue());
    }

    public boolean k() {
        if (b("schedule_ad_hidden_on_ad_dismiss", (Boolean) false)) {
            return true;
        }
        return a("schedule_ad_hidden_on_ad_dismiss", (Boolean) this.b.a(com.applovin.impl.sdk.b.a.P));
    }

    public long l() {
        long jB = b("ad_hidden_on_ad_dismiss_callback_delay_ms", -1L);
        return jB >= 0 ? jB : a("ad_hidden_on_ad_dismiss_callback_delay_ms", ((Long) this.b.a(com.applovin.impl.sdk.b.a.Q)).longValue());
    }

    @Override // com.applovin.impl.mediation.b.e
    public String toString() {
        return "MediatedFullscreenAd{format=" + getFormat() + ", adUnitId=" + getAdUnitId() + ", isReady=" + isReady() + ", adapterClass='" + u() + "', adapterName='" + v() + "', isTesting=" + w() + ", isRefreshEnabled=" + A() + ", getAdRefreshMillis=" + B() + '}';
    }
}
