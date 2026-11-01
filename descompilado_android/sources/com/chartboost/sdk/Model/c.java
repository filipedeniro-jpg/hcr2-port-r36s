package com.chartboost.sdk.Model;

import android.content.SharedPreferences;
import android.os.Handler;
import com.applovin.mediation.AppLovinNativeAdapter;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.i;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.ak;
import com.chartboost.sdk.impl.al;
import com.chartboost.sdk.impl.ap;
import com.chartboost.sdk.impl.bc;
import com.chartboost.sdk.impl.bf;
import com.chartboost.sdk.impl.c.a;
import com.chartboost.sdk.impl.s;
import com.chartboost.sdk.impl.u;
import com.chartboost.sdk.impl.v;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private com.chartboost.sdk.e C;
    private Runnable D;
    public final com.chartboost.sdk.impl.c a;
    public final f b;
    public final ah c;
    public final ap d;
    public final com.chartboost.sdk.Tracking.a e;
    public final Handler f;
    public final com.chartboost.sdk.c g;
    public final ak h;
    public final com.chartboost.sdk.d i;
    public final al j;
    public final d k;
    public final String m;
    public final String o;
    public final a p;
    public final SharedPreferences q;
    public bc s;
    public aj w;
    public boolean z;
    private Boolean B = null;
    public boolean t = false;
    public boolean u = false;
    public boolean v = false;
    public boolean y = false;
    public int l = 0;
    public boolean r = false;
    public boolean x = false;
    public int n = 3;
    private boolean A = true;

    public c(a aVar, d dVar, f fVar, ah ahVar, ap apVar, SharedPreferences sharedPreferences, com.chartboost.sdk.Tracking.a aVar2, Handler handler, com.chartboost.sdk.c cVar, ak akVar, com.chartboost.sdk.d dVar2, al alVar, com.chartboost.sdk.impl.c cVar2, String str, String str2) {
        this.z = false;
        this.p = aVar;
        this.a = cVar2;
        this.b = fVar;
        this.c = ahVar;
        this.d = apVar;
        this.e = aVar2;
        this.f = handler;
        this.g = cVar;
        this.h = akVar;
        this.i = dVar2;
        this.j = alVar;
        this.k = dVar;
        this.z = true;
        this.m = str;
        this.o = str2;
        this.q = sharedPreferences;
    }

    public boolean a() {
        this.l = 0;
        if (this.p.b == 0) {
            int i = this.a.a;
            if (i != 0) {
                if (i == 1) {
                    this.n = 2;
                    this.C = new v(this, this.b, this.f, this.g);
                    this.A = false;
                }
            } else if (this.p.p.equals("video")) {
                this.n = 1;
                this.C = new v(this, this.b, this.f, this.g);
                this.A = false;
            } else {
                this.n = 0;
                this.C = new u(this, this.f, this.g);
            }
        } else {
            int i2 = this.a.a;
            if (i2 != 0) {
                if (i2 == 1) {
                    this.n = 2;
                    this.A = false;
                }
            } else if (this.p.p.equals("video")) {
                this.n = 1;
                this.A = false;
            } else {
                this.n = 0;
            }
            this.C = new bf(this, this.b, this.c, this.q, this.e, this.f, this.g, this.i);
        }
        return this.C.a(this.p.a);
    }

    public boolean b() {
        return this.A;
    }

    public void c() {
        this.z = true;
        this.g.b(this);
        this.k.b(this);
    }

    public void d() {
        this.k.a(this);
    }

    public boolean a(JSONObject jSONObject) {
        if (this.l != 2 || this.t) {
            return false;
        }
        String str = this.p.j;
        String str2 = this.p.i;
        if (!str2.isEmpty()) {
            try {
                if (this.h.a(str2)) {
                    try {
                        this.B = Boolean.TRUE;
                        str = str2;
                    } catch (Exception e) {
                        e = e;
                        str = str2;
                        com.chartboost.sdk.Tracking.a.a(getClass(), "onClick", e);
                    }
                } else {
                    this.B = Boolean.FALSE;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        if (this.x) {
            return false;
        }
        this.x = true;
        this.z = false;
        a(str, jSONObject);
        return true;
    }

    private boolean x() {
        return this.B != null;
    }

    private boolean y() {
        return this.B.booleanValue();
    }

    public void a(CBError.CBImpressionError cBImpressionError) {
        this.k.a(this, cBImpressionError);
    }

    public void e() {
        this.u = true;
        this.A = true;
        if (this.a.a == 1 && i.c != null) {
            i.c.didCompleteRewardedVideo(this.m, this.p.k);
        }
        w();
    }

    public void f() {
        this.v = true;
    }

    public boolean g() {
        com.chartboost.sdk.e eVar = this.C;
        if (eVar != null) {
            eVar.b();
            if (this.C.e() != null) {
                return true;
            }
        } else {
            CBLogging.b("CBImpression", "reinitializing -- no view protocol exists!!");
        }
        CBLogging.e("CBImpression", "reinitializing -- view not yet created");
        return false;
    }

    public void h() {
        i();
        if (this.r) {
            com.chartboost.sdk.e eVar = this.C;
            if (eVar != null) {
                eVar.d();
            }
            this.C = null;
            CBLogging.e("CBImpression", "Destroying the view and view data");
        }
    }

    public void i() {
        bc bcVar = this.s;
        if (bcVar != null) {
            bcVar.b();
            try {
                if (this.C != null && this.C.e() != null && this.C.e().getParent() != null) {
                    this.s.removeView(this.C.e());
                }
            } catch (Exception e) {
                CBLogging.a("CBImpression", "Exception raised while cleaning up views", e);
                com.chartboost.sdk.Tracking.a.a(getClass(), "cleanUpViews", e);
            }
            this.s = null;
        }
        com.chartboost.sdk.e eVar = this.C;
        if (eVar != null) {
            eVar.f();
        }
        CBLogging.e("CBImpression", "Destroying the view");
    }

    public CBError.CBImpressionError j() {
        try {
            if (this.C != null) {
                return this.C.c();
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "tryCreatingView", e);
        }
        return CBError.CBImpressionError.ERROR_CREATING_VIEW;
    }

    public com.chartboost.sdk.e.a k() {
        com.chartboost.sdk.e eVar = this.C;
        if (eVar != null) {
            return eVar.e();
        }
        return null;
    }

    public void l() {
        com.chartboost.sdk.e eVar = this.C;
        if (eVar == null || eVar.e() == null) {
            return;
        }
        this.C.e().setVisibility(8);
    }

    public void a(Runnable runnable) {
        this.D = runnable;
    }

    public void m() {
        this.t = true;
    }

    public void n() {
        Runnable runnable = this.D;
        if (runnable != null) {
            runnable.run();
            this.D = null;
        }
        this.t = false;
    }

    public String o() {
        return this.p.f;
    }

    public void p() {
        this.k.c(this);
    }

    public boolean q() {
        com.chartboost.sdk.e eVar = this.C;
        if (eVar != null) {
            return eVar.l();
        }
        return false;
    }

    public void r() {
        this.x = false;
        com.chartboost.sdk.e eVar = this.C;
        if (eVar == null || !this.y) {
            return;
        }
        this.y = false;
        eVar.m();
    }

    public void s() {
        this.x = false;
    }

    public void t() {
        com.chartboost.sdk.e eVar = this.C;
        if (eVar == null || this.y) {
            return;
        }
        this.y = true;
        eVar.n();
    }

    public com.chartboost.sdk.e u() {
        return this.C;
    }

    public boolean v() {
        return this.z;
    }

    public void w() {
        aj ajVar = new aj("/api/video-complete", this.d, this.e, 2, null);
        ajVar.a("location", this.m);
        ajVar.a("reward", Integer.valueOf(this.p.k));
        ajVar.a("currency-name", this.p.l);
        ajVar.a(AppLovinNativeAdapter.KEY_EXTRA_AD_ID, o());
        ajVar.a("force_close", (Object) false);
        if (!this.p.g.isEmpty()) {
            ajVar.a("cgn", this.p.g);
        }
        com.chartboost.sdk.e eVarU = k() != null ? u() : null;
        if (eVarU != null) {
            float fK = eVarU.k();
            float fJ = eVarU.j();
            CBLogging.a(getClass().getSimpleName(), String.format(Locale.US, "TotalDuration: %f PlaybackTime: %f", Float.valueOf(fJ), Float.valueOf(fK)));
            float f = fJ / 1000.0f;
            ajVar.a("total_time", Float.valueOf(f));
            if (fK <= 0.0f) {
                ajVar.a("playback_time", Float.valueOf(f));
            } else {
                ajVar.a("playback_time", Float.valueOf(fK / 1000.0f));
            }
        }
        this.c.a(ajVar);
        this.e.b(this.a.a(this.p.b), o());
    }

    void a(String str, JSONObject jSONObject) {
        com.chartboost.sdk.e eVar;
        com.chartboost.sdk.d dVarC;
        Handler handler = this.f;
        com.chartboost.sdk.impl.c cVar = this.a;
        cVar.getClass();
        handler.post(cVar.new a(1, this.m, null));
        if (b() && this.l == 2 && (dVarC = this.g.c()) != null) {
            dVarC.b(this);
        }
        if (!s.a().a(str)) {
            aj ajVar = new aj("/api/click", this.d, this.e, 2, null);
            if (!this.p.f.isEmpty()) {
                ajVar.a(AppLovinNativeAdapter.KEY_EXTRA_AD_ID, this.p.f);
            }
            if (!this.p.m.isEmpty()) {
                ajVar.a("to", this.p.m);
            }
            if (!this.p.g.isEmpty()) {
                ajVar.a("cgn", this.p.g);
            }
            if (!this.p.h.isEmpty()) {
                ajVar.a("creative", this.p.h);
            }
            int i = this.n;
            if (i == 1 || i == 2) {
                if (this.p.b == 0 && k() != null) {
                    eVar = (v) u();
                } else {
                    eVar = (this.p.b != 1 || k() == null) ? null : (bf) u();
                }
                if (eVar != null) {
                    float fK = eVar.k();
                    float fJ = eVar.j();
                    CBLogging.a(getClass().getSimpleName(), String.format(Locale.US, "TotalDuration: %f PlaybackTime: %f", Float.valueOf(fJ), Float.valueOf(fK)));
                    float f = fJ / 1000.0f;
                    ajVar.a("total_time", Float.valueOf(f));
                    if (fK <= 0.0f) {
                        ajVar.a("playback_time", Float.valueOf(f));
                    } else {
                        ajVar.a("playback_time", Float.valueOf(fK / 1000.0f));
                    }
                }
            }
            if (jSONObject != null) {
                ajVar.a("click_coordinates", jSONObject);
            }
            ajVar.a("location", this.m);
            if (x()) {
                ajVar.a("retarget_reinstall", Boolean.valueOf(y()));
            }
            this.w = ajVar;
            this.h.a(this, str, null);
        } else {
            this.h.a(this, false, str, CBError.CBClickError.URI_INVALID, null);
        }
        this.e.c(this.a.a(this.p.b), this.m, o());
    }
}
