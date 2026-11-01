package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0577Hp implements InterfaceC0528Fr {
    public final /* synthetic */ C0567Hf B;

    public C0577Hp(C0567Hf this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0528Fr
    public final boolean QC() {
        if (!this.B.M.canGoBack()) {
            return false;
        }
        this.B.M.goBack();
        return true;
    }
}
