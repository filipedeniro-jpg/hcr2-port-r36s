package com.adcolony.sdk;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.view.MotionEventCompat;
import java.io.File;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class w extends ImageView {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private boolean g;
    private boolean h;
    private String i;
    private String j;
    private af k;
    private c l;

    w(Context context, af afVar, int i, c cVar) {
        super(context);
        this.a = i;
        this.k = afVar;
        this.l = cVar;
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
        y.a(jSONObjectA, "ad_session_id", this.j);
        y.b(jSONObjectA, "container_x", this.b + x);
        y.b(jSONObjectA, "container_y", this.c + y);
        y.b(jSONObjectA, "view_x", x);
        y.b(jSONObjectA, "view_y", y);
        y.b(jSONObjectA, "id", this.l.getId());
        if (action == 0) {
            new af("AdContainer.on_touch_began", this.l.c(), jSONObjectA).b();
            return true;
        }
        if (action == 1) {
            if (!this.l.r()) {
                lVarA.a(dVarM.f().get(this.j));
            }
            if (x > 0 && x < this.d && y > 0 && y < this.e) {
                new af("AdContainer.on_touch_ended", this.l.c(), jSONObjectA).b();
                return true;
            }
            new af("AdContainer.on_touch_cancelled", this.l.c(), jSONObjectA).b();
            return true;
        }
        if (action == 2) {
            new af("AdContainer.on_touch_moved", this.l.c(), jSONObjectA).b();
            return true;
        }
        if (action == 3) {
            new af("AdContainer.on_touch_cancelled", this.l.c(), jSONObjectA).b();
            return true;
        }
        if (action == 5) {
            int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", ((int) motionEvent.getX(action2)) + this.b);
            y.b(jSONObjectA, "container_y", ((int) motionEvent.getY(action2)) + this.c);
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action2));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action2));
            new af("AdContainer.on_touch_began", this.l.c(), jSONObjectA).b();
            return true;
        }
        if (action != 6) {
            return true;
        }
        int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        int x2 = (int) motionEvent.getX(action3);
        int y2 = (int) motionEvent.getY(action3);
        y.b(jSONObjectA, "container_x", ((int) motionEvent.getX(action3)) + this.b);
        y.b(jSONObjectA, "container_y", ((int) motionEvent.getY(action3)) + this.c);
        y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action3));
        y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action3));
        if (!this.l.r()) {
            lVarA.a(dVarM.f().get(this.j));
        }
        if (x2 > 0 && x2 < this.d && y2 > 0 && y2 < this.e) {
            new af("AdContainer.on_touch_ended", this.l.c(), jSONObjectA).b();
            return true;
        }
        new af("AdContainer.on_touch_cancelled", this.l.c(), jSONObjectA).b();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        return y.c(jSONObjectC, "id") == this.a && y.c(jSONObjectC, "container_id") == this.l.d() && y.b(jSONObjectC, "ad_session_id").equals(this.l.b());
    }

    void a() {
        JSONObject jSONObjectC = this.k.c();
        this.j = y.b(jSONObjectC, "ad_session_id");
        this.b = y.c(jSONObjectC, "x");
        this.c = y.c(jSONObjectC, "y");
        this.d = y.c(jSONObjectC, "width");
        this.e = y.c(jSONObjectC, "height");
        this.i = y.b(jSONObjectC, "filepath");
        this.f = y.d(jSONObjectC, com.my.target.i.N);
        this.g = y.d(jSONObjectC, "invert_y");
        this.h = y.d(jSONObjectC, "wrap_content");
        setImageURI(Uri.fromFile(new File(this.i)));
        if (this.f) {
            float fO = (this.e * a.a().n().o()) / getDrawable().getIntrinsicHeight();
            this.e = (int) (getDrawable().getIntrinsicHeight() * fO);
            this.d = (int) (getDrawable().getIntrinsicWidth() * fO);
            this.b -= this.d;
            this.c = this.g ? this.c + this.e : this.c - this.e;
        }
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = this.h ? new FrameLayout.LayoutParams(-2, -2) : new FrameLayout.LayoutParams(this.d, this.e);
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.gravity = 0;
        this.l.addView(this, layoutParams);
        this.l.n().add(a.a("ImageView.set_visible", new ah() { // from class: com.adcolony.sdk.w.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (w.this.a(afVar)) {
                    w.this.d(afVar);
                }
            }
        }, true));
        this.l.n().add(a.a("ImageView.set_bounds", new ah() { // from class: com.adcolony.sdk.w.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (w.this.a(afVar)) {
                    w.this.b(afVar);
                }
            }
        }, true));
        this.l.n().add(a.a("ImageView.set_image", new ah() { // from class: com.adcolony.sdk.w.3
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (w.this.a(afVar)) {
                    w.this.c(afVar);
                }
            }
        }, true));
        this.l.o().add("ImageView.set_visible");
        this.l.o().add("ImageView.set_bounds");
        this.l.o().add("ImageView.set_image");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        this.b = y.c(jSONObjectC, "x");
        this.c = y.c(jSONObjectC, "y");
        this.d = y.c(jSONObjectC, "width");
        this.e = y.c(jSONObjectC, "height");
        if (this.f) {
            float fO = (this.e * a.a().n().o()) / getDrawable().getIntrinsicHeight();
            this.e = (int) (getDrawable().getIntrinsicHeight() * fO);
            this.d = (int) (getDrawable().getIntrinsicWidth() * fO);
            this.b -= this.d;
            this.c -= this.e;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.b, this.c, 0, 0);
        layoutParams.width = this.d;
        layoutParams.height = this.e;
        setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(af afVar) {
        this.i = y.b(afVar.c(), "filepath");
        setImageURI(Uri.fromFile(new File(this.i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(af afVar) {
        if (y.d(afVar.c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    int[] b() {
        return new int[]{this.b, this.c, this.d, this.e};
    }
}
