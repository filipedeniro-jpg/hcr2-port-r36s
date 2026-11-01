package com.facebook.ads.redexgen.X;

import android.util.SparseArray;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Eo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0499Eo {
    public long C;
    public int D;
    public int E;
    public int K;
    public int L;
    private SparseArray<Object> Q;
    private int R = -1;
    public int J = 0;
    public int B = 0;
    public int I = 1;
    public int H = 0;
    public boolean O = false;
    public boolean F = false;
    public boolean P = false;
    public boolean G = false;
    public boolean N = false;
    public boolean M = false;

    public final void A(int accepted) {
        if ((this.I & accepted) == 0) {
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(accepted) + " but it is " + Integer.toBinaryString(this.I));
        }
    }

    public final int B() {
        return this.F ? this.J - this.B : this.H;
    }

    public final boolean C() {
        return this.M;
    }

    public final boolean D() {
        return this.R != -1;
    }

    public final boolean E() {
        return this.F;
    }

    public final void F(C1G adapter) {
        this.I = 1;
        this.H = adapter.C();
        this.F = false;
        this.P = false;
        this.G = false;
    }

    public final String toString() {
        return "State{mTargetPosition=" + this.R + ", mData=" + this.Q + ", mItemCount=" + this.H + ", mPreviousLayoutItemCount=" + this.J + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.B + ", mStructureChanged=" + this.O + ", mInPreLayout=" + this.F + ", mRunSimpleAnimations=" + this.N + ", mRunPredictiveAnimations=" + this.M + '}';
    }
}
