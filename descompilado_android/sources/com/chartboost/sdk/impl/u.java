package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class u extends com.chartboost.sdk.e {
    com.chartboost.sdk.Libraries.h j;
    com.chartboost.sdk.Libraries.h k;
    com.chartboost.sdk.Libraries.h l;
    com.chartboost.sdk.Libraries.h m;
    com.chartboost.sdk.Libraries.h n;
    com.chartboost.sdk.Libraries.h o;
    protected float p;
    private final String q;

    public class a extends com.chartboost.sdk.e.a {
        protected ay c;
        protected az d;
        protected az e;
        protected ImageView f;
        private boolean h;

        protected a(Context context) {
            super(context);
            this.h = false;
            setBackgroundColor(0);
            setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            com.chartboost.sdk.g gVarA = com.chartboost.sdk.g.a();
            this.c = (ay) gVarA.a(new ay(context));
            addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
            this.e = (az) gVarA.a(new az(context) { // from class: com.chartboost.sdk.impl.u.a.1
                @Override // com.chartboost.sdk.impl.az
                protected void a(MotionEvent motionEvent) {
                    a.this.a(motionEvent.getX(), motionEvent.getY(), a.this.e.getWidth(), a.this.e.getHeight());
                }
            });
            a(this.e);
            this.e.setContentDescription("CBAd");
            this.f = (ImageView) gVarA.a(new ImageView(context));
            this.f.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            addView(this.f);
            addView(this.e);
        }

        protected void c() {
            this.d = new az(getContext()) { // from class: com.chartboost.sdk.impl.u.a.2
                @Override // com.chartboost.sdk.impl.az
                protected void a(MotionEvent motionEvent) {
                    a.this.d();
                }
            };
            this.d.setContentDescription("CBClose");
            addView(this.d);
        }

        protected void a(float f, float f2, float f3, float f4) {
            u.this.b(com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("x", Float.valueOf(f)), com.chartboost.sdk.Libraries.e.a("y", Float.valueOf(f2)), com.chartboost.sdk.Libraries.e.a(com.my.target.i.WIDTH, Float.valueOf(f3)), com.chartboost.sdk.Libraries.e.a(com.my.target.i.HEIGHT, Float.valueOf(f4))));
        }

        @Override // com.chartboost.sdk.e.a
        protected void a(int i, int i2) {
            int iRound;
            int iRound2;
            if (!this.h) {
                c();
                this.h = true;
            }
            boolean zA = CBUtility.a(u.this.a());
            com.chartboost.sdk.Libraries.h hVar = zA ? u.this.j : u.this.k;
            com.chartboost.sdk.Libraries.h hVar2 = zA ? u.this.l : u.this.m;
            if (!hVar.c()) {
                if (hVar == u.this.j) {
                    hVar = u.this.k;
                } else {
                    hVar = u.this.j;
                }
            }
            if (!hVar2.c()) {
                if (hVar2 == u.this.l) {
                    hVar2 = u.this.m;
                } else {
                    hVar2 = u.this.l;
                }
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            u.this.a(layoutParams, hVar, 1.0f);
            u.this.p = Math.min(Math.min(i / layoutParams.width, i2 / layoutParams.height), 1.0f);
            layoutParams.width = (int) (layoutParams.width * u.this.p);
            layoutParams.height = (int) (layoutParams.height * u.this.p);
            Point pointB = u.this.b(zA ? "frame-portrait" : "frame-landscape");
            layoutParams.leftMargin = Math.round(((i - layoutParams.width) / 2.0f) + ((pointB.x / hVar.e()) * u.this.p));
            layoutParams.topMargin = Math.round(((i2 - layoutParams.height) / 2.0f) + ((pointB.y / hVar.e()) * u.this.p));
            u.this.a(layoutParams2, hVar2, 1.0f);
            Point pointB2 = u.this.b(zA ? "close-portrait" : "close-landscape");
            if (pointB2.x == 0 && pointB2.y == 0) {
                iRound2 = layoutParams.leftMargin + layoutParams.width + Math.round((-layoutParams2.width) / 2.0f);
                iRound = layoutParams.topMargin + Math.round((-layoutParams2.height) / 2.0f);
            } else {
                int iRound3 = Math.round(((layoutParams.leftMargin + (layoutParams.width / 2.0f)) + pointB2.x) - (layoutParams2.width / 2.0f));
                iRound = Math.round(((layoutParams.topMargin + (layoutParams.height / 2.0f)) + pointB2.y) - (layoutParams2.height / 2.0f));
                iRound2 = iRound3;
            }
            layoutParams2.leftMargin = Math.min(Math.max(0, iRound2), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, iRound), i2 - layoutParams2.height);
            this.c.setLayoutParams(layoutParams);
            this.d.setLayoutParams(layoutParams2);
            this.c.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.c.a(hVar);
            this.d.a(hVar2);
            com.chartboost.sdk.Libraries.h hVar3 = zA ? u.this.n : u.this.o;
            if (!hVar3.c()) {
                if (hVar3 == u.this.n) {
                    hVar3 = u.this.o;
                } else {
                    hVar3 = u.this.n;
                }
            }
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            u uVar = u.this;
            uVar.a(layoutParams3, hVar3, uVar.p);
            Point pointB3 = u.this.b(zA ? "ad-portrait" : "ad-landscape");
            layoutParams3.leftMargin = Math.round(((i - layoutParams3.width) / 2.0f) + ((pointB3.x / hVar3.e()) * u.this.p));
            layoutParams3.topMargin = Math.round(((i2 - layoutParams3.height) / 2.0f) + ((pointB3.y / hVar3.e()) * u.this.p));
            this.f.setLayoutParams(layoutParams3);
            this.e.setLayoutParams(layoutParams3);
            this.e.a(ImageView.ScaleType.FIT_CENTER);
            this.e.a(hVar3);
        }

        protected void d() {
            u.this.h();
        }

        @Override // com.chartboost.sdk.e.a
        public void b() {
            super.b();
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
        }
    }

    public u(com.chartboost.sdk.Model.c cVar, Handler handler, com.chartboost.sdk.c cVar2) {
        super(cVar, handler, cVar2);
        this.q = "ImageViewProtocol";
        this.p = 1.0f;
        this.j = new com.chartboost.sdk.Libraries.h(this);
        this.k = new com.chartboost.sdk.Libraries.h(this);
        this.l = new com.chartboost.sdk.Libraries.h(this);
        this.m = new com.chartboost.sdk.Libraries.h(this);
        this.n = new com.chartboost.sdk.Libraries.h(this);
        this.o = new com.chartboost.sdk.Libraries.h(this);
    }

    @Override // com.chartboost.sdk.e
    protected com.chartboost.sdk.e.a b(Context context) {
        return new a(context);
    }

    @Override // com.chartboost.sdk.e
    public boolean a(JSONObject jSONObject) {
        if (!super.a(jSONObject)) {
            return false;
        }
        if (this.d.isNull("frame-portrait") || this.d.isNull("close-portrait")) {
            this.h = false;
        }
        if (this.d.isNull("frame-landscape") || this.d.isNull("close-landscape")) {
            this.i = false;
        }
        if (this.d.isNull("ad-portrait")) {
            this.h = false;
        }
        if (this.d.isNull("ad-landscape")) {
            this.i = false;
        }
        if (this.k.a("frame-landscape") && this.j.a("frame-portrait") && this.m.a("close-landscape") && this.l.a("close-portrait") && this.o.a("ad-landscape") && this.n.a("ad-portrait")) {
            return true;
        }
        CBLogging.b("ImageViewProtocol", "Error while downloading the assets");
        a(CBError.CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
        return false;
    }

    protected Point b(String str) {
        JSONObject jSONObjectA = com.chartboost.sdk.Libraries.e.a(this.d, str, com.my.target.bj.gD);
        if (jSONObjectA != null) {
            return new Point(jSONObjectA.optInt("x"), jSONObjectA.optInt("y"));
        }
        return new Point(0, 0);
    }

    public void a(ViewGroup.LayoutParams layoutParams, com.chartboost.sdk.Libraries.h hVar, float f) {
        if (hVar == null || !hVar.c()) {
            return;
        }
        layoutParams.width = (int) ((hVar.a() / hVar.e()) * f);
        layoutParams.height = (int) ((hVar.b() / hVar.e()) * f);
    }

    @Override // com.chartboost.sdk.e
    public void d() {
        super.d();
        this.k = null;
        this.j = null;
        this.m = null;
        this.l = null;
        this.o = null;
        this.n = null;
    }
}
