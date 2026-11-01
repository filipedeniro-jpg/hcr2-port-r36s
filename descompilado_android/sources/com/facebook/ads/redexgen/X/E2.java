package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class E2 {
    public int B;
    public boolean C;
    public int D;
    public boolean E;
    public final /* synthetic */ C0484Dz F;

    public E2(C0484Dz this$0) {
        this.F = this$0;
        E();
    }

    public final void A() {
        int iJ;
        if (this.C) {
            iJ = this.F.D.F();
        } else {
            iJ = this.F.D.J();
        }
        this.B = iJ;
    }

    public final void B(View child) {
        if (this.C) {
            this.B = this.F.D.A(child) + this.F.D.L();
        } else {
            this.B = this.F.D.D(child);
        }
        this.D = this.F.w(child);
    }

    public final void C(View child) {
        int estimatedEnd = this.F.D.L();
        if (estimatedEnd >= 0) {
            B(child);
            return;
        }
        int spaceChange = this.F.w(child);
        this.D = spaceChange;
        if (this.C) {
            int iF = this.F.D.F() - estimatedEnd;
            int prevLayoutEnd = this.F.D.A(child);
            int i = iF - prevLayoutEnd;
            int spaceChange2 = this.F.D.F();
            this.B = spaceChange2 - i;
            if (i <= 0) {
                return;
            }
            int spaceChange3 = this.F.D.B(child);
            int i2 = this.B - spaceChange3;
            int iJ = this.F.D.J();
            int spaceChange4 = this.F.D.D(child);
            int layoutStart = Math.min(spaceChange4 - iJ, 0);
            int i3 = i2 - (iJ + layoutStart);
            if (i3 >= 0) {
                return;
            }
            int startMargin = this.B;
            int startReference = -i3;
            this.B = startMargin + Math.min(i, startReference);
            return;
        }
        int iD = this.F.D.D(child);
        int childStart = this.F.D.J();
        int i4 = iD - childStart;
        this.B = iD;
        if (i4 <= 0) {
            return;
        }
        int childStart2 = this.F.D.B(child);
        int i5 = iD + childStart2;
        int startMargin2 = this.F.D.F();
        int previousLayoutEnd = this.F.D.A(child);
        int startMargin3 = (startMargin2 - estimatedEnd) - previousLayoutEnd;
        int estimatedEnd2 = this.F.D.F();
        int previousLayoutEnd2 = Math.min(0, startMargin3);
        int estimatedEnd3 = (estimatedEnd2 - previousLayoutEnd2) - i5;
        if (estimatedEnd3 >= 0) {
            return;
        }
        int startMargin4 = this.B;
        int endMargin = -estimatedEnd3;
        this.B = startMargin4 - Math.min(i4, endMargin);
    }

    public final boolean D(View child, C0499Eo state) {
        C0479Du c0479Du = (C0479Du) child.getLayoutParams();
        return !c0479Du.C() && c0479Du.A() >= 0 && c0479Du.A() < state.B();
    }

    public final void E() {
        this.D = -1;
        this.B = Integer.MIN_VALUE;
        this.C = false;
        this.E = false;
    }

    public final String toString() {
        return "AnchorInfo{mPosition=" + this.D + ", mCoordinate=" + this.B + ", mLayoutFromEnd=" + this.C + ", mValid=" + this.E + '}';
    }
}
