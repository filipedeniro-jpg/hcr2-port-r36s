package com.applovin.impl.sdk.d;

import android.os.Build;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinWebViewActivity;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class k extends com.applovin.impl.sdk.d.a {
    private static int a;
    private final AtomicBoolean c;

    private class a extends com.applovin.impl.sdk.d.a {
        public a(com.applovin.impl.sdk.h hVar) {
            super("TaskTimeoutFetchBasicSettings", hVar, true);
        }

        @Override // com.applovin.impl.sdk.d.a
        public com.applovin.impl.sdk.c.i a() {
            return com.applovin.impl.sdk.c.i.g;
        }

        @Override // java.lang.Runnable
        public void run() {
            d("Timing out fetch basic settings...");
            k.this.a(new JSONObject());
        }
    }

    public k(com.applovin.impl.sdk.h hVar) {
        super("TaskFetchBasicSettings", hVar, true);
        this.c = new AtomicBoolean();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        if (this.c.compareAndSet(false, true)) {
            com.applovin.impl.sdk.utils.f.d(jSONObject, this.b);
            com.applovin.impl.sdk.utils.f.c(jSONObject, this.b);
            com.applovin.impl.mediation.d.b.a(jSONObject, this.b);
            com.applovin.impl.mediation.d.b.b(jSONObject, this.b);
            b("Executing initialize SDK...");
            this.b.x().a(com.applovin.impl.sdk.utils.g.a(jSONObject, "smd", (Boolean) false, this.b).booleanValue());
            this.b.F().a(new p(this.b));
            com.applovin.impl.sdk.utils.f.e(jSONObject, this.b);
            com.applovin.impl.sdk.utils.f.f(jSONObject, this.b);
            b("Finished executing initialize SDK");
        }
    }

    private String g() {
        return com.applovin.impl.sdk.utils.f.a((String) this.b.a(com.applovin.impl.sdk.b.b.aC), "4.0/i", c());
    }

    private String h() {
        return com.applovin.impl.sdk.utils.f.a((String) this.b.a(com.applovin.impl.sdk.b.b.aD), "4.0/i", c());
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.d;
    }

    protected Map<String, String> b() {
        HashMap map = new HashMap();
        map.put("sdk_version", AppLovinSdk.VERSION);
        map.put("build", String.valueOf(120));
        int i = a + 1;
        a = i;
        map.put("init_count", String.valueOf(i));
        map.put("server_installed_at", com.applovin.impl.sdk.utils.k.e((String) this.b.a(com.applovin.impl.sdk.b.b.Z)));
        com.applovin.impl.sdk.utils.n.a("first_install", Boolean.valueOf(this.b.D()), map);
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.eV)).booleanValue()) {
            map.put(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, this.b.u());
        }
        Boolean boolA = com.applovin.impl.sdk.e.a(e());
        if (boolA != null) {
            map.put("huc", boolA.toString());
        }
        Boolean boolB = com.applovin.impl.sdk.e.b(e());
        if (boolB != null) {
            map.put("aru", boolB.toString());
        }
        String str = (String) this.b.a(com.applovin.impl.sdk.b.b.eh);
        if (com.applovin.impl.sdk.utils.k.b(str)) {
            map.put("plugin_version", com.applovin.impl.sdk.utils.k.e(str));
        }
        String strO = this.b.o();
        if (com.applovin.impl.sdk.utils.k.b(strO)) {
            map.put("mediation_provider", com.applovin.impl.sdk.utils.k.e(strO));
        }
        com.applovin.impl.mediation.d.c.a aVarA = com.applovin.impl.mediation.d.c.a();
        map.put("installed_mediation_adapter_classnames", com.applovin.impl.sdk.utils.k.e(TextUtils.join(",", aVarA.a())));
        map.put("uninstalled_mediation_adapter_classnames", com.applovin.impl.sdk.utils.k.e(TextUtils.join(",", aVarA.b())));
        com.applovin.impl.sdk.i.b bVarC = this.b.J().c();
        map.put(InMobiNetworkValues.PACKAGE_NAME, com.applovin.impl.sdk.utils.k.e(bVarC.c));
        map.put("app_version", com.applovin.impl.sdk.utils.k.e(bVarC.b));
        map.put("platform", com.applovin.impl.sdk.utils.k.e(this.b.J().b()));
        map.put("os", com.applovin.impl.sdk.utils.k.e(Build.VERSION.RELEASE));
        map.put("tg", this.b.a(com.applovin.impl.sdk.b.d.c));
        return map;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.applovin.impl.sdk.network.b bVarA = com.applovin.impl.sdk.network.b.a(this.b).a(g()).c(h()).a(b()).b(HttpRequest.METHOD_GET).a(new JSONObject()).a(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dR)).intValue()).c(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dS)).intValue()).b(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dQ)).intValue()).a();
        this.b.F().a(new a(this.b), q.a.TIMEOUT, ((long) ((Integer) this.b.a(com.applovin.impl.sdk.b.b.dQ)).intValue()) + 250);
        w<JSONObject> wVar = new w<JSONObject>(bVarA, this.b, f()) { // from class: com.applovin.impl.sdk.d.k.1
            @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
            public void a(int i) {
                d("Unable to fetch basic SDK settings: server returned " + i);
                k.this.a(new JSONObject());
            }

            @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
            public void a(JSONObject jSONObject, int i) {
                k.this.a(jSONObject);
            }
        };
        wVar.a(com.applovin.impl.sdk.b.b.aE);
        wVar.b(com.applovin.impl.sdk.b.b.aF);
        this.b.F().a(wVar);
    }
}
