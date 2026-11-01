package com.adcolony.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class b extends Activity {
    c c;
    String e;
    int f;
    boolean g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    f m;
    final int a = 0;
    final int b = 1;
    int d = -1;

    b() {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!a.b() || a.a().s() == null) {
            finish();
            return;
        }
        l lVarA = a.a();
        this.i = false;
        this.c = lVarA.s();
        this.c.b(false);
        if (aw.g()) {
            this.c.b(true);
        }
        this.e = this.c.b();
        this.f = this.c.c();
        this.m = a.a().m().g().get(this.e);
        this.j = lVarA.d().getMultiWindowEnabled();
        if (this.j) {
            getWindow().addFlags(2048);
            getWindow().clearFlags(1024);
        } else {
            getWindow().addFlags(1024);
            getWindow().clearFlags(2048);
        }
        requestWindowFeature(1);
        getWindow().getDecorView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        if (lVarA.d().getKeepScreenOn()) {
            getWindow().addFlags(128);
        }
        ViewParent parent = this.c.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this.c);
        }
        setContentView(this.c);
        this.c.n().add(a.a("AdSession.finish_fullscreen_ad", new ah() { // from class: com.adcolony.sdk.b.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                b.this.a(afVar);
            }
        }, true));
        this.c.n().add(a.a("AdSession.change_orientation", new ah() { // from class: com.adcolony.sdk.b.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                JSONObject jSONObjectC = afVar.c();
                if (y.b(jSONObjectC, "id").equals(b.this.e)) {
                    b.this.a(y.c(jSONObjectC, "orientation"));
                }
            }
        }, true));
        this.c.o().add("AdSession.finish_fullscreen_ad");
        this.c.o().add("AdSession.change_orientation");
        a(this.d);
        if (!this.c.t()) {
            JSONObject jSONObjectA = y.a();
            y.a(jSONObjectA, "id", this.c.b());
            y.b(jSONObjectA, "screen_width", this.c.q());
            y.b(jSONObjectA, "screen_height", this.c.p());
            new aa.a().a("AdSession.on_fullscreen_ad_started").a(aa.b);
            new af("AdSession.on_fullscreen_ad_started", this.c.c(), jSONObjectA).b();
            this.c.c(true);
            return;
        }
        a();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        a(this.h);
        this.h = false;
    }

    void a(boolean z) {
        this.m = a.a().m().g().get(this.e);
        Iterator<Map.Entry<Integer, ax>> it = this.c.e().entrySet().iterator();
        while (it.hasNext() && !isFinishing()) {
            ax value = it.next().getValue();
            if (!value.j() && value.i().isPlaying()) {
                value.f();
            }
        }
        f fVar = this.m;
        if (fVar != null) {
            fVar.a();
        }
        AdColonyInterstitial adColonyInterstitialU = a.a().u();
        if (adColonyInterstitialU != null && adColonyInterstitialU.g() && adColonyInterstitialU.h().e() != null && z && this.k) {
            adColonyInterstitialU.h().b("pause");
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        a();
        b(this.h);
        this.h = true;
        this.l = true;
    }

    void b(boolean z) {
        Iterator<Map.Entry<Integer, ax>> it = this.c.e().entrySet().iterator();
        while (it.hasNext()) {
            ax value = it.next().getValue();
            if (!value.j() && !value.i().isPlaying() && !a.a().r().c()) {
                value.e();
            }
        }
        f fVar = this.m;
        if (fVar != null) {
            fVar.b();
        }
        AdColonyInterstitial adColonyInterstitialU = a.a().u();
        if (adColonyInterstitialU == null || !adColonyInterstitialU.g() || adColonyInterstitialU.h().e() == null) {
            return;
        }
        if ((!z || (z && !this.k)) && this.l) {
            adColonyInterstitialU.h().b("resume");
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        if (z && this.h) {
            a.a().l().c(true);
            b(this.h);
            this.k = true;
        } else {
            if (z || !this.h) {
                return;
            }
            new aa.a().a("Activity is active but window does not have focus, pausing.").a(aa.d);
            a.a().l().b(true);
            a(this.h);
            this.k = false;
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (!a.b() || this.c == null || this.g) {
            return;
        }
        if ((Build.VERSION.SDK_INT < 24 || !aw.g()) && !this.c.s()) {
            JSONObject jSONObjectA = y.a();
            y.a(jSONObjectA, "id", this.c.b());
            new af("AdSession.on_error", this.c.c(), jSONObjectA).b();
            this.i = true;
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", this.c.b());
        new af("AdSession.on_back_button", this.c.c(), jSONObjectA).b();
    }

    void a() {
        l lVarA = a.a();
        if (this.c == null) {
            this.c = lVarA.s();
        }
        c cVar = this.c;
        if (cVar == null) {
            return;
        }
        cVar.b(false);
        if (aw.g()) {
            this.c.b(true);
        }
        int iP = lVarA.c.p();
        int iQ = this.j ? lVarA.c.q() - aw.b(a.c()) : lVarA.c.q();
        if (iP <= 0 || iQ <= 0) {
            return;
        }
        JSONObject jSONObjectA = y.a();
        y.b(jSONObjectA, "screen_width", iP);
        y.b(jSONObjectA, "screen_height", iQ);
        y.a(jSONObjectA, "ad_session_id", this.c.b());
        y.b(jSONObjectA, "id", this.c.d());
        this.c.setLayoutParams(new FrameLayout.LayoutParams(iP, iQ));
        this.c.b(iP);
        this.c.a(iQ);
        new af("AdContainer.on_orientation_change", this.c.c(), jSONObjectA).b();
    }

    void a(af afVar) {
        int iC = y.c(afVar.c(), "status");
        if ((iC == 5 || iC == 0 || iC == 6 || iC == 1) && !this.g) {
            l lVarA = a.a();
            o oVarR = lVarA.r();
            lVarA.b(afVar);
            if (oVarR.b() != null) {
                oVarR.b().dismiss();
                oVarR.a((AlertDialog) null);
            }
            if (!this.i) {
                finish();
            }
            this.g = true;
            ((ViewGroup) getWindow().getDecorView()).removeAllViews();
            lVarA.c(false);
            JSONObject jSONObjectA = y.a();
            y.a(jSONObjectA, "id", this.c.b());
            new af("AdSession.on_close", this.c.c(), jSONObjectA).b();
            lVarA.a((c) null);
            lVarA.a((AdColonyInterstitial) null);
            lVarA.a((az) null);
            a.a().m().c().remove(this.c.b());
        }
    }

    void a(int i) {
        if (i == 0) {
            setRequestedOrientation(7);
        } else if (i == 1) {
            setRequestedOrientation(6);
        } else {
            setRequestedOrientation(4);
        }
        this.d = i;
    }
}
