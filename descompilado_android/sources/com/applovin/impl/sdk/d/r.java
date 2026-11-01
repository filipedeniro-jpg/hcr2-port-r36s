package com.applovin.impl.sdk.d;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class r extends a implements AppLovinAdLoadListener {
    private final JSONObject a;
    private final com.applovin.impl.sdk.ad.d c;
    private final com.applovin.impl.sdk.ad.b d;
    private final AppLovinAdLoadListener e;

    public r(JSONObject jSONObject, com.applovin.impl.sdk.ad.d dVar, com.applovin.impl.sdk.ad.b bVar, AppLovinAdLoadListener appLovinAdLoadListener, com.applovin.impl.sdk.h hVar) {
        super("TaskProcessAdResponse", hVar);
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        }
        if (dVar == null) {
            throw new IllegalArgumentException("No zone specified");
        }
        this.a = jSONObject;
        this.c = dVar;
        this.d = bVar;
        this.e = appLovinAdLoadListener;
    }

    private void a(int i) {
        com.applovin.impl.sdk.utils.n.a(this.e, this.c, i, this.b);
    }

    private void a(AppLovinAd appLovinAd) {
        try {
            if (this.e != null) {
                this.e.adReceived(appLovinAd);
            }
        } catch (Throwable th) {
            a("Unable process a ad received notification", th);
        }
    }

    private void a(JSONObject jSONObject) {
        String strA = com.applovin.impl.sdk.utils.g.a(jSONObject, "type", "undefined", this.b);
        if ("applovin".equalsIgnoreCase(strA)) {
            a("Starting task for AppLovin ad...");
            this.b.F().a(new t(jSONObject, this.a, this.d, this, this.b));
        } else {
            if ("vast".equalsIgnoreCase(strA)) {
                a("Starting task for VAST ad...");
                this.b.F().a(s.a(jSONObject, this.a, this.d, this, this.b));
                return;
            }
            c("Unable to process ad of unknown type: " + strA);
            failedToReceiveAd(-800);
        }
    }

    private void b() {
        a(-6);
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.q;
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void adReceived(AppLovinAd appLovinAd) {
        a(appLovinAd);
    }

    @Override // com.applovin.sdk.AppLovinAdLoadListener
    public void failedToReceiveAd(int i) {
        b();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            a("Processing ad response...");
            JSONArray jSONArray = this.a.getJSONArray("ads");
            if (jSONArray.length() > 0) {
                a("Processing ad...");
                try {
                    a(jSONArray.getJSONObject(0));
                } catch (Throwable unused) {
                    d("Encountered error while processing ad");
                    b();
                    this.b.H().a(a());
                }
            } else {
                c("No ads were returned from the server");
                com.applovin.impl.sdk.utils.n.a(this.c.a(), this.a, this.b);
                a(204);
            }
        } catch (Throwable th) {
            a("Encountered error while processing ad response", th);
            b();
            this.b.H().a(a());
        }
    }
}
