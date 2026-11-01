package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class GP extends GO {
    public final /* synthetic */ GQ B;

    public GP(GQ this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass75 event) {
        if (this.B.B != null) {
            ((KF) this.B.B.getInternalNativeAd()).Q(false, true);
        }
        this.B.F.onError();
    }
}
