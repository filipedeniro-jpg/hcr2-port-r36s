package com.facebook.ads.redexgen.X;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class EP implements DM {
    public final /* synthetic */ C0503Es B;

    public EP(C0503Es this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final int PC(View view) {
        return this.B.indexOfChild(view);
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final void UB(View child, int index) {
        this.B.addView(child, index);
        this.B.M(child);
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final void XE(View child) {
        AbstractC0502Er abstractC0502ErQ = C0503Es.Q(child);
        if (abstractC0502ErQ == null) {
            return;
        }
        abstractC0502ErQ.R(this.B);
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final void ZB(View child, int index, ViewGroup.LayoutParams layoutParams) {
        AbstractC0502Er abstractC0502ErQ = C0503Es.Q(child);
        if (abstractC0502ErQ != null) {
            if (!abstractC0502ErQ.W() && !abstractC0502ErQ.f()) {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + abstractC0502ErQ + this.B.S());
            }
            abstractC0502ErQ.F();
        }
        this.B.attachViewToParent(child, index, layoutParams);
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final void mB(int offset) {
        AbstractC0502Er abstractC0502ErQ;
        View viewVB = vB(offset);
        if (viewVB != null && (abstractC0502ErQ = C0503Es.Q(viewVB)) != null) {
            if (abstractC0502ErQ.W() && !abstractC0502ErQ.f()) {
                throw new IllegalArgumentException("called detach on an already detached child " + abstractC0502ErQ + this.B.S());
            }
            abstractC0502ErQ.B(256);
        }
        this.B.detachViewFromParent(offset);
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final void mF() {
        int count = wB();
        for (int i = 0; i < count; i++) {
            View viewVB = vB(i);
            this.B.N(viewVB);
            viewVB.clearAnimation();
        }
        this.B.removeAllViews();
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final void nF(int index) {
        View childAt = this.B.getChildAt(index);
        if (childAt != null) {
            this.B.N(childAt);
            childAt.clearAnimation();
        }
        this.B.removeViewAt(index);
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final View vB(int offset) {
        return this.B.getChildAt(offset);
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final int wB() {
        return this.B.getChildCount();
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final void xD(View child) {
        AbstractC0502Er abstractC0502ErQ = C0503Es.Q(child);
        if (abstractC0502ErQ == null) {
            return;
        }
        abstractC0502ErQ.Q(this.B);
    }

    @Override // com.facebook.ads.redexgen.X.DM
    public final AbstractC0502Er zB(View view) {
        return C0503Es.Q(view);
    }
}
