package com.adcolony.sdk;

import android.util.Log;
import com.appsflyer.ServerParameters;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class al {
    static final String a = "adcolony_android";
    static final String b = "adcolony_fatal_reports";
    z c;
    ScheduledExecutorService d;
    HashMap<String, Object> g;
    List<ab> e = new ArrayList();
    List<ab> f = new ArrayList();
    private x h = new x(a, "3.3.5", "Production");
    private x i = new x(b, "3.3.5", "Production");

    al(z zVar, ScheduledExecutorService scheduledExecutorService, HashMap<String, Object> map) {
        this.c = zVar;
        this.d = scheduledExecutorService;
        this.g = map;
    }

    synchronized void a(String str) {
        this.g.put("controllerVersion", str);
    }

    synchronized void b(String str) {
        this.g.put("sessionId", str);
    }

    synchronized void a(long j, TimeUnit timeUnit) {
        try {
            if (!this.d.isShutdown() && !this.d.isTerminated()) {
                this.d.scheduleAtFixedRate(new Runnable() { // from class: com.adcolony.sdk.al.1
                    @Override // java.lang.Runnable
                    public void run() {
                        al.this.b();
                    }
                }, j, j, timeUnit);
            }
        } catch (RuntimeException unused) {
            Log.e("ADCLogError", "Internal error when submitting remote log to executor service");
        }
    }

    synchronized void a() {
        this.d.shutdown();
        try {
            if (!this.d.awaitTermination(1L, TimeUnit.SECONDS)) {
                this.d.shutdownNow();
                if (!this.d.awaitTermination(1L, TimeUnit.SECONDS)) {
                    System.err.println(getClass().getSimpleName() + ": ScheduledExecutorService did not terminate");
                }
            }
        } catch (InterruptedException unused) {
            this.d.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    synchronized void b() {
        synchronized (this) {
            try {
                if (this.e.size() > 0) {
                    this.c.a(a(this.h, this.e));
                    this.e.clear();
                }
                if (this.f.size() > 0) {
                    this.c.a(a(this.i, this.f));
                    this.f.clear();
                }
            } catch (IOException | JSONException unused) {
            }
        }
    }

    synchronized void c(String str) {
        a(new ab.a().a(3).a(this.h).a(str).a());
    }

    synchronized void d(String str) {
        a(new ab.a().a(2).a(this.h).a(str).a());
    }

    synchronized void e(String str) {
        a(new ab.a().a(1).a(this.h).a(str).a());
    }

    synchronized void f(String str) {
        a(new ab.a().a(0).a(this.h).a(str).a());
    }

    void a(s sVar) {
        sVar.a(this.i);
        sVar.a(-1);
        b(sVar);
    }

    synchronized void a(final ab abVar) {
        try {
            if (!this.d.isShutdown() && !this.d.isTerminated()) {
                this.d.submit(new Runnable() { // from class: com.adcolony.sdk.al.2
                    @Override // java.lang.Runnable
                    public void run() {
                        al.this.e.add(abVar);
                    }
                });
            }
        } catch (RejectedExecutionException unused) {
            Log.e("ADCLogError", "Internal error when submitting remote log to executor service");
        }
    }

    synchronized void b(ab abVar) {
        this.f.add(abVar);
    }

    String a(x xVar, List<ab> list) throws JSONException, IOException {
        String strC = a.a().c.c();
        String str = this.g.get(ServerParameters.ADVERTISING_ID_PARAM) != null ? (String) this.g.get(ServerParameters.ADVERTISING_ID_PARAM) : "unknown";
        if (strC != null && strC.length() > 0 && !strC.equals(str)) {
            this.g.put(ServerParameters.ADVERTISING_ID_PARAM, strC);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(FirebaseAnalytics.Param.INDEX, xVar.b());
        jSONObject.put("environment", xVar.d());
        jSONObject.put("version", xVar.c());
        JSONArray jSONArray = new JSONArray();
        Iterator<ab> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(c(it.next()));
        }
        jSONObject.put("logs", jSONArray);
        return jSONObject.toString();
    }

    private synchronized JSONObject c(ab abVar) throws JSONException {
        JSONObject jSONObject;
        jSONObject = new JSONObject(this.g);
        jSONObject.put("environment", abVar.f().d());
        jSONObject.put("level", abVar.b());
        jSONObject.put("message", abVar.d());
        jSONObject.put("clientTimestamp", abVar.e());
        JSONObject mediationInfo = a.a().d().getMediationInfo();
        JSONObject pluginInfo = a.a().d().getPluginInfo();
        double dA = a.a().n().a(a.c());
        jSONObject.put("mediation_network", y.b(mediationInfo, "name"));
        jSONObject.put("mediation_network_version", y.b(mediationInfo, "version"));
        jSONObject.put("plugin", y.b(pluginInfo, "name"));
        jSONObject.put("plugin_version", y.b(pluginInfo, "version"));
        jSONObject.put("batteryInfo", dA);
        if (abVar instanceof s) {
            jSONObject = y.a(jSONObject, ((s) abVar).a());
            jSONObject.put("platform", "android");
        }
        return jSONObject;
    }
}
