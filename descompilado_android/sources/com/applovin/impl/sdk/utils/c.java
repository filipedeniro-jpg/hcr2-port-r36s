package com.applovin.impl.sdk.utils;

import com.applovin.impl.sdk.s;

/* JADX INFO: loaded from: classes.dex */
public class c implements s.a {
    private final l a;

    private c(long j, com.applovin.impl.sdk.h hVar, final Runnable runnable) {
        final s sVarT = hVar.T();
        this.a = l.a(j, hVar, new Runnable() { // from class: com.applovin.impl.sdk.utils.c.1
            @Override // java.lang.Runnable
            public void run() {
                sVarT.b(c.this);
                c.this.a();
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
        sVarT.a(this);
    }

    public static c a(long j, com.applovin.impl.sdk.h hVar, Runnable runnable) {
        return new c(j, hVar, runnable);
    }

    public void a() {
        this.a.d();
    }

    @Override // com.applovin.impl.sdk.s.a
    public void b() {
        this.a.b();
    }

    @Override // com.applovin.impl.sdk.s.a
    public void c() {
        this.a.c();
    }
}
