package com.adcolony.sdk;

import androidx.annotation.NonNull;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyCustomMessage {
    String a;
    String b;

    public AdColonyCustomMessage(@NonNull String str, @NonNull String str2) {
        if (aw.d(str) || aw.d(str2)) {
            this.a = str;
            this.b = str2;
        }
    }

    public String getMessage() {
        return this.b;
    }

    public String getType() {
        return this.a;
    }

    public AdColonyCustomMessage set(String str, String str2) {
        this.a = str;
        this.b = str2;
        return this;
    }

    public void send() {
        try {
            AdColony.a.execute(new Runnable() { // from class: com.adcolony.sdk.AdColonyCustomMessage.1
                @Override // java.lang.Runnable
                public void run() {
                    AdColony.a();
                    JSONObject jSONObjectA = y.a();
                    y.a(jSONObjectA, "type", AdColonyCustomMessage.this.a);
                    y.a(jSONObjectA, "message", AdColonyCustomMessage.this.b);
                    new af("CustomMessage.native_send", 1, jSONObjectA).b();
                }
            });
        } catch (RejectedExecutionException unused) {
        }
    }
}
