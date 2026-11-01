package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.webkit.WebView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.ai;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.ak;
import com.chartboost.sdk.impl.al;
import com.chartboost.sdk.impl.ao;
import com.chartboost.sdk.impl.ap;
import com.chartboost.sdk.impl.aw;
import com.chartboost.sdk.impl.e.a;
import com.chartboost.sdk.impl.l;
import com.chartboost.sdk.impl.m;
import com.chartboost.sdk.impl.o;
import com.chartboost.sdk.impl.s;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class h {
    private static h v;
    public final Executor a;
    final l b;
    public final com.chartboost.sdk.Libraries.d c;
    public final com.chartboost.sdk.impl.e d;
    public final com.chartboost.sdk.impl.c e;
    final com.chartboost.sdk.impl.e f;
    final com.chartboost.sdk.impl.c g;
    public final ah h;
    final m i;
    public final ap j;
    final com.chartboost.sdk.impl.e k;
    final com.chartboost.sdk.impl.c l;
    public final AtomicReference<com.chartboost.sdk.Model.e> m;
    final SharedPreferences n;
    public final com.chartboost.sdk.Tracking.a o;
    public final Handler p;
    public final c q;
    public final ak r;
    boolean s = true;
    boolean t = false;
    boolean u = true;
    private final s w;

    public static h a() {
        return v;
    }

    static void a(h hVar) {
        v = hVar;
    }

    h(Activity activity, String str, String str2, s sVar, ScheduledExecutorService scheduledExecutorService, Handler handler, Executor executor) {
        JSONObject jSONObject;
        g gVarA = g.a();
        Context applicationContext = activity.getApplicationContext();
        this.c = (com.chartboost.sdk.Libraries.d) gVarA.a(new com.chartboost.sdk.Libraries.d(applicationContext));
        ai aiVar = (ai) gVarA.a(new ai());
        com.chartboost.sdk.Libraries.i iVar = (com.chartboost.sdk.Libraries.i) gVarA.a(new com.chartboost.sdk.Libraries.i());
        this.h = (ah) gVarA.a(new ah(scheduledExecutorService, (ao) gVarA.a(new ao()), aiVar, iVar, handler, executor));
        SharedPreferences sharedPreferencesA = a(applicationContext);
        try {
            jSONObject = new JSONObject(sharedPreferencesA.getString("config", "{}"));
        } catch (Exception e) {
            CBLogging.b("Sdk", "Unable to process config");
            e.printStackTrace();
            jSONObject = new JSONObject();
        }
        AtomicReference<com.chartboost.sdk.Model.e> atomicReference = new AtomicReference<>(null);
        if (!b.a(atomicReference, jSONObject, sharedPreferencesA)) {
            atomicReference.set(new com.chartboost.sdk.Model.e(new JSONObject()));
        }
        this.w = sVar;
        this.a = scheduledExecutorService;
        this.m = atomicReference;
        this.n = sharedPreferencesA;
        this.p = handler;
        com.chartboost.sdk.Libraries.f fVar = new com.chartboost.sdk.Libraries.f(sVar, applicationContext, atomicReference);
        if (!atomicReference.get().y) {
            i.w = "";
        } else {
            a(applicationContext, null, sharedPreferencesA);
        }
        this.j = (ap) gVarA.a(new ap(applicationContext, str, this.c, aiVar, atomicReference, sharedPreferencesA, iVar));
        this.o = (com.chartboost.sdk.Tracking.a) gVarA.a(new com.chartboost.sdk.Tracking.a(atomicReference));
        this.b = (l) gVarA.a(new l(scheduledExecutorService, fVar, this.h, aiVar, atomicReference, iVar, this.o));
        d dVar = (d) gVarA.a(new d((aw) g.a().a(new aw(handler)), this.b, atomicReference, handler));
        this.r = (ak) gVarA.a(new ak(scheduledExecutorService, this.h, aiVar, handler));
        this.q = (c) gVarA.a(new c(activity, aiVar, this, this.o, handler, dVar));
        al alVar = (al) gVarA.a(new al(fVar));
        this.e = com.chartboost.sdk.impl.c.c();
        this.g = com.chartboost.sdk.impl.c.a();
        this.l = com.chartboost.sdk.impl.c.b();
        this.d = (com.chartboost.sdk.impl.e) gVarA.a(new com.chartboost.sdk.impl.e(this.e, scheduledExecutorService, this.b, fVar, this.h, aiVar, this.j, atomicReference, sharedPreferencesA, iVar, this.o, handler, this.q, this.r, dVar, alVar));
        this.f = (com.chartboost.sdk.impl.e) gVarA.a(new com.chartboost.sdk.impl.e(this.g, scheduledExecutorService, this.b, fVar, this.h, aiVar, this.j, atomicReference, sharedPreferencesA, iVar, this.o, handler, this.q, this.r, dVar, alVar));
        this.k = (com.chartboost.sdk.impl.e) gVarA.a(new com.chartboost.sdk.impl.e(this.l, scheduledExecutorService, this.b, fVar, this.h, aiVar, this.j, atomicReference, sharedPreferencesA, iVar, this.o, handler, this.q, this.r, dVar, alVar));
        this.i = (m) gVarA.a(new m(this.b, fVar, this.h, this.j, this.o, atomicReference));
        i.m = applicationContext;
        i.k = str;
        i.l = str2;
        if (sharedPreferencesA.contains("cbLimitTrack") && !sharedPreferencesA.contains("cbGDPR")) {
            i.x = sharedPreferencesA.getBoolean("cbLimitTrack", false) ? Chartboost.CBPIDataUseConsent.NO_BEHAVIORAL : Chartboost.CBPIDataUseConsent.UNKNOWN;
        } else {
            i.x = Chartboost.CBPIDataUseConsent.valueOf(sharedPreferencesA.getInt("cbGDPR", i.x.getValue()));
        }
        aiVar.a(i.m);
        if (s.a().a(19)) {
            o.a(activity.getApplication(), atomicReference.get().J, !atomicReference.get().K, !atomicReference.get().L);
        }
    }

    private static SharedPreferences a(Context context) {
        return context.getSharedPreferences("cbPrefs", 0);
    }

    void a(final Runnable runnable) {
        this.s = true;
        aj ajVar = new aj("/api/config", this.j, this.o, 1, new aj.a() { // from class: com.chartboost.sdk.h.1
            @Override // com.chartboost.sdk.impl.aj.a
            public void a(aj ajVar2, JSONObject jSONObject) {
                h.this.s = false;
                JSONObject jSONObjectA = com.chartboost.sdk.Libraries.e.a(jSONObject, ServerResponseWrapper.RESPONSE_FIELD);
                if (jSONObjectA != null && b.a(h.this.m, jSONObjectA, h.this.n)) {
                    h.this.n.edit().putString("config", jSONObjectA.toString()).apply();
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
                if (h.this.t) {
                    return;
                }
                com.chartboost.sdk.a aVar = i.c;
                if (aVar != null) {
                    aVar.didInitialize();
                }
                h.this.t = true;
            }

            @Override // com.chartboost.sdk.impl.aj.a
            public void a(aj ajVar2, CBError cBError) {
                h.this.s = false;
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
                if (h.this.t) {
                    return;
                }
                com.chartboost.sdk.a aVar = i.c;
                if (aVar != null) {
                    aVar.didInitialize();
                }
                h.this.t = true;
            }
        });
        ajVar.l = true;
        this.h.a(ajVar);
    }

    void a(Activity activity) {
        if (this.w.a(23)) {
            b.a((Context) activity);
        }
        if (this.u || this.q.e()) {
            return;
        }
        this.b.c();
    }

    void b() {
        if (i.m == null) {
            CBLogging.b("Sdk", "The context must be set through the Chartboost method onCreate() before calling startSession().");
        } else {
            g();
        }
    }

    void c() {
        this.p.postDelayed(new a(0), 500L);
    }

    private void g() {
        this.o.a();
        if (this.u) {
            return;
        }
        a(new a(3));
    }

    void d() {
        this.o.b();
    }

    void e() {
        if (this.t) {
            return;
        }
        if (i.c != null) {
            i.c.didInitialize();
        }
        this.t = true;
    }

    public static void b(Runnable runnable) {
        s sVarA = s.a();
        if (!sVarA.e()) {
            sVarA.a.post(runnable);
        } else {
            runnable.run();
        }
    }

    static boolean f() {
        h hVarA = a();
        if (hVarA == null || !hVarA.m.get().c) {
            return true;
        }
        try {
            throw new Exception("Chartboost Integration Warning: your account has been disabled for this session. This app has no active publishing campaigns, please create a publishing campaign in the Chartboost dashboard and wait at least 30 minutes to re-enable. If you need assistance, please visit http://chartboo.st/publishing .");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public class a implements Runnable {
        final int a;
        String b = null;
        boolean c = false;
        boolean d = false;

        a(int i) {
            this.a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                int i = this.a;
                if (i == 0) {
                    h.this.d();
                } else if (i == 1) {
                    i.t = this.c;
                } else if (i == 2) {
                    i.v = this.d;
                    if (this.d && h.f()) {
                        h.this.i.a();
                    } else {
                        h.this.i.b();
                    }
                } else if (i == 3) {
                    aj ajVar = new aj("api/install", h.this.j, h.this.o, 2, null);
                    ajVar.l = true;
                    h.this.h.a(ajVar);
                    Executor executor = h.this.a;
                    com.chartboost.sdk.impl.e eVar = h.this.d;
                    eVar.getClass();
                    executor.execute(eVar.new a(0, null, null, null));
                    Executor executor2 = h.this.a;
                    com.chartboost.sdk.impl.e eVar2 = h.this.f;
                    eVar2.getClass();
                    executor2.execute(eVar2.new a(0, null, null, null));
                    Executor executor3 = h.this.a;
                    com.chartboost.sdk.impl.e eVar3 = h.this.k;
                    eVar3.getClass();
                    executor3.execute(eVar3.new a(0, null, null, null));
                    h.this.a.execute(h.this.new a(4));
                    h.this.u = false;
                } else if (i == 4) {
                    h.this.i.a();
                } else if (i == 5 && i.c != null) {
                    i.c.didFailToLoadMoreApps(this.b, CBError.CBImpressionError.END_POINT_DISABLED);
                }
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(a.class, "run (" + this.a + ")", e);
            }
        }
    }

    public static void a(Context context, WebView webView, SharedPreferences sharedPreferences) {
        String userAgentString;
        String str = i.w;
        try {
            if (webView == null) {
                if (!sharedPreferences.contains("user_agent")) {
                    userAgentString = new WebView(context.getApplicationContext()).getSettings().getUserAgentString();
                } else {
                    userAgentString = sharedPreferences.getString("user_agent", i.w);
                }
            } else {
                userAgentString = webView.getSettings().getUserAgentString();
            }
            str = userAgentString;
        } catch (Exception unused) {
        }
        i.w = str;
        sharedPreferences.edit().putString("user_agent", str).apply();
    }

    static void a(Context context, Chartboost.CBPIDataUseConsent cBPIDataUseConsent) {
        i.x = cBPIDataUseConsent;
        SharedPreferences sharedPreferencesA = a(context);
        if (sharedPreferencesA != null) {
            sharedPreferencesA.edit().putInt("cbGDPR", cBPIDataUseConsent.getValue()).apply();
        }
    }
}
