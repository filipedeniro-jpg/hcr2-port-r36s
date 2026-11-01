package com.applovin.impl.sdk.d;

import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class l extends m {
    private final List<String> a;

    public l(List<String> list, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
        super(com.applovin.impl.sdk.ad.d.a(a(list), hVar), appLovinAdLoadListener, "TaskFetchMultizoneAd", hVar);
        this.a = Collections.unmodifiableList(list);
    }

    private static String a(List<String> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("No zone identifiers specified");
        }
        return list.get(0);
    }

    @Override // com.applovin.impl.sdk.d.m, com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.m;
    }

    @Override // com.applovin.impl.sdk.d.m
    Map<String, String> b() {
        HashMap map = new HashMap(1);
        List<String> list = this.a;
        map.put("zone_ids", com.applovin.impl.sdk.utils.k.e(com.applovin.impl.sdk.utils.d.a(list, list.size())));
        return map;
    }

    @Override // com.applovin.impl.sdk.d.m
    protected com.applovin.impl.sdk.ad.b g() {
        return com.applovin.impl.sdk.ad.b.APPLOVIN_MULTIZONE;
    }
}
