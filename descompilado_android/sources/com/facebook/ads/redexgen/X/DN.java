package com.facebook.ads.redexgen.X;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class DN {
    public final DM C;
    public final DL B = new DL();
    public final List<View> D = new ArrayList();

    public DN(DM callback) {
        this.C = callback;
    }

    public final void A(View child, int index, boolean hidden) {
        int iB;
        if (index < 0) {
            iB = this.C.wB();
        } else {
            iB = B(index);
        }
        this.B.D(iB, hidden);
        if (hidden) {
            C(child);
        }
        this.C.UB(child, iB);
    }

    private int B(int index) {
        if (index < 0) {
            return -1;
        }
        int iWB = this.C.wB();
        int removedBefore = index;
        while (removedBefore < iWB) {
            int iB = index - (removedBefore - this.B.B(removedBefore));
            if (iB == 0) {
                while (this.B.C(removedBefore)) {
                    removedBefore++;
                }
                return removedBefore;
            }
            removedBefore += iB;
        }
        return -1;
    }

    public final void B(View child, boolean hidden) {
        A(child, -1, hidden);
    }

    private void C(View child) {
        this.D.add(child);
        this.C.xD(child);
    }

    public final void C(View child, int index, ViewGroup.LayoutParams layoutParams, boolean hidden) {
        int iB;
        if (index < 0) {
            iB = this.C.wB();
        } else {
            iB = B(index);
        }
        this.B.D(iB, hidden);
        if (hidden) {
            C(child);
        }
        this.C.ZB(child, iB, layoutParams);
    }

    public final void D(int index) {
        int iB = B(index);
        this.B.E(iB);
        this.C.mB(iB);
    }

    private boolean D(View child) {
        if (!this.D.remove(child)) {
            return false;
        }
        this.C.XE(child);
        return true;
    }

    public final View E(int position) {
        int size = this.D.size();
        for (int i = 0; i < size; i++) {
            View view = this.D.get(i);
            AbstractC0502Er abstractC0502ErZB = this.C.zB(view);
            int i2 = abstractC0502ErZB.K();
            if (i2 == position && !abstractC0502ErZB.R() && !abstractC0502ErZB.U()) {
                return view;
            }
        }
        return null;
    }

    public final View F(int index) {
        return this.C.vB(B(index));
    }

    public final int G() {
        return this.C.wB() - this.D.size();
    }

    public final View H(int index) {
        return this.C.vB(index);
    }

    public final int I() {
        return this.C.wB();
    }

    public final void J(View view) {
        int iPC = this.C.PC(view);
        if (iPC < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        this.B.G(iPC);
        C(view);
    }

    public final int K(View child) {
        int iPC = this.C.PC(child);
        if (iPC == -1 || this.B.C(iPC)) {
            return -1;
        }
        return iPC - this.B.B(iPC);
    }

    public final boolean L(View view) {
        return this.D.contains(view);
    }

    public final void M() {
        this.B.F();
        for (int size = this.D.size() - 1; size >= 0; size--) {
            this.C.XE(this.D.get(size));
            this.D.remove(size);
        }
        this.C.mF();
    }

    public final void N(View view) {
        int iPC = this.C.PC(view);
        if (iPC < 0) {
            return;
        }
        if (this.B.E(iPC)) {
            D(view);
        }
        this.C.nF(iPC);
    }

    public final void O(int index) {
        int iB = B(index);
        View viewVB = this.C.vB(iB);
        if (viewVB == null) {
            return;
        }
        if (this.B.E(iB)) {
            D(viewVB);
        }
        this.C.nF(iB);
    }

    public final boolean P(View view) {
        int iPC = this.C.PC(view);
        if (iPC == -1) {
            D(view);
            return true;
        }
        if (this.B.C(iPC)) {
            this.B.E(iPC);
            D(view);
            this.C.nF(iPC);
            return true;
        }
        return false;
    }

    public final void Q(View view) {
        int iPC = this.C.PC(view);
        if (iPC < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (!this.B.C(iPC)) {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
        this.B.A(iPC);
        D(view);
    }

    public final String toString() {
        return this.B.toString() + ", hidden list:" + this.D.size();
    }
}
