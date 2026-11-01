package com.applovin.impl.sdk.utils;

/* JADX INFO: loaded from: classes.dex */
public final class m {
    private final com.applovin.impl.sdk.h a;
    private String b;

    public m(com.applovin.impl.sdk.h hVar) {
        this.a = hVar;
        if (!((Boolean) hVar.a(com.applovin.impl.sdk.b.b.eg)).booleanValue()) {
            hVar.b(com.applovin.impl.sdk.b.d.b);
        }
        String str = (String) hVar.a(com.applovin.impl.sdk.b.d.b);
        if (k.b(str)) {
            hVar.w().a("AppLovinSdk", "Using identifier (" + str + ") from previous session");
            this.b = str;
        }
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        if (((Boolean) this.a.a(com.applovin.impl.sdk.b.b.eg)).booleanValue()) {
            this.a.a(com.applovin.impl.sdk.b.d.b, str);
        }
        this.b = str;
    }
}
