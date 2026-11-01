package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FY implements Runnable {
    public final /* synthetic */ FZ B;

    public FY(FZ this$1) {
        this.B = this$1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.B.B.B) {
            return;
        }
        this.B.B.J();
    }
}
