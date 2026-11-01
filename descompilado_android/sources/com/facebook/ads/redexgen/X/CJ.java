package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class CJ extends RelativeLayout {
    private C0445Cm B;
    private int C;
    private int D;
    private int E;
    private boolean F;

    @Nullable
    private InterfaceC0468Dj G;
    private AbstractC03789x H;
    private int I;
    private int J;

    public CJ(Context context, AbstractC03789x scrollingView, int dragRange, int dragTop) {
        super(context);
        this.F = true;
        this.E = 0;
        this.I = 0;
        this.B = C0445Cm.B(this, 1.0f, new CK(this));
        this.H = scrollingView;
        this.C = dragTop;
        this.H.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.J = dragRange;
        this.D = this.J;
        this.H.offsetTopAndBottom(this.J);
        this.I = this.J;
        addView(this.H);
        setBackgroundColor(0);
    }

    public final boolean A() {
        return this.F;
    }

    public final void B() {
        this.H.offsetTopAndBottom(this.J);
        this.I = this.J;
        N();
    }

    public final void C() {
        this.H.offsetTopAndBottom(this.C);
        this.I = this.C;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        this.F = false;
        if (this.G != null) {
            this.G.kD();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        this.F = true;
        if (this.G != null) {
            this.G.iE();
        }
    }

    @Override // android.view.View
    public final void computeScroll() {
        if (this.B.E(true)) {
            C9Y.J(this);
        } else {
            this.I = this.H.getTop();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent event) {
        return this.F && this.B.I(this.H, (int) event.getX(), (int) event.getY()) && this.H.getScrollY() == 0;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.H.offsetTopAndBottom(this.I);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        this.H.C(event);
        if (!this.B.I(this.H, x, y)) {
            return super.onTouchEvent(event);
        }
        this.B.H(event);
        return true;
    }

    public void setDragListener(InterfaceC0468Dj listener) {
        this.G = listener;
    }

    public void setDragRange(int dragRange) {
        this.J = dragRange;
        this.B.A(this.H, 0, this.J);
    }
}
