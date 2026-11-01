package com.applovin.impl.sdk.d;

import com.applovin.sdk.AppLovinAdLoadListener;
import io.fabric.sdk.android.services.network.HttpRequest;

/* JADX INFO: loaded from: classes.dex */
class y extends a {
    private com.applovin.impl.a.c a;
    private final AppLovinAdLoadListener c;

    y(com.applovin.impl.a.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
        super("TaskResolveVastWrapper", hVar);
        this.c = appLovinAdLoadListener;
        this.a = cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        d("Failed to resolve VAST wrapper due to error code " + i);
        if (i == -103) {
            com.applovin.impl.sdk.utils.n.a(this.c, this.a.g(), i, this.b);
        } else {
            com.applovin.impl.a.i.a(this.a, this.c, i == -102 ? com.applovin.impl.a.d.TIMED_OUT : com.applovin.impl.a.d.GENERAL_WRAPPER_ERROR, i, this.b);
        }
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.x;
    }

    @Override // java.lang.Runnable
    public void run() {
        String strA = com.applovin.impl.a.i.a(this.a);
        if (!com.applovin.impl.sdk.utils.k.b(strA)) {
            d("Resolving VAST failed. Could not find resolution URL");
            a(-1);
            return;
        }
        a("Resolving VAST ad with depth " + this.a.a() + " at " + strA);
        try {
            this.b.F().a(new w<com.applovin.impl.sdk.utils.o>(com.applovin.impl.sdk.network.b.a(this.b).a(strA).b(HttpRequest.METHOD_GET).a(com.applovin.impl.sdk.utils.o.a).a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.eS)).intValue()).b(((Integer) this.b.a(com.applovin.impl.sdk.b.b.eT)).intValue()).a(false).a(), this.b) { // from class: com.applovin.impl.sdk.d.y.1
                @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
                public void a(int i) {
                    d("Unable to resolve VAST wrapper. Server returned " + i);
                    y.this.a(i);
                }

                @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
                public void a(com.applovin.impl.sdk.utils.o oVar, int i) {
                    this.b.F().a(s.a(oVar, y.this.a, y.this.c, y.this.b));
                }
            });
        } catch (Throwable th) {
            a("Unable to resolve VAST wrapper", th);
            a(-1);
            this.b.H().a(a());
        }
    }
}
