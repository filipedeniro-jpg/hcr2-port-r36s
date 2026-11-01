package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public class bc extends RelativeLayout {
    private com.chartboost.sdk.e.a a;
    private ax b;
    private ax c;
    private final com.chartboost.sdk.Model.c d;

    public void b() {
    }

    public bc(Context context, com.chartboost.sdk.Model.c cVar) {
        super(context);
        this.d = cVar;
        if (cVar.p.b == 0) {
            this.b = new ax(context);
            addView(this.b, new RelativeLayout.LayoutParams(-1, -1));
            this.c = new ax(context);
            addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
            this.c.setVisibility(8);
        }
    }

    public void a() {
        if (this.a == null) {
            this.a = this.d.k();
            com.chartboost.sdk.e.a aVar = this.a;
            if (aVar != null) {
                addView(aVar, new RelativeLayout.LayoutParams(-1, -1));
                this.a.a();
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        performClick();
        return true;
    }

    public ax c() {
        return this.b;
    }

    public View d() {
        return this.a;
    }

    public com.chartboost.sdk.Model.c e() {
        return this.d;
    }

    public boolean f() {
        com.chartboost.sdk.e.a aVar = this.a;
        return aVar != null && aVar.getVisibility() == 0;
    }

    @Override // android.view.View
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
