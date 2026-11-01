package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0635Jw extends C1B {
    public final /* synthetic */ C0638Jz B;

    public C0635Jw(C0638Jz this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.C1B
    public final void A() {
        if (this.B.I == null) {
            return;
        }
        this.B.I.C();
    }

    @Override // com.facebook.ads.redexgen.X.C1B
    public final void B() {
        if (this.B.I == null) {
            return;
        }
        if (!this.B.L && (this.B.K || this.B.X())) {
            this.B.U(C7A.AUTO_STARTED);
        }
        this.B.K = false;
        this.B.L = false;
    }
}
