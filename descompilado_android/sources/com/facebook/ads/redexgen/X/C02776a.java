package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6a, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C02776a extends AbstractC02976u {
    public final /* synthetic */ C6Z B;

    public C02776a(C6Z this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(C02986v event) {
        if (this.B.L != null && !this.B.K && event.A().getAction() == 0) {
            this.B.D.removeCallbacksAndMessages(null);
            if (this.B.N(EnumC02595i.REVERSE_ANIMATED)) {
                this.B.M();
                this.B.O(true, false);
            }
            if (!this.B.J) {
                return;
            }
            this.B.D.postDelayed(new Runnable() { // from class: com.facebook.ads.redexgen.X.6b
                @Override // java.lang.Runnable
                public final void run() {
                    this.B.B.M();
                    this.B.B.O(true, true);
                }
            }, this.B.C);
        }
    }
}
