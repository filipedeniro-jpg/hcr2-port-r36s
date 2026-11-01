package com.facebook.ads.redexgen.X;

import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Er, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC0502Er {
    private static final List<Object> T = Collections.EMPTY_LIST;
    public final View B;
    public WeakReference<C0503Es> E;
    public C0503Es G;
    private int O;
    public int J = -1;
    public int F = -1;
    public long C = -1;
    public int D = -1;
    public int K = -1;
    public AbstractC0502Er L = null;
    public AbstractC0502Er M = null;
    public List<Object> H = null;
    public List<Object> N = null;
    private int Q = 0;
    private C0491Eg R = null;
    private boolean P = false;
    private int S = 0;

    @VisibleForTesting
    public int I = -1;

    public AbstractC0502Er(View itemView) {
        if (itemView == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.B = itemView;
    }

    public final void A(Object payload) {
        if (payload == null) {
            B(1024);
        } else {
            if ((this.O & 1024) != 0) {
                return;
            }
            N();
            this.H.add(payload);
        }
    }

    public final void B(int flags) {
        this.O |= flags;
    }

    public final void C() {
        this.F = -1;
        this.K = -1;
    }

    public final void D() {
        if (this.H != null) {
            this.H.clear();
        }
        this.O &= -1025;
    }

    public final void E() {
        this.O &= -33;
    }

    public final void F() {
        this.O &= -257;
    }

    public final void G(int mNewPosition, int offset, boolean applyToPreLayout) {
        B(8);
        Z(offset, applyToPreLayout);
        this.J = mNewPosition;
    }

    public final int H() {
        if (this.G == null) {
            return -1;
        }
        return this.G.X(this);
    }

    public final long I() {
        return this.C;
    }

    public final int J() {
        return this.D;
    }

    public final int K() {
        return this.K == -1 ? this.J : this.K;
    }

    public final int L() {
        return this.F;
    }

    public final boolean M() {
        return (this.O & 32) != 0;
    }

    /* JADX INFO: renamed from: N, reason: collision with other method in class */
    public final List<Object> m247N() {
        if ((this.O & 1024) == 0) {
            if (this.H == null || this.H.size() == 0) {
                return T;
            }
            return this.N;
        }
        return T;
    }

    private void N() {
        if (this.H == null) {
            this.H = new ArrayList();
            this.N = Collections.unmodifiableList(this.H);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean O() {
        return (this.O & 16) == 0 && C9Y.N(this.B);
    }

    public final boolean O(int flags) {
        return (this.O & flags) != 0;
    }

    private final boolean P() {
        return (this.O & 512) != 0 || R();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q(C0503Es parent) {
        this.S = C9Y.S(this.B);
        parent.DA(this, 4);
    }

    public final boolean Q() {
        return (this.O & 1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R(C0503Es parent) {
        parent.DA(this, this.S);
        this.S = 0;
    }

    public final boolean R() {
        return (this.O & 4) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean S() {
        return (this.O & 16) != 0;
    }

    public final boolean T() {
        return (this.O & 16) == 0 && !C9Y.N(this.B);
    }

    public final boolean U() {
        return (this.O & 8) != 0;
    }

    public final boolean V() {
        return this.R != null;
    }

    public final boolean W() {
        return (this.O & 256) != 0;
    }

    public final boolean X() {
        return (this.O & 2) != 0;
    }

    public final boolean Y() {
        return (this.O & 2) != 0;
    }

    public final void Z(int offset, boolean applyToPreLayout) {
        if (this.F == -1) {
            this.F = this.J;
        }
        if (this.K == -1) {
            this.K = this.J;
        }
        if (applyToPreLayout) {
            this.K += offset;
        }
        this.J += offset;
        if (this.B.getLayoutParams() != null) {
            ((C0479Du) this.B.getLayoutParams()).C = true;
        }
    }

    public final void a() {
        this.O = 0;
        this.J = -1;
        this.F = -1;
        this.C = -1L;
        this.K = -1;
        this.Q = 0;
        this.L = null;
        this.M = null;
        D();
        this.S = 0;
        this.I = -1;
        C0503Es.O(this);
    }

    public final void b() {
        if (this.F == -1) {
            this.F = this.J;
        }
    }

    public final void c(int flags, int mask) {
        this.O = (this.O & (mask ^ (-1))) | (flags & mask);
    }

    public final void d(boolean recyclable) {
        this.Q = recyclable ? this.Q - 1 : this.Q + 1;
        if (this.Q < 0) {
            this.Q = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
        } else if (!recyclable && this.Q == 1) {
            this.O |= 16;
        } else {
            if (!recyclable || this.Q != 0) {
                return;
            }
            this.O &= -17;
        }
    }

    public final void e(C0491Eg recycler, boolean isChangeScrap) {
        this.R = recycler;
        this.P = isChangeScrap;
    }

    public final boolean f() {
        return (this.O & 128) != 0;
    }

    public final void g() {
        this.R.Z(this);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + this.J + " id=" + this.C + ", oldPos=" + this.F + ", pLpos:" + this.K);
        if (V()) {
            sb.append(" scrap ").append(this.P ? "[changeScrap]" : "[attachedScrap]");
        }
        if (R()) {
            sb.append(" invalid");
        }
        if (!Q()) {
            sb.append(" unbound");
        }
        if (Y()) {
            sb.append(" update");
        }
        if (U()) {
            sb.append(" removed");
        }
        if (f()) {
            sb.append(" ignored");
        }
        if (W()) {
            sb.append(" tmpDetached");
        }
        if (!T()) {
            sb.append(" not recyclable(" + this.Q + ")");
        }
        if (P()) {
            sb.append(" undefined adapter position");
        }
        if (this.B.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }
}
