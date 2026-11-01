package com.applovin.impl.sdk.d;

import com.applovin.sdk.AppLovinPostbackListener;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class j extends a {
    private final com.applovin.impl.sdk.network.f a;
    private final AppLovinPostbackListener c;
    private final q.a d;

    public j(com.applovin.impl.sdk.network.f fVar, q.a aVar, com.applovin.impl.sdk.h hVar, AppLovinPostbackListener appLovinPostbackListener) {
        super("TaskDispatchPostback", hVar);
        if (fVar == null) {
            throw new IllegalArgumentException("No request specified");
        }
        this.a = fVar;
        this.c = appLovinPostbackListener;
        this.d = aVar;
    }

    @Override // com.applovin.impl.sdk.d.a
    public com.applovin.impl.sdk.c.i a() {
        return com.applovin.impl.sdk.c.i.c;
    }

    @Override // java.lang.Runnable
    public void run() {
        final String strA = this.a.a();
        if (com.applovin.impl.sdk.utils.k.b(strA)) {
            w<JSONObject> wVar = new w<JSONObject>(this.a, c()) { // from class: com.applovin.impl.sdk.d.j.1
                @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
                public void a(int i) {
                    d("Failed to dispatch postback. Error code: " + i + " URL: " + strA);
                    if (j.this.c != null) {
                        j.this.c.onPostbackFailure(strA, i);
                    }
                }

                @Override // com.applovin.impl.sdk.d.w, com.applovin.impl.sdk.network.a.b
                public void a(JSONObject jSONObject, int i) {
                    a("Successfully dispatched postback to URL: " + strA);
                    if (j.this.c != null) {
                        j.this.c.onPostbackSuccess(strA);
                    }
                }
            };
            wVar.a(this.d);
            c().F().a(wVar);
        } else {
            b("Requested URL is not valid; nothing to do...");
            AppLovinPostbackListener appLovinPostbackListener = this.c;
            if (appLovinPostbackListener != null) {
                appLovinPostbackListener.onPostbackFailure(strA, -900);
            }
        }
    }
}
