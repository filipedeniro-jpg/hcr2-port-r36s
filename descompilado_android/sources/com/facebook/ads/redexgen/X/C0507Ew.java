package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ew, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0507Ew extends AbstractC0463De {
    public boolean B = false;
    public final /* synthetic */ EA C;

    public C0507Ew(EA this$0) {
        this.C = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0463De
    public final void A(C0503Es recyclerView, int newState) {
        super.A(recyclerView, newState);
        if (newState == 0 && this.B) {
            this.B = false;
            this.C.E();
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0463De
    public final void B(C0503Es recyclerView, int dx, int dy) {
        if (dx != 0 || dy != 0) {
            this.B = true;
        }
    }
}
