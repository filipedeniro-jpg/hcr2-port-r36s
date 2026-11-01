package com.facebook.ads.redexgen.X;

import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class E7 {
    private AbstractC0481Dw B;
    private boolean C;
    private C0503Es D;
    private boolean F;
    private View H;
    private int G = -1;
    private final C0497Em E = new C0497Em(0, 0);

    public abstract void H(int i, int i2, C0499Eo c0499Eo, C0497Em c0497Em);

    public abstract void I();

    public abstract void J();

    public abstract void K(View view, C0499Eo c0499Eo, C0497Em c0497Em);

    public final int A() {
        return this.D.R.b();
    }

    @Nullable
    public final AbstractC0481Dw B() {
        return this.B;
    }

    public final int C() {
        return this.G;
    }

    private final View C(int position) {
        return this.D.R.V(position);
    }

    private final int D(View view) {
        return this.D.Y(view);
    }

    public final boolean D() {
        return this.C;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(int dx, int dy) {
        C0503Es c0503Es = this.D;
        if (!this.F || this.G == -1 || c0503Es == null) {
            N();
        }
        this.C = false;
        if (this.H != null) {
            if (D(this.H) == this.G) {
                K(this.H, c0503Es.Z, this.E);
                this.E.C(c0503Es);
                N();
            } else {
                Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                this.H = null;
            }
        }
        if (this.F) {
            H(dx, dy, c0503Es.Z, this.E);
            boolean zA = this.E.A();
            this.E.C(c0503Es);
            if (zA) {
                if (this.F) {
                    this.C = true;
                    c0503Es.d.B();
                } else {
                    N();
                }
            }
        }
    }

    public final boolean E() {
        return this.F;
    }

    public final void F(PointF scrollVector) {
        float fSqrt = (float) Math.sqrt((scrollVector.x * scrollVector.x) + (scrollVector.y * scrollVector.y));
        scrollVector.x /= fSqrt;
        scrollVector.y /= fSqrt;
    }

    public final void G(View child) {
        if (D(child) == C()) {
            this.H = child;
        }
    }

    public final void L(int targetPosition) {
        this.G = targetPosition;
    }

    public final void M(C0503Es recyclerView, AbstractC0481Dw layoutManager) {
        this.D = recyclerView;
        this.B = layoutManager;
        if (this.G == -1) {
            throw new IllegalArgumentException("Invalid target position");
        }
        this.D.Z.R = this.G;
        this.F = true;
        this.C = true;
        this.H = C(C());
        I();
        this.D.d.B();
    }

    public final void N() {
        if (!this.F) {
            return;
        }
        J();
        this.D.Z.R = -1;
        this.H = null;
        this.G = -1;
        this.C = false;
        this.F = false;
        this.B.O(this);
        this.B = null;
        this.D = null;
    }
}
