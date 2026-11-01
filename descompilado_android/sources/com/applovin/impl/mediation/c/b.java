package com.applovin.impl.mediation.c;

import android.app.Activity;
import android.text.TextUtils;
import com.applovin.impl.mediation.b.g;
import com.applovin.impl.sdk.c.i;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.k;
import com.facebook.internal.AnalyticsEvents;
import com.google.ads.mediation.unity.BuildConfig;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.applovin.impl.sdk.d.a {
    private static String a;
    private final Activity c;
    private final a d;

    public interface a {
        void a(JSONArray jSONArray);
    }

    static {
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(a("APPLOVIN_NETWORK", "com.applovin.mediation.adapters.AppLovinMediationAdapter"));
            a("FACEBOOK_NETWORK", "com.applovin.mediation.adapters.FacebookMediationAdapter").put("run_on_ui_thread", false);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("signal_providers", jSONArray);
            a = jSONObject.toString();
        } catch (JSONException unused) {
        }
    }

    public b(Activity activity, h hVar, a aVar) {
        super("TaskCollectSignals", hVar);
        if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        this.c = activity;
        this.d = aVar;
    }

    private String a(String str, com.applovin.impl.sdk.b.b<Integer> bVar) {
        int iIntValue;
        return (!TextUtils.isEmpty(str) && (iIntValue = ((Integer) this.b.a(bVar)).intValue()) > 0) ? str.substring(0, Math.min(str.length(), iIntValue)) : "";
    }

    private static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", str);
        jSONObject.put("class", str2);
        jSONObject.put("adapter_timeout_ms", BuildConfig.VERSION_CODE);
        jSONObject.put("max_signal_length", 32768);
        jSONObject.put("scode", "");
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final g gVar, final com.applovin.impl.mediation.b.f.a aVar) {
        Runnable runnable = new Runnable() { // from class: com.applovin.impl.mediation.c.b.2
            @Override // java.lang.Runnable
            public void run() {
                b.this.b.a(b.this.c).collectSignal(gVar, b.this.c, aVar);
            }
        };
        if (gVar.x()) {
            a("Running signal collection for " + gVar + " on the main thread");
            this.c.runOnUiThread(runnable);
            return;
        }
        a("Running signal collection for " + gVar + " on the background thread");
        runnable.run();
    }

    private void a(Collection<com.applovin.impl.mediation.b.f> collection) {
        String str;
        String strA;
        JSONArray jSONArray = new JSONArray();
        for (com.applovin.impl.mediation.b.f fVar : collection) {
            try {
                JSONObject jSONObject = new JSONObject();
                g gVarA = fVar.a();
                jSONObject.put("name", gVarA.v());
                jSONObject.put("class", gVarA.u());
                jSONObject.put("adapter_version", a(fVar.c(), com.applovin.impl.sdk.b.a.n));
                jSONObject.put("sdk_version", a(fVar.b(), com.applovin.impl.sdk.b.a.o));
                JSONObject jSONObject2 = new JSONObject();
                if (k.b(fVar.e())) {
                    str = AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE;
                    strA = fVar.e();
                } else {
                    str = "signal";
                    strA = a(fVar.d(), com.applovin.impl.sdk.b.a.p);
                }
                jSONObject2.put(str, strA);
                jSONObject.put("data", jSONObject2);
                jSONArray.put(jSONObject);
                a("Collected signal from " + gVarA);
            } catch (JSONException e) {
                a("Failed to create signal data", e);
            }
        }
        a(jSONArray);
    }

    private void a(JSONArray jSONArray) {
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(jSONArray);
        }
    }

    private void b(String str, Throwable th) {
        a("No signals collected: " + str, th);
        a(new JSONArray());
    }

    @Override // com.applovin.impl.sdk.d.a
    public i a() {
        return i.A;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        String str2 = (String) this.b.b(com.applovin.impl.sdk.b.d.r, a);
        if (!k.b(str2)) {
            b("No signal providers provided", null);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            JSONArray jSONArrayA = com.applovin.impl.sdk.utils.g.a(jSONObject, "signal_providers", (JSONArray) null, this.b);
            if (jSONArrayA.length() <= 0) {
                b("No signal providers found", null);
                return;
            }
            a("Collecting signals from " + jSONArrayA.length() + " signal providers(s)...");
            final List listA = com.applovin.impl.sdk.utils.d.a(jSONArrayA.length());
            final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            final CountDownLatch countDownLatch = new CountDownLatch(jSONArrayA.length());
            ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(((Integer) this.b.a(com.applovin.impl.sdk.b.a.k)).intValue());
            for (int i = 0; i < jSONArrayA.length(); i++) {
                final g gVar = new g(jSONArrayA.getJSONObject(i), jSONObject, this.b);
                executorServiceNewFixedThreadPool.execute(new Runnable() { // from class: com.applovin.impl.mediation.c.b.1
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.a(gVar, new com.applovin.impl.mediation.b.f.a() { // from class: com.applovin.impl.mediation.c.b.1.1
                            @Override // com.applovin.impl.mediation.b.f.a
                            public void a(com.applovin.impl.mediation.b.f fVar) {
                                if (atomicBoolean.get() && fVar != null) {
                                    listA.add(fVar);
                                }
                                countDownLatch.countDown();
                            }
                        });
                    }
                });
            }
            countDownLatch.await(((Long) this.b.a(com.applovin.impl.sdk.b.a.m)).longValue(), TimeUnit.MILLISECONDS);
            atomicBoolean.set(false);
            if (((Boolean) this.b.a(com.applovin.impl.sdk.b.a.l)).booleanValue()) {
                executorServiceNewFixedThreadPool.shutdown();
            }
            a(listA);
        } catch (InterruptedException e) {
            e = e;
            str = "Failed to wait for signals";
            b(str, e);
        } catch (JSONException e2) {
            e = e2;
            str = "Failed to parse signals JSON";
            b(str, e);
        } catch (Throwable th) {
            e = th;
            str = "Failed to collect signals";
            b(str, e);
        }
    }
}
