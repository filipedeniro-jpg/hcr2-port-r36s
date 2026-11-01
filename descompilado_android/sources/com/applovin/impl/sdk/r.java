package com.applovin.impl.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class r {
    private final h a;
    private final JSONObject b;
    private final Object c = new Object();

    public r(h hVar) {
        this.a = hVar;
        this.b = com.applovin.impl.sdk.utils.g.a((String) hVar.b(com.applovin.impl.sdk.b.d.m, "{}"), new JSONObject(), hVar);
    }

    public Integer a(String str) {
        Integer numValueOf;
        synchronized (this.c) {
            if (this.b.has(str)) {
                com.applovin.impl.sdk.utils.g.a(this.b, str, com.applovin.impl.sdk.utils.g.b(this.b, str, 0, this.a) + 1, this.a);
            } else {
                com.applovin.impl.sdk.utils.g.a(this.b, str, 1, this.a);
            }
            this.a.a(com.applovin.impl.sdk.b.d.m, this.b.toString());
            numValueOf = Integer.valueOf(com.applovin.impl.sdk.utils.g.b(this.b, str, 0, this.a));
        }
        return numValueOf;
    }
}
