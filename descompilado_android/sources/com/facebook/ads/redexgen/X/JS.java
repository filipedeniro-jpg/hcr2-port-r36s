package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class JS implements Runnable {
    public final /* synthetic */ JU B;

    public JS(JU this$0) {
        this.B = this$0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.B.N = false;
        if (!this.B.K.getQueue().isEmpty()) {
            return;
        }
        this.B.K.execute(this.B.J);
    }
}
