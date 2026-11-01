package com.applovin.impl.mediation;

import com.applovin.impl.sdk.n;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private final com.applovin.impl.sdk.h a;
    private final n b;
    private final a c;
    private com.applovin.impl.sdk.utils.c d;

    public interface a {
        void c(com.applovin.impl.mediation.b.c cVar);
    }

    c(com.applovin.impl.sdk.h hVar, a aVar) {
        this.a = hVar;
        this.b = hVar.w();
        this.c = aVar;
    }

    public void a() {
        this.b.a("AdHiddenCallbackTimeoutManager", "Cancelling timeout");
        com.applovin.impl.sdk.utils.c cVar = this.d;
        if (cVar != null) {
            cVar.a();
            this.d = null;
        }
    }

    public void a(final com.applovin.impl.mediation.b.c cVar, long j) {
        this.b.a("AdHiddenCallbackTimeoutManager", "Scheduling in " + j + "ms...");
        this.d = com.applovin.impl.sdk.utils.c.a(j, this.a, new Runnable() { // from class: com.applovin.impl.mediation.c.1
            @Override // java.lang.Runnable
            public void run() {
                c.this.b.a("AdHiddenCallbackTimeoutManager", "Timing out...");
                c.this.c.c(cVar);
            }
        });
    }
}
