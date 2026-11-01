package com.applovin.impl.sdk.d;

import android.text.TextUtils;
import android.util.Log;
import com.applovin.sdk.AppLovinSdk;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.facebook.internal.NativeProtocol;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class b extends a {
    b(com.applovin.impl.sdk.h hVar) {
        super("TaskApiSubmitData", hVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        try {
            this.b.K().c();
            JSONObject jSONObjectA = com.applovin.impl.sdk.utils.f.a(jSONObject);
            com.applovin.impl.sdk.b.c cVarY = this.b.y();
            cVarY.a(com.applovin.impl.sdk.b.b.U, jSONObjectA.getString("device_id"));
            cVarY.a(com.applovin.impl.sdk.b.b.V, jSONObjectA.getString("device_token"));
            cVarY.a();
            com.applovin.impl.sdk.utils.f.d(jSONObjectA, this.b);
            this.b.i();
            com.applovin.impl.sdk.utils.f.e(jSONObjectA, this.b);
            String strA = com.applovin.impl.sdk.utils.g.a(jSONObjectA, "latest_version", "", this.b);
            if (e(strA)) {
                String strA2 = "Current SDK version (9.4.2) is outdated. Please integrate the latest version of the AppLovin SDK (" + strA + "). Doing so will improve your CPMs and ensure you have access to the latest revenue earning features.";
                if (com.applovin.impl.sdk.utils.g.a(jSONObjectA, "sdk_update_message")) {
                    strA2 = com.applovin.impl.sdk.utils.g.a(jSONObjectA, "sdk_update_message", strA2, this.b);
                }
                Log.w("AppLovinSdk", strA2);
            }
            this.b.G().b();
            this.b.H().b();
        } catch (Throwable th) {
            a("Unable to parse API response", th);
        }
    }

    private void b(JSONObject jSONObject) throws JSONException {
        com.applovin.impl.sdk.i iVarJ = this.b.J();
        com.applovin.impl.sdk.i.b bVarC = iVarJ.c();
        com.applovin.impl.sdk.i.d dVarA = iVarJ.a();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("model", dVarA.a);
        jSONObject2.put("os", dVarA.b);
        jSONObject2.put("brand", dVarA.d);
        jSONObject2.put("brand_name", dVarA.e);
        jSONObject2.put("hardware", dVarA.f);
        jSONObject2.put("sdk_version", dVarA.h);
        jSONObject2.put("revision", dVarA.g);
        jSONObject2.put("adns", dVarA.m);
        jSONObject2.put("adnsd", dVarA.n);
        jSONObject2.put("gy", com.applovin.impl.sdk.utils.k.a(dVarA.v));
        jSONObject2.put("country_code", dVarA.i);
        jSONObject2.put("carrier", dVarA.j);
        jSONObject2.put("orientation_lock", dVarA.l);
        jSONObject2.put("tz_offset", dVarA.o);
        jSONObject2.put("adr", com.applovin.impl.sdk.utils.k.a(dVarA.q));
        jSONObject2.put("wvvc", dVarA.p);
        jSONObject2.put("volume", dVarA.s);
        jSONObject2.put("type", "android");
        jSONObject2.put("sim", com.applovin.impl.sdk.utils.k.a(dVarA.u));
        jSONObject2.put("tv", com.applovin.impl.sdk.utils.k.a(dVarA.w));
        jSONObject2.put("fs", dVarA.y);
        jSONObject2.put("fm", String.valueOf(dVarA.z.b));
        jSONObject2.put("tm", String.valueOf(dVarA.z.a));
        jSONObject2.put("lmt", String.valueOf(dVarA.z.c));
        jSONObject2.put("lm", String.valueOf(dVarA.z.d));
        g(jSONObject2);
        String strL = this.b.l();
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ee)).booleanValue() && com.applovin.impl.sdk.utils.k.b(strL)) {
            jSONObject2.put("cuid", strL);
        }
        Boolean bool = dVarA.A;
        if (bool != null) {
            jSONObject2.put("huc", bool.toString());
        }
        Boolean bool2 = dVarA.B;
        if (bool2 != null) {
            jSONObject2.put("aru", bool2.toString());
        }
        com.applovin.impl.sdk.i.c cVar = dVarA.r;
        if (cVar != null) {
            jSONObject2.put("act", cVar.a);
            jSONObject2.put("acm", cVar.b);
        }
        String str = dVarA.t;
        if (com.applovin.impl.sdk.utils.k.b(str)) {
            jSONObject2.put("ua", com.applovin.impl.sdk.utils.k.e(str));
        }
        String str2 = dVarA.x;
        if (!TextUtils.isEmpty(str2)) {
            jSONObject2.put("so", com.applovin.impl.sdk.utils.k.e(str2));
        }
        Locale locale = dVarA.k;
        if (locale != null) {
            jSONObject2.put("locale", com.applovin.impl.sdk.utils.k.e(locale.toString()));
        }
        jSONObject.put(DeviceRequestsHelper.DEVICE_INFO_PARAM, jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(InMobiNetworkValues.PACKAGE_NAME, bVarC.c);
        jSONObject3.put("installer_name", bVarC.d);
        jSONObject3.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, bVarC.a);
        jSONObject3.put("app_version", bVarC.b);
        jSONObject3.put("installed_at", bVarC.f);
        jSONObject3.put("tg", bVarC.e);
        jSONObject3.put("applovin_sdk_version", AppLovinSdk.VERSION);
        jSONObject3.put("first_install", String.valueOf(this.b.D()));
        String str3 = (String) this.b.a(com.applovin.impl.sdk.b.b.eh);
        if (com.applovin.impl.sdk.utils.k.b(str3)) {
            jSONObject3.put("plugin_version", str3);
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ee)).booleanValue() && com.applovin.impl.sdk.utils.k.b(strL)) {
            jSONObject3.put("cuid", strL);
        }
        jSONObject.put("app_info", jSONObject3);
    }

    private void c(JSONObject jSONObject) throws JSONException {
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eA)).booleanValue()) {
            jSONObject.put("stats", this.b.G().c());
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ag)).booleanValue()) {
            JSONObject jSONObjectB = com.applovin.impl.sdk.network.c.b(e());
            if (jSONObjectB.length() > 0) {
                jSONObject.put("network_response_codes", jSONObjectB);
            }
            if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ah)).booleanValue()) {
                com.applovin.impl.sdk.network.c.a(e());
            }
        }
    }

    private void d(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArrayA;
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eH)).booleanValue() || (jSONArrayA = this.b.K().a()) == null || jSONArrayA.length() <= 0) {
            return;
        }
        jSONObject.put("errors", jSONArrayA);
    }

    private void e(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArrayA;
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eG)).booleanValue() || (jSONArrayA = this.b.H().a()) == null || jSONArrayA.length() <= 0) {
            return;
        }
        jSONObject.put("tasks", jSONArrayA);
    }

    private boolean e(String str) {
        try {
            if (com.applovin.impl.sdk.utils.k.b(str) && !AppLovinSdk.VERSION.equals(str)) {
                List<String> listA = com.applovin.impl.sdk.utils.d.a(str, "\\.");
                List<String> listA2 = com.applovin.impl.sdk.utils.d.a(AppLovinSdk.VERSION, "\\.");
                if (listA.size() == 3 && listA2.size() == 3) {
                    for (int i = 0; i < 3; i++) {
                        int i2 = Integer.parseInt(listA2.get(i));
                        int i3 = Integer.parseInt(listA.get(i));
                        if (i2 < i3) {
                            return true;
                        }
                        if (i2 > i3) {
                            return false;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            a("Encountered exception while checking if current version is outdated", th);
        }
        return false;
    }

    private void f(JSONObject jSONObject) {
        w<JSONObject> wVar = new w<JSONObject>(com.applovin.impl.sdk.network.b.a(this.b).a(com.applovin.impl.sdk.utils.f.a("2.0/device", this.b)).c(com.applovin.impl.sdk.utils.f.b("2.0/device", this.b)).a(com.applovin.impl.sdk.utils.f.e(this.b)).b(HttpRequest.METHOD_POST).a(jSONObject).a(new JSONObject()).a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dO)).intValue()).a(), this.b) { // from class: com.applovin.impl.sdk.d.b.1
            @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
            public void a(int i) {
                com.applovin.impl.sdk.utils.f.a(i, this.b);
            }

            @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
            public void a(JSONObject jSONObject2, int i) {
                b.this.a(jSONObject2);
            }
        };
        wVar.a(com.applovin.impl.sdk.b.b.aG);
        wVar.b(com.applovin.impl.sdk.b.b.aH);
        this.b.F().a(wVar);
    }

    private void g(JSONObject jSONObject) {
        try {
            com.applovin.impl.sdk.i.a aVarD = this.b.J().d();
            String str = aVarD.b;
            if (com.applovin.impl.sdk.utils.k.b(str)) {
                jSONObject.put("idfa", str);
            }
            jSONObject.put("dnt", Boolean.toString(aVarD.a));
        } catch (Throwable th) {
            a("Failed to populate advertising info", th);
        }
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.h;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            b("Submitting user data...");
            JSONObject jSONObject = new JSONObject();
            b(jSONObject);
            c(jSONObject);
            d(jSONObject);
            e(jSONObject);
            f(jSONObject);
        } catch (JSONException e) {
            a("Unable to build JSON message with collected data", e);
            this.b.H().a(a());
        }
    }
}
