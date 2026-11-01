package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class AB implements Runnable {
    public final /* synthetic */ A3 B;

    public AB(A3 this$0) {
        this.B = this$0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.B.Q != null && this.B.S != null) {
            this.B.Q.setCloseButtonStyle(F2.CROSS);
            this.B.Q.setToolbarListener(this.B.S);
            this.B.Q.E(true);
            this.B.Q.J(0, true);
            this.B.Q.setToolbarActionMessage("");
        }
    }
}
