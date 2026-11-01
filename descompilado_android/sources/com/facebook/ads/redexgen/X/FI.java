package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FI implements ET {
    public final /* synthetic */ FP B;

    public FI(FP this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.ET
    public final int EE(int childCount, int i) {
        if (this.B.L == null) {
            return i;
        }
        int i2 = this.B.M;
        if (i2 == -1) {
            i2 = this.B.P.indexOfChild(this.B.L);
            this.B.M = i2;
        }
        if (i == childCount - 1) {
            return i2;
        }
        return i >= i2 ? i + 1 : i;
    }
}
