package com.facebook.ads.redexgen.X;

import android.util.Log;
import android.view.animation.Interpolator;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Em, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0497Em {
    private boolean B;
    private int C;
    private int D;
    private int E;
    private int F;
    private Interpolator G;
    private int H;

    public C0497Em(int dx, int dy) {
        this(dx, dy, Integer.MIN_VALUE, null);
    }

    private C0497Em(int dx, int dy, int duration, Interpolator interpolator) {
        this.H = -1;
        this.B = false;
        this.C = 0;
        this.E = dx;
        this.F = dy;
        this.D = duration;
        this.G = interpolator;
    }

    public final boolean A() {
        return this.H >= 0;
    }

    private void B() {
        if (this.G != null && this.D < 1) {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
        if (this.D < 1) {
            throw new IllegalStateException("Scroll duration must be a positive number");
        }
    }

    public final void B(int targetPosition) {
        this.H = targetPosition;
    }

    public final void C(C0503Es recyclerView) {
        if (this.H >= 0) {
            int i = this.H;
            this.H = -1;
            recyclerView.f(i);
            this.B = false;
            return;
        }
        if (this.B) {
            B();
            if (this.G == null) {
                if (this.D == Integer.MIN_VALUE) {
                    recyclerView.d.C(this.E, this.F);
                } else {
                    recyclerView.d.D(this.E, this.F, this.D);
                }
            } else {
                recyclerView.d.E(this.E, this.F, this.D, this.G);
            }
            this.C++;
            if (this.C > 10) {
                Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
            }
            this.B = false;
            return;
        }
        this.C = 0;
    }

    public final void D(int dx, int dy, int duration, Interpolator interpolator) {
        this.E = dx;
        this.F = dy;
        this.D = duration;
        this.G = interpolator;
        this.B = true;
    }
}
