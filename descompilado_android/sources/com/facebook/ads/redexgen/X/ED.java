package com.facebook.ads.redexgen.X;

import android.graphics.Rect;
import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class ED {
    public final AbstractC0481Dw B;
    public final Rect C;
    private int D;

    public abstract int A(View view);

    public abstract int B(View view);

    public abstract int C(View view);

    public abstract int D(View view);

    public abstract int E();

    public abstract int F();

    public abstract int G();

    public abstract int H();

    public abstract int J();

    public abstract int K();

    public abstract int M(View view);

    public abstract int N(View view);

    public abstract void O(int i);

    private ED(AbstractC0481Dw layoutManager) {
        this.D = Integer.MIN_VALUE;
        this.C = new Rect();
        this.B = layoutManager;
    }

    public /* synthetic */ ED(AbstractC0481Dw x0, EE x1) {
        this(x0);
    }

    public static ED B(AbstractC0481Dw layoutManager) {
        return new EE(layoutManager);
    }

    public static ED C(AbstractC0481Dw layoutManager, int orientation) {
        switch (orientation) {
            case 0:
                return B(layoutManager);
            case 1:
                return D(layoutManager);
            default:
                throw new IllegalArgumentException("invalid orientation");
        }
    }

    public static ED D(AbstractC0481Dw layoutManager) {
        return new EF(layoutManager);
    }

    public final void I() {
        this.D = K();
    }

    public final int L() {
        if (Integer.MIN_VALUE == this.D) {
            return 0;
        }
        return K() - this.D;
    }
}
