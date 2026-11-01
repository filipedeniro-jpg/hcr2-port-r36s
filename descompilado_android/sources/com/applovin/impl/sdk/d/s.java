package com.applovin.impl.sdk.d;

import com.applovin.sdk.AppLovinAdLoadListener;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
abstract class s extends com.applovin.impl.sdk.d.a {
    private final AppLovinAdLoadListener a;
    private final a c;

    private static final class a extends com.applovin.impl.a.c {
        a(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.ad.b bVar, com.applovin.impl.sdk.h hVar) {
            super(jSONObject, jSONObject2, bVar, hVar);
        }

        void a(com.applovin.impl.sdk.utils.o oVar) {
            if (oVar == null) {
                throw new IllegalArgumentException("No aggregated vast response specified");
            }
            this.a.add(oVar);
        }
    }

    private static final class b extends s {
        private final JSONObject a;

        b(com.applovin.impl.a.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
            super(cVar, appLovinAdLoadListener, hVar);
            if (appLovinAdLoadListener == null) {
                throw new IllegalArgumentException("No callback specified.");
            }
            this.a = cVar.c();
        }

        @Override // com.applovin.impl.sdk.d.a
        public com.applovin.impl.sdk.c.i a() {
            return com.applovin.impl.sdk.c.i.r;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.applovin.impl.a.d dVar;
            a("Processing SDK JSON response...");
            String strA = com.applovin.impl.sdk.utils.g.a(this.a, "xml", (String) null, this.b);
            if (!com.applovin.impl.sdk.utils.k.b(strA)) {
                d("No VAST response received.");
                dVar = com.applovin.impl.a.d.NO_WRAPPER_RESPONSE;
            } else {
                if (strA.length() < ((Integer) this.b.a(com.applovin.impl.sdk.b.b.eL)).intValue()) {
                    try {
                        a(com.applovin.impl.sdk.utils.p.a(strA, this.b));
                        return;
                    } catch (Throwable th) {
                        a("Unable to parse VAST response", th);
                        a(com.applovin.impl.a.d.XML_PARSING);
                        this.b.H().a(a());
                        return;
                    }
                }
                d("VAST response is over max length");
                dVar = com.applovin.impl.a.d.XML_PARSING;
            }
            a(dVar);
        }
    }

    private static final class c extends s {
        private final com.applovin.impl.sdk.utils.o a;

        c(com.applovin.impl.sdk.utils.o oVar, com.applovin.impl.a.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
            super(cVar, appLovinAdLoadListener, hVar);
            if (oVar == null) {
                throw new IllegalArgumentException("No response specified.");
            }
            if (cVar == null) {
                throw new IllegalArgumentException("No context specified.");
            }
            if (appLovinAdLoadListener == null) {
                throw new IllegalArgumentException("No callback specified.");
            }
            this.a = oVar;
        }

        @Override // com.applovin.impl.sdk.d.a
        public com.applovin.impl.sdk.c.i a() {
            return com.applovin.impl.sdk.c.i.s;
        }

        @Override // java.lang.Runnable
        public void run() {
            a("Processing VAST Wrapper response...");
            a(this.a);
        }
    }

    s(com.applovin.impl.a.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
        super("TaskProcessVastResponse", hVar);
        if (cVar == null) {
            throw new IllegalArgumentException("No context specified.");
        }
        this.a = appLovinAdLoadListener;
        this.c = (a) cVar;
    }

    public static s a(com.applovin.impl.sdk.utils.o oVar, com.applovin.impl.a.c cVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
        return new c(oVar, cVar, appLovinAdLoadListener, hVar);
    }

    public static s a(JSONObject jSONObject, JSONObject jSONObject2, com.applovin.impl.sdk.ad.b bVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
        return new b(new a(jSONObject, jSONObject2, bVar, hVar), appLovinAdLoadListener, hVar);
    }

    void a(com.applovin.impl.a.d dVar) {
        d("Failed to process VAST response due to VAST error code " + dVar);
        com.applovin.impl.a.i.a(this.c, this.a, dVar, -6, this.b);
    }

    void a(com.applovin.impl.sdk.utils.o oVar) {
        com.applovin.impl.a.d dVar;
        com.applovin.impl.sdk.d.a vVar;
        int iA = this.c.a();
        a("Finished parsing XML at depth " + iA);
        this.c.a(oVar);
        if (!com.applovin.impl.a.i.a(oVar)) {
            if (com.applovin.impl.a.i.b(oVar)) {
                a("VAST response is inline. Rendering ad...");
                vVar = new v(this.c, this.a, this.b);
                this.b.F().a(vVar);
            } else {
                d("VAST response is an error");
                dVar = com.applovin.impl.a.d.NO_WRAPPER_RESPONSE;
                a(dVar);
            }
        }
        int iIntValue = ((Integer) this.b.a(com.applovin.impl.sdk.b.b.eM)).intValue();
        if (iA < iIntValue) {
            a("VAST response is wrapper. Resolving...");
            vVar = new y(this.c, this.a, this.b);
            this.b.F().a(vVar);
        } else {
            d("Reached beyond max wrapper depth of " + iIntValue);
            dVar = com.applovin.impl.a.d.WRAPPER_LIMIT_REACHED;
            a(dVar);
        }
    }
}
