package com.adcolony.sdk;

import android.R;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.VideoView;
import androidx.annotation.FloatRange;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.integralads.avid.library.adcolony.session.AvidManagedVideoAdSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class c extends FrameLayout {
    private AvidManagedVideoAdSession A;
    boolean a;
    boolean b;
    Context c;
    VideoView d;
    private HashMap<Integer, ax> e;
    private HashMap<Integer, au> f;
    private HashMap<Integer, u> g;
    private HashMap<Integer, ay> h;
    private HashMap<Integer, h> i;
    private HashMap<Integer, r> j;
    private HashMap<Integer, w> k;
    private HashMap<Integer, Boolean> l;
    private HashMap<Integer, View> m;
    private int n;
    private int o;
    private int p;
    private int q;
    private String r;
    private float s;
    private double t;
    private long u;
    private ArrayList<ah> v;
    private ArrayList<String> w;
    private boolean x;
    private boolean y;
    private boolean z;

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    c(Context context, String str) {
        super(context);
        this.s = 0.0f;
        this.t = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.u = 0L;
        this.c = context;
        this.r = str;
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
    }

    boolean a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        return y.c(jSONObjectC, "container_id") == this.p && y.b(jSONObjectC, "ad_session_id").equals(this.r);
    }

    void b(af afVar) {
        int iQ;
        this.e = new HashMap<>();
        this.f = new HashMap<>();
        this.g = new HashMap<>();
        this.h = new HashMap<>();
        this.i = new HashMap<>();
        this.j = new HashMap<>();
        this.k = new HashMap<>();
        this.l = new HashMap<>();
        this.m = new HashMap<>();
        this.v = new ArrayList<>();
        this.w = new ArrayList<>();
        JSONObject jSONObjectC = afVar.c();
        this.p = y.c(jSONObjectC, "id");
        this.n = y.c(jSONObjectC, "width");
        this.o = y.c(jSONObjectC, "height");
        this.q = y.c(jSONObjectC, "module_id");
        this.b = y.d(jSONObjectC, "viewability_enabled");
        this.x = this.p == 1;
        l lVarA = a.a();
        if (this.n == 0 && this.o == 0) {
            this.n = lVarA.c.p();
            if (lVarA.d().getMultiWindowEnabled()) {
                iQ = lVarA.c.q() - aw.b(a.c());
            } else {
                iQ = lVarA.c.q();
            }
            this.o = iQ;
        } else {
            setLayoutParams(new FrameLayout.LayoutParams(this.n, this.o));
        }
        this.v.add(a.a("VideoView.create", new ah() { // from class: com.adcolony.sdk.c.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c cVar = c.this;
                    cVar.a(cVar.g(afVar2));
                }
            }
        }, true));
        this.v.add(a.a("VideoView.destroy", new ah() { // from class: com.adcolony.sdk.c.7
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c.this.h(afVar2);
                }
            }
        }, true));
        this.v.add(a.a("WebView.create", new ah() { // from class: com.adcolony.sdk.c.8
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c cVar = c.this;
                    cVar.a(cVar.i(afVar2));
                }
            }
        }, true));
        this.v.add(a.a("WebView.destroy", new ah() { // from class: com.adcolony.sdk.c.9
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c.this.j(afVar2);
                }
            }
        }, true));
        this.v.add(a.a("RenderView.create", new ah() { // from class: com.adcolony.sdk.c.10
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c cVar = c.this;
                    cVar.a(cVar.m(afVar2));
                }
            }
        }, true));
        this.v.add(a.a("RenderView.destroy", new ah() { // from class: com.adcolony.sdk.c.11
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c.this.n(afVar2);
                }
            }
        }, true));
        this.v.add(a.a("TextView.create", new ah() { // from class: com.adcolony.sdk.c.12
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c cVar = c.this;
                    cVar.a(cVar.k(afVar2));
                }
            }
        }, true));
        this.v.add(a.a("TextView.destroy", new ah() { // from class: com.adcolony.sdk.c.13
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c.this.l(afVar2);
                }
            }
        }, true));
        this.v.add(a.a("ImageView.create", new ah() { // from class: com.adcolony.sdk.c.14
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c cVar = c.this;
                    cVar.a(cVar.e(afVar2));
                }
            }
        }, true));
        this.v.add(a.a("ImageView.destroy", new ah() { // from class: com.adcolony.sdk.c.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c.this.f(afVar2);
                }
            }
        }, true));
        this.v.add(a.a("ColorView.create", new ah() { // from class: com.adcolony.sdk.c.3
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c cVar = c.this;
                    cVar.a(cVar.c(afVar2));
                }
            }
        }, true));
        this.v.add(a.a("ColorView.destroy", new ah() { // from class: com.adcolony.sdk.c.4
            @Override // com.adcolony.sdk.ah
            public void a(af afVar2) {
                if (c.this.a(afVar2)) {
                    c.this.d(afVar2);
                }
            }
        }, true));
        this.w.add("VideoView.create");
        this.w.add("VideoView.destroy");
        this.w.add("WebView.create");
        this.w.add("WebView.destroy");
        this.w.add("RenderView.create");
        this.w.add("RenderView.destroy");
        this.w.add("TextView.create");
        this.w.add("TextView.destroy");
        this.w.add("ImageView.create");
        this.w.add("ImageView.destroy");
        this.w.add("ColorView.create");
        this.w.add("ColorView.destroy");
        this.d = new VideoView(this.c);
        this.d.setVisibility(8);
        addView(this.d);
        setClipToPadding(false);
        if (this.b) {
            d(y.d(afVar.c(), "advanced_viewability"));
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        l lVarA = a.a();
        d dVarM = lVarA.m();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        JSONObject jSONObjectA = y.a();
        y.b(jSONObjectA, "view_id", -1);
        y.a(jSONObjectA, "ad_session_id", this.r);
        y.b(jSONObjectA, "container_x", x);
        y.b(jSONObjectA, "container_y", y);
        y.b(jSONObjectA, "view_x", x);
        y.b(jSONObjectA, "view_y", y);
        y.b(jSONObjectA, "id", this.p);
        if (action == 0) {
            new af("AdContainer.on_touch_began", this.q, jSONObjectA).b();
        } else if (action == 1) {
            if (!this.x) {
                lVarA.a(dVarM.f().get(this.r));
            }
            new af("AdContainer.on_touch_ended", this.q, jSONObjectA).b();
        } else if (action == 2) {
            new af("AdContainer.on_touch_moved", this.q, jSONObjectA).b();
        } else if (action == 3) {
            new af("AdContainer.on_touch_cancelled", this.q, jSONObjectA).b();
        } else if (action == 5) {
            int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", (int) motionEvent.getX(action2));
            y.b(jSONObjectA, "container_y", (int) motionEvent.getY(action2));
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action2));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action2));
            new af("AdContainer.on_touch_began", this.q, jSONObjectA).b();
        } else if (action == 6) {
            int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", (int) motionEvent.getX(action3));
            y.b(jSONObjectA, "container_y", (int) motionEvent.getY(action3));
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action3));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action3));
            y.b(jSONObjectA, "x", (int) motionEvent.getX(action3));
            y.b(jSONObjectA, "y", (int) motionEvent.getY(action3));
            if (!this.x) {
                lVarA.a(dVarM.f().get(this.r));
            }
            new af("AdContainer.on_touch_ended", this.q, jSONObjectA).b();
        }
        return true;
    }

    h c(af afVar) {
        int iC = y.c(afVar.c(), "id");
        h hVar = new h(this.c, afVar, iC, this);
        hVar.a();
        this.i.put(Integer.valueOf(iC), hVar);
        this.m.put(Integer.valueOf(iC), hVar);
        return hVar;
    }

    boolean d(af afVar) {
        int iC = y.c(afVar.c(), "id");
        View viewRemove = this.m.remove(Integer.valueOf(iC));
        h hVarRemove = this.i.remove(Integer.valueOf(iC));
        if (viewRemove == null || hVarRemove == null) {
            a.a().m().a(afVar.d(), "" + iC);
            return false;
        }
        removeView(hVarRemove);
        return true;
    }

    w e(af afVar) {
        int iC = y.c(afVar.c(), "id");
        w wVar = new w(this.c, afVar, iC, this);
        wVar.a();
        this.k.put(Integer.valueOf(iC), wVar);
        this.m.put(Integer.valueOf(iC), wVar);
        return wVar;
    }

    boolean f(af afVar) {
        int iC = y.c(afVar.c(), "id");
        View viewRemove = this.m.remove(Integer.valueOf(iC));
        w wVarRemove = this.k.remove(Integer.valueOf(iC));
        if (viewRemove == null || wVarRemove == null) {
            a.a().m().a(afVar.d(), "" + iC);
            return false;
        }
        removeView(wVarRemove);
        return true;
    }

    ax g(af afVar) {
        int iC = y.c(afVar.c(), "id");
        ax axVar = new ax(this.c, afVar, iC, this);
        axVar.b();
        this.e.put(Integer.valueOf(iC), axVar);
        this.m.put(Integer.valueOf(iC), axVar);
        return axVar;
    }

    boolean h(af afVar) {
        int iC = y.c(afVar.c(), "id");
        View viewRemove = this.m.remove(Integer.valueOf(iC));
        ax axVarRemove = this.e.remove(Integer.valueOf(iC));
        if (viewRemove == null || axVarRemove == null) {
            a.a().m().a(afVar.d(), "" + iC);
            return false;
        }
        if (axVarRemove.h()) {
            axVarRemove.d();
        }
        axVarRemove.a();
        removeView(axVarRemove);
        return true;
    }

    ay i(af afVar) {
        ay ayVar;
        JSONObject jSONObjectC = afVar.c();
        int iC = y.c(jSONObjectC, "id");
        boolean zD = y.d(jSONObjectC, "is_module");
        l lVarA = a.a();
        if (zD) {
            ayVar = lVarA.x().get(Integer.valueOf(y.c(jSONObjectC, "module_id")));
            if (ayVar == null) {
                new aa.a().a("Module WebView created with invalid id").a(aa.g);
                return null;
            }
            ayVar.a(afVar, iC, this);
        } else {
            ayVar = new ay(this.c, afVar, iC, lVarA.q().d(), this);
        }
        this.h.put(Integer.valueOf(iC), ayVar);
        this.m.put(Integer.valueOf(iC), ayVar);
        JSONObject jSONObjectA = y.a();
        y.b(jSONObjectA, "module_id", ayVar.a());
        afVar.a(jSONObjectA).b();
        return ayVar;
    }

    boolean j(af afVar) {
        int iC = y.c(afVar.c(), "id");
        l lVarA = a.a();
        View viewRemove = this.m.remove(Integer.valueOf(iC));
        ay ayVarRemove = this.h.remove(Integer.valueOf(iC));
        if (ayVarRemove == null || viewRemove == null) {
            lVarA.m().a(afVar.d(), "" + iC);
            return false;
        }
        lVarA.q().a(ayVarRemove.a());
        removeView(ayVarRemove);
        return true;
    }

    View k(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        int iC = y.c(jSONObjectC, "id");
        if (y.d(jSONObjectC, "editable")) {
            r rVar = new r(this.c, afVar, iC, this);
            rVar.a();
            this.j.put(Integer.valueOf(iC), rVar);
            this.m.put(Integer.valueOf(iC), rVar);
            this.l.put(Integer.valueOf(iC), true);
            return rVar;
        }
        if (!y.d(jSONObjectC, "button")) {
            au auVar = new au(this.c, afVar, iC, this);
            auVar.a();
            this.f.put(Integer.valueOf(iC), auVar);
            this.m.put(Integer.valueOf(iC), auVar);
            this.l.put(Integer.valueOf(iC), false);
            return auVar;
        }
        au auVar2 = new au(this.c, R.style.Widget.DeviceDefault.Button, afVar, iC, this);
        auVar2.a();
        this.f.put(Integer.valueOf(iC), auVar2);
        this.m.put(Integer.valueOf(iC), auVar2);
        this.l.put(Integer.valueOf(iC), false);
        return auVar2;
    }

    boolean l(af afVar) {
        au auVarRemove;
        int iC = y.c(afVar.c(), "id");
        View viewRemove = this.m.remove(Integer.valueOf(iC));
        if (this.l.remove(Integer.valueOf(this.p)).booleanValue()) {
            auVarRemove = this.j.remove(Integer.valueOf(iC));
        } else {
            auVarRemove = this.f.remove(Integer.valueOf(iC));
        }
        if (viewRemove == null || auVarRemove == null) {
            a.a().m().a(afVar.d(), "" + iC);
            return false;
        }
        removeView(auVarRemove);
        return true;
    }

    u m(af afVar) {
        int iC = y.c(afVar.c(), "id");
        u uVar = new u(this.c, afVar, iC, this);
        uVar.a();
        this.g.put(Integer.valueOf(iC), uVar);
        this.m.put(Integer.valueOf(iC), uVar);
        return uVar;
    }

    boolean n(af afVar) {
        int iC = y.c(afVar.c(), "id");
        View viewRemove = this.m.remove(Integer.valueOf(iC));
        u uVarRemove = this.g.remove(Integer.valueOf(iC));
        if (viewRemove == null || uVarRemove == null) {
            a.a().m().a(afVar.d(), "" + iC);
            return false;
        }
        uVarRemove.b();
        removeView(uVarRemove);
        return true;
    }

    private void d(final boolean z) {
        final Runnable runnable = new Runnable() { // from class: com.adcolony.sdk.c.5
            @Override // java.lang.Runnable
            public void run() {
                if (c.this.u == 0) {
                    c.this.u = System.currentTimeMillis();
                }
                float fA = bc.a((View) c.this.getParent(), a.c(), true, z, true);
                double dB = aw.b(aw.a((Context) a.c()));
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (c.this.u + 200 < jCurrentTimeMillis) {
                    c.this.u = jCurrentTimeMillis;
                    if (c.this.s != fA || c.this.t != dB) {
                        c.this.a(fA, dB);
                    }
                    c.this.s = fA;
                    c.this.t = dB;
                }
            }
        };
        new Thread(new Runnable() { // from class: com.adcolony.sdk.c.6
            @Override // java.lang.Runnable
            public void run() {
                while (!c.this.a) {
                    aw.a(runnable);
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(@FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 100.0d) float f, @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 1.0d) double d) {
        JSONObject jSONObjectA = y.a();
        y.b(jSONObjectA, "id", this.p);
        y.a(jSONObjectA, "ad_session_id", this.r);
        y.a(jSONObjectA, "exposure", f);
        y.a(jSONObjectA, "volume", d);
        new af("AdContainer.on_exposure_change", this.q, jSONObjectA).b();
    }

    void a() {
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", this.r);
        new af("AdSession.on_error", this.q, jSONObjectA).b();
    }

    String b() {
        return this.r;
    }

    int c() {
        return this.q;
    }

    int d() {
        return this.p;
    }

    HashMap<Integer, ax> e() {
        return this.e;
    }

    HashMap<Integer, au> f() {
        return this.f;
    }

    HashMap<Integer, u> g() {
        return this.g;
    }

    HashMap<Integer, ay> h() {
        return this.h;
    }

    HashMap<Integer, h> i() {
        return this.i;
    }

    HashMap<Integer, r> j() {
        return this.j;
    }

    HashMap<Integer, w> k() {
        return this.k;
    }

    HashMap<Integer, Boolean> l() {
        return this.l;
    }

    HashMap<Integer, View> m() {
        return this.m;
    }

    ArrayList<ah> n() {
        return this.v;
    }

    ArrayList<String> o() {
        return this.w;
    }

    int p() {
        return this.o;
    }

    void a(int i) {
        this.o = i;
    }

    int q() {
        return this.n;
    }

    void b(int i) {
        this.n = i;
    }

    boolean r() {
        return this.x;
    }

    void a(boolean z) {
        this.x = z;
    }

    boolean s() {
        return this.z;
    }

    void b(boolean z) {
        this.z = z;
    }

    boolean t() {
        return this.y;
    }

    void c(boolean z) {
        this.y = z;
    }

    void a(AvidManagedVideoAdSession avidManagedVideoAdSession) {
        this.A = avidManagedVideoAdSession;
        a(this.m);
    }

    void a(Map map) {
        if (this.A == null || map == null) {
            return;
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            this.A.registerFriendlyObstruction((View) ((Map.Entry) it.next()).getValue());
        }
    }

    void a(View view) {
        AvidManagedVideoAdSession avidManagedVideoAdSession = this.A;
        if (avidManagedVideoAdSession == null || view == null) {
            return;
        }
        avidManagedVideoAdSession.registerFriendlyObstruction(view);
    }
}
