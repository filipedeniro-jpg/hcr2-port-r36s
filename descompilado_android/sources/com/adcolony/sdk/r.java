package com.adcolony.sdk;

import android.content.Context;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.core.view.MotionEventCompat;
import com.google.android.exoplayer2.util.MimeTypes;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class r extends EditText {
    private c A;
    private af B;
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;
    private final int k;
    private final int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private String w;
    private String x;
    private String y;
    private String z;

    int a(boolean z, int i) {
        if (i == 0) {
            return z ? 1 : 16;
        }
        if (i == 1) {
            return z ? 3 : 48;
        }
        if (i != 2) {
            return 17;
        }
        return z ? 5 : 80;
    }

    r(Context context, af afVar, int i, c cVar) {
        super(context);
        this.a = 0;
        this.b = 1;
        this.c = 2;
        this.d = 3;
        this.e = 1;
        this.f = 2;
        this.g = 3;
        this.h = 0;
        this.i = 1;
        this.j = 2;
        this.k = 1;
        this.l = 2;
        this.m = i;
        this.B = afVar;
        this.A = cVar;
    }

    void a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        this.u = y.c(jSONObjectC, "x");
        this.v = y.c(jSONObjectC, "y");
        setGravity(a(true, this.u) | a(false, this.v));
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        l lVarA = a.a();
        d dVarM = lVarA.m();
        int action = motionEvent.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        JSONObject jSONObjectA = y.a();
        y.b(jSONObjectA, "view_id", this.m);
        y.a(jSONObjectA, "ad_session_id", this.w);
        y.b(jSONObjectA, "container_x", this.n + x);
        y.b(jSONObjectA, "container_y", this.o + y);
        y.b(jSONObjectA, "view_x", x);
        y.b(jSONObjectA, "view_y", y);
        y.b(jSONObjectA, "id", this.A.d());
        if (action == 0) {
            new af("AdContainer.on_touch_began", this.A.c(), jSONObjectA).b();
        } else if (action == 1) {
            if (!this.A.r()) {
                lVarA.a(dVarM.f().get(this.w));
            }
            new af("AdContainer.on_touch_ended", this.A.c(), jSONObjectA).b();
        } else if (action == 2) {
            new af("AdContainer.on_touch_moved", this.A.c(), jSONObjectA).b();
        } else if (action == 3) {
            new af("AdContainer.on_touch_cancelled", this.A.c(), jSONObjectA).b();
        } else if (action == 5) {
            int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", ((int) motionEvent.getX(action2)) + this.n);
            y.b(jSONObjectA, "container_y", ((int) motionEvent.getY(action2)) + this.o);
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action2));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action2));
            new af("AdContainer.on_touch_began", this.A.c(), jSONObjectA).b();
        } else if (action == 6) {
            int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", ((int) motionEvent.getX(action3)) + this.n);
            y.b(jSONObjectA, "container_y", ((int) motionEvent.getY(action3)) + this.o);
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action3));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action3));
            if (!this.A.r()) {
                lVarA.a(dVarM.f().get(this.w));
            }
            new af("AdContainer.on_touch_ended", this.A.c(), jSONObjectA).b();
        }
        return true;
    }

    boolean b(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        return y.c(jSONObjectC, "id") == this.m && y.c(jSONObjectC, "container_id") == this.A.d() && y.b(jSONObjectC, "ad_session_id").equals(this.A.b());
    }

    void a() {
        JSONObject jSONObjectC = this.B.c();
        this.w = y.b(jSONObjectC, "ad_session_id");
        this.n = y.c(jSONObjectC, "x");
        this.o = y.c(jSONObjectC, "y");
        this.p = y.c(jSONObjectC, "width");
        this.q = y.c(jSONObjectC, "height");
        this.s = y.c(jSONObjectC, "font_family");
        this.r = y.c(jSONObjectC, "font_style");
        this.t = y.c(jSONObjectC, "font_size");
        this.x = y.b(jSONObjectC, "background_color");
        this.y = y.b(jSONObjectC, "font_color");
        this.z = y.b(jSONObjectC, MimeTypes.BASE_TYPE_TEXT);
        this.u = y.c(jSONObjectC, "align_x");
        this.v = y.c(jSONObjectC, "align_y");
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.p, this.q);
        layoutParams.setMargins(this.n, this.o, 0, 0);
        layoutParams.gravity = 0;
        this.A.addView(this, layoutParams);
        int i = this.s;
        if (i == 0) {
            setTypeface(Typeface.DEFAULT);
        } else if (i == 1) {
            setTypeface(Typeface.SERIF);
        } else if (i == 2) {
            setTypeface(Typeface.SANS_SERIF);
        } else if (i == 3) {
            setTypeface(Typeface.MONOSPACE);
        }
        int i2 = this.r;
        if (i2 == 0) {
            setTypeface(getTypeface(), 0);
        } else if (i2 == 1) {
            setTypeface(getTypeface(), 1);
        } else if (i2 == 2) {
            setTypeface(getTypeface(), 2);
        } else if (i2 == 3) {
            setTypeface(getTypeface(), 3);
        }
        setText(this.z);
        setTextSize(this.t);
        setGravity(a(true, this.u) | a(false, this.v));
        if (!this.x.equals("")) {
            setBackgroundColor(aw.g(this.x));
        }
        if (!this.y.equals("")) {
            setTextColor(aw.g(this.y));
        }
        this.A.n().add(a.a("TextView.set_visible", new ah() { // from class: com.adcolony.sdk.r.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (r.this.b(afVar)) {
                    r.this.k(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_bounds", new ah() { // from class: com.adcolony.sdk.r.3
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (r.this.b(afVar)) {
                    r.this.d(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_font_color", new ah() { // from class: com.adcolony.sdk.r.4
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (r.this.b(afVar)) {
                    r.this.f(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_background_color", new ah() { // from class: com.adcolony.sdk.r.5
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (r.this.b(afVar)) {
                    r.this.e(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_typeface", new ah() { // from class: com.adcolony.sdk.r.6
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (r.this.b(afVar)) {
                    r.this.j(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_font_size", new ah() { // from class: com.adcolony.sdk.r.7
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (r.this.b(afVar)) {
                    r.this.g(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_font_style", new ah() { // from class: com.adcolony.sdk.r.8
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (r.this.b(afVar)) {
                    r.this.h(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.get_text", new ah() { // from class: com.adcolony.sdk.r.9
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (r.this.b(afVar)) {
                    r.this.c(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.set_text", new ah() { // from class: com.adcolony.sdk.r.10
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (r.this.b(afVar)) {
                    r.this.i(afVar);
                }
            }
        }, true));
        this.A.n().add(a.a("TextView.align", new ah() { // from class: com.adcolony.sdk.r.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (r.this.b(afVar)) {
                    r.this.a(afVar);
                }
            }
        }, true));
        this.A.o().add("TextView.set_visible");
        this.A.o().add("TextView.set_bounds");
        this.A.o().add("TextView.set_font_color");
        this.A.o().add("TextView.set_background_color");
        this.A.o().add("TextView.set_typeface");
        this.A.o().add("TextView.set_font_size");
        this.A.o().add("TextView.set_font_style");
        this.A.o().add("TextView.get_text");
        this.A.o().add("TextView.set_text");
        this.A.o().add("TextView.align");
    }

    void c(af afVar) {
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, MimeTypes.BASE_TYPE_TEXT, getText().toString());
        afVar.a(jSONObjectA).b();
    }

    void d(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        this.n = y.c(jSONObjectC, "x");
        this.o = y.c(jSONObjectC, "y");
        this.p = y.c(jSONObjectC, "width");
        this.q = y.c(jSONObjectC, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.n, this.o, 0, 0);
        layoutParams.width = this.p;
        layoutParams.height = this.q;
        setLayoutParams(layoutParams);
    }

    void e(af afVar) {
        this.x = y.b(afVar.c(), "background_color");
        setBackgroundColor(aw.g(this.x));
    }

    void f(af afVar) {
        this.y = y.b(afVar.c(), "font_color");
        setTextColor(aw.g(this.y));
    }

    void g(af afVar) {
        this.t = y.c(afVar.c(), "font_size");
        setTextSize(this.t);
    }

    void h(af afVar) {
        int iC = y.c(afVar.c(), "font_style");
        this.r = iC;
        if (iC == 0) {
            setTypeface(getTypeface(), 0);
            return;
        }
        if (iC == 1) {
            setTypeface(getTypeface(), 1);
        } else if (iC == 2) {
            setTypeface(getTypeface(), 2);
        } else {
            if (iC != 3) {
                return;
            }
            setTypeface(getTypeface(), 3);
        }
    }

    void i(af afVar) {
        this.z = y.b(afVar.c(), MimeTypes.BASE_TYPE_TEXT);
        setText(this.z);
    }

    void j(af afVar) {
        int iC = y.c(afVar.c(), "font_family");
        this.s = iC;
        if (iC == 0) {
            setTypeface(Typeface.DEFAULT);
            return;
        }
        if (iC == 1) {
            setTypeface(Typeface.SERIF);
        } else if (iC == 2) {
            setTypeface(Typeface.SANS_SERIF);
        } else {
            if (iC != 3) {
                return;
            }
            setTypeface(Typeface.MONOSPACE);
        }
    }

    void k(af afVar) {
        if (y.d(afVar.c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
