package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class JR implements Runnable {
    public final /* synthetic */ JU B;

    public JR(JU this$0) {
        this.B = this$0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        JU.B(this.B);
        if (this.B.C > 0) {
            try {
                Thread.sleep(this.B.C);
            } catch (InterruptedException e) {
            }
        }
        this.B.C();
    }
}
