package com.adcolony.sdk;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.MotionEventCompat;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class h extends View {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private String f;
    private String g;
    private c h;
    private af i;

    h(Context context, af afVar, int i, c cVar) {
        super(context);
        this.h = cVar;
        this.i = afVar;
        this.a = i;
    }

    boolean a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        return y.c(jSONObjectC, "id") == this.a && y.c(jSONObjectC, "container_id") == this.h.d() && y.b(jSONObjectC, "ad_session_id").equals(this.h.b());
    }

    void a() {
        JSONObject jSONObjectC = this.i.c();
        this.g = y.b(jSONObjectC, "ad_session_id");
        this.b = y.c(jSONObjectC, "x");
        this.c = y.c(jSONObjectC, "y");
        this.d = y.c(jSONObjectC, "width");
        this.e = y.c(jSONObjectC, "height");
        this.f = y.b(jSONObjectC, "color");
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.d, this.e);
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.gravity = 0;
        this.h.addView(this, layoutParams);
        setBackgroundColor(aw.g(this.f));
        this.h.n().add(a.a("ColorView.set_bounds", new ah() { // from class: com.adcolony.sdk.h.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (h.this.a(afVar)) {
                    h.this.b(afVar);
                }
            }
        }, true));
        this.h.n().add(a.a("ColorView.set_visible", new ah() { // from class: com.adcolony.sdk.h.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (h.this.a(afVar)) {
                    h.this.d(afVar);
                }
            }
        }, true));
        this.h.n().add(a.a("ColorView.set_color", new ah() { // from class: com.adcolony.sdk.h.3
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (h.this.a(afVar)) {
                    h.this.c(afVar);
                }
            }
        }, true));
        this.h.o().add("ColorView.set_bounds");
        this.h.o().add("ColorView.set_visible");
        this.h.o().add("ColorView.set_color");
    }

    @Override // android.view.View
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
        y.b(jSONObjectA, "view_id", this.a);
        y.a(jSONObjectA, "ad_session_id", this.g);
        y.b(jSONObjectA, "container_x", this.b + x);
        y.b(jSONObjectA, "container_y", this.c + y);
        y.b(jSONObjectA, "view_x", x);
        y.b(jSONObjectA, "view_y", y);
        y.b(jSONObjectA, "id", this.h.d());
        if (action == 0) {
            new af("AdContainer.on_touch_began", this.h.c(), jSONObjectA).b();
        } else if (action == 1) {
            if (!this.h.r()) {
                lVarA.a(dVarM.f().get(this.g));
            }
            new af("AdContainer.on_touch_ended", this.h.c(), jSONObjectA).b();
        } else if (action == 2) {
            new af("AdContainer.on_touch_moved", this.h.c(), jSONObjectA).b();
        } else if (action == 3) {
            new af("AdContainer.on_touch_cancelled", this.h.c(), jSONObjectA).b();
        } else if (action == 5) {
            int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", ((int) motionEvent.getX(action2)) + this.b);
            y.b(jSONObjectA, "container_y", ((int) motionEvent.getY(action2)) + this.c);
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action2));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action2));
            new af("AdContainer.on_touch_began", this.h.c(), jSONObjectA).b();
        } else if (action == 6) {
            int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", ((int) motionEvent.getX(action3)) + this.b);
            y.b(jSONObjectA, "container_y", ((int) motionEvent.getY(action3)) + this.c);
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action3));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action3));
            if (!this.h.r()) {
                lVarA.a(dVarM.f().get(this.g));
            }
            new af("AdContainer.on_touch_ended", this.h.c(), jSONObjectA).b();
        }
        return true;
    }

    void b(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        this.b = y.c(jSONObjectC, "x");
        this.c = y.c(jSONObjectC, "y");
        this.d = y.c(jSONObjectC, "width");
        this.e = y.c(jSONObjectC, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.width = this.d;
        layoutParams.height = this.e;
        setLayoutParams(layoutParams);
    }

    void c(af afVar) {
        setBackgroundColor(aw.g(y.b(afVar.c(), "color")));
    }

    void d(af afVar) {
        if (y.d(afVar.c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
