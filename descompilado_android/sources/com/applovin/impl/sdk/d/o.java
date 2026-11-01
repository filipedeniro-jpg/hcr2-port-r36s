package com.applovin.impl.sdk.d;

import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class o extends m {
    private final com.applovin.impl.sdk.ad.c a;

    public o(com.applovin.impl.sdk.ad.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
        super(com.applovin.impl.sdk.ad.d.a("adtoken_zone", hVar), appLovinAdLoadListener, "TaskFetchTokenAd", hVar);
        this.a = cVar;
    }

    @Override // com.applovin.impl.sdk.d.m, com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.p;
    }

    @Override // com.applovin.impl.sdk.d.m
    Map<String, String> b() {
        HashMap map = new HashMap(2);
        map.put("adtoken", com.applovin.impl.sdk.utils.k.e(this.a.a()));
        map.put("adtoken_prefix", com.applovin.impl.sdk.utils.k.e(this.a.c()));
        return map;
    }

    @Override // com.applovin.impl.sdk.d.m
    protected com.applovin.impl.sdk.ad.b g() {
        return com.applovin.impl.sdk.ad.b.REGULAR_AD_TOKEN;
    }
}
