package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DL {
    public long B = 0;
    public DL C;

    public final void A(int index) {
        if (index >= 64) {
            if (this.C != null) {
                this.C.A(index - 64);
                return;
            }
            return;
        }
        this.B &= (1 << index) ^ (-1);
    }

    public final int B(int index) {
        if (this.C == null) {
            if (index >= 64) {
                return Long.bitCount(this.B);
            }
            return Long.bitCount(this.B & ((1 << index) - 1));
        }
        if (index < 64) {
            return Long.bitCount(this.B & ((1 << index) - 1));
        }
        return this.C.B(index - 64) + Long.bitCount(this.B);
    }

    private void B() {
        if (this.C == null) {
            this.C = new DL();
        }
    }

    public final boolean C(int index) {
        if (index < 64) {
            return (this.B & (1 << index)) != 0;
        }
        B();
        return this.C.C(index - 64);
    }

    public final void D(int index, boolean value) {
        if (index >= 64) {
            B();
            this.C.D(index - 64, value);
            return;
        }
        boolean z = (this.B & Long.MIN_VALUE) != 0;
        long j = (1 << index) - 1;
        this.B = (this.B & j) | ((this.B & ((-1) ^ j)) << 1);
        if (value) {
            G(index);
        } else {
            A(index);
        }
        if (!z && this.C == null) {
            return;
        }
        B();
        this.C.D(0, z);
    }

    public final boolean E(int index) {
        if (index >= 64) {
            B();
            return this.C.E(index - 64);
        }
        long j = 1 << index;
        boolean z = (this.B & j) != 0;
        long mask = (-1) ^ j;
        this.B &= mask;
        long j2 = j - 1;
        long before = (-1) ^ j2;
        this.B = (this.B & j2) | Long.rotateRight(this.B & before, 1);
        if (this.C == null) {
            return z;
        }
        if (this.C.C(0)) {
            G(63);
        }
        this.C.E(0);
        return z;
    }

    public final void F() {
        this.B = 0L;
        if (this.C != null) {
            this.C.F();
        }
    }

    public final void G(int index) {
        if (index >= 64) {
            B();
            this.C.G(index - 64);
        } else {
            this.B |= 1 << index;
        }
    }

    public final String toString() {
        return this.C == null ? Long.toBinaryString(this.B) : this.C.toString() + "xx" + Long.toBinaryString(this.B);
    }
}
