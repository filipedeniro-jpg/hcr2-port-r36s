package com.applovin.impl.sdk.d;

import com.applovin.sdk.AppLovinAdLoadListener;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class m extends a {
    private final com.applovin.impl.sdk.ad.d a;
    private final AppLovinAdLoadListener c;
    private boolean d;

    public m(com.applovin.impl.sdk.ad.d dVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
        this(dVar, appLovinAdLoadListener, "TaskFetchNextAd", hVar);
    }

    m(com.applovin.impl.sdk.ad.d dVar, AppLovinAdLoadListener appLovinAdLoadListener, String str, com.applovin.impl.sdk.h hVar) {
        super(str, hVar);
        this.d = false;
        this.a = dVar;
        this.c = appLovinAdLoadListener;
    }

    private void a(com.applovin.impl.sdk.c.h hVar) {
        long jB = hVar.b(com.applovin.impl.sdk.c.g.c);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - jB > TimeUnit.MINUTES.toMillis(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dW)).intValue())) {
            hVar.b(com.applovin.impl.sdk.c.g.c, jCurrentTimeMillis);
            hVar.c(com.applovin.impl.sdk.c.g.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        boolean z = i != 204;
        c().w().a(d(), Boolean.valueOf(z), "Unable to fetch " + this.a + " ad: server returned " + i);
        try {
            a(i);
        } catch (Throwable th) {
            c().w().c(d(), "Unable process a failure to recieve an ad", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(JSONObject jSONObject) {
        com.applovin.impl.sdk.utils.f.d(jSONObject, this.b);
        com.applovin.impl.sdk.utils.f.c(jSONObject, this.b);
        this.b.i();
        com.applovin.impl.sdk.utils.f.e(jSONObject, this.b);
        a aVarA = a(jSONObject);
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eZ)).booleanValue()) {
            this.b.F().a(aVarA);
        } else {
            this.b.F().a(aVarA, q.a.MAIN);
        }
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.n;
    }

    protected a a(JSONObject jSONObject) {
        return new r(jSONObject, this.a, g(), this.c, this.b);
    }

    protected void a(int i) {
        AppLovinAdLoadListener appLovinAdLoadListener = this.c;
        if (appLovinAdLoadListener != null) {
            if (appLovinAdLoadListener instanceof com.applovin.impl.sdk.k) {
                ((com.applovin.impl.sdk.k) appLovinAdLoadListener).a(this.a, i);
            } else {
                appLovinAdLoadListener.failedToReceiveAd(i);
            }
        }
    }

    public void a(boolean z) {
        this.d = z;
    }

    Map<String, String> b() {
        HashMap map = new HashMap(4);
        map.put("zone_id", com.applovin.impl.sdk.utils.k.e(this.a.a()));
        if (this.a.b() != null) {
            map.put("size", this.a.b().getLabel());
        }
        if (this.a.c() != null) {
            map.put("require", this.a.c().getLabel());
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ac)).booleanValue()) {
            map.put("n", String.valueOf(this.b.W().a(this.a.a())));
        }
        return map;
    }

    protected com.applovin.impl.sdk.ad.b g() {
        return this.a.j() ? com.applovin.impl.sdk.ad.b.APPLOVIN_PRIMARY_ZONE : com.applovin.impl.sdk.ad.b.APPLOVIN_CUSTOM_ZONE;
    }

    protected String h() {
        return com.applovin.impl.sdk.utils.f.g(this.b);
    }

    protected String i() {
        return com.applovin.impl.sdk.utils.f.h(this.b);
    }

    @Override // java.lang.Runnable
    public void run() {
        StringBuilder sb;
        String str;
        if (this.d) {
            sb = new StringBuilder();
            str = "Preloading next ad of zone: ";
        } else {
            sb = new StringBuilder();
            str = "Fetching next ad of zone: ";
        }
        sb.append(str);
        sb.append(this.a);
        a(sb.toString());
        com.applovin.impl.sdk.c.h hVarG = this.b.G();
        hVarG.a(com.applovin.impl.sdk.c.g.a);
        if (hVarG.b(com.applovin.impl.sdk.c.g.c) == 0) {
            hVarG.b(com.applovin.impl.sdk.c.g.c, System.currentTimeMillis());
        }
        try {
            Map<String, String> mapA = this.b.J().a(b(), this.d, false);
            a(hVarG);
            w<JSONObject> wVar = new w<JSONObject>(com.applovin.impl.sdk.network.b.a(this.b).a(h()).a(mapA).c(i()).b(HttpRequest.METHOD_GET).a(new JSONObject()).a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dM)).intValue()).b(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dL)).intValue()).a(), this.b) { // from class: com.applovin.impl.sdk.d.m.1
                @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
                public void a(int i) {
                    m.this.b(i);
                }

                @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
                public void a(JSONObject jSONObject, int i) {
                    if (i != 200) {
                        m.this.b(i);
                        return;
                    }
                    com.applovin.impl.sdk.utils.g.b(jSONObject, "ad_fetch_latency_millis", this.d.a(), this.b);
                    com.applovin.impl.sdk.utils.g.b(jSONObject, "ad_fetch_response_size", this.d.b(), this.b);
                    m.this.b(jSONObject);
                }
            };
            wVar.a(com.applovin.impl.sdk.b.b.aE);
            wVar.b(com.applovin.impl.sdk.b.b.aF);
            this.b.F().a(wVar);
        } catch (Throwable th) {
            a("Unable to fetch ad " + this.a, th);
            b(0);
            this.b.H().a(a());
        }
    }
}
