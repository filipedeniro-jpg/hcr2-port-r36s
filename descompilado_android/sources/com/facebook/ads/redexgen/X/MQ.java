package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class MQ implements Runnable {
    public final /* synthetic */ MS B;

    public MQ(MS this$0) {
        this.B = this$0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.B.C()) {
            return;
        }
        this.B.D();
        this.B.D.postDelayed(this, 1000L);
    }
}
