package com.adcolony.sdk;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.core.view.MotionEventCompat;
import com.ironsource.sdk.constants.Constants;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class u extends GLSurfaceView {
    v a;
    int b;
    int c;
    int d;
    int e;
    int f;
    String g;
    boolean h;
    c i;
    af j;
    boolean k;

    u(Context context, af afVar, int i, c cVar) {
        super(context);
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        this.b = i;
        this.j = afVar;
        this.i = cVar;
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
        y.b(jSONObjectA, "view_id", this.b);
        y.a(jSONObjectA, "ad_session_id", this.g);
        y.b(jSONObjectA, "container_x", this.c + x);
        y.b(jSONObjectA, "container_y", this.d + y);
        y.b(jSONObjectA, "view_x", x);
        y.b(jSONObjectA, "view_y", y);
        y.b(jSONObjectA, "id", this.i.d());
        if (action == 0) {
            new af("AdContainer.on_touch_began", this.i.c(), jSONObjectA).b();
        } else if (action == 1) {
            if (!this.i.r()) {
                lVarA.a(dVarM.f().get(this.g));
            }
            new af("AdContainer.on_touch_ended", this.i.c(), jSONObjectA).b();
        } else if (action == 2) {
            new af("AdContainer.on_touch_moved", this.i.c(), jSONObjectA).b();
        } else if (action == 3) {
            new af("AdContainer.on_touch_cancelled", this.i.c(), jSONObjectA).b();
        } else if (action == 5) {
            int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", ((int) motionEvent.getX(action2)) + this.c);
            y.b(jSONObjectA, "container_y", ((int) motionEvent.getY(action2)) + this.d);
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action2));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action2));
            new af("AdContainer.on_touch_began", this.i.c(), jSONObjectA).b();
        } else if (action == 6) {
            int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", ((int) motionEvent.getX(action3)) + this.c);
            y.b(jSONObjectA, "container_y", ((int) motionEvent.getY(action3)) + this.d);
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action3));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action3));
            if (!this.i.r()) {
                lVarA.a(dVarM.f().get(this.g));
            }
            new af("AdContainer.on_touch_ended", this.i.c(), jSONObjectA).b();
        }
        return true;
    }

    void a() {
        JSONObject jSONObjectC = this.j.c();
        this.g = y.b(jSONObjectC, "ad_session_id");
        this.c = y.c(jSONObjectC, "x");
        this.d = y.c(jSONObjectC, "y");
        this.e = y.c(jSONObjectC, "width");
        this.f = y.c(jSONObjectC, "height");
        this.h = y.d(jSONObjectC, Constants.ParametersKeys.TRANSPARENT);
        setEGLConfigChooser(8, 8, 8, 8, 16, 8);
        if (this.h) {
            getHolder().setFormat(-3);
            setZOrderOnTop(true);
        } else {
            getHolder().setFormat(1);
        }
        this.a = new v(this, true, this.g);
        setRenderer(this.a);
        this.i.n().add(a.a("RenderView.set_visible", new ah() { // from class: com.adcolony.sdk.u.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (u.this.a(afVar)) {
                    u.this.c(afVar);
                }
            }
        }, true));
        this.i.n().add(a.a("RenderView.set_bounds", new ah() { // from class: com.adcolony.sdk.u.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (u.this.a(afVar)) {
                    u.this.b(afVar);
                }
            }
        }, true));
        this.i.o().add("RenderView.set_visible");
        this.i.o().add("RenderView.set_bounds");
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.e, this.f);
        layoutParams.setMargins(this.c, this.d, 0, 0);
        layoutParams.gravity = 0;
        this.i.addView(this, layoutParams);
    }

    boolean b() {
        if (this.k) {
            return false;
        }
        this.k = true;
        this.a.a();
        return true;
    }

    @Override // android.opengl.GLSurfaceView
    protected void finalize() throws Throwable {
        b();
    }

    boolean a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        return y.c(jSONObjectC, "id") == this.b && y.c(jSONObjectC, "container_id") == this.i.d() && y.b(jSONObjectC, "ad_session_id").equals(this.i.b());
    }

    void b(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        this.c = y.c(jSONObjectC, "x");
        this.d = y.c(jSONObjectC, "y");
        this.e = y.c(jSONObjectC, "width");
        this.f = y.c(jSONObjectC, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.c, this.d, 0, 0);
        layoutParams.width = this.e;
        layoutParams.height = this.f;
        setLayoutParams(layoutParams);
        getHolder().setFixedSize(this.e, this.f);
    }

    void c(af afVar) {
        if (y.d(afVar.c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
