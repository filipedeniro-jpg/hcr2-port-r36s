package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class EK implements Runnable {
    public final /* synthetic */ C0503Es B;

    public EK(C0503Es this$0) {
        this.B = this$0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.B.J || this.B.isLayoutRequested()) {
            return;
        }
        if (!this.B.M) {
            this.B.requestLayout();
        } else if (this.B.S) {
            this.B.T = true;
        } else {
            this.B.K();
        }
    }
}
