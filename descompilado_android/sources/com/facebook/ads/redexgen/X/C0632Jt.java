package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jt, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0632Jt implements C1D {
    public final /* synthetic */ C0638Jz B;

    public C0632Jt(C0638Jz this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.C1D
    public final void FE(boolean success) {
        this.B.D.set(success);
        if (!this.B.M.get() || this.B.F == null) {
            return;
        }
        this.B.F.oE(success);
    }
}
