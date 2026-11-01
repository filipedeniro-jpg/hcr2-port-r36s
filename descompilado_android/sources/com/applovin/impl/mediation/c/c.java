package com.applovin.impl.mediation.c;

import android.app.Activity;
import android.graphics.Point;
import android.text.TextUtils;
import com.applovin.impl.sdk.c.g;
import com.applovin.impl.sdk.d.w;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.i;
import com.applovin.impl.sdk.utils.k;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinWebViewActivity;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.facebook.internal.NativeProtocol;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c extends com.applovin.impl.sdk.d.a {
    private final String a;
    private final MaxAdFormat c;
    private final com.applovin.impl.mediation.f d;
    private final Activity e;
    private final MaxAdListener f;
    private JSONArray g;

    public c(String str, MaxAdFormat maxAdFormat, com.applovin.impl.mediation.f fVar, Activity activity, h hVar, MaxAdListener maxAdListener) {
        super("TaskFetchMediatedAd " + str, hVar);
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("No ad unit ID specified");
        }
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        if (maxAdListener == null) {
            throw new IllegalArgumentException("No listener specified");
        }
        this.a = str;
        this.c = maxAdFormat;
        this.d = fVar;
        this.e = activity;
        this.f = maxAdListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        boolean z = i != 204;
        this.b.w().a(d(), Boolean.valueOf(z), "Unable to fetch " + this.a + " ad: server returned " + i);
        b(i);
    }

    private void a(com.applovin.impl.sdk.c.h hVar) {
        long jB = hVar.b(g.c);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - jB > TimeUnit.MINUTES.toMillis(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dW)).intValue())) {
            hVar.b(g.c, jCurrentTimeMillis);
            hVar.c(g.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        try {
            com.applovin.impl.sdk.utils.f.d(jSONObject, this.b);
            com.applovin.impl.sdk.utils.f.c(jSONObject, this.b);
            com.applovin.impl.sdk.utils.f.f(jSONObject, this.b);
            com.applovin.impl.mediation.d.b.a(jSONObject, this.b);
            com.applovin.impl.mediation.d.b.b(jSONObject, this.b);
            this.b.i();
            f fVarB = b(jSONObject);
            if (((Boolean) this.b.a(com.applovin.impl.sdk.b.a.h)).booleanValue()) {
                this.b.F().a(fVarB);
            } else {
                this.b.F().a(fVarB, com.applovin.impl.mediation.d.c.a(this.c, this.b));
            }
        } catch (Throwable th) {
            a("Unable to process mediated ad response", th);
            b(-800);
        }
    }

    private f b(JSONObject jSONObject) {
        return new f(this.a, this.c, jSONObject, this.d, this.e, this.b, this.f);
    }

    private String b() {
        return com.applovin.impl.mediation.d.b.a(this.b);
    }

    private void b(int i) {
        com.applovin.impl.sdk.utils.h.a(this.f, this.a, i, this.b);
    }

    private void c(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(Constants.ParametersKeys.LOADED, new JSONArray((Collection) this.b.a(this.e).getLoadedAdapterClassnames()));
            jSONObject2.put(Constants.ParametersKeys.FAILED, new JSONArray((Collection) this.b.a(this.e).getFailedAdapterClassnames()));
            jSONObject.put("classname_info", jSONObject2);
            jSONObject.put("initialized_adapter_classnames", new JSONArray((Collection) this.b.a(this.e).getInitializedAdapterNames()));
        } catch (Exception e) {
            a("Failed to populate adapter classnames", e);
        }
    }

    private void d(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = this.g;
        if (jSONArray != null) {
            jSONObject.put("signal_data", jSONArray);
        }
    }

    private void e(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("ad_unit_id", this.a);
        jSONObject2.put("ad_format", com.applovin.impl.mediation.d.c.a(this.c));
        if (this.d != null && ((Boolean) this.b.a(com.applovin.impl.sdk.b.a.g)).booleanValue()) {
            jSONObject2.put("extra_parameters", com.applovin.impl.sdk.utils.g.a((Map<String, ?>) com.applovin.impl.sdk.utils.g.a(this.d.a())));
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ac)).booleanValue()) {
            jSONObject2.put("n", String.valueOf(this.b.W().a(this.a)));
        }
        jSONObject.put("ad_info", jSONObject2);
    }

    private void f(JSONObject jSONObject) throws JSONException {
        i iVarJ = this.b.J();
        i.d dVarA = iVarJ.a();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("brand", dVarA.d);
        jSONObject2.put("brand_name", dVarA.e);
        jSONObject2.put("hardware", dVarA.f);
        jSONObject2.put("api_level", dVarA.h);
        jSONObject2.put("carrier", dVarA.j);
        jSONObject2.put("country_code", dVarA.i);
        jSONObject2.put("locale", dVarA.k);
        jSONObject2.put("model", dVarA.a);
        jSONObject2.put("os", dVarA.b);
        jSONObject2.put("platform", dVarA.c);
        jSONObject2.put("revision", dVarA.g);
        jSONObject2.put("orientation_lock", dVarA.l);
        jSONObject2.put("tz_offset", dVarA.o);
        jSONObject2.put("wvvc", dVarA.p);
        jSONObject2.put("adns", dVarA.m);
        jSONObject2.put("adnsd", dVarA.n);
        jSONObject2.put("sim", k.a(dVarA.u));
        jSONObject2.put("gy", k.a(dVarA.v));
        jSONObject2.put("tv", k.a(dVarA.w));
        jSONObject2.put("fs", dVarA.y);
        jSONObject2.put("fm", dVarA.z.b);
        jSONObject2.put("tm", dVarA.z.a);
        jSONObject2.put("lmt", dVarA.z.c);
        jSONObject2.put("lm", dVarA.z.d);
        jSONObject2.put("adr", k.a(dVarA.q));
        jSONObject2.put("volume", dVarA.s);
        jSONObject2.put("network", com.applovin.impl.sdk.utils.f.f(this.b));
        if (k.b(dVarA.t)) {
            jSONObject2.put("ua", dVarA.t);
        }
        if (k.b(dVarA.x)) {
            jSONObject2.put("so", dVarA.x);
        }
        i.c cVar = dVarA.r;
        if (cVar != null) {
            jSONObject2.put("act", cVar.a);
            jSONObject2.put("acm", cVar.b);
        }
        Boolean bool = dVarA.A;
        if (bool != null) {
            jSONObject2.put("huc", bool.toString());
        }
        Boolean bool2 = dVarA.B;
        if (bool2 != null) {
            jSONObject2.put("aru", bool2.toString());
        }
        Point pointA = com.applovin.impl.sdk.utils.e.a(e());
        jSONObject2.put("dx", Integer.toString(pointA.x));
        jSONObject2.put("dy", Integer.toString(pointA.y));
        g(jSONObject2);
        jSONObject.put(DeviceRequestsHelper.DEVICE_INFO_PARAM, jSONObject2);
        i.b bVarC = iVarJ.c();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(InMobiNetworkValues.PACKAGE_NAME, bVarC.c);
        jSONObject3.put("installer_name", bVarC.d);
        jSONObject3.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, bVarC.a);
        jSONObject3.put("app_version", bVarC.b);
        jSONObject3.put("installed_at", bVarC.f);
        jSONObject3.put("tg", bVarC.e);
        jSONObject3.put("api_did", this.b.a(com.applovin.impl.sdk.b.b.U));
        jSONObject3.put("sdk_version", AppLovinSdk.VERSION);
        jSONObject3.put("build", 120);
        jSONObject3.put("test_ads", this.b.m().isTestAdsEnabled());
        jSONObject3.put("first_install", String.valueOf(this.b.D()));
        String strL = this.b.l();
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ee)).booleanValue() && k.b(strL)) {
            jSONObject3.put("cuid", strL);
        }
        String str = (String) this.b.a(com.applovin.impl.sdk.b.b.eh);
        if (k.b(str)) {
            jSONObject3.put("plugin_version", str);
        }
        jSONObject.put("app_info", jSONObject3);
    }

    private String g() {
        return com.applovin.impl.mediation.d.b.b(this.b);
    }

    private void g(JSONObject jSONObject) {
        try {
            i.a aVarD = this.b.J().d();
            String str = aVarD.b;
            if (k.b(str)) {
                jSONObject.put("idfa", str);
            }
            jSONObject.put("dnt", aVarD.a);
        } catch (Throwable th) {
            a("Failed to populate advertising info", th);
        }
    }

    private JSONObject h() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        e(jSONObject);
        f(jSONObject);
        d(jSONObject);
        c(jSONObject);
        jSONObject.put("sc", k.e((String) this.b.a(com.applovin.impl.sdk.b.b.X)));
        jSONObject.put("sc2", k.e((String) this.b.a(com.applovin.impl.sdk.b.b.Y)));
        jSONObject.put("server_installed_at", k.e((String) this.b.a(com.applovin.impl.sdk.b.b.Z)));
        String str = (String) this.b.a(com.applovin.impl.sdk.b.d.t);
        if (k.b(str)) {
            jSONObject.put("persisted_data", k.e(str));
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eA)).booleanValue()) {
            h(jSONObject);
        }
        if (this.b.j()) {
            jSONObject.put("pnr", Boolean.toString(this.b.k()));
        }
        jSONObject.put("mediation_provider", this.b.o());
        return jSONObject;
    }

    private void h(JSONObject jSONObject) {
        try {
            com.applovin.impl.sdk.c.h hVarG = this.b.G();
            jSONObject.put("li", String.valueOf(hVarG.b(g.b)));
            jSONObject.put("si", String.valueOf(hVarG.b(g.d)));
            jSONObject.put("pf", String.valueOf(hVarG.b(g.h)));
            jSONObject.put("mpf", String.valueOf(hVarG.b(g.r)));
            jSONObject.put("gpf", String.valueOf(hVarG.b(g.i)));
        } catch (Throwable th) {
            a("Failed to populate ad serving info", th);
        }
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.B;
    }

    public void a(JSONArray jSONArray) {
        this.g = jSONArray;
    }

    @Override // java.lang.Runnable
    public void run() {
        a("Fetching next ad for ad unit id: " + this.a + " and format: " + this.c);
        com.applovin.impl.sdk.c.h hVarG = this.b.G();
        hVarG.a(g.q);
        if (hVarG.b(g.c) == 0) {
            hVarG.b(g.c, System.currentTimeMillis());
        }
        try {
            JSONObject jSONObjectH = h();
            HashMap map = new HashMap();
            map.put("rid", UUID.randomUUID().toString());
            if (jSONObjectH.has("huc")) {
                map.put("huc", String.valueOf(com.applovin.impl.sdk.utils.g.a(jSONObjectH, "huc", (Boolean) false, this.b)));
            }
            if (jSONObjectH.has("aru")) {
                map.put("aru", String.valueOf(com.applovin.impl.sdk.utils.g.a(jSONObjectH, "aru", (Boolean) false, this.b)));
            }
            if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eV)).booleanValue()) {
                map.put(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, this.b.u());
            }
            a(hVarG);
            w<JSONObject> wVar = new w<JSONObject>(com.applovin.impl.sdk.network.b.a(this.b).b(HttpRequest.METHOD_POST).a(b()).c(g()).a((Map<String, String>) map).a(jSONObjectH).a(new JSONObject()).b(((Long) this.b.a(com.applovin.impl.sdk.b.a.e)).intValue()).a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dM)).intValue()).c(((Long) this.b.a(com.applovin.impl.sdk.b.a.d)).intValue()).a(), this.b) { // from class: com.applovin.impl.mediation.c.c.1
                @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
                public void a(int i) {
                    c.this.a(i);
                }

                @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
                public void a(JSONObject jSONObject, int i) {
                    if (i != 200) {
                        c.this.a(i);
                        return;
                    }
                    com.applovin.impl.sdk.utils.g.b(jSONObject, "ad_fetch_latency_millis", this.d.a(), this.b);
                    com.applovin.impl.sdk.utils.g.b(jSONObject, "ad_fetch_response_size", this.d.b(), this.b);
                    c.this.a(jSONObject);
                }
            };
            wVar.a(com.applovin.impl.sdk.b.a.a);
            wVar.b(com.applovin.impl.sdk.b.a.b);
            this.b.F().a(wVar);
        } catch (Throwable th) {
            a("Unable to fetch ad " + this.a, th);
            a(0);
            this.b.H().a(a());
        }
    }
}
