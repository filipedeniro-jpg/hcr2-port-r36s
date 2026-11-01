package com.adcolony.sdk;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class m implements ah {
    m() {
        a.a("CustomMessage.controller_send", this);
    }

    @Override // com.adcolony.sdk.ah
    public void a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        final String strB = y.b(jSONObjectC, "type");
        final String strB2 = y.b(jSONObjectC, "message");
        aw.a(new Runnable() { // from class: com.adcolony.sdk.m.1
            @Override // java.lang.Runnable
            public void run() {
                new aa.a().a("Received custom message ").a(strB2).a(" of type ").a(strB).a(aa.d);
                try {
                    AdColonyCustomMessageListener adColonyCustomMessageListener = a.a().z().get(strB);
                    if (adColonyCustomMessageListener != null) {
                        adColonyCustomMessageListener.onAdColonyCustomMessage(new AdColonyCustomMessage(strB, strB2));
                    }
                } catch (RuntimeException unused) {
                }
            }
        });
    }
}
