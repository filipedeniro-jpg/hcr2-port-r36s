package com.facebook.ads.redexgen.X;

import android.view.View;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class FA {
    public F8 B = new F8();
    public final EZ C;

    public FA(EZ callback) {
        this.C = callback;
    }

    public final View A(int fromIndex, int toIndex, int preferredBoundFlags, int acceptableBoundFlags) {
        int next = this.C.GC();
        int iFC = this.C.FC();
        int i = toIndex > fromIndex ? 1 : -1;
        View view = null;
        while (fromIndex != toIndex) {
            View viewVB = this.C.vB(fromIndex);
            this.B.D(next, iFC, this.C.yB(viewVB), this.C.xB(viewVB));
            if (preferredBoundFlags != 0) {
                this.B.C();
                this.B.A(preferredBoundFlags);
                if (this.B.B()) {
                    return viewVB;
                }
            }
            if (acceptableBoundFlags != 0) {
                this.B.C();
                this.B.A(acceptableBoundFlags);
                if (this.B.B()) {
                    view = viewVB;
                }
            }
            fromIndex += i;
        }
        return view;
    }
}
