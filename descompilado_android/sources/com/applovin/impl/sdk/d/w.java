package com.applovin.impl.sdk.d;

import android.text.TextUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class w<T> extends a implements com.applovin.impl.sdk.network.a.b<T> {
    private final com.applovin.impl.sdk.network.b<T> a;
    private final com.applovin.impl.sdk.network.a.b<T> c;
    protected com.applovin.impl.sdk.network.a.C0027a d;
    private q.a e;
    private com.applovin.impl.sdk.b.b<String> f;
    private com.applovin.impl.sdk.b.b<String> g;

    public w(com.applovin.impl.sdk.network.b<T> bVar, com.applovin.impl.sdk.h hVar) {
        this(bVar, hVar, false);
    }

    public w(com.applovin.impl.sdk.network.b<T> bVar, final com.applovin.impl.sdk.h hVar, boolean z) {
        super("TaskRepeatRequest", hVar, z);
        this.e = q.a.BACKGROUND;
        this.f = null;
        this.g = null;
        if (bVar == null) {
            throw new IllegalArgumentException("No request specified");
        }
        this.a = bVar;
        this.d = new com.applovin.impl.sdk.network.a.C0027a();
        this.c = new com.applovin.impl.sdk.network.a.b<T>() { // from class: com.applovin.impl.sdk.d.w.1
            @Override // com.applovin.impl.sdk.network.a.b
            public void a(int i) {
                w wVar;
                com.applovin.impl.sdk.b.b bVar2;
                boolean z2 = i < 200 || i >= 500;
                boolean z3 = i != -103;
                if (z2 && z3) {
                    String strE = w.this.a.e();
                    if (w.this.a.i() > 0) {
                        w.this.c("Unable to send request due to server failure (code " + i + "). " + w.this.a.i() + " attempts left, retrying in " + TimeUnit.MILLISECONDS.toSeconds(w.this.a.k()) + " seconds...");
                        int i2 = w.this.a.i() - 1;
                        w.this.a.a(i2);
                        if (i2 == 0) {
                            w wVar2 = w.this;
                            wVar2.c(wVar2.f);
                            if (com.applovin.impl.sdk.utils.k.b(strE) && strE.length() >= 4) {
                                w.this.a.a(strE);
                                w.this.b("Switching to backup endpoint " + strE);
                            }
                        }
                        q qVarF = hVar.F();
                        w wVar3 = w.this;
                        qVarF.a(wVar3, wVar3.e, w.this.a.k());
                        return;
                    }
                    if (strE == null || !strE.equals(w.this.a.a())) {
                        wVar = w.this;
                        bVar2 = wVar.f;
                    } else {
                        wVar = w.this;
                        bVar2 = wVar.g;
                    }
                    wVar.c(bVar2);
                }
                w.this.a(i);
            }

            @Override // com.applovin.impl.sdk.network.a.b
            public void a(T t, int i) {
                w.this.a.a(0);
                w.this.a(t, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <ST> void c(com.applovin.impl.sdk.b.b<ST> bVar) {
        if (bVar != null) {
            com.applovin.impl.sdk.b.c cVarY = c().y();
            cVarY.a((com.applovin.impl.sdk.b.b<?>) bVar, (Object) bVar.b());
            cVarY.a();
        }
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.e;
    }

    public void a(int i) {
    }

    public void a(com.applovin.impl.sdk.b.b<String> bVar) {
        this.f = bVar;
    }

    public void a(q.a aVar) {
        this.e = aVar;
    }

    public void a(T t, int i) {
    }

    public void b(com.applovin.impl.sdk.b.b<String> bVar) {
        this.g = bVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        int i;
        com.applovin.impl.sdk.network.a aVarE = c().E();
        if (!c().c() && !c().d()) {
            d("AppLovin SDK is disabled: please check your connection");
            c().w().e("AppLovinSdk", "AppLovin SDK is disabled: please check your connection");
            i = -22;
        } else {
            if (com.applovin.impl.sdk.utils.k.b(this.a.a()) && this.a.a().length() >= 4) {
                if (TextUtils.isEmpty(this.a.c())) {
                    this.a.b(this.a.d() != null ? HttpRequest.METHOD_POST : HttpRequest.METHOD_GET);
                }
                aVarE.a(this.a, this.d, this.c);
                return;
            }
            d("Task has an invalid or null request endpoint.");
            i = -900;
        }
        a(i);
    }
}
