package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class B9 implements Runnable {
    public final /* synthetic */ B8 B;

    public B9(B8 this$0) {
        this.B = this$0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.B.F();
        if (this.B.K) {
            this.B.O.postDelayed(this.B.P, 250L);
        }
    }
}
