package com.facebook.ads.redexgen.X;

import android.view.View;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class E4 {
    public int B;
    public int C;
    public boolean E;
    public int G;
    public int H;
    public int I;
    public int J;
    public int M;
    public boolean K = true;
    public int D = 0;
    public boolean F = false;
    public List<AbstractC0502Er> L = null;

    public final void A() {
        B((View) null);
    }

    private final void B(View ignore) {
        View viewC = C(ignore);
        if (viewC == null) {
            this.C = -1;
        } else {
            this.C = ((C0479Du) viewC.getLayoutParams()).A();
        }
    }

    public final boolean B(C0499Eo state) {
        return this.C >= 0 && this.C < state.B();
    }

    private final View C(View ignore) {
        int iA;
        int i = this.L.size();
        View view = null;
        int size = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < i; i2++) {
            View view2 = this.L.get(i2).B;
            C0479Du c0479Du = (C0479Du) view2.getLayoutParams();
            if (view2 != ignore && !c0479Du.C() && (iA = (c0479Du.A() - this.C) * this.G) >= 0 && iA < size) {
                view = view2;
                size = iA;
                if (iA == 0) {
                    break;
                }
            }
        }
        return view;
    }

    public final View C(C0491Eg recycler) {
        if (this.L != null) {
            return D();
        }
        View viewJ = recycler.J(this.C);
        this.C += this.G;
        return viewJ;
    }

    private View D() {
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            View view = this.L.get(i).B;
            C0479Du c0479Du = (C0479Du) view.getLayoutParams();
            if (!c0479Du.C() && this.C == c0479Du.A()) {
                B(view);
                return view;
            }
        }
        return null;
    }
}
