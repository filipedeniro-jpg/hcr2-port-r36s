package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class EQ implements DH {
    public final /* synthetic */ C0503Es B;

    public EQ(C0503Es this$0) {
        this.B = this$0;
    }

    private final void B(DI op) {
        switch (op.B) {
            case 1:
                this.B.R.TA(this.B, op.E, op.C);
                break;
            case 2:
                this.B.R.WA(this.B, op.E, op.C);
                break;
            case 4:
                this.B.R.XA(this.B, op.E, op.C, op.D);
                break;
            case 8:
                this.B.R.VA(this.B, op.E, op.C, 1);
                break;
        }
    }

    @Override // com.facebook.ads.redexgen.X.DH
    public final void FD(int positionStart, int itemCount, Object payload) {
        this.B.B(positionStart, itemCount, payload);
        this.B.Q = true;
    }

    @Override // com.facebook.ads.redexgen.X.DH
    public final void ID(int positionStart, int itemCount) {
        this.B.i(positionStart, itemCount);
        this.B.P = true;
    }

    @Override // com.facebook.ads.redexgen.X.DH
    public final void JD(int from, int to) {
        this.B.j(from, to);
        this.B.P = true;
    }

    @Override // com.facebook.ads.redexgen.X.DH
    public final void KD(int start, int count) {
        this.B.k(start, count, true);
        this.B.P = true;
        this.B.Z.B += count;
    }

    @Override // com.facebook.ads.redexgen.X.DH
    public final void LD(int positionStart, int itemCount) {
        this.B.k(positionStart, itemCount, false);
        this.B.P = true;
    }

    @Override // com.facebook.ads.redexgen.X.DH
    public final AbstractC0502Er rB(int position) {
        AbstractC0502Er abstractC0502ErW = this.B.W(position, true);
        if (abstractC0502ErW == null || this.B.F.L(abstractC0502ErW.B)) {
            return null;
        }
        return abstractC0502ErW;
    }

    @Override // com.facebook.ads.redexgen.X.DH
    public final void rD(DI op) {
        B(op);
    }

    @Override // com.facebook.ads.redexgen.X.DH
    public final void tD(DI op) {
        B(op);
    }
}
