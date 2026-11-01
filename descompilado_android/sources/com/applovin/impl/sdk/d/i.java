package com.applovin.impl.sdk.d;

/* JADX INFO: loaded from: classes.dex */
public class i extends com.applovin.impl.sdk.d.a {
    private final a a;

    public interface a {
        void a(com.applovin.impl.sdk.i.a aVar);
    }

    public i(com.applovin.impl.sdk.h hVar, a aVar) {
        super("TaskCollectAdvertisingId", hVar);
        this.a = aVar;
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.b;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.a(c().J().d());
    }
}
