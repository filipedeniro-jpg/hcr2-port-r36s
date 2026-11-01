package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class EL implements Runnable {
    public final /* synthetic */ C0503Es B;

    public EL(C0503Es this$0) {
        this.B = this$0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.B.N != null) {
            this.B.N.U();
        }
        this.B.V = false;
    }
}
