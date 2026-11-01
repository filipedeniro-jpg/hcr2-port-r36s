package com.applovin.impl.mediation;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.sdk.AppLovinSdkUtils;

/* JADX INFO: loaded from: classes.dex */
public class b implements a.InterfaceC0014a, c.a {
    private final a a;
    private final c b;
    private final MaxAdListener c;

    public b(com.applovin.impl.sdk.h hVar, MaxAdListener maxAdListener) {
        this.c = maxAdListener;
        this.a = new a(hVar);
        this.b = new c(hVar, this);
    }

    @Override // com.applovin.impl.mediation.a.InterfaceC0014a
    public void a(final com.applovin.impl.mediation.b.c cVar) {
        AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() { // from class: com.applovin.impl.mediation.b.1
            @Override // java.lang.Runnable
            public void run() {
                b.this.c.onAdHidden(cVar);
            }
        }, cVar.l());
    }

    public void a(MaxAd maxAd) {
        this.b.a();
        this.a.a();
    }

    public void b(com.applovin.impl.mediation.b.c cVar) {
        long j = cVar.j();
        if (j >= 0) {
            this.b.a(cVar, j);
        }
        if (cVar.k()) {
            this.a.a(cVar, this);
        }
    }

    @Override // com.applovin.impl.mediation.c.a
    public void c(com.applovin.impl.mediation.b.c cVar) {
        this.c.onAdHidden(cVar);
    }
}
