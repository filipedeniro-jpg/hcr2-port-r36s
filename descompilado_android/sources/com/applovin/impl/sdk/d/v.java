package com.applovin.impl.sdk.d;

import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdType;
import com.my.target.bj;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
class v extends a {
    private com.applovin.impl.a.c a;
    private final AppLovinAdLoadListener c;

    v(com.applovin.impl.a.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
        super("TaskRenderVastAd", hVar);
        if (cVar == null) {
            throw new IllegalArgumentException("No context specified.");
        }
        this.c = appLovinAdLoadListener;
        this.a = cVar;
    }

    private void a(com.applovin.impl.a.d dVar, Throwable th) {
        a("Failed to render valid VAST ad", th);
        com.applovin.impl.a.i.a(this.a, this.c, dVar, -6, this.b);
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.v;
    }

    @Override // java.lang.Runnable
    public void run() {
        a("Rendering VAST ad...");
        int size = this.a.b().size();
        HashSet hashSet = new HashSet(size);
        HashSet hashSet2 = new HashSet(size);
        String strA = "";
        String strA2 = strA;
        com.applovin.impl.a.f fVarA = null;
        com.applovin.impl.a.j jVarA = null;
        com.applovin.impl.a.b bVarA = null;
        for (com.applovin.impl.sdk.utils.o oVar : this.a.b()) {
            com.applovin.impl.sdk.utils.o oVarC = oVar.c(com.applovin.impl.a.i.a(oVar) ? bj.fW : bj.fV);
            if (oVarC != null) {
                com.applovin.impl.sdk.utils.o oVarC2 = oVarC.c("AdSystem");
                if (oVarC2 != null) {
                    fVarA = com.applovin.impl.a.f.a(oVarC2, fVarA, this.b);
                }
                strA = com.applovin.impl.a.i.a(oVarC, "AdTitle", strA);
                strA2 = com.applovin.impl.a.i.a(oVarC, "Description", strA2);
                com.applovin.impl.a.i.a(oVarC.a(bj.fX), hashSet, this.a, this.b);
                com.applovin.impl.a.i.a(oVarC.a("Error"), hashSet2, this.a, this.b);
                com.applovin.impl.sdk.utils.o oVarB = oVarC.b(bj.fY);
                if (oVarB != null) {
                    for (com.applovin.impl.sdk.utils.o oVar2 : oVarB.d()) {
                        com.applovin.impl.sdk.utils.o oVarB2 = oVar2.b(bj.ga);
                        if (oVarB2 != null) {
                            jVarA = com.applovin.impl.a.j.a(oVarB2, jVarA, this.a, this.b);
                        } else {
                            com.applovin.impl.sdk.utils.o oVarC3 = oVar2.c(bj.gL);
                            if (oVarC3 != null) {
                                com.applovin.impl.sdk.utils.o oVarC4 = oVarC3.c(bj.gM);
                                if (oVarC4 != null) {
                                    bVarA = com.applovin.impl.a.b.a(oVarC4, bVarA, this.a, this.b);
                                }
                            } else {
                                d("Received and will skip rendering for an unidentified creative: " + oVar2);
                            }
                        }
                    }
                }
            } else {
                d("Did not find wrapper or inline response for node: " + oVar);
            }
        }
        try {
            com.applovin.impl.a.a aVarA = com.applovin.impl.a.a.aB().a(this.b).a(this.a.c()).b(this.a.d()).a(this.a.e()).a(this.a.f()).a(strA).b(strA2).a(fVarA).a(jVarA).a(bVarA).a(hashSet).b(hashSet2).a();
            com.applovin.impl.a.d dVarA = com.applovin.impl.a.i.a(aVarA);
            if (dVarA != null) {
                a(dVarA, (Throwable) null);
                return;
            }
            h hVar = new h(aVarA, this.b, this.c);
            q.a aVar = q.a.CACHING_OTHER;
            if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.be)).booleanValue()) {
                if (aVarA.getType() == AppLovinAdType.REGULAR) {
                    aVar = q.a.CACHING_INTERSTITIAL;
                } else if (aVarA.getType() == AppLovinAdType.INCENTIVIZED) {
                    aVar = q.a.CACHING_INCENTIVIZED;
                }
            }
            this.b.F().a(hVar, aVar);
        } catch (Throwable th) {
            a(com.applovin.impl.a.d.GENERAL_WRAPPER_ERROR, th);
            this.b.H().a(a());
        }
    }
}
