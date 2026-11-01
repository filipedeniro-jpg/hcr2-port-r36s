package com.facebook.ads.redexgen.X;

import java.util.Arrays;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0474Dp implements InterfaceC0473Do {
    public int B;
    public int[] C;
    public int D;
    public int E;

    public final void A() {
        if (this.C != null) {
            Arrays.fill(this.C, -1);
        }
        this.B = 0;
    }

    public final void B(C0503Es view, boolean nested) {
        this.B = 0;
        if (this.C != null) {
            Arrays.fill(this.C, -1);
        }
        AbstractC0481Dw abstractC0481Dw = view.R;
        if (view.C != null && abstractC0481Dw != null && abstractC0481Dw.DA()) {
            if (nested) {
                if (!view.D.F()) {
                    abstractC0481Dw.K(view.C.C(), this);
                }
            } else if (!view.m248c()) {
                abstractC0481Dw.J(this.D, this.E, view.Z, this);
            }
            if (this.B > abstractC0481Dw.F) {
                abstractC0481Dw.F = this.B;
                abstractC0481Dw.G = nested;
                view.X.a();
            }
        }
    }

    public final boolean C(int position) {
        if (this.C != null) {
            int i = this.B * 2;
            for (int i2 = 0; i2 < i; i2 += 2) {
                if (this.C[i2] == position) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void D(int dx, int dy) {
        this.D = dx;
        this.E = dy;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0473Do
    public final void TB(int layoutPosition, int pixelDistance) {
        if (layoutPosition < 0) {
            throw new IllegalArgumentException("Layout positions must be non-negative");
        }
        if (pixelDistance < 0) {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
        int i = this.B * 2;
        if (this.C == null) {
            this.C = new int[4];
            Arrays.fill(this.C, -1);
        } else if (i >= this.C.length) {
            int[] iArr = this.C;
            this.C = new int[i * 2];
            System.arraycopy(iArr, 0, this.C, 0, iArr.length);
        }
        this.C[i] = layoutPosition;
        this.C[i + 1] = pixelDistance;
        this.B++;
    }
}
