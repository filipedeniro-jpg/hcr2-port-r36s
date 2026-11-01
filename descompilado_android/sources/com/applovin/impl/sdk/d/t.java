package com.applovin.impl.sdk.d;

import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class t extends a {
    private final JSONObject a;
    private final JSONObject c;
    private final AppLovinAdLoadListener d;
    private final com.applovin.impl.sdk.ad.b e;

    t(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.ad.b bVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
        super("TaskRenderAppLovinAd", hVar);
        this.a = jSONObject;
        this.c = jSONObject2;
        this.e = bVar;
        this.d = appLovinAdLoadListener;
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.t;
    }

    @Override // java.lang.Runnable
    public void run() {
        a("Rendering ad...");
        com.applovin.impl.sdk.ad.a aVar = new com.applovin.impl.sdk.ad.a(this.a, this.c, this.e, this.b);
        boolean zBooleanValue = com.applovin.impl.sdk.utils.g.a(this.a, "gs_load_immediately", (Boolean) false, this.b).booleanValue();
        boolean zBooleanValue2 = com.applovin.impl.sdk.utils.g.a(this.a, "vs_load_immediately", (Boolean) true, this.b).booleanValue();
        d dVar = new d(aVar, this.b, this.d);
        dVar.a(zBooleanValue2);
        dVar.b(zBooleanValue);
        q.a aVar2 = q.a.CACHING_OTHER;
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.be)).booleanValue()) {
            if (aVar.getSize() == AppLovinAdSize.INTERSTITIAL && aVar.getType() == AppLovinAdType.REGULAR) {
                aVar2 = q.a.CACHING_INTERSTITIAL;
            } else if (aVar.getSize() == AppLovinAdSize.INTERSTITIAL && aVar.getType() == AppLovinAdType.INCENTIVIZED) {
                aVar2 = q.a.CACHING_INCENTIVIZED;
            }
        }
        this.b.F().a(dVar, aVar2);
    }
}
