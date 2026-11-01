package com.applovin.impl.mediation.c;

import android.app.Activity;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.g;
import com.applovin.impl.sdk.utils.k;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.applovin.impl.sdk.d.a {
    private final Activity a;

    public a(Activity activity, h hVar) {
        super("TaskAutoInitAdapters", hVar);
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        this.a = activity;
    }

    @Override // com.applovin.impl.sdk.d.a
    public i a() {
        return i.z;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        String str2 = (String) this.b.a(com.applovin.impl.sdk.b.d.s);
        if (!k.b(str2)) {
            a("No auto-init adapters provided", (Throwable) null);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            JSONArray jSONArrayA = g.a(jSONObject, "auto_init_adapters", (JSONArray) null, this.b);
            if (jSONArrayA.length() <= 0) {
                d("No auto-init adapters found");
                return;
            }
            a("Auto-initing " + jSONArrayA.length() + " adapter(s)...");
            ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(((Integer) this.b.a(com.applovin.impl.sdk.b.a.r)).intValue());
            for (int i = 0; i < jSONArrayA.length(); i++) {
                final com.applovin.impl.mediation.b.e eVar = new com.applovin.impl.mediation.b.e(jSONArrayA.getJSONObject(i), jSONObject, this.b);
                executorServiceNewFixedThreadPool.execute(new Runnable() { // from class: com.applovin.impl.mediation.c.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.a("Auto-initing adapter: " + eVar);
                        a.this.b.a(a.this.a).initializeAdapter(eVar, a.this.a);
                    }
                });
            }
        } catch (JSONException e) {
            e = e;
            str = "Failed to parse auto-init adapters JSON";
            a(str, e);
        } catch (Throwable th) {
            e = th;
            str = "Failed to auto-init adapters";
            a(str, e);
        }
    }
}
