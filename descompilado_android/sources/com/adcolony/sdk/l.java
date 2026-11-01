package com.adcolony.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.integralads.avid.library.adcolony.BuildConfig;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.ironsource.mediationsdk.utils.GeneralPropertiesWorker;
import com.vungle.warren.network.VungleApiClient;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class l implements p.a {
    private static volatile String I = "";
    static final String a = "026ae9c9824b3e483fa6c71fa88f57ae27816141";
    static final String b = "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5";
    static String e = "https://adc3-launch.adcolony.com/v4/launch";
    private JSONObject A;
    private String D;
    private String E;
    private String F;
    private String G;
    private boolean J;
    private boolean K;
    private boolean L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private boolean T;
    private int U;
    private Application.ActivityLifecycleCallbacks X;
    private ExternalAvidAdSessionContext Y;
    n c;
    aj d;
    boolean f;
    private m g;
    private ag h;
    private q i;
    private ao j;
    private d k;
    private o l;
    private t m;
    private at n;
    private ar o;
    private ADCCrashReportManager p;
    private ac q;
    private c r;
    private az s;
    private AdColonyInterstitial t;
    private AdColonyRewardListener u;
    private AdColonyAppOptions w;
    private af x;
    private boolean y;
    private af z;
    private HashMap<String, AdColonyCustomMessageListener> v = new HashMap<>();
    private HashMap<String, AdColonyZone> B = new HashMap<>();
    private HashMap<Integer, ay> C = new HashMap<>();
    private String H = "";
    private int V = 1;
    private final int W = 120;

    l() {
    }

    Activity a() {
        return a.c();
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0113  */
    void a(AdColonyAppOptions adColonyAppOptions, boolean z) {
        boolean z2;
        this.L = z;
        this.w = adColonyAppOptions;
        this.p = new ADCCrashReportManager();
        this.h = new ag();
        this.g = new m();
        this.i = new q();
        this.i.a();
        this.j = new ao();
        this.j.a();
        this.k = new d();
        this.k.a();
        this.l = new o();
        this.m = new t();
        this.m.a();
        this.q = new ac();
        ac acVar = this.q;
        ac.c();
        this.o = new ar();
        this.o.a();
        this.n = new at();
        this.n.a();
        this.c = new n();
        this.c.e();
        this.d = new aj();
        this.D = this.d.c();
        AdColony.a(a.c(), adColonyAppOptions);
        if (!z) {
            this.P = new File(this.o.g() + a).exists();
            this.Q = new File(this.o.g() + b).exists();
            if (this.P && this.Q) {
                if (y.b(y.c(this.o.g() + a), GeneralPropertiesWorker.SDK_VERSION).equals(this.c.C())) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            } else {
                z2 = false;
            }
            this.O = z2;
            if (this.P) {
                this.A = y.c(this.o.g() + a);
                b(this.A);
            }
            e(this.O);
            K();
        }
        a.a("Module.load", new ah() { // from class: com.adcolony.sdk.l.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                l.this.a(afVar);
            }
        });
        a.a("Module.unload", new ah() { // from class: com.adcolony.sdk.l.12
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                l.this.f(afVar);
            }
        });
        a.a("AdColony.on_configured", new ah() { // from class: com.adcolony.sdk.l.13
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                l.this.M = true;
                if (l.this.S) {
                    JSONObject jSONObjectA = y.a();
                    JSONObject jSONObjectA2 = y.a();
                    y.a(jSONObjectA2, "app_version", aw.b());
                    y.a(jSONObjectA, "app_bundle_info", jSONObjectA2);
                    new af("AdColony.on_update", 1, jSONObjectA).b();
                    l.this.S = false;
                }
                if (l.this.T) {
                    new af("AdColony.on_install", 1).b();
                }
                if (ac.l != null) {
                    ac.l.b(y.b(afVar.c(), "app_session_id"));
                }
                if (AdColonyEventTracker.b()) {
                    AdColonyEventTracker.a();
                }
                int iA = y.a(afVar.c(), "concurrent_requests", 4);
                if (iA != l.this.i.b()) {
                    l.this.i.a(iA);
                }
                l.this.E();
            }
        });
        a.a("AdColony.get_app_info", new ah() { // from class: com.adcolony.sdk.l.14
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                l.this.g(afVar);
            }
        });
        a.a("AdColony.v4vc_reward", new ah() { // from class: com.adcolony.sdk.l.15
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                l.this.d(afVar);
            }
        });
        a.a("AdColony.zone_info", new ah() { // from class: com.adcolony.sdk.l.16
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                l.this.e(afVar);
            }
        });
        a.a("AdColony.probe_launch_server", new ah() { // from class: com.adcolony.sdk.l.17
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                l.this.a(true, true);
            }
        });
        a.a("Crypto.sha1", new ah() { // from class: com.adcolony.sdk.l.18
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, "sha1", aw.c(y.b(afVar.c(), "data")));
                afVar.a(jSONObjectA).b();
            }
        });
        a.a("Crypto.crc32", new ah() { // from class: com.adcolony.sdk.l.19
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                JSONObject jSONObjectA = y.a();
                y.b(jSONObjectA, "crc32", aw.b(y.b(afVar.c(), "data")));
                afVar.a(jSONObjectA).b();
            }
        });
        a.a("Crypto.uuid", new ah() { // from class: com.adcolony.sdk.l.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                int iC = y.c(afVar.c(), "number");
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, "uuids", aw.a(iC));
                afVar.a(jSONObjectA).b();
            }
        });
        a.a("Device.query_advertiser_info", new ah() { // from class: com.adcolony.sdk.l.3
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                final Activity activityC = a.c();
                if (activityC != null) {
                    aw.b.execute(new Runnable() { // from class: com.adcolony.sdk.l.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            l.this.a(activityC, afVar);
                        }
                    });
                }
            }
        });
        a.a("AdColony.controller_version", new ah() { // from class: com.adcolony.sdk.l.4
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                l.this.c.f = y.b(afVar.c(), "version");
                if (ac.l != null) {
                    ac.l.a(l.this.c.f);
                }
                new aa.a().a("Controller version: ").a(l.this.c.f).a(aa.d);
            }
        });
        int iA = aw.a(this.o);
        this.S = iA == 1;
        this.T = iA == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "type", "AdColony.on_configuration_completed");
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = f().keySet().iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        JSONObject jSONObjectA2 = y.a();
        y.a(jSONObjectA2, "zone_ids", jSONArray);
        y.a(jSONObjectA, "message", jSONObjectA2);
        new af("CustomMessage.controller_send", 0, jSONObjectA).b();
    }

    private boolean F() {
        if (this.L || !this.c.d().contains("arm") || ADCNative.nativeNeonSupported()) {
            return true;
        }
        new aa.a().a("ARM architechture without NEON support. Disabling AdColony.").a(aa.g);
        a(true);
        return false;
    }

    private boolean e(boolean z) {
        return a(z, false);
    }

    String b() {
        return this.H;
    }

    JSONObject c() {
        return this.A;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(boolean z, boolean z2) {
        if (!a.d()) {
            return false;
        }
        this.R = z2;
        this.O = z;
        if (z && !z2 && !J()) {
            return false;
        }
        G();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        new Thread(new Runnable() { // from class: com.adcolony.sdk.l.5
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, "url", l.e);
                y.a(jSONObjectA, FirebaseAnalytics.Param.CONTENT_TYPE, "application/json");
                y.a(jSONObjectA, "content", l.this.c.a(l.this.c).toString());
                new aa.a().a("Launch: ").a(l.this.c.a(l.this.c).toString()).a(aa.b);
                new aa.a().a("Saving Launch to ").a(l.this.o.g()).a(l.a).a(aa.d);
                l.this.i.a(new p(new af("WebServices.post", 0, jSONObjectA), l.this));
            }
        }).start();
    }

    private boolean a(JSONObject jSONObject) {
        if (!this.O) {
            new aa.a().a("Non-standard launch. Downloading new controller.").a(aa.f);
            return true;
        }
        JSONObject jSONObject2 = this.A;
        if (jSONObject2 != null && y.b(y.f(jSONObject2, "controller"), "sha1").equals(y.b(y.f(jSONObject, "controller"), "sha1"))) {
            return false;
        }
        new aa.a().a("Controller sha1 does not match, downloading new controller.").a(aa.f);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(af afVar) {
        a(y.c(afVar.c(), "id"));
    }

    void a(boolean z) {
        this.L = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(af afVar) {
        JSONObject jSONObject = this.w.d;
        y.a(jSONObject, "app_id", this.w.a);
        y.a(jSONObject, "zone_ids", this.w.c);
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "options", jSONObject);
        afVar.a(jSONObjectA).b();
    }

    boolean a(final af afVar) {
        final Activity activityC = a.c();
        if (activityC == null) {
            return false;
        }
        try {
            int iC = afVar.c().has("id") ? y.c(afVar.c(), "id") : 0;
            if (iC <= 0) {
                iC = this.h.d();
            }
            final int i = iC;
            a(i);
            boolean zD = y.d(afVar.c(), "is_webview");
            final boolean zD2 = y.d(afVar.c(), "is_display_module");
            if (zD) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.l.6
                    @Override // java.lang.Runnable
                    public void run() {
                        ay ayVar = new ay(activityC.getApplicationContext(), l.this.h.d(), zD2);
                        ay ayVar2 = ayVar;
                        ayVar2.a(true, afVar);
                        l.this.C.put(Integer.valueOf(ayVar.a()), ayVar2);
                    }
                });
            } else {
                final ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
                executorServiceNewSingleThreadExecutor.submit(new Runnable() { // from class: com.adcolony.sdk.l.7
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject jSONObjectF = y.f(afVar.c(), "info");
                        l lVarA = a.a();
                        if (i == 1 && lVarA.d() != null) {
                            y.a(jSONObjectF, "options", lVarA.d().d());
                        }
                        l.this.h.a(new ADCVMModule(activityC, i, y.b(afVar.c(), "filepath"), jSONObjectF, executorServiceNewSingleThreadExecutor));
                    }
                });
                JSONObject jSONObjectA = y.a();
                y.a(jSONObjectA, "success", true);
                y.b(jSONObjectA, "id", i);
                afVar.a(jSONObjectA).b();
            }
            return true;
        } catch (RuntimeException e2) {
            new aa.a().a("Failed to create AdUnit file://").a(y.b(afVar.c(), "filepath")).a(aa.h);
            new aa.a().a(e2.toString()).a(aa.h);
            e2.printStackTrace();
            return false;
        }
    }

    void b(af afVar) {
        this.x = afVar;
    }

    void c(af afVar) {
        this.z = afVar;
    }

    private void H() {
        if (a.a().l().e()) {
            this.U++;
            int i = this.V;
            int i2 = this.U;
            this.V = i * i2 <= 120 ? i * i2 : 120;
            aw.a(new Runnable() { // from class: com.adcolony.sdk.l.8
                @Override // java.lang.Runnable
                public void run() {
                    new Handler().postDelayed(new Runnable() { // from class: com.adcolony.sdk.l.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (a.a().l().e()) {
                                l.this.G();
                            }
                        }
                    }, l.this.V * 1000);
                }
            });
            return;
        }
        new aa.a().a("Max launch server download attempts hit, or AdColony is no longer").a(" active.").a(aa.f);
    }

    @Override // com.adcolony.sdk.p.a
    public void a(p pVar, af afVar, Map<String, List<String>> map) {
        if (pVar.a.equals(e)) {
            if (pVar.c) {
                new aa.a().a("Launch: ").a(pVar.b).a(aa.b);
                JSONObject jSONObjectA = y.a(pVar.b);
                y.a(jSONObjectA, GeneralPropertiesWorker.SDK_VERSION, this.c.C());
                y.h(jSONObjectA, this.o.g() + a);
                if (!c(jSONObjectA)) {
                    if (this.O) {
                        return;
                    }
                    new aa.a().a("Incomplete or disabled launch server response. ").a("Disabling AdColony until next launch.").a(aa.g);
                    a(true);
                    return;
                }
                if (a(jSONObjectA)) {
                    new aa.a().a("Controller missing or out of date. Downloading controller").a(aa.d);
                    JSONObject jSONObjectA2 = y.a();
                    y.a(jSONObjectA2, "url", this.E);
                    y.a(jSONObjectA2, "filepath", this.o.g() + b);
                    this.i.a(new p(new af("WebServices.download", 0, jSONObjectA2), this));
                }
                this.A = jSONObjectA;
                return;
            }
            H();
            return;
        }
        if (pVar.a.equals(this.E)) {
            if (!c(this.F)) {
                new aa.a().a("Downloaded controller sha1 does not match, retrying.").a(aa.e);
                H();
            } else {
                if (this.O || this.R) {
                    return;
                }
                aw.a(new Runnable() { // from class: com.adcolony.sdk.l.9
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean zJ = l.this.J();
                        new aa.a().a("Loaded library. Success=" + zJ).a(aa.b);
                    }
                });
            }
        }
    }

    private boolean I() {
        if (!this.N) {
            try {
                System.loadLibrary("js");
                System.loadLibrary(BuildConfig.SDK_NAME);
            } catch (UnsatisfiedLinkError unused) {
                a(true);
                new aa.a().a("Expecting libadcolony.so in libs folder but it was not found.").a(" Disabling AdColony until next launch.").a(aa.g);
                return false;
            }
        }
        this.N = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean J() {
        if (!I()) {
            return false;
        }
        this.p.a();
        this.p.b();
        this.h.a();
        F();
        return true;
    }

    private boolean c(String str) {
        Activity activityC = a.c();
        if (activityC == null) {
            return false;
        }
        File file = new File(activityC.getFilesDir().getAbsolutePath() + "/adc3/" + b);
        if (file.exists()) {
            return aw.a(str, file);
        }
        return false;
    }

    boolean a(Context context, af afVar) {
        if (context == null) {
            return false;
        }
        AdvertisingIdClient.Info advertisingIdInfo = null;
        try {
            advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (Build.MANUFACTURER.equals(VungleApiClient.MANUFACTURER_AMAZON)) {
                return false;
            }
            new aa.a().a("Advertising ID is not available. Collecting Android ID instead of").a(" Advertising ID.").a(aa.e);
            return false;
        } catch (NoClassDefFoundError unused) {
            new aa.a().a("Google Play Services ads dependencies are missing. Collecting ").a("Android ID instead of Advertising ID.").a(aa.e);
            return false;
        } catch (NoSuchMethodError unused2) {
            new aa.a().a("Google Play Services is out of date, please update to GPS 4.0+. ").a("Collecting Android ID instead of Advertising ID.").a(aa.e);
        }
        if (advertisingIdInfo == null) {
            return false;
        }
        this.c.a = advertisingIdInfo.getId();
        ac.l.g.put("advertisingId", this.c.a);
        this.c.c = advertisingIdInfo.isLimitAdTrackingEnabled();
        this.c.b = true;
        if (afVar != null) {
            JSONObject jSONObjectA = y.a();
            y.a(jSONObjectA, "advertiser_id", this.c.c());
            y.a(jSONObjectA, "limit_ad_tracking", this.c.g());
            afVar.a(jSONObjectA).b();
        }
        return true;
    }

    void a(AdColonyAppOptions adColonyAppOptions) {
        synchronized (this.k.c()) {
            Iterator<Map.Entry<String, AdColonyInterstitial>> it = this.k.c().entrySet().iterator();
            while (it.hasNext()) {
                AdColonyInterstitial value = it.next().getValue();
                AdColonyInterstitialListener listener = value.getListener();
                value.a(true);
                if (listener != null) {
                    listener.onExpiring(value);
                }
            }
            this.k.c().clear();
        }
        this.M = false;
        a(1);
        this.B.clear();
        this.w = adColonyAppOptions;
        this.h.a();
        a(true, true);
    }

    boolean a(int i) {
        if (this.h.a(i) == null) {
            return false;
        }
        if (this.C.containsKey(Integer.valueOf(i))) {
            ay ayVar = this.C.get(Integer.valueOf(i));
            if (ayVar.g()) {
                ayVar.loadUrl("about:blank");
                ayVar.clearCache(true);
                ayVar.removeAllViews();
                ayVar.a(true);
            }
            this.C.remove(Integer.valueOf(i));
        }
        af afVar = this.z;
        if (afVar != null) {
            afVar.b();
            this.z = null;
            this.y = false;
        }
        new aa.a().a("Destroying module with id = ").a(i).a(aa.d);
        return true;
    }

    private void b(JSONObject jSONObject) {
        if (!ADCVMModule.a) {
            JSONObject jSONObjectF = y.f(jSONObject, "logging");
            ac.k = y.a(jSONObjectF, "send_level", 1);
            ac.a = y.d(jSONObjectF, "log_private");
            ac.i = y.a(jSONObjectF, "print_level", 3);
            ADCCrashReportManager.a = y.d(jSONObjectF, "enable_crash_reporting");
            if (ADCCrashReportManager.a && I()) {
                this.p.a();
                this.p.b();
            }
            this.q.a(y.g(jSONObjectF, "modules"));
        }
        this.c.a(y.f(jSONObject, TtmlNode.TAG_METADATA));
        this.H = y.b(y.f(jSONObject, "controller"), "version");
    }

    private boolean c(JSONObject jSONObject) {
        if (jSONObject == null) {
            new aa.a().a("Launch response verification failed - response is null or unknown").a(aa.d);
            return false;
        }
        try {
            try {
                JSONObject jSONObjectF = y.f(jSONObject, "controller");
                this.E = y.b(jSONObjectF, "url");
                this.F = y.b(jSONObjectF, "sha1");
                this.G = y.b(jSONObject, "status");
                I = y.b(jSONObject, "pie");
                if (AdColonyEventTracker.b()) {
                    AdColonyEventTracker.a();
                }
                b(jSONObject);
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            new File(this.o.g() + a).delete();
        }
        if (this.G.equals("disable")) {
            try {
                new File(this.o.g() + b).delete();
            } catch (Exception unused3) {
            }
            new aa.a().a("Launch server response with disabled status. Disabling AdColony ").a("until next launch.").a(aa.f);
            a(true);
            return false;
        }
        if (!this.E.equals("") && !this.G.equals("")) {
            return true;
        }
        new aa.a().a("Missing controller status or URL. Disabling AdColony until next ").a("launch.").a(aa.g);
        return false;
    }

    boolean d(final af afVar) {
        if (this.u == null) {
            return false;
        }
        aw.a(new Runnable() { // from class: com.adcolony.sdk.l.10
            @Override // java.lang.Runnable
            public void run() {
                l.this.u.onReward(new AdColonyReward(afVar));
            }
        });
        return true;
    }

    void e(af afVar) {
        AdColonyZone adColonyZone;
        if (this.L) {
            new aa.a().a("AdColony is disabled. Ignoring zone_info message.").a(aa.f);
            return;
        }
        String strB = y.b(afVar.c(), "zone_id");
        if (this.B.containsKey(strB)) {
            adColonyZone = this.B.get(strB);
        } else {
            AdColonyZone adColonyZone2 = new AdColonyZone(strB);
            this.B.put(strB, adColonyZone2);
            adColonyZone = adColonyZone2;
        }
        adColonyZone.a(afVar);
    }

    private void K() {
        Activity activityC = a.c();
        if (activityC == null || this.X != null) {
            return;
        }
        this.X = new Application.ActivityLifecycleCallbacks() { // from class: com.adcolony.sdk.l.11
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                a.b = true;
                a.a(activity);
                Activity activityC2 = a.c();
                if (activityC2 != null && l.this.j.c() && (activityC2 instanceof b) && !((b) activityC2).g) {
                    new aa.a().a("Ignoring onActivityResumed").a(aa.d);
                    return;
                }
                new aa.a().a("onActivityResumed() Activity Lifecycle Callback").a(aa.d);
                a.a(activity);
                if (l.this.x != null) {
                    l.this.x.a(l.this.x.c()).b();
                    l.this.x = null;
                }
                l.this.K = false;
                l.this.j.d(true);
                l.this.j.e(true);
                l.this.j.f(false);
                if (l.this.f && !l.this.j.e()) {
                    l.this.j.a(true);
                }
                l.this.l.a();
                if (ac.l == null || ac.l.d == null || ac.l.d.isShutdown() || ac.l.d.isTerminated()) {
                    AdColony.a(activity, a.a().w);
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                a.b = false;
                l.this.j.d(false);
                l.this.j.e(true);
                a.a().c.D();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                if (!l.this.j.e()) {
                    l.this.j.a(true);
                }
                a.a(activity);
            }
        };
        activityC.getApplication().registerActivityLifecycleCallbacks(this.X);
    }

    AdColonyAppOptions d() {
        if (this.w == null) {
            this.w = new AdColonyAppOptions();
        }
        return this.w;
    }

    boolean e() {
        return this.w != null;
    }

    void b(@NonNull AdColonyAppOptions adColonyAppOptions) {
        this.w = adColonyAppOptions;
    }

    HashMap<String, AdColonyZone> f() {
        return this.B;
    }

    void b(boolean z) {
        this.K = z;
    }

    boolean g() {
        return this.K;
    }

    boolean h() {
        return this.L;
    }

    AdColonyRewardListener i() {
        return this.u;
    }

    void a(AdColonyRewardListener adColonyRewardListener) {
        this.u = adColonyRewardListener;
    }

    t j() {
        if (this.m == null) {
            this.m = new t();
            this.m.a();
        }
        return this.m;
    }

    ADCCrashReportManager k() {
        if (this.p == null) {
            this.p = new ADCCrashReportManager();
        }
        this.p.a();
        return this.p;
    }

    ao l() {
        if (this.j == null) {
            this.j = new ao();
            this.j.a();
        }
        return this.j;
    }

    d m() {
        if (this.k == null) {
            this.k = new d();
            this.k.a();
        }
        return this.k;
    }

    n n() {
        if (this.c == null) {
            this.c = new n();
            this.c.e();
        }
        return this.c;
    }

    ar o() {
        if (this.o == null) {
            this.o = new ar();
            this.o.a();
        }
        return this.o;
    }

    aj p() {
        if (this.d == null) {
            this.d = new aj();
        }
        return this.d;
    }

    ag q() {
        if (this.h == null) {
            this.h = new ag();
            this.h.a();
        }
        return this.h;
    }

    o r() {
        if (this.l == null) {
            this.l = new o();
        }
        return this.l;
    }

    c s() {
        return this.r;
    }

    void a(c cVar) {
        this.r = cVar;
    }

    az t() {
        return this.s;
    }

    void a(az azVar) {
        this.s = azVar;
    }

    AdColonyInterstitial u() {
        return this.t;
    }

    void a(AdColonyInterstitial adColonyInterstitial) {
        this.t = adColonyInterstitial;
    }

    String v() {
        return this.D;
    }

    void a(String str) {
        this.D = str;
    }

    boolean w() {
        return this.J;
    }

    void c(boolean z) {
        this.J = z;
    }

    HashMap<Integer, ay> x() {
        return this.C;
    }

    boolean y() {
        return this.y;
    }

    void d(boolean z) {
        this.y = z;
    }

    void b(@NonNull String str) {
        e = str;
    }

    HashMap<String, AdColonyCustomMessageListener> z() {
        return this.v;
    }

    boolean A() {
        return this.M;
    }

    boolean B() {
        return this.N;
    }

    static String C() {
        return I;
    }

    ExternalAvidAdSessionContext D() {
        if (this.Y == null) {
            this.Y = new ExternalAvidAdSessionContext("3.3.5", true);
        }
        return this.Y;
    }
}
