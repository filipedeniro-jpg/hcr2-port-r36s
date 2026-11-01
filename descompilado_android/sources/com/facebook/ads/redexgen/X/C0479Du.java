package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Du, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0479Du extends ViewGroup.MarginLayoutParams {
    public final Rect B;
    public boolean C;
    public boolean D;
    public AbstractC0502Er E;

    public C0479Du(int width, int height) {
        super(width, height);
        this.B = new Rect();
        this.C = true;
        this.D = false;
    }

    public C0479Du(Context c, AttributeSet attrs) {
        super(c, attrs);
        this.B = new Rect();
        this.C = true;
        this.D = false;
    }

    public C0479Du(ViewGroup.LayoutParams source) {
        super(source);
        this.B = new Rect();
        this.C = true;
        this.D = false;
    }

    public C0479Du(ViewGroup.MarginLayoutParams source) {
        super(source);
        this.B = new Rect();
        this.C = true;
        this.D = false;
    }

    public C0479Du(C0479Du source) {
        super((ViewGroup.LayoutParams) source);
        this.B = new Rect();
        this.C = true;
        this.D = false;
    }

    public final int A() {
        return this.E.K();
    }

    public final boolean B() {
        return this.E.X();
    }

    public final boolean C() {
        return this.E.U();
    }

    public final boolean D() {
        return this.E.R();
    }
}
