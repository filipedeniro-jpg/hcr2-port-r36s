package com.applovin.impl.mediation.a.b;

import android.os.Build;
import com.applovin.impl.sdk.b.b;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.d.w;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.k;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinWebViewActivity;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.applovin.impl.sdk.d.a {
    private final com.applovin.impl.sdk.network.a.b<JSONObject> a;

    public a(com.applovin.impl.sdk.network.a.b<JSONObject> bVar, h hVar) {
        super("TaskFetchMediationDebuggerInfo", hVar, true);
        this.a = bVar;
    }

    @Override // com.applovin.impl.sdk.d.a
    public i a() {
        return i.I;
    }

    protected Map<String, String> b() {
        HashMap map = new HashMap();
        map.put("sdk_version", AppLovinSdk.VERSION);
        map.put("build", String.valueOf(120));
        if (!((Boolean) this.b.a(b.eV)).booleanValue()) {
            map.put(AppLovinWebViewActivity.INTENT_EXTRA_KEY_SDK_KEY, this.b.u());
        }
        com.applovin.impl.sdk.i.b bVarC = this.b.J().c();
        map.put(InMobiNetworkValues.PACKAGE_NAME, k.e(bVarC.c));
        map.put("app_version", k.e(bVarC.b));
        map.put("platform", k.e(this.b.J().b()));
        map.put("os", k.e(Build.VERSION.RELEASE));
        return map;
    }

    @Override // java.lang.Runnable
    public void run() {
        w<JSONObject> wVar = new w<JSONObject>(com.applovin.impl.sdk.network.b.a(this.b).a(com.applovin.impl.mediation.d.b.c(this.b)).c(com.applovin.impl.mediation.d.b.d(this.b)).a(b()).b(HttpRequest.METHOD_GET).a(new JSONObject()).b(((Long) this.b.a(com.applovin.impl.sdk.b.a.f)).intValue()).a(), this.b, f()) { // from class: com.applovin.impl.mediation.a.b.a.1
            @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
            public void a(int i) {
                a.this.a.a(i);
            }

            @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
            public void a(JSONObject jSONObject, int i) {
                a.this.a.a(jSONObject, i);
            }
        };
        wVar.a(com.applovin.impl.sdk.b.a.a);
        wVar.b(com.applovin.impl.sdk.b.a.b);
        this.b.F().a(wVar);
    }
}
