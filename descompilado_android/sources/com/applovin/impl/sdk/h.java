package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.applovin.impl.mediation.MediationServiceImpl;
import com.applovin.impl.sdk.network.PostbackServiceImpl;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinUserService;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class h {
    protected static Context a;
    private com.applovin.impl.sdk.ad.e A;
    private com.applovin.impl.sdk.c.c B;
    private s C;
    private a D;
    private m E;
    private r F;
    private PostbackServiceImpl G;
    private com.applovin.impl.sdk.network.d H;
    private MediationServiceImpl I;
    private com.applovin.impl.mediation.a.a J;
    private final Object K = new Object();
    private boolean L = false;
    private boolean M = false;
    private boolean N = false;
    private boolean O = false;
    private AppLovinSdk.SdkInitializationListener P;
    private AppLovinSdk.SdkInitializationListener Q;
    private AppLovinSdkConfiguration R;
    protected com.applovin.impl.sdk.b.c b;
    private String c;
    private WeakReference<Activity> d;
    private long e;
    private AppLovinSdkSettings f;
    private String g;
    private AppLovinAdServiceImpl h;
    private NativeAdServiceImpl i;
    private EventServiceImpl j;
    private UserServiceImpl k;
    private VariableServiceImpl l;
    private AppLovinSdk m;
    private n n;
    private com.applovin.impl.sdk.d.q o;
    private com.applovin.impl.sdk.network.a p;
    private com.applovin.impl.sdk.c.h q;
    private com.applovin.impl.sdk.c.j r;
    private i s;
    private com.applovin.impl.sdk.b.e t;
    private com.applovin.impl.sdk.c.f u;
    private g v;
    private com.applovin.impl.sdk.utils.m w;
    private c x;
    private o y;
    private l z;

    public static Context A() {
        return a;
    }

    public Activity B() {
        WeakReference<Activity> weakReference = this.d;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public long C() {
        return this.e;
    }

    public boolean D() {
        return this.O;
    }

    public com.applovin.impl.sdk.network.a E() {
        return this.p;
    }

    public com.applovin.impl.sdk.d.q F() {
        return this.o;
    }

    public com.applovin.impl.sdk.c.h G() {
        return this.q;
    }

    public com.applovin.impl.sdk.c.j H() {
        return this.r;
    }

    public com.applovin.impl.sdk.network.d I() {
        return this.H;
    }

    public i J() {
        return this.s;
    }

    public com.applovin.impl.sdk.c.f K() {
        return this.u;
    }

    public g L() {
        return this.v;
    }

    public PostbackServiceImpl M() {
        return this.G;
    }

    public AppLovinSdk N() {
        return this.m;
    }

    public c O() {
        return this.x;
    }

    public o P() {
        return this.y;
    }

    public l Q() {
        return this.z;
    }

    public com.applovin.impl.sdk.ad.e R() {
        return this.A;
    }

    public com.applovin.impl.sdk.c.c S() {
        return this.B;
    }

    public s T() {
        return this.C;
    }

    public m U() {
        return this.E;
    }

    public a V() {
        return this.D;
    }

    public r W() {
        return this.F;
    }

    public MediationServiceImpl a(Activity activity) {
        this.I.maybeInitialize(activity);
        return this.I;
    }

    public <ST> com.applovin.impl.sdk.b.b<ST> a(String str, com.applovin.impl.sdk.b.b<ST> bVar) {
        return this.b.a(str, bVar);
    }

    public <T> T a(com.applovin.impl.sdk.b.b<T> bVar) {
        return (T) this.b.a(bVar);
    }

    public <T> T a(com.applovin.impl.sdk.b.d<T> dVar) {
        return (T) b(dVar, null);
    }

    public <T> T a(String str, T t, Class cls, SharedPreferences sharedPreferences) {
        return (T) this.t.a(str, t, cls, sharedPreferences);
    }

    public void a() {
        synchronized (this.K) {
            if (!this.L && !this.M) {
                b();
            }
        }
    }

    public void a(long j) {
        this.v.a(j);
    }

    public void a(SharedPreferences sharedPreferences) {
        this.t.a(sharedPreferences);
    }

    public <T> void a(com.applovin.impl.sdk.b.d<T> dVar, T t) {
        this.t.a(dVar, t);
    }

    public <T> void a(com.applovin.impl.sdk.b.d<T> dVar, T t, SharedPreferences sharedPreferences) {
        this.t.a(dVar, t, sharedPreferences);
    }

    public void a(AppLovinSdk.SdkInitializationListener sdkInitializationListener) {
        if (!d()) {
            this.P = sdkInitializationListener;
        } else if (sdkInitializationListener != null) {
            sdkInitializationListener.onSdkInitialized(this.R);
        }
    }

    public void a(AppLovinSdk appLovinSdk) {
        this.m = appLovinSdk;
    }

    public void a(String str) {
        this.b.a(com.applovin.impl.sdk.b.b.eh, str);
        this.b.a();
    }

    public void a(String str, AppLovinSdkSettings appLovinSdkSettings, Context context) {
        com.applovin.impl.sdk.b.e eVar;
        com.applovin.impl.sdk.b.d<String> dVar;
        String string;
        this.c = str;
        this.e = System.currentTimeMillis();
        this.f = appLovinSdkSettings;
        this.R = new SdkConfigurationImpl(this);
        a = context.getApplicationContext();
        if (context instanceof Activity) {
            this.d = new WeakReference<>((Activity) context);
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                this.n = new n(this);
                this.t = new com.applovin.impl.sdk.b.e(this);
                this.b = new com.applovin.impl.sdk.b.c(this);
                this.b.b();
                this.u = new com.applovin.impl.sdk.c.f(this);
                this.u.b();
                this.z = new l(this);
                this.x = new c(this);
                this.y = new o(this);
                this.A = new com.applovin.impl.sdk.ad.e(this);
                this.j = new EventServiceImpl(this);
                this.k = new UserServiceImpl(this);
                this.l = new VariableServiceImpl(this);
                this.B = new com.applovin.impl.sdk.c.c(this);
                this.o = new com.applovin.impl.sdk.d.q(this);
                this.p = new com.applovin.impl.sdk.network.a(this);
                this.q = new com.applovin.impl.sdk.c.h(this);
                this.r = new com.applovin.impl.sdk.c.j(this);
                this.s = new i(this);
                this.D = new a(context);
                this.h = new AppLovinAdServiceImpl(this);
                this.i = new NativeAdServiceImpl(this);
                this.C = new s(this);
                this.E = new m(this);
                this.G = new PostbackServiceImpl(this);
                this.H = new com.applovin.impl.sdk.network.d(this);
                this.I = new MediationServiceImpl(this);
                this.J = new com.applovin.impl.mediation.a.a(this);
                this.v = new g(this);
                this.w = new com.applovin.impl.sdk.utils.m(this);
                this.F = new r(this);
                if (TextUtils.isEmpty(str)) {
                    this.N = true;
                    Log.e("AppLovinSdk", "Unable to find AppLovin SDK key. Please add  meta-data android:name=\"applovin.sdk.key\" android:value=\"YOUR_SDK_KEY_HERE\" into AndroidManifest.xml.");
                    StringWriter stringWriter = new StringWriter();
                    new Throwable("").printStackTrace(new PrintWriter(stringWriter));
                    Log.e("AppLovinSdk", "Called with an invalid SDK key from: " + stringWriter.toString());
                }
                if (v()) {
                    a(false);
                } else {
                    if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.ae)).booleanValue()) {
                        appLovinSdkSettings.setTestAdsEnabled(com.applovin.impl.sdk.utils.n.b(context));
                        appLovinSdkSettings.setVerboseLogging(com.applovin.impl.sdk.utils.n.c(context));
                        y().a(appLovinSdkSettings);
                        y().a();
                    }
                    SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    if (TextUtils.isEmpty((String) this.t.b(com.applovin.impl.sdk.b.d.a, (Object) null, defaultSharedPreferences))) {
                        this.O = true;
                        eVar = this.t;
                        dVar = com.applovin.impl.sdk.b.d.a;
                        string = Boolean.toString(true);
                    } else {
                        eVar = this.t;
                        dVar = com.applovin.impl.sdk.b.d.a;
                        string = Boolean.toString(false);
                    }
                    eVar.a(dVar, string, defaultSharedPreferences);
                    if (TextUtils.isEmpty((String) a(com.applovin.impl.sdk.b.d.c))) {
                        a(com.applovin.impl.sdk.b.d.c, String.valueOf(((int) (Math.random() * 100.0d)) + 1));
                    }
                    b();
                }
            } catch (Throwable th) {
                Log.e("AppLovinSdk", "Failed to load AppLovin SDK, ad serving will be disabled", th);
                a(false);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public <T> void a(String str, T t, SharedPreferences.Editor editor) {
        this.t.a(str, t, editor);
    }

    public void a(boolean z) {
        synchronized (this.K) {
            this.L = false;
            this.M = z;
        }
        F().b();
    }

    public <T> T b(com.applovin.impl.sdk.b.d<T> dVar, T t) {
        return (T) this.t.b(dVar, t);
    }

    public <T> T b(com.applovin.impl.sdk.b.d<T> dVar, T t, SharedPreferences sharedPreferences) {
        return (T) this.t.b(dVar, t, sharedPreferences);
    }

    public List<String> b(com.applovin.impl.sdk.b.b bVar) {
        return this.b.b(bVar);
    }

    public void b() {
        synchronized (this.K) {
            this.L = true;
            F().a();
            F().a(new com.applovin.impl.sdk.d.k(this), com.applovin.impl.sdk.d.q.a.MAIN);
        }
    }

    public <T> void b(com.applovin.impl.sdk.b.d<T> dVar) {
        this.t.a(dVar);
    }

    public void b(String str) {
        this.w.a(str);
    }

    public void c(String str) {
        this.g = str;
    }

    public boolean c() {
        boolean z;
        synchronized (this.K) {
            z = this.L;
        }
        return z;
    }

    public boolean d() {
        boolean z;
        synchronized (this.K) {
            z = this.M;
        }
        return z;
    }

    public void e() {
        final AppLovinSdk.SdkInitializationListener sdkInitializationListener = this.P;
        if (sdkInitializationListener != null) {
            if (d()) {
                this.P = null;
                this.Q = null;
            } else {
                if (this.Q == sdkInitializationListener) {
                    return;
                }
                if (((Boolean) a(com.applovin.impl.sdk.b.b.ai)).booleanValue()) {
                    this.P = null;
                } else {
                    this.Q = sdkInitializationListener;
                }
            }
            AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() { // from class: com.applovin.impl.sdk.h.1
                @Override // java.lang.Runnable
                public void run() {
                    h.this.n.a("AppLovinSdk", "Calling back publisher's initialization completion handler...");
                    sdkInitializationListener.onSdkInitialized(h.this.R);
                }
            }, Math.max(0L, ((Long) a(com.applovin.impl.sdk.b.b.aj)).longValue()));
        }
    }

    public void f() {
        long jB = this.q.b(com.applovin.impl.sdk.c.g.g);
        this.b.c();
        this.b.a();
        this.q.a();
        this.B.b();
        this.r.b();
        this.q.b(com.applovin.impl.sdk.c.g.g, jB + 1);
        b();
    }

    public boolean g() {
        Iterator<String> it = com.applovin.impl.sdk.utils.d.a((String) a(com.applovin.impl.sdk.b.b.ei)).iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(Build.MANUFACTURER)) {
                return true;
            }
        }
        return false;
    }

    public void h() {
        this.J.b();
    }

    public void i() {
        this.C.a(a);
    }

    public boolean j() {
        return this.C.d();
    }

    public boolean k() {
        return this.C.e();
    }

    public String l() {
        return this.w.a();
    }

    public AppLovinSdkSettings m() {
        return this.f;
    }

    public AppLovinSdkConfiguration n() {
        return this.R;
    }

    public String o() {
        return this.g;
    }

    public AppLovinAdServiceImpl p() {
        return this.h;
    }

    public NativeAdServiceImpl q() {
        return this.i;
    }

    public AppLovinEventService r() {
        return this.j;
    }

    public AppLovinUserService s() {
        return this.k;
    }

    public VariableServiceImpl t() {
        return this.l;
    }

    public String toString() {
        return "CoreSdk{sdkKey='" + this.c + "', enabled=" + this.M + ", isFirstSession=" + this.O + '}';
    }

    public String u() {
        return this.c;
    }

    public boolean v() {
        return this.N;
    }

    public n w() {
        return this.n;
    }

    public com.applovin.impl.mediation.a.a x() {
        return this.J;
    }

    public com.applovin.impl.sdk.b.c y() {
        return this.b;
    }

    public Context z() {
        return a;
    }
}
