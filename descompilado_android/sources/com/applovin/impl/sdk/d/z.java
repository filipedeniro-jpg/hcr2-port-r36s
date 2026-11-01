package com.applovin.impl.sdk.d;

import io.fabric.sdk.android.services.network.HttpRequest;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
abstract class z extends a {
    z(String str, com.applovin.impl.sdk.h hVar) {
        super(str, hVar);
    }

    void a(String str, JSONObject jSONObject, int i, final com.applovin.impl.sdk.network.a.b bVar) {
        w<JSONObject> wVar = new w<JSONObject>(com.applovin.impl.sdk.network.b.a(this.b).a(com.applovin.impl.sdk.utils.f.a(str, this.b)).c(com.applovin.impl.sdk.utils.f.b(str, this.b)).a(com.applovin.impl.sdk.utils.f.e(this.b)).b(HttpRequest.METHOD_POST).a(jSONObject).a(new JSONObject()).a(i).a(), this.b) { // from class: com.applovin.impl.sdk.d.z.1
            @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
            public void a(int i2) {
                bVar.a(i2);
            }

            @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
            public void a(JSONObject jSONObject2, int i2) {
                bVar.a(jSONObject2, i2);
            }
        };
        wVar.a(com.applovin.impl.sdk.b.b.aG);
        wVar.b(com.applovin.impl.sdk.b.b.aH);
        this.b.F().a(wVar);
    }
}
