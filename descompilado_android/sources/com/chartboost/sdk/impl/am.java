package com.chartboost.sdk.impl;

import com.applovin.sdk.AppLovinEventParameters;
import com.chartboost.sdk.Libraries.CBUtility;
import com.facebook.appevents.UserDataStore;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class am extends aj {
    private final JSONObject n;
    private final JSONObject o;
    private final JSONObject p;
    private final JSONObject q;

    public am(String str, ap apVar, com.chartboost.sdk.Tracking.a aVar, int i, aj.a aVar2) {
        super(str, apVar, aVar, i, aVar2);
        this.n = new JSONObject();
        this.o = new JSONObject();
        this.p = new JSONObject();
        this.q = new JSONObject();
    }

    @Override // com.chartboost.sdk.impl.aj
    protected void c() {
        com.chartboost.sdk.Libraries.e.a(this.o, "app", this.m.s);
        com.chartboost.sdk.Libraries.e.a(this.o, "bundle", this.m.j);
        com.chartboost.sdk.Libraries.e.a(this.o, com.my.target.be.a.eX, this.m.k);
        com.chartboost.sdk.Libraries.e.a(this.o, "custom_id", com.chartboost.sdk.i.a);
        com.chartboost.sdk.Libraries.e.a(this.o, "session_id", "");
        com.chartboost.sdk.Libraries.e.a(this.o, "ui", -1);
        com.chartboost.sdk.Libraries.e.a(this.o, "test_mode", false);
        com.chartboost.sdk.Libraries.e.a(this.o, "certification_providers", o.f());
        a("app", this.o);
        com.chartboost.sdk.Libraries.e.a(this.p, "carrier", com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("carrier_name", this.m.v.optString("carrier-name")), com.chartboost.sdk.Libraries.e.a("mobile_country_code", this.m.v.optString("mobile-country-code")), com.chartboost.sdk.Libraries.e.a("mobile_network_code", this.m.v.optString("mobile-network-code")), com.chartboost.sdk.Libraries.e.a("iso_country_code", this.m.v.optString("iso-country-code")), com.chartboost.sdk.Libraries.e.a("phone_type", Integer.valueOf(this.m.v.optInt("phone-type")))));
        com.chartboost.sdk.Libraries.e.a(this.p, "model", this.m.f);
        com.chartboost.sdk.Libraries.e.a(this.p, "device_type", this.m.t);
        com.chartboost.sdk.Libraries.e.a(this.p, "actual_device_type", this.m.u);
        com.chartboost.sdk.Libraries.e.a(this.p, "os", this.m.g);
        com.chartboost.sdk.Libraries.e.a(this.p, UserDataStore.COUNTRY, this.m.h);
        com.chartboost.sdk.Libraries.e.a(this.p, "language", this.m.i);
        com.chartboost.sdk.Libraries.e.a(this.p, "timestamp", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(this.m.e.a())));
        com.chartboost.sdk.Libraries.e.a(this.p, "reachability", Integer.valueOf(this.m.b.a()));
        com.chartboost.sdk.Libraries.e.a(this.p, "scale", this.m.r);
        com.chartboost.sdk.Libraries.e.a(this.p, "is_portrait", Boolean.valueOf(CBUtility.a(CBUtility.a())));
        com.chartboost.sdk.Libraries.e.a(this.p, "rooted_device", Boolean.valueOf(this.m.w));
        com.chartboost.sdk.Libraries.e.a(this.p, com.my.target.i.K, this.m.x);
        com.chartboost.sdk.Libraries.e.a(this.p, "mobile_network", this.m.y);
        com.chartboost.sdk.Libraries.e.a(this.p, "dw", this.m.o);
        com.chartboost.sdk.Libraries.e.a(this.p, "dh", this.m.p);
        com.chartboost.sdk.Libraries.e.a(this.p, com.my.target.i.N, this.m.q);
        com.chartboost.sdk.Libraries.e.a(this.p, com.my.target.i.WIDTH, this.m.m);
        com.chartboost.sdk.Libraries.e.a(this.p, com.my.target.i.HEIGHT, this.m.n);
        com.chartboost.sdk.Libraries.e.a(this.p, "user_agent", com.chartboost.sdk.i.w);
        com.chartboost.sdk.Libraries.e.a(this.p, "device_family", "");
        com.chartboost.sdk.Libraries.e.a(this.p, "retina", false);
        com.chartboost.sdk.Libraries.d.a aVarA = this.m.a.a();
        com.chartboost.sdk.Libraries.e.a(this.p, "identity", aVarA.b);
        if (aVarA.a != -1) {
            com.chartboost.sdk.Libraries.e.a(this.p, "limit_ad_tracking", Boolean.valueOf(aVarA.a == 1));
        }
        com.chartboost.sdk.Libraries.e.a(this.p, "pidatauseconsent", Integer.valueOf(com.chartboost.sdk.i.x.getValue()));
        a("device", this.p);
        com.chartboost.sdk.Libraries.e.a(this.n, "framework", "");
        com.chartboost.sdk.Libraries.e.a(this.n, "sdk", this.m.l);
        if (com.chartboost.sdk.i.d != null) {
            com.chartboost.sdk.Libraries.e.a(this.n, "framework_version", com.chartboost.sdk.i.f);
            com.chartboost.sdk.Libraries.e.a(this.n, "wrapper_version", com.chartboost.sdk.i.b);
        }
        com.chartboost.sdk.Libraries.e.a(this.n, "mediation", com.chartboost.sdk.i.h);
        com.chartboost.sdk.Libraries.e.a(this.n, "commit_hash", "ea5c9878e5dca6c95016765177cbd146c39a21f7");
        String str = this.m.c.get().a;
        if (!s.a().a(str)) {
            com.chartboost.sdk.Libraries.e.a(this.n, "config_variant", str);
        }
        a("sdk", this.n);
        com.chartboost.sdk.Libraries.e.a(this.q, SettingsJsonConstants.SESSION_KEY, Integer.valueOf(this.m.d.getInt("cbPrefSessionCount", 0)));
        if (this.q.isNull("cache")) {
            com.chartboost.sdk.Libraries.e.a(this.q, "cache", false);
        }
        if (this.q.isNull(AppLovinEventParameters.REVENUE_AMOUNT)) {
            com.chartboost.sdk.Libraries.e.a(this.q, AppLovinEventParameters.REVENUE_AMOUNT, 0);
        }
        if (this.q.isNull("retry_count")) {
            com.chartboost.sdk.Libraries.e.a(this.q, "retry_count", 0);
        }
        if (this.q.isNull("location")) {
            com.chartboost.sdk.Libraries.e.a(this.q, "location", "");
        }
        a("ad", this.q);
    }

    public void a(String str, Object obj, int i) {
        if (i == 0) {
            com.chartboost.sdk.Libraries.e.a(this.q, str, obj);
            a("ad", this.q);
        }
    }
}
