package com.facebook.ads.redexgen.X;

import com.ironsource.sdk.constants.Constants;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DI {
    public int B;
    public int C;
    public Object D;
    public int E;

    public DI(int cmd, int positionStart, int itemCount, Object payload) {
        this.B = cmd;
        this.E = positionStart;
        this.C = itemCount;
        this.D = payload;
    }

    private final String B() {
        switch (this.B) {
            case 1:
                return "add";
            case 2:
                return "rm";
            case 3:
            case 5:
            case 6:
            case 7:
            default:
                return "??";
            case 4:
                return "up";
            case 8:
                return "mv";
        }
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DI di = (DI) o;
        if (this.B != di.B) {
            return false;
        }
        if (this.B == 8 && Math.abs(this.C - this.E) == 1 && this.C == di.E && this.E == di.C) {
            return true;
        }
        if (this.C == di.C && this.E == di.E) {
            if (this.D != null) {
                return this.D.equals(di.D);
            }
            return di.D == null;
        }
        return false;
    }

    public final int hashCode() {
        return (((this.B * 31) + this.E) * 31) + this.C;
    }

    public final String toString() {
        return Integer.toHexString(System.identityHashCode(this)) + Constants.RequestParameters.LEFT_BRACKETS + B() + ",s:" + this.E + "c:" + this.C + ",p:" + this.D + Constants.RequestParameters.RIGHT_BRACKETS;
    }
}
