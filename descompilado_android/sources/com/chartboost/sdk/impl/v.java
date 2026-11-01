package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.sdk.constants.Constants;
import java.io.File;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class v extends u {
    protected boolean A;
    protected boolean B;
    protected boolean C;
    protected int D;
    protected com.chartboost.sdk.Libraries.h E;
    protected com.chartboost.sdk.Libraries.h F;
    protected com.chartboost.sdk.Libraries.h G;
    protected com.chartboost.sdk.Libraries.h H;
    protected com.chartboost.sdk.Libraries.h I;
    protected com.chartboost.sdk.Libraries.h J;
    protected com.chartboost.sdk.Libraries.h K;
    protected com.chartboost.sdk.Libraries.h L;
    protected boolean M;
    protected boolean N;
    protected boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    final com.chartboost.sdk.Libraries.f q;
    protected int r;
    protected int s;
    protected String t;
    protected String u;
    protected int v;
    protected int w;
    JSONObject x;
    protected boolean y;
    protected boolean z;

    public class a extends u.a {
        final ab h;
        y i;
        final t j;
        final w k;
        private final az m;
        private View n;
        private final az o;

        private a(Context context) {
            JSONObject jSONObjectOptJSONObject;
            JSONObject jSONObjectOptJSONObject2;
            super(context);
            com.chartboost.sdk.g gVarA = com.chartboost.sdk.g.a();
            if (v.this.N) {
                this.n = new View(context);
                this.n.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                this.n.setVisibility(8);
                addView(this.n);
            }
            if (v.this.e.n == 2) {
                this.i = (y) gVarA.a(new y(context, v.this));
                this.i.setVisibility(8);
                addView(this.i);
            }
            this.h = (ab) gVarA.a(new ab(context, v.this));
            a(this.h.g);
            this.h.setVisibility(8);
            addView(this.h);
            this.j = (t) gVarA.a(new t(context, v.this));
            this.j.setVisibility(8);
            addView(this.j);
            if (v.this.e.n == 2) {
                this.k = (w) gVarA.a(new w(context, v.this));
                this.k.setVisibility(8);
                addView(this.k);
            } else {
                this.k = null;
            }
            this.m = new az(getContext()) { // from class: com.chartboost.sdk.impl.v.a.1
                @Override // com.chartboost.sdk.impl.az
                protected void a(MotionEvent motionEvent) {
                    if (v.this.e.n == 2) {
                        a.this.k.a(false);
                    }
                    if (v.this.r == 1) {
                        a.this.c(false);
                    }
                    a.this.b(true);
                }
            };
            this.m.setVisibility(8);
            addView(this.m);
            this.o = new az(getContext()) { // from class: com.chartboost.sdk.impl.v.a.2
                @Override // com.chartboost.sdk.impl.az
                protected void a(MotionEvent motionEvent) {
                    a.this.d();
                }
            };
            this.o.setVisibility(8);
            this.o.setContentDescription("CBClose");
            addView(this.o);
            JSONObject jSONObjectOptJSONObject3 = v.this.x.optJSONObject("progress");
            JSONObject jSONObjectOptJSONObject4 = v.this.x.optJSONObject("video-controls-background");
            if (jSONObjectOptJSONObject3 != null && !jSONObjectOptJSONObject3.isNull("background-color") && !jSONObjectOptJSONObject3.isNull("border-color") && !jSONObjectOptJSONObject3.isNull("progress-color") && !jSONObjectOptJSONObject3.isNull("radius")) {
                v.this.M = true;
                x xVarC = this.h.c();
                xVarC.a(com.chartboost.sdk.e.a(jSONObjectOptJSONObject3.optString("background-color")));
                xVarC.b(com.chartboost.sdk.e.a(jSONObjectOptJSONObject3.optString("border-color")));
                xVarC.c(com.chartboost.sdk.e.a(jSONObjectOptJSONObject3.optString("progress-color")));
                xVarC.b((float) jSONObjectOptJSONObject3.optDouble("radius", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
            }
            if (jSONObjectOptJSONObject4 != null && !jSONObjectOptJSONObject4.isNull("color")) {
                this.h.a(com.chartboost.sdk.e.a(jSONObjectOptJSONObject4.optString("color")));
            }
            if (v.this.e.n == 2 && v.this.A && (jSONObjectOptJSONObject2 = v.this.x.optJSONObject("post-video-toaster")) != null) {
                this.j.a(jSONObjectOptJSONObject2.optString("title"), jSONObjectOptJSONObject2.optString("tagline"));
            }
            if (v.this.e.n == 2 && v.this.z && (jSONObjectOptJSONObject = v.this.x.optJSONObject("confirmation")) != null) {
                this.i.a(jSONObjectOptJSONObject.optString(MimeTypes.BASE_TYPE_TEXT), com.chartboost.sdk.e.a(jSONObjectOptJSONObject.optString("color")));
            }
            if (v.this.e.n == 2 && v.this.B) {
                JSONObject jSONObjectA = com.chartboost.sdk.Libraries.e.a(v.this.x, "post-video-reward-toaster");
                this.k.a((jSONObjectA == null || !jSONObjectA.optString(Constants.ParametersKeys.POSITION).equals("inside-top")) ? 1 : 0);
                this.k.a(jSONObjectA != null ? jSONObjectA.optString(MimeTypes.BASE_TYPE_TEXT) : "");
                if (v.this.J.c()) {
                    this.k.a(v.this.L);
                }
            }
            JSONObject jSONObjectG = v.this.g();
            if (jSONObjectG == null || jSONObjectG.isNull("video-click-button")) {
                this.h.d();
            }
            this.h.d(v.this.x.optBoolean("video-progress-timer-enabled"));
            if (v.this.O || v.this.N) {
                this.f.setVisibility(4);
            }
            String[] strArr = new String[1];
            strArr[0] = CBUtility.a(v.this.a()) ? "video-portrait" : "video-landscape";
            JSONObject jSONObjectA2 = com.chartboost.sdk.Libraries.e.a(jSONObjectG, strArr);
            v.this.u = jSONObjectA2 != null ? jSONObjectA2.optString("id") : "";
            if (v.this.u.isEmpty()) {
                v.this.a(CBError.CBImpressionError.VIDEO_ID_MISSING);
                return;
            }
            if (v.this.t == null) {
                v.this.t = v.this.q.a(v.this.u);
            }
            if (v.this.t == null) {
                v.this.a(CBError.CBImpressionError.VIDEO_UNAVAILABLE);
            } else {
                this.h.a(v.this.t);
            }
        }

        @Override // com.chartboost.sdk.impl.u.a
        protected void c() {
            super.c();
            if (v.this.r == 0 && (!v.this.z || v.this.o())) {
                b(false);
            } else {
                a(v.this.r, false);
            }
        }

        public void e() {
            c(true);
            this.h.h();
            v.this.s++;
            if (v.this.s > 1 || v.this.u() || v.this.v < 1) {
                return;
            }
            v.this.e.e();
        }

        @Override // com.chartboost.sdk.impl.u.a, com.chartboost.sdk.e.a
        protected void a(int i, int i2) {
            super.a(i, i2);
            a(v.this.r, false);
            boolean zA = CBUtility.a(v.this.a());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -1);
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.c.getLayoutParams();
            v vVar = v.this;
            vVar.a(layoutParams2, zA ? vVar.F : vVar.E, 1.0f);
            Point pointB = v.this.b(zA ? "replay-portrait" : "replay-landscape");
            int iRound = Math.round(((layoutParams6.leftMargin + (layoutParams6.width / 2.0f)) + pointB.x) - (layoutParams2.width / 2.0f));
            int iRound2 = Math.round(((layoutParams6.topMargin + (layoutParams6.height / 2.0f)) + pointB.y) - (layoutParams2.height / 2.0f));
            layoutParams2.leftMargin = Math.min(Math.max(0, iRound), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, iRound2), i2 - layoutParams2.height);
            this.m.bringToFront();
            if (zA) {
                this.m.a(v.this.F);
            } else {
                this.m.a(v.this.E);
            }
            RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
            if (!v.this.s()) {
                layoutParams3.width = layoutParams7.width;
                layoutParams3.height = layoutParams7.height;
                layoutParams3.leftMargin = layoutParams7.leftMargin;
                layoutParams3.topMargin = layoutParams7.topMargin;
                layoutParams4.width = layoutParams7.width;
                layoutParams4.height = layoutParams7.height;
                layoutParams4.leftMargin = layoutParams7.leftMargin;
                layoutParams4.topMargin = layoutParams7.topMargin;
            } else {
                RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
                com.chartboost.sdk.Libraries.h hVar = zA ? v.this.l : v.this.m;
                v.this.a(layoutParams8, hVar, 1.0f);
                layoutParams8.leftMargin = 0;
                layoutParams8.topMargin = 0;
                layoutParams8.addRule(11);
                this.o.setLayoutParams(layoutParams8);
                this.o.a(hVar);
            }
            layoutParams5.width = layoutParams7.width;
            layoutParams5.height = 72;
            layoutParams5.leftMargin = layoutParams7.leftMargin;
            layoutParams5.topMargin = (layoutParams7.topMargin + layoutParams7.height) - 72;
            if (v.this.N) {
                this.n.setLayoutParams(layoutParams);
            }
            if (v.this.e.n == 2) {
                this.i.setLayoutParams(layoutParams3);
            }
            this.h.setLayoutParams(layoutParams4);
            this.j.setLayoutParams(layoutParams5);
            this.m.setLayoutParams(layoutParams2);
            if (v.this.e.n == 2) {
                this.i.a();
            }
            this.h.a();
        }

        void b(boolean z) {
            if (v.this.r == 1) {
                return;
            }
            if (v.this.z) {
                a(0, z);
                return;
            }
            a(1, z);
            JSONObject jSONObjectA = com.chartboost.sdk.Libraries.e.a(v.this.x, "timer");
            if (v.this.s < 1 && jSONObjectA != null && !jSONObjectA.isNull("delay")) {
                Object[] objArr = new Object[1];
                objArr[0] = v.this.y ? "visible" : com.my.target.aa.f.br;
                CBLogging.c("InterstitialVideoViewProtocol", String.format("controls starting %s, setting timer", objArr));
                this.h.b(v.this.y);
                v.this.a(this.h, new Runnable() { // from class: com.chartboost.sdk.impl.v.a.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Object[] objArr2 = new Object[1];
                        objArr2[0] = v.this.y ? com.my.target.aa.f.br : "shown";
                        CBLogging.c("InterstitialVideoViewProtocol", String.format("controls %s automatically from timer", objArr2));
                        a.this.h.a(!v.this.y, true);
                        synchronized (v.this.g) {
                            v.this.g.remove(a.this.h);
                        }
                    }
                }, Math.round(jSONObjectA.optDouble("delay", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) * 1000.0d));
            } else {
                this.h.b(!v.this.y);
            }
            this.h.e();
            if (v.this.s <= 1) {
                v.this.e.f();
            }
        }

        void c(boolean z) {
            this.h.f();
            if (v.this.r == 1 && z) {
                if (v.this.s < 1 && v.this.x != null && !v.this.x.isNull("post-video-reward-toaster") && v.this.B && v.this.J.c() && v.this.K.c()) {
                    e(true);
                }
                a(2, true);
                if (CBUtility.a(CBUtility.a())) {
                    requestLayout();
                }
            }
        }

        private void e(boolean z) {
            if (z) {
                this.k.a(true);
            } else {
                this.k.setVisibility(0);
            }
            v.this.a.postDelayed(new Runnable() { // from class: com.chartboost.sdk.impl.v.a.4
                @Override // java.lang.Runnable
                public void run() {
                    a.this.k.a(false);
                }
            }, 2500L);
        }

        private void a(int i, boolean z) {
            v vVar = v.this;
            vVar.r = i;
            if (i == 0) {
                vVar.a(!vVar.s(), this.e, z);
                if (v.this.e.n == 2) {
                    v.this.a(true, (View) this.i, z);
                }
                if (v.this.N) {
                    v.this.a(false, this.n, z);
                }
                v.this.a(false, (View) this.h, z);
                v.this.a(false, (View) this.m, z);
                v.this.a(false, (View) this.j, z);
                this.e.setEnabled(false);
                this.m.setEnabled(false);
                this.h.setEnabled(false);
            } else if (i == 1) {
                vVar.a(false, (View) this.e, z);
                if (v.this.e.n == 2) {
                    v.this.a(false, (View) this.i, z);
                }
                if (v.this.N) {
                    v.this.a(true, this.n, z);
                }
                v.this.a(true, (View) this.h, z);
                v.this.a(false, (View) this.m, z);
                v.this.a(false, (View) this.j, z);
                this.e.setEnabled(true);
                this.m.setEnabled(false);
                this.h.setEnabled(true);
            } else if (i == 2) {
                vVar.a(true, (View) this.e, z);
                if (v.this.e.n == 2) {
                    v.this.a(false, (View) this.i, z);
                }
                if (v.this.N) {
                    v.this.a(false, this.n, z);
                }
                v.this.a(false, (View) this.h, z);
                v.this.a(true, (View) this.m, z);
                v.this.a(v.this.K.c() && v.this.J.c() && v.this.A, this.j, z);
                this.m.setEnabled(true);
                this.e.setEnabled(true);
                this.h.setEnabled(false);
                if (v.this.C) {
                    e(false);
                }
            }
            boolean zF = f();
            az azVarD = d(true);
            azVarD.setEnabled(zF);
            v.this.a(zF, azVarD, z);
            az azVarD2 = d(false);
            azVarD2.setEnabled(false);
            v.this.a(false, (View) azVarD2, z);
            if (v.this.O || v.this.N) {
                v vVar2 = v.this;
                vVar2.a(!vVar2.s(), this.f, z);
            }
            v vVar3 = v.this;
            vVar3.a(!vVar3.s(), this.c, z);
            a(i != 0);
        }

        protected boolean f() {
            if (v.this.r == 1 && v.this.s < 1) {
                StringBuilder sb = new StringBuilder();
                sb.append("close-");
                sb.append(CBUtility.a(v.this.a()) ? "portrait" : "landscape");
                JSONObject jSONObjectA = com.chartboost.sdk.Libraries.e.a(v.this.g(), sb.toString());
                float fOptDouble = jSONObjectA != null ? (float) jSONObjectA.optDouble("delay", -1.0d) : -1.0f;
                int iRound = fOptDouble >= 0.0f ? Math.round(fOptDouble * 1000.0f) : -1;
                v.this.D = iRound;
                if (iRound < 0 || iRound > this.h.b().d()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.chartboost.sdk.impl.u.a, com.chartboost.sdk.e.a
        public void b() {
            v.this.n();
            super.b();
        }

        @Override // com.chartboost.sdk.impl.u.a
        protected void d() {
            if (v.this.r == 1 && v.this.e.a.a == 1) {
                return;
            }
            if (v.this.r == 1) {
                c(false);
                this.h.h();
                if (v.this.s < 1) {
                    v.this.s++;
                    v.this.e.e();
                }
            }
            v.this.a.post(new Runnable() { // from class: com.chartboost.sdk.impl.v.a.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        v.this.h();
                    } catch (Exception e) {
                        com.chartboost.sdk.Tracking.a.a(a.class, "onCloseButton Runnable.run", e);
                    }
                }
            });
        }

        @Override // com.chartboost.sdk.impl.u.a
        protected void a(float f, float f2, float f3, float f4) {
            if ((v.this.y && v.this.r == 1) || v.this.r == 0) {
                return;
            }
            b(f, f2, f3, f4);
        }

        protected void b(float f, float f2, float f3, float f4) {
            if (v.this.r == 1) {
                c(false);
            }
            v.this.b(com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("x", Float.valueOf(f)), com.chartboost.sdk.Libraries.e.a("y", Float.valueOf(f2)), com.chartboost.sdk.Libraries.e.a(com.my.target.i.WIDTH, Float.valueOf(f3)), com.chartboost.sdk.Libraries.e.a(com.my.target.i.HEIGHT, Float.valueOf(f4))));
        }

        protected void g() {
            v.this.z = false;
            b(true);
        }

        public az d(boolean z) {
            return (!(v.this.s() && z) && (v.this.s() || z)) ? this.d : this.o;
        }
    }

    public v(com.chartboost.sdk.Model.c cVar, com.chartboost.sdk.Libraries.f fVar, Handler handler, com.chartboost.sdk.c cVar2) {
        super(cVar, handler, cVar2);
        this.r = 0;
        this.P = false;
        this.v = 0;
        this.w = 0;
        this.Q = false;
        this.R = false;
        this.C = false;
        this.D = 0;
        this.M = false;
        this.N = false;
        this.O = false;
        this.q = fVar;
        this.r = 0;
        this.E = new com.chartboost.sdk.Libraries.h(this);
        this.F = new com.chartboost.sdk.Libraries.h(this);
        this.G = new com.chartboost.sdk.Libraries.h(this);
        this.H = new com.chartboost.sdk.Libraries.h(this);
        this.I = new com.chartboost.sdk.Libraries.h(this);
        this.J = new com.chartboost.sdk.Libraries.h(this);
        this.K = new com.chartboost.sdk.Libraries.h(this);
        this.L = new com.chartboost.sdk.Libraries.h(this);
        this.s = 0;
    }

    public boolean o() {
        return this.e.n == 1;
    }

    @Override // com.chartboost.sdk.impl.u, com.chartboost.sdk.e
    protected com.chartboost.sdk.e.a b(Context context) {
        return new a(context);
    }

    @Override // com.chartboost.sdk.e
    public boolean l() {
        e().d();
        return true;
    }

    @Override // com.chartboost.sdk.e
    public void m() {
        super.m();
        if (this.r == 1 && this.P) {
            e().h.b().a(this.v);
            e().h.e();
        }
        this.P = false;
    }

    @Override // com.chartboost.sdk.e
    public void n() {
        super.n();
        if (this.r != 1 || this.P) {
            return;
        }
        this.P = true;
        e().h.g();
    }

    @Override // com.chartboost.sdk.impl.u, com.chartboost.sdk.e
    public boolean a(JSONObject jSONObject) {
        if (!super.a(jSONObject)) {
            return false;
        }
        this.x = jSONObject.optJSONObject("ux");
        if (this.x == null) {
            this.x = com.chartboost.sdk.Libraries.e.a(new com.chartboost.sdk.Libraries.e.a[0]);
        }
        if (this.d.isNull("video-landscape") || this.d.isNull("replay-landscape")) {
            this.i = false;
        }
        if (!this.E.a("replay-landscape") || !this.F.a("replay-portrait") || !this.I.a("video-click-button") || !this.J.a("post-video-reward-icon") || !this.K.a("post-video-button") || !this.G.a("video-confirmation-button") || !this.H.a("video-confirmation-icon") || !this.L.a("post-video-reward-icon")) {
            CBLogging.b("InterstitialVideoViewProtocol", "Error while downloading the assets");
            a(CBError.CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
            return false;
        }
        this.y = this.x.optBoolean("video-controls-togglable");
        this.N = jSONObject.optBoolean("fullscreen");
        this.O = jSONObject.optBoolean("preroll_popup_fullscreen");
        if (this.e.n == 2) {
            JSONObject jSONObjectOptJSONObject = this.x.optJSONObject("confirmation");
            JSONObject jSONObjectOptJSONObject2 = this.x.optJSONObject("post-video-toaster");
            if (jSONObjectOptJSONObject2 != null && !jSONObjectOptJSONObject2.isNull("title") && !jSONObjectOptJSONObject2.isNull("tagline")) {
                this.A = true;
            }
            if (jSONObjectOptJSONObject != null && !jSONObjectOptJSONObject.isNull(MimeTypes.BASE_TYPE_TEXT) && !jSONObjectOptJSONObject.isNull("color")) {
                this.z = true;
            }
            if (!this.x.isNull("post-video-reward-toaster")) {
                this.B = true;
            }
        }
        return true;
    }

    @Override // com.chartboost.sdk.e
    protected void i() {
        if (this.z && (!this.G.c() || !this.H.c())) {
            this.z = false;
        }
        super.i();
    }

    @Override // com.chartboost.sdk.impl.u, com.chartboost.sdk.e
    public void d() {
        super.d();
        this.E = null;
        this.F = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.G = null;
        this.H = null;
        this.L = null;
    }

    public boolean p() {
        return this.r == 1;
    }

    @Override // com.chartboost.sdk.e
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public a e() {
        return (a) super.e();
    }

    protected void r() {
        this.e.p();
    }

    protected boolean s() {
        int i = this.r;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    return false;
                }
            } else if (!this.N && !CBUtility.a(CBUtility.a())) {
                return false;
            }
        } else if (!this.O && !CBUtility.a(CBUtility.a())) {
            return false;
        }
        return true;
    }

    public boolean t() {
        return this.Q;
    }

    public void a(boolean z) {
        this.Q = z;
    }

    public boolean u() {
        return this.R;
    }

    public void v() {
        String str = this.t;
        if (str != null) {
            new File(str).delete();
        }
        this.R = true;
        a(CBError.CBImpressionError.ERROR_PLAYING_VIDEO);
    }

    @Override // com.chartboost.sdk.e
    public float j() {
        return this.w;
    }

    @Override // com.chartboost.sdk.e
    public float k() {
        return this.v;
    }
}
