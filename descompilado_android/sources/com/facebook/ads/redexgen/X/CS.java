package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class CS implements Runnable {
    public final /* synthetic */ CO B;

    public CS(CO this$0) {
        this.B = this$0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.B.R) {
            this.B.f("Video was never prepared");
        }
    }
}
