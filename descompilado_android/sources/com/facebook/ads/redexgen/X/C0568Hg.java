package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0568Hg implements InterfaceC0569Hh {
    public final /* synthetic */ C0567Hf B;

    public C0568Hg(C0567Hf this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0569Hh
    public final void kE(String url) {
        this.B.L.setProgress(100);
        this.B.J = false;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0569Hh
    public final void mE(String url) {
        this.B.J = true;
        this.B.G.setUrl(url);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0569Hh
    public final void vE(int progress) {
        if (this.B.J) {
            this.B.L.setProgress(progress);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0569Hh
    public final void xE(String title) {
        this.B.G.setTitle(title);
    }
}
