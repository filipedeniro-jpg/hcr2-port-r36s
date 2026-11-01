package com.facebook.ads.redexgen.X;

import net.hockeyapp.android.Strings;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class F8 {
    public int B = 0;
    public int C;
    public int D;
    public int E;
    public int F;

    public final void A(int flags) {
        this.B |= flags;
    }

    private final int B(int x, int y) {
        if (x > y) {
            return 1;
        }
        if (x == y) {
            return 2;
        }
        return 4;
    }

    public final boolean B() {
        if ((this.B & 7) != 0 && (this.B & (B(this.D, this.F) << 0)) == 0) {
            return false;
        }
        if ((this.B & 112) != 0 && (this.B & (B(this.D, this.E) << 4)) == 0) {
            return false;
        }
        if ((this.B & Strings.PERMISSION_UPDATE_TITLE_ID) == 0 || (this.B & (B(this.C, this.F) << 8)) != 0) {
            return (this.B & 28672) == 0 || (this.B & (B(this.C, this.E) << 12)) != 0;
        }
        return false;
    }

    public final void C() {
        this.B = 0;
    }

    public final void D(int rvStart, int rvEnd, int childStart, int childEnd) {
        this.F = rvStart;
        this.E = rvEnd;
        this.D = childStart;
        this.C = childEnd;
    }
}
