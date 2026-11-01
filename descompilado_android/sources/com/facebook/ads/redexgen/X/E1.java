package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class E1 extends C0503Es implements View.OnTouchListener {
    public int B;
    public final int C;
    private boolean D;
    private boolean E;
    private C0484Dz F;
    private int G;
    private E0 H;

    public E1(Context context) {
        super(context);
        this.B = 0;
        this.G = 0;
        this.D = true;
        this.E = false;
        this.C = E();
        setOnTouchListener(this);
    }

    public E1(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.B = 0;
        this.G = 0;
        this.D = true;
        this.E = false;
        this.C = E();
        setOnTouchListener(this);
    }

    public E1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.B = 0;
        this.G = 0;
        this.D = true;
        this.E = false;
        this.C = E();
        setOnTouchListener(this);
    }

    private int E() {
        return ((int) getContext().getResources().getDisplayMetrics().density) * 10;
    }

    private int F(int position, int delta) {
        return Math.min(position + delta, getItemCount() - 1);
    }

    public void FA(int position, boolean isAnimation) {
        if (getAdapter() == null) {
            return;
        }
        this.B = position;
        if (isAnimation) {
            v(position);
        } else {
            CA(position);
        }
    }

    private int G(int rawX) {
        int i = this.G - rawX;
        int iIC = this.H.IC(i);
        if (i > this.C) {
            return F(this.B, iIC);
        }
        if (i < (-this.C)) {
            return H(this.B, iIC);
        }
        return this.B;
    }

    private int H(int position, int delta) {
        return Math.max(position - delta, 0);
    }

    public int getCurrentPosition() {
        return this.B;
    }

    private int getItemCount() {
        if (getAdapter() == null) {
            return 0;
        }
        return getAdapter().C();
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent event) {
        int rawX = (int) event.getRawX();
        int actionMasked = event.getActionMasked();
        if (actionMasked == 1 || actionMasked == 6 || actionMasked == 3 || actionMasked == 4) {
            if (this.E) {
                FA(G(rawX), true);
            }
            this.D = true;
            this.E = false;
            return true;
        }
        if (actionMasked != 0 && actionMasked != 5 && (!this.D || actionMasked != 2)) {
            return false;
        }
        this.G = rawX;
        if (this.D) {
            this.D = false;
        }
        this.E = true;
        return false;
    }

    @Override // com.facebook.ads.redexgen.X.C0503Es
    public void setLayoutManager(AbstractC0481Dw layout) {
        if (!(layout instanceof C0484Dz)) {
            throw new IllegalArgumentException("SnapRecyclerView only supports LinearLayoutManager");
        }
        super.setLayoutManager(layout);
        this.F = (C0484Dz) layout;
    }

    public void setSnapDelegate(E0 delegate) {
        this.H = delegate;
    }
}
