package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0471Dm implements InterfaceC0528Fr {
    public final /* synthetic */ C0465Dg B;
    public final /* synthetic */ C0531Fu C;

    public C0471Dm(C0465Dg this$0, C0531Fu c0531Fu) {
        this.B = this$0;
        this.C = c0531Fu;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0528Fr
    public final boolean QC() {
        if (this.B.H()) {
            return true;
        }
        if (this.B.C != null && this.B.C.A()) {
            return true;
        }
        if (this.B.E()) {
            this.B.G(this.C);
            return true;
        }
        return false;
    }
}
