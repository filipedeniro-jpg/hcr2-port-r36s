package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class EX {
    public int B;
    public int C;
    public int D;
    public int E;

    public final EX A(AbstractC0502Er holder) {
        return B(holder, 0);
    }

    private final EX B(AbstractC0502Er holder, int flags) {
        View view = holder.B;
        this.D = view.getLeft();
        this.C = view.getTop();
        this.E = view.getRight();
        this.B = view.getBottom();
        return this;
    }
}
