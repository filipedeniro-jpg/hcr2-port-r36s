package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6d, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C02806d extends GE {
    public final /* synthetic */ C6Z B;

    public C02806d(C6Z this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass71 event) {
        if (this.B.K && this.B.J) {
            this.B.K = false;
            if (!this.B.N(EnumC02595i.ANIMATING) && !this.B.M) {
                if (this.B.N(EnumC02595i.ANIMATED)) {
                    this.B.M();
                    this.B.O(true, true);
                    return;
                }
                return;
            }
            this.B.M = false;
            this.B.D.postDelayed(new Runnable() { // from class: com.facebook.ads.redexgen.X.6e
                @Override // java.lang.Runnable
                public final void run() {
                    this.B.B.M();
                    this.B.B.O(true, true);
                }
            }, this.B.C);
        }
    }
}
